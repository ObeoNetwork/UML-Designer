package org.obeonetwork.dsl.uml2.design.tests.automation.unit.contexts;

import static org.junit.Assert.assertEquals;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.services.UMLServices;
import org.obeonetwork.dsl.uml2.design.services.internal.EditLabelSwitch;
import org.obeonetwork.dsl.uml2.design.tests.automation.contexts.Context;

public class TheUmlModelWithAnAssociationAndTwoEndProperties extends Context {
	private Association startAssociation;
	private Property firstEnd;
	private Property secondEnd;
	private EditLabelSwitch editSwitch = new EditLabelSwitch();

	public void editFirstRole(String string) {
		Property role = UMLServices.getSource(startAssociation);
		editSwitch.setEditedLabelContent(string);
		editSwitch.caseRole(role);
	}

	public void editSecondRole(String string) {
		Property role = UMLServices.getTarget(startAssociation);
		editSwitch.setEditedLabelContent(string);
		editSwitch.caseRole(role);
	}

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
		// Nothing
	}

	public void assertfirstEndEquals(String name) {
		assertEquals(name, firstEnd.getName());
	}

	public void assertSecondEndEquals(String name) {
		assertEquals(name, secondEnd.getName());
	}

}
