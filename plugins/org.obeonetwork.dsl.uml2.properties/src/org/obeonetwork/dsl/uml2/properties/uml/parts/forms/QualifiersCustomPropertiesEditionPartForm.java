/*******************************************************************************
 * Copyright (c) 2016 Obeo.
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
 * Customize the table representing the qualifiers. This custom part form is
 * referenced in the plugin.xml to be used in place of the generated one
 * {@link QualifiersPropertiesEditionPartForm}.
 * 
 * @author <a href="mailto:frederic.bats@obeo.fr">Frederic Bats</a>
 */
public class QualifiersCustomPropertiesEditionPartForm extends QualifiersPropertiesEditionPartForm {

    final TableLabelService labelService = new TableLabelService();

    final TableColumnService tableColumnService = new TableColumnService();

    public QualifiersCustomPropertiesEditionPartForm() {
        super();
    }

    public QualifiersCustomPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
        super(editionComponent);
    }

    // Part of this code was just copied from the generated class in order to
    // override the label provider
    @Override
    protected Composite createQualifiersTableComposition(FormToolkit widgetFactory, Composite parent) {
        this.qualifiers = new ReferencesTable(getDescription(UmlViewsRepository.Qualifiers.qualifiers_, UmlMessages.QualifiersPropertiesEditionPart_QualifiersLabel), new ReferencesTableListener() {
            public void handleAdd() {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(QualifiersCustomPropertiesEditionPartForm.this, UmlViewsRepository.Qualifiers.qualifiers_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
                qualifiers.refresh();
            }

            public void handleEdit(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(QualifiersCustomPropertiesEditionPartForm.this, UmlViewsRepository.Qualifiers.qualifiers_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
                qualifiers.refresh();
            }

            public void handleMove(EObject element, int oldIndex, int newIndex) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(QualifiersCustomPropertiesEditionPartForm.this, UmlViewsRepository.Qualifiers.qualifiers_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
                qualifiers.refresh();
            }

            public void handleRemove(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(QualifiersCustomPropertiesEditionPartForm.this, UmlViewsRepository.Qualifiers.qualifiers_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
                qualifiers.refresh();
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
        for (ViewerFilter filter : this.qualifiersFilters) {
            this.qualifiers.addFilter(filter);
        }
        this.qualifiers.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Qualifiers.qualifiers_, UmlViewsRepository.FORM_KIND));
        this.qualifiers.createControls(parent, widgetFactory);
        this.qualifiers.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                if (e.item != null && e.item.getData() instanceof EObject) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(QualifiersCustomPropertiesEditionPartForm.this, UmlViewsRepository.Qualifiers.qualifiers_,
                            PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
                }
            }

        });
        GridData qualifiersData = new GridData(GridData.FILL_HORIZONTAL);
        qualifiersData.horizontalSpan = 3;
        this.qualifiers.setLayoutData(qualifiersData);
        this.qualifiers.setLowerBound(0);
        this.qualifiers.setUpperBound(-1);
        qualifiers.setID(UmlViewsRepository.Qualifiers.qualifiers_);
        qualifiers.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
        // Start of user code for createQualifiersTableComposition
        Table table = qualifiers.getTable();
        TableColumn[] columns = table.getColumns();
        // dispose
        for (TableColumn tableColumn : columns) {
            tableColumn.dispose();
        }

        table.setHeaderVisible(true);
        tableColumnService.createMediumColumn(table, TableColumnName.NAME);
        tableColumnService.createMediumColumn(table, TableColumnName.TYPE);

        // End of user code
        return parent;
    }

}
