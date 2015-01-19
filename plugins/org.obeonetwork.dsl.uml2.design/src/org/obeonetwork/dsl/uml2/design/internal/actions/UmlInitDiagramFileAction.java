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
package org.obeonetwork.dsl.uml2.design.internal.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
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
import org.obeonetwork.dsl.uml2.design.api.utils.UmlViewpoints;

/**
 * Init diagram actions.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
@SuppressWarnings("restriction")
public class UmlInitDiagramFileAction implements IObjectActionDelegate {

	private IWorkbenchPart targetPart;

	private URI domainModelURI;

	private IStructuredSelection selection;

	private Session createSession() {
		final CreateSessionResourceWizard wizard = new CreateSessionResourceWizard(selection);
		wizard.init(PlatformUI.getWorkbench(), selection);
		final WizardDialog dialog = new WizardDialog(getShell(), wizard);
		dialog.create();
		dialog.getShell().setText("Create Representation File"); //$NON-NLS-1$
		if (dialog.open() == Window.OK) {

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

	private Shell getShell() {
		return targetPart.getSite().getShell();
	}

	protected void openCreateRepresentationWizard(final Session existingSession) {

		existingSession.getTransactionalEditingDomain().getCommandStack()
		.execute(new RecordingCommand(existingSession.getTransactionalEditingDomain()) {

			@Override
			protected void doExecute() {
				final ViewpointSelectionCallback selectionCallback = new ViewpointSelectionCallback();
				for (final Viewpoint previouslySelected : existingSession
						.getSelectedViewpoints(false)) {
					selectionCallback.deselectViewpoint(previouslySelected, existingSession,
							new NullProgressMonitor());
				}
				selectionCallback.selectViewpoint(UmlViewpoints.fromViewpointRegistry().capture(),
						existingSession, new NullProgressMonitor());
				selectionCallback.selectViewpoint(UmlViewpoints.fromViewpointRegistry().design(),
						existingSession, new NullProgressMonitor());
				selectionCallback.selectViewpoint(UmlViewpoints.fromViewpointRegistry().review(),
						existingSession, new NullProgressMonitor());
				selectionCallback.selectViewpoint(UmlViewpoints.fromViewpointRegistry().extend(),
						existingSession, new NullProgressMonitor());
			}
		});

		final CreateRepresentationWizard wizard = new CreateRepresentationWizard(existingSession);
		wizard.init();
		final WizardDialog dialog = new WizardDialog(getShell(), wizard);
		dialog.create();
		dialog.getShell().setText("Create Representation Wizard"); //$NON-NLS-1$
		dialog.open();
	}

	public void run(IAction action) {
		Session existingSession = null;
		for (final Session session : SessionManager.INSTANCE.getSessions()) {
			final ResourceSet set = session.getTransactionalEditingDomain().getResourceSet();
			for (final Resource res : set.getResources()) {
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

	public void selectionChanged(IAction action, ISelection selectionChanged) {
		domainModelURI = null;
		action.setEnabled(false);
		if (selectionChanged instanceof IStructuredSelection == false || selectionChanged.isEmpty()) {
			return;
		}
		selection = (IStructuredSelection)selectionChanged;
		final IFile file = (IFile)((IStructuredSelection)selectionChanged).getFirstElement();
		domainModelURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		action.setEnabled(true);
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}
}
