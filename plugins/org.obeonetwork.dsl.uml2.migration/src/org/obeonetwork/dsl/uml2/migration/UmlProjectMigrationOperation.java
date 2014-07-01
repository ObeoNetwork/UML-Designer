/*******************************************************************************
 * Copyright (c) 2009, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.migration;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.obeonetwork.dsl.uml2.design.ui.wizards.newmodel.UmlProjectUtils;

public class UmlProjectMigrationOperation extends WorkspaceModifyOperation {
	private IProject project;
	private IWorkbenchPage page;
	private IWorkbenchWindow window;

	public UmlProjectMigrationOperation(IProject project) {
		this.project = project;
		window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		page = window.getActivePage();
	}

	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException,
			InvocationTargetException, InterruptedException {
		try {
			monitor.beginTask("Migrate the project : ", 100);
			// Convert to modeling project
			monitor.subTask("Convert to modeling project...");
			System.out.println("Convert");
			Option<ModelingProject> modelingProject = convertToModelingProject(monitor);
			monitor.worked(25);
			// Close all existing open editors
			System.out.println("Close editors");
			monitor.subTask("Close all opened editors...");
			closeOpenEditors();
			monitor.worked(25);

			if (modelingProject.some()) {
				monitor.subTask("Activate UML viewpoints...");
				Session session = modelingProject.get().getSession();
				// Activate viewpoints
				System.out.println("Enable vp");
				UmlProjectUtils.enableUMLViewpoints(session);
				monitor.worked(25);
			}
			// Save migrated project
			System.out.println("Close");
			monitor.subTask("Close the project...");
			project.close(monitor);
			monitor.subTask("Re-open the project...");
			project.open(monitor);
			monitor.worked(25);
			System.out.println("Re-open the project");
			// close and reopened it
		} finally {
			monitor.done();
		}
	}

	public Option<ModelingProject> convertToModelingProject(
			IProgressMonitor monitor) {
		/* Convert the existing UML project to a modeling project. */
		try {
			ModelingProjectManager.INSTANCE.convertToModelingProject(project,
					monitor);
			Option<ModelingProject> optionalModelingProject = ModelingProject
					.asModelingProject(project);
			if (optionalModelingProject.some()) {
				return optionalModelingProject;
			}
		} catch (CoreException e) {
			Activator.getDefault().logError(e);
		}
		return Options.newNone();
	}

	public void closeOpenEditors() {
		page.closeAllEditors(false);
	}
}
