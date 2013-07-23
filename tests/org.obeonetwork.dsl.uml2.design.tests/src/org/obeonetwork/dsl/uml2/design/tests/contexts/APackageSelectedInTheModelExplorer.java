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

package org.obeonetwork.dsl.uml2.design.tests.contexts;

import static org.junit.Assert.*;
	import org.obeonetwork.dsl.uml2.design.tests.automation.Context;

// Start of user code APackageSelectedInTheModelExplorer imports
import org.obeonetwork.dsl.uml2.design.tests.contexts.UMLDesignerBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;

// End of user code

/**
 * Context : A package selected in the model explorer
 */
public class APackageSelectedInTheModelExplorer extends Context {
// Start of user code APackageSelectedInTheModelExplorer variables
	protected UMLDesignerBot bot = new UMLDesignerBot();

	// End of user code

	@Override
	public void setup() {
		// Start of user code APackageSelectedInTheModelExplorer setup
		bot.importTravelAgencyProject();
		// End of user code
	}

	@Override
	public void tearDown() {
		// Start of user code APackageSelectedInTheModelExplorer tear down
		bot.saveChanges();
		bot.deleteTravelAgencyProject();
		// End of user code
	}
	/**
	 * Action : I create a new class diagram
	 */
	public void actionICreateANewClassDiagram() {
		// Start of user code ICreateANewClassDiagram
		// TODO Implement action ICreateANewClassDiagram
		fail("Action ICreateANewClassDiagram not implemented");
		// End of user code
	}

	/**
	 * Behavior : A class diagram is created and opened
	 */
	public void assertAClassDiagramIsCreatedAndOpened() {
		// Start of user code AClassDiagramIsCreatedAndOpened
		// TODO Implement behavior AClassDiagramIsCreatedAndOpened
		fail("Behavior AClassDiagramIsCreatedAndOpened not implemented");
		// End of user code
	}

// Start of user code APackageSelectedInTheModelExplorer private methods
	// Nothing
	// End of user code
}
