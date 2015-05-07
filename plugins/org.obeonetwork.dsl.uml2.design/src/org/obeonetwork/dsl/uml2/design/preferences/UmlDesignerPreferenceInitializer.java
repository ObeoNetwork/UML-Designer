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
package org.obeonetwork.dsl.uml2.design.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;

/**
 * Initialize default UML designer preferences value.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlDesignerPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		UMLDesignerPlugin.getDefault().getPreferenceStore()
		.setDefault(UmlDesignerPreferences.DELETION_CONFIRMATION_ENABLED_ID, true);
	}
}