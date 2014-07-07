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
import org.obeonetwork.dsl.uml2.design.services.AutosizeTrigger;

/**
 * Session listener.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlDesignerSessionManagerListener implements SessionManagerListener {

	public void notifyAddSession(Session newSession) {
		newSession.getEventBroker().addLocalTrigger(AutosizeTrigger.IS_GMF_NODE_ATTACHMENT,
				new AutosizeTrigger(newSession.getTransactionalEditingDomain()));
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

	public void notify(Session updated, int notification) {
		// Nothing
	}

}
