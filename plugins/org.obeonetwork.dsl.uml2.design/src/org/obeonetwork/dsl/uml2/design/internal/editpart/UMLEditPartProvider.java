/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.internal.editpart;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.CreateGraphicEditPartOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.IEditPartOperation;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.obeonetwork.dsl.uml2.design.internal.services.AssociationServices;
import org.obeonetwork.dsl.uml2.design.internal.services.EditLabelSwitch;

/**
 * UML edit part provider.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
@SuppressWarnings("restriction")
public class UMLEditPartProvider extends AbstractEditPartProvider {

	class UMLDirectEditForBeginRole extends LabelDirectEditPolicy {

		@Override
		protected org.eclipse.gef.commands.Command getDirectEditCommand(
				org.eclipse.gef.requests.DirectEditRequest edit) {
			final EObject element = ((org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart)getHost())
					.resolveSemanticElement();
			final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(element);
			final String labelText = (String)edit.getCellEditor().getValue();
			final RecordingCommand cmd = new RecordingCommand(domain) {

				@Override
				protected void doExecute() {
					if (element instanceof DSemanticDecorator) {
						final EObject target = ((DSemanticDecorator)element).getTarget();
						if (target instanceof Association) {
							final Property end = AssociationServices.INSTANCE.getSource((Association)target);
							if (end != null) {
								final EditLabelSwitch swch = new EditLabelSwitch();
								swch.setEditedLabelContent(labelText);
								swch.caseRole(end);
							}
						}

					}
				}
			};
			return new ICommandProxy(new GMFCommandWrapper(domain, cmd));

		}

	}

	class UMLDirectEditForEndRole extends LabelDirectEditPolicy {

		@Override
		protected org.eclipse.gef.commands.Command getDirectEditCommand(
				org.eclipse.gef.requests.DirectEditRequest edit) {
			final EObject element = ((org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart)getHost())
					.resolveSemanticElement();
			final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(element);
			final String labelText = (String)edit.getCellEditor().getValue();
			final RecordingCommand cmd = new RecordingCommand(domain) {

				@Override
				protected void doExecute() {
					if (element instanceof DSemanticDecorator) {
						final EObject target = ((DSemanticDecorator)element).getTarget();

						if (target instanceof Association) {
							final Property end = AssociationServices.INSTANCE.getTarget((Association)target);
							if (end != null) {
								final EditLabelSwitch swch = new EditLabelSwitch();
								swch.setEditedLabelContent(labelText);
								swch.caseRole(end);
							}
						}

					}
				}
			};
			return new ICommandProxy(new GMFCommandWrapper(domain, cmd));

		}

	}

	class UMLDirectEditForLabel extends LabelDirectEditPolicy {

		@Override
		protected org.eclipse.gef.commands.Command getDirectEditCommand(
				org.eclipse.gef.requests.DirectEditRequest edit) {
			final EObject element = ((org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart)getHost())
					.resolveSemanticElement();
			final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(element);
			final String labelText = (String)edit.getCellEditor().getValue();
			final RecordingCommand cmd = new RecordingCommand(domain) {

				@Override
				protected void doExecute() {
					if (element instanceof DSemanticDecorator) {
						final EObject target = ((DSemanticDecorator)element).getTarget();
						if (target instanceof Association) {
							((NamedElement)target).setName(labelText);
						}
					}
				}
			};
			return new ICommandProxy(new GMFCommandWrapper(domain, cmd));

		}
	}

	/**
	 * @generated
	 */
	@Override
	public synchronized IGraphicalEditPart createGraphicEditPart(View view) {
		switch (SiriusVisualIDRegistry.getVisualID(view)) {

			case DEdgeBeginNameEditPart.VISUAL_ID:
				final DEdgeBeginNameEditPart dEdgeBeginPart = new DEdgeBeginNameEditPart(view) {

					@Override
					protected boolean isDirectEditEnabled() {
						return true;
					}

				};
				dEdgeBeginPart.installEditPolicy(org.eclipse.gef.RequestConstants.REQ_DIRECT_EDIT,
						new UMLDirectEditForBeginRole());
				return dEdgeBeginPart;

			case DEdgeEndNameEditPart.VISUAL_ID:
				final DEdgeEndNameEditPart dEdgeEndPart = new DEdgeEndNameEditPart(view) {
					@Override
					protected boolean isDirectEditEnabled() {
						return true;
					}

				};
				dEdgeEndPart.installEditPolicy(org.eclipse.gef.RequestConstants.REQ_DIRECT_EDIT,
						new UMLDirectEditForEndRole());
				return dEdgeEndPart;

			case DEdgeNameEditPart.VISUAL_ID:
				final DEdgeNameEditPart dEdgePart = new DEdgeNameEditPart(view) {
					@Override
					protected boolean isDirectEditEnabled() {
						return true;
					}

				};
				dEdgePart.installEditPolicy(org.eclipse.gef.RequestConstants.REQ_DIRECT_EDIT,
						new UMLDirectEditForLabel());
				return dEdgePart;
			default:
				break;
		}
		return null;
	}

	@Override
	public boolean provides(IOperation operation) {
		if (operation instanceof CreateGraphicEditPartOperation) {
			final View view = ((IEditPartOperation)operation).getView();
			if (view.getElement() instanceof DSemanticDecorator) {
				if (((DSemanticDecorator)view.getElement()).getTarget() instanceof Element) {
					switch (SiriusVisualIDRegistry.getVisualID(view)) {

						case DEdgeBeginNameEditPart.VISUAL_ID:
							return true;

						case DEdgeEndNameEditPart.VISUAL_ID:
							return true;

						case DEdgeNameEditPart.VISUAL_ID:
							return true;
						default:
							break;
					}
				}
			}

		}
		return false;
	}

}
