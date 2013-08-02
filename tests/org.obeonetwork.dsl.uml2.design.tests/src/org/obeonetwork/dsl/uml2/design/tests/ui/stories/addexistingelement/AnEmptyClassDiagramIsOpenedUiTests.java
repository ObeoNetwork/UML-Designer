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

package org.obeonetwork.dsl.uml2.design.tests.ui.stories.addexistingelement;
import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.contexts.AnEmptyClassDiagramIsOpened;

public class AnEmptyClassDiagramIsOpenedUiTests {
	@Rule
	public AnEmptyClassDiagramIsOpened context = new AnEmptyClassDiagramIsOpened();

	@Test
	public void addAnExistingClassInTheClassDiagram() throws Exception {
		context.actionISelectTheAddToolFromThePaletteAndIClickOnTheDiagramAndISelectAnExisting("classclass");
		context.assertANewElementAppearsOnTheDiagramWhichIsA("classclass");
	}
	@Test
	public void addAnExistingDatatypeInTheClassDiagram() throws Exception {
		context.actionISelectTheAddToolFromThePaletteAndIClickOnTheDiagramAndISelectAnExisting("datatype");
		context.assertANewElementAppearsOnTheDiagramWhichIsA("datatype");
	}
	@Test
	public void addAnExistingPrimitiveTypeInTheClassDiagram() throws Exception {
		context.actionISelectTheAddToolFromThePaletteAndIClickOnTheDiagramAndISelectAnExisting("primitive type");
		context.assertANewElementAppearsOnTheDiagramWhichIsA("primitive type");
	}
	@Test
	public void addAnExistingEnumerationInTheClassDiagram() throws Exception {
		context.actionISelectTheAddToolFromThePaletteAndIClickOnTheDiagramAndISelectAnExisting("enumeration");
		context.assertANewElementAppearsOnTheDiagramWhichIsA("enumeration");
	}
	@Test
	public void addAnExistingComponentInTheClassDiagram() throws Exception {
		context.actionISelectTheAddToolFromThePaletteAndIClickOnTheDiagramAndISelectAnExisting("component");
		context.assertANewElementAppearsOnTheDiagramWhichIsA("component");
	}
}
