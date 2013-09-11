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
package org.obeonetwork.dsl.uml2.design.tests.plugin.manual.services;

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
	 * createAsynchronousMessage} service. Create an asynchronous message (defined on an existing operation)
	 * between to empty lifelines.
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
	 * createAsynchronousMessage} service. Create an asynchronous message and a new operation between to empty
	 * lifelines.
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
	 * createSynchronousMessage} service. Create a synchronous message (defined on an existing operation)
	 * between to empty lifelines.
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
	 * createSynchronousMessage} service. Create a synchronous message and a new operation between to empty
	 * lifelines.
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
