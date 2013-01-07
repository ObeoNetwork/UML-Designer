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
package org.obeonetwork.dsl.uml2.design.tests.automation.ui.contexts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.uml2.uml.Model;
import org.obeonetwork.dsl.uml2.design.tests.automation.ui.UIContextTestCase;

import fr.obeo.dsl.viewpoint.tests.swtbot.support.api.editor.SWTBotDesignerEditor;

// @Context("TheReferenceClassDiagramOpened")
public class TheReferenceClassDiagramOpened extends UIContextTestCase {
	SWTBotDesignerEditor classDiagram;

	@Override
	public void setup() {
		classDiagram = bot.openEntitiesClassDiagram();
	}

	@Override
	public void tearDown() {
		bot.saveChanges();
		bot.deleteTravelAgencyProject();
	}

	// @Action("CreateAClass")
	protected void actionCreateAClass() {
		actionCreateAType("Class");
	}

	// @Action("CreateAnEnumeration")
	protected void actionCreateAnEnumeration() {
		actionCreateAType("Enumeration");
	}

	private void actionCreateAType(String toolName) {
		classDiagram.activateTool(toolName);
		classDiagram.click(10, 10);
		classDiagram.save();
	}

	// @Action("DeleteAClass")
	protected void actionDeleteAClass() {
		bot.deleteCatalogClass();
	}

	// @Behaviour("ElementCreatedInUmlModel")
	protected void assertElementCreatedInUmlModel(String elementName) {
		Model model = bot.getTravelAgencyModel();
		assertNotNull(model.getOwnedMember(elementName));
	}

	// @Behaviour("ElementExistsInTheReferenceClassDiagram")
	protected void assertElementExistsInTheReferenceClassDiagram(
			String elementName) {
		assertNotNull(classDiagram.getEditPart(elementName));
	}

	// @Behaviour("ClassDeletedInUmlModel")
	protected void assertClassDeletedInUmlModel() {
		Model model = bot.getTravelAgencyModel();
		assertNull(model.getMember("Catalog"));
	}

	// @Behaviour("ClassRemovedFromTheReferenceClassDiagram")
	protected void assertClassRemovedFromTheReferenceClassDiagram() {
		try {
			classDiagram.getEditPart("Catalog");
			fail();
		} catch (WidgetNotFoundException e) {
			assertEquals("Expected to find widget Catalog", e.getMessage());
		}
	}
}
