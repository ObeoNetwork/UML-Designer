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
package org.obeonetwork.dsl.uml2.design;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.PlatformUI;
import org.obeonetwork.dsl.uml2.design.services.AutosizeTrigger;

/**
 * Session listener.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlDesignerSessionManagerListener implements SessionManagerListener {

	private final OpenHelpContextListener openedEditorListener = new OpenHelpContextListener();

	public void notifyAddSession(Session newSession) {
		newSession.getEventBroker().addLocalTrigger(AutosizeTrigger.IS_GMF_NODE_ATTACHMENT,
				new AutosizeTrigger(newSession.getTransactionalEditingDomain()));
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService()
				.addPartListener(openedEditorListener);
	}

	public void notifyRemoveSession(Session removedSession) {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService()
				.removePartListener(openedEditorListener);
	}

	public void viewpointSelected(Viewpoint selectedSirius) {
		// Nothing
	}

	public void viewpointDeselected(Viewpoint deselectedSirius) {
		// Nothing
	}

	public void notify(Session updated, int notification) {
		// Nothing
	}

}
