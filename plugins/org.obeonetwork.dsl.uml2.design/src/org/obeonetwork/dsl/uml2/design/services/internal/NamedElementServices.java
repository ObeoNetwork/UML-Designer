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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;

/**
 * Utility services to manage name creation on sequence diagrams.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public final class NamedElementServices {

	/**
	 * Scenario element name prefix.
	 */
	private static final String SCENARIO_PREFIX = "Scenario_";

	/**
	 * Operation element name prefix.
	 */
	private static final String OPERATION_PREFIX = "Operation_";

	/**
	 * Hidden constructor.
	 */
	private NamedElementServices() {
	}

	/**
	 * Parse the edited package to find the name of the new interaction.
	 * 
	 * @param pkg
	 *            the container {@link Package} object.
	 * @return Name for new interaction.
	 */
	public static String getNewInteractionName(EObject pkg) {
		final StringBuffer name = new StringBuffer(SCENARIO_PREFIX);
		name.append(getNumberOfElements(((Package)pkg).getPackagedElements(), SCENARIO_PREFIX));
		return name.toString();
	}

	/**
	 * Parse the edited class to find the name of the new operation.
	 * 
	 * @param type
	 *            the container {@link org.eclipse.uml2.uml.Class} object.
	 * @return New operation name
	 */
	public static String getNewOperationName(org.eclipse.uml2.uml.Class type) {
		final StringBuffer name = new StringBuffer(OPERATION_PREFIX);
		name.append(getNumberOfElements(type.getOperations(), OPERATION_PREFIX));
		return name.toString();
	}

	/**
	 * Search the index of the last created element.
	 * 
	 * @param elements
	 *            List of elements.
	 * @param prefix
	 *            Prefix defining the index
	 * @return The index to use for a new element
	 */
	@SuppressWarnings("rawtypes")
	private static int getNumberOfElements(List elements, String prefix) {
		int lastUsedIndex = -1;
		for (Object element : elements) {
			final String name = ((NamedElement)element).getName();
			if (name != null && name.startsWith(prefix)) {
				final int index = Integer.valueOf(name.substring(name.lastIndexOf("_") + 1));
				if (index > lastUsedIndex)
					lastUsedIndex = index;
			}
		}

		return lastUsedIndex + 1;
	}
}
