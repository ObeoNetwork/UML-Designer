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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.obeonetwork.dsl.uml2.design.Activator;
import org.obeonetwork.dsl.uml2.design.SessionCreationOperation;


public class UmlModelWizard extends Wizard implements INewWizard {
	
	public static final String MODEL_FILE_EXTENSION = "uml"; //$NON-NLS-1$
	public static final String SESSION_FILE_EXTENSION = "aird"; //$NON-NLS-1$
	
	private UmlModelWizardNewModelFilePage newModelFilePage;
	private UmlModelWizardInitModelPage initModelPage;
	private UmlModelWizardNewSessionFilePage newSessionFilePage;
	
	/**
	 * Remember the selection during initialization for populating the default container.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IStructuredSelection selection;

	/**
	 * Remember the workbench during initialization.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IWorkbench workbench;
	
	@Override
	public boolean performFinish() {
        final IRunnableWithProgress op = new SessionCreationOperation(getModelFile(), getSessionModelFile(), initModelPage.getInitialObjectName());
        try {
            getContainer().run(false, true, op);
            return true;
        } catch (final InterruptedException e) {
            // Ignore.
        } catch (final InvocationTargetException e) {
        	Activator.log(IStatus.ERROR, Messages.UmlModelWizard_UI_Error_CreatingUmlModel, e);
        }
        return false;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		setWindowTitle(Messages.UmlModelWizard_UI_WizardTitle);
	}
	
	@Override
	public void addPages() {
		newModelFilePage = new UmlModelWizardNewModelFilePage(Messages.UmlModelWizard_UI_NewModelFilePageId, selection);
		newModelFilePage.setTitle(Messages.UmlModelWizard_UI_NewModelFilePageTitle);
		newModelFilePage.setDescription(Messages.UmlModelWizard_UI_NewModelFilePageDescription);
		newModelFilePage.setFileName(Messages.UmlModelWizard_UI_ModelFileDefaultName + "." + MODEL_FILE_EXTENSION);  //$NON-NLS-1$
		addPage(newModelFilePage);
		
		initModelPage = new UmlModelWizardInitModelPage(Messages.UmlModelWizard_UI_InitModelPageId);
		initModelPage.setTitle(Messages.UmlModelWizard_UI_InitModelPageTitle);
		initModelPage.setDescription(Messages.UmlModelWizard_UI_InitModelPageDescription);
		addPage(initModelPage);
		
		newSessionFilePage = new UmlModelWizardNewSessionFilePage(Messages.UmlModelWizard_UI_NewSessionFilePageId, selection);
		newSessionFilePage.setTitle(Messages.UmlModelWizard_UI_NewSessionFilePageTitle);
		newSessionFilePage.setDescription(Messages.UmlModelWizard_UI_NewSessionFilePageDescription);
		newSessionFilePage.setFileName(Messages.UmlModelWizard_UI_SessionFileDefaultName + "." + SESSION_FILE_EXTENSION); //$NON-NLS-1$
		addPage(newSessionFilePage); 
		
		// Try and get the resource selection to determine a current directory for the file dialog.
		if (selection != null && !selection.isEmpty()) {
			// Get the resource...
			//
			Object selectedElement = selection.iterator().next();
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
					newSessionFilePage.setContainerFullPath(selectedResource.getFullPath());

					// Make up a unique new name here.
					String defaultModelBaseFilename = Messages.UmlModelWizard_UI_ModelFileDefaultName; //$NON-NLS-1$
					String modelFilename = defaultModelBaseFilename + "." + MODEL_FILE_EXTENSION; //$NON-NLS-1$
					for (int i = 1; ((IContainer)selectedResource).findMember(modelFilename) != null; ++i) {
						modelFilename = defaultModelBaseFilename + i + "." + MODEL_FILE_EXTENSION; //$NON-NLS-1$
					}
					newModelFilePage.setFileName(modelFilename);
					
					String defaultSessionBaseFilename = Messages.UmlModelWizard_UI_SessionFileDefaultName; //$NON-NLS-1$
					String sessionFilename = defaultSessionBaseFilename + "." + SESSION_FILE_EXTENSION; //$NON-NLS-1$
					for (int i = 1; ((IContainer)selectedResource).findMember(sessionFilename) != null; ++i) {
						sessionFilename = defaultSessionBaseFilename + i + "." + SESSION_FILE_EXTENSION; //$NON-NLS-1$
					}
					newSessionFilePage.setFileName(sessionFilename);
				}
			}
		}
	}
	
	public IFile getModelFile() {
		return newModelFilePage.getModelFile();
	}
	
	private IFile getSessionModelFile() {
		return newSessionFilePage.getModelFile();
	}
}
