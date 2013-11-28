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
package org.obeonetwork.dsl.uml2.design;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.obeonetwork.dsl.uml2.design.ui.wizards.newmodel.UmlProjectUtils;

/**
 * An operation to create and initialize a new session with empty semantic UML model.
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class InitUmlModel extends WorkspaceModifyOperation {
	/**
	 * The UML project.
	 */
	private IProject project;

	/**
	 * The name of the semantic root element.
	 */
	private String rootObjectName;

	/**
	 * The name of the UML file.
	 */
	private String umlFileName;

	/**
	 * Constructor.
	 * 
	 * @param modelFile
	 *            An {@link IFile} handle representing the semantic model to create.
	 * @param airdFile
	 *            An {@link IFile} handle representing the session file to create.
	 * @param rootObjectName
	 *            The name of the semantic root element.
	 * @param fileName
	 */
	public InitUmlModel(IProject project, String rootObjectName, String fileName) {
		super(null);
		this.project = project;
		this.rootObjectName = rootObjectName;
		this.umlFileName = fileName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void execute(final IProgressMonitor monitor) throws CoreException, InterruptedException {
		final Option<ModelingProject> created = ModelingProject.asModelingProject(project);
		if (created.some()) {
			Display.getDefault().syncExec(new Runnable() {

				public void run() {
					// Create default empty UML model
					UmlProjectUtils.createSemanticResource(project, rootObjectName, umlFileName);

					// Enable UML viewpoints
					ModelingProject modelingProject = created.get();
					UmlProjectUtils.enableUMLViewpoints(modelingProject.getSession());
				}
			});
		}
	}

}
