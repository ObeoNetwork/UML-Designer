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

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;


/**
 * Utilities to get related elements.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class RelatedServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final RelatedServices INSTANCE = new RelatedServices();

	/**
	 * Hidden constructor.
	 */
	private RelatedServices() {

	}

	/**
	 * Get related elements.
	 *
	 * @param cur
	 *            Element
	 * @return Related elements
	 */
	public Collection<EObject> getRelated(EObject cur) {
		return new RelatedElementsSwitch().getRelatedElements(cur);
	}
}
