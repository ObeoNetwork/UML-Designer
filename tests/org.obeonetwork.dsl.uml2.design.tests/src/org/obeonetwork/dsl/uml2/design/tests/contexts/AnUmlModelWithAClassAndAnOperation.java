/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/

package org.obeonetwork.dsl.uml2.design.tests.contexts;

import static org.junit.Assert.*;
	import org.obeonetwork.dsl.uml2.design.tests.automation.Context;

// Start of user code AnUmlModelWithAClassAndAnOperation imports
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.services.ActivityServices;

import java.util.Collections;
import java.util.List;
// End of user code

/**
 * Context : An Uml model with a class and an operation
 */
public class AnUmlModelWithAClassAndAnOperation extends Context {
// Start of user code AnUmlModelWithAClassAndAnOperation variables
	/**
	 * The services to test.
	 */
	private ActivityServices services;

	/**
	 * The activity used as test input.
	 */
	private Activity activityUnderTest;

	/**
	 * The operation.
	 */
	private Operation op;

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
	 * The partitions.
	 */
	ActivityPartition partition;
	ActivityPartition partition2;
	
	/**
	 * Activity node.
	 */
	ActivityNode node;
	/**
	 * Found nodes.
	 */
	List<ActivityNode> foundNodes;
	
	/**
	 * Action.
	 */
	Action action;
	// End of user code

	@Override
	public void setup() {
		// Start of user code AnUmlModelWithAClassAndAnOperation setup
		services = new ActivityServices();
		activityUnderTest = initMockActivityModel();
		final Class aClass = UMLFactory.eINSTANCE.createClass();
		op = UMLFactory.eINSTANCE.createOperation();
		op.setName("op");
		aClass.getOwnedOperations().add(op);
		// End of user code
	}

	@Override
	public void tearDown() {
		// Start of user code AnUmlModelWithAClassAndAnOperation tear down
		// Nothing
		// End of user code
	}
	/**
	 * Action : I create an output pin on an opaque action
	 */
	public void actionICreateAnOutputPinOnAnOpaqueAction() {
		// Start of user code ICreateAnOutputPinOnAnOpaqueAction
		action = UMLFactory.eINSTANCE.createOpaqueAction();
		services.createOutputPin(action);
		// End of user code
	}

	/**
	 * Action : I create an output pin on a call operation action
	 */
	public void actionICreateAnOutputPinOnACallOperationAction() {
		// Start of user code ICreateAnOutputPinOnACallOperationAction
		action = UMLFactory.eINSTANCE.createCallOperationAction();
		services.createOutputPin(action);
		// End of user code
	}

	/**
	 * Action : I create an input pin on a call operation action
	 */
	public void actionICreateAnInputPinOnACallOperationAction() {
		// Start of user code ICreateAnInputPinOnACallOperationAction
		action = UMLFactory.eINSTANCE.createCallOperationAction();
		services.createInputPin(action);
		// End of user code
	}

	/**
	 * Action : I drop a partition to an activity
	 */
	public void actionIDropAPartitionToAnActivity() {
		// Start of user code IDropAPartitionToAnActivity
		partition = activityUnderTest.getPartition(P1);
		partition2 = activityUnderTest.getPartition("p2");
		services.dropNode(activityUnderTest, partition);
		// End of user code
	}

	/**
	 * Action : I query the activity nodes of an activity partition
	 */
	public void actionIQueryTheActivityNodesOfAnActivityPartition() {
		// Start of user code IQueryTheActivityNodesOfAnActivityPartition
		node = activityUnderTest.getNode("InPartition1");
		foundNodes = services.getActivityNodes(activityUnderTest.getPartition(P1));
		// End of user code
	}

	/**
	 * Action : I query all the activity partitions of an activity
	 */
	public void actionIQueryAllTheActivityPartitionsOfAnActivity() {
		// Start of user code IQueryAllTheActivityPartitionsOfAnActivity
		partition = activityUnderTest.getPartition(P1);
		// End of user code
	}

	/**
	 * Action : I drop a partition to another partition
	 */
	public void actionIDropAPartitionToAnotherPartition() {
		// Start of user code IDropAPartitionToAnotherPartition
		partition = activityUnderTest.getPartition(P1);
		partition2 = activityUnderTest.getPartition("p2");
		services.dropNode(partition2, partition);		// End of user code
	}

	/**
	 * Action : I initialize an activity for an operation
	 */
	public void actionIInitializeAnActivityForAnOperation() {
		// Start of user code IInitializeAnActivityForAnOperation
		activityUnderTest = services.initActivityForOperation(op);
	}

	/**
	 * Behavior : An activity is created
	 */
	public void assertAnActivityIsCreated() {
		// Start of user code AnActivityIsCreated
		assertNotNull(activityUnderTest);
		assertEquals(activityUnderTest, op.getMethods().get(0));
		// End of user code
	}

	/**
	 * Action : I drop a node to an activity
	 */
	public void actionIDropANodeToAnActivity() {
		// Start of user code IDropANodeToAnActivity
		node = activityUnderTest.getNode(IN_ACTIVITY_ACTION);
		services.dropNode(activityUnderTest, node);
		// End of user code
	}

	/**
	 * Action : I query the activity nodes of an activity
	 */
	public void actionIQueryTheActivityNodesOfAnActivity() {
		// Start of user code IQueryTheActivityNodesOfAnActivity
		node = activityUnderTest.getNode(IN_ACTIVITY_ACTION);
		foundNodes = services.getActivityNodes(activityUnderTest);
		// End of user code
	}

	/**
	 * Action : I create an input pin on an opaque action
	 */
	public void actionICreateAnInputPinOnAnOpaqueAction() {
		// Start of user code ICreateAnInputPinOnAnOpaqueAction
		action = UMLFactory.eINSTANCE.createOpaqueAction();
		services.createInputPin(action);
		// End of user code
	}

	/**
	 * Action : I drop a node to a partition
	 */
	public void actionIDropANodeToAPartition() {
		// Start of user code IDropANodeToAPartition
		node = activityUnderTest.getNode(IN_ACTIVITY_ACTION);
		partition = activityUnderTest.getPartition(P1);
		
		services.dropNode(partition, node);
		// End of user code
	}

	/**
	 * Action : I query the parent activity of an activity partition
	 */
	public void actionIQueryTheParentActivityOfAnActivityPartition() {
		// Start of user code IQueryTheParentActivityOfAnActivityPartition
		partition = activityUnderTest.getPartition(P1).getSubpartition(SUB_P1);
		// End of user code
	}

	/**
	 * Behavior : An input pin is created on the opaque action
	 */
	public void assertAnInputPinIsCreatedOnTheOpaqueAction() {
		// Start of user code AnInputPinIsCreatedOnTheOpaqueAction
		assertFalse(((OpaqueAction)action).getInputValues().isEmpty());
		// End of user code
	}
	/**
	 * Behavior : An input pin is created on the call operation action
	 */
	public void assertAnInputPinIsCreatedOnTheCallOperationAction() {
		// Start of user code AnInputPinIsCreatedOnTheCallOperationAction
		assertFalse(((CallOperationAction)action).getArguments().isEmpty());
		// End of user code
	}
	/**
	 * Behavior : I get all the activity partitions defined for the activity
	 */
	public void assertIGetAllTheActivityPartitionsDefinedForTheActivity() {
		// Start of user code IGetAllTheActivityPartitionsDefinedForTheActivity
		assertEquals(partition.getSubpartitions(), services.getActivityPartitions(partition));

		assertNull(services.getActivityPartitions(UMLFactory.eINSTANCE.createClass()));
		// End of user code
	}
	/**
	 * Behavior : The activity contains the partition
	 */
	public void assertTheActivityContainsThePartition() {
		// Start of user code TheActivityContainsThePartition
		assertFalse(partition2.getSubpartitions().contains(partition));
		assertTrue(activityUnderTest.getPartitions().contains(partition));
		// End of user code
	}
	/**
	 * Behavior : I get all the parent activity nodes
	 */
	public void assertIGetAllTheParentActivityNodes() {
		// Start of user code IGetAllTheParentActivityNodes
		assertEquals(1, foundNodes.size());
		assertEquals(node, foundNodes.get(0));
		// End of user code
	}
	/**
	 * Behavior : An output pin is created on the opaque action
	 */
	public void assertAnOutputPinIsCreatedOnTheOpaqueAction() {
		// Start of user code AnOutputPinIsCreatedOnTheOpaqueAction
		assertFalse(((OpaqueAction)action).getOutputValues().isEmpty());
		// End of user code
	}
	/**
	 * Behavior : An output pin is created on the call operation action
	 */
	public void assertAnOutputPinIsCreatedOnTheCallOperationAction() {
		// Start of user code AnOutputPinIsCreatedOnTheCallOperationAction
		assertFalse(((CallOperationAction)action).getResults().isEmpty());
		// End of user code
	}
	/**
	 * Behavior : The parent partition contains the dropped partition
	 */
	public void assertTheParentPartitionContainsTheDroppedPartition() {
		// Start of user code TheParentPartitionContainsTheDroppedPartition
		assertTrue(partition2.getSubpartitions().contains(partition));
		assertFalse(activityUnderTest.getPartitions().contains(partition));
		// End of user code
	}
	/**
	 * Behavior : The activity contains the node
	 */
	public void assertTheActivityContainsTheNode() {
		// Start of user code TheActivityContainsTheNode
		assertTrue(node.getInPartitions().isEmpty());
		// End of user code
	}
	/**
	 * Behavior : An activity which referenced the operation is created
	 */
	public void assertAnActivityWhichReferencedTheOperationIsCreated() {
		// Start of user code AnActivityWhichReferencedTheOperationIsCreated
		assertNotNull(activityUnderTest);
		assertEquals(activityUnderTest, op.getMethods().get(0));
		// End of user code
	}
	/**
	 * Behavior : The partition contains the node
	 */
	public void assertThePartitionContainsTheNode() {
		// Start of user code ThePartitionContainsTheNode
		assertTrue(partition.getNodes().contains(node));
		// End of user code
	}
	/**
	 * Behavior : I get all the parent activity partition nodes
	 */
	public void assertIGetAllTheParentActivityPartitionNodes() {
		// Start of user code IGetAllTheParentActivityPartitionNodes
		assertEquals(1, foundNodes.size());
		assertEquals(node, foundNodes.get(0));
		
		assertEquals(Collections.EMPTY_LIST, services.getActivityNodes(UMLFactory.eINSTANCE.createClass()));
		// End of user code
	}
	/**
	 * Behavior : I get the parent activity
	 */
	public void assertIGetTheParentActivity() {
		// Start of user code IGetTheParentActivity
		assertEquals(activityUnderTest, services.findParentActivity(partition));
		
		assertNull(services.findParentActivity(UMLFactory.eINSTANCE.createClass()));
		// End of user code
	}

// Start of user code AnUmlModelWithAClassAndAnOperation private methods
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
	// End of user code
}
