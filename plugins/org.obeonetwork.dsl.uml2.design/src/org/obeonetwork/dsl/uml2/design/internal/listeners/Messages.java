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
package org.obeonetwork.dsl.uml2.design.internal.listeners;

import org.eclipse.osgi.util.NLS;

/**
 * Messages.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.obeonetwork.dsl.uml2.design.internal.listeners.messages"; //$NON-NLS-1$

	/**
	 * Model explorer not found in Workbench.
	 */
	public static final String UmlModelExplorer_UI_ErrorMsg_NotFound = null;


	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
