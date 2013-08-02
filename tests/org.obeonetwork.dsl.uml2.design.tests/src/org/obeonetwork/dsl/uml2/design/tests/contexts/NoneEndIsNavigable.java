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

// Start of user code NoneEndIsNavigable imports
// Nothing
// End of user code

/**
 * Context : None end is navigable
 */
public class NoneEndIsNavigable extends AnUmlModelWithAnAssociation {
// Start of user code NoneEndIsNavigable variables
	// Nothing
	// End of user code

	@Override
	public void setup() {
		super.setup();
		// Start of user code NoneEndIsNavigable setup
		firstEnd.setIsNavigable(false);
		secondEnd.setIsNavigable(false);
		// End of user code
	}

	@Override
	public void tearDown() {
		super.tearDown();
		// Start of user code NoneEndIsNavigable tear down
		// Nothing
		// End of user code
	}
	/**
	 * Action : I edit the label of the first role to
	 */
	public void actionIEditTheLabelOfTheFirstRoleTo(String iEditTheLabelOfTheFirstRoleTo0) {
		// Start of user code IEditTheLabelOfTheFirstRoleTo
		/*
		 * only typing one name will edit the first end
		 */
		editFirstRole(startAssociation, iEditTheLabelOfTheFirstRoleTo0);
		// End of user code
	}

	/**
	 * Behavior : The first end name equals
	 */
	public void assertTheFirstEndNameEquals(String theFirstEndNameEquals0) {
		// Start of user code TheFirstEndNameEquals
		assertEquals(theFirstEndNameEquals0, firstEnd.getName());
		// End of user code
	}
	/**
	 * Behavior : The first end upper cardinality equals
	 */
	public void assertTheFirstEndUpperCardinalityEquals(String theFirstEndUpperCardinalityEquals0) {
		// Start of user code TheFirstEndUpperCardinalityEquals
		assertEquals(Integer.parseInt(theFirstEndUpperCardinalityEquals0), firstEnd.getUpper());
		// End of user code
	}
	/**
	 * Behavior : The first end lower cardinality equals
	 */
	public void assertTheFirstEndLowerCardinalityEquals(String theFirstEndLowerCardinalityEquals0) {
		// Start of user code TheFirstEndLowerCardinalityEquals
		assertEquals(Integer.parseInt(theFirstEndLowerCardinalityEquals0), firstEnd.getLower());
		// End of user code
	}

// Start of user code NoneEndIsNavigable private methods
	// Nothing
	// End of user code
}
