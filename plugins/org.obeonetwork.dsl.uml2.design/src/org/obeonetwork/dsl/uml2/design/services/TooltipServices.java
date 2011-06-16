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

import org.eclipse.uml2.uml.Element;
import org.obeonetwork.dsl.uml2.design.services.internal.TooltipLabelSwitch;

/**
 * Service class to handle the tooltip generation.
 *
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class TooltipServices {

	/**
	 * Compute the tooltip for the given element.
	 * 
	 * @param context the context object on which to execute this service.
	 * @return the computed tooltip String to display
	 */
	public String computeUmlTooltip(Element context) {
		final TooltipLabelSwitch tooltipLabel = new TooltipLabelSwitch();
		return tooltipLabel.doSwitch(context);
	}
}
