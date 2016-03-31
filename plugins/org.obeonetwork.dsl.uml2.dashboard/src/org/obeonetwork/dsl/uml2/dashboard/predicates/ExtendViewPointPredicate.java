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
package org.obeonetwork.dsl.uml2.dashboard.predicates;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.predicates.IPredicate;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.obeonetwork.dsl.uml2.dashboard.services.DashboardServices;

/**
 * Check if UML designer Extend viewpoint is enable.
 *
 * @author Frederic Bats<a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class ExtendViewPointPredicate implements IPredicate {

	/**
	 * Constructor.
	 */
	public ExtendViewPointPredicate() {
		super();
	}

	public boolean isOk() {
		final Session session = ActivityExplorerManager.INSTANCE.getSession();

		if (ActivityExplorerManager.INSTANCE.getSession() != null) {
			return DashboardServices.INSTANCE.isEnabledVP(session, DashboardServices.INSTANCE.VP_EXTEND);
		}
		return false;
	}
}
