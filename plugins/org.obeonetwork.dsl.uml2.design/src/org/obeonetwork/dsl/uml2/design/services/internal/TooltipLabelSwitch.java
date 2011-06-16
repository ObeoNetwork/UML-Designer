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

import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Property;

/**
 * A switch that handle the tooltip label computation for each UML types.
 *
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class TooltipLabelSwitch extends DisplayLabelSwitch {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseProperty(Property object) {
		String tooltip = caseStructuralFeature(object);
		if (object.getDefault() != null && !"".equals(object.getDefault().trim())) {
			// is the label on multiple lines ?
			if (object.getDefault().contains(NL)) {
				tooltip += " = {";
				final String[] defaultValue = object.getDefault().split(NL);
				// We use only the 5 first lines
				for (int i = 0; i < defaultValue.length; i++) {
					if (i >= 4) {
						tooltip += NL + "...";
						break;
					} else {
						tooltip += NL + defaultValue[i];
					}
				}
				tooltip += NL + "}";
			} else {
				tooltip += " = " + object.getDefault();
			}
		} else if (object.getDefaultValue() instanceof InstanceValue) {
			tooltip += " = " + ((InstanceValue)object.getDefaultValue()).getName();
		}
		return tooltip;
	}
}
