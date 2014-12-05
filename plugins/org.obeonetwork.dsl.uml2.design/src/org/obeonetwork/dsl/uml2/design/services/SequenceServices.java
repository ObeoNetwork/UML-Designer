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
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.RefreshGraphicalOrderingOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.RefreshSemanticOrderingsOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SynchronizeGraphicalOrderingOperation;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
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
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.services.internal.NamedElementServices;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

/**
 * Utility services to manage sequence diagrams.
 * 
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class SequenceServices {
	private final String EXECUTION_END_SUFFIX = "_finish";

	/**
	 * Signal name suffix.
	 */
	private final String SIGNAL_SUFFIX = "_signal";

	/**
	 * Sender message name suffix.
	 */
	private final String SENDER_MESSAGE_SUFFIX = "_sender";

	/**
	 * Receiver message name suffix.
	 */
	private final String RECEIVER_MESSAGE_SUFFIX = "_receiver";

	/**
	 * Logger.
	 */
	private LogServices logger = new LogServices();

	/**
	 * Operation service.
	 */
	private OperationServices operationService = new OperationServices();

	public NamedElement findOccurrenceSpecificationContextForSendEvent(Message message) {
		return findOccurrenceSpecificationContext((OccurrenceSpecification)message.getSendEvent());
	}

	public NamedElement findOccurrenceSpecificationContextForReceiveEvent(Message message) {
		return findOccurrenceSpecificationContext((OccurrenceSpecification)message.getReceiveEvent());
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

		Interaction enclosingInteraction = getEnclosingInteraction(occurrenceSpecification);
		List<InteractionFragment> allFragments = enclosingInteraction.getFragments();

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
	 * Get the enclosing interaction
	 * 
	 * @param occurrenceSpecification
	 * @return the interaction
	 */
	private Interaction getEnclosingInteraction(Element occurrenceSpecification) {
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

		Interaction interaction = getEnclosingInteraction(occurence);
		for (InteractionFragment fragment : interaction.getFragments()) {
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
		if (message == null)
			return null;
		final Map<Message, BehaviorExecutionSpecification> behaviors = new HashMap<Message, BehaviorExecutionSpecification>();
		for (InteractionFragment fragment : message.getInteraction().getFragments()) {
			if (fragment instanceof BehaviorExecutionSpecification) {
				final BehaviorExecutionSpecification behavior = (BehaviorExecutionSpecification)fragment;
				final OccurrenceSpecification behaviorStart = behavior.getStart();
				if (behaviorStart instanceof MessageOccurrenceSpecification
						&& message.equals(((MessageOccurrenceSpecification)behaviorStart).getMessage()))
					behaviors.put(message, behavior);
			}
		}
		return behaviors.get(message);
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
		if (!(element instanceof org.eclipse.uml2.uml.Class) && !(element instanceof Property)) {
			logger.warning(
					"An instance specification or a property must be selected to import a lifeline but you have selected "
							+ element.getName() + " which is a " + element.getClass().getSimpleName(), null);
		}

		// Create lifeline
		if (element instanceof org.eclipse.uml2.uml.Class) {
			createLifeline(interaction, (org.eclipse.uml2.uml.Class)element);
		} else if (element instanceof Property) {
			createLifeline(interaction, (Property)element);
		} else if (element instanceof Actor) {
			createLifeline(interaction, (Actor)element);
		}
	}

	private void createLifeline(Interaction interaction, org.eclipse.uml2.uml.Class type) {
		// Create lifeline
		final Lifeline lifeline = UMLFactory.eINSTANCE.createLifeline();
		lifeline.setName(type.getName());
		final Property property = UMLFactory.eINSTANCE.createProperty();
		property.setName(type.getName());
		property.setType(type);
		interaction.getOwnedAttributes().add(property);
		lifeline.setRepresents(property);
		interaction.getLifelines().add(lifeline);
	}

	private void createLifeline(Interaction interaction, Actor type) {
		// Create lifeline
		final Lifeline lifeline = UMLFactory.eINSTANCE.createLifeline();
		lifeline.setName(type.getName());
		final Property property = UMLFactory.eINSTANCE.createProperty();
		property.setName(type.getName());
		property.setType(type);
		interaction.getOwnedAttributes().add(property);
		lifeline.setRepresents(property);
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
	 * Add a context to the lifeline.
	 * 
	 * @param lifeline
	 *            the lifeline
	 * @param context
	 *            the context
	 */
	public void addContext(Lifeline lifeline, Property context) {
		// Create lifeline
		deleteContext(lifeline);
		final Dependency dependency = UMLFactory.eINSTANCE.createDependency();
		dependency.setName(lifeline.getName() + "_" + context.getName());
		dependency.getClients().add(lifeline);
		dependency.getSuppliers().add(context);
		lifeline.getNearestPackage().getPackagedElements().add(dependency);
		lifeline.getClientDependencies().add(dependency);
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

	public void createSynchronousMessageWithExecution(Interaction interaction, NamedElement sourceFragment,
			NamedElement targetFragment, EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor,
			Operation operation) {
		Element startingEndPredecessorSemanticEnd = null;
		Element finishingEndPredecessorSemanticEnd = null;
		if (startingEndPredecessor != null) {
			startingEndPredecessorSemanticEnd = (Element)startingEndPredecessor.getSemanticEnd();
		}
		if (finishingEndPredecessor != null) {
			finishingEndPredecessorSemanticEnd = (Element)finishingEndPredecessor.getSemanticEnd();
		}

		createSynchronousMessage(interaction, sourceFragment, targetFragment, true,
				startingEndPredecessorSemanticEnd, finishingEndPredecessorSemanticEnd, operation);
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
			NamedElement targetFragment, Boolean createExecution, Element startingEndPredecessor,
			Element finishingEndPredecessor, Operation operation) {
		final Lifeline target = getLifeline(targetFragment);
		final BehaviorExecutionSpecification predecessorExecution = getExecution((InteractionFragment)startingEndPredecessor);

		final UMLFactory factory = UMLFactory.eINSTANCE;
		final EList<InteractionFragment> fragments = interaction.getFragments();

		Message message = createSynchronousMessage(interaction, sourceFragment, targetFragment,
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
		if (execution != null)
			execution.setStart((MessageOccurrenceSpecification)message.getReceiveEvent());

		Message replyMessage = createReplyMessage(interaction, sourceFragment, targetFragment,
				startingEndPredecessor, finishingEndPredecessor, message);
		interaction.getMessages().add(replyMessage);

		if (execution != null)
			execution.setFinish((MessageOccurrenceSpecification)replyMessage.getSendEvent());

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

		fragments.add(fragments.indexOf((MessageOccurrenceSpecification)message.getSendEvent()) + 1,
				(MessageOccurrenceSpecification)message.getReceiveEvent());

		fragments.add((MessageOccurrenceSpecification)replyMessage.getSendEvent());
		if (execution != null) {
			fragments.add(fragments.indexOf((MessageOccurrenceSpecification)message.getReceiveEvent()) + 1,
					execution);
			fragments.move(fragments.indexOf(execution) + 1,
					(MessageOccurrenceSpecification)replyMessage.getSendEvent());
		} else
			fragments.move(fragments.indexOf((MessageOccurrenceSpecification)message.getReceiveEvent()) + 1,
					(MessageOccurrenceSpecification)replyMessage.getSendEvent());

		fragments.add(fragments.indexOf((MessageOccurrenceSpecification)replyMessage.getSendEvent()) + 1,
				(MessageOccurrenceSpecification)replyMessage.getReceiveEvent());
	}

	/**
	 * Get all classes and their containers
	 * 
	 * @param element
	 *            a model element
	 * @return all classes and their containers of the UML model
	 */
	public Set<Element> getAllClassesAndContainers(Element element) {
		Element rootContainer = (Element)EcoreUtil.getRootContainer(element);
		Iterator<EObject> eObjects = rootContainer.eAllContents();

		Set<Element> result = new HashSet<Element>();

		result.add(rootContainer);

		while (eObjects.hasNext()) {
			Element eObject = (Element)eObjects.next();
			if (eObject instanceof org.eclipse.uml2.uml.Class && !(eObject instanceof OpaqueBehavior)) {
				result.add(eObject);
				Set<Element> containers = getAllContainers(eObject);
				result.addAll(containers);
			}
			if (eObject instanceof Property) {
				Property property = (Property)eObject;
				if (property.getType() instanceof org.eclipse.uml2.uml.Class) {
					result.add(property);
					Set<Element> containers = getAllContainers(property);
					result.addAll(containers);
				}
			}
		}

		return result;
	}

	/**
	 * Get all actors and their containers
	 * 
	 * @param element
	 *            a model element
	 * @return all actors and their containers of the UML model
	 */
	public Set<Element> getAllActorsAndContainers(EObject element) {
		Element rootContainer = (Element)EcoreUtil.getRootContainer(element);
		Iterator<EObject> eObjects = rootContainer.eAllContents();

		Set<Element> result = new HashSet<Element>();

		result.add(rootContainer);

		while (eObjects.hasNext()) {
			Element eObject = (Element)eObjects.next();
			if (eObject instanceof Actor) {
				result.add(eObject);
				Set<Element> containers = getAllContainers(eObject);
				result.addAll(containers);
			}
			if (eObject instanceof Property) {
				Property property = (Property)eObject;
				if (property.getType() instanceof Actor) {
					result.add(property);
					Set<Element> containers = getAllContainers(property);
					result.addAll(containers);
				}
			}
		}

		return result;
	}

	/**
	 * Get a set of all containers from an eObject to the model root
	 * 
	 * @param eObject
	 *            an eObject
	 * @return a list of containers
	 */
	private Set<Element> getAllContainers(Element eObject) {
		Element container = (Element)eObject.eContainer();
		Set<Element> result = new HashSet<Element>();

		while (container.eContainer() != null) {
			result.add(container);
			container = (Element)container.eContainer();
		}

		return result;
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
	public Message createSynchronousMessage(Interaction interaction, NamedElement sourceFragment,
			NamedElement targetFragment, Element startingEndPredecessor, Element finishingEndPredecessor,
			Operation operation) {

		final UMLFactory factory = UMLFactory.eINSTANCE;

		final Lifeline source = getLifeline(sourceFragment);
		final Lifeline target = getLifeline(targetFragment);

		String messageName = "";
		if (operation == null) {
			messageName = "Message_" + interaction.getMessages().size();
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
	public Message createReplyMessage(Interaction interaction, NamedElement sourceFragment,
			NamedElement targetFragment, Element startingEndPredecessor, Element finishingEndPredecessor,
			Message message) {

		final UMLFactory factory = UMLFactory.eINSTANCE;

		final Lifeline source = getLifeline(sourceFragment);
		final Lifeline target = getLifeline(targetFragment);

		// Create reply message
		final Message replyMessage = factory.createMessage();
		replyMessage.setName(message.getName() + "_reply");
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
	 * Delete lifeline.
	 * 
	 * @param lifeline
	 *            Lifeline to delete
	 */
	public void delete(Lifeline lifeline) {
		if (lifeline == null)
			return;
		// Delete dependency
		deleteContext(lifeline);

		// Delete all executions
		for (ExecutionSpecification execution : executionSemanticCandidates(lifeline)) {
			if (execution instanceof BehaviorExecutionSpecification)
				delete((BehaviorExecutionSpecification)execution);
		}

		// Delete all messages
		for (Message message : getAllMessages(lifeline)) {
			delete(message);
		}

		// Delete lifeline
		lifeline.destroy();
	}

	/**
	 * Delete the client dependency used for the context.
	 * 
	 * @param lifeline
	 *            the lifeline
	 */
	// add an eannotation
	public void deleteContext(Lifeline lifeline) {
		final Object[] dependencies = lifeline.getClientDependencies().toArray();

		for (int i = 0; i < dependencies.length; i++) {
			EcoreUtil.delete((Dependency)dependencies[i]);
		}

	}

	/**
	 * Get all messages associated to lifeline.
	 * 
	 * @param lifeline
	 *            Lifeline
	 * @return Messages associated to lifeline
	 */
	private List<Message> getAllMessages(Lifeline lifeline) {
		final List<Message> messages = new ArrayList<Message>();
		if (lifeline != null && lifeline.getInteraction() != null) {
			for (Message message : lifeline.getInteraction().getMessages()) {
				for (Lifeline coveredLifeline : ((MessageOccurrenceSpecification)message.getSendEvent())
						.getCovereds()) {
					if (lifeline.equals(coveredLifeline))
						messages.add(message);
				}
			}
		}
		return messages;
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
			fragments.remove(start);
		}
		final OccurrenceSpecification finish = execution.getFinish();
		if (finish instanceof ExecutionOccurrenceSpecification) {
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
			fragments.remove(receiveMessage);
		}
		final MessageOccurrenceSpecification sendMessage = (MessageOccurrenceSpecification)message
				.getSendEvent();
		if (sendMessage != null) {
			final BehaviorExecutionSpecification execution = getExecution(sendMessage);
			if (execution != null)
				delete(execution);
			// Delete signal
			List<PackageableElement> packagedElements = new ArrayList<PackageableElement>();
			packagedElements.addAll(message.getNearestPackage().getPackagedElements());
			for (PackageableElement packageableElement : packagedElements) {
				if (packageableElement instanceof Signal) {
					Signal signal = (Signal)packageableElement;
					if (signal.getName().startsWith(message.getName())) {
						signal.destroy();
					}
				}
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
			final Type type = getType((Lifeline)target);
			if (type != null)
				elements = type.getOwnedElements();
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
	public Interaction createInteraction(EObject pkg) {
		final UMLFactory factory = UMLFactory.eINSTANCE;
		final Interaction interaction = factory.createInteraction();
		interaction.setName(NamedElementServices.getNewInteractionName((Package)pkg));
		((Package)pkg).getPackagedElements().add(interaction);
		return interaction;
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

	private boolean combinedFragmentStartPredecessorChanged(CombinedFragment combinedFragment,
			InteractionFragment startingEndPredecessorAfter) {
		final Interaction interaction = combinedFragment.getEnclosingInteraction();
		final EList<InteractionFragment> fragments = interaction.getFragments();
		final InteractionFragment initialPredecessor = fragments.get(fragments.indexOf(combinedFragment) - 1);

		return !initialPredecessor.equals(startingEndPredecessorAfter);
	}

	private boolean isStartOfExecution(InteractionFragment startingEndPredecessorAfter,
			EList<InteractionFragment> fragments) {
		if (startingEndPredecessorAfter instanceof ExecutionOccurrenceSpecification) {
			ExecutionOccurrenceSpecification executionStart = (ExecutionOccurrenceSpecification)startingEndPredecessorAfter;
			if (executionStart.getExecution().getStart().equals(executionStart)) {
				return true;
			}
		}

		if (startingEndPredecessorAfter instanceof MessageOccurrenceSpecification) {
			if ((fragments.indexOf(startingEndPredecessorAfter) + 1) < fragments.size()) {
				InteractionFragment candidate = fragments
						.get(fragments.indexOf(startingEndPredecessorAfter) + 1);
				if (candidate instanceof BehaviorExecutionSpecification) {
					BehaviorExecutionSpecification behaviorExecution = (BehaviorExecutionSpecification)candidate;
					if (behaviorExecution.getStart().equals(startingEndPredecessorAfter)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private boolean executionStartPredecessorChanged(ExecutionSpecification execution,
			InteractionFragment startingEndPredecessorAfter) {
		final Interaction interaction = execution.getEnclosingInteraction();
		final EList<InteractionFragment> fragments = interaction.getFragments();
		final InteractionFragment initialPredecessor = fragments
				.get(fragments.indexOf(execution.getStart()) - 1);

		return !initialPredecessor.equals(startingEndPredecessorAfter);
	}

	private boolean executionFinishPredecessorChanged(ExecutionSpecification execution,
			InteractionFragment finishingEndPredecessorAfter) {
		final Interaction interaction = execution.getEnclosingInteraction();
		final EList<InteractionFragment> fragments = interaction.getFragments();
		final InteractionFragment initialPredecessor = fragments
				.get(fragments.indexOf(execution.getFinish()) - 1);

		return !initialPredecessor.equals(finishingEndPredecessorAfter);
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
		if (fragment != null)
			return fragments.indexOf(fragment);
		return 0;
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
				Message replyMessage = getReplyMessage(message);
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

	private boolean messageStartPredecessorChanged(Message message,
			InteractionFragment startingEndPredecessorAfter) {
		final Interaction interaction = message.getInteraction();
		final EList<InteractionFragment> fragments = interaction.getFragments();
		final InteractionFragment initialPredecessor = fragments
				.get(fragments.indexOf(message.getSendEvent()) - 1);

		return !initialPredecessor.equals(startingEndPredecessorAfter);

	}

	private boolean isMoveAuthorized(Message message) {
		if (message.getMessageSort().equals(MessageSort.REPLY_LITERAL)) {
			return false;
		}
		return true;
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
			EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor) {
		Element startingEndPredecessorSemanticEnd = null;
		if (startingEndPredecessor != null) {
			startingEndPredecessorSemanticEnd = (Element)startingEndPredecessor.getSemanticEnd();
		}
		Element finishingEndPredecessorSemanticEnd = null;
		if (finishingEndPredecessor != null) {
			finishingEndPredecessorSemanticEnd = (Element)finishingEndPredecessor.getSemanticEnd();
		}
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
		createAsynchronousMessage(interaction, source, target, true, startingEndPredecessorSemanticEnd,
				finishingEndPredecessorSemanticEnd, operation);
	}

	/**
	 * Create an operation and an synchronous message from a lifeline or an execution. Create the operation in
	 * the class and the synchronous message in the interaction.
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
	public void createOperationAndSynchMessage(NamedElement target, NamedElement source,
			EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor) {
		Element startingEndPredecessorSemanticEnd = null;
		if (startingEndPredecessor != null) {
			startingEndPredecessorSemanticEnd = (Element)startingEndPredecessor.getSemanticEnd();
		}
		Element finishingEndPredecessorSemanticEnd = null;
		if (finishingEndPredecessor != null) {
			finishingEndPredecessorSemanticEnd = (Element)finishingEndPredecessor.getSemanticEnd();
		}
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
		createSynchronousMessage(interaction, source, target, true, startingEndPredecessorSemanticEnd,
				finishingEndPredecessorSemanticEnd, operation);
	}

	/**
	 * Get type associated to a lifeline.
	 * 
	 * @param target
	 *            Lifeline
	 * @return Type
	 */
	private Type getType(Lifeline target) {
		if (target.getRepresents() != null) {
			return target.getRepresents().getType();
		}

		if (target.getClientDependencies() != null && !target.getClientDependencies().isEmpty()) {
			return ((InstanceSpecification)target.getClientDependencies().get(0).getSuppliers().get(0))
					.getClassifiers().get(0);
		}
		return null;

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
					new RefreshSemanticOrderingsOperation(diagram.getSequenceDDiagram()).execute();
					new SynchronizeGraphicalOrderingOperation(sdep.getDiagramView(), false).execute();
				}
			}
		}
	}

	public Object getFinishingEnd(final CombinedFragment combinedFragment) {

		if (combinedFragment.getOperands().isEmpty()) {
			return combinedFragment;
		} else {
			InteractionOperand lastOperand = combinedFragment.getOperands().get(
					combinedFragment.getOperands().size() - 1);
			return getFinishingEnd(lastOperand);
		}

	}

	public Element getFinishingEnd(final InteractionOperand interactionOperand) {

		for (Element element : interactionOperand.allOwnedElements()) {
			if (element instanceof Comment) {
				return element;
			}
		}

		return null;
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
	 * @param operation
	 *            Operation to be associated with the message
	 */
	public void createAsynchronousMessage(Interaction interaction, NamedElement sourceFragment,
			NamedElement targetFragment, NamedElement startingEndPredecessor,
			NamedElement finishingEndPredecessor, Operation operation) {
		createAsynchronousMessage(interaction, sourceFragment, targetFragment, false, startingEndPredecessor,
				finishingEndPredecessor, operation);
	}

	public void createAsynchronousMessageWithExecution(Interaction interaction, NamedElement sourceFragment,
			NamedElement targetFragment, EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor,
			Operation operation) {
		Element startingEndPredecessorSemanticEnd = null;
		Element finishingEndPredecessorSemanticEnd = null;
		if (startingEndPredecessor != null) {
			startingEndPredecessorSemanticEnd = (Element)startingEndPredecessor.getSemanticEnd();
		}
		if (finishingEndPredecessor != null) {
			finishingEndPredecessorSemanticEnd = (Element)finishingEndPredecessor.getSemanticEnd();
		}

		createAsynchronousMessage(interaction, sourceFragment, targetFragment, true,
				startingEndPredecessorSemanticEnd, finishingEndPredecessorSemanticEnd, operation);
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

		MessageOccurrenceSpecification senderEventMessage = (MessageOccurrenceSpecification)message
				.getSendEvent();
		MessageOccurrenceSpecification receiverEventMessage = (MessageOccurrenceSpecification)message
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

	private BehaviorExecutionSpecification createExecution(/* Operation operation, */
	final Lifeline covered, final Message message) {
		final UMLFactory factory = UMLFactory.eINSTANCE;
		final OpaqueBehavior behavior = factory.createOpaqueBehavior();

		behavior.setName(message.getName());
		// behavior.setSpecification(operation);

		BehaviorExecutionSpecification execution = factory.createBehaviorExecutionSpecification();
		execution.setName(message.getName());
		execution.getCovereds().add(covered);
		execution.setBehavior(behavior);

		return execution;
	}

	private Message createAsynchronousMessage(Interaction interaction, boolean createOperation,
			NamedElement sourceFragment, NamedElement targetFragment, Operation operation) {
		final UMLFactory factory = UMLFactory.eINSTANCE;
		final Message message = factory.createMessage();

		final Lifeline source = getLifeline(sourceFragment);
		final Lifeline target = getLifeline(targetFragment);

		String messageName = "";
		if (operation == null) {
			messageName = "Message_" + Integer.toString(interaction.getMessages().size());
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
	 * Create a combined fragment
	 * 
	 * @param interaction
	 *            interaction
	 * @param startingEndPredecessor
	 *            start end predecessor
	 * @param finishingEndPredecessor
	 *            finish end predecessor
	 * @param coveredLifelines
	 *            lifelines covered by the combines fragment
	 */
	public void createCombinedFragment(Interaction interaction, InteractionFragment startingEndPredecessor,
			InteractionFragment finishingEndPredecessor, List<Lifeline> coveredLifelines) {

		final UMLFactory factory = UMLFactory.eINSTANCE;
		CombinedFragment combinedFragment = factory.createCombinedFragment();
		// combinedFragment.setName("");
		combinedFragment.getCovereds().addAll(coveredLifelines);

		int index = interaction.getFragments().indexOf(startingEndPredecessor) + 1;
		interaction.getFragments().add(index, combinedFragment);

		InteractionOperand defaultOperand = factory.createInteractionOperand();
		// defaultOperand.setName("true");
		defaultOperand.getCovereds().addAll(coveredLifelines);
		combinedFragment.getOperands().add(defaultOperand);

		// TODO refactor the next lines when the way to know the end of a CombinedFragment is found
		Comment endCombinedFragment = factory.createComment();
		endCombinedFragment.setBody("endCF");
		defaultOperand.getOwnedComments().add(endCombinedFragment);

	}

	/**
	 * Create en operand in a combine fragment
	 * 
	 * @param combinedFragment
	 *            combined fragment
	 */
	public void createOperand(CombinedFragment combinedFragment) {
		InteractionOperand operand = UMLFactory.eINSTANCE.createInteractionOperand();
		// TODO : remove this comment when fix CF finishing end
		Comment endOfOperand = UMLFactory.eINSTANCE.createComment();
		endOfOperand.setBody("end of Operand");
		operand.getOwnedComments().add(endOfOperand);

		combinedFragment.getOperands().add(operand);
	}

	/**
	 * reorder lifelines horizontally
	 * 
	 * @param movedLifeline
	 *            moved lifeline
	 * @param predecessorBefore
	 *            lifeline predecessor before
	 * @param predecessorAfter
	 *            lifeline predecessor after
	 */
	public void reorderLifeline(Lifeline movedLifeline, Lifeline predecessorBefore, Lifeline predecessorAfter) {
		Interaction ownedInteraction = movedLifeline.getInteraction();
		EList<Lifeline> lifelines = ownedInteraction.getLifelines();
		final int movedLifelineIndex = lifelines.indexOf(movedLifeline);
		if (predecessorAfter != null) {
			final int predecessorAfterIndex = lifelines.indexOf(predecessorAfter);
			if (movedLifelineIndex > predecessorAfterIndex) {
				// Moved from the right to the left
				lifelines.move(predecessorAfterIndex + 1, movedLifeline);
				return;
			}
			if (movedLifelineIndex < predecessorAfterIndex) {
				// Moved from the left to the right
				lifelines.move(predecessorAfterIndex, movedLifeline);
				return;
			}
		} else {
			// moved at the beginning
			lifelines.move(0, movedLifeline);
		}
	}

	/**
	 * return the ordered list of all ends of an interaction
	 * 
	 * @param interaction
	 *            interaction
	 * @return the ordered list of all ends
	 */
	public List<EObject> getEndsOrdering(Interaction interaction) {

		List<EObject> result = new BasicEList<EObject>();

		Iterator<EObject> eObjects = interaction.eAllContents();
		while (eObjects.hasNext()) {
			EObject eObject = (EObject)eObjects.next();
			if (eObject instanceof InteractionFragment) {
				result.add(eObject);
			}
			if (eObject instanceof CombinedFragment) {
				result.add(eObject);
			}

			// TODO: remove/adapt when combine fragment end element is defined
			if (eObject instanceof Comment) {
				result.add(eObject);
			}
			if (eObject instanceof InteractionOperand) {
				InteractionOperand interactionOperand = (InteractionOperand)eObject;
				List<EObject> tempList = getOrderedInteractionOperandElements(interactionOperand);
				for (int i = 0; i < tempList.size(); i++) {
					eObjects.next();
				}
				result.addAll(tempList);
			}
		}

		return result;
	}

	private List<EObject> getOrderedInteractionOperandElements(InteractionOperand interactionOperand) {
		List<EObject> result = new BasicEList<EObject>();

		Iterator<EObject> eObjects = interactionOperand.eAllContents();
		Comment lastElement = null;

		while (eObjects.hasNext()) {
			EObject eObject = (EObject)eObjects.next();
			if (eObject instanceof Comment) {
				lastElement = (Comment)eObject;
			} else {
				result.add(eObject);
			}

		}

		if (lastElement != null) {
			result.add(lastElement);
		}

		return result;

	}

	/**
	 * Get all the operation contained in the class (or actor) represented by a lifeline
	 * 
	 * @param lifeline
	 *            lifeline
	 * @return a list of operations
	 */
	public List<Operation> getAllOperations(Lifeline lifeline) {
		List<Operation> result = new BasicEList<Operation>();

		if (null != lifeline.getRepresents()) {
			ConnectableElement element = lifeline.getRepresents();
			Type typedElement = element.getType();

			if (typedElement instanceof Class) {
				Class umlClass = (Class)typedElement;
				result.addAll(umlClass.getOwnedOperations());
			} else if (typedElement instanceof Actor) {
				Actor actor = (Actor)typedElement;
				result.addAll(actor.getAllOperations());
			}
		}

		return result;
	}

	/**
	 * Get all the operation contained in the class (or actor) represented by the lifeline covered by the
	 * execution
	 * 
	 * @param execution
	 *            execution
	 * @return a list of operations
	 */
	public List<Operation> getAllOperations(BehaviorExecutionSpecification execution) {
		List<Operation> result = new BasicEList<Operation>();
		Lifeline lifeline = execution.getCovereds().get(0);

		if (null != lifeline.getRepresents()) {
			ConnectableElement element = lifeline.getRepresents();
			Type typedElement = element.getType();

			if (typedElement instanceof Class) {
				Class umlClass = (Class)typedElement;
				result.addAll(umlClass.getOwnedOperations());
			} else if (typedElement instanceof Actor) {
				Actor actor = (Actor)typedElement;
				result.addAll(actor.getAllOperations());
			}
		}

		return result;
	}

	/**
	 * Get all the creation message of an interaction
	 * 
	 * @param interaction
	 *            interaction
	 * @return the creation messages contained in an interaction
	 */
	public List<Message> getCreationMessages(Interaction interaction) {
		List<Message> result = new BasicEList<Message>();

		for (Message message : interaction.getMessages()) {
			if (message.getMessageSort().equals(MessageSort.CREATE_MESSAGE)) {
				result.add(message);
			}
		}

		return result;
	}

	/**
	 * Get synchronous message (and reply) and asynchronous message contained in an interaction
	 * 
	 * @param interaction
	 *            interaction
	 * @return list of messages
	 */
	public List<Message> getSynchAndAsynchMessages(Interaction interaction) {
		List<Message> result = new BasicEList<Message>();

		for (Message message : interaction.getMessages()) {
			if (message.getMessageSort().equals(MessageSort.SYNCH_CALL)
					|| message.getMessageSort().equals(MessageSort.ASYNCH_CALL)
					|| message.getMessageSort().equals(MessageSort.REPLY)) {
				result.add(message);
			}
		}

		return result;
	}

	public boolean isRepresentingProperty(Lifeline element) {
		// [getRepresents()<>null/]
		return element.getRepresents() != null;
	}

	public List<Dependency> getClientDependencies(Lifeline element) {
		// [lifeline.oclAsType(uml::Lifeline).clientDependencies/]
		return element.getClientDependencies();
	}

	public boolean isValidMessageEnd(Element element) {
		// [preTarget->filter(uml::Lifeline).represents.type<>null or
		// preTarget->filter(uml::ExecutionSpecification).covered.represents.type<>null or
		// preTarget->filter(uml::Lifeline).clientDependency.supplier.oclAsType(uml::Property).classifier<>null
		// or
		// preTarget->filter(uml::ExecutionSpecification).covered.clientDependency.supplier.classifier<>null/]
		return (element instanceof Lifeline && ((Lifeline)element).getRepresents() != null)
				|| (element instanceof ExecutionSpecification && isCoveredTypeSet(((ExecutionSpecification)element)))
				|| (element instanceof Lifeline && ((Lifeline)element).getRepresents() != null);
	}

	private boolean isCoveredTypeSet(ExecutionSpecification element) {
		if (element == null)
			return false;
		for (Lifeline covered : element.getCovereds()) {
			ConnectableElement connectedElement = covered.getRepresents();
			if (connectedElement.getTemplateParameter() != null)
				return true;
		}
		return false;
	}

	private boolean isPropertyClassifierSet(Lifeline element) {
		if (element == null)
			return false;
		for (Dependency dependency : element.getClientDependencies()) {
			for (NamedElement supplier : dependency.getSuppliers()) {
				if (supplier instanceof Property) {
					return ((Property)supplier).getClass_() != null;
				}
			}
		}
		return false;
	}

	public boolean isValidMessageEnd(EObject preTarget) {
		if (preTarget == null) {
			return false;
		}

		if (preTarget instanceof Lifeline) {
			return isValidMessageEndForLifeline(preTarget);

		} else if (preTarget instanceof ExecutionSpecification) {
			for (Lifeline lifeline : ((ExecutionSpecification)preTarget).getCovereds()) {
				boolean result = isValidMessageEndForLifeline(lifeline);
				if (result) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isValidMessageEndForLifeline(EObject preTarget) {
		ConnectableElement element = ((Lifeline)preTarget).getRepresents();
		if (element != null && element.getType() != null) {
			return true;
		}
		List<Dependency> dependencies = ((Lifeline)preTarget).getClientDependencies();
		for (Dependency dependency : dependencies) {
			for (NamedElement supplier : dependency.getSuppliers()) {
				if (supplier.getClass() != null) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isSynchCall(Message message) {
		if (message == null)
			return false;
		return MessageSort.ASYNCH_CALL_LITERAL.equals(message.getMessageSort());
	}

	public boolean isReply(Message message) {
		if (message == null)
			return false;
		return MessageSort.REPLY_LITERAL.equals(message.getMessageSort());
	}

	public boolean isNotReply(Message message) {
		return !isReply(message);
	}

	public Set<EObject> getEndsOrdering(Interaction interaction, List<EObject> eventEnds) {
		Set<EObject> endsOrderingSet = ImmutableSet.copyOf(getEndsOrdering(interaction));
		Set<EObject> eventEndsSet = ImmutableSet.copyOf(eventEnds);
		Sets.SetView<EObject> intersection = Sets.intersection(endsOrderingSet, eventEndsSet);
		return intersection;
	}

	public void reorderFragment(Element fragment, EventEnd startingEndPredecessorAfter,
			EventEnd finishingEndPredecessorAfter) {
		InteractionFragment startingEndPredecessorAfterSemanticEnd = (InteractionFragment)startingEndPredecessorAfter
				.getSemanticEnd();
		InteractionFragment finishingEndPredecessorAfterSemanticEnd = (InteractionFragment)finishingEndPredecessorAfter
				.getSemanticEnd();
		if (fragment instanceof CombinedFragment) {
			reorder((CombinedFragment)fragment, startingEndPredecessorAfterSemanticEnd,
					finishingEndPredecessorAfterSemanticEnd);
		} else if (fragment instanceof ExecutionSpecification) {
			reorder((ExecutionSpecification)fragment, startingEndPredecessorAfterSemanticEnd,
					finishingEndPredecessorAfterSemanticEnd);
		} else if (fragment instanceof Message) {
			reorder((Message)fragment, startingEndPredecessorAfterSemanticEnd,
					finishingEndPredecessorAfterSemanticEnd);
		}
	}

	/**
	 * Retrieve the nearest package associated to a lifeline. Method used to find the class diagram package.
	 * 
	 * @param lifeline
	 *            Lifeline
	 * @return Class diagram package
	 */
	public Package getClassDiagramFromLifeline(Lifeline lifeline) {
		if (lifeline != null) {
			ConnectableElement element = lifeline.getRepresents();
			if (element != null) {
				Type type = element.getType();
				if (type != null) {
					return type.getPackage();
				}
			}
		}
		return null;
	}

	/**
	 * Retrieve the nearest package associated to an execution. Method used to find the class diagram package.
	 * 
	 * @param execution
	 *            Execution
	 * @return Class diagram package
	 */
	public Package getClassDiagramFromExecution(BehaviorExecutionSpecification execution) {
		if (execution != null) {
			Behavior behavior = execution.getBehavior();
			if (behavior != null) {
				BehavioralFeature feature = behavior.getSpecification();
				if (feature != null) {
					return feature.getNearestPackage();
				}
			}
		}
		return null;
	}

	/**
	 * Compute lifeline comment label.
	 * 
	 * @param lifeline
	 *            Lifeline
	 * @return LAbel
	 */
	public String computeLifelineCommentLabel(Lifeline lifeline) {
		ConnectableElement represent = lifeline.getRepresents();
		// ['current container :
		// '+self.oclAsType(uml::Lifeline).represents.eContainer().oclAsType(uml::NamedElement).name/]
		EList<Dependency> dependencies = lifeline.getClientDependencies();

		if (represent != null) {
			EObject container = represent.eContainer();
			if (dependencies.size() == 0) {
				if (container != null && container instanceof NamedElement) {
					return "current container : " + ((NamedElement)container).getName();
				}
			} else {
				// ['current container : '+self.oclAsType(uml::Lifeline).represents.eContainer().name+'\n
				// context dependency:
				// '+self.oclAsType(uml::Lifeline).clientDependency.supplier.name->sep('::')/]
				if (container != null && container instanceof NamedElement) {
					EList<NamedElement> suppliers = dependencies.get(0).getSuppliers();
					if (suppliers != null && suppliers.size() > 0) {
						EObject supplier = suppliers.get(0);
						if (supplier != null && supplier instanceof NamedElement)
							return "current container : " + ((NamedElement)container).getName()
									+ " context dependency: " + ((NamedElement)supplier).getName();
					}
				}
			}
		} else {
			if (dependencies != null) {
				EList<NamedElement> suppliers = dependencies.get(0).getSuppliers();
				if (dependencies.size() > 1) {
					// ['context dependency:
					// '+self.oclAsType(uml::Lifeline).clientDependency.supplier.name->sep('::')/]
					if (suppliers != null && suppliers.size() > 0) {
						EObject supplier = suppliers.get(0);
						if (supplier != null && supplier instanceof NamedElement)
							return "context dependency: " + ((NamedElement)supplier).getName();
					}
				} else if (dependencies.size() == 1) {
					// ['current container :
					// '+self.oclAsType(uml::Lifeline).clientDependency.supplier.eContainer().name/]
					if (suppliers != null && suppliers.size() > 0) {
						EObject supplier = suppliers.get(0);
						if (supplier != null) {
							EObject container = supplier.eContainer();
							if (container != null && container instanceof NamedElement) {
								return "current container : " + ((NamedElement)container).getName();
							}
						}
					}
				}
			}
		}
		return null;
	}
}
