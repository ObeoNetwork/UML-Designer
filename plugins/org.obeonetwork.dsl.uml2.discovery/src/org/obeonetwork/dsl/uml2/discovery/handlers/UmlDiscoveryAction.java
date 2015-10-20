/*******************************************************************************
 * Copyright (c)  2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.discovery.handlers;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlDiscoveryAction extends Action {

	/**
	 * The constructor.
	 */
	public UmlDiscoveryAction() {
	}

	@Override
	public void run() {

		final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		UmlDiscovery.openUmlDiscoveryWizard(window);

	}
}
