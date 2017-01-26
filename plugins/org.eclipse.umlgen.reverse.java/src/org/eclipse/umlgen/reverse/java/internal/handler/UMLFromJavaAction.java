/*******************************************************************************
 * Copyright (c) 2005 Anyware Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.reverse.java.internal.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.umlgen.reverse.java.internal.ReversePlugin;
import org.eclipse.umlgen.reverse.java.internal.wizards.Java2UMLWizard;

/**
 * This action launch the convertion wizard.
 */
public class UMLFromJavaAction extends AbstractHandler {

    /** The java element. */
    protected IJavaElement javaElement;

    /** the window. */
    private IWorkbenchWindow window;

    /**
     * Sets the active part for the delegate. The active part is commonly used to get a working context for
     * the action, such as the shell for any dialog which is needed. This method will be called every time the
     * action appears in a popup menu. The targetPart may change with each invocation.
     *
     * @param action
     *            : the action proxy that handles presentation portion of the action; must not be null.
     * @param targetPart
     *            the new part target; must not be null.
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        window = targetPart.getSite().getWorkbenchWindow();
    }

    public Object execute(ExecutionEvent event) throws ExecutionException {

        // MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Info",
        // "Info for you");

        // Shell shell = Display.getCurrent().getActiveShell();
        Shell shell = Display.getDefault().getActiveShell();

        // Shell shell = window.getShell();

        try {
            IStructuredSelection selection = (IStructuredSelection)HandlerUtil
                    .getCurrentSelectionChecked(event);

            // Object obj = selection.getFirstElement();

            if (selection instanceof IStructuredSelection) {
                Object selectedObj = ((IStructuredSelection)selection).getFirstElement();
                if (selectedObj instanceof IPackageFragment) {
                    javaElement = (IPackageFragment)selectedObj;
                } else if (selectedObj instanceof IJavaProject) {
                    javaElement = (IJavaProject)selectedObj;
                }
            }

            Wizard wizard;
            wizard = createWizard();
            if (wizard instanceof IWorkbenchWizard) {
                ((IWorkbenchWizard)wizard).init(getWorkbench(),
                        new StructuredSelection(getInitialContainer()));
            }
            WizardDialog dialog = new WizardDialog(shell, wizard);
            dialog.create();
            int result = dialog.open();
            postWizard(result);
        } catch (CoreException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // } catch (ExecutionException e) {
        // String title = "Error";
        // String message = "An error occured during wizard execution.\n"
        // + "See error logs for more details.";
        //
        // //ErrorDialog.openError(shell, title, message, e.getStatus());
        // throw e;
        // }
        return null;
    }

    /**
     * Return the container associated with the selected IJavaElement.
     *
     * @return the initial container
     */
    protected IContainer getInitialContainer() {
        IContainer container = null;
        if (javaElement != null) {
            try {
                container = (IContainer)javaElement.getCorrespondingResource();
            } catch (JavaModelException e) {
                ReversePlugin.log(e);
            }
        }
        return container;
    }

    /**
     * Returns the Eclipse workbench.
     *
     * @return the workbench
     */
    protected IWorkbench getWorkbench() {
        return ReversePlugin.getDefault().getWorkbench();
    }

    /**
     * Create the wizard.
     *
     * @return the wizard that creates the reverse engineered model
     * @throws CoreException
     *             if an error occured during wizard
     */
    protected Wizard createWizard() throws CoreException {
        Java2UMLWizard wizard = new Java2UMLWizard();
        wizard.setJavaElement(javaElement);
        return wizard;
    }

    /**
     * Execute the post action after the wizard.
     *
     * @param code
     *            the return code of the wizard
     * @return <code>true</code> if the action is executed
     */
    protected boolean postWizard(int code) {
        if (code == Window.OK) {
            // TODO open the editor
            return true;
        }
        return false;
    }

    // /**
    // * Notifies this action delegate that the selection in the workbench has changed.
    // * Implementers can use this opportunity to change the availability of the
    // * action or to modify other presentation properties.
    // * When the selection changes, the action enablement state is updated based on
    // * the criteria specified in the plugin.xml file. Then the delegate is notified
    // * of the selection change regardless of whether the enablement criteria in the
    // * plugin.xml file is met.
    // *
    // * @param action the action proxy that handles presentation portion of
    // * the action
    // * @param selection the current selection, or null if there
    // * is no selection.
    // * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
    // * org.eclipse.jface.viewers.ISelection)
    // */
    // public void selectionChanged(IAction action, ISelection selection) {
    // javaElement = null;
    // if (selection instanceof IStructuredSelection) {
    // Object selectedObj = ((IStructuredSelection)selection).getFirstElement();
    // if (selectedObj instanceof IPackageFragment) {
    // javaElement = (IPackageFragment)selectedObj;
    // } else if (selectedObj instanceof IJavaProject) {
    // javaElement = (IJavaProject)selectedObj;
    // }
    // // TODO manage adaptable objects
    // }
    // action.setEnabled(javaElement != null);
    // }
}
