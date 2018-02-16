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
package org.obeonetwork.dsl.uml2.core.internal.services;

import org.eclipse.core.runtime.IStatus;
import org.obeonetwork.dsl.uml2.core.UMLDesignerCorePlugin;

/**
 * This service provides methods to log info, warning and errors in the error
 * log view.
 *
 * @author Yann Mortier
 *         <a href="mailto:yann.mortier@obeo.fr">yann.mortier@obeo.fr</a>
 */
public class LogServices {
    /**
     * A singleton instance to be accessed by other java services.
     */
    public static final LogServices INSTANCE = new LogServices();

    /**
     * Hidden constructor.
     */
    private LogServices() {

    }

    /**
     * Log an error.
     *
     * @param s
     *            the message.
     * @param t
     *            the stack trace.
     */
    public void error(String s, Throwable t) {
        UMLDesignerCorePlugin.log(IStatus.ERROR, s, t);
    }

    /**
     * Log an information.
     *
     * @param s
     *            the message.
     */
    public void info(String s) {
        UMLDesignerCorePlugin.log(IStatus.INFO, s, null);
    }

    /**
     * Log a warning.
     *
     * @param s
     *            the message.
     * @param t
     *            the stack trace.
     */
    public void warning(String s, Throwable t) {
        UMLDesignerCorePlugin.log(IStatus.WARNING, s, t);
    }

}
