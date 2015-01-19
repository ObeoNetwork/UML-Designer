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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
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
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OccurrenceSpecification;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.internal.services.LabelServices;
import org.obeonetwork.dsl.uml2.design.internal.services.LogServices;
import org.obeonetwork.dsl.uml2.design.internal.services.OperationServices;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

/**
 * A set of services to handle the Sequence diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class SequenceDiagramServices extends AbstractDiagramServices {

	private final org.obeonetwork.dsl.uml2.design.internal.services.SequenceServices internalService = new org.obeonetwork.dsl.uml2.design.internal.services.SequenceServices();

	/**
	 * Compute lifeline comment label.
	 *
	 * @param lifeline
	 *            Lifeline
	 * @return LAbel
	 */
	public String computeLifelineCommentLabel(Lifeline lifeline) {
		final ConnectableElement represent = lifeline.getRepresents();
		// ['current container :
		// '+self.oclAsType(uml::Lifeline).represents.eContainer().oclAsType(uml::NamedElement).name/]
		final EList<Dependency> dependencies = lifeline.getClientDependencies();

		if (represent != null) {
			final EObject container = represent.eContainer();
			if (dependencies.size() == 0) {
				if (container != null && container instanceof NamedElement) {
					return "current container : " + ((NamedElement)container).getName(); //$NON-NLS-1$
				}
			} else {
				// ['current container : '+self.oclAsType(uml::Lifeline).represents.eContainer().name+'\n
				// context dependency:
				// '+self.oclAsType(uml::Lifeline).clientDependency.supplier.name->sep('::')/]
				if (container != null && container instanceof NamedElement) {
					final EList<NamedElement> suppliers = dependencies.get(0).getSuppliers();
					if (suppliers != null && suppliers.size() > 0) {
						final EObject supplier = suppliers.get(0);
						if (supplier != null && supplier instanceof NamedElement) {
							return "current container : " + ((NamedElement)container).getName() //$NON-NLS-1$
									+ " context dependency: " + ((NamedElement)supplier).getName(); //$NON-NLS-1$
						}
					}
				}
			}
		} else {
			if (dependencies != null) {
				final EList<NamedElement> suppliers = dependencies.get(0).getSuppliers();
				if (dependencies.size() > 1) {
					// ['context dependency:
					// '+self.oclAsType(uml::Lifeline).clientDependency.supplier.name->sep('::')/]
					if (suppliers != null && suppliers.size() > 0) {
						final EObject supplier = suppliers.get(0);
						if (supplier != null && supplier instanceof NamedElement) {
							return "context dependency: " + ((NamedElement)supplier).getName(); //$NON-NLS-1$
						}
					}
				} else if (dependencies.size() == 1) {
					// ['current container :
					// '+self.oclAsType(uml::Lifeline).clientDependency.supplier.eContainer().name/]
					if (suppliers != null && suppliers.size() > 0) {
						final EObject supplier = suppliers.get(0);
						if (supplier != null) {
							final EObject container = supplier.eContainer();
							if (container != null && container instanceof NamedElement) {
								return "current container : " + ((NamedElement)container).getName(); //$NON-NLS-1$
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Create asynchronous message with execution.
	 *
	 * @param interaction
	 *            Interaction
	 * @param sourceFragment
	 *            Source fragment
	 * @param targetFragment
	 *            Target fragment
	 * @param startingEndPredecessor
	 *            Starting event
	 * @param finishingEndPredecessor
	 *            Finishing event
	 * @param operation
	 *            Operation
	 */
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

		internalService.createAsynchronousMessage(interaction, sourceFragment, targetFragment, true,
				startingEndPredecessorSemanticEnd, finishingEndPredecessorSemanticEnd, operation);
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
	 * @param element
	 *            Instance specification or property associated to lifeline
	 */
	public void createLifeline(Interaction interaction, NamedElement element) {
		// If the element selected in the selection wizard is not an instance specification or a property,
		// return a warning in error log view
		if (!(element instanceof org.eclipse.uml2.uml.Class) && !(element instanceof Property)) {
			LogServices.INSTANCE.warning(
					"An instance specification or a property must be selected to import a lifeline but you have selected " //$NON-NLS-1$
					+ element.getName() + " which is a " + element.getClass().getSimpleName(), null); //$NON-NLS-1$
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
		final Operation operation = OperationServices.INSTANCE.createOperation(type);
		// Create execution
		internalService.createExecution(execution.getEnclosingInteraction(), execution, operation,
				startingEndPredecessor);
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
		final Operation operation = OperationServices.INSTANCE.createOperation(type);

		// Create execution
		internalService.createExecution(lifeline.getInteraction(), lifeline, operation,
				startingEndPredecessor);
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
		final Operation operation = OperationServices.INSTANCE.createOperation(type);
		// Create message
		internalService.createAsynchronousMessage(interaction, source, target, true,
				startingEndPredecessorSemanticEnd, finishingEndPredecessorSemanticEnd, operation);
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
		final Operation operation = OperationServices.INSTANCE.createOperation(type);
		// Create message
		internalService.createSynchronousMessage(interaction, source, target, true,
				startingEndPredecessorSemanticEnd, finishingEndPredecessorSemanticEnd, operation);
	}

	/**
	 * Create synchronous message with execution.
	 *
	 * @param interaction
	 *            Interaction
	 * @param sourceFragment
	 *            Source fragment
	 * @param targetFragment
	 *            Target fragment
	 * @param startingEndPredecessor
	 *            Starting event
	 * @param finishingEndPredecessor
	 *            Finishing event
	 * @param operation
	 *            Operation
	 */
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

		internalService.createSynchronousMessage(interaction, sourceFragment, targetFragment, true,
				startingEndPredecessorSemanticEnd, finishingEndPredecessorSemanticEnd, operation);
	}

	/**
	 * Delete execution.
	 *
	 * @param execution
	 *            Execution to delete
	 */
	public void delete(BehaviorExecutionSpecification execution) {
		if (execution == null) {
			return;
		}

		// Get fragments
		final Interaction interaction = (Interaction)execution.eContainer();

		// Delete opaque behavior
		final Behavior behavior = execution.getBehavior();
		if (behavior != null) {
			interaction.getOwnedBehaviors().remove(behavior);
		}

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
	 * Delete lifeline.
	 *
	 * @param lifeline
	 *            Lifeline to delete
	 */
	public void delete(Lifeline lifeline) {
		if (lifeline == null) {
			return;
		}
		// Delete dependency
		deleteContext(lifeline);

		// Delete all executions
		for (final ExecutionSpecification execution : executionSemanticCandidates(lifeline)) {
			if (execution instanceof BehaviorExecutionSpecification) {
				delete((BehaviorExecutionSpecification)execution);
			}
		}

		// Delete all messages
		for (final Message message : getAllMessages(lifeline)) {
			delete(message);
		}

		// Delete lifeline
		lifeline.destroy();
	}

	/**
	 * Delete message.
	 *
	 * @param message
	 *            Message to delete
	 */
	public void delete(Message message) {
		if (message == null) {
			return;
		}

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
				final Message reply = internalService.getReplyMessage(message);
				if (reply != null) {
					delete(reply);
				}
			}

			final BehaviorExecutionSpecification execution = internalService.getExecution(receiveMessage);
			if (execution != null) {
				delete(execution);
			}
			fragments.remove(receiveMessage);
		}
		final MessageOccurrenceSpecification sendMessage = (MessageOccurrenceSpecification)message
				.getSendEvent();
		if (sendMessage != null) {
			final BehaviorExecutionSpecification execution = internalService.getExecution(sendMessage);
			if (execution != null) {
				delete(execution);
			}
			// Delete signal
			final List<PackageableElement> packagedElements = new ArrayList<PackageableElement>();
			packagedElements.addAll(message.getNearestPackage().getPackagedElements());
			for (final PackageableElement packageableElement : packagedElements) {
				if (packageableElement instanceof Signal) {
					final Signal signal = (Signal)packageableElement;
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
	 * Delete the client dependency used for the context.
	 *
	 * @param lifeline
	 *            the lifeline
	 */
	// add an eannotation
	private void deleteContext(Lifeline lifeline) {
		final Object[] dependencies = lifeline.getClientDependencies().toArray();

		for (int i = 0; i < dependencies.length; i++) {
			EcoreUtil.delete((Dependency)dependencies[i]);
		}

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
		for (final ExecutionSpecification subExecution : executions) {
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
		for (final ExecutionSpecification subExecution : executions) {
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
	public List<ExecutionSpecification> executionSemanticCandidates(ExecutionSpecification execution) {
		final List<InteractionFragment> fragments = execution.getEnclosingInteraction().getFragments();

		final int startIndex = fragments.indexOf(execution.getStart());
		final int finishIndex = fragments.indexOf(execution.getFinish());
		final List<InteractionFragment> candidateFragments = new ArrayList<InteractionFragment>(
				fragments.subList(startIndex + 2, finishIndex));
		return getFirstLevelExecutions(execution.getCovereds().get(0), candidateFragments);
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
	 * Find occurrence specification context for a receive event.
	 *
	 * @param message
	 *            Message
	 * @return Occurrence specification context
	 */
	public NamedElement findOccurrenceSpecificationContextForReceiveEvent(Message message) {
		return internalService.findOccurrenceSpecificationContext((OccurrenceSpecification)message
				.getReceiveEvent());
	}

	/**
	 * Find occurrence specification context for a send event.
	 *
	 * @param message
	 *            Message
	 * @return Occurrence specification context
	 */
	public NamedElement findOccurrenceSpecificationContextForSendEvent(Message message) {
		return internalService.findOccurrenceSpecificationContext((OccurrenceSpecification)message
				.getSendEvent());
	}

	/**
	 * Get all actors and their containers
	 *
	 * @param element
	 *            a model element
	 * @return all actors and their containers of the UML model
	 */
	public Set<Element> getAllActorsAndContainers(EObject element) {
		final Element rootContainer = (Element)EcoreUtil.getRootContainer(element);
		final Iterator<EObject> eObjects = rootContainer.eAllContents();

		final Set<Element> result = new HashSet<Element>();

		result.add(rootContainer);

		while (eObjects.hasNext()) {
			final Element eObject = (Element)eObjects.next();
			if (eObject instanceof Actor) {
				result.add(eObject);
				final Set<Element> containers = getAllContainers(eObject);
				result.addAll(containers);
			}
			if (eObject instanceof Property) {
				final Property property = (Property)eObject;
				if (property.getType() instanceof Actor) {
					result.add(property);
					final Set<Element> containers = getAllContainers(property);
					result.addAll(containers);
				}
			}
		}

		return result;
	}

	/**
	 * Get all classes and their containers
	 *
	 * @param element
	 *            a model element
	 * @return all classes and their containers of the UML model
	 */
	public Set<Element> getAllClassesAndContainers(Element element) {
		final Element rootContainer = (Element)EcoreUtil.getRootContainer(element);
		final Iterator<EObject> eObjects = rootContainer.eAllContents();

		final Set<Element> result = new HashSet<Element>();

		result.add(rootContainer);

		while (eObjects.hasNext()) {
			final Element eObject = (Element)eObjects.next();
			if (eObject instanceof org.eclipse.uml2.uml.Class && !(eObject instanceof OpaqueBehavior)) {
				result.add(eObject);
				final Set<Element> containers = getAllContainers(eObject);
				result.addAll(containers);
			}
			if (eObject instanceof Property) {
				final Property property = (Property)eObject;
				if (property.getType() instanceof org.eclipse.uml2.uml.Class) {
					result.add(property);
					final Set<Element> containers = getAllContainers(property);
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
		final Set<Element> result = new HashSet<Element>();

		while (container.eContainer() != null) {
			result.add(container);
			container = (Element)container.eContainer();
		}

		return result;
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
			for (final Message message : lifeline.getInteraction().getMessages()) {
				for (final Lifeline coveredLifeline : ((MessageOccurrenceSpecification)message.getSendEvent())
						.getCovereds()) {
					if (lifeline.equals(coveredLifeline)) {
						messages.add(message);
					}
				}
			}
		}
		return messages;
	}

	/**
	 * return the ordered list of all ends of an interaction
	 *
	 * @param interaction
	 *            interaction
	 * @return the ordered list of all ends
	 */
	public List<EObject> getEndsOrdering(Interaction interaction) {

		final List<EObject> result = new BasicEList<EObject>();

		final Iterator<EObject> eObjects = interaction.eAllContents();
		while (eObjects.hasNext()) {
			final EObject eObject = eObjects.next();
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
				final InteractionOperand interactionOperand = (InteractionOperand)eObject;
				final List<EObject> tempList = getOrderedInteractionOperandElements(interactionOperand);
				for (int i = 0; i < tempList.size(); i++) {
					eObjects.next();
				}
				result.addAll(tempList);
			}
		}

		return result;
	}

	/**
	 * Get ends ordering.
	 *
	 * @param interaction
	 *            Interaction
	 * @param eventEnds
	 *            Event ends
	 * @return Ends ordering
	 */
	public Set<EObject> getEndsOrdering(Interaction interaction, List<EObject> eventEnds) {
		final Set<EObject> endsOrderingSet = ImmutableSet.copyOf(getEndsOrdering(interaction));
		final Set<EObject> eventEndsSet = ImmutableSet.copyOf(eventEnds);
		final Sets.SetView<EObject> intersection = Sets.intersection(endsOrderingSet, eventEndsSet);
		return intersection;
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
		for (final InteractionFragment fragment : candidateFragments) {
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

	private List<EObject> getOrderedInteractionOperandElements(InteractionOperand interactionOperand) {
		final List<EObject> result = new BasicEList<EObject>();

		final Iterator<EObject> eObjects = interactionOperand.eAllContents();
		Comment lastElement = null;

		while (eObjects.hasNext()) {
			final EObject eObject = eObjects.next();
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
	 * Get sequence diagram label.
	 *
	 * @param interaction
	 *            Interaction associated to sequence diagram
	 * @return SEquence diagram label
	 */
	public String getSequenceDiagramName(Interaction interaction) {
		return LabelServices.INSTANCE.getSequenceDiagramName(interaction);
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
	 * Check if message is not a reply message.
	 *
	 * @param message
	 *            Message
	 * @return True if message is not a reply message
	 */
	public boolean isNotReply(Message message) {
		return !isReply(message);
	}

	/**
	 * Check if message is a reply message.
	 *
	 * @param message
	 *            Message
	 * @return True if message is a reply message
	 */
	public boolean isReply(Message message) {
		if (message == null) {
			return false;
		}
		return MessageSort.REPLY_LITERAL.equals(message.getMessageSort());
	}

	/**
	 * Check if lifeline is representing a property.
	 *
	 * @param element
	 *            Lifeline
	 * @return True iflifeline is representing a property
	 */
	public boolean isRepresentingProperty(Lifeline element) {
		// [getRepresents()<>null/]
		return element.getRepresents() != null;
	}

	/**
	 * Check if message is a synchronous call.
	 *
	 * @param message
	 *            Message
	 * @return True if message is a synchronous call
	 */
	public boolean isSynchCall(Message message) {
		if (message == null) {
			return false;
		}
		return MessageSort.ASYNCH_CALL_LITERAL.equals(message.getMessageSort());
	}

	/**
	 * Check if element is a valid message end.
	 *
	 * @param element
	 *            Element
	 * @return True if element is a valid message end
	 */
	public boolean isValidMessageEnd(Element element) {
		// [preTarget->filter(uml::Lifeline).represents.type<>null or
		// preTarget->filter(uml::ExecutionSpecification).covered.represents.type<>null or
		// preTarget->filter(uml::Lifeline).clientDependency.supplier.oclAsType(uml::Property).classifier<>null
		// or
		// preTarget->filter(uml::ExecutionSpecification).covered.clientDependency.supplier.classifier<>null/]
		return element instanceof Lifeline && ((Lifeline)element).getRepresents() != null
				|| element instanceof ExecutionSpecification
				&& internalService.isCoveredTypeSet((ExecutionSpecification)element)
				|| element instanceof Lifeline && ((Lifeline)element).getRepresents() != null;
	}

	/**
	 * Check if element is a valid message end.
	 *
	 * @param preTarget
	 *            Element
	 * @return True if element is a valid message end
	 */
	public boolean isValidMessageEnd(EObject preTarget) {
		if (preTarget == null) {
			return false;
		}

		if (preTarget instanceof Lifeline) {
			return isValidMessageEndForLifeline(preTarget);

		} else if (preTarget instanceof ExecutionSpecification) {
			for (final Lifeline lifeline : ((ExecutionSpecification)preTarget).getCovereds()) {
				final boolean result = isValidMessageEndForLifeline(lifeline);
				if (result) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isValidMessageEndForLifeline(EObject preTarget) {
		final ConnectableElement element = ((Lifeline)preTarget).getRepresents();
		if (element != null && element.getType() != null) {
			return true;
		}
		final List<Dependency> dependencies = ((Lifeline)preTarget).getClientDependencies();
		for (final Dependency dependency : dependencies) {
			for (final NamedElement supplier : dependency.getSuppliers()) {
				if (supplier.getClass() != null) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Reorder fragment.
	 *
	 * @param fragment
	 *            Fragment
	 * @param startingEndPredecessorAfter
	 *            Starting end predecessor after reorder
	 * @param finishingEndPredecessorAfter
	 *            Finishing end predecessor after reorder
	 */
	public void reorderFragment(Element fragment, EventEnd startingEndPredecessorAfter,
			EventEnd finishingEndPredecessorAfter) {
		final InteractionFragment startingEndPredecessorAfterSemanticEnd = (InteractionFragment)startingEndPredecessorAfter
				.getSemanticEnd();
		final InteractionFragment finishingEndPredecessorAfterSemanticEnd = (InteractionFragment)finishingEndPredecessorAfter
				.getSemanticEnd();
		if (fragment instanceof CombinedFragment) {
			internalService.reorder((CombinedFragment)fragment, startingEndPredecessorAfterSemanticEnd,
					finishingEndPredecessorAfterSemanticEnd);
		} else if (fragment instanceof ExecutionSpecification) {
			internalService.reorder((ExecutionSpecification)fragment, startingEndPredecessorAfterSemanticEnd,
					finishingEndPredecessorAfterSemanticEnd);
		} else if (fragment instanceof Message) {
			internalService.reorder((Message)fragment, startingEndPredecessorAfterSemanticEnd,
					finishingEndPredecessorAfterSemanticEnd);
		}
	}

	/**
	 * Reorder lifeline horizontally.
	 *
	 * @param movedLifeline
	 *            moved lifeline
	 * @param predecessorBefore
	 *            lifeline predecessor before
	 * @param predecessorAfter
	 *            lifeline predecessor after
	 */
	public void reorderLifeline(Lifeline movedLifeline, Lifeline predecessorBefore, Lifeline predecessorAfter) {
		final Interaction ownedInteraction = movedLifeline.getInteraction();
		final EList<Lifeline> lifelines = ownedInteraction.getLifelines();
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
}
