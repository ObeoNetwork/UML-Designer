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

// Start of user code TheSecondEndIsNavigable imports

// End of user code

/**
 * Context : The second end is navigable
 */
public class TheSecondEndIsNavigable extends AnUmlModelWithAnAssociation {
// Start of user code TheSecondEndIsNavigable variables
	// Nothing
	// End of user code

	@Override
	public void setup() {
		super.setup();
		// Start of user code TheSecondEndIsNavigable setup
		firstEnd.setIsNavigable(false);
		secondEnd.setIsNavigable(true);
		// End of user code
	}

	@Override
	public void tearDown() {
		super.tearDown();
		// Start of user code TheSecondEndIsNavigable tear down
		// Nothing
		// End of user code
	}
	/**
	 * Action : I edit the label of the second role to
	 */
	public void actionIEditTheLabelOfTheSecondRoleTo(String iEditTheLabelOfTheSecondRoleTo0) {
		// Start of user code IEditTheLabelOfTheSecondRoleTo
		/*
		 * only typing one name will edit the first end
		 */
		editSecondRole(startAssociation, "aNavigableProperty");
		// End of user code
	}

	/**
	 * Behavior : The second end name equals
	 */
	public void assertTheSecondEndNameEquals(String theSecondEndNameEquals0) {
		// Start of user code TheSecondEndNameEquals
		assertEquals("aNavigableProperty", secondEnd.getName());
		// End of user code
	}

// Start of user code TheSecondEndIsNavigable private methods
	// Nothing
	// End of user code
}
