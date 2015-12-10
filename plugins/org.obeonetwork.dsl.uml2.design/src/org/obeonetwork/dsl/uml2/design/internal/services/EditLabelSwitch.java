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
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ClassifierTemplateParameter;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ProtocolStateMachine;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateParameterSubstitution;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.TemplateableElement;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.util.UMLSwitch;

/**
 * A switch that handle the label edition for each UML types.
 *
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class EditLabelSwitch extends UMLSwitch<Element> implements ILabelConstants {
	/**
	 * Create new classifier template parameter.
	 *
	 * @param context
	 *            Context
	 * @param templateSignature
	 *            Template signature
	 * @param newTemplateClassName
	 *            Template class name
	 * @return New Classifier template parameter
	 */
	private static ClassifierTemplateParameter createNewClassifierTemplateParameter(
			TemplateSignature templateSignature, String newTemplateClassName) {
		final ClassifierTemplateParameter result = UMLFactory.eINSTANCE.createClassifierTemplateParameter();
		final Class newGenericClass = UMLFactory.eINSTANCE.createClass();
		newGenericClass.setName(newTemplateClassName);
		result.setOwnedDefault(newGenericClass);
		result.setParameteredElement(newGenericClass);

		templateSignature.getOwnedParameters().add(result);
		return result;
	}

	/**
	 * The raw label content edited by the user.
	 */
	private String editedLabelContent;

	@Override
	public Element caseActivity(Activity object) {
		// Implemented for direct edit Transition
		object.setName(editedLabelContent);
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseActivityEdge(ActivityEdge object) {
		ValueSpecification expr = object.getGuard();
		if (expr == null || expr instanceof LiteralBoolean) {
			expr = UMLFactory.eINSTANCE.createOpaqueExpression();
			expr.setName(object.getName() + GUARD_SUFFIX);
			object.setGuard(expr);
		}

		if (editedLabelContent.matches("\\[.*\\]")) { //$NON-NLS-1$
			// Remove the first & last bracket character.
			editedLabelContent = editedLabelContent.substring(1, editedLabelContent.length() - 1);
		}

		if (expr instanceof OpaqueExpression) {
			((OpaqueExpression)expr).getBodies().clear();
			((OpaqueExpression)expr).getBodies().add(editedLabelContent);
		}

		return caseNamedElement(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseAssociation(Association object) {
		/**
		 * Nothing to do the label direct edit is done by the edit part provider {@see #UMLEditPartProvider}
		 */
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseBehaviorExecutionSpecification(BehaviorExecutionSpecification execution) {
		// Edit execution
		caseExecutionSpecification(execution);
		// Edit opaque behavior
		final Behavior behavior = execution.getBehavior();
		LabelServices.INSTANCE.editUmlLabel(behavior, editedLabelContent);
		// Edit operation
		final Operation operation = (Operation)behavior.getSpecification();
		LabelServices.INSTANCE.editUmlLabel(operation, editedLabelContent);
		return execution;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseClass(Class object) {

		editedLabelContent = parseInputLabel(object, editedLabelContent);
		return caseNamedElement(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseComment(Comment comment) {
		comment.setBody(editedLabelContent);
		return comment;
	}

	@Override
	public Element caseConstraint(Constraint object) {
		// this case was implemlented for transition direct edit from StateMachineDiagram
		setEditedLabelContent(editedLabelContent);
		doSwitch(object.getSpecification());
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseDataStoreNode(DataStoreNode object) {
		editedLabelContent = editedLabelContent.replaceFirst("(<<Datastore>>|\u00ABDatastore\u00BB)\\s*", ""); //$NON-NLS-1$ //$NON-NLS-2$

		return caseNamedElement(object);
	}

	@Override
	public Element caseEnumerationLiteral(EnumerationLiteral object) {
		object.setName(editedLabelContent);
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseExecutionOccurrenceSpecification(ExecutionOccurrenceSpecification occurence) {
		if (occurence.equals(occurence.getExecution().getStart())) {
			occurence.setName(editedLabelContent + START_SUFFIX);
		} else if (occurence.equals(occurence.getExecution().getFinish())) {
			occurence.setName(editedLabelContent + FINISH_SUFFIX);
		} else {
			occurence.setName(editedLabelContent);
		}
		return occurence;
	}

	/**
	 * {@inheritDoc}
	 */
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
			LabelServices.INSTANCE.editUmlLabel(
					((MessageOccurrenceSpecification)execution.getStart()).getMessage(), editedLabelContent);
		}
		return execution;
	}

	@Override
	public Element caseFunctionBehavior(FunctionBehavior object) {
		// Implemented for direct edit Transition
		object.setName(editedLabelContent);
		return object;
	}

	@Override
	public Element caseInteraction(Interaction object) {
		// Implemented for direct edit Transition
		object.setName(editedLabelContent);
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseLifeline(Lifeline lifeline) {
		final String name = editedLabelContent.substring(0, editedLabelContent.indexOf(":")); //$NON-NLS-1$
		// Edit associated instance name
		if (lifeline.getClientDependencies() != null && lifeline.getClientDependencies().size() > 0) {
			((InstanceSpecification)lifeline.getClientDependencies().get(0).getSuppliers().get(0))
			.setName(name);
		}
		// Edit lifeline name
		lifeline.setName(name);

		// Edit dependency
		final String type = editedLabelContent.substring(editedLabelContent.indexOf(":") + 1, //$NON-NLS-1$
				editedLabelContent.length()).trim();
		final Iterator<?> itr = lifeline.getModel().eAllContents();
		final Map<String, InstanceSpecification> instances = new HashMap<String, InstanceSpecification>();
		while (itr.hasNext()) {
			final Object element = itr.next();
			if (element instanceof InstanceSpecification) {
				instances.put(((InstanceSpecification)element).getClassifiers().get(0).getName(),
						(InstanceSpecification)element);
			}
		}
		if (instances.containsKey(type)) {
			final InstanceSpecification instance = instances.get(type);
			lifeline.getClientDependencies().clear();
			final Dependency dependency = UMLFactory.eINSTANCE.createDependency();
			dependency.getClients().add(lifeline);
			dependency.getSuppliers().add(instance);
			lifeline.getInteraction().getNearestPackage().getPackagedElements().add(dependency);
		}

		return lifeline;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseMessage(Message message) {
		// Edit message name
		message.setName(editedLabelContent);
		// Edit message send
		LabelServices.INSTANCE.editUmlLabel(message.getSendEvent(), editedLabelContent);
		// Edit message receive
		LabelServices.INSTANCE.editUmlLabel(message.getReceiveEvent(), editedLabelContent);
		return message;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseMessageOccurrenceSpecification(MessageOccurrenceSpecification occurence) {
		if (occurence.equals(occurence.getMessage().getSendEvent())) {
			occurence.setName(editedLabelContent + SENDER_SUFFIX);
		} else if (occurence.equals(occurence.getMessage().getReceiveEvent())) {
			occurence.setName(editedLabelContent + RECEIVER_SUFFIX);
		} else {
			occurence.setName(editedLabelContent);
		}
		return occurence;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseNamedElement(NamedElement object) {
		// FIXME We should detect the stereotype brackets instead of trying to
		// replace the generated label
		// that might have been modified...
		final String stereotype = DisplayLabelSwitch.computeStereotypes(object);
		if (stereotype != null && !"".equals(stereotype)) { //$NON-NLS-1$
			editedLabelContent = editedLabelContent.replace(stereotype, ""); //$NON-NLS-1$
		}
		object.setName(editedLabelContent);
		return object;
	}

	@Override
	public Element caseOpaqueBehavior(OpaqueBehavior object) {
		// Implemented for direct edit Transition
		object.setName(editedLabelContent);
		return object;
	}

	@Override
	public Element caseOpaqueExpression(OpaqueExpression object) {
		// This case was implemented in the case of direct editing of transition in StateMachineDiagram
		object.getBodies().clear();
		object.getBodies().add(editedLabelContent);

		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseOperation(Operation object) {
		OperationServices.INSTANCE.parseInputLabel(object, editedLabelContent);
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseProperty(Property object) {
		PropertyServices.INSTANCE.parseInputLabel(object, editedLabelContent);
		return object;
	}

	@Override
	public Element caseProtocolStateMachine(ProtocolStateMachine object) {
		// Implemented for direct edit Transition
		object.setName(editedLabelContent);
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseRegion(Region region) {
		// Name of a region is in the form "OwnerName : RegionName"
		final int pos = editedLabelContent.indexOf(":"); //$NON-NLS-1$
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

	/**
	 * Case Role.
	 *
	 * @param property
	 *            Property
	 */
	public void caseRole(Property property) {
		/*
		 * We need to find roles names, end user might type: "aRoleName" "aRoleName - aRoleName "
		 * aRoleName[cardinality]
		 */
		String escapedLabel = editedLabelContent;
		if (editedLabelContent.indexOf('/') > -1) {
			// should be derived
			escapedLabel.replace('/', ' ');
			property.setIsDerived(true);
		} else {
			property.setIsDerived(false);
		}
		escapedLabel = escapedLabel.trim();
		// multiplicity
		if (escapedLabel.indexOf('[') > -1) {
			final String endOfMul = escapedLabel.substring(escapedLabel.indexOf('[') + 1);
			escapedLabel = escapedLabel.substring(0, escapedLabel.indexOf('['));
			escapedLabel = escapedLabel.trim();
			// lower bound
			int lowerBound = property.getLower();
			int upperBound = property.getUpper();
			if (endOfMul.indexOf(']') > -1) {
				final String mulInter = endOfMul.substring(0, endOfMul.indexOf(']')).trim();
				if ("*".equals(mulInter)) { //$NON-NLS-1$
					lowerBound = 0;
					upperBound = -1;
				} else {
					if (mulInter.length() > 0) {
						if (mulInter.indexOf("..") > -1) { //$NON-NLS-1$
							final String low = mulInter.substring(0, mulInter.indexOf("..")).trim(); //$NON-NLS-1$
							if (low.length() > 0) {
								lowerBound = Integer.valueOf(low).intValue();
							}
							final String up = mulInter.substring(mulInter.indexOf("..") + 2).trim(); //$NON-NLS-1$
							if (up.length() > 0) {
								if ("*".equals(up)) { //$NON-NLS-1$
									upperBound = -1;
								} else {
									upperBound = Integer.valueOf(up).intValue();
								}
							}
						} else {
							final int singleBound = Integer.valueOf(mulInter).intValue();
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseSlot(Slot slot) {
		for (final ValueSpecification value : slot.getValues()) {
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

	@Override
	public Element caseStateMachine(StateMachine object) {
		// Implemented for direct edit Transition
		object.setName(editedLabelContent);
		return object;
	}

	@Override
	public Element caseTemplateableElement(TemplateableElement object) {
		editedLabelContent = parseInputLabel(object, editedLabelContent);
		return super.caseTemplateableElement(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseTemplateBinding(TemplateBinding object) {
		TemplateBindingServices.INSTANCE.parseInputLabel(object, editedLabelContent);
		editedLabelContent = new DisplayLabelSwitch().doSwitch(object);
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseTransition(Transition object) {
		final String newLabel = editedLabelContent;

		// Edit guard if not exist create it
		editTransitionGuard(object, newLabel);

		// Edit behaviorExpression if not exist create it
		editTransitionBehaviorExpression(object, newLabel);

		// Edit triggers if not exist create it
		editTransitionTrigger(object, newLabel);
		return object;
	}

	@Override
	public Element caseTrigger(Trigger object) {
		// Implemented for direct edit Transition
		object.setName(editedLabelContent);
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseTypedElement(TypedElement object) {
		final String[] splittedLabel = editedLabelContent.split("\\s*:\\s*"); //$NON-NLS-1$

		if (splittedLabel.length == 1) {
			return caseNamedElement(object);
		}
		object.setName(splittedLabel[0]);

		// TODO retrieve and set the element type

		return object;
	}

	private void editTransitionBehaviorExpression(Transition object, String newLabel){
		final int behaviorStartIndex = newLabel.lastIndexOf("/");//$NON-NLS-1$

		if (behaviorStartIndex != -1) {
			int guardEndIndex = -1;
			if (newLabel.matches(".*\\[.*\\].*")) {//$NON-NLS-1$
				guardEndIndex = newLabel.lastIndexOf("]");//$NON-NLS-1$
			}
			if (behaviorStartIndex > guardEndIndex) { // to be sure "/" is not in guard
				final String behaviorLabelContent = newLabel.substring(behaviorStartIndex + 1);
				if (!behaviorLabelContent.isEmpty()) {// no change if string is empty
					Behavior effect = object.getEffect();
					if (effect == null) {
						effect = UMLFactory.eINSTANCE.createOpaqueBehavior();
						object.setEffect(effect);
					}
					setEditedLabelContent(behaviorLabelContent);
					doSwitch(effect);
				}
			}
		} else {
			if (object.getEffect() != null) {
				object.getEffect().destroy();
			}
		}

	}

	private void editTransitionGuard(Transition object, String newLabel){
		final int guardStartIndex = newLabel.indexOf("[");//$NON-NLS-1$

		if (newLabel.matches(".*\\[.*\\].*")) { //$NON-NLS-1$
			String editedGuardLabelContent = newLabel;
			final int guardEndIndex = editedGuardLabelContent.lastIndexOf("]");//$NON-NLS-1$
			editedGuardLabelContent = editedGuardLabelContent.substring(guardStartIndex+1, guardEndIndex);
			if (!editedGuardLabelContent.isEmpty()) {// no change if string is empty
				OpaqueExpression expr;
				Constraint constraint = object.getGuard();
				if (constraint == null) {
					// Create new constraint
					constraint = UMLFactory.eINSTANCE.createConstraint();
					constraint.setName(object.getName() + "_transition"); //$NON-NLS-1$
					// With opaque expression
					expr = UMLFactory.eINSTANCE.createOpaqueExpression();
					expr.setName(object.getName() + GUARD_SUFFIX);
					constraint.setSpecification(expr);
					object.setGuard(constraint);
				}
				setEditedLabelContent(editedGuardLabelContent);
				doSwitch(constraint);
			}
		} else {
			// Check a guard was removed from model
			if (object.getGuard() != null) {
				// Delete constaint and sub elements
				object.getGuard().destroy();
			}
		}
	}

	private void editTransitionTrigger(Transition object, String newLabel){
		final int guardStartIndex = newLabel.indexOf("[");//$NON-NLS-1$
		final int behaviorStartIndex = newLabel.lastIndexOf("/");//$NON-NLS-1$

		// Find trigger part label
		int triggerEndIndex = -1;
		if (behaviorStartIndex >= 0 && guardStartIndex >= 0) {
			triggerEndIndex = guardStartIndex;
			if (guardStartIndex > behaviorStartIndex) {
				triggerEndIndex = behaviorStartIndex;
			}
		} else if (behaviorStartIndex != -1) { // String don't start with behaviorExpression
			triggerEndIndex = behaviorStartIndex;
		} else if (guardStartIndex != -1) {// String don't start with guard
			triggerEndIndex = guardStartIndex;
		} else {// String don't have guard or behavior expression
			triggerEndIndex = newLabel.length();
		}
		// Get a list of trigger labels
		final List<String> triggersLabels = new ArrayList<String>();

		if (!newLabel.isEmpty() && triggerEndIndex > 0) {
			final String triggerEditedLabel = newLabel.substring(0,triggerEndIndex);

			// String[] triggersLabels = {triggerEditedLabel};
			if (triggerEditedLabel.contains(",")) {//$NON-NLS-1$
				triggersLabels.addAll(Arrays.asList(triggerEditedLabel.split(",")));//$NON-NLS-1$
			} else {
				triggersLabels.add(triggerEditedLabel);
			}
		}

		// For each label edit trigger
		if (triggersLabels.size() > 0) {
			// Find existing trigger
			final List<String> existingTriggersNames = new ArrayList<String>();
			for (final Trigger trigger : object.getTriggers()) {
				existingTriggersNames.add(trigger.getName());
			}
			// Find trigger to create and trigger to delete
			final List<String> toBeCreated = new ArrayList<String>();
			for (final String label : triggersLabels) {
				if (existingTriggersNames.contains(label.trim())) {
					existingTriggersNames.remove(label.trim());
				} else {
					toBeCreated.add(label.trim());
				}
			}
			// delete no not found trigger
			for (final String label : existingTriggersNames) {
				object.getTrigger(label).destroy();
			}
			// Create and add new trigger to transition
			for (final String label : toBeCreated) {
				final Trigger trigger = UMLFactory.eINSTANCE.createTrigger();
				object.getTriggers().add(trigger);
				setEditedLabelContent(label.trim());
				doSwitch(trigger);
			}

		}else{
			//remove all triggers
			final EList<Trigger> triggers = object.getTriggers();
			if (triggers.size()>0){
				for (final Trigger trigger : triggers){
					trigger.destroy();
				}
			}
		}
	}

	/**
	 * Parse the edited label content and update the underlying {@link Class}.
	 *
	 * @param aTemplateableElement
	 *            the context {@link Class} object.
	 * @param inputLabel
	 *            the user edited label content.
	 * @return Class name
	 */
	private String parseInputLabel(org.eclipse.uml2.uml.TemplateableElement aTemplateableElement,
			String inputLabel) {
		String result = inputLabel;
		final String validLabel = "[a-zA-Z_0-9]+((\\s)*<[a-zA-Z_0-9]+(,(\\s)*[a-zA-Z_0-9]+)*>)?"; //$NON-NLS-1$
		final String templatedLabel = "[a-zA-Z_0-9]+((\\s)*<[a-zA-Z_0-9]+(,(\\s)*[a-zA-Z_0-9]+)*>)"; //$NON-NLS-1$

		if (inputLabel.matches(validLabel)) {
			if (inputLabel.matches(templatedLabel) && aTemplateableElement instanceof Classifier) {
				final String[] splittedLabel = inputLabel.split("(\\s)*<"); //$NON-NLS-1$
				result = splittedLabel[0].trim();

				// rename the template parameter
				final String templateSignatureLabel = splittedLabel[1].replace(">", ""); //$NON-NLS-1$ //$NON-NLS-2$
				final String[] templateParamLabels = templateSignatureLabel.split(",(\\s)*"); //$NON-NLS-1$
				TemplateSignature templateSignature = aTemplateableElement.getOwnedTemplateSignature();
				if (templateSignature == null) {
					// create a template signature
					templateSignature = aTemplateableElement.createOwnedTemplateSignature();
				}
				final List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
				for (int i = 0; i < templateParamLabels.length; i++) {
					final String templateParamLabel = templateParamLabels[i].trim();
					try {
						final TemplateParameter templateParameter = templateParameters.get(i);
						if (templateParameter.getParameteredElement() instanceof NamedElement) {
							((NamedElement)templateParameter.getParameteredElement())
							.setName(templateParamLabel);
						}
					} catch (final IndexOutOfBoundsException e) {
						final TemplateParameter createNewClassifierTemplateParameter;
						createNewClassifierTemplateParameter = createNewClassifierTemplateParameter(
								templateSignature, templateParamLabel);
						final Session sess = SessionManager.INSTANCE.getSession(templateSignature);
						final Collection<Setting> inverseReferences = sess.getSemanticCrossReferencer()
								.getInverseReferences(templateSignature);
						for (final Setting setting : inverseReferences) {
							if (setting.getEObject() instanceof TemplateBinding) {
								final TemplateBinding templateBinding = (TemplateBinding)setting.getEObject();
								final TemplateParameterSubstitution templateParameterSubstitution = templateBinding
										.createParameterSubstitution();
								templateParameterSubstitution.setFormal(createNewClassifierTemplateParameter);
							}
						}
					}
				}
				while (templateParamLabels.length < templateParameters.size()) {
					// remove related binding
					final TemplateParameter templateParameterToRemove = templateParameters
							.get(templateParameters.size() - 1);
					final Session sess = SessionManager.INSTANCE.getSession(templateParameterToRemove);
					final Collection<Setting> inverseReferences = sess.getSemanticCrossReferencer()
							.getInverseReferences(templateParameterToRemove);
					for (final Setting setting : inverseReferences) {
						if (setting.getEObject() instanceof TemplateParameterSubstitution) {
							TemplateParameterSubstitution templateParameterSubstitution = (TemplateParameterSubstitution)setting
									.getEObject();
							final TemplateBinding templateBinding = templateParameterSubstitution
									.getTemplateBinding();
							templateBinding.getParameterSubstitutions().remove(templateParameterSubstitution);
							templateParameterSubstitution = null;
						}
					}
					templateSignature.getParameters().remove(templateParameterToRemove);
				}
			} else {
				final TemplateSignature templateSignature = aTemplateableElement.getOwnedTemplateSignature();
				if (templateSignature != null) {

					// delete templateBinding
					final Session sess = SessionManager.INSTANCE.getSession(templateSignature);
					final Collection<Setting> inverseReferences = sess.getSemanticCrossReferencer()
							.getInverseReferences(templateSignature);
					for (final Setting setting : inverseReferences) {
						if (setting.getEObject() instanceof TemplateBinding) {
							final TemplateBinding templateBinding = (TemplateBinding)setting.getEObject();
							templateBinding.getParameterSubstitutions().clear();
							templateBinding.setSignature(null);
							final TemplateableElement boundElement = templateBinding.getBoundElement();
							boundElement.setOwnedTemplateSignature(null);
							boundElement.getTemplateBindings().clear();
						}
					}

					// delete templateSignature and templateParameter
					templateSignature.getParameters().clear();
					templateSignature.getOwnedParameters().clear();
					((ParameterableElement)aTemplateableElement).setTemplateParameter(null);
					aTemplateableElement.setOwnedTemplateSignature(null);
				}
			}
		}
		return result;
	}

	/**
	 * Set edited label content.
	 *
	 * @param editedLabelContent
	 *            Label content
	 */
	public void setEditedLabelContent(String editedLabelContent) {
		this.editedLabelContent = editedLabelContent;
	}
}
