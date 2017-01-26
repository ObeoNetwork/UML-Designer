/*******************************************************************************
 * Copyright (c) 2011, 2014 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephane Begaudeau (Obeo) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.gen.java.ui.launch;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.umlgen.gen.java.main.Uml2java;
import org.eclipse.umlgen.gen.java.services.UML2JavaConfigurationHolder;
import org.eclipse.umlgen.gen.java.ui.UML2JavaUIActivator;
import org.eclipse.umlgen.gen.java.utils.IUML2JavaConstants;

/**
 * The UML to Java launch configuration launcher.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 1.0
 */
public class UML2JavaLaunchDelegate implements ILaunchConfigurationDelegate {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.eclipse.debug.core.ILaunchConfiguration,
     *      java.lang.String, org.eclipse.debug.core.ILaunch, org.eclipse.core.runtime.IProgressMonitor)
     */
    public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch,
            IProgressMonitor monitor) throws CoreException {
        String umlModelPath = "";
        try {
            umlModelPath = configuration.getAttribute(IUML2JavaConstants.UML_MODEL_PATH, "");
        } catch (CoreException e) {
            IStatus status = new Status(IStatus.ERROR, UML2JavaUIActivator.PLUGIN_ID, e.getMessage(), e);
            UML2JavaUIActivator.getDefault().getLog().log(status);
        }

        if (umlModelPath == null || umlModelPath.length() == 0) {
            return;
        }

        IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(umlModelPath));
        IContainer container = ResourcesPlugin.getWorkspace().getRoot();
        if (file != null && container != null && file.isAccessible() && container.isAccessible()) {
            URI modelURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);

            UML2JavaConfigurationHolder configurationHolder = this.createConfigurationHolder(configuration);

            // Add the location of the generation to the configuration holder
            configurationHolder.put(IUML2JavaConstants.GENERATION_ROOT_PATH, container.getLocation().toFile()
                    .getAbsolutePath());

            try {
                Uml2java uml2java = new Uml2java(modelURI, container.getLocation().toFile(),
                        new ArrayList<String>());

                uml2java.setConfigurationHolder(configurationHolder);

                uml2java.doGenerate(BasicMonitor.toMonitor(monitor));
                container.refreshLocal(IResource.DEPTH_INFINITE, monitor);
            } catch (IOException e) {
                IStatus status = new Status(IStatus.ERROR, UML2JavaUIActivator.PLUGIN_ID, e.getMessage(), e);
                UML2JavaUIActivator.getDefault().getLog().log(status);
            }
        }
    }

    /**
     * Creates the configuration holder from the launch configuration.
     * 
     * @param configuration
     *            The launch configuration
     * @return The configuration holder from the launch configuration.
     */
    private UML2JavaConfigurationHolder createConfigurationHolder(ILaunchConfiguration configuration) {
        UML2JavaConfigurationHolder configurationHolder = new UML2JavaConfigurationHolder();

        try {
            // General
            configurationHolder.put(IUML2JavaConstants.UML_MODEL_PATH, configuration.getAttribute(
                    IUML2JavaConstants.UML_MODEL_PATH, ""));
            configurationHolder.put(IUML2JavaConstants.DEFAULT_PROJECT_NAME, configuration.getAttribute(
                    IUML2JavaConstants.DEFAULT_PROJECT_NAME, ""));
            configurationHolder.put(IUML2JavaConstants.SOURCE_FOLDER_PATH, configuration.getAttribute(
                    IUML2JavaConstants.SOURCE_FOLDER_PATH, ""));
            configurationHolder.put(IUML2JavaConstants.OUTPUT_FOLDER_PATH, configuration.getAttribute(
                    IUML2JavaConstants.OUTPUT_FOLDER_PATH, ""));
            configurationHolder.put(IUML2JavaConstants.JRE_EXECUTION_ENVIRONMENT, configuration.getAttribute(
                    IUML2JavaConstants.JRE_EXECUTION_ENVIRONMENT, ""));

            // Class
            configurationHolder.put(IUML2JavaConstants.PACKAGES_TO_IGNORE_DURING_GENERATION, configuration
                    .getAttribute(IUML2JavaConstants.PACKAGES_TO_IGNORE_DURING_GENERATION, ""));
            configurationHolder.put(IUML2JavaConstants.PACKAGES_TO_IGNORE_DURING_IMPORTS, configuration
                    .getAttribute(IUML2JavaConstants.PACKAGES_TO_IGNORE_DURING_IMPORTS, ""));
            configurationHolder.put(IUML2JavaConstants.GENERATE_GETTERS_AND_SETTERS, configuration
                    .getAttribute(IUML2JavaConstants.GENERATE_GETTERS_AND_SETTERS, false));
            configurationHolder.put(IUML2JavaConstants.GENERATE_GETTERS_COLLECTIONS, configuration
                    .getAttribute(IUML2JavaConstants.GENERATE_GETTERS_COLLECTIONS, false));
            configurationHolder.put(IUML2JavaConstants.GENERATE_SETTERS_COLLECTIONS, configuration
                    .getAttribute(IUML2JavaConstants.GENERATE_SETTERS_COLLECTIONS, false));
            configurationHolder.put(IUML2JavaConstants.GENERATE_ADVANCED_ACCESSORS_COLLECTIONS, configuration
                    .getAttribute(IUML2JavaConstants.GENERATE_ADVANCED_ACCESSORS_COLLECTIONS, false));
            configurationHolder.put(IUML2JavaConstants.AUTHOR, configuration.getAttribute(
                    IUML2JavaConstants.AUTHOR, ""));
            configurationHolder.put(IUML2JavaConstants.VERSION, configuration.getAttribute(
                    IUML2JavaConstants.VERSION, ""));
            configurationHolder.put(IUML2JavaConstants.COPYRIGHT_AND_LICENSE, configuration.getAttribute(
                    IUML2JavaConstants.COPYRIGHT_AND_LICENSE, ""));

            // Component
            configurationHolder.put(IUML2JavaConstants.COMPONENTS_TO_IGNORE, configuration.getAttribute(
                    IUML2JavaConstants.COMPONENTS_TO_IGNORE, ""));
            configurationHolder.put(IUML2JavaConstants.COMPONENTS_ARCHITECTURE, configuration.getAttribute(
                    IUML2JavaConstants.COMPONENTS_ARCHITECTURE, ""));
            configurationHolder.put(IUML2JavaConstants.BUNDLE_PROVIDER, configuration.getAttribute(
                    IUML2JavaConstants.BUNDLE_PROVIDER, ""));

            // Type
            configurationHolder.put(IUML2JavaConstants.ORDERED_UNIQUE_TYPE, configuration.getAttribute(
                    IUML2JavaConstants.ORDERED_UNIQUE_TYPE, ""));
            configurationHolder.put(IUML2JavaConstants.ORDERED_NOT_UNIQUE_TYPE, configuration.getAttribute(
                    IUML2JavaConstants.ORDERED_NOT_UNIQUE_TYPE, ""));
            configurationHolder.put(IUML2JavaConstants.NOT_ORDERED_UNIQUE_TYPE, configuration.getAttribute(
                    IUML2JavaConstants.NOT_ORDERED_UNIQUE_TYPE, ""));
            configurationHolder.put(IUML2JavaConstants.NOT_ORDERED_NOT_UNIQUE_TYPE, configuration
                    .getAttribute(IUML2JavaConstants.NOT_ORDERED_NOT_UNIQUE_TYPE, ""));

            configurationHolder.put(IUML2JavaConstants.IGNORE_JAVA_TYPES_DURING_GENERATION_AND_IMPORT,
                    configuration.getAttribute(
                            IUML2JavaConstants.IGNORE_JAVA_TYPES_DURING_GENERATION_AND_IMPORT, false));
            configurationHolder.put(IUML2JavaConstants.TYPES_TO_IGNORE_DURING_GENERATION, configuration
                    .getAttribute(IUML2JavaConstants.TYPES_TO_IGNORE_DURING_GENERATION, ""));
            configurationHolder.put(IUML2JavaConstants.TYPES_TO_IGNORE_DURING_IMPORTS, configuration
                    .getAttribute(IUML2JavaConstants.TYPES_TO_IGNORE_DURING_IMPORTS, ""));

        } catch (CoreException e) {
            IStatus status = new Status(IStatus.ERROR, UML2JavaUIActivator.PLUGIN_ID, e.getMessage(), e);
            UML2JavaUIActivator.getDefault().getLog().log(status);
        }

        return configurationHolder;
    }

}
