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
import org.eclipse.uml2.uml.Profile;
import org.obeonetwork.dsl.uml2.properties.parts.CustomSectionPropertiesEditingPart;
import org.obeonetwork.dsl.uml2.properties.service.TableColumnService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService.TableColumnName;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ProfilesPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.CustomUmlMessages;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class ProfilesPropertiesEditionPartForm extends
		CustomSectionPropertiesEditingPart implements
		IFormPropertiesEditionPart, ProfilesPropertiesEditionPart {

	protected ReferencesTable appliedProfiles;
	protected List<ViewerFilter> appliedProfilesBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> appliedProfilesFilters = new ArrayList<ViewerFilter>();
	private TableLabelService labelService;
	private final TableColumnService tableColumnService = new TableColumnService();

	/**
	 * For {@link ISection} use only.
	 */
	public ProfilesPropertiesEditionPartForm() {
		super();
	}

	/**
	 * Default constructor
	 * 
	 * @param editionComponent
	 *            the {@link IPropertiesEditionComponent} that manage this part
	 */
	public ProfilesPropertiesEditionPartForm(
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
		CompositionSequence profilesStep = new BindingCompositionSequence(
				propertiesEditionComponent);
		profilesStep.addStep(CustomUmlViewsRepository.Profiles.appliedProfiles);

		composer = new PartComposer(profilesStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == CustomUmlViewsRepository.Profiles.appliedProfiles) {
					return createAppliedProfilesReferencesTable(widgetFactory,
							parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}

	protected Composite createAppliedProfilesReferencesTable(
			FormToolkit widgetFactory, Composite parent) {
		this.appliedProfiles = new ReferencesTable(
				getDescription(
						CustomUmlViewsRepository.Profiles.appliedProfiles,
						CustomUmlMessages.ProfilesPropertiesEditionPart_AppliedProfilesLabel),
				new ReferencesTableListener() {
					public void handleAdd() {
						addAppliedProfiles();
					}

					public void handleEdit(EObject element) {
						editAppliedProfiles(element);
					}

					public void handleMove(EObject element, int oldIndex,
							int newIndex) {
						moveAppliedProfiles(element, oldIndex, newIndex);
					}

					public void handleRemove(EObject element) {
						removeFromAppliedProfiles(element);
					}

					public void navigateTo(EObject element) {
					}
				}) {
			@Override
			public AdapterFactoryLabelProvider getLabelProvider() {
				return new AdapterFactoryLabelProvider(adapterFactory) {
					@Override
					public String getColumnText(Object object, int columnIndex) {
						if (object instanceof Profile) {
							Profile profile = (Profile) object;
							if (columnIndex == tableColumnService
									.indexOf(TableColumnName.PROFILE)) {
								return labelService.caseProfile(profile);
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
		for (ViewerFilter filter : this.appliedProfilesFilters) {
			this.appliedProfiles.addFilter(filter);
		}
		this.appliedProfiles.setHelpText(propertiesEditionComponent
				.getHelpContent(
						CustomUmlViewsRepository.Profiles.appliedProfiles,
						UmlViewsRepository.FORM_KIND));
		this.appliedProfiles.createControls(parent, widgetFactory);
		this.appliedProfiles.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									ProfilesPropertiesEditionPartForm.this,
									CustomUmlViewsRepository.Profiles.appliedProfiles,
									PropertiesEditionEvent.CHANGE,
									PropertiesEditionEvent.SELECTION_CHANGED,
									null, e.item.getData()));
				}
			}

		});
		GridData appliedProfilesData = new GridData(GridData.FILL_HORIZONTAL);
		appliedProfilesData.horizontalSpan = 3;
		this.appliedProfiles.setLayoutData(appliedProfilesData);
		this.appliedProfiles.disableMove();
		appliedProfiles
				.setID(CustomUmlViewsRepository.Profiles.appliedProfiles);
		appliedProfiles.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		// Start of user code for createAppliedProfilesTableComposition
		Table table = appliedProfiles.getTable();
		TableColumn[] columns = table.getColumns();
		// dispose
		for (TableColumn tableColumn : columns) {
			tableColumn.dispose();
		}

		table.setHeaderVisible(true);
		tableColumnService.createMediumColumn(table, TableColumnName.PROFILE);

		// End of user code
		return parent;
	}

	protected void addAppliedProfiles() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(
				appliedProfiles.getInput(), appliedProfilesFilters,
				appliedProfilesBusinessFilters, "appliedProfiles",
				propertiesEditionComponent.getEditingContext()
						.getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									ProfilesPropertiesEditionPartForm.this,
									CustomUmlViewsRepository.Profiles.appliedProfiles,
									PropertiesEditionEvent.COMMIT,
									PropertiesEditionEvent.ADD, null, elem));
				}
				appliedProfiles.refresh();
			}
		};
		dialog.open();
	}

	protected void moveAppliedProfiles(EObject element, int oldIndex,
			int newIndex) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						ProfilesPropertiesEditionPartForm.this,
						CustomUmlViewsRepository.Profiles.appliedProfiles,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.MOVE, element, newIndex));
		appliedProfiles.refresh();
	}

	protected void removeFromAppliedProfiles(EObject element) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						ProfilesPropertiesEditionPartForm.this,
						CustomUmlViewsRepository.Profiles.appliedProfiles,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.REMOVE, null, element));
		appliedProfiles.refresh();
	}

	protected void editAppliedProfiles(EObject element) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						ProfilesPropertiesEditionPartForm.this,
						CustomUmlViewsRepository.Profiles.appliedProfiles,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.EDIT, null, element));
		appliedProfiles.refresh();
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfilesPropertiesEditionPart#updateAppliedProfiles()
	 */
	public void updateAppliedProfiles() {
		appliedProfiles.refresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfilesPropertiesEditionPart#addFilterAppliedProfiles(ViewerFilter
	 *      filter)
	 */
	public void addFilterToAppliedProfiles(ViewerFilter filter) {
		appliedProfilesFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfilesPropertiesEditionPart#addBusinessFilterAppliedProfiles(ViewerFilter
	 *      filter)
	 */
	public void addBusinessFilterToAppliedProfiles(ViewerFilter filter) {
		appliedProfilesBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfilesPropertiesEditionPart#isContainedInAppliedProfilesTable(EObject
	 *      element)
	 */
	public boolean isContainedInAppliedProfilesTable(EObject element) {
		return ((ReferencesTableSettings) appliedProfiles.getInput())
				.contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 */
	public String getTitle() {
		return CustomUmlMessages.Profiles_Part_Title;
	}

	/**
	 * {@inheritDoc}
	 */
	public void initAppliedProfiles(OperationsTableSettings settings) {
		if (current.eResource() != null
				&& current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		labelService = new TableLabelService(settings.getSource());
		OperationsTableContentProvider contentProvider = new OperationsTableContentProvider();
		appliedProfiles.setContentProvider(contentProvider);
		appliedProfiles.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(CustomUmlViewsRepository.Profiles.appliedProfiles);
		if (eefElementEditorReadOnlyState && appliedProfiles.isEnabled()) {
			appliedProfiles.setEnabled(false);
			appliedProfiles.setToolTipText(CustomUmlMessages.Profiles_ReadOnly);
		} else if (!eefElementEditorReadOnlyState
				&& !appliedProfiles.isEnabled()) {
			appliedProfiles.setEnabled(true);
		}

	}

	@Override
	protected String getDescription(Object editor, String alternate) {
		return CustomUmlMessages.ProfilesPropertiesEditionPart_AppliedProfilesLabel;
	}

}
