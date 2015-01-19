/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.api.utils;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Type;
import org.obeonetwork.dsl.uml2.design.internal.services.ElementServices;

/**
 * Utilities to handle UML elements.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlUtils {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final UmlUtils INSTANCE = new UmlUtils();

	/**
	 * Find a {@link Type} element that match the given name in the ResourceSet of the given element.
	 *
	 * @param object
	 *            the object for which to find a corresponding type.
	 * @param typeName
	 *            the type name to match.
	 * @return the found {@link Type} element or <code>null</code>
	 */
	public Type findTypeByName(EObject object, String typeName) {
		return ElementServices.INSTANCE.findTypeByName(object, typeName);
	}

}
