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
package org.obeonetwork.dsl.uml2.design.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.ExecutionEvent;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OccurrenceSpecification;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.ReceiveSignalEvent;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.SendSignalEvent;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.services.internal.NamedElementServices;

import fr.obeo.dsl.common.ui.tools.api.util.EclipseUIUtil;
import fr.obeo.dsl.viewpoint.DDiagram;
import fr.obeo.dsl.viewpoint.DDiagramElement;
import fr.obeo.dsl.viewpoint.DEdge;
import fr.obeo.dsl.viewpoint.DNode;
import fr.obeo.dsl.viewpoint.DRepresentation;
import fr.obeo.dsl.viewpoint.business.api.dialect.DialectManager;
import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.business.api.session.SessionManager;
import fr.obeo.dsl.viewpoint.diagram.sequence.business.internal.elements.ISequenceEvent;
import fr.obeo.dsl.viewpoint.diagram.sequence.business.internal.elements.SequenceDiagram;
import fr.obeo.dsl.viewpoint.diagram.sequence.business.internal.operation.RefreshGraphicalOrderingOperation;
import fr.obeo.dsl.viewpoint.diagram.sequence.business.internal.operation.RefreshSemanticOrderingOperation;
import fr.obeo.dsl.viewpoint.diagram.sequence.business.internal.operation.SynchronizeGraphicalOrderingOperation;
import fr.obeo.dsl.viewpoint.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import fr.obeo.dsl.viewpoint.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import fr.obeo.dsl.viewpoint.diagram.tools.api.editor.DDiagramEditor;

/**
 * Utility services to manage sequence diagrams.
 * 
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class SequenceServices {
	/**
	 * Signal name suffix.
	 */
	private static final String SIGNAL_SUFFIX = "_signal";

	/**
	 * Event message name suffix.
	 */
	private static final String EVENT_MESSAGE_SUFFIX = "_event";

	/**
	 * Sender message name suffix.
	 */
	private static final String SENDER_MESSAGE_SUFFIX = "_sender";

	/**
	 * Receiver message name suffix.
	 */
	private static final String RECEIVER_MESSAGE_SUFFIX = "_receiver";

	/**
	 * Logger.
	 */
	private LogServices logger = new LogServices();

	/**
	 * Operation service.
	 */
	private OperationServices operationService = new OperationServices();

	/**
	 * Retrieves the context element ({@link Lifeline} or {@link ExecutionSpecification}) of the given
	 * {@link OccurrenceSpecification}.
	 * 
	 * @param occurrenceSpecification
	 *            the {@link OccurrenceSpecification} for which to find the context
	 * @return the {@link ExecutionSpecification} on which the given {@link OccurrenceSpecification} is
	 *         attached or otherwise, the {@link Lifeline}otherwise it is attached to.
	 */
	public NamedElement findOccurrenceSpecificationContext(OccurrenceSpecification occurrenceSpecification) {
		final Lifeline lifeline = occurrenceSpecification.getCovereds().get(0);
		final Stack<NamedElement> context = new Stack<NamedElement>();
		context.add(lifeline);

		final List<InteractionFragment> allFragments = occurrenceSpecification.getEnclosingInteraction()
				.getFragments();
		final List<InteractionFragment> fragments = new ArrayList<InteractionFragment>();
		for (InteractionFragment fragment : allFragments) {
			if (fragment.getCovered(lifeline.getName()) != null)
				fragments.add(fragment);
		}

		for (int i = 0; i < fragments.size(); i++) {
			final InteractionFragment e = fragments.get(i);
			InteractionFragment en;
			if (i + 1 < fragments.size())
				en = fragments.get(i + 1);
			else
				en = null;

			if (e instanceof MessageOccurrenceSpecification && en != null
					&& en instanceof ExecutionSpecification)
				context.add(en);

			if (e instanceof ExecutionOccurrenceSpecification) {
				if (en == null || !(en instanceof ExecutionSpecification))
					context.pop();
			}

			// Found our element
			if (e == occurrenceSpecification) {
				return context.peek();
			}

			if (e instanceof ExecutionOccurrenceSpecification) {
				if (en != null && en instanceof ExecutionSpecification)
					context.add(fragments.get(i + 1));
			}

			if (e instanceof MessageOccurrenceSpecification && isEnd(e, fragments)) {
				context.pop();
			}
		}

		return lifeline;
	}

	/**
	 * Check if fragment is an execution finish.
	 * 
	 * @param endCandidate
	 *            Element to check
	 * @param fragments
	 *            Defined fragments
	 * @return True if element is the end of an execution
	 */
	private boolean isEnd(InteractionFragment endCandidate, List<InteractionFragment> fragments) {
		final List<InteractionFragment> executionFinishes = new ArrayList<InteractionFragment>();
		for (InteractionFragment fragment : fragments) {
			if (fragment instanceof BehaviorExecutionSpecification) {
				// Get start
				final BehaviorExecutionSpecification behavior = (BehaviorExecutionSpecification)fragment;
				// Get finish
				executionFinishes.add(behavior.getFinish());
			}
		}
		return executionFinishes.contains(endCandidate);
	}

	/**
	 * Get the execution associated to a fragment.
	 * 
	 * @param occurence
	 *            Occurence
	 * @return Execution
	 */
	private BehaviorExecutionSpecification getExecution(InteractionFragment occurence) {
		if (occurence == null)
			return null;
		final Map<InteractionFragment, BehaviorExecutionSpecification> behaviors = new HashMap<InteractionFragment, BehaviorExecutionSpecification>();
		for (InteractionFragment fragment : occurence.getEnclosingInteraction().getFragments()) {
			if (fragment instanceof BehaviorExecutionSpecification) {
				final BehaviorExecutionSpecification behavior = (BehaviorExecutionSpecification)fragment;
				// Get start
				behaviors.put(behavior.getStart(), behavior);
				// Get finish
				behaviors.put(behavior.getFinish(), behavior);
			}
		}
		return behaviors.get(occurence);
	}

	/**
	 * Finds the first level of {@link ExecutionSpecification} in the context of the given {@link Lifeline}.
	 * 
	 * @param lifeline
	 *            the context.
	 * @return the {@link ExecutionSpecification} semantic candidates.
	 */
	public List<ExecutionSpecification> executionSemanticCandidates(Lifeline lifeline) {
		return getFirstLevelExecutions(lifeline, lifeline.getInteraction().getFragments());
	}

	/**
	 * Finds the first level of {@link ExecutionSpecification} in the context of the given
	 * {@link ExecutionSpecification}.
	 * 
	 * @param execution
	 *            the context.
	 * @return the {@link ExecutionSpecification} semantic candidates.
	 */
	public List<ExecutionSpecification> executionSemanticCandidates(ExecutionSpecification execution) {
		final List<InteractionFragment> fragments = execution.getEnclosingInteraction().getFragments();

		final int startIndex = fragments.indexOf(execution.getStart());
		final int finishIndex = fragments.indexOf(execution.getFinish());
		final List<InteractionFragment> candidateFragments = new ArrayList<InteractionFragment>(
				fragments.subList(startIndex + 2, finishIndex));
		return getFirstLevelExecutions(execution.getCovereds().get(0), candidateFragments);
	}

	/**
	 * Finds the first level of {@link ExecutionSpecification} in the context of the given
	 * {@link ExecutionSpecification}.
	 * 
	 * @param execution
	 *            the context.
	 * @return the {@link ExecutionSpecification} semantic candidates.
	 */
	public List<OccurrenceSpecification> executionSemanticCandidateOccurences(ExecutionSpecification execution) {
		final List<ExecutionSpecification> executions = executionSemanticCandidates(execution);
		final List<OccurrenceSpecification> occurrences = new ArrayList<OccurrenceSpecification>();
		occurrences.add(execution.getStart());
		occurrences.add(execution.getFinish());
		for (ExecutionSpecification subExecution : executions) {
			occurrences.add(subExecution.getStart());
			occurrences.add(subExecution.getFinish());
		}
		return occurrences;
	}

	/**
	 * Finds the first level of {@link ExecutionSpecification} in the context of the given
	 * {@link ExecutionSpecification}.
	 * 
	 * @param lifeline
	 *            the lifeline.
	 * @return the {@link ExecutionSpecification} semantic candidates.
	 */
	public List<OccurrenceSpecification> executionSemanticCandidateOccurences(Lifeline lifeline) {
		final List<ExecutionSpecification> executions = executionSemanticCandidates(lifeline);
		final List<OccurrenceSpecification> occurrences = new ArrayList<OccurrenceSpecification>();
		for (ExecutionSpecification subExecution : executions) {
			occurrences.add(subExecution.getStart());
			occurrences.add(subExecution.getFinish());
		}
		return occurrences;
	}

	/**
	 * Find the first level of {@link ExecutionSpecification} in the given {@link InteractionFragment} list.
	 * 
	 * @param lifeline
	 *            the {@link Lifeline} which is covered by the searched {@link ExecutionSpecification}
	 * @param candidateFragments
	 *            a sub-list of {@link InteractionFragment} to inspect for the first
	 *            {@link ExecutionSpecification} level.
	 * @return {@link List} of the {@link ExecutionSpecification}
	 */
	private List<ExecutionSpecification> getFirstLevelExecutions(Lifeline lifeline,
			final List<InteractionFragment> candidateFragments) {
		final List<ExecutionSpecification> executions = new ArrayList<ExecutionSpecification>();
		ExecutionSpecification subExec = null;
		for (InteractionFragment fragment : candidateFragments) {
			if (fragment instanceof ExecutionSpecification && fragment.getCovereds().contains(lifeline)) {
				// Element on the same lifeline
				if (subExec == null) {
					subExec = (ExecutionSpecification)fragment;
				}
			} else if (fragment instanceof OccurrenceSpecification && subExec != null
					&& fragment.equals(subExec.getFinish())) {
				executions.add(subExec);
				subExec = null;
			}
		}

		return executions;
	}

	/**
	 * Delete the all the semantic elements attached to the given node. This will find the semantic elements
	 * of the current node and those coming recursively from sub-bordered nodes and linked edges.
	 * 
	 * @param node
	 *            the root node to delete.
	 * @return the parent diagram.
	 */
	public DDiagram fullDelete(DNode node) {
		final Collection<EObject> elementsToDelete = getSemanticElementsToDelete(node);

		for (EObject eObject : elementsToDelete) {
			EcoreUtil.remove(eObject);
		}

		return node.getParentDiagram();
	}

	/**
	 * Retrieves all the semantic elements of the current node, those of the incoming and outgoing edges and
	 * recursively along the sub-bordered node tree.
	 * 
	 * @param node
	 *            the root {@link DNode}
	 * @return the list of attached semantic elements
	 */
	private static Collection<EObject> getSemanticElementsToDelete(DNode node) {
		final Collection<EObject> elementsToDelete = new LinkedHashSet<EObject>();

		elementsToDelete.addAll(node.getSemanticElements());

		for (DEdge incomingEdge : node.getIncomingEdges()) {
			elementsToDelete.addAll(incomingEdge.getSemanticElements());
		}

		for (DEdge outgoingEdges : node.getOutgoingEdges()) {
			elementsToDelete.addAll(outgoingEdges.getSemanticElements());
		}

		for (DNode borderedNode : node.getOwnedBorderedNodes()) {
			elementsToDelete.addAll(getSemanticElementsToDelete(borderedNode));
		}

		return elementsToDelete;
	}

	/**
	 * Create a lifeline. Lifeline could be created in an interaction.
	 * 
	 * @param interaction
	 *            Interaction
	 * @param element
	 *            Instance specification or property associated to lifeline
	 */
	public void createLifeline(Interaction interaction, NamedElement element) {
		// If the element selected in the selection wizard is not an instance specification or a property,
		// return a warning in error log view
		if (!(element instanceof InstanceSpecification) && !(element instanceof Property)) {
			logger.warning(
					"An instance specification or a property must be selected to import a lifeline but you have selected "
							+ element.getName() + " which is a " + element.getClass().getSimpleName(), null);
		}

		// Create lifeline
		if (element instanceof InstanceSpecification) {
			createLifeline(interaction, (InstanceSpecification)element);
		} else {
			createLifeline(interaction, (Property)element);
		}
	}

	/**
	 * Create a lifeline. Lifeline could be created in an interaction.
	 * 
	 * @param interaction
	 *            Interaction
	 * @param instance
	 *            Instance specification associated to lifeline
	 */
	private void createLifeline(Interaction interaction, InstanceSpecification instance) {
		// Create lifeline
		final Lifeline lifeline = UMLFactory.eINSTANCE.createLifeline();
		lifeline.setName(((InstanceSpecification)instance).getName());
		final Dependency dependency = UMLFactory.eINSTANCE.createDependency();
		dependency.setName(lifeline.getName() + "_" + instance.getName());
		dependency.getClients().add(lifeline);
		dependency.getSuppliers().add(instance);
		interaction.getNearestPackage().getPackagedElements().add(dependency);
		lifeline.getClientDependencies().add(dependency);
		interaction.getLifelines().add(lifeline);
	}

	/**
	 * Create a lifeline. Lifeline could be created in an interaction.
	 * 
	 * @param interaction
	 *            Interaction
	 * @param property
	 *            Property associated to lifeline
	 */
	private void createLifeline(Interaction interaction, Property property) {
		// Create lifeline
		final Lifeline lifeline = UMLFactory.eINSTANCE.createLifeline();
		lifeline.setName(property.getName());
		lifeline.setRepresents(property);
		interaction.getLifelines().add(lifeline);
	}

	/**
	 * Create a typed execution. Execution could be created on lifeline or other parent execution.
	 * 
	 * @param interaction
	 *            Interaction
	 * @param fragment
	 *            Lifeline or parent execution
	 * @param operation
	 *            Operation associated to execution
	 * @param startingEndPredecessor
	 *            Starting end predecessor
	 */
	public void createExecution(Interaction interaction, NamedElement fragment, Operation operation,
			NamedElement startingEndPredecessor) {
		final Lifeline lifeline = getLifeline(fragment);

		final UMLFactory factory = UMLFactory.eINSTANCE;
		StringBuffer executionName;
		if (operation == null) {
			final List<BehaviorExecutionSpecification> behaviors = new ArrayList<BehaviorExecutionSpecification>();
			for (InteractionFragment behavior : interaction.getFragments()) {
				if (behavior instanceof BehaviorExecutionSpecification)
					behaviors.add((BehaviorExecutionSpecification)behavior);
			}
			executionName = new StringBuffer("BehaviorExecution_").append(behaviors.size());
		} else {
			executionName = new StringBuffer(operation.getName());
		}

		// Create execution start
		final ExecutionOccurrenceSpecification startExec = factory.createExecutionOccurrenceSpecification();
		final StringBuffer startExecName = new StringBuffer(executionName).append("_start");
		startExec.setName(startExecName.toString());
		startExec.getCovereds().add(lifeline);
		// Create start event
		final ExecutionEvent startEvent = factory.createExecutionEvent();
		startEvent.setName(startExecName.append(EVENT_MESSAGE_SUFFIX).toString());
		startExec.setEvent(startEvent);

		// Create behavior
		final OpaqueBehavior behavior = factory.createOpaqueBehavior();
		behavior.setName(executionName.toString());
		behavior.setSpecification(operation);
		interaction.getOwnedBehaviors().add(behavior);
		final BehaviorExecutionSpecification execution = factory.createBehaviorExecutionSpecification();
		execution.setName(executionName.toString());
		execution.getCovereds().add(lifeline);
		execution.setBehavior(behavior);

		execution.setStart(startExec);
		startExec.setExecution(execution);

		// Create execution end
		final ExecutionOccurrenceSpecification endExec = factory.createExecutionOccurrenceSpecification();
		final StringBuffer endExecName = new StringBuffer(executionName).append("_finish");
		endExec.setName(endExecName.toString());
		endExec.getCovereds().add(lifeline);
		endExec.setExecution(execution);
		execution.setFinish(endExec);
		// Create end event
		final ExecutionEvent endEvent = factory.createExecutionEvent();
		endEvent.setName(endExecName.append(EVENT_MESSAGE_SUFFIX).toString());
		endExec.setEvent(endEvent);

		// Add and order fragments under the interaction
		final EList<InteractionFragment> fragments = interaction.getFragments();

		// Ordered fragments
		fragments.add(startExec);
		startExec.getNearestPackage().getPackagedElements().add(startEvent);

		// If execution starts from an execution, add the new execution start after the execution
		// specification
		if (startingEndPredecessor instanceof OccurrenceSpecification
				&& getExecution((OccurrenceSpecification)startingEndPredecessor) != null
				&& startingEndPredecessor
						.equals(getExecution((OccurrenceSpecification)startingEndPredecessor).getStart()))
			fragments.move(fragments.indexOf(startingEndPredecessor) + 2, startExec);
		else
			// Message starts from a lifeline, add the message start after the last starting predecessor
			// (message)
			fragments.move(fragments.indexOf(startingEndPredecessor) + 1, startExec);
		fragments.add(execution);
		fragments.move(fragments.indexOf(startExec) + 1, execution);
		fragments.add(endExec);
		endExec.getNearestPackage().getPackagedElements().add(endEvent);
		fragments.move(fragments.indexOf(execution) + 1, endExec);
	}

	/**
	 * Create a typed execution. Execution could be created on lifeline or other parent execution.
	 * 
	 * @param interaction
	 *            Interaction
	 * @param fragment
	 *            Lifeline or parent execution
	 * @param startingEndPredecessor
	 *            Starting end predecessor
	 */
	public void createExecution(Interaction interaction, NamedElement fragment,
			NamedElement startingEndPredecessor) {
		createExecution(interaction, fragment, null, startingEndPredecessor);
	}

	/**
	 * Create asynchronous typed message.
	 * 
	 * @param interaction
	 *            Interaction
	 * @param sourceFragment
	 *            Source
	 * @param targetFragment
	 *            Target
	 * @param operation
	 *            Operation associated to message
	 * @param startingEndPredecessor
	 *            Starting end predecessor
	 * @param finishingEndPredecessor
	 *            Finishing end predecessor
	 */
	public void createAsynchronousMessage(Interaction interaction, NamedElement sourceFragment,
			NamedElement targetFragment, Operation operation, NamedElement startingEndPredecessor,
			NamedElement finishingEndPredecessor) {
		final Lifeline source = getLifeline(sourceFragment);
		final Lifeline target = getLifeline(targetFragment);
		final BehaviorExecutionSpecification predecessorExecution = getExecution((InteractionFragment)startingEndPredecessor);

		final UMLFactory factory = UMLFactory.eINSTANCE;
		final EList<InteractionFragment> fragments = interaction.getFragments();

		// Create message
		final Message message = factory.createMessage();
		StringBuffer operationName;
		if (operation == null)
			operationName = new StringBuffer("Message_").append(interaction.getMessages().size());
		else
			operationName = new StringBuffer(operation.getName());

		message.setName(operationName.toString());
		message.setMessageSort(MessageSort.ASYNCH_CALL_LITERAL);
		interaction.getMessages().add(message);

		// Create message send event
		final MessageOccurrenceSpecification senderEventMessage = factory
				.createMessageOccurrenceSpecification();
		final StringBuffer senderEventName = new StringBuffer(operationName).append(SENDER_MESSAGE_SUFFIX);
		senderEventMessage.setName(senderEventName.toString());
		senderEventMessage.getCovereds().add(source);
		senderEventMessage.setMessage(message);

		// Create message receive event
		final MessageOccurrenceSpecification receiverEventMessage = factory
				.createMessageOccurrenceSpecification();
		final StringBuffer receiverEventName = new StringBuffer(operationName)
				.append(RECEIVER_MESSAGE_SUFFIX);
		receiverEventMessage.setName(receiverEventName.toString());
		receiverEventMessage.getCovereds().add(target);
		receiverEventMessage.setMessage(message);

		message.setSendEvent(senderEventMessage);
		message.setReceiveEvent(receiverEventMessage);

		// Create behavior
		BehaviorExecutionSpecification execution = null;
		if (operation != null) {
			final OpaqueBehavior behavior = factory.createOpaqueBehavior();
			behavior.setName(operationName.toString());
			if (targetFragment instanceof ExecutionSpecification)
				behavior.setSpecification(operation);
			interaction.getOwnedBehaviors().add(behavior);

			execution = factory.createBehaviorExecutionSpecification();
			execution.setName(operationName.toString());
			execution.getCovereds().add(target);
			execution.setBehavior(behavior);

			// Create send event
			final SendOperationEvent sendEvent = factory.createSendOperationEvent();
			sendEvent.setName(senderEventName.append(EVENT_MESSAGE_SUFFIX).toString());
			senderEventMessage.setEvent(sendEvent);
			sendEvent.setOperation(operation);
			// Create receive event
			final ReceiveOperationEvent receiveEvent = factory.createReceiveOperationEvent();
			receiveEvent.setName(receiverEventName.append(EVENT_MESSAGE_SUFFIX).toString());
			receiverEventMessage.setEvent(receiveEvent);
			receiveEvent.setOperation(operation);
			message.getNearestPackage().getPackagedElements().add(sendEvent);
			message.getNearestPackage().getPackagedElements().add(receiveEvent);
		} else {
			// Create send event
			final SendSignalEvent sendEvent = factory.createSendSignalEvent();
			sendEvent.setName(senderEventName.append(EVENT_MESSAGE_SUFFIX).toString());
			senderEventMessage.setEvent(sendEvent);
			// Create receive event
			final ReceiveSignalEvent receiveEvent = factory.createReceiveSignalEvent();
			receiveEvent.setName(receiverEventName.append(EVENT_MESSAGE_SUFFIX).toString());
			receiverEventMessage.setEvent(receiveEvent);
			// Create signal
			final Signal signal = factory.createSignal();
			signal.setName(operationName.append(SIGNAL_SUFFIX).toString());
			sendEvent.setSignal(signal);
			receiveEvent.setSignal(signal);
			message.getNearestPackage().getPackagedElements().add(signal);
			message.getNearestPackage().getPackagedElements().add(sendEvent);
			message.getNearestPackage().getPackagedElements().add(receiveEvent);
		}

		ExecutionOccurrenceSpecification endExec = null;
		if (execution != null) {
			execution.setStart(receiverEventMessage);
			// Create end execution
			endExec = factory.createExecutionOccurrenceSpecification();
			final StringBuffer executionEndName = new StringBuffer(execution.getName()).append("_finish");
			endExec.setName(executionEndName.toString());
			endExec.getCovereds().add(target);
			endExec.setExecution(execution);
			execution.setFinish(endExec);
			// Create end event
			final ExecutionEvent endEvent = factory.createExecutionEvent();
			endEvent.setName(executionEndName.append(EVENT_MESSAGE_SUFFIX).toString());
			endExec.setEvent(endEvent);
			message.getNearestPackage().getPackagedElements().add(endEvent);
		}

		// Add and order fragments under the interaction
		// Add message after starting end predecessor
		fragments.add(senderEventMessage);

		// If predecessor is the beginning of an execution add message after the execution
		if (startingEndPredecessor != null && startingEndPredecessor instanceof OccurrenceSpecification
				&& predecessorExecution != null
				&& startingEndPredecessor.equals(predecessorExecution.getStart()))
			fragments.move(fragments.indexOf(predecessorExecution) + 1, senderEventMessage);
		// Else set it directly after the predecessor
		else
			fragments.move(fragments.indexOf(startingEndPredecessor) + 1, senderEventMessage);
		fragments.add(receiverEventMessage);
		fragments.move(fragments.indexOf(senderEventMessage) + 1, receiverEventMessage);
		if (execution != null) {
			fragments.add(execution);
			fragments.add(endExec);
			fragments.move(fragments.indexOf(receiverEventMessage) + 1, execution);
			fragments.move(fragments.indexOf(execution) + 1, endExec);
		}
	}

	/**
	 * Create asynchronous message.
	 * 
	 * @param interaction
	 *            Interaction
	 * @param sourceFragment
	 *            Source
	 * @param targetFragment
	 *            Target
	 * @param startingEndPredecessor
	 *            Starting end predecessor
	 * @param finishingEndPredecessor
	 *            Finishing end predecessor
	 */
	public void createAsynchronousMessage(Interaction interaction, NamedElement sourceFragment,
			NamedElement targetFragment, NamedElement startingEndPredecessor,
			NamedElement finishingEndPredecessor) {
		createAsynchronousMessage(interaction, sourceFragment, targetFragment, null, startingEndPredecessor,
				finishingEndPredecessor);
	}

	/**
	 * Create synchronous typed message.
	 * 
	 * @param interaction
	 *            Interaction
	 * @param sourceFragment
	 *            Source
	 * @param targetFragment
	 *            Target
	 * @param operation
	 *            Operation associated to message
	 * @param startingEndPredecessor
	 *            Starting end predecessor
	 * @param finishingEndPredecessor
	 *            Finishing end predecessor
	 */
	public void createSynchronousMessage(Interaction interaction, NamedElement sourceFragment,
			NamedElement targetFragment, Operation operation, NamedElement startingEndPredecessor,
			NamedElement finishingEndPredecessor) {
		final Lifeline source = getLifeline(sourceFragment);
		final Lifeline target = getLifeline(targetFragment);
		final BehaviorExecutionSpecification predecessorExecution = getExecution((InteractionFragment)startingEndPredecessor);

		final UMLFactory factory = UMLFactory.eINSTANCE;
		final EList<InteractionFragment> fragments = interaction.getFragments();

		// Create message
		final Message message = factory.createMessage();
		StringBuffer operationName;
		if (operation == null)
			operationName = new StringBuffer("Message_").append(interaction.getMessages().size());
		else
			operationName = new StringBuffer(operation.getName());

		message.setName(operationName.toString());
		message.setMessageSort(MessageSort.SYNCH_CALL_LITERAL);
		interaction.getMessages().add(message);

		// Create message send event
		final MessageOccurrenceSpecification senderEventMessage = factory
				.createMessageOccurrenceSpecification();
		final StringBuffer senderEventName = new StringBuffer(operationName).append(SENDER_MESSAGE_SUFFIX);
		senderEventMessage.setName(senderEventName.toString());
		senderEventMessage.getCovereds().add(source);
		senderEventMessage.setMessage(message);

		// Create message receive event
		final MessageOccurrenceSpecification receiverEventMessage = factory
				.createMessageOccurrenceSpecification();
		final StringBuffer receiverEventName = new StringBuffer(operationName)
				.append(RECEIVER_MESSAGE_SUFFIX);
		receiverEventMessage.setName(receiverEventName.toString());
		receiverEventMessage.getCovereds().add(target);
		receiverEventMessage.setMessage(message);

		message.setSendEvent(senderEventMessage);
		message.setReceiveEvent(receiverEventMessage);

		// Create behavior
		BehaviorExecutionSpecification execution = null;
		if (operation != null) {
			final OpaqueBehavior behavior = factory.createOpaqueBehavior();
			behavior.setName(operationName.toString());
			if (operation != null || targetFragment instanceof ExecutionSpecification)
				behavior.setSpecification(operation);
			interaction.getOwnedBehaviors().add(behavior);

			execution = factory.createBehaviorExecutionSpecification();
			execution.setName(operationName.toString());
			execution.getCovereds().add(target);
			execution.setBehavior(behavior);

			// Create send event
			final SendOperationEvent sendEvent = factory.createSendOperationEvent();
			sendEvent.setName(senderEventName.append(EVENT_MESSAGE_SUFFIX).toString());
			senderEventMessage.setEvent(sendEvent);
			sendEvent.setOperation(operation);
			// Create receive event
			final ReceiveOperationEvent receiveEvent = factory.createReceiveOperationEvent();
			receiveEvent.setName(receiverEventName.append(EVENT_MESSAGE_SUFFIX).toString());
			receiverEventMessage.setEvent(receiveEvent);
			receiveEvent.setOperation(operation);
			message.getNearestPackage().getPackagedElements().add(sendEvent);
			message.getNearestPackage().getPackagedElements().add(receiveEvent);
		} else {
			// Create send event
			final SendSignalEvent sendEvent = factory.createSendSignalEvent();
			sendEvent.setName(senderEventName.append(EVENT_MESSAGE_SUFFIX).toString());
			senderEventMessage.setEvent(sendEvent);
			// Create receive event
			final ReceiveSignalEvent receiveEvent = factory.createReceiveSignalEvent();
			receiveEvent.setName(receiverEventName.append(EVENT_MESSAGE_SUFFIX).toString());
			receiverEventMessage.setEvent(receiveEvent);
			// Create signal
			final Signal signal = factory.createSignal();
			final StringBuffer signalName = new StringBuffer(operationName).append(SIGNAL_SUFFIX);
			signal.setName(signalName.toString());
			sendEvent.setSignal(signal);
			receiveEvent.setSignal(signal);
			message.getNearestPackage().getPackagedElements().add(signal);
			message.getNearestPackage().getPackagedElements().add(sendEvent);
			message.getNearestPackage().getPackagedElements().add(receiveEvent);
		}
		if (execution != null)
			execution.setStart(receiverEventMessage);

		// Create reply message
		final Message replyMessage = factory.createMessage();
		final StringBuffer replyName = new StringBuffer(operationName).append("_reply");
		replyMessage.setName(replyName.toString());
		replyMessage.setMessageSort(MessageSort.REPLY_LITERAL);
		interaction.getMessages().add(replyMessage);

		// Create reply message send event
		final MessageOccurrenceSpecification senderEventReplyMessage = factory
				.createMessageOccurrenceSpecification();
		final StringBuffer senderReplyEventName = new StringBuffer(replyName).append(SENDER_MESSAGE_SUFFIX);
		senderEventReplyMessage.setName(senderReplyEventName.toString());
		senderEventReplyMessage.getCovereds().add(target);
		senderEventReplyMessage.setMessage(replyMessage);

		// Create reply message receive event
		final MessageOccurrenceSpecification receiverEventReplyMessage = factory
				.createMessageOccurrenceSpecification();
		final StringBuffer receiverReplyEventName = new StringBuffer(replyName)
				.append(RECEIVER_MESSAGE_SUFFIX);
		receiverEventReplyMessage.setName(receiverReplyEventName.toString());
		receiverEventReplyMessage.getCovereds().add(source);
		receiverEventReplyMessage.setMessage(replyMessage);

		replyMessage.setSendEvent(senderEventReplyMessage);
		replyMessage.setReceiveEvent(receiverEventReplyMessage);
		if (execution != null)
			execution.setFinish(senderEventReplyMessage);

		if (operation != null) {
			// Create send event
			final SendOperationEvent sendReplyEvent = factory.createSendOperationEvent();
			sendReplyEvent.setName(senderEventName.append(EVENT_MESSAGE_SUFFIX).toString());
			senderEventReplyMessage.setEvent(sendReplyEvent);
			sendReplyEvent.setOperation(operation);
			// Create receive event
			final ReceiveOperationEvent receiveReplyEvent = factory.createReceiveOperationEvent();
			receiveReplyEvent.setName(receiverEventName.append(EVENT_MESSAGE_SUFFIX).toString());
			receiverEventReplyMessage.setEvent(receiveReplyEvent);
			receiveReplyEvent.setOperation(operation);
			message.getNearestPackage().getPackagedElements().add(sendReplyEvent);
			message.getNearestPackage().getPackagedElements().add(receiveReplyEvent);
		} else {
			// Create send reply event
			final SendSignalEvent sendReplyEvent = factory.createSendSignalEvent();
			sendReplyEvent.setName(senderReplyEventName.append(EVENT_MESSAGE_SUFFIX).toString());
			sendReplyEvent.setSignal(((SendSignalEvent)senderEventMessage.getEvent()).getSignal());
			senderEventReplyMessage.setEvent(sendReplyEvent);
			// Create receive reply event
			final ReceiveSignalEvent receiveReplyEvent = factory.createReceiveSignalEvent();
			receiveReplyEvent.setName(receiverReplyEventName.append(EVENT_MESSAGE_SUFFIX).toString());
			receiveReplyEvent.setSignal(((ReceiveSignalEvent)receiverEventMessage.getEvent()).getSignal());
			receiverEventReplyMessage.setEvent(receiveReplyEvent);
			message.getNearestPackage().getPackagedElements().add(sendReplyEvent);
			message.getNearestPackage().getPackagedElements().add(receiveReplyEvent);
		}

		// Add and order fragments under the interaction
		// Add message after starting end predecessor
		fragments.add(senderEventMessage);

		// If predecessor is the beginning of an execution add message after the execution
		if (startingEndPredecessor != null && startingEndPredecessor instanceof OccurrenceSpecification
				&& predecessorExecution != null
				&& startingEndPredecessor.equals(predecessorExecution.getStart()))
			fragments.move(fragments.indexOf(predecessorExecution) + 1, senderEventMessage);
		// Else set it directly after the predecessor
		else
			fragments.move(fragments.indexOf(startingEndPredecessor) + 1, senderEventMessage);
		fragments.add(receiverEventMessage);
		fragments.move(fragments.indexOf(senderEventMessage) + 1, receiverEventMessage);
		fragments.add(senderEventReplyMessage);
		if (execution != null) {
			fragments.add(execution);
			fragments.move(fragments.indexOf(receiverEventMessage) + 1, execution);
			fragments.move(fragments.indexOf(execution) + 1, senderEventReplyMessage);
		} else
			fragments.move(fragments.indexOf(receiverEventMessage) + 1, senderEventReplyMessage);

		fragments.add(receiverEventReplyMessage);
		fragments.move(fragments.indexOf(senderEventReplyMessage) + 1, receiverEventReplyMessage);
	}

	/**
	 * Create synchronous typed message.
	 * 
	 * @param interaction
	 *            Interaction
	 * @param sourceFragment
	 *            Source
	 * @param targetFragment
	 *            Target
	 * @param startingEndPredecessor
	 *            Starting end predecessor
	 * @param finishingEndPredecessor
	 *            Finishing end predecessor
	 */
	public void createSynchronousMessage(Interaction interaction, NamedElement sourceFragment,
			NamedElement targetFragment, NamedElement startingEndPredecessor,
			NamedElement finishingEndPredecessor) {
		createSynchronousMessage(interaction, sourceFragment, targetFragment, null, startingEndPredecessor,
				finishingEndPredecessor);
	}

	/**
	 * Delete lifeline.
	 * 
	 * @param lifeline
	 *            Lifeline to delete
	 */
	public void delete(Lifeline lifeline) {
		if (lifeline == null)
			return;
		// Delete dependency
		final EList<Dependency> dependencies = lifeline.getClientDependencies();
		if (dependencies != null && dependencies.size() > 0) {
			final Dependency dependency = dependencies.get(0);
			if (dependency != null)
				dependency.destroy();
		}

		// Delete lifeline
		lifeline.destroy();
	}

	/**
	 * Delete execution.
	 * 
	 * @param execution
	 *            Execution to delete
	 */
	public void delete(BehaviorExecutionSpecification execution) {
		if (execution == null)
			return;

		// Get fragments
		final Interaction interaction = (Interaction)execution.eContainer();

		// Delete opaque behavior
		final Behavior behavior = execution.getBehavior();
		if (behavior != null)
			interaction.getOwnedBehaviors().remove(behavior);

		// Delete start and finish behavior
		final List<InteractionFragment> fragments = interaction.getFragments();
		final OccurrenceSpecification start = execution.getStart();
		if (start instanceof ExecutionOccurrenceSpecification) {
			// Delete event
			final Event event = start.getEvent();
			if (event != null)
				event.destroy();
			fragments.remove(start);
		}
		final OccurrenceSpecification finish = execution.getFinish();
		if (finish instanceof ExecutionOccurrenceSpecification) {
			// Delete event
			final Event event = finish.getEvent();
			if (event != null)
				event.destroy();
			fragments.remove(finish);
		}
		// Delete execution
		fragments.remove(execution);
	}

	/**
	 * Delete message.
	 * 
	 * @param message
	 *            Message to delete
	 */
	public void delete(Message message) {
		if (message == null)
			return;

		// Get fragments
		final Interaction interaction = (Interaction)message.eContainer();
		final List<InteractionFragment> fragments = interaction.getFragments();

		// Delete start and finish message and if an execution is associated to the message remove also the
		// execution
		final MessageOccurrenceSpecification receiveMessage = (MessageOccurrenceSpecification)message
				.getReceiveEvent();
		if (receiveMessage != null) {
			// If message is a synchronous message delete also the reply message
			if (MessageSort.SYNCH_CALL_LITERAL.equals(message.getMessageSort())) {
				final Message reply = getReplyMessage(message);
				if (reply != null)
					delete(reply);
			}

			final BehaviorExecutionSpecification execution = getExecution(receiveMessage);
			if (execution != null) {
				delete(execution);
			}
			final Event receiveEvent = receiveMessage.getEvent();
			if (receiveEvent instanceof ReceiveSignalEvent) {
				// Delete signal
				final Signal signal = ((ReceiveSignalEvent)receiveEvent).getSignal();
				if (receiveEvent instanceof ReceiveSignalEvent && signal != null) {
					signal.destroy();
				}
				receiveEvent.destroy();
			}
			fragments.remove(receiveMessage);
		}
		final MessageOccurrenceSpecification sendMessage = (MessageOccurrenceSpecification)message
				.getSendEvent();
		if (sendMessage != null) {
			final BehaviorExecutionSpecification execution = getExecution(sendMessage);
			if (execution != null)
				delete(execution);
			final Event sendEvent = sendMessage.getEvent();
			// Delete signal
			if (sendEvent instanceof SendSignalEvent) {
				final Signal signal = ((SendSignalEvent)sendEvent).getSignal();
				if (sendEvent instanceof SendSignalEvent && signal != null) {
					signal.destroy();
				}
				sendEvent.destroy();
			}
			fragments.remove(message.getSendEvent());
		}
		// Delete message
		interaction.getMessages().remove(message);
	}

	/**
	 * Get the lifeline associated to a fragment.
	 * 
	 * @param fragment
	 *            Fragment
	 * @return Lifeline if exists otherwise null
	 */
	private Lifeline getLifeline(Element fragment) {
		if (fragment instanceof Lifeline) {
			return (Lifeline)fragment;
		} else if (fragment instanceof ExecutionSpecification) {
			final List<Lifeline> lifelines = ((ExecutionSpecification)fragment).getCovereds();
			if (lifelines != null && !lifelines.isEmpty()) {
				return lifelines.get(0);
			}
		}
		return null;
	}

	/**
	 * Get operations associated to a lifeline or an execution. To get all the available operations, we should
	 * get the one defined directly under the class and those defined in realized interface.
	 * 
	 * @param target
	 *            Lifeline or execution
	 * @return All operations available for the lifeline or execution
	 */
	public List<Operation> getOperations(EObject target) {
		List<Element> elements = null;
		if (target instanceof Lifeline) {
			elements = getType((Lifeline)target).getOwnedElements();
		} else if (target instanceof ExecutionSpecification) {
			elements = ((ExecutionSpecification)target).getOwnedElements();
		}

		if (elements == null)
			return null;

		final List<Operation> operations = new ArrayList<Operation>();
		// represents.type.ownedOperation + represents.type.interfaceRealization.contract.ownedOperation
		for (Element element : elements) {
			// represents.type.ownedOperation
			if (element instanceof Operation) {
				operations.add((Operation)element);
			}
			// represents.type.interfaceRealization.contract.ownedOperation
			else if (element instanceof InterfaceRealization
					&& ((InterfaceRealization)element).getContract() != null) {
				operations.addAll(((InterfaceRealization)element).getContract().getAllOperations());
			}
		}

		return operations;
	}

	/**
	 * Create interaction a new interaction in package.
	 * 
	 * @param pkg
	 *            Package containing new interaction.
	 */
	public void createInteraction(EObject pkg) {
		final UMLFactory factory = UMLFactory.eINSTANCE;
		final Interaction interaction = factory.createInteraction();
		interaction.setName(NamedElementServices.getNewInteractionName((Package)pkg));
		((Package)pkg).getPackagedElements().add(interaction);
	}

	/**
	 * Reorder execution.
	 * 
	 * @param execution
	 *            Moved execution
	 * @param startingEndPredecessorAfter
	 *            Fragment preceding moved execution start before the beginning of reorder operation
	 * @param finishingEndPredecessorAfter
	 *            Fragment preceding moved execution finish before the beginning of reorder operation
	 */
	public void reorder(ExecutionSpecification execution, InteractionFragment startingEndPredecessorAfter,
			InteractionFragment finishingEndPredecessorAfter) {
		// Get current interaction
		final Interaction interaction = execution.getEnclosingInteraction();
		// Re-order fragments under the interaction
		final EList<InteractionFragment> fragments = interaction.getFragments();

		final List<InteractionFragment> subFragments = new ArrayList<InteractionFragment>();
		subFragments.addAll(fragments.subList(fragments.indexOf(execution.getStart()) + 2,
				fragments.indexOf(execution.getFinish())));
		int startIndex = getPredecessorIndex(startingEndPredecessorAfter, fragments);
		// Move the execution start and the execution
		if (startingEndPredecessorAfter != null) {
			// If startingEndPredecessorAfter is the start of an execution, keep the behavior just after the
			// start
			startIndex = fragments.indexOf(startingEndPredecessorAfter);
			reorderExecutionOccurence(execution, execution.getStart(), startingEndPredecessorAfter,
					interaction, fragments);
		} else {
			reorderExecutionOccurence(execution, execution.getStart(), startingEndPredecessorAfter,
					interaction, fragments);
		}

		// Move all the elements attached to the moved execution
		for (InteractionFragment fragment : subFragments) {
			// If execution end is a message move the message start and receive
			if (fragment.equals(execution.getFinish())
					&& execution.getFinish() instanceof MessageOccurrenceSpecification)
				reorder(((MessageOccurrenceSpecification)execution.getFinish()).getMessage(), execution, null);
			else {
				int newIndex = startIndex + subFragments.indexOf(fragment) + 1;
				// Predecessor is the last fragment
				if (startIndex == fragments.size() - 1)
					newIndex = startIndex;
				fragments.move(newIndex, fragments.indexOf(fragment));
			}
		}

		// Move the execution finish
		if (finishingEndPredecessorAfter != null) {
			reorderExecutionOccurence(execution, execution.getFinish(), finishingEndPredecessorAfter,
					interaction, fragments);
		}
	}

	/**
	 * Get the index of a predecessor fragment.
	 * 
	 * @param fragment
	 *            Fragment to search
	 * @param fragments
	 *            List of fragments
	 * @return Index of fragment if exists otherwise 0
	 */
	private int getPredecessorIndex(InteractionFragment fragment, final EList<InteractionFragment> fragments) {
		if (fragment != null)
			return fragments.indexOf(fragment);
		return 0;
	}

	/**
	 * Move an execution occurrence.
	 * 
	 * @param execution
	 *            Execution to move
	 * @param occurrence
	 *            Occurrence start or finish
	 * @param startingEndPredecessorAfter
	 *            Predecessor after move
	 * @param interaction
	 *            Interaction
	 * @param fragments
	 *            Fragments
	 */
	private void reorderExecutionOccurence(ExecutionSpecification execution,
			OccurrenceSpecification occurrence, InteractionFragment startingEndPredecessorAfter,
			final Interaction interaction, final EList<InteractionFragment> fragments) {
		int startIndex = getPredecessorIndex(startingEndPredecessorAfter, fragments);
		if (getExecution(startingEndPredecessorAfter) != null
				&& startingEndPredecessorAfter == (getExecution(startingEndPredecessorAfter).getStart())) {
			startIndex += 1;
		}

		// If element moves up
		if (startingEndPredecessorAfter != null && fragments.indexOf(occurrence) > startIndex) {
			startIndex += 1;
		}

		// If execution start is a message move the message start and receive
		if (occurrence instanceof MessageOccurrenceSpecification) {
			reorder(((MessageOccurrenceSpecification)occurrence).getMessage(), startingEndPredecessorAfter,
					null);
			if (fragments.indexOf(occurrence) > startIndex) {
				startIndex += 1;
			}
		} else {
			// Move execution start
			fragments.move(startIndex, fragments.indexOf(occurrence));
		}
		if (getExecution(occurrence).getStart() == occurrence) {
			// Move execution
			int behaviorIndex = startIndex;
			if (fragments.indexOf(execution) > startIndex) {
				behaviorIndex += 1;
			}
			fragments.move(behaviorIndex, fragments.indexOf(execution));
		}
		if (occurrence instanceof MessageOccurrenceSpecification) {
			final Message message = ((MessageOccurrenceSpecification)occurrence).getMessage();
			// If message is a synchronous message, move the reply message
			if (MessageSort.SYNCH_CALL_LITERAL.equals(message.getMessageSort())) {
				final Message reply = getReplyMessage(message);
				reorder(reply, (InteractionFragment)message.getReceiveEvent(),
						(InteractionFragment)message.getReceiveEvent());
				refresh(interaction);
			}
		}
	}

	/**
	 * Reorder message.
	 * 
	 * @param message
	 *            Moved message
	 * @param startingEndPredecessorAfter
	 *            Fragment preceding moved execution start before the beginning of reorder operation
	 * @param finishingEndPredecessorAfter
	 *            Fragment preceding moved execution finish before the beginning of reorder operation
	 */
	public void reorder(Message message, InteractionFragment startingEndPredecessorAfter,
			InteractionFragment finishingEndPredecessorAfter) {
		// Get current interaction
		final Interaction interaction = message.getInteraction();
		// Re-order fragments under the interaction
		final EList<InteractionFragment> fragments = interaction.getFragments();

		// Move message start after startEndPredecessorAfter
		int sendMsgIndex = 0;
		if (startingEndPredecessorAfter != null) {
			// If startingEndPredecessorAfter is the start of an execution, keep the behavior just after the
			// start
			sendMsgIndex = fragments.indexOf(startingEndPredecessorAfter);
			if (getExecution(startingEndPredecessorAfter) != null
					&& startingEndPredecessorAfter == (getExecution(startingEndPredecessorAfter).getStart())) {
				sendMsgIndex += 1;
			}

			// If element moves up
			if (fragments.indexOf(message.getSendEvent()) > sendMsgIndex) {
				sendMsgIndex += 1;
			}

			// If execution start is a message move the message start and receive
			// Move execution start
			fragments.move(sendMsgIndex, fragments.indexOf(message.getSendEvent()));

			// Move execution
			int receiveMsgIndex = sendMsgIndex;
			if (fragments.indexOf(message.getReceiveEvent()) > sendMsgIndex) {
				receiveMsgIndex += 1;
			}
			fragments.move(receiveMsgIndex, fragments.indexOf(message.getReceiveEvent()));
		} else {
			// Element moved at the beginning of sequence, so no startingPredecessorAfter exists
			// Move execution start
			fragments.move(sendMsgIndex, fragments.indexOf(message.getSendEvent()));
			// Move execution
			int receiveMsgIndex = sendMsgIndex;
			if (fragments.indexOf(message.getReceiveEvent()) > sendMsgIndex) {
				receiveMsgIndex += 1;
			}
			fragments.move(receiveMsgIndex, fragments.indexOf(message.getReceiveEvent()));
		}
	}

	/**
	 * Refresh diagram associated to interaction.
	 * 
	 * @param interaction
	 *            Interaction
	 */
	private void refresh(Interaction interaction) {
		// Refresh current representation
		// Get session
		final Session session = SessionManager.INSTANCE.getSession(interaction);
		if (session != null) {
			// Get representation
			final DRepresentation diagram = (DRepresentation)DialectManager.INSTANCE.getRepresentations(
					interaction, session).toArray()[0];
			// Refresh current sequence diagram
			DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
		}
	}

	/**
	 * Create an operation from a lifeline. Create the operation in the class and the execution on the
	 * lifeline.
	 * 
	 * @param lifeline
	 *            Lifeline
	 * @param startingEndPredecessor
	 *            Predecessor
	 */
	public void createOperation(Lifeline lifeline, NamedElement startingEndPredecessor) {
		// Get associated class
		final org.eclipse.uml2.uml.Type type = getType(lifeline);
		final Operation operation = operationService.createOperation(type);

		// Create execution
		createExecution(lifeline.getInteraction(), lifeline, operation, (NamedElement)startingEndPredecessor);
	}

	/**
	 * Create an operation from an execution. Create the operation in the class and the execution on the
	 * execution.
	 * 
	 * @param execution
	 *            Execution
	 * @param startingEndPredecessor
	 *            Predecessor
	 */
	public void createOperation(ExecutionSpecification execution, NamedElement startingEndPredecessor) {
		// Get associated class
		final org.eclipse.uml2.uml.Type type = getType(execution.getCovereds().get(0));
		final Operation operation = operationService.createOperation(type);
		// Create execution
		createExecution(execution.getEnclosingInteraction(), execution, operation, startingEndPredecessor);
	}

	/**
	 * Create an operation and an asynchronous message from a lifeline or an execution. Create the operation
	 * in the class and the asynchronous message in the interaction.
	 * 
	 * @param target
	 *            Target message element, it could be a lifeline or an execution
	 * @param source
	 *            Source message element, it could be a lifeline or an execution
	 * @param startingEndPredecessor
	 *            Start predecessor
	 * @param finishingEndPredecessor
	 *            Finish predecessor
	 */
	public void createOperationAndAsynchMessage(NamedElement target, NamedElement source,
			NamedElement startingEndPredecessor, NamedElement finishingEndPredecessor) {
		// Get associated class and interaction
		org.eclipse.uml2.uml.Type type;
		Interaction interaction;
		if (target instanceof Lifeline) {
			type = getType((Lifeline)target);
			interaction = ((Lifeline)target).getInteraction();
		} else {
			type = getType(((ExecutionSpecification)target).getCovereds().get(0));
			interaction = ((ExecutionSpecification)target).getEnclosingInteraction();
		}
		final Operation operation = operationService.createOperation(type);
		// Create message
		createAsynchronousMessage(interaction, source, target, operation, startingEndPredecessor,
				finishingEndPredecessor);
	}

	/**
	 * Create an operation from a lifeline or an execution. Create the operation in the class and the
	 * execution on the source element.
	 * 
	 * @param target
	 *            Target message element, it could be a lifeline or an execution
	 * @param source
	 *            Source messgae element, it could be a lifeline or an execution
	 * @param startingEndPredecessor
	 *            Start predecessor
	 * @param finishingEndPredecessor
	 *            Finish predecessor
	 */
	public void createOperationAndSynchMessage(NamedElement target, NamedElement source,
			NamedElement startingEndPredecessor, NamedElement finishingEndPredecessor) {
		// Get associated class and interaction
		org.eclipse.uml2.uml.Type type;
		Interaction interaction;
		if (target instanceof Lifeline) {
			type = getType((Lifeline)target);
			interaction = ((Lifeline)target).getInteraction();
		} else {
			type = getType(((ExecutionSpecification)target).getCovereds().get(0));
			interaction = ((ExecutionSpecification)target).getEnclosingInteraction();
		}
		final Operation operation = operationService.createOperation(type);
		// Create message
		createSynchronousMessage(interaction, source, target, operation, startingEndPredecessor,
				finishingEndPredecessor);
	}

	/**
	 * Get type associated to a lifeline.
	 * 
	 * @param target
	 *            Lifeline
	 * @return Type
	 */
	private Type getType(Lifeline target) {
		if (target.getClientDependencies() != null && !target.getClientDependencies().isEmpty()) {
			return ((InstanceSpecification)target.getClientDependencies().get(0).getSuppliers().get(0))
					.getClassifiers().get(0);
		}
		return target.getRepresents().getType();
	}

	/**
	 * Check if a message is associated to a start execution.
	 * 
	 * @param message
	 *            Message
	 * @return True if message is used as start for an execution
	 */
	public boolean hasStartMessage(Message message) {
		final MessageOccurrenceSpecification msgReceive = (MessageOccurrenceSpecification)message
				.getReceiveEvent();
		final ExecutionSpecification execution = (ExecutionSpecification)findOccurrenceSpecificationContext(msgReceive);
		if (execution.getStart() instanceof MessageOccurrenceSpecification
				|| msgReceive.equals(execution.getStart()))
			return true;
		return false;
	}

	/**
	 * Link an existing end message as execution target start.
	 * 
	 * @param message
	 *            Message
	 * @param containerView
	 *            Diagram
	 */
	public void linkToExecutionAsStart(Message message, DDiagramElement containerView) {
		// Get associated execution
		final MessageOccurrenceSpecification msgReceive = (MessageOccurrenceSpecification)message
				.getReceiveEvent();
		final ExecutionSpecification execution = (ExecutionSpecification)findOccurrenceSpecificationContext(msgReceive);
		// Get current execution start
		final ExecutionOccurrenceSpecification executionStart = (ExecutionOccurrenceSpecification)execution
				.getStart();
		// Set end message as execution start
		execution.setStart(msgReceive);

		// Move execution and all sub level elements after message receiver
		final EList<InteractionFragment> fragments = message.getInteraction().getFragments();
		fragments.move(fragments.indexOf(msgReceive), execution);

		// Remove associated event
		executionStart.getEvent().destroy();
		// Remove execution start
		fragments.remove(executionStart);
		executionStart.destroy();

		// If message is a synchronous message, move and rename reply
		if (MessageSort.SYNCH_CALL_LITERAL.equals(message.getMessageSort())) {
			// Get message reply
			final Message msgReply = getReplyMessage(message);
			// Get current execution finish
			final OccurrenceSpecification executionFinish = (OccurrenceSpecification)execution.getFinish();
			final MessageOccurrenceSpecification msgReplySend = (MessageOccurrenceSpecification)msgReply
					.getSendEvent();

			// Move message reply after execution
			reorder(msgReply, execution.getFinish(), execution.getFinish());

			// Set end message as execution finish
			execution.setFinish(msgReplySend);
			// Remove associated event
			executionFinish.getEvent().destroy();
			// Remove execution finish
			fragments.remove(executionFinish);
			executionFinish.destroy();

			// Rename message, message sender and message receiver
			msgReply.setName(execution.getName());
			msgReply.getReceiveEvent().setName(execution.getName() + RECEIVER_MESSAGE_SUFFIX);
			msgReplySend.setName(execution.getName() + SENDER_MESSAGE_SUFFIX);
		}

		// Rename message, message sender and message receiver
		message.setName(execution.getName());
		message.getSendEvent().setName(execution.getName() + SENDER_MESSAGE_SUFFIX);
		msgReceive.setName(execution.getName() + RECEIVER_MESSAGE_SUFFIX);

		// Refresh layout
		refreshLayout(containerView);
	}

	/**
	 * Get the reply message associated to a message.
	 * 
	 * @param message
	 *            Message
	 * @return Reply message if exists otherwise null
	 */
	private Message getReplyMessage(Message message) {
		for (Message messageReply : message.getInteraction().getMessages()) {
			if (MessageSort.REPLY_LITERAL.equals(messageReply.getMessageSort())
					&& messageReply.getName().startsWith(message.getName())) {
				return messageReply;
			}
		}
		return null;
	}

	/**
	 * Refresh layout.
	 * 
	 * @param containerView
	 *            Diagram
	 */
	private void refreshLayout(DDiagramElement containerView) {
		final IEditorPart ed = EclipseUIUtil.getActiveEditor();
		if (ed instanceof DDiagramEditor && ed instanceof IDiagramWorkbenchPart) {
			final Map editPartRegistry = ((IDiagramWorkbenchPart)ed).getDiagramGraphicalViewer()
					.getEditPartRegistry();
			final EditPart targetEditPart = (EditPart)editPartRegistry.get(containerView);

			if (targetEditPart instanceof ISequenceEventEditPart) {
				final ISequenceEvent ise = ((ISequenceEventEditPart)targetEditPart).getISequenceEvent();

				if (ise != null) {
					final SequenceDiagram diagram = ise.getDiagram();
					final SequenceDiagramEditPart sdep = (SequenceDiagramEditPart)editPartRegistry
							.get(diagram.getNotationDiagram());

					new RefreshGraphicalOrderingOperation(diagram).execute();
					new RefreshSemanticOrderingOperation(diagram.getSequenceDDiagram()).execute();
					new SynchronizeGraphicalOrderingOperation(sdep.getDiagramView(),false).execute();
				}
			}
		}
	}
}
