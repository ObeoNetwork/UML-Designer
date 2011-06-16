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
import org.obeonetwork.dsl.uml2.design.services.internal.EditLabelSwitch;

/**
 * Handle the diagram elements' label edition.
 * 
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 */
public class EditLabelServices {

	/**
	 * Parse the edited label string and update the underlying context {@link Element}.
	 * 
	 * @param context the context object on which to execute this service.
	 * @param editedLabelContent the content entered by the user.
	 * @return the context {@link Element}
	 */
	public Element editUmlLabel(Element context, String editedLabelContent) {
		final EditLabelSwitch editLabel = new EditLabelSwitch();
		editLabel.setEditedLabelContent(editedLabelContent);
		return editLabel.doSwitch(context);
	}

}
