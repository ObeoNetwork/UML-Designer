/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/

package org.obeonetwork.dsl.uml2.design.api.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.obeonetwork.dsl.uml2.design.internal.wizards.newmodel.UmlModelWizardInitModelPage;
import org.obeonetwork.dsl.uml2.design.internal.wizards.newproject.UMLProjectWizard;

/**
 * Set of utilities to manage UML projects.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public abstract class AbstractUmlProjectWizard extends UMLProjectWizard {
	// Uml project wizard can be extended by other designers as SysML designer
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPages() {
		super.addPages();
	}

	/**
	 * Get model page.
	 *
	 * @return Model page
	 */
	public UmlModelWizardInitModelPage getModelPage() {
		return modelPage;
	}

	/**
	 * Get new project page.
	 *
	 * @return Project page
	 */
	public WizardNewProjectCreationPage getNewProjectPage() {
		return newProjectPage;
	}

	/**
	 * Get project.
	 * 
	 * @return Project
	 */
	public IProject getProject() {
		return project;
	}

	@Override
	public boolean performFinish() {
		return super.performFinish();
	}
}
