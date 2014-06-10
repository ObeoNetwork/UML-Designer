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
package org.obeonetwork.dsl.sirius.gendoc.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.ext.swt.ImageFileFormat;
import org.eclipse.sirius.ui.tools.api.actions.export.ExportAction;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.swt.widgets.Display;
import org.obeonetwork.dsl.sirius.gendoc.wizard.Gendoc2WizardPage;

/**
 * Sirius services.
 * 
 * @author Axel Richard <a
 *         href="mailto:axel.richard@obeo.fr">axel.richard@obeo.fr</a>
 */
public class SiriusServices {

	/** Image file format use for aird diagram images. */
	private static final ImageFileFormat IMAGE_FILE_FORMAT = ImageFileFormat.PNG;

	/** Image file extension use for aird diagram images. */
	private static final String IMAGE_FILE_EXTENSION = "."
			+ IMAGE_FILE_FORMAT.getName().toLowerCase();

	/**
	 * Get the diagram image path corresponding to the given diagram name for
	 * the given element.
	 * 
	 * @param object
	 *            the given element.
	 * @param diagramName
	 *            the given diagram name.
	 * @return the diagram image path corresponding to the given diagram name
	 *         for the given element.
	 */
	public String getDiagramWithName(EObject object, String diagramName) {

		String diagramPath = null;

		final DAnalysis aird = getAird();

		// Gets the tmp directory path where the image are saved
		final IPath targetPath = new Path(FilesUtils
				.createTempDirAndDeleteOnShutdown().getAbsolutePath());

		final List<DRepresentation> representations = new ArrayList<DRepresentation>();

		for (DRepresentation dRepresentation : getRepresentations(aird, object)) {
			if (diagramName.equals(dRepresentation.getName())) {
				diagramPath = targetPath.toOSString() + File.separator
						+ dRepresentation.getName() + IMAGE_FILE_EXTENSION;
				representations.add(dRepresentation);
				break;
			}
		}
		// Export the diagrams from the aird file.
		exportDiagrams(representations, targetPath);

		return diagramPath;

	}

	/**
	 * Get all diagram images paths corresponding to the given diagram
	 * representation name for the given element.
	 * 
	 * @param object
	 *            the given element.
	 * @param diagramRepresentationName
	 *            the given diagram representation name.
	 * @return all diagram images paths corresponding to the given diagram
	 *         representation name for the given element.
	 */
	public List<String> getDiagramsWithRepresentationName(final EObject object,
			String diagramRepresentationName) {

		final DAnalysis aird = getAird();
		
		// Gets the tmp directory path where the image are saved
		final IPath imagesTargetPath = new Path(FilesUtils
				.createTempDirAndDeleteOnShutdown().getAbsolutePath());

		// Gets the representation from the object and the
		// diagramRepresentationName in the aird resource
		final List<DRepresentation> representations = getRepresentationsWithName(
				aird, object, diagramRepresentationName);

		// Export the diagrams from the aird file.
		exportDiagrams(representations, imagesTargetPath);

		return getDiagramPaths(representations, imagesTargetPath);

	}

	/**
	 * Get all diagram images paths associated to the given element.
	 * 
	 * @param object
	 *            the given element.
	 * @return all diagram images paths associated to the given element.
	 */
	public List<String> getDiagrams(final EObject object) {
		
		final DAnalysis aird = getAird();
		
		// Gets the tmp directory path where the image are saved
		final IPath imagesTargetPath = new Path(FilesUtils
				.createTempDirAndDeleteOnShutdown().getAbsolutePath());

		// Gets the representation from the object in the aird resource
		final List<DRepresentation> representations = getRepresentations(aird,
				object);

		// Export the diagrams from the aird file.
		exportDiagrams(representations, imagesTargetPath);

		return getDiagramPaths(representations, imagesTargetPath);
	}

	/**
	 * Get the aird diagram path filled in the wizard dialog page.
	 * 
	 * @return the aird diagram path filled in the wizard dialog page.
	 */
	private String getAirdDiagramPath() {
		return Gendoc2WizardPage.getAirdDiagramPath();
	}

	/**
	 * Gets the representations that have the given name.
	 * 
	 * @param aird
	 *            the aird file.
	 * @param object
	 *            the object.
	 * @param diagramRepresentationName
	 *            the diagram representation name.
	 * @return the representations.
	 */
	private List<DRepresentation> getRepresentationsWithName(DAnalysis aird,
			EObject object, String diagramRepresentationName) {

		final List<DRepresentation> representations = new ArrayList<DRepresentation>();

		for (DRepresentation dRepresentation : getRepresentations(aird, object)) {
			if (dRepresentation instanceof DSemanticDiagram) {
				final DSemanticDiagram semanticDiagram = (DSemanticDiagram) dRepresentation;
				if (diagramRepresentationName.equals(semanticDiagram
						.getDescription().getName())) {
					representations.add(dRepresentation);
				}
			}
		}
		return representations;
	}

	/**
	 * Gets the representations associated with the given object.
	 * 
	 * @param aird
	 *            the aird file.
	 * @param object
	 *            the object.
	 * @return the representations.
	 */
	private List<DRepresentation> getRepresentations(DAnalysis aird,
			EObject object) {

		final List<DRepresentation> representations = new ArrayList<DRepresentation>();

		final EList<DView> ownedViews = aird.getOwnedViews();
		for (DView dView : ownedViews) {
			final EList<DRepresentation> allRepresentations = dView
					.getAllRepresentations();
			for (DRepresentation dRepresentation : allRepresentations) {
				if (dRepresentation instanceof DSemanticDiagram) {
					final DSemanticDiagram semanticDiagram = (DSemanticDiagram) dRepresentation;
					if (EcoreUtil.equals(semanticDiagram.getTarget(), object)) {
						representations.add(dRepresentation);
					}
				}
			}
		}
		return representations;
	}

	/**
	 * Gets the diagram paths.
	 * 
	 * @param representations
	 *            the representation
	 * @param imagesTargetPath
	 *            the path
	 * @return the corresponding path.
	 */
	private List<String> getDiagramPaths(List<DRepresentation> representations,
			IPath imagesTargetPath) {
		final List<String> diagramPaths = new ArrayList<String>();

		for (DRepresentation dRepresentation : representations) {
			diagramPaths.add(imagesTargetPath.toOSString() + File.separator
					+ dRepresentation.getName() + IMAGE_FILE_EXTENSION);
		}

		return diagramPaths;
	}

	/**
	 * Gets the session {@link Session} from the given resource.
	 * 
	 * @param airdWorkspaceResource
	 *            the aird workspace resource
	 * @return the existing {@link Session} if it already exists, or a new one
	 *         instead.
	 */
	private Session getSession(IResource airdWorkspaceResource) {
		URI uri = URI.createPlatformResourceURI(airdWorkspaceResource
				.getFullPath().toOSString(), false);
		Session session = SessionManager.INSTANCE.getExistingSession(uri);
		if (session == null) {
			session = SessionManager.INSTANCE.getSession(uri,
					new NullProgressMonitor());
		}
		return session;
	}

	/**
	 * Gets the aird {@link DAnalysis}.
	 * 
	 * @return the {@link DAnalysis}
	 */
	private DAnalysis getAird() {
		final IResource airdWorkspaceResource = getAirdWorkspaceResource();
		Session session = getSession(airdWorkspaceResource);
		final Resource airdResource = session.getSessionResource();
		return (DAnalysis) airdResource.getContents().get(0);
	}

	/**
	 * Gets the {@link IResource}.
	 * 
	 * @return the {@link IResource}.
	 */
	private IResource getAirdWorkspaceResource() {
		// Gets the aird from the wizard
		final IPath airdModelPath = new Path(getAirdDiagramPath());
		final IResource airdWorkspaceResource = ResourcesPlugin.getWorkspace()
				.getRoot().getFileForLocation(airdModelPath);
		return airdWorkspaceResource;
	}

	/**
	 * Export the given representations as images in the given target path.
	 * 
	 * @param representations
	 *            the given representations.
	 * @param targetPath
	 *            the given target path.
	 */
	private void exportDiagrams(final List<DRepresentation> representations,
			final IPath targetPath) {
		final ExportAction exportAction = new ExportAction(
				getSession(getAirdWorkspaceResource()), representations,
				targetPath, IMAGE_FILE_FORMAT, false);

		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				try {
					exportAction.run(new NullProgressMonitor());
				} catch (Exception e) {
					final IStatus message = new Status(Status.ERROR,
							Activator.PLUGIN_ID, e.getMessage());
					Activator.getDefault().getLog().log(message);
				}
			}
		});
	}
}
