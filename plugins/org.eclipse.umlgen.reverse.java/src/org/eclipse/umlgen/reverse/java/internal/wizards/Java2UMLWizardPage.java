/*******************************************************************************
 * Copyright (c) 2006 Anyware Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies) - initial API and implementation
 *    Urs Zeidler   - Added some widget to support the new features, model imports
 *                   and profile import
 *******************************************************************************/
package org.eclipse.umlgen.reverse.java.internal.wizards;

import java.util.ArrayList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.umlgen.reverse.java.AbstractJava2UMLConverter.ActivityGeneration;

/**
 * The "New" wizard page allows setting the container for the new file as well as the file name. The page will
 * only accept file name without the extension OR with the extension that matches the expected one (uml2).
 */
public class Java2UMLWizardPage extends WizardPage {

    /** The text for the container of the file generated. */
    private Text containerText;

    /** The text for the name of the file generated. */
    private Text fileText;

    /** The text for the model name. */
    private Text modelText;

    /** The element selected. */
    private ISelection selection;

    /** The list for the model imports. */
    private List listModelImports;

    /** specific-mode only list; is automatically filled out if an existing model is selected. */
    private java.util.List<String> automaticListModelImports;

    /** Combo of Limit visibility. */
    private Combo restrictVisCombo;

    /** The button Add. */
    private Button buttonadd;

    /** The button delete. */
    private Button buttondel;

    /** The button clear. */
    private Button buttonclear;

    // Activity Generation
    /** The selection for no activities. */
    private Button noActivityRadio;

    /** The selection for annotated activity only. */
    private Button annotatedActivityRadio;

    /** The selection for all activities. */
    private Button allActivityRadio;

    /** Combo of logging level. */
    private Combo loggingLevelChoice;

    /**
     * Constructor for SampleNewWizardPage.
     *
     * @param selection
     *            current selection
     */
    public Java2UMLWizardPage(ISelection selection) {
        super("wizardPage");
        setTitle("UML Model File");
        setDescription("This wizard creates a new file with *.uml extension from a Java Reverse Engineering process.");
        this.selection = selection;
    }

    /**
     * Creates the top level control for this dialog page under the given parent composite. Implementors are
     * responsible for ensuring that the created control can be accessed via getControl
     *
     * @param parent
     *            the parent composite
     * @see IDialogPage#createControl(Composite)
     */
    public void createControl(Composite parent) {
        Group groupModel;
        Group groupModel1;
        Label lblLoggingLevel;
        Composite compositebtn;
        GridData gd1;
        GridData gd2;

        Composite container = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 3;
        layout.verticalSpacing = 9;

        Label label = new Label(container, SWT.NULL);
        label.setText("&Container:");

        containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.grabExcessHorizontalSpace = false;
        containerText.setLayoutData(gd);
        containerText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });

        Button button = new Button(container, SWT.PUSH);
        button.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        button.setText("Browse...");
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                handleBrowse();
            }
        });
        Label fileNameLabel = new Label(container, SWT.NULL);
        fileNameLabel.setText("&File name:");

        fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd1 = new GridData(GridData.FILL_HORIZONTAL);
        gd1.horizontalSpan = 2;
        fileText.setLayoutData(gd1);
        fileText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });

        label = new Label(container, SWT.NULL);
        label.setText("&Model name:");
        modelText = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd2 = new GridData(GridData.FILL_HORIZONTAL);
        gd2.horizontalSpan = 2;
        modelText.setLayoutData(gd2);
        modelText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });

        // Patch TSL: limit the import with the visibility
        label = new Label(container, SWT.NULL);
        label.setText("&Limit Visibility:");
        restrictVisCombo = new Combo(container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
        restrictVisCombo.add(VisibilityKind.PUBLIC_LITERAL.getLiteral());
        restrictVisCombo.add(VisibilityKind.PACKAGE_LITERAL.getLiteral());
        restrictVisCombo.add(VisibilityKind.PROTECTED_LITERAL.getLiteral());
        restrictVisCombo.add(VisibilityKind.PRIVATE_LITERAL.getLiteral());
        restrictVisCombo.select(3); // By default, include everything

        gd = new GridData(GridData.FILL_HORIZONTAL);
        restrictVisCombo.setLayoutData(gd);
        restrictVisCombo.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });
        new Label(container, SWT.NONE);

        lblLoggingLevel = new Label(container, SWT.NONE);
        lblLoggingLevel.setText("Logging level:");

        loggingLevelChoice = new Combo(container, SWT.READ_ONLY);
        loggingLevelChoice.setItems(new String[] {"debug", "info", "error" });
        loggingLevelChoice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        loggingLevelChoice.select(1);
        new Label(container, SWT.NONE);

        GridLayout gridLayout1 = new GridLayout();
        gridLayout1.numColumns = 3; // Generated
        GridData gridData5 = new GridData();
        gridData5.grabExcessVerticalSpace = true;
        gridData5.grabExcessHorizontalSpace = true;
        gridData5.verticalSpan = 20; // Generated
        gridData5.horizontalAlignment = GridData.FILL; // Generated
        gridData5.verticalAlignment = GridData.FILL; // Generated
        gridData5.horizontalSpan = 10; // Generated
        groupModel = new Group(container, SWT.NONE);
        groupModel.setText("Model imports"); // Generated
        groupModel.setLayoutData(gridData5); // Generated
        groupModel.setLayout(gridLayout1); // Generated

        GridData gridData7 = new GridData();
        gridData7.grabExcessHorizontalSpace = true; // Generated
        gridData7.grabExcessVerticalSpace = true;
        gridData7.verticalSpan = 20; // Generated
        gridData7.horizontalAlignment = GridData.FILL; // Generated
        gridData7.verticalAlignment = GridData.FILL; // Generated
        gridData7.horizontalSpan = 10; // Generated
        listModelImports = new List(groupModel, SWT.MULTI | SWT.BORDER);
        listModelImports.setLayoutData(gridData7); // Generated

        RowLayout rowLayout = new RowLayout();
        rowLayout.type = SWT.HORIZONTAL; // Generated
        rowLayout.justify = false; // Generated
        rowLayout.pack = false; // Generated
        rowLayout.fill = false; // Generated
        GridData gridData10 = new GridData();
        gridData10.horizontalAlignment = GridData.FILL; // Generated
        gridData10.horizontalSpan = 2; // Generated
        // gridData10.verticalAlignment = GridData.CENTER; // Generated

        compositebtn = new Composite(groupModel, SWT.NONE);
        compositebtn.setLayoutData(gridData10); // Generated
        compositebtn.setLayout(rowLayout); // Generated
        buttonadd = new Button(compositebtn, SWT.NONE);
        buttonadd.setText("add"); // Generated
        buttonadd.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                handleAddImport();
            }
        });
        buttondel = new Button(compositebtn, SWT.NONE);
        buttondel.setText("del"); // Generated
        buttondel.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                String[] selection2 = listModelImports.getSelection();
                for (String string : selection2) {
                    listModelImports.remove(string);
                }
            }
        });

        buttonclear = new Button(compositebtn, SWT.NONE);
        buttonclear.setText("clear"); // Generated
        new Label(groupModel, SWT.NONE);
        buttonclear.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            @Override
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                listModelImports.setItems(new String[] {});
            }
        });

        groupModel1 = new Group(container, SWT.NONE);
        groupModel1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1));
        groupModel1.setText("Activity generation");
        groupModel1.setLayout(new GridLayout(3, false));

        noActivityRadio = new Button(groupModel1, SWT.RADIO);
        noActivityRadio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        noActivityRadio.setBounds(0, 0, 90, 16);
        noActivityRadio.setText("None");
        noActivityRadio.setSelection(true);

        annotatedActivityRadio = new Button(groupModel1, SWT.RADIO);
        annotatedActivityRadio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        annotatedActivityRadio.setBounds(0, 0, 90, 16);
        annotatedActivityRadio.setText("Annotated Only");
        annotatedActivityRadio.setEnabled(false);
        annotatedActivityRadio.setSelection(false);

        allActivityRadio = new Button(groupModel1, SWT.RADIO);
        allActivityRadio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        allActivityRadio.setBounds(0, 0, 90, 16);
        allActivityRadio.setText("All");
        allActivityRadio.setSelection(false);

        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);

        initialize();
        dialogChanged();
        setControl(container);
    }

    /**
     * Tests if the current workbench selection is a suitable container to use.
     */

    private void initialize() {
        if (selection != null && !selection.isEmpty() && selection instanceof IStructuredSelection) {
            IStructuredSelection ssel = (IStructuredSelection)selection;
            if (ssel.size() > 1) {
                return;
            }
            Object obj = ssel.getFirstElement();
            if (obj instanceof IResource) {
                IContainer container;
                if (obj instanceof IContainer) {
                    container = (IContainer)obj;
                } else {
                    container = ((IResource)obj).getParent();
                }
                String name = container.getFullPath().toString();
                containerText.setText(name);
                name = container.getName();
                modelText.setText(name);
                fileText.setText(name + ".uml");
            }
        }

    }

    /**
     * Add Import.
     */
    private void handleAddImport() {
        IContainer cont = ResourcesPlugin.getWorkspace().getRoot();

        ResourceSelectionDialog dialog = new ResourceSelectionDialog(getShell(), cont,
                "select Model to Import");

        if (dialog.open() == IDialogConstants.OK_ID) {
            // dialog.setBlockOnOpen(true);
            Object[] obj = dialog.getResult();
            if (obj == null || obj.length < 1) {
                return;
            }
            for (Object object : obj) {
                if (object instanceof IResource) {
                    String filename = ((IResource)object).getFullPath().toString();
                    listModelImports.add(filename);
                }
            }
        }
    }

    /**
     * Uses the standard container selection dialog to choose the new value for the container field.
     */
    private void handleBrowse() {
        ContainerSelectionDialog dialog = new ContainerSelectionDialog(getShell(), ResourcesPlugin
                .getWorkspace().getRoot(), false, "Select new file container");
        if (dialog.open() == Window.OK) {
            Object[] result = dialog.getResult();
            if (result.length == 1) {
                containerText.setText(((Path)result[0]).toString());
            }
        }
    }

    /**
     * Ensures that both text fields are set.
     */

    private void dialogChanged() {
        IResource container = ResourcesPlugin.getWorkspace().getRoot().findMember(
                new Path(getContainerName()));
        String fileName = getFileName();

        if (getContainerName().length() == 0) {
            updateStatus("File container must be specified");
            return;
        }
        if (container == null || (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
            updateStatus("File container must exist");
            return;
        }
        if (!container.isAccessible()) {
            updateStatus("Project must be writable");
            return;
        }
        if (fileName.length() == 0) {
            updateStatus("File name must be specified");
            return;
        }
        if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
            updateStatus("File name must be valid");
            return;
        }
        int dotLoc = fileName.lastIndexOf('.');
        if (dotLoc != -1) {
            String ext = fileName.substring(dotLoc + 1);
            if (!"uml".equalsIgnoreCase(ext)) {
                updateStatus("File extension must be \"uml\"");
                return;
            }
        }
        calculateModelImports(container, fileName, getModelName());
        updateStatus(null);
    }

    /**
     * Calculates and sets the automated imports list. Assumes that the paths given are valid, writable, with
     * a .uml extension
     *
     * @param modelName
     * @param fileName
     * @param resource
     */
    private void calculateModelImports(IResource resource, String fileName, String modelName) {
        // Smart list reset : only removes from the master list elements that were added automatically to
        // begin with.
        if (automaticListModelImports == null) {
            automaticListModelImports = new ArrayList<String>();
        }
        for (String string : automaticListModelImports) {
            listModelImports.remove(string);
        }
        automaticListModelImports.clear();
    }

    /**
     * Updates the model Import.
     *
     * @param model
     */
    private void updateModelImports(Model model) {
        if (model.getPackageImports() != null) {
            for (PackageImport pImport : model.getPackageImports()) {
                Model otherModel = pImport.getImportedPackage().getModel();
                // if the source's model is located in another resource, add it to our list
                if (otherModel != null && !model.eResource().equals(otherModel.eResource())) {
                    String platformString = otherModel.eResource().getURI().toPlatformString(true);
                    automaticListModelImports.add(platformString);
                    listModelImports.add(platformString);
                }
            }
        }
    }

    /**
     * Updates Status.
     *
     * @param message
     */
    private void updateStatus(String message) {
        setErrorMessage(message);
        setPageComplete(message == null);
    }

    /**
     * Returns the container text.
     *
     * @return the name of the container
     */
    public String getContainerName() {
        return containerText.getText();
    }

    /**
     * Returns the filename.
     *
     * @return the name
     */
    public String getFileName() {
        return fileText.getText();
    }

    /**
     * Returns the list of imports.
     *
     * @return the list
     */
    public String[] getImportList() {
        return listModelImports.getItems();
    }

    /**
     * Return the visibility that limits the import.
     *
     * @return The visibility
     */
    public VisibilityKind getVisibilityKind() {
        switch (restrictVisCombo.getSelectionIndex()) {
            case 1:
                return VisibilityKind.PACKAGE_LITERAL;
            case 2:
                return VisibilityKind.PROTECTED_LITERAL;
            case 3:
                return VisibilityKind.PRIVATE_LITERAL;
            default:
                return VisibilityKind.PUBLIC_LITERAL;
        }
    }

    /**
     * Returns the model names.
     *
     * @return the name
     */
    public String getModelName() {
        return modelText.getText();
    }

    /**
     * Return the ActivityGeneration.
     *
     * @return the ActivityGeneration
     */
    public ActivityGeneration getActivityGeneration() {
        if (allActivityRadio.getSelection()) {
            return ActivityGeneration.ALL;
        } else if (noActivityRadio.getSelection()) {
            return ActivityGeneration.NONE;
        } else if (annotatedActivityRadio.getSelection()) {
            return ActivityGeneration.ANNOTATED;
        }
        return null;
    }

    /**
     * Set the ActivityGeneration.
     *
     * @param activityGen
     */
    public void setActivityGen(ActivityGeneration activityGen) {
        switch (activityGen) {
            case ALL:
                allActivityRadio.setSelection(true);
                return;
            case ANNOTATED:
                annotatedActivityRadio.setSelection(true);
                return;
            case NONE:
                noActivityRadio.setSelection(true);
                return;
            default:
                return;
        }
    }

    /**
     * Return the level of the log.
     *
     * @return the level
     */
    public int getLoggingLevel() {
        switch (loggingLevelChoice.getSelectionIndex()) {
            case 0:
                return IStatus.INFO;
            case 1:
                return IStatus.WARNING;
            case 2:
                return IStatus.ERROR;
            default:
                return IStatus.INFO;
        }
    }
}
