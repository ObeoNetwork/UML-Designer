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
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * Utility services to manage edges direct label edition on properties.
 *
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo .fr</a>
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public final class PropertyServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final PropertyServices INSTANCE = new PropertyServices();

	/**
	 * Hidden constructor.
	 */
	private PropertyServices() {
	}

	/**
	 * Update the {@link Property} multiplicity.
	 *
	 * @param property
	 *            the context {@link Property} object.
	 * @param lowerBound
	 *            the lower bound user content
	 * @param upperBound
	 *            the upper bound user content
	 */
	private void handleMultiplicity(Property property, String lowerBound, String upperBound) {
		final int lower = LabelServices.INSTANCE.convertBound(lowerBound);
		final int upper = LabelServices.INSTANCE.convertBound(upperBound);

		if (lower != Integer.MAX_VALUE && upper != Integer.MAX_VALUE && (lower <= upper || upper == -1)) {
			if (lower == -1) {
				property.setLower(0);
			} else {
				property.setLower(lower);
			}
			property.setUpper(upper);
		}
	}

	/**
	 * Parse the edited label content for the multiplicity & type part and update the underlying
	 * {@link Property}.
	 *
	 * @param property
	 *            the context {@link Property} object.
	 * @param typeInfo
	 *            the user edited label content for the multiplicity & type part.
	 */
	private void handleTypeAndMultiplicity(Property property, String typeInfo) {
		// Normal expression is
		// ^(.*)\[\s*((\S+)\s*\.\.)?\s*(\S+)\s*\]$
		// after doubling the backslashes it becomes
		final Pattern ptnTypeInfo = Pattern.compile("^(.*)\\[\\s*((\\S+)\\s*\\.\\.)?\\s*(\\S+)\\s*\\]$"); //$NON-NLS-1$

		// it can be read as :
		// anything from the beginning of the string until a "[" is encountered
		// then the multiplicity is provided between "[" and "]" characters
		// in a form like "lower..upper" or "upper"
		final Matcher mtchTypeInfo = ptnTypeInfo.matcher(typeInfo);

		String typeName = ""; //$NON-NLS-1$
		if (mtchTypeInfo.find()) {
			typeName = mtchTypeInfo.group(1);
			final String lowerBound = mtchTypeInfo.group(3);
			final String upperBound = mtchTypeInfo.group(4);

			// Checking validity of bounds
			// "-1" and "*" can't be used as a lower bound
			// "0" can't be used as a upper bound
			if ((lowerBound == null || lowerBound.matches("^[0-9]+$")) //$NON-NLS-1$
					&& upperBound.matches("^[1-9][0-9]*|\\*|-1$")) { //$NON-NLS-1$
				// Handling multiplicity
				if (lowerBound == null || "".equals(lowerBound)) { //$NON-NLS-1$
					handleMultiplicity(property, upperBound, upperBound);
				} else {
					handleMultiplicity(property, lowerBound, upperBound);
				}
			}
		} else {
			// only a type name has been provided
			typeName = typeInfo;
		}

		// Search the type and sets it on the property if found
		if (typeName != null && !"".equals(typeName)) { //$NON-NLS-1$
			Type foundType = ElementServices.INSTANCE.findTypeByName(property, typeName);

			if (foundType == null) {
				final Namespace namespace = (Namespace)property.eContainer();

				// Register primitive type
				if (isUmlPrimitiveType(namespace, typeName)) {
					ElementServices.INSTANCE.importUmlPrimitiveTypes(namespace);
				} else if (isEcorePrimitiveType(namespace, typeName)) {
					ElementServices.INSTANCE.importEcorePrimitiveTypes(namespace);
				} else if (isJavaPrimitiveType(namespace, typeName)) {
					ElementServices.INSTANCE.importJavaPrimitiveTypes(namespace);
				} else if (isXmlPrimitiveType(namespace, typeName)) {
					ElementServices.INSTANCE.importXmlPrimitiveTypes(namespace);
				}

				// Get the primitive type
				foundType = ElementServices.INSTANCE.findTypeByName(property, typeName);

				if (foundType == null) {
					// Create a new class
					foundType = UMLFactory.eINSTANCE.createClass();
					foundType.setName(typeName);
					final Package pkg = property.getOwner().getNearestPackage();
					pkg.getPackagedElements().add(foundType);
				}
			}

			property.setType(foundType);

		}
	}

	/**
	 * Indicates if a type name is an Ecore primitive type.
	 *
	 * @param namespace
	 *            the {@link Namespace} context
	 * @param typeName
	 *            Type name
	 * @return True if type name is a primitive type otherwise false
	 */
	public boolean isEcorePrimitiveType(Namespace namespace, String typeName) {
		return isPrimitiveType(namespace, typeName, UMLResource.ECORE_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Indicates if a type name is a Java primitive type.
	 *
	 * @param namespace
	 *            the {@link Namespace} context
	 * @param typeName
	 *            Type name
	 * @return True if type name is a primitive type otherwise false
	 */
	public boolean isJavaPrimitiveType(Namespace namespace, String typeName) {
		return isPrimitiveType(namespace, typeName, UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Indicates if a type name is a primitive type.
	 *
	 * @param namespace
	 *            the {@link Namespace} context
	 * @param typeName
	 *            Type name
	 * @param libraryUri
	 *            the URI of the library to load.
	 * @return True if type name is a primitive type otherwise false
	 */
	private boolean isPrimitiveType(Namespace namespace, String typeName, String libraryUri) {
		final ResourceSet resourceSet = namespace.eResource().getResourceSet();
		final Resource resource = resourceSet.getResource(URI.createURI(libraryUri), true);
		// Add the resource to the session's semantic resources
		final Package root = (Package)EcoreUtil.getObjectByType(resource.getContents(),
				UMLPackage.Literals.PACKAGE);
		// Search the type and sets it on the property if found
		if (typeName != null && !"".equals(typeName)) { //$NON-NLS-1$
			final Type foundType = ElementServices.INSTANCE.findTypeByName(root, typeName);
			if (foundType != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Indicates if a type name is a UML primitive type.
	 *
	 * @param namespace
	 *            the {@link Namespace} context
	 * @param typeName
	 *            Type name
	 * @return True if type name is a primitive type otherwise false
	 */
	public boolean isUmlPrimitiveType(Namespace namespace, String typeName) {
		return isPrimitiveType(namespace, typeName, UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Indicates if a type name is an XML primitive type.
	 *
	 * @param namespace
	 *            the {@link Namespace} context
	 * @param typeName
	 *            Type name
	 * @return True if type name is a primitive type otherwise false
	 */
	public boolean isXmlPrimitiveType(Namespace namespace, String typeName) {
		return isPrimitiveType(namespace, typeName, UMLResource.XML_PRIMITIVE_TYPES_LIBRARY_URI);
	}

	/**
	 * Parse the edited label content and update the underlying {@link Property} .
	 *
	 * @param property
	 *            the context {@link Property} object.
	 * @param inputLabel
	 *            the user edited label content.
	 * @return Property name
	 */
	public String parseInputLabel(Property property, String inputLabel) {
		// Smart edit multiplicity : x, x y, x..y, [x], [x y], [x..y], x..*,
		// [x..*], *, [*], -1, [-1]
		Pattern p = Pattern.compile("\\[?([0-9]*)[ .]*(([0-9 \\* \\-1]*)?)\\]?"); //$NON-NLS-1$
		Matcher m = p.matcher(inputLabel.trim());
		if (m.find() && m.group(1) != null && m.group(1).length() > 0
				|| m.group(2) != null && m.group(2).length() > 0) {
			final String lower = m.group(1).trim();
			final String upper = m.group(2).trim();
			if (upper != null && upper.length() > 0) {
				if ("*".equals(upper) || "-1".equals(upper)) { //$NON-NLS-1$ //$NON-NLS-2$
					property.setUpper(-1);
					property.setLower(0);
				} else {
					property.setUpper(Integer.parseInt(upper));
				}
			} else if (lower != null && lower.length() > 0) {
				property.setUpper(Integer.parseInt(lower));
			}

			if (lower != null && lower.length() > 0) {
				property.setLower(Integer.parseInt(lower));
			}
		}
		// Other cases
		else {
			// The discriminating parts in the label are :
			// - a "/" at the beginning for a derived property
			// - a ":" between the name and the type
			// - a "=" preceding the default value
			// Between these signs who all are optional one can find any
			// character
			p = Pattern.compile("^(/?)([^:]*):?([^=]*)=?(.*)$"); //$NON-NLS-1$
			m = p.matcher(inputLabel.trim());

			if (m.find()) {
				final boolean isDerived = m.group(1) != null && !"".equals(m.group(1)); //$NON-NLS-1$
				property.setIsDerived(isDerived);

				final String name = m.group(2).trim();
				if (name != null && name.length() > 0) {
					property.setName(name);
				}

				final String typeInfo = m.group(3).trim();
				handleTypeAndMultiplicity(property, typeInfo);

				// Use UML api to manage the default value
				if (m.group(4) != null && !"".equals(m.group(4))) { //$NON-NLS-1$
					final String defaultValue = m.group(4).trim();
					property.setDefault(defaultValue);
				}
				return name;
			}
		}
		return property.getName();
	}
}
