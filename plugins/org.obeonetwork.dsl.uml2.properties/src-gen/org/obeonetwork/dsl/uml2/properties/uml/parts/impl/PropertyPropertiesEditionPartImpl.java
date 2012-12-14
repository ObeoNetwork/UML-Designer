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
package org.obeonetwork.dsl.uml2.properties.uml.parts.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;

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
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
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

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class PropertyPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, PropertyPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected Button isLeaf;
	protected Button isStatic;
	protected EObjectFlatComboViewer type;
	protected Button isOrdered;
	protected Button isUnique;
	protected Button isReadOnly;
	protected EObjectFlatComboViewer owningTemplateParameter;
	protected EObjectFlatComboViewer templateParameter;
	protected EObjectFlatComboViewer class_;
	protected EObjectFlatComboViewer datatype;
	protected Button isDerived;
	protected Button isDerivedUnion;
	protected EMFComboViewer aggregation;
	protected ReferencesTable redefinedProperty;
	protected List<ViewerFilter> redefinedPropertyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> redefinedPropertyFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer owningAssociation;
	protected ReferencesTable subsettedProperty;
	protected List<ViewerFilter> subsettedPropertyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> subsettedPropertyFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer association;
	protected EObjectFlatComboViewer associationEnd;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public PropertyPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createFigure(org.eclipse.swt.widgets.Composite)
	 * @generated
	 */
	public Composite createFigure(final Composite parent) {
		view = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		view.setLayout(layout);
		createControls(view);
		return view;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createControls(org.eclipse.swt.widgets.Composite)
	 * @generated
	 */
	public void createControls(Composite view) { 
		CompositionSequence propertyStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = propertyStep.addStep(UmlViewsRepository.Property.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.isLeaf);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.isStatic);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.type);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.isOrdered);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.isUnique);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.isReadOnly);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.owningTemplateParameter);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.templateParameter);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.class_);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.datatype);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.isDerived);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.isDerivedUnion);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.aggregation);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.redefinedProperty);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.owningAssociation);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.subsettedProperty);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.association);
		propertiesStep.addStep(UmlViewsRepository.Property.Properties.associationEnd);
		
		
		composer = new PartComposer(propertyStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.Property.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.name) {
					return createNameText(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.visibility) {
					return createVisibilityEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.clientDependency) {
					return createClientDependencyAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.isLeaf) {
					return createIsLeafCheckbox(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.isStatic) {
					return createIsStaticCheckbox(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.type) {
					return createTypeFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.isOrdered) {
					return createIsOrderedCheckbox(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.isUnique) {
					return createIsUniqueCheckbox(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.isReadOnly) {
					return createIsReadOnlyCheckbox(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.owningTemplateParameter) {
					return createOwningTemplateParameterFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.templateParameter) {
					return createTemplateParameterFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.class_) {
					return createClass_FlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.datatype) {
					return createDatatypeFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.isDerived) {
					return createIsDerivedCheckbox(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.isDerivedUnion) {
					return createIsDerivedUnionCheckbox(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.aggregation) {
					return createAggregationEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.redefinedProperty) {
					return createRedefinedPropertyAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.owningAssociation) {
					return createOwningAssociationFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.subsettedProperty) {
					return createSubsettedPropertyAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.association) {
					return createAssociationFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Property.Properties.associationEnd) {
					return createAssociationEndFlatComboViewer(parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}

	/**
	 * @generated
	 */
	protected Composite createPropertiesGroup(Composite parent) {
		Group propertiesGroup = new Group(parent, SWT.NONE);
		propertiesGroup.setText(UmlMessages.PropertyPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	/**
	 * @generated
	 */
	
	protected Composite createNameText(Composite parent) {
		createDescription(parent, UmlViewsRepository.Property.Properties.name, UmlMessages.PropertyPropertiesEditionPart_NameLabel);
		name = SWTUtils.createScrollableText(parent, SWT.BORDER);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(nameData);
		name.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * @generated
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
			}

		});
		name.addKeyListener(new KeyAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * @generated
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, UmlViewsRepository.Property.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Property.Properties.visibility, UmlMessages.PropertyPropertiesEditionPart_VisibilityLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.Property.Properties.visibility);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Property.Properties.clientDependency, UmlMessages.PropertyPropertiesEditionPart_ClientDependencyLabel);		 
		this.clientDependency = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
		this.clientDependency.createControls(parent);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.Property.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	 * @generated
	 */
	
	protected Composite createIsLeafCheckbox(Composite parent) {
		isLeaf = new Button(parent, SWT.CHECK);
		isLeaf.setText(getDescription(UmlViewsRepository.Property.Properties.isLeaf, UmlMessages.PropertyPropertiesEditionPart_IsLeafLabel));
		isLeaf.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
			}

		});
		GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
		isLeafData.horizontalSpan = 2;
		isLeaf.setLayoutData(isLeafData);
		EditingUtils.setID(isLeaf, UmlViewsRepository.Property.Properties.isLeaf);
		EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.isLeaf, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsStaticCheckbox(Composite parent) {
		isStatic = new Button(parent, SWT.CHECK);
		isStatic.setText(getDescription(UmlViewsRepository.Property.Properties.isStatic, UmlMessages.PropertyPropertiesEditionPart_IsStaticLabel));
		isStatic.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.isStatic, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isStatic.getSelection())));
			}

		});
		GridData isStaticData = new GridData(GridData.FILL_HORIZONTAL);
		isStaticData.horizontalSpan = 2;
		isStatic.setLayoutData(isStaticData);
		EditingUtils.setID(isStatic, UmlViewsRepository.Property.Properties.isStatic);
		EditingUtils.setEEFtype(isStatic, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.isStatic, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createTypeFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Property.Properties.type, UmlMessages.PropertyPropertiesEditionPart_TypeLabel);
		type = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Property.Properties.type, UmlViewsRepository.SWT_KIND));
		type.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		type.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.type, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getType()));
			}

		});
		GridData typeData = new GridData(GridData.FILL_HORIZONTAL);
		type.setLayoutData(typeData);
		type.setID(UmlViewsRepository.Property.Properties.type);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.type, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsOrderedCheckbox(Composite parent) {
		isOrdered = new Button(parent, SWT.CHECK);
		isOrdered.setText(getDescription(UmlViewsRepository.Property.Properties.isOrdered, UmlMessages.PropertyPropertiesEditionPart_IsOrderedLabel));
		isOrdered.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.isOrdered, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isOrdered.getSelection())));
			}

		});
		GridData isOrderedData = new GridData(GridData.FILL_HORIZONTAL);
		isOrderedData.horizontalSpan = 2;
		isOrdered.setLayoutData(isOrderedData);
		EditingUtils.setID(isOrdered, UmlViewsRepository.Property.Properties.isOrdered);
		EditingUtils.setEEFtype(isOrdered, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.isOrdered, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsUniqueCheckbox(Composite parent) {
		isUnique = new Button(parent, SWT.CHECK);
		isUnique.setText(getDescription(UmlViewsRepository.Property.Properties.isUnique, UmlMessages.PropertyPropertiesEditionPart_IsUniqueLabel));
		isUnique.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.isUnique, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isUnique.getSelection())));
			}

		});
		GridData isUniqueData = new GridData(GridData.FILL_HORIZONTAL);
		isUniqueData.horizontalSpan = 2;
		isUnique.setLayoutData(isUniqueData);
		EditingUtils.setID(isUnique, UmlViewsRepository.Property.Properties.isUnique);
		EditingUtils.setEEFtype(isUnique, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.isUnique, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsReadOnlyCheckbox(Composite parent) {
		isReadOnly = new Button(parent, SWT.CHECK);
		isReadOnly.setText(getDescription(UmlViewsRepository.Property.Properties.isReadOnly, UmlMessages.PropertyPropertiesEditionPart_IsReadOnlyLabel));
		isReadOnly.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.isReadOnly, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isReadOnly.getSelection())));
			}

		});
		GridData isReadOnlyData = new GridData(GridData.FILL_HORIZONTAL);
		isReadOnlyData.horizontalSpan = 2;
		isReadOnly.setLayoutData(isReadOnlyData);
		EditingUtils.setID(isReadOnly, UmlViewsRepository.Property.Properties.isReadOnly);
		EditingUtils.setEEFtype(isReadOnly, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.isReadOnly, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createOwningTemplateParameterFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Property.Properties.owningTemplateParameter, UmlMessages.PropertyPropertiesEditionPart_OwningTemplateParameterLabel);
		owningTemplateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Property.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND));
		owningTemplateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		owningTemplateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.owningTemplateParameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getOwningTemplateParameter()));
			}

		});
		GridData owningTemplateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		owningTemplateParameter.setLayoutData(owningTemplateParameterData);
		owningTemplateParameter.setID(UmlViewsRepository.Property.Properties.owningTemplateParameter);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createTemplateParameterFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Property.Properties.templateParameter, UmlMessages.PropertyPropertiesEditionPart_TemplateParameterLabel);
		templateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Property.Properties.templateParameter, UmlViewsRepository.SWT_KIND));
		templateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		templateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.templateParameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getTemplateParameter()));
			}

		});
		GridData templateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		templateParameter.setLayoutData(templateParameterData);
		templateParameter.setID(UmlViewsRepository.Property.Properties.templateParameter);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.templateParameter, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createClass_FlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Property.Properties.class_, UmlMessages.PropertyPropertiesEditionPart_Class_Label);
		class_ = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Property.Properties.class_, UmlViewsRepository.SWT_KIND));
		class_.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		class_.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.class_, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getClass_()));
			}

		});
		GridData class_Data = new GridData(GridData.FILL_HORIZONTAL);
		class_.setLayoutData(class_Data);
		class_.setID(UmlViewsRepository.Property.Properties.class_);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.class_, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createDatatypeFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Property.Properties.datatype, UmlMessages.PropertyPropertiesEditionPart_DatatypeLabel);
		datatype = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Property.Properties.datatype, UmlViewsRepository.SWT_KIND));
		datatype.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		datatype.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.datatype, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getDatatype()));
			}

		});
		GridData datatypeData = new GridData(GridData.FILL_HORIZONTAL);
		datatype.setLayoutData(datatypeData);
		datatype.setID(UmlViewsRepository.Property.Properties.datatype);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.datatype, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsDerivedCheckbox(Composite parent) {
		isDerived = new Button(parent, SWT.CHECK);
		isDerived.setText(getDescription(UmlViewsRepository.Property.Properties.isDerived, UmlMessages.PropertyPropertiesEditionPart_IsDerivedLabel));
		isDerived.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.isDerived, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isDerived.getSelection())));
			}

		});
		GridData isDerivedData = new GridData(GridData.FILL_HORIZONTAL);
		isDerivedData.horizontalSpan = 2;
		isDerived.setLayoutData(isDerivedData);
		EditingUtils.setID(isDerived, UmlViewsRepository.Property.Properties.isDerived);
		EditingUtils.setEEFtype(isDerived, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.isDerived, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsDerivedUnionCheckbox(Composite parent) {
		isDerivedUnion = new Button(parent, SWT.CHECK);
		isDerivedUnion.setText(getDescription(UmlViewsRepository.Property.Properties.isDerivedUnion, UmlMessages.PropertyPropertiesEditionPart_IsDerivedUnionLabel));
		isDerivedUnion.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.isDerivedUnion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isDerivedUnion.getSelection())));
			}

		});
		GridData isDerivedUnionData = new GridData(GridData.FILL_HORIZONTAL);
		isDerivedUnionData.horizontalSpan = 2;
		isDerivedUnion.setLayoutData(isDerivedUnionData);
		EditingUtils.setID(isDerivedUnion, UmlViewsRepository.Property.Properties.isDerivedUnion);
		EditingUtils.setEEFtype(isDerivedUnion, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.isDerivedUnion, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createAggregationEMFComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Property.Properties.aggregation, UmlMessages.PropertyPropertiesEditionPart_AggregationLabel);
		aggregation = new EMFComboViewer(parent);
		aggregation.setContentProvider(new ArrayContentProvider());
		aggregation.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
		GridData aggregationData = new GridData(GridData.FILL_HORIZONTAL);
		aggregation.getCombo().setLayoutData(aggregationData);
		aggregation.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 * 	@generated
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.aggregation, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getAggregation()));
			}

		});
		aggregation.setID(UmlViewsRepository.Property.Properties.aggregation);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.aggregation, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createRedefinedPropertyAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Property.Properties.redefinedProperty, UmlMessages.PropertyPropertiesEditionPart_RedefinedPropertyLabel);		 
		this.redefinedProperty = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addRedefinedProperty(); }
			public void handleEdit(EObject element) { editRedefinedProperty(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedProperty(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRedefinedProperty(element); }
			public void navigateTo(EObject element) { }
		});
		this.redefinedProperty.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.redefinedProperty, UmlViewsRepository.SWT_KIND));
		this.redefinedProperty.createControls(parent);
		this.redefinedProperty.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.redefinedProperty, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData redefinedPropertyData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedPropertyData.horizontalSpan = 3;
		this.redefinedProperty.setLayoutData(redefinedPropertyData);
		this.redefinedProperty.disableMove();
		redefinedProperty.setID(UmlViewsRepository.Property.Properties.redefinedProperty);
		redefinedProperty.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addRedefinedProperty() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(redefinedProperty.getInput(), redefinedPropertyFilters, redefinedPropertyBusinessFilters,
		"redefinedProperty", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.redefinedProperty,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				redefinedProperty.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveRedefinedProperty(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.redefinedProperty, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		redefinedProperty.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromRedefinedProperty(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.redefinedProperty, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		redefinedProperty.refresh();
	}

	/**
	 * @generated
	 */
	protected void editRedefinedProperty(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				redefinedProperty.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createOwningAssociationFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Property.Properties.owningAssociation, UmlMessages.PropertyPropertiesEditionPart_OwningAssociationLabel);
		owningAssociation = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Property.Properties.owningAssociation, UmlViewsRepository.SWT_KIND));
		owningAssociation.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		owningAssociation.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.owningAssociation, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getOwningAssociation()));
			}

		});
		GridData owningAssociationData = new GridData(GridData.FILL_HORIZONTAL);
		owningAssociation.setLayoutData(owningAssociationData);
		owningAssociation.setID(UmlViewsRepository.Property.Properties.owningAssociation);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.owningAssociation, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createSubsettedPropertyAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Property.Properties.subsettedProperty, UmlMessages.PropertyPropertiesEditionPart_SubsettedPropertyLabel);		 
		this.subsettedProperty = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addSubsettedProperty(); }
			public void handleEdit(EObject element) { editSubsettedProperty(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveSubsettedProperty(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromSubsettedProperty(element); }
			public void navigateTo(EObject element) { }
		});
		this.subsettedProperty.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.subsettedProperty, UmlViewsRepository.SWT_KIND));
		this.subsettedProperty.createControls(parent);
		this.subsettedProperty.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.subsettedProperty, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData subsettedPropertyData = new GridData(GridData.FILL_HORIZONTAL);
		subsettedPropertyData.horizontalSpan = 3;
		this.subsettedProperty.setLayoutData(subsettedPropertyData);
		this.subsettedProperty.disableMove();
		subsettedProperty.setID(UmlViewsRepository.Property.Properties.subsettedProperty);
		subsettedProperty.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addSubsettedProperty() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(subsettedProperty.getInput(), subsettedPropertyFilters, subsettedPropertyBusinessFilters,
		"subsettedProperty", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.subsettedProperty,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				subsettedProperty.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveSubsettedProperty(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.subsettedProperty, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		subsettedProperty.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromSubsettedProperty(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.subsettedProperty, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		subsettedProperty.refresh();
	}

	/**
	 * @generated
	 */
	protected void editSubsettedProperty(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				subsettedProperty.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createAssociationFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Property.Properties.association, UmlMessages.PropertyPropertiesEditionPart_AssociationLabel);
		association = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Property.Properties.association, UmlViewsRepository.SWT_KIND));
		association.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		association.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.association, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getAssociation()));
			}

		});
		GridData associationData = new GridData(GridData.FILL_HORIZONTAL);
		association.setLayoutData(associationData);
		association.setID(UmlViewsRepository.Property.Properties.association);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.association, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createAssociationEndFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Property.Properties.associationEnd, UmlMessages.PropertyPropertiesEditionPart_AssociationEndLabel);
		associationEnd = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Property.Properties.associationEnd, UmlViewsRepository.SWT_KIND));
		associationEnd.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		associationEnd.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PropertyPropertiesEditionPartImpl.this, UmlViewsRepository.Property.Properties.associationEnd, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getAssociationEnd()));
			}

		});
		GridData associationEndData = new GridData(GridData.FILL_HORIZONTAL);
		associationEnd.setLayoutData(associationEndData);
		associationEnd.setID(UmlViewsRepository.Property.Properties.associationEnd);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Property.Properties.associationEnd, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.name);
		if (readOnly && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
		Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
		return Boolean.valueOf(isLeaf.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
		if (newValue != null) {
			isLeaf.setSelection(newValue.booleanValue());
		} else {
			isLeaf.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.isLeaf);
		if (readOnly && isLeaf.isEnabled()) {
			isLeaf.setEnabled(false);
			isLeaf.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !isLeaf.isEnabled()) {
			isLeaf.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getIsStatic()
	 * @generated
	 */
	public Boolean getIsStatic() {
		return Boolean.valueOf(isStatic.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setIsStatic(Boolean newValue)
	 * @generated
	 */
	public void setIsStatic(Boolean newValue) {
		if (newValue != null) {
			isStatic.setSelection(newValue.booleanValue());
		} else {
			isStatic.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.isStatic);
		if (readOnly && isStatic.isEnabled()) {
			isStatic.setEnabled(false);
			isStatic.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !isStatic.isEnabled()) {
			isStatic.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getType()
	 * @generated
	 */
	public EObject getType() {
		if (type.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) type.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#initType(EObjectFlatComboSettings)
	 */
	public void initType(EObjectFlatComboSettings settings) {
		type.setInput(settings);
		if (current != null) {
			type.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.type);
		if (readOnly && type.isEnabled()) {
			type.setEnabled(false);
			type.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !type.isEnabled()) {
			type.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setType(EObject newValue)
	 * @generated
	 */
	public void setType(EObject newValue) {
		if (newValue != null) {
			type.setSelection(new StructuredSelection(newValue));
		} else {
			type.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.type);
		if (readOnly && type.isEnabled()) {
			type.setEnabled(false);
			type.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !type.isEnabled()) {
			type.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setTypeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTypeButtonMode(ButtonsModeEnum newValue) {
		type.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addFilterType(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToType(ViewerFilter filter) {
		type.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addBusinessFilterType(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToType(ViewerFilter filter) {
		type.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getIsOrdered()
	 * @generated
	 */
	public Boolean getIsOrdered() {
		return Boolean.valueOf(isOrdered.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setIsOrdered(Boolean newValue)
	 * @generated
	 */
	public void setIsOrdered(Boolean newValue) {
		if (newValue != null) {
			isOrdered.setSelection(newValue.booleanValue());
		} else {
			isOrdered.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.isOrdered);
		if (readOnly && isOrdered.isEnabled()) {
			isOrdered.setEnabled(false);
			isOrdered.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !isOrdered.isEnabled()) {
			isOrdered.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getIsUnique()
	 * @generated
	 */
	public Boolean getIsUnique() {
		return Boolean.valueOf(isUnique.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setIsUnique(Boolean newValue)
	 * @generated
	 */
	public void setIsUnique(Boolean newValue) {
		if (newValue != null) {
			isUnique.setSelection(newValue.booleanValue());
		} else {
			isUnique.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.isUnique);
		if (readOnly && isUnique.isEnabled()) {
			isUnique.setEnabled(false);
			isUnique.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !isUnique.isEnabled()) {
			isUnique.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getIsReadOnly()
	 * @generated
	 */
	public Boolean getIsReadOnly() {
		return Boolean.valueOf(isReadOnly.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setIsReadOnly(Boolean newValue)
	 * @generated
	 */
	public void setIsReadOnly(Boolean newValue) {
		if (newValue != null) {
			isReadOnly.setSelection(newValue.booleanValue());
		} else {
			isReadOnly.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.isReadOnly);
		if (readOnly && isReadOnly.isEnabled()) {
			isReadOnly.setEnabled(false);
			isReadOnly.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !isReadOnly.isEnabled()) {
			isReadOnly.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getOwningTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#initOwningTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initOwningTemplateParameter(EObjectFlatComboSettings settings) {
		owningTemplateParameter.setInput(settings);
		if (current != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.owningTemplateParameter);
		if (readOnly && owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(false);
			owningTemplateParameter.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setOwningTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setOwningTemplateParameter(EObject newValue) {
		if (newValue != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(newValue));
		} else {
			owningTemplateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.owningTemplateParameter);
		if (readOnly && owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(false);
			owningTemplateParameter.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		owningTemplateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addBusinessFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#initTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initTemplateParameter(EObjectFlatComboSettings settings) {
		templateParameter.setInput(settings);
		if (current != null) {
			templateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.templateParameter);
		if (readOnly && templateParameter.isEnabled()) {
			templateParameter.setEnabled(false);
			templateParameter.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !templateParameter.isEnabled()) {
			templateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setTemplateParameter(EObject newValue) {
		if (newValue != null) {
			templateParameter.setSelection(new StructuredSelection(newValue));
		} else {
			templateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.templateParameter);
		if (readOnly && templateParameter.isEnabled()) {
			templateParameter.setEnabled(false);
			templateParameter.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !templateParameter.isEnabled()) {
			templateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		templateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addBusinessFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getClass_()
	 * @generated
	 */
	public EObject getClass_() {
		if (class_.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) class_.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#initClass_(EObjectFlatComboSettings)
	 */
	public void initClass_(EObjectFlatComboSettings settings) {
		class_.setInput(settings);
		if (current != null) {
			class_.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.class_);
		if (readOnly && class_.isEnabled()) {
			class_.setEnabled(false);
			class_.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !class_.isEnabled()) {
			class_.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setClass_(EObject newValue)
	 * @generated
	 */
	public void setClass_(EObject newValue) {
		if (newValue != null) {
			class_.setSelection(new StructuredSelection(newValue));
		} else {
			class_.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.class_);
		if (readOnly && class_.isEnabled()) {
			class_.setEnabled(false);
			class_.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !class_.isEnabled()) {
			class_.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setClass_ButtonMode(ButtonsModeEnum newValue)
	 */
	public void setClass_ButtonMode(ButtonsModeEnum newValue) {
		class_.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addFilterClass_(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClass_(ViewerFilter filter) {
		class_.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addBusinessFilterClass_(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClass_(ViewerFilter filter) {
		class_.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getDatatype()
	 * @generated
	 */
	public EObject getDatatype() {
		if (datatype.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) datatype.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#initDatatype(EObjectFlatComboSettings)
	 */
	public void initDatatype(EObjectFlatComboSettings settings) {
		datatype.setInput(settings);
		if (current != null) {
			datatype.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.datatype);
		if (readOnly && datatype.isEnabled()) {
			datatype.setEnabled(false);
			datatype.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !datatype.isEnabled()) {
			datatype.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setDatatype(EObject newValue)
	 * @generated
	 */
	public void setDatatype(EObject newValue) {
		if (newValue != null) {
			datatype.setSelection(new StructuredSelection(newValue));
		} else {
			datatype.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.datatype);
		if (readOnly && datatype.isEnabled()) {
			datatype.setEnabled(false);
			datatype.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !datatype.isEnabled()) {
			datatype.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setDatatypeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDatatypeButtonMode(ButtonsModeEnum newValue) {
		datatype.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addFilterDatatype(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToDatatype(ViewerFilter filter) {
		datatype.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addBusinessFilterDatatype(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToDatatype(ViewerFilter filter) {
		datatype.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getIsDerived()
	 * @generated
	 */
	public Boolean getIsDerived() {
		return Boolean.valueOf(isDerived.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setIsDerived(Boolean newValue)
	 * @generated
	 */
	public void setIsDerived(Boolean newValue) {
		if (newValue != null) {
			isDerived.setSelection(newValue.booleanValue());
		} else {
			isDerived.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.isDerived);
		if (readOnly && isDerived.isEnabled()) {
			isDerived.setEnabled(false);
			isDerived.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !isDerived.isEnabled()) {
			isDerived.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getIsDerivedUnion()
	 * @generated
	 */
	public Boolean getIsDerivedUnion() {
		return Boolean.valueOf(isDerivedUnion.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setIsDerivedUnion(Boolean newValue)
	 * @generated
	 */
	public void setIsDerivedUnion(Boolean newValue) {
		if (newValue != null) {
			isDerivedUnion.setSelection(newValue.booleanValue());
		} else {
			isDerivedUnion.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.isDerivedUnion);
		if (readOnly && isDerivedUnion.isEnabled()) {
			isDerivedUnion.setEnabled(false);
			isDerivedUnion.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !isDerivedUnion.isEnabled()) {
			isDerivedUnion.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getAggregation()
	 * @generated
	 */
	public Enumerator getAggregation() {
		Enumerator selection = (Enumerator) ((StructuredSelection) aggregation.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#initAggregation(Object input, Enumerator current)
	 */
	public void initAggregation(Object input, Enumerator current) {
		aggregation.setInput(input);
		aggregation.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.aggregation);
		if (readOnly && aggregation.isEnabled()) {
			aggregation.setEnabled(false);
			aggregation.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !aggregation.isEnabled()) {
			aggregation.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setAggregation(Enumerator newValue)
	 * @generated
	 */
	public void setAggregation(Enumerator newValue) {
		aggregation.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.aggregation);
		if (readOnly && aggregation.isEnabled()) {
			aggregation.setEnabled(false);
			aggregation.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !aggregation.isEnabled()) {
			aggregation.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#initRedefinedProperty(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedProperty(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedProperty.setContentProvider(contentProvider);
		redefinedProperty.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.redefinedProperty);
		if (readOnly && redefinedProperty.getTable().isEnabled()) {
			redefinedProperty.setEnabled(false);
			redefinedProperty.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !redefinedProperty.getTable().isEnabled()) {
			redefinedProperty.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#updateRedefinedProperty()
	 * @generated
	 */
	public void updateRedefinedProperty() {
	redefinedProperty.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addFilterRedefinedProperty(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedProperty(ViewerFilter filter) {
		redefinedPropertyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addBusinessFilterRedefinedProperty(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedProperty(ViewerFilter filter) {
		redefinedPropertyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#isContainedInRedefinedPropertyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRedefinedPropertyTable(EObject element) {
		return ((ReferencesTableSettings)redefinedProperty.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getOwningAssociation()
	 * @generated
	 */
	public EObject getOwningAssociation() {
		if (owningAssociation.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) owningAssociation.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#initOwningAssociation(EObjectFlatComboSettings)
	 */
	public void initOwningAssociation(EObjectFlatComboSettings settings) {
		owningAssociation.setInput(settings);
		if (current != null) {
			owningAssociation.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.owningAssociation);
		if (readOnly && owningAssociation.isEnabled()) {
			owningAssociation.setEnabled(false);
			owningAssociation.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !owningAssociation.isEnabled()) {
			owningAssociation.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setOwningAssociation(EObject newValue)
	 * @generated
	 */
	public void setOwningAssociation(EObject newValue) {
		if (newValue != null) {
			owningAssociation.setSelection(new StructuredSelection(newValue));
		} else {
			owningAssociation.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.owningAssociation);
		if (readOnly && owningAssociation.isEnabled()) {
			owningAssociation.setEnabled(false);
			owningAssociation.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !owningAssociation.isEnabled()) {
			owningAssociation.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setOwningAssociationButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningAssociationButtonMode(ButtonsModeEnum newValue) {
		owningAssociation.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addFilterOwningAssociation(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOwningAssociation(ViewerFilter filter) {
		owningAssociation.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addBusinessFilterOwningAssociation(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOwningAssociation(ViewerFilter filter) {
		owningAssociation.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#initSubsettedProperty(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initSubsettedProperty(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		subsettedProperty.setContentProvider(contentProvider);
		subsettedProperty.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.subsettedProperty);
		if (readOnly && subsettedProperty.getTable().isEnabled()) {
			subsettedProperty.setEnabled(false);
			subsettedProperty.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !subsettedProperty.getTable().isEnabled()) {
			subsettedProperty.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#updateSubsettedProperty()
	 * @generated
	 */
	public void updateSubsettedProperty() {
	subsettedProperty.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addFilterSubsettedProperty(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSubsettedProperty(ViewerFilter filter) {
		subsettedPropertyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addBusinessFilterSubsettedProperty(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSubsettedProperty(ViewerFilter filter) {
		subsettedPropertyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#isContainedInSubsettedPropertyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInSubsettedPropertyTable(EObject element) {
		return ((ReferencesTableSettings)subsettedProperty.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getAssociation()
	 * @generated
	 */
	public EObject getAssociation() {
		if (association.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) association.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#initAssociation(EObjectFlatComboSettings)
	 */
	public void initAssociation(EObjectFlatComboSettings settings) {
		association.setInput(settings);
		if (current != null) {
			association.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.association);
		if (readOnly && association.isEnabled()) {
			association.setEnabled(false);
			association.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !association.isEnabled()) {
			association.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setAssociation(EObject newValue)
	 * @generated
	 */
	public void setAssociation(EObject newValue) {
		if (newValue != null) {
			association.setSelection(new StructuredSelection(newValue));
		} else {
			association.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.association);
		if (readOnly && association.isEnabled()) {
			association.setEnabled(false);
			association.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !association.isEnabled()) {
			association.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setAssociationButtonMode(ButtonsModeEnum newValue)
	 */
	public void setAssociationButtonMode(ButtonsModeEnum newValue) {
		association.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addFilterAssociation(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToAssociation(ViewerFilter filter) {
		association.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addBusinessFilterAssociation(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToAssociation(ViewerFilter filter) {
		association.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#getAssociationEnd()
	 * @generated
	 */
	public EObject getAssociationEnd() {
		if (associationEnd.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) associationEnd.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#initAssociationEnd(EObjectFlatComboSettings)
	 */
	public void initAssociationEnd(EObjectFlatComboSettings settings) {
		associationEnd.setInput(settings);
		if (current != null) {
			associationEnd.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.associationEnd);
		if (readOnly && associationEnd.isEnabled()) {
			associationEnd.setEnabled(false);
			associationEnd.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !associationEnd.isEnabled()) {
			associationEnd.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setAssociationEnd(EObject newValue)
	 * @generated
	 */
	public void setAssociationEnd(EObject newValue) {
		if (newValue != null) {
			associationEnd.setSelection(new StructuredSelection(newValue));
		} else {
			associationEnd.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Property.Properties.associationEnd);
		if (readOnly && associationEnd.isEnabled()) {
			associationEnd.setEnabled(false);
			associationEnd.setToolTipText(UmlMessages.Property_ReadOnly);
		} else if (!readOnly && !associationEnd.isEnabled()) {
			associationEnd.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#setAssociationEndButtonMode(ButtonsModeEnum newValue)
	 */
	public void setAssociationEndButtonMode(ButtonsModeEnum newValue) {
		associationEnd.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addFilterAssociationEnd(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToAssociationEnd(ViewerFilter filter) {
		associationEnd.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PropertyPropertiesEditionPart#addBusinessFilterAssociationEnd(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToAssociationEnd(ViewerFilter filter) {
		associationEnd.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.Property_Part_Title;
	}



}
