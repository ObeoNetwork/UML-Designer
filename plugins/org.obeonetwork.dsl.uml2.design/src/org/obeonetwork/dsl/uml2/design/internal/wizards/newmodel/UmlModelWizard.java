/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.internal.wizards.newmodel;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.obeonetwork.dsl.uml2.core.api.wizards.UmlProjectUtils;
import org.obeonetwork.dsl.uml2.core.wizard.AbstractNewUmlModelWizard;
import org.obeonetwork.dsl.uml2.dashboard.services.DashboardServices;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;
import org.obeonetwork.dsl.uml2.design.api.utils.UmlViewpoints;
import org.obeonetwork.dsl.uml2.design.internal.wizards.Messages;

/**
 * The wizard to create a new UML designer model.
 *
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo .fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlModelWizard extends AbstractNewUmlModelWizard {
	/**
	 * The semantic model creation file page.
	 */
	private UmlModelWizardNewModelFilePage newModelFilePage;

	/**
	 * The initialization model page.
	 */
	private UmlModelWizardInitModelPage initModelPage;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPages() {
		newModelFilePage = new UmlModelWizardNewModelFilePage(Messages.UmlModelWizard_UI_NewModelFilePageId,
				selection);
		newModelFilePage.setTitle(Messages.UmlModelWizard_UI_NewModelFilePageTitle);
		newModelFilePage.setDescription(Messages.UmlModelWizard_UI_NewModelFilePageDescription);
		newModelFilePage.setFileName(Messages.UmlModelWizard_UI_ModelFileDefaultName + UmlProjectUtils.DOT
				+ UmlProjectUtils.MODEL_FILE_EXTENSION);
		addPage(newModelFilePage);

		initModelPage = new UmlModelWizardInitModelPage(Messages.UmlModelWizard_UI_InitModelPageId);
		initModelPage.setTitle(Messages.UmlModelWizard_UI_InitModelPageTitle);
		initModelPage.setDescription(Messages.UmlModelWizard_UI_InitModelPageDescription);
		addPage(initModelPage);

		// Try and get the resource selection to determine a current directory
		// for the file dialog.
		if (selection != null && !selection.isEmpty()) {
			// Get the resource...
			//
			final Object selectedElement = selection.iterator().next();
			if (selectedElement instanceof IResource) {
				// Get the resource parent, if its a file.
				IResource selectedResource = (IResource)selectedElement;
				if (selectedResource.getType() == IResource.FILE) {
					selectedResource = selectedResource.getParent();
				}

				// This gives us a directory...
				if (selectedResource instanceof IFolder || selectedResource instanceof IProject) {
					// Set this for the container.
					//
					newModelFilePage.setContainerFullPath(selectedResource.getFullPath());

					// Make up a unique new name here.
					final String modelFilename = getNewModelName(selectedResource);
					newModelFilePage.setFileName(modelFilename);
				}
			}
		}
	}

	/**
	 * Compute the name of the new UML model.
	 *
	 * @param selectedResource
	 *            Selected resource
	 * @return Name of the new UML model
	 */
	private String getNewModelName(IResource selectedResource) {
		final String defaultModelBaseFilename = Messages.UmlModelWizard_UI_ModelFileDefaultName;
		String modelFilename = defaultModelBaseFilename + UmlProjectUtils.DOT
				+ UmlProjectUtils.MODEL_FILE_EXTENSION;
		for (int i = 1; ((IContainer)selectedResource).findMember(modelFilename) != null; ++i) {
			modelFilename = defaultModelBaseFilename + i + UmlProjectUtils.DOT
					+ UmlProjectUtils.MODEL_FILE_EXTENSION;
		}
		return modelFilename;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection structuredSelection) {
		selection = structuredSelection;
		setWindowTitle(Messages.UmlModelWizard_UI_WizardTitle);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean performFinish() {
		final Option<IFile> option = newModelFilePage.getModelFile();

		if (option.some()) {
			final IFile modelFile = option.get();
			project = modelFile.getProject();

			// Convert project to modeling project
			try {
				ModelingProjectManager.INSTANCE.convertToModelingProject(project, new NullProgressMonitor());
			} catch (final CoreException e) {
				UMLDesignerPlugin.log(IStatus.ERROR, Messages.UmlModelWizard_UI_Error_CreatingUmlModel, e);
				return false;
			}

			rootObjectName = initModelPage.getInitialObjectName();
			newUmlModelFileName = newModelFilePage.getFileName();

			super.performFinish();

			final Option<ModelingProject> created = ModelingProject.asModelingProject(project);
			if (created.some()) {
				final Session session = created.get().getSession();
				if (session != null) {
					session.getTransactionalEditingDomain().getCommandStack()
					.execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
						@Override
						protected void doExecute() {
							UmlViewpoints.enable(session);
						}
					});
				}
			}

			// Open the dashboard
			DashboardServices.INSTANCE.openDashboard(project);
			// Open the contextual help
			// Context ids are defined in the html/contexts.xml file in
			// org.obeonetwork.dsl.uml2.design.doc project.
			final String contextId = "org.obeonetwork.dsl.uml2.design.doc.Dashboard"; //$NON-NLS-1$
			PlatformUI.getWorkbench().getHelpSystem()
			.setHelp(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), contextId);
			PlatformUI.getWorkbench().getHelpSystem().displayDynamicHelp();
			return true;
		}
		return false;
	}
}
