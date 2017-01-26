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
package org.eclipse.umlgen.gen.java.ui.launch.tabs;

import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMInstall2;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.environments.IExecutionEnvironment;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.util.Policy;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;
import org.eclipse.umlgen.gen.java.ui.UML2JavaUIActivator;
import org.eclipse.umlgen.gen.java.ui.utils.UML2JavaMessages;
import org.eclipse.umlgen.gen.java.utils.IUML2JavaConstants;

/**
 * The UML to Java launch configuration tab.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 1.0
 */
public class UML2JavaGeneralLaunchConfigurationTab extends AbstractUML2JavaLaunchConfigurationTab {

    /** The last selected EE JRE settings key. */
    private static final String LAST_SELECTED_EE_SETTINGS_KEY = JavaUI.ID_PLUGIN
            + ".last.selected.execution.enviroment"; //$NON-NLS-1$

    /**
     * The workspace relative path of the input model.
     */
    private Text modelPathText;

    /**
     * The name of the default project.
     */
    private Text defaultProjectNameText;

    /**
     * The path of the input folder in a Java project.
     */
    private Text sourceFolderPathText;

    /**
     * The path of the output folder in a Java project.
     */
    private Text outputFolderPathText;

    /**
     * The available JRE execution environment.
     */
    private Combo executionEnvJRECombo;

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
        this.createJavaGroup(composite, font);

        this.setControl(composite);
        this.update();
    }

    /**
     * Creates the group containing the general options of the generation.
     * 
     * @param composite
     *            The composite containing the group
     * @param font
     *            the font used by the parent of the group
     */
    private void createGenerationGroup(Composite composite, Font font) {
        GridData gd;
        Group generationGroup = createGroup(composite, UML2JavaMessages
                .getString("UML2JavaGeneralLaunchConfigurationTab.GenerationGroupName"), 3, 1,
                GridData.FILL_HORIZONTAL);
        Composite comp = new Composite(generationGroup, SWT.NONE);
        GridLayout layout = new GridLayout(4, false);
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        comp.setLayout(layout);
        comp.setFont(font);
        gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 2;
        comp.setLayoutData(gd);

        Label modelPathLabel = new Label(comp, SWT.NONE);
        modelPathLabel.setText(UML2JavaMessages
                .getString("UML2JavaGeneralLaunchConfigurationTab.UMLModelPathLabel"));

        this.modelPathText = new Text(comp, SWT.SINGLE | SWT.BORDER);
        this.modelPathText.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.modelPathText.setLayoutData(gd);
        this.modelPathText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                update();
            }
        });

        final Button browseModelButton = createPushButton(comp, UML2JavaMessages
                .getString("UML2JavaGeneralLaunchConfigurationTab.BrowseButtonName"), null);
        browseModelButton.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
            }

            public void widgetSelected(SelectionEvent e) {
                FilteredResourcesSelectionDialog dialog = new FilteredResourcesSelectionDialog(getShell(),
                        false, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
                dialog.setTitle(UML2JavaMessages
                        .getString("UML2JavaGeneralLaunchConfigurationTab.UMLModelDialogTitle"));
                dialog.setInitialPattern("*.uml");
                dialog.open();
                if (dialog.getResult() != null && dialog.getResult().length > 0) {
                    Object[] results = dialog.getResult();
                    for (Object result : results) {
                        if (result instanceof IFile) {
                            modelPathText.setText(((IFile)result).getFullPath().toString());
                            break;
                        }
                    }
                }
                update();
                updateLaunchConfigurationDialog();
            }
        });
        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaGeneralLaunchConfigurationTab.UMLModelPathHelp"));
    }

    /**
     * Creates the group containing the Java options of the generation.
     * 
     * @param composite
     *            the composite containing the group
     * @param font
     *            The font used by the parent of the group
     */
    private void createJavaGroup(Composite composite, Font font) {
        GridData gd;
        Group javaGroup = createGroup(composite, UML2JavaMessages
                .getString("UML2JavaGeneralLaunchConfigurationTab.JavaGroupName"), 3, 1,
                GridData.FILL_HORIZONTAL);
        Composite comp = new Composite(javaGroup, SWT.NONE);
        GridLayout layout = new GridLayout(3, false);
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        comp.setLayout(layout);
        comp.setFont(font);
        gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 2;
        comp.setLayoutData(gd);

        // Default project name
        Label defaultProjectNameLabel = new Label(comp, SWT.NONE);
        defaultProjectNameLabel.setText(UML2JavaMessages
                .getString("UML2JavaGeneralLaunchConfigurationTab.DefaultProjectNameLabel"));

        this.defaultProjectNameText = new Text(comp, SWT.SINGLE | SWT.BORDER);
        this.defaultProjectNameText.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.defaultProjectNameText.setLayoutData(gd);
        this.defaultProjectNameText.setText(IUML2JavaConstants.Default.DEFAULT_DEFAULT_PROJECT_NAME);
        this.defaultProjectNameText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaGeneralLaunchConfigurationTab.DefaultProjectNameHelp"));

        // Source folder path
        Label sourceFolderPathLabel = new Label(comp, SWT.NONE);
        sourceFolderPathLabel.setText(UML2JavaMessages
                .getString("UML2JavaGeneralLaunchConfigurationTab.SourceFolderPathLabel"));

        this.sourceFolderPathText = new Text(comp, SWT.SINGLE | SWT.BORDER);
        this.sourceFolderPathText.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.sourceFolderPathText.setLayoutData(gd);
        this.sourceFolderPathText.setText(IUML2JavaConstants.Default.DEFAULT_SOURCE_FOLDER_PATH);
        this.sourceFolderPathText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaGeneralLaunchConfigurationTab.SourceFolderPathHelp"));

        // Output folder path
        Label outputFolderPathLabel = new Label(comp, SWT.NONE);
        outputFolderPathLabel.setText(UML2JavaMessages
                .getString("UML2JavaGeneralLaunchConfigurationTab.OutputFolderPathLabel"));

        this.outputFolderPathText = new Text(comp, SWT.SINGLE | SWT.BORDER);
        this.outputFolderPathText.setFont(composite.getFont());
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        this.outputFolderPathText.setLayoutData(gd);
        this.outputFolderPathText.setText(IUML2JavaConstants.Default.DEFAULT_OUTPUT_FOLDER_PATH);
        this.outputFolderPathText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaGeneralLaunchConfigurationTab.OutputFolderPathHelp"));

        // JRE Selection
        Label jreExecutionEnvironmentLabel = new Label(comp, SWT.NONE);
        jreExecutionEnvironmentLabel.setText(UML2JavaMessages
                .getString("UML2JavaGeneralLaunchConfigurationTab.JREExecutionEnvironmentLabel"));

        executionEnvJRECombo = new Combo(comp, SWT.READ_ONLY);
        executionEnvJRECombo.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

        String selectedItem = this.getLastSelectedEE();
        int selectionIndex = executionEnvJRECombo.getSelectionIndex();

        IExecutionEnvironment[] fInstalledEEs = JavaRuntime.getExecutionEnvironmentsManager()
                .getExecutionEnvironments();
        Arrays.sort(fInstalledEEs, new Comparator<IExecutionEnvironment>() {
            @SuppressWarnings("unchecked")
            public int compare(IExecutionEnvironment arg0, IExecutionEnvironment arg1) {
                return Policy.getComparator().compare(arg0.getId(), arg1.getId());
            }
        });

        // find new index
        selectionIndex = -1;
        String[] eeLabels = new String[fInstalledEEs.length];
        String[] fEECompliance = new String[fInstalledEEs.length];
        for (int i = 0; i < fInstalledEEs.length; i++) {
            eeLabels[i] = fInstalledEEs[i].getId();
            if (selectedItem != null && eeLabels[i].equals(selectedItem)) {
                selectionIndex = i;
            }
            fEECompliance[i] = JavaModelUtil.getExecutionEnvironmentCompliance(fInstalledEEs[i]);
        }
        executionEnvJRECombo.setItems(eeLabels);
        if (selectionIndex == -1) {
            executionEnvJRECombo.setText(getDefaultEEName());
        } else {
            executionEnvJRECombo.setText(selectedItem);
        }

        executionEnvJRECombo.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                update();
            }
        });

        executionEnvJRECombo.addSelectionListener(new SelectionListener() {

            public void widgetSelected(SelectionEvent e) {
                update();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                update();
            }
        });

        createHelpButton(comp, UML2JavaMessages
                .getString("UML2JavaGeneralLaunchConfigurationTab.JREExecutionEnvironmentHelp"));
    }

    /**
     * Returns the last selected EE JRE.
     * 
     * @return The last selected EE JRE.
     */
    private String getLastSelectedEE() {
        IDialogSettings settings = JavaPlugin.getDefault().getDialogSettings();
        return settings.get(LAST_SELECTED_EE_SETTINGS_KEY);
    }

    /**
     * Returns the default execution environment name.
     * 
     * @return The default execution environment name.
     */
    private String getDefaultEEName() {
        IVMInstall defaultVM = JavaRuntime.getDefaultVMInstall();

        IExecutionEnvironment[] environments = JavaRuntime.getExecutionEnvironmentsManager()
                .getExecutionEnvironments();
        if (defaultVM != null) {
            for (IExecutionEnvironment environment : environments) {
                IVMInstall eeDefaultVM = environment.getDefaultVM();
                if (eeDefaultVM != null && defaultVM.getId().equals(eeDefaultVM.getId())) {
                    return environment.getId();
                }
            }
        }

        String defaultCC;
        if (defaultVM instanceof IVMInstall2) {
            defaultCC = JavaModelUtil.getCompilerCompliance((IVMInstall2)defaultVM, JavaCore.VERSION_1_4);
        } else {
            defaultCC = JavaCore.VERSION_1_4;
        }

        String result = "JavaSE-1.7"; //$NON-NLS-1$
        for (IExecutionEnvironment environment : environments) {
            String eeCompliance = JavaModelUtil.getExecutionEnvironmentCompliance(environment);
            if (defaultCC.endsWith(eeCompliance)) {
                result = environment.getId();
                break;
            }
        }

        return result;
    }

    /**
     * Checks potential errors.
     */
    private void update() {
        this.setErrorMessage(null);

        this.getLaunchConfigurationDialog().updateButtons();
        this.getLaunchConfigurationDialog().updateMessage();

        // Check the path of the model
        if (this.modelPathText != null) {
            String text = this.modelPathText.getText();
            if (text != null && text.length() > 0) {
                IFile model = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(text));
                if (model != null && !model.exists()) {
                    this.setErrorMessage(UML2JavaMessages
                            .getString("UML2JavaGeneralLaunchConfigurationTab.MissingInputModel"));
                }
            }
        }

        // Check the input / output folder path's length
        if (this.sourceFolderPathText != null && this.sourceFolderPathText.getText() != null
                && this.sourceFolderPathText.getText().trim().length() == 0) {
            this.setErrorMessage(UML2JavaMessages
                    .getString("UML2JavaGeneralLaunchConfigurationTab.MissingSourceFolderPath"));
        } else if (this.outputFolderPathText != null && this.outputFolderPathText.getText() != null
                && this.outputFolderPathText.getText().trim().length() == 0) {
            this.setErrorMessage(UML2JavaMessages
                    .getString("UML2JavaGeneralLaunchConfigurationTab.MissingOutputFolderPath"));
        } else if (this.defaultProjectNameText != null && this.defaultProjectNameText.getText() != null
                && this.defaultProjectNameText.getText().trim().length() == 0) {
            this.setErrorMessage(UML2JavaMessages
                    .getString("UML2JavaGeneralLaunchConfigurationTab.MissingDefaultProjectName"));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
     */
    public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
        // model path
        configuration.setAttribute(IUML2JavaConstants.UML_MODEL_PATH, "");
        if (this.modelPathText != null) {
            this.modelPathText.setText("");
        }

        // default project name
        configuration.setAttribute(IUML2JavaConstants.DEFAULT_PROJECT_NAME,
                IUML2JavaConstants.Default.DEFAULT_DEFAULT_PROJECT_NAME);
        if (this.defaultProjectNameText != null) {
            this.defaultProjectNameText.setText(IUML2JavaConstants.Default.DEFAULT_DEFAULT_PROJECT_NAME);
        }

        // source folder path
        configuration.setAttribute(IUML2JavaConstants.SOURCE_FOLDER_PATH,
                IUML2JavaConstants.Default.DEFAULT_SOURCE_FOLDER_PATH);
        if (this.sourceFolderPathText != null) {
            this.sourceFolderPathText.setText(IUML2JavaConstants.Default.DEFAULT_SOURCE_FOLDER_PATH);
        }

        // output folder path
        configuration.setAttribute(IUML2JavaConstants.OUTPUT_FOLDER_PATH,
                IUML2JavaConstants.Default.DEFAULT_OUTPUT_FOLDER_PATH);
        if (this.outputFolderPathText != null) {
            this.outputFolderPathText.setText(IUML2JavaConstants.Default.DEFAULT_OUTPUT_FOLDER_PATH);
        }

        // JRE execution environment
        configuration.setAttribute(IUML2JavaConstants.JRE_EXECUTION_ENVIRONMENT,
                IUML2JavaConstants.Default.DEFAULT_JRE_EXECUTION_ENVIRONMENT);
        if (this.executionEnvJRECombo != null) {
            this.executionEnvJRECombo.setText(IUML2JavaConstants.Default.DEFAULT_JRE_EXECUTION_ENVIRONMENT);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
     */
    public void initializeFrom(ILaunchConfiguration configuration) {
        try {
            // Model path
            String attribute = configuration.getAttribute(IUML2JavaConstants.UML_MODEL_PATH, "");
            this.modelPathText.setText(attribute);

            // Source folder path
            attribute = configuration.getAttribute(IUML2JavaConstants.SOURCE_FOLDER_PATH,
                    IUML2JavaConstants.Default.DEFAULT_SOURCE_FOLDER_PATH);
            this.sourceFolderPathText.setText(attribute);

            // Output folder path
            attribute = configuration.getAttribute(IUML2JavaConstants.OUTPUT_FOLDER_PATH,
                    IUML2JavaConstants.Default.DEFAULT_OUTPUT_FOLDER_PATH);
            this.outputFolderPathText.setText(attribute);

            // Default project name
            attribute = configuration.getAttribute(IUML2JavaConstants.DEFAULT_PROJECT_NAME,
                    IUML2JavaConstants.Default.DEFAULT_DEFAULT_PROJECT_NAME);
            this.defaultProjectNameText.setText(attribute);

            // JRE execution environment
            attribute = configuration.getAttribute(IUML2JavaConstants.JRE_EXECUTION_ENVIRONMENT,
                    IUML2JavaConstants.Default.DEFAULT_JRE_EXECUTION_ENVIRONMENT);
            this.executionEnvJRECombo.setText(attribute);

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
        // Model path
        String umlModelPath = this.modelPathText.getText();
        configuration.setAttribute(IUML2JavaConstants.UML_MODEL_PATH, umlModelPath);

        // Source folder path
        String sourceFolderPath = this.sourceFolderPathText.getText();
        configuration.setAttribute(IUML2JavaConstants.SOURCE_FOLDER_PATH, sourceFolderPath);

        // Output folder path
        String outputFolderPath = this.outputFolderPathText.getText();
        configuration.setAttribute(IUML2JavaConstants.OUTPUT_FOLDER_PATH, outputFolderPath);

        // default project name
        String defaultProjectName = this.defaultProjectNameText.getText();
        configuration.setAttribute(IUML2JavaConstants.DEFAULT_PROJECT_NAME, defaultProjectName);

        // JRE execution environment
        String jreExecutionEnvironment = this.executionEnvJRECombo.getText();
        configuration.setAttribute(IUML2JavaConstants.JRE_EXECUTION_ENVIRONMENT, jreExecutionEnvironment);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
     */
    @Override
    public boolean isValid(ILaunchConfiguration configuration) {
        boolean isValid = true;
        try {
            // Model path
            String attribute = configuration.getAttribute(IUML2JavaConstants.UML_MODEL_PATH, "");
            isValid = isValid && attribute != null && attribute.trim().length() > 0;

            // Source folder path
            attribute = configuration.getAttribute(IUML2JavaConstants.SOURCE_FOLDER_PATH,
                    IUML2JavaConstants.Default.DEFAULT_SOURCE_FOLDER_PATH);
            isValid = isValid && attribute != null && attribute.trim().length() > 0;

            // Output folder path
            attribute = configuration.getAttribute(IUML2JavaConstants.OUTPUT_FOLDER_PATH,
                    IUML2JavaConstants.Default.DEFAULT_OUTPUT_FOLDER_PATH);
            isValid = isValid && attribute != null && attribute.trim().length() > 0;

            // Default project name
            attribute = configuration.getAttribute(IUML2JavaConstants.DEFAULT_PROJECT_NAME,
                    IUML2JavaConstants.Default.DEFAULT_DEFAULT_PROJECT_NAME);
            isValid = isValid && attribute != null && attribute.trim().length() > 0;

            // JRE execution environment
            attribute = configuration.getAttribute(IUML2JavaConstants.JRE_EXECUTION_ENVIRONMENT,
                    IUML2JavaConstants.Default.DEFAULT_JRE_EXECUTION_ENVIRONMENT);
            isValid = isValid && attribute != null && attribute.trim().length() > 0;

        } catch (CoreException e) {
            IStatus status = new Status(IStatus.ERROR, UML2JavaUIActivator.PLUGIN_ID, e.getMessage(), e);
            UML2JavaUIActivator.getDefault().getLog().log(status);
        }
        return isValid;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
     */
    public String getName() {
        return UML2JavaMessages.getString("UML2JavaGeneralLaunchConfigurationTab.Name");
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#getImage()
     */
    @Override
    public Image getImage() {
        return UML2JavaUIActivator.getDefault().getImage("icons/model_obj.gif");
    }

}
