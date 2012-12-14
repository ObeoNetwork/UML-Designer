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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.EcoreAdapterFactory;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.part.impl.SectionPropertiesEditingPart;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;

import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;

import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EMFComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ComponentRealizationPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, ComponentRealizationPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer owningTemplateParameter;
	protected EObjectFlatComboViewer templateParameter;
	protected ReferencesTable supplier;
	protected List<ViewerFilter> supplierBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> supplierFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable client;
	protected List<ViewerFilter> clientBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer abstraction;
	protected ReferencesTable realizingClassifier;
	protected List<ViewerFilter> realizingClassifierBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> realizingClassifierFilters = new ArrayList<ViewerFilter>();



	/**
	 * For {@link ISection} use only.
	 */
	public ComponentRealizationPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ComponentRealizationPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
	 *  createFigure(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 * @generated
	 */
	public Composite createFigure(final Composite parent, final FormToolkit widgetFactory) {
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
	 *  createControls(org.eclipse.ui.forms.widgets.FormToolkit, org.eclipse.swt.widgets.Composite)
	 * @generated
	 */
	public void createControls(final FormToolkit widgetFactory, Composite view) {
		CompositionSequence componentRealizationStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = componentRealizationStep.addStep(UmlViewsRepository.ComponentRealization.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.ComponentRealization.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.ComponentRealization.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.ComponentRealization.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.ComponentRealization.Properties.owningTemplateParameter);
		propertiesStep.addStep(UmlViewsRepository.ComponentRealization.Properties.templateParameter);
		propertiesStep.addStep(UmlViewsRepository.ComponentRealization.Properties.supplier);
		propertiesStep.addStep(UmlViewsRepository.ComponentRealization.Properties.client);
		propertiesStep.addStep(UmlViewsRepository.ComponentRealization.Properties.abstraction);
		propertiesStep.addStep(UmlViewsRepository.ComponentRealization.Properties.realizingClassifier);
		
		
		composer = new PartComposer(componentRealizationStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.ComponentRealization.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ComponentRealization.Properties.name) {
					return createNameText(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ComponentRealization.Properties.visibility) {
					return createVisibilityEMFComboViewer(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ComponentRealization.Properties.clientDependency) {
					return createClientDependencyReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ComponentRealization.Properties.owningTemplateParameter) {
					return createOwningTemplateParameterFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ComponentRealization.Properties.templateParameter) {
					return createTemplateParameterFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ComponentRealization.Properties.supplier) {
					return createSupplierReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ComponentRealization.Properties.client) {
					return createClientReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ComponentRealization.Properties.abstraction) {
					return createAbstractionFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ComponentRealization.Properties.realizingClassifier) {
					return createRealizingClassifierReferencesTable(widgetFactory, parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}
	/**
	 * @generated
	 */
	protected Composite createPropertiesGroup(FormToolkit widgetFactory, final Composite parent) {
		Section propertiesSection = widgetFactory.createSection(parent, Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		propertiesSection.setText(UmlMessages.ComponentRealizationPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesSectionData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesSectionData.horizontalSpan = 3;
		propertiesSection.setLayoutData(propertiesSectionData);
		Composite propertiesGroup = widgetFactory.createComposite(propertiesSection);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		propertiesSection.setClient(propertiesGroup);
		return propertiesGroup;
	}

	/**
	 * @generated
	 */
	
	protected Composite createNameText(FormToolkit widgetFactory, Composite parent) {
		createDescription(parent, UmlViewsRepository.ComponentRealization.Properties.name, UmlMessages.ComponentRealizationPropertiesEditionPart_NameLabel);
		name = widgetFactory.createText(parent, ""); //$NON-NLS-1$
		name.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		widgetFactory.paintBordersFor(parent);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(nameData);
		name.addFocusListener(new FocusAdapter() {
			/**
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * @generated
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(
							ComponentRealizationPropertiesEditionPartForm.this,
							UmlViewsRepository.ComponentRealization.Properties.name,
							PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									ComponentRealizationPropertiesEditionPartForm.this,
									UmlViewsRepository.ComponentRealization.Properties.name,
									PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_LOST,
									null, name.getText()));
				}
			}

			/**
			 * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
			 */
			@Override
			public void focusGained(FocusEvent e) {
				if (propertiesEditionComponent != null) {
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									ComponentRealizationPropertiesEditionPartForm.this,
									null,
									PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_GAINED,
									null, null));
				}
			}
		});
		name.addKeyListener(new KeyAdapter() {
			/**
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * @generated
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}
		});
		EditingUtils.setID(name, UmlViewsRepository.ComponentRealization.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ComponentRealization.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		createDescription(parent, UmlViewsRepository.ComponentRealization.Properties.visibility, UmlMessages.ComponentRealizationPropertiesEditionPart_VisibilityLabel);
		visibility = new EMFComboViewer(parent);
		visibility.setContentProvider(new ArrayContentProvider());
		visibility.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
		GridData visibilityData = new GridData(GridData.FILL_HORIZONTAL);
		visibility.getCombo().setLayoutData(visibilityData);
		visibility.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 * 	@generated
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.ComponentRealization.Properties.visibility);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ComponentRealization.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.clientDependency = new ReferencesTable(getDescription(UmlViewsRepository.ComponentRealization.Properties.clientDependency, UmlMessages.ComponentRealizationPropertiesEditionPart_ClientDependencyLabel), new ReferencesTableListener	() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ComponentRealization.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
		this.clientDependency.createControls(parent, widgetFactory);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.ComponentRealization.Properties.clientDependency);
		clientDependency.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addClientDependency() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(clientDependency.getInput(), clientDependencyFilters, clientDependencyBusinessFilters,
		"clientDependency", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.clientDependency,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				clientDependency.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveClientDependency(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void editClientDependency(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				clientDependency.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createOwningTemplateParameterFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ComponentRealization.Properties.owningTemplateParameter, UmlMessages.ComponentRealizationPropertiesEditionPart_OwningTemplateParameterLabel);
		owningTemplateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ComponentRealization.Properties.owningTemplateParameter, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(owningTemplateParameter);
		owningTemplateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData owningTemplateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		owningTemplateParameter.setLayoutData(owningTemplateParameterData);
		owningTemplateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.owningTemplateParameter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getOwningTemplateParameter()));
			}

		});
		owningTemplateParameter.setID(UmlViewsRepository.ComponentRealization.Properties.owningTemplateParameter);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ComponentRealization.Properties.owningTemplateParameter, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createTemplateParameterFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ComponentRealization.Properties.templateParameter, UmlMessages.ComponentRealizationPropertiesEditionPart_TemplateParameterLabel);
		templateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ComponentRealization.Properties.templateParameter, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(templateParameter);
		templateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData templateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		templateParameter.setLayoutData(templateParameterData);
		templateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.templateParameter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getTemplateParameter()));
			}

		});
		templateParameter.setID(UmlViewsRepository.ComponentRealization.Properties.templateParameter);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ComponentRealization.Properties.templateParameter, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createSupplierReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.supplier = new ReferencesTable(getDescription(UmlViewsRepository.ComponentRealization.Properties.supplier, UmlMessages.ComponentRealizationPropertiesEditionPart_SupplierLabel), new ReferencesTableListener	() {
			public void handleAdd() { addSupplier(); }
			public void handleEdit(EObject element) { editSupplier(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveSupplier(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromSupplier(element); }
			public void navigateTo(EObject element) { }
		});
		this.supplier.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ComponentRealization.Properties.supplier, UmlViewsRepository.FORM_KIND));
		this.supplier.createControls(parent, widgetFactory);
		this.supplier.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.supplier, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData supplierData = new GridData(GridData.FILL_HORIZONTAL);
		supplierData.horizontalSpan = 3;
		this.supplier.setLayoutData(supplierData);
		this.supplier.disableMove();
		supplier.setID(UmlViewsRepository.ComponentRealization.Properties.supplier);
		supplier.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addSupplier() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(supplier.getInput(), supplierFilters, supplierBusinessFilters,
		"supplier", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.supplier,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				supplier.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveSupplier(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.supplier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		supplier.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromSupplier(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.supplier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		supplier.refresh();
	}

	/**
	 * @generated
	 */
	protected void editSupplier(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				supplier.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	protected Composite createClientReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.client = new ReferencesTable(getDescription(UmlViewsRepository.ComponentRealization.Properties.client, UmlMessages.ComponentRealizationPropertiesEditionPart_ClientLabel), new ReferencesTableListener	() {
			public void handleAdd() { addClient(); }
			public void handleEdit(EObject element) { editClient(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClient(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClient(element); }
			public void navigateTo(EObject element) { }
		});
		this.client.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ComponentRealization.Properties.client, UmlViewsRepository.FORM_KIND));
		this.client.createControls(parent, widgetFactory);
		this.client.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.client, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientData = new GridData(GridData.FILL_HORIZONTAL);
		clientData.horizontalSpan = 3;
		this.client.setLayoutData(clientData);
		this.client.disableMove();
		client.setID(UmlViewsRepository.ComponentRealization.Properties.client);
		client.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addClient() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(client.getInput(), clientFilters, clientBusinessFilters,
		"client", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.client,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				client.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveClient(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.client, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		client.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClient(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.client, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		client.refresh();
	}

	/**
	 * @generated
	 */
	protected void editClient(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				client.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createAbstractionFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ComponentRealization.Properties.abstraction, UmlMessages.ComponentRealizationPropertiesEditionPart_AbstractionLabel);
		abstraction = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ComponentRealization.Properties.abstraction, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(abstraction);
		abstraction.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData abstractionData = new GridData(GridData.FILL_HORIZONTAL);
		abstraction.setLayoutData(abstractionData);
		abstraction.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.abstraction, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getAbstraction()));
			}

		});
		abstraction.setID(UmlViewsRepository.ComponentRealization.Properties.abstraction);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ComponentRealization.Properties.abstraction, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createRealizingClassifierReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.realizingClassifier = new ReferencesTable(getDescription(UmlViewsRepository.ComponentRealization.Properties.realizingClassifier, UmlMessages.ComponentRealizationPropertiesEditionPart_RealizingClassifierLabel), new ReferencesTableListener	() {
			public void handleAdd() { addRealizingClassifier(); }
			public void handleEdit(EObject element) { editRealizingClassifier(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRealizingClassifier(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRealizingClassifier(element); }
			public void navigateTo(EObject element) { }
		});
		this.realizingClassifier.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ComponentRealization.Properties.realizingClassifier, UmlViewsRepository.FORM_KIND));
		this.realizingClassifier.createControls(parent, widgetFactory);
		this.realizingClassifier.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.realizingClassifier, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData realizingClassifierData = new GridData(GridData.FILL_HORIZONTAL);
		realizingClassifierData.horizontalSpan = 3;
		this.realizingClassifier.setLayoutData(realizingClassifierData);
		this.realizingClassifier.disableMove();
		realizingClassifier.setID(UmlViewsRepository.ComponentRealization.Properties.realizingClassifier);
		realizingClassifier.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addRealizingClassifier() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(realizingClassifier.getInput(), realizingClassifierFilters, realizingClassifierBusinessFilters,
		"realizingClassifier", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.realizingClassifier,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				realizingClassifier.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveRealizingClassifier(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.realizingClassifier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		realizingClassifier.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromRealizingClassifier(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ComponentRealizationPropertiesEditionPartForm.this, UmlViewsRepository.ComponentRealization.Properties.realizingClassifier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		realizingClassifier.refresh();
	}

	/**
	 * @generated
	 */
	protected void editRealizingClassifier(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				realizingClassifier.refresh();
			}
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ComponentRealization.Properties.name);
		if (readOnly && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(UmlMessages.ComponentRealization_ReadOnly);
		} else if (!readOnly && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
		Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.ComponentRealization.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.ComponentRealization_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.ComponentRealization.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.ComponentRealization_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ComponentRealization.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.ComponentRealization_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#getOwningTemplateParameter()
	 * @generated
	 */
	public EObject getOwningTemplateParameter() {
		if (owningTemplateParameter.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) owningTemplateParameter.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#initOwningTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initOwningTemplateParameter(EObjectFlatComboSettings settings) {
		owningTemplateParameter.setInput(settings);
		if (current != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ComponentRealization.Properties.owningTemplateParameter);
		if (readOnly && owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(false);
			owningTemplateParameter.setToolTipText(UmlMessages.ComponentRealization_ReadOnly);
		} else if (!readOnly && !owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#setOwningTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setOwningTemplateParameter(EObject newValue) {
		if (newValue != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(newValue));
		} else {
			owningTemplateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ComponentRealization.Properties.owningTemplateParameter);
		if (readOnly && owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(false);
			owningTemplateParameter.setToolTipText(UmlMessages.ComponentRealization_ReadOnly);
		} else if (!readOnly && !owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		owningTemplateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#addFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#addBusinessFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#getTemplateParameter()
	 * @generated
	 */
	public EObject getTemplateParameter() {
		if (templateParameter.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) templateParameter.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#initTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initTemplateParameter(EObjectFlatComboSettings settings) {
		templateParameter.setInput(settings);
		if (current != null) {
			templateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ComponentRealization.Properties.templateParameter);
		if (readOnly && templateParameter.isEnabled()) {
			templateParameter.setEnabled(false);
			templateParameter.setToolTipText(UmlMessages.ComponentRealization_ReadOnly);
		} else if (!readOnly && !templateParameter.isEnabled()) {
			templateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#setTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setTemplateParameter(EObject newValue) {
		if (newValue != null) {
			templateParameter.setSelection(new StructuredSelection(newValue));
		} else {
			templateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ComponentRealization.Properties.templateParameter);
		if (readOnly && templateParameter.isEnabled()) {
			templateParameter.setEnabled(false);
			templateParameter.setToolTipText(UmlMessages.ComponentRealization_ReadOnly);
		} else if (!readOnly && !templateParameter.isEnabled()) {
			templateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#setTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		templateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#addFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#addBusinessFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#initSupplier(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initSupplier(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		supplier.setContentProvider(contentProvider);
		supplier.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ComponentRealization.Properties.supplier);
		if (readOnly && supplier.getTable().isEnabled()) {
			supplier.setEnabled(false);
			supplier.setToolTipText(UmlMessages.ComponentRealization_ReadOnly);
		} else if (!readOnly && !supplier.getTable().isEnabled()) {
			supplier.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#updateSupplier()
	 * @generated
	 */
	public void updateSupplier() {
	supplier.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#addFilterSupplier(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSupplier(ViewerFilter filter) {
		supplierFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#addBusinessFilterSupplier(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSupplier(ViewerFilter filter) {
		supplierBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#isContainedInSupplierTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInSupplierTable(EObject element) {
		return ((ReferencesTableSettings)supplier.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#initClient(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClient(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		client.setContentProvider(contentProvider);
		client.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ComponentRealization.Properties.client);
		if (readOnly && client.getTable().isEnabled()) {
			client.setEnabled(false);
			client.setToolTipText(UmlMessages.ComponentRealization_ReadOnly);
		} else if (!readOnly && !client.getTable().isEnabled()) {
			client.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#updateClient()
	 * @generated
	 */
	public void updateClient() {
	client.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#addFilterClient(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClient(ViewerFilter filter) {
		clientFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#addBusinessFilterClient(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClient(ViewerFilter filter) {
		clientBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#isContainedInClientTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientTable(EObject element) {
		return ((ReferencesTableSettings)client.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#getAbstraction()
	 * @generated
	 */
	public EObject getAbstraction() {
		if (abstraction.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) abstraction.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#initAbstraction(EObjectFlatComboSettings)
	 */
	public void initAbstraction(EObjectFlatComboSettings settings) {
		abstraction.setInput(settings);
		if (current != null) {
			abstraction.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ComponentRealization.Properties.abstraction);
		if (readOnly && abstraction.isEnabled()) {
			abstraction.setEnabled(false);
			abstraction.setToolTipText(UmlMessages.ComponentRealization_ReadOnly);
		} else if (!readOnly && !abstraction.isEnabled()) {
			abstraction.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#setAbstraction(EObject newValue)
	 * @generated
	 */
	public void setAbstraction(EObject newValue) {
		if (newValue != null) {
			abstraction.setSelection(new StructuredSelection(newValue));
		} else {
			abstraction.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ComponentRealization.Properties.abstraction);
		if (readOnly && abstraction.isEnabled()) {
			abstraction.setEnabled(false);
			abstraction.setToolTipText(UmlMessages.ComponentRealization_ReadOnly);
		} else if (!readOnly && !abstraction.isEnabled()) {
			abstraction.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#setAbstractionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setAbstractionButtonMode(ButtonsModeEnum newValue) {
		abstraction.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#addFilterAbstraction(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToAbstraction(ViewerFilter filter) {
		abstraction.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#addBusinessFilterAbstraction(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToAbstraction(ViewerFilter filter) {
		abstraction.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#initRealizingClassifier(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRealizingClassifier(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		realizingClassifier.setContentProvider(contentProvider);
		realizingClassifier.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ComponentRealization.Properties.realizingClassifier);
		if (readOnly && realizingClassifier.getTable().isEnabled()) {
			realizingClassifier.setEnabled(false);
			realizingClassifier.setToolTipText(UmlMessages.ComponentRealization_ReadOnly);
		} else if (!readOnly && !realizingClassifier.getTable().isEnabled()) {
			realizingClassifier.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#updateRealizingClassifier()
	 * @generated
	 */
	public void updateRealizingClassifier() {
	realizingClassifier.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#addFilterRealizingClassifier(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRealizingClassifier(ViewerFilter filter) {
		realizingClassifierFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#addBusinessFilterRealizingClassifier(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRealizingClassifier(ViewerFilter filter) {
		realizingClassifierBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ComponentRealizationPropertiesEditionPart#isContainedInRealizingClassifierTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRealizingClassifierTable(EObject element) {
		return ((ReferencesTableSettings)realizingClassifier.getInput()).contains(element);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.ComponentRealization_Part_Title;
	}



}
