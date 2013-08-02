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
import org.obeonetwork.dsl.uml2.design.tests.contexts.AnEmptyDiagramIsOpened;

public class AnEmptyDiagramIsOpenedUiTests {
	@Rule
	public AnEmptyDiagramIsOpened context = new AnEmptyDiagramIsOpened();

	@Test
	public void addAnExistingInterfaceInTheClassDiagram() throws Exception {
		context.actionISelectTheAddToolFromThePaletteAndIClickOnTheDiagramAndISelectAnExisting("interface");
		context.assertANewElementAppearsOnTheDiagramWhichIsA("interface");
	}
	@Test
	public void addAnExistingPackageInTheClassDiagram() throws Exception {
		context.actionISelectTheAddToolFromThePaletteAndIClickOnTheDiagramAndISelectAnExisting("package");
		context.assertANewElementAppearsOnTheDiagramWhichIsA("package");
	}
}
