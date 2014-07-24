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
package org.obeonetwork.dsl.uml2.design.ui.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.ui.wizard.ExampleInstallerWizard;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;
import org.obeonetwork.dsl.uml2.design.ui.wizards.newmodel.Messages;

/**
 * Implement the perform finish method when the example import wizard OK button is pressed.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlExampleWizard extends ExampleInstallerWizard {

	@Override
	/**
	 * Import selected example and switch to modeling perspective.
	 * {@inheritDoc}
	 */
	public boolean performFinish() {

		super.performFinish();

		IRunnableWithProgress op = new WorkspaceModifyOperation(null) {
			@Override
			protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {

				// Switch to the modeling perspective
				try {
					PlatformUI.getWorkbench().showPerspective(
							"org.eclipse.sirius.ui.tools.perspective.modeling",
							PlatformUI.getWorkbench().getActiveWorkbenchWindow());
				} catch (WorkbenchException e) {
					ErrorDialog.openError(getContainer().getShell(), Messages.UmlExampleWizard_UI_Error_Open,
							null, e.getStatus());
				}
			}
		};
		try {
			getContainer().run(false, true, op);
		} catch (InvocationTargetException e) {
			if (e.getTargetException() instanceof CoreException) {
				ErrorDialog.openError(getContainer().getShell(), Messages.UmlExampleWizard_UI_Error_Open,
						null, ((CoreException)e.getTargetException()).getStatus());
			} else {
				UMLDesignerPlugin.log(IStatus.ERROR, Messages.UmlExampleWizard_UI_Error_Open, e);
			}
		} catch (InterruptedException e) {
			return false;
		}

		// Select it in the explorer
		for (ProjectDescriptor projectDescriptor : getProjectDescriptors()) {
			IProject project = projectDescriptor.getProject();
			BasicNewResourceWizard.selectAndReveal(project, PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow());
		}

		return true;
	}
}
