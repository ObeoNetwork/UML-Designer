/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.internal.services;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;

/**
 * Services to handle typed Setereotype concerns.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class StereotypeServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final StereotypeServices INSTANCE = new StereotypeServices();

	/**
	 * Get base class associated to a stereotype application.
	 *
	 * @param stereotypeApplication
	 *            Stereotype application
	 * @return Base class
	 */
	public Element getBaseClass(EObject stereotypeApplication) {
		return (Element)stereotypeApplication.eGet(stereotypeApplication.eClass().getEStructuralFeature(
				"base_Class")); //$NON-NLS-1$
	}
}
