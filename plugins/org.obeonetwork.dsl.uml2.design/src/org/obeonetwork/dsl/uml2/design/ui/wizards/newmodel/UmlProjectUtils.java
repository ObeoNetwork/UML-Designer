package org.obeonetwork.dsl.uml2.design.ui.wizards.newmodel;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;
import org.obeonetwork.dsl.uml2.design.ui.extension.editor.UmlViewpoints;

import com.google.common.collect.Maps;

public class UmlProjectUtils {
	/**
	 * Dot constant.
	 */
	public static final String DOT = ".";

	/**
	 * The UML file extension.
	 */
	public static final String MODEL_FILE_EXTENSION = "uml"; //$NON-NLS-1$

	/**
	 * UML structural viewpoint name defined in odesign.
	 */
	public static final String UML_STRUCTURAL_VP = "UML Structural Modeling";

	/**
	 * UML behavioral viewpoint name defined in odesign.
	 */
	public static final String UML_BEHAVIORAL_VP = "UML Behavioral Modeling";

	/**
	 * UML extensions viewpoint name defined in odesign.
	 */
	public static final String UML_EXTENSIONS_VP = "UML Extensions";

	/**
	 * The type name of an uml.Model element.
	 */
	public static final String MODEL_OBJECT = "Model"; //$NON-NLS-1$

	/**
	 * The type name of an uml.Package element.
	 */
	public static final String PACKAGE_OBJECT = "Package"; //$NON-NLS-1$

	public static final String PROFILE_OBJECT = "Profile";

	/**
	 * Creates the semantic root element from the given operation arguments.
	 * 
	 * @return the semantic root {@link EObject}
	 */
	private static EObject createInitialModel(String rootObjectName) {
		Package root = null;
		if (MODEL_OBJECT.equals(rootObjectName)) {
			root = UMLFactory.eINSTANCE.createModel();
			root.setName(Messages.UmlModelWizard_DefaultModelName);
		} else if (PACKAGE_OBJECT.equals(rootObjectName)) {
			root = UMLFactory.eINSTANCE.createPackage();
			root.setName(Messages.UmlModelWizard_DefaultPackageName);
		} else if (PROFILE_OBJECT.equals(rootObjectName)) {
			root = UMLFactory.eINSTANCE.createProfile();
			root.setName(Messages.UmlModelWizard_DefaultPackageName);
		}
		return root;
	}

	public static void enableUMLViewpoints(final Session session) {
		if (session != null) {
			session.getTransactionalEditingDomain().getCommandStack()
					.execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
						@Override
						protected void doExecute() {
							ViewpointSelectionCallback selection = new ViewpointSelectionCallback();
							for (Viewpoint previouslySelected : session.getSelectedViewpoints(false)) {
								selection.deselectViewpoint(previouslySelected, session,
										new NullProgressMonitor());
							}
							selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().structural(),
									session, new NullProgressMonitor());
							selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().behavioral(),
									session, new NullProgressMonitor());
							selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().extensions(),
									session, new NullProgressMonitor());
							selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().dashboard(),
									session, new NullProgressMonitor());
						}
					});
		}
	}

	/**
	 * Create a new UML model in a project.
	 * 
	 * @param project
	 *            Modeling project
	 * @param rootObjectName
	 *            Name of the root object
	 * @param newUmlFileName
	 *            Name of the UML file
	 * @return Newly created UML file
	 */
	public static Option<IFile> createSemanticResource(final IProject project, final String rootObjectName,
			String newUmlFileName) {
		Option<ModelingProject> modelingProject = ModelingProject.asModelingProject(project);
		final Session session = modelingProject.get().getSession();
		final String platformPath = getNewUmlModelFilePath(project, newUmlFileName);
		session.getTransactionalEditingDomain().getCommandStack()
				.execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
					@Override
					protected void doExecute() {

						final URI semanticModelURI = URI.createPlatformResourceURI(platformPath, true);
						Resource res = new ResourceSetImpl().createResource(semanticModelURI);
						/* Add the initial model object to the contents. */
						final EObject rootObject = UmlProjectUtils.createInitialModel(rootObjectName);

						if (rootObject != null) {
							res.getContents().add(rootObject);
						}
						try {
							res.save(Maps.newHashMap());
						} catch (IOException e) {
							UMLDesignerPlugin.log(IStatus.ERROR,
									Messages.UmlModelWizard_UI_Error_CreatingUmlModel, e);
						}

						session.addSemanticResource(semanticModelURI, new NullProgressMonitor());

						session.save(new NullProgressMonitor());
					}
				});
		return Options.newSome(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformPath)));
	}

	/**
	 * Get the new UML model file path.
	 * 
	 * @param project
	 *            Project
	 * @param umlFileName
	 *            File name
	 * @return UML model file path
	 */
	private static String getNewUmlModelFilePath(IProject project, String umlFileName) {
		return '/' + project.getName() + '/' + umlFileName.toLowerCase();
	}
}
