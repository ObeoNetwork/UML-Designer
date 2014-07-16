package org.obeonetwork.dsl.uml2.design.ui.wizards.newmodel;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.obeonetwork.dsl.uml2.design.InitUmlModel;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;

/**
 * Implement the perform finish method when the UMl creation wizard OK button is pressed.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public abstract class AbstractNewUmlModelWizard extends BasicNewProjectResourceWizard {

	/**
	 * Uml project.
	 */
	protected IProject project;

	/**
	 * Name of the model root element.
	 */
	protected String rootObjectName;

	/**
	 * Name of the UML file.
	 */
	protected String newUmlModelFileName;

	@Override
	/**
	 * Create a default UML model then select it in the explorer and switch to modeling perspective.
	 * The project, the rootObjectName and the newUmlModelFileName must be initialized before calling the performFinish method.
	 * {@inheritDoc}
	 */
	public boolean performFinish() {
		if (project == null || rootObjectName == null || newUmlModelFileName == null) {
			throw new IllegalArgumentException();
		}

		IRunnableWithProgress op = new WorkspaceModifyOperation(null) {
			@Override
			protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {
				// Do not call super as we don't want to use the super perform method to create the project,
				// in our case the project was created using the modeling project api, we need to extends the
				// BasicNewProjectResourceWizard to implement the perspective switch easily.
				InitUmlModel init = new InitUmlModel(project, rootObjectName, newUmlModelFileName);
				try {
					getContainer().run(false, true, init);
				} catch (final InterruptedException e) {
					// Ignore.
				} catch (final InvocationTargetException e) {
					UMLDesignerPlugin
							.log(IStatus.ERROR, Messages.UmlModelWizard_UI_Error_CreatingUmlModel, e);
				}

				// Get the newly created UML file
				IResource newUmlModelFile = project.findMember(newUmlModelFileName);

				// Switch to the modeling perspective
				updatePerspective();

				// Select it in the explorer
				selectAndReveal(newUmlModelFile, PlatformUI.getWorkbench().getActiveWorkbenchWindow());

				// Open the dashboard
				openDashboard(project, monitor);

				// Open the contextual help
				// Context ids are defined in the html/contexts.xml file in
				// org.obeonetwork.dsl.uml2.design.doc project.
				String contextId = "org.obeonetwork.dsl.uml2.design.doc.Dashboard";
				PlatformUI.getWorkbench().getHelpSystem()
						.setHelp(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), contextId);
				PlatformUI.getWorkbench().getHelpSystem().displayDynamicHelp();
			}
		};
		try {
			getContainer().run(false, true, op);
		} catch (InvocationTargetException e) {
			if (e.getTargetException() instanceof CoreException) {
				ErrorDialog.openError(getContainer().getShell(),
						Messages.UmlModelWizard_UI_Error_CreatingUmlModel, null,
						((CoreException)e.getTargetException()).getStatus());
			} else {
				UMLDesignerPlugin.log(IStatus.ERROR, Messages.UmlModelWizard_UI_Error_CreatingUmlModel, e);
			}
		} catch (InterruptedException e) {
			return false;
		}

		return true;
	}

	/**
	 * Open the dashboard representation containing in the representation file of this Modeling project.
	 * 
	 * @param project
	 *            The modeling project containing the representations file.
	 * @param monitor
	 *            a {@link IProgressMonitor} to show progression of first {@link DRepresentation} opening
	 */
	private void openDashboard(IProject project, IProgressMonitor monitor) {
		Option<ModelingProject> opionalModelingProject = ModelingProject.asModelingProject(project);
		if (opionalModelingProject.some()) {
			Session session = opionalModelingProject.get().getSession();
			if (session != null) {
				if (!session.getSelectedViews().isEmpty()) {
					for (DView view : session.getSelectedViews()) {
						if (!view.getOwnedRepresentations().isEmpty()) {
							for (DRepresentation representation : view.getOwnedRepresentations()) {
								RepresentationDescription description = DialectManager.INSTANCE
										.getDescription(representation);
								if ("Dashboard".equals(description.getName())) {
									DialectUIManager.INSTANCE.openEditor(session, representation, monitor);
									return;
								}
							}
						}
					}
				}
			}
		}
	}
}
