/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * Messages used in UML designer preferences.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class PreferencesMessages extends NLS {
	private static final String BUNDLE_NAME = "org.obeonetwork.dsl.uml2.design.preferences.messages"; //$NON-NLS-1$

	/**
	 * Description.
	 */
	public static String UmlDesigner_PreferencePage_Description;

	/**
	 * Deletion confirmation.
	 */
	public static String UmlDesigner_PreferencePage_EnableDeletionConfirm;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, PreferencesMessages.class);
	}

	private PreferencesMessages() {
	}
}
