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
package org.eclipse.umlgen.gen.java;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 0.1
 */
public class UML2JavaActivator extends Plugin {

    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "org.eclipse.umlgen.gen.java";

    /**
     * The shared instance.
     */
    private static UML2JavaActivator plugin;

    /**
     * The constructor.
     */
    public UML2JavaActivator() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
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
    public static UML2JavaActivator getDefault() {
        return plugin;
    }

    /**
     * Trace an Exception in the error log.
     * 
     * @param e
     *            Exception to log.
     * @param blocker
     *            <code>True</code> if the exception must be logged as error, <code>False</code> to log it as
     *            a warning.
     */
    public static void log(Exception e, boolean blocker) {
        if (e == null) {
            throw new NullPointerException(UML2JavaMessages.getString("UML2JavaPlugin.LoggingNullException")); //$NON-NLS-1$
        }

        if (getDefault() == null) {
            // We are out of eclipse. Prints the stack trace on standard error.
            // CHECKSTYLE:OFF
            e.printStackTrace();
            // CHECKSTYLE:ON
        } else if (e instanceof CoreException) {
            log(((CoreException)e).getStatus());
        } else if (e instanceof NullPointerException) {
            int severity = IStatus.WARNING;
            if (blocker) {
                severity = IStatus.ERROR;
            }
            log(new Status(severity, PLUGIN_ID, severity, UML2JavaMessages
                    .getString("UML2JavaPlugin.RequiredElementNotFound"), e)); //$NON-NLS-1$
        } else {
            int severity = IStatus.WARNING;
            if (blocker) {
                severity = IStatus.ERROR;
            }
            log(new Status(severity, PLUGIN_ID, severity, e.getMessage(), e));
        }
    }

    /**
     * Puts the given status in the error log view.
     * 
     * @param status
     *            Error Status.
     */
    public static void log(IStatus status) {
        // Eclipse platform displays NullPointer on standard error instead of throwing it.
        // We'll handle this by throwing it ourselves.
        if (status == null) {
            throw new NullPointerException(UML2JavaMessages.getString("UML2JavaPlugin.LoggingNullStats")); //$NON-NLS-1$
        }

        if (getDefault() != null) {
            getDefault().getLog().log(status);
        } else {
            // We are out of eclipse. Prints the message on standard error.
            // CHECKSTYLE:OFF
            System.err.println(status.getMessage());
            status.getException().printStackTrace();
            // CHECKSTYLE:ON
        }
    }

    /**
     * Log an information.
     * 
     * @param information
     *            the message to log.
     */
    public static void log(String information) {
        System.out.println(information);
    }
}
