/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.parts.forms;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Property;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService.TableColumnName;
import org.obeonetwork.dsl.uml2.properties.service.TableColumnService;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;

/**
 * Customize the table representing the attributes. This custom part form is
 * referenced in the plugin.xml to be used in place of the generated one
 * {@link AttributesPropertiesEditionPartForm}.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class AttributesCustomPropertiesEditionPartForm extends AttributesPropertiesEditionPartForm {

    final TableLabelService labelService = new TableLabelService();

    final TableColumnService tableColumnService = new TableColumnService();

    public AttributesCustomPropertiesEditionPartForm() {
        super();
    }

    public AttributesCustomPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
        super(editionComponent);
    }

    // Part of this code was just copied from the generated class in order to
    // override the label provider
    @Override
    protected Composite createAttributesTableComposition(FormToolkit widgetFactory, Composite parent) {
        this.attributes = new ReferencesTable(getDescription(UmlViewsRepository.Attributes.attributes_, UmlMessages.AttributesPropertiesEditionPart_AttributesLabel), new ReferencesTableListener() {
            public void handleAdd() {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AttributesCustomPropertiesEditionPartForm.this, UmlViewsRepository.Attributes.attributes_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
                attributes.refresh();
            }

            public void handleEdit(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AttributesCustomPropertiesEditionPartForm.this, UmlViewsRepository.Attributes.attributes_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
                attributes.refresh();
            }

            public void handleMove(EObject element, int oldIndex, int newIndex) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AttributesCustomPropertiesEditionPartForm.this, UmlViewsRepository.Attributes.attributes_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
                attributes.refresh();
            }

            public void handleRemove(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AttributesCustomPropertiesEditionPartForm.this, UmlViewsRepository.Attributes.attributes_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
                attributes.refresh();
            }

            public void navigateTo(EObject element) {
            }
        }) {
            @Override
            public AdapterFactoryLabelProvider getLabelProvider() {
                return new AdapterFactoryLabelProvider(adapterFactory) {
                    @Override
                    public String getColumnText(Object object, int columnIndex) {

                        if (object instanceof Property) {
                            Property property = (Property) object;
                            if (columnIndex == tableColumnService.indexOf(TableColumnName.NAME)) {
                                return labelService.caseName(property);
                            }
                            if (columnIndex == tableColumnService.indexOf(TableColumnName.TYPE)) {
                                return labelService.caseType(property);
                            }
                            if (columnIndex == tableColumnService.indexOf(TableColumnName.DEFAULT_VALUE)) {
                                return labelService.caseDefaultValue(property);
                            }
                            if (columnIndex == tableColumnService.indexOf(TableColumnName.VISIBILITY)) {
                                return labelService.caseVisibility(property);
                            }
                            if (columnIndex == tableColumnService.indexOf(TableColumnName.IS_STATIC)) {
                                return labelService.caseIsStatic(property);
                            }
                            if (columnIndex == tableColumnService.indexOf(TableColumnName.MULTIPLICITY)) {
                                return labelService.caseMultiplicity(property);
                            }
                        }
                        return super.getColumnText(object, columnIndex);
                    }

                    @Override
                    public Image getColumnImage(Object object, int columnIndex) {
                        if (columnIndex == tableColumnService.indexOf(TableColumnName.NAME)) {
                            return super.getColumnImage(object, columnIndex);
                        }
                        return null;
                    }
                };
            }
        };
        for (ViewerFilter filter : this.attributesFilters) {
            this.attributes.addFilter(filter);
        }
        this.attributes.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Attributes.attributes_, UmlViewsRepository.FORM_KIND));
        this.attributes.createControls(parent, widgetFactory);
        this.attributes.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                if (e.item != null && e.item.getData() instanceof EObject) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AttributesCustomPropertiesEditionPartForm.this, UmlViewsRepository.Attributes.attributes_,
                            PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
                }
            }

        });
        GridData attributesData = new GridData(GridData.FILL_HORIZONTAL);
        attributesData.horizontalSpan = 3;
        this.attributes.setLayoutData(attributesData);
        this.attributes.setLowerBound(0);
        this.attributes.setUpperBound(-1);
        attributes.setID(UmlViewsRepository.Attributes.attributes_);
        attributes.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
        // Start of user code for createAttributesTableComposition
        Table table = attributes.getTable();
        TableColumn[] columns = table.getColumns();
        // dispose
        for (TableColumn tableColumn : columns) {
            tableColumn.dispose();
        }

        table.setHeaderVisible(true);
        tableColumnService.createMediumColumn(table, TableColumnName.NAME);
        tableColumnService.createMediumColumn(table, TableColumnName.TYPE);
        tableColumnService.createMediumColumn(table, TableColumnName.DEFAULT_VALUE);
        tableColumnService.createMediumColumn(table, TableColumnName.VISIBILITY);
        tableColumnService.createSmallColumn(table, TableColumnName.IS_STATIC);
        tableColumnService.createSmallColumn(table, TableColumnName.MULTIPLICITY);

        // End of user code
        return parent;
    }

}
