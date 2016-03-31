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
package org.obeonetwork.dsl.uml2.dashboard.activities.help;

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.BlankHyperLinkAdapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.HyperlinkEvent;

/**
 * Link to open help context.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class OpenContextHelp extends BlankHyperLinkAdapter {
	private final String contextId;

	/**
	 * Constructor.
	 *
	 * @param contextId
	 *            context id
	 */
	public OpenContextHelp(String contextId){
		// Context ids are defined in the html/contexts.xml file in
		// org.obeonetwork.dsl.uml2.design.doc project.
		this.contextId =  "org.obeonetwork.dsl.uml2.design.doc." + contextId;//$NON-NLS-1$
	}

	@Override
	protected void linkPressed(HyperlinkEvent event, EObject project, Session session) {
		openContextHelp();
	}

	private void openContextHelp() {
		if (PlatformUI.getWorkbench() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
				&& PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell() != null) {
			PlatformUI.getWorkbench().getHelpSystem()
			.setHelp(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), contextId);
			PlatformUI.getWorkbench().getHelpSystem().displayDynamicHelp();
		}
	}
}
