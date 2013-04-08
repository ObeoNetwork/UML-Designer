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
import org.obeonetwork.dsl.uml2.design.tests.automation.contexts.Context;
import org.obeonetwork.dsl.uml2.design.tests.automation.contexts.UMLDesignerBot;

import fr.obeo.dsl.viewpoint.tests.swtbot.support.api.editor.SWTBotDesignerEditor;

public class TheReferenceClassDiagramOpened extends Context {
	protected UMLDesignerBot bot;
	SWTBotDesignerEditor classDiagram;

	@Override
	public void setup() {
		bot = new UMLDesignerBot();
		classDiagram = bot.openEntitiesClassDiagram();
	}

	@Override
	public void tearDown() {
		bot.saveChanges();
		bot.deleteTravelAgencyProject();
	}

	// @Action("CreateAClass")
	public void actionCreateAClass() {
		actionCreateAType("Class");
	}

	// @Action("CreateAnEnumeration")
	public void actionCreateAnEnumeration() {
		actionCreateAType("Enumeration");
	}

	private void actionCreateAType(String toolName) {
		classDiagram.activateTool(toolName);
		classDiagram.click(10, 10);
		classDiagram.save();
	}

	// @Action("DeleteAClass")
	public void actionDeleteAClass() {
		bot.deleteCatalogClass();
	}

	// @Behaviour("ElementCreatedInUmlModel")
	public void assertElementCreatedInUmlModel(String elementName) {
		Model model = bot.getTravelAgencyModel();
		assertNotNull(model.getOwnedMember(elementName));
	}

	// @Behaviour("ElementExistsInTheReferenceClassDiagram")
	public void assertElementExistsInTheReferenceClassDiagram(String elementName) {
		assertNotNull(classDiagram.getEditPart(elementName));
	}

	// @Behaviour("ClassDeletedInUmlModel")
	public void assertClassDeletedInUmlModel() {
		Model model = bot.getTravelAgencyModel();
		assertNull(model.getMember("Catalog"));
	}

	// @Behaviour("ClassRemovedFromTheReferenceClassDiagram")
	public void assertClassRemovedFromTheReferenceClassDiagram() {
		try {
			classDiagram.getEditPart("Catalog");
			fail();
		} catch (WidgetNotFoundException e) {
			assertEquals("Expected to find widget Catalog", e.getMessage());
		}
	}
}
