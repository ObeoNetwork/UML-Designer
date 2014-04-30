/*******************************************************************************
 * Copyright (c) 2009, 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.internal.helper.task.operations.CreateViewTask;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeContainerSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DSemanticDiagramSpec;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.CreateView;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ComponentRealization;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Usage;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;
import org.obeonetwork.dsl.uml2.design.ui.wizards.newmodel.Messages;

/**
 * A set of services to handle graphically Component diagram actions and tests.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class UIComponentServices {

	/**
	 * Create a component realization.
	 * 
	 * @param realizingClassifier
	 *            Classifier that realizes the component
	 * @param abstraction
	 *            the component realized
	 * @return the component realization
	 */
	public ComponentRealization createComponentRealization(Classifier realizingClassifier,
			Component abstraction) {
		ComponentRealization result = abstraction.createRealization(realizingClassifier.getName() + "To"
				+ abstraction.getName());
		result.getRealizingClassifiers().add(realizingClassifier);
		return result;
	}

	/**
	 * Boolean query for create Dependency Connection Start Precondition.
	 * 
	 * @param preSource
	 *            the source
	 * @param preSourceView
	 *            the source view
	 * @param container
	 *            the container
	 * @param diagram
	 *            the diagram
	 * @return true if valid source
	 */
	public boolean createDependencyConnectionStartPrecondition(Element preSource,
			DSemanticDecorator preSourceView, Element container, DSemanticDiagram diagram) {
		boolean fromClassOrPort = preSource instanceof org.eclipse.uml2.uml.Class
				|| preSource instanceof Port;
		boolean fromInterface = preSource instanceof org.eclipse.uml2.uml.Interface;
		return fromClassOrPort || fromInterface;
	}

	/**
	 * Boolean query for create Dependency Connection Complete Precondition.
	 * 
	 * @param preSource
	 *            the source
	 * @param preSourceView
	 *            the source view
	 * @param preTarget
	 *            the target
	 * @param preTargetView
	 *            the target view
	 * @param container
	 *            the container
	 * @param diagram
	 *            the diagram
	 * @return true if valid source and target
	 */
	public boolean createDependencyConnectionCompletePrecondition(Element preSource,
			DSemanticDecorator preSourceView, Element preTarget, DSemanticDecorator preTargetView,
			Element container, DSemanticDiagram diagram) {

		boolean fromInterfaceToClassOrPort = preSource instanceof org.eclipse.uml2.uml.Interface
				&& (preTarget instanceof org.eclipse.uml2.uml.Class || preTarget instanceof Port);

		boolean fromClassOrPortToInterface = (preSource instanceof org.eclipse.uml2.uml.Class || preSource instanceof Port)
				&& preTarget instanceof org.eclipse.uml2.uml.Interface;

		return fromInterfaceToClassOrPort || fromClassOrPortToInterface;
	}

	/**
	 * Drop a semantic element from a diagram and create the corresponding view in the given container.
	 * 
	 * @param newContainer
	 *            Semantic container
	 * @param semanticElement
	 *            Element to drop
	 * @param containerView
	 *            Container view
	 */
	public void dropComponentFromDiagram(final Element newContainer, final NamedElement semanticElement,
			final DSemanticDecorator containerView) {
		new UIServices().dropFromDiagram(newContainer, semanticElement, containerView);
		drop(newContainer, semanticElement, containerView, true);
	}

	/**
	 * Drop a semantic element and create the corresponding view in the given container.
	 * 
	 * @param newContainer
	 *            Semantic container
	 * @param semanticElement
	 *            Element to drop
	 * @param containerView
	 *            Container view
	 */
	@SuppressWarnings("restriction")
	public void dropComponentFromModel(final Element newContainer, final NamedElement semanticElement,
			final DSemanticDecorator containerView) {
		new UIServices().dropFromModel(newContainer, semanticElement, containerView);
		drop(newContainer, semanticElement, containerView, !(containerView instanceof DSemanticDiagramSpec));
	}

	/**
	 * Drop a semantic element and create the corresponding view in the given container
	 * 
	 * @param newContainer
	 *            Semantic container
	 * @param semanticElement
	 *            Element to drop
	 * @param containerView
	 *            Container view
	 * @param moveSemanticElement
	 *            True to move the dropped semantic element or false to just show the element on a diagram
	 */
	private void drop(final Element newContainer, final NamedElement semanticElement,
			final DSemanticDecorator containerView, boolean moveSemanticElement) {
		if (semanticElement instanceof org.eclipse.uml2.uml.Class) {
			org.eclipse.uml2.uml.Class component = (org.eclipse.uml2.uml.Class)semanticElement;
			final Session session = SessionManager.INSTANCE.getSession(newContainer);
			Element oldContainer = component.getOwner();
			if (moveSemanticElement && oldContainer != newContainer) {
				// TODO check if something is needed here
			}

			// get all interfaces related to this component
			List<Dependency> availableDependencies = new DependencyServices()
					.getAvailableDependencies(component);
			for (Dependency dependency : availableDependencies) {
				if (dependency instanceof InterfaceRealization) {
					for (NamedElement namedElement : dependency.getSuppliers()) {
						createProvidedInterfaceView(namedElement, containerView, session, "newContainerView");
					}
				} else if (dependency instanceof Usage) {
					for (NamedElement namedElement : dependency.getSuppliers()) {
						createRequireInterfaceView(namedElement, containerView, session, "newContainerView");
					}
				}
			}
		}
	}

	/**
	 * Create view.
	 * 
	 * @param semanticElement
	 *            Semantic element
	 * @param containerView
	 *            Container view
	 * @param session
	 *            Session
	 * @param containerViewVariable
	 *            Name of the container view variable
	 */
	private void createProvidedInterfaceView(final EObject semanticElement,
			final DSemanticDecorator containerView, final Session session, final String containerViewVariable) {
		// Get all available mappings applicable for the copiedElement in the
		// current container
		List<DiagramElementMapping> semanticElementMappings = getMappings(semanticElement, containerView,
				session);
		// The mapping position is related to the vsm.
		createView(semanticElement, semanticElementMappings.get(0), containerView, session,
				containerViewVariable);
	}

	/**
	 * Create view.
	 * 
	 * @param semanticElement
	 *            Semantic element
	 * @param containerView
	 *            Container view
	 * @param session
	 *            Session
	 * @param containerViewVariable
	 *            Name of the container view variable
	 */
	private void createRequireInterfaceView(final EObject semanticElement,
			final DSemanticDecorator containerView, final Session session, final String containerViewVariable) {
		// Get all available mappings applicable for the copiedElement in the
		// current container
		List<DiagramElementMapping> semanticElementMappings = getMappings(semanticElement, containerView,
				session);
		// The mapping position is related to the vsm.
		createView(semanticElement, semanticElementMappings.get(1), containerView, session,
				containerViewVariable);
	}

	/**
	 * Create view.
	 * 
	 * @param semanticElement
	 *            Semantic element
	 * @param containerView
	 *            Container view
	 * @param session
	 *            Session
	 * @param containerViewVariable
	 *            Name of the container view variable
	 */
	private void createView(final EObject semanticElement, DiagramElementMapping semanticElementMapping,
			final DSemanticDecorator containerView, final Session session, final String containerViewVariable) {
		// Build a createView tool
		final CreateView createViewOp = ToolFactory.eINSTANCE.createCreateView();
		final DiagramElementMapping tmpCopiedElementMapping = semanticElementMapping;
		createViewOp.setMapping(tmpCopiedElementMapping);
		final String containerViewExpression = "var:" + containerViewVariable;
		createViewOp.setContainerViewExpression(containerViewExpression);

		session.getTransactionalEditingDomain().getCommandStack()
				.execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

					@SuppressWarnings("restriction")
					@Override
					protected void doExecute() {
						try {
							// Get the command context
							DRepresentation representation = null;
							if (containerView instanceof DRepresentation) {
								representation = (DRepresentation)containerView;
							} else if (containerView instanceof DDiagramElement) {
								representation = ((DDiagramElement)containerView).getParentDiagram();
							}

							final CommandContext context = new CommandContext(semanticElement, representation);

							// Execute the create view task
							new CreateViewTask(context, session.getModelAccessor(), createViewOp, session
									.getInterpreter()).execute();
						} catch (MetaClassNotFoundException e) {
							UMLDesignerPlugin.log(IStatus.ERROR, Messages.bind(
									Messages.UmlModelWizard_UI_ErrorMsg_BadFileExtension, semanticElement), e);
						} catch (FeatureNotFoundException e) {
							UMLDesignerPlugin.log(IStatus.ERROR, Messages.bind(
									Messages.UmlModelWizard_UI_ErrorMsg_BadFileExtension, semanticElement), e);
						}
					}
				});
	}

	/**
	 * Get mappings available for a semantic element and a given container view.
	 * 
	 * @param semanticElement
	 *            Semantic element
	 * @param containerView
	 *            Container view
	 * @param session
	 *            Session
	 * @return List of mappings which could not be null
	 */
	@SuppressWarnings("restriction")
	private List<DiagramElementMapping> getMappings(final EObject semanticElement,
			final DSemanticDecorator containerView, Session session) {
		ModelAccessor modelAccessor = session.getModelAccessor();
		List<DiagramElementMapping> mappings = new ArrayList<DiagramElementMapping>();

		if (containerView instanceof DSemanticDiagram) {

			for (DiagramElementMapping mapping : ((DSemanticDiagram)containerView).getDescription()
					.getAllContainerMappings()) {
				String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)
						&& !mapping.isSynchronizationLock()) {
					mappings.add(mapping);
				}
			}
			for (DiagramElementMapping mapping : ((DSemanticDiagram)containerView).getDescription()
					.getAllNodeMappings()) {
				String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)
						&& !mapping.isSynchronizationLock()) {
					mappings.add(mapping);
				}
			}
		} else if (containerView instanceof DNodeContainerSpec) {
			for (DiagramElementMapping mapping : ((DNodeContainerSpec)containerView).getActualMapping()
					.getAllContainerMappings()) {
				String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)
						&& !mapping.isSynchronizationLock()) {
					mappings.add(mapping);
				}
			}
			for (DiagramElementMapping mapping : ((DNodeContainerSpec)containerView).getActualMapping()
					.getAllNodeMappings()) {
				String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)
						&& !mapping.isSynchronizationLock()) {
					mappings.add(mapping);
				}
			}
		}
		return mappings;
	}
}
