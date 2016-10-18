/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.dashboard.listeners;

import java.lang.ref.WeakReference;

import org.eclipse.amalgam.explorer.activity.ui.ActivityExplorerActivator;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.amalgam.explorer.activity.ui.api.preferences.PreferenceConstants;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * Activate the activity explorer opening only for UML resources.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DashboardSessionListener implements SessionManagerListener {

	public void notifyAddSession(Session newSession) {
		// Nothing
	}

	public void notifyRemoveSession(Session removedSession) {
		// Nothing
	}

	public void viewpointSelected(Viewpoint selectedSirius) {
		// Nothing
	}

	public void viewpointDeselected(Viewpoint deselectedSirius) {
		// Nothing
	}

	public void notify(Session sessionp, int notification) {
		WeakReference<Session> session = new WeakReference<Session>(sessionp);
		switch (notification) {
			case SessionListener.OPENED:
				if ((session.get() != null) && !(session.get().getSemanticResources().isEmpty())) {
					for (Resource resource : session.get().getSemanticResources()) {
						// Check that we have an UML model
						if (resource instanceof UMLResource) {
							final boolean initialValue = ActivityExplorerActivator.getDefault()
									.getPreferenceStore()
									.getBoolean(PreferenceConstants.P_OPEN_ACTIVITY_EXPLORER);
							// Open the activity explorer
							ActivityExplorerActivator.getDefault().getPreferenceStore()
									.setValue(PreferenceConstants.P_OPEN_ACTIVITY_EXPLORER, true);
							ActivityExplorerManager.INSTANCE.openEditor(session.get());
							ActivityExplorerActivator.getDefault().getPreferenceStore()
									.setValue(PreferenceConstants.P_OPEN_ACTIVITY_EXPLORER, initialValue);
							break;
						}
					}
				}
		}
	}
}
