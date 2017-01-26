/*******************************************************************************
 * Copyright (c) 2010, 2015 CS Systèmes d'Information (CS-SI).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Christophe Le Camus (CS-SI) - initial API and implementation
 *     Mikael Barbero (Obeo) 	- evolutions
 *     Sebastien Gabel (CS-SI) - evolutions
 *     Cedric Notot (Obeo) - evolutions to cut off from diagram part
 *******************************************************************************/
package org.eclipse.umlgen.gen.java.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.ILaunchGroup;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.umlgen.gen.java.ui.UML2JavaUIActivator;
import org.eclipse.umlgen.gen.java.ui.common.ConfigurationServices;
import org.eclipse.umlgen.gen.java.utils.IUML2JavaConstants;

/**
 * This generates C code from elements of a UML model.
 */
public class GenerateJavaHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        try {
            IStructuredSelection selection = (IStructuredSelection)HandlerUtil.getCurrentSelectionChecked(
                    event);
            IResource model = null;
            Object selectedObj = selection.getFirstElement();
            if (selectedObj instanceof IResource) {
                model = (IResource)selectedObj;
            } else if (selectedObj instanceof EObject) {
                model = ResourcesPlugin.getWorkspace().getRoot().findMember(((EObject)selectedObj).eResource()
                        .getURI().toPlatformString(true));
            }

            if (model != null) {
                String configName = ConfigurationServices.getConfigurationProperty(model);
                if (configName != null) {
                    ILaunchConfiguration config = ConfigurationServices.getStoredLaunchConfiguration(
                            configName);

                    if (config != null) {
                        String computedModelPath = model.getFullPath().toString();

                        String modelPath = config.getAttribute(IUML2JavaConstants.UML_MODEL_PATH, "");

                        if (modelPath != null && modelPath.equals(computedModelPath)) {
                            ILaunchGroup group = ConfigurationServices.getLaunchGroup();
                            if (group != null) {
                                DebugUITools.launch(config, group.getMode());
                            }
                        } else {
                            IStatus status = new Status(IStatus.ERROR, UML2JavaUIActivator.PLUGIN_ID,
                                    "No configuration matches with this model.");
                            UML2JavaUIActivator.getDefault().getLog().log(status);
                        }
                    } else {
                        IStatus status = new Status(IStatus.INFO, UML2JavaUIActivator.PLUGIN_ID,
                                "The launch configuration \"" + configName
                                        + "\" does not exist. Maybe it has been removed. You may define this in the properties of the model: \""
                                        + model.getFullPath().toString() + "\"");
                        UML2JavaUIActivator.getDefault().getLog().log(status);
                    }
                } else {
                    IStatus status = new Status(IStatus.INFO, UML2JavaUIActivator.PLUGIN_ID,
                            "No Java generation launch configuration has been chosen for the model: \""
                                    + model.getFullPath().toString() + "\"");
                    UML2JavaUIActivator.getDefault().getLog().log(status);
                }
            }
        } catch (ExecutionException e) {
            throw e;
        } catch (CoreException e2) {
            e2.printStackTrace();
        }
        return null;
    }

}
