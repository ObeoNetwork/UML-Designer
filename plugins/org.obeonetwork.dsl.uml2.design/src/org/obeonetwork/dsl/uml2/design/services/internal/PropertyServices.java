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
package org.obeonetwork.dsl.uml2.design.services.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.obeonetwork.dsl.uml2.design.services.EcoreServices;
import org.obeonetwork.dsl.uml2.design.services.LabelServices;

/**
 * Utility services to manage edges direct label editoin on properties.
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public final class PropertyServices {

	/**
	 * Hidden constructor.
	 */
	private PropertyServices() {
	}

	/**
	 * Parse the edited label content and update the underlying {@link Property}.
	 * 
	 * @param property
	 *            the context {@link Property} object.
	 * @param inputLabel
	 *            the user edited label content.
	 * @return Property name
	 */
	public static String parseInputLabel(Property property, String inputLabel) {
		// The discriminating parts in the label are :
		// - a "/" at the beginning for a derived property
		// - a ":" between the name and the type
		// - a "=" preceding the default value
		// Between these signs who all are optional one can find any character
		final Pattern p = Pattern.compile("^(/?)([^:]*):?([^=]*)=?(.*)$");
		final Matcher m = p.matcher(inputLabel.trim());

		if (m.find()) {
			final boolean isDerived = m.group(1) != null && !"".equals(m.group(1));
			property.setIsDerived(isDerived);

			final String name = m.group(2).trim();
			property.setName(name);

			final String typeInfo = m.group(3).trim();
			handleTypeAndMultiplicity(property, typeInfo);

			// Use UML api to manage the default value
			if (m.group(4) != null && !"".equals(m.group(4))) {
				String defaultValue = m.group(4).trim();
				property.setDefault(defaultValue);
			}
			return name;
		}
		return null;
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
	private static void handleTypeAndMultiplicity(Property property, String typeInfo) {
		// Normal expression is
		// ^(.*)\[\s*((\S+)\s*\.\.)?\s*(\S+)\s*\]$
		// after doubling the backslashes it becomes
		final Pattern ptnTypeInfo = Pattern.compile("^(.*)\\[\\s*((\\S+)\\s*\\.\\.)?\\s*(\\S+)\\s*\\]$");

		// it can be read as :
		// anything from the beginning of the string until a "[" is encountered
		// then the multiplicity is provided between "[" and "]" characters
		// in a form like "lower..upper" or "upper"
		final Matcher mtchTypeInfo = ptnTypeInfo.matcher(typeInfo);

		String typeName = "";
		if (mtchTypeInfo.find()) {
			typeName = mtchTypeInfo.group(1);
			final String lowerBound = mtchTypeInfo.group(3);
			final String upperBound = mtchTypeInfo.group(4);

			// Checking validity of bounds
			// "-1" and "*" can't be used as a lower bound
			// "0" can't be used as a upper bound
			if ((lowerBound == null || lowerBound.matches("^[0-9]+$"))
					&& upperBound.matches("^[1-9][0-9]*|\\*|-1$")) {
				// Handling multiplicity
				if (lowerBound == null || "".equals(lowerBound)) {
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
		if (typeName != null && !"".equals(typeName)) {
			final Type foundType = EcoreServices.INSTANCE.findTypeByName(property, typeName);
			if (foundType != null) {
				property.setType(foundType);
			}
		}
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
	private static void handleMultiplicity(Property property, String lowerBound, String upperBound) {
		final Integer lower = LabelServices.convertBound(lowerBound);
		final Integer upper = LabelServices.convertBound(upperBound);

		if (lower != null && upper != null && (lower <= upper || upper == -1)) {
			if (lower == -1) {
				property.setLower(0);
			} else {
				property.setLower(lower);
			}
			property.setUpper(upper);
		}
	}
}
