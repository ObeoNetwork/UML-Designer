/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Lists;

/**
 * Services to handle Dashboard concerns.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DashboardServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final DashboardServices INSTANCE = new DashboardServices();

	/**
	 * Hidden constructor.
	 */
	private DashboardServices() {

	}

	/**
	 * Get all the root uml model elements which define a dashboard.
	 *
	 * @return List of model elements.
	 */
	public List<EObject> getUmlModelsWithDashboard() {
		final List<EObject> results = Lists.newArrayList();
		// Get all available dashboards
		final Collection<Session> sessions = SessionManager.INSTANCE.getSessions();
		for (final Session session : sessions) {
			// Check if the dashboard viewpoint is active for the current session
			final Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
			boolean isDashboardViewpointActive = false;
			for (final Viewpoint viewpoint : selectedViewpoints) {
				if (org.obeonetwork.dsl.uml2.design.api.services.DashboardServices.DASHBOARD_VP
						.equals(viewpoint.getName())) {
					isDashboardViewpointActive = true;
				}
			}
			if (isDashboardViewpointActive) {
				// Check if a dashboard representation exists for the current session
				final Collection<DRepresentation> representations = DialectManager.INSTANCE
						.getAllRepresentations(session);
				final Iterator<DRepresentation> iterator = representations.iterator();
				boolean findDashboard = false;
				while (iterator.hasNext() && !findDashboard) {
					final DRepresentation representation = iterator.next();
					if (representation instanceof DSemanticDiagram) {
						final DSemanticDiagram diagram = (DSemanticDiagram)representation;
						if (org.obeonetwork.dsl.uml2.design.api.services.DashboardServices.DASHBOARD_DIAGRAM_DESCRIPTION_ID
								.equals(diagram.getDescription().getName())) {
							final EObject target = ((DSemanticDiagram)representation).getTarget();
							results.add(target);
							findDashboard = true;
						}
					}
				}
			}
		}
		return results;
	}
}
