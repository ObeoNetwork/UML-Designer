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
package org.obeonetwork.dsl.uml2.design.ui.wizards.newmodel;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public abstract class AbstractUmlModelWizardNewFileCreationPage extends WizardNewFileCreationPage {

	public AbstractUmlModelWizardNewFileCreationPage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
	}

	public IFile getModelFile() {
		return ResourcesPlugin.getWorkspace().getRoot().getFile(getContainerFullPath().append(getFileName()));
	}
	
	@Override
	protected boolean validatePage() {
		if (super.validatePage()) {
			String requiredExt = getRequiredExtension();
			String enteredExt = new Path(getFileName()).getFileExtension();
			if (enteredExt == null || !enteredExt.equals(requiredExt)) {
				setErrorMessage(Messages.bind(Messages.UmlModelWizard_UI_ErrorMsg_BadFileExtension, requiredExt));
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}
	
	abstract protected String getRequiredExtension();
}
