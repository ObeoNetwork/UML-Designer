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
package org.obeonetwork.dsl.uml2.discovery.handlers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.amalgam.discovery.DiscoveryDefinition;
import org.eclipse.amalgam.discovery.core.CancellableXMIResourceImpl;
import org.eclipse.amalgam.discovery.ui.viewer.DiscoveryContentProvider;
import org.eclipse.amalgam.discovery.ui.wizards.DiscoveryWizard;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.obeonetwork.dsl.uml2.discovery.UmlDiscoveryActivator;

/**
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlDiscovery extends DiscoveryContentProvider {
	private static final String CATALOG_URI = "http://www.umldesigner.org/discovery.xmi";

	@Override
	public DiscoveryDefinition load(IProgressMonitor monitor) throws InterruptedException {
		URI catalogURI = URI.createURI(CATALOG_URI);
		UmlDiscoveryActivator.getDefault().prepareProxySettings(CATALOG_URI);
		CancellableXMIResourceImpl res = new CancellableXMIResourceImpl(catalogURI, monitor);
		try {
			res.load(Collections.EMPTY_MAP);
		} catch (IOException e) {
			errorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), e);
		} catch (OperationCanceledException e) {
			throw new InterruptedException();
		}
		DiscoveryDefinition result = (DiscoveryDefinition)res.getContents().get(0);
		this.disco = result;
		return result;
	}

	private void errorDialog(final IWorkbenchWindow window, Exception e) {
		String message = "We can't connect to the discovery source: \n" + CATALOG_URI
				+ "\n Make sure you're connected to internet and try again.";
		MessageDialog.openError(window.getShell(), "Can't connect to discovery source", message);
		throw new RuntimeException(e);
	}

	@Override
	public String getDescription() {
		return "Pick a UML component to install it.";
	}

	@Override
	public String getTitle() {
		return "Eclipse UML Components Discovery";
	}

	public static void openUmlDiscoveryWizard(IWorkbenchWindow window) {
		final DiscoveryContentProvider provider = new UmlDiscovery();
		IWorkbench wb = PlatformUI.getWorkbench();
		IProgressService ps = wb.getProgressService();
		try {
			ps.busyCursorWhile(new IRunnableWithProgress() {

				public void run(IProgressMonitor pm) {
					try {
						provider.load(pm);
					} catch (InterruptedException e) {
						/*
						 * End user cancelled
						 */
					}
				}
			});

			if (provider.getDiscovery() != null) {
				DiscoveryWizard wizard = new DiscoveryWizard(provider);

				WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
				dialog.setMinimumPageSize(600, 400);
				dialog.open();
			}
		} catch (InvocationTargetException e1) {
			if (!(e1.getCause() instanceof OperationCanceledException)) {
				UmlDiscoveryActivator.getDefault().getLog()
						.log(new Status(IStatus.ERROR, UmlDiscoveryActivator.PLUGIN_ID, e1.getMessage(), e1));
			}
		} catch (InterruptedException e1) {
			/*
			 * End user cancelled
			 */
		}

	}

}
