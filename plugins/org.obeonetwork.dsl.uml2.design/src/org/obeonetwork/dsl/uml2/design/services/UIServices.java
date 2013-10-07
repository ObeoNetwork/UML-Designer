/*******************************************************************************
 * Copyright (c) 2011 Obeo.
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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;
import org.obeonetwork.dsl.uml2.design.ui.wizards.newmodel.Messages;

import fr.obeo.dsl.viewpoint.DDiagramElement;
import fr.obeo.dsl.viewpoint.DRepresentation;
import fr.obeo.dsl.viewpoint.DSemanticDecorator;
import fr.obeo.dsl.viewpoint.DSemanticDiagram;
import fr.obeo.dsl.viewpoint.business.api.dialect.DialectManager;
import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.business.api.session.SessionManager;
import fr.obeo.dsl.viewpoint.business.internal.helper.task.operations.CreateViewTask;
import fr.obeo.dsl.viewpoint.business.internal.metamodel.spec.DNodeContainerSpec;
import fr.obeo.dsl.viewpoint.business.internal.metamodel.spec.DSemanticDiagramSpec;
import fr.obeo.dsl.viewpoint.description.AbstractNodeMapping;
import fr.obeo.dsl.viewpoint.description.DiagramElementMapping;
import fr.obeo.dsl.viewpoint.description.tool.CreateView;
import fr.obeo.dsl.viewpoint.description.tool.ToolFactory;
import fr.obeo.dsl.viewpoint.tools.api.command.CommandContext;
import fr.obeo.mda.ecore.extender.business.api.accessor.ModelAccessor;
import fr.obeo.mda.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import fr.obeo.mda.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;

/**
 * Services to handle UI concerns.
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane .thibaudeau@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UIServices {

	/**
	 * Refresh a representation. This service has been created to handle a bug on the generic tool : the force
	 * refresh option does not work as expected
	 * 
	 * @param representation
	 *            Representation to be refreshed
	 * @return the specified representation
	 */
	public DRepresentation refreshDiagram(DRepresentation representation) {
		DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
		return representation;
	}

	/**
	 * Paste a semantic element and create the corresponding view in the given container
	 * 
	 * @param container
	 *            Semantic container
	 * @param semanticElement
	 *            Element to paste
	 * @param containerView
	 *            Container view
	 */
	public void paste(final Element container, final Element semanticElement,
			final DSemanticDecorator elementView, final DSemanticDecorator containerView) {
		// Paste the semantic element from the clipboard to the selected
		// container
		final Session session = SessionManager.INSTANCE.getSession(container);
		TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
		// The feature is set to null because the domain will deduce it
		Command cmd = AddCommand.create(domain, container, null, semanticElement);
		if (cmd.canExecute()) {
			cmd.execute();
		}
		// Create the view for the pasted element
		createView(semanticElement, containerView, session, "containerView");
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
	private void drop(final Element newContainer, final Element semanticElement,
			final DSemanticDecorator containerView, boolean moveSemanticElement) {
		final Session session = SessionManager.INSTANCE.getSession(newContainer);
		Element oldContainer = semanticElement.getOwner();
		if (moveSemanticElement && oldContainer != newContainer) {
			// Move the semantic element to the selected container
			TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
			// The feature is set to null because the domain will deduce it
			Command cmd = AddCommand.create(domain, newContainer, null, semanticElement);
			if (cmd.canExecute()) {
				cmd.execute();
			}
			cmd = RemoveCommand.create(domain, oldContainer, null, semanticElement);
			if (cmd.canExecute()) {
				cmd.execute();
			}

			if (semanticElement instanceof UseCase) {
				// Reset the current element as subject
				cmd = SetCommand.create(domain, semanticElement, UMLPackage.Literals.USE_CASE__SUBJECT,
						SetCommand.UNSET_VALUE);
				if (cmd.canExecute()) {
					cmd.execute();
				}
				List<Element> subjects = new ArrayList<Element>();
				subjects.add(newContainer);
				cmd = SetCommand.create(domain, semanticElement, UMLPackage.Literals.USE_CASE__SUBJECT,
						subjects);
				if (cmd.canExecute()) {
					cmd.execute();
				}
			}
		}

		// Create the view for the dropped element
		createView(semanticElement, containerView, session, "newContainerView");
	}

	/**
	 * Drop a semantic element from a diagram and create the corresponding view in the given container
	 * 
	 * @param newContainer
	 *            Semantic container
	 * @param semanticElement
	 *            Element to drop
	 * @param containerView
	 *            Container view
	 */
	public void dropFromDiagram(final Element newContainer, final Element semanticElement,
			final DSemanticDecorator containerView) {
		drop(newContainer, semanticElement, containerView, true);
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
	 */
	@SuppressWarnings("restriction")
	public void dropFromModel(final Element newContainer, final Element semanticElement,
			final DSemanticDecorator containerView) {
		drop(newContainer, semanticElement, containerView, !(containerView instanceof DSemanticDiagramSpec));
	}

	/**
	 * Check if a semantic element can be represented in a given container view.
	 * 
	 * @param container
	 *            Semantic container
	 * @param semanticElement
	 *            Element to test
	 * @param containerView
	 *            Container view
	 */
	public boolean isValidElementForContainerView(final Element container,
			final NamedElement semanticElement, final DSemanticDecorator containerView) {
		final Session session = SessionManager.INSTANCE.getSession(container);

		// Get all available mappings applicable for the selected element in the
		// current container
		List<DiagramElementMapping> semanticElementMappings = getMappings(semanticElement, containerView,
				session);

		return semanticElementMappings.size() > 0;
	}

	/**
	 * Check if an element is a container.
	 * 
	 * @param container
	 *            Semantic container
	 * @param containerView
	 *            Container view
	 */
	public boolean existValidElementsForContainerView(final Element container,
			final DSemanticDecorator containerView) {
		final Session session = SessionManager.INSTANCE.getSession(container);

		// Get all available mappings applicable for the selected element in the
		// current container
		List<DiagramElementMapping> semanticElementMappings = getMappings(containerView, session);

		return semanticElementMappings.size() > 0;
	}

	public void addExistingElements(final EObject containerView, final List<EObject> semanticElementList) {
		if (!(containerView instanceof DSemanticDecorator) || semanticElementList == null
				|| semanticElementList.isEmpty())
			return;
		final Session session = SessionManager.INSTANCE.getSession(semanticElementList.get(0));
		for (EObject semanticElement : semanticElementList) {
			// Create the view for the added element
			createView(semanticElement, (DSemanticDecorator)containerView, session, "containerView");
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
	private void createView(final EObject semanticElement, final DSemanticDecorator containerView,
			final Session session, final String containerViewVariable) {
		// Get all available mappings applicable for the copiedElement in the
		// current container
		List<DiagramElementMapping> semanticElementMappings = getMappings(semanticElement, containerView,
				session);

		// Build a createView tool
		final CreateView createViewOp = ToolFactory.eINSTANCE.createCreateView();
		for (DiagramElementMapping copiedElementMapping : semanticElementMappings) {
			final DiagramElementMapping tmpCopiedElementMapping = copiedElementMapping;
			createViewOp.setMapping(tmpCopiedElementMapping);
			final String containerViewExpression = "<%$" + containerViewVariable + "%>";
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

								final CommandContext context = new CommandContext(semanticElement,
										representation);

								// Execute the create view task
								new CreateViewTask(context, session.getModelAccessor(), createViewOp, session
										.getInterpreter()).execute();
							} catch (MetaClassNotFoundException e) {
								UMLDesignerPlugin.log(IStatus.ERROR, Messages
										.bind(Messages.UmlModelWizard_UI_ErrorMsg_BadFileExtension,
												semanticElement), e);
							} catch (FeatureNotFoundException e) {
								UMLDesignerPlugin.log(IStatus.ERROR, Messages
										.bind(Messages.UmlModelWizard_UI_ErrorMsg_BadFileExtension,
												semanticElement), e);
							}
						}
					});
		}
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

			for (DiagramElementMapping mapping : (((DSemanticDiagram)containerView).getDescription()
					.getAllContainerMappings())) {
				String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)
						&& !mapping.isSynchronizationLock()) {
					mappings.add(mapping);
				}
			}
			for (DiagramElementMapping mapping : (((DSemanticDiagram)containerView).getDescription()
					.getAllNodeMappings())) {
				String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)
						&& !mapping.isSynchronizationLock()) {
					mappings.add(mapping);
				}
			}
		} else if (containerView instanceof DNodeContainerSpec) {
			for (DiagramElementMapping mapping : (((DNodeContainerSpec)containerView).getActualMapping()
					.getAllContainerMappings())) {
				String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)
						&& !mapping.isSynchronizationLock()) {
					mappings.add(mapping);
				}
			}
			for (DiagramElementMapping mapping : (((DNodeContainerSpec)containerView).getActualMapping()
					.getAllNodeMappings())) {
				String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)
						&& !mapping.isSynchronizationLock()) {
					mappings.add(mapping);
				}
			}
		}
		return mappings;
	}

	/**
	 * Get mappings available for a given container view.
	 * 
	 * @param containerView
	 *            Container view
	 * @param session
	 *            Session
	 * @return List of mappings which could not be null
	 */
	@SuppressWarnings("restriction")
	private List<DiagramElementMapping> getMappings(final DSemanticDecorator containerView, Session session) {
		List<DiagramElementMapping> mappings = new ArrayList<DiagramElementMapping>();

		if (containerView instanceof DSemanticDiagram) {

			for (DiagramElementMapping mapping : (((DSemanticDiagram)containerView).getDescription()
					.getAllContainerMappings())) {
				if (!mapping.isSynchronizationLock()) {
					mappings.add(mapping);
				}
			}
			for (DiagramElementMapping mapping : (((DSemanticDiagram)containerView).getDescription()
					.getAllNodeMappings())) {
				if (!mapping.isSynchronizationLock()) {
					mappings.add(mapping);
				}
			}
		} else if (containerView instanceof DNodeContainerSpec) {
			for (DiagramElementMapping mapping : (((DNodeContainerSpec)containerView).getActualMapping()
					.getAllContainerMappings())) {
				if (!mapping.isSynchronizationLock()) {
					mappings.add(mapping);
				}
			}
			for (DiagramElementMapping mapping : (((DNodeContainerSpec)containerView).getActualMapping()
					.getAllNodeMappings())) {
				if (!mapping.isSynchronizationLock()) {
					mappings.add(mapping);
				}
			}
		}
		return mappings;
	}
}
