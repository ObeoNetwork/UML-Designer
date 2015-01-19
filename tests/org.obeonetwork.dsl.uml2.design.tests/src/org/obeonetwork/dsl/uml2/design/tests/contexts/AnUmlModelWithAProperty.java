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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UMLPackage.Literals;
import org.eclipse.uml2.uml.resource.UMLResource;
// Start of user code AnUmlModelWithAProperty imports
import org.obeonetwork.dsl.uml2.design.internal.services.PropertyServices;
import org.obeonetwork.dsl.uml2.design.tests.automation.Context;

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

	private static final ResourceSet RESOURCE_SET = new ResourceSetImpl();

	protected static void registerPathmaps(URI baseUri) {
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP),
				baseUri.appendSegment("libraries").appendSegment(""));
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.METAMODELS_PATHMAP),
				baseUri.appendSegment("metamodels").appendSegment(""));
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.PROFILES_PATHMAP),
				baseUri.appendSegment("profiles").appendSegment(""));
	}

	protected static org.eclipse.uml2.uml.Package load(URI uri) throws Exception {
		Resource resource = RESOURCE_SET.getResource(uri, true);
		return (org.eclipse.uml2.uml.Package)EcoreUtil.getObjectByType(resource.getContents(),
				Literals.PACKAGE);
	}

	@Override
	public void setup() {
		// Start of user code AnUmlModelWithAProperty setup
		resourceSet = new ResourceSetImpl();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			@SuppressWarnings("unused")
			final EPackage pkg = UMLPackage.eINSTANCE;
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
					UMLResource.Factory.INSTANCE);
			resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);

			Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
					UMLResource.Factory.INSTANCE);

			final String umlProfile = "metamodels/UML.metamodel.uml";
			final URL url = AnUmlModelWithAProperty.class.getClassLoader().getResource(umlProfile);
			String baseUrl = url.toString();
			baseUrl = baseUrl.substring(0, baseUrl.length() - umlProfile.length());
			registerPathmaps(URI.createURI(baseUrl));
			try {
				load(URI.createURI(UMLResource.UML_METAMODEL_URI));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		File tempFile = new File("temp.uml");
		Resource resource = resourceSet.createResource(URI.createFileURI(tempFile.getAbsolutePath()));
		resourceSet.getResources().add(resource);
		Model model = UMLFactory.eINSTANCE.createModel();
		resource.getContents().add(model);

		stringPrimitiveType = UMLFactory.eINSTANCE.createPrimitiveType();
		stringPrimitiveType.setName(PRIMITIVE_TYPE_STRING);
		model.getPackagedElements().add(stringPrimitiveType);

		integerPrimitiveType = UMLFactory.eINSTANCE.createPrimitiveType();
		integerPrimitiveType.setName(PRIMITIVE_TYPE_INTEGER);
		model.getPackagedElements().add(integerPrimitiveType);

		org.eclipse.uml2.uml.Class clazz = UMLFactory.eINSTANCE.createClass();
		model.getPackagedElements().add(clazz);

		property = createPropertyStringMultiple();
		property.setIsDerived(true);
		property.setLower(2);
		property.setUpper(4);
		clazz.getOwnedAttributes().add(property);
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
		PropertyServices.INSTANCE.parseInputLabel(property, iEditTheLabelOfThePropertyTo0);
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
	 * Behavior : The lower bound of the property equals
	 */
	public void assertTheLowerBoundOfThePropertyEquals(String theLowerBoundOfThePropertyEquals0) {
		// Start of user code TheLowerBoundOfThePropertyEquals
		assertEquals(Integer.parseInt(theLowerBoundOfThePropertyEquals0), property.getLower());
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

	/**
	 * Behavior : The property name equals
	 */
	public void assertThePropertyNameEquals(String thePropertyNameEquals0) {
		// Start of user code ThePropertyNameEquals
		assertEquals(thePropertyNameEquals0, property.getName());
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
	 * Behavior : The property is derived
	 */
	public void assertThePropertyIsDerived() {
		// Start of user code ThePropertyIsDerived
		assertEquals(true, property.isDerived());
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
	 * @param name
	 *            the property name.
	 * @param type
	 *            the property {@link Type}.
	 * @param lower
	 *            the property lower bound
	 * @param upper
	 *            the property upper bound
	 * @param derived
	 *            the property derived flag
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
