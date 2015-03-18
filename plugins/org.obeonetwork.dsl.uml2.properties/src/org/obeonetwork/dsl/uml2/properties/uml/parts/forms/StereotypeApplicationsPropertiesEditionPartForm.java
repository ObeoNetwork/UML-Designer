/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.OperationsTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.OperationsTableSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.obeonetwork.dsl.uml2.properties.parts.CustomSectionPropertiesEditingPart;
import org.obeonetwork.dsl.uml2.properties.service.TableColumnService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService.TableColumnName;
import org.obeonetwork.dsl.uml2.properties.uml.components.StereotypeApplicationsPropertiesEditionComponent.StereotypeApplication.StereotypeApplicationProperty;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.StereotypeApplicationsPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.CustomUmlMessages;

/**
 * @author <a href="mailto:cedric.notot@obeo.fr">Cédric Notot</a>
 */
public class StereotypeApplicationsPropertiesEditionPartForm extends
		CustomSectionPropertiesEditingPart implements
		IFormPropertiesEditionPart, StereotypeApplicationsPropertiesEditionPart {

	protected ReferencesTable stereotypeApplications;
	private TableLabelService labelService;
	private final TableColumnService tableColumnService = new TableColumnService();

	/**
	 * For {@link ISection} use only.
	 */
	public StereotypeApplicationsPropertiesEditionPartForm() {
		super();
	}

	/**
	 * Default constructor
	 * 
	 * @param editionComponent
	 *            the {@link IPropertiesEditionComponent} that manage this part
	 */
	public StereotypeApplicationsPropertiesEditionPartForm(
			IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
	 *      createFigure(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	public Composite createFigure(final Composite parent,
			final FormToolkit widgetFactory) {
		ScrolledForm scrolledForm = widgetFactory.createScrolledForm(parent);
		Form form = scrolledForm.getForm();
		view = form.getBody();
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		view.setLayout(layout);
		createControls(widgetFactory, view);
		return scrolledForm;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
	 *      createControls(org.eclipse.ui.forms.widgets.FormToolkit,
	 *      org.eclipse.swt.widgets.Composite)
	 */
	public void createControls(final FormToolkit widgetFactory, Composite view) {
		CompositionSequence stereotypesStep = new BindingCompositionSequence(
				propertiesEditionComponent);
		stereotypesStep
				.addStep(CustomUmlViewsRepository.StereotypeApplications.stereotypeApplications);

		composer = new PartComposer(stereotypesStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == CustomUmlViewsRepository.StereotypeApplications.stereotypeApplications) {
					return createStereotypeApplicationsReferencesTable(
							widgetFactory, parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}

	/**
	 * @param widgetFactory
	 * @param parent
	 * @return
	 */
	protected Composite createStereotypeApplicationsReferencesTable(
			FormToolkit widgetFactory, Composite parent) {

		final ReferencesTableListener referencesTableListener = new ReferencesTableListener() {

			public void handleEdit(EObject element) {
				editStereotypeApplication(element);
			}

			public void navigateTo(EObject element) {
			}

			public void handleAdd() {
				// TODO Auto-generated method stub

			}

			public void handleRemove(EObject element) {
				// TODO Auto-generated method stub

			}

			public void handleMove(EObject element, int oldIndex, int newIndex) {
				// TODO Auto-generated method stub

			}

		};

		this.stereotypeApplications = new ReferencesTable(
				getDescription(
						CustomUmlViewsRepository.StereotypeApplications.stereotypeApplications,
						CustomUmlMessages.StereotypeApplicationsPropertiesEditionPart_StereotypeApplicationsLabel),
				referencesTableListener) {

			@Override
			public void createControls(Composite parent) {
				super.createControls(parent);
				addButton.setVisible(false);
				removeButton.setVisible(false);
			}

			@Override
			public AdapterFactoryLabelProvider getLabelProvider() {
				return new AdapterFactoryLabelProvider(adapterFactory) {
					@Override
					public String getColumnText(Object object, int columnIndex) {

						if (object instanceof StereotypeApplicationProperty) {

							StereotypeApplicationProperty property = (StereotypeApplicationProperty) object;

							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.STEREOTYPE)) {
								return labelService.caseStereotype(property
										.getStereotype());
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.ESTRUCTURAL_FEATURE)) {
								return labelService
										.caseEStructuralFeature(property
												.getFeature());
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.VALUE)) {
								return labelService
										.caseStereotypeApplicationPropertyValue(
												property,
												new AdapterFactoryLabelProvider(
														adapterFactory));
							}
						}
						return "";
					}

					@Override
					public Image getColumnImage(Object object, int columnIndex) {
						return null;
					}
				};
			}
		};
		this.stereotypeApplications
				.setHelpText(propertiesEditionComponent
						.getHelpContent(
								CustomUmlViewsRepository.StereotypeApplications.stereotypeApplications,
								UmlViewsRepository.FORM_KIND));
		this.stereotypeApplications.createControls(parent, widgetFactory);
		this.stereotypeApplications
				.addSelectionListener(new SelectionAdapter() {

					public void widgetSelected(SelectionEvent e) {
						if (e.item != null
								&& e.item.getData() instanceof EObject) {
							propertiesEditionComponent
									.firePropertiesChanged(new PropertiesEditionEvent(
											StereotypeApplicationsPropertiesEditionPartForm.this,
											CustomUmlViewsRepository.StereotypeApplications.stereotypeApplications,
											PropertiesEditionEvent.CHANGE,
											PropertiesEditionEvent.SELECTION_CHANGED,
											null, e.item.getData()));
						}
					}

				});
		GridData stereotypeApplicationsData = new GridData(
				GridData.FILL_HORIZONTAL);
		stereotypeApplicationsData.horizontalSpan = 3;
		this.stereotypeApplications.setLayoutData(stereotypeApplicationsData);
		this.stereotypeApplications.disableMove();
		stereotypeApplications
				.setID(CustomUmlViewsRepository.StereotypeApplications.stereotypeApplications);
		stereotypeApplications.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$

		final Table table = stereotypeApplications.getTable();
		TableColumn[] columns = table.getColumns();
		// dispose
		for (TableColumn tableColumn : columns) {
			tableColumn.dispose();
		}

		table.setHeaderVisible(true);
		tableColumnService
				.createMediumColumn(table, TableColumnName.STEREOTYPE);
		tableColumnService.createMediumColumn(table,
				TableColumnName.ESTRUCTURAL_FEATURE);
		tableColumnService.createMediumColumn(table, TableColumnName.VALUE);

		table.addListener(SWT.MouseDoubleClick, new Listener() {
			/**
			 * @{inheritDoc
			 */
			public void handleEvent(Event event) {
				if (table.getSelection().length > 0) {
					TableItem item = table.getSelection()[0];
					if (item.getData() instanceof StereotypeApplicationProperty) {
						// Edit
						editStereotypeApplication(item.getData());
					}
				}
			}
		});

		return parent;
	}

	/**
	 * @param element
	 */
	protected void editStereotypeApplication(Object element) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						StereotypeApplicationsPropertiesEditionPartForm.this,
						CustomUmlViewsRepository.StereotypeApplications.stereotypeApplications,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.EDIT, null, element));
		stereotypeApplications.refresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StereotypeApplicationsPropertiesEditionPart#updateStereotypeApplications()
	 */
	public void updateStereotypeApplications() {
		stereotypeApplications.refresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 */
	public String getTitle() {
		return CustomUmlMessages.StereotypeApplications_Part_Title;
	}

	/**
	 * {@inheritDoc}
	 */
	public void initStereotypeApplications(OperationsTableSettings settings) {
		if (current.eResource() != null
				&& current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		labelService = new TableLabelService(settings.getSource());
		OperationsTableContentProvider contentProvider = new OperationsTableContentProvider();
		stereotypeApplications.setContentProvider(contentProvider);
		stereotypeApplications.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(CustomUmlMessages.StereotypeApplicationsPropertiesEditionPart_StereotypeApplicationsLabel);
		if (eefElementEditorReadOnlyState && stereotypeApplications.isEnabled()) {
			stereotypeApplications.setEnabled(false);
			stereotypeApplications
					.setToolTipText(CustomUmlMessages.Stereotypes_ReadOnly);
		} else if (!eefElementEditorReadOnlyState
				&& !stereotypeApplications.isEnabled()) {
			stereotypeApplications.setEnabled(true);
		}

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart#getDescription(java.lang.Object,
	 *      java.lang.String)
	 */
	@Override
	protected String getDescription(Object editor, String alternate) {
		return CustomUmlMessages.StereotypeApplicationsPropertiesEditionPart_StereotypeApplicationsLabel;
	}

}
