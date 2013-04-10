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

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.obeonetwork.dsl.uml2.usage.dialog.UsageDialog;
import org.obeonetwork.dsl.uml2.usage.preferences.UsagePreferences;

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
	 * Usage preferences.
	 */
	private UsagePreferences preferences = new UsagePreferences();

	public void earlyStartup() {
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				try {
					askUser();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

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

	/**
	 * Ask to user if it is ok to send some statistics about its usage of UML
	 * Designer.
	 */
	private void askUser() {
		// Check preference to see if user already answer the question
		if (preferences.hasAnswered()) {
			return;
		}

		// User does not answer to the question, ask him
		Shell shell = PlatformUI.getWorkbench().getModalDialogShellProvider()
				.getShell();
		UsageDialog dialog = new UsageDialog(shell);
		int answer = dialog.open();

		// Set the user answer to the preference store in order to not ask him
		// anymore if he answered. If the user just close the dialog, the
		// question will be ask again at next startup. The user can update its
		// answer at any time in the preferences page.
		preferences.storeUserAnswer(answer);
	}

}
