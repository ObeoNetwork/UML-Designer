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
 * Unit tests on properties services
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class PropertyServicesTest extends TestCase {

	private static final String PRIMITIVE_TYPE_STRING = "String";
	private static final String PRIMITIVE_TYPE_INTEGER = "Integer";

	private static final String OLD_NAME = "oldName";

	private boolean initialized = false;
	
	private ResourceSet resourceSet;
	
	private PrimitiveType TYPE_STRING;
	private PrimitiveType TYPE_INTEGER;
	
	
	@Override
	protected void setUp() throws Exception {
		if (!initialized) {
			if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
				@SuppressWarnings("unused")
				EPackage pkg = UMLPackage.eINSTANCE;
				Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
			}
			
			resourceSet = new ResourceSetImpl();
			Resource resource = new ResourceImpl();
			resourceSet.getResources().add(resource);
			
			
			TYPE_STRING = UMLFactory.eINSTANCE.createPrimitiveType();
			TYPE_STRING.setName(PRIMITIVE_TYPE_STRING);
			resource.getContents().add(TYPE_STRING);
			
			TYPE_INTEGER = UMLFactory.eINSTANCE.createPrimitiveType();
			TYPE_INTEGER.setName(PRIMITIVE_TYPE_INTEGER);
			resource.getContents().add(TYPE_INTEGER);
			
			initialized = true;
		}
	}
	
	private Property createProperty(String name, Type type, int lower, int upper, boolean derived) {
		Property property = UMLFactory.eINSTANCE.createProperty();
		property.setName(name);
		property.setType(type);
		property.setLower(lower);
		property.setUpper(upper);
		property.setIsDerived(derived);
		
		resourceSet.getResources().get(0).getContents().add(property);
		
		return property;
	}
	
	private Property createPropertyStringMultiple() {
		return createProperty(OLD_NAME, TYPE_STRING, 0, -1, false);
	}
	
	public void testNameOnly() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_STRING, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}
	
	public void testNameOnlyWithWhitespaces() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "   	toto  	");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_STRING, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}
	
	public void testNameAndType() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto : Integer");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_INTEGER, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}
	
	public void testNameAndNotExistingType() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto : NonExistingType");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_STRING, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}
	
	public void testTypeOnly() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, " : Integer");
		assertEquals("", property.getName());
		assertEquals(TYPE_INTEGER, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}
	
	public void testIsDerivedAndName() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "/toto");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_STRING, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(true, property.isDerived());
	}
	
	public void testIsDerivedWithoutName() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "/");
		assertEquals("", property.getName());
		assertEquals(TYPE_STRING, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(true, property.isDerived());
	}
	
	public void testIsDerivedNameAndType() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "/toto : Integer");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_INTEGER, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(true, property.isDerived());
	}
	
	public void testIsDerivedNameAndNonExistingType() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "/toto : NonExistingType");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_STRING, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(true, property.isDerived());
	}
	
	public void testNotIsDerivedAndName() {
		Property property = createPropertyStringMultiple();
		property.setIsDerived(true);
		PropertyServices.parseInputLabel(property, "toto  ");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_STRING, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}
	
	public void testNameTypeAndMultiplicityUsingOneBound() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto : Integer [1] ");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_INTEGER, property.getType());
		assertEquals(1, property.getLower());
		assertEquals(1, property.getUpper());
		assertEquals(false, property.isDerived());
	}
	
	public void testNameTypeAndMultiplicityUsingOneBoundStar() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto : Integer [*] ");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_INTEGER, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}
	
	public void testNameTypeAndMultiplicityUsingOneBoundMinusOne() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto : Integer [-1] ");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_INTEGER, property.getType());
		assertEquals(0, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}
	
	public void testNameTypeAndMultiplicityWithDifferentBounds() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto : Integer [1..5] ");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_INTEGER, property.getType());
		assertEquals(1, property.getLower());
		assertEquals(5, property.getUpper());
		assertEquals(false, property.isDerived());
	}
	
	public void testNameTypeAndMultiplicityWithDifferentBoundsUsingStar() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "toto : Integer [1..*] ");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_INTEGER, property.getType());
		assertEquals(1, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}
	
	public void testNameTypeAndMultiplicityWithDifferentBoundsUsingMinusOne() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "  toto : Integer [5..-1] ");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_INTEGER, property.getType());
		assertEquals(5, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(false, property.isDerived());
	}
	
	public void testFullwithLotsOfSpaces() {
		Property property = createPropertyStringMultiple();
		PropertyServices.parseInputLabel(property, "  /   to   to    :    Integer     [    5    ..   -1   ]    ");
		assertEquals("to   to", property.getName());
		assertEquals(TYPE_INTEGER, property.getType());
		assertEquals(5, property.getLower());
		assertEquals(-1, property.getUpper());
		assertEquals(true, property.isDerived());
	}
	
	public void testNameTypeAndMultiplicityWithIncorrectBounds() {
		Property property = createPropertyStringMultiple();
		property.setLower(2);
		property.setUpper(4);
		PropertyServices.parseInputLabel(property, "  toto : Integer [*..*] ");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_INTEGER, property.getType());
		assertEquals(2, property.getLower());
		assertEquals(4, property.getUpper());
		assertEquals(false, property.isDerived());
	}
	
	public void testNameTypeAndMultiplicityWithIncorrectBounds2() {
		Property property = createPropertyStringMultiple();
		property.setLower(2);
		property.setUpper(4);
		PropertyServices.parseInputLabel(property, "  toto : Integer [-1..2] ");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_INTEGER, property.getType());
		assertEquals(2, property.getLower());
		assertEquals(4, property.getUpper());
		assertEquals(false, property.isDerived());
	}
	
	public void testNameTypeAndMultiplicityWithIncorrectBounds3() {
		Property property = createPropertyStringMultiple();
		property.setLower(2);
		property.setUpper(4);
		PropertyServices.parseInputLabel(property, "  toto : Integer [5..3] ");
		assertEquals("toto", property.getName());
		assertEquals(TYPE_INTEGER, property.getType());
		assertEquals(2, property.getLower());
		assertEquals(4, property.getUpper());
		assertEquals(false, property.isDerived());
	}
}
