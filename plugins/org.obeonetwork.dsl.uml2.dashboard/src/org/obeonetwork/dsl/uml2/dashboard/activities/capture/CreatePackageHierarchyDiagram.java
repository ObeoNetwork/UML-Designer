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
package org.obeonetwork.dsl.uml2.dashboard.activities.capture;

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.NewDiagramHyperLinkAdapter;

/**
 * Link to create a Package Hierarchy diagram.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class CreatePackageHierarchyDiagram extends NewDiagramHyperLinkAdapter {
	private static final String PACKAGE_HIERARCHY = "Package Hierarchy"; //$NON-NLS-1$

	@Override
	public String getRepresentationName() {
		return PACKAGE_HIERARCHY;
	}
}
