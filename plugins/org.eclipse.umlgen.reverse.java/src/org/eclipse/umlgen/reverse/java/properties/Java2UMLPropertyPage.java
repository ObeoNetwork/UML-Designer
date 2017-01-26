/*******************************************************************************
 * Copyright (c) 2010 Communication & Systems.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christophe Le Camus (CS)  - initial API and implementation
 *    Ludovic Faure (CS)
 *******************************************************************************/
package org.eclipse.umlgen.reverse.java.properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.umlgen.reverse.java.AbstractPreferencePage;
import org.eclipse.umlgen.reverse.java.internal.ReversePlugin;
import org.eclipse.umlgen.reverse.java.preferencesStore.Java2UMLPreferencesStoreConstants;
import org.eclipse.umlgen.reverse.java.preferencesStore.Java2UMLPreferencesStoreManager;

/**
 * Manages the customization for reverse Java to UML.<br />
 * Allows the reverse of the imports rather than computed by Eclipse Allows the computing of packages
 * dependencies from imports basis. Creation : 7 december 2010<br/>
 */
public class Java2UMLPropertyPage extends AbstractPreferencePage {

    /** Reverse Imports. */
    private BooleanFieldEditor reverseImports;

    /** Compute dependencies. */
    private BooleanFieldEditor computeDependencies;

    /** * A collection of related persistent documents. */
    private ResourceSet rscSet;

    /**
     * Constructor.
     */
    public Java2UMLPropertyPage() {
        rscSet = new ResourceSetImpl();
    }

    /**
     * Sets the object that owns the properties shown in this page.
     *
     * @param element
     *            : the object that owns the properties shown in this page
     * @see org.eclipse.umlgen.reverse.java.AbstractPreferencePage#setElement(org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    public void setElement(IAdaptable element) {
        super.setElement(element);
        Java2UMLPreferencesStoreManager.setDefaultValues((IProject)getElement());
    }

    /**
     * Creates and returns the SWT control for the customized body of this preference page under the given
     * parent composite.
     *
     * @param parent
     *            the parent composite
     * @return the new control
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        // Main Composite
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        final GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        mainComposite.setLayout(layout);

        // create the top group related to define the synchronization mode when a new project has been started
        createReverseGroup(mainComposite);

        loadPreferences();

        return mainComposite;
    }

    /**
     * Creates the first group on which synchronization policy is defined (from C source or from UML model).
     *
     * @param parent
     *            The composite parent
     */
    private void createReverseGroup(Composite parent) {
        reverseImports = new BooleanFieldEditor(Java2UMLPreferencesStoreConstants.REVERSE_IMPORTS_NAME,
                Java2UMLPreferencesStoreConstants.REVERSE_IMPORTS_LABEL, parent);
        reverseImports.setPreferenceStore(getPreferenceStore());
        computeDependencies = new BooleanFieldEditor(
                Java2UMLPreferencesStoreConstants.COMPUTE_DEPENDENCIES_NAME,
                Java2UMLPreferencesStoreConstants.COMPUTE_DEPENDENCIES_LABEL, parent);
        computeDependencies.setPreferenceStore(getPreferenceStore());
    }

    /**
     * Loads the preferences.
     */
    private void loadPreferences() {
        reverseImports.load();
        computeDependencies.load();
    }

    /**
     * Stores the preferences.
     */
    private void storePreferences() {
        reverseImports.store();
        computeDependencies.store();
    }

    /**
     * Loads the default preferences.
     */
    private void loadDefaultPreferences() {
        reverseImports.loadDefault();
        computeDependencies.loadDefault();
    }

    /**
     * .
     *
     * @see org.eclipse.jface.preference.PreferencePage#performOk()
     */
    @Override
    public boolean performOk() {
        storePreferences();
        return super.performOk();
    }

    /**
     * Performs special processing when this page's Defaults button has been pressed. This is a framework hook
     * method for subclasses to do special things when the Defaults button has been pressed. Subclasses may
     * override, but should call super.performDefaults.
     *
     * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
     */
    @Override
    protected void performDefaults() {
        loadDefaultPreferences();
        super.performDefaults();
    }

    /**
     * Return Bundle ID.
     *
     * @return String
     * @see org.eclipse.umlgen.reverse.java.AbstractPreferencePage#getBundleId()
     */
    @Override
    protected String getBundleId() {
        return ReversePlugin.getId();
    }

    /**
     * Initializes this preference page for the given workbench. This method is called automatically as the
     * preference page is being created and initialized. Clients must not call this method.
     *
     * @param workbench
     *            the workbench
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        // nothing to do while initializing
    }

    /**
     * The DialogPage implementation of this IDialogPage</code> method disposes of the page image if it has
     * one. Subclasses may extend.
     *
     * @see org.eclipse.jface.dialogs.DialogPage#dispose()
     */
    @Override
    public void dispose() {
        for (Resource rsc : rscSet.getResources()) {
            rsc.unload();
        }
        rscSet = null;
    }
}
