package org.obeonetwork.dsl.uml2.core.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.obeonetwork.dsl.uml2.core.ProjectNatureUml;
import org.obeonetwork.dsl.uml2.core.UMLDesignerCorePlugin;
import org.obeonetwork.dsl.uml2.core.internal.wizards.Messages;
import org.obeonetwork.dsl.uml2.core.internal.wizards.newproject.InitUmlModel;

/**
 * Implement the perform finish method when the UMl creation wizard OK button is
 * pressed.
 *
 * @author Melanie Bats
 *         <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public abstract class AbstractNewUmlModelWizard extends BasicNewProjectResourceWizard {

	/**
	 * Uml project.
	 */
	public IProject project;

	/**
	 * Name of the model root element.
	 */
	public String rootObjectName;

	/**
	 * Name of the UML file.
	 */
	public String newUmlModelFileName;

	@Override
	/**
	 * Create a default UML model then select it in the explorer and switch to
	 * modeling perspective. The project, the rootObjectName and the
	 * newUmlModelFileName must be initialized before calling the performFinish
	 * method. {@inheritDoc}
	 */
	public boolean performFinish() {
		if (project == null || rootObjectName == null || newUmlModelFileName == null) {
			throw new IllegalArgumentException();
		}

		// Set Uml project nature
		IProjectDescription description;
		try {
			description = project.getDescription();

			final String[] natures = description.getNatureIds();
			final String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = ProjectNatureUml.NATURE_ID;

			// validate the natures
			final IWorkspace workspace = ResourcesPlugin.getWorkspace();
			final IStatus status = workspace.validateNatureSet(newNatures);

			// only apply new nature, if the status is ok
			if (status.getCode() == IStatus.OK) {
				description.setNatureIds(newNatures);
				project.setDescription(description, null);
			}
		} catch (final CoreException e) {
			UMLDesignerCorePlugin.log(IStatus.ERROR, Messages.UmlModelWizard_UI_Error_SettingProjectNature,
					e);
		}

		final IRunnableWithProgress op = new WorkspaceModifyOperation(null) {
			@Override
			protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {
				// Do not call super as we don't want to use the super perform
				// method to create the project,
				// in our case the project was created using the modeling
				// project api, we need to extends the
				// BasicNewProjectResourceWizard to implement the perspective
				// switch easily.
				final InitUmlModel init = new InitUmlModel(project, rootObjectName, newUmlModelFileName);
				try {
					getContainer().run(false, true, init);
				} catch (final InterruptedException e) {
					// Ignore.
				} catch (final InvocationTargetException e) {
					UMLDesignerCorePlugin.log(IStatus.ERROR, Messages.UmlModelWizard_UI_Error_CreatingUmlModel, e);
				}

				// Get the newly created UML file
				final IResource newUmlModelFile = project.findMember(newUmlModelFileName);

				// Switch to the modeling perspective
				updatePerspective();

				// Select it in the explorer
				selectAndReveal(newUmlModelFile, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			}
		};
		try {
			getContainer().run(false, true, op);
		} catch (final InvocationTargetException e) {
			if (e.getTargetException() instanceof CoreException) {
				ErrorDialog.openError(getContainer().getShell(), Messages.UmlModelWizard_UI_Error_CreatingUmlModel, null, ((CoreException) e.getTargetException()).getStatus());
			} else {
				UMLDesignerCorePlugin.log(IStatus.ERROR, Messages.UmlModelWizard_UI_Error_CreatingUmlModel, e);
			}
		} catch (final InterruptedException e) {
			return false;
		}

		return true;
	}
}
