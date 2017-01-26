/*******************************************************************************
 * Copyright (c) 2015 CNES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cedric Notot (Obeo) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.gen.java.ui.properties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.internal.ui.launchConfigurations.LaunchConfigurationTabGroupViewer;
import org.eclipse.debug.internal.ui.launchConfigurations.LaunchConfigurationsDialog;
import org.eclipse.debug.internal.ui.launchConfigurations.LaunchConfigurationsMessages;
import org.eclipse.debug.internal.ui.launchConfigurations.LaunchGroupExtension;
import org.eclipse.debug.ui.ILaunchGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.umlgen.gen.java.ui.common.ConfigurationServices;
import org.eclipse.umlgen.gen.java.ui.launch.IUML2JavaUIConstants;
import org.eclipse.umlgen.gen.java.utils.IUML2JavaConstants;

/**
 * Properties page to select the launch configuration for the Java generation.
 */
public class LaunchConfigurationPropertiesPage extends PropertyPage {

    /** The combo to select a launch configuration. */
    private LaunchConfigurationsCombo combo;

    /** The viewer to edit a launch configuration for Java generation. */
    private CustomLaunchConfigurationTabGroupViewer viewer;

    /**
     * Constructor.
     */
    public LaunchConfigurationPropertiesPage() {
        noDefaultAndApplyButton();
    }

    @Override
    protected Control createContents(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);
        GridData data = new GridData();
        composite.setLayoutData(data);

        // Create the combo to choose the launch configuration to edit and to select for the Java generation.
        combo = new LaunchConfigurationsCombo(composite, SWT.READ_ONLY);

        String configNameToSelect = ConfigurationServices.getConfigurationProperty((IResource)getElement());
        List<ILaunchConfiguration> configs = ConfigurationServices
                .getStoredJavaGenerationLaunchConfigurations((IResource)getElement());

        if (configs.isEmpty() || configNameToSelect == null) {
            Label isStoredLabel = new Label(composite, SWT.NONE);
            isStoredLabel.setForeground(new org.eclipse.swt.graphics.Color(composite.getDisplay(), new RGB(
                    255, 0, 0)));
            isStoredLabel.setText(
                    "No favourite configuration for this model. Click on OK to choose the selected one.");
        }

        // Create the viewer from the launch configurations.
        viewer = createViewer(composite);

        if (viewer != null) {

            combo.setLaunchConfigurations(configs);
            combo.setViewer(viewer);

            if ((configNameToSelect == null || configNameToSelect.compareTo("") == 0 || !combo.contains(
                    configNameToSelect)) && !combo.isEmpty()) {
                configNameToSelect = combo.getItem(0);
            } else if (combo.isEmpty()) {
                try {
                    ILaunchConfigurationWorkingCopy newLaunchConfig = createLaunchConfigurationWorkingCopy();
                    combo.add(newLaunchConfig);
                    configNameToSelect = newLaunchConfig.getName();
                    newLaunchConfig.setAttribute(IUML2JavaConstants.UML_MODEL_PATH, ((IResource)getElement())
                            .getFullPath().toString());
                } catch (CoreException e) {
                    e.printStackTrace();
                }
            }
            combo.select(configNameToSelect);
        }
        return composite;
    }

    @Override
    public boolean performOk() {
        try {
            if (combo.getSelectedLaunchConfiguration() instanceof ILaunchConfigurationWorkingCopy
                    && ((ILaunchConfigurationWorkingCopy)combo.getSelectedLaunchConfiguration())
                            .getOriginal() == null) {
                // Case where a new launch configuration working copy has been created (no launch
                // configuration stored).
                ((ILaunchConfigurationWorkingCopy)combo.getSelectedLaunchConfiguration()).doSave();
            } else if (viewer != null) {
                // Save the changes in the related stored launch configuration.
                viewer.doApply();
            }

            ConfigurationServices.saveConfigurationProperty((IResource)getElement(), combo
                    .getSelectedLaunchConfiguration().getName());

        } catch (CoreException e) {
            return false;
        }
        return true;
    }

    /**
     * Create the viewer in the properties page.
     *
     * @param parent
     *            The parent composite.
     * @return The viewer.
     */
    private CustomLaunchConfigurationTabGroupViewer createViewer(Composite parent) {
        ILaunchGroup launchGroup = ConfigurationServices.getLaunchGroup();
        if (launchGroup instanceof LaunchGroupExtension) {
            // We want just to instantiate the dialog for the viewer needs.
            CustomLaunchConfigurationsDialog dialog = new CustomLaunchConfigurationsDialog(getShell(),
                    (LaunchGroupExtension)launchGroup);
            return new CustomLaunchConfigurationTabGroupViewer(parent, dialog);
        }
        return null;
    }

    /**
     * Create a working copy of launch configuration for Java generation.
     *
     * @return The working copy.
     * @throws CoreException
     *             exception.
     */
    private ILaunchConfigurationWorkingCopy createLaunchConfigurationWorkingCopy() throws CoreException {

        // Kind of launch configurations for Java generation.
        ILaunchConfigurationType type = DebugPlugin.getDefault().getLaunchManager()
                .getLaunchConfigurationType(IUML2JavaUIConstants.LAUNCH_CONFIGURATION_TYPE);

        return type.newInstance(null, DebugPlugin.getDefault().getLaunchManager()
                .generateLaunchConfigurationName(
                        LaunchConfigurationsMessages.CreateLaunchConfigurationAction_New_configuration_2));

    }

    /**
     * A combo which displays the name of launch configurations and which is attached to a launch
     * configuration viewer.
     */
    private class LaunchConfigurationsCombo extends Combo {

        /** Launch configuration name (id) to the related launch configuration itself. */
        private Map<String, ILaunchConfiguration> mConfigsRegistry = new HashMap<String, ILaunchConfiguration>();

        /** The selected launch configuration. */
        private ILaunchConfiguration mSelectedConfig;

        /** The attached viewer. */
        private CustomLaunchConfigurationTabGroupViewer mViewer;

        /** The selection listener. */
        private SelectionListener selectionListener;

        /**
         * Constructor.
         *
         * @param parent
         *            The parent composite.
         * @param style
         *            Style of the Combo.
         */
        public LaunchConfigurationsCombo(Composite parent, int style) {
            super(parent, style);
        }

        /**
         * Fill the combo with the name of the launch configurations.
         *
         * @param configs
         *            The launch configurations.
         */
        public void setLaunchConfigurations(List<ILaunchConfiguration> configs) {
            for (ILaunchConfiguration config : configs) {
                add(config);
            }
        }

        @Override
        protected void checkSubclass() {
        }

        /**
         * Adds the argument to the end of the receiver's list.
         *
         * @param configuration
         *            The configuration to add.
         */
        public void add(ILaunchConfiguration configuration) {
            add(configuration.getName());
            mConfigsRegistry.put(configuration.getName(), configuration);
        }

        /**
         * This attaches the given viewer to the combo.
         *
         * @param viewer
         *            The viewer.
         */
        public void setViewer(final CustomLaunchConfigurationTabGroupViewer viewer) {
            mViewer = viewer;
            if (selectionListener == null) {
                selectionListener = new SelectionListener() {

                    public void widgetSelected(SelectionEvent e) {
                        String selection = getText();
                        ILaunchConfiguration selectedConfig = mConfigsRegistry.get(selection);

                        // Notify the viewer about the new selection.
                        mViewer.doInputChanged(selectedConfig);

                        mSelectedConfig = selectedConfig;
                    }

                    public void widgetDefaultSelected(SelectionEvent e) {
                        // Do nothing.
                    }
                };
                addSelectionListener(selectionListener);
            }
        }

        /**
         * Select the given item.
         *
         * @param text
         *            The text item.
         */
        public void select(String text) {
            ILaunchConfiguration configToSelect = mConfigsRegistry.get(text);
            if (configToSelect != null && configToSelect != mSelectedConfig) {
                select(configToSelect);
            }
        }

        /**
         * Select the given launch configuration.
         *
         * @param configuration
         *            The launch configuration.
         */
        public void select(ILaunchConfiguration configuration) {
            if (!configuration.getName().equals(getText())) {
                setText(configuration.getName());
            }
            mConfigsRegistry.put(configuration.getName(), configuration);
            mSelectedConfig = configuration;
            mViewer.doInputChanged(configuration);
        }

        /**
         * Update the current item with the given launch configuration.
         *
         * @param configuration
         *            The new launch configuration.
         */
        public void updateCurrentItem(ILaunchConfiguration configuration) {
            mConfigsRegistry.remove(getText());
            mConfigsRegistry.put(configuration.getName(), configuration);
            mSelectedConfig = configuration;
            int indexToUpdate = indexOf(getText());
            setItem(indexToUpdate, configuration.getName());
            setText(configuration.getName());
            pack(true);
        }

        public ILaunchConfiguration getSelectedLaunchConfiguration() {
            return mSelectedConfig;
        }

        /**
         * Check if the combo contains the given text item?
         *
         * @param text
         *            The text item.
         * @return True if the combo contains it.
         */
        public boolean contains(String text) {
            return mConfigsRegistry.containsKey(text);
        }

        public boolean isEmpty() {
            return mConfigsRegistry.isEmpty();
        }

    }

    /**
     * The launch configuration dialog required by the launch configuration tab group viewer.
     */
    private class CustomLaunchConfigurationsDialog extends LaunchConfigurationsDialog {

        /**
         * Constructor.
         *
         * @param shell
         *            Shell
         * @param group
         *            LaunchGroupExtension
         */
        public CustomLaunchConfigurationsDialog(Shell shell, LaunchGroupExtension group) {
            super(shell, group);
        }

        @Override
        public boolean isTreeSelectionEmpty() {
            // No Launch configuration dialog so no tree to manage. We consider that it is always empty.
            return true;
        }

        @Override
        public void updateButtons() {
            // Refresh only the buttons from the viewer.
            getTabViewer().refresh();
        }

        @Override
        public void updateMessage() {
            // No Launch configuration dialog so no messages to display from it.
        }

        /**
         * This enables to set the Tab Group viewer on this dialog.
         *
         * @param viewer
         *            The Tab Group viewer.
         */
        public void setViewer(LaunchConfigurationTabGroupViewer viewer) {
            setTabViewer(viewer);
        }

    }

    /**
     * The launch configuration tab group viewer fit to be used in the context of this properties page.
     */
    private class CustomLaunchConfigurationTabGroupViewer extends LaunchConfigurationTabGroupViewer {

        /** Tool tip text for the revert button. */
        private static final String MSG_REVERT_BUTTON = "Revert the last changes";

        /** Tool tip text for the apply button. */
        private static final String MSG_APPLY_BUTTON = "Save the changes in the related launch configuration";

        /**
         * Constructor.
         *
         * @param parent
         *            Composite.
         * @param dialog
         *            The launch configuration dialog.
         */
        public CustomLaunchConfigurationTabGroupViewer(Composite parent,
                CustomLaunchConfigurationsDialog dialog) {
            super(parent, dialog);
            dialog.setViewer(this);
            this.getApplyButton().setToolTipText(MSG_APPLY_BUTTON);
            this.getRevertButton().setToolTipText(MSG_REVERT_BUTTON);
        }

        /**
         * This calls {@link LaunchConfigurationTabGroupViewer#inputChanged(Object)}.
         *
         * @param config
         *            The launch configuration.
         */
        public void doInputChanged(ILaunchConfiguration config) {
            inputChanged(config);
        }

        /**
         * This calls {@link LaunchConfigurationTabGroupViewer#handleApplyPressed()}.
         */
        public void doApply() {
            handleApplyPressed();
        }

        @Override
        protected ILaunchConfiguration handleApplyPressed() {
            ILaunchConfiguration launchConfig = super.handleApplyPressed();

            if (launchConfig != null && !combo.getText().equals(launchConfig.getName())) {
                // Renaming of the current launch configuration case

                // A new launch configuration is created and the original one is deleted.
                // We have to call inputChanged() to define the new original launch configuration.
                // -> the storedLaunchConfig will be considered as the new original one.
                ILaunchConfiguration storedLaunchConfig = ConfigurationServices.getStoredLaunchConfiguration(
                        launchConfig.getName());
                combo.select(storedLaunchConfig);

                // -> update of the combo
                combo.updateCurrentItem(storedLaunchConfig);

                launchConfig = storedLaunchConfig;
            }

            return launchConfig;

        }

    }

}
