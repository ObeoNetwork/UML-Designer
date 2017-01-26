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
package org.eclipse.umlgen.gen.java.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 0.1
 */
public class UML2JavaUIActivator extends AbstractUIPlugin {

    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "org.eclipse.umlgen.gen.java.ui";

    /**
     * The shared instance.
     */
    private static UML2JavaUIActivator plugin;

    /**
     * The images.
     */
    private Map<String, Image> imageMap = new HashMap<String, Image>();

    /**
     * The constructor.
     */
    public UML2JavaUIActivator() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
     * @generated
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     * @generated
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static UML2JavaUIActivator getDefault() {
        return plugin;
    }

    /**
     * Returns an image at the given plug-in relative path.
     * 
     * @param path
     *            is a plug-in relative path
     * @return the image
     */
    public Image getImage(String path) {
        Image result = imageMap.get(path);
        if (result == null) {
            ImageDescriptor descriptor = getImageDescriptor(path);
            if (descriptor != null) {
                result = descriptor.createImage();
                imageMap.put(path, result);
            }
        }
        return result;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path.
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
