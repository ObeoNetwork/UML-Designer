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

import org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ActivityPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ActivityPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected Button isLeaf;
	protected EObjectFlatComboViewer owningTemplateParameter;
	protected EObjectFlatComboViewer templateParameter;
	protected Button isAbstract;
	protected ReferencesTable powertypeExtent;
	protected List<ViewerFilter> powertypeExtentBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> powertypeExtentFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable redefinedClassifier;
	protected List<ViewerFilter> redefinedClassifierBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> redefinedClassifierFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer representation;
	protected ReferencesTable useCase;
	protected List<ViewerFilter> useCaseBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> useCaseFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer classifierBehavior;
	protected Button isActive;
	protected Button isReentrant;
	protected ReferencesTable redefinedBehavior;
	protected List<ViewerFilter> redefinedBehaviorBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> redefinedBehaviorFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable precondition;
	protected List<ViewerFilter> preconditionBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> preconditionFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable postcondition;
	protected List<ViewerFilter> postconditionBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> postconditionFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer specification;
	protected Button isReadOnly;
	protected ReferencesTable partition;
	protected List<ViewerFilter> partitionBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> partitionFilters = new ArrayList<ViewerFilter>();
	protected Button isSingleExecution;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ActivityPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence activityStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = activityStep.addStep(UmlViewsRepository.Activity.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.isLeaf);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.owningTemplateParameter);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.templateParameter);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.isAbstract);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.powertypeExtent);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.redefinedClassifier);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.representation);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.useCase);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.classifierBehavior);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.isActive);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.isReentrant);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.redefinedBehavior);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.precondition);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.postcondition);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.specification);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.isReadOnly);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.partition);
		propertiesStep.addStep(UmlViewsRepository.Activity.Properties.isSingleExecution);
		
		
		composer = new PartComposer(activityStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.Activity.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.name) {
					return createNameText(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.visibility) {
					return createVisibilityEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.clientDependency) {
					return createClientDependencyAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.isLeaf) {
					return createIsLeafCheckbox(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.owningTemplateParameter) {
					return createOwningTemplateParameterFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.templateParameter) {
					return createTemplateParameterFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.isAbstract) {
					return createIsAbstractCheckbox(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.powertypeExtent) {
					return createPowertypeExtentAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.redefinedClassifier) {
					return createRedefinedClassifierAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.representation) {
					return createRepresentationFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.useCase) {
					return createUseCaseAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.classifierBehavior) {
					return createClassifierBehaviorFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.isActive) {
					return createIsActiveCheckbox(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.isReentrant) {
					return createIsReentrantCheckbox(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.redefinedBehavior) {
					return createRedefinedBehaviorAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.precondition) {
					return createPreconditionAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.postcondition) {
					return createPostconditionAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.specification) {
					return createSpecificationFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.isReadOnly) {
					return createIsReadOnlyCheckbox(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.partition) {
					return createPartitionAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Activity.Properties.isSingleExecution) {
					return createIsSingleExecutionCheckbox(parent);
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
		propertiesGroup.setText(UmlMessages.ActivityPropertiesEditionPart_PropertiesGroupLabel);
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
		createDescription(parent, UmlViewsRepository.Activity.Properties.name, UmlMessages.ActivityPropertiesEditionPart_NameLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, UmlViewsRepository.Activity.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Activity.Properties.visibility, UmlMessages.ActivityPropertiesEditionPart_VisibilityLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.Activity.Properties.visibility);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Activity.Properties.clientDependency, UmlMessages.ActivityPropertiesEditionPart_ClientDependencyLabel);		 
		this.clientDependency = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
		this.clientDependency.createControls(parent);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.Activity.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		isLeaf.setText(getDescription(UmlViewsRepository.Activity.Properties.isLeaf, UmlMessages.ActivityPropertiesEditionPart_IsLeafLabel));
		isLeaf.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
			}

		});
		GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
		isLeafData.horizontalSpan = 2;
		isLeaf.setLayoutData(isLeafData);
		EditingUtils.setID(isLeaf, UmlViewsRepository.Activity.Properties.isLeaf);
		EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.isLeaf, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createOwningTemplateParameterFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Activity.Properties.owningTemplateParameter, UmlMessages.ActivityPropertiesEditionPart_OwningTemplateParameterLabel);
		owningTemplateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Activity.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND));
		owningTemplateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		owningTemplateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.owningTemplateParameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getOwningTemplateParameter()));
			}

		});
		GridData owningTemplateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		owningTemplateParameter.setLayoutData(owningTemplateParameterData);
		owningTemplateParameter.setID(UmlViewsRepository.Activity.Properties.owningTemplateParameter);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createTemplateParameterFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Activity.Properties.templateParameter, UmlMessages.ActivityPropertiesEditionPart_TemplateParameterLabel);
		templateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Activity.Properties.templateParameter, UmlViewsRepository.SWT_KIND));
		templateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		templateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.templateParameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getTemplateParameter()));
			}

		});
		GridData templateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		templateParameter.setLayoutData(templateParameterData);
		templateParameter.setID(UmlViewsRepository.Activity.Properties.templateParameter);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.templateParameter, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsAbstractCheckbox(Composite parent) {
		isAbstract = new Button(parent, SWT.CHECK);
		isAbstract.setText(getDescription(UmlViewsRepository.Activity.Properties.isAbstract, UmlMessages.ActivityPropertiesEditionPart_IsAbstractLabel));
		isAbstract.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.isAbstract, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isAbstract.getSelection())));
			}

		});
		GridData isAbstractData = new GridData(GridData.FILL_HORIZONTAL);
		isAbstractData.horizontalSpan = 2;
		isAbstract.setLayoutData(isAbstractData);
		EditingUtils.setID(isAbstract, UmlViewsRepository.Activity.Properties.isAbstract);
		EditingUtils.setEEFtype(isAbstract, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.isAbstract, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createPowertypeExtentAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Activity.Properties.powertypeExtent, UmlMessages.ActivityPropertiesEditionPart_PowertypeExtentLabel);		 
		this.powertypeExtent = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addPowertypeExtent(); }
			public void handleEdit(EObject element) { editPowertypeExtent(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { movePowertypeExtent(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromPowertypeExtent(element); }
			public void navigateTo(EObject element) { }
		});
		this.powertypeExtent.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.powertypeExtent, UmlViewsRepository.SWT_KIND));
		this.powertypeExtent.createControls(parent);
		this.powertypeExtent.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.powertypeExtent, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData powertypeExtentData = new GridData(GridData.FILL_HORIZONTAL);
		powertypeExtentData.horizontalSpan = 3;
		this.powertypeExtent.setLayoutData(powertypeExtentData);
		this.powertypeExtent.disableMove();
		powertypeExtent.setID(UmlViewsRepository.Activity.Properties.powertypeExtent);
		powertypeExtent.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addPowertypeExtent() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(powertypeExtent.getInput(), powertypeExtentFilters, powertypeExtentBusinessFilters,
		"powertypeExtent", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.powertypeExtent,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				powertypeExtent.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void movePowertypeExtent(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.powertypeExtent, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		powertypeExtent.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromPowertypeExtent(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.powertypeExtent, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		powertypeExtent.refresh();
	}

	/**
	 * @generated
	 */
	protected void editPowertypeExtent(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				powertypeExtent.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	protected Composite createRedefinedClassifierAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Activity.Properties.redefinedClassifier, UmlMessages.ActivityPropertiesEditionPart_RedefinedClassifierLabel);		 
		this.redefinedClassifier = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addRedefinedClassifier(); }
			public void handleEdit(EObject element) { editRedefinedClassifier(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedClassifier(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRedefinedClassifier(element); }
			public void navigateTo(EObject element) { }
		});
		this.redefinedClassifier.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.redefinedClassifier, UmlViewsRepository.SWT_KIND));
		this.redefinedClassifier.createControls(parent);
		this.redefinedClassifier.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.redefinedClassifier, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData redefinedClassifierData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedClassifierData.horizontalSpan = 3;
		this.redefinedClassifier.setLayoutData(redefinedClassifierData);
		this.redefinedClassifier.disableMove();
		redefinedClassifier.setID(UmlViewsRepository.Activity.Properties.redefinedClassifier);
		redefinedClassifier.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addRedefinedClassifier() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(redefinedClassifier.getInput(), redefinedClassifierFilters, redefinedClassifierBusinessFilters,
		"redefinedClassifier", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.redefinedClassifier,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				redefinedClassifier.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveRedefinedClassifier(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.redefinedClassifier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		redefinedClassifier.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromRedefinedClassifier(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.redefinedClassifier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		redefinedClassifier.refresh();
	}

	/**
	 * @generated
	 */
	protected void editRedefinedClassifier(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				redefinedClassifier.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createRepresentationFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Activity.Properties.representation, UmlMessages.ActivityPropertiesEditionPart_RepresentationLabel);
		representation = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Activity.Properties.representation, UmlViewsRepository.SWT_KIND));
		representation.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		representation.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.representation, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getRepresentation()));
			}

		});
		GridData representationData = new GridData(GridData.FILL_HORIZONTAL);
		representation.setLayoutData(representationData);
		representation.setID(UmlViewsRepository.Activity.Properties.representation);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.representation, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createUseCaseAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Activity.Properties.useCase, UmlMessages.ActivityPropertiesEditionPart_UseCaseLabel);		 
		this.useCase = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addUseCase(); }
			public void handleEdit(EObject element) { editUseCase(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveUseCase(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromUseCase(element); }
			public void navigateTo(EObject element) { }
		});
		this.useCase.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.useCase, UmlViewsRepository.SWT_KIND));
		this.useCase.createControls(parent);
		this.useCase.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.useCase, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData useCaseData = new GridData(GridData.FILL_HORIZONTAL);
		useCaseData.horizontalSpan = 3;
		this.useCase.setLayoutData(useCaseData);
		this.useCase.disableMove();
		useCase.setID(UmlViewsRepository.Activity.Properties.useCase);
		useCase.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addUseCase() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(useCase.getInput(), useCaseFilters, useCaseBusinessFilters,
		"useCase", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.useCase,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				useCase.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveUseCase(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.useCase, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		useCase.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromUseCase(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.useCase, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		useCase.refresh();
	}

	/**
	 * @generated
	 */
	protected void editUseCase(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				useCase.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createClassifierBehaviorFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Activity.Properties.classifierBehavior, UmlMessages.ActivityPropertiesEditionPart_ClassifierBehaviorLabel);
		classifierBehavior = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Activity.Properties.classifierBehavior, UmlViewsRepository.SWT_KIND));
		classifierBehavior.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		classifierBehavior.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.classifierBehavior, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getClassifierBehavior()));
			}

		});
		GridData classifierBehaviorData = new GridData(GridData.FILL_HORIZONTAL);
		classifierBehavior.setLayoutData(classifierBehaviorData);
		classifierBehavior.setID(UmlViewsRepository.Activity.Properties.classifierBehavior);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.classifierBehavior, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsActiveCheckbox(Composite parent) {
		isActive = new Button(parent, SWT.CHECK);
		isActive.setText(getDescription(UmlViewsRepository.Activity.Properties.isActive, UmlMessages.ActivityPropertiesEditionPart_IsActiveLabel));
		isActive.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.isActive, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isActive.getSelection())));
			}

		});
		GridData isActiveData = new GridData(GridData.FILL_HORIZONTAL);
		isActiveData.horizontalSpan = 2;
		isActive.setLayoutData(isActiveData);
		EditingUtils.setID(isActive, UmlViewsRepository.Activity.Properties.isActive);
		EditingUtils.setEEFtype(isActive, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.isActive, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsReentrantCheckbox(Composite parent) {
		isReentrant = new Button(parent, SWT.CHECK);
		isReentrant.setText(getDescription(UmlViewsRepository.Activity.Properties.isReentrant, UmlMessages.ActivityPropertiesEditionPart_IsReentrantLabel));
		isReentrant.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.isReentrant, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isReentrant.getSelection())));
			}

		});
		GridData isReentrantData = new GridData(GridData.FILL_HORIZONTAL);
		isReentrantData.horizontalSpan = 2;
		isReentrant.setLayoutData(isReentrantData);
		EditingUtils.setID(isReentrant, UmlViewsRepository.Activity.Properties.isReentrant);
		EditingUtils.setEEFtype(isReentrant, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.isReentrant, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createRedefinedBehaviorAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Activity.Properties.redefinedBehavior, UmlMessages.ActivityPropertiesEditionPart_RedefinedBehaviorLabel);		 
		this.redefinedBehavior = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addRedefinedBehavior(); }
			public void handleEdit(EObject element) { editRedefinedBehavior(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedBehavior(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRedefinedBehavior(element); }
			public void navigateTo(EObject element) { }
		});
		this.redefinedBehavior.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.redefinedBehavior, UmlViewsRepository.SWT_KIND));
		this.redefinedBehavior.createControls(parent);
		this.redefinedBehavior.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.redefinedBehavior, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData redefinedBehaviorData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedBehaviorData.horizontalSpan = 3;
		this.redefinedBehavior.setLayoutData(redefinedBehaviorData);
		this.redefinedBehavior.disableMove();
		redefinedBehavior.setID(UmlViewsRepository.Activity.Properties.redefinedBehavior);
		redefinedBehavior.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addRedefinedBehavior() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(redefinedBehavior.getInput(), redefinedBehaviorFilters, redefinedBehaviorBusinessFilters,
		"redefinedBehavior", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.redefinedBehavior,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				redefinedBehavior.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveRedefinedBehavior(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.redefinedBehavior, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		redefinedBehavior.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromRedefinedBehavior(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.redefinedBehavior, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		redefinedBehavior.refresh();
	}

	/**
	 * @generated
	 */
	protected void editRedefinedBehavior(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				redefinedBehavior.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	protected Composite createPreconditionAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Activity.Properties.precondition, UmlMessages.ActivityPropertiesEditionPart_PreconditionLabel);		 
		this.precondition = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addPrecondition(); }
			public void handleEdit(EObject element) { editPrecondition(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { movePrecondition(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromPrecondition(element); }
			public void navigateTo(EObject element) { }
		});
		this.precondition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.precondition, UmlViewsRepository.SWT_KIND));
		this.precondition.createControls(parent);
		this.precondition.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.precondition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData preconditionData = new GridData(GridData.FILL_HORIZONTAL);
		preconditionData.horizontalSpan = 3;
		this.precondition.setLayoutData(preconditionData);
		this.precondition.disableMove();
		precondition.setID(UmlViewsRepository.Activity.Properties.precondition);
		precondition.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addPrecondition() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(precondition.getInput(), preconditionFilters, preconditionBusinessFilters,
		"precondition", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.precondition,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				precondition.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void movePrecondition(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.precondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		precondition.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromPrecondition(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.precondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		precondition.refresh();
	}

	/**
	 * @generated
	 */
	protected void editPrecondition(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				precondition.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	protected Composite createPostconditionAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Activity.Properties.postcondition, UmlMessages.ActivityPropertiesEditionPart_PostconditionLabel);		 
		this.postcondition = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addPostcondition(); }
			public void handleEdit(EObject element) { editPostcondition(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { movePostcondition(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromPostcondition(element); }
			public void navigateTo(EObject element) { }
		});
		this.postcondition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.postcondition, UmlViewsRepository.SWT_KIND));
		this.postcondition.createControls(parent);
		this.postcondition.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.postcondition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData postconditionData = new GridData(GridData.FILL_HORIZONTAL);
		postconditionData.horizontalSpan = 3;
		this.postcondition.setLayoutData(postconditionData);
		this.postcondition.disableMove();
		postcondition.setID(UmlViewsRepository.Activity.Properties.postcondition);
		postcondition.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addPostcondition() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(postcondition.getInput(), postconditionFilters, postconditionBusinessFilters,
		"postcondition", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.postcondition,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				postcondition.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void movePostcondition(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.postcondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		postcondition.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromPostcondition(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.postcondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		postcondition.refresh();
	}

	/**
	 * @generated
	 */
	protected void editPostcondition(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				postcondition.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createSpecificationFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Activity.Properties.specification, UmlMessages.ActivityPropertiesEditionPart_SpecificationLabel);
		specification = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Activity.Properties.specification, UmlViewsRepository.SWT_KIND));
		specification.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		specification.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.specification, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getSpecification()));
			}

		});
		GridData specificationData = new GridData(GridData.FILL_HORIZONTAL);
		specification.setLayoutData(specificationData);
		specification.setID(UmlViewsRepository.Activity.Properties.specification);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.specification, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsReadOnlyCheckbox(Composite parent) {
		isReadOnly = new Button(parent, SWT.CHECK);
		isReadOnly.setText(getDescription(UmlViewsRepository.Activity.Properties.isReadOnly, UmlMessages.ActivityPropertiesEditionPart_IsReadOnlyLabel));
		isReadOnly.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.isReadOnly, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isReadOnly.getSelection())));
			}

		});
		GridData isReadOnlyData = new GridData(GridData.FILL_HORIZONTAL);
		isReadOnlyData.horizontalSpan = 2;
		isReadOnly.setLayoutData(isReadOnlyData);
		EditingUtils.setID(isReadOnly, UmlViewsRepository.Activity.Properties.isReadOnly);
		EditingUtils.setEEFtype(isReadOnly, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.isReadOnly, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createPartitionAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Activity.Properties.partition, UmlMessages.ActivityPropertiesEditionPart_PartitionLabel);		 
		this.partition = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addPartition(); }
			public void handleEdit(EObject element) { editPartition(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { movePartition(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromPartition(element); }
			public void navigateTo(EObject element) { }
		});
		this.partition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.partition, UmlViewsRepository.SWT_KIND));
		this.partition.createControls(parent);
		this.partition.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.partition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData partitionData = new GridData(GridData.FILL_HORIZONTAL);
		partitionData.horizontalSpan = 3;
		this.partition.setLayoutData(partitionData);
		this.partition.disableMove();
		partition.setID(UmlViewsRepository.Activity.Properties.partition);
		partition.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addPartition() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(partition.getInput(), partitionFilters, partitionBusinessFilters,
		"partition", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.partition,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				partition.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void movePartition(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.partition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		partition.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromPartition(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.partition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		partition.refresh();
	}

	/**
	 * @generated
	 */
	protected void editPartition(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				partition.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsSingleExecutionCheckbox(Composite parent) {
		isSingleExecution = new Button(parent, SWT.CHECK);
		isSingleExecution.setText(getDescription(UmlViewsRepository.Activity.Properties.isSingleExecution, UmlMessages.ActivityPropertiesEditionPart_IsSingleExecutionLabel));
		isSingleExecution.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPropertiesEditionPartImpl.this, UmlViewsRepository.Activity.Properties.isSingleExecution, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isSingleExecution.getSelection())));
			}

		});
		GridData isSingleExecutionData = new GridData(GridData.FILL_HORIZONTAL);
		isSingleExecutionData.horizontalSpan = 2;
		isSingleExecution.setLayoutData(isSingleExecutionData);
		EditingUtils.setID(isSingleExecution, UmlViewsRepository.Activity.Properties.isSingleExecution);
		EditingUtils.setEEFtype(isSingleExecution, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Activity.Properties.isSingleExecution, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.name);
		if (readOnly && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
		Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
		return Boolean.valueOf(isLeaf.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
		if (newValue != null) {
			isLeaf.setSelection(newValue.booleanValue());
		} else {
			isLeaf.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.isLeaf);
		if (readOnly && isLeaf.isEnabled()) {
			isLeaf.setEnabled(false);
			isLeaf.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !isLeaf.isEnabled()) {
			isLeaf.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#getOwningTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#initOwningTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initOwningTemplateParameter(EObjectFlatComboSettings settings) {
		owningTemplateParameter.setInput(settings);
		if (current != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.owningTemplateParameter);
		if (readOnly && owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(false);
			owningTemplateParameter.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setOwningTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setOwningTemplateParameter(EObject newValue) {
		if (newValue != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(newValue));
		} else {
			owningTemplateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.owningTemplateParameter);
		if (readOnly && owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(false);
			owningTemplateParameter.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		owningTemplateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addBusinessFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#getTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#initTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initTemplateParameter(EObjectFlatComboSettings settings) {
		templateParameter.setInput(settings);
		if (current != null) {
			templateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.templateParameter);
		if (readOnly && templateParameter.isEnabled()) {
			templateParameter.setEnabled(false);
			templateParameter.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !templateParameter.isEnabled()) {
			templateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setTemplateParameter(EObject newValue) {
		if (newValue != null) {
			templateParameter.setSelection(new StructuredSelection(newValue));
		} else {
			templateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.templateParameter);
		if (readOnly && templateParameter.isEnabled()) {
			templateParameter.setEnabled(false);
			templateParameter.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !templateParameter.isEnabled()) {
			templateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		templateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addBusinessFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#getIsAbstract()
	 * @generated
	 */
	public Boolean getIsAbstract() {
		return Boolean.valueOf(isAbstract.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setIsAbstract(Boolean newValue)
	 * @generated
	 */
	public void setIsAbstract(Boolean newValue) {
		if (newValue != null) {
			isAbstract.setSelection(newValue.booleanValue());
		} else {
			isAbstract.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.isAbstract);
		if (readOnly && isAbstract.isEnabled()) {
			isAbstract.setEnabled(false);
			isAbstract.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !isAbstract.isEnabled()) {
			isAbstract.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#initPowertypeExtent(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initPowertypeExtent(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		powertypeExtent.setContentProvider(contentProvider);
		powertypeExtent.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.powertypeExtent);
		if (readOnly && powertypeExtent.getTable().isEnabled()) {
			powertypeExtent.setEnabled(false);
			powertypeExtent.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !powertypeExtent.getTable().isEnabled()) {
			powertypeExtent.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#updatePowertypeExtent()
	 * @generated
	 */
	public void updatePowertypeExtent() {
	powertypeExtent.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addFilterPowertypeExtent(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToPowertypeExtent(ViewerFilter filter) {
		powertypeExtentFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addBusinessFilterPowertypeExtent(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToPowertypeExtent(ViewerFilter filter) {
		powertypeExtentBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#isContainedInPowertypeExtentTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInPowertypeExtentTable(EObject element) {
		return ((ReferencesTableSettings)powertypeExtent.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#initRedefinedClassifier(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedClassifier(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedClassifier.setContentProvider(contentProvider);
		redefinedClassifier.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.redefinedClassifier);
		if (readOnly && redefinedClassifier.getTable().isEnabled()) {
			redefinedClassifier.setEnabled(false);
			redefinedClassifier.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !redefinedClassifier.getTable().isEnabled()) {
			redefinedClassifier.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#updateRedefinedClassifier()
	 * @generated
	 */
	public void updateRedefinedClassifier() {
	redefinedClassifier.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addFilterRedefinedClassifier(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedClassifier(ViewerFilter filter) {
		redefinedClassifierFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addBusinessFilterRedefinedClassifier(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedClassifier(ViewerFilter filter) {
		redefinedClassifierBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#isContainedInRedefinedClassifierTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRedefinedClassifierTable(EObject element) {
		return ((ReferencesTableSettings)redefinedClassifier.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#getRepresentation()
	 * @generated
	 */
	public EObject getRepresentation() {
		if (representation.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) representation.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#initRepresentation(EObjectFlatComboSettings)
	 */
	public void initRepresentation(EObjectFlatComboSettings settings) {
		representation.setInput(settings);
		if (current != null) {
			representation.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.representation);
		if (readOnly && representation.isEnabled()) {
			representation.setEnabled(false);
			representation.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !representation.isEnabled()) {
			representation.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setRepresentation(EObject newValue)
	 * @generated
	 */
	public void setRepresentation(EObject newValue) {
		if (newValue != null) {
			representation.setSelection(new StructuredSelection(newValue));
		} else {
			representation.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.representation);
		if (readOnly && representation.isEnabled()) {
			representation.setEnabled(false);
			representation.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !representation.isEnabled()) {
			representation.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setRepresentationButtonMode(ButtonsModeEnum newValue)
	 */
	public void setRepresentationButtonMode(ButtonsModeEnum newValue) {
		representation.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addFilterRepresentation(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRepresentation(ViewerFilter filter) {
		representation.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addBusinessFilterRepresentation(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRepresentation(ViewerFilter filter) {
		representation.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#initUseCase(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initUseCase(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		useCase.setContentProvider(contentProvider);
		useCase.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.useCase);
		if (readOnly && useCase.getTable().isEnabled()) {
			useCase.setEnabled(false);
			useCase.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !useCase.getTable().isEnabled()) {
			useCase.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#updateUseCase()
	 * @generated
	 */
	public void updateUseCase() {
	useCase.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addFilterUseCase(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToUseCase(ViewerFilter filter) {
		useCaseFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addBusinessFilterUseCase(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToUseCase(ViewerFilter filter) {
		useCaseBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#isContainedInUseCaseTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInUseCaseTable(EObject element) {
		return ((ReferencesTableSettings)useCase.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#getClassifierBehavior()
	 * @generated
	 */
	public EObject getClassifierBehavior() {
		if (classifierBehavior.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) classifierBehavior.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#initClassifierBehavior(EObjectFlatComboSettings)
	 */
	public void initClassifierBehavior(EObjectFlatComboSettings settings) {
		classifierBehavior.setInput(settings);
		if (current != null) {
			classifierBehavior.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.classifierBehavior);
		if (readOnly && classifierBehavior.isEnabled()) {
			classifierBehavior.setEnabled(false);
			classifierBehavior.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !classifierBehavior.isEnabled()) {
			classifierBehavior.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setClassifierBehavior(EObject newValue)
	 * @generated
	 */
	public void setClassifierBehavior(EObject newValue) {
		if (newValue != null) {
			classifierBehavior.setSelection(new StructuredSelection(newValue));
		} else {
			classifierBehavior.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.classifierBehavior);
		if (readOnly && classifierBehavior.isEnabled()) {
			classifierBehavior.setEnabled(false);
			classifierBehavior.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !classifierBehavior.isEnabled()) {
			classifierBehavior.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setClassifierBehaviorButtonMode(ButtonsModeEnum newValue)
	 */
	public void setClassifierBehaviorButtonMode(ButtonsModeEnum newValue) {
		classifierBehavior.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addFilterClassifierBehavior(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClassifierBehavior(ViewerFilter filter) {
		classifierBehavior.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addBusinessFilterClassifierBehavior(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClassifierBehavior(ViewerFilter filter) {
		classifierBehavior.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#getIsActive()
	 * @generated
	 */
	public Boolean getIsActive() {
		return Boolean.valueOf(isActive.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setIsActive(Boolean newValue)
	 * @generated
	 */
	public void setIsActive(Boolean newValue) {
		if (newValue != null) {
			isActive.setSelection(newValue.booleanValue());
		} else {
			isActive.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.isActive);
		if (readOnly && isActive.isEnabled()) {
			isActive.setEnabled(false);
			isActive.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !isActive.isEnabled()) {
			isActive.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#getIsReentrant()
	 * @generated
	 */
	public Boolean getIsReentrant() {
		return Boolean.valueOf(isReentrant.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setIsReentrant(Boolean newValue)
	 * @generated
	 */
	public void setIsReentrant(Boolean newValue) {
		if (newValue != null) {
			isReentrant.setSelection(newValue.booleanValue());
		} else {
			isReentrant.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.isReentrant);
		if (readOnly && isReentrant.isEnabled()) {
			isReentrant.setEnabled(false);
			isReentrant.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !isReentrant.isEnabled()) {
			isReentrant.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#initRedefinedBehavior(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedBehavior(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedBehavior.setContentProvider(contentProvider);
		redefinedBehavior.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.redefinedBehavior);
		if (readOnly && redefinedBehavior.getTable().isEnabled()) {
			redefinedBehavior.setEnabled(false);
			redefinedBehavior.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !redefinedBehavior.getTable().isEnabled()) {
			redefinedBehavior.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#updateRedefinedBehavior()
	 * @generated
	 */
	public void updateRedefinedBehavior() {
	redefinedBehavior.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addFilterRedefinedBehavior(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedBehavior(ViewerFilter filter) {
		redefinedBehaviorFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addBusinessFilterRedefinedBehavior(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedBehavior(ViewerFilter filter) {
		redefinedBehaviorBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#isContainedInRedefinedBehaviorTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRedefinedBehaviorTable(EObject element) {
		return ((ReferencesTableSettings)redefinedBehavior.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#initPrecondition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initPrecondition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		precondition.setContentProvider(contentProvider);
		precondition.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.precondition);
		if (readOnly && precondition.getTable().isEnabled()) {
			precondition.setEnabled(false);
			precondition.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !precondition.getTable().isEnabled()) {
			precondition.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#updatePrecondition()
	 * @generated
	 */
	public void updatePrecondition() {
	precondition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addFilterPrecondition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToPrecondition(ViewerFilter filter) {
		preconditionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addBusinessFilterPrecondition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToPrecondition(ViewerFilter filter) {
		preconditionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#isContainedInPreconditionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInPreconditionTable(EObject element) {
		return ((ReferencesTableSettings)precondition.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#initPostcondition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initPostcondition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		postcondition.setContentProvider(contentProvider);
		postcondition.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.postcondition);
		if (readOnly && postcondition.getTable().isEnabled()) {
			postcondition.setEnabled(false);
			postcondition.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !postcondition.getTable().isEnabled()) {
			postcondition.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#updatePostcondition()
	 * @generated
	 */
	public void updatePostcondition() {
	postcondition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addFilterPostcondition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToPostcondition(ViewerFilter filter) {
		postconditionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addBusinessFilterPostcondition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToPostcondition(ViewerFilter filter) {
		postconditionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#isContainedInPostconditionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInPostconditionTable(EObject element) {
		return ((ReferencesTableSettings)postcondition.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#getSpecification()
	 * @generated
	 */
	public EObject getSpecification() {
		if (specification.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) specification.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#initSpecification(EObjectFlatComboSettings)
	 */
	public void initSpecification(EObjectFlatComboSettings settings) {
		specification.setInput(settings);
		if (current != null) {
			specification.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.specification);
		if (readOnly && specification.isEnabled()) {
			specification.setEnabled(false);
			specification.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !specification.isEnabled()) {
			specification.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setSpecification(EObject newValue)
	 * @generated
	 */
	public void setSpecification(EObject newValue) {
		if (newValue != null) {
			specification.setSelection(new StructuredSelection(newValue));
		} else {
			specification.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.specification);
		if (readOnly && specification.isEnabled()) {
			specification.setEnabled(false);
			specification.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !specification.isEnabled()) {
			specification.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setSpecificationButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSpecificationButtonMode(ButtonsModeEnum newValue) {
		specification.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addFilterSpecification(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSpecification(ViewerFilter filter) {
		specification.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addBusinessFilterSpecification(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSpecification(ViewerFilter filter) {
		specification.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#getIsReadOnly()
	 * @generated
	 */
	public Boolean getIsReadOnly() {
		return Boolean.valueOf(isReadOnly.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setIsReadOnly(Boolean newValue)
	 * @generated
	 */
	public void setIsReadOnly(Boolean newValue) {
		if (newValue != null) {
			isReadOnly.setSelection(newValue.booleanValue());
		} else {
			isReadOnly.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.isReadOnly);
		if (readOnly && isReadOnly.isEnabled()) {
			isReadOnly.setEnabled(false);
			isReadOnly.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !isReadOnly.isEnabled()) {
			isReadOnly.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#initPartition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initPartition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		partition.setContentProvider(contentProvider);
		partition.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.partition);
		if (readOnly && partition.getTable().isEnabled()) {
			partition.setEnabled(false);
			partition.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !partition.getTable().isEnabled()) {
			partition.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#updatePartition()
	 * @generated
	 */
	public void updatePartition() {
	partition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addFilterPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToPartition(ViewerFilter filter) {
		partitionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#addBusinessFilterPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToPartition(ViewerFilter filter) {
		partitionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#isContainedInPartitionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInPartitionTable(EObject element) {
		return ((ReferencesTableSettings)partition.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#getIsSingleExecution()
	 * @generated
	 */
	public Boolean getIsSingleExecution() {
		return Boolean.valueOf(isSingleExecution.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart#setIsSingleExecution(Boolean newValue)
	 * @generated
	 */
	public void setIsSingleExecution(Boolean newValue) {
		if (newValue != null) {
			isSingleExecution.setSelection(newValue.booleanValue());
		} else {
			isSingleExecution.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Activity.Properties.isSingleExecution);
		if (readOnly && isSingleExecution.isEnabled()) {
			isSingleExecution.setEnabled(false);
			isSingleExecution.setToolTipText(UmlMessages.Activity_ReadOnly);
		} else if (!readOnly && !isSingleExecution.isEnabled()) {
			isSingleExecution.setEnabled(true);
		}	
		
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.Activity_Part_Title;
	}



}
