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

// Start of user code AnUmlModelWithAnAssociation imports
import static org.junit.Assert.assertEquals;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.services.UMLServices;
import org.obeonetwork.dsl.uml2.design.services.internal.EditLabelSwitch;
// End of user code

/**
 * Context : An Uml model with an association
 */
public class AnUmlModelWithAnAssociation extends Context {
// Start of user code AnUmlModelWithAnAssociation variables
	private Association startAssociation;

	private Property firstEnd;

	private Property secondEnd;

	private EditLabelSwitch editSwitch = new EditLabelSwitch();

	// End of user code

	@Override
	public void setup() {
		// Start of user code AnUmlModelWithAnAssociation setup
		startAssociation = UMLFactory.eINSTANCE.createAssociation();
		Property oneEnd = UMLFactory.eINSTANCE.createProperty();
		Property anotherEnd = UMLFactory.eINSTANCE.createProperty();
		startAssociation.getOwnedEnds().add(oneEnd);
		startAssociation.getOwnedEnds().add(anotherEnd);
		firstEnd = UMLServices.getSource(startAssociation);
		secondEnd = UMLServices.getTarget(startAssociation);
		// End of user code
	}

	@Override
	public void tearDown() {
		// Start of user code AnUmlModelWithAnAssociation tear down
		// Nothing
		// End of user code
	}
	/**
	 * Action : I edit the label of the first role to
	 */
	public void actionIEditTheLabelOfTheFirstRoleTo(String firstRole) {
		// Start of user code IEditTheLabelOfTheFirstRoleTo
		Property role = UMLServices.getSource(startAssociation);
		editSwitch.setEditedLabelContent(firstRole);
		editSwitch.caseRole(role);
		// End of user code
	}

	/**
	 * Action : I edit the label of the second role to
	 */
	public void actionIEditTheLabelOfTheSecondRoleTo(String secondRole) {
		// Start of user code IEditTheLabelOfTheSecondRoleTo
		Property role = UMLServices.getTarget(startAssociation);
		editSwitch.setEditedLabelContent(secondRole);
		editSwitch.caseRole(role);
		// End of user code
	}

	/**
	 * Behavior : The second end name equals
	 */
	public void assertTheSecondEndNameEquals(String secondRole) {
		// Start of user code TheSecondEndNameEquals
		assertEquals(secondRole, secondEnd.getName());
		// End of user code
	}
	/**
	 * Behavior : The first end name equals
	 */
	public void assertTheFirstEndNameEquals(String firstRole) {
		// Start of user code TheFirstEndNameEquals
		assertEquals(firstRole, firstEnd.getName());
		// End of user code
	}

// Start of user code AnUmlModelWithAnAssociation private methods
	// Nothing
	// End of user code
}
