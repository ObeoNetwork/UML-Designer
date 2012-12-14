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

import org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class StateMachinePropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, StateMachinePropertiesEditionPart {

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
	protected ReferencesTable submachineState;
	protected List<ViewerFilter> submachineStateBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> submachineStateFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable extendedStateMachine;
	protected List<ViewerFilter> extendedStateMachineBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> extendedStateMachineFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public StateMachinePropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence stateMachineStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = stateMachineStep.addStep(UmlViewsRepository.StateMachine.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.isLeaf);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.owningTemplateParameter);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.templateParameter);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.isAbstract);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.powertypeExtent);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.redefinedClassifier);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.representation);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.useCase);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.classifierBehavior);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.isActive);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.isReentrant);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.redefinedBehavior);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.precondition);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.postcondition);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.specification);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.submachineState);
		propertiesStep.addStep(UmlViewsRepository.StateMachine.Properties.extendedStateMachine);
		
		
		composer = new PartComposer(stateMachineStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.StateMachine.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.name) {
					return createNameText(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.visibility) {
					return createVisibilityEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.clientDependency) {
					return createClientDependencyAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.isLeaf) {
					return createIsLeafCheckbox(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.owningTemplateParameter) {
					return createOwningTemplateParameterFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.templateParameter) {
					return createTemplateParameterFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.isAbstract) {
					return createIsAbstractCheckbox(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.powertypeExtent) {
					return createPowertypeExtentAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.redefinedClassifier) {
					return createRedefinedClassifierAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.representation) {
					return createRepresentationFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.useCase) {
					return createUseCaseAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.classifierBehavior) {
					return createClassifierBehaviorFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.isActive) {
					return createIsActiveCheckbox(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.isReentrant) {
					return createIsReentrantCheckbox(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.redefinedBehavior) {
					return createRedefinedBehaviorAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.precondition) {
					return createPreconditionAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.postcondition) {
					return createPostconditionAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.specification) {
					return createSpecificationFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.submachineState) {
					return createSubmachineStateAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.StateMachine.Properties.extendedStateMachine) {
					return createExtendedStateMachineAdvancedReferencesTable(parent);
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
		propertiesGroup.setText(UmlMessages.StateMachinePropertiesEditionPart_PropertiesGroupLabel);
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
		createDescription(parent, UmlViewsRepository.StateMachine.Properties.name, UmlMessages.StateMachinePropertiesEditionPart_NameLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, UmlViewsRepository.StateMachine.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.StateMachine.Properties.visibility, UmlMessages.StateMachinePropertiesEditionPart_VisibilityLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.StateMachine.Properties.visibility);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.StateMachine.Properties.clientDependency, UmlMessages.StateMachinePropertiesEditionPart_ClientDependencyLabel);		 
		this.clientDependency = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
		this.clientDependency.createControls(parent);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.StateMachine.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		isLeaf.setText(getDescription(UmlViewsRepository.StateMachine.Properties.isLeaf, UmlMessages.StateMachinePropertiesEditionPart_IsLeafLabel));
		isLeaf.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
			}

		});
		GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
		isLeafData.horizontalSpan = 2;
		isLeaf.setLayoutData(isLeafData);
		EditingUtils.setID(isLeaf, UmlViewsRepository.StateMachine.Properties.isLeaf);
		EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.isLeaf, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createOwningTemplateParameterFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.StateMachine.Properties.owningTemplateParameter, UmlMessages.StateMachinePropertiesEditionPart_OwningTemplateParameterLabel);
		owningTemplateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.StateMachine.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND));
		owningTemplateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		owningTemplateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.owningTemplateParameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getOwningTemplateParameter()));
			}

		});
		GridData owningTemplateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		owningTemplateParameter.setLayoutData(owningTemplateParameterData);
		owningTemplateParameter.setID(UmlViewsRepository.StateMachine.Properties.owningTemplateParameter);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createTemplateParameterFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.StateMachine.Properties.templateParameter, UmlMessages.StateMachinePropertiesEditionPart_TemplateParameterLabel);
		templateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.StateMachine.Properties.templateParameter, UmlViewsRepository.SWT_KIND));
		templateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		templateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.templateParameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getTemplateParameter()));
			}

		});
		GridData templateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		templateParameter.setLayoutData(templateParameterData);
		templateParameter.setID(UmlViewsRepository.StateMachine.Properties.templateParameter);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.templateParameter, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsAbstractCheckbox(Composite parent) {
		isAbstract = new Button(parent, SWT.CHECK);
		isAbstract.setText(getDescription(UmlViewsRepository.StateMachine.Properties.isAbstract, UmlMessages.StateMachinePropertiesEditionPart_IsAbstractLabel));
		isAbstract.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.isAbstract, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isAbstract.getSelection())));
			}

		});
		GridData isAbstractData = new GridData(GridData.FILL_HORIZONTAL);
		isAbstractData.horizontalSpan = 2;
		isAbstract.setLayoutData(isAbstractData);
		EditingUtils.setID(isAbstract, UmlViewsRepository.StateMachine.Properties.isAbstract);
		EditingUtils.setEEFtype(isAbstract, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.isAbstract, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createPowertypeExtentAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.StateMachine.Properties.powertypeExtent, UmlMessages.StateMachinePropertiesEditionPart_PowertypeExtentLabel);		 
		this.powertypeExtent = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addPowertypeExtent(); }
			public void handleEdit(EObject element) { editPowertypeExtent(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { movePowertypeExtent(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromPowertypeExtent(element); }
			public void navigateTo(EObject element) { }
		});
		this.powertypeExtent.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.powertypeExtent, UmlViewsRepository.SWT_KIND));
		this.powertypeExtent.createControls(parent);
		this.powertypeExtent.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.powertypeExtent, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData powertypeExtentData = new GridData(GridData.FILL_HORIZONTAL);
		powertypeExtentData.horizontalSpan = 3;
		this.powertypeExtent.setLayoutData(powertypeExtentData);
		this.powertypeExtent.disableMove();
		powertypeExtent.setID(UmlViewsRepository.StateMachine.Properties.powertypeExtent);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.powertypeExtent,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.powertypeExtent, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		powertypeExtent.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromPowertypeExtent(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.powertypeExtent, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		String label = getDescription(UmlViewsRepository.StateMachine.Properties.redefinedClassifier, UmlMessages.StateMachinePropertiesEditionPart_RedefinedClassifierLabel);		 
		this.redefinedClassifier = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addRedefinedClassifier(); }
			public void handleEdit(EObject element) { editRedefinedClassifier(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedClassifier(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRedefinedClassifier(element); }
			public void navigateTo(EObject element) { }
		});
		this.redefinedClassifier.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.redefinedClassifier, UmlViewsRepository.SWT_KIND));
		this.redefinedClassifier.createControls(parent);
		this.redefinedClassifier.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.redefinedClassifier, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData redefinedClassifierData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedClassifierData.horizontalSpan = 3;
		this.redefinedClassifier.setLayoutData(redefinedClassifierData);
		this.redefinedClassifier.disableMove();
		redefinedClassifier.setID(UmlViewsRepository.StateMachine.Properties.redefinedClassifier);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.redefinedClassifier,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.redefinedClassifier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		redefinedClassifier.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromRedefinedClassifier(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.redefinedClassifier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		createDescription(parent, UmlViewsRepository.StateMachine.Properties.representation, UmlMessages.StateMachinePropertiesEditionPart_RepresentationLabel);
		representation = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.StateMachine.Properties.representation, UmlViewsRepository.SWT_KIND));
		representation.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		representation.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.representation, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getRepresentation()));
			}

		});
		GridData representationData = new GridData(GridData.FILL_HORIZONTAL);
		representation.setLayoutData(representationData);
		representation.setID(UmlViewsRepository.StateMachine.Properties.representation);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.representation, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createUseCaseAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.StateMachine.Properties.useCase, UmlMessages.StateMachinePropertiesEditionPart_UseCaseLabel);		 
		this.useCase = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addUseCase(); }
			public void handleEdit(EObject element) { editUseCase(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveUseCase(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromUseCase(element); }
			public void navigateTo(EObject element) { }
		});
		this.useCase.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.useCase, UmlViewsRepository.SWT_KIND));
		this.useCase.createControls(parent);
		this.useCase.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.useCase, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData useCaseData = new GridData(GridData.FILL_HORIZONTAL);
		useCaseData.horizontalSpan = 3;
		this.useCase.setLayoutData(useCaseData);
		this.useCase.disableMove();
		useCase.setID(UmlViewsRepository.StateMachine.Properties.useCase);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.useCase,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.useCase, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		useCase.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromUseCase(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.useCase, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		createDescription(parent, UmlViewsRepository.StateMachine.Properties.classifierBehavior, UmlMessages.StateMachinePropertiesEditionPart_ClassifierBehaviorLabel);
		classifierBehavior = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.StateMachine.Properties.classifierBehavior, UmlViewsRepository.SWT_KIND));
		classifierBehavior.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		classifierBehavior.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.classifierBehavior, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getClassifierBehavior()));
			}

		});
		GridData classifierBehaviorData = new GridData(GridData.FILL_HORIZONTAL);
		classifierBehavior.setLayoutData(classifierBehaviorData);
		classifierBehavior.setID(UmlViewsRepository.StateMachine.Properties.classifierBehavior);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.classifierBehavior, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsActiveCheckbox(Composite parent) {
		isActive = new Button(parent, SWT.CHECK);
		isActive.setText(getDescription(UmlViewsRepository.StateMachine.Properties.isActive, UmlMessages.StateMachinePropertiesEditionPart_IsActiveLabel));
		isActive.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.isActive, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isActive.getSelection())));
			}

		});
		GridData isActiveData = new GridData(GridData.FILL_HORIZONTAL);
		isActiveData.horizontalSpan = 2;
		isActive.setLayoutData(isActiveData);
		EditingUtils.setID(isActive, UmlViewsRepository.StateMachine.Properties.isActive);
		EditingUtils.setEEFtype(isActive, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.isActive, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsReentrantCheckbox(Composite parent) {
		isReentrant = new Button(parent, SWT.CHECK);
		isReentrant.setText(getDescription(UmlViewsRepository.StateMachine.Properties.isReentrant, UmlMessages.StateMachinePropertiesEditionPart_IsReentrantLabel));
		isReentrant.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.isReentrant, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isReentrant.getSelection())));
			}

		});
		GridData isReentrantData = new GridData(GridData.FILL_HORIZONTAL);
		isReentrantData.horizontalSpan = 2;
		isReentrant.setLayoutData(isReentrantData);
		EditingUtils.setID(isReentrant, UmlViewsRepository.StateMachine.Properties.isReentrant);
		EditingUtils.setEEFtype(isReentrant, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.isReentrant, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createRedefinedBehaviorAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.StateMachine.Properties.redefinedBehavior, UmlMessages.StateMachinePropertiesEditionPart_RedefinedBehaviorLabel);		 
		this.redefinedBehavior = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addRedefinedBehavior(); }
			public void handleEdit(EObject element) { editRedefinedBehavior(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedBehavior(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRedefinedBehavior(element); }
			public void navigateTo(EObject element) { }
		});
		this.redefinedBehavior.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.redefinedBehavior, UmlViewsRepository.SWT_KIND));
		this.redefinedBehavior.createControls(parent);
		this.redefinedBehavior.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.redefinedBehavior, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData redefinedBehaviorData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedBehaviorData.horizontalSpan = 3;
		this.redefinedBehavior.setLayoutData(redefinedBehaviorData);
		this.redefinedBehavior.disableMove();
		redefinedBehavior.setID(UmlViewsRepository.StateMachine.Properties.redefinedBehavior);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.redefinedBehavior,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.redefinedBehavior, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		redefinedBehavior.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromRedefinedBehavior(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.redefinedBehavior, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		String label = getDescription(UmlViewsRepository.StateMachine.Properties.precondition, UmlMessages.StateMachinePropertiesEditionPart_PreconditionLabel);		 
		this.precondition = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addPrecondition(); }
			public void handleEdit(EObject element) { editPrecondition(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { movePrecondition(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromPrecondition(element); }
			public void navigateTo(EObject element) { }
		});
		this.precondition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.precondition, UmlViewsRepository.SWT_KIND));
		this.precondition.createControls(parent);
		this.precondition.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.precondition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData preconditionData = new GridData(GridData.FILL_HORIZONTAL);
		preconditionData.horizontalSpan = 3;
		this.precondition.setLayoutData(preconditionData);
		this.precondition.disableMove();
		precondition.setID(UmlViewsRepository.StateMachine.Properties.precondition);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.precondition,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.precondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		precondition.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromPrecondition(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.precondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		String label = getDescription(UmlViewsRepository.StateMachine.Properties.postcondition, UmlMessages.StateMachinePropertiesEditionPart_PostconditionLabel);		 
		this.postcondition = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addPostcondition(); }
			public void handleEdit(EObject element) { editPostcondition(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { movePostcondition(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromPostcondition(element); }
			public void navigateTo(EObject element) { }
		});
		this.postcondition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.postcondition, UmlViewsRepository.SWT_KIND));
		this.postcondition.createControls(parent);
		this.postcondition.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.postcondition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData postconditionData = new GridData(GridData.FILL_HORIZONTAL);
		postconditionData.horizontalSpan = 3;
		this.postcondition.setLayoutData(postconditionData);
		this.postcondition.disableMove();
		postcondition.setID(UmlViewsRepository.StateMachine.Properties.postcondition);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.postcondition,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.postcondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		postcondition.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromPostcondition(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.postcondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		createDescription(parent, UmlViewsRepository.StateMachine.Properties.specification, UmlMessages.StateMachinePropertiesEditionPart_SpecificationLabel);
		specification = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.StateMachine.Properties.specification, UmlViewsRepository.SWT_KIND));
		specification.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		specification.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.specification, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getSpecification()));
			}

		});
		GridData specificationData = new GridData(GridData.FILL_HORIZONTAL);
		specification.setLayoutData(specificationData);
		specification.setID(UmlViewsRepository.StateMachine.Properties.specification);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.specification, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createSubmachineStateAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.StateMachine.Properties.submachineState, UmlMessages.StateMachinePropertiesEditionPart_SubmachineStateLabel);		 
		this.submachineState = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addSubmachineState(); }
			public void handleEdit(EObject element) { editSubmachineState(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveSubmachineState(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromSubmachineState(element); }
			public void navigateTo(EObject element) { }
		});
		this.submachineState.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.submachineState, UmlViewsRepository.SWT_KIND));
		this.submachineState.createControls(parent);
		this.submachineState.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.submachineState, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData submachineStateData = new GridData(GridData.FILL_HORIZONTAL);
		submachineStateData.horizontalSpan = 3;
		this.submachineState.setLayoutData(submachineStateData);
		this.submachineState.disableMove();
		submachineState.setID(UmlViewsRepository.StateMachine.Properties.submachineState);
		submachineState.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addSubmachineState() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(submachineState.getInput(), submachineStateFilters, submachineStateBusinessFilters,
		"submachineState", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.submachineState,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				submachineState.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveSubmachineState(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.submachineState, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		submachineState.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromSubmachineState(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.submachineState, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		submachineState.refresh();
	}

	/**
	 * @generated
	 */
	protected void editSubmachineState(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				submachineState.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	protected Composite createExtendedStateMachineAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.StateMachine.Properties.extendedStateMachine, UmlMessages.StateMachinePropertiesEditionPart_ExtendedStateMachineLabel);		 
		this.extendedStateMachine = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addExtendedStateMachine(); }
			public void handleEdit(EObject element) { editExtendedStateMachine(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveExtendedStateMachine(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromExtendedStateMachine(element); }
			public void navigateTo(EObject element) { }
		});
		this.extendedStateMachine.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.StateMachine.Properties.extendedStateMachine, UmlViewsRepository.SWT_KIND));
		this.extendedStateMachine.createControls(parent);
		this.extendedStateMachine.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.extendedStateMachine, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData extendedStateMachineData = new GridData(GridData.FILL_HORIZONTAL);
		extendedStateMachineData.horizontalSpan = 3;
		this.extendedStateMachine.setLayoutData(extendedStateMachineData);
		this.extendedStateMachine.disableMove();
		extendedStateMachine.setID(UmlViewsRepository.StateMachine.Properties.extendedStateMachine);
		extendedStateMachine.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addExtendedStateMachine() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(extendedStateMachine.getInput(), extendedStateMachineFilters, extendedStateMachineBusinessFilters,
		"extendedStateMachine", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.extendedStateMachine,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				extendedStateMachine.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveExtendedStateMachine(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.extendedStateMachine, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		extendedStateMachine.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromExtendedStateMachine(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StateMachinePropertiesEditionPartImpl.this, UmlViewsRepository.StateMachine.Properties.extendedStateMachine, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		extendedStateMachine.refresh();
	}

	/**
	 * @generated
	 */
	protected void editExtendedStateMachine(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				extendedStateMachine.refresh();
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.name);
		if (readOnly && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
		Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
		return Boolean.valueOf(isLeaf.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
		if (newValue != null) {
			isLeaf.setSelection(newValue.booleanValue());
		} else {
			isLeaf.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.isLeaf);
		if (readOnly && isLeaf.isEnabled()) {
			isLeaf.setEnabled(false);
			isLeaf.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !isLeaf.isEnabled()) {
			isLeaf.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#getOwningTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initOwningTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initOwningTemplateParameter(EObjectFlatComboSettings settings) {
		owningTemplateParameter.setInput(settings);
		if (current != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.owningTemplateParameter);
		if (readOnly && owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(false);
			owningTemplateParameter.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setOwningTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setOwningTemplateParameter(EObject newValue) {
		if (newValue != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(newValue));
		} else {
			owningTemplateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.owningTemplateParameter);
		if (readOnly && owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(false);
			owningTemplateParameter.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		owningTemplateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addBusinessFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#getTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initTemplateParameter(EObjectFlatComboSettings settings) {
		templateParameter.setInput(settings);
		if (current != null) {
			templateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.templateParameter);
		if (readOnly && templateParameter.isEnabled()) {
			templateParameter.setEnabled(false);
			templateParameter.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !templateParameter.isEnabled()) {
			templateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setTemplateParameter(EObject newValue) {
		if (newValue != null) {
			templateParameter.setSelection(new StructuredSelection(newValue));
		} else {
			templateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.templateParameter);
		if (readOnly && templateParameter.isEnabled()) {
			templateParameter.setEnabled(false);
			templateParameter.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !templateParameter.isEnabled()) {
			templateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		templateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addBusinessFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#getIsAbstract()
	 * @generated
	 */
	public Boolean getIsAbstract() {
		return Boolean.valueOf(isAbstract.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setIsAbstract(Boolean newValue)
	 * @generated
	 */
	public void setIsAbstract(Boolean newValue) {
		if (newValue != null) {
			isAbstract.setSelection(newValue.booleanValue());
		} else {
			isAbstract.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.isAbstract);
		if (readOnly && isAbstract.isEnabled()) {
			isAbstract.setEnabled(false);
			isAbstract.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !isAbstract.isEnabled()) {
			isAbstract.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initPowertypeExtent(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initPowertypeExtent(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		powertypeExtent.setContentProvider(contentProvider);
		powertypeExtent.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.powertypeExtent);
		if (readOnly && powertypeExtent.getTable().isEnabled()) {
			powertypeExtent.setEnabled(false);
			powertypeExtent.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !powertypeExtent.getTable().isEnabled()) {
			powertypeExtent.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#updatePowertypeExtent()
	 * @generated
	 */
	public void updatePowertypeExtent() {
	powertypeExtent.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addFilterPowertypeExtent(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToPowertypeExtent(ViewerFilter filter) {
		powertypeExtentFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addBusinessFilterPowertypeExtent(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToPowertypeExtent(ViewerFilter filter) {
		powertypeExtentBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#isContainedInPowertypeExtentTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInPowertypeExtentTable(EObject element) {
		return ((ReferencesTableSettings)powertypeExtent.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initRedefinedClassifier(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedClassifier(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedClassifier.setContentProvider(contentProvider);
		redefinedClassifier.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.redefinedClassifier);
		if (readOnly && redefinedClassifier.getTable().isEnabled()) {
			redefinedClassifier.setEnabled(false);
			redefinedClassifier.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !redefinedClassifier.getTable().isEnabled()) {
			redefinedClassifier.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#updateRedefinedClassifier()
	 * @generated
	 */
	public void updateRedefinedClassifier() {
	redefinedClassifier.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addFilterRedefinedClassifier(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedClassifier(ViewerFilter filter) {
		redefinedClassifierFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addBusinessFilterRedefinedClassifier(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedClassifier(ViewerFilter filter) {
		redefinedClassifierBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#isContainedInRedefinedClassifierTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRedefinedClassifierTable(EObject element) {
		return ((ReferencesTableSettings)redefinedClassifier.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#getRepresentation()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initRepresentation(EObjectFlatComboSettings)
	 */
	public void initRepresentation(EObjectFlatComboSettings settings) {
		representation.setInput(settings);
		if (current != null) {
			representation.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.representation);
		if (readOnly && representation.isEnabled()) {
			representation.setEnabled(false);
			representation.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !representation.isEnabled()) {
			representation.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setRepresentation(EObject newValue)
	 * @generated
	 */
	public void setRepresentation(EObject newValue) {
		if (newValue != null) {
			representation.setSelection(new StructuredSelection(newValue));
		} else {
			representation.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.representation);
		if (readOnly && representation.isEnabled()) {
			representation.setEnabled(false);
			representation.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !representation.isEnabled()) {
			representation.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setRepresentationButtonMode(ButtonsModeEnum newValue)
	 */
	public void setRepresentationButtonMode(ButtonsModeEnum newValue) {
		representation.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addFilterRepresentation(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRepresentation(ViewerFilter filter) {
		representation.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addBusinessFilterRepresentation(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRepresentation(ViewerFilter filter) {
		representation.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initUseCase(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initUseCase(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		useCase.setContentProvider(contentProvider);
		useCase.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.useCase);
		if (readOnly && useCase.getTable().isEnabled()) {
			useCase.setEnabled(false);
			useCase.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !useCase.getTable().isEnabled()) {
			useCase.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#updateUseCase()
	 * @generated
	 */
	public void updateUseCase() {
	useCase.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addFilterUseCase(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToUseCase(ViewerFilter filter) {
		useCaseFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addBusinessFilterUseCase(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToUseCase(ViewerFilter filter) {
		useCaseBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#isContainedInUseCaseTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInUseCaseTable(EObject element) {
		return ((ReferencesTableSettings)useCase.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#getClassifierBehavior()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initClassifierBehavior(EObjectFlatComboSettings)
	 */
	public void initClassifierBehavior(EObjectFlatComboSettings settings) {
		classifierBehavior.setInput(settings);
		if (current != null) {
			classifierBehavior.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.classifierBehavior);
		if (readOnly && classifierBehavior.isEnabled()) {
			classifierBehavior.setEnabled(false);
			classifierBehavior.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !classifierBehavior.isEnabled()) {
			classifierBehavior.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setClassifierBehavior(EObject newValue)
	 * @generated
	 */
	public void setClassifierBehavior(EObject newValue) {
		if (newValue != null) {
			classifierBehavior.setSelection(new StructuredSelection(newValue));
		} else {
			classifierBehavior.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.classifierBehavior);
		if (readOnly && classifierBehavior.isEnabled()) {
			classifierBehavior.setEnabled(false);
			classifierBehavior.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !classifierBehavior.isEnabled()) {
			classifierBehavior.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setClassifierBehaviorButtonMode(ButtonsModeEnum newValue)
	 */
	public void setClassifierBehaviorButtonMode(ButtonsModeEnum newValue) {
		classifierBehavior.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addFilterClassifierBehavior(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClassifierBehavior(ViewerFilter filter) {
		classifierBehavior.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addBusinessFilterClassifierBehavior(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClassifierBehavior(ViewerFilter filter) {
		classifierBehavior.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#getIsActive()
	 * @generated
	 */
	public Boolean getIsActive() {
		return Boolean.valueOf(isActive.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setIsActive(Boolean newValue)
	 * @generated
	 */
	public void setIsActive(Boolean newValue) {
		if (newValue != null) {
			isActive.setSelection(newValue.booleanValue());
		} else {
			isActive.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.isActive);
		if (readOnly && isActive.isEnabled()) {
			isActive.setEnabled(false);
			isActive.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !isActive.isEnabled()) {
			isActive.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#getIsReentrant()
	 * @generated
	 */
	public Boolean getIsReentrant() {
		return Boolean.valueOf(isReentrant.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setIsReentrant(Boolean newValue)
	 * @generated
	 */
	public void setIsReentrant(Boolean newValue) {
		if (newValue != null) {
			isReentrant.setSelection(newValue.booleanValue());
		} else {
			isReentrant.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.isReentrant);
		if (readOnly && isReentrant.isEnabled()) {
			isReentrant.setEnabled(false);
			isReentrant.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !isReentrant.isEnabled()) {
			isReentrant.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initRedefinedBehavior(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedBehavior(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedBehavior.setContentProvider(contentProvider);
		redefinedBehavior.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.redefinedBehavior);
		if (readOnly && redefinedBehavior.getTable().isEnabled()) {
			redefinedBehavior.setEnabled(false);
			redefinedBehavior.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !redefinedBehavior.getTable().isEnabled()) {
			redefinedBehavior.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#updateRedefinedBehavior()
	 * @generated
	 */
	public void updateRedefinedBehavior() {
	redefinedBehavior.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addFilterRedefinedBehavior(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedBehavior(ViewerFilter filter) {
		redefinedBehaviorFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addBusinessFilterRedefinedBehavior(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedBehavior(ViewerFilter filter) {
		redefinedBehaviorBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#isContainedInRedefinedBehaviorTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRedefinedBehaviorTable(EObject element) {
		return ((ReferencesTableSettings)redefinedBehavior.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initPrecondition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initPrecondition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		precondition.setContentProvider(contentProvider);
		precondition.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.precondition);
		if (readOnly && precondition.getTable().isEnabled()) {
			precondition.setEnabled(false);
			precondition.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !precondition.getTable().isEnabled()) {
			precondition.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#updatePrecondition()
	 * @generated
	 */
	public void updatePrecondition() {
	precondition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addFilterPrecondition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToPrecondition(ViewerFilter filter) {
		preconditionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addBusinessFilterPrecondition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToPrecondition(ViewerFilter filter) {
		preconditionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#isContainedInPreconditionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInPreconditionTable(EObject element) {
		return ((ReferencesTableSettings)precondition.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initPostcondition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initPostcondition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		postcondition.setContentProvider(contentProvider);
		postcondition.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.postcondition);
		if (readOnly && postcondition.getTable().isEnabled()) {
			postcondition.setEnabled(false);
			postcondition.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !postcondition.getTable().isEnabled()) {
			postcondition.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#updatePostcondition()
	 * @generated
	 */
	public void updatePostcondition() {
	postcondition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addFilterPostcondition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToPostcondition(ViewerFilter filter) {
		postconditionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addBusinessFilterPostcondition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToPostcondition(ViewerFilter filter) {
		postconditionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#isContainedInPostconditionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInPostconditionTable(EObject element) {
		return ((ReferencesTableSettings)postcondition.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#getSpecification()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initSpecification(EObjectFlatComboSettings)
	 */
	public void initSpecification(EObjectFlatComboSettings settings) {
		specification.setInput(settings);
		if (current != null) {
			specification.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.specification);
		if (readOnly && specification.isEnabled()) {
			specification.setEnabled(false);
			specification.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !specification.isEnabled()) {
			specification.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setSpecification(EObject newValue)
	 * @generated
	 */
	public void setSpecification(EObject newValue) {
		if (newValue != null) {
			specification.setSelection(new StructuredSelection(newValue));
		} else {
			specification.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.specification);
		if (readOnly && specification.isEnabled()) {
			specification.setEnabled(false);
			specification.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !specification.isEnabled()) {
			specification.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#setSpecificationButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSpecificationButtonMode(ButtonsModeEnum newValue) {
		specification.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addFilterSpecification(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSpecification(ViewerFilter filter) {
		specification.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addBusinessFilterSpecification(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSpecification(ViewerFilter filter) {
		specification.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initSubmachineState(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initSubmachineState(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		submachineState.setContentProvider(contentProvider);
		submachineState.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.submachineState);
		if (readOnly && submachineState.getTable().isEnabled()) {
			submachineState.setEnabled(false);
			submachineState.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !submachineState.getTable().isEnabled()) {
			submachineState.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#updateSubmachineState()
	 * @generated
	 */
	public void updateSubmachineState() {
	submachineState.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addFilterSubmachineState(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSubmachineState(ViewerFilter filter) {
		submachineStateFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addBusinessFilterSubmachineState(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSubmachineState(ViewerFilter filter) {
		submachineStateBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#isContainedInSubmachineStateTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInSubmachineStateTable(EObject element) {
		return ((ReferencesTableSettings)submachineState.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#initExtendedStateMachine(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initExtendedStateMachine(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		extendedStateMachine.setContentProvider(contentProvider);
		extendedStateMachine.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.StateMachine.Properties.extendedStateMachine);
		if (readOnly && extendedStateMachine.getTable().isEnabled()) {
			extendedStateMachine.setEnabled(false);
			extendedStateMachine.setToolTipText(UmlMessages.StateMachine_ReadOnly);
		} else if (!readOnly && !extendedStateMachine.getTable().isEnabled()) {
			extendedStateMachine.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#updateExtendedStateMachine()
	 * @generated
	 */
	public void updateExtendedStateMachine() {
	extendedStateMachine.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addFilterExtendedStateMachine(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToExtendedStateMachine(ViewerFilter filter) {
		extendedStateMachineFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#addBusinessFilterExtendedStateMachine(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToExtendedStateMachine(ViewerFilter filter) {
		extendedStateMachineBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.StateMachinePropertiesEditionPart#isContainedInExtendedStateMachineTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInExtendedStateMachineTable(EObject element) {
		return ((ReferencesTableSettings)extendedStateMachine.getInput()).contains(element);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.StateMachine_Part_Title;
	}



}
