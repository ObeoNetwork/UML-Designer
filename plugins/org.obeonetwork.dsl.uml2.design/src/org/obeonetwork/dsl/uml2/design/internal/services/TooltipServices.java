/*******************************************************************************
 * Copyright (c) 2016 Obeo.
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

/**
 * Manage the diagram elements' tooltips.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class TooltipServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final TooltipServices INSTANCE = new TooltipServices();

	/**
	 * Hidden constructor.
	 */
	private TooltipServices() {

	}

	/**
	 * Compute the tooltip of the given element.
	 *
	 * @param element
	 *            the {@link EObject} for which to retrieve a tooltip.
	 * @return the computed tooltip.
	 */
	public String computeTooltip(EObject element) {
		final TooltipSwitch tooltip = new TooltipSwitch();
		if (element != null) {
			return tooltip.doSwitch(element);
		}
		return ""; //$NON-NLS-1$
	}
}
