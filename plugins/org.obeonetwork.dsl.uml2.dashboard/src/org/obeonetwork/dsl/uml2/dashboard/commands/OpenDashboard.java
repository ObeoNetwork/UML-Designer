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
package org.obeonetwork.dsl.uml2.dashboard.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.ParameterValueConversionException;
import org.eclipse.core.resources.IProject;
import org.obeonetwork.dsl.uml2.dashboard.services.DashboardServices;

/**
 * Open a Dashboard based on activity explorer.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class OpenDashboard extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Get for which project we should open the dashboard
		final String paramModel = event
				.getParameter(DashboardContributionItems.OPEN_DASHBOARD_CMD_PARAM_MODEL_KEY);
		final IProjectConverter converter = new IProjectConverter();
		try {
			final IProject project = (IProject)converter.convertToObject(paramModel);
			DashboardServices.INSTANCE.openDashboard(project);
		} catch (final ParameterValueConversionException e) {
			LogServices.INSTANCE.error("Opening dashboard failed", e); //$NON-NLS-1$
		}

		return null;
	}
}
