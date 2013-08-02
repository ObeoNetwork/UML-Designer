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

package org.obeonetwork.dsl.uml2.design.tests.ui.stories.deleteafeature;
import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.contexts.AClassDiagramIsOpened;

public class AClassDiagramIsOpenedUiTests {
	@Rule
	public AClassDiagramIsOpened context = new AClassDiagramIsOpened();

	@Test
	public void deleteAPropertyInTheClassDiagramFromTheDeleteFromModelToolInTheToolbar() throws Exception {
		context.actionISelectTheDeleteFromModelToolInToolbarAndIClickOnAPropertyInAClassInTheDiagram();
		context.assertThePropertyIsDeletedFromTheModel();
		context.assertThePropertyDoesNotAppearAnymoreOnTheDiagram();
	}
	@Test
	public void deleteALiteralInTheClassDiagramFromTheDeleteFromModelToolInTheToolbar() throws Exception {
		context.actionISelectTheDeleteFromModelToolInToolbarAndIClickOnALiteralInAClassInTheDiagram();
		context.assertTheLiteralIsDeletedFromTheModel();
		context.assertTheLiteralDoesNotAppearAnymoreOnTheDiagram();
	}
}
