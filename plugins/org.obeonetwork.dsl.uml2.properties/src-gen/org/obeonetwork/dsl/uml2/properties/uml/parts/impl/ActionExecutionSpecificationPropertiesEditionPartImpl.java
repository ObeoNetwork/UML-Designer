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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ActionExecutionSpecificationPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ActionExecutionSpecificationPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable covered;
	protected List<ViewerFilter> coveredBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> coveredFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer enclosingInteraction;
	protected EObjectFlatComboViewer enclosingOperand;
	protected EObjectFlatComboViewer start;
	protected EObjectFlatComboViewer finish;
	protected EObjectFlatComboViewer action;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ActionExecutionSpecificationPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence actionExecutionSpecificationStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = actionExecutionSpecificationStep.addStep(UmlViewsRepository.ActionExecutionSpecification.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.ActionExecutionSpecification.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.ActionExecutionSpecification.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.ActionExecutionSpecification.Properties.covered);
		propertiesStep.addStep(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction);
		propertiesStep.addStep(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand);
		propertiesStep.addStep(UmlViewsRepository.ActionExecutionSpecification.Properties.start);
		propertiesStep.addStep(UmlViewsRepository.ActionExecutionSpecification.Properties.finish);
		propertiesStep.addStep(UmlViewsRepository.ActionExecutionSpecification.Properties.action);
		
		
		composer = new PartComposer(actionExecutionSpecificationStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.ActionExecutionSpecification.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.ActionExecutionSpecification.Properties.name) {
					return createNameText(parent);
				}
				if (key == UmlViewsRepository.ActionExecutionSpecification.Properties.visibility) {
					return createVisibilityEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency) {
					return createClientDependencyAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.ActionExecutionSpecification.Properties.covered) {
					return createCoveredAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction) {
					return createEnclosingInteractionFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand) {
					return createEnclosingOperandFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.ActionExecutionSpecification.Properties.start) {
					return createStartFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.ActionExecutionSpecification.Properties.finish) {
					return createFinishFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.ActionExecutionSpecification.Properties.action) {
					return createActionFlatComboViewer(parent);
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
		propertiesGroup.setText(UmlMessages.ActionExecutionSpecificationPropertiesEditionPart_PropertiesGroupLabel);
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
		createDescription(parent, UmlViewsRepository.ActionExecutionSpecification.Properties.name, UmlMessages.ActionExecutionSpecificationPropertiesEditionPart_NameLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, UmlViewsRepository.ActionExecutionSpecification.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionExecutionSpecification.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.ActionExecutionSpecification.Properties.visibility, UmlMessages.ActionExecutionSpecificationPropertiesEditionPart_VisibilityLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.ActionExecutionSpecification.Properties.visibility);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionExecutionSpecification.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency, UmlMessages.ActionExecutionSpecificationPropertiesEditionPart_ClientDependencyLabel);		 
		this.clientDependency = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
		this.clientDependency.createControls(parent);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	protected Composite createCoveredAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.ActionExecutionSpecification.Properties.covered, UmlMessages.ActionExecutionSpecificationPropertiesEditionPart_CoveredLabel);		 
		this.covered = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addCovered(); }
			public void handleEdit(EObject element) { editCovered(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveCovered(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromCovered(element); }
			public void navigateTo(EObject element) { }
		});
		this.covered.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionExecutionSpecification.Properties.covered, UmlViewsRepository.SWT_KIND));
		this.covered.createControls(parent);
		this.covered.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.covered, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData coveredData = new GridData(GridData.FILL_HORIZONTAL);
		coveredData.horizontalSpan = 3;
		this.covered.setLayoutData(coveredData);
		this.covered.disableMove();
		covered.setID(UmlViewsRepository.ActionExecutionSpecification.Properties.covered);
		covered.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addCovered() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(covered.getInput(), coveredFilters, coveredBusinessFilters,
		"covered", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.covered,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				covered.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveCovered(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.covered, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		covered.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromCovered(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.covered, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		covered.refresh();
	}

	/**
	 * @generated
	 */
	protected void editCovered(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				covered.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createEnclosingInteractionFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction, UmlMessages.ActionExecutionSpecificationPropertiesEditionPart_EnclosingInteractionLabel);
		enclosingInteraction = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction, UmlViewsRepository.SWT_KIND));
		enclosingInteraction.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		enclosingInteraction.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getEnclosingInteraction()));
			}

		});
		GridData enclosingInteractionData = new GridData(GridData.FILL_HORIZONTAL);
		enclosingInteraction.setLayoutData(enclosingInteractionData);
		enclosingInteraction.setID(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createEnclosingOperandFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand, UmlMessages.ActionExecutionSpecificationPropertiesEditionPart_EnclosingOperandLabel);
		enclosingOperand = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand, UmlViewsRepository.SWT_KIND));
		enclosingOperand.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		enclosingOperand.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getEnclosingOperand()));
			}

		});
		GridData enclosingOperandData = new GridData(GridData.FILL_HORIZONTAL);
		enclosingOperand.setLayoutData(enclosingOperandData);
		enclosingOperand.setID(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createStartFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.ActionExecutionSpecification.Properties.start, UmlMessages.ActionExecutionSpecificationPropertiesEditionPart_StartLabel);
		start = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ActionExecutionSpecification.Properties.start, UmlViewsRepository.SWT_KIND));
		start.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		start.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.start, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getStart()));
			}

		});
		GridData startData = new GridData(GridData.FILL_HORIZONTAL);
		start.setLayoutData(startData);
		start.setID(UmlViewsRepository.ActionExecutionSpecification.Properties.start);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionExecutionSpecification.Properties.start, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createFinishFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.ActionExecutionSpecification.Properties.finish, UmlMessages.ActionExecutionSpecificationPropertiesEditionPart_FinishLabel);
		finish = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ActionExecutionSpecification.Properties.finish, UmlViewsRepository.SWT_KIND));
		finish.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		finish.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.finish, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getFinish()));
			}

		});
		GridData finishData = new GridData(GridData.FILL_HORIZONTAL);
		finish.setLayoutData(finishData);
		finish.setID(UmlViewsRepository.ActionExecutionSpecification.Properties.finish);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionExecutionSpecification.Properties.finish, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createActionFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.ActionExecutionSpecification.Properties.action, UmlMessages.ActionExecutionSpecificationPropertiesEditionPart_ActionLabel);
		action = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ActionExecutionSpecification.Properties.action, UmlViewsRepository.SWT_KIND));
		action.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		action.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionExecutionSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.ActionExecutionSpecification.Properties.action, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getAction()));
			}

		});
		GridData actionData = new GridData(GridData.FILL_HORIZONTAL);
		action.setLayoutData(actionData);
		action.setID(UmlViewsRepository.ActionExecutionSpecification.Properties.action);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionExecutionSpecification.Properties.action, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.name);
		if (readOnly && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
		Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#initCovered(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initCovered(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		covered.setContentProvider(contentProvider);
		covered.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.covered);
		if (readOnly && covered.getTable().isEnabled()) {
			covered.setEnabled(false);
			covered.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !covered.getTable().isEnabled()) {
			covered.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#updateCovered()
	 * @generated
	 */
	public void updateCovered() {
	covered.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#addFilterCovered(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToCovered(ViewerFilter filter) {
		coveredFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#addBusinessFilterCovered(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToCovered(ViewerFilter filter) {
		coveredBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#isContainedInCoveredTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInCoveredTable(EObject element) {
		return ((ReferencesTableSettings)covered.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#getEnclosingInteraction()
	 * @generated
	 */
	public EObject getEnclosingInteraction() {
		if (enclosingInteraction.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) enclosingInteraction.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#initEnclosingInteraction(EObjectFlatComboSettings)
	 */
	public void initEnclosingInteraction(EObjectFlatComboSettings settings) {
		enclosingInteraction.setInput(settings);
		if (current != null) {
			enclosingInteraction.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction);
		if (readOnly && enclosingInteraction.isEnabled()) {
			enclosingInteraction.setEnabled(false);
			enclosingInteraction.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !enclosingInteraction.isEnabled()) {
			enclosingInteraction.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#setEnclosingInteraction(EObject newValue)
	 * @generated
	 */
	public void setEnclosingInteraction(EObject newValue) {
		if (newValue != null) {
			enclosingInteraction.setSelection(new StructuredSelection(newValue));
		} else {
			enclosingInteraction.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction);
		if (readOnly && enclosingInteraction.isEnabled()) {
			enclosingInteraction.setEnabled(false);
			enclosingInteraction.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !enclosingInteraction.isEnabled()) {
			enclosingInteraction.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#setEnclosingInteractionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setEnclosingInteractionButtonMode(ButtonsModeEnum newValue) {
		enclosingInteraction.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#addFilterEnclosingInteraction(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEnclosingInteraction(ViewerFilter filter) {
		enclosingInteraction.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#addBusinessFilterEnclosingInteraction(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEnclosingInteraction(ViewerFilter filter) {
		enclosingInteraction.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#getEnclosingOperand()
	 * @generated
	 */
	public EObject getEnclosingOperand() {
		if (enclosingOperand.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) enclosingOperand.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#initEnclosingOperand(EObjectFlatComboSettings)
	 */
	public void initEnclosingOperand(EObjectFlatComboSettings settings) {
		enclosingOperand.setInput(settings);
		if (current != null) {
			enclosingOperand.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand);
		if (readOnly && enclosingOperand.isEnabled()) {
			enclosingOperand.setEnabled(false);
			enclosingOperand.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !enclosingOperand.isEnabled()) {
			enclosingOperand.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#setEnclosingOperand(EObject newValue)
	 * @generated
	 */
	public void setEnclosingOperand(EObject newValue) {
		if (newValue != null) {
			enclosingOperand.setSelection(new StructuredSelection(newValue));
		} else {
			enclosingOperand.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand);
		if (readOnly && enclosingOperand.isEnabled()) {
			enclosingOperand.setEnabled(false);
			enclosingOperand.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !enclosingOperand.isEnabled()) {
			enclosingOperand.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#setEnclosingOperandButtonMode(ButtonsModeEnum newValue)
	 */
	public void setEnclosingOperandButtonMode(ButtonsModeEnum newValue) {
		enclosingOperand.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#addFilterEnclosingOperand(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEnclosingOperand(ViewerFilter filter) {
		enclosingOperand.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#addBusinessFilterEnclosingOperand(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEnclosingOperand(ViewerFilter filter) {
		enclosingOperand.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#getStart()
	 * @generated
	 */
	public EObject getStart() {
		if (start.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) start.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#initStart(EObjectFlatComboSettings)
	 */
	public void initStart(EObjectFlatComboSettings settings) {
		start.setInput(settings);
		if (current != null) {
			start.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.start);
		if (readOnly && start.isEnabled()) {
			start.setEnabled(false);
			start.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !start.isEnabled()) {
			start.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#setStart(EObject newValue)
	 * @generated
	 */
	public void setStart(EObject newValue) {
		if (newValue != null) {
			start.setSelection(new StructuredSelection(newValue));
		} else {
			start.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.start);
		if (readOnly && start.isEnabled()) {
			start.setEnabled(false);
			start.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !start.isEnabled()) {
			start.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#setStartButtonMode(ButtonsModeEnum newValue)
	 */
	public void setStartButtonMode(ButtonsModeEnum newValue) {
		start.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#addFilterStart(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToStart(ViewerFilter filter) {
		start.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#addBusinessFilterStart(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToStart(ViewerFilter filter) {
		start.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#getFinish()
	 * @generated
	 */
	public EObject getFinish() {
		if (finish.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) finish.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#initFinish(EObjectFlatComboSettings)
	 */
	public void initFinish(EObjectFlatComboSettings settings) {
		finish.setInput(settings);
		if (current != null) {
			finish.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.finish);
		if (readOnly && finish.isEnabled()) {
			finish.setEnabled(false);
			finish.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !finish.isEnabled()) {
			finish.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#setFinish(EObject newValue)
	 * @generated
	 */
	public void setFinish(EObject newValue) {
		if (newValue != null) {
			finish.setSelection(new StructuredSelection(newValue));
		} else {
			finish.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.finish);
		if (readOnly && finish.isEnabled()) {
			finish.setEnabled(false);
			finish.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !finish.isEnabled()) {
			finish.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#setFinishButtonMode(ButtonsModeEnum newValue)
	 */
	public void setFinishButtonMode(ButtonsModeEnum newValue) {
		finish.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#addFilterFinish(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToFinish(ViewerFilter filter) {
		finish.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#addBusinessFilterFinish(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToFinish(ViewerFilter filter) {
		finish.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#getAction()
	 * @generated
	 */
	public EObject getAction() {
		if (action.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) action.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#initAction(EObjectFlatComboSettings)
	 */
	public void initAction(EObjectFlatComboSettings settings) {
		action.setInput(settings);
		if (current != null) {
			action.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.action);
		if (readOnly && action.isEnabled()) {
			action.setEnabled(false);
			action.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !action.isEnabled()) {
			action.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#setAction(EObject newValue)
	 * @generated
	 */
	public void setAction(EObject newValue) {
		if (newValue != null) {
			action.setSelection(new StructuredSelection(newValue));
		} else {
			action.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionExecutionSpecification.Properties.action);
		if (readOnly && action.isEnabled()) {
			action.setEnabled(false);
			action.setToolTipText(UmlMessages.ActionExecutionSpecification_ReadOnly);
		} else if (!readOnly && !action.isEnabled()) {
			action.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#setActionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setActionButtonMode(ButtonsModeEnum newValue) {
		action.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#addFilterAction(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToAction(ViewerFilter filter) {
		action.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart#addBusinessFilterAction(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToAction(ViewerFilter filter) {
		action.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.ActionExecutionSpecification_Part_Title;
	}



}
