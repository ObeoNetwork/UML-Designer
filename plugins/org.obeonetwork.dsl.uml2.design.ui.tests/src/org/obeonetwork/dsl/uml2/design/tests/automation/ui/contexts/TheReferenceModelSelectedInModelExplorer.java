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

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.obeonetwork.dsl.uml2.design.tests.automation.contexts.Context;
import org.obeonetwork.dsl.uml2.design.tests.automation.contexts.UMLDesignerBot;

//@Context("TheReferenceModelSelectedInModelExplorer")
public class TheReferenceModelSelectedInModelExplorer extends Context {
	protected UMLDesignerBot bot = new UMLDesignerBot();

	@Override
	public void setup() {
		bot.importTravelAgencyProject();
	}

	@Override
	public void tearDown() {
		bot.saveChanges();
		bot.deleteTravelAgencyProject();
	}

	// @Action("CreateAClassDiagram")
	public void actionCreateAClassDiagram() {
		bot.createAClassDiagram();
	}

	// @Behaviour("ClassDiagramCreated")
	public void assertClassDiagramCreated() {
		SWTBotEditor editor = bot.getActiveEditor();
		assertEquals("Travel Agency Class Diagram", editor.getTitle());
	}
}
