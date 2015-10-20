/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.internal.commands;

import java.util.Collection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.ParameterValueConversionException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.obeonetwork.dsl.uml2.design.api.services.DashboardServices;
import org.obeonetwork.dsl.uml2.design.internal.services.LogServices;

/**
 * Open a dashboard representation.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class OpenDashboard extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Get for which project we should open the dashboard representation
		final String paramModel = event
				.getParameter(DashboardContributionItems.OPEN_DASHBOARD_CMD_PARAM_MODEL_KEY);
		final UmlElementConverter converter = new UmlElementConverter();
		try {
			final EObject eObj = (EObject)converter.convertToObject(paramModel);
			final Session session = SessionManager.INSTANCE.getSession(eObj);
			if (session != null) {
				final Collection<DRepresentation> representations = DialectManager.INSTANCE
						.getAllRepresentations(session);
				for (final DRepresentation representation : representations) {
					if (representation instanceof DSemanticDiagram) {
						final DSemanticDiagram diagram = (DSemanticDiagram)representation;
						if (DashboardServices.DASHBOARD_DIAGRAM_DESCRIPTION_ID
								.equals(diagram.getDescription().getName())) {
							DialectUIManager.INSTANCE.openEditor(session, representation,
									new NullProgressMonitor());
						}
					}
				}
			}

		} catch (final ParameterValueConversionException e) {
			LogServices.INSTANCE.error("Opening dashboard failed", e); //$NON-NLS-1$
		}

		return null;
	}
}
