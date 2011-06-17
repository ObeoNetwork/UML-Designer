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
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OccurrenceSpecification;
import org.obeonetwork.dsl.uml2.design.services.SequenceServices;
import org.obeonetwork.dsl.uml2.design.tests.Activator;

/**
 * Unit tests on sequence services.
 * 
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 */
public class SequenceServiceTests extends TestCase {
	/**
	 * Action execution 3.
	 */
	private static final String ACTION_EXECUTION_3 = "ActionExecution_3";

	/**
	 * The test model URI.
	 */
	private static final String RESOURCE_URI = Activator.PLUGIN_ID + "/resources/Test.uml";
	
	/**
	 * The root interaction.
	 */
	private Interaction rootInteraction;

	/**
	 * The sequence services instance.
	 */
	private SequenceServices sequenceServices;

	/**
	 * Constructor.
	 */
	public SequenceServiceTests() {
		final ResourceSet rset = new ResourceSetImpl();
		final Resource resource = rset.getResource(URI.createPlatformPluginURI(RESOURCE_URI, true), true);

		rootInteraction = (Interaction)((Model)resource.getContents().get(0))
				.getOwnedMember("testInteraction");
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
	 * Test the executionSemanticCandidates() service against {@link Lifeline}.
	 */
	public void testExecutionSemanticCandidatesLifeline() {
		final Lifeline linLifeline = rootInteraction.getLifeline("Lifeline_3");
		final List<ExecutionSpecification> executions = sequenceServices.executionSemanticCandidates(linLifeline);

		assertEquals(2, executions.size());
		assertEquals("ActionExecution_1", executions.get(0).getName());
		assertEquals(ACTION_EXECUTION_3, executions.get(1).getName());
	}

	/**
	 * Test the executionSemanticCandidates() service against {@link ExecutionSpecification}.
	 */
	public void testExecutionSemanticCandidatesExecutionSpecification() {
		final ExecutionSpecification execution = (ExecutionSpecification)rootInteraction
				.getFragment(ACTION_EXECUTION_3);
		final List<ExecutionSpecification> executions = sequenceServices.executionSemanticCandidates(execution);

		assertEquals(2, executions.size());
		assertEquals("ActionExecution_4", executions.get(0).getName());
		assertEquals("ActionExecution_6", executions.get(1).getName());
	}

	/**
	 * Test findOccurrenceSpecificationContext() service.
	 * @throws Exception in case of error.
	 */
	public void testFindOccurenceSpecificationContext() throws Exception {
		OccurrenceSpecification executionOccurrence;

		executionOccurrence = (OccurrenceSpecification)rootInteraction.getFragment("Message_1_sender");
		assertEquals("Lifeline_1", sequenceServices.findOccurrenceSpecificationContext(executionOccurrence)
				.getName());
		executionOccurrence = (OccurrenceSpecification)rootInteraction.getFragment("Message_1_receiver");
		assertEquals("Lifeline_2", sequenceServices.findOccurrenceSpecificationContext(executionOccurrence)
				.getName());

		executionOccurrence = (OccurrenceSpecification)rootInteraction.getFragment("ActionExecution_2_start");
		assertEquals("Lifeline_2", sequenceServices.findOccurrenceSpecificationContext(executionOccurrence)
				.getName());

		executionOccurrence = (OccurrenceSpecification)rootInteraction.getFragment("ActionExecution_4_start");
		assertEquals(ACTION_EXECUTION_3,
				sequenceServices.findOccurrenceSpecificationContext(executionOccurrence).getName());

		executionOccurrence = (OccurrenceSpecification)rootInteraction.getFragment("Message_3_receiver");
		assertEquals(ACTION_EXECUTION_3,
				sequenceServices.findOccurrenceSpecificationContext(executionOccurrence).getName());

		executionOccurrence = (OccurrenceSpecification)rootInteraction.getFragment("Message_4_sender");
		assertEquals("ActionExecution_5",
				sequenceServices.findOccurrenceSpecificationContext(executionOccurrence).getName());
	}

}
