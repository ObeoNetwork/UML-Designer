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
package org.obeonetwork.dsl.uml2.design.tests.automation.unit.contexts;

import static org.junit.Assert.assertEquals;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.services.UMLServices;
import org.obeonetwork.dsl.uml2.design.services.internal.EditLabelSwitch;
import org.obeonetwork.dsl.uml2.design.tests.automation.unit.UnitContextTestCase;

// @Context("TheUmlModelWithAnAssociationAndTwoEndProperties")
public class TheUmlModelWithAnAssociationAndTwoEndProperties extends
		UnitContextTestCase {
	private Association startAssociation;
	private Property firstEnd;
	private Property secondEnd;
	private EditLabelSwitch editSwitch = new EditLabelSwitch();

	@Override
	public void setup() {
		startAssociation = UMLFactory.eINSTANCE.createAssociation();
		Property oneEnd = UMLFactory.eINSTANCE.createProperty();
		Property anotherEnd = UMLFactory.eINSTANCE.createProperty();
		startAssociation.getOwnedEnds().add(oneEnd);
		startAssociation.getOwnedEnds().add(anotherEnd);
		firstEnd = UMLServices.getSource(startAssociation);
		secondEnd = UMLServices.getTarget(startAssociation);
	}

	@Override
	public void tearDown() {
	}

	// @Action("EditAssociationRoles")
	protected void actionEditAssociationRoles() {
		editFirstRole(startAssociation, "firstRole");
		editSecondRole(startAssociation, "secondRole");
	}

	// @Behaviour("PropertiesEndValueUpdated")
	protected void assertPropertiesEndValueUpdated() {
		assertEquals("firstRole", firstEnd.getName());
		assertEquals("secondRole", secondEnd.getName());
	}

	private void editFirstRole(Association startAssociation2, String string) {
		Property role = UMLServices.getSource(startAssociation2);
		editSwitch.setEditedLabelContent(string);
		editSwitch.caseRole(role);
	}

	private void editSecondRole(Association startAssociation2, String string) {
		Property role = UMLServices.getTarget(startAssociation2);
		editSwitch.setEditedLabelContent(string);
		editSwitch.caseRole(role);
	}
}
