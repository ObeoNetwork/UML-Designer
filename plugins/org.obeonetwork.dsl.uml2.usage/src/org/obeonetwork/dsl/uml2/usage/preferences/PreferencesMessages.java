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
package org.obeonetwork.dsl.uml2.usage.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * Messages used in usage report preferences.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class PreferencesMessages extends NLS {
	private static final String BUNDLE_NAME = "org.obeonetwork.dsl.uml2.usage.preferences.messages"; //$NON-NLS-1$

	public static String Usage_PreferencePage_ReportedValues;

	public static String Usage_PreferencePage_Locale;

	public static String Usage_PreferencePage_OperatingSystem;

	public static String Usage_PreferencePage_OperatingSystemVersion;

	public static String Usage_PreferencePage_ProductId;

	public static String Usage_PreferencePage_ProductVersion;

	public static String Usage_PreferencePage_Diagrams;

	public static String Usage_PreferencePage_AllowReporting;

	public static String Usage_PreferencePage_Description;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, PreferencesMessages.class);
	}

	private PreferencesMessages() {
	}
}
