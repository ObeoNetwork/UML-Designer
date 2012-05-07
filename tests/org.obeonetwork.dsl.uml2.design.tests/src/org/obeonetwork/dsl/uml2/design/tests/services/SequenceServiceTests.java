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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
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
	 * Get the class defined in an interaction.
	 * 
	 * @param interaction
	 *            Interaction
	 * @param names
	 *            Class name
	 * @return Class
	 */
	private org.eclipse.uml2.uml.Class getClass(Interaction interaction, String... names) {
		NamedElement element = interaction.getModel();
		for (String name : names) {
			if (element instanceof Namespace)
				element = ((Namespace)element).getOwnedMember(name);
		}
		return (org.eclipse.uml2.uml.Class)element;
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
	 * {@link SequenceServices#createAsynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createAsynchronousMessage} service. Create an asynchronous message between to empty lifelines.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateAsynchronousMessage() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_2");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		sequenceServices.createAsynchronousMessage(interaction, source, target, null, null);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(2, fragments.size());
		assertEquals("Message does not exist", "Message_0", interaction.getMessages().get(0).getName());
		assertTrue(fragments.get(0) instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_sender", fragments.get(0).getName());
		assertTrue(fragments.get(1) instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_receiver", fragments.get(1).getName());
		assertEquals(((Message)interaction.getMessages().get(0)).getSendEvent(), fragments.get(0));
		assertEquals(((Message)interaction.getMessages().get(0)).getReceiveEvent(), fragments.get(1));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(0)).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(1)).getCovered(target.getName()));
	}

	/**
	 * Test
	 * {@link SequenceServices#createAsynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createAsynchronousMessage} service. Create an asynchronous message between to empty lifelines.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateAsynchronousMessageOnExecution() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_3");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("compute");

		sequenceServices.createAsynchronousMessage(interaction, execution, target, execution.getStart(),
				execution.getStart());

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(5, fragments.size());

		final InteractionFragment sourceExecutionStart = fragments.get(0);
		final InteractionFragment sourceExecution = fragments.get(1);
		final InteractionFragment messageSend = fragments.get(2);
		final InteractionFragment messageReceive = fragments.get(3);
		final InteractionFragment sourceExecutionFinish = fragments.get(4);

		// Execution
		assertEquals("Opaque behavior does not exist", "compute", interaction.getOwnedBehaviors().get(0)
				.getName());
		assertTrue(sourceExecutionStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_start", sourceExecutionStart.getName());
		assertTrue(sourceExecutionFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_finish", sourceExecutionFinish.getName());
		assertTrue(sourceExecution instanceof BehaviorExecutionSpecification);
		assertEquals("compute", sourceExecution.getName());
		assertEquals(((BehaviorExecutionSpecification)sourceExecution).getStart(), sourceExecutionStart);
		assertEquals(((BehaviorExecutionSpecification)sourceExecution).getFinish(), sourceExecutionFinish);
		assertNotNull(((BehaviorExecutionSpecification)sourceExecution).getCovered(source.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(0),
				((BehaviorExecutionSpecification)sourceExecution).getBehavior());

		// Message
		assertEquals("Message does not exist", "Message_0", interaction.getMessages().get(0).getName());
		assertTrue(messageSend instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_sender", messageSend.getName());
		assertTrue(messageReceive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_receiver", messageReceive.getName());
		assertEquals(((Message)interaction.getMessages().get(0)).getSendEvent(), messageSend);
		assertEquals(((Message)interaction.getMessages().get(0)).getReceiveEvent(), messageReceive);
		assertNotNull(((MessageOccurrenceSpecification)messageSend).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)messageReceive).getCovered(target.getName()));
	}

	/**
	 * Test
	 * {@link SequenceServices#createAsynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createAsynchronousMessage} service. Import an asynchronous message between to empty lifelines.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testImportAsynchronousMessage() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_2");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		final Operation operation = (Operation)source.getRepresents().getType().getOwnedElements().get(0);

		sequenceServices.createAsynchronousMessage(interaction, source, target, operation, null, null);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(4, fragments.size());
		// Message
		assertEquals("Message does not exist", "compute", interaction.getMessages().get(0).getName());
		assertTrue(fragments.get(0) instanceof MessageOccurrenceSpecification);
		assertEquals("compute_sender", fragments.get(0).getName());
		assertTrue(fragments.get(1) instanceof MessageOccurrenceSpecification);
		assertEquals("compute_receiver", fragments.get(1).getName());
		assertEquals(((Message)interaction.getMessages().get(0)).getSendEvent(), fragments.get(0));
		assertEquals(((Message)interaction.getMessages().get(0)).getReceiveEvent(), fragments.get(1));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(0)).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(1)).getCovered(target.getName()));

		// Execution
		assertEquals("Opaque behavior does not exist", "compute", interaction.getOwnedBehaviors().get(0)
				.getName());
		assertTrue(fragments.get(3) instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_finish", fragments.get(3).getName());
		assertTrue(fragments.get(2) instanceof BehaviorExecutionSpecification);
		assertEquals("compute", fragments.get(2).getName());
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getStart(), fragments.get(1));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getFinish(), fragments.get(3));
		assertNotNull(((BehaviorExecutionSpecification)fragments.get(2)).getCovered(target.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(0),
				((BehaviorExecutionSpecification)fragments.get(2)).getBehavior());
	}

	/**
	 * Test
	 * {@link SequenceServices#createAsynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createAsynchronousMessage} service. Import an asynchronous message between an existing execution and an
	 * empty lifeline.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testImportAsynchronousMessageOnExecution() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_3");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		final Operation operation = (Operation)target.getRepresents().getType().getOwnedElements().get(1);
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("compute");

		sequenceServices.createAsynchronousMessage(interaction, execution, target, operation,
				execution.getStart(), execution.getStart());

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(7, fragments.size());

		final InteractionFragment sourceExecutionStart = fragments.get(0);
		final InteractionFragment sourceExecution = fragments.get(1);
		final InteractionFragment messageSend = fragments.get(2);
		final InteractionFragment messageReceive = fragments.get(3);
		final InteractionFragment targetExecution = fragments.get(4);
		final InteractionFragment targetExecutionFinish = fragments.get(5);
		final InteractionFragment sourceExecutionFinish = fragments.get(6);

		// Execution compute
		assertEquals("Opaque behavior does not exist", "compute", interaction.getOwnedBehaviors().get(0)
				.getName());
		assertTrue(sourceExecutionStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_start", sourceExecutionStart.getName());
		assertTrue(sourceExecutionFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_finish", sourceExecutionFinish.getName());
		assertTrue(sourceExecution instanceof BehaviorExecutionSpecification);
		assertEquals("compute", sourceExecution.getName());
		assertEquals(((BehaviorExecutionSpecification)sourceExecution).getStart(), sourceExecutionStart);
		assertEquals(((BehaviorExecutionSpecification)sourceExecution).getFinish(), sourceExecutionFinish);
		assertNotNull(((BehaviorExecutionSpecification)sourceExecution).getCovered(source.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(0),
				((BehaviorExecutionSpecification)sourceExecution).getBehavior());

		// Message get
		assertEquals("Message does not exist", "get", interaction.getMessages().get(0).getName());
		assertTrue(messageSend instanceof MessageOccurrenceSpecification);
		assertEquals("get_sender", messageSend.getName());
		assertTrue(messageReceive instanceof MessageOccurrenceSpecification);
		assertEquals("get_receiver", messageReceive.getName());
		assertEquals(((Message)interaction.getMessages().get(0)).getSendEvent(), messageSend);
		assertEquals(((Message)interaction.getMessages().get(0)).getReceiveEvent(), messageReceive);
		assertNotNull(((MessageOccurrenceSpecification)messageSend).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)messageReceive).getCovered(target.getName()));

		// Execution get
		assertEquals("Opaque behavior does not exist", "get", interaction.getOwnedBehaviors().get(1)
				.getName());
		assertTrue(targetExecution instanceof BehaviorExecutionSpecification);
		assertEquals("get", targetExecution.getName());
		assertEquals(((BehaviorExecutionSpecification)targetExecution).getStart(), messageReceive);
		assertTrue(targetExecutionFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_finish", targetExecutionFinish.getName());
		assertEquals(((BehaviorExecutionSpecification)targetExecution).getFinish(), targetExecutionFinish);
		assertNotNull(((BehaviorExecutionSpecification)targetExecution).getCovered(target.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(1),
				((BehaviorExecutionSpecification)targetExecution).getBehavior());
	}

	/**
	 * Test
	 * {@link SequenceServices#createAsynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createAsynchronousMessage} service. Import two asynchronous messages between an existing execution and
	 * an empty lifeline.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testImportTwoAsynchronousMessagesOnExecution() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_7");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		final Operation operation = (Operation)target.getRepresents().getType().getOwnedElements().get(0);
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("compute");
		final ExecutionSpecification execution2 = (ExecutionSpecification)interaction.getFragment("get");
		sequenceServices.createAsynchronousMessage(interaction, execution, target, operation,
				execution2.getFinish(), execution2.getFinish());

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(11, fragments.size());

		final InteractionFragment sourceExecutionStart = fragments.get(0);
		final InteractionFragment sourceExecution = fragments.get(1);
		final InteractionFragment messageSend = fragments.get(2);
		final InteractionFragment messageReceive = fragments.get(3);
		final InteractionFragment targetExecution = fragments.get(4);
		final InteractionFragment targetExecutionFinish = fragments.get(5);
		final InteractionFragment messageSend2 = fragments.get(6);
		final InteractionFragment messageReceive2 = fragments.get(7);
		final InteractionFragment targetExecution2 = fragments.get(8);
		final InteractionFragment targetExecution2Finish = fragments.get(9);
		final InteractionFragment sourceExecutionFinish = fragments.get(10);

		// Execution compute
		assertEquals("Opaque behavior does not exist", "compute", interaction.getOwnedBehaviors().get(0)
				.getName());
		assertTrue(sourceExecutionStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_start", sourceExecutionStart.getName());
		assertTrue(sourceExecutionFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_finish", sourceExecutionFinish.getName());
		assertTrue(sourceExecution instanceof BehaviorExecutionSpecification);
		assertEquals("compute", sourceExecution.getName());
		assertEquals(((BehaviorExecutionSpecification)sourceExecution).getStart(), sourceExecutionStart);
		assertEquals(((BehaviorExecutionSpecification)sourceExecution).getFinish(), sourceExecutionFinish);
		assertNotNull(((BehaviorExecutionSpecification)sourceExecution).getCovered(source.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(0),
				((BehaviorExecutionSpecification)sourceExecution).getBehavior());

		// Message get
		final Message message = (Message)interaction.getMessages().get(0);
		assertEquals("Message does not exist", "get", message.getName());
		assertTrue(messageSend instanceof MessageOccurrenceSpecification);
		assertEquals("get_sender", messageSend.getName());
		assertTrue(messageReceive instanceof MessageOccurrenceSpecification);
		assertEquals("get_receiver", messageReceive.getName());
		assertEquals(message.getSendEvent(), messageSend);
		assertEquals(message.getReceiveEvent(), messageReceive);
		assertNotNull(((MessageOccurrenceSpecification)messageSend).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)messageReceive).getCovered(target.getName()));

		// Execution get
		assertEquals("Opaque behavior does not exist", "get", interaction.getOwnedBehaviors().get(1)
				.getName());
		assertTrue(targetExecution instanceof BehaviorExecutionSpecification);
		assertEquals("get", targetExecution.getName());
		assertEquals(((BehaviorExecutionSpecification)targetExecution).getStart(), messageReceive);
		assertTrue(targetExecutionFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_finish", targetExecutionFinish.getName());
		assertEquals(((BehaviorExecutionSpecification)targetExecution).getFinish(), targetExecutionFinish);
		assertNotNull(((BehaviorExecutionSpecification)targetExecution).getCovered(target.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(1),
				((BehaviorExecutionSpecification)targetExecution).getBehavior());

		// Message produce
		final Message message2 = (Message)interaction.getMessages().get(1);
		assertEquals("Message does not exist", "produce", interaction.getMessages().get(1).getName());
		assertTrue(messageSend2 instanceof MessageOccurrenceSpecification);
		assertEquals("produce_sender", messageSend2.getName());
		assertTrue(messageReceive2 instanceof MessageOccurrenceSpecification);
		assertEquals("produce_receiver", messageReceive2.getName());
		assertEquals(message2.getSendEvent(), messageSend2);
		assertEquals(message2.getReceiveEvent(), messageReceive2);
		assertNotNull(((MessageOccurrenceSpecification)messageSend2).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)messageReceive2).getCovered(target.getName()));

		// Execution produce
		assertEquals("Opaque behavior does not exist", "produce", interaction.getOwnedBehaviors().get(2)
				.getName());
		assertTrue(targetExecution2 instanceof BehaviorExecutionSpecification);
		assertEquals("produce", targetExecution2.getName());
		assertEquals(((BehaviorExecutionSpecification)targetExecution2).getStart(), messageReceive2);
		assertTrue(targetExecution2Finish instanceof ExecutionOccurrenceSpecification);
		assertEquals("produce_finish", targetExecution2Finish.getName());
		assertEquals(((BehaviorExecutionSpecification)targetExecution2).getFinish(), targetExecution2Finish);
		assertNotNull(((BehaviorExecutionSpecification)targetExecution2).getCovered(target.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(2),
				((BehaviorExecutionSpecification)targetExecution2).getBehavior());
	}

	/**
	 * Test
	 * {@link SequenceServices#createAsynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createAsynchronousMessage} service. Import an asynchronous message between two existing executions.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateAsynchronousMessageOnExecutionToExecution() throws Exception {
		// Interaction scenario_4
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_4");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		// SourceFragment compute behavior
		final ExecutionSpecification executionSource = (ExecutionSpecification)interaction
				.getFragment("compute");
		// TargetFragment get behavior
		final ExecutionSpecification executionTarget = (ExecutionSpecification)interaction.getFragment("get");
		// Operation null
		// StartingElementPredecessor compute_start
		// FinishingElementPredecessor compute_start
		sequenceServices.createAsynchronousMessage(interaction, executionSource, executionTarget,
				executionTarget.getStart(), executionTarget.getStart());

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(8, fragments.size());

		final InteractionFragment computeStart = fragments.get(0);
		final InteractionFragment compute = fragments.get(1);
		final InteractionFragment getStart = fragments.get(2);
		final InteractionFragment get = fragments.get(3);
		final InteractionFragment msg0Send = fragments.get(4);
		final InteractionFragment msg0Receive = fragments.get(5);
		final InteractionFragment getFinish = fragments.get(6);
		final InteractionFragment computeFinish = fragments.get(7);

		// Execution compute
		assertEquals("Opaque behavior does not exist", "compute", interaction.getOwnedBehaviors().get(0)
				.getName());

		assertTrue(computeStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_start", computeStart.getName());
		assertTrue(computeFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_finish", computeFinish.getName());
		assertTrue(compute instanceof BehaviorExecutionSpecification);
		assertEquals("compute", compute.getName());
		assertEquals(((BehaviorExecutionSpecification)compute).getStart(), computeStart);
		assertEquals(((BehaviorExecutionSpecification)compute).getFinish(), computeFinish);
		assertNotNull(((BehaviorExecutionSpecification)compute).getCovered(source.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(0),
				((BehaviorExecutionSpecification)compute).getBehavior());

		// Message Message_0
		assertEquals("Message does not exist", "Message_0", interaction.getMessages().get(0).getName());
		assertTrue(msg0Send instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_sender", msg0Send.getName());
		assertTrue(msg0Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_receiver", msg0Receive.getName());
		final Message msg0 = (Message)interaction.getMessages().get(0);
		assertEquals(msg0.getSendEvent(), msg0Send);
		assertEquals(msg0.getReceiveEvent(), msg0Receive);
		assertNotNull(((MessageOccurrenceSpecification)msg0Send).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg0Receive).getCovered(target.getName()));

		// Execution get
		assertEquals("Opaque behavior does not exist", "get", interaction.getOwnedBehaviors().get(1)
				.getName());
		assertTrue(get instanceof BehaviorExecutionSpecification);
		assertEquals("get", get.getName());
		assertTrue(getStart instanceof ExecutionOccurrenceSpecification);
		assertEquals(((BehaviorExecutionSpecification)get).getStart(), getStart);
		assertTrue(getFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_finish", getFinish.getName());
		assertEquals(((BehaviorExecutionSpecification)get).getFinish(), getFinish);
		assertNotNull(((BehaviorExecutionSpecification)get).getCovered(target.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(1),
				((BehaviorExecutionSpecification)get).getBehavior());
	}

	/**
	 * Test
	 * {@link SequenceServices#createAsynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createAsynchronousMessage} service. Import an asynchronous message between two existing executions.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateAsynchronousMessageOnExecutionToExecution2() throws Exception {
		// Interaction scenario_4
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_4");
		final Lifeline source = interaction.getLifeline("producers");
		final Lifeline target = interaction.getLifeline("consumers");
		// SourceFragment get behavior
		final ExecutionSpecification executionSource = (ExecutionSpecification)interaction.getFragment("get");
		// TargetFragment compute behavior
		final ExecutionSpecification executionTarget = (ExecutionSpecification)interaction
				.getFragment("compute");
		// Operation null
		// StartingElementPredecessor get_start
		// FinishingElementPredecessor get_start
		sequenceServices.createAsynchronousMessage(interaction, executionSource, executionTarget,
				executionSource.getStart(), executionSource.getStart());

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(8, fragments.size());

		final InteractionFragment computeStart = fragments.get(0);
		final InteractionFragment compute = fragments.get(1);
		final InteractionFragment getStart = fragments.get(2);
		final InteractionFragment get = fragments.get(3);
		final InteractionFragment msg0Send = fragments.get(4);
		final InteractionFragment msg0Receive = fragments.get(5);
		final InteractionFragment getFinish = fragments.get(6);
		final InteractionFragment computeFinish = fragments.get(7);

		// Execution compute
		assertEquals("Opaque behavior does not exist", "compute", interaction.getOwnedBehaviors().get(0)
				.getName());
		assertTrue(computeStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_start", computeStart.getName());
		assertTrue(computeFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("compute_finish", computeFinish.getName());
		assertTrue(compute instanceof BehaviorExecutionSpecification);
		assertEquals("compute", compute.getName());
		assertEquals(((BehaviorExecutionSpecification)compute).getStart(), computeStart);
		assertEquals(((BehaviorExecutionSpecification)compute).getFinish(), computeFinish);
		assertNotNull(((BehaviorExecutionSpecification)compute).getCovered(target.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(0),
				((BehaviorExecutionSpecification)compute).getBehavior());

		// Execution get
		assertEquals("Opaque behavior does not exist", "get", interaction.getOwnedBehaviors().get(1)
				.getName());
		assertTrue(get instanceof BehaviorExecutionSpecification);
		assertEquals("get", get.getName());
		assertTrue(getStart instanceof ExecutionOccurrenceSpecification);
		assertEquals(((BehaviorExecutionSpecification)get).getStart(), getStart);
		assertTrue(getFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_finish", getFinish.getName());
		assertEquals(((BehaviorExecutionSpecification)get).getFinish(), getFinish);
		assertNotNull(((BehaviorExecutionSpecification)get).getCovered(source.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(1),
				((BehaviorExecutionSpecification)get).getBehavior());

		// Message Message_0
		assertEquals("Message does not exist", "Message_0", interaction.getMessages().get(0).getName());
		assertTrue(msg0Send instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_sender", msg0Send.getName());
		assertTrue(msg0Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_receiver", msg0Receive.getName());
		final Message msg0 = (Message)interaction.getMessages().get(0);
		assertEquals(msg0.getSendEvent(), msg0Send);
		assertEquals(msg0.getReceiveEvent(), msg0Receive);
		assertNotNull(((MessageOccurrenceSpecification)msg0Send).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg0Receive).getCovered(target.getName()));
	}

	/**
	 * Test
	 * {@link SequenceServices#createSynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createSynchronousMessage} service. Create a synchronous message between to empty lifelines.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateSynchronousMessage() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_2");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		sequenceServices.createSynchronousMessage(interaction, source, target, null, null);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(4, fragments.size());
		// Message
		assertEquals("Message does not exist", "Message_0", interaction.getMessages().get(0).getName());
		assertTrue(fragments.get(0) instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_sender", fragments.get(0).getName());
		assertTrue(fragments.get(1) instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_receiver", fragments.get(1).getName());
		assertEquals(((Message)interaction.getMessages().get(0)).getSendEvent(), fragments.get(0));
		assertEquals(((Message)interaction.getMessages().get(0)).getReceiveEvent(), fragments.get(1));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(0)).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(1)).getCovered(target.getName()));

		// Reply message
		assertEquals("Message does not exist", "Message_0_reply", interaction.getMessages().get(1).getName());
		assertTrue(fragments.get(2) instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_reply_sender", fragments.get(2).getName());
		assertTrue(fragments.get(3) instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_reply_receiver", fragments.get(3).getName());
		assertEquals(((Message)interaction.getMessages().get(1)).getSendEvent(), fragments.get(2));
		assertEquals(((Message)interaction.getMessages().get(1)).getReceiveEvent(), fragments.get(3));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(2)).getCovered(target.getName()));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(3)).getCovered(source.getName()));
	}

	/**
	 * Test
	 * {@link SequenceServices#createSynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createSynchronousMessage} service. Create a synchronous message between an existing execution and an
	 * empty lifeline.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateSynchronousMessageOnExecution() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_3");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("compute");

		sequenceServices.createSynchronousMessage(interaction, execution, target, execution.getStart(),
				execution.getStart());

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(7, fragments.size());
		final InteractionFragment computeStart = fragments.get(0);
		final InteractionFragment compute = fragments.get(1);
		final InteractionFragment messageSend = fragments.get(2);
		final InteractionFragment messageReceive = fragments.get(3);
		final InteractionFragment messageReplySend = fragments.get(4);
		final InteractionFragment messageReplyReceive = fragments.get(5);
		final InteractionFragment computeFinish = fragments.get(6);

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
		assertNotNull(((BehaviorExecutionSpecification)compute).getCovered(source.getName()));
		assertEquals(computeBehavior, ((BehaviorExecutionSpecification)compute).getBehavior());

		// Message Message_0
		final Message message = interaction.getMessages().get(0);
		assertEquals("Message does not exist", "Message_0", message.getName());
		assertTrue(messageSend instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_sender", messageSend.getName());
		assertTrue(messageReceive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_receiver", messageReceive.getName());
		assertEquals(message.getSendEvent(), messageSend);
		assertEquals(message.getReceiveEvent(), messageReceive);
		assertNotNull(((MessageOccurrenceSpecification)messageSend).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)messageReceive).getCovered(target.getName()));

		// Reply message Message_0
		final Message messageReply = interaction.getMessages().get(1);
		assertEquals("Message does not exist", "Message_0_reply", messageReply.getName());
		assertTrue(messageReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_reply_sender", messageReplySend.getName());
		assertTrue(messageReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_reply_receiver", messageReplyReceive.getName());
		assertEquals(messageReply.getSendEvent(), messageReplySend);
		assertEquals(messageReply.getReceiveEvent(), messageReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)messageReplySend).getCovered(target.getName()));
		assertNotNull(((MessageOccurrenceSpecification)messageReplyReceive).getCovered(source.getName()));
	}

	/**
	 * Test
	 * {@link SequenceServices#createOperationAndSynchMessage(NamedElement, NamedElement, NamedElement, NamedElement)
	 * createOperationAndSynchMessage} service. Create a synchronous message between an existing execution and
	 * an empty lifeline.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateSynchronousMessageAndOperationOnExecution() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_4");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("compute");

		sequenceServices.createOperationAndSynchMessage(target, source, execution.getStart(),
				execution.getStart());

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(11, fragments.size());
		final InteractionFragment computeStart = fragments.get(0);
		final InteractionFragment compute = fragments.get(1);
		final InteractionFragment operationMsgSend = fragments.get(2);
		final InteractionFragment operationMsgReceive = fragments.get(3);
		final InteractionFragment operation = fragments.get(4);
		final InteractionFragment operationMsgReplySend = fragments.get(5);
		final InteractionFragment operationMsgReplyReceive = fragments.get(6);
		final InteractionFragment getStart = fragments.get(7);
		final InteractionFragment get = fragments.get(8);
		final InteractionFragment getFinish = fragments.get(9);
		final InteractionFragment computeFinish = fragments.get(10);

		// Operation Operation_0 in class Producer
		final org.eclipse.uml2.uml.Class producer = getClass(interaction, "Structure", "Producer");
		String operationName = "Operation_2";
		assertEquals(operationName, producer.getOperations().get(producer.getOperations().size() - 1)
				.getName());

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
		assertNotNull(((BehaviorExecutionSpecification)compute).getCovered(source.getName()));
		assertEquals(computeBehavior, ((BehaviorExecutionSpecification)compute).getBehavior());

		// Message Operation_0
		final Message message = interaction.getMessages().get(0);
		assertEquals("Message does not exist", operationName, message.getName());
		assertTrue(operationMsgSend instanceof MessageOccurrenceSpecification);
		assertEquals(operationName + "_sender", operationMsgSend.getName());
		assertTrue(operationMsgReceive instanceof MessageOccurrenceSpecification);
		assertEquals(operationName + "_receiver", operationMsgReceive.getName());
		assertEquals(message.getSendEvent(), operationMsgSend);
		assertEquals(message.getReceiveEvent(), operationMsgReceive);
		assertNotNull(((MessageOccurrenceSpecification)operationMsgSend).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)operationMsgReceive).getCovered(target.getName()));

		// Execution Operation_0
		final Behavior operation0Behavior = interaction.getOwnedBehaviors().get(2);
		assertEquals("Opaque behavior does not exist", operationName, operation0Behavior.getName());
		assertTrue(operationMsgReceive instanceof MessageOccurrenceSpecification);
		assertEquals(operationName + "_receiver", operationMsgReceive.getName());
		assertTrue(operationMsgReplySend instanceof MessageOccurrenceSpecification);
		assertEquals(operationName + "_reply_sender", operationMsgReplySend.getName());
		assertTrue(operation instanceof BehaviorExecutionSpecification);
		assertEquals(operationName, operation.getName());
		assertEquals(((BehaviorExecutionSpecification)operation).getStart(), operationMsgReceive);
		assertEquals(((BehaviorExecutionSpecification)operation).getFinish(), operationMsgReplySend);
		assertNotNull(((BehaviorExecutionSpecification)operation).getCovered(target.getName()));
		assertEquals(operation0Behavior, ((BehaviorExecutionSpecification)operation).getBehavior());

		// Reply message Operation_0
		final Message messageReply = interaction.getMessages().get(1);
		assertEquals("Message does not exist", operationName + "_reply", messageReply.getName());
		assertTrue(operationMsgReplySend instanceof MessageOccurrenceSpecification);
		assertEquals(operationName + "_reply_sender", operationMsgReplySend.getName());
		assertTrue(operationMsgReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals(operationName + "_reply_receiver", operationMsgReplyReceive.getName());
		assertEquals(messageReply.getSendEvent(), operationMsgReplySend);
		assertEquals(messageReply.getReceiveEvent(), operationMsgReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)operationMsgReplySend).getCovered(target.getName()));
		assertNotNull(((MessageOccurrenceSpecification)operationMsgReplyReceive).getCovered(source.getName()));

		// Execution get
		final Behavior getBehavior = interaction.getOwnedBehaviors().get(1);
		assertEquals("Opaque behavior does not exist", "get", getBehavior.getName());
		assertTrue(getStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_start", getStart.getName());
		assertTrue(getFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_finish", getFinish.getName());
		assertTrue(get instanceof BehaviorExecutionSpecification);
		assertEquals("get", get.getName());
		assertEquals(((BehaviorExecutionSpecification)get).getStart(), getStart);
		assertEquals(((BehaviorExecutionSpecification)get).getFinish(), getFinish);
		assertNotNull(((BehaviorExecutionSpecification)get).getCovered(target.getName()));
		assertEquals(getBehavior, ((BehaviorExecutionSpecification)get).getBehavior());
	}

	/**
	 * Test
	 * {@link SequenceServices#createOperationAndAsynchMessage(NamedElement, NamedElement, NamedElement, NamedElement)
	 * createOperationAndAsynchMessage} service. Create an asynchronous message between an existing execution
	 * and an empty lifeline.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateAsynchronousMessageAndOperationOnExecution() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_4");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("compute");

		sequenceServices.createOperationAndAsynchMessage(target, source, execution.getStart(),
				execution.getStart());

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(10, fragments.size());
		final InteractionFragment computeStart = fragments.get(0);
		final InteractionFragment compute = fragments.get(1);
		final InteractionFragment operationMsgSend = fragments.get(2);
		final InteractionFragment operationMsgReceive = fragments.get(3);
		final InteractionFragment operation = fragments.get(4);
		final InteractionFragment operationFinish = fragments.get(5);
		final InteractionFragment getStart = fragments.get(6);
		final InteractionFragment get = fragments.get(7);
		final InteractionFragment getFinish = fragments.get(8);
		final InteractionFragment computeFinish = fragments.get(9);

		// Operation Operation_0 in class Producer
		final org.eclipse.uml2.uml.Class producer = getClass(interaction, "Structure", "Producer");
		String operationName = "Operation_2";
		assertEquals(operationName, producer.getOperations().get(producer.getOperations().size() - 1)
				.getName());

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
		assertNotNull(((BehaviorExecutionSpecification)compute).getCovered(source.getName()));
		assertEquals(computeBehavior, ((BehaviorExecutionSpecification)compute).getBehavior());

		// Message Operation_0
		final Message message = interaction.getMessages().get(0);
		assertEquals("Message does not exist", operationName, message.getName());
		assertTrue(operationMsgSend instanceof MessageOccurrenceSpecification);
		assertEquals(operationName + "_sender", operationMsgSend.getName());
		assertTrue(operationMsgReceive instanceof MessageOccurrenceSpecification);
		assertEquals(operationName + "_receiver", operationMsgReceive.getName());
		assertEquals(message.getSendEvent(), operationMsgSend);
		assertEquals(message.getReceiveEvent(), operationMsgReceive);
		assertNotNull(((MessageOccurrenceSpecification)operationMsgSend).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)operationMsgReceive).getCovered(target.getName()));

		// Execution Operation_0
		final Behavior operation0Behavior = interaction.getOwnedBehaviors().get(2);
		assertEquals("Opaque behavior does not exist", operationName, operation0Behavior.getName());
		assertTrue(operationMsgReceive instanceof MessageOccurrenceSpecification);
		assertEquals(operationName + "_receiver", operationMsgReceive.getName());
		assertTrue(operationFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals(operationName + "_finish", operationFinish.getName());
		assertTrue(operation instanceof BehaviorExecutionSpecification);
		assertEquals(operationName, operation.getName());
		assertEquals(((BehaviorExecutionSpecification)operation).getStart(), operationMsgReceive);
		assertEquals(((BehaviorExecutionSpecification)operation).getFinish(), operationFinish);
		assertNotNull(((BehaviorExecutionSpecification)operation).getCovered(target.getName()));
		assertEquals(operation0Behavior, ((BehaviorExecutionSpecification)operation).getBehavior());

		// Execution get
		final Behavior getBehavior = interaction.getOwnedBehaviors().get(1);
		assertEquals("Opaque behavior does not exist", "get", getBehavior.getName());
		assertTrue(getStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_start", getStart.getName());
		assertTrue(getFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_finish", getFinish.getName());
		assertTrue(get instanceof BehaviorExecutionSpecification);
		assertEquals("get", get.getName());
		assertEquals(((BehaviorExecutionSpecification)get).getStart(), getStart);
		assertEquals(((BehaviorExecutionSpecification)get).getFinish(), getFinish);
		assertNotNull(((BehaviorExecutionSpecification)get).getCovered(target.getName()));
		assertEquals(getBehavior, ((BehaviorExecutionSpecification)get).getBehavior());
	}

	/**
	 * Test
	 * {@link SequenceServices#createSynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createSynchronousMessage} service. Create a synchronous message between an existing execution and an
	 * other execution.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateSynchronousMessageOnExecutionToExecution() throws Exception {
		// Interaction Scenario_4
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_4");
		// SourceFragment compute execution
		final ExecutionSpecification executionSource = (ExecutionSpecification)interaction
				.getFragment("compute");
		// TargetFragment get execution
		final ExecutionSpecification executionTarget = (ExecutionSpecification)interaction.getFragment("get");
		// Operation null
		// StartingEndPredecessor compute_start
		// FinishingEndPredecessor compute_start
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");

		sequenceServices.createSynchronousMessage(interaction, executionSource, executionTarget,
				executionTarget.getStart(), executionTarget.getStart());
		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(10, fragments.size());

		final InteractionFragment computeStart = fragments.get(0);
		final InteractionFragment compute = fragments.get(1);
		final InteractionFragment getStart = fragments.get(2);
		final InteractionFragment get = fragments.get(3);
		final InteractionFragment msg0Send = fragments.get(4);
		final InteractionFragment msg0Receive = fragments.get(5);
		final InteractionFragment msg0ReplySend = fragments.get(6);
		final InteractionFragment msg0ReplyReceive = fragments.get(7);
		final InteractionFragment getFinish = fragments.get(8);
		final InteractionFragment computeFinish = fragments.get(9);

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
		assertNotNull(((BehaviorExecutionSpecification)compute).getCovered(source.getName()));
		assertEquals(computeBehavior, ((BehaviorExecutionSpecification)compute).getBehavior());

		// Execution get
		final Behavior getBehavior = interaction.getOwnedBehaviors().get(1);
		assertEquals("Opaque behavior does not exist", "get", getBehavior.getName());
		assertTrue(getStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_start", getStart.getName());
		assertTrue(getFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_finish", getFinish.getName());
		assertTrue(get instanceof BehaviorExecutionSpecification);
		assertEquals("get", get.getName());
		assertEquals(((BehaviorExecutionSpecification)get).getStart(), getStart);
		assertEquals(((BehaviorExecutionSpecification)get).getFinish(), getFinish);
		assertNotNull(((BehaviorExecutionSpecification)get).getCovered(target.getName()));
		assertEquals(getBehavior, ((BehaviorExecutionSpecification)get).getBehavior());

		// Message Message_0
		final Message msg0 = interaction.getMessages().get(0);
		assertEquals("Message does not exist", "Message_0", msg0.getName());
		assertTrue(msg0Send instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_sender", msg0Send.getName());
		assertTrue(msg0Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_receiver", msg0Receive.getName());
		assertEquals(msg0.getSendEvent(), msg0Send);
		assertEquals(msg0.getReceiveEvent(), msg0Receive);
		assertNotNull(((MessageOccurrenceSpecification)msg0Send).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg0Receive).getCovered(target.getName()));

		// Reply message get
		final Message msg0ReplyMessage = interaction.getMessages().get(1);
		assertEquals("Message does not exist", "Message_0_reply", msg0ReplyMessage.getName());
		assertTrue(msg0ReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_reply_sender", msg0ReplySend.getName());
		assertTrue(msg0ReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_reply_receiver", msg0ReplyReceive.getName());
		assertEquals(msg0ReplyMessage.getSendEvent(), msg0ReplySend);
		assertEquals(msg0ReplyMessage.getReceiveEvent(), msg0ReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)msg0ReplySend).getCovered(target.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg0ReplyReceive).getCovered(source.getName()));
	}

	/**
	 * Test
	 * {@link SequenceServices#createSynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createSynchronousMessage} service. Create a synchronous message between an existing execution and an
	 * other execution.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateSynchronousMessageOnExecutionToExecution2() throws Exception {
		// Interaction Scenario_4
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_4");
		// SourceFragment get execution
		final ExecutionSpecification executionSource = (ExecutionSpecification)interaction.getFragment("get");
		// TargetFragment compute execution
		final ExecutionSpecification executionTarget = (ExecutionSpecification)interaction
				.getFragment("compute");
		// Operation null
		// StartingEndPredecessor get_start
		// FinishingEndPredecessor get_start
		final Lifeline source = interaction.getLifeline("producers");
		final Lifeline target = interaction.getLifeline("consumers");

		sequenceServices.createSynchronousMessage(interaction, executionSource, executionTarget,
				executionSource.getStart(), executionSource.getStart());

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(10, fragments.size());

		final InteractionFragment computeStart = fragments.get(0);
		final InteractionFragment compute = fragments.get(1);
		final InteractionFragment getStart = fragments.get(2);
		final InteractionFragment get = fragments.get(3);
		final InteractionFragment msg0Send = fragments.get(4);
		final InteractionFragment msg0Receive = fragments.get(5);
		final InteractionFragment msg0ReplySend = fragments.get(6);
		final InteractionFragment msg0ReplyReceive = fragments.get(7);
		final InteractionFragment getFinish = fragments.get(8);
		final InteractionFragment computeFinish = fragments.get(9);

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
		assertNotNull(((BehaviorExecutionSpecification)compute).getCovered(target.getName()));
		assertEquals(computeBehavior, ((BehaviorExecutionSpecification)compute).getBehavior());

		// Execution get
		final Behavior getBehavior = interaction.getOwnedBehaviors().get(1);
		assertEquals("Opaque behavior does not exist", "get", getBehavior.getName());
		assertTrue(getStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_start", getStart.getName());
		assertTrue(getFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("get_finish", getFinish.getName());
		assertTrue(get instanceof BehaviorExecutionSpecification);
		assertEquals("get", get.getName());
		assertEquals(((BehaviorExecutionSpecification)get).getStart(), getStart);
		assertEquals(((BehaviorExecutionSpecification)get).getFinish(), getFinish);
		assertNotNull(((BehaviorExecutionSpecification)get).getCovered(source.getName()));
		assertEquals(getBehavior, ((BehaviorExecutionSpecification)get).getBehavior());

		// Message Message_0
		final Message msg0 = interaction.getMessages().get(0);
		assertEquals("Message does not exist", "Message_0", msg0.getName());
		assertTrue(msg0Send instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_sender", msg0Send.getName());
		assertTrue(msg0Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_receiver", msg0Receive.getName());
		assertEquals(msg0.getSendEvent(), msg0Send);
		assertEquals(msg0.getReceiveEvent(), msg0Receive);
		assertNotNull(((MessageOccurrenceSpecification)msg0Send).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg0Receive).getCovered(target.getName()));

		// Reply message Message_0
		final Message msg0ReplyMessage = interaction.getMessages().get(1);
		assertEquals("Message does not exist", "Message_0_reply", msg0ReplyMessage.getName());
		assertTrue(msg0ReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_reply_sender", msg0ReplySend.getName());
		assertTrue(msg0ReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_reply_receiver", msg0ReplyReceive.getName());
		assertEquals(msg0ReplyMessage.getSendEvent(), msg0ReplySend);
		assertEquals(msg0ReplyMessage.getReceiveEvent(), msg0ReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)msg0ReplySend).getCovered(target.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg0ReplyReceive).getCovered(source.getName()));
	}

	/**
	 * Test
	 * {@link SequenceServices#createSynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createSynchronousMessage} service. Create a synchronous message between an existing execution and an
	 * empty lifeline.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testImportSynchronousMessageOnExecution() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_3");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		final Operation operation = (Operation)target.getRepresents().getType().getOwnedElements().get(1);
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("compute");

		sequenceServices.createSynchronousMessage(interaction, execution, target, operation,
				execution.getStart(), execution.getStart());

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(8, fragments.size());

		final InteractionFragment computeStart = fragments.get(0);
		final InteractionFragment compute = fragments.get(1);
		final InteractionFragment getSend = fragments.get(2);
		final InteractionFragment getReceive = fragments.get(3);
		final InteractionFragment get = fragments.get(4);
		final InteractionFragment getReplySend = fragments.get(5);
		final InteractionFragment getReplyReceive = fragments.get(6);
		final InteractionFragment computeFinish = fragments.get(7);

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
		assertNotNull(((BehaviorExecutionSpecification)compute).getCovered(source.getName()));
		assertEquals(computeBehavior, ((BehaviorExecutionSpecification)compute).getBehavior());

		// Message get
		final Message getMessage = interaction.getMessages().get(0);
		assertEquals("Message does not exist", "get", getMessage.getName());
		assertTrue(getSend instanceof MessageOccurrenceSpecification);
		assertEquals("get_sender", getSend.getName());
		assertTrue(getReceive instanceof MessageOccurrenceSpecification);
		assertEquals("get_receiver", getReceive.getName());
		assertEquals(getMessage.getSendEvent(), getSend);
		assertEquals(getMessage.getReceiveEvent(), getReceive);
		assertNotNull(((MessageOccurrenceSpecification)getSend).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)getReceive).getCovered(target.getName()));

		// Execution get
		final Behavior getBehavior = interaction.getOwnedBehaviors().get(1);
		assertEquals("Opaque behavior does not exist", "get", getBehavior.getName());
		assertTrue(get instanceof BehaviorExecutionSpecification);
		assertEquals("get", get.getName());
		assertEquals(((BehaviorExecutionSpecification)get).getStart(), getReceive);
		assertEquals(((BehaviorExecutionSpecification)get).getFinish(), getReplySend);
		assertNotNull(((BehaviorExecutionSpecification)get).getCovered(target.getName()));
		assertEquals(getBehavior, ((BehaviorExecutionSpecification)get).getBehavior());

		// Reply message get
		final Message getReplyMessage = interaction.getMessages().get(1);
		assertEquals("Message does not exist", "get_reply", getReplyMessage.getName());
		assertTrue(getReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("get_reply_sender", getReplySend.getName());
		assertTrue(getReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals("get_reply_receiver", getReplyReceive.getName());
		assertEquals(getReplyMessage.getSendEvent(), getReplySend);
		assertEquals(getReplyMessage.getReceiveEvent(), getReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)getReplySend).getCovered(target.getName()));
		assertNotNull(((MessageOccurrenceSpecification)getReplyReceive).getCovered(source.getName()));
	}

	/**
	 * Test
	 * {@link SequenceServices#createSynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createSynchronousMessage} service. Import a synchronous message between to empty lifelines.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testImportSynchronousMessage() throws Exception {
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_2");
		final Lifeline source = interaction.getLifeline("consumers");
		final Lifeline target = interaction.getLifeline("producers");
		final Operation operation = (Operation)source.getRepresents().getType().getOwnedElements().get(0);

		sequenceServices.createSynchronousMessage(interaction, source, target, operation, null, null);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(5, fragments.size());
		// Message
		assertEquals("Message does not exist", "compute", interaction.getMessages().get(0).getName());
		assertTrue(fragments.get(0) instanceof MessageOccurrenceSpecification);
		assertEquals("compute_sender", fragments.get(0).getName());
		assertTrue(fragments.get(1) instanceof MessageOccurrenceSpecification);
		assertEquals("compute_receiver", fragments.get(1).getName());
		assertEquals(((Message)interaction.getMessages().get(0)).getSendEvent(), fragments.get(0));
		assertEquals(((Message)interaction.getMessages().get(0)).getReceiveEvent(), fragments.get(1));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(0)).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(1)).getCovered(target.getName()));

		// Reply message
		assertEquals("Message does not exist", "compute_reply", interaction.getMessages().get(1).getName());
		assertTrue(fragments.get(3) instanceof MessageOccurrenceSpecification);
		assertEquals("compute_reply_sender", fragments.get(3).getName());
		assertTrue(fragments.get(4) instanceof MessageOccurrenceSpecification);
		assertEquals("compute_reply_receiver", fragments.get(4).getName());
		assertEquals(((Message)interaction.getMessages().get(1)).getSendEvent(), fragments.get(3));
		assertEquals(((Message)interaction.getMessages().get(1)).getReceiveEvent(), fragments.get(4));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(3)).getCovered(target.getName()));
		assertNotNull(((MessageOccurrenceSpecification)fragments.get(4)).getCovered(source.getName()));

		// Execution
		assertEquals("Opaque behavior does not exist", "compute", interaction.getOwnedBehaviors().get(0)
				.getName());
		assertTrue(fragments.get(2) instanceof BehaviorExecutionSpecification);
		assertEquals("compute", fragments.get(2).getName());
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getStart(), fragments.get(1));
		assertEquals(((BehaviorExecutionSpecification)fragments.get(2)).getFinish(), fragments.get(3));
		assertNotNull(((BehaviorExecutionSpecification)fragments.get(2)).getCovered(target.getName()));
		assertEquals(interaction.getOwnedBehaviors().get(0),
				((BehaviorExecutionSpecification)fragments.get(2)).getBehavior());
	}

	/**
	 * Test
	 * {@link SequenceServices#createSynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createSynchronousMessage} service. Create a synchronous message between an existing execution and a
	 * lifeline. An execution exists on the source which already defines an asynchronous message.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testImportSynchronousMessageOnExecution2() throws Exception {
		// Interaction Scenario_8
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_8");
		final Lifeline source = interaction.getLifeline("consumers");
		// SourceFragment compute execution
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("compute");
		// TargetFragment producers lifeline
		final Lifeline target = interaction.getLifeline("producers");
		// Operation produce
		final Operation operation = (Operation)target.getRepresents().getType().getOwnedElements().get(0);
		// StratingEndPredecessor get_finish execution occurrence
		// FinishindEnPredecessor get_finish execution occurrence
		final ExecutionSpecification execution2 = (ExecutionSpecification)interaction.getFragment("get");

		sequenceServices.createSynchronousMessage(interaction, execution, target, operation,
				execution2.getFinish(), execution2.getFinish());

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(12, fragments.size());

		final InteractionFragment computeStart = fragments.get(0);
		final InteractionFragment compute = fragments.get(1);
		final InteractionFragment getSend = fragments.get(2);
		final InteractionFragment getReceive = fragments.get(3);
		final InteractionFragment get = fragments.get(4);
		final InteractionFragment getFinish = fragments.get(5);
		final InteractionFragment produceSend = fragments.get(6);
		final InteractionFragment produceReceive = fragments.get(7);
		final InteractionFragment produce = fragments.get(8);
		final InteractionFragment produceReplySend = fragments.get(9);
		final InteractionFragment produceReplyReceive = fragments.get(10);
		final InteractionFragment computeFinish = fragments.get(11);

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
		assertNotNull(((BehaviorExecutionSpecification)compute).getCovered(source.getName()));
		assertEquals(computeBehavior, ((BehaviorExecutionSpecification)compute).getBehavior());

		// Asynchronous message get
		final Message messageGet = interaction.getMessages().get(0);
		assertEquals("Message does not exist", "get", messageGet.getName());
		assertTrue(getSend instanceof MessageOccurrenceSpecification);
		assertEquals("get_sender", getSend.getName());
		assertTrue(getReceive instanceof MessageOccurrenceSpecification);
		assertEquals("get_receiver", getReceive.getName());
		assertEquals(messageGet.getSendEvent(), getSend);
		assertEquals(messageGet.getReceiveEvent(), getReceive);
		assertNotNull(((MessageOccurrenceSpecification)getSend).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)getReceive).getCovered(target.getName()));

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
		assertNotNull(((BehaviorExecutionSpecification)get).getCovered(target.getName()));
		assertEquals(getBehavior, ((BehaviorExecutionSpecification)get).getBehavior());

		// Synchronous message produce
		final Message messageProduce = interaction.getMessages().get(1);
		assertEquals("Message does not exist", "produce", messageProduce.getName());
		assertTrue(produceSend instanceof MessageOccurrenceSpecification);
		assertEquals("produce_sender", produceSend.getName());
		assertTrue(produceReceive instanceof MessageOccurrenceSpecification);
		assertEquals("produce_receiver", produceReceive.getName());
		assertEquals(messageProduce.getSendEvent(), produceSend);
		assertEquals(messageProduce.getReceiveEvent(), produceReceive);
		assertNotNull(((MessageOccurrenceSpecification)produceSend).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)produceReceive).getCovered(target.getName()));

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
		assertNotNull(((BehaviorExecutionSpecification)produce).getCovered(target.getName()));
		assertEquals(produceBehavior, ((BehaviorExecutionSpecification)produce).getBehavior());

		// Synchronous message reply produce
		final Message messageReplyProduce = interaction.getMessages().get(2);
		assertEquals("Message does not exist", "produce_reply", messageReplyProduce.getName());
		assertTrue(produceReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("produce_reply_sender", produceReplySend.getName());
		assertTrue(produceReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals("produce_reply_receiver", produceReplyReceive.getName());
		assertEquals(messageReplyProduce.getSendEvent(), produceReplySend);
		assertEquals(messageReplyProduce.getReceiveEvent(), produceReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)produceReplySend).getCovered(target.getName()));
		assertNotNull(((MessageOccurrenceSpecification)produceReplyReceive).getCovered(source.getName()));
	}

	/**
	 * Test
	 * {@link SequenceServices#createSynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createSynchronousMessage} service. Create a synchronous message between an existing execution and a
	 * lifeline. An execution exists on the source which already defines an asynchronous message.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testImportAsynchronousMessageOnExecution2() throws Exception {
		// Interaction Scenario_9
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_9");
		final Lifeline source = interaction.getLifeline("consumers");
		// SourceFragment compute execution
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("compute");
		// TargetFragment producers lifeline
		final Lifeline target = interaction.getLifeline("producers");
		// Operation produce
		final Operation operation = (Operation)target.getRepresents().getType().getOwnedElements().get(0);
		// StratingEndPredecessor get_reply_receiver execution occurrence
		// FinishindEnPredecessor get_reply_receiver execution occurrence
		final MessageOccurrenceSpecification messageReplyReceive = (MessageOccurrenceSpecification)interaction
				.getFragment("get_reply_receiver");

		sequenceServices.createAsynchronousMessage(interaction, execution, target, operation,
				messageReplyReceive, messageReplyReceive);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(12, fragments.size());

		final InteractionFragment computeStart = fragments.get(0);
		final InteractionFragment compute = fragments.get(1);
		final InteractionFragment getSend = fragments.get(2);
		final InteractionFragment getReceive = fragments.get(3);
		final InteractionFragment get = fragments.get(4);
		final InteractionFragment getReplySend = fragments.get(5);
		final InteractionFragment getReplyReceive = fragments.get(6);
		final InteractionFragment produceSend = fragments.get(7);
		final InteractionFragment produceReceive = fragments.get(8);
		final InteractionFragment produce = fragments.get(9);
		final InteractionFragment produceFinish = fragments.get(10);
		final InteractionFragment computeFinish = fragments.get(11);

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
		assertNotNull(((BehaviorExecutionSpecification)compute).getCovered(source.getName()));
		assertEquals(computeBehavior, ((BehaviorExecutionSpecification)compute).getBehavior());

		// Synchronous message get
		final Message messageGet = interaction.getMessages().get(0);
		assertEquals("Message does not exist", "get", messageGet.getName());
		assertTrue(getSend instanceof MessageOccurrenceSpecification);
		assertEquals("get_sender", getSend.getName());
		assertTrue(getReceive instanceof MessageOccurrenceSpecification);
		assertEquals("get_receiver", getReceive.getName());
		assertEquals(messageGet.getSendEvent(), getSend);
		assertEquals(messageGet.getReceiveEvent(), getReceive);
		assertNotNull(((MessageOccurrenceSpecification)getSend).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)getReceive).getCovered(target.getName()));

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
		assertNotNull(((BehaviorExecutionSpecification)get).getCovered(target.getName()));
		assertEquals(getBehavior, ((BehaviorExecutionSpecification)get).getBehavior());

		// Synchronous message reply get
		final Message messageReplyGet = interaction.getMessages().get(1);
		assertEquals("Message does not exist", "get_reply", messageReplyGet.getName());
		assertTrue(getReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("get_reply_sender", getReplySend.getName());
		assertTrue(getReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals("get_reply_receiver", getReplyReceive.getName());
		assertEquals(messageReplyGet.getSendEvent(), getReplySend);
		assertEquals(messageReplyGet.getReceiveEvent(), getReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)getReplySend).getCovered(target.getName()));
		assertNotNull(((MessageOccurrenceSpecification)getReplyReceive).getCovered(source.getName()));

		// Asynchronous message produce
		final Message messageProduce = interaction.getMessages().get(2);
		assertEquals("Message does not exist", "produce", messageProduce.getName());
		assertTrue(produceSend instanceof MessageOccurrenceSpecification);
		assertEquals("produce_sender", produceSend.getName());
		assertTrue(produceReceive instanceof MessageOccurrenceSpecification);
		assertEquals("produce_receiver", produceReceive.getName());
		assertEquals(messageProduce.getSendEvent(), produceSend);
		assertEquals(messageProduce.getReceiveEvent(), produceReceive);
		assertNotNull(((MessageOccurrenceSpecification)produceSend).getCovered(source.getName()));
		assertNotNull(((MessageOccurrenceSpecification)produceReceive).getCovered(target.getName()));

		// Execution produce
		final Behavior produceBehavior = interaction.getOwnedBehaviors().get(2);
		assertEquals("Opaque behavior does not exist", "produce", produceBehavior.getName());
		assertTrue(produceReceive instanceof MessageOccurrenceSpecification);
		assertEquals("produce_receiver", produceReceive.getName());
		assertTrue(produceFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("produce_finish", produceFinish.getName());
		assertTrue(produce instanceof BehaviorExecutionSpecification);
		assertEquals("produce", produce.getName());
		assertEquals(((BehaviorExecutionSpecification)produce).getStart(), produceReceive);
		assertEquals(((BehaviorExecutionSpecification)produce).getFinish(), produceFinish);
		assertNotNull(((BehaviorExecutionSpecification)produce).getCovered(target.getName()));
		assertEquals(produceBehavior, ((BehaviorExecutionSpecification)produce).getBehavior());
	}

	/**
	 * Test {@link SequenceServices#reorder(ExecutionSpecification, InteractionFragment, InteractionFragment)
	 * reorder} service. Reorder two executions on the same lifeline. Move the second execution before the
	 * previous on the lifeline.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testReorderExecutions() throws Exception {
		// Interaction Scenario_10
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_10");
		final Lifeline lifeline = interaction.getLifeline("producers");

		// Execution to move produce
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("produce");
		// StartingEndPredecessorAfter get_start execution occurrence
		final ExecutionOccurrenceSpecification startingEndPredecessorAfter = null;
		// FinishingEndPredecessorAfter produce_start execution occurrence
		final ExecutionOccurrenceSpecification finishingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("produce_start");

		sequenceServices.reorder(execution, startingEndPredecessorAfter, finishingEndPredecessorAfter);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(12, fragments.size());

		final InteractionFragment produceStart = fragments.get(0);
		final InteractionFragment produce = fragments.get(1);
		final InteractionFragment produceFinish = fragments.get(2);
		final InteractionFragment getStart = fragments.get(3);
		final InteractionFragment get = fragments.get(4);
		final InteractionFragment getFinish = fragments.get(5);

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
	 * reorder} service. Reorder two executions on different lifelines. Move the third execution after the
	 * fourth.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testReorderExecutions5() throws Exception {
		// Interaction Scenario_12
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_12");
		final Lifeline producers = interaction.getLifeline("producers");
		final Lifeline consumers = interaction.getLifeline("consumers");

		// Execution to move compute
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("compute");
		// StartingEndPredecessorAfter BehaviorExecution_2_finish execution occurrence
		final ExecutionOccurrenceSpecification startingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("BehaviorExecution_2_finish");
		// FinishingEndPredecessorAfter produce_reply_receiver execution occurrence
		final MessageOccurrenceSpecification finishingEndPredecessorAfter = (MessageOccurrenceSpecification)interaction
				.getFragment("produce_reply_receiver");

		sequenceServices.reorder(execution, startingEndPredecessorAfter, finishingEndPredecessorAfter);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(22, fragments.size());

		final InteractionFragment behavior2Start = fragments.get(0);
		final InteractionFragment behavior2 = fragments.get(1);
		final InteractionFragment behavior2Finish = fragments.get(2);
		final InteractionFragment computeStart = fragments.get(3);
		final InteractionFragment compute = fragments.get(4);
		final InteractionFragment behavior1Start = fragments.get(5);
		final InteractionFragment behavior1 = fragments.get(6);
		final InteractionFragment msg3Send = fragments.get(7);
		final InteractionFragment msg3Receive = fragments.get(8);
		final InteractionFragment msg3ReplySend = fragments.get(9);
		final InteractionFragment msg3ReplyReceive = fragments.get(10);
		final InteractionFragment behavior1Finish = fragments.get(11);
		final InteractionFragment getSend = fragments.get(12);
		final InteractionFragment getReceive = fragments.get(13);
		final InteractionFragment get = fragments.get(14);
		final InteractionFragment getFinish = fragments.get(15);
		final InteractionFragment produceSend = fragments.get(16);
		final InteractionFragment produceReceive = fragments.get(17);
		final InteractionFragment produce = fragments.get(18);
		final InteractionFragment produceReplySend = fragments.get(19);
		final InteractionFragment produceReplyReceive = fragments.get(20);
		final InteractionFragment computeFinish = fragments.get(21);

		// Execution BehaviorExecution_2
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
		assertNotNull(((BehaviorExecutionSpecification)behavior2).getCovered(consumers.getName()));
		assertEquals(behavior2Behavior, ((BehaviorExecutionSpecification)behavior2).getBehavior());

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

		// Execution BehaviorExecution_1
		final Behavior behavior1Behavior = interaction.getOwnedBehaviors().get(1);
		assertEquals("Opaque behavior does not exist", "BehaviorExecution_1", behavior1Behavior.getName());
		assertTrue(behavior1Start instanceof ExecutionOccurrenceSpecification);
		assertEquals("BehaviorExecution_1_start", behavior1Start.getName());
		assertTrue(behavior1Finish instanceof ExecutionOccurrenceSpecification);
		assertEquals("BehaviorExecution_1_finish", behavior1Finish.getName());
		assertTrue(behavior1 instanceof BehaviorExecutionSpecification);
		assertEquals("BehaviorExecution_1", behavior1.getName());
		assertEquals(((BehaviorExecutionSpecification)behavior1).getStart(), behavior1Start);
		assertEquals(((BehaviorExecutionSpecification)behavior1).getFinish(), behavior1Finish);
		assertNotNull(((BehaviorExecutionSpecification)behavior1).getCovered(consumers.getName()));
		assertEquals(behavior1Behavior, ((BehaviorExecutionSpecification)behavior1).getBehavior());

		// Message Message_3
		final Message msg3 = interaction.getMessages().get(3);
		assertEquals("Message does not exist", "Message_3", msg3.getName());
		assertTrue(msg3Send instanceof MessageOccurrenceSpecification);
		assertEquals("Message_3_sender", msg3Send.getName());
		assertTrue(msg3Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_3_receiver", msg3Receive.getName());
		assertEquals(msg3.getSendEvent(), msg3Send);
		assertEquals(msg3.getReceiveEvent(), msg3Receive);
		assertNotNull(((MessageOccurrenceSpecification)msg3Send).getCovered(consumers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg3Receive).getCovered(producers.getName()));

		// Message Message_3_reply
		final Message msg3Reply = interaction.getMessages().get(4);
		assertEquals("Message does not exist", "Message_3_reply", msg3Reply.getName());
		assertTrue(msg3ReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("Message_3_reply_sender", msg3ReplySend.getName());
		assertTrue(msg3ReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_3_reply_receiver", msg3ReplyReceive.getName());
		assertEquals(msg3Reply.getSendEvent(), msg3ReplySend);
		assertEquals(msg3Reply.getReceiveEvent(), msg3ReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)msg3ReplySend).getCovered(producers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg3ReplyReceive).getCovered(consumers.getName()));

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
		final Behavior getBehavior = interaction.getOwnedBehaviors().get(3);
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

		// Message produce
		final Message produceMessage = interaction.getMessages().get(1);
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
		final Behavior produceBehavior = interaction.getOwnedBehaviors().get(4);
		assertEquals("Opaque behavior does not exist", "produce", produceBehavior.getName());
		assertTrue(produceSend instanceof MessageOccurrenceSpecification);
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
		final Message produceReplyMessage = interaction.getMessages().get(2);
		assertEquals("Message does not exist", "produce_reply", produceReplyMessage.getName());
		assertTrue(produceReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("produce_reply_sender", produceReplySend.getName());
		assertTrue(produceReplyReceive instanceof MessageOccurrenceSpecification);
		assertEquals("produce_reply_receiver", produceReplyReceive.getName());
		assertEquals(produceReplyMessage.getSendEvent(), produceReplySend);
		assertEquals(produceReplyMessage.getReceiveEvent(), produceReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)produceReplySend).getCovered(producers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)produceReplyReceive).getCovered(consumers.getName()));
	}

	/**
	 * Test {@link SequenceServices#reorder(ExecutionSpecification, InteractionFragment, InteractionFragment)
	 * reorder} service. Reorder two executions on different lifelines. Move the first execution in order that
	 * executions are mixed, ie : second execution start > first execution start > second execution end >
	 * first execution end.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testReorderExecutions6() throws Exception {
		// Interaction Scenario_15
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_15");
		final Lifeline lifeline1 = interaction.getLifeline("Lifeline_1");
		final Lifeline lifeline2 = interaction.getLifeline("Lifeline_2");

		// Execution to move A
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("A");
		// StartingEndPredecessorAfter B_start execution occurrence
		final ExecutionOccurrenceSpecification startingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("B_start");
		// FinishingEndPredecessorAfter B_finish execution occurrence
		final ExecutionOccurrenceSpecification finishingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("B_finish");

		sequenceServices.reorder(execution, startingEndPredecessorAfter, finishingEndPredecessorAfter);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(6, fragments.size());

		final InteractionFragment bStart = fragments.get(0);
		final InteractionFragment b = fragments.get(1);
		final InteractionFragment aStart = fragments.get(2);
		final InteractionFragment a = fragments.get(3);
		final InteractionFragment bFinish = fragments.get(4);
		final InteractionFragment aFinish = fragments.get(5);

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
	 * Test {@link SequenceServices#reorder(ExecutionSpecification, InteractionFragment, InteractionFragment)
	 * reorder} service. Reorder two executions on different lifelines. Move the execution A after execution
	 * B, the sub-execution C should be also moved.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testReorderExecutions8() throws Exception {
		// Interaction Scenario_16
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_16");
		final Lifeline lifeline1 = interaction.getLifeline("Lifeline_1");
		final Lifeline lifeline2 = interaction.getLifeline("Lifeline_2");

		// Execution to move A
		final ExecutionSpecification execution = (ExecutionSpecification)interaction.getFragment("A");
		// StartingEndPredecessorAfter B_finish execution occurrence
		final ExecutionOccurrenceSpecification startingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("B_finish");
		// FinishingEndPredecessorAfter C_finish execution occurrence
		final ExecutionOccurrenceSpecification finishingEndPredecessorAfter = (ExecutionOccurrenceSpecification)interaction
				.getFragment("C_finish");

		sequenceServices.reorder(execution, startingEndPredecessorAfter, finishingEndPredecessorAfter);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(9, fragments.size());

		final InteractionFragment bStart = fragments.get(0);
		final InteractionFragment b = fragments.get(1);
		final InteractionFragment bFinish = fragments.get(2);
		final InteractionFragment aStart = fragments.get(3);
		final InteractionFragment a = fragments.get(4);
		final InteractionFragment cStart = fragments.get(5);
		final InteractionFragment c = fragments.get(6);
		final InteractionFragment cFinish = fragments.get(7);
		final InteractionFragment aFinish = fragments.get(8);

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

		// Execution C
		final Behavior cBehavior = interaction.getOwnedBehaviors().get(2);
		assertEquals("Opaque behavior does not exist", "C", cBehavior.getName());
		assertTrue(cStart instanceof ExecutionOccurrenceSpecification);
		assertEquals("C_start", cStart.getName());
		assertTrue(cFinish instanceof ExecutionOccurrenceSpecification);
		assertEquals("C_finish", cFinish.getName());
		assertTrue(c instanceof BehaviorExecutionSpecification);
		assertEquals("C", c.getName());
		assertEquals(((BehaviorExecutionSpecification)c).getStart(), cStart);
		assertEquals(((BehaviorExecutionSpecification)c).getFinish(), cFinish);
		assertNotNull(((BehaviorExecutionSpecification)c).getCovered(lifeline1.getName()));
		assertEquals(cBehavior, ((BehaviorExecutionSpecification)c).getBehavior());

	}

	/**
	 * Test {@link SequenceServices#reorder(Message, InteractionFragment, InteractionFragment) reorder}
	 * service. Reorder two messages on the same lifelines. Move the first message after the previous on the
	 * lifeline.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testReorderMessages() throws Exception {
		// Interaction Scenario_11
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_11");
		final Lifeline producers = interaction.getLifeline("producers");
		final Lifeline consumers = interaction.getLifeline("consumers");

		// Message to move Message_0
		final Message message = (Message)interaction.getMessage("Message_0");
		// StartingEndPredecessorAfter Message_1_receiver message occurrence
		final MessageOccurrenceSpecification startingEndPredecessorAfter = (MessageOccurrenceSpecification)interaction
				.getFragment("Message_1_receiver");
		// FinishingEndPredecessorAfter Message_0_sender message occurrence
		final MessageOccurrenceSpecification finishingEndPredecessorAfter = (MessageOccurrenceSpecification)interaction
				.getFragment("Message_0_sender");

		sequenceServices.reorder(message, startingEndPredecessorAfter, finishingEndPredecessorAfter);

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(4, fragments.size());

		final InteractionFragment msg1Send = fragments.get(0);
		final InteractionFragment msg1Receive = fragments.get(1);
		final InteractionFragment msg0Send = fragments.get(2);
		final InteractionFragment msg0Receive = fragments.get(3);

		// Message Message_1
		final Message msg1 = interaction.getMessages().get(1);
		assertEquals("Message does not exist", "Message_1", msg1.getName());
		assertTrue(msg1Send instanceof MessageOccurrenceSpecification);
		assertEquals("Message_1_sender", msg1Send.getName());
		assertTrue(msg1Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_1_receiver", msg1Receive.getName());
		assertEquals(msg1.getSendEvent(), msg1Send);
		assertEquals(msg1.getReceiveEvent(), msg1Receive);
		assertNotNull(((MessageOccurrenceSpecification)msg1Send).getCovered(consumers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg1Receive).getCovered(producers.getName()));

		// Message Message_0
		final Message msg0 = interaction.getMessages().get(0);
		assertEquals("Message does not exist", "Message_0", msg0.getName());
		assertTrue(msg0Send instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_sender", msg0Send.getName());
		assertTrue(msg1Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_receiver", msg0Receive.getName());
		assertEquals(msg0.getSendEvent(), msg0Send);
		assertEquals(msg0.getReceiveEvent(), msg0Receive);
		assertNotNull(((MessageOccurrenceSpecification)msg0Send).getCovered(consumers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg0Receive).getCovered(producers.getName()));
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
	 * Test
	 * {@link SequenceServices#createAsynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createAsynchronousMessage} service. Create an asynchronous message between a lifeline and an existing
	 * execution.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateAsynchronousMessageToExecution() throws Exception {
		// Interaction Scenario_3
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_3");
		final Lifeline producers = interaction.getLifeline("producers");
		final Lifeline consumers = interaction.getLifeline("consumers");

		// SourceFragment producers lifeline
		// TargetFragment compute behavior
		final ExecutionSpecification targetExecution = (ExecutionSpecification)interaction
				.getFragment("compute");
		// Operation null
		// StartingElementPredecessor compute_start execution occurrence
		// FinishingElementPredecessor compute_start execution occurrence
		sequenceServices.createAsynchronousMessage(interaction, producers, targetExecution,
				targetExecution.getStart(), targetExecution.getStart());

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(5, fragments.size());

		final InteractionFragment computeStart = fragments.get(0);
		final InteractionFragment compute = fragments.get(1);
		final InteractionFragment msg0Send = fragments.get(2);
		final InteractionFragment msg0Receive = fragments.get(3);
		final InteractionFragment computeFinish = fragments.get(4);

		// Message Message_0
		final Message msg0 = interaction.getMessages().get(0);
		assertEquals("Message does not exist", "Message_0", msg0.getName());
		assertTrue(msg0Send instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_sender", msg0Send.getName());
		assertTrue(msg0Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_receiver", msg0Receive.getName());
		assertEquals(msg0.getSendEvent(), msg0Send);
		assertEquals(msg0.getReceiveEvent(), msg0Receive);
		assertNotNull(((MessageOccurrenceSpecification)msg0Send).getCovered(producers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg0Receive).getCovered(consumers.getName()));

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
	 * Test
	 * {@link SequenceServices#createSynchronousMessage(Interaction, NamedElement, NamedElement, Operation, NamedElement, NamedElement)
	 * createSynchronousMessage} service. Create a synchronous message between a lifeline and an existing
	 * execution.
	 * 
	 * @throws Exception
	 *             in case of error
	 */
	public void testCreateSynchronousMessageToExecution() throws Exception {
		// Interaction Scenario_3
		final Interaction interaction = getInteraction(RESOURCE_URI, "Scenario_3");
		final Lifeline producers = interaction.getLifeline("producers");
		final Lifeline consumers = interaction.getLifeline("consumers");

		// SourceFragment producers lifeline
		// TargetFragment compute behavior
		final ExecutionSpecification targetExecution = (ExecutionSpecification)interaction
				.getFragment("compute");
		// Operation null
		// StartingElementPredecessor compute_start execution occurrence
		// FinishingElementPredecessor compute_start execution occurrence
		sequenceServices.createSynchronousMessage(interaction, producers, targetExecution,
				targetExecution.getStart(), targetExecution.getStart());

		final List<InteractionFragment> fragments = interaction.getFragments();
		assertEquals(7, fragments.size());

		final InteractionFragment computeStart = fragments.get(0);
		final InteractionFragment compute = fragments.get(1);
		final InteractionFragment msg0Send = fragments.get(2);
		final InteractionFragment msg0Receive = fragments.get(3);
		final InteractionFragment msg0ReplySend = fragments.get(4);
		final InteractionFragment msg0ReplyReceive = fragments.get(5);
		final InteractionFragment computeFinish = fragments.get(6);

		// Message Message_0
		final Message msg0 = interaction.getMessages().get(0);
		assertEquals("Message does not exist", "Message_0", msg0.getName());
		assertTrue(msg0Send instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_sender", msg0Send.getName());
		assertTrue(msg0Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_receiver", msg0Receive.getName());
		assertEquals(msg0.getSendEvent(), msg0Send);
		assertEquals(msg0.getReceiveEvent(), msg0Receive);
		assertNotNull(((MessageOccurrenceSpecification)msg0Send).getCovered(producers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg0Receive).getCovered(consumers.getName()));

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

		// Message Message_0_reply
		final Message msg0ReplyMessage = interaction.getMessages().get(1);
		assertEquals("Message does not exist", "Message_0_reply", msg0ReplyMessage.getName());
		assertTrue(msg0ReplySend instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_reply_sender", msg0ReplySend.getName());
		assertTrue(msg0Receive instanceof MessageOccurrenceSpecification);
		assertEquals("Message_0_reply_receiver", msg0ReplyReceive.getName());
		assertEquals(msg0ReplyMessage.getSendEvent(), msg0ReplySend);
		assertEquals(msg0ReplyMessage.getReceiveEvent(), msg0ReplyReceive);
		assertNotNull(((MessageOccurrenceSpecification)msg0ReplySend).getCovered(consumers.getName()));
		assertNotNull(((MessageOccurrenceSpecification)msg0ReplyReceive).getCovered(producers.getName()));
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
		assertEquals(1, elements.size());
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
