package org.obeonetwork.dsl.uml2.design.ui.extension.editpart;

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
import org.obeonetwork.dsl.uml2.design.services.ClassDiagramServices;
import org.obeonetwork.dsl.uml2.design.services.internal.EditLabelSwitch;

public class UMLEditPartProvider extends AbstractEditPartProvider {

	/**
	 * @generated
	 */
	public synchronized IGraphicalEditPart createGraphicEditPart(View view) {
		switch (SiriusVisualIDRegistry.getVisualID(view)) {

			case DEdgeBeginNameEditPart.VISUAL_ID:
				DEdgeBeginNameEditPart dEdgeBeginPart = new DEdgeBeginNameEditPart(view) {

					@Override
					protected boolean isDirectEditEnabled() {
						return true;
					}

				};
				dEdgeBeginPart.installEditPolicy(org.eclipse.gef.RequestConstants.REQ_DIRECT_EDIT,
						new UMLDirectEditForBeginRole());
				return dEdgeBeginPart;

			case DEdgeEndNameEditPart.VISUAL_ID:
				DEdgeEndNameEditPart dEdgeEndPart = new DEdgeEndNameEditPart(view) {
					@Override
					protected boolean isDirectEditEnabled() {
						return true;
					}

				};
				dEdgeEndPart.installEditPolicy(org.eclipse.gef.RequestConstants.REQ_DIRECT_EDIT,
						new UMLDirectEditForEndRole());
				return dEdgeEndPart;

			case DEdgeNameEditPart.VISUAL_ID:
				DEdgeNameEditPart dEdgePart = new DEdgeNameEditPart(view) {
					@Override
					protected boolean isDirectEditEnabled() {
						return true;
					}

				};
				dEdgePart.installEditPolicy(org.eclipse.gef.RequestConstants.REQ_DIRECT_EDIT,
						new UMLDirectEditForLabel());
				return dEdgePart;
		}
		return null;
	}

	@Override
	public boolean provides(IOperation operation) {
		if (operation instanceof CreateGraphicEditPartOperation) {
			View view = ((IEditPartOperation)operation).getView();
			if (view.getElement() instanceof DSemanticDecorator) {
				if (((DSemanticDecorator)view.getElement()).getTarget() instanceof Element) {
					switch (SiriusVisualIDRegistry.getVisualID(view)) {

						case DEdgeBeginNameEditPart.VISUAL_ID:
							return true;

						case DEdgeEndNameEditPart.VISUAL_ID:
							return true;

						case DEdgeNameEditPart.VISUAL_ID:
							return true;
					}
				}
			}

		}
		return false;
	}

	class UMLDirectEditForLabel extends LabelDirectEditPolicy {

		protected org.eclipse.gef.commands.Command getDirectEditCommand(
				org.eclipse.gef.requests.DirectEditRequest edit) {
			final EObject element = ((org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart)getHost())
					.resolveSemanticElement();
			final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(element);
			final String labelText = (String)edit.getCellEditor().getValue();
			RecordingCommand cmd = new RecordingCommand(domain) {

				@Override
				protected void doExecute() {
					if (element instanceof DSemanticDecorator) {
						EObject target = ((DSemanticDecorator)element).getTarget();
						if (target instanceof Association) {
							((NamedElement)target).setName(labelText);
						}
					}
				}
			};
			return new ICommandProxy(new GMFCommandWrapper(domain, cmd));

		};

	}

	class UMLDirectEditForBeginRole extends LabelDirectEditPolicy {

		protected org.eclipse.gef.commands.Command getDirectEditCommand(
				org.eclipse.gef.requests.DirectEditRequest edit) {
			final EObject element = ((org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart)getHost())
					.resolveSemanticElement();
			final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(element);
			final String labelText = (String)edit.getCellEditor().getValue();
			RecordingCommand cmd = new RecordingCommand(domain) {

				@Override
				protected void doExecute() {
					if (element instanceof DSemanticDecorator) {
						EObject target = ((DSemanticDecorator)element).getTarget();
						if (target instanceof Association) {
							Property end = new ClassDiagramServices().getSource((Association)target);
							if (end != null) {
								EditLabelSwitch swch = new EditLabelSwitch();
								swch.setEditedLabelContent(labelText);
								swch.caseRole(end);
							}
						}

					}
				}
			};
			return new ICommandProxy(new GMFCommandWrapper(domain, cmd));

		};

	}

	class UMLDirectEditForEndRole extends LabelDirectEditPolicy {

		protected org.eclipse.gef.commands.Command getDirectEditCommand(
				org.eclipse.gef.requests.DirectEditRequest edit) {
			final EObject element = ((org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart)getHost())
					.resolveSemanticElement();
			final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(element);
			final String labelText = (String)edit.getCellEditor().getValue();
			RecordingCommand cmd = new RecordingCommand(domain) {

				@Override
				protected void doExecute() {
					if (element instanceof DSemanticDecorator) {
						EObject target = ((DSemanticDecorator)element).getTarget();
						if (target instanceof Association) {
							Property end = new ClassDiagramServices().getTarget((Association)target);
							if (end != null) {
								EditLabelSwitch swch = new EditLabelSwitch();
								swch.setEditedLabelContent(labelText);
								swch.caseRole(end);
							}
						}

					}
				}
			};
			return new ICommandProxy(new GMFCommandWrapper(domain, cmd));

		};

	}

}
