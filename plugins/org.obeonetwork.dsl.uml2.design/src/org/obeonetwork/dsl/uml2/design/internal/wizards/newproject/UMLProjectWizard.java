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
package org.obeonetwork.dsl.uml2.design.internal.wizards.newproject;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.obeonetwork.dsl.uml2.core.api.wizards.UmlProjectUtils;
import org.obeonetwork.dsl.uml2.core.wizard.AbstractNewUmlModelWizard;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;
import org.obeonetwork.dsl.uml2.design.api.utils.UmlViewpoints;
import org.obeonetwork.dsl.uml2.design.internal.wizards.Messages;
import org.obeonetwork.dsl.uml2.design.internal.wizards.newmodel.UmlModelWizardInitModelPage;

/**
 * The wizard to create a new UML designer project.
 *
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo .fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UMLProjectWizard extends AbstractNewUmlModelWizard {
	protected UmlModelWizardInitModelPage modelPage;

	protected WizardNewProjectCreationPage newProjectPage;

	@Override
	public void addPages() {
		// we're not calling the super as we want to control the project
		// creation, we don't want the default
		// page.
		// super.addPages();

		newProjectPage = new WizardNewProjectCreationPage("Project"); //$NON-NLS-1$
		newProjectPage.setInitialProjectName(""); //$NON-NLS-1$
		newProjectPage.setTitle("Create a UML Modeling project"); //$NON-NLS-1$
		newProjectPage.setDescription("Enter a project name"); //$NON-NLS-1$
		addPage(newProjectPage);

		modelPage = new UmlModelWizardInitModelPage(Messages.UmlModelWizard_UI_InitModelPageId);
		modelPage.setTitle(Messages.UmlModelWizard_UI_InitModelPageTitle);
		modelPage.setDescription(Messages.UmlModelWizard_UI_InitModelPageDescription);
		addPage(modelPage);
	}

	@Override
	public boolean performFinish() {
		try {
			project = ModelingProjectManager.INSTANCE.createNewModelingProject(
					newProjectPage.getProjectName(), newProjectPage.getLocationPath(), true,
					new NullProgressMonitor());
			rootObjectName = modelPage.getInitialObjectName();
			newUmlModelFileName = modelPage.getInitialObjectName().toLowerCase() + UmlProjectUtils.DOT
					+ UmlProjectUtils.MODEL_FILE_EXTENSION;

			super.performFinish();

			// Enable UML viewpoints
			final Option<ModelingProject> created = ModelingProject.asModelingProject(project);
			if (created.some()) {
				Display.getDefault().syncExec(new Runnable() {

					public void run() {
						// Create default empty UML model
						org.obeonetwork.dsl.uml2.core.api.wizards.UmlProjectUtils.createSemanticResource(project, rootObjectName, newUmlModelFileName);

						created.get();

						final Session session = created.get().getSession();
						if (session != null) {
							UmlViewpoints.enable(session);
							if (SessionStatus.DIRTY.equals(session.getStatus())) {
								session.save(new NullProgressMonitor());
							}
						}
					}
				});
			}
		} catch (final CoreException e) {
			UMLDesignerPlugin.log(IStatus.ERROR, Messages.UmlModelWizard_UI_Error_CreatingUmlModel, e);
			return false;
		}

		return true;
	}
}
