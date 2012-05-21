package org.obeonetwork.dsl.uml2.design.ui.wizards.newmodel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;

import fr.obeo.dsl.common.tools.api.util.Option;
import fr.obeo.dsl.common.tools.api.util.Options;
import fr.obeo.dsl.viewpoint.business.api.componentization.ViewpointRegistry;
import fr.obeo.dsl.viewpoint.business.api.modelingproject.ModelingProject;
import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.description.Viewpoint;
import fr.obeo.dsl.viewpoint.ui.business.api.viewpoint.ViewpointSelectionCallback;
import fr.obeo.dsl.viewpoint.ui.tools.api.project.ModelingProjectManager;

public class UMLProjectWizard extends BasicNewResourceWizard {
	/**
	 * Dot constant.
	 */
	public static final String DOT = ".";

	/**
	 * The UML file extension.
	 */
	public static final String MODEL_FILE_EXTENSION = "uml"; //$NON-NLS-1$

	private UmlModelWizardInitModelPage modelPage;

	private WizardNewProjectCreationPage newProjectPage;

	@Override
	public void addPages() {
		super.addPages();

		newProjectPage = new WizardNewProjectCreationPage("Project"); //$NON-NLS-1$
		newProjectPage.setInitialProjectName("");
		newProjectPage.setTitle("Create a UML Modeling project");
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
			IProject project = ModelingProjectManager.INSTANCE.createNewModelingProject(
					newProjectPage.getProjectName(), newProjectPage.getLocationPath(), true);
			Option<IFile> optionalNewfile = createEcoreResource(project);
			if (optionalNewfile.some() && optionalNewfile.get().exists()) {
				selectAndReveal(optionalNewfile.get());
			} else {
				selectAndReveal(project);
			}
			return true;

		} catch (CoreException e) {
			UMLDesignerPlugin.log(IStatus.ERROR, Messages.UmlModelWizard_UI_Error_CreatingUmlModel, e);
		}
		return false;

	}

	private Option<IFile> createEcoreResource(IProject project) {
		final Session session;
		Option<ModelingProject> modelingProject = ModelingProject.asModelingProject(project);
		if (modelingProject.some()) {
			session = modelingProject.get().getSession();
		} else {
			session = null;
		}

		if (session == null) {
			return Options.newNone();
		}

		/*
		 * Create a resource for this file. Don't specify acontent type, as it could be Ecore or EMOF.Create
		 * in a other resourceset and let the workspace monitor for modeling project add it as semantic
		 * resource.
		 */
		final ResourceSet rs = new ResourceSetImpl();
		String platformPath = '/' + project.getName() + '/' + modelPage.getInitialObjectName().toLowerCase()
				+ DOT + MODEL_FILE_EXTENSION;
		final URI semanticModelURI = URI.createPlatformResourceURI(platformPath, true);
		final Resource resource = rs.createResource(semanticModelURI);

		/* Add the initial model object to the contents. */
		final EObject rootObject = createInitialModel();

		if (rootObject != null) {
			resource.getContents().add(rootObject);
		}

		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {

			@Override
			protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException,
					InterruptedException {
				try {
					resource.save(new HashMap<Object, Object>());
				} catch (IOException e) {
					/* do nothing it should always work */
				}
			}
		};
		try {
			new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(true,
					false, operation);
		} catch (InvocationTargetException e) {
			/* do nothing it should always work */
		} catch (InterruptedException e) {
			/* do nothing it should always work */
		}

		session.getTransactionalEditingDomain().getCommandStack()
				.execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
					@Override
					protected void doExecute() {
						ViewpointSelectionCallback callback = new ViewpointSelectionCallback();

						for (Viewpoint vp : ViewpointRegistry.getInstance().getViewpoints()) {
							if ("UML Structural Modeling".equals(vp.getName())) {
								callback.selectViewpoint(vp, session);
							} else if ("UML Behavioral Modeling".equals(vp.getName())) {
								callback.selectViewpoint(vp, session);
							}

						}
					}
				});
		return Options.newSome(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformPath)));
	}

	/**
	 * Creates the semantic root element from the given operation arguments.
	 * 
	 * @return the semantic root {@link EObject}
	 */
	private EObject createInitialModel() {
		EClassifier found = UMLPackage.eINSTANCE.getEClassifier(modelPage.getInitialObjectName());
		if (found instanceof EClass) {
			return UMLFactory.eINSTANCE.create((EClass)found);
		} else {
			return UMLFactory.eINSTANCE.createModel();
		}
	}

}
