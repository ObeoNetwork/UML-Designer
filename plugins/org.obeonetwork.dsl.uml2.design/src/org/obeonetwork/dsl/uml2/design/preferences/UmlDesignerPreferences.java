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

import org.eclipse.jface.preference.IPreferenceStore;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;

/**
 * Preferences for Uml Designer.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlDesignerPreferences {
	/**
	 * Key for deletion confirmation enabled preference in preference store.
	 */
	public static final String DELETION_CONFIRMATION_ENABLED_ID = "deletion_confirmation_enabled_preference"; //$NON-NLS-1$

	/**
	 * The preference store.
	 */
	private final IPreferenceStore preferenceStore = UMLDesignerPlugin.getDefault().getPreferenceStore();

	/**
	 * Check if the deletion confirmation is enabled.
	 *
	 * @return True if enabled otherwise false
	 */
	public boolean isDeletionConfirmationEnabled() {
		if (preferenceStore.getBoolean(DELETION_CONFIRMATION_ENABLED_ID)) {
			return true;
		}
		return false;
	}
}
