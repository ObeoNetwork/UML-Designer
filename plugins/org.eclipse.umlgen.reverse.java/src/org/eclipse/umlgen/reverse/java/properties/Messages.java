/*******************************************************************************
 * Copyright (c) 2010 Communication & Systems.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christophe Le Camus (CS) - initial API and implementation
 *    Ludovic Faure (CS)
 *******************************************************************************/
package org.eclipse.umlgen.reverse.java.properties;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Class for message internationalization.
 */
public final class Messages {

    /** Name of the bundle. */
    private static final String BUNDLE_NAME = "org.eclipse.umlgen.reverse.java.properties.messages"; //$NON-NLS-1$

    /** Resource of the bundle. */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Constructor.
     */
    private Messages() {
    }

    /**
     * Returns the text of the resource bundle.
     *
     * @param key
     * @return the text
     */
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
