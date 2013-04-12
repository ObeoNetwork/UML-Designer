/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.usage.analytics;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Activates listener on SIrius editors at startup.
 * 
 * @author Cedric Brun <a
 *         href="mailto:cedric.brun@obeo.fr">cedric.brun@obeo.fr</a>
 * @author Melanie Bats <a
 *         href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UsageStartup implements IStartup {

	/**
	 * Sirius editors listener.
	 */
	private SiriusEditorsListener usageListener = null;

	/**
	 * Called at Eclipse startup.
	 */
	public void earlyStartup() {
		PlatformUI.getWorkbench().addWindowListener(new IWindowListener() {

			public void windowOpened(IWorkbenchWindow window) {
			}

			public void windowDeactivated(IWorkbenchWindow window) {
			}

			public void windowClosed(IWorkbenchWindow window) {
			}

			public void windowActivated(IWorkbenchWindow window) {
				if (usageListener == null
						&& PlatformUI.getWorkbench() != null
						&& PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
					usageListener = new SiriusEditorsListener();
					PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getPartService().addPartListener(usageListener);
				}

			}

		});
	}

}
