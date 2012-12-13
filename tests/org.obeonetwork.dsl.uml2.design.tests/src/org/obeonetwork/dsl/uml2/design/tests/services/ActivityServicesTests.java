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

import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLFactory;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.services.ActivityServices;

/**
 * Test case for {@link ActivityServices}.
 *
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 *
 */
public class ActivityServicesTests extends TestCase {
	/**
	 * The name of the action located at {@link Activity} root.
	 */
	private static final String IN_ACTIVITY_ACTION = "InActivityAction";

	/**
	 * SubP1 partition name.
	 */
	private static final String SUB_P1 = "subP1";

	/**
	 * P1 partition name.
	 */
	private static final String P1 = "p1";

	/**
	 * The services to test.
	 */
	private ActivityServices services;
	
	/**
	 * The activity used as test input.
	 */
	private Activity activityUnderTest;

	/**
	 * Setup tests.
	 * 
	 * @throws java.lang.Exception in case something happens
	 */
	@Before
	public void setUp() throws Exception {
		services = new ActivityServices();
		activityUnderTest = initMockActivityModel();
	}
	
	/**
	 * Initialize a mock {@link Activity} used for testing purpose.
	 * 
	 * @return the created {@link Activity}
	 */
	private static Activity initMockActivityModel() {
		final Activity activity = UMLFactory.eINSTANCE.createActivity();
		
		ActivityPartition partition = UMLFactory.eINSTANCE.createActivityPartition();
		partition.setName(P1);
		activity.getPartitions().add(partition);
		
		OpaqueAction action = UMLFactory.eINSTANCE.createOpaqueAction();
		action.setName(IN_ACTIVITY_ACTION);
		activity.getOwnedNodes().add(action);
		
		action = UMLFactory.eINSTANCE.createOpaqueAction();
		action.setName("InPartition1");
		activity.getOwnedNodes().add(action);
		partition.getNodes().add(action);
		
		final ActivityPartition subPartition = UMLFactory.eINSTANCE.createActivityPartition();
		subPartition.setName(SUB_P1);
		partition.getSubpartitions().add(subPartition);
		
		partition = UMLFactory.eINSTANCE.createActivityPartition();
		partition.setName("p2");
		activity.getPartitions().add(partition);
		
		return activity;
	}

	/**
	 * Test method for {@link org.obeonetwork.dsl.uml2.design.services.ActivityServices#initActivityForOperation(org.eclipse.uml2.uml.Operation)}.
	 */
	@Test
	public void testInitActivityForOperation() {
		final Class aClass = UMLFactory.eINSTANCE.createClass();
		final Operation op = UMLFactory.eINSTANCE.createOperation();
		op.setName("op");
		aClass.getOwnedOperations().add(op);
		
		final Activity activity = services.initActivityForOperation(op);
		assertNotNull(activity);
		assertEquals(activity, op.getMethods().get(0));
		
		assertEquals(activity, services.initActivityForOperation(op));
	}
	
	/**
	 * Test method for {@link org.obeonetwork.dsl.uml2.design.services.ActivityServices#getActivityPartitions(org.eclipse.uml2.uml.Element)}.
	 */
	@Test
	public void testGetActivityPartitions() {
		assertEquals(activityUnderTest.getPartitions(), services.getActivityPartitions(activityUnderTest));
		
		final ActivityPartition partition = activityUnderTest.getPartition(P1);
		assertEquals(partition.getSubpartitions(), services.getActivityPartitions(partition));
		
		assertNull(services.getActivityPartitions(UMLFactory.eINSTANCE.createClass()));
	}

	/**
	 * Test method for {@link org.obeonetwork.dsl.uml2.design.services.ActivityServices#findParentActivity(org.eclipse.uml2.uml.Element)}.
	 */
	@Test
	public void testFindParentActivity() {
		assertEquals(activityUnderTest, services.findParentActivity(activityUnderTest));
		
		ActivityPartition partition = activityUnderTest.getPartition(P1);
		partition = partition.getSubpartition(SUB_P1);
		assertEquals(activityUnderTest, services.findParentActivity(partition));
		
		assertNull(services.findParentActivity(UMLFactory.eINSTANCE.createClass()));
	}

	/**
	 * Test method for {@link org.obeonetwork.dsl.uml2.design.services.ActivityServices#getActivityNodes(org.eclipse.uml2.uml.Element)}.
	 */
	@Test
	public void testGetActivityNodes() {
		ActivityNode node;
		List<ActivityNode> foundNodes;
		
		node = activityUnderTest.getNode(IN_ACTIVITY_ACTION);
		foundNodes = services.getActivityNodes(activityUnderTest);
		assertEquals(1, foundNodes.size());
		assertEquals(node, foundNodes.get(0));
		
		node = activityUnderTest.getNode("InPartition1");
		foundNodes = services.getActivityNodes(activityUnderTest.getPartition(P1));
		assertEquals(1, foundNodes.size());
		assertEquals(node, foundNodes.get(0));
		
		assertEquals(Collections.EMPTY_LIST, services.getActivityNodes(UMLFactory.eINSTANCE.createClass()));
	}

	/**
	 * Test method for {@link org.obeonetwork.dsl.uml2.design.services.ActivityServices#createInputPin(org.eclipse.uml2.uml.Action)}.
	 */
	@Test
	public void testCreateInputPin() {
		Action action;
		
		action = UMLFactory.eINSTANCE.createCallOperationAction();
		services.createInputPin(action);
		assertFalse(((CallOperationAction)action).getArguments().isEmpty());
		
		action = UMLFactory.eINSTANCE.createOpaqueAction();
		services.createInputPin(action);
		assertFalse(((OpaqueAction)action).getInputValues().isEmpty());
	}

	/**
	 * Test method for {@link org.obeonetwork.dsl.uml2.design.services.ActivityServices#createOutputPin(org.eclipse.uml2.uml.Action)}.
	 */
	@Test
	public void testCreateOutputPin() {
		Action action;
		
		action = UMLFactory.eINSTANCE.createCallOperationAction();
		services.createOutputPin(action);
		assertFalse(((CallOperationAction)action).getResults().isEmpty());
		
		action = UMLFactory.eINSTANCE.createOpaqueAction();
		services.createOutputPin(action);
		assertFalse(((OpaqueAction)action).getOutputValues().isEmpty());
	}

	/**
	 * Test method for {@link org.obeonetwork.dsl.uml2.design.services.ActivityServices#dropNode(org.eclipse.uml2.uml.Element, org.eclipse.uml2.uml.Element)}.
	 */
	@Test
	public void testDropNode() {
		final ActivityNode node = activityUnderTest.getNode(IN_ACTIVITY_ACTION);
		final ActivityPartition partition = activityUnderTest.getPartition(P1);
		
		services.dropNode(partition, node);
		assertTrue(partition.getNodes().contains(node));
		
		services.dropNode(activityUnderTest, node);
		assertTrue(node.getInPartitions().isEmpty());
		
		final ActivityPartition partition2 = activityUnderTest.getPartition("p2");
		services.dropNode(partition2, partition);
		assertTrue(partition2.getSubpartitions().contains(partition));
		assertFalse(activityUnderTest.getPartitions().contains(partition));
		
		services.dropNode(activityUnderTest, partition);
		assertFalse(partition2.getSubpartitions().contains(partition));
		assertTrue(activityUnderTest.getPartitions().contains(partition));
	}

}
