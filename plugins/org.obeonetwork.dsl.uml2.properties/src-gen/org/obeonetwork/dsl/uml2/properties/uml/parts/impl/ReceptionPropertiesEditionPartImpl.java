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

import org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ReceptionPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ReceptionPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected Button isLeaf;
	protected Button isStatic;
	protected Button isAbstract;
	protected ReferencesTable method;
	protected List<ViewerFilter> methodBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> methodFilters = new ArrayList<ViewerFilter>();
	protected EMFComboViewer concurrency;
	protected ReferencesTable raisedException;
	protected List<ViewerFilter> raisedExceptionBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> raisedExceptionFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer signal;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ReceptionPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence receptionStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = receptionStep.addStep(UmlViewsRepository.Reception.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.Reception.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.Reception.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.Reception.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.Reception.Properties.isLeaf);
		propertiesStep.addStep(UmlViewsRepository.Reception.Properties.isStatic);
		propertiesStep.addStep(UmlViewsRepository.Reception.Properties.isAbstract);
		propertiesStep.addStep(UmlViewsRepository.Reception.Properties.method);
		propertiesStep.addStep(UmlViewsRepository.Reception.Properties.concurrency);
		propertiesStep.addStep(UmlViewsRepository.Reception.Properties.raisedException);
		propertiesStep.addStep(UmlViewsRepository.Reception.Properties.signal);
		
		
		composer = new PartComposer(receptionStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.Reception.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.Reception.Properties.name) {
					return createNameText(parent);
				}
				if (key == UmlViewsRepository.Reception.Properties.visibility) {
					return createVisibilityEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.Reception.Properties.clientDependency) {
					return createClientDependencyAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Reception.Properties.isLeaf) {
					return createIsLeafCheckbox(parent);
				}
				if (key == UmlViewsRepository.Reception.Properties.isStatic) {
					return createIsStaticCheckbox(parent);
				}
				if (key == UmlViewsRepository.Reception.Properties.isAbstract) {
					return createIsAbstractCheckbox(parent);
				}
				if (key == UmlViewsRepository.Reception.Properties.method) {
					return createMethodAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Reception.Properties.concurrency) {
					return createConcurrencyEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.Reception.Properties.raisedException) {
					return createRaisedExceptionAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Reception.Properties.signal) {
					return createSignalFlatComboViewer(parent);
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
		propertiesGroup.setText(UmlMessages.ReceptionPropertiesEditionPart_PropertiesGroupLabel);
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
		createDescription(parent, UmlViewsRepository.Reception.Properties.name, UmlMessages.ReceptionPropertiesEditionPart_NameLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, UmlViewsRepository.Reception.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Reception.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Reception.Properties.visibility, UmlMessages.ReceptionPropertiesEditionPart_VisibilityLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.Reception.Properties.visibility);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Reception.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Reception.Properties.clientDependency, UmlMessages.ReceptionPropertiesEditionPart_ClientDependencyLabel);		 
		this.clientDependency = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Reception.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
		this.clientDependency.createControls(parent);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.Reception.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		isLeaf.setText(getDescription(UmlViewsRepository.Reception.Properties.isLeaf, UmlMessages.ReceptionPropertiesEditionPart_IsLeafLabel));
		isLeaf.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
			}

		});
		GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
		isLeafData.horizontalSpan = 2;
		isLeaf.setLayoutData(isLeafData);
		EditingUtils.setID(isLeaf, UmlViewsRepository.Reception.Properties.isLeaf);
		EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Reception.Properties.isLeaf, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsStaticCheckbox(Composite parent) {
		isStatic = new Button(parent, SWT.CHECK);
		isStatic.setText(getDescription(UmlViewsRepository.Reception.Properties.isStatic, UmlMessages.ReceptionPropertiesEditionPart_IsStaticLabel));
		isStatic.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.isStatic, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isStatic.getSelection())));
			}

		});
		GridData isStaticData = new GridData(GridData.FILL_HORIZONTAL);
		isStaticData.horizontalSpan = 2;
		isStatic.setLayoutData(isStaticData);
		EditingUtils.setID(isStatic, UmlViewsRepository.Reception.Properties.isStatic);
		EditingUtils.setEEFtype(isStatic, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Reception.Properties.isStatic, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsAbstractCheckbox(Composite parent) {
		isAbstract = new Button(parent, SWT.CHECK);
		isAbstract.setText(getDescription(UmlViewsRepository.Reception.Properties.isAbstract, UmlMessages.ReceptionPropertiesEditionPart_IsAbstractLabel));
		isAbstract.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.isAbstract, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isAbstract.getSelection())));
			}

		});
		GridData isAbstractData = new GridData(GridData.FILL_HORIZONTAL);
		isAbstractData.horizontalSpan = 2;
		isAbstract.setLayoutData(isAbstractData);
		EditingUtils.setID(isAbstract, UmlViewsRepository.Reception.Properties.isAbstract);
		EditingUtils.setEEFtype(isAbstract, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Reception.Properties.isAbstract, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createMethodAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Reception.Properties.method, UmlMessages.ReceptionPropertiesEditionPart_MethodLabel);		 
		this.method = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addMethod(); }
			public void handleEdit(EObject element) { editMethod(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveMethod(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromMethod(element); }
			public void navigateTo(EObject element) { }
		});
		this.method.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Reception.Properties.method, UmlViewsRepository.SWT_KIND));
		this.method.createControls(parent);
		this.method.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.method, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData methodData = new GridData(GridData.FILL_HORIZONTAL);
		methodData.horizontalSpan = 3;
		this.method.setLayoutData(methodData);
		this.method.disableMove();
		method.setID(UmlViewsRepository.Reception.Properties.method);
		method.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addMethod() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(method.getInput(), methodFilters, methodBusinessFilters,
		"method", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.method,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				method.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveMethod(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.method, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		method.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromMethod(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.method, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		method.refresh();
	}

	/**
	 * @generated
	 */
	protected void editMethod(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				method.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	
	protected Composite createConcurrencyEMFComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Reception.Properties.concurrency, UmlMessages.ReceptionPropertiesEditionPart_ConcurrencyLabel);
		concurrency = new EMFComboViewer(parent);
		concurrency.setContentProvider(new ArrayContentProvider());
		concurrency.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
		GridData concurrencyData = new GridData(GridData.FILL_HORIZONTAL);
		concurrency.getCombo().setLayoutData(concurrencyData);
		concurrency.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 * 	@generated
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.concurrency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getConcurrency()));
			}

		});
		concurrency.setID(UmlViewsRepository.Reception.Properties.concurrency);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Reception.Properties.concurrency, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createRaisedExceptionAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Reception.Properties.raisedException, UmlMessages.ReceptionPropertiesEditionPart_RaisedExceptionLabel);		 
		this.raisedException = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addRaisedException(); }
			public void handleEdit(EObject element) { editRaisedException(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRaisedException(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRaisedException(element); }
			public void navigateTo(EObject element) { }
		});
		this.raisedException.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Reception.Properties.raisedException, UmlViewsRepository.SWT_KIND));
		this.raisedException.createControls(parent);
		this.raisedException.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.raisedException, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData raisedExceptionData = new GridData(GridData.FILL_HORIZONTAL);
		raisedExceptionData.horizontalSpan = 3;
		this.raisedException.setLayoutData(raisedExceptionData);
		this.raisedException.disableMove();
		raisedException.setID(UmlViewsRepository.Reception.Properties.raisedException);
		raisedException.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addRaisedException() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(raisedException.getInput(), raisedExceptionFilters, raisedExceptionBusinessFilters,
		"raisedException", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.raisedException,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				raisedException.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveRaisedException(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.raisedException, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		raisedException.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromRaisedException(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.raisedException, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		raisedException.refresh();
	}

	/**
	 * @generated
	 */
	protected void editRaisedException(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				raisedException.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createSignalFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Reception.Properties.signal, UmlMessages.ReceptionPropertiesEditionPart_SignalLabel);
		signal = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Reception.Properties.signal, UmlViewsRepository.SWT_KIND));
		signal.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		signal.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReceptionPropertiesEditionPartImpl.this, UmlViewsRepository.Reception.Properties.signal, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getSignal()));
			}

		});
		GridData signalData = new GridData(GridData.FILL_HORIZONTAL);
		signal.setLayoutData(signalData);
		signal.setID(UmlViewsRepository.Reception.Properties.signal);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Reception.Properties.signal, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Reception.Properties.name);
		if (readOnly && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(UmlMessages.Reception_ReadOnly);
		} else if (!readOnly && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
		Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Reception.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.Reception_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.Reception.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.Reception_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Reception.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.Reception_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
		return Boolean.valueOf(isLeaf.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
		if (newValue != null) {
			isLeaf.setSelection(newValue.booleanValue());
		} else {
			isLeaf.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Reception.Properties.isLeaf);
		if (readOnly && isLeaf.isEnabled()) {
			isLeaf.setEnabled(false);
			isLeaf.setToolTipText(UmlMessages.Reception_ReadOnly);
		} else if (!readOnly && !isLeaf.isEnabled()) {
			isLeaf.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#getIsStatic()
	 * @generated
	 */
	public Boolean getIsStatic() {
		return Boolean.valueOf(isStatic.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#setIsStatic(Boolean newValue)
	 * @generated
	 */
	public void setIsStatic(Boolean newValue) {
		if (newValue != null) {
			isStatic.setSelection(newValue.booleanValue());
		} else {
			isStatic.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Reception.Properties.isStatic);
		if (readOnly && isStatic.isEnabled()) {
			isStatic.setEnabled(false);
			isStatic.setToolTipText(UmlMessages.Reception_ReadOnly);
		} else if (!readOnly && !isStatic.isEnabled()) {
			isStatic.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#getIsAbstract()
	 * @generated
	 */
	public Boolean getIsAbstract() {
		return Boolean.valueOf(isAbstract.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#setIsAbstract(Boolean newValue)
	 * @generated
	 */
	public void setIsAbstract(Boolean newValue) {
		if (newValue != null) {
			isAbstract.setSelection(newValue.booleanValue());
		} else {
			isAbstract.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Reception.Properties.isAbstract);
		if (readOnly && isAbstract.isEnabled()) {
			isAbstract.setEnabled(false);
			isAbstract.setToolTipText(UmlMessages.Reception_ReadOnly);
		} else if (!readOnly && !isAbstract.isEnabled()) {
			isAbstract.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#initMethod(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initMethod(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		method.setContentProvider(contentProvider);
		method.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Reception.Properties.method);
		if (readOnly && method.getTable().isEnabled()) {
			method.setEnabled(false);
			method.setToolTipText(UmlMessages.Reception_ReadOnly);
		} else if (!readOnly && !method.getTable().isEnabled()) {
			method.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#updateMethod()
	 * @generated
	 */
	public void updateMethod() {
	method.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#addFilterMethod(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToMethod(ViewerFilter filter) {
		methodFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#addBusinessFilterMethod(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToMethod(ViewerFilter filter) {
		methodBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#isContainedInMethodTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInMethodTable(EObject element) {
		return ((ReferencesTableSettings)method.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#getConcurrency()
	 * @generated
	 */
	public Enumerator getConcurrency() {
		Enumerator selection = (Enumerator) ((StructuredSelection) concurrency.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#initConcurrency(Object input, Enumerator current)
	 */
	public void initConcurrency(Object input, Enumerator current) {
		concurrency.setInput(input);
		concurrency.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Reception.Properties.concurrency);
		if (readOnly && concurrency.isEnabled()) {
			concurrency.setEnabled(false);
			concurrency.setToolTipText(UmlMessages.Reception_ReadOnly);
		} else if (!readOnly && !concurrency.isEnabled()) {
			concurrency.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#setConcurrency(Enumerator newValue)
	 * @generated
	 */
	public void setConcurrency(Enumerator newValue) {
		concurrency.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.Reception.Properties.concurrency);
		if (readOnly && concurrency.isEnabled()) {
			concurrency.setEnabled(false);
			concurrency.setToolTipText(UmlMessages.Reception_ReadOnly);
		} else if (!readOnly && !concurrency.isEnabled()) {
			concurrency.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#initRaisedException(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRaisedException(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		raisedException.setContentProvider(contentProvider);
		raisedException.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Reception.Properties.raisedException);
		if (readOnly && raisedException.getTable().isEnabled()) {
			raisedException.setEnabled(false);
			raisedException.setToolTipText(UmlMessages.Reception_ReadOnly);
		} else if (!readOnly && !raisedException.getTable().isEnabled()) {
			raisedException.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#updateRaisedException()
	 * @generated
	 */
	public void updateRaisedException() {
	raisedException.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#addFilterRaisedException(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRaisedException(ViewerFilter filter) {
		raisedExceptionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#addBusinessFilterRaisedException(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRaisedException(ViewerFilter filter) {
		raisedExceptionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#isContainedInRaisedExceptionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRaisedExceptionTable(EObject element) {
		return ((ReferencesTableSettings)raisedException.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#getSignal()
	 * @generated
	 */
	public EObject getSignal() {
		if (signal.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) signal.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#initSignal(EObjectFlatComboSettings)
	 */
	public void initSignal(EObjectFlatComboSettings settings) {
		signal.setInput(settings);
		if (current != null) {
			signal.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Reception.Properties.signal);
		if (readOnly && signal.isEnabled()) {
			signal.setEnabled(false);
			signal.setToolTipText(UmlMessages.Reception_ReadOnly);
		} else if (!readOnly && !signal.isEnabled()) {
			signal.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#setSignal(EObject newValue)
	 * @generated
	 */
	public void setSignal(EObject newValue) {
		if (newValue != null) {
			signal.setSelection(new StructuredSelection(newValue));
		} else {
			signal.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Reception.Properties.signal);
		if (readOnly && signal.isEnabled()) {
			signal.setEnabled(false);
			signal.setToolTipText(UmlMessages.Reception_ReadOnly);
		} else if (!readOnly && !signal.isEnabled()) {
			signal.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#setSignalButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSignalButtonMode(ButtonsModeEnum newValue) {
		signal.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#addFilterSignal(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSignal(ViewerFilter filter) {
		signal.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart#addBusinessFilterSignal(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSignal(ViewerFilter filter) {
		signal.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.Reception_Part_Title;
	}



}
