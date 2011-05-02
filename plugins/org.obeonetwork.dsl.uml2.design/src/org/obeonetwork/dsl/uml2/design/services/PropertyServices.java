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
package org.obeonetwork.dsl.uml2.design.services;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
/**
 * Utility services to manage edges direct label editoin on properties
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class PropertyServices {

	static public void parseInputLabel(Property property, String inputLabel) {
		// The discriminating parts in the label are : 
		// - a "/" at the beginning for a derived property
		// - a ":" between the name and the type
		// - a "=" preceding the default value
		// Between these signs who all are optional one can find any character
		Pattern p = Pattern.compile("^(/?)([^:]*):?([^=]*)=?.*$");
		Matcher m = p.matcher(inputLabel.trim());
		
		if (m.find()) {
			boolean isDerived = (m.group(1) != null && !"".equals(m.group(1)));
			property.setIsDerived(isDerived);
			
			String name = m.group(2).trim();
			property.setName(name);
			
			String typeInfo = m.group(3).trim();
			handleTypeAndMultiplicity(property, typeInfo);
		}
	}
	
	static private void handleTypeAndMultiplicity(Property property, String typeInfo) {
		// Normal expression is 
		// ^(.*)\[\s*((\S+)\s*\.\.)?\s*(\S+)\s*\]$
		// after doubling the backslashes it becomes
		Pattern ptnTypeInfo = Pattern.compile("^(.*)\\[\\s*((\\S+)\\s*\\.\\.)?\\s*(\\S+)\\s*\\]$");
		
		// it can be read as :
		// anything from the beginning of the string until a "[" is encountered
		// then the multiplicity is provided between "[" and "]" characters
		// in a form like "lower..upper" or "upper"
		Matcher mtchTypeInfo = ptnTypeInfo.matcher(typeInfo);
		
		String typeName = "";
		if (mtchTypeInfo.find()) {
			typeName = mtchTypeInfo.group(1);
			String lowerBound = mtchTypeInfo.group(3);
			String upperBound = mtchTypeInfo.group(4);
			
			// Checking validity of bounds
			// "-1" and "*" can't be used as a lower bound
			// "0" can't be used as a upper bound
			if ((lowerBound == null || lowerBound.matches("^[0-9]+$")) && 
				upperBound.matches("^[1-9][0-9]*|\\*|-1$")) {
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
			Type foundType = EcoreServices.findTypeByName(property, typeName);
			if (foundType != null) {
				property.setType(foundType);
			}
		}
	}
	
	static private void handleMultiplicity(Property property, String lowerBound, String upperBound) {
		Integer lower = convertBound(lowerBound);
		Integer upper = convertBound(upperBound);
		
		if (lower != null && upper != null && (lower <= upper || upper == -1)) {
			if (lower == -1) {
				property.setLower(0);
			} else {
				property.setLower(lower);
			}
			property.setUpper(upper);
		}
	}
	
	static private Integer convertBound(String bound) {
		if ("*".equals(bound)) {
			return new Integer(-1);
		}
		try {
			return new Integer(bound);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
