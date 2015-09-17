/*******************************************************************************
 * Copyright (c) 2008, 2011 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 *     Obeo - Some improvements
 *******************************************************************************/
package org.eclipse.emf.eef.runtime.ui.widgets.referencestable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;
import org.eclipse.emf.eef.runtime.ui.utils.EEFRuntimeUIMessages;
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.IPropertiesFilteredWidget;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

/**
 * Base class for a complex section composite. This composite has a label, a
 * table that describes a tree structure, and four buttons on the side of the
 * table to add an element into the table, remove selected element(s), move up
 * or down the selected element.
 * 
 * @author Remi SCHNEKENBURGER
 * @author Patrick Tessier
 * @author <a href="mailto:jerome.benois@obeo.fr">Jerome Benois</a>
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class MemberEndsTable extends ReferencesTable implements IPropertiesFilteredWidget {

    /**
     * Image for the add element button.
     */
    final protected static Image NEW_ELEMENT_IMG = EEFRuntimePlugin.getImage(EEFRuntimePlugin.ICONS_16x16 + "Add_16x16.gif"); //$NON-NLS-1$

    /**
     * Image for the delete element button.
     */
    final protected static Image DELETE_ELEMENT_IMG = EEFRuntimePlugin.getImage(EEFRuntimePlugin.ICONS_16x16 + "Delete_16x16.gif"); //$NON-NLS-1$

    /**
     * Image for the up button.
     */
    final protected static Image UP_ELEMENT_IMG = EEFRuntimePlugin.getImage(EEFRuntimePlugin.ICONS_16x16 + "ArrowUp_16x16.gif"); //$NON-NLS-1$

    /**
     * Image for the down button.
     */
    final protected static Image DOWN_ELEMENT_IMG = EEFRuntimePlugin.getImage(EEFRuntimePlugin.ICONS_16x16 + "ArrowDown_16x16.gif"); //$NON-NLS-1$

    /** list of element that we want to display * */
    private Object input;

    /**
     * Label above the table.
     */
    private Label label;

    /**
     * Table that displays a property for the current element.
     */
    private Table table;

    /** the table viewer to associate the label provider * */
    private TableViewer tableViewer;

    /**
     * Button that adds an element.
     */
    protected Button addButton;

    /**
     * Button that removes an element.
     */
    protected Button removeButton;

    /**
     * button that moves the element up.
     */
    protected Button upButton;

    /**
     * button that moves the element down.
     */
    protected Button downButton;

    /**
     * Listener for the add button.
     */
    private MouseListener addButtonlistener;

    /**
     * Listener for the delete button.
     */
    private MouseListener removeButtonlistener;

    /**
     * Listener for the up button.
     */
    private MouseListener upButtonlistener;

    /**
     * Listener for the down button.
     */
    private MouseListener downButtonlistener;

    private Listener tableListener;

    private int upperBound = -1;

    private int lowerBound = 0;

    /**
     * The listener used by the client to handle business events (Add, Remove,
     * Move, NavigateTo)
     */
    private MemberEndsTableListener referencesTableListener;

    private String labelToDisplay;

    /**
     * The Form tool kit use to use this widget in an Eclipse Forms compliant
     * mode
     */
    private FormToolkit widgetFactory;

    /**
     * The main composite
     */
    private Composite composite;

    /**
     * The adapter factory.
     */
    protected AdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

    /**
     * The help text
     */
    private String helpText;

    /** The business rules filters. */
    protected List<ViewerFilter> bpFilters;

    /** The filters. */
    protected List<ViewerFilter> filters;

    /**
     * ContentProvider of the table
     */
    private IStructuredContentProvider contentProvider;

    // Set the table column property names
    private final String NAVIGABLE_COLUMN = "navigable";

    private final String PROPERTY_COLUMN = "property";

    private final String OWNED_COLUMN = "owned";

    /**
     * Set column names
     */
    private String[] columnNames = new String[] { PROPERTY_COLUMN, NAVIGABLE_COLUMN, OWNED_COLUMN };

    /**
     * the constructor
     * 
     * @param labeltoDisplay
     *            the label to display
     * @param the
     *            listener to handle Add, Remove, Move and NavigateTo events
     */
    public MemberEndsTable(String labeltoDisplay, MemberEndsTableListener referenceListener) {
        super(labeltoDisplay, referenceListener);
        this.labelToDisplay = labeltoDisplay;
        this.addButtonlistener = new AddButtonlistener();
        this.removeButtonlistener = new RemoveButtonlistener();
        this.upButtonlistener = new UpButtonlistener();
        this.downButtonlistener = new DownButtonlistener();
        bpFilters = new ArrayList<ViewerFilter>();
        filters = new ArrayList<ViewerFilter>();
        addTableReferenceListener(referenceListener);
    }

    public void addTableReferenceListener(MemberEndsTableListener referenceListener) {
        this.referencesTableListener = referenceListener;
    }

    public void addSelectionListener(SelectionListener selectionListener) {
        this.table.addSelectionListener(selectionListener);
    }

    public void createControls(Composite parent, FormToolkit widgetFactory) {
        this.widgetFactory = widgetFactory;
        createControls(parent);
    }

    private Composite createComposite(Composite parent) {
        Composite composite;
        if (widgetFactory == null) {
            composite = new Composite(parent, SWT.NONE);
        } else {
            composite = widgetFactory.createComposite(parent);
        }
        return composite;
    }

    private Button createButton(Composite parent, String text, int style) {
        Button button;
        if (widgetFactory == null) {
            button = new Button(parent, style);
            button.setText(text);
        } else {
            button = widgetFactory.createButton(parent, text, style);
        }
        return button;
    }

    private Label createLabel(Composite parent, String text, int style) {
        Label label;
        if (widgetFactory == null) {
            label = new Label(parent, SWT.PUSH);
            label.setText(text);
        } else {
            label = widgetFactory.createLabel(parent, text, style);
        }
        return label;
    }

    private Table createTable(Composite parent, int style) {
        Table table;
        if (widgetFactory == null) {
            table = new Table(parent, style);
        } else {
            table = widgetFactory.createTable(parent, style);
        }
        return table;
    }

    public void createControls(Composite parent) {
        composite = createComposite(parent);
        if (parent instanceof ExpandableComposite) {
            ((ExpandableComposite) parent).setClient(composite);
        }
        FormLayout formLayout = new FormLayout();
        formLayout.marginTop = 7;
        composite.setLayout(formLayout);

        FormData data;

        // Create Help Button
        data = new FormData();
        data.top = new FormAttachment(-2, 0);
        data.right = new FormAttachment(100, -ITabbedPropertyConstants.HSPACE);
        Control helpButton = null;
        if (helpText != null) {
            if (widgetFactory != null) {
                helpButton = FormUtils.createHelpButton(widgetFactory, composite, helpText, null);
            } else {
                helpButton = SWTUtils.createHelpButton(composite, helpText, null);
            }
            helpButton.setLayoutData(data);
        }

        // ///////////////////////////////////////////////////////////////////////////
        // Create and place button vertically on the left side
        // Button : Add Element
        // Button Delete Element
        removeButton = createButton(composite, "", SWT.PUSH); //$NON-NLS-1$
        removeButton.setVisible(true);
        removeButton.setImage(DELETE_ELEMENT_IMG);
        removeButton.setToolTipText(EEFRuntimeUIMessages.ReferencesTable_remove_tooltip);
        data = new FormData();
        // data.top = new FormAttachment(addButton,
        // ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(-6, 0);
        if (helpText != null) {
            data.right = new FormAttachment(helpButton, -ITabbedPropertyConstants.HSPACE);
        } else {
            data.right = new FormAttachment(100, -ITabbedPropertyConstants.HSPACE);
        }
        removeButton.setLayoutData(data);
        removeButton.addMouseListener(removeButtonlistener);

        addButton = createButton(composite, "", SWT.PUSH); //$NON-NLS-1$
        addButton.setVisible(true);
        addButton.setImage(NEW_ELEMENT_IMG);
        addButton.setToolTipText(EEFRuntimeUIMessages.ReferencesTable_add_tooltip);

        data = new FormData();
        // data.top = new FormAttachment(label,
        // ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(-6, 0);
        data.right = new FormAttachment(removeButton, -ITabbedPropertyConstants.HSPACE);
        addButton.setLayoutData(data);
        addButton.addMouseListener(addButtonlistener);

        // Button Up
        upButton = createButton(composite, "", SWT.PUSH); //$NON-NLS-1$
        upButton.setVisible(true);
        upButton.setImage(UP_ELEMENT_IMG);
        upButton.setToolTipText(EEFRuntimeUIMessages.ReferencesTable_up_tooltip);

        data = new FormData();
        // data.top = new FormAttachment(removeButton,
        // ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(-6, 0);
        data.right = new FormAttachment(addButton, -ITabbedPropertyConstants.HSPACE);
        upButton.setLayoutData(data);
        upButton.addMouseListener(upButtonlistener);

        // Button Down
        downButton = createButton(composite, "", SWT.PUSH); //$NON-NLS-1$
        downButton.setVisible(true);
        downButton.setImage(DOWN_ELEMENT_IMG);
        downButton.setToolTipText(EEFRuntimeUIMessages.ReferencesTable_down_tooltip);

        data = new FormData();
        // data.top = new FormAttachment(upButton,
        // ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(-6, 0);
        data.right = new FormAttachment(upButton, -ITabbedPropertyConstants.HSPACE);
        downButton.setLayoutData(data);
        downButton.addMouseListener(downButtonlistener);

        // Create label
        label = createLabel(composite, labelToDisplay, SWT.NONE);
        // label.setLayout(new FormLayout());
        data = new FormData();
        data.left = new FormAttachment(2, 0);
        data.right = new FormAttachment(downButton, -ITabbedPropertyConstants.HSPACE - 5/* 50 */);
        data.top = new FormAttachment(0, 0);
        label.setLayoutData(data);

        // ///////////////////////////////////////////////////////////////////////////
        // Create and place Table
        table = createTable(composite, SWT.MULTI | SWT.H_SCROLL | SWT.BORDER);
        table.setLayout(new FormLayout());
        table.setVisible(true);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalSpan = 3;
        table.setLayoutData(gridData);

        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        // 1st column with task Property name
        TableColumn column = new TableColumn(table, SWT.LEFT, 0);
        column.setText("Property");
        column.setWidth(100);

        // 2nd column with image/checkboxes - NOTE: The SWT.CENTER has no
        // effect!!
        column = new TableColumn(table, SWT.CENTER, 1);
        column.setText("Navigable");
        column.setWidth(100);

        // 3rd column with image/checkboxes
        column = new TableColumn(table, SWT.CENTER, 2);
        column.setText("Owned");
        column.setWidth(100);

        table.addListener(SWT.MouseDoubleClick, tableListener = new EditItemListener());
        // createTable
        tableViewer = new TableViewer(table);
        tableViewer.setUseHashlookup(true);
        tableViewer.setColumnProperties(columnNames);

        // Create the cell editors
        CellEditor[] editors = new CellEditor[columnNames.length];

        // Column 1 : Property (Free text)
        TextCellEditor textEditor = new TextCellEditor(table);
        editors[0] = textEditor;

        // Column 2 : Navigability (Checkbox)
        editors[1] = new CheckboxCellEditor(table);

        // Column 3 : Owned (Checkbox)
        editors[2] = new CheckboxCellEditor(table);

        // Assign the cell editors to the viewer
        tableViewer.setCellEditors(editors);

        // Set the cell modifier for the viewer
        tableViewer.setCellModifier(new MemberEndsTableCellModifier(this));

        // The filters.

        data = new FormData();
        data.height = 100;
        data.top = new FormAttachment(label, ITabbedPropertyConstants.VSPACE + 4);
        data.left = new FormAttachment(0, ITabbedPropertyConstants.HSPACE);
        data.right = new FormAttachment(100, -ITabbedPropertyConstants.HSPACE);

        table.setLayoutData(data);
        table.addMouseListener(new MouseListener() {

            public void mouseDoubleClick(MouseEvent e) {
                if (table.getSelection() != null && table.getSelectionCount() != 0 && table.getSelection()[0].getData() instanceof EObject) {
                    // Navigate
                    referencesTableListener.navigateTo((EObject) table.getSelection()[0].getData());
                }
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        // tableViewer.refresh();
        // table.pack();
    }

    /**
     * @param layoutData
     *            the layoutData to set
     */
    public void setLayoutData(Object layoutData) {
        composite.setLayoutData(layoutData);
    }

    /**
     * @param contentProvider
     *            contentProvider to use in the Table
     */
    public void setContentProvider(IStructuredContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }

    public void setUpperBound(int value) {
        if (value < 0)
            this.upperBound = -1;
        else
            this.upperBound = value;
    }

    public int getUpperBound() {
        return this.upperBound;
    }

    public void setLowerBound(int value) {
        if (value <= 0)
            this.lowerBound = 0;
        else
            this.lowerBound = value;
    }

    public int getLowerBound() {
        return this.lowerBound;
    }

    private int getSize() {
        if (contentProvider != null && input != null)
            return contentProvider.getElements(input).length;
        return -1;
    }

    private int indexOf(Object elem) {
        Object[] elements = contentProvider.getElements(input);
        for (int i = 0; i < elements.length; i++) {
            Object next = elements[i];
            if (next.equals(elem))
                return i;

        }
        return -1;
    }

    /**
     * Sets the given ID to the EMFComboViewer
     * 
     * @param id
     *            the ID to give
     */
    public void setID(Object id) {
        EditingUtils.setID(table, id);
        EditingUtils.setID(addButton, id);
        EditingUtils.setID(removeButton, id);
        EditingUtils.setID(upButton, id);
        EditingUtils.setID(downButton, id);
    }

    /**
     * Defines the type of reference table
     * 
     * @param id
     *            the type to give
     */
    public void setEEFType(String type) {
        EditingUtils.setEEFtype(table, type + "::field");
        EditingUtils.setEEFtype(addButton, type + "::addbutton");
        EditingUtils.setEEFtype(removeButton, type + "::removebutton");
        EditingUtils.setEEFtype(upButton, type + "::upbutton");
        EditingUtils.setEEFtype(downButton, type + "::downbutton");
    }

    /**
     * @return the ID of the EObjectFlatComboViewer
     */
    public Object getID() {
        return EditingUtils.getID(table);
    }

    /**
     * @param helpText
     */
    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public void refresh() {
        tableViewer.refresh();
        computeAddButtonStatus();
        computeRemoveButtonStatus();
    }

    private void computeRemoveButtonStatus() {
        if (getTable().getEnabled() && (getSize() > this.lowerBound))
            removeButton.setEnabled(true);
        else
            removeButton.setEnabled(false);

    }

    private void computeAddButtonStatus() {
        if (getTable().getEnabled() && (this.upperBound < 0 || getSize() < this.upperBound))
            addButton.setEnabled(true);
        else
            addButton.setEnabled(false);
    }

    /**
     * display the content of the table
     */
    public void initLabelProvider() {
        if (!table.isDisposed()) {
            // set the label provider
            tableViewer.setLabelProvider(getLabelProvider());
        }
    }

    /**
     * Returns the label provider for the composite
     * 
     * @return the label provider or <code>null</code>
     */
    public AdapterFactoryLabelProvider getLabelProvider() {
        return new AdapterFactoryLabelProvider(adapterFactory);
    }

    /**
     * Disable Move capability (Hide Up and Down buttons)
     */
    public void disableMove() {
        upButton.setVisible(false);
        downButton.setVisible(false);
    }

    /**
     * Listener for the Add Button Specific behavior is implemented in
     * {@link ReferencesTable#addButtonPressed()}.
     * 
     * @author Remi SCHNEKENBURGER
     */
    private class AddButtonlistener implements MouseListener {

        /**
         * {@inheritDoc}
         */
        public void mouseDoubleClick(MouseEvent e) {
            // do nothing
        }

        /**
         * {@inheritDoc}
         */
        public void mouseDown(MouseEvent e) {
            // do nothing
        }

        /**
         * {@inheritDoc}
         */
        public void mouseUp(MouseEvent e) {
            referencesTableListener.handleAdd();

        }
    }

    /**
     * Listener for the Remove Button Specific behavior is implemented in
     * {@link ReferencesTable#removeButtonPressed()}.
     * 
     * @author Remi SCHNEKENBURGER
     */
    private class RemoveButtonlistener implements MouseListener {

        /**
         * {@inheritDoc}
         */
        public void mouseDoubleClick(MouseEvent e) {
            // do nothing
        }

        /**
         * {@inheritDoc}
         */
        public void mouseDown(MouseEvent e) {
            // do nothing
        }

        /**
         * {@inheritDoc}
         */
        public void mouseUp(MouseEvent e) {
            // Keep selection
            TableItem[] tableItems = table.getSelection();

            for (int i = (tableItems.length - 1); i >= 0; i--) {
                // Remove
                referencesTableListener.handleRemove((EObject) tableItems[i].getData());
            }
        }
    }

    /**
     * Listener for the Up Button Specific behavior is implemented in
     * {@link ReferencesTable#upButtonPressed()}.
     * 
     * @author Remi SCHNEKENBURGER
     */
    private class UpButtonlistener implements MouseListener {

        /**
         * {@inheritDoc}
         */
        public void mouseDoubleClick(MouseEvent e) {
            // do nothing
        }

        /**
         * {@inheritDoc}
         */
        public void mouseDown(MouseEvent e) {
            // do nothing
        }

        /**
         * {@inheritDoc}
         */
        public void mouseUp(MouseEvent e) {
            // Keep selection
            TableItem[] tableItems = table.getSelection();

            for (int i = (tableItems.length - 1); i >= 0; i--) {
                // Get use case

                int newIndex = indexOf(tableItems[i].getData()) - 1;
                if (newIndex >= 0 && newIndex < getSize()) {
                    // Move
                    referencesTableListener.handleMove((EObject) tableItems[i].getData(), newIndex + 1, newIndex);
                }
            }

        }
    }

    /**
     * Listener for the Down Button Specific behavior is implemented in
     * {@link ReferencesTable#downButtonPressed()}.
     * 
     * @author Remi SCHNEKENBURGER
     */
    private class DownButtonlistener implements MouseListener {

        /**
         * {@inheritDoc}
         */
        public void mouseDoubleClick(MouseEvent e) {
            // do nothing
        }

        /**
         * {@inheritDoc}
         */
        public void mouseDown(MouseEvent e) {
            // do nothing
        }

        /**
         * {@inheritDoc}
         */
        public void mouseUp(MouseEvent e) {
            TableItem[] tableItems = table.getSelection();
            for (int i = (tableItems.length - 1); i >= 0; i--) {
                // Get use case
                int newIndex = indexOf(tableItems[i].getData()) + 1;
                if (newIndex >= 0 && newIndex < getSize()) {
                    // Move
                    referencesTableListener.handleMove((EObject) tableItems[i].getData(), newIndex - 1, newIndex);
                }
            }
        }
    }

    /**
	 * 
	 */
    private class EditItemListener implements Listener {

        /**
         * @{inheritDoc
         */
        public void handleEvent(Event event) {
            if (table.getSelection().length > 0) {
                TableItem item = table.getSelection()[0];
                if (item.getData() instanceof EObject) {
                    // Edit
                    referencesTableListener.handleEdit((EObject) item.getData());
                }
            }
        }
    }

    /**
     * removes listeners from buttons.
     */
    public void dispose() {
        if (addButton != null && !addButton.isDisposed())
            addButton.removeMouseListener(addButtonlistener);
        if (removeButton != null && !removeButton.isDisposed())
            removeButton.removeMouseListener(removeButtonlistener);
        if (upButton != null && !upButton.isDisposed())
            upButton.removeMouseListener(upButtonlistener);
        if (downButton != null && !downButton.isDisposed())
            downButton.removeMouseListener(downButtonlistener);
        if (table != null && !table.isDisposed())
            table.removeListener(SWT.MouseDoubleClick, tableListener);
        if (filters != null) {
            filters.clear();
            filters = null;
        }
        if (bpFilters != null) {
            bpFilters.clear();
            bpFilters = null;
        }
    }

    /**
     * input of viewer
     * 
     * @param input
     */
    public void setInput(Object input) {
        this.input = input;
        initLabelProvider();
        tableViewer.setContentProvider(contentProvider);
        tableViewer.setInput(input);
        for (ViewerFilter filter : filters) {
            this.tableViewer.addFilter(filter);
        }
        for (ViewerFilter filter : bpFilters) {
            this.tableViewer.addFilter(filter);
        }
        computeAddButtonStatus();
        computeRemoveButtonStatus();
    }

    /**
     * @return the input of the viewer
     */
    public Object getInput() {
        return input;
    }

    public interface MemberEndsTableListener extends ReferencesTableListener {
        void handleCheckNavigableEnds(EObject element);

        void handleUncheckNavigableEnds(EObject element);

        void handleCheckOwnedEnds(EObject element);

        void handleUncheckOwnedEnds(EObject element);
    }

    /*
     * (non-Javadoc)
     * @seeorg.eclipse.emf.eef.runtime.ui.widgets.IPropertiesFilteredWidget#
     * addBusinessRuleFilter(org.eclipse. jface.viewers.ViewerFilter)
     */
    public void addBusinessRuleFilter(ViewerFilter filter) {
        this.bpFilters.add(filter);
        if (this.tableViewer != null) {
            this.tableViewer.addFilter(filter);
            this.tableViewer.refresh();
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.emf.eef.runtime.ui.widgets.IPropertiesFilteredWidget#addFilter
     * (org.eclipse.jface.viewers .ViewerFilter)
     */
    public void addFilter(ViewerFilter filter) {
        this.filters.add(filter);
        if (this.tableViewer != null) {
            this.tableViewer.addFilter(filter);
            this.tableViewer.refresh();
        }
    }

    /*
     * (non-Javadoc)
     * @seeorg.eclipse.emf.eef.runtime.ui.widgets.IPropertiesFilteredWidget#
     * removeBusinessRuleFilter(org.eclipse .jface.viewers.ViewerFilter)
     */
    public void removeBusinessRuleFilter(ViewerFilter filter) {
        this.bpFilters.remove(filter);
        if (this.tableViewer != null) {
            this.tableViewer.removeFilter(filter);
            this.tableViewer.refresh();
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.emf.eef.runtime.ui.widgets.IPropertiesFilteredWidget#removeFilter
     * (org.eclipse.jface.viewers .ViewerFilter)
     */
    public void removeFilter(ViewerFilter filter) {
        this.filters.remove(filter);
        if (this.tableViewer != null) {
            this.tableViewer.removeFilter(filter);
            this.tableViewer.refresh();
        }
    }

    protected void refreshFilters() {
    }

    /**
     * Sets the tables readonly or not
     * 
     * @param enabled
     *            to set the table readonly or not
     */
    public void setEnabled(boolean enabled) {
        addButton.setEnabled(enabled);
        downButton.setEnabled(enabled);
        removeButton.setEnabled(enabled);
        table.setEnabled(enabled);
        upButton.setEnabled(enabled);
        downButton.setEnabled(enabled);
    }

    /**
     * @return if the table is enabled
     */
    public boolean isEnabled() {
        return table.isEnabled();
    }

    /**
     * Sets the tooltip text for the viewer
     * 
     * @param tooltip
     *            the tooltip text
     */
    public void setToolTipText(String tooltip) {
        addButton.setToolTipText(tooltip);
        downButton.setToolTipText(tooltip);
        removeButton.setToolTipText(tooltip);
        table.setToolTipText(tooltip);
        upButton.setToolTipText(tooltip);
    }

    /**
     * Returns the table.
     * 
     * @return the table.
     */
    public Table getTable() {
        return table;
    }

    /**
     * Return the column names in a collection
     * 
     * @return List containing column names
     */
    @SuppressWarnings("rawtypes")
    public java.util.List getColumnNames() {
        return Arrays.asList(columnNames);
    }

    public MemberEndsTableListener getReferencesTableListener() {
        return referencesTableListener;
    }

    public TableViewer getTableViewer() {
        return tableViewer;
    }

}
