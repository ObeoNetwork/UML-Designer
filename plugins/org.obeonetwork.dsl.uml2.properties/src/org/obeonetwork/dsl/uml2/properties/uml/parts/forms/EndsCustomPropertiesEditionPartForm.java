/*******************************************************************************
 * Copyright (c) 2009, 2012 Obeo.
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
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.obeonetwork.dsl.uml2.properties.service.TableColumnService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService.TableColumnName;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;

/**
 * Customize the table representing the attributes. This custom part form is
 * referenced in the plugin.xml to be used in place of the generated one
 * {@link EndsPropertiesEditionPartForm}.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class EndsCustomPropertiesEditionPartForm extends EndsPropertiesEditionPartForm {

    final TableLabelService labelService = new TableLabelService();

    final TableColumnService tableColumnService = new TableColumnService();

    /**
     * For {@link ISection} use only.
     */
    public EndsCustomPropertiesEditionPartForm() {
        super();
    }

    /**
     * Default constructor
     * 
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     * @generated
     */
    public EndsCustomPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
        super(editionComponent);
    }

    // Part of this code was just copied from the generated class in order to
    // override the label provider
    @Override
    protected Composite createEndsTableComposition(FormToolkit widgetFactory, Composite parent) {
        this.ends = new ReferencesTable(getDescription(UmlViewsRepository.Ends.ends_, UmlMessages.EndsPropertiesEditionPart_EndsLabel), new ReferencesTableListener() {
            public void handleAdd() {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EndsCustomPropertiesEditionPartForm.this, UmlViewsRepository.Ends.ends_, PropertiesEditionEvent.COMMIT,
                        PropertiesEditionEvent.ADD, null, null));
                ends.refresh();
            }

            public void handleEdit(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EndsCustomPropertiesEditionPartForm.this, UmlViewsRepository.Ends.ends_, PropertiesEditionEvent.COMMIT,
                        PropertiesEditionEvent.EDIT, null, element));
                ends.refresh();
            }

            public void handleMove(EObject element, int oldIndex, int newIndex) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EndsCustomPropertiesEditionPartForm.this, UmlViewsRepository.Ends.ends_, PropertiesEditionEvent.COMMIT,
                        PropertiesEditionEvent.MOVE, element, newIndex));
                ends.refresh();
            }

            public void handleRemove(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EndsCustomPropertiesEditionPartForm.this, UmlViewsRepository.Ends.ends_, PropertiesEditionEvent.COMMIT,
                        PropertiesEditionEvent.REMOVE, null, element));
                ends.refresh();
            }

            public void navigateTo(EObject element) {
            }
        }) {
            @Override
            public AdapterFactoryLabelProvider getLabelProvider() {
                return new AdapterFactoryLabelProvider(adapterFactory) {
                    @Override
                    public String getColumnText(Object object, int columnIndex) {

                        if (object instanceof ConnectorEnd) {
                            ConnectorEnd connectorEnd = (ConnectorEnd) object;
                            if (columnIndex == tableColumnService.indexOf(TableColumnName.ROLE)) {
                                return labelService.caseRole(connectorEnd);
                            }
                        }
                        return super.getColumnText(object, columnIndex);
                    }

                    @Override
                    public Image getColumnImage(Object object, int columnIndex) {
                        return null;
                    }
                };
            }
        };
        for (ViewerFilter filter : this.endsFilters) {
            this.ends.addFilter(filter);
        }
        this.ends.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Ends.ends_, UmlViewsRepository.FORM_KIND));
        this.ends.createControls(parent, widgetFactory);
        this.ends.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                if (e.item != null && e.item.getData() instanceof EObject) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EndsCustomPropertiesEditionPartForm.this, UmlViewsRepository.Ends.ends_, PropertiesEditionEvent.CHANGE,
                            PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
                }
            }

        });
        GridData endsData = new GridData(GridData.FILL_HORIZONTAL);
        endsData.horizontalSpan = 3;
        this.ends.setLayoutData(endsData);
        this.ends.setLowerBound(0);
        this.ends.setUpperBound(2);
        ends.disableMove();
        ends.setID(UmlViewsRepository.Ends.ends_);
        ends.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
        // Start of user code for createEndsTableComposition
        Table table = ends.getTable();
        TableColumn[] columns = table.getColumns();
        // dispose
        for (TableColumn tableColumn : columns) {
            tableColumn.dispose();
        }

        table.setHeaderVisible(true);
        tableColumnService.createMediumColumn(table, TableColumnName.ROLE);

        // End of user code
        return parent;
    }

}
