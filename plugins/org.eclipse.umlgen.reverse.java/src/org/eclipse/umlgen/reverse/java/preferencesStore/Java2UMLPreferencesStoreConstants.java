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
package org.eclipse.umlgen.reverse.java.preferencesStore;

/**
 * Constants for the Java2UML Preference Store.
 */
public final class Java2UMLPreferencesStoreConstants {

    /**
     * Reverse Imports node name.
     */
    public static final String REVERSE_IMPORTS_NAME = "REVERSE_INDICATOR";

    /**
     * Reverse Imports label.
     */
    public static final String REVERSE_IMPORTS_LABEL = "Do not Reverse Imports from Java code.";

    /**
     * Reverse Imports from Java code.
     */
    public static final boolean REVERSE_IMPORTS = false;

    /**
     * Compute Dependencies node name.
     */
    public static final String COMPUTE_DEPENDENCIES_NAME = "COMPUTE_DEPENDENCIES_INDICATOR";

    /**
     * Compute Dependencies label.
     */
    public static final String COMPUTE_DEPENDENCIES_LABEL = "Do not Compute Package Dependencies from Java Code.";

    /**
     * Compute Dependencies from Java code.
     */
    public static final boolean COMPUTE_DEPENDENCIES = false;

    /**
     * Do not instanciate.
     */
    private Java2UMLPreferencesStoreConstants() {

    }
}
