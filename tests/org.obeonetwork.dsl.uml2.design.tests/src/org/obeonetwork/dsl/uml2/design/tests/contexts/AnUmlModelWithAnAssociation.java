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
	protected Association startAssociation;

	protected Property firstEnd;

	protected Property secondEnd;

	protected EditLabelSwitch editSwitch = new EditLabelSwitch();
	
	private UMLServices umlServices = new UMLServices();

	// End of user code

	@Override
	public void setup() {
		// Start of user code AnUmlModelWithAnAssociation setup
		startAssociation = UMLFactory.eINSTANCE.createAssociation();
		Property oneEnd = UMLFactory.eINSTANCE.createProperty();
		Property anotherEnd = UMLFactory.eINSTANCE.createProperty();
		startAssociation.getOwnedEnds().add(oneEnd);
		startAssociation.getOwnedEnds().add(anotherEnd);
		firstEnd = umlServices.getSource(startAssociation);
		secondEnd = umlServices.getTarget(startAssociation);
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
	public void actionIEditTheLabelOfTheFirstRoleTo(String iEditTheLabelOfTheFirstRoleTo0) {
		// Start of user code IEditTheLabelOfTheFirstRoleTo
		editFirstRole(startAssociation, iEditTheLabelOfTheFirstRoleTo0);
		// End of user code
	}

	/**
	 * Action : I edit the label of the second role to
	 */
	public void actionIEditTheLabelOfTheSecondRoleTo(String iEditTheLabelOfTheSecondRoleTo0) {
		// Start of user code IEditTheLabelOfTheSecondRoleTo
		editSecondRole(startAssociation, iEditTheLabelOfTheSecondRoleTo0);
		// End of user code
	}

	/**
	 * Behavior : The first end is derived
	 */
	public void assertTheFirstEndIsDerived() {
		// Start of user code TheFirstEndIsDerived
		assertEquals(true, firstEnd.isDerived());
		// End of user code
	}
	/**
	 * Behavior : The second end upper cardinality equals
	 */
	public void assertTheSecondEndUpperCardinalityEquals(String theSecondEndUpperCardinalityEquals0) {
		// Start of user code TheSecondEndUpperCardinalityEquals
		assertEquals(Integer.parseInt(theSecondEndUpperCardinalityEquals0), secondEnd.getUpper());
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
	 * Behavior : The first end is not derived
	 */
	public void assertTheFirstEndIsNotDerived() {
		// Start of user code TheFirstEndIsNotDerived
		assertEquals(false, firstEnd.isDerived());
		// End of user code
	}
	/**
	 * Behavior : The second end is derived
	 */
	public void assertTheSecondEndIsDerived() {
		// Start of user code TheSecondEndIsDerived
		assertEquals(true, secondEnd.isDerived());
		// End of user code
	}
	/**
	 * Behavior : The second end is not derived
	 */
	public void assertTheSecondEndIsNotDerived() {
		// Start of user code TheSecondEndIsNotDerived
		assertEquals(false, secondEnd.isDerived());
		// End of user code
	}
	/**
	 * Behavior : The second end name equals
	 */
	public void assertTheSecondEndNameEquals(String theSecondEndNameEquals0) {
		// Start of user code TheSecondEndNameEquals
		assertEquals(theSecondEndNameEquals0, secondEnd.getName());
		// End of user code
	}
	/**
	 * Behavior : The second end lower cardinality equals
	 */
	public void assertTheSecondEndLowerCardinalityEquals(String theSecondEndLowerCardinalityEquals0) {
		// Start of user code TheSecondEndLowerCardinalityEquals
		assertEquals(Integer.parseInt(theSecondEndLowerCardinalityEquals0), secondEnd.getLower());
		// End of user code
	}

// Start of user code AnUmlModelWithAnAssociation private methods
	protected void editFirstRole(Association startAssociation2, String string) {
		Property role = umlServices.getSource(startAssociation2);
		editSwitch.setEditedLabelContent(string);
		editSwitch.caseRole(role);
	}

	protected void editSecondRole(Association startAssociation2, String string) {
		Property role = umlServices.getTarget(startAssociation2);
		editSwitch.setEditedLabelContent(string);
		editSwitch.caseRole(role);
	}

	// End of user code
}
