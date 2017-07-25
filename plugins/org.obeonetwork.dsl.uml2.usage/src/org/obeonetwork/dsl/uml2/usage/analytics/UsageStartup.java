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

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener.Stub;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.obeonetwork.dsl.uml2.usage.dialog.UsageDialog;
import org.obeonetwork.dsl.uml2.usage.preferences.UsagePreferences;

/**
 * Activates listener on Sirius editors at startup.
 * 
 * @author Cedric Brun <a href="mailto:cedric.brun@obeo.fr">cedric.brun@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UsageStartup extends Stub {

	/**
	 * Sirius editors listener.
	 */
	private SiriusEditorsListener usageListener = null;

	private IWindowListener windowListener = null;

	@Override
	public void notify(Session updated, int notification) {
		/*
		 * we could filter for SessionListener.OPENED events but it might be possible that this event even
		 * before the workbench is up and ready. It sounds more fit to keeps being notified for all the events
		 * but only doing something once and checking that swiftly.
		 */
		if (windowListener == null && PlatformUI.getWorkbench() != null) {
			windowListener = new IWindowListener() {

				public void windowOpened(IWorkbenchWindow window) {
				}

				public void windowDeactivated(IWorkbenchWindow window) {
				}

				public void windowClosed(IWorkbenchWindow window) {
				}

				public void windowActivated(IWorkbenchWindow window) {
					if (usageListener == null && PlatformUI.getWorkbench() != null
							&& PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {

						usageListener = new SiriusEditorsListener();
						UsagePreferences preferences = new UsagePreferences();
						askUser(preferences);
						if (preferences.isEnabled()) {
							SessionManager.INSTANCE.addSessionsListener(usageListener);
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService()
									.addPartListener(usageListener);
							/*
							 * a Dialect editor might be opened before arriving here, for instance if Eclipse
							 * was restarted with an opened editor. To make sure we count this we go through
							 * all the pages and all the corresponding editor references and "pretend" they
							 * got opened.
							 */
							for (IWorkbenchPage page : window.getPages()) {
								for (IEditorReference ref : page.getEditorReferences()) {
									usageListener.partOpened(ref);
								}
							}
						}
					}
				}

			};
			PlatformUI.getWorkbench().addWindowListener(windowListener);
		}
	}

	/**
	 * Ask to user if it is ok to send some statistics about its usage of UML Designer.
	 */
	private void askUser(UsagePreferences preferences) {
		// Check preference to see if user already answer the question
		if (preferences.hasAnswered()) {
			return;
		}

		// User does not answer to the question, ask him
		Shell shell = PlatformUI.getWorkbench().getModalDialogShellProvider().getShell();
		UsageDialog dialog = new UsageDialog(shell);
		int answer = dialog.open();

		// Set the user answer to the preference store in order to not ask him
		// anymore if he answered. If the user just close the dialog, the
		// question will be ask again at next startup. The user can update its
		// answer at any time in the preferences page.
		preferences.storeUserAnswer(answer);
	}
}
