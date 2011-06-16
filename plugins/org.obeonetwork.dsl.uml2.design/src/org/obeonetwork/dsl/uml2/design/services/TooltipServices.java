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

public class TooltipServices {
	
	public String computeUmlTooltip(Element element) {
		final TooltipLabelSwitch tooltipLabel = new TooltipLabelSwitch();
		return tooltipLabel.doSwitch(element);
	}
}
