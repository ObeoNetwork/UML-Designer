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

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.obeonetwork.dsl.uml2.design.services.internal.PropertyServices;

/**
 * Unit tests on properties services.
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class PropertyServicesTest extends TestCase {

	/**
	 * Toto.
	 */
	private static final String TOTO = "toto";

	/**
	 * String primitive type name.
	 */
	private static final String PRIMITIVE_TYPE_STRING = "String";

	/**
	 * Integer primitive type name.
	 */
	private static final String PRIMITIVE_TYPE_INTEGER = "Integer";

	/**
	 * Old name constant.
	 */
	private static final String OLD_NAME = "oldName";

	/**
	 * The test resource set.
	 */
	private ResourceSet resourceSet;

	/**
	 * The string primitive type.
	 */
	private PrimitiveType stringPrimitiveType;

	/**
	 * The integer primitive type.
	 */
	private PrimitiveType integerPrimitiveType;
	
	/**
	 * Constructor.
	 */
	public PropertyServicesTest() {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			@SuppressWarnings("unused")
			final EPackage pkg = UMLPackage.eINSTANCE;
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
					UMLResource.Factory.INSTANCE);
		}

		resourceSet = new ResourceSetImpl();
		final Resource resource = new ResourceImpl();
		resourceSet.getResources().add(resource);

		stringPrimitiveType = UMLFactory.eINSTANCE.createPrimitiveType();
		stringPrimitiveType.setName(PRIMITIVE_TYPE_STRING);
		resource.getContents().add(stringPrimitiveType);

		integerPrimitiveType = UMLFactory.eINSTANCE.createPrimitiveType();
		integerPrimitiveType.setName(PRIMITIVE_TYPE_INTEGER);
		resource.getContents().add(integerPrimitiveType);
	}

	/**
	 * Creates a new {@link Property} and attach it to the first {@link Resource}.
	 * 
	 * @param name the property name.
	 * @param type the property {@link Type}.
	 * @param lower the property lower bound
	 * @param upper the property upper bound
	 * @param derived the property derived flag
	 * @return the new property
	 */
	private Property createProperty(String name, Type type, int lower, int upper, boolean derived) {
		final Property property = UMLFactory.eINSTANCE.createProperty();
		property.setName(name);
		property.setType(type);
		property.setLower(lower);
		property.setUpper(upper);
		property.setIsDerived(derived);

		resourceSet.getResources().get(0).getContents().add(property);

		return property;
	}

	/**
	 * Creates a String property [0..*].
	 * 
	 * @return the new property
	 */
	private Property createPropertyStringMultiple() {
		return createProperty(OLD_NAME, stringPrimitiveType, 0, -1, false);
	}

	/**
	 * Test the property name update.
	 */
	public void testNameOnly() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, TOTO);
		assertEquals(TOTO, property.getName());
		assertEquals(stringPrimitiveType, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}

	/**
	 * The the property name update with whitespaces.
	 */
	public void testNameOnlyWithWhitespaces() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "   	toto  	");
		assertEquals(TOTO, property.getName());
		assertEquals(stringPrimitiveType, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}

	/**
	 * Test the property name and type update.
	 */
	public void testNameAndType() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto : Integer");
		assertEquals(TOTO, property.getName());
		assertEquals(integerPrimitiveType, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}

	/**
	 * Test the property name & type update with an inexistent type.
	 */
	public void testNameAndNotExistingType() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto : NonExistingType");
		assertEquals(TOTO, property.getName());
		assertEquals(stringPrimitiveType, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}

	/**
	 * Test the type only update.
	 */
	public void testTypeOnly() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, " : Integer");
		assertEquals("", property.getName());
		assertEquals(integerPrimitiveType, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}

	/**
	 * Test derived with name update.
	 */
	public void testIsDerivedAndName() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "/toto");
		assertEquals(TOTO, property.getName());
		assertEquals(stringPrimitiveType, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(true, property.isDerived());
	}

	/**
	 * Test derived only update.
	 */
	public void testIsDerivedWithoutName() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "/");
		assertEquals("", property.getName());
		assertEquals(stringPrimitiveType, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(true, property.isDerived());
	}

	/**
	 * Test derived with name and type.
	 */
	public void testIsDerivedNameAndType() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "/toto : Integer");
		assertEquals(TOTO, property.getName());
		assertEquals(integerPrimitiveType, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(true, property.isDerived());
	}

	/**
	 * Test derived with name and inexistent type.
	 */
	public void testIsDerivedNameAndNonExistingType() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "/toto : NonExistingType");
		assertEquals(TOTO, property.getName());
		assertEquals(stringPrimitiveType, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(true, property.isDerived());
	}

	/**
	 * Test not derived with name.
	 */
	public void testNotIsDerivedAndName() {
		final Property property = createPropertyStringMultiple();
		property.setIsDerived(true);
		PropertyServices.parseInputLabel(property, "toto  ");
		assertEquals(TOTO, property.getName());
		assertEquals(stringPrimitiveType, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}

	/**
	 * Test name with type and multiplicity [1] update.
	 */
	public void testNameTypeAndMultiplicityUsingOneBound() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto : Integer [1] ");
		assertEquals(TOTO, property.getName());
		assertEquals(integerPrimitiveType, property.getType());
		assertEquals(1, property.getLower());
		assertEquals(1, property.getUpper());
		assertEquals(false, property.isDerived());
	}

	/**
	 * Test name with type and multiplicity [*] update.
	 */
	public void testNameTypeAndMultiplicityUsingOneBoundStar() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto : Integer [*] ");
		assertEquals(TOTO, property.getName());
		assertEquals(integerPrimitiveType, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}

	/**
	 * Test name with type and multiplicity [-1].
	 */
	public void testNameTypeAndMultiplicityUsingOneBoundMinusOne() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto : Integer [-1] ");
		assertEquals(TOTO, property.getName());
		assertEquals(integerPrimitiveType, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}

	/**
	 * Test name with type and bounded multiplicity update. 
	 */
	public void testNameTypeAndMultiplicityWithDifferentBounds() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto : Integer [1..5] ");
		assertEquals(TOTO, property.getName());
		assertEquals(integerPrimitiveType, property.getType());
		assertEquals(1, property.getLower());
		assertEquals(5, property.getUpper());
		assertEquals(false, property.isDerived());
	}

	/**
	 * Test name with type and multiplicity [1..*] update.
	 */
	public void testNameTypeAndMultiplicityWithDifferentBoundsUsingStar() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto : Integer [1..*] ");
		assertEquals(TOTO, property.getName());
		assertEquals(integerPrimitiveType, property.getType());
		assertEquals(1, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}

	/**
	 * Test name with type and multiplicity bounded and infinite.
	 */
	public void testNameTypeAndMultiplicityWithDifferentBoundsUsingMinusOne() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "  toto : Integer [5..-1] ");
		assertEquals(TOTO, property.getName());
		assertEquals(integerPrimitiveType, property.getType());
		assertEquals(5, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}

	/**
	 * Full test update with whitespaces.
	 */
	public void testFullwithLotsOfSpaces() {
		final Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property,
				"  /   to   to    :    Integer     [    5    ..   -1   ]    ");
		assertEquals("to   to", property.getName());
		assertEquals(integerPrimitiveType, property.getType());
		assertEquals(5, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(true, property.isDerived());
	}

	/**
	 * Test incorrect bounds.
	 */
	public void testNameTypeAndMultiplicityWithIncorrectBounds() {
		final Property property = createPropertyStringMultiple();
		property.setLower(2);
		property.setUpper(4);
		PropertyServices.parseInputLabel(property, "  toto : Integer [*..*] ");
		assertEquals(TOTO, property.getName());
		assertEquals(integerPrimitiveType, property.getType());
		assertEquals(2, property.getLower());
		assertEquals(4, property.getUpper());
		assertEquals(false, property.isDerived());
	}

	/**
	 * Test incorrect bounds 2.
	 */
	public void testNameTypeAndMultiplicityWithIncorrectBounds2() {
		final Property property = createPropertyStringMultiple();
		property.setLower(2);
		property.setUpper(4);
		PropertyServices.parseInputLabel(property, "  toto : Integer [-1..2] ");
		assertEquals(TOTO, property.getName());
		assertEquals(integerPrimitiveType, property.getType());
		assertEquals(2, property.getLower());
		assertEquals(4, property.getUpper());
		assertEquals(false, property.isDerived());
	}

	/**
	 * Test incorrect bounds 3.
	 */
	public void testNameTypeAndMultiplicityWithIncorrectBounds3() {
		final Property property = createPropertyStringMultiple();
		property.setLower(2);
		property.setUpper(4);
		PropertyServices.parseInputLabel(property, "  toto : Integer [5..3] ");
		assertEquals(TOTO, property.getName());
		assertEquals(integerPrimitiveType, property.getType());
		assertEquals(2, property.getLower());
		assertEquals(4, property.getUpper());
		assertEquals(false, property.isDerived());
	}
}
