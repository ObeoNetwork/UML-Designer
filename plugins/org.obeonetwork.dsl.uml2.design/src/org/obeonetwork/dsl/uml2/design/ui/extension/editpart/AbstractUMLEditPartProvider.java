package org.obeonetwork.dsl.uml2.design.ui.extension.editpart;

import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
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
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.collect.Lists;

/**
 * Abstraction between AbstractEditPartProvider and UMLEditPartProvider created in order to allow direct editing the cardinalities of associations in class diagrams.
 * <br>
 * Most of the code has been taken from org.eclipse.emf.ecoretools.design.parts.EcoreToolsSpecificEditPartProvider (in org.eclipse.emf.ecoretools 3.0.0) which is not (right now) in UMLDesigner.
 * <br>
 * In fine, org.eclipse.emf.ecoretools should probably be included in UML Designer or EcoreToolsSpecificEditPartProvider should be abstracted in a shared component. 
 * 
 * @author Christophe Boudjennah <a href="mailto:emailadress">christophe.boudjennah@obeo.fr</a>
 * 
 *
 * @see AbstractEditPartProvider
 * @see UMLEditPartProvider
 */
public class AbstractUMLEditPartProvider extends AbstractEditPartProvider {
	public synchronized IGraphicalEditPart createGraphicEditPart(View view) {
		switch (SiriusVisualIDRegistry.getVisualID(view)) {

			case DEdgeBeginNameEditPart.VISUAL_ID:
				DEdgeBeginNameEditPart dEdgePart = new DEdgeBeginNameEditPart(view) {

					@Override
					protected boolean isDirectEditEnabled() {
						return true;
					}

				};
				dEdgePart.installEditPolicy(org.eclipse.gef.RequestConstants.REQ_DIRECT_EDIT,
						new EcoreToolsDirectEditForEndRole());
				return dEdgePart;

			case DEdgeEndNameEditPart.VISUAL_ID:
				DEdgeEndNameEditPart dEdgeEndPart = new DEdgeEndNameEditPart(view) {
					@Override
					protected boolean isDirectEditEnabled() {
						return true;
					}

				};
				dEdgeEndPart.installEditPolicy(org.eclipse.gef.RequestConstants.REQ_DIRECT_EDIT,
						new EcoreToolsDirectEditForBeginRole());
				return dEdgeEndPart;

			case DNodeListEditPart.VISUAL_ID:
				//code in  EcoreToolsSpecificEditPartProvider
				//return new DNodeListEditPartWithAlpha(view);
				return super.createGraphicEditPart(view);
		}
		return null;
	}

	@Override
	public boolean provides(IOperation operation) {
		if (operation instanceof CreateGraphicEditPartOperation) {
			View view = ((IEditPartOperation)operation).getView();
			if (view.getElement() instanceof DSemanticDecorator) {
				EObject semanticTarget = ((DSemanticDecorator)view.getElement()).getTarget();
				if (isFromEcoreToolsDesign((DSemanticDecorator)view.getElement()))
					if (semanticTarget instanceof EReference
							&& ((EReference)semanticTarget).getEOpposite() != null) {
						switch (SiriusVisualIDRegistry.getVisualID(view)) {

							case DEdgeBeginNameEditPart.VISUAL_ID:
								return true;

							case DEdgeEndNameEditPart.VISUAL_ID:
								return true;
						}
					} else if (semanticTarget instanceof EClass) {
						switch (SiriusVisualIDRegistry.getVisualID(view)) {

							case DNodeListEditPart.VISUAL_ID:
								return true;
						}

					}
			}

		}
		return false;
	}

	private boolean isFromEcoreToolsDesign(DSemanticDecorator element) {
		/*
		 * At least only provide our editparts if the semantic element is from Ecore.ecore
		 */
		if (element.getTarget() instanceof EObject && element.getTarget().eClass() != null
				&& element.getTarget().eClass().getEPackage() == EcorePackage.eINSTANCE) {
			return true;
		}
		return false;
	}

	class EcoreToolsDirectEditForBeginRole extends LabelDirectEditPolicy {

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
						if (target instanceof EReference) {
							/*new EReferenceServices().*/performEdit((EReference)target, labelText);
						}

					}
				}
			};
			return new ICommandProxy(new GMFCommandWrapper(domain, cmd));

		};

	}

	class EcoreToolsDirectEditForEndRole extends LabelDirectEditPolicy {

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
						if (target instanceof EReference && ((EReference)target).getEOpposite() != null) {
							/*new EReferenceServices().*/performEdit(((EReference)target).getEOpposite(),
									labelText);
						}

					}
				}
			};
			return new ICommandProxy(new GMFCommandWrapper(domain, cmd));

		};

	}
	
	
	
	/**
	 * Local "copy" of org.eclipse.emf.ecoretools.design.service.EReferenceServices (in org.eclipse.emf.ecoretools 3.0.0)
	 * */
	
	private static final String CARDINALITY_SEPARATOR = "..";

	public static final String EOPPOSITE_SEPARATOR = " - ";

	public static final String DERIVED_PREFIX = "/";

	public static final String CARDINALITY_UNBOUNDED = "*";

	public static final String CARDINALITY_UNBOUNDED_ALTERNATIVE = "-1";

	
	
	public EReference performEdit(EReference ref, String editString) {
		if ("0".equals(editString.trim())) {
			ref.setLowerBound(0);
		} else if ("1".equals(editString.trim())) {
			ref.setLowerBound(1);
		} else if ("11".equals(editString.trim())) {
			ref.setLowerBound(1);
			ref.setUpperBound(1);
		} else if (CARDINALITY_UNBOUNDED.equals(editString.trim())) {
			ref.setUpperBound(-1);
		} else if (CARDINALITY_UNBOUNDED_ALTERNATIVE.equals(editString.trim())) {
			ref.setUpperBound(-1);
		} else {
			editName(ref, editString);
			editCardinality(ref, editString);
		}
		return ref;
	}

	private void editName(EReference ref, String editString) {
		String namePart = extractNamePart(ref, editString);
		if (namePart != null && namePart.trim().length() > 0) {
			boolean derived = namePart.startsWith(DERIVED_PREFIX);
			ref.setDerived(derived);
			ref.setName(namePart.substring(
					derived ? DERIVED_PREFIX.length() : 0).trim());
		}
	}

	private String extractNamePart(EReference ref, String name) {
		int end = name.indexOf("]");
		if (end != -1 && end < name.length()) {
			return name.substring(end + 1).trim();
		} else {
			return name.trim();
		}
	}

	private void editCardinality(EReference ref, String editString) {
		List<Integer> card = parseCardinality(editString);
		if (card.get(0) != null) {
			ref.setLowerBound(card.get(0).intValue());
		}
		if (card.get(1) != null) {
			ref.setUpperBound(card.get(1).intValue());
		}
	}
	
	/**
	 * Made public only to allow testing. Returns a List instead of an array
	 * because one the method is public (for testing), Acceleo will try to
	 * interpret as a service, but it does not handle arrays.
	 */
	public List<Integer> parseCardinality(String editString) {
		List<Integer> result = Lists.newArrayList(null, null);
		String spec = extractCardinalityPart(editString);
		if (spec != null) {
			if (spec.contains(CARDINALITY_SEPARATOR)) {
				String[] parts = spec.split(Pattern
						.quote(CARDINALITY_SEPARATOR));
				switch (parts.length) {
				case 0:
					break;
				case 1:
					if (spec.startsWith(CARDINALITY_SEPARATOR)) {
						result.set(1, parseBound(parts[0]));
					} else if (spec.endsWith(CARDINALITY_SEPARATOR)) {
						result.set(0, parseBound(parts[0]));
					}
					break;
				default: // 2 (or more, but only the first 2 are considered)
					result.set(0, parseBound(parts[0]));
					result.set(1, parseBound(parts[1]));
				}
			}
		}
		return result;
	}
	
	private Integer parseBound(String bound) {
		if (CARDINALITY_UNBOUNDED.equals(bound.trim())) {
			return Integer.valueOf(-1);
		} else {
			try {
				return Integer.parseInt(bound.trim());
			} catch (NumberFormatException _) {
				return null;
			}
		}
	}

	/**
	 * Made public only to allow testing.
	 */
	public String extractCardinalityPart(String editString) {
		int start = editString.indexOf("[");
		int end = editString.indexOf("]");
		if (start != -1 && end != -1 && start < end
				&& end < editString.length()) {
			return editString.substring(start + 1, end).trim();
		} else {
			return null;
		}
	}
	/**********************************************************/
}
