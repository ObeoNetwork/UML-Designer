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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.widgets.CustomReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.OperationsTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.OperationsTableSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.uml2.uml.Relationship;
import org.obeonetwork.dsl.uml2.properties.parts.CustomSectionPropertiesEditingPart;
import org.obeonetwork.dsl.uml2.properties.service.TableColumnService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService.TableColumnName;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.RelationshipsPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.CustomUmlMessages;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class RelationshipsPropertiesEditionPartForm extends
		CustomSectionPropertiesEditingPart implements
		IFormPropertiesEditionPart, RelationshipsPropertiesEditionPart {

	protected CustomReferencesTable relationshipsOriginating;
	protected List<ViewerFilter> relationshipsOriginatingBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> relationshipsOriginatingFilters = new ArrayList<ViewerFilter>();
	protected CustomReferencesTable relationshipsTargeting;
	protected List<ViewerFilter> relationshipsTargetingBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> relationshipsTargetingFilters = new ArrayList<ViewerFilter>();

	private TableLabelService labelService;
	private final TableColumnService tableColumnService = new TableColumnService();

	/**
	 * For {@link ISection} use only.
	 */
	public RelationshipsPropertiesEditionPartForm() {
		super();
	}

	/**
	 * Default constructor
	 * 
	 * @param editionComponent
	 *            the {@link IPropertiesEditionComponent} that manage this part
	 */
	public RelationshipsPropertiesEditionPartForm(
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
		CompositionSequence relationshipsStep = new BindingCompositionSequence(
				propertiesEditionComponent);
		relationshipsStep
				.addStep(CustomUmlViewsRepository.Relationships.relationshipsOriginating);
		relationshipsStep
				.addStep(CustomUmlViewsRepository.Relationships.relationshipsTargeting);

		composer = new PartComposer(relationshipsStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == CustomUmlViewsRepository.Relationships.relationshipsOriginating) {
					return createRelationshipsReferencesTable(widgetFactory,
							parent);
				}
				if (key == CustomUmlViewsRepository.Relationships.relationshipsTargeting) {
					return createRelationshipsTargetingReferencesTable(
							widgetFactory, parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}

	protected Composite createRelationshipsReferencesTable(
			FormToolkit widgetFactory, Composite parent) {
		this.relationshipsOriginating = new CustomReferencesTable(
				getDescription(
						CustomUmlViewsRepository.Relationships.relationshipsOriginating,
						CustomUmlMessages.RelationshipsPropertiesEditionPart_RelationshipsOriginatingLabel),
				new ReferencesTableListener() {
					public void handleAdd() {
						addRelationshipsOriginating();
					}

					public void handleEdit(EObject element) {
						editRelationshipsOriginating(element);
					}

					public void handleMove(EObject element, int oldIndex,
							int newIndex) {
						moveRelationshipsOriginating(element, oldIndex,
								newIndex);
					}

					public void handleRemove(EObject element) {
						removeFromRelationshipsOriginating(element);
					}

					public void navigateTo(EObject element) {
					}
				}) {
			@Override
			public AdapterFactoryLabelProvider getLabelProvider() {
				return new AdapterFactoryLabelProvider(adapterFactory) {
					@Override
					public String getColumnText(Object object, int columnIndex) {
						if (object instanceof Relationship) {
							Relationship relationships = (Relationship) object;
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.NAME)) {
								return labelService.caseName(relationships);
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.TYPE)) {
								return labelService.caseType(relationships);
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.OTHER_RELATED_ELEMENTS)) {
								return labelService
										.caseOtherRelatedElements(relationships);
							}
						}
						return "";
					}

					@Override
					public Image getColumnImage(Object object, int columnIndex) {
						if (object != null && object instanceof Relationship) {
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.NAME)) {
								return super
										.getColumnImage(object, columnIndex);
							}
						}
						return null;
					}
				};
			}
		};
		this.relationshipsOriginating
				.setHelpText(propertiesEditionComponent
						.getHelpContent(
								CustomUmlViewsRepository.Relationships.relationshipsOriginating,
								UmlViewsRepository.FORM_KIND));
		this.relationshipsOriginating.createControls(parent, widgetFactory);
		this.relationshipsOriginating
				.addSelectionListener(new SelectionAdapter() {

					public void widgetSelected(SelectionEvent e) {
						if (e.item != null
								&& e.item.getData() instanceof EObject) {
							propertiesEditionComponent
									.firePropertiesChanged(new PropertiesEditionEvent(
											RelationshipsPropertiesEditionPartForm.this,
											CustomUmlViewsRepository.Relationships.relationshipsOriginating,
											PropertiesEditionEvent.CHANGE,
											PropertiesEditionEvent.SELECTION_CHANGED,
											null, e.item.getData()));
						}
					}

				});
		GridData relationshipsData = new GridData(GridData.FILL_HORIZONTAL);
		relationshipsData.horizontalSpan = 3;
		this.relationshipsOriginating.setLayoutData(relationshipsData);
		this.relationshipsOriginating.disableMove();

		relationshipsOriginating
				.setID(CustomUmlViewsRepository.Relationships.relationshipsOriginating);
		relationshipsOriginating.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		// Start of user code for createRelationshipsOriginatingReferencesTable
		this.relationshipsOriginating.disableButtons();
		Table table = relationshipsOriginating.getTable();
		TableColumn[] columns = table.getColumns();
		// dispose
		for (TableColumn tableColumn : columns) {
			tableColumn.dispose();
		}

		table.setHeaderVisible(true);
		tableColumnService.createMediumColumn(table, TableColumnName.NAME);
		tableColumnService.createMediumColumn(table, TableColumnName.TYPE);
		tableColumnService.createMediumColumn(table,
				TableColumnName.OTHER_RELATED_ELEMENTS);
		// End of user code
		return parent;
	}

	protected void addRelationshipsOriginating() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(
				relationshipsOriginating.getInput(),
				relationshipsOriginatingFilters,
				relationshipsOriginatingBusinessFilters,
				"relationshipsOriginating", propertiesEditionComponent
						.getEditingContext().getAdapterFactory(),
				current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									RelationshipsPropertiesEditionPartForm.this,
									CustomUmlViewsRepository.Relationships.relationshipsOriginating,
									PropertiesEditionEvent.COMMIT,
									PropertiesEditionEvent.ADD, null, elem));
				}
				relationshipsOriginating.refresh();
			}
		};
		dialog.open();
	}

	protected void moveRelationshipsOriginating(EObject element, int oldIndex,
			int newIndex) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						RelationshipsPropertiesEditionPartForm.this,
						CustomUmlViewsRepository.Relationships.relationshipsOriginating,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.MOVE, element, newIndex));
		relationshipsOriginating.refresh();
	}

	protected void removeFromRelationshipsOriginating(EObject element) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						RelationshipsPropertiesEditionPartForm.this,
						CustomUmlViewsRepository.Relationships.relationshipsOriginating,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.REMOVE, null, element));
		relationshipsOriginating.refresh();
	}

	protected void editRelationshipsOriginating(EObject element) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						RelationshipsPropertiesEditionPartForm.this,
						CustomUmlViewsRepository.Relationships.relationshipsOriginating,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.EDIT, null, element));
		relationshipsOriginating.refresh();
	}

	protected Composite createRelationshipsTargetingReferencesTable(
			FormToolkit widgetFactory, Composite parent) {
		this.relationshipsTargeting = new CustomReferencesTable(
				getDescription(
						CustomUmlViewsRepository.Relationships.relationshipsTargeting,
						CustomUmlMessages.RelationshipsPropertiesEditionPart_RelationshipsTargetingLabel),
				new ReferencesTableListener() {
					public void handleAdd() {
						addRelationshipsTargeting();
					}

					public void handleEdit(EObject element) {
						editRelationshipsTargeting(element);
					}

					public void handleMove(EObject element, int oldIndex,
							int newIndex) {
						moveRelationshipsTargeting(element, oldIndex, newIndex);
					}

					public void handleRemove(EObject element) {
						removeFromRelationshipsTargeting(element);
					}

					public void navigateTo(EObject element) {
					}
				}) {
			@Override
			public AdapterFactoryLabelProvider getLabelProvider() {
				return new AdapterFactoryLabelProvider(adapterFactory) {
					@Override
					public String getColumnText(Object object, int columnIndex) {
						if (object instanceof Relationship) {
							Relationship relationships = (Relationship) object;
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.NAME)) {
								return labelService.caseName(relationships);
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.TYPE)) {
								return labelService.caseType(relationships);
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.OTHER_RELATED_ELEMENTS)) {
								return labelService
										.caseOtherRelatedElements(relationships);
							}
						}
						return "";
					}

					@Override
					public Image getColumnImage(Object object, int columnIndex) {
						if (object instanceof Relationship) {
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.NAME)) {
								return super
										.getColumnImage(object, columnIndex);
							}
						}
						return null;
					}
				};
			}
		};
		this.relationshipsTargeting
				.setHelpText(propertiesEditionComponent
						.getHelpContent(
								CustomUmlViewsRepository.Relationships.relationshipsTargeting,
								UmlViewsRepository.FORM_KIND));
		this.relationshipsTargeting.createControls(parent, widgetFactory);
		this.relationshipsTargeting
				.addSelectionListener(new SelectionAdapter() {

					public void widgetSelected(SelectionEvent e) {
						if (e.item != null
								&& e.item.getData() instanceof EObject) {
							propertiesEditionComponent
									.firePropertiesChanged(new PropertiesEditionEvent(
											RelationshipsPropertiesEditionPartForm.this,
											CustomUmlViewsRepository.Relationships.relationshipsTargeting,
											PropertiesEditionEvent.CHANGE,
											PropertiesEditionEvent.SELECTION_CHANGED,
											null, e.item.getData()));
						}
					}

				});
		GridData relationshipsTargetingData = new GridData(
				GridData.FILL_HORIZONTAL);
		relationshipsTargetingData.horizontalSpan = 3;
		this.relationshipsTargeting.setLayoutData(relationshipsTargetingData);
		this.relationshipsTargeting.disableMove();
		relationshipsTargeting
				.setID(CustomUmlViewsRepository.Relationships.relationshipsTargeting);
		relationshipsTargeting.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		// Start of user code for createRelationshipsTargetingReferencesTable
		this.relationshipsTargeting.disableButtons();
		Table table = relationshipsTargeting.getTable();
		TableColumn[] columns = table.getColumns();
		// dispose
		for (TableColumn tableColumn : columns) {
			tableColumn.dispose();
		}

		table.setHeaderVisible(true);
		tableColumnService.createMediumColumn(table, TableColumnName.NAME);
		tableColumnService.createMediumColumn(table, TableColumnName.TYPE);
		tableColumnService.createMediumColumn(table,
				TableColumnName.OTHER_RELATED_ELEMENTS);

		// End of user code
		return parent;
	}

	protected void addRelationshipsTargeting() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(
				relationshipsTargeting.getInput(),
				relationshipsTargetingFilters,
				relationshipsTargetingBusinessFilters,
				"relationshipsTargeting", propertiesEditionComponent
						.getEditingContext().getAdapterFactory(),
				current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									RelationshipsPropertiesEditionPartForm.this,
									CustomUmlViewsRepository.Relationships.relationshipsTargeting,
									PropertiesEditionEvent.COMMIT,
									PropertiesEditionEvent.ADD, null, elem));
				}
				relationshipsTargeting.refresh();
			}
		};
		dialog.open();
	}

	protected void moveRelationshipsTargeting(EObject element, int oldIndex,
			int newIndex) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						RelationshipsPropertiesEditionPartForm.this,
						CustomUmlViewsRepository.Relationships.relationshipsTargeting,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.MOVE, element, newIndex));
		relationshipsTargeting.refresh();
	}

	protected void removeFromRelationshipsTargeting(EObject element) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						RelationshipsPropertiesEditionPartForm.this,
						CustomUmlViewsRepository.Relationships.relationshipsTargeting,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.REMOVE, null, element));
		relationshipsTargeting.refresh();
	}

	protected void editRelationshipsTargeting(EObject element) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						RelationshipsPropertiesEditionPartForm.this,
						CustomUmlViewsRepository.Relationships.relationshipsTargeting,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.EDIT, null, element));
		relationshipsTargeting.refresh();
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RelationshipsPropertiesEditionPart#updateRelationshipsOriginating()
	 */
	public void updateRelationshipsOriginating() {
		relationshipsOriginating.refresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RelationshipsPropertiesEditionPart#addFilterRelationships(ViewerFilter
	 *      filter)
	 */
	public void addFilterToRelationshipsOriginating(ViewerFilter filter) {
		relationshipsOriginatingFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RelationshipsPropertiesEditionPart#addBusinessFilterRelationships(ViewerFilter
	 *      filter)
	 */
	public void addBusinessFilterToRelationshipsOriginating(ViewerFilter filter) {
		relationshipsOriginatingBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RelationshipsPropertiesEditionPart#isContainedInRelationshipsOriginatingTable(EObject
	 *      element)
	 */
	public boolean isContainedInRelationshipsOriginatingTable(EObject element) {
		return ((ReferencesTableSettings) relationshipsOriginating.getInput())
				.contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RelationshipsPropertiesEditionPart#updateRelationshipsTargeting()
	 */
	public void updateRelationshipsTargeting() {
		relationshipsTargeting.refresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RelationshipsPropertiesEditionPart#addFilterRelationshipsTargeting(ViewerFilter
	 *      filter)
	 */
	public void addFilterToRelationshipsTargeting(ViewerFilter filter) {
		relationshipsTargetingFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RelationshipsPropertiesEditionPart#addBusinessFilterRelationshipsTargeting(ViewerFilter
	 *      filter)
	 */
	public void addBusinessFilterToRelationshipsTargeting(ViewerFilter filter) {
		relationshipsTargetingBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RelationshipsPropertiesEditionPart#isContainedInRelationshipsTargetingTable(EObject
	 *      element)
	 */
	public boolean isContainedInRelationshipsTargetingTable(EObject element) {
		return ((ReferencesTableSettings) relationshipsTargeting.getInput())
				.contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 */
	public String getTitle() {
		return CustomUmlMessages.Relationships_Part_Title;
	}

	public void initRelationshipsOriginating(OperationsTableSettings settings) {
		if (current.eResource() != null
				&& current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		labelService = new TableLabelService(settings.getSource());
		OperationsTableContentProvider contentProvider = new OperationsTableContentProvider();
		relationshipsOriginating.setContentProvider(contentProvider);
		relationshipsOriginating.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(CustomUmlViewsRepository.Relationships.relationshipsOriginating);
		if (eefElementEditorReadOnlyState
				&& relationshipsOriginating.getTable().isEnabled()) {
			relationshipsOriginating.setEnabled(false);
			relationshipsOriginating
					.setToolTipText(CustomUmlMessages.Relationships_ReadOnly);
		} else if (!eefElementEditorReadOnlyState
				&& !relationshipsOriginating.getTable().isEnabled()) {
			relationshipsOriginating.setEnabled(true);
		}

	}

	public void initRelationshipsTargeting(OperationsTableSettings settings) {
		if (current.eResource() != null
				&& current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		OperationsTableContentProvider contentProvider = new OperationsTableContentProvider();
		relationshipsTargeting.setContentProvider(contentProvider);
		relationshipsTargeting.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(CustomUmlViewsRepository.Relationships.relationshipsTargeting);
		if (eefElementEditorReadOnlyState
				&& relationshipsTargeting.getTable().isEnabled()) {
			relationshipsTargeting.setEnabled(false);
			relationshipsTargeting
					.setToolTipText(CustomUmlMessages.Relationships_ReadOnly);
		} else if (!eefElementEditorReadOnlyState
				&& !relationshipsTargeting.getTable().isEnabled()) {
			relationshipsTargeting.setEnabled(true);
		}

	}

	@Override
	public String getDescription(Object editor, String alternate) {
		if (editor
				.equals(CustomUmlViewsRepository.Relationships.relationshipsOriginating))
			return CustomUmlMessages.RelationshipsPropertiesEditionPart_RelationshipsOriginatingLabel;
		else
			return CustomUmlMessages.RelationshipsPropertiesEditionPart_RelationshipsTargetingLabel;
	}

}
