/*******************************************************************************
 * Copyright (c) 2008, 2014 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephane Begaudeau (Obeo) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.gen.java.ui.launch.tabs;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.umlgen.gen.java.ui.UML2JavaUIActivator;
import org.eclipse.umlgen.gen.java.ui.utils.UML2JavaMessages;
import org.eclipse.umlgen.gen.java.utils.IUML2JavaConstants;

/**
 * The class tab of the launch configuration.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 2.0
 */
public class UML2JavaClassLaunchConfigurationTab extends AbstractUML2JavaLaunchConfigurationTab {

    /**
     * A comma-separated list of packages to ignore during the generation.
     */
    private Text packagesToIgnoreDuringTheGenerationText;

    /**
     * A comma-separated list of packages to ignore while computing import declaration.
     */
    private Text packagesToIgnoreDuringTheImportText;

    /**
     * Indicates if we should generate getters and setters.
     */
    private Button generateGettersAndSettersButton;

    /**
     * Indicates if we should generate getters for collections.
     */
    private Button generateGettersForCollectionsButton;

    /**
     * Indicates if we should generate setters for collections.
     */
    private Button generateSettersForCollectionsButton;

    /**
     * Indicates if we should generate advanced accessors for collections.
     */
    private Button generateAdvancedAccessorsForCollectionsButton;

    /**
     * The author of the code.
     */
    private Text authorText;

    /**
     * The version of the code.
     */
    private Text versionText;

    /**
     * The compyright and the license of the code.
     */
    private Text copyrightAndLicenseText;

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        Font font = parent.getFont();
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(1, false));
        composite.setFont(font);
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 1;
        composite.setLayoutData(gd);

        this.createGenerationGroup(composite, font);
        this.createAccessorsGroup(composite, font);
        this.createDocumentationGroup(composite, font);

        this.setControl(composite);
        this.update();
    }

    /**
     * Creates the group containing the generation options of the class generation.
     *
     * @param composite
     *            The composite containing the group
     * @param font
     *            The font used by the parent of the group
     */
    private void createGenerationGroup(Composite composite, Font font) {
        GridData gd;
        Group generationGroup = createGroup(composite, UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.GenerationGroupName"), 3, 1,
                GridData.FILL_HORIZONTAL);
        Composite comp = new Composite(generationGroup, SWT.NONE);
        GridLayout layout = new GridLayout(3, false);
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        comp.setLayout(layout);
        comp.setFont(font);
        gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 2;
        comp.setLayoutData(gd);

        // Packages to ignore during the generation
        Label packagesToIgnoreDuringTheGenerationLabel = new Label(comp, SWT.NONE);
        packagesToIgnoreDuringTheGenerationLabel.setText(UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.PackagesToIgnoreDuringTheGenerationLabel"));

        this.packagesToIgnoreDuringTheGenerationText = new Text(comp, SWT.SINGLE | SWT.BORDER);
        this.packagesToIgnoreDuringTheGenerationText.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.packagesToIgnoreDuringTheGenerationText.setLayoutData(gd);
        this.packagesToIgnoreDuringTheGenerationText
                .setText(IUML2JavaConstants.Default.DEFAULT_PACKAGES_TO_IGNORE_DURING_GENERATION);
        this.packagesToIgnoreDuringTheGenerationText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.PackagesToIgnoreDuringTheGenerationHelp"));

        // Packages to ignore during the imports
        Label packagesToIgnoreDuringTheImportLabel = new Label(comp, SWT.NONE);
        packagesToIgnoreDuringTheImportLabel.setText(UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.PackagesToIgnoreDuringTheImportLabel"));

        this.packagesToIgnoreDuringTheImportText = new Text(comp, SWT.SINGLE | SWT.BORDER);
        this.packagesToIgnoreDuringTheImportText.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.packagesToIgnoreDuringTheImportText.setLayoutData(gd);
        this.packagesToIgnoreDuringTheImportText
                .setText(IUML2JavaConstants.Default.DEFAULT_PACKAGES_TO_IGNORE_DURING_IMPORTS);
        this.packagesToIgnoreDuringTheImportText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.TypessToIgnoreDuringTheImportHelp"));
    }

    /**
     * Creates the group containing the accessors options of the class generation.
     *
     * @param composite
     *            The composite containing the group
     * @param font
     *            The font used by the parent of the group
     */
    private void createAccessorsGroup(Composite composite, Font font) {
        GridData gd;
        Group accessorsGroup = createGroup(composite, UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.AccessorsGroupName"), 3, 1,
                GridData.FILL_HORIZONTAL);
        Composite comp = new Composite(accessorsGroup, SWT.NONE);
        GridLayout layout = new GridLayout(2, false);
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        comp.setLayout(layout);
        comp.setFont(font);
        gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 2;
        comp.setLayoutData(gd);

        // Generate getters and setters
        this.generateGettersAndSettersButton = new Button(comp, SWT.CHECK);
        this.generateGettersAndSettersButton.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.generateGettersAndSettersButton.setLayoutData(gd);
        this.generateGettersAndSettersButton.setText(UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.GenerateGettersAndSettersLabel"));
        this.generateGettersAndSettersButton
                .setSelection(IUML2JavaConstants.Default.DEFAULT_GENERATE_GETTERS_AND_SETTERS);
        this.generateGettersAndSettersButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                update();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.GenerateGettersAndSettersHelp"));

        // Generate getters for collections
        this.generateGettersForCollectionsButton = new Button(comp, SWT.CHECK);
        this.generateGettersForCollectionsButton.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.generateGettersForCollectionsButton.setLayoutData(gd);
        this.generateGettersForCollectionsButton.setText(UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.GenerateGettersForCollectionsLabel"));
        this.generateGettersForCollectionsButton
                .setSelection(IUML2JavaConstants.Default.DEFAULT_GENERATE_GETTERS_COLLECTIONS);
        this.generateGettersForCollectionsButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                update();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.GenerateGettersForCollectionsHelp"));

        // Generate setters for collections
        this.generateSettersForCollectionsButton = new Button(comp, SWT.CHECK);
        this.generateSettersForCollectionsButton.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.generateSettersForCollectionsButton.setLayoutData(gd);
        this.generateSettersForCollectionsButton.setText(UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.GenerateSettersForCollectionsLabel"));
        this.generateSettersForCollectionsButton
                .setSelection(IUML2JavaConstants.Default.DEFAULT_GENERATE_SETTERS_COLLECTIONS);
        this.generateSettersForCollectionsButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                update();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.GenerateSettersForCollectionsHelp"));

        // Generate advanced accessors for collections
        this.generateAdvancedAccessorsForCollectionsButton = new Button(comp, SWT.CHECK);
        this.generateAdvancedAccessorsForCollectionsButton.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.generateAdvancedAccessorsForCollectionsButton.setLayoutData(gd);
        this.generateAdvancedAccessorsForCollectionsButton
                .setText(UML2JavaMessages
                        .getString("UML2JavaClassLaunchConfigurationTab.GenerateAdvancedAccessorsForCollectionsLabel"));
        this.generateAdvancedAccessorsForCollectionsButton
                .setSelection(IUML2JavaConstants.Default.DEFAULT_GENERATE_ADVANCED_ACCESSORS_COLLECTIONS);
        this.generateAdvancedAccessorsForCollectionsButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                update();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.GenerateAdvancedAccessorsForCollectionsHelp"));
    }

    /**
     * Creates the group containing the documentation options of the class generation.
     *
     * @param composite
     *            The composite containing the group
     * @param font
     *            The font used by the parent of the group
     */
    private void createDocumentationGroup(Composite composite, Font font) {
        GridData gd;
        Group documentationGroup = createGroup(composite, UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.DocumentationGroupName"), 3, 1,
                GridData.FILL_HORIZONTAL);
        Composite comp = new Composite(documentationGroup, SWT.NONE);
        GridLayout layout = new GridLayout(3, false);
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        comp.setLayout(layout);
        comp.setFont(font);
        gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 2;
        comp.setLayoutData(gd);

        // Author
        Label authorLabel = new Label(comp, SWT.NONE);
        authorLabel.setText(UML2JavaMessages.getString("UML2JavaClassLaunchConfigurationTab.AuthorLabel"));

        this.authorText = new Text(comp, SWT.SINGLE | SWT.BORDER);
        this.authorText.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.authorText.setLayoutData(gd);
        this.authorText.setText(IUML2JavaConstants.Default.DEFAULT_AUTHOR);
        this.authorText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages.getString("UML2JavaClassLaunchConfigurationTab.AuthorHelp"));

        // Version
        Label versionLabel = new Label(comp, SWT.NONE);
        versionLabel.setText(UML2JavaMessages.getString("UML2JavaClassLaunchConfigurationTab.VersionLabel"));

        this.versionText = new Text(comp, SWT.SINGLE | SWT.BORDER);
        this.versionText.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.versionText.setLayoutData(gd);
        this.versionText.setText(IUML2JavaConstants.Default.DEFAULT_VERSION);
        this.versionText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages.getString("UML2JavaClassLaunchConfigurationTab.VersionHelp"));

        // Copyright and license
        Label copyrightAndLicenseLabel = new Label(comp, SWT.NONE);
        copyrightAndLicenseLabel.setText(UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.CopyrightAndLicenseLabel"));

        this.copyrightAndLicenseText = new Text(comp, SWT.MULTI | SWT.BORDER);
        this.copyrightAndLicenseText.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.copyrightAndLicenseText.setLayoutData(gd);
        this.copyrightAndLicenseText.setText(IUML2JavaConstants.Default.DEFAULT_COPYRIGHT_AND_LICENSE);
        this.copyrightAndLicenseText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaClassLaunchConfigurationTab.CopyrightAndLicenseHelp"));
    }

    /**
     * Update the launch configuration and check potential errors.
     */
    private void update() {
        // nothing yet

        this.getLaunchConfigurationDialog().updateButtons();
        this.getLaunchConfigurationDialog().updateMessage();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
     */
    public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
        // packages to ignore during the generation
        configuration.setAttribute(IUML2JavaConstants.PACKAGES_TO_IGNORE_DURING_GENERATION,
                IUML2JavaConstants.Default.DEFAULT_PACKAGES_TO_IGNORE_DURING_GENERATION);
        if (this.packagesToIgnoreDuringTheGenerationText != null) {
            this.packagesToIgnoreDuringTheGenerationText
                    .setText(IUML2JavaConstants.Default.DEFAULT_PACKAGES_TO_IGNORE_DURING_GENERATION);
        }

        // packages to ignore during the import computation
        configuration.setAttribute(IUML2JavaConstants.PACKAGES_TO_IGNORE_DURING_IMPORTS,
                IUML2JavaConstants.Default.DEFAULT_PACKAGES_TO_IGNORE_DURING_IMPORTS);
        if (this.packagesToIgnoreDuringTheImportText != null) {
            this.packagesToIgnoreDuringTheImportText
                    .setText(IUML2JavaConstants.Default.DEFAULT_PACKAGES_TO_IGNORE_DURING_IMPORTS);
        }

        // generate getters and setters
        configuration.setAttribute(IUML2JavaConstants.GENERATE_GETTERS_AND_SETTERS,
                IUML2JavaConstants.Default.DEFAULT_GENERATE_GETTERS_AND_SETTERS);
        if (this.generateGettersAndSettersButton != null) {
            this.generateGettersAndSettersButton
                    .setSelection(IUML2JavaConstants.Default.DEFAULT_GENERATE_GETTERS_AND_SETTERS);
        }

        // generate getters for collections
        configuration.setAttribute(IUML2JavaConstants.GENERATE_GETTERS_COLLECTIONS,
                IUML2JavaConstants.Default.DEFAULT_GENERATE_GETTERS_COLLECTIONS);
        if (this.generateGettersForCollectionsButton != null) {
            this.generateGettersForCollectionsButton
                    .setSelection(IUML2JavaConstants.Default.DEFAULT_GENERATE_GETTERS_COLLECTIONS);
        }

        // generate setters for collections
        configuration.setAttribute(IUML2JavaConstants.GENERATE_SETTERS_COLLECTIONS,
                IUML2JavaConstants.Default.DEFAULT_GENERATE_SETTERS_COLLECTIONS);
        if (this.generateSettersForCollectionsButton != null) {
            this.generateSettersForCollectionsButton
                    .setSelection(IUML2JavaConstants.Default.DEFAULT_GENERATE_SETTERS_COLLECTIONS);
        }

        // generate advanced accessors for collections
        configuration.setAttribute(IUML2JavaConstants.GENERATE_ADVANCED_ACCESSORS_COLLECTIONS,
                IUML2JavaConstants.Default.DEFAULT_GENERATE_ADVANCED_ACCESSORS_COLLECTIONS);
        if (this.generateAdvancedAccessorsForCollectionsButton != null) {
            this.generateAdvancedAccessorsForCollectionsButton
                    .setSelection(IUML2JavaConstants.Default.DEFAULT_GENERATE_ADVANCED_ACCESSORS_COLLECTIONS);
        }

        // author
        configuration.setAttribute(IUML2JavaConstants.AUTHOR, IUML2JavaConstants.Default.DEFAULT_AUTHOR);
        if (this.authorText != null) {
            this.authorText.setText(IUML2JavaConstants.Default.DEFAULT_AUTHOR);
        }

        // version
        configuration.setAttribute(IUML2JavaConstants.VERSION, IUML2JavaConstants.Default.DEFAULT_VERSION);
        if (this.versionText != null) {
            this.versionText.setText(IUML2JavaConstants.Default.DEFAULT_VERSION);
        }

        // copyright and license
        configuration.setAttribute(IUML2JavaConstants.COPYRIGHT_AND_LICENSE,
                IUML2JavaConstants.Default.DEFAULT_COPYRIGHT_AND_LICENSE);
        if (this.copyrightAndLicenseText != null) {
            this.copyrightAndLicenseText.setText(IUML2JavaConstants.Default.DEFAULT_COPYRIGHT_AND_LICENSE);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
     */
    public void initializeFrom(ILaunchConfiguration configuration) {
        try {
            // Packages to ignore during the generation
            String stringAttribute = configuration.getAttribute(
                    IUML2JavaConstants.PACKAGES_TO_IGNORE_DURING_GENERATION,
                    IUML2JavaConstants.Default.DEFAULT_PACKAGES_TO_IGNORE_DURING_GENERATION);
            this.packagesToIgnoreDuringTheGenerationText.setText(stringAttribute);

            // Packages to ignore during the imports
            stringAttribute = configuration.getAttribute(
                    IUML2JavaConstants.PACKAGES_TO_IGNORE_DURING_IMPORTS,
                    IUML2JavaConstants.Default.DEFAULT_PACKAGES_TO_IGNORE_DURING_IMPORTS);
            this.packagesToIgnoreDuringTheImportText.setText(stringAttribute);

            // Generate getters and setters
            boolean booleanAttribute = configuration.getAttribute(
                    IUML2JavaConstants.GENERATE_GETTERS_AND_SETTERS,
                    IUML2JavaConstants.Default.DEFAULT_GENERATE_GETTERS_AND_SETTERS);
            this.generateGettersAndSettersButton.setSelection(booleanAttribute);

            // Generate getters for collections
            booleanAttribute = configuration.getAttribute(IUML2JavaConstants.GENERATE_GETTERS_COLLECTIONS,
                    IUML2JavaConstants.Default.DEFAULT_GENERATE_GETTERS_COLLECTIONS);
            this.generateGettersForCollectionsButton.setSelection(booleanAttribute);

            // Generate setters for collections
            booleanAttribute = configuration.getAttribute(IUML2JavaConstants.GENERATE_SETTERS_COLLECTIONS,
                    IUML2JavaConstants.Default.DEFAULT_GENERATE_SETTERS_COLLECTIONS);
            this.generateSettersForCollectionsButton.setSelection(booleanAttribute);

            // Generate advanced accessors for collections
            booleanAttribute = configuration.getAttribute(
                    IUML2JavaConstants.GENERATE_ADVANCED_ACCESSORS_COLLECTIONS,
                    IUML2JavaConstants.Default.DEFAULT_GENERATE_ADVANCED_ACCESSORS_COLLECTIONS);
            this.generateAdvancedAccessorsForCollectionsButton.setSelection(booleanAttribute);

            // Author
            stringAttribute = configuration.getAttribute(IUML2JavaConstants.AUTHOR,
                    IUML2JavaConstants.Default.DEFAULT_AUTHOR);
            this.authorText.setText(stringAttribute);

            // Version
            stringAttribute = configuration.getAttribute(IUML2JavaConstants.VERSION,
                    IUML2JavaConstants.Default.DEFAULT_VERSION);
            this.versionText.setText(stringAttribute);

            // Copyright
            stringAttribute = configuration.getAttribute(IUML2JavaConstants.COPYRIGHT_AND_LICENSE,
                    IUML2JavaConstants.Default.DEFAULT_COPYRIGHT_AND_LICENSE);
            this.copyrightAndLicenseText.setText(stringAttribute);

        } catch (CoreException e) {
            IStatus status = new Status(IStatus.ERROR, UML2JavaUIActivator.PLUGIN_ID, e.getMessage(), e);
            UML2JavaUIActivator.getDefault().getLog().log(status);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
     */
    public void performApply(ILaunchConfigurationWorkingCopy configuration) {
        // Packages to ignore during the generation
        String packagesToIgnoreDuringTheGeneration = this.packagesToIgnoreDuringTheGenerationText.getText();
        configuration.setAttribute(IUML2JavaConstants.PACKAGES_TO_IGNORE_DURING_GENERATION,
                packagesToIgnoreDuringTheGeneration);

        // Packages to ignore during the imports
        String packagesToIgnoreDuringTheImports = this.packagesToIgnoreDuringTheImportText.getText();
        configuration.setAttribute(IUML2JavaConstants.PACKAGES_TO_IGNORE_DURING_IMPORTS,
                packagesToIgnoreDuringTheImports);

        // Generate getters and setters
        boolean generateGettersAndSetters = this.generateGettersAndSettersButton.getSelection();
        configuration
                .setAttribute(IUML2JavaConstants.GENERATE_GETTERS_AND_SETTERS, generateGettersAndSetters);

        // Generate getters for collections
        boolean generateGettersForCollections = this.generateGettersForCollectionsButton.getSelection();
        configuration.setAttribute(IUML2JavaConstants.GENERATE_GETTERS_COLLECTIONS,
                generateGettersForCollections);

        // Generate setters for collections
        boolean generateSettersForCollections = this.generateSettersForCollectionsButton.getSelection();
        configuration.setAttribute(IUML2JavaConstants.GENERATE_SETTERS_COLLECTIONS,
                generateSettersForCollections);

        // Generate advanced accessors for collections.
        boolean generateAdvancedAccessorsForCollections = this.generateAdvancedAccessorsForCollectionsButton
                .getSelection();
        configuration.setAttribute(IUML2JavaConstants.GENERATE_ADVANCED_ACCESSORS_COLLECTIONS,
                generateAdvancedAccessorsForCollections);

        // Author
        String author = this.authorText.getText();
        configuration.setAttribute(IUML2JavaConstants.AUTHOR, author);

        // Version
        String version = this.versionText.getText();
        configuration.setAttribute(IUML2JavaConstants.VERSION, version);

        // Copyright and license
        String copyrightAndLicense = this.copyrightAndLicenseText.getText();
        configuration.setAttribute(IUML2JavaConstants.COPYRIGHT_AND_LICENSE, copyrightAndLicense);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
     */
    @Override
    public boolean isValid(ILaunchConfiguration launchConfig) {
        return super.isValid(launchConfig);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
     */
    public String getName() {
        return UML2JavaMessages.getString("UML2JavaClassLaunchConfigurationTab.Name");
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#getImage()
     */
    @Override
    public Image getImage() {
        return UML2JavaUIActivator.getDefault().getImage("icons/class_obj.gif");
    }

}
