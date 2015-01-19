package org.obeonetwork.dsl.uml2.design.tests.unit.manual;

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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.FinalNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.UMLFactory;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.api.services.ActivityDiagramServices;

public class CreateActivityEdgeUnitTests {

	ActivityDiagramServices services = new ActivityDiagramServices();

	/**
	 * Initial node shall not have any incoming activity edge which means it should not be selectable as
	 * target for create object flow or create control flow tools.
	 */
	@Test
	public void initialNodeNotHaveAnyIncomingActivityEdge() throws Exception {
		InitialNode node = UMLFactory.eINSTANCE.createInitialNode();

		assertFalse(services.isValidActivityEdgeEnd(null, node));
	}

	/**
	 * A ForkNode shall have exactly one incoming control flow, though it may have multiple outgoing
	 * ActivityEdges.
	 */
	@Test
	public void forkNodeHaveOneIncomingControlFlow() {
		ForkNode node = UMLFactory.eINSTANCE.createForkNode();
		ControlFlow flow = UMLFactory.eINSTANCE.createControlFlow();
		node.getIncomings().add(flow);

		assertFalse(services.isValidActivityEdgeEnd(null, node));
	}

	/**
	 * A ForkNode shall have exactly one incoming object flow, though it may have multiple outgoing
	 * ActivityEdges.
	 */
	@Test
	public void forkNodeHaveOneIncomingObjectFlow() {
		ForkNode node = UMLFactory.eINSTANCE.createForkNode();
		ObjectFlow flow = UMLFactory.eINSTANCE.createObjectFlow();
		node.getIncomings().add(flow);

		assertFalse(services.isValidActivityEdgeEnd(null, node));
	}

	/**
	 * A DecisionNode shall have at least one and at most two incoming ActivityEdges.
	 */
	@Test
	public void decisionNodeHaveOneAndAtMostTwoIncomingObjectFlows() {
		DecisionNode node = UMLFactory.eINSTANCE.createDecisionNode();
		ObjectFlow flow = UMLFactory.eINSTANCE.createObjectFlow();
		node.getIncomings().add(flow);

		assertTrue(services.isValidActivityEdgeEnd(null, node));

		ObjectFlow flow2 = UMLFactory.eINSTANCE.createObjectFlow();
		node.getIncomings().add(flow2);

		assertFalse(services.isValidActivityEdgeEnd(null, node));
	}

	/**
	 * A DecisionNode shall have at least one and at most two incoming ActivityEdges.
	 */
	@Test
	public void decisionNodeHaveOneAndAtMostTwoIncomingControlFlows() {
		DecisionNode node = UMLFactory.eINSTANCE.createDecisionNode();
		ControlFlow flow = UMLFactory.eINSTANCE.createControlFlow();
		node.getIncomings().add(flow);

		assertTrue(services.isValidActivityEdgeEnd(null, node));

		ControlFlow flow2 = UMLFactory.eINSTANCE.createControlFlow();
		node.getIncomings().add(flow2);

		assertFalse(services.isValidActivityEdgeEnd(null, node));
	}

	/**
	 * The outgoing ActivityEdges of an InitialNode must all be ControlFlows.
	 */
	@Test
	public void initialNodeOutgoingMustBeControlFlows() throws Exception {
		InitialNode node = UMLFactory.eINSTANCE.createInitialNode();

		assertFalse(services.isValidObjectFlowStart(node));
	}

	/**
	 * ForkNode : if the incoming edge is an ObjectFlow, then all outgoing edges shall be ObjectFlows
	 */
	@Test
	public void forkNodeIncomingsOutgoingsMustBeObjectFlows() throws Exception {
		ForkNode node = UMLFactory.eINSTANCE.createForkNode();
		ControlFlow inFlow = UMLFactory.eINSTANCE.createControlFlow();
		node.getIncomings().add(inFlow);

		assertFalse(services.isValidObjectFlowStart(node));
	}

	/**
	 * ForkNode : if the incoming edge is an ObjectFlow, then all outgoing edges shall be ObjectFlows
	 */
	@Test
	public void forkNodeIncomingsOutgoingsMustBeControlFlows() throws Exception {
		ForkNode node = UMLFactory.eINSTANCE.createForkNode();
		ObjectFlow inFlow = UMLFactory.eINSTANCE.createObjectFlow();
		node.getIncomings().add(inFlow);

		assertFalse(services.isValidControlFlowStart(node));
	}

	/**
	 * JoinNode : if the incoming edge is an ObjectFlow, then all outgoing edges shall be ObjectFlows
	 */
	@Test
	public void joinNodeIncomingsOutgoingsMustBeObjectFlows() throws Exception {
		JoinNode node = UMLFactory.eINSTANCE.createJoinNode();
		ControlFlow inFlow = UMLFactory.eINSTANCE.createControlFlow();
		node.getIncomings().add(inFlow);

		assertFalse(services.isValidObjectFlowStart(node));
	}

	/**
	 * JoinNode : if the incoming edge is an ObjectFlow, then all outgoing edges shall be ObjectFlows
	 */
	@Test
	public void joinNodeIncomingsOutgoingsMustBeControlFlows() throws Exception {
		JoinNode node = UMLFactory.eINSTANCE.createJoinNode();
		ObjectFlow inFlow = UMLFactory.eINSTANCE.createObjectFlow();
		node.getIncomings().add(inFlow);

		assertFalse(services.isValidControlFlowStart(node));
	}

	/**
	 * MergeNode: if the incoming edge is an ObjectFlow, then all outgoing edges shall be ObjectFlows
	 */
	@Test
	public void mergeNodeIncomingsOutgoingsMustBeObjectFlows() throws Exception {
		MergeNode node = UMLFactory.eINSTANCE.createMergeNode();
		ControlFlow inFlow = UMLFactory.eINSTANCE.createControlFlow();
		node.getIncomings().add(inFlow);

		assertFalse(services.isValidObjectFlowStart(node));
	}

	/**
	 * MergeNode : if the incoming edge is an ObjectFlow, then all outgoing edges shall be ObjectFlows
	 */
	@Test
	public void mergeNodeIncomingsOutgoingsMustBeControlFlows() throws Exception {
		MergeNode node = UMLFactory.eINSTANCE.createMergeNode();
		ObjectFlow inFlow = UMLFactory.eINSTANCE.createObjectFlow();
		node.getIncomings().add(inFlow);

		assertFalse(services.isValidControlFlowStart(node));
	}

	/**
	 * DecisionNode : if the incoming edge is an ObjectFlow, then all outgoing edges shall be ObjectFlows
	 */
	@Test
	public void decisionNodeIncomingsOutgoingsMustBeObjectFlows() throws Exception {
		DecisionNode node = UMLFactory.eINSTANCE.createDecisionNode();
		ControlFlow inFlow = UMLFactory.eINSTANCE.createControlFlow();
		node.getIncomings().add(inFlow);

		assertFalse(services.isValidObjectFlowStart(node));
	}

	/**
	 * DecisionNode : if the incoming edge is an ObjectFlow, then all outgoing edges shall be ObjectFlows
	 */
	@Test
	public void decisionNodeIncomingsOutgoingsMustBeControlFlows() throws Exception {
		DecisionNode node = UMLFactory.eINSTANCE.createDecisionNode();
		ObjectFlow inFlow = UMLFactory.eINSTANCE.createObjectFlow();
		node.getIncomings().add(inFlow);

		assertFalse(services.isValidControlFlowStart(node));
	}

	/**
	 * A FinalNode shall not have outgoing ActivityEdges.
	 */
	@Test
	public void finalNodeNotHaveAnyOutgoingActivityEdge() throws Exception {
		FinalNode node = UMLFactory.eINSTANCE.createActivityFinalNode();

		assertFalse(services.isValidControlFlowStart(node));
	}

	/**
	 * JoinNode shall have exactly one outgoing ActivityEdge but may have multiple incoming ActivityEdges.
	 */
	@Test
	public void joinNodeHaveOneOutgoingControlFlow() throws Exception {
		JoinNode node = UMLFactory.eINSTANCE.createJoinNode();
		ControlFlow flow = UMLFactory.eINSTANCE.createControlFlow();
		node.getOutgoings().add(flow);
		assertFalse(services.isValidControlFlowStart(node));
	}

	/**
	 * JoinNode shall have exactly one outgoing ActivityEdge but may have multiple incoming ActivityEdges.
	 */
	@Test
	public void joinNodeHaveOneOutgoingObjectFlow() throws Exception {
		JoinNode node = UMLFactory.eINSTANCE.createJoinNode();
		ObjectFlow flow = UMLFactory.eINSTANCE.createObjectFlow();
		node.getOutgoings().add(flow);
		assertFalse(services.isValidObjectFlowStart(node));
	}

	/**
	 * MergeNode shall have exactly one outgoing ActivityEdge but may have multiple incoming ActivityEdges.
	 */
	@Test
	public void mergeNodeHaveOneOutgoingControlFlow() throws Exception {
		MergeNode node = UMLFactory.eINSTANCE.createMergeNode();
		ControlFlow flow = UMLFactory.eINSTANCE.createControlFlow();
		node.getOutgoings().add(flow);
		assertFalse(services.isValidControlFlowStart(node));
	}

	/**
	 * MergeNode shall have exactly one outgoing ActivityEdge but may have multiple incoming ActivityEdges.
	 */
	@Test
	public void mergeNodeHaveOneOutgoingObjectFlow() throws Exception {
		MergeNode node = UMLFactory.eINSTANCE.createMergeNode();
		ObjectFlow flow = UMLFactory.eINSTANCE.createObjectFlow();
		node.getOutgoings().add(flow);
		assertFalse(services.isValidObjectFlowStart(node));
	}

	/**
	 * DecisionNode shall have exactly one outgoing ActivityEdge but may have multiple incoming ActivityEdges.
	 */
	@Test
	public void decisionNodeHaveOneOutgoingControlFlow() throws Exception {
		DecisionNode node = UMLFactory.eINSTANCE.createDecisionNode();
		ControlFlow flow = UMLFactory.eINSTANCE.createControlFlow();
		node.getOutgoings().add(flow);
		assertFalse(services.isValidControlFlowStart(node));
	}

	/**
	 * DecisionNode shall have exactly one outgoing ActivityEdge but may have multiple incoming ActivityEdges.
	 */
	@Test
	public void decisionNodeHaveOneOutgoingObjectFlow() throws Exception {
		DecisionNode node = UMLFactory.eINSTANCE.createDecisionNode();
		ObjectFlow flow = UMLFactory.eINSTANCE.createObjectFlow();
		node.getOutgoings().add(flow);
		assertFalse(services.isValidObjectFlowStart(node));
	}
}
