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
 * Unit tests on sequence services
 * 
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 */
public class SequenceServiceTests extends TestCase {
	private Interaction rootInteraction;
	
	private static final String RESOURCE_URI = Activator.PLUGIN_ID + "/resources/Test.uml";
	
	public SequenceServiceTests() {
		ResourceSet rset = new ResourceSetImpl();
		Resource resource = rset.getResource(URI.createPlatformPluginURI(RESOURCE_URI, true), true);
		
		rootInteraction = (Interaction) ((Model)resource.getContents().get(0)).getOwnedMember("testInteraction");
	}

	public void testExecutionSemanticCandidatesLifeline() {
		Lifeline linLifeline = rootInteraction.getLifeline("Lifeline_3");
		List<ExecutionSpecification> executions = SequenceServices.executionSemanticCandidates(linLifeline);
		
		assertEquals(2, executions.size());
		assertEquals("ActionExecution_1", executions.get(0).getName());
		assertEquals("ActionExecution_3", executions.get(1).getName());
	}

	public void testExecutionSemanticCandidatesExecutionSpecification() {
		ExecutionSpecification execution = (ExecutionSpecification) rootInteraction.getFragment("ActionExecution_3");
		List<ExecutionSpecification> executions = SequenceServices.executionSemanticCandidates(execution);
		
		assertEquals(2, executions.size());
		assertEquals("ActionExecution_4", executions.get(0).getName());
		assertEquals("ActionExecution_6", executions.get(1).getName());
	}
	
	public void testFindOccurenceSpecificationContext() throws Exception {
		OccurrenceSpecification executionOccurrence;
		
		executionOccurrence = (OccurrenceSpecification) rootInteraction.getFragment("Message_1_sender");
		assertEquals("Lifeline_1", SequenceServices.findOccurrenceSpecificationContext(executionOccurrence).getName());
		executionOccurrence = (OccurrenceSpecification) rootInteraction.getFragment("Message_1_receiver");
		assertEquals("Lifeline_2", SequenceServices.findOccurrenceSpecificationContext(executionOccurrence).getName());
		
		executionOccurrence = (OccurrenceSpecification) rootInteraction.getFragment("ActionExecution_2_start");
		assertEquals("Lifeline_2", SequenceServices.findOccurrenceSpecificationContext(executionOccurrence).getName());
		
		executionOccurrence = (OccurrenceSpecification) rootInteraction.getFragment("ActionExecution_4_start");
		assertEquals("ActionExecution_3", SequenceServices.findOccurrenceSpecificationContext(executionOccurrence).getName());
		
		executionOccurrence = (OccurrenceSpecification) rootInteraction.getFragment("Message_3_receiver");
		assertEquals("ActionExecution_3", SequenceServices.findOccurrenceSpecificationContext(executionOccurrence).getName());
		
		executionOccurrence = (OccurrenceSpecification) rootInteraction.getFragment("Message_4_sender");
		assertEquals("ActionExecution_5", SequenceServices.findOccurrenceSpecificationContext(executionOccurrence).getName());
	}

}
