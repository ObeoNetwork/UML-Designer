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
package org.obeonetwork.dsl.uml2.design.api.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityGroup;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutableNode;
import org.eclipse.uml2.uml.FinalNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.InterruptibleActivityRegion;
import org.eclipse.uml2.uml.InvocationAction;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.internal.services.LabelServices;
import org.obeonetwork.dsl.uml2.design.internal.wizards.Messages;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * A set of services to handle the Activity diagram.
 *
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ActivityDiagramServices extends AbstractDiagramServices {
	/**
	 * Return the label of an opaque action with its body.
	 *
	 * @param opaqueAction
	 *            Opaque Action
	 * @return Opaque Action label
	 */
	public String computeOpaqueActionWithBodyLabel(OpaqueAction opaqueAction) {
		return LabelServices.INSTANCE.computeOpaqueActionWithBodyLabel(opaqueAction);
	}

	/**
	 * Creates a new {@link InputPin} element in the given context.
	 *
	 * @param context
	 *            the context object on which to execute this service.
	 * @return the new {@link InputPin}
	 */
	public InputPin createInputPin(Action context) {
		final InputPin pin = UMLFactory.eINSTANCE.createInputPin();
		pin.setName("Input_" + context.getInputs().size()); //$NON-NLS-1$

		if (context instanceof InvocationAction) {
			((InvocationAction)context).getArguments().add(pin);
		} else if (context instanceof OpaqueAction) {
			((OpaqueAction)context).getInputValues().add(pin);
		} else {
			throw new UnsupportedOperationException("Can't create InputPin for context of type: " //$NON-NLS-1$
					+ context.eClass().getName());
		}

		return pin;
	}

	/**
	 * Create input pin from a parameter.
	 *
	 * @param action
	 *            Call action
	 * @param parameter
	 *            Parameter
	 * @return Pin
	 */
	public Pin createInputPinFromParameter(CallAction action, Parameter parameter) {
		final InputPin pin = UMLFactory.eINSTANCE.createInputPin();
		action.getArguments().add(pin);
		setPinAttributes(parameter, pin);
		return null;
	}

	/**
	 * Creates a new {@link OutputPin} element in the given context.
	 *
	 * @param context
	 *            the context object on which to execute this service.
	 * @return the new {@link OutputPin}
	 */
	public OutputPin createOutputPin(Action context) {
		final OutputPin pin = UMLFactory.eINSTANCE.createOutputPin();
		pin.setName("Output_" + context.getOutputs().size()); //$NON-NLS-1$

		if (context instanceof CallAction) {
			((CallAction)context).getResults().add(pin);
		} else if (context instanceof OpaqueAction) {
			((OpaqueAction)context).getOutputValues().add(pin);
		} else {
			throw new UnsupportedOperationException("Can't create InputPin for context of type: " //$NON-NLS-1$
					+ context.eClass().getName());
		}

		return pin;
	}

	/**
	 * Create output pin from a parameter.
	 *
	 * @param action
	 *            Call action
	 * @param parameter
	 *            Parameter
	 * @return Pin
	 */
	public Pin createOutputPinFromParameter(CallAction action, Parameter parameter) {
		final OutputPin pin = UMLFactory.eINSTANCE.createOutputPin();
		action.getResults().add(pin);
		setPinAttributes(parameter, pin);
		return null;
	}

	/**
	 * Manages the drag & drop of {@link ActivityNode}.
	 *
	 * @param context
	 *            object ({@link Activity} or {@link ActivityPartition}) on which to drop the node
	 * @param node
	 *            the node to drop
	 * @return the given node object
	 */
	private ActivityNode dropNode(Element context, ActivityNode node, Element oldContext) {
		if (oldContext instanceof ActivityPartition) {
			node.getInPartitions().remove(oldContext);
		} else if (oldContext instanceof InterruptibleActivityRegion) {
			node.getInInterruptibleRegions().remove(oldContext);
		}
		if (context instanceof ActivityPartition) {
			node.getInPartitions().add((ActivityPartition)context);
		} else if (context instanceof InterruptibleActivityRegion) {
			node.getInInterruptibleRegions().add((InterruptibleActivityRegion)context);
		}

		return node;
	}

	/**
	 * Manages the drag & drop of {@link ActivityPartition}.
	 *
	 * @param context
	 *            object ({@link Activity} or {@link ActivityPartition}) on which to drop the node
	 * @param partition
	 *            the partition to drop
	 * @param oldContext
	 *            Old context
	 * @return the given partition object
	 */
	private ActivityPartition dropNode(Element context, ActivityPartition partition, Element oldContext) {
		if (context instanceof Activity) {
			((Activity)context).getPartitions().add(partition);
		} else if (context instanceof ActivityPartition) {
			((ActivityPartition)context).getSubpartitions().add(partition);
		}
		return partition;
	}

	/**
	 * Manages dispatch to corresponding drag&drop services.<br>
	 * This is needed to workaround polymorphism issue with Java service from a deployed VP.
	 *
	 * @param context
	 *            the object on which to drop the node.
	 * @param node
	 *            the dropped node
	 * @param oldContext
	 *            Old context
	 * @return the given node or <code>null</code> if the given node is not of a correct type.
	 */
	public Element dropNode(Element context, Element node, Element oldContext) {
		if (node instanceof ActivityNode) {
			return dropNode(context, (ActivityNode)node, oldContext);
		} else if (node instanceof ActivityPartition) {
			return dropNode(context, (ActivityPartition)node, oldContext);
		} else if (node instanceof InterruptibleActivityRegion) {
			return dropNode(context, (InterruptibleActivityRegion)node, oldContext);
		}

		return null;
	}

	/**
	 * Manages the drag & drop of {@link InterruptibleActivityRegion}.
	 *
	 * @param context
	 *            object ({@link Activity} or {@link InterruptibleActivityRegion}) on which to drop the node
	 * @param interruptRegion
	 *            the partition to drop
	 * @param oldContext
	 *            Old context
	 * @return the given partition object
	 */
	private InterruptibleActivityRegion dropNode(Element context, InterruptibleActivityRegion interruptRegion,
			Element oldContext) {
		if (context instanceof Activity) {
			((Activity)context).getOwnedGroups().add(interruptRegion);
		}
		return interruptRegion;
	}

	/**
	 * Parse the edited label string and update the underlying context {@link Element}.
	 *
	 * @param opaqueAction
	 *            the context object on which to execute this service.
	 * @param editedLabelContent
	 *            the content entered by the user.
	 * @return the context {@link Element}
	 */
	public Element editOpaqueActionWithBodyLabel(OpaqueAction opaqueAction, String editedLabelContent) {
		opaqueAction.getBodies().clear();
		opaqueAction.getBodies().add(editedLabelContent);
		return opaqueAction;
	}

	/**
	 * Return the given context object if it's an {@link Activity} or find it into the parent elements.<br>
	 * This is used to compute the context of Activity diagram creation tools. Activity Nodes & Actions can be
	 * created within either an {@link Activity} or an {@link ActivityPartition}, but they are always
	 * contained by the parent {@link Activity}.
	 *
	 * @param context
	 *            the context object on which to execute this service.
	 * @return the parent activity of the context object
	 */
	public Activity findParentActivity(Element context) {
		if (context instanceof Activity) {
			return (Activity)context;
		}

		if (context.eContainer() != null) {
			return findParentActivity((Element)context.eContainer());
		}

		return null;
	}

	/**
	 * Get the {@link AcceptEventAction} elements for the given context.
	 *
	 * @param context
	 *            the context object on which to execute this service.
	 * @return the {@link List} of {@link AcceptEventAction}
	 */
	public List<ActivityNode> getAcceptEventActions(Element context) {
		final List<ActivityNode> allActivityNodes = getActivityNodes(context);
		final List<ActivityNode> childNodes = new ArrayList<ActivityNode>(allActivityNodes);
		for (final ActivityNode activityNode : allActivityNodes) {
			if (!(activityNode instanceof AcceptEventAction)) {
				childNodes.remove(activityNode);
			}
		}

		return childNodes;
	}

	/**
	 * Get the child {@link ActivityNode} elements for the given context. This is used to retrieve the
	 * semantic candidates of {@link ActivityNode} mappings from the context semantic object which can be
	 * either the {@link Activity} or an {@link ActivityPartition}.
	 *
	 * @param context
	 *            the context object on which to execute this service.
	 * @return the {@link List} of {@link ActivityNode}
	 */
	private List<ActivityNode> getActivityNodes(Element context) {
		List<ActivityNode> childNodes;

		if (context instanceof Activity) {
			final List<ActivityNode> allActivityNodes = ((Activity)context).getOwnedNodes();
			childNodes = new ArrayList<ActivityNode>(allActivityNodes);

			for (final ActivityNode activityNode : allActivityNodes) {
				if (activityNode.getInPartitions().size() > 0) {
					for (final ActivityPartition partition : activityNode.getInPartitions()) {
						childNodes.removeAll(partition.getNodes());
					}
				}
				if (activityNode.getInInterruptibleRegions().size() > 0) {
					for (final InterruptibleActivityRegion interruptRegion : activityNode
							.getInInterruptibleRegions()) {
						childNodes.removeAll(interruptRegion.getNodes());
					}
				}
			}
		} else if (context instanceof ActivityPartition) {
			childNodes = ((ActivityPartition)context).getNodes();
		} else if (context instanceof InterruptibleActivityRegion) {
			childNodes = ((InterruptibleActivityRegion)context).getNodes();
		} else {
			childNodes = Collections.emptyList();
		}

		return childNodes;
	}

	/**
	 * Retrieves the child {@link ActivityPartition} from either {@link Activity} or {@link ActivityPartition}
	 * context object. This is used has the semantic candidates expression of AD_ActivityPartition container
	 * mapping.
	 *
	 * @param context
	 *            the context object on which to execute this service.
	 * @return a list of {@link ActivityPartition}
	 */
	public EList<ActivityPartition> getActivityPartitions(Element context) {
		if (context instanceof Activity) {
			return ((Activity)context).getPartitions();
		} else if (context instanceof ActivityPartition) {
			return ((ActivityPartition)context).getSubpartitions();
		}

		return null;
	}

	/**
	 * Get all the behaviors available in the semantic resources.
	 *
	 * @param eObj
	 *            Semantic element
	 * @return All the behaviors
	 */
	private List<EObject> getAllBehaviors(Element element) {
		final List<EObject> behaviors = Lists.newArrayList();
		final List<org.eclipse.uml2.uml.Package> rootPkgs = getAllAvailableRootPackages(element);
		for (final org.eclipse.uml2.uml.Package pkg : rootPkgs) {
			Iterators.addAll(behaviors,
					Iterators.filter(pkg.eAllContents(), Predicates.instanceOf(Behavior.class)));
		}

		return behaviors;
	}

	/**
	 * Get all the operations available in the semantic resources.
	 *
	 * @param element
	 *            Semantic element
	 * @return All the behaviors
	 */
	public List<EObject> getAllBehaviorsAndPackages(Element element) {
		final List<EObject> results = Lists.newArrayList();
		final List<EObject> behaviors = getAllBehaviors(element);
		for (EObject eObject : behaviors) {
			while (eObject.eContainer() != null) {
				results.add(eObject.eContainer());
				eObject = eObject.eContainer();
			}
		}
		results.addAll(behaviors);

		return results;
	}

	/**
	 * Get all the operations available in the semantic resources.
	 *
	 * @param eObj
	 *            Semantic element
	 * @return All the operations
	 */
	private List<EObject> getAllOperations(Element element) {
		final List<EObject> operations = Lists.newArrayList();
		final List<org.eclipse.uml2.uml.Package> rootPkgs = getAllAvailableRootPackages(element);
		final Predicate<EObject> predicate = new Predicate<EObject>() {
			public boolean apply(EObject eObj) {
				return eObj instanceof Operation && (((Operation)eObj).getMethods() == null
						|| ((Operation)eObj).getMethods().size() == 0);
			}
		};
		for (final org.eclipse.uml2.uml.Package pkg : rootPkgs) {
			Iterators.addAll(operations, Iterators.filter(pkg.eAllContents(), predicate));
		}

		return operations;
	}

	/**
	 * Get all the operations available in the semantic resources.
	 *
	 * @param eObj
	 *            Semantic element
	 * @return All the operations
	 */
	public List<EObject> getAllOperationsAndPackages(Element eObj) {
		final List<EObject> results = Lists.newArrayList();
		final List<EObject> operations = getAllOperations(eObj);
		for (EObject eObject : operations) {
			while (eObject.eContainer() != null) {
				results.add(eObject.eContainer());
				eObject = eObject.eContainer();
			}
		}
		results.addAll(operations);

		return results;
	}

	/**
	 * Get all the signals available in the semantic resources.
	 *
	 * @param element
	 *            Semantic element
	 * @return All the signals
	 */
	public List<EObject> getAllSignals(Element element) {
		final List<EObject> signals = Lists.newArrayList();
		final List<org.eclipse.uml2.uml.Package> rootPkgs = getAllAvailableRootPackages(element);
		for (final org.eclipse.uml2.uml.Package pkg : rootPkgs) {
			Iterators.addAll(signals,
					Iterators.filter(pkg.eAllContents(), Predicates.instanceOf(Signal.class)));
		}

		return signals;
	}

	/**
	 * Get call operation consistency error message.
	 *
	 * @param callOperationAction
	 *            Call operation action
	 * @return Error message
	 */
	public String getCallOperationConsistencyErrorMessage(CallOperationAction callOperationAction) {
		String message = ""; //$NON-NLS-1$
		final Operation operation = callOperationAction.getOperation();
		for (final Parameter param : operation.getOwnedParameters()) {
			if (ParameterDirectionKind.IN_LITERAL.equals(param.getDirection())) {
				final Pin pin = callOperationAction.getArgument(param.getName(), param.getType());
				if (pin == null) {
					// Can't find a param associated to the current pin we
					// ignore the target pin
					final Object[] params = new Object[] {callOperationAction.getQualifiedName(),
							param.getName(), operation.getQualifiedName()};
					message = NLS.bind(Messages.UmlValidationErrorOnCallOperationAction2, params);
				}
			} else if (ParameterDirectionKind.OUT_LITERAL.equals(param.getDirection())
					|| ParameterDirectionKind.RETURN_LITERAL.equals(param.getDirection())) {
				final Pin pin = callOperationAction.getResult(param.getName(), param.getType());
				if (pin == null) {
					// Can't find a param associated to the current pin we
					// ignore the target pin
					final Object[] params = new Object[] {callOperationAction.getQualifiedName(),
							param.getName(), operation.getQualifiedName()};
					message = NLS.bind(Messages.UmlValidationErrorOnCallOperationAction2, params);
				}
			} else {
				final Pin inputPin = callOperationAction.getArgument(param.getName(), param.getType());
				if (inputPin == null) {
					// Can't find a param associated to the current pin we
					// ignore the target pin
					final Object[] params = new Object[] {callOperationAction.getQualifiedName(),
							param.getName(), operation.getQualifiedName()};
					message = NLS.bind(Messages.UmlValidationErrorOnCallOperationAction2, params);
				}
				final Pin outputPin = callOperationAction.getResult(param.getName(), param.getType());
				if (outputPin == null) {
					// Can't find a param associated to the current pin we
					// ignore the target pin
					final Object[] params = new Object[] {callOperationAction.getQualifiedName(),
							param.getName(), operation.getQualifiedName()};
					message = NLS.bind(Messages.UmlValidationErrorOnCallOperationAction2, params);
				}
			}
		}

		return message;
	}

	/**
	 * Get call operation pin consistency error message
	 *
	 * @param pin
	 *            Pin
	 * @return Error message
	 */
	public String getCallOperationPinConsistencyErrorMessage(Pin pin) {
		String message = ""; //$NON-NLS-1$
		final String pinName = pin.getName();
		final Type pinType = pin.getType();

		final EObject container = pin.eContainer();
		if (container instanceof CallAction) {
			final CallAction callAction = (CallAction)container;
			if (callAction instanceof CallOperationAction) {
				final CallOperationAction callOperationAction = (CallOperationAction)callAction;
				final Operation operation = callOperationAction.getOperation();
				// Find parameter associated to the pin thanks to the name and
				// type
				final org.eclipse.uml2.uml.Parameter param = operation.getOwnedParameter(pinName, pinType);
				if (param == null && !"target".equals(pinName)) { //$NON-NLS-1$
					// Can't find a param associated to the current pin we
					// ignore the target pin
					final Object[] params = new Object[] {callAction.getQualifiedName(), pinName,
							operation.getQualifiedName()};
					message = NLS.bind(Messages.UmlValidationErrorOnCallOperationAction, params);
				} else if (param != null) {
					if (!param.getName().equals(pin.getName())) {
						final Object[] params = new Object[] {callAction.getQualifiedName(), pinName, "name"}; //$NON-NLS-1$
						message = NLS.bind(Messages.UmlValidationErrorOnCallOperationActionPin, params);
					} else if (!param.getType().equals(pin.getType())) {
						final Object[] params = new Object[] {callAction.getQualifiedName(), pinName, "type"}; //$NON-NLS-1$
						message = NLS.bind(Messages.UmlValidationErrorOnCallOperationActionPin, params);
					} else if (param.isOrdered() != pin.isOrdered()) {
						final Object[] params = new Object[] {callAction.getQualifiedName(), pinName,
								"ordered"}; //$NON-NLS-1$
						message = NLS.bind(Messages.UmlValidationErrorOnCallOperationActionPin, params);
					} else if (param.getLowerValue() == null && pin.getLowerValue() != null
							|| param.getLowerValue() != null && pin.getLowerValue() == null
							|| !(param.getLowerValue() == null && pin.getLowerValue() == null
									|| param.getLowerValue().stringValue()
											.equals(pin.getLowerValue().stringValue()))) {
						final Object[] params = new Object[] {callAction.getQualifiedName(), pinName,
								"lower value"}; //$NON-NLS-1$
						message = NLS.bind(Messages.UmlValidationErrorOnCallOperationActionPin, params);
					} else if (param.getUpperValue() == null && pin.getUpperValue() != null
							|| param.getUpperValue() != null && pin.getUpperValue() == null
							|| !(param.getUpperValue() == null && pin.getUpperValue() == null
									|| param.getUpperValue().stringValue()
											.equals(pin.getUpperValue().stringValue()))) {
						final Object[] params = new Object[] {callAction.getQualifiedName(), pinName,
								"upper value"}; //$NON-NLS-1$
						message = NLS.bind(Messages.UmlValidationErrorOnCallOperationActionPin, params);
					}
				}
			}
		}
		return message;
	}

	/**
	 * Get the {@link ExecutableNode} elements for the given context.
	 *
	 * @param context
	 *            the context object on which to execute this service.
	 * @return the {@link List} of {@link ExecutableNode}
	 */
	public List<ActivityNode> getExecutableNodes(Element context) {
		final List<ActivityNode> allActivityNodes = getActivityNodes(context);
		final List<ActivityNode> childNodes = new ArrayList<ActivityNode>(allActivityNodes);
		for (final ActivityNode activityNode : allActivityNodes) {
			if (activityNode instanceof AcceptEventAction) {
				childNodes.remove(activityNode);
			}
		}

		return childNodes;
	}

	/**
	 * Get input parameters.
	 *
	 * @param operation
	 *            Operation
	 * @return Input parameters
	 */
	public List<Parameter> getInputParameters(Operation operation) {
		return operation.inputParameters();
	}

	/**
	 * Retrieves the child {@link InterruptibleActivityRegion} from either {@link Activity} or
	 * {@link InterruptibleActivityRegion} context object. This is used has the semantic candidates expression
	 * of AD_InterruptibleActivityRegion container mapping.
	 *
	 * @param context
	 *            the context object on which to execute this service.
	 * @return a list of {@link InterruptibleActivityRegion}
	 */
	public List<InterruptibleActivityRegion> getInterruptibleActivityRegions(Element context) {
		final List<InterruptibleActivityRegion> interruptibleRegions = Lists.newArrayList();
		if (context instanceof Activity) {
			for (final ActivityGroup group : ((Activity)context).getOwnedGroups()) {
				if (group instanceof InterruptibleActivityRegion) {
					interruptibleRegions.add((InterruptibleActivityRegion)group);
				}
			}
		}

		return interruptibleRegions;
	}

	/**
	 * Get the interruptible region associated to an activity node.
	 *
	 * @param node
	 *            Activity node
	 * @return Interruptible region
	 */
	public InterruptibleActivityRegion getInterruptibleRegion(ActivityNode node) {
		return node.getInInterruptibleRegions().get(0);
	}

	/**
	 * Get output parameters.
	 *
	 * @param operation
	 *            Operation
	 * @return Output parameters
	 */
	public List<Parameter> getOutputParameters(Operation operation) {
		return operation.outputParameters();
	}

	/**
	 * Check if an accept event action is an accept call action.
	 *
	 * @param action
	 *            Action
	 * @return True if it is a accept call action otherwise false
	 */
	public boolean isAcceptCallAction(AcceptEventAction action) {
		if (action != null && action.getTriggers() != null && action.getTriggers().size() == 1) {
			final Trigger trigger = action.getTriggers().get(0);
			if (trigger.getEvent() != null && trigger.getEvent() instanceof CallEvent) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if an accept event action is an accept signal action.
	 *
	 * @param action
	 *            Action
	 * @return True if it is a accept signal action otherwise false
	 */
	public boolean isAcceptSignalAction(AcceptEventAction action) {
		if (action != null && action.getTriggers() != null && action.getTriggers().size() == 1) {
			final Trigger trigger = action.getTriggers().get(0);
			if (trigger.getEvent() != null && trigger.getEvent() instanceof SignalEvent) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if call operation action is consistent.
	 *
	 * @param callOperationAction
	 *            Action
	 * @return True if is consistent
	 */
	public boolean isConsistent(CallOperationAction callOperationAction) {
		return !isNotConsistent(callOperationAction);
	}

	/**
	 * Check if pin is consistent.
	 *
	 * @param pin
	 *            Pin
	 * @return True if is consistent
	 */
	public boolean isConsistent(Pin pin) {
		return !isNotConsistent(pin);
	}

	/**
	 * Check if call operation action is not consistent.
	 *
	 * @param callOperationAction
	 *            Action
	 * @return True if is not consistent
	 */
	public boolean isNotConsistent(CallOperationAction callOperationAction) {
		return getCallOperationConsistencyErrorMessage(callOperationAction).length() > 0;
	}

	/**
	 * Check if pin is not consistent.
	 *
	 * @param pin
	 *            Pin
	 * @return True if is not consistent
	 */
	public boolean isNotConsistent(Pin pin) {
		return getCallOperationPinConsistencyErrorMessage(pin).length() > 0;
	}

	/**
	 * Check if is a valid activity edge end.
	 *
	 * @param source
	 *            Edge source
	 * @param preTarget
	 *            Element
	 * @return True if element is a valid activity edge end
	 */
	public boolean isValidActivityEdgeEnd(Element source, Element preTarget) {
		return isValidFlowEnd(source, preTarget);
	}

	/**
	 * Check if is a valid control flow start.
	 *
	 * @param preSource
	 *            Source
	 * @return True if source is a valid control flow start
	 */
	public boolean isValidControlFlowStart(Element preSource) {
		// ForkNode, JoinNode, MergeNode, DecisionNode : if the incoming edge is
		// a ControlFlow, then all
		// outgoing edges shall be ControlFlows
		if (preSource instanceof ActivityNode) {
			for (final ActivityEdge incoming : ((ActivityNode)preSource).getIncomings()) {
				if (incoming instanceof ObjectFlow) {
					return false;
				}
			}
		}

		return isValidFlowStart(preSource);
	}

	private boolean isValidFlowEnd(Element source, Element preTarget) {
		if (source instanceof InputPin) {
			return false;
		}
		if (source instanceof OutputPin && preTarget instanceof OutputPin) {
			return false;
		}
		if (source instanceof ActivityParameterNode && preTarget instanceof OutputPin) {
			return false;
		}
		if (source instanceof DataStoreNode && preTarget instanceof OutputPin) {
			return false;
		}

		// InitialNode shall not have any incoming ActivityEdges
		if (preTarget instanceof InitialNode) {
			return false;
		}

		// A ForkNode shall have exactly one incoming ActivityEdge, though it
		// may have multiple outgoing
		// ActivityEdges.
		if (preTarget instanceof ForkNode) {
			final List<ActivityEdge> incomings = ((ForkNode)preTarget).getIncomings();
			if (incomings != null && incomings.size() == 1) {
				return false;
			}
		}

		// A DecisionNode shall have at least one and at most two incoming
		// ActivityEdges
		if (preTarget instanceof DecisionNode) {
			final List<ActivityEdge> incomings = ((DecisionNode)preTarget).getIncomings();
			if (incomings != null && incomings.size() == 2) {
				return false;
			}
		}

		return true;
	}

	private boolean isValidFlowStart(Element preSource) {
		// A FinalNode shall not have outgoing ActivityEdges
		if (preSource instanceof FinalNode) {
			return false;
		}

		// A JoinNode/MergeNode shall have exactly one outgoing ActivityEdge but
		// may have
		// multiple incoming ActivityEdges.
		if (preSource instanceof JoinNode || preSource instanceof MergeNode) {
			final List<ActivityEdge> outgoing = ((ActivityNode)preSource).getOutgoings();
			if (outgoing != null && outgoing.size() == 1) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Check if the selected opaque action is not in the same interrupted region than the selected source of
	 * the interrupting edge.
	 *
	 * @param preTarget
	 *            Pre selected target for the interrupting edge
	 * @param source
	 *            Selected source element of the interrupting edge
	 * @return True the target element is valid else false
	 */
	public boolean isValidInterruptingEdgeEnd(OpaqueAction preTarget, OpaqueAction source) {
		final List<InterruptibleActivityRegion> preTargetRegions = preTarget.getInInterruptibleRegions();
		if (preTargetRegions != null) {
			for (final InterruptibleActivityRegion sourceRegion : source.getInInterruptibleRegions()) {
				if (preTargetRegions.contains(sourceRegion)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check if is a valid object flow start.
	 *
	 * @param preSource
	 *            Source
	 * @return True if source is a valid object flow start
	 */
	public boolean isValidObjectFlowStart(Element preSource) {
		// The outgoing ActivityEdges of an InitialNode must all be ControlFlows
		if (preSource instanceof InitialNode) {
			return false;
		}
		if (preSource instanceof InputPin) {
			return false;
		}
		// ForkNode, JoinNode, MergeNode, DecisionNode : if the incoming edge is
		// an ObjectFlow, then all
		// outgoing edges shall be ObjectFlows
		if (preSource instanceof ActivityNode) {
			for (final ActivityEdge incoming : ((ActivityNode)preSource).getIncomings()) {
				if (incoming instanceof ControlFlow) {
					return false;
				}
			}
		}

		return isValidFlowStart(preSource);
	}

	/**
	 * Check if an accept event action is a wait time action.
	 *
	 * @param action
	 *            Action
	 * @return True if it is a wait time action otherwise false
	 */
	public boolean isWaitTimeAction(AcceptEventAction action) {
		if (action != null && action.getTriggers() != null && action.getTriggers().size() == 1) {
			final Trigger trigger = action.getTriggers().get(0);
			if (trigger.getEvent() != null && trigger.getEvent() instanceof TimeEvent) {
				return true;
			}
		}
		return false;
	}

	private void setPinAttributes(Parameter parameter, final Pin pin) {
		pin.setName(parameter.getName());
		pin.setIsOrdered(parameter.isOrdered());
		pin.setType(parameter.getType());
		pin.setLowerValue(parameter.getLowerValue());
		pin.setUpperValue(parameter.getUpperValue());
	}
}
