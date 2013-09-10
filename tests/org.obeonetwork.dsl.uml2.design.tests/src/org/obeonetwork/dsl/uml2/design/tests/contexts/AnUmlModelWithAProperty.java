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

// Start of user code AnUmlModelWithAProperty imports
import org.obeonetwork.dsl.uml2.design.services.internal.PropertyServices;
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
// End of user code

/**
 * Context : An Uml model with a property
 */
public class AnUmlModelWithAProperty extends Context {
// Start of user code AnUmlModelWithAProperty variables
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
	 * The property.
	 */
	private Property property;
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
// End of user code

	@Override
	public void setup() {
		// Start of user code AnUmlModelWithAProperty setup
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
		
		property = createPropertyStringMultiple();
		property.setIsDerived(true);
		property.setLower(2);
		property.setUpper(4);
		// End of user code
	}

	@Override
	public void tearDown() {
		// Start of user code AnUmlModelWithAProperty tear down
		// Nothing
		// End of user code
	}
	/**
	 * Action : I edit the label of the property to
	 */
	public void actionIEditTheLabelOfThePropertyTo(String iEditTheLabelOfThePropertyTo0) {
		// Start of user code IEditTheLabelOfThePropertyTo
		PropertyServices.parseInputLabel(property, iEditTheLabelOfThePropertyTo0);
		// End of user code
	}

	/**
	 * Behavior : The upper bound of the property equals
	 */
	public void assertTheUpperBoundOfThePropertyEquals(String theUpperBoundOfThePropertyEquals0) {
		// Start of user code TheUpperBoundOfThePropertyEquals
		assertEquals(Integer.parseInt(theUpperBoundOfThePropertyEquals0), property.getUpper());
		// End of user code
	}
	/**
	 * Behavior : The property is not derived
	 */
	public void assertThePropertyIsNotDerived() {
		// Start of user code ThePropertyIsNotDerived
		assertEquals(false, property.isDerived());
		// End of user code
	}
	/**
	 * Behavior : The lower bound of the property equals
	 */
	public void assertTheLowerBoundOfThePropertyEquals(String theLowerBoundOfThePropertyEquals0) {
		// Start of user code TheLowerBoundOfThePropertyEquals
		assertEquals(Integer.parseInt(theLowerBoundOfThePropertyEquals0), property.getLower());
		// End of user code
	}
	/**
	 * Behavior : The property name equals
	 */
	public void assertThePropertyNameEquals(String thePropertyNameEquals0) {
		// Start of user code ThePropertyNameEquals
		assertEquals(thePropertyNameEquals0, property.getName());
		// End of user code
	}
	/**
	 * Behavior : The property is derived
	 */
	public void assertThePropertyIsDerived() {
		// Start of user code ThePropertyIsDerived
		assertEquals(true, property.isDerived());
		// End of user code
	}
	/**
	 * Behavior : The property type equals
	 */
	public void assertThePropertyTypeEquals(String thePropertyTypeEquals0) {
		// Start of user code ThePropertyTypeEquals
		assertEquals(thePropertyTypeEquals0, property.getType().getName());
		// End of user code
	}

// Start of user code AnUmlModelWithAProperty private methods
	/**
	 * Creates a String property [0..*].
	 * 
	 * @return the new property
	 */
	private Property createPropertyStringMultiple() {
		return createProperty(OLD_NAME, stringPrimitiveType, 0, -1, false);
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
// End of user code
}
