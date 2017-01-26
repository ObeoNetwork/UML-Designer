/*******************************************************************************
 * Copyright (c) 2008 Topcased contributors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thibault Landre (Atos Origin) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.reverse.java;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * An abstract implementation of a Preference page for Topcased's projects. This preference page allows
 * clients to define preference page in the preference of Eclipse, and in the properties of an element in the
 * workspace. Clients must implement the <code>getBundleId()</code> method in order to define the preference
 * scope (Project or Instance) of the preference page.
 */
public abstract class AbstractPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, IWorkbenchPropertyPage {

    /** the project. */
    private IProject project;

    /**
     * Returns the project.
     *
     * @return the project
     * @see org.eclipse.ui.IWorkbenchPropertyPage#getElement()
     */
    public IAdaptable getElement() {
        return project;
    }

    /**
     * Sets the object that owns the properties shown in this page.
     *
     * @param element
     *            : the object that owns the properties shown in this page
     * @see org.eclipse.ui.IWorkbenchPropertyPage#setElement(org.eclipse.core.runtime.IAdaptable)
     */
    public void setElement(IAdaptable element) {
        project = (IProject)element.getAdapter(IResource.class);
    }

    /**
     * Returns the preference store of this preference page.
     *
     * @return the preference store, or <code>null</code> if none
     */
    @Override
    protected IPreferenceStore doGetPreferenceStore() {
        IPreferenceStore store;
        if (project != null) {
            store = new ScopedPreferenceStore(new ProjectScope(project), getBundleId());
        } else {
            store = new ScopedPreferenceStore(new InstanceScope(), getBundleId());
        }
        return store;
    }

    /**
     * Return Bundle ID.
     *
     * @return String
     */
    protected abstract String getBundleId();

}
