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
package org.obeonetwork.dsl.uml2.design.internal.listeners;

import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.obeonetwork.dsl.uml2.design.api.utils.UmlViewpoints;

/**
 * Uml designer session listener used to listen session opening and viewpoint selection change.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlDesignerSessionListener implements SessionListener {
	private final Session session;

	/**
	 * Constructor.
	 *
	 * @param session
	 *            the listened session
	 */
	public UmlDesignerSessionListener(Session session) {
		this.session = session;
	}

	public void notify(int changeKind) {
		if (changeKind == SessionListener.OPENED
				|| changeKind == SessionListener.SELECTED_VIEWS_CHANGE_KIND) {
			// The Reused viewpoint must not be disabled by the user as other
			// viewpoints depend on it, so it
			// is re-enabled automatically at the session opening or when the
			// user change the viewpoint
			// selection

			for (final Viewpoint selected : session.getSelectedViewpoints(false)) {
				final List<Viewpoint> umlDesignerViewpoints = UmlViewpoints.getUmlViewpoints();
				if (selected.getName().equals(UmlViewpoints.getReusedViewPoint().getName())) {
					return;
				}
				for (final Viewpoint umlVp : umlDesignerViewpoints) {
					if (selected.getName().equals(umlVp.getName())) {
						UmlViewpoints.enableReused(session);
						return;
					}
				}
			}
		}
	}
}
