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
import org.eclipse.emf.eef.runtime.part.impl.SectionPropertiesEditingPart;
import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
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
import org.eclipse.uml2.uml.Stereotype;
import org.obeonetwork.dsl.uml2.properties.service.TableColumnService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService.TableColumnName;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.StereotypesPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.CustomUmlMessages;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class StereotypesPropertiesEditionPartForm extends
		SectionPropertiesEditingPart implements IFormPropertiesEditionPart,
		StereotypesPropertiesEditionPart {

	protected ReferencesTable appliedStereotypes;
	protected List<ViewerFilter> appliedStereotypesBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> appliedStereotypesFilters = new ArrayList<ViewerFilter>();
	private TableLabelService labelService;
	private final TableColumnService tableColumnService = new TableColumnService();

	/**
	 * For {@link ISection} use only.
	 */
	public StereotypesPropertiesEditionPartForm() {
		super();
	}

	/**
	 * Default constructor
	 * 
	 * @param editionComponent
	 *            the {@link IPropertiesEditionComponent} that manage this part
	 */
	public StereotypesPropertiesEditionPartForm(
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
				.addStep(CustomUmlViewsRepository.Stereotypes.appliedStereotypes);

		composer = new PartComposer(stereotypesStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == CustomUmlViewsRepository.Stereotypes.appliedStereotypes) {
					return createAppliedStereotypesReferencesTable(
							widgetFactory, parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}

	protected Composite createAppliedStereotypesReferencesTable(
			FormToolkit widgetFactory, Composite parent) {
		this.appliedStereotypes = new ReferencesTable(
				getDescription(
						CustomUmlViewsRepository.Stereotypes.appliedStereotypes,
						CustomUmlMessages.StereotypesPropertiesEditionPart_AppliedStereotypesLabel),
				new ReferencesTableListener() {
					public void handleAdd() {
						addAppliedStereotypes();
					}

					public void handleEdit(EObject element) {
						editAppliedStereotypes(element);
					}

					public void handleMove(EObject element, int oldIndex,
							int newIndex) {
						moveAppliedStereotypes(element, oldIndex, newIndex);
					}

					public void handleRemove(EObject element) {
						removeFromAppliedStereotypes(element);
					}

					public void navigateTo(EObject element) {
					}
				}) {
			@Override
			public AdapterFactoryLabelProvider getLabelProvider() {
				return new AdapterFactoryLabelProvider(adapterFactory) {
					@Override
					public String getColumnText(Object object, int columnIndex) {
						if (object instanceof Stereotype) {
							Stereotype stereotype = (Stereotype) object;
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.STEREOTYPE)) {
								return labelService.caseStereotype(stereotype);
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.PROFILE)) {
								return labelService.caseProfile(stereotype);
							}
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.REQUIRED)) {
								return labelService.caseRequired(stereotype);
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
		for (ViewerFilter filter : this.appliedStereotypesFilters) {
			this.appliedStereotypes.addFilter(filter);
		}
		this.appliedStereotypes
				.setHelpText(propertiesEditionComponent
						.getHelpContent(
								CustomUmlViewsRepository.Stereotypes.appliedStereotypes,
								UmlViewsRepository.FORM_KIND));
		this.appliedStereotypes.createControls(parent, widgetFactory);
		this.appliedStereotypes.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									StereotypesPropertiesEditionPartForm.this,
									CustomUmlViewsRepository.Stereotypes.appliedStereotypes,
									PropertiesEditionEvent.CHANGE,
									PropertiesEditionEvent.SELECTION_CHANGED,
									null, e.item.getData()));
				}
			}

		});
		GridData appliedStereotypesData = new GridData(GridData.FILL_HORIZONTAL);
		appliedStereotypesData.horizontalSpan = 3;
		this.appliedStereotypes.setLayoutData(appliedStereotypesData);
		this.appliedStereotypes.disableMove();
		appliedStereotypes
				.setID(CustomUmlViewsRepository.Stereotypes.appliedStereotypes);
		appliedStereotypes.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		// Start of user code for createAppliedStereotypesTableComposition
		Table table = appliedStereotypes.getTable();
		TableColumn[] columns = table.getColumns();
		// dispose
		for (TableColumn tableColumn : columns) {
			tableColumn.dispose();
		}

		table.setHeaderVisible(true);
		tableColumnService
				.createMediumColumn(table, TableColumnName.STEREOTYPE);
		tableColumnService.createMediumColumn(table, TableColumnName.PROFILE);
		tableColumnService.createSmallColumn(table, TableColumnName.REQUIRED);

		// End of user code
		return parent;
	}

	protected void addAppliedStereotypes() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(
				appliedStereotypes.getInput(), appliedStereotypesFilters,
				appliedStereotypesBusinessFilters, "appliedStereotypes",
				propertiesEditionComponent.getEditingContext()
						.getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									StereotypesPropertiesEditionPartForm.this,
									CustomUmlViewsRepository.Stereotypes.appliedStereotypes,
									PropertiesEditionEvent.COMMIT,
									PropertiesEditionEvent.ADD, null, elem));
				}
				appliedStereotypes.refresh();
			}
		};
		dialog.open();
	}

	protected void moveAppliedStereotypes(EObject element, int oldIndex,
			int newIndex) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						StereotypesPropertiesEditionPartForm.this,
						CustomUmlViewsRepository.Stereotypes.appliedStereotypes,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.MOVE, element, newIndex));
		appliedStereotypes.refresh();
	}

	protected void removeFromAppliedStereotypes(EObject element) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						StereotypesPropertiesEditionPartForm.this,
						CustomUmlViewsRepository.Stereotypes.appliedStereotypes,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.REMOVE, null, element));
		appliedStereotypes.refresh();
	}

	protected void editAppliedStereotypes(EObject element) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						StereotypesPropertiesEditionPartForm.this,
						CustomUmlViewsRepository.Stereotypes.appliedStereotypes,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.EDIT, null, element));
		appliedStereotypes.refresh();
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StereotypesPropertiesEditionPart#updateAppliedStereotypes()
	 */
	public void updateAppliedStereotypes() {
		appliedStereotypes.refresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StereotypesPropertiesEditionPart#addFilterAppliedStereotypes(ViewerFilter
	 *      filter)
	 */
	public void addFilterToAppliedStereotypes(ViewerFilter filter) {
		appliedStereotypesFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StereotypesPropertiesEditionPart#addBusinessFilterAppliedStereotypes(ViewerFilter
	 *      filter)
	 */
	public void addBusinessFilterToAppliedStereotypes(ViewerFilter filter) {
		appliedStereotypesBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StereotypesPropertiesEditionPart#isContainedInAppliedStereotypesTable(EObject
	 *      element)
	 */
	public boolean isContainedInAppliedStereotypesTable(EObject element) {
		return ((ReferencesTableSettings) appliedStereotypes.getInput())
				.contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 */
	public String getTitle() {
		return CustomUmlMessages.Stereotypes_Part_Title;
	}

	/**
	 * {@inheritDoc}
	 */
	public void initAppliedStereotypes(OperationsTableSettings settings) {
		if (current.eResource() != null
				&& current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		labelService = new TableLabelService(settings.getSource());
		OperationsTableContentProvider contentProvider = new OperationsTableContentProvider();
		appliedStereotypes.setContentProvider(contentProvider);
		appliedStereotypes.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(CustomUmlViewsRepository.Stereotypes.appliedStereotypes);
		if (eefElementEditorReadOnlyState && appliedStereotypes.isEnabled()) {
			appliedStereotypes.setEnabled(false);
			appliedStereotypes
					.setToolTipText(CustomUmlMessages.Stereotypes_ReadOnly);
		} else if (!eefElementEditorReadOnlyState
				&& !appliedStereotypes.isEnabled()) {
			appliedStereotypes.setEnabled(true);
		}

	}

	@Override
	protected String getDescription(Object editor, String alternate) {
		return CustomUmlMessages.StereotypesPropertiesEditionPart_AppliedStereotypesLabel;
	}

}
