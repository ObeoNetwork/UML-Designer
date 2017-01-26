/*******************************************************************************
 * Copyright (c) 2006 Anyware Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies) - initial API and implementation
 *    Urs Zeidler   - Added two importing options, model imports, profile import
 *    Thomas Szadel (Atos Origin) - Allow to limit the details of the import
 *******************************************************************************/
package org.eclipse.umlgen.reverse.java.internal.wizards;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.umlgen.reverse.java.AbstractJava2UMLConverter;
import org.eclipse.umlgen.reverse.java.AbstractJava2UMLConverter.ActivityGeneration;
import org.eclipse.umlgen.reverse.java.Java2UMLConverter;
import org.eclipse.umlgen.reverse.java.internal.ReversePlugin;
import org.eclipse.umlgen.reverse.java.logging.LogUtils;

/**
 * This is a sample new wizard. Its role is to create a new file resource in the provided container. If the
 * container resource (a folder or a project) is selected in the workspace when the wizard is opened, it will
 * accept it as the target container. The wizard creates one file with the extension "uml2". If a sample
 * multi-page editor (also available as a template) is registered for the same extension, it will be able to
 * open it.
 */
public class Java2UMLWizard extends Wizard implements INewWizard {

    /** The wizard page. */
    protected Java2UMLWizardPage page;

    /** . */
    protected ISelection selection;

    /** the java element. */
    protected IJavaElement javaElement;

    /**
     * Constructor for Java2UML2Wizard.
     */
    public Java2UMLWizard() {
        super();
        setNeedsProgressMonitor(true);
    }

    /**
     * Adding the page to the wizard.
     */
    @Override
    public void addPages() {
        page = new Java2UMLWizardPage(selection);
        addPage(page);
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and
     * run it using wizard as execution context.
     */
    @Override
    public boolean performFinish() {
        final String containerName = page.getContainerName();
        final String fileName = page.getFileName();
        final VisibilityKind visibility = page.getVisibilityKind();
        final ActivityGeneration activityGen = page.getActivityGeneration();
        final String[] importList = page.getImportList();
        final String modelName = page.getModelName();
        final int logLevel = page.getLoggingLevel();

        LogUtils.setLogLevel(logLevel);
        IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(IProgressMonitor monitor) throws InvocationTargetException {
                try {
                    doFinish(containerName, fileName, importList, monitor, modelName, visibility, activityGen);
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                } finally {
                    monitor.done();
                }
            }
        };
        try {
            getContainer().run(true, false, op);
        } catch (InterruptedException e) {
            e.printStackTrace();
            LogUtils.logThrowable(e);
            return false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            Throwable realException = e.getTargetException();
            MessageDialog.openError(getShell(), "Error", realException.getMessage());
            return false;
        }

        return true;
    }

    /**
     * The worker method. It will find the container, create the file if missing or just replace its contents,
     * and open the editor on the newly created file.
     *
     * @param containerName
     * @param fileName
     * @param importList
     * @param monitor
     * @param modelName
     * @param visibility
     *            The max visibiltiy to import.
     * @param activityGen
     * @throws CoreException
     */
    protected void doFinish(String containerName, String fileName, String[] importList,
            IProgressMonitor monitor, String modelName, VisibilityKind visibility,
            ActivityGeneration activityGen) throws CoreException {
        // create a sample file
        monitor.beginTask("Creating " + fileName, 2);
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(new Path(containerName));
        if (!resource.exists() || !(resource instanceof IContainer)) {
            throwCoreException("Container \"" + containerName + "\" does not exist.");
        }
        IContainer container = (IContainer)resource;
        final IFile file = container.getFile(new Path(fileName));

        if (javaElement == null) {
            throwCoreException("No java element selected.");
        }

        AbstractJava2UMLConverter converter = null;
        converter = new Java2UMLConverter();

        Package model = null;
        converter.setImportList(importList);
        converter.setModelName(modelName);
        // TSL: limit the visibility
        converter.setVisibility(visibility);

        // Create a resource set
        ResourceSet resourceSet = new ResourceSetImpl();

        // Get the URI of the model file.
        URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toString(), false);

        // Create a resource for this file.
        Resource emfResource = resourceSet.createResource(fileURI);

        LogUtils.logMessage("Convert " + javaElement.getElementName());
        model = converter.convert(javaElement, emfResource, activityGen);

        // Save the contents of the resource to the file
        // system.
        //
        Map<?, ?> options = new HashMap<Object, Object>();
        try {
            LogUtils.logMessage("Saving uml file " + fileURI.path());
            emfResource.save(options);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            IStatus status = new Status(IStatus.ERROR, ReversePlugin.getId(), IStatus.OK,
                    "An error occured during saving resource", ioe);
            throw new CoreException(status);
        }

        // Refreshing java element to ensure log file is present and up to date
        javaElement.getResource().refreshLocal(IResource.DEPTH_ONE, null);
        LogUtils.logMessage("Uml file " + fileURI.path() + " saved");

    }

    /**
     * We will accept the selection in the workbench to see if we can initialize from it.
     *
     * @param workbench
     * @param sel
     * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection sel) {
        this.selection = sel;
    }

    /**
     * Core exceptions contain a status object describing the cause of the exception.
     *
     * @param message
     * @throws CoreException
     */
    protected void throwCoreException(String message) throws CoreException {
        IStatus status = new Status(IStatus.ERROR, ReversePlugin.getId(), IStatus.OK, message, null);
        throw new CoreException(status);
    }

    /**
     * Get the Java Element to be reverse engineered.
     *
     * @return the element to transform
     */
    public IJavaElement getJavaElement() {
        return javaElement;
    }

    /**
     * Get the Java Element to be reverse engineered.
     *
     * @param elt
     *            the element to transform
     */
    public void setJavaElement(IJavaElement elt) {
        javaElement = elt;
    }

    /**
     * Set the activity generation setting.
     *
     * @param activityGen
     */
    public void setActivityGen(ActivityGeneration activityGen) {
        page.setActivityGen(activityGen);
    }
}
