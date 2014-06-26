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
package org.obeonetwork.dsl.uml2.design.ui.extension.commands;

import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ParameterValueConversionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.obeonetwork.dsl.uml2.design.services.DashboardServices;
import org.obeonetwork.dsl.uml2.design.services.LogServices;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Contribute to the open dashboard toolbar the different available dashboards per project name.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DashboardContributionItems extends CompoundContributionItem {
	public static final String OPEN_DASHBOARD_CMD_ID = "org.obeonetwork.dsl.uml2.actions.openDashboard";

	public static final String OPEN_DASHBOARD_CMD_PARAM_MODEL_KEY = "org.obeonetwork.dsl.uml2.actions.openDashboard.param.model";

	public DashboardContributionItems() {
	}

	public DashboardContributionItems(final String id) {
		super(id);
	}

	@Override
	protected IContributionItem[] getContributionItems() {
		List<IContributionItem> menuItems = Lists.newArrayList();
		// Get all available dashboards
		DashboardServices service = new DashboardServices();
		List<EObject> umlModelRoots = service.getUmlModelsWithDashboard();
		for (EObject eObject : umlModelRoots) {
			// Get the project name
			IFile resourceFile = ResourcesPlugin.getWorkspace().getRoot()
					.getFile(new Path(eObject.eResource().getURI().toPlatformString(true)));
			String projectName = resourceFile.getProject().getName();

			Map<String, String> parameters = Maps.newHashMap();
			UmlElementConverter converter = new UmlElementConverter();
			try {
				parameters.put(OPEN_DASHBOARD_CMD_PARAM_MODEL_KEY, converter.convertToString(eObject));
			} catch (ParameterValueConversionException e) {
				LogServices logService = new LogServices();
				logService.error("Opening dashboard for project " + projectName + " failed", e);
			}
			final CommandContributionItemParameter contributionParameter = new CommandContributionItemParameter(
					PlatformUI.getWorkbench(), null, OPEN_DASHBOARD_CMD_ID, parameters, null, null, null,
					projectName, null, "Open the dashboard of the project : " + projectName,
					CommandContributionItem.STYLE_PUSH, null, true);

			contributionParameter.visibleEnabled = true;
			// Create a new item in the menu for the project
			CommandContributionItem commandContributionItem = new CommandContributionItem(
					contributionParameter);
			menuItems.add(commandContributionItem);
		}

		return menuItems.toArray(new IContributionItem[menuItems.size()]);
	}

}
