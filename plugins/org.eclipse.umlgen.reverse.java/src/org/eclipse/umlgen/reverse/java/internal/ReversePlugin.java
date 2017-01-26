/*******************************************************************************
 * Copyright (c) 2006 Anyware Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.reverse.java.internal;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class ReversePlugin extends AbstractUIPlugin {

    /**
     * The shared instance.
     */
    private static ReversePlugin plugin;

    /**
     * The constructor.
     */
    public ReversePlugin() {
        plugin = this;
    }

    /**
     * This method is called upon plug-in activation.
     *
     * @param context
     * @throws Exception
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
    }

    /**
     * This method is called when the plug-in is stopped.
     *
     * @param context
     * @throws Exception
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        super.stop(context);
        plugin = null;
    }

    /**
     * Returns the shared instance.
     *
     * @return plugin
     */
    public static ReversePlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path.
     *
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.umlgen.reverse.java", path);
    }

    /**
     * Returns the plugin ID.
     *
     * @return Id
     */
    public static String getId() {
        return getDefault().getBundle().getSymbolicName();
    }

    /**
     * Log a message with given level into the Eclipse log file.
     *
     * @param message
     *            the message to log
     * @param level
     *            the message priority
     */
    public static void log(String message, int level) {
        IStatus status = null;
        status = new Status(level, getId(), IStatus.OK, message, null);
        log(status);
    }

    /**
     * Log an exception into the Eclipse log file.
     *
     * @param e
     *            the exception to log
     */
    public static void log(Throwable e) {
        if (e instanceof InvocationTargetException) {
            e = ((InvocationTargetException)e).getTargetException();
        }

        IStatus status = null;
        if (e instanceof CoreException) {
            status = ((CoreException)e).getStatus();
        } else {
            status = new Status(IStatus.ERROR, getId(), IStatus.OK, "Error", e);
        }

        log(status);
    }

    /**
     * Log an IStatus.
     *
     * @param status
     */
    public static void log(IStatus status) {
        getDefault().getLog().log(status);
    }
}
