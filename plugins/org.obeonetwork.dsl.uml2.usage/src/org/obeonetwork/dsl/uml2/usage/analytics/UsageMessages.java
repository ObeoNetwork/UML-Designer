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
package org.obeonetwork.dsl.uml2.usage.analytics;

import org.eclipse.osgi.util.NLS;

/**
 * Messages used in usage enablement dialog.
 * 
 * @author Melanie Bats <a
 *         href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UsageMessages extends NLS {
	private static final String BUNDLE_NAME = "org.obeonetwork.dsl.uml2.usage.analytics.messages"; //$NON-NLS-1$

	public static String Usage_GoogleAnalytics;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, UsageMessages.class);
	}

	private UsageMessages() {
	}
}
