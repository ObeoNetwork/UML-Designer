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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.design.ui.wizards.newmodel.Messages;

import fr.obeo.dsl.common.ui.tools.api.editing.EditingDomainService;
import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.ui.business.api.session.SessionHelper;

/**
 * An operation to create and initialize a new session with empty semantic UML model.
 *
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class SessionCreationOperation extends WorkspaceModifyOperation {

	/**
	 * The type name of an uml.Model element.
	 */
	public static final String MODEL_OBJECT = "Model"; //$NON-NLS-1$

	/**
	 * The type name of an uml.Package element.
	 */
	public static final String PACKAGE_OBJECT = "Package"; //$NON-NLS-1$

	/**
	 * An {@link IFile} handle representing the semantic model to create.
	 */
	private IFile modelFile;

	/**
	 * An {@link IFile} handle representing the session file to create.
	 */
	private IFile airdFile;

	/**
	 * The name of the semantic root element.
	 */
	private String rootObjectName;

	/**
	 * The session created after the execution of the operation.
	 */
	private Session createdSession;

	/**
	 * Constructor.
	 *
	 * @param modelFile An {@link IFile} handle representing the semantic model to create.
	 * @param airdFile An {@link IFile} handle representing the session file to create.
	 * @param rootObjectName The name of the semantic root element.
	 */
	public SessionCreationOperation(IFile modelFile, IFile airdFile, String rootObjectName) {
		super(null);
		this.modelFile = modelFile;
		this.airdFile = airdFile;
		this.rootObjectName = rootObjectName;
	}

	public Session getCreatedSession() {
		return createdSession;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void execute(final IProgressMonitor monitor) throws CoreException, InterruptedException {
		// Create a resource set
		final TransactionalEditingDomain domain = EditingDomainService.getInstance()
				.getEditingDomainProvider().getEditingDomain();
		final ResourceSet resourceSet = domain.getResourceSet();
		resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());

		// Get the URI of the model file.
		final URI fileURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);

		// Create a resource for this file. Don't specify a
		// content type, as it could be Ecore or EMOF.
		final Resource resource = resourceSet.createResource(fileURI);

		// Add the initial model object to the contents.
		final EObject rootObject = createInitialModel();
		if (rootObject != null) {
			domain.getCommandStack().execute(new AddEObjectAsRootCommand(domain, resource, rootObject));
		}

		final URI airdURI = URI.createPlatformResourceURI(airdFile.getFullPath().toString(), true);
		final Resource airdResource = resourceSet.createResource(airdURI);
		final Collection<Resource> semantics = new ArrayList<Resource>();
		semantics.add(resource);

		try {
			createdSession = SessionHelper.createLocalSessionFromModels(semantics, airdResource);
			createdSession.save();
		} catch (final IOException e) {
			UMLDesignerPlugin.log(IStatus.ERROR, Messages.UmlModelWizard_UI_Error_CreatingUmlModelSession, e);
		} catch (final InvocationTargetException e) {
			UMLDesignerPlugin.log(IStatus.ERROR, "Error while creating the UML model session", e); //$NON-NLS-1$
		}
	}

	/**
	 * Command to add the root element to the semantic model within the {@link TransactionalEditingDomain}.
	 *
	 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
	 */
	private class AddEObjectAsRootCommand extends RecordingCommand {

		/**
		 * The semantic model root {@link EObject}.
		 */
		private EObject root;

		/**
		 * The semantic {@link Resource}.
		 */
		private Resource resource;

		/**
		 * Constructor.
		 *
		 * @param domain the {@link TransactionalEditingDomain} on which to execute this command.
		 * @param resource the semantic resource to update.
		 * @param root the semantic root {@link EObject}.
		 */
		public AddEObjectAsRootCommand(TransactionalEditingDomain domain, Resource resource, EObject root) {
			super(domain, "Add the given EObject as root of the given Resource"); //$NON-NLS-1$
			this.resource = resource;
			this.root = root;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void doExecute() {
			if (resource != null && root != null) {
				resource.getContents().add(root);
			}
		}
	}

	/**
	 * Creates the semantic root element from the given operation arguments.
	 * 
	 * @return the semantic root {@link EObject}
	 */
	private EObject createInitialModel() {
		Package root = null;
		if (MODEL_OBJECT.equals(rootObjectName)) {
			root = UMLFactory.eINSTANCE.createModel();
			root.setName(Messages.UmlModelWizard_DefaultModelName);
		} else if (PACKAGE_OBJECT.equals(rootObjectName)) {
			root = UMLFactory.eINSTANCE.createPackage();
			root.setName(Messages.UmlModelWizard_DefaultPackageName);
		}
		return root;
	}
}
