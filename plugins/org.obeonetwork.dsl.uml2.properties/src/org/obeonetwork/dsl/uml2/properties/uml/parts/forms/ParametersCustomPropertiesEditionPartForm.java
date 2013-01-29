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
import org.eclipse.uml2.uml.Parameter;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService.TableColumnName;
import org.obeonetwork.dsl.uml2.properties.service.TableColumnService;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;

/**
 * Customize the table representing the parameters. This custom part form is
 * referenced in the plugin.xml to be used in place of the generated one
 * {@link ParametersPropertiesEditionPartForm}.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class ParametersCustomPropertiesEditionPartForm extends
		ParametersPropertiesEditionPartForm {

	final TableLabelService labelService = new TableLabelService();
	final TableColumnService tableColumnService = new TableColumnService();

	public ParametersCustomPropertiesEditionPartForm() {
		super();
	}

	public ParametersCustomPropertiesEditionPartForm(
			IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	// Part of this code was just copied from the generated class in order to
	// override the label provider
	@Override
	protected Composite createParametersTableComposition(
			FormToolkit widgetFactory, Composite parent) {
		this.parameters = new ReferencesTable(getDescription(
				UmlViewsRepository.Parameters.parameters_,
				UmlMessages.ParametersPropertiesEditionPart_ParametersLabel),
				new ReferencesTableListener() {
					public void handleAdd() {
						propertiesEditionComponent
								.firePropertiesChanged(new PropertiesEditionEvent(
										ParametersCustomPropertiesEditionPartForm.this,
										UmlViewsRepository.Parameters.parameters_,
										PropertiesEditionEvent.COMMIT,
										PropertiesEditionEvent.ADD, null, null));
						parameters.refresh();
					}

					public void handleEdit(EObject element) {
						propertiesEditionComponent
								.firePropertiesChanged(new PropertiesEditionEvent(
										ParametersCustomPropertiesEditionPartForm.this,
										UmlViewsRepository.Parameters.parameters_,
										PropertiesEditionEvent.COMMIT,
										PropertiesEditionEvent.EDIT, null,
										element));
						parameters.refresh();
					}

					public void handleMove(EObject element, int oldIndex,
							int newIndex) {
						propertiesEditionComponent
								.firePropertiesChanged(new PropertiesEditionEvent(
										ParametersCustomPropertiesEditionPartForm.this,
										UmlViewsRepository.Parameters.parameters_,
										PropertiesEditionEvent.COMMIT,
										PropertiesEditionEvent.MOVE, element,
										newIndex));
						parameters.refresh();
					}

					public void handleRemove(EObject element) {
						propertiesEditionComponent
								.firePropertiesChanged(new PropertiesEditionEvent(
										ParametersCustomPropertiesEditionPartForm.this,
										UmlViewsRepository.Parameters.parameters_,
										PropertiesEditionEvent.COMMIT,
										PropertiesEditionEvent.REMOVE, null,
										element));
						parameters.refresh();
					}

					public void navigateTo(EObject element) {
					}
				}) {
			@Override
			public AdapterFactoryLabelProvider getLabelProvider() {

				return new AdapterFactoryLabelProvider(adapterFactory) {
					@Override
					public String getColumnText(Object object, int columnIndex) {
						if (object instanceof Parameter) {
							Parameter parameter = (Parameter) object;
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.NAME)) {
								return labelService.caseName(parameter);
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.DIRECTION)) {
								return labelService.caseDirection(parameter);
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.TYPE)) {
								return labelService.caseType(parameter);
							}
						}

						return super.getColumnText(object, columnIndex);
					}

					@Override
					public Image getColumnImage(Object object, int columnIndex) {
						if (object instanceof Parameter) {
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
		for (ViewerFilter filter : this.parametersFilters) {
			this.parameters.addFilter(filter);
		}
		this.parameters.setHelpText(propertiesEditionComponent.getHelpContent(
				UmlViewsRepository.Parameters.parameters_,
				UmlViewsRepository.FORM_KIND));
		this.parameters.createControls(parent, widgetFactory);
		this.parameters.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									ParametersCustomPropertiesEditionPartForm.this,
									UmlViewsRepository.Parameters.parameters_,
									PropertiesEditionEvent.CHANGE,
									PropertiesEditionEvent.SELECTION_CHANGED,
									null, e.item.getData()));
				}
			}

		});
		GridData parametersData = new GridData(GridData.FILL_HORIZONTAL);
		parametersData.horizontalSpan = 3;
		this.parameters.setLayoutData(parametersData);
		this.parameters.setLowerBound(0);
		this.parameters.setUpperBound(-1);
		parameters.setID(UmlViewsRepository.Parameters.parameters_);
		parameters.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		// Start of user code for createParametersTableComposition
		Table table = parameters.getTable();
		TableColumn[] columns = table.getColumns();
		// dispose
		for (TableColumn tableColumn : columns) {
			tableColumn.dispose();
		}

		table.setHeaderVisible(true);
		tableColumnService.createMediumColumn(table, TableColumnName.NAME);
		tableColumnService.createMediumColumn(table, TableColumnName.DIRECTION);
		tableColumnService.createMediumColumn(table, TableColumnName.TYPE);

		// End of user code
		return parent;
	}

}
