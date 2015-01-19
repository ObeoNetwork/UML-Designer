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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.obeonetwork.dsl.uml2.design.internal.wizards.newmodel.UmlModelWizard;

/**
 * Create a new UML model command handler.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class NewUmlModel extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {

		final INewWizard wizard = new UmlModelWizard();

		// Initialize the selection
		final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		final ISelectionService service = window.getSelectionService();
		final IStructuredSelection selection = (IStructuredSelection)service
				.getSelection("org.eclipse.sirius.ui.tools.views.model.explorer"); //$NON-NLS-1$
		wizard.init(PlatformUI.getWorkbench(), selection);

		// Open the new uml model wizard
		final WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
		dialog.open();
		return null;
	}
}
