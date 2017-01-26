/*******************************************************************************
 * Copyright (c) 2010, 2015 Communication & Systems and others.
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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.umlgen.reverse.java.internal.ReversePlugin;

/**
 * Manager for the Java2UML Preference Store.
 */
public final class Java2UMLPreferencesStoreManager {
    /**
     * Constructor.
     */
    private Java2UMLPreferencesStoreManager() {
        // avoid to be instantiated.
    }

    /**
     * Sets the default values into the preference store when the C2UML property page is called.
     *
     * @param project
     *            The selected IProject corresponding to the Java Project
     */
    public static void setDefaultValues(IProject project) {
        IPreferenceStore store = getPreferenceStore(project);
        store.setDefault(Java2UMLPreferencesStoreConstants.REVERSE_IMPORTS_NAME,
                Java2UMLPreferencesStoreConstants.REVERSE_IMPORTS);
        store.setDefault(Java2UMLPreferencesStoreConstants.COMPUTE_DEPENDENCIES_NAME,
                Java2UMLPreferencesStoreConstants.COMPUTE_DEPENDENCIES);
    }

    /**
     * Sets the initial values when the C nature is added to the project.
     *
     * @param project
     *            The selected IProject corresponding to the C Project
     */
    public static void setInitialValues(IProject project) {
        IPreferenceStore store = getPreferenceStore(project);
        store.setValue(Java2UMLPreferencesStoreConstants.REVERSE_IMPORTS_NAME,
                Java2UMLPreferencesStoreConstants.REVERSE_IMPORTS);
        store.setValue(Java2UMLPreferencesStoreConstants.COMPUTE_DEPENDENCIES_NAME,
                Java2UMLPreferencesStoreConstants.COMPUTE_DEPENDENCIES);
    }

    /**
     * Gets the project or instance preference store.
     *
     * @param project
     *            the IProject in the UML model will be created.
     * @return the project scope preference store or the instance scope
     */
    public static IPreferenceStore getPreferenceStore(IProject project) {
        IPersistentPreferenceStore store;
        if (project != null) {
            store = new ScopedPreferenceStore(new ProjectScope(project), ReversePlugin.getId());
        } else {
            store = new ScopedPreferenceStore(new InstanceScope(), ReversePlugin.getId());
        }
        return store;
    }

}
