package org.obeonetwork.dsl.uml2.design.ui.wizards.newmodel;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.PlatformUI;
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

		// Do not call super as we don't want to use the super perform method to create the project, in our
		// case the project was created using the modeling project api, we need to extends the
		// BasicNewProjectResourceWizard to implement the perspective switch easily.
		InitUmlModel init = new InitUmlModel(project, rootObjectName, newUmlModelFileName);
		try {
			getContainer().run(false, true, init);
		} catch (final InterruptedException e) {
			// Ignore.
			return false;
		} catch (final InvocationTargetException e) {
			UMLDesignerPlugin.log(IStatus.ERROR, Messages.UmlModelWizard_UI_Error_CreatingUmlModel, e);
			return false;
		}

		// Get the newly created UML file
		IResource newUmlModelFile = project.findMember(newUmlModelFileName);

		// Select it in the explorer
		selectAndReveal(newUmlModelFile, PlatformUI.getWorkbench().getActiveWorkbenchWindow());

		// Switch to the modeling perspective
		updatePerspective();
		return true;
	}
}
