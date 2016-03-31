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
package org.obeonetwork.dsl.uml2.dashboard.activities.help;

/**
 * Link to open reference documentation help.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class OpenReferenceDocumentationHelpActivity extends OpenContextHelp {
	private static final String REFERENCE_DOCUMENTATION = "ReferenceDocumentation";//$NON-NLS-1$

	/**
	 * Constructor.
	 */
	public OpenReferenceDocumentationHelpActivity() {
		super(REFERENCE_DOCUMENTATION);
	}
}
