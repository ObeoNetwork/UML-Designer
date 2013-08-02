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

package org.obeonetwork.dsl.uml2.design.tests.ui.stories.createafeature;
import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.contexts.AClassDiagramIsOpened;

public class AClassDiagramIsOpenedUiTests {
	@Rule
	public AClassDiagramIsOpened context = new AClassDiagramIsOpened();

	@Test
	public void createAPropertyContainedInAClassInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectThePropertyCreationToolFromThePaletteAndISelectAClass();
		context.assertAPropertyIsCreatedInTheClassInTheModel();
		context.assertAPropertyAppearsInTheClassOnTheDiagram();
	}
	@Test
	public void createAPropertyContainedInAnInterfaceInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectThePropertyCreationToolFromThePaletteAndISelectAnInterface();
		context.assertAPropertyIsCreatedInTheInterfaceInTheModel();
		context.assertAPropertyAppearsInTheInterfaceOnTheDiagram();
	}
	@Test
	public void createAPropertyContainedInADatatypeInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectThePropertyCreationToolFromThePaletteAndISelectADatatype();
		context.assertAPropertyIsCreatedInTheDatatypeInTheModel();
		context.assertAPropertyAppearsInTheDatatypeOnTheDiagram();
	}
	@Test
	public void createAPropertyContainedInAPrimitiveTypeInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectThePropertyCreationToolFromThePaletteAndISelectAPrimitiveType();
		context.assertAPropertyIsCreatedInThePrimitiveTypeInTheModel();
		context.assertAPropertyAppearsInThePrimitiveTypeOnTheDiagram();
	}
	@Test
	public void createATypedPropertyContainedInAClassInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAClassAndISelectAType();
		context.assertAPropertyIsCreatedInTheClassInTheModel();
		context.assertAPropertyAppearsInTheClassOnTheDiagram();
		context.assertThePropertyTypesEqualsTheSelectedType();
	}
	@Test
	public void createATypedPropertyContainedInAnInterfaceInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAnInterfaceAndISelectAType();
		context.assertAPropertyIsCreatedInTheInterfaceInTheModel();
		context.assertAPropertyAppearsInTheInterfaceOnTheDiagram();
		context.assertThePropertyTypesEqualsTheSelectedType();
	}
	@Test
	public void createATypedPropertyContainedInAPrimitiveTypeInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheTypedPropertyCreationToolFromThePaletteAndISelectAPrimitiveTypeAndISelectAType();
		context.assertAPropertyIsCreatedInThePrimitiveTypeInTheModel();
		context.assertAPropertyAppearsInThePrimitiveTypeOnTheDiagram();
		context.assertThePropertyTypesEqualsTheSelectedType();
	}
	@Test
	public void createATypedPropertyContainedInADatatypeInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheTypedPropertyCreationToolFromThePaletteAndISelectADatatypeAndISelectAType();
		context.assertAPropertyIsCreatedInTheDatatypeInTheModel();
		context.assertAPropertyAppearsInTheDatatypeOnTheDiagram();
		context.assertThePropertyTypesEqualsTheSelectedType();
	}
	@Test
	public void createAnOperationContainedInAClassInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheOperationCreationToolFromThePaletteAndISelectAClass();
		context.assertAnOperationIsCreatedInTheClassInTheModel();
		context.assertAnOperationAppearsInTheClassOnTheDiagram();
	}
	@Test
	public void createAnOperationContainedInAnInterfaceInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheOperationCreationToolFromThePaletteAndISelectAnInterface();
		context.assertAnOperationIsCreatedInTheInterfaceInTheModel();
		context.assertAnOperationAppearsInTheInterfaceOnTheDiagram();
	}
	@Test
	public void createAnOperationContainedInADatatypeInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheOperationCreationToolFromThePaletteAndISelectADatatype();
		context.assertAnOperationIsCreatedInTheDatatypeInTheModel();
		context.assertAnOperationAppearsInTheDatatypeOnTheDiagram();
	}
	@Test
	public void createAnOperationContainedInAPrimitiveTypeInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheOperationCreationToolFromThePaletteAndISelectAPrimitiveType();
		context.assertAnOperationIsCreatedInThePrimitiveTypeInTheModel();
		context.assertAnOperationAppearsInThePrimitiveTypeOnTheDiagram();
	}
	@Test
	public void createAnOperationContainedInAnEnumerationInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheOperationCreationToolFromThePaletteAndISelectAnEnumeration();
		context.assertAnOperationIsCreatedInTheEnumerationInTheModel();
		context.assertAnOperationAppearsInTheEnumerationOnTheDiagram();
	}
	@Test
	public void createALiteralContainedInAnEnumerationInTheClassDiagramFromThePalette() throws Exception {
		context.actionISelectTheLiteralCreationToolFromThePaletteAndISelectAnEnumeration();
		context.assertALiteralIsCreatedInTheEnumerationInTheModel();
		context.assertALiteralAppearsInTheEnumerationOnTheDiagram();
	}
}
