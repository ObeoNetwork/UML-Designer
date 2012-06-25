/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.services.internal;

import java.util.Iterator;

import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.obeonetwork.dsl.uml2.design.services.UMLServices;

/**
 * A switch that handle the label computation for each UML types.
 * 
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DisplayLabelSwitch extends UMLSwitch<String> implements ILabelConstants {

	/**
	 * Spaced column constant.
	 */
	private static final String SPACED_COLUMN = " : ";

	/**
	 * Spaced column constant.
	 */
	private static final String SPACED_COMMA = ", ";

	/**
	 * Closing brace constant.
	 */
	private static final String CLOSING_BRACE = "]";

	/**
	 * Opening brace constant.
	 */
	private static final String OPENING_BRACE = "[";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseActivityEdge(ActivityEdge object) {
		final ValueSpecification value = object.getGuard();

		if (value instanceof OpaqueExpression) {
			final String expr = ((OpaqueExpression)value).getBodies().get(0);
			if (expr != null && !"".equalsIgnoreCase(expr) && !"true".equalsIgnoreCase(expr)
					&& !"1".equalsIgnoreCase(expr)) {
				return OPENING_BRACE + expr + CLOSING_BRACE;
			}
		}

		return "";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseTransition(Transition object) {
		final Constraint constraint = object.getGuard();
		if (constraint != null) {
			final ValueSpecification value = constraint.getSpecification();

			if (value instanceof OpaqueExpression) {
				final String expr = ((OpaqueExpression)value).getBodies().get(0);
				if (expr != null && !"".equalsIgnoreCase(expr) && !"true".equalsIgnoreCase(expr)
						&& !"1".equalsIgnoreCase(expr)) {
					return OPENING_BRACE + expr + CLOSING_BRACE;
				}
			}
		}

		return "";
	}

	/**
	 * Compute the {@link Stereotype} label part for the given {@link Element}.
	 * 
	 * @param element
	 *            the context element.
	 * @return the {@link Stereotype} label.
	 */
	public static String computeStereotypes(Element element) {
		final Iterator<Stereotype> it = element.getAppliedStereotypes().iterator();

		if (!it.hasNext()) {
			return "";
		}

		final StringBuffer stereotypeLabel = new StringBuffer();
		stereotypeLabel.append(OPEN_QUOTE_MARK);
		for (;;) {
			final Stereotype appliedStereotype = it.next();

			stereotypeLabel.append(appliedStereotype.getName());
			if (it.hasNext()) {
				stereotypeLabel.append(", ");
			} else {
				break;
			}
		}
		stereotypeLabel.append(CLOSE_QUOTE_MARK);
		if (element instanceof Feature) {
			stereotypeLabel.append(" ");
		} else {
			stereotypeLabel.append(NL);
		}

		return stereotypeLabel.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseProperty(Property object) {
		String label = "";
		if (object.isDerived()) {
			label += "/";
		}
		label += caseStructuralFeature(object);
		if (object.getDefault() != null && !"".equals(object.getDefault().trim())) {
			// is the label on multiple lines ?
			if (object.getDefault().contains(NL)) {
				label += " = ...";
			} else {
				label += " = " + object.getDefault();
			}
		} else if (object.getDefaultValue() instanceof InstanceValue) {
			label += " = " + ((InstanceValue)object.getDefaultValue()).getName();
		}
		return label;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseOperation(Operation object) {
		final StringBuilder label = new StringBuilder(caseNamedElement(object));
		label.append("(");

		boolean first = true;
		for (Parameter parameter : object.getOwnedParameters()) {
			if (!ParameterDirectionKind.RETURN_LITERAL.equals(parameter.getDirection())) {
				if (!first) {
					label.append(", ");
				} else {
					first = false;
				}
				label.append(caseTypedElement(parameter));
			}
		}
		label.append(")");
		if (object.getType() != null) {
			label.append(SPACED_COLUMN);
			label.append(object.getType().getName());
		}
		return label.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseStructuralFeature(StructuralFeature object) {
		return caseTypedElement(object) + " " + caseMultiplicityElement(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseMultiplicityElement(MultiplicityElement object) {
		if (object.getLower() == object.getUpper()) {
			// [1..1]
			return "[" + object.getLower() + "]";
		} else if (object.getLower() == 0 && object.getUpper() == -1) {
			// [0..*]
			return "[*]";
		} else {
			String label = OPENING_BRACE + object.getLower() + "..";
			if (object.getUpper() == -1) {
				label += "*]";
			} else {
				label += object.getUpper() + CLOSING_BRACE;
			}
			return label;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseTypedElement(TypedElement object) {
		if (object.getType() != null) {
			return caseNamedElement(object) + SPACED_COLUMN + object.getType().getName();
		} else {
			return caseNamedElement(object) + SPACED_COLUMN;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseActivityPartition(ActivityPartition object) {
		if (object.getRepresents() instanceof NamedElement) {
			return caseNamedElement(object) + SPACED_COLUMN
					+ ((NamedElement)object.getRepresents()).getName();
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String casePin(Pin object) {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(caseTypedElement(object));
		buffer.append(caseMultiplicityElement(object));
		return buffer.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseDataStoreNode(DataStoreNode object) {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(OPEN_QUOTE_MARK);
		buffer.append("Datastore");
		buffer.append(CLOSE_QUOTE_MARK);
		buffer.append(NL);
		buffer.append(caseNamedElement(object));
		return buffer.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseCallOperationAction(CallOperationAction object) {
		if (object.getOperation() != null) {
			return caseNamedElement(object) + SPACED_COLUMN + object.getOperation().getName();
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseOpaqueAction(OpaqueAction object) {
		final Iterator<String> it = object.getBodies().iterator();

		if (it.hasNext()) {
			final StringBuffer buffer = new StringBuffer();
			buffer.append(caseNamedElement(object));
			buffer.append(NL);

			while (it.hasNext()) {
				buffer.append(NL);
				buffer.append(it.next());
			}

			return buffer.toString();
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseNamedElement(NamedElement object) {
		return computeStereotypes(object) + object.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseElementImport(ElementImport object) {
		return object.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseAssociation(Association object) {
		return object.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseLifeline(Lifeline lifeline) {
		final StringBuilder label = new StringBuilder();
		if (lifeline.getRepresents() != null && isDependencyDescribed(lifeline)) {
			for (NamedElement context : lifeline.getClientDependencies().get(0).getSuppliers()) {
				label.append(context.getLabel());
				label.append(SPACED_COLUMN.trim());
				label.append(SPACED_COLUMN.trim());
			}
			label.append(doSwitch(lifeline.getRepresents()));
		} else if (lifeline.getRepresents() == null && isDependencyDescribed(lifeline)) {
			for (NamedElement context : lifeline.getClientDependencies().get(0).getSuppliers()) {
				label.append(doSwitch(context));
			}
		} else if (lifeline.getRepresents() instanceof Property) {
			// label.append(SPACED_COLUMN);
			label.append(caseProperty((Property)lifeline.getRepresents()));
		} else {
			label.append(caseNamedElement(lifeline));
		}
		return label.toString();
	}

	/**
	 * Test if a context dependency is added to the lifeline.
	 * 
	 * @param lifeline
	 *            the lifeline
	 * @return true if any
	 */
	private boolean isDependencyDescribed(Lifeline lifeline) {
		return lifeline.getClientDependencies() != null && lifeline.getClientDependencies().size() > 0
				&& lifeline.getClientDependencies().get(0) != null
				&& lifeline.getClientDependencies().get(0).getSuppliers().size() > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseExecutionSpecification(ExecutionSpecification execution) {
		if (execution instanceof BehaviorExecutionSpecification) {
			if (((BehaviorExecutionSpecification)execution).getBehavior() != null
					&& ((BehaviorExecutionSpecification)execution).getBehavior().getSpecification() != null)
				return caseOperation((Operation)((BehaviorExecutionSpecification)execution).getBehavior()
						.getSpecification());
		}
		return execution.getLabel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseMessage(Message message) {
		return message.getName();
	}

	/**
	 * Compute label for association end.
	 * 
	 * @param p
	 *            the {@link Association}'s {@link Property} end.
	 * @return the label of the association end.
	 */
	private String getAssociationEndLabel(Property p) {
		final StringBuilder sb = new StringBuilder("");
		if (p.isDerived()) {
			sb.append("/");
		}
		sb.append(p.getName());
		sb.append(caseMultiplicityElement(p));
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public String caseInstanceSpecification(InstanceSpecification object) {
		final StringBuilder label = new StringBuilder(caseNamedElement(object));

		if (object.getClassifiers() != null && object.getClassifiers().size() > 0) {
			label.append(SPACED_COLUMN);
			final Iterator<Classifier> it = object.getClassifiers().iterator();
			while (it.hasNext()) {
				final Classifier classifier = (Classifier)it.next();
				label.append(doSwitch(classifier).replace("\n", " "));
				if (it.hasNext())
					label.append(SPACED_COMMA);
			}
		}
		return label.toString();
	}

}
