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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.obeonetwork.dsl.uml2.design.services.LabelServices;
import org.obeonetwork.dsl.uml2.design.services.UMLServices;

/**
 * A switch that handle the label edition for each UML types.
 * 
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 */
public class EditLabelSwitch extends UMLSwitch<Element> implements ILabelConstants {

	private static final String RECEIVER_SUFFIX = "_receiver";

	private static final String SENDER_SUFFIX = "_sender";

	private static final String FINISH_SUFFIX = "_finish";

	private static final String START_SUFFIX = "_start";

	private final LabelServices service = new LabelServices();

	/**
	 * The guard suffix constant.
	 */
	private static final String GUARD_SUFFIX = "_guard";

	/**
	 * The raw label content edited by the user.
	 */
	private String editedLabelContent;

	public void setEditedLabelContent(String editedLabelContent) {
		this.editedLabelContent = editedLabelContent;
	}

	@Override
	public Element caseAssociation(Association object) {
		/*
		 * We need to find roles names, end user might type: "aRoleName" "aRoleName - aRoleName
		 * "aRoleName[cardinality] - anotherRoleName[cardinality]
		 */
		// let's see if we have several role names typed
		final Property source = UMLServices.getSource(object);
		final Property target = UMLServices.getTarget(object);
		if (source != null && target != null) {
			if (editedLabelContent.indexOf('-') > -1) {
				//
				String completeSourceLabel = editedLabelContent.substring(0, editedLabelContent.indexOf('-'))
						.trim();
				editAssociationEndLabel(source, completeSourceLabel);
				String completeTargetLabel = editedLabelContent
						.substring(editedLabelContent.indexOf('-') + 1).trim();
				editAssociationEndLabel(target, completeTargetLabel);
			} else {
				String singleEndLabel = editedLabelContent.trim();
				if (target.isNavigable()) {
					editAssociationEndLabel(target, singleEndLabel);
				} else {
					editAssociationEndLabel(source, singleEndLabel);
				}

			}
			return object;
		} else {
			return super.caseAssociation(object);
		}
	}

	private void editAssociationEndLabel(Property property, String label) {
		String escapedLabel = label;
		if (label.indexOf('/') > -1) {
			// should be derived
			escapedLabel.replace('/', ' ');
			property.setIsDerived(true);
		} else {
			property.setIsDerived(false);
		}
		escapedLabel = escapedLabel.trim();
		// multiplicity
		if (escapedLabel.indexOf('[') > -1) {
			String endOfMul = escapedLabel.substring(escapedLabel.indexOf('[') + 1);
			escapedLabel = escapedLabel.substring(0, escapedLabel.indexOf('['));
			escapedLabel = escapedLabel.trim();
			// lower bound
			int lowerBound = property.getLower();
			int upperBound = property.getUpper();
			if (endOfMul.indexOf(']') > -1) {
				String mulInter = endOfMul.substring(0, endOfMul.indexOf(']')).trim();
				if ("*".equals(mulInter)) {
					lowerBound = 0;
					upperBound = -1;
				} else {
					if (mulInter.length() > 0) {
						if (mulInter.indexOf("..") > -1) {
							String low = mulInter.substring(0, mulInter.indexOf("..")).trim();
							if (low.length() > 0) {
								lowerBound = Integer.valueOf(low);
							}
							String up = mulInter.substring(mulInter.indexOf("..") + 2).trim();
							if (up.length() > 0) {
								upperBound = Integer.valueOf(up);
							}
						} else {
							Integer singleBound = Integer.valueOf(mulInter);
							lowerBound = singleBound;
							upperBound = singleBound;
						}

					}
				}
			}
			property.setLower(lowerBound);
			property.setUpper(upperBound);
		}
		property.setName(escapedLabel);
	}

	public Element caseComment(Comment comment) {
		comment.setBody(editedLabelContent);
		return comment;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseProperty(Property object) {
		PropertyServices.parseInputLabel(object, editedLabelContent);
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseOperation(Operation object) {
		OperationServices.parseInputLabel(object, editedLabelContent);
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseActivityEdge(ActivityEdge object) {
		OpaqueExpression expr = (OpaqueExpression)object.getGuard();
		if (expr == null) {
			expr = UMLFactory.eINSTANCE.createOpaqueExpression();
			expr.setName(object.getName() + GUARD_SUFFIX);
			object.setGuard(expr);
		}

		if (editedLabelContent.matches("\\[.*\\]")) {
			// Remove the first & last bracket character.
			editedLabelContent = editedLabelContent.substring(1, editedLabelContent.length() - 1);
		}

		expr.getBodies().clear();
		expr.getBodies().add(editedLabelContent);

		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseTransition(Transition object) {
		OpaqueExpression expr;
		Constraint constraint = object.getGuard();
		if (constraint != null) {
			expr = (OpaqueExpression)constraint.getSpecification();
			if (expr == null) {
				expr = UMLFactory.eINSTANCE.createOpaqueExpression();
				expr.setName(object.getName() + GUARD_SUFFIX);
				constraint.setSpecification(expr);
			}
		} else {
			constraint = UMLFactory.eINSTANCE.createConstraint();
			constraint.setName(object.getName() + "_transition");
			expr = UMLFactory.eINSTANCE.createOpaqueExpression();
			expr.setName(object.getName() + GUARD_SUFFIX);
			constraint.setSpecification(expr);
			object.setGuard(constraint);
		}

		if (editedLabelContent.matches("\\[.*\\]")) {
			// Remove the first & last bracket character.
			editedLabelContent = editedLabelContent.substring(1, editedLabelContent.length() - 1);
		}

		expr.getBodies().clear();
		expr.getBodies().add(editedLabelContent);

		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseNamedElement(NamedElement object) {
		// FIXME We should detect the stereotype brackets instead of trying to replace the generated label
		// that might have been modified...
		final String stereotype = DisplayLabelSwitch.computeStereotypes(object);
		if (stereotype != null && !"".equals(stereotype)) {
			editedLabelContent = editedLabelContent.replace(stereotype, "");
		}
		object.setName(editedLabelContent);
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseTypedElement(TypedElement object) {
		final String[] splittedLabel = editedLabelContent.split("\\s*:\\s*");

		if (splittedLabel.length == 1) {
			return caseNamedElement(object);
		} else {
			object.setName(splittedLabel[0]);

			// TODO retrieve and set the element type
		}

		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseDataStoreNode(DataStoreNode object) {
		editedLabelContent = editedLabelContent.replaceFirst("(<<Datastore>>|\u00ABDatastore\u00BB)\\s*", "");

		return caseNamedElement(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseRegion(Region region) {
		// Name of a region is in the form "OwnerName : RegionName"
		final int pos = editedLabelContent.indexOf(":");
		if (pos > -1) {
			final String ownerName = editedLabelContent.substring(0, pos).trim();
			if (region.getOwner() instanceof NamedElement) {
				((NamedElement)region.getOwner()).setName(ownerName);
			}
			region.setName(editedLabelContent.substring(pos + 1).trim());
		} else {
			region.setName(editedLabelContent.trim());
		}

		return region;
	}

	@Override
	public Element caseExecutionSpecification(ExecutionSpecification execution) {
		// Edit execution name
		execution.setName(editedLabelContent);
		// Edit start execution name
		execution.getStart().setName(editedLabelContent + START_SUFFIX);
		// Edit finish execution name
		execution.getFinish().setName(editedLabelContent + FINISH_SUFFIX);
		// Edit message if exists
		if (execution.getStart() instanceof MessageOccurrenceSpecification) {
			service.editUmlLabel(((MessageOccurrenceSpecification)execution.getStart()).getMessage(),
					editedLabelContent);
		}
		return execution;
	}

	@Override
	public Element caseBehaviorExecutionSpecification(BehaviorExecutionSpecification execution) {
		// Edit execution
		caseExecutionSpecification(execution);
		// Edit opaque behavior
		Behavior behavior = execution.getBehavior();
		service.editUmlLabel(behavior, editedLabelContent);
		// Edit operation
		Operation operation = (Operation)behavior.getSpecification();
		service.editUmlLabel(operation, editedLabelContent);
		return execution;
	}

	@Override
	public Element caseMessage(Message message) {
		// Edit message name
		message.setName(editedLabelContent);
		// Edit message send
		service.editUmlLabel(message.getSendEvent(), editedLabelContent);
		// Edit message receive
		service.editUmlLabel(message.getReceiveEvent(), editedLabelContent);
		return message;
	}

	@Override
	public Element caseLifeline(Lifeline lifeline) {
		String name = editedLabelContent.substring(0, editedLabelContent.indexOf(":"));
		// Edit associated instance name
		if (lifeline.getClientDependencies() != null && lifeline.getClientDependencies().size() > 0)
			((InstanceSpecification)lifeline.getClientDependencies().get(0).getSuppliers().get(0))
					.setName(name);
		// Edit lifeline name
		lifeline.setName(name);

		// Edit dependency
		String type = editedLabelContent.substring(editedLabelContent.indexOf(":") + 1,
				editedLabelContent.length()).trim();
		Iterator itr = lifeline.getModel().eAllContents();
		Map<String, InstanceSpecification> instances = new HashMap<String, InstanceSpecification>();
		while (itr.hasNext()) {
			Object element = itr.next();
			if (element instanceof InstanceSpecification) {
				instances.put(((InstanceSpecification)element).getClassifiers().get(0).getName(),
						(InstanceSpecification)element);
			}
		}
		if (instances.containsKey(type)) {
			InstanceSpecification instance = instances.get(type);
			lifeline.getClientDependencies().clear();
			Dependency dependency = UMLFactory.eINSTANCE.createDependency();
			dependency.getClients().add(lifeline);
			dependency.getSuppliers().add(instance);
			lifeline.getInteraction().getNearestPackage().getPackagedElements().add(dependency);
		}

		return lifeline;
	}

	@Override
	public Element caseMessageOccurrenceSpecification(MessageOccurrenceSpecification occurence) {
		if (occurence.equals(occurence.getMessage().getSendEvent()))
			occurence.setName(editedLabelContent + SENDER_SUFFIX);
		else if (occurence.equals(occurence.getMessage().getReceiveEvent()))
			occurence.setName(editedLabelContent + RECEIVER_SUFFIX);
		else
			occurence.setName(editedLabelContent);
		return occurence;
	}

	@Override
	public Element caseExecutionOccurrenceSpecification(ExecutionOccurrenceSpecification occurence) {
		if (occurence.equals(occurence.getExecution().getStart()))
			occurence.setName(editedLabelContent + START_SUFFIX);
		else if (occurence.equals(occurence.getExecution().getFinish()))
			occurence.setName(editedLabelContent + FINISH_SUFFIX);
		else
			occurence.setName(editedLabelContent);
		return occurence;
	}

	@Override
	public Element caseLiteralInteger(LiteralInteger literal) {
		literal.setValue(Integer.parseInt(ObjectServices.getValue(editedLabelContent)));
		return literal;
	}

	@Override
	public Element caseLiteralBoolean(LiteralBoolean literal) {
		literal.setValue(Boolean.parseBoolean(ObjectServices.getValue(editedLabelContent)));
		return literal;
	}

	@Override
	public Element caseLiteralString(LiteralString literal) {
		literal.setValue(ObjectServices.getValue(editedLabelContent));
		return literal;
	}

	@Override
	public Element caseInstanceSpecification(InstanceSpecification instance) {
		instance.setName(ObjectServices.getInstanceName(editedLabelContent));
		return instance;
	}

	@Override
	public Element caseSlot(Slot slot) {
		for (ValueSpecification value : slot.getValues()) {
			if (value instanceof InstanceValue) {
				caseInstanceValue((InstanceValue)value);
			} else if (value instanceof LiteralInteger) {
				caseLiteralInteger((LiteralInteger)value);
			} else if (value instanceof LiteralBoolean) {
				caseLiteralBoolean((LiteralBoolean)value);
			} else if (value instanceof LiteralString) {
				caseLiteralString((LiteralString)value);
			}
		}
		return slot;
	}
}
