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

package org.obeonetwork.dsl.uml2.design.tests.ui.stories.createarelationship;
import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.contexts.AClassDiagramIsOpened;

public class AClassDiagramIsOpenedUiTests {
	@Rule
	public AClassDiagramIsOpened context = new AClassDiagramIsOpened();

	@Test
	public void createAnAssociationBetweenTwoClassesInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheAssociationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass();
		context.assertAnAssociationIsCreatedInTheModel();
		context.assertAnAssociationAppearsBetweenTheClassesOnTheDiagram();
	}
	@Test
	public void createACompositionBetweenTwoClassesInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheCompositionCreationToolFromThePaletteAndISelectASourceClassAndATargetClass();
		context.assertACompositionIsCreatedInTheModel();
		context.assertACompositionAppearsBetweenTheClassesOnTheDiagram();
	}
	@Test
	public void createAnAggregationBetweenTwoClassesInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheAggregationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass();
		context.assertAnAggregationIsCreatedInTheModel();
		context.assertAnAggregationAppearsBetweenTheClassesOnTheDiagram();
	}
	@Test
	public void createAnAssociationClassBetweenTwoClassesInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheAssociationClassCreationToolFromThePaletteAndISelectASourceClassAndATargetClass();
		context.assertAnAssociationClassIsCreatedInTheModel();
		context.assertAnAssociationClassAppearsBetweenTheClassesOnTheDiagram();
	}
	@Test
	public void createAGeneralizationBetweenTwoClassesInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheGeneralizationCreationToolFromThePaletteAndISelectASourceClassAndATargetClass();
		context.assertAGeneralizationIsCreatedInTheModel();
		context.assertAGeneralizationAppearsBetweenTheClassesOnTheDiagram();
	}
	@Test
	public void createAnInterfaceRealizationInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheInterfaceRealizationCreationToolFromThePaletteAndISelectASourceClassAndATargetInterface();
		context.assertAnInterfaceRealizationIsCreatedInTheModel();
		context.assertAnInterfaceRealizationAppearsBetweenTheClassAndTheInterfaceOnTheDiagram();
	}
	@Test
	public void createADependencyBetweenTwoClassesInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheDependencyCreationToolFromThePaletteAndISelectASourceClassAndATargetClass();
		context.assertADependencyIsCreatedInTheModel();
		context.assertADependencyAppearsBetweenTheClassesOnTheDiagram();
	}
	@Test
	public void createAUsageBetweenTwoClassesInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheUsageCreationToolFromThePaletteAndISelectASourceClassAndATargetClass();
		context.assertAUsageIsCreatedInTheModel();
		context.assertAUsageAppearsBetweenTheClassesOnTheDiagram();
	}
	@Test
	public void createANestClassifierBetweenTwoClassesInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheClassifierCreationToolFromThePaletteAndISelectASourceClassAndATargetClass();
		context.assertAClassifierIsCreatedInTheModel();
		context.assertAClassifierAppearsBetweenTheClassesOnTheDiagram();
	}
}
