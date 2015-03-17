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
import org.obeonetwork.dsl.uml2.design.internal.services.DashboardServices;
import org.obeonetwork.dsl.uml2.design.internal.services.LogServices;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Contribute to the open dashboard toolbar the different available dashboards per project name.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DashboardContributionItems extends CompoundContributionItem {
	/**
	 * Open dashboard command.
	 */
	public static final String OPEN_DASHBOARD_CMD_ID = "org.obeonetwork.dsl.uml2.actions.openDashboard"; //$NON-NLS-1$

	/**
	 * Model parameter.
	 */
	public static final String OPEN_DASHBOARD_CMD_PARAM_MODEL_KEY = "org.obeonetwork.dsl.uml2.actions.openDashboard.param.model"; //$NON-NLS-1$

	/**
	 * Constructor.
	 */
	public DashboardContributionItems() {
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 */
	public DashboardContributionItems(final String id) {
		super(id);
	}

	@Override
	protected IContributionItem[] getContributionItems() {
		final List<IContributionItem> menuItems = Lists.newArrayList();
		// Get all available dashboards
		final List<EObject> umlModelRoots = DashboardServices.INSTANCE.getUmlModelsWithDashboard();
		for (final EObject eObject : umlModelRoots) {
			// Get the project name
			final IFile resourceFile = ResourcesPlugin.getWorkspace().getRoot()
					.getFile(new Path(eObject.eResource().getURI().toPlatformString(true)));
			final String projectName = resourceFile.getProject().getName();

			final Map<String, String> parameters = Maps.newHashMap();
			final UmlElementConverter converter = new UmlElementConverter();
			try {
				parameters.put(OPEN_DASHBOARD_CMD_PARAM_MODEL_KEY, converter.convertToString(eObject));
			} catch (final ParameterValueConversionException e) {
				LogServices.INSTANCE.error("Opening dashboard for project " + projectName + " failed", e); //$NON-NLS-1$ //$NON-NLS-2$
			}
			final CommandContributionItemParameter contributionParameter = new CommandContributionItemParameter(
					PlatformUI.getWorkbench(), null, OPEN_DASHBOARD_CMD_ID, parameters, null, null, null,
					projectName, null, "Open the dashboard of the project : " + projectName, //$NON-NLS-1$
					CommandContributionItem.STYLE_PUSH, null, true);

			contributionParameter.visibleEnabled = true;
			// Create a new item in the menu for the project
			final CommandContributionItem commandContributionItem = new CommandContributionItem(
					contributionParameter);
			menuItems.add(commandContributionItem);
		}

		return menuItems.toArray(new IContributionItem[menuItems.size()]);
	}

}
