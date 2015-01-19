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
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OccurrenceSpecification;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Utility internal services to manage sequence diagrams.
 *
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class SequenceServices {
	/**
	 * Execution end suffix.
	 */
	public final String EXECUTION_END_SUFFIX = "_finish"; //$NON-NLS-1$


	/**
	 * Signal name suffix.
	 */
	public final String SIGNAL_SUFFIX = "_signal"; //$NON-NLS-1$


	/**
	 * Sender message name suffix.
	 */
	public final String SENDER_MESSAGE_SUFFIX = "_sender"; //$NON-NLS-1$

	/**
	 * Receiver message name suffix.
	 */
	public final String RECEIVER_MESSAGE_SUFFIX = "_receiver"; //$NON-NLS-1$



	private boolean combinedFragmentStartPredecessorChanged(CombinedFragment combinedFragment,
			InteractionFragment startingEndPredecessorAfter) {
		final Interaction interaction = combinedFragment.getEnclosingInteraction();
		final EList<InteractionFragment> fragments = interaction.getFragments();
		final InteractionFragment initialPredecessor = fragments.get(fragments.indexOf(combinedFragment) - 1);

		return !initialPredecessor.equals(startingEndPredecessorAfter);
	}

	/**
	 * Create asynchronous message.
	 *
	 * @param interaction
	 *            Interaction
	 * @param createOperation
	 *            Operation must be created true otherwise false
	 * @param sourceFragment
	 *            Source
	 * @param targetFragment
	 *            Target
	 * @param operation
	 *            Operation
	 * @return Message
	 */
	public Message createAsynchronousMessage(Interaction interaction, boolean createOperation,
			NamedElement sourceFragment, NamedElement targetFragment, Operation operation) {
		final UMLFactory factory = UMLFactory.eINSTANCE;
		final Message message = factory.createMessage();

		final Lifeline source = getLifeline(sourceFragment);
		final Lifeline target = getLifeline(targetFragment);

		String messageName = ""; //$NON-NLS-1$
		if (operation == null) {
			messageName = "Message_" + Integer.toString(interaction.getMessages().size()); //$NON-NLS-1$
		} else {
			messageName = operation.getName();
		}

		message.setName(messageName);
		message.setMessageSort(MessageSort.ASYNCH_CALL_LITERAL);

		// Create message send event
		final MessageOccurrenceSpecification senderEventMessage = factory
				.createMessageOccurrenceSpecification();
		senderEventMessage.setName(message.getName() + SENDER_MESSAGE_SUFFIX);
		senderEventMessage.getCovereds().add(source);
		senderEventMessage.setMessage(message);

		// Create message receive event
		final MessageOccurrenceSpecification receiverEventMessage = factory
				.createMessageOccurrenceSpecification();
		receiverEventMessage.setName(message.getName() + RECEIVER_MESSAGE_SUFFIX);
		receiverEventMessage.getCovereds().add(target);
		receiverEventMessage.setMessage(message);

		message.setSendEvent(senderEventMessage);
		message.setReceiveEvent(receiverEventMessage);

		return message;
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
	 * @param createExecution
	 *            Set to true to create an execution
	 * @param startingEndPredecessor
	 *            Starting end predecessor
	 * @param finishingEndPredecessor
	 *            Finishing end predecessor
	 * @param operation
	 *            Operation associated to message
	 */
	public void createAsynchronousMessage(Interaction interaction, NamedElement sourceFragment,
			NamedElement targetFragment, boolean createExecution, Element startingEndPredecessor,
			Element finishingEndPredecessor, Operation operation) {
		final Lifeline target = getLifeline(targetFragment);

		final BehaviorExecutionSpecification predecessorExecution = getExecution((InteractionFragment)startingEndPredecessor);

		final UMLFactory factory = UMLFactory.eINSTANCE;
		final EList<InteractionFragment> fragments = interaction.getFragments();

		// Create message
		final Message message = createAsynchronousMessage(interaction, createExecution, sourceFragment,
				targetFragment, operation);

		if (null != operation) {
			message.setSignature(operation);
		}

		interaction.getMessages().add(message);

		// Create execution or signal
		BehaviorExecutionSpecification execution = null;
		if (createExecution) {
			execution = createExecution(/* operation, */target, message);
			interaction.getOwnedBehaviors().add(execution.getBehavior());
		} else {
			final Signal signal = factory.createSignal();
			signal.setName(message.getName() + SIGNAL_SUFFIX);
			message.getNearestPackage().getPackagedElements().add(signal);
		}

		final MessageOccurrenceSpecification senderEventMessage = (MessageOccurrenceSpecification)message
				.getSendEvent();
		final MessageOccurrenceSpecification receiverEventMessage = (MessageOccurrenceSpecification)message
				.getReceiveEvent();

		ExecutionOccurrenceSpecification endExec = null;
		if (execution != null) {
			execution.setStart(receiverEventMessage);
			endExec = factory.createExecutionOccurrenceSpecification();
			endExec.setName(execution.getName() + EXECUTION_END_SUFFIX);
			endExec.getCovereds().add(target);
			endExec.setExecution(execution);
			execution.setFinish(endExec);
		}

		// If predecessor is the beginning of an execution add message after the execution
		if (startingEndPredecessor != null && startingEndPredecessor instanceof OccurrenceSpecification
				&& predecessorExecution != null
				&& startingEndPredecessor.equals(predecessorExecution.getStart())) {
			fragments.add(fragments.indexOf(predecessorExecution) + 1, senderEventMessage);
		}
		// Else set it directly after the predecessor
		else {
			fragments.add(fragments.indexOf(startingEndPredecessor) + 1, senderEventMessage);
		}

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
			for (final InteractionFragment behavior : interaction.getFragments()) {
				if (behavior instanceof BehaviorExecutionSpecification) {
					behaviors.add((BehaviorExecutionSpecification)behavior);
				}
			}
			executionName = new StringBuffer("BehaviorExecution_").append(behaviors.size()); //$NON-NLS-1$
		} else {
			executionName = new StringBuffer(operation.getName());
		}

		// Create execution start
		final ExecutionOccurrenceSpecification startExec = factory.createExecutionOccurrenceSpecification();
		final StringBuffer startExecName = new StringBuffer(executionName).append("_start"); //$NON-NLS-1$
		startExec.setName(startExecName.toString());
		startExec.getCovereds().add(lifeline);

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
		final StringBuffer endExecName = new StringBuffer(executionName).append(EXECUTION_END_SUFFIX);
		endExec.setName(endExecName.toString());
		endExec.getCovereds().add(lifeline);
		endExec.setExecution(execution);
		execution.setFinish(endExec);

		// Add and order fragments under the interaction
		final EList<InteractionFragment> fragments = interaction.getFragments();

		// Ordered fragments
		fragments.add(startExec);

		// If execution starts from an execution, add the new execution start after the execution
		// specification
		if (startingEndPredecessor instanceof OccurrenceSpecification
				&& getExecution((OccurrenceSpecification)startingEndPredecessor) != null
				&& startingEndPredecessor
				.equals(getExecution((OccurrenceSpecification)startingEndPredecessor).getStart())) {
			fragments.move(fragments.indexOf(startingEndPredecessor) + 2, startExec);
		} else {
			// Message starts from a lifeline, add the message start after the last starting predecessor
			// (message)
			fragments.move(fragments.indexOf(startingEndPredecessor) + 1, startExec);
		}
		fragments.add(execution);
		fragments.move(fragments.indexOf(startExec) + 1, execution);
		fragments.add(endExec);
		fragments.move(fragments.indexOf(execution) + 1, endExec);
	}

	/**
	 * Create execution.
	 *
	 * @param covered
	 *            Lifeline
	 * @param message
	 *            Message
	 * @return Execution
	 */
	public BehaviorExecutionSpecification createExecution(/* Operation operation, */
			final Lifeline covered, final Message message) {
		final UMLFactory factory = UMLFactory.eINSTANCE;
		final OpaqueBehavior behavior = factory.createOpaqueBehavior();

		behavior.setName(message.getName());
		// behavior.setSpecification(operation);

		final BehaviorExecutionSpecification execution = factory.createBehaviorExecutionSpecification();
		execution.setName(message.getName());
		execution.getCovereds().add(covered);
		execution.setBehavior(behavior);

		return execution;
	}

	/**
	 * create a reply message for a synchronous message
	 *
	 * @param interaction
	 *            interaction.
	 * @param sourceFragment
	 *            source
	 * @param targetFragment
	 *            target
	 * @param startingEndPredecessor
	 *            starting end predecessor
	 * @param finishingEndPredecessor
	 *            finishing end predecessor
	 * @param message
	 *            a synchronous message
	 * @return a reply message
	 */
	private Message createReplyMessage(Interaction interaction, NamedElement sourceFragment,
			NamedElement targetFragment, Element startingEndPredecessor, Element finishingEndPredecessor,
			Message message) {

		final UMLFactory factory = UMLFactory.eINSTANCE;

		final Lifeline source = getLifeline(sourceFragment);
		final Lifeline target = getLifeline(targetFragment);

		// Create reply message
		final Message replyMessage = factory.createMessage();
		replyMessage.setName(message.getName() + "_reply"); //$NON-NLS-1$
		replyMessage.setMessageSort(MessageSort.REPLY_LITERAL);
		interaction.getMessages().add(replyMessage);

		// Create reply message send event
		final MessageOccurrenceSpecification senderEventReplyMessage = factory
				.createMessageOccurrenceSpecification();
		senderEventReplyMessage.setName(replyMessage.getName() + SENDER_MESSAGE_SUFFIX);
		senderEventReplyMessage.getCovereds().add(target);
		senderEventReplyMessage.setMessage(replyMessage);

		// Create reply message receive event
		final MessageOccurrenceSpecification receiverEventReplyMessage = factory
				.createMessageOccurrenceSpecification();
		receiverEventReplyMessage.setName(replyMessage.getName() + RECEIVER_MESSAGE_SUFFIX);
		receiverEventReplyMessage.getCovereds().add(source);
		receiverEventReplyMessage.setMessage(replyMessage);

		replyMessage.setSendEvent(senderEventReplyMessage);
		replyMessage.setReceiveEvent(receiverEventReplyMessage);

		return replyMessage;
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
	 * @param createExecution
	 *            set to true to create an execution
	 * @param startingEndPredecessor
	 *            Starting end predecessor
	 * @param finishingEndPredecessor
	 *            Finishing end predecessor
	 * @param operation
	 *            Operation associated with the message
	 */
	public void createSynchronousMessage(Interaction interaction, NamedElement sourceFragment,
			NamedElement targetFragment, boolean createExecution, Element startingEndPredecessor,
			Element finishingEndPredecessor, Operation operation) {
		final Lifeline target = getLifeline(targetFragment);
		final BehaviorExecutionSpecification predecessorExecution = getExecution((InteractionFragment)startingEndPredecessor);

		final UMLFactory factory = UMLFactory.eINSTANCE;
		final EList<InteractionFragment> fragments = interaction.getFragments();

		final Message message = createSynchronousMessage(interaction, sourceFragment, targetFragment,
				startingEndPredecessor, finishingEndPredecessor, operation);
		interaction.getMessages().add(message);

		if (null != operation) {
			message.setSignature(operation);
		}

		// Create behavior
		BehaviorExecutionSpecification execution = null;
		if (createExecution) {
			execution = createExecution(/* operation, */target, message);
			interaction.getOwnedBehaviors().add(execution.getBehavior());
		} else {
			// Create signal
			final Signal signal = factory.createSignal();
			signal.setName(message.getName() + SIGNAL_SUFFIX);
			message.getNearestPackage().getPackagedElements().add(signal);
		}
		if (execution != null) {
			execution.setStart((MessageOccurrenceSpecification)message.getReceiveEvent());
		}

		final Message replyMessage = createReplyMessage(interaction, sourceFragment, targetFragment,
				startingEndPredecessor, finishingEndPredecessor, message);
		interaction.getMessages().add(replyMessage);

		if (execution != null) {
			execution.setFinish((MessageOccurrenceSpecification)replyMessage.getSendEvent());
		}

		// Add and order fragments under the interaction
		// If predecessor is the beginning of an execution add message after the execution
		if (startingEndPredecessor != null && startingEndPredecessor instanceof OccurrenceSpecification
				&& predecessorExecution != null
				&& startingEndPredecessor.equals(predecessorExecution.getStart())) {
			fragments.add(fragments.indexOf(predecessorExecution) + 1,
					(MessageOccurrenceSpecification)message.getSendEvent());
			// Else set it directly after the predecessor
		} else {
			fragments.add(fragments.indexOf(startingEndPredecessor) + 1,
					(MessageOccurrenceSpecification)message.getSendEvent());
		}

		fragments.add(fragments.indexOf(message.getSendEvent()) + 1,
				(MessageOccurrenceSpecification)message.getReceiveEvent());

		fragments.add((MessageOccurrenceSpecification)replyMessage.getSendEvent());
		if (execution != null) {
			fragments.add(fragments.indexOf(message.getReceiveEvent()) + 1,
					execution);
			fragments.move(fragments.indexOf(execution) + 1,
					(MessageOccurrenceSpecification)replyMessage.getSendEvent());
		} else {
			fragments.move(fragments.indexOf(message.getReceiveEvent()) + 1,
					(MessageOccurrenceSpecification)replyMessage.getSendEvent());
		}

		fragments.add(fragments.indexOf(replyMessage.getSendEvent()) + 1,
				(MessageOccurrenceSpecification)replyMessage.getReceiveEvent());
	}

	/**
	 * Create synchronous typed message.
	 *
	 * @param interaction
	 *            interaction
	 * @param sourceFragment
	 *            source
	 * @param targetFragment
	 *            target
	 * @param startingEndPredecessor
	 *            starting end predecessor
	 * @param finishingEndPredecessor
	 *            finishing end predecessor
	 * @param operation
	 *            operation associated with the message
	 * @return a synchronous message
	 */
	private Message createSynchronousMessage(Interaction interaction, NamedElement sourceFragment,
			NamedElement targetFragment, Element startingEndPredecessor, Element finishingEndPredecessor,
			Operation operation) {

		final UMLFactory factory = UMLFactory.eINSTANCE;

		final Lifeline source = getLifeline(sourceFragment);
		final Lifeline target = getLifeline(targetFragment);

		String messageName = ""; //$NON-NLS-1$
		if (operation == null) {
			messageName = "Message_" + interaction.getMessages().size(); //$NON-NLS-1$
		} else {
			messageName = operation.getName();
		}

		final Message message = factory.createMessage();
		message.setName(messageName);
		message.setMessageSort(MessageSort.SYNCH_CALL_LITERAL);

		// Create message send event
		final MessageOccurrenceSpecification senderEventMessage = factory
				.createMessageOccurrenceSpecification();
		senderEventMessage.setName(message.getName() + SENDER_MESSAGE_SUFFIX);
		senderEventMessage.getCovereds().add(source);
		senderEventMessage.setMessage(message);

		// Create message receive event
		final MessageOccurrenceSpecification receiverEventMessage = factory
				.createMessageOccurrenceSpecification();
		receiverEventMessage.setName(message.getName() + RECEIVER_MESSAGE_SUFFIX);
		receiverEventMessage.getCovereds().add(target);
		receiverEventMessage.setMessage(message);

		message.setSendEvent(senderEventMessage);
		message.setReceiveEvent(receiverEventMessage);

		return message;

	}

	private boolean executionFinishPredecessorChanged(ExecutionSpecification execution,
			InteractionFragment finishingEndPredecessorAfter) {
		final Interaction interaction = execution.getEnclosingInteraction();
		final EList<InteractionFragment> fragments = interaction.getFragments();
		final InteractionFragment initialPredecessor = fragments
				.get(fragments.indexOf(execution.getFinish()) - 1);

		return !initialPredecessor.equals(finishingEndPredecessorAfter);
	}

	private boolean executionStartPredecessorChanged(ExecutionSpecification execution,
			InteractionFragment startingEndPredecessorAfter) {
		final Interaction interaction = execution.getEnclosingInteraction();
		final EList<InteractionFragment> fragments = interaction.getFragments();
		final InteractionFragment initialPredecessor = fragments
				.get(fragments.indexOf(execution.getStart()) - 1);

		return !initialPredecessor.equals(startingEndPredecessorAfter);
	}

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

		final Interaction enclosingInteraction = getEnclosingInteraction(occurrenceSpecification);
		final List<InteractionFragment> allFragments = enclosingInteraction.getFragments();

		final List<InteractionFragment> fragments = new ArrayList<InteractionFragment>();
		for (final InteractionFragment fragment : allFragments) {
			if (fragment.getCovered(lifeline.getName()) != null) {
				fragments.add(fragment);
			}
		}

		for (int i = 0; i < fragments.size(); i++) {
			final InteractionFragment e = fragments.get(i);
			InteractionFragment en;
			if (i + 1 < fragments.size()) {
				en = fragments.get(i + 1);
			} else {
				en = null;
			}

			if (e instanceof MessageOccurrenceSpecification && en != null
					&& en instanceof ExecutionSpecification) {
				context.add(en);
			}

			if (e instanceof ExecutionOccurrenceSpecification) {
				if (en == null || !(en instanceof ExecutionSpecification)) {
					context.pop();
				}
			}

			// Found our element
			if (e == occurrenceSpecification) {
				return context.peek();
			}

			if (e instanceof ExecutionOccurrenceSpecification) {
				if (en != null && en instanceof ExecutionSpecification) {
					context.add(fragments.get(i + 1));
				}
			}

			if (e instanceof MessageOccurrenceSpecification && isEnd(e, fragments)) {
				context.pop();
			}
		}

		return lifeline;
	}

	/**
	 * Get the enclosing interaction
	 *
	 * @param occurrenceSpecification
	 * @return the interaction
	 */
	public Interaction getEnclosingInteraction(Element occurrenceSpecification) {
		Interaction result = null;
		EObject container = occurrenceSpecification.eContainer();
		while (!(container instanceof Interaction)) {
			container = container.eContainer();
		}

		if (container instanceof Interaction) {
			result = (Interaction)container;
			return result;
		}

		return result;
	}

	/**
	 * Get the execution associated to a fragment.
	 *
	 * @param occurence
	 *            Occurence
	 * @return Execution
	 */
	public BehaviorExecutionSpecification getExecution(InteractionFragment occurence) {
		if (occurence == null) {
			return null;
		}
		final Map<InteractionFragment, BehaviorExecutionSpecification> behaviors = new HashMap<InteractionFragment, BehaviorExecutionSpecification>();

		final Interaction interaction = getEnclosingInteraction(occurence);
		for (final InteractionFragment fragment : interaction.getFragments()) {
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
	 * Get the execution associated to a fragment.
	 *
	 * @param message
	 *            Message
	 * @return Execution
	 */
	private BehaviorExecutionSpecification getExecution(Message message) {
		if (message == null) {
			return null;
		}
		final Map<Message, BehaviorExecutionSpecification> behaviors = new HashMap<Message, BehaviorExecutionSpecification>();
		for (final InteractionFragment fragment : message.getInteraction().getFragments()) {
			if (fragment instanceof BehaviorExecutionSpecification) {
				final BehaviorExecutionSpecification behavior = (BehaviorExecutionSpecification)fragment;
				final OccurrenceSpecification behaviorStart = behavior.getStart();
				if (behaviorStart instanceof MessageOccurrenceSpecification
						&& message.equals(((MessageOccurrenceSpecification)behaviorStart).getMessage())) {
					behaviors.put(message, behavior);
				}
			}
		}
		return behaviors.get(message);
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
	private int getFragmentIndex(InteractionFragment fragment, final EList<InteractionFragment> fragments) {
		if (fragment != null) {
			return fragments.indexOf(fragment);
		}
		return 0;
	}

	/**
	 * Get the lifeline associated to a fragment.
	 *
	 * @param fragment
	 *            Fragment
	 * @return Lifeline if exists otherwise null
	 */
	public Lifeline getLifeline(Element fragment) {
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
	 * Get the reply message associated to a message.
	 *
	 * @param message
	 *            Message
	 * @return Reply message if exists otherwise null
	 */
	public Message getReplyMessage(Message message) {
		// To get the reply message associated to a message
		// Get the execution associated to message if exists
		final BehaviorExecutionSpecification execution = getExecution(message);
		if (execution != null) {
			// Get the end execution occurrence
			final OccurrenceSpecification end = execution.getFinish();
			if (end instanceof MessageOccurrenceSpecification) {
				// Get the message
				return ((MessageOccurrenceSpecification)end).getMessage();
			}
		}
		// else in case of message without execution search by name
		for (final Message messageReply : message.getInteraction().getMessages()) {
			if (MessageSort.REPLY_LITERAL.equals(messageReply.getMessageSort())
					&& messageReply.getName().startsWith(message.getName())) {
				return messageReply;
			}
		}
		return null;
	}

	/**
	 * Check if is covered type.
	 *
	 * @param element
	 *            Execution specification
	 * @return True if is a covered type
	 */
	public boolean isCoveredTypeSet(ExecutionSpecification element) {
		if (element == null) {
			return false;
		}
		for (final Lifeline covered : element.getCovereds()) {
			final ConnectableElement connectedElement = covered.getRepresents();
			if (connectedElement.getTemplateParameter() != null) {
				return true;
			}
		}
		return false;
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
		for (final InteractionFragment fragment : fragments) {
			if (fragment instanceof BehaviorExecutionSpecification) {
				// Get start
				final BehaviorExecutionSpecification behavior = (BehaviorExecutionSpecification)fragment;
				// Get finish
				executionFinishes.add(behavior.getFinish());
			}
		}
		return executionFinishes.contains(endCandidate);
	}

	private boolean isMoveAuthorized(Message message) {
		if (message.getMessageSort().equals(MessageSort.REPLY_LITERAL)) {
			return false;
		}
		return true;
	}





	private boolean isStartOfExecution(InteractionFragment startingEndPredecessorAfter,
			EList<InteractionFragment> fragments) {
		if (startingEndPredecessorAfter instanceof ExecutionOccurrenceSpecification) {
			final ExecutionOccurrenceSpecification executionStart = (ExecutionOccurrenceSpecification)startingEndPredecessorAfter;
			if (executionStart.getExecution().getStart().equals(executionStart)) {
				return true;
			}
		}

		if (startingEndPredecessorAfter instanceof MessageOccurrenceSpecification) {
			if (fragments.indexOf(startingEndPredecessorAfter) + 1 < fragments.size()) {
				final InteractionFragment candidate = fragments
						.get(fragments.indexOf(startingEndPredecessorAfter) + 1);
				if (candidate instanceof BehaviorExecutionSpecification) {
					final BehaviorExecutionSpecification behaviorExecution = (BehaviorExecutionSpecification)candidate;
					if (behaviorExecution.getStart().equals(startingEndPredecessorAfter)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private boolean messageStartPredecessorChanged(Message message,
			InteractionFragment startingEndPredecessorAfter) {
		final Interaction interaction = message.getInteraction();
		final EList<InteractionFragment> fragments = interaction.getFragments();
		final InteractionFragment initialPredecessor = fragments
				.get(fragments.indexOf(message.getSendEvent()) - 1);

		return !initialPredecessor.equals(startingEndPredecessorAfter);

	}


	/**
	 * Reorder combined fragment.
	 *
	 * @param combinedFragment
	 *            Moved combined fragment
	 * @param startingEndPredecessorAfter
	 *            Fragment preceding moved combined fragment start before the beginning of reorder operation
	 * @param finishingEndPredecessorAfter
	 *            Fragment preceding moved combined fragment finish before the beginning of reorder operation
	 */
	public void reorder(CombinedFragment combinedFragment, InteractionFragment startingEndPredecessorAfter,
			InteractionFragment finishingEndPredecessorAfter) {

		final Interaction interaction = combinedFragment.getEnclosingInteraction();
		final EList<InteractionFragment> fragments = interaction.getFragments();

		final boolean combinedFragmentStartPredecessorChanged = combinedFragmentStartPredecessorChanged(
				combinedFragment, startingEndPredecessorAfter);

		if (combinedFragmentStartPredecessorChanged) {
			fragments.remove(combinedFragment);
			if (isStartOfExecution(startingEndPredecessorAfter, fragments)) {
				fragments.add(getFragmentIndex(startingEndPredecessorAfter, fragments) + 2, combinedFragment);
			} else {
				fragments.add(getFragmentIndex(startingEndPredecessorAfter, fragments) + 1, combinedFragment);
			}
		}
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

		final Interaction interaction = execution.getEnclosingInteraction();
		final EList<InteractionFragment> fragments = interaction.getFragments();

		final boolean executionFinishPredecessorChanged = executionFinishPredecessorChanged(execution,
				finishingEndPredecessorAfter);
		final boolean executionStartPredecessorChanged = executionStartPredecessorChanged(execution,
				startingEndPredecessorAfter);

		if (executionStartPredecessorChanged) {

			fragments.remove(execution.getStart());
			fragments.remove(execution);

			if (isStartOfExecution(startingEndPredecessorAfter, fragments)) {
				fragments.add(getFragmentIndex(startingEndPredecessorAfter, fragments) + 2,
						execution.getStart());
			} else {
				fragments.add(getFragmentIndex(startingEndPredecessorAfter, fragments) + 1,
						execution.getStart());
			}
			fragments.add(getFragmentIndex(execution.getStart(), fragments) + 1, execution);
		}

		if (executionFinishPredecessorChanged) {
			fragments.remove(execution.getFinish());
			if (isStartOfExecution(finishingEndPredecessorAfter, fragments)) {
				fragments.add(getFragmentIndex(finishingEndPredecessorAfter, fragments) + 2,
						execution.getFinish());
			} else {
				fragments.add(getFragmentIndex(finishingEndPredecessorAfter, fragments) + 1,
						execution.getFinish());
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
		if (isMoveAuthorized(message)) {
			final Interaction interaction = message.getInteraction();
			final EList<InteractionFragment> fragments = interaction.getFragments();

			final boolean messageStartPredecessorChanged = messageStartPredecessorChanged(message,
					startingEndPredecessorAfter);

			if (messageStartPredecessorChanged) {
				fragments.remove(message.getSendEvent());
				fragments.remove(message.getReceiveEvent());

				if (isStartOfExecution(startingEndPredecessorAfter, fragments)) {
					fragments.add(getFragmentIndex(startingEndPredecessorAfter, fragments) + 2,
							(MessageOccurrenceSpecification)message.getSendEvent());
				} else {
					fragments.add(getFragmentIndex(startingEndPredecessorAfter, fragments) + 1,
							(MessageOccurrenceSpecification)message.getSendEvent());
				}

				fragments
				.add(getFragmentIndex((MessageOccurrenceSpecification)message.getSendEvent(),
						fragments) + 1, (MessageOccurrenceSpecification)message.getReceiveEvent());
			}

			// Move reply for synchronous message
			if (message.getMessageSort().equals(MessageSort.SYNCH_CALL_LITERAL)) {
				final Message replyMessage = getReplyMessage(message);
				fragments.remove(replyMessage.getSendEvent());
				fragments.remove(replyMessage.getReceiveEvent());

				if (isStartOfExecution((MessageOccurrenceSpecification)message.getReceiveEvent(), fragments)) {
					fragments.add(
							getFragmentIndex((MessageOccurrenceSpecification)message.getReceiveEvent(),
									fragments) + 2, (MessageOccurrenceSpecification)replyMessage
									.getSendEvent());
				} else {
					fragments.add(
							getFragmentIndex((MessageOccurrenceSpecification)message.getReceiveEvent(),
									fragments) + 1, (MessageOccurrenceSpecification)replyMessage
									.getSendEvent());
				}

				fragments.add(
						getFragmentIndex((MessageOccurrenceSpecification)replyMessage.getSendEvent(),
								fragments) + 1, (MessageOccurrenceSpecification)replyMessage
								.getReceiveEvent());

			}
		}
	}


}
