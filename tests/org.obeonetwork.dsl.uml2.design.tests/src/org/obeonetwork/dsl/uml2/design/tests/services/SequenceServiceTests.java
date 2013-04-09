// CHECKSTYLE:OFF
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
package org.obeonetwork.dsl.uml2.design.tests.services;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OccurrenceSpecification;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.obeonetwork.dsl.uml2.design.services.SequenceServices;
import org.obeonetwork.dsl.uml2.design.tests.Activator;

/**
 * Unit tests on sequence services.
 * 
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class SequenceServiceTests extends TestCase {
	/**
	 * The test model URI.
	 */
	private static final String RESOURCE_URI = Activator.PLUGIN_ID + "/resources/Test.uml";

	/**
	 * The sequence services instance.
	 */
	private SequenceServices sequenceServices;

	/**
	 * Get interaction defined in a resource.
	 * 
	 * @param resourceUri
	 *            URI
	 * @param name
	 *            Interaction name
	 * @return Interaction
	 */
	private Interaction getInteraction(String resourceUri, String name) {
		final ResourceSet rset = new ResourceSetImpl();
		final Resource resource = rset.getResource(URI.createPlatformPluginURI(resourceUri, true), true);

		return (Interaction)((Model)resource.getContents().get(0)).getOwnedMember(name);
	}

	/**
	 * Get interaction defined in a resource.
	 * 
	 * @param resourceUri
	 *            URI
	 * @param name
	 *            Interaction name
	 * @return Interaction
	 */
	private Interaction getDeleteInteraction(String resourceUri, String name) {
		final ResourceSet rset = new ResourceSetImpl();
		final Resource resource = rset.getResource(URI.createPlatformPluginURI(resourceUri, true), true);
		return (Interaction)((Package)((Package)((Model)resource.getContents().get(0))
				.getPackagedElement("Delete")).getOwnedMember(name)).getOwnedMember(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		sequenceServices = new SequenceServices();
	}

	/**
	 * Test the {@link SequenceServices#executionSemanticCandidates(Lifeline) executionSemanticCandidates}
	 * service against {@link Lifeline}.
	 */
	public void testExecutionSemanticCandidatesLifeline() {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_5");
		final Lifeline lifeline = interaction.getLifeline("consumers");
		final List<ExecutionSpecification> executions = sequenceServices
				.executionSemanticCandidates(lifeline);

		assertEquals(3, executions.size());
		assertEquals("compute", executions.get(0).getName());
		assertEquals("BehaviorExecution_2", executions.get(1).getName());
		assertEquals("BehaviorExecution_5", executions.get(2).getName());
	}

	/**
	 * Test the {@link SequenceServices#executionSemanticCandidates(ExecutionSpecification)
	 * executionSemanticCandidates} service against {@link ExecutionSpecification}.
	 */
	public void testExecutionSemanticCandidatesExecutionSpecification() {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_5");
		final BehaviorExecutionSpecification execution = (BehaviorExecutionSpecification)interaction
				.getFragment("compute");
		final List<ExecutionSpecification> subExecution = sequenceServices
				.executionSemanticCandidates(execution);

		assertEquals(1, subExecution.size());
		assertEquals("BehaviorExecution_1", subExecution.get(0).getName());

		final BehaviorExecutionSpecification execution2 = (BehaviorExecutionSpecification)interaction
				.getFragment("BehaviorExecution_2");
		final List<ExecutionSpecification> subExecution2 = sequenceServices
				.executionSemanticCandidates(execution2);
		assertEquals("BehaviorExecution_3", subExecution2.get(0).getName());
		assertEquals("BehaviorExecution_4", subExecution2.get(1).getName());
	}

	/**
	 * Test {@link SequenceServices#findOccurrenceSpecificationContext(OccurrenceSpecification)
	 * findOccurrenceSpecificationContext} service.
	 * 
	 * @throws Exception
	 *             in case of error.
	 */
	public void testFindOccurenceSpecificationContext() throws Exception {
		OccurrenceSpecification executionOccurrence;
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_6");
		executionOccurrence = (OccurrenceSpecification)interaction.getFragment("compute_start");
		assertEquals("consumers", sequenceServices.findOccurrenceSpecificationContext(executionOccurrence)
				.getName());
		executionOccurrence = (OccurrenceSpecification)interaction.getFragment("compute_finish");
		assertEquals("consumers", sequenceServices.findOccurrenceSpecificationContext(executionOccurrence)
				.getName());

		executionOccurrence = (OccurrenceSpecification)interaction.getFragment("get_receiver");
		assertEquals("get", sequenceServices.findOccurrenceSpecificationContext(executionOccurrence)
				.getName());

		executionOccurrence = (OccurrenceSpecification)interaction.getFragment("get_reply_sender");
		assertEquals("get", sequenceServices.findOccurrenceSpecificationContext(executionOccurrence)
				.getName());

		executionOccurrence = (OccurrenceSpecification)interaction.getFragment("get_reply_receiver");
		assertEquals("compute", sequenceServices.findOccurrenceSpecificationContext(executionOccurrence)
				.getName());

		executionOccurrence = (OccurrenceSpecification)interaction.getFragment("BehaviorExecution_3_start");
		assertEquals("compute", sequenceServices.findOccurrenceSpecificationContext(executionOccurrence)
				.getName());

		executionOccurrence = (OccurrenceSpecification)interaction.getFragment("produce_sender");
		assertEquals("BehaviorExecution_3",
				sequenceServices.findOccurrenceSpecificationContext(executionOccurrence).getName());
	}

	/**
	 * Test {@link SequenceServices#createExecution(Interaction, NamedElement, Operation, NamedElement)
	 * createExecution} service. Create a new execution on an empty lifeline.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateExecution() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_0");
		final Lifeline lifeline = interaction.getLifeline("consumers");
		sequenceServices.createExecution(interaction, lifeline, null);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(3, fragments.size());
		assertEquals("Opaque behavior does not exist", "BehaviorExecution_0", interaction.getOwnedBehaviors()
				.get(0).getName());
		assertTrue(fragments.get(0) instanceof ExecutionOccurrenceSpecification);
		assertEquals("BehaviorExecution_0_start", fragments.get(0).getName());
		assertTrue(fragments.get(2) instanceof ExecutionOccurrenceSpecification);
		assertEquals("BehaviorExecution_0_finish", fragments.get(2).getName());
		assertTrue(fragments.get(1) instanceof BehaviorExecutionSpecification);
		assertEquals("BehaviorExecution_0", fragments.get(1).getName());
		assertEquals(((BehaviorExecutionSpecification)fragments.get(1)).getStart(), fragments.get(0));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(1)).getFinish(), fragments.get(2));
		assertNotNull(((BehaviorExecutionSpecification)fragments.get(1)).getCovered(lifeline.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(0),
				((BehaviorExecutionSpecification)fragments.get(1)).getBehavior());
	}

	/**
	 * Test {@link SequenceServices#createExecution(Interaction, NamedElement, Operation, NamedElement)
	 * createExecution} service. Import an execution on an empty lifeline.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testImportExecution() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_0");
		final Lifeline lifeline = interaction.getLifeline("consumers");
		final Operation operation = (Operation)lifeline.getRepresents().getType().getOwnedElements().get(0);
		sequenceServices.createExecution(interaction, lifeline, operation, null);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(3, fragments.size());
		assertEquals("Opaque behavior does not exist", "compute", interaction.getOwnedBehaviors().get(0)
				.getName());
		assertTrue(fragments.get(0) instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_start", fragments.get(0).getName());
		assertTrue(fragments.get(2) instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_finish", fragments.get(2).getName());
		assertTrue(fragments.get(1) instanceof BehaviorExecutionSpecification);
		assertEquals("compute", fragments.get(1).getName());
		assertEquals(((BehaviorExecutionSpecification)fragments.get(1)).getStart(), fragments.get(0));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(1)).getFinish(), fragments.get(2));
		assertNotNull(((BehaviorExecutionSpecification)fragments.get(1)).getCovered(lifeline.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(0),
				((BehaviorExecutionSpecification)fragments.get(1)).getBehavior());
	}

	/**
	 * Test {@link SequenceServices#createExecution(Interaction, NamedElement, Operation, NamedElement)
	 * createExecution} service. Create an execution on an existing execution.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateExecutionOnExecution() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_1");
		final Lifeline lifeline = interaction.getLifeline("consumers");
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("compute");
		sequenceServices.createExecution(interaction, lifeline, execution);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(6, fragments.size());
		assertEquals("Opaque behavior does not exist", "BehaviorExecution_1", interaction.getOwnedBehaviors()
				.get(1).getName());
		assertTrue(fragments.get(2) instanceof ExecutionOccurrenceSpecification);
		assertEquals("BehaviorExecution_1_start", fragments.get(2).getName());
		assertTrue(fragments.get(4) instanceof ExecutionOccurrenceSpecification);
		assertEquals("BehaviorExecution_1_finish", fragments.get(4).getName());
		assertTrue(fragments.get(3) instanceof BehaviorExecutionSpecification);
		assertEquals("BehaviorExecution_1", fragments.get(3).getName());
		assertEquals(((BehaviorExecutionSpecification)fragments.get(3)).getStart(), fragments.get(2));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(3)).getFinish(), fragments.get(4));
		assertNotNull(((BehaviorExecutionSpecification)fragments.get(3)).getCovered(lifeline.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(1),
				((BehaviorExecutionSpecification)fragments.get(3)).getBehavior());
	}

	/**
	 * Test
	 * {@link SequenceServices#createAsynchronousMessage(Interaction, NamedElement, NamedElement, boolean, NamedElement, NamedElement, Operation)
	 * createAsynchronousMessage} service. Create an asynchronous message  (defined on an existing operation) between to empty lifelines.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateAsynchronousMessageWithExistingOperation() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_2");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		final Operation operation = (Operation)target.getRepresents().getType().getOwnedElements().get(0);

		sequenceServices.createAsynchronousMessage(interaction, source, target, true, null, null, operation);

		final List<InteractionFragment> fragments = interaction.getFragments();
		final List<Behavior> behaviors = interaction.getOwnedBehaviors();
		assertEquals(4, fragments.size());
		assertEquals(1, behaviors.size());
		assertEquals("Message does not exist", "produce", interaction.getMessages().get(0).getName());
		assertTrue(fragments.get(0) instanceof MessageOccurrenceSpecification);
		assertEquals("produce_sender", fragments.get(0).getName());
		assertTrue(fragments.get(1) instanceof MessageOccurrenceSpecification);
		assertEquals("produce_receiver", fragments.get(1).getName());
		assertTrue(fragments.get(2) instanceof BehaviorExecutionSpecification);
		assertEquals("produce", fragments.get(2).getName());
		assertTrue(fragments.get(3) instanceof ExecutionOccurrenceSpecification);
		assertEquals("produce_finish", fragments.get(3).getName());
		assertEquals(((Message)interaction.getMessages().get(0)).getSendEvent(), fragments.get(0));
		assertEquals(((Message)interaction.getMessages().get(0)).getReceiveEvent(), fragments.get(1));
		assertEquals(((Message)interaction.getMessages().get(0)).getMessageSort(),
				MessageSort.ASYNCH_CALL_LITERAL);
		assertEquals(((Message)interaction.getMessages().get(0)).getSignature(), operation);
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getStart(), fragments.get(1));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getFinish(), fragments.get(3));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getBehavior(), behaviors.get(0));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(0)).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(1)).getCovered(target.getName()));
		assertNotNull(((BehaviorExecutionSpecification)fragments.get(2)).getCovered(target.getName()));
	}

	/**
	 * Test
	 * {@link SequenceServices#createOperationAndAsynchMessage(NamedElement, NamedElement, NamedElement, NamedElement)
	 * createAsynchronousMessage} service. Create an asynchronous message and a new operation between to empty lifelines.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateAsynchronousMessageWithNewOperation() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_2");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		final org.eclipse.uml2.uml.Class targetClass = (Class)target.getRepresents().getType();

		sequenceServices.createOperationAndAsynchMessage(target, source, null, null);

		final List<InteractionFragment> fragments = interaction.getFragments();
		final List<Behavior> behaviors = interaction.getOwnedBehaviors();
		assertEquals(4, fragments.size());
		assertEquals(1, behaviors.size());
		assertEquals("Message does not exist", "Operation_3", interaction.getMessages().get(0).getName());
		assertTrue(fragments.get(0) instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_3_sender", fragments.get(0).getName());
		assertTrue(fragments.get(1) instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_3_receiver", fragments.get(1).getName());
		assertTrue(fragments.get(2) instanceof BehaviorExecutionSpecification);
		assertEquals("Operation_3", fragments.get(2).getName());
		assertTrue(fragments.get(3) instanceof ExecutionOccurrenceSpecification);
		assertEquals("Operation_3_finish", fragments.get(3).getName());
		assertEquals(((Message)interaction.getMessages().get(0)).getSendEvent(), fragments.get(0));
		assertEquals(((Message)interaction.getMessages().get(0)).getReceiveEvent(), fragments.get(1));
		assertEquals(((Message)interaction.getMessages().get(0)).getMessageSort(),
				MessageSort.ASYNCH_CALL_LITERAL);
		assertEquals("Operation does not exist", "Operation_3", targetClass.getOwnedOperations().get(2)
				.getName());
		assertEquals(((Message)interaction.getMessages().get(0)).getSignature(), targetClass
				.getOwnedOperations().get(2));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getStart(), fragments.get(1));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getFinish(), fragments.get(3));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getBehavior(), behaviors.get(0));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(0)).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(1)).getCovered(target.getName()));
		assertNotNull(((BehaviorExecutionSpecification)fragments.get(2)).getCovered(target.getName()));
	}

	/**
	 * Test
	 * {@link SequenceServices#createSynchronousMessage(Interaction, NamedElement, NamedElement, boolean, NamedElement, NamedElement, Operation)
	 * createSynchronousMessage} service. Create a synchronous message (defined on an existing operation) between to empty lifelines.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateSynchronousMessageWithExistingOperation() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_2");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		final Operation operation = (Operation)target.getRepresents().getType().getOwnedElements().get(0);

		sequenceServices.createSynchronousMessage(interaction, source, target, true, null, null, operation);

		final List<InteractionFragment> fragments = interaction.getFragments();
		final List<Behavior> behaviors = interaction.getOwnedBehaviors();

		assertEquals(5, fragments.size());
		// Message
		assertEquals("Message does not exist", "produce", interaction.getMessages().get(0).getName());
		assertTrue(fragments.get(0) instanceof MessageOccurrenceSpecification);
		assertEquals("produce_sender", fragments.get(0).getName());
		assertTrue(fragments.get(1) instanceof MessageOccurrenceSpecification);
		assertEquals("produce_receiver", fragments.get(1).getName());
		assertTrue(fragments.get(2) instanceof BehaviorExecutionSpecification);
		assertEquals("produce", fragments.get(2).getName());
		assertEquals(((Message)interaction.getMessages().get(0)).getSendEvent(), fragments.get(0));
		assertEquals(((Message)interaction.getMessages().get(0)).getReceiveEvent(), fragments.get(1));
		assertEquals(((Message)interaction.getMessages().get(0)).getMessageSort(),
				MessageSort.SYNCH_CALL_LITERAL);
		assertEquals(((Message)interaction.getMessages().get(0)).getSignature(), operation);
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getStart(), fragments.get(1));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getFinish(), fragments.get(3));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getBehavior(), behaviors.get(0));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(0)).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(1)).getCovered(target.getName()));
		assertNotNull(((BehaviorExecutionSpecification)fragments.get(2)).getCovered(target.getName()));

		// Reply message
		assertEquals("Message does not exist", "produce_reply", interaction.getMessages().get(1).getName());
		assertTrue(fragments.get(3) instanceof MessageOccurrenceSpecification);
		assertEquals("produce_reply_sender", fragments.get(3).getName());
		assertTrue(fragments.get(4) instanceof MessageOccurrenceSpecification);
		assertEquals("produce_reply_receiver", fragments.get(4).getName());
		assertEquals(((Message)interaction.getMessages().get(1)).getSendEvent(), fragments.get(3));
		assertEquals(((Message)interaction.getMessages().get(1)).getReceiveEvent(), fragments.get(4));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(3)).getCovered(target.getName()));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(4)).getCovered(source.getName()));
	}

	/**
	 * Test
	 * {@link SequenceServices#createOperationAndSynchMessage(NamedElement, NamedElement, NamedElement, NamedElement)
	 * createSynchronousMessage} service. Create a synchronous message and a new operation between to empty lifelines.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateSynchronousMessageWithNewOperation() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_2");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		final org.eclipse.uml2.uml.Class targetClass = (Class)target.getRepresents().getType();

		sequenceServices.createOperationAndSynchMessage(target, source, null, null);

		final List<InteractionFragment> fragments = interaction.getFragments();
		final List<Behavior> behaviors = interaction.getOwnedBehaviors();

		assertEquals(5, fragments.size());
		// Message
		assertEquals("Message does not exist", "Operation_3", interaction.getMessages().get(0).getName());
		assertTrue(fragments.get(0) instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_3_sender", fragments.get(0).getName());
		assertTrue(fragments.get(1) instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_3_receiver", fragments.get(1).getName());
		assertTrue(fragments.get(2) instanceof BehaviorExecutionSpecification);
		assertEquals("Operation_3", fragments.get(2).getName());
		assertEquals(((Message)interaction.getMessages().get(0)).getSendEvent(), fragments.get(0));
		assertEquals(((Message)interaction.getMessages().get(0)).getReceiveEvent(), fragments.get(1));
		assertEquals(((Message)interaction.getMessages().get(0)).getMessageSort(),
				MessageSort.SYNCH_CALL_LITERAL);
		assertEquals("Operation does not exist", "Operation_3", targetClass.getOwnedOperations().get(2)
				.getName());
		assertEquals(((Message)interaction.getMessages().get(0)).getSignature(), targetClass
				.getOwnedOperations().get(2));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getStart(), fragments.get(1));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getFinish(), fragments.get(3));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getBehavior(), behaviors.get(0));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(0)).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(1)).getCovered(target.getName()));
		assertNotNull(((BehaviorExecutionSpecification)fragments.get(2)).getCovered(target.getName()));

		// Reply message
		assertEquals("Message does not exist", "Operation_3_reply", interaction.getMessages().get(1)
				.getName());
		assertTrue(fragments.get(3) instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_3_reply_sender", fragments.get(3).getName());
		assertTrue(fragments.get(4) instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_3_reply_receiver", fragments.get(4).getName());
		assertEquals(((Message)interaction.getMessages().get(1)).getSendEvent(), fragments.get(3));
		assertEquals(((Message)interaction.getMessages().get(1)).getReceiveEvent(), fragments.get(4));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(3)).getCovered(target.getName()));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(4)).getCovered(source.getName()));
	}

	/**
	 * Test {@link SequenceServices#reorder(ExecutionSpecification, InteractionFragment, InteractionFragment)
	 * reorder} service. Reorder two executions on the same lifeline. Move the second execution on the first.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testReorderExecutions2() throws Exception {
		// Interaction Scenario_10
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_10");
		final Lifeline lifeline = interaction.getLifeline("producers");

		// Execution to move produce
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("produce");
		// StartingEndPredecessorAfter get_start execution occurrence
		final ExecutionOccurrenceSpecification startingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("get_start");
		// FinishingEndPredecessorAfter produce_start execution occurrence
		final ExecutionOccurrenceSpecification finishingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("produce_start");

		sequenceServices.reorder(execution, startingEndPredecessorAfter, finishingEndPredecessorAfter);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(12, fragments.size());

		final InteractionFragment getStart = fragments.get(0);
		final InteractionFragment get = fragments.get(1);
		final InteractionFragment produceStart = fragments.get(2);
		final InteractionFragment produce = fragments.get(3);
		final InteractionFragment produceFinish = fragments.get(4);
		final InteractionFragment getFinish = fragments.get(5);

		// Execution get
		final Behavior getBehavior = interaction.getOwnedBehaviors().get(0);
		assertEquals("Opaque behavior does not exist", "get", getBehavior.getName());
		assertTrue(getStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_start", getStart.getName());
		assertTrue(getFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_finish", getFinish.getName());
		assertTrue(get instanceof BehaviorExecutionSpecification);
		assertEquals("get", get.getName());
		assertEquals(((BehaviorExecutionSpecification)get).getStart(), getStart);
		assertEquals(((BehaviorExecutionSpecification)get).getFinish(), getFinish);
		assertNotNull(((BehaviorExecutionSpecification)get).getCovered(lifeline.getName()));
		assertEquals(getBehavior, ((BehaviorExecutionSpecification)get).getBehavior());

		// Execution produce
		final Behavior produceBehavior = interaction.getOwnedBehaviors().get(1);
		assertEquals("Opaque behavior does not exist", "produce", produceBehavior.getName());
		assertTrue(produceStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("produce_start", produceStart.getName());
		assertTrue(produceFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("produce_finish", produceFinish.getName());
		assertTrue(produce instanceof BehaviorExecutionSpecification);
		assertEquals("produce", produce.getName());
		assertEquals(((BehaviorExecutionSpecification)produce).getStart(), produceStart);
		assertEquals(((BehaviorExecutionSpecification)produce).getFinish(), produceFinish);
		assertNotNull(((BehaviorExecutionSpecification)produce).getCovered(lifeline.getName()));
		assertEquals(produceBehavior, ((BehaviorExecutionSpecification)produce).getBehavior());
	}

	/**
	 * Test {@link SequenceServices#reorder(ExecutionSpecification, InteractionFragment, InteractionFragment)
	 * reorder} service. Reorder two executions on the same lifeline. Move the third execution between the
	 * first end the second.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testReorderExecutions3() throws Exception {
		// Interaction Scenario_10
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_10");
		final Lifeline lifeline = interaction.getLifeline("producers");

		// Execution to move produce
		final ExecutionSpecification execution = (ExecutionSpecification)interaction
				.getFragment("BehaviorExecution_2");
		// StartingEndPredecessorAfter get_finish execution occurrence
		final ExecutionOccurrenceSpecification startingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("get_finish");
		// FinishingEndPredecessorAfter BehaviorExecution2_start execution occurrence
		final ExecutionOccurrenceSpecification finishingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("BehaviorExecution_2_start");

		sequenceServices.reorder(execution, startingEndPredecessorAfter, finishingEndPredecessorAfter);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(12, fragments.size());

		final InteractionFragment getStart = fragments.get(0);
		final InteractionFragment get = fragments.get(1);
		final InteractionFragment getFinish = fragments.get(2);
		final InteractionFragment behavior2Start = fragments.get(3);
		final InteractionFragment behavior2 = fragments.get(4);
		final InteractionFragment behavior2Finish = fragments.get(5);
		final InteractionFragment produceStart = fragments.get(6);
		final InteractionFragment produce = fragments.get(7);
		final InteractionFragment produceFinish = fragments.get(8);

		// Execution get
		final Behavior getBehavior = interaction.getOwnedBehaviors().get(0);
		assertEquals("Opaque behavior does not exist", "get", getBehavior.getName());
		assertTrue(getStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_start", getStart.getName());
		assertTrue(getFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_finish", getFinish.getName());
		assertTrue(get instanceof BehaviorExecutionSpecification);
		assertEquals("get", get.getName());
		assertEquals(((BehaviorExecutionSpecification)get).getStart(), getStart);
		assertEquals(((BehaviorExecutionSpecification)get).getFinish(), getFinish);
		assertNotNull(((BehaviorExecutionSpecification)get).getCovered(lifeline.getName()));
		assertEquals(getBehavior, ((BehaviorExecutionSpecification)get).getBehavior());

		// Execution behavior2
		final Behavior behavior2Behavior = interaction.getOwnedBehaviors().get(2);
		assertEquals("Opaque behavior does not exist", "BehaviorExecution_2", behavior2Behavior.getName());
		assertTrue(behavior2Start instanceof ExecutionOccurrenceSpecification);
		assertEquals("BehaviorExecution_2_start", behavior2Start.getName());
		assertTrue(behavior2Finish instanceof ExecutionOccurrenceSpecification);
		assertEquals("BehaviorExecution_2_finish", behavior2Finish.getName());
		assertTrue(behavior2 instanceof BehaviorExecutionSpecification);
		assertEquals("BehaviorExecution_2", behavior2.getName());
		assertEquals(((BehaviorExecutionSpecification)behavior2).getStart(), behavior2Start);
		assertEquals(((BehaviorExecutionSpecification)behavior2).getFinish(), behavior2Finish);
		assertNotNull(((BehaviorExecutionSpecification)behavior2).getCovered(lifeline.getName()));
		assertEquals(behavior2Behavior, ((BehaviorExecutionSpecification)behavior2).getBehavior());

		// Execution produce
		final Behavior produceBehavior = interaction.getOwnedBehaviors().get(1);
		assertEquals("Opaque behavior does not exist", "produce", produceBehavior.getName());
		assertTrue(produceStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("produce_start", produceStart.getName());
		assertTrue(produceFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("produce_finish", produceFinish.getName());
		assertTrue(produce instanceof BehaviorExecutionSpecification);
		assertEquals("produce", produce.getName());
		assertEquals(((BehaviorExecutionSpecification)produce).getStart(), produceStart);
		assertEquals(((BehaviorExecutionSpecification)produce).getFinish(), produceFinish);
		assertNotNull(((BehaviorExecutionSpecification)produce).getCovered(lifeline.getName()));
		assertEquals(produceBehavior, ((BehaviorExecutionSpecification)produce).getBehavior());
	}

	/**
	 * Test {@link SequenceServices#reorder(ExecutionSpecification, InteractionFragment, InteractionFragment)
	 * reorder} service. Reorder two executions on different lifelines. Move the third execution after the
	 * fourth.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testReorderExecutions4() throws Exception {
		// Interaction Scenario_10
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_10");
		final Lifeline producers = interaction.getLifeline("producers");
		final Lifeline consumers = interaction.getLifeline("consumers");

		// Execution to move produce
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("produce");
		// StartingEndPredecessorAfter compute_finish execution occurrence
		final ExecutionOccurrenceSpecification startingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("compute_finish");
		// FinishingEndPredecessorAfter produce_start execution occurrence
		final ExecutionOccurrenceSpecification finishingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("produce_start");

		sequenceServices.reorder(execution, startingEndPredecessorAfter, finishingEndPredecessorAfter);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(12, fragments.size());

		final InteractionFragment getStart = fragments.get(0);
		final InteractionFragment get = fragments.get(1);
		final InteractionFragment getFinish = fragments.get(2);
		final InteractionFragment behavior2Start = fragments.get(3);
		final InteractionFragment behavior2 = fragments.get(4);
		final InteractionFragment behavior2Finish = fragments.get(5);
		final InteractionFragment computeStart = fragments.get(6);
		final InteractionFragment compute = fragments.get(7);
		final InteractionFragment computeFinish = fragments.get(8);
		final InteractionFragment produceStart = fragments.get(9);
		final InteractionFragment produce = fragments.get(10);
		final InteractionFragment produceFinish = fragments.get(11);

		// Execution get
		final Behavior getBehavior = interaction.getOwnedBehaviors().get(0);
		assertEquals("Opaque behavior does not exist", "get", getBehavior.getName());
		assertTrue(getStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_start", getStart.getName());
		assertTrue(getFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_finish", getFinish.getName());
		assertTrue(get instanceof BehaviorExecutionSpecification);
		assertEquals("get", get.getName());
		assertEquals(((BehaviorExecutionSpecification)get).getStart(), getStart);
		assertEquals(((BehaviorExecutionSpecification)get).getFinish(), getFinish);
		assertNotNull(((BehaviorExecutionSpecification)get).getCovered(producers.getName()));
		assertEquals(getBehavior, ((BehaviorExecutionSpecification)get).getBehavior());

		// Execution behavior2
		final Behavior behavior2Behavior = interaction.getOwnedBehaviors().get(2);
		assertEquals("Opaque behavior does not exist", "BehaviorExecution_2", behavior2Behavior.getName());
		assertTrue(behavior2Start instanceof ExecutionOccurrenceSpecification);
		assertEquals("BehaviorExecution_2_start", behavior2Start.getName());
		assertTrue(behavior2Finish instanceof ExecutionOccurrenceSpecification);
		assertEquals("BehaviorExecution_2_finish", behavior2Finish.getName());
		assertTrue(behavior2 instanceof BehaviorExecutionSpecification);
		assertEquals("BehaviorExecution_2", behavior2.getName());
		assertEquals(((BehaviorExecutionSpecification)behavior2).getStart(), behavior2Start);
		assertEquals(((BehaviorExecutionSpecification)behavior2).getFinish(), behavior2Finish);
		assertNotNull(((BehaviorExecutionSpecification)behavior2).getCovered(producers.getName()));
		assertEquals(behavior2Behavior, ((BehaviorExecutionSpecification)behavior2).getBehavior());

		// Execution compute
		final Behavior computeBehavior = interaction.getOwnedBehaviors().get(3);
		assertEquals("Opaque behavior does not exist", "compute", computeBehavior.getName());
		assertTrue(computeStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_start", computeStart.getName());
		assertTrue(computeFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_finish", computeFinish.getName());
		assertTrue(compute instanceof BehaviorExecutionSpecification);
		assertEquals("compute", compute.getName());
		assertEquals(((BehaviorExecutionSpecification)compute).getStart(), computeStart);
		assertEquals(((BehaviorExecutionSpecification)compute).getFinish(), computeFinish);
		assertNotNull(((BehaviorExecutionSpecification)compute).getCovered(consumers.getName()));
		assertEquals(computeBehavior, ((BehaviorExecutionSpecification)compute).getBehavior());

		// Execution produce
		final Behavior produceBehavior = interaction.getOwnedBehaviors().get(1);
		assertEquals("Opaque behavior does not exist", "produce", produceBehavior.getName());
		assertTrue(produceStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("produce_start", produceStart.getName());
		assertTrue(produceFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("produce_finish", produceFinish.getName());
		assertTrue(produce instanceof BehaviorExecutionSpecification);
		assertEquals("produce", produce.getName());
		assertEquals(((BehaviorExecutionSpecification)produce).getStart(), produceStart);
		assertEquals(((BehaviorExecutionSpecification)produce).getFinish(), produceFinish);
		assertNotNull(((BehaviorExecutionSpecification)produce).getCovered(producers.getName()));
		assertEquals(produceBehavior, ((BehaviorExecutionSpecification)produce).getBehavior());
	}

	/**
	 * Test {@link SequenceServices#reorder(ExecutionSpecification, InteractionFragment, InteractionFragment)
	 * reorder} service. Reorder two executions on different lifelines. Move the second execution in order
	 * that executions are mixed, ie : first execution start > second execution start > first execution end >
	 * second execution end.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testReorderExecutions7() throws Exception {
		// Interaction Scenario_15
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_15");
		final Lifeline lifeline1 = interaction.getLifeline("Lifeline_1");
		final Lifeline lifeline2 = interaction.getLifeline("Lifeline_2");

		// Execution to move B
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("B");
		// StartingEndPredecessorAfter A_start execution occurrence
		final ExecutionOccurrenceSpecification startingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("A_start");
		// FinishingEndPredecessorAfter A_finish execution occurrence
		final ExecutionOccurrenceSpecification finishingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("A_finish");

		sequenceServices.reorder(execution, startingEndPredecessorAfter, finishingEndPredecessorAfter);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(6, fragments.size());

		final InteractionFragment aStart = fragments.get(0);
		final InteractionFragment a = fragments.get(1);
		final InteractionFragment bStart = fragments.get(2);
		final InteractionFragment b = fragments.get(3);
		final InteractionFragment aFinish = fragments.get(4);
		final InteractionFragment bFinish = fragments.get(5);

		// Execution B
		final Behavior bBehavior = interaction.getOwnedBehaviors().get(1);
		assertEquals("Opaque behavior does not exist", "B", bBehavior.getName());
		assertTrue(bStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("B_start", bStart.getName());
		assertTrue(bFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("B_finish", bFinish.getName());
		assertTrue(b instanceof BehaviorExecutionSpecification);
		assertEquals("B", b.getName());
		assertEquals(((BehaviorExecutionSpecification)b).getStart(), bStart);
		assertEquals(((BehaviorExecutionSpecification)b).getFinish(), bFinish);
		assertNotNull(((BehaviorExecutionSpecification)b).getCovered(lifeline2.getName()));
		assertEquals(bBehavior, ((BehaviorExecutionSpecification)b).getBehavior());

		// Execution A
		final Behavior aBehavior = interaction.getOwnedBehaviors().get(0);
		assertEquals("Opaque behavior does not exist", "A", aBehavior.getName());
		assertTrue(aStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("A_start", aStart.getName());
		assertTrue(aFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("A_finish", aFinish.getName());
		assertTrue(a instanceof BehaviorExecutionSpecification);
		assertEquals("A", a.getName());
		assertEquals(((BehaviorExecutionSpecification)a).getStart(), aStart);
		assertEquals(((BehaviorExecutionSpecification)a).getFinish(), aFinish);
		assertNotNull(((BehaviorExecutionSpecification)a).getCovered(lifeline1.getName()));
		assertEquals(aBehavior, ((BehaviorExecutionSpecification)a).getBehavior());
	}

	/**
	 * Test {@link SequenceServices#reorder(Message, InteractionFragment, InteractionFragment) reorder}
	 * service. Reorder two messages which as the smae name. Move the first get message after the second on
	 * the lifeline.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testReorderMessagesWithSameName() throws Exception {
		// Interaction Scenario_18
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_18");
		final Lifeline producers = interaction.getLifeline("producers");
		final Lifeline consumers = interaction.getLifeline("consumers");

		// Message to move get
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("get");
		// StartingEndPredecessorAfter compute_finish execution occurrence
		final ExecutionOccurrenceSpecification startingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("compute_finish");
		// FinishingEndPredecessorAfter null
		final MessageOccurrenceSpecification finishingEndPredecessorAfter = (MessageOccurrenceSpecification)interaction
				.getFragment("get_receiver");

		sequenceServices.reorder(execution, startingEndPredecessorAfter, finishingEndPredecessorAfter);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(18, fragments.size());

		final InteractionFragment produceSend = fragments.get(0);
		final InteractionFragment produceReceive = fragments.get(1);
		final InteractionFragment produce = fragments.get(2);
		final InteractionFragment produceReplySend = fragments.get(3);
		final InteractionFragment produceReplyReceive = fragments.get(4);
		final InteractionFragment computeStart = fragments.get(5);
		final InteractionFragment compute = fragments.get(6);
		final InteractionFragment getSend = fragments.get(7);
		final InteractionFragment getReceive = fragments.get(8);
		final InteractionFragment get = fragments.get(9);
		final InteractionFragment getReplySend = fragments.get(10);
		final InteractionFragment getReplyReceive = fragments.get(11);
		final InteractionFragment computeFinish = fragments.get(12);
		final InteractionFragment get2Send = fragments.get(13);
		final InteractionFragment get2Receive = fragments.get(14);
		final InteractionFragment get2 = fragments.get(15);
		final InteractionFragment get2ReplySend = fragments.get(16);
		final InteractionFragment get2ReplyReceive = fragments.get(17);

		// Message produce
		final Message produceMessage = interaction.getMessages().get(2);
		assertEquals("Message does not exist", "produce", produceMessage.getName());
		assertTrue(produceSend instanceof MessageOccurrenceSpecification);
		assertEquals("produce_sender", produceSend.getName());
		assertTrue(produceReceive instanceof MessageOccurrenceSpecification);
		assertEquals("produce_receiver", produceReceive.getName());
		assertEquals(produceMessage.getSendEvent(), produceSend);
		assertEquals(produceMessage.getReceiveEvent(), produceReceive);
		assertNotNull(((MessageOccurrenceSpecification)produceSend).getCovered(consumers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)produceReceive).getCovered(producers.getName()));

		// Execution produce
		final Behavior produceBehavior = interaction.getOwnedBehaviors().get(2);
		assertEquals("Opaque behavior does not exist", "produce", produceBehavior.getName());
		assertTrue(produceReceive instanceof MessageOccurrenceSpecification);
		assertEquals("produce_receiver", produceReceive.getName());
		assertTrue(produceReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("produce_reply_sender", produceReplySend.getName());
		assertTrue(produce instanceof BehaviorExecutionSpecification);
		assertEquals("produce", produce.getName());
		assertEquals(((BehaviorExecutionSpecification)produce).getStart(), produceReceive);
		assertEquals(((BehaviorExecutionSpecification)produce).getFinish(), produceReplySend);
		assertNotNull(((BehaviorExecutionSpecification)produce).getCovered(producers.getName()));
		assertEquals(produceBehavior, ((BehaviorExecutionSpecification)produce).getBehavior());

		// Message produce_reply
		final Message produceReplyMessage = interaction.getMessages().get(3);
		assertEquals("Message does not exist", "produce_reply", produceReplyMessage.getName());
		assertTrue(produceReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("produce_reply_sender", produceReplySend.getName());
		assertTrue(produceReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals("produce_reply_receiver", produceReplyReceive.getName());
		assertEquals(produceReplyMessage.getSendEvent(), produceReplySend);
		assertEquals(produceReplyMessage.getReceiveEvent(), produceReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)produceReplySend).getCovered(producers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)produceReplyReceive).getCovered(consumers.getName()));

		// Message get
		final Message getMessage = interaction.getMessages().get(0);
		assertEquals("Message does not exist", "get", getMessage.getName());
		assertTrue(getSend instanceof MessageOccurrenceSpecification);
		assertEquals("get_sender", getSend.getName());
		assertTrue(getReceive instanceof MessageOccurrenceSpecification);
		assertEquals("get_receiver", getReceive.getName());
		assertEquals(getMessage.getSendEvent(), getSend);
		assertEquals(getMessage.getReceiveEvent(), getReceive);
		assertNotNull(((MessageOccurrenceSpecification)getSend).getCovered(consumers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)getReceive).getCovered(producers.getName()));

		// Execution get
		final Behavior getBehavior = interaction.getOwnedBehaviors().get(1);
		assertEquals("Opaque behavior does not exist", "get", getBehavior.getName());
		assertTrue(getReceive instanceof MessageOccurrenceSpecification);
		assertEquals("get_receiver", getReceive.getName());
		assertTrue(getReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("get_reply_sender", getReplySend.getName());
		assertTrue(get instanceof BehaviorExecutionSpecification);
		assertEquals("get", get.getName());
		assertEquals(((BehaviorExecutionSpecification)get).getStart(), getReceive);
		assertEquals(((BehaviorExecutionSpecification)get).getFinish(), getReplySend);
		assertNotNull(((BehaviorExecutionSpecification)get).getCovered(producers.getName()));
		assertEquals(getBehavior, ((BehaviorExecutionSpecification)get).getBehavior());

		// Message get_reply
		final Message getReplyMessage = interaction.getMessages().get(1);
		assertEquals("Message does not exist", "get_reply", getReplyMessage.getName());
		assertTrue(getReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("get_reply_sender", getReplySend.getName());
		assertTrue(getReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals("get_reply_receiver", getReplyReceive.getName());
		assertEquals(getReplyMessage.getSendEvent(), getReplySend);
		assertEquals(getReplyMessage.getReceiveEvent(), getReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)getReplySend).getCovered(producers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)getReplyReceive).getCovered(consumers.getName()));

		// Message get2
		final Message get2Message = interaction.getMessages().get(4);
		assertEquals("Message does not exist", "get", get2Message.getName());
		assertTrue(get2Send instanceof MessageOccurrenceSpecification);
		assertEquals("get_sender", get2Send.getName());
		assertTrue(get2Receive instanceof MessageOccurrenceSpecification);
		assertEquals("get_receiver", get2Receive.getName());
		assertEquals(get2Message.getSendEvent(), get2Send);
		assertEquals(get2Message.getReceiveEvent(), get2Receive);
		assertNotNull(((MessageOccurrenceSpecification)get2Send).getCovered(consumers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)get2Receive).getCovered(producers.getName()));

		// Execution get
		final Behavior get2Behavior = interaction.getOwnedBehaviors().get(3);
		assertEquals("Opaque behavior does not exist", "get", getBehavior.getName());
		assertTrue(get2Receive instanceof MessageOccurrenceSpecification);
		assertEquals("get_receiver", getReceive.getName());
		assertTrue(get2ReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("get_reply_sender", get2ReplySend.getName());
		assertTrue(get instanceof BehaviorExecutionSpecification);
		assertEquals("get", get2.getName());
		assertEquals(((BehaviorExecutionSpecification)get2).getStart(), get2Receive);
		assertEquals(((BehaviorExecutionSpecification)get2).getFinish(), get2ReplySend);
		assertNotNull(((BehaviorExecutionSpecification)get2).getCovered(producers.getName()));
		assertEquals(get2Behavior, ((BehaviorExecutionSpecification)get2).getBehavior());

		// Message get_reply
		final Message get2ReplyMessage = interaction.getMessages().get(5);
		assertEquals("Message does not exist", "get_reply", get2ReplyMessage.getName());
		assertTrue(get2ReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("get_reply_sender", get2ReplySend.getName());
		assertTrue(get2ReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals("get_reply_receiver", get2ReplyReceive.getName());
		assertEquals(get2ReplyMessage.getSendEvent(), get2ReplySend);
		assertEquals(get2ReplyMessage.getReceiveEvent(), get2ReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)get2ReplySend).getCovered(producers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)get2ReplyReceive).getCovered(consumers.getName()));
	}

	/**
	 * Test {@link SequenceServices#reorder(Message, InteractionFragment, InteractionFragment) reorder}
	 * service. Reorder a message and an execution. Move the message from the lifeline to the execution.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testReorderMessageAndExecution() throws Exception {
		// Interaction Scenario_13
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_13");
		final Lifeline producers = interaction.getLifeline("producers");
		final Lifeline consumers = interaction.getLifeline("consumers");

		// Message to move Message_1
		final Message message = (Message)interaction.getMessage("Message_1");
		// StartingEndPredecessorAfter produce_start execution occurrence
		final ExecutionOccurrenceSpecification startingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("produce_start");
		// FinishingEndPredecessorAfter Message_1_sender message occurrence
		final MessageOccurrenceSpecification finishingEndPredecessorAfter = (MessageOccurrenceSpecification)interaction
				.getFragment("Message_1_sender");

		sequenceServices.reorder(message, startingEndPredecessorAfter, finishingEndPredecessorAfter);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(5, fragments.size());

		final InteractionFragment produceStart = fragments.get(0);
		final InteractionFragment produce = fragments.get(1);
		final InteractionFragment msg1Send = fragments.get(2);
		final InteractionFragment msg1Receive = fragments.get(3);
		final InteractionFragment produceFinish = fragments.get(4);

		// Execution produce
		final Behavior produceBehavior = interaction.getOwnedBehaviors().get(0);
		assertEquals("Opaque behavior does not exist", "produce", produceBehavior.getName());
		assertTrue(produceStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("produce_start", produceStart.getName());
		assertTrue(produceFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("produce_finish", produceFinish.getName());
		assertTrue(produce instanceof BehaviorExecutionSpecification);
		assertEquals("produce", produce.getName());
		assertEquals(((BehaviorExecutionSpecification)produce).getStart(), produceStart);
		assertEquals(((BehaviorExecutionSpecification)produce).getFinish(), produceFinish);
		assertNotNull(((BehaviorExecutionSpecification)produce).getCovered(producers.getName()));
		assertEquals(produceBehavior, ((BehaviorExecutionSpecification)produce).getBehavior());

		// Message Message_1
		final Message msg1 = interaction.getMessages().get(0);
		assertEquals("Message does not exist", "Message_1", msg1.getName());
		assertTrue(msg1Send instanceof MessageOccurrenceSpecification);
		assertEquals("Message_1_sender", msg1Send.getName());
		assertTrue(msg1Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_1_receiver", msg1Receive.getName());
		assertEquals(msg1.getSendEvent(), msg1Send);
		assertEquals(msg1.getReceiveEvent(), msg1Receive);
		assertNotNull(((MessageOccurrenceSpecification)msg1Send).getCovered(producers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg1Receive).getCovered(consumers.getName()));
	}

	/**
	 * Test {@link SequenceServices#reorder(Message, InteractionFragment, InteractionFragment) reorder}
	 * service. Reorder a message and an execution. Move the message from the lifeline to the execution.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testReorderMessageAndExecution2() throws Exception {
		// Interaction Scenario_14
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_14");
		final Lifeline producers = interaction.getLifeline("producers");
		final Lifeline consumers = interaction.getLifeline("consumers");

		// Execution to move Operation_1
		final ExecutionSpecification execution = (ExecutionSpecification)interaction
				.getFragment("Operation_1");
		// StartingEndPredecessorAfter Operation_2_finish execution occurrence
		final ExecutionOccurrenceSpecification startingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("Operation_2_finish");
		// FinishingEndPredecessorAfter Operation_1_receiver message occurrence
		final MessageOccurrenceSpecification finishingEndPredecessorAfter = (MessageOccurrenceSpecification)interaction
				.getFragment("Operation_1_receiver");

		sequenceServices.reorder(execution, startingEndPredecessorAfter, finishingEndPredecessorAfter);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(24, fragments.size());

		final InteractionFragment op0Send = fragments.get(0);
		final InteractionFragment op0Receive = fragments.get(1);
		final InteractionFragment op0 = fragments.get(2);
		final InteractionFragment op0Finish = fragments.get(3);
		final InteractionFragment testSend = fragments.get(4);
		final InteractionFragment testReceive = fragments.get(5);
		final InteractionFragment test = fragments.get(6);
		final InteractionFragment testReplySend = fragments.get(7);
		final InteractionFragment testReplyReceive = fragments.get(8);
		final InteractionFragment msg3Send = fragments.get(9);
		final InteractionFragment msg3Receive = fragments.get(10);
		final InteractionFragment msg3ReplySend = fragments.get(11);
		final InteractionFragment msg3ReplyReceive = fragments.get(12);
		final InteractionFragment msg7Send = fragments.get(13);
		final InteractionFragment msg7Receive = fragments.get(14);
		final InteractionFragment op2Send = fragments.get(15);
		final InteractionFragment op2Receive = fragments.get(16);
		final InteractionFragment op2 = fragments.get(17);
		final InteractionFragment op2Finish = fragments.get(18);
		final InteractionFragment op1Send = fragments.get(19);
		final InteractionFragment op1Receive = fragments.get(20);
		final InteractionFragment op1 = fragments.get(21);
		final InteractionFragment op1ReplySend = fragments.get(22);
		final InteractionFragment op1ReplyReceive = fragments.get(23);

		// Message Operation_0
		final Message op0Message = interaction.getMessages().get(0);
		assertEquals("Message does not exist", "Operation_0", op0Message.getName());
		assertTrue(op0Send instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_0_sender", op0Send.getName());
		assertTrue(op0Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_0_receiver", op0Receive.getName());
		assertEquals(op0Message.getSendEvent(), op0Send);
		assertEquals(op0Message.getReceiveEvent(), op0Receive);
		assertNotNull(((MessageOccurrenceSpecification)op0Send).getCovered(producers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)op0Receive).getCovered(consumers.getName()));

		// Execution Operation_0
		final Behavior op0Behavior = interaction.getOwnedBehaviors().get(0);
		assertEquals("Opaque behavior does not exist", "Operation_0", op0Behavior.getName());
		assertTrue(op0Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_0_receiver", op0Receive.getName());
		assertTrue(op0Finish instanceof ExecutionOccurrenceSpecification);
		assertEquals("Operation_0_finish", op0Finish.getName());
		assertTrue(op0 instanceof BehaviorExecutionSpecification);
		assertEquals("Operation_0", op0.getName());
		assertEquals(((BehaviorExecutionSpecification)op0).getStart(), op0Receive);
		assertEquals(((BehaviorExecutionSpecification)op0).getFinish(), op0Finish);
		assertNotNull(((BehaviorExecutionSpecification)op0).getCovered(consumers.getName()));
		assertEquals(op0Behavior, ((BehaviorExecutionSpecification)op0).getBehavior());

		// Message test
		final Message testMessage = interaction.getMessages().get(1);
		assertEquals("Message does not exist", "test", testMessage.getName());
		assertTrue(testSend instanceof MessageOccurrenceSpecification);
		assertEquals("test_sender", testSend.getName());
		assertTrue(testReceive instanceof MessageOccurrenceSpecification);
		assertEquals("test_receiver", testReceive.getName());
		assertEquals(testMessage.getSendEvent(), testSend);
		assertEquals(testMessage.getReceiveEvent(), testReceive);
		assertNotNull(((MessageOccurrenceSpecification)testSend).getCovered(producers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)testReceive).getCovered(consumers.getName()));

		// Execution test
		final Behavior testBehavior = interaction.getOwnedBehaviors().get(1);
		assertEquals("Opaque behavior does not exist", "test", testBehavior.getName());
		assertTrue(testReceive instanceof MessageOccurrenceSpecification);
		assertEquals("test_receiver", testReceive.getName());
		assertTrue(testReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("test_reply_sender", testReplySend.getName());
		assertTrue(test instanceof BehaviorExecutionSpecification);
		assertEquals("test", test.getName());
		assertEquals(((BehaviorExecutionSpecification)test).getStart(), testReceive);
		assertEquals(((BehaviorExecutionSpecification)test).getFinish(), testReplySend);
		assertNotNull(((BehaviorExecutionSpecification)test).getCovered(consumers.getName()));
		assertEquals(testBehavior, ((BehaviorExecutionSpecification)test).getBehavior());

		// Message test_reply
		final Message testReplyMessage = interaction.getMessages().get(2);
		assertEquals("Message does not exist", "test_reply", testReplyMessage.getName());
		assertTrue(testReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("test_reply_sender", testReplySend.getName());
		assertTrue(testReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals("test_reply_receiver", testReplyReceive.getName());
		assertEquals(testReplyMessage.getSendEvent(), testReplySend);
		assertEquals(testReplyMessage.getReceiveEvent(), testReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)testReplySend).getCovered(consumers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)testReplyReceive).getCovered(producers.getName()));

		// Message Message_3
		final Message msg3 = interaction.getMessages().get(3);
		assertEquals("Message does not exist", "Message_3", msg3.getName());
		assertTrue(msg3Send instanceof MessageOccurrenceSpecification);
		assertEquals("Message_3_sender", msg3Send.getName());
		assertTrue(msg3Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_3_receiver", msg3Receive.getName());
		assertEquals(msg3.getSendEvent(), msg3Send);
		assertEquals(msg3.getReceiveEvent(), msg3Receive);
		assertNotNull(((MessageOccurrenceSpecification)msg3Send).getCovered(producers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg3Receive).getCovered(consumers.getName()));

		// Message Message_3_reply
		final Message msg3Reply = interaction.getMessages().get(4);
		assertEquals("Message does not exist", "Message_3_reply", msg3Reply.getName());
		assertTrue(msg3ReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("Message_3_reply_sender", msg3ReplySend.getName());
		assertTrue(msg3ReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_3_reply_receiver", msg3ReplyReceive.getName());
		assertEquals(msg3Reply.getSendEvent(), msg3ReplySend);
		assertEquals(msg3Reply.getReceiveEvent(), msg3ReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)msg3ReplySend).getCovered(consumers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg3ReplyReceive).getCovered(producers.getName()));

		// Message Message_7
		final Message msg7 = interaction.getMessages().get(7);
		assertEquals("Message does not exist", "Message_7", msg7.getName());
		assertTrue(msg7Send instanceof MessageOccurrenceSpecification);
		assertEquals("Message_7_sender", msg7Send.getName());
		assertTrue(msg7Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_7_receiver", msg7Receive.getName());
		assertEquals(msg7.getSendEvent(), msg7Send);
		assertEquals(msg7.getReceiveEvent(), msg7Receive);
		assertNotNull(((MessageOccurrenceSpecification)msg7Send).getCovered(producers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg7Receive).getCovered(consumers.getName()));

		// Message Operation_2
		final Message op2Message = interaction.getMessages().get(8);
		assertEquals("Message does not exist", "Operation_2", op2Message.getName());
		assertTrue(op2Send instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_2_sender", op2Send.getName());
		assertTrue(op2Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_2_receiver", op2Receive.getName());
		assertEquals(op2Message.getSendEvent(), op2Send);
		assertEquals(op2Message.getReceiveEvent(), op2Receive);
		assertNotNull(((MessageOccurrenceSpecification)op2Send).getCovered(producers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)op2Receive).getCovered(consumers.getName()));

		// Execution Operation_2
		final Behavior op2Behavior = interaction.getOwnedBehaviors().get(3);
		assertEquals("Opaque behavior does not exist", "Operation_2", op2Behavior.getName());
		assertTrue(op2Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_2_receiver", op2Receive.getName());
		assertTrue(op2Finish instanceof ExecutionOccurrenceSpecification);
		assertEquals("Operation_2_finish", op2Finish.getName());
		assertTrue(op2 instanceof BehaviorExecutionSpecification);
		assertEquals("Operation_2", op2.getName());
		assertEquals(((BehaviorExecutionSpecification)op2).getStart(), op2Receive);
		assertEquals(((BehaviorExecutionSpecification)op2).getFinish(), op2Finish);
		assertNotNull(((BehaviorExecutionSpecification)op2).getCovered(consumers.getName()));
		assertEquals(op2Behavior, ((BehaviorExecutionSpecification)op2).getBehavior());

		// Message Operation_1
		final Message op1Message = interaction.getMessages().get(5);
		assertEquals("Message does not exist", "Operation_1", op1Message.getName());
		assertTrue(op1Send instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_1_sender", op1Send.getName());
		assertTrue(op1Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_1_receiver", op1Receive.getName());
		assertEquals(op1Message.getSendEvent(), op1Send);
		assertEquals(op1Message.getReceiveEvent(), op1Receive);
		assertNotNull(((MessageOccurrenceSpecification)op1Send).getCovered(producers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)op1Receive).getCovered(consumers.getName()));

		// Execution Operation_1
		final Behavior op1Behavior = interaction.getOwnedBehaviors().get(2);
		assertEquals("Opaque behavior does not exist", "Operation_1", op1Behavior.getName());
		assertTrue(op1Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_1_receiver", op1Receive.getName());
		assertTrue(op1ReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_1_reply_sender", op1ReplySend.getName());
		assertTrue(op1 instanceof BehaviorExecutionSpecification);
		assertEquals("Operation_1", op1.getName());
		assertEquals(((BehaviorExecutionSpecification)op1).getStart(), op1Receive);
		assertEquals(((BehaviorExecutionSpecification)op1).getFinish(), op1ReplySend);
		assertNotNull(((BehaviorExecutionSpecification)op1).getCovered(consumers.getName()));
		assertEquals(op1Behavior, ((BehaviorExecutionSpecification)op1).getBehavior());

		// Message Operation_1_reply
		final Message op1ReplyMessage = interaction.getMessages().get(6);
		assertEquals("Message does not exist", "Operation_1_reply", op1ReplyMessage.getName());
		assertTrue(op1ReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_1_reply_sender", op1ReplySend.getName());
		assertTrue(op1ReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals("Operation_1_reply_receiver", op1ReplyReceive.getName());
		assertEquals(op1ReplyMessage.getSendEvent(), op1ReplySend);
		assertEquals(op1ReplyMessage.getReceiveEvent(), op1ReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)op1ReplySend).getCovered(consumers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)op1ReplyReceive).getCovered(producers.getName()));
	}

	/**
	 * Test {@link SequenceServices#reorder(Message, InteractionFragment, InteractionFragment) reorder}
	 * service. Reorder a message and an execution. Move the message from the lifeline to the execution.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testReorderMessageAndExecution3() throws Exception {
		// Interaction Scenario_17
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_17");
		final Lifeline producers = interaction.getLifeline("producers");
		final Lifeline consumers = interaction.getLifeline("consumers");

		// Execution to move get
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("get");
		// StartingEndPredecessorAfter not exists
		final ExecutionOccurrenceSpecification startingEndPredecessorAfter = null;
		// FinishingEndPredecessorAfter get_receiver message occurrence
		final MessageOccurrenceSpecification finishingEndPredecessorAfter = (MessageOccurrenceSpecification)interaction
				.getFragment("get_receiver");

		sequenceServices.reorder(execution, startingEndPredecessorAfter, finishingEndPredecessorAfter);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(7, fragments.size());

		final InteractionFragment getSend = fragments.get(0);
		final InteractionFragment getReceive = fragments.get(1);
		final InteractionFragment get = fragments.get(2);
		final InteractionFragment getFinish = fragments.get(3);
		final InteractionFragment computeStart = fragments.get(4);
		final InteractionFragment compute = fragments.get(5);
		final InteractionFragment computeFinish = fragments.get(6);

		// Message get
		final Message getMessage = interaction.getMessages().get(0);
		assertEquals("Message does not exist", "get", getMessage.getName());
		assertTrue(getSend instanceof MessageOccurrenceSpecification);
		assertEquals("get_sender", getSend.getName());
		assertTrue(getReceive instanceof MessageOccurrenceSpecification);
		assertEquals("get_receiver", getReceive.getName());
		assertEquals(getMessage.getSendEvent(), getSend);
		assertEquals(getMessage.getReceiveEvent(), getReceive);
		assertNotNull(((MessageOccurrenceSpecification)getSend).getCovered(consumers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)getReceive).getCovered(producers.getName()));

		// Execution get
		final Behavior getBehavior = interaction.getOwnedBehaviors().get(1);
		assertEquals("Opaque behavior does not exist", "get", getBehavior.getName());
		assertTrue(getReceive instanceof MessageOccurrenceSpecification);
		assertEquals("get_receiver", getReceive.getName());
		assertTrue(getFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_finish", getFinish.getName());
		assertTrue(get instanceof BehaviorExecutionSpecification);
		assertEquals("get", get.getName());
		assertEquals(((BehaviorExecutionSpecification)get).getStart(), getReceive);
		assertEquals(((BehaviorExecutionSpecification)get).getFinish(), getFinish);
		assertNotNull(((BehaviorExecutionSpecification)get).getCovered(producers.getName()));
		assertEquals(getBehavior, ((BehaviorExecutionSpecification)get).getBehavior());

		// Execution compute
		final Behavior computeBehavior = interaction.getOwnedBehaviors().get(0);
		assertEquals("Opaque behavior does not exist", "compute", computeBehavior.getName());
		assertTrue(computeStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_start", computeStart.getName());
		assertTrue(computeFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_finish", computeFinish.getName());
		assertTrue(compute instanceof BehaviorExecutionSpecification);
		assertEquals("compute", compute.getName());
		assertEquals(((BehaviorExecutionSpecification)compute).getStart(), computeStart);
		assertEquals(((BehaviorExecutionSpecification)compute).getFinish(), computeFinish);
		assertNotNull(((BehaviorExecutionSpecification)compute).getCovered(consumers.getName()));
		assertEquals(computeBehavior, ((BehaviorExecutionSpecification)compute).getBehavior());
	}

	/**
	 * Test {@link SequenceServices#delete(Lifeline) delete} service. Delete a lifeline.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testDeleteLifeline() throws Exception {
		// Interaction Delete Scenario_5
		final Interaction interaction = getDeleteInteraction(RESOURCE_URI, "DEL_Scenario_5");

		// Lifeline to delete consumers
		final Lifeline lifeline1 = interaction.getLifeline("consumers");

		// Lifeline to delete producers
		final Lifeline lifeline2 = interaction.getLifeline("producers");

		// Delete
		sequenceServices.delete(lifeline1);
		sequenceServices.delete(lifeline2);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(0, fragments.size());

		final List<Behavior> behaviors = interaction.getOwnedBehaviors();
		assertEquals(0, behaviors.size());

		final List<Element> elements = ((Package)interaction.eContainer()).getOwnedElements();
		assertEquals(1, elements.size());
	}

	/**
	 * Test {@link SequenceServices#delete(BehaviorExecutionSpecification) delete} service. Delete an
	 * execution.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testDeleteExecution() throws Exception {
		// Interaction Delete Scenario_0
		final Interaction interaction = getDeleteInteraction(RESOURCE_URI, "DEL_Scenario_0");

		// Execution to delete compute
		final BehaviorExecutionSpecification execution = (BehaviorExecutionSpecification)interaction
				.getFragment("compute");

		// Delete
		sequenceServices.delete(execution);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(0, fragments.size());

		final List<Behavior> behaviors = interaction.getOwnedBehaviors();
		assertEquals(0, behaviors.size());

		final List<Element> elements = ((Package)interaction.eContainer()).getOwnedElements();
		assertEquals(2, elements.size());
	}

	/**
	 * Test {@link SequenceServices#delete(Message) delete} service. Delete an asynchronous message.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testDeleteAsynchronousMessage() throws Exception {
		// Interaction Delete Scenario_1
		final Interaction interaction = getDeleteInteraction(RESOURCE_URI, "DEL_Scenario_1");

		// Message to delete get
		final Message message = (Message)interaction.getMessage("Message_0");

		// Delete
		sequenceServices.delete(message);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(0, fragments.size());

		final List<Behavior> behaviors = interaction.getOwnedBehaviors();
		assertEquals(0, behaviors.size());

		final List<Element> elements = ((Package)interaction.eContainer()).getOwnedElements();
		assertEquals(1, elements.size());
	}

	/**
	 * Test {@link SequenceServices#delete(Message) delete} service. Delete a synchronous message.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testDeleteSynchronousMessage() throws Exception {
		// Interaction Delete Scenario_2
		final Interaction interaction = getDeleteInteraction(RESOURCE_URI, "DEL_Scenario_2");

		// Message to delete get
		final Message message = (Message)interaction.getMessage("Message_0");

		// Delete
		sequenceServices.delete(message);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(0, fragments.size());

		final List<Behavior> behaviors = interaction.getOwnedBehaviors();
		assertEquals(0, behaviors.size());

		final List<Element> elements = ((Package)interaction.eContainer()).getOwnedElements();
		assertEquals(1, elements.size());
	}

	/**
	 * Test {@link SequenceServices#delete(Message) delete} service. Delete an asynchronous message associated
	 * to an execution.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testDeleteAsynchronousMessageWithExecution() throws Exception {
		// Interaction Delete Scenario_1
		final Interaction interaction = getDeleteInteraction(RESOURCE_URI, "DEL_Scenario_3");

		// Message to delete get
		final Message message = (Message)interaction.getMessage("Operation_0");

		// Delete
		sequenceServices.delete(message);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(0, fragments.size());

		final List<Behavior> behaviors = interaction.getOwnedBehaviors();
		assertEquals(0, behaviors.size());

		final List<Element> elements = ((Package)interaction.eContainer()).getOwnedElements();
		assertEquals(1, elements.size());
	}

	/**
	 * Test {@link SequenceServices#delete(Message) delete} service. Delete a synchronous message associated
	 * to an execution.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testDeleteSynchronousMessageWithExecution() throws Exception {
		// Interaction Delete Scenario_1
		final Interaction interaction = getDeleteInteraction(RESOURCE_URI, "DEL_Scenario_4");

		// Message to delete get
		final Message message = (Message)interaction.getMessage("Operation_1");

		// Delete
		sequenceServices.delete(message);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(0, fragments.size());

		final List<Behavior> behaviors = interaction.getOwnedBehaviors();
		assertEquals(0, behaviors.size());

		final List<Element> elements = ((Package)interaction.eContainer()).getOwnedElements();
		assertEquals(1, elements.size());
	}
}
