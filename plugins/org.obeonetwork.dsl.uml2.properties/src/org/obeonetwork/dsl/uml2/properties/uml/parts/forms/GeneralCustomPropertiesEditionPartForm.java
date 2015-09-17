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
import org.eclipse.emf.eef.runtime.ui.widgets.HorizontalBox;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.CheckboxUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.MemberEndsTable;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.MemberEndsTable.MemberEndsTableListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Property;
import org.obeonetwork.dsl.uml2.properties.service.TableColumnService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService.TableColumnName;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;

/**
 * Customize the general properties tab to create a checked box table for
 * association member ends.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class GeneralCustomPropertiesEditionPartForm extends GeneralPropertiesEditionPartForm {

    final TableLabelService labelService = new TableLabelService();

    final TableColumnService tableColumnService = new TableColumnService();

    public GeneralCustomPropertiesEditionPartForm() {
        super();
    }

    /**
     * Default constructor
     * 
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     * @generated
     */
    public GeneralCustomPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
        super(editionComponent);
    }

    @Override
    protected Composite createMemberEndReferencesTable(FormToolkit widgetFactory, Composite parent) {
        this.memberEnd = new MemberEndsTable(getDescription(UmlViewsRepository.General.memberEnd, UmlMessages.GeneralPropertiesEditionPart_MemberEndLabel), new MemberEndsTableListener() {
            public void handleAdd() {
                addMemberEnd();
            }

            public void handleEdit(EObject element) {
                editMemberEnd(element);
            }

            public void handleMove(EObject element, int oldIndex, int newIndex) {
                moveMemberEnd(element, oldIndex, newIndex);
            }

            public void handleRemove(EObject element) {
                removeFromMemberEnd(element);
            }

            public void navigateTo(EObject element) {
            }

            public void handleCheckNavigableEnds(EObject element) {
                addToNavigableOwnedEnds(element);
            }

            public void handleUncheckNavigableEnds(EObject element) {
                addToOwnedEnds(element, CustomUmlViewsRepository.General.memberEndNavigable, true);
            }

            public void handleCheckOwnedEnds(EObject element) {
                addToOwnedEnds(element, CustomUmlViewsRepository.General.memberEndOwned, false);
            }

            public void handleUncheckOwnedEnds(EObject element) {
                addToClassifier(element);
            }
        }) {
            @Override
            public AdapterFactoryLabelProvider getLabelProvider() {
                return new AdapterFactoryLabelProvider(adapterFactory) {
                    @Override
                    public String getColumnText(Object object, int columnIndex) {

                        if (object instanceof Property) {
                            Property property = (Property) object;
                            if (columnIndex == tableColumnService.indexOf(TableColumnName.PROPERTY)) {
                                return labelService.caseProperty(property);
                            }
                            if (columnIndex == tableColumnService.indexOf(TableColumnName.NAVIGABILITY)) {
                                return "";
                            }
                            if (columnIndex == tableColumnService.indexOf(TableColumnName.OWNED)) {
                                return "";
                            }
                        }
                        return super.getColumnText(object, columnIndex);
                    }

                    @Override
                    public Image getColumnImage(Object object, int columnIndex) {
                        if (columnIndex == tableColumnService.indexOf(TableColumnName.PROPERTY)) {
                            return super.getColumnImage(object, columnIndex);
                        }
                        if (columnIndex == tableColumnService.indexOf(TableColumnName.NAVIGABILITY)) {
                            return CheckboxUtils.getCheckboxImage(labelService.caseNavigability(object));
                        }
                        if (columnIndex == tableColumnService.indexOf(TableColumnName.OWNED)) {
                            return CheckboxUtils.getCheckboxImage(labelService.caseOwned(object));
                        }
                        return null;
                    }
                };
            }
        };
        this.memberEnd.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.memberEnd, UmlViewsRepository.FORM_KIND));
        this.memberEnd.createControls(parent, widgetFactory);
        this.memberEnd.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                if (e.item != null && e.item.getData() instanceof EObject) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralCustomPropertiesEditionPartForm.this, UmlViewsRepository.General.memberEnd,
                            PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
                }
            }

        });
        GridData memberEndData = new GridData(GridData.FILL_HORIZONTAL);
        memberEndData.horizontalSpan = 3;
        this.memberEnd.setLayoutData(memberEndData);
        this.memberEnd.disableMove();
        memberEnd.setID(UmlViewsRepository.General.memberEnd);
        memberEnd.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
        // Start of user code for createMemberEndReferencesTable
        Table table = memberEnd.getTable();
        TableColumn[] columns = table.getColumns();
        // dispose
        for (TableColumn tableColumn : columns) {
            tableColumn.dispose();
        }

        table.setHeaderVisible(true);
        tableColumnService.createMediumColumn(table, TableColumnName.PROPERTY);
        tableColumnService.createMediumColumn(table, TableColumnName.NAVIGABILITY);
        tableColumnService.createSmallColumn(table, TableColumnName.OWNED);

        // End of user code
        return parent;
    }

    /**
     * Set property to association navigable owned ends.
     * 
     * @param element
     *            Property
     */
    private void addToNavigableOwnedEnds(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralCustomPropertiesEditionPartForm.this, CustomUmlViewsRepository.General.memberEndNavigable,
                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, false, element));
        memberEnd.refresh();
    }

    /**
     * Set property to association owned ends.
     * 
     * @param element
     *            Property
     */
    private void addToOwnedEnds(EObject element, String memberEndKind, boolean wasOwned) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralCustomPropertiesEditionPartForm.this, memberEndKind, PropertiesEditionEvent.COMMIT,
                PropertiesEditionEvent.EDIT, wasOwned, element));
        memberEnd.refresh();
    }

    /**
     * Set property to a classifier.
     * 
     * @param element
     *            Property
     */
    private void addToClassifier(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralCustomPropertiesEditionPartForm.this, CustomUmlViewsRepository.General.memberEndOwned,
                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, true, element));
        memberEnd.refresh();
    }

    @Override
    protected Composite createQualifiersHBox(FormToolkit widgetFactory, Composite parent) {
        HorizontalBox qualifiersHBox = (HorizontalBox) super.createQualifiersHBox(widgetFactory, parent);
        qualifiersHBox.setEditorCount(15);
        return qualifiersHBox;
    }
}
