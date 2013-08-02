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

package org.obeonetwork.dsl.uml2.design.tests.ui.stories.reconnectarelationship;
import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.contexts.AClassDiagramIsOpened;

public class AClassDiagramIsOpenedUiTests {
	@Rule
	public AClassDiagramIsOpened context = new AClassDiagramIsOpened();

	@Test
	public void reconnectAnAssociationInTheClassDiagram() throws Exception {
		context.actionIClickOnAnEndOfAnAssociationInTheDiagramAndISelectAnotherClass();
		context.assertTheAssociationEndIsSetToTheNewClassInTheModel();
		context.assertTheAssociationIsConnectedToTheNewClassOnTheDiagram();
	}
	@Test
	public void reconnectACompositionInTheClassDiagram() throws Exception {
		context.actionIClickOnAnEndOfACompositionInTheDiagramAndISelectAnotherClass();
		context.assertTheCompositionEndIsSetToTheNewClassInTheModel();
		context.assertTheCompositionIsConnectedToTheNewClassOnTheDiagram();
	}
	@Test
	public void reconnectAnAggregationInTheClassDiagram() throws Exception {
		context.actionIClickOnAnEndOfAnAggregationInTheDiagramAndISelectAnotherClass();
		context.assertTheAggregationEndIsSetToTheNewClassInTheModel();
		context.assertTheAggregationIsConnectedToTheNewClassOnTheDiagram();
	}
	@Test
	public void reconnectAnAssociationClassInTheClassDiagram() throws Exception {
		context.actionIClickOnAnEndOfAnAssociationClassInTheDiagramAndISelectAnotherClass();
		context.assertTheAssociationClassEndIsSetToTheNewClassInTheModel();
		context.assertTheAssociationClassIsConnectedToTheNewClassOnTheDiagram();
	}
	@Test
	public void reconnectAGeneralizationInTheClassDiagram() throws Exception {
		context.actionIClickOnAnEndOfAGeneralizationInTheDiagramAndISelectAnotherClass();
		context.assertTheGeneralizationEndIsSetToTheNewClassInTheModel();
		context.assertTheGeneralizationIsConnectedToTheNewClassOnTheDiagram();
	}
	@Test
	public void reconnectAnInterfaceRealizationInTheClassDiagram() throws Exception {
		context.actionIClickOnAnEndOfAnInterfaceRealizationInTheDiagramAndISelectAnotherClass();
		context.assertTheInterfaceRealizationEndIsSetToTheNewClassInTheModel();
		context.assertTheInterfaceRealizationIsConnectedToTheNewClassOnTheDiagram();
	}
}
