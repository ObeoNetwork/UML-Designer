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

package org.obeonetwork.dsl.uml2.design.tests.unit.stories.manageactivity;
import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.contexts.AnUmlModelWithAClassAndAnOperation;

public class AnUmlModelWithAClassAndAnOperationUnitTests {
	@Rule
	public AnUmlModelWithAClassAndAnOperation context = new AnUmlModelWithAClassAndAnOperation();

	@Test
	public void initActivityForAnOperation() throws Exception {
		context.actionIInitializeAnActivityForAnOperation();
		context.assertAnActivityWhichReferencedTheOperationIsCreated();
	}
	@Test
	public void getActivityPartitions() throws Exception {
		context.actionIQueryAllTheActivityPartitionsOfAnActivity();
		context.assertIGetAllTheActivityPartitionsDefinedForTheActivity();
	}
	@Test
	public void findParentActivity() throws Exception {
		context.actionIQueryTheParentActivityOfAnActivityPartition();
		context.assertIGetTheParentActivity();
	}
	@Test
	public void createInputPin() throws Exception {
		context.actionICreateAnInputPinOnACallOperationAction();
		context.assertAnInputPinIsCreatedOnTheCallOperationAction();
	}
	@Test
	public void createInputPin2() throws Exception {
		context.actionICreateAnInputPinOnAnOpaqueAction();
		context.assertAnInputPinIsCreatedOnTheOpaqueAction();
	}
	@Test
	public void createOutputPin() throws Exception {
		context.actionICreateAnOutputPinOnACallOperationAction();
		context.assertAnOutputPinIsCreatedOnTheCallOperationAction();
	}
	@Test
	public void createOutputPin2() throws Exception {
		context.actionICreateAnOutputPinOnAnOpaqueAction();
		context.assertAnOutputPinIsCreatedOnTheOpaqueAction();
	}
	@Test
	public void dropNode() throws Exception {
		context.actionIDropANodeToAPartition();
		context.assertThePartitionContainsTheNode();
	}
	@Test
	public void dropNode2() throws Exception {
		context.actionIDropANodeToAnActivity();
		context.assertTheActivityContainsTheNode();
	}
	@Test
	public void dropNode3() throws Exception {
		context.actionIDropAPartitionToAnotherPartition();
		context.assertTheParentPartitionContainsTheDroppedPartition();
	}
	@Test
	public void dropNode4() throws Exception {
		context.actionIDropAPartitionToAnActivity();
		context.assertTheActivityContainsThePartition();
	}
}
