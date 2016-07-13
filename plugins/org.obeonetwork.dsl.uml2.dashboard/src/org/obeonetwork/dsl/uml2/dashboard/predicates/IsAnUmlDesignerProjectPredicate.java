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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.predicates.IPredicate;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.obeonetwork.dsl.uml2.dashboard.services.DashboardServices;

public class IsAnUmlDesignerProjectPredicate implements IPredicate {
	private final List<String> umlDesignerViewpoints = new ArrayList<String>(
			Arrays.asList(DashboardServices.INSTANCE.VP_CAPTURE, DashboardServices.INSTANCE.VP_DESIGN,
					DashboardServices.INSTANCE.VP_REVIEW, DashboardServices.INSTANCE.VP_EXTEND,
					DashboardServices.INSTANCE.VP_REUSED));

	public IsAnUmlDesignerProjectPredicate() {
	}

	public boolean isOk() {
		final Session session = ActivityExplorerManager.INSTANCE.getSession();
		if (session != null) {
			boolean isEnabledVP = false;
			final Iterator<String> vpIterator = umlDesignerViewpoints.iterator();
			while (vpIterator.hasNext() && isEnabledVP == false) {
				isEnabledVP = DashboardServices.INSTANCE.isEnabledVP(session, vpIterator.next());
			}
			if (isEnabledVP) {
				return isEnabledVP;
			}
		}
		return false;
	}
}
