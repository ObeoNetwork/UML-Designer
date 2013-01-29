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
import org.eclipse.uml2.uml.Operation;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService.TableColumnName;
import org.obeonetwork.dsl.uml2.properties.service.TableColumnService;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;

/**
 * Customize the table representing the operations. This custom part form is
 * referenced in the plugin.xml to be used in place of the generated one
 * {@link OperationsPropertiesEditionPartForm}.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class OperationsCustomPropertiesEditionPartForm extends
		OperationsPropertiesEditionPartForm {
	final TableLabelService labelService = new TableLabelService();
	final TableColumnService tableColumnService = new TableColumnService();

	public OperationsCustomPropertiesEditionPartForm() {
		super();
	}

	public OperationsCustomPropertiesEditionPartForm(
			IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	// Part of this code was just copied from the generated class in order to
	// override the label provider
	@Override
	protected Composite createOperationsTableComposition(
			FormToolkit widgetFactory, Composite parent) {
		this.operations = new ReferencesTable(getDescription(
				UmlViewsRepository.Operations.operations_,
				UmlMessages.OperationsPropertiesEditionPart_OperationsLabel),
				new ReferencesTableListener() {
					public void handleAdd() {
						propertiesEditionComponent
								.firePropertiesChanged(new PropertiesEditionEvent(
										OperationsCustomPropertiesEditionPartForm.this,
										UmlViewsRepository.Operations.operations_,
										PropertiesEditionEvent.COMMIT,
										PropertiesEditionEvent.ADD, null, null));
						operations.refresh();
					}

					public void handleEdit(EObject element) {
						propertiesEditionComponent
								.firePropertiesChanged(new PropertiesEditionEvent(
										OperationsCustomPropertiesEditionPartForm.this,
										UmlViewsRepository.Operations.operations_,
										PropertiesEditionEvent.COMMIT,
										PropertiesEditionEvent.EDIT, null,
										element));
						operations.refresh();
					}

					public void handleMove(EObject element, int oldIndex,
							int newIndex) {
						propertiesEditionComponent
								.firePropertiesChanged(new PropertiesEditionEvent(
										OperationsCustomPropertiesEditionPartForm.this,
										UmlViewsRepository.Operations.operations_,
										PropertiesEditionEvent.COMMIT,
										PropertiesEditionEvent.MOVE, element,
										newIndex));
						operations.refresh();
					}

					public void handleRemove(EObject element) {
						propertiesEditionComponent
								.firePropertiesChanged(new PropertiesEditionEvent(
										OperationsCustomPropertiesEditionPartForm.this,
										UmlViewsRepository.Operations.operations_,
										PropertiesEditionEvent.COMMIT,
										PropertiesEditionEvent.REMOVE, null,
										element));
						operations.refresh();
					}

					public void navigateTo(EObject element) {
					}

				}) {

			@Override
			public AdapterFactoryLabelProvider getLabelProvider() {
				return new AdapterFactoryLabelProvider(adapterFactory) {

					@Override
					public String getColumnText(Object object, int columnIndex) {
						if (object instanceof Operation) {
							Operation operation = (Operation) object;
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.NAME)) {
								return labelService.caseName(operation);
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.VISIBILITY)) {
								return labelService.caseVisibility(operation);
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.IS_STATIC)) {
								return labelService.caseIsStatic(operation);
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.IS_ABSTRACT)) {
								return labelService.caseIsAbstract(operation);
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.TYPE)) {
								return labelService.caseType(operation);
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.PARAMETERS)) {
								return labelService.caseParameters(operation);
							}
						}
						return super.getColumnText(object, columnIndex);
					}

					@Override
					public Image getColumnImage(Object object, int columnIndex) {
						if (object instanceof Operation) {
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.NAME)) {
								return super
										.getColumnImage(object, columnIndex);
							}
							return null;
						}
						return super.getColumnImage(object, columnIndex);
					}

				};
			}

		};

		for (ViewerFilter filter : this.operationsFilters) {
			this.operations.addFilter(filter);
		}
		this.operations.setHelpText(propertiesEditionComponent.getHelpContent(
				UmlViewsRepository.Operations.operations_,
				UmlViewsRepository.FORM_KIND));
		this.operations.createControls(parent, widgetFactory);
		this.operations.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									OperationsCustomPropertiesEditionPartForm.this,
									UmlViewsRepository.Operations.operations_,
									PropertiesEditionEvent.CHANGE,
									PropertiesEditionEvent.SELECTION_CHANGED,
									null, e.item.getData()));
				}
			}

		});
		GridData operationsData = new GridData(GridData.FILL_HORIZONTAL);
		operationsData.horizontalSpan = 3;
		this.operations.setLayoutData(operationsData);
		this.operations.setLowerBound(0);
		this.operations.setUpperBound(-1);
		operations.setID(UmlViewsRepository.Operations.operations_);
		operations.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$

		// Start of user code for createOperationsTableComposition
		Table table = operations.getTable();
		TableColumn[] columns = table.getColumns();
		// dispose
		for (TableColumn tableColumn : columns) {
			tableColumn.dispose();
		}

		table.setHeaderVisible(true);
		tableColumnService.createMediumColumn(table, TableColumnName.NAME);
		tableColumnService.createMediumColumn(table, TableColumnName.VISIBILITY);
		tableColumnService.createSmallColumn(table, TableColumnName.IS_STATIC);
		tableColumnService.createSmallColumn(table, TableColumnName.IS_ABSTRACT);
		tableColumnService.createMediumColumn(table, TableColumnName.TYPE);
		tableColumnService.createLargeColumn(table, TableColumnName.PARAMETERS);

		// End of user code

		return parent;
	}

}
