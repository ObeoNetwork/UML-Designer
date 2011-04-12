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
import org.obeonetwork.dsl.uml2.design.services.internal.DisplayLabelSwitch;

/**
 * Handle the diagram elements' label display.
 * 
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 */
public class DisplayLabelServices {

	public String computeUmlLabel(Element element) {
		final DisplayLabelSwitch displayLabel = new DisplayLabelSwitch();
		
		return displayLabel.doSwitch(element);
	}
}
