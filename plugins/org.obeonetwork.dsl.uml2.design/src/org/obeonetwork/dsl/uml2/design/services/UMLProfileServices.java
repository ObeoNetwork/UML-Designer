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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;

/**
 * A set of Java services for UML profiled models.
 * 
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 * @author Frederic Thomas <a href="mailto:frederic.thomas@obeo.fr">frederic.thomas@obeo.fr</a>
 */

public class UMLProfileServices {

	/**
	 * Stereotype argument delimiter.
	 */
	private static final String SEPARATOR = ",";

	/**
	 * Returns a string describing the stereotype applied to the given element.<br/>
	 * It will display the name of each stereotype, then the value of its attributes if required.
	 * 
	 * @param elt
	 *            the stereotyped element to describe.
	 * @param attributesToDisplay
	 *            the comma separated list of stereotype attributes to describe.
	 * @return A String describing the stereotypes applied to the given element.
	 */
	public static String getStereotypesDescription(Element elt, String attributesToDisplay) {
		String description = "";

		final ArrayList<String> displayedAttributeList = new ArrayList<String>(Arrays
				.asList(attributesToDisplay.split(SEPARATOR)));

		for (final Iterator<Stereotype> stereotypesIterator = elt.getAppliedStereotypes().iterator(); stereotypesIterator
				.hasNext(); ) {
			final Stereotype stereotype = stereotypesIterator.next();

			description = description.concat("<<" + stereotype.getName() + ">>\n");

			for (final Iterator<Property> attributeIterator = stereotype.getAllAttributes().iterator(); attributeIterator
					.hasNext(); ) {
				final Property attribute = attributeIterator.next();

				if (displayedAttributeList.contains(attribute.getName())) {
					final Object obj = getDisplayContent(elt, stereotype, attribute.getName());
					if (obj != null) {
						description = description.concat(attribute.getName() + " = " + obj + "\n");
					}
				}
			}
		}

		return description;
	}

	/**
	 * Return the stereotype attribute value of the given stereotyped element.
	 * 
	 * @param elt
	 *            the stereotyped element
	 * @param stereotype
	 *            the stereotype applied to the stereotyped element
	 * @param attributeToDisplay
	 *            the attribute name we want to retreive
	 * @return the value of the stereotype attribute.
	 */
	private static Object getDisplayContent(Element elt, Stereotype stereotype, String attributeToDisplay) {
		final Object obj = elt.getValue(stereotype, attributeToDisplay);

		if (obj instanceof NamedElement) {
			return ((NamedElement)obj).getName();
		}

		return obj;
	}

	/**
	 * Checks if the element is referenced as a tagged value.
	 * 
	 * @param elt
	 *            the element to search as a tagged value
	 * @param stereotypedElement
	 *            the element stereotyped in which the element may be referenced
	 * @param stereo
	 *            the stereotype in which the element may be referenced
	 * @param tag
	 *            the tag which may reference the element
	 * @return Returns true is the given element is referenced by the tag, false otherwise.
	 */
	public Boolean isReferencedBy(Element elt, Element stereotypedElement, Stereotype stereo, Property tag) {
		if (stereotypedElement != null && stereotypedElement.isStereotypeApplied(stereo)) {
			final BasicEList<Element> values = new BasicEList<Element>();
			final Object value = stereotypedElement.getValue(stereo, tag.getName());
			// The value of a tag can be a list
			if (value instanceof EList) {
				values.addAll((EList<Element>)value);
			} else {
				values.add((Element)value);
			}
			return values.contains(elt);
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the element is referenced as a tagged value.
	 * 
	 * @param elt
	 *            the element to search as a tagged value
	 * @param stereotypedElement
	 *            the element stereotyped in which the element may be referenced
	 * @param stereotypeName
	 *            the stereotype name in which the element may be referenced
	 * @param tagName
	 *            the tag name which may reference the element
	 * @return Returns true is the given element is referenced by the tag, false otherwise.
	 */
	public Boolean isReferencedBy(Element elt, Element stereotypedElement, String stereotypeName, String tagName) {
		Stereotype stereotype = null;
		// Get the stereotypes
		final EList<Stereotype> stereotypes = stereotypedElement.getAppliedStereotypes();
		for (final Iterator iterator = stereotypes.iterator(); iterator.hasNext() && stereotype == null; ) {
			final Stereotype localStereotype = (Stereotype)iterator.next();
			if (localStereotype.getName().equalsIgnoreCase(stereotypeName)) {
				stereotype = localStereotype;
			}
		}
		
		if (stereotype!= null && stereotypedElement != null && stereotypedElement.isStereotypeApplied(stereotype)) {
			final BasicEList<Element> values = new BasicEList<Element>();
			final Object value = stereotypedElement.getValue(stereotype, tagName);
			// The value of a tag can be a list
			if (value instanceof EList) {
				values.addAll((EList<Element>)value);
			} else {
				values.add((Element)value);
			}
			return values.contains(elt);
		} else {
			return false;
		}
	}

	/**
	 * Checks if the element is referenced as a tagged value.
	 * 
	 * @param elt
	 *            the element to search as a tagged value
	 * @param stereotypedElement
	 *            the element stereotyped in which the element may be referenced
	 * @param stereotypeName
	 *            the name of the stereotype in which the element may be referenced
	 * @return Returns true is the given element is referenced by a tag of the stereotyped element, false
	 *         otherwise.
	 */
	public Boolean isReferencedBy(Element elt, Element stereotypedElement, String stereotypeName) {
		Boolean isReferenced = false;
		Stereotype stereotype = null;
		// Get the stereotypes
		final EList<Stereotype> stereotypes = stereotypedElement.getAppliedStereotypes();
		for (final Iterator iterator = stereotypes.iterator(); iterator.hasNext() && stereotype == null; ) {
			final Stereotype localStereotype = (Stereotype)iterator.next();
			if (localStereotype.getName().equalsIgnoreCase(stereotypeName)) {
				stereotype = localStereotype;
			}
		}
		// Check for each tag if the elt is referenced
		if (stereotype != null) {
			for (final Iterator<Property> iterator = stereotype.getOwnedAttributes().iterator(); iterator.hasNext()
					&& !isReferenced; ) {
				final Property tag = (Property)iterator.next();
				// if the type of the tag is a property or an operation of the UML metamodel then check the
				// reference
				if ("Property".equalsIgnoreCase(tag.getType().getName())
						| "Operation".equalsIgnoreCase(tag.getType().getName())) {
					isReferenced = isReferencedBy(elt, stereotypedElement, stereotype, tag);
				}
			}
		}
		// Otherwise return false -> the stereotype is not applied or the elt is not referenced
		return isReferenced;
	}
	
	
	
}
