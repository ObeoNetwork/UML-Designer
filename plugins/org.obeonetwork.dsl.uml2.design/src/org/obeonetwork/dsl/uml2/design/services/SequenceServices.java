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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
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
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.services.internal.NamedElementServices;

import fr.obeo.dsl.common.ui.tools.api.util.EclipseUIUtil;
import fr.obeo.dsl.viewpoint.DDiagram;
import fr.obeo.dsl.viewpoint.DDiagramElement;
import fr.obeo.dsl.viewpoint.DEdge;
import fr.obeo.dsl.viewpoint.DNode;
import fr.obeo.dsl.viewpoint.diagram.sequence.business.internal.elements.ISequenceEvent;
import fr.obeo.dsl.viewpoint.diagram.sequence.business.internal.elements.SequenceDiagram;
import fr.obeo.dsl.viewpoint.diagram.sequence.business.internal.operation.RefreshGraphicalOrderingOperation;
import fr.obeo.dsl.viewpoint.diagram.sequence.business.internal.operation.RefreshSemanticOrderingOperation;
import fr.obeo.dsl.viewpoint.diagram.sequence.ui.tool.internal.edit.operation.SynchronizeGraphicalOrderingOperation;
import fr.obeo.dsl.viewpoint.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import fr.obeo.dsl.viewpoint.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import fr.obeo.dsl.viewpoint.diagram.tools.api.editor.DDiagramEditor;

/**
 * Utility services to manage sequence diagrams.
 * 
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 * @author Mélanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class SequenceServices {
	private static final String SENDER_MESSAGE_SUFFIX = "_sender";

	private static final String RECEIVER_MESSAGE_SUFFIX = "_receiver";

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
		Stack<NamedElement> context = new Stack<NamedElement>();
		context.add(lifeline);

		List<InteractionFragment> allFragments = occurrenceSpecification.getEnclosingInteraction()
				.getFragments();
		List<InteractionFragment> fragments = new ArrayList<InteractionFragment>();
		for (InteractionFragment fragment : allFragments) {
			if (fragment.getCovered(lifeline.getName()) != null)
				fragments.add(fragment);
		}

		for (int i = 0; i < fragments.size(); i++) {
			InteractionFragment e = fragments.get(i);
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

	private boolean isEnd(InteractionFragment endCandidate, List<InteractionFragment> fragments) {
		List<InteractionFragment> executionFinishes = new ArrayList<InteractionFragment>();
		for (InteractionFragment fragment : fragments) {
			if (fragment instanceof BehaviorExecutionSpecification) {
				// Get start
				BehaviorExecutionSpecification behavior = (BehaviorExecutionSpecification)fragment;
				// Get finish
				executionFinishes.add(behavior.getFinish());
			}
		}
		return executionFinishes.contains(endCandidate);
	}

	private BehaviorExecutionSpecification getExecution(InteractionFragment occurence) {
		if (occurence == null)
			return null;
		Map<InteractionFragment, BehaviorExecutionSpecification> behaviors = new HashMap<InteractionFragment, BehaviorExecutionSpecification>();
		for (InteractionFragment fragment : occurence.getEnclosingInteraction().getFragments()) {
			if (fragment instanceof BehaviorExecutionSpecification) {
				BehaviorExecutionSpecification behavior = (BehaviorExecutionSpecification)fragment;
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
		List<ExecutionSpecification> executions = executionSemanticCandidates(execution);
		List<OccurrenceSpecification> occurrences = new ArrayList<OccurrenceSpecification>();
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
	 * @param execution
	 *            the context.
	 * @return the {@link ExecutionSpecification} semantic candidates.
	 */
	public List<OccurrenceSpecification> executionSemanticCandidateOccurences(Lifeline lifeline) {
		List<ExecutionSpecification> executions = executionSemanticCandidates(lifeline);
		List<OccurrenceSpecification> occurrences = new ArrayList<OccurrenceSpecification>();
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
		List<ExecutionSpecification> executions = new ArrayList<ExecutionSpecification>();
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
		Lifeline lifeline = getLifeline(fragment);

		UMLFactory factory = UMLFactory.eINSTANCE;
		StringBuffer executionName;
		if (operation == null) {
			List<BehaviorExecutionSpecification> behaviors = new ArrayList<BehaviorExecutionSpecification>();
			for (InteractionFragment behavior : interaction.getFragments()) {
				if (behavior instanceof BehaviorExecutionSpecification)
					behaviors.add((BehaviorExecutionSpecification)behavior);
			}
			executionName = new StringBuffer("BehaviorExecution_").append(behaviors.size());
		} else {
			executionName = new StringBuffer(operation.getName());
		}

		// Create execution start
		ExecutionOccurrenceSpecification startExec = factory.createExecutionOccurrenceSpecification();
		StringBuffer startExecName = new StringBuffer(executionName).append("_start");
		startExec.setName(startExecName.toString());
		startExec.getCovereds().add(lifeline);

		// Create behavior
		OpaqueBehavior behavior = factory.createOpaqueBehavior();
		behavior.setName(executionName.toString());
		behavior.setSpecification(operation);
		interaction.getOwnedBehaviors().add(behavior);
		BehaviorExecutionSpecification execution = factory.createBehaviorExecutionSpecification();
		execution.setName(executionName.toString());
		execution.getCovereds().add(lifeline);
		execution.setBehavior(behavior);

		execution.setStart(startExec);
		startExec.setExecution(execution);

		// Create execution end
		ExecutionOccurrenceSpecification endExec = factory.createExecutionOccurrenceSpecification();
		StringBuffer endExecName = new StringBuffer(executionName).append("_finish");
		endExec.setName(endExecName.toString());
		endExec.getCovereds().add(lifeline);
		endExec.setExecution(execution);
		execution.setFinish(endExec);

		// Add and order fragments under the interaction
		EList<InteractionFragment> fragments = interaction.getFragments();

		// Ordered fragments
		fragments.add(startExec);
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
		Lifeline source = getLifeline(sourceFragment);
		Lifeline target = getLifeline(targetFragment);
		BehaviorExecutionSpecification predecessorExecution = getExecution((InteractionFragment)startingEndPredecessor);

		UMLFactory factory = UMLFactory.eINSTANCE;
		EList<InteractionFragment> fragments = interaction.getFragments();

		// Create message
		Message message = factory.createMessage();
		StringBuffer operationName;
		if (operation == null)
			operationName = new StringBuffer("Message_").append(interaction.getMessages().size());
		else
			operationName = new StringBuffer(operation.getName());

		message.setName(operationName.toString());
		message.setMessageSort(MessageSort.ASYNCH_CALL_LITERAL);
		interaction.getMessages().add(message);

		// Create message send event
		MessageOccurrenceSpecification senderEventMessage = factory.createMessageOccurrenceSpecification();
		StringBuffer senderEventName = new StringBuffer(operationName).append(SENDER_MESSAGE_SUFFIX);
		senderEventMessage.setName(senderEventName.toString());
		senderEventMessage.getCovereds().add(source);
		senderEventMessage.setMessage(message);

		// Create message receive event
		MessageOccurrenceSpecification receiverEventMessage = factory.createMessageOccurrenceSpecification();
		StringBuffer receiverEventName = new StringBuffer(operationName).append(RECEIVER_MESSAGE_SUFFIX);
		receiverEventMessage.setName(receiverEventName.toString());
		receiverEventMessage.getCovereds().add(target);
		receiverEventMessage.setMessage(message);

		message.setSendEvent(senderEventMessage);
		message.setReceiveEvent(receiverEventMessage);

		// Create behavior
		BehaviorExecutionSpecification execution = null;
		if (operation != null) {
			OpaqueBehavior behavior = factory.createOpaqueBehavior();
			behavior.setName(operationName.toString());
			if (operation != null || targetFragment instanceof ExecutionSpecification)
				behavior.setSpecification(operation);
			interaction.getOwnedBehaviors().add(behavior);

			execution = factory.createBehaviorExecutionSpecification();
			execution.setName(operationName.toString());
			execution.getCovereds().add(target);
			execution.setBehavior(behavior);
		}

		ExecutionOccurrenceSpecification endExec = null;
		if (execution != null) {
			execution.setStart(receiverEventMessage);
			// Create end execution
			endExec = factory.createExecutionOccurrenceSpecification();
			StringBuffer executionEndName = new StringBuffer(execution.getName()).append("_finish");
			endExec.setName(executionEndName.toString());
			endExec.getCovereds().add(target);
			endExec.setExecution(execution);
			execution.setFinish(endExec);
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
		Lifeline source = getLifeline(sourceFragment);
		Lifeline target = getLifeline(targetFragment);
		BehaviorExecutionSpecification predecessorExecution = getExecution((InteractionFragment)startingEndPredecessor);

		UMLFactory factory = UMLFactory.eINSTANCE;
		EList<InteractionFragment> fragments = interaction.getFragments();

		// Create message
		Message message = factory.createMessage();
		StringBuffer operationName;
		if (operation == null)
			operationName = new StringBuffer("Message_").append(interaction.getMessages().size());
		else
			operationName = new StringBuffer(operation.getName());

		message.setName(operationName.toString());
		message.setMessageSort(MessageSort.SYNCH_CALL_LITERAL);
		interaction.getMessages().add(message);

		// Create message send event
		MessageOccurrenceSpecification senderEventMessage = factory.createMessageOccurrenceSpecification();
		StringBuffer senderEventName = new StringBuffer(operationName).append(SENDER_MESSAGE_SUFFIX);
		senderEventMessage.setName(senderEventName.toString());
		senderEventMessage.getCovereds().add(source);
		senderEventMessage.setMessage(message);

		// Create message receive event
		MessageOccurrenceSpecification receiverEventMessage = factory.createMessageOccurrenceSpecification();
		StringBuffer receiverEventName = new StringBuffer(operationName).append(RECEIVER_MESSAGE_SUFFIX);
		receiverEventMessage.setName(receiverEventName.toString());
		receiverEventMessage.getCovereds().add(target);
		receiverEventMessage.setMessage(message);

		message.setSendEvent(senderEventMessage);
		message.setReceiveEvent(receiverEventMessage);

		// Create behavior
		BehaviorExecutionSpecification execution = null;
		if (operation != null) {
			OpaqueBehavior behavior = factory.createOpaqueBehavior();
			behavior.setName(operationName.toString());
			if (operation != null || targetFragment instanceof ExecutionSpecification)
				behavior.setSpecification(operation);
			interaction.getOwnedBehaviors().add(behavior);

			execution = factory.createBehaviorExecutionSpecification();
			execution.setName(operationName.toString());
			execution.getCovereds().add(target);
			execution.setBehavior(behavior);
		}
		if (execution != null)
			execution.setStart(receiverEventMessage);

		// Create reply message
		Message replyMessage = factory.createMessage();
		StringBuffer replyName = new StringBuffer(operationName).append("_reply");
		replyMessage.setName(replyName.toString());
		replyMessage.setMessageSort(MessageSort.REPLY_LITERAL);
		interaction.getMessages().add(replyMessage);

		// Create reply message send event
		MessageOccurrenceSpecification senderEventReplyMessage = factory
				.createMessageOccurrenceSpecification();
		StringBuffer senderReplyEventName = new StringBuffer(replyName).append(SENDER_MESSAGE_SUFFIX);
		senderEventReplyMessage.setName(senderReplyEventName.toString());
		senderEventReplyMessage.getCovereds().add(target);
		senderEventReplyMessage.setMessage(replyMessage);

		// Create reply message receive event
		MessageOccurrenceSpecification receiverEventReplyMessage = factory
				.createMessageOccurrenceSpecification();
		StringBuffer receiverReplyEventName = new StringBuffer(replyName).append(RECEIVER_MESSAGE_SUFFIX);
		receiverEventReplyMessage.setName(receiverReplyEventName.toString());
		receiverEventReplyMessage.getCovereds().add(source);
		receiverEventReplyMessage.setMessage(replyMessage);

		replyMessage.setSendEvent(senderEventReplyMessage);
		replyMessage.setReceiveEvent(receiverEventReplyMessage);
		if (execution != null)
			execution.setFinish(senderEventReplyMessage);

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
	 * @param operation
	 *            Operation associated to message
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
	 * Delete execution.
	 * 
	 * @param execution
	 *            Execution to delete
	 */
	public void delete(BehaviorExecutionSpecification execution) {
		// Get fragments
		Interaction interaction = (Interaction)execution.eContainer();

		// Delete opaque behavior
		interaction.getOwnedBehaviors().remove(execution.getBehavior());
		// Delete start and finish behavior
		List<InteractionFragment> fragments = interaction.getFragments();
		if (execution.getStart() instanceof ExecutionOccurrenceSpecification)
			fragments.remove(execution.getStart());
		if (execution.getFinish() instanceof ExecutionOccurrenceSpecification)
			fragments.remove(execution.getFinish());
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
		// Get fragments
		Interaction interaction = (Interaction)message.eContainer();
		List<InteractionFragment> fragments = interaction.getFragments();

		// Delete start and finish message and if an execution is associated to the message remove also the
		// execution
		InteractionFragment receiveEvent = (InteractionFragment)message.getReceiveEvent();
		// If message is a synchronous message delete also the reply message
		if (MessageSort.SYNCH_CALL_LITERAL.equals(message.getMessageSort())) {
			if (getExecution(receiveEvent) != null
					&& getExecution(receiveEvent).getFinish() instanceof MessageOccurrenceSpecification)
				delete(((MessageOccurrenceSpecification)getExecution(receiveEvent).getFinish()).getMessage());
		}

		if (getExecution(receiveEvent) != null)
			delete(getExecution(receiveEvent));
		fragments.remove(receiveEvent);

		InteractionFragment sendEvent = (InteractionFragment)message.getSendEvent();
		if (getExecution(sendEvent) != null)
			delete(getExecution(sendEvent));
		fragments.remove(message.getSendEvent());

		// Delete message
		interaction.getMessages().remove(message);
	}

	private Lifeline getLifeline(Element fragment) {
		if (fragment instanceof Lifeline) {
			return (Lifeline)fragment;
		} else if (fragment instanceof ExecutionSpecification) {
			List<Lifeline> lifelines = ((ExecutionSpecification)fragment).getCovereds();
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
			elements = ((Lifeline)target).getRepresents().getType().getOwnedElements();
		} else if (target instanceof ExecutionSpecification) {
			elements = ((ExecutionSpecification)target).getOwnedElements();
		}

		if (elements == null)
			return null;

		List<Operation> operations = new ArrayList<Operation>();
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
		UMLFactory factory = UMLFactory.eINSTANCE;
		Interaction interaction = factory.createInteraction();
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
		Interaction interaction = execution.getEnclosingInteraction();
		// Re-order fragments under the interaction
		EList<InteractionFragment> fragments = interaction.getFragments();

		// Move execution start after startEndPredecessorAfter
		int startIndex = 0;
		if (startingEndPredecessorAfter != null) {
			// If predecessor is the start of an execution, keep execution just after execution start
			if (getExecution(startingEndPredecessorAfter) != null
					&& startingEndPredecessorAfter == (getExecution(startingEndPredecessorAfter).getStart()))
				startIndex = fragments.indexOf(getExecution(startingEndPredecessorAfter)) + 1;
			else {
				// Predecessor is the last fragment, the move operation will update the list
				if (fragments.indexOf(startingEndPredecessorAfter) == fragments.size() - 1)
					startIndex = fragments.indexOf(startingEndPredecessorAfter);
				else
					startIndex = fragments.indexOf(startingEndPredecessorAfter) + 1;
			}
		}
		List<InteractionFragment> subFragments = new ArrayList<InteractionFragment>();
		subFragments.addAll(fragments.subList(fragments.indexOf(execution.getStart()) + 1,
				fragments.indexOf(execution.getFinish()) + 1));

		// If execution start is a message move the message start and receive
		if (execution.getStart() instanceof MessageOccurrenceSpecification)
			reorder(((MessageOccurrenceSpecification)execution.getStart()).getMessage(),
					startingEndPredecessorAfter, null);
		else
			fragments.move(startIndex, fragments.indexOf(execution.getStart()));

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
		Interaction interaction = message.getInteraction();
		// Re-order fragments under the interaction
		EList<InteractionFragment> fragments = interaction.getFragments();

		// Move message start after startEndPredecessorAfter
		int sendMsgIndex = 0;
		if (startingEndPredecessorAfter != null) {
			// If predecessor is the start of an execution, keep execution just after execution start
			if (getExecution(startingEndPredecessorAfter) != null
					&& startingEndPredecessorAfter == (getExecution(startingEndPredecessorAfter).getStart()))
				sendMsgIndex = fragments.indexOf(getExecution(startingEndPredecessorAfter)) + 1;
			else {
				// Predecessor is the last fragment, the move operation will update the list
				if (fragments.indexOf(startingEndPredecessorAfter) == fragments.size() - 1)
					sendMsgIndex = fragments.indexOf(startingEndPredecessorAfter);
				else
					sendMsgIndex = fragments.indexOf(startingEndPredecessorAfter) + 1;
			}
		}
		fragments.move(sendMsgIndex, fragments.indexOf(message.getSendEvent()));
		int receiveMsgIndex = fragments.indexOf(message.getSendEvent()) + 1;
		// Predecessor is the last fragment
		if (sendMsgIndex == fragments.size() - 1)
			receiveMsgIndex = sendMsgIndex;
		fragments.move(receiveMsgIndex, fragments.indexOf(message.getReceiveEvent()));
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
		org.eclipse.uml2.uml.Class type = (org.eclipse.uml2.uml.Class)lifeline.getRepresents().getType();
		Operation operation = OperationServices.createOperation(type);
		// Create execution
		createExecution(lifeline.getInteraction(), lifeline, operation, startingEndPredecessor);
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
		org.eclipse.uml2.uml.Class type = (org.eclipse.uml2.uml.Class)execution.getCovereds().get(0)
				.getRepresents().getType();
		Operation operation = OperationServices.createOperation(type);
		// Create execution
		createExecution(execution.getEnclosingInteraction(), execution, operation, startingEndPredecessor);
	}

	/**
	 * Create an operation from a lifeline. Create the operation in the class and the asynchronous message in
	 * the interaction.
	 * 
	 * @param lifeline
	 *            Lifeline
	 * @param startingEndPredecessor
	 *            Predecessor
	 */
	public void createOperationAndAsynchMessage(NamedElement target, NamedElement source,
			NamedElement startingEndPredecessor, NamedElement finishingEndPredecessor) {
		// Get associated class and interaction
		org.eclipse.uml2.uml.Class type;
		Interaction interaction;
		if (target instanceof Lifeline) {
			type = (org.eclipse.uml2.uml.Class)((Lifeline)target).getRepresents().getType();
			interaction = ((Lifeline)target).getInteraction();
		} else {
			type = (org.eclipse.uml2.uml.Class)(((ExecutionSpecification)target).getCovereds().get(0))
					.getRepresents().getType();
			interaction = ((ExecutionSpecification)target).getEnclosingInteraction();
		}
		Operation operation = OperationServices.createOperation(type);
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
		org.eclipse.uml2.uml.Class type;
		Interaction interaction;
		if (target instanceof Lifeline) {
			type = (org.eclipse.uml2.uml.Class)((Lifeline)target).getRepresents().getType();
			interaction = ((Lifeline)target).getInteraction();
		} else {
			type = (org.eclipse.uml2.uml.Class)(((ExecutionSpecification)target).getCovereds().get(0))
					.getRepresents().getType();
			interaction = ((ExecutionSpecification)target).getEnclosingInteraction();
		}
		Operation operation = OperationServices.createOperation(type);
		// Create message
		createSynchronousMessage(interaction, source, target, operation, startingEndPredecessor,
				finishingEndPredecessor);
	}

	public boolean hasStartMessage(Message message) {
		MessageOccurrenceSpecification msgReceive = (MessageOccurrenceSpecification)message.getReceiveEvent();
		ExecutionSpecification execution = (ExecutionSpecification)findOccurrenceSpecificationContext(msgReceive);
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
	 */
	public void linkToExecutionAsStart(Message message, DDiagramElement containerView) {
		// Get associated execution
		MessageOccurrenceSpecification msgReceive = (MessageOccurrenceSpecification)message.getReceiveEvent();
		ExecutionSpecification execution = (ExecutionSpecification)findOccurrenceSpecificationContext(msgReceive);
		// Get current execution start
		ExecutionOccurrenceSpecification executionStart = (ExecutionOccurrenceSpecification)execution
				.getStart();
		// Set end message as execution start
		execution.setStart(msgReceive);
		// Remove execution start
		EList<InteractionFragment> fragments = message.getInteraction().getFragments();

		// Move execution and all sub level elements after message receiver
		fragments.move(fragments.indexOf(msgReceive), execution);

		fragments.remove(executionStart);
		executionStart.destroy();

		// If message is a synchronous message, move and rename reply
		if (MessageSort.SYNCH_CALL_LITERAL.equals(message.getMessageSort())) {
			// Get message reply
			Message msgReply = getReplyMessage(message);
			// Get current execution finish
			OccurrenceSpecification executionFinish = (OccurrenceSpecification)execution.getFinish();
			MessageOccurrenceSpecification msgReplySend = (MessageOccurrenceSpecification)msgReply
					.getSendEvent();

			// Move message reply after execution
			reorder(msgReply, execution.getFinish(), execution.getFinish());

			// Set end message as execution finish
			execution.setFinish(msgReplySend);
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

	private Message getReplyMessage(Message message) {
		for (Message messageReply : message.getInteraction().getMessages()) {
			if (MessageSort.REPLY_LITERAL.equals(messageReply.getMessageSort())
					&& messageReply.getName().startsWith(message.getName())) {
				return messageReply;
			}
		}
		return null;
	}

	private void refreshLayout(DDiagramElement containerView) {
		IEditorPart ed = EclipseUIUtil.getActiveEditor();
		if (ed instanceof DDiagramEditor && ed instanceof IDiagramWorkbenchPart) {
			Map editPartRegistry = ((IDiagramWorkbenchPart)ed).getDiagramGraphicalViewer()
					.getEditPartRegistry();
			EditPart targetEditPart = (EditPart)editPartRegistry.get(containerView);

			if (targetEditPart instanceof ISequenceEventEditPart) {
				ISequenceEvent ise = ((ISequenceEventEditPart)targetEditPart).getISequenceEvent();

				if (ise != null) {
					SequenceDiagram diagram = ise.getDiagram();
					SequenceDiagramEditPart sdep = (SequenceDiagramEditPart)editPartRegistry.get(diagram
							.getNotationDiagram());

					new RefreshGraphicalOrderingOperation(diagram).execute();
					new RefreshSemanticOrderingOperation(diagram.getSequenceDDiagram()).execute();
					new SynchronizeGraphicalOrderingOperation(sdep, false).execute();
				}
			}
		}
	}
}
