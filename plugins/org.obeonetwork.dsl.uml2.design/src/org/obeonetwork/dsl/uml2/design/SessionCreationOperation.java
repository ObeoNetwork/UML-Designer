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

public class SessionCreationOperation extends WorkspaceModifyOperation {
	
	public static final String MODEL_OBJECT = "Model"; //$NON-NLS-1$
	public static final String PACKAGE_OBJECT = "Package"; //$NON-NLS-1$

    private IFile modelFile;

    private IFile airdFile;
    
    private String rootObjectName;
    
    private Session createdSession;

    public SessionCreationOperation(IFile modelFile, IFile airdFile, String rootObjectName) {
        super(null);
        this.modelFile = modelFile;
        this.airdFile = airdFile;
        this.rootObjectName = rootObjectName;
    }
    
    public Session getCreatedSession() {
    	return createdSession;
    }

    @Override
    protected void execute(final IProgressMonitor monitor) throws CoreException, InterruptedException {
        // Create a resource set
        final TransactionalEditingDomain domain = EditingDomainService.getInstance().getEditingDomainProvider().getEditingDomain();
        final ResourceSet resourceSet = domain.getResourceSet();
        resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());

        // Get the URI of the model file.
        URI fileURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);

        // Create a resource for this file. Don't specify a
        // content type, as it could be Ecore or EMOF.
        final Resource resource = resourceSet.createResource(fileURI);

        // Add the initial model object to the contents.
        final EObject rootObject = createInitialModel();
        if (rootObject != null) {
            domain.getCommandStack().execute(new AddEObjectAsRootCommand(domain, resource, rootObject));
        }

        URI airdURI = URI.createPlatformResourceURI(airdFile.getFullPath().toString(), true);
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

    private class AddEObjectAsRootCommand extends RecordingCommand {

        private EObject root;

        private Resource resource;

        public AddEObjectAsRootCommand(TransactionalEditingDomain domain, Resource resource, EObject root) {
            super(domain, "Add the given EObject as root of the given Resource"); //$NON-NLS-1$
            this.resource = resource;
            this.root = root;
        }

        @Override
        protected void doExecute() {
            if (resource != null && root != null) {
                resource.getContents().add(root);
            }
        }
    }
    
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
