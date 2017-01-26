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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.umlgen.gen.java.ui.UML2JavaUIActivator;
import org.eclipse.umlgen.gen.java.ui.utils.UML2JavaMessages;
import org.eclipse.umlgen.gen.java.utils.IUML2JavaConstants;

/**
 * The component tab of the launch configuration.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 2.0
 */
public class UML2JavaComponentLaunchConfigurationTab extends AbstractUML2JavaLaunchConfigurationTab {

    /**
     * The comma-separated list of the components to ignore during the generation.
     */
    private Text componentsToIgnoreText;

    /**
     * The type of component to generate.
     */
    private Combo componentArtifactsCombo;

    /**
     * The name of the provider of the bundle.
     */
    private Text bundleProviderNameText;

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
        this.createDocumentationGroup(composite, font);

        this.setControl(composite);
        this.update();
    }

    /**
     * Creates the content of the generation group.
     *
     * @param composite
     *            The composite in which the generation group will be created.
     * @param font
     *            The font of the composite parent
     */
    private void createGenerationGroup(Composite composite, Font font) {
        GridData gd;
        Group generationGroup = createGroup(composite, UML2JavaMessages
                .getString("UML2JavaComponentLaunchConfigurationTab.GenerationGroupName"), 3, 1,
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

        // Components to ignore during the generation
        Label componentsToIgnoreLabel = new Label(comp, SWT.NONE);
        componentsToIgnoreLabel.setText(UML2JavaMessages
                .getString("UML2JavaComponentLaunchConfigurationTab.ComponentToIgnoreLabel"));

        this.componentsToIgnoreText = new Text(comp, SWT.SINGLE | SWT.BORDER);
        this.componentsToIgnoreText.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.componentsToIgnoreText.setLayoutData(gd);
        this.componentsToIgnoreText.setText(IUML2JavaConstants.Default.DEFAULT_COMPONENTS_TO_IGNORE);
        this.componentsToIgnoreText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaComponentLaunchConfigurationTab.ComponentToIgnoreHelp"));

        // Support component artifacts
        Label generateOSGiArtifactsLabel = new Label(comp, SWT.NONE);
        generateOSGiArtifactsLabel.setText(UML2JavaMessages
                .getString("UML2JavaComponentLaunchConfigurationTab.GenerateComponentArtifactsLabel"));

        componentArtifactsCombo = new Combo(comp, SWT.READ_ONLY);
        componentArtifactsCombo.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        componentArtifactsCombo.setItems(new String[] {
        /* IUML2JavaConstants.Default.DEFAULT_COMPONENT_ARTIFACTS_TYPE_OSGI, */
        IUML2JavaConstants.Default.DEFAULT_COMPONENT_ARTIFACTS_TYPE_ECLIPSE,
                IUML2JavaConstants.Default.DEFAULT_COMPONENT_ARTIFACTS_TYPE_JAVA, });
        componentArtifactsCombo.select(0);
        componentArtifactsCombo.addSelectionListener(new SelectionListener() {

            public void widgetSelected(SelectionEvent e) {
                update();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaComponentLaunchConfigurationTab.GenerateComponentArtifactsHelp"));

    }

    /**
     * Creates the content of the documentation group.
     *
     * @param composite
     *            The composite in which the documentation group will be created.
     * @param font
     *            The font of the composite parent
     */
    private void createDocumentationGroup(Composite composite, Font font) {
        GridData gd;
        Group generationGroup = createGroup(composite, UML2JavaMessages
                .getString("UML2JavaComponentLaunchConfigurationTab.DocumentationGroupName"), 3, 1,
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

        // Bundle provider
        Label bundleProviderLabel = new Label(comp, SWT.NONE);
        bundleProviderLabel.setText(UML2JavaMessages
                .getString("UML2JavaComponentLaunchConfigurationTab.BundleProviderLabel"));

        this.bundleProviderNameText = new Text(comp, SWT.SINGLE | SWT.BORDER);
        this.bundleProviderNameText.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.bundleProviderNameText.setLayoutData(gd);
        this.bundleProviderNameText.setText(IUML2JavaConstants.Default.DEFAULT_BUNDLE_PROVIDER_NAME);
        this.bundleProviderNameText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaComponentLaunchConfigurationTab.BundleProviderHelp"));
    }

    /**
     * Update the launch configuration and check potential errors.
     */
    private void update() {
        // do nothing

        this.getLaunchConfigurationDialog().updateButtons();
        this.getLaunchConfigurationDialog().updateMessage();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
     */
    public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
        // Components to ignore
        configuration.setAttribute(IUML2JavaConstants.COMPONENTS_TO_IGNORE,
                IUML2JavaConstants.Default.DEFAULT_COMPONENTS_TO_IGNORE);
        if (this.componentsToIgnoreText != null) {
            this.componentsToIgnoreText.setText(IUML2JavaConstants.Default.DEFAULT_COMPONENTS_TO_IGNORE);
        }

        // Component architecture kind
        configuration.setAttribute(IUML2JavaConstants.COMPONENTS_ARCHITECTURE,
                IUML2JavaConstants.Default.DEFAULT_COMPONENT_ARTIFACTS_TYPE_ECLIPSE);
        if (this.componentArtifactsCombo != null) {
            this.componentArtifactsCombo
                    .setText(IUML2JavaConstants.Default.DEFAULT_COMPONENT_ARTIFACTS_TYPE_ECLIPSE);
        }

        // Bundle provider
        configuration.setAttribute(IUML2JavaConstants.BUNDLE_PROVIDER,
                IUML2JavaConstants.Default.DEFAULT_BUNDLE_PROVIDER_NAME);
        if (this.bundleProviderNameText != null) {
            this.bundleProviderNameText.setText(IUML2JavaConstants.Default.DEFAULT_BUNDLE_PROVIDER_NAME);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
     */
    public void initializeFrom(ILaunchConfiguration configuration) {
        try {
            // Components to ignore
            String attribute = configuration.getAttribute(IUML2JavaConstants.COMPONENTS_TO_IGNORE, "");
            this.componentsToIgnoreText.setText(attribute);

            // Components architecture kind
            attribute = configuration.getAttribute(IUML2JavaConstants.COMPONENTS_ARCHITECTURE,
                    IUML2JavaConstants.Default.DEFAULT_COMPONENT_ARTIFACTS_TYPE_ECLIPSE);
            this.componentArtifactsCombo.setText(attribute);

            // Bundle provider
            attribute = configuration.getAttribute(IUML2JavaConstants.BUNDLE_PROVIDER,
                    IUML2JavaConstants.Default.DEFAULT_BUNDLE_PROVIDER_NAME);
            this.bundleProviderNameText.setText(attribute);

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
        // Components to ignore
        String componentsToIgnore = this.componentsToIgnoreText.getText();
        configuration.setAttribute(IUML2JavaConstants.COMPONENTS_TO_IGNORE, componentsToIgnore);

        // Component artifacts kind
        String componentArtifactsKind = this.componentArtifactsCombo.getText();
        configuration.setAttribute(IUML2JavaConstants.COMPONENTS_ARCHITECTURE, componentArtifactsKind);

        // Bundle provider
        String bundleProvider = this.bundleProviderNameText.getText();
        configuration.setAttribute(IUML2JavaConstants.BUNDLE_PROVIDER, bundleProvider);
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
        return UML2JavaMessages.getString("UML2JavaComponentLaunchConfigurationTab.Name");
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#getImage()
     */
    @Override
    public Image getImage() {
        return UML2JavaUIActivator.getDefault().getImage("icons/component_obj.gif");
    }

}
