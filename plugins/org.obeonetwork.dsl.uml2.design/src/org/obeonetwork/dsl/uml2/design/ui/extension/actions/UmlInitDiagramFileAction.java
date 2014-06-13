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
package org.obeonetwork.dsl.uml2.design.ui.extension.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.tools.internal.wizards.CreateRepresentationWizard;
import org.eclipse.sirius.ui.tools.internal.wizards.CreateSessionResourceWizard;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.obeonetwork.dsl.uml2.design.ui.extension.editor.UmlViewpoints;

public class UmlInitDiagramFileAction implements IObjectActionDelegate {

	private IWorkbenchPart targetPart;

	private URI domainModelURI;

	private IStructuredSelection selection;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	public void selectionChanged(IAction action, ISelection selection) {
		domainModelURI = null;
		action.setEnabled(false);
		if (selection instanceof IStructuredSelection == false || selection.isEmpty()) {
			return;
		}
		this.selection = (IStructuredSelection)selection;
		IFile file = (IFile)((IStructuredSelection)selection).getFirstElement();
		domainModelURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		action.setEnabled(true);
	}

	private Shell getShell() {
		return targetPart.getSite().getShell();
	}

	public void run(IAction action) {
		Session existingSession = null;
		for (Session session : SessionManager.INSTANCE.getSessions()) {
			ResourceSet set = session.getTransactionalEditingDomain().getResourceSet();
			for (Resource res : set.getResources()) {
				if (res.getURI() != null) {
					if (set.getURIConverter().normalize(res.getURI())
							.equals(set.getURIConverter().normalize(domainModelURI))) {
						existingSession = session;
					}
				}
			}
		}
		if (existingSession == null) {
			existingSession = createSession();
		}

		if (existingSession != null) {
			openCreateRepresentationWizard(existingSession);
		}

	}

	private Session createSession() {
		final CreateSessionResourceWizard wizard = new CreateSessionResourceWizard(selection);
		wizard.init(PlatformUI.getWorkbench(), selection);
		final WizardDialog dialog = new WizardDialog(getShell(), wizard);
		dialog.create();
		dialog.getShell().setText("Create Representation File");
		if (dialog.open() == Dialog.OK) {

			wizard.getCreatedSession()
					.getTransactionalEditingDomain()
					.getCommandStack()
					.execute(
							new RecordingCommand(wizard.getCreatedSession().getTransactionalEditingDomain()) {

								@Override
								protected void doExecute() {
									wizard.getCreatedSession().addSemanticResource(domainModelURI,
											new NullProgressMonitor());

								}
							});
			return wizard.getCreatedSession();
		}
		return null;

	}

	protected void openCreateRepresentationWizard(final Session existingSession) {

		existingSession.getTransactionalEditingDomain().getCommandStack()
				.execute(new RecordingCommand(existingSession.getTransactionalEditingDomain()) {

					@Override
					protected void doExecute() {
						ViewpointSelectionCallback selection = new ViewpointSelectionCallback();
						for (Viewpoint previouslySelected : existingSession.getSelectedViewpoints(false)) {
							selection.deselectViewpoint(previouslySelected, existingSession,
									new NullProgressMonitor());
						}
						selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().structural(),
								existingSession, new NullProgressMonitor());
						selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().behavioral(),
								existingSession, new NullProgressMonitor());
						selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().extensions(),
								existingSession, new NullProgressMonitor());
						selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().profile(),
								existingSession, new NullProgressMonitor());
					}
				});

		CreateRepresentationWizard wizard = new CreateRepresentationWizard(existingSession);
		wizard.init();
		final WizardDialog dialog = new WizardDialog(getShell(), wizard);
		dialog.create();
		dialog.getShell().setText("Create Representation Wizard");
		dialog.open();
	}
}
