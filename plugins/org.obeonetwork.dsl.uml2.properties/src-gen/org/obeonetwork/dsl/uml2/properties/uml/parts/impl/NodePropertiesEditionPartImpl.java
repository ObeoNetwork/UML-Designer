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

import org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class NodePropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, NodePropertiesEditionPart {

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



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public NodePropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence nodeStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = nodeStep.addStep(UmlViewsRepository.Node.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.Node.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.Node.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.Node.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.Node.Properties.isLeaf);
		propertiesStep.addStep(UmlViewsRepository.Node.Properties.owningTemplateParameter);
		propertiesStep.addStep(UmlViewsRepository.Node.Properties.templateParameter);
		propertiesStep.addStep(UmlViewsRepository.Node.Properties.isAbstract);
		propertiesStep.addStep(UmlViewsRepository.Node.Properties.powertypeExtent);
		propertiesStep.addStep(UmlViewsRepository.Node.Properties.redefinedClassifier);
		propertiesStep.addStep(UmlViewsRepository.Node.Properties.representation);
		propertiesStep.addStep(UmlViewsRepository.Node.Properties.useCase);
		propertiesStep.addStep(UmlViewsRepository.Node.Properties.classifierBehavior);
		propertiesStep.addStep(UmlViewsRepository.Node.Properties.isActive);
		
		
		composer = new PartComposer(nodeStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.Node.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.Node.Properties.name) {
					return createNameText(parent);
				}
				if (key == UmlViewsRepository.Node.Properties.visibility) {
					return createVisibilityEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.Node.Properties.clientDependency) {
					return createClientDependencyAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Node.Properties.isLeaf) {
					return createIsLeafCheckbox(parent);
				}
				if (key == UmlViewsRepository.Node.Properties.owningTemplateParameter) {
					return createOwningTemplateParameterFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Node.Properties.templateParameter) {
					return createTemplateParameterFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Node.Properties.isAbstract) {
					return createIsAbstractCheckbox(parent);
				}
				if (key == UmlViewsRepository.Node.Properties.powertypeExtent) {
					return createPowertypeExtentAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Node.Properties.redefinedClassifier) {
					return createRedefinedClassifierAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Node.Properties.representation) {
					return createRepresentationFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Node.Properties.useCase) {
					return createUseCaseAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Node.Properties.classifierBehavior) {
					return createClassifierBehaviorFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Node.Properties.isActive) {
					return createIsActiveCheckbox(parent);
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
		propertiesGroup.setText(UmlMessages.NodePropertiesEditionPart_PropertiesGroupLabel);
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
		createDescription(parent, UmlViewsRepository.Node.Properties.name, UmlMessages.NodePropertiesEditionPart_NameLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, UmlViewsRepository.Node.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Node.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Node.Properties.visibility, UmlMessages.NodePropertiesEditionPart_VisibilityLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.Node.Properties.visibility);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Node.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Node.Properties.clientDependency, UmlMessages.NodePropertiesEditionPart_ClientDependencyLabel);		 
		this.clientDependency = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Node.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
		this.clientDependency.createControls(parent);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.Node.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		isLeaf.setText(getDescription(UmlViewsRepository.Node.Properties.isLeaf, UmlMessages.NodePropertiesEditionPart_IsLeafLabel));
		isLeaf.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
			}

		});
		GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
		isLeafData.horizontalSpan = 2;
		isLeaf.setLayoutData(isLeafData);
		EditingUtils.setID(isLeaf, UmlViewsRepository.Node.Properties.isLeaf);
		EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Node.Properties.isLeaf, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createOwningTemplateParameterFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Node.Properties.owningTemplateParameter, UmlMessages.NodePropertiesEditionPart_OwningTemplateParameterLabel);
		owningTemplateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Node.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND));
		owningTemplateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		owningTemplateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.owningTemplateParameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getOwningTemplateParameter()));
			}

		});
		GridData owningTemplateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		owningTemplateParameter.setLayoutData(owningTemplateParameterData);
		owningTemplateParameter.setID(UmlViewsRepository.Node.Properties.owningTemplateParameter);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Node.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createTemplateParameterFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Node.Properties.templateParameter, UmlMessages.NodePropertiesEditionPart_TemplateParameterLabel);
		templateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Node.Properties.templateParameter, UmlViewsRepository.SWT_KIND));
		templateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		templateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.templateParameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getTemplateParameter()));
			}

		});
		GridData templateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		templateParameter.setLayoutData(templateParameterData);
		templateParameter.setID(UmlViewsRepository.Node.Properties.templateParameter);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Node.Properties.templateParameter, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsAbstractCheckbox(Composite parent) {
		isAbstract = new Button(parent, SWT.CHECK);
		isAbstract.setText(getDescription(UmlViewsRepository.Node.Properties.isAbstract, UmlMessages.NodePropertiesEditionPart_IsAbstractLabel));
		isAbstract.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.isAbstract, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isAbstract.getSelection())));
			}

		});
		GridData isAbstractData = new GridData(GridData.FILL_HORIZONTAL);
		isAbstractData.horizontalSpan = 2;
		isAbstract.setLayoutData(isAbstractData);
		EditingUtils.setID(isAbstract, UmlViewsRepository.Node.Properties.isAbstract);
		EditingUtils.setEEFtype(isAbstract, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Node.Properties.isAbstract, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createPowertypeExtentAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Node.Properties.powertypeExtent, UmlMessages.NodePropertiesEditionPart_PowertypeExtentLabel);		 
		this.powertypeExtent = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addPowertypeExtent(); }
			public void handleEdit(EObject element) { editPowertypeExtent(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { movePowertypeExtent(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromPowertypeExtent(element); }
			public void navigateTo(EObject element) { }
		});
		this.powertypeExtent.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Node.Properties.powertypeExtent, UmlViewsRepository.SWT_KIND));
		this.powertypeExtent.createControls(parent);
		this.powertypeExtent.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.powertypeExtent, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData powertypeExtentData = new GridData(GridData.FILL_HORIZONTAL);
		powertypeExtentData.horizontalSpan = 3;
		this.powertypeExtent.setLayoutData(powertypeExtentData);
		this.powertypeExtent.disableMove();
		powertypeExtent.setID(UmlViewsRepository.Node.Properties.powertypeExtent);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.powertypeExtent,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.powertypeExtent, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		powertypeExtent.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromPowertypeExtent(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.powertypeExtent, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		String label = getDescription(UmlViewsRepository.Node.Properties.redefinedClassifier, UmlMessages.NodePropertiesEditionPart_RedefinedClassifierLabel);		 
		this.redefinedClassifier = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addRedefinedClassifier(); }
			public void handleEdit(EObject element) { editRedefinedClassifier(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedClassifier(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRedefinedClassifier(element); }
			public void navigateTo(EObject element) { }
		});
		this.redefinedClassifier.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Node.Properties.redefinedClassifier, UmlViewsRepository.SWT_KIND));
		this.redefinedClassifier.createControls(parent);
		this.redefinedClassifier.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.redefinedClassifier, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData redefinedClassifierData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedClassifierData.horizontalSpan = 3;
		this.redefinedClassifier.setLayoutData(redefinedClassifierData);
		this.redefinedClassifier.disableMove();
		redefinedClassifier.setID(UmlViewsRepository.Node.Properties.redefinedClassifier);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.redefinedClassifier,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.redefinedClassifier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		redefinedClassifier.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromRedefinedClassifier(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.redefinedClassifier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		createDescription(parent, UmlViewsRepository.Node.Properties.representation, UmlMessages.NodePropertiesEditionPart_RepresentationLabel);
		representation = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Node.Properties.representation, UmlViewsRepository.SWT_KIND));
		representation.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		representation.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.representation, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getRepresentation()));
			}

		});
		GridData representationData = new GridData(GridData.FILL_HORIZONTAL);
		representation.setLayoutData(representationData);
		representation.setID(UmlViewsRepository.Node.Properties.representation);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Node.Properties.representation, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createUseCaseAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Node.Properties.useCase, UmlMessages.NodePropertiesEditionPart_UseCaseLabel);		 
		this.useCase = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addUseCase(); }
			public void handleEdit(EObject element) { editUseCase(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveUseCase(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromUseCase(element); }
			public void navigateTo(EObject element) { }
		});
		this.useCase.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Node.Properties.useCase, UmlViewsRepository.SWT_KIND));
		this.useCase.createControls(parent);
		this.useCase.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.useCase, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData useCaseData = new GridData(GridData.FILL_HORIZONTAL);
		useCaseData.horizontalSpan = 3;
		this.useCase.setLayoutData(useCaseData);
		this.useCase.disableMove();
		useCase.setID(UmlViewsRepository.Node.Properties.useCase);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.useCase,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.useCase, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		useCase.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromUseCase(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.useCase, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		createDescription(parent, UmlViewsRepository.Node.Properties.classifierBehavior, UmlMessages.NodePropertiesEditionPart_ClassifierBehaviorLabel);
		classifierBehavior = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Node.Properties.classifierBehavior, UmlViewsRepository.SWT_KIND));
		classifierBehavior.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		classifierBehavior.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.classifierBehavior, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getClassifierBehavior()));
			}

		});
		GridData classifierBehaviorData = new GridData(GridData.FILL_HORIZONTAL);
		classifierBehavior.setLayoutData(classifierBehaviorData);
		classifierBehavior.setID(UmlViewsRepository.Node.Properties.classifierBehavior);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Node.Properties.classifierBehavior, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsActiveCheckbox(Composite parent) {
		isActive = new Button(parent, SWT.CHECK);
		isActive.setText(getDescription(UmlViewsRepository.Node.Properties.isActive, UmlMessages.NodePropertiesEditionPart_IsActiveLabel));
		isActive.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NodePropertiesEditionPartImpl.this, UmlViewsRepository.Node.Properties.isActive, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isActive.getSelection())));
			}

		});
		GridData isActiveData = new GridData(GridData.FILL_HORIZONTAL);
		isActiveData.horizontalSpan = 2;
		isActive.setLayoutData(isActiveData);
		EditingUtils.setID(isActive, UmlViewsRepository.Node.Properties.isActive);
		EditingUtils.setEEFtype(isActive, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Node.Properties.isActive, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.name);
		if (readOnly && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
		Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
		return Boolean.valueOf(isLeaf.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
		if (newValue != null) {
			isLeaf.setSelection(newValue.booleanValue());
		} else {
			isLeaf.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.isLeaf);
		if (readOnly && isLeaf.isEnabled()) {
			isLeaf.setEnabled(false);
			isLeaf.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !isLeaf.isEnabled()) {
			isLeaf.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#getOwningTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#initOwningTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initOwningTemplateParameter(EObjectFlatComboSettings settings) {
		owningTemplateParameter.setInput(settings);
		if (current != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.owningTemplateParameter);
		if (readOnly && owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(false);
			owningTemplateParameter.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#setOwningTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setOwningTemplateParameter(EObject newValue) {
		if (newValue != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(newValue));
		} else {
			owningTemplateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.owningTemplateParameter);
		if (readOnly && owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(false);
			owningTemplateParameter.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		owningTemplateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addBusinessFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#getTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#initTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initTemplateParameter(EObjectFlatComboSettings settings) {
		templateParameter.setInput(settings);
		if (current != null) {
			templateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.templateParameter);
		if (readOnly && templateParameter.isEnabled()) {
			templateParameter.setEnabled(false);
			templateParameter.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !templateParameter.isEnabled()) {
			templateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#setTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setTemplateParameter(EObject newValue) {
		if (newValue != null) {
			templateParameter.setSelection(new StructuredSelection(newValue));
		} else {
			templateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.templateParameter);
		if (readOnly && templateParameter.isEnabled()) {
			templateParameter.setEnabled(false);
			templateParameter.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !templateParameter.isEnabled()) {
			templateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#setTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		templateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addBusinessFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#getIsAbstract()
	 * @generated
	 */
	public Boolean getIsAbstract() {
		return Boolean.valueOf(isAbstract.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#setIsAbstract(Boolean newValue)
	 * @generated
	 */
	public void setIsAbstract(Boolean newValue) {
		if (newValue != null) {
			isAbstract.setSelection(newValue.booleanValue());
		} else {
			isAbstract.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.isAbstract);
		if (readOnly && isAbstract.isEnabled()) {
			isAbstract.setEnabled(false);
			isAbstract.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !isAbstract.isEnabled()) {
			isAbstract.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#initPowertypeExtent(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initPowertypeExtent(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		powertypeExtent.setContentProvider(contentProvider);
		powertypeExtent.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.powertypeExtent);
		if (readOnly && powertypeExtent.getTable().isEnabled()) {
			powertypeExtent.setEnabled(false);
			powertypeExtent.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !powertypeExtent.getTable().isEnabled()) {
			powertypeExtent.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#updatePowertypeExtent()
	 * @generated
	 */
	public void updatePowertypeExtent() {
	powertypeExtent.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addFilterPowertypeExtent(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToPowertypeExtent(ViewerFilter filter) {
		powertypeExtentFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addBusinessFilterPowertypeExtent(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToPowertypeExtent(ViewerFilter filter) {
		powertypeExtentBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#isContainedInPowertypeExtentTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInPowertypeExtentTable(EObject element) {
		return ((ReferencesTableSettings)powertypeExtent.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#initRedefinedClassifier(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedClassifier(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedClassifier.setContentProvider(contentProvider);
		redefinedClassifier.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.redefinedClassifier);
		if (readOnly && redefinedClassifier.getTable().isEnabled()) {
			redefinedClassifier.setEnabled(false);
			redefinedClassifier.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !redefinedClassifier.getTable().isEnabled()) {
			redefinedClassifier.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#updateRedefinedClassifier()
	 * @generated
	 */
	public void updateRedefinedClassifier() {
	redefinedClassifier.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addFilterRedefinedClassifier(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedClassifier(ViewerFilter filter) {
		redefinedClassifierFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addBusinessFilterRedefinedClassifier(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedClassifier(ViewerFilter filter) {
		redefinedClassifierBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#isContainedInRedefinedClassifierTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRedefinedClassifierTable(EObject element) {
		return ((ReferencesTableSettings)redefinedClassifier.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#getRepresentation()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#initRepresentation(EObjectFlatComboSettings)
	 */
	public void initRepresentation(EObjectFlatComboSettings settings) {
		representation.setInput(settings);
		if (current != null) {
			representation.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.representation);
		if (readOnly && representation.isEnabled()) {
			representation.setEnabled(false);
			representation.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !representation.isEnabled()) {
			representation.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#setRepresentation(EObject newValue)
	 * @generated
	 */
	public void setRepresentation(EObject newValue) {
		if (newValue != null) {
			representation.setSelection(new StructuredSelection(newValue));
		} else {
			representation.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.representation);
		if (readOnly && representation.isEnabled()) {
			representation.setEnabled(false);
			representation.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !representation.isEnabled()) {
			representation.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#setRepresentationButtonMode(ButtonsModeEnum newValue)
	 */
	public void setRepresentationButtonMode(ButtonsModeEnum newValue) {
		representation.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addFilterRepresentation(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRepresentation(ViewerFilter filter) {
		representation.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addBusinessFilterRepresentation(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRepresentation(ViewerFilter filter) {
		representation.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#initUseCase(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initUseCase(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		useCase.setContentProvider(contentProvider);
		useCase.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.useCase);
		if (readOnly && useCase.getTable().isEnabled()) {
			useCase.setEnabled(false);
			useCase.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !useCase.getTable().isEnabled()) {
			useCase.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#updateUseCase()
	 * @generated
	 */
	public void updateUseCase() {
	useCase.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addFilterUseCase(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToUseCase(ViewerFilter filter) {
		useCaseFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addBusinessFilterUseCase(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToUseCase(ViewerFilter filter) {
		useCaseBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#isContainedInUseCaseTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInUseCaseTable(EObject element) {
		return ((ReferencesTableSettings)useCase.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#getClassifierBehavior()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#initClassifierBehavior(EObjectFlatComboSettings)
	 */
	public void initClassifierBehavior(EObjectFlatComboSettings settings) {
		classifierBehavior.setInput(settings);
		if (current != null) {
			classifierBehavior.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.classifierBehavior);
		if (readOnly && classifierBehavior.isEnabled()) {
			classifierBehavior.setEnabled(false);
			classifierBehavior.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !classifierBehavior.isEnabled()) {
			classifierBehavior.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#setClassifierBehavior(EObject newValue)
	 * @generated
	 */
	public void setClassifierBehavior(EObject newValue) {
		if (newValue != null) {
			classifierBehavior.setSelection(new StructuredSelection(newValue));
		} else {
			classifierBehavior.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.classifierBehavior);
		if (readOnly && classifierBehavior.isEnabled()) {
			classifierBehavior.setEnabled(false);
			classifierBehavior.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !classifierBehavior.isEnabled()) {
			classifierBehavior.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#setClassifierBehaviorButtonMode(ButtonsModeEnum newValue)
	 */
	public void setClassifierBehaviorButtonMode(ButtonsModeEnum newValue) {
		classifierBehavior.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addFilterClassifierBehavior(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClassifierBehavior(ViewerFilter filter) {
		classifierBehavior.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#addBusinessFilterClassifierBehavior(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClassifierBehavior(ViewerFilter filter) {
		classifierBehavior.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#getIsActive()
	 * @generated
	 */
	public Boolean getIsActive() {
		return Boolean.valueOf(isActive.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.NodePropertiesEditionPart#setIsActive(Boolean newValue)
	 * @generated
	 */
	public void setIsActive(Boolean newValue) {
		if (newValue != null) {
			isActive.setSelection(newValue.booleanValue());
		} else {
			isActive.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Node.Properties.isActive);
		if (readOnly && isActive.isEnabled()) {
			isActive.setEnabled(false);
			isActive.setToolTipText(UmlMessages.Node_ReadOnly);
		} else if (!readOnly && !isActive.isEnabled()) {
			isActive.setEnabled(true);
		}	
		
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.Node_Part_Title;
	}



}
