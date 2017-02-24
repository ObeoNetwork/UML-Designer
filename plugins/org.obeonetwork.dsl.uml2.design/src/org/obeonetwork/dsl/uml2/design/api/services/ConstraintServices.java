/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.api.services;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Namespace;

/**
 * @author Axel Richard <a href="mailto:axel.richard@obeo.fr">axel.richard@obeo.fr</a>
 */
public class ConstraintServices {

	/**
	 * Returns the associated Namespace element if found, <code>null</code> otherwise.
	 *
	 * @param object
	 *            the object for which we want to find the associated Namespace.
	 * @return the associated Namespace element if found, <code>null</code> otherwise.
	 */
	public Namespace getAssociatedNamespace(EObject object) {
		final Namespace namespace;
		if (object instanceof Namespace) {
			namespace = (Namespace)object;
		} else if (object != null) {
			namespace = getAssociatedNamespace(object.eContainer());
		} else {
			namespace = null;
		}
		return namespace;
	}
}
