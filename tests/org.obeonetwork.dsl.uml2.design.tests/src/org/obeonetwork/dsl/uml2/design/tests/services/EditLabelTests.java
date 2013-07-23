/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.tests.services;

import junit.framework.TestCase;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.services.LabelServices;
import org.obeonetwork.dsl.uml2.design.services.UMLServices;
import org.obeonetwork.dsl.uml2.design.services.internal.EditLabelSwitch;

public class EditLabelTests extends TestCase {

	private Association startAssociation;

	private Property firstEnd;

	private Property secondEnd;

	private LabelServices service = new LabelServices();

	private EditLabelSwitch editSwitch = new EditLabelSwitch();

	@Override
	protected void setUp() throws Exception {
		startAssociation = UMLFactory.eINSTANCE.createAssociation();
		Property oneEnd = UMLFactory.eINSTANCE.createProperty();
		Property anotherEnd = UMLFactory.eINSTANCE.createProperty();
		startAssociation.getOwnedEnds().add(oneEnd);
		startAssociation.getOwnedEnds().add(anotherEnd);
		firstEnd = new UMLServices().getSource(startAssociation);
		secondEnd = new UMLServices().getTarget(startAssociation);
	}

	public void testClassDiagramEditRolesNames() throws Exception {
		editFirstRole(startAssociation, "firstRole");
		editSecondRole(startAssociation, "secondRole");
		assertEquals("firstRole", firstEnd.getName());
		assertEquals("secondRole", secondEnd.getName());

		/*
		 * we have to trim spaces
		 */
		editFirstRole(startAssociation, "  firstRole  ");
		editSecondRole(startAssociation, "  secondRole  ");
		assertEquals("firstRole", firstEnd.getName());
		assertEquals("secondRole", secondEnd.getName());

		editFirstRole(startAssociation, "firstRoleRenamed");
		editSecondRole(startAssociation, "secondRole");

		assertEquals("firstRoleRenamed", firstEnd.getName());
		assertEquals("secondRole", secondEnd.getName());

	}

	private void editFirstRole(Association startAssociation2, String string) {
		Property role = new UMLServices().getSource(startAssociation2);
		editSwitch.setEditedLabelContent(string);
		editSwitch.caseRole(role);
	}

	private void editSecondRole(Association startAssociation2, String string) {
		Property role = new UMLServices().getTarget(startAssociation2);
		editSwitch.setEditedLabelContent(string);
		editSwitch.caseRole(role);
	}

	public void testClassDiagramEditRolesNamesSecondNavigable() throws Exception {
		firstEnd.setIsNavigable(false);
		secondEnd.setIsNavigable(true);
		/*
		 * only typing one name will edit the first end
		 */
		editSecondRole(startAssociation, "aNavigableProperty");
		assertEquals("aNavigableProperty", secondEnd.getName());
	}

	public void testClassDiagramEditRolesNamesNoneNavigable() throws Exception {
		firstEnd.setIsNavigable(false);
		secondEnd.setIsNavigable(false);
		/*
		 * only typing one name will edit the first end
		 */
		editFirstRole(startAssociation, "aNavigableProperty");
		assertEquals("aNavigableProperty", firstEnd.getName());
	}

	public void testClassDiagramEditRolesNamesNoneNavigableCard() throws Exception {
		firstEnd.setIsNavigable(false);
		secondEnd.setIsNavigable(false);
		/*
		 * only typing one name will edit the first end
		 */
		editFirstRole(startAssociation, "aNavigableProperty[2..4]");
		assertEquals("aNavigableProperty", firstEnd.getName());
		assertEquals(2, firstEnd.getLower());
		assertEquals(4, firstEnd.getUpper());
	}

	public void testClassDiagramEditRolesNamesNoneNavigableCardSpaces() throws Exception {
		firstEnd.setIsNavigable(false);
		secondEnd.setIsNavigable(false);
		/*
		 * only typing one name will edit the first end
		 */
		editFirstRole(startAssociation, "aNavigableProperty  [ 2  .. 4  ]");
		assertEquals("aNavigableProperty", firstEnd.getName());
		assertEquals(2, firstEnd.getLower());
		assertEquals(4, firstEnd.getUpper());
	}

	public void testClassDiagramEditRolesNamesNoneNavigableOnlyCard() throws Exception {
		firstEnd.setIsNavigable(false);
		secondEnd.setIsNavigable(false);
		/*
		 * only typing one name will edit the first end
		 */
		editFirstRole(startAssociation, "[2..4]");
		assertEquals(2, firstEnd.getLower());
		assertEquals(4, firstEnd.getUpper());
	}

	public void testClassDiagramEditRolesNamesFirstNavigable() throws Exception {
		firstEnd.setIsNavigable(true);
		secondEnd.setIsNavigable(false);
		/*
		 * only typing one name will edit the first end
		 */
		editFirstRole(startAssociation, "aNavigableProperty");
		assertEquals("aNavigableProperty", firstEnd.getName());
	}

	public void testCardinality() throws Exception {

		editFirstRole(startAssociation, "firstRole[0..1] ");
		editSecondRole(startAssociation, "secondRole[*]");
		assertEquals(0, firstEnd.getLower());
		assertEquals(1, firstEnd.getUpper());
		assertEquals(-1, secondEnd.getUpper());
		assertEquals(0, secondEnd.getLower());

		// with spaces
		editFirstRole(startAssociation, "firstRole [ 0 .. 1 ] ");
		editSecondRole(startAssociation, " secondRole [ * ] ");
		assertEquals(0, firstEnd.getLower());
		assertEquals(1, firstEnd.getUpper());
		assertEquals(-1, secondEnd.getUpper());
		assertEquals(0, secondEnd.getLower());

		editFirstRole(startAssociation, "firstRole[5..5] ");
		editSecondRole(startAssociation, " secondRole");
		assertEquals(5, firstEnd.getLower());
		assertEquals(5, firstEnd.getUpper());
		assertEquals(-1, secondEnd.getUpper());
		assertEquals(0, secondEnd.getLower());

		/*
		 * only one end should change cardinality
		 */
		editFirstRole(startAssociation, "firstRole");
		editSecondRole(startAssociation, " secondRole[1..5]");
		assertEquals(5, firstEnd.getLower());
		assertEquals(5, firstEnd.getUpper());
		assertEquals(1, secondEnd.getLower());
		assertEquals(5, secondEnd.getUpper());

		editFirstRole(startAssociation, "firstRole[*] ");
		editSecondRole(startAssociation, " secondRole");
		assertEquals(0, firstEnd.getLower());
		assertEquals(-1, firstEnd.getUpper());
		assertEquals(1, secondEnd.getLower());
		assertEquals(5, secondEnd.getUpper());
	}

	public void testDerivedNone() throws Exception {
		editFirstRole(startAssociation, "firstRole[0..1] ");
		editSecondRole(startAssociation, " secondRole[*]");
		assertEquals(false, firstEnd.isDerived());
		assertEquals(false, secondEnd.isDerived());
	}

	public void testDerivedFirst() throws Exception {

		editFirstRole(startAssociation, "/firstRole[0..1] ");
		editSecondRole(startAssociation, " secondRole[*]");
		assertEquals(true, firstEnd.isDerived());
		assertEquals(false, secondEnd.isDerived());
	}

	public void testDerivedSecond() throws Exception {

		editFirstRole(startAssociation, "firstRole[0..1] ");
		editSecondRole(startAssociation, " /secondRole[*]");
		assertEquals(false, firstEnd.isDerived());
		assertEquals(true, secondEnd.isDerived());
	}

	public void testDerivedBoth() throws Exception {

		editFirstRole(startAssociation, "/firstRole[0..1] ");
		editSecondRole(startAssociation, " /secondRole[*]");
		assertEquals(true, firstEnd.isDerived());
		assertEquals(true, secondEnd.isDerived());
	}

	public void testDerivedBothWithSpaces() throws Exception {
		editFirstRole(startAssociation, " / firstRole ");
		editSecondRole(startAssociation, " /  secondRole [ * ] ");
		service.editUmlLabel(startAssociation, "/   firstRole  - /  secondRole [ * ]   ");
		assertEquals(true, firstEnd.isDerived());
		assertEquals(true, secondEnd.isDerived());
	}

}
