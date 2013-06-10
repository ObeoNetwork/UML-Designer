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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;
import org.obeonetwork.dsl.uml2.design.services.internal.ContainerSwitch;
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
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
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
	public void paste(final Element container, final NamedElement semanticElement,
			final DSemanticDecorator containerView) {
		// Attach the copiedElement to its the selected container
		setContainer(semanticElement, container);

		// Change the name to show that it is a copy
		semanticElement.setName(new LabelServices().computeUmlLabel(semanticElement) + "_Copy");

		// Create the view for the pasted element
		final Session session = SessionManager.INSTANCE.getSession(container);
		createView(semanticElement, containerView, session);
	}

	/**
	 * Attached semantic element to the given container.
	 * 
	 * @param semanticElement
	 *            Semantic element
	 * @param context
	 *            Container
	 */
	private void setContainer(EObject semanticElement, EObject context) {
		final ContainerSwitch container = new ContainerSwitch();
		container.setContainment(semanticElement);
		container.doSwitch(context);
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
	 */
	private void createView(final EObject semanticElement, final DSemanticDecorator containerView,
			final Session session) {
		// Get all available mappings applicable for the copiedElement in the current container
		List<DiagramElementMapping> semanticElementMappings = getMappings(semanticElement, containerView,
				session);

		// Build a createView tool
		final CreateView createViewOp = ToolFactory.eINSTANCE.createCreateView();
		for (DiagramElementMapping copiedElementMapping : semanticElementMappings) {
			final DiagramElementMapping tmpCopiedElementMapping = copiedElementMapping;
			createViewOp.setMapping(tmpCopiedElementMapping);
			createViewOp.setContainerViewExpression("<%$containerView%>");

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
								new CreateViewTask(context, session.getModelAccessor(),
										tmpCopiedElementMapping, "<%$containerView%>", createViewOp, session
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
	 * @return List of mappings
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
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)) {
					mappings.add(mapping);
				}
			}
			for (DiagramElementMapping mapping : (((DSemanticDiagram)containerView).getDescription()
					.getAllNodeMappings())) {
				String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)) {
					mappings.add(mapping);
				}
			}
		} else if (containerView instanceof DNodeContainerSpec) {
			for (DiagramElementMapping mapping : (((DNodeContainerSpec)containerView).getActualMapping()
					.getAllContainerMappings())) {
				String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)) {
					mappings.add(mapping);
				}
			}
			for (DiagramElementMapping mapping : (((DNodeContainerSpec)containerView).getActualMapping()
					.getAllNodeMappings())) {
				String domainClass = ((AbstractNodeMapping)mapping).getDomainClass();
				if (modelAccessor.eInstanceOf(semanticElement, domainClass)) {
					mappings.add(mapping);
				}
			}
		}
		return mappings;
	}
}
