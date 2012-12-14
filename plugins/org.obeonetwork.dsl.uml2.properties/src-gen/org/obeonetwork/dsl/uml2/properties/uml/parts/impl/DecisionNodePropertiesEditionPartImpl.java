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

import org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class DecisionNodePropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, DecisionNodePropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected Button isLeaf;
	protected EObjectFlatComboViewer inStructuredNode;
	protected EObjectFlatComboViewer activity;
	protected ReferencesTable outgoing;
	protected List<ViewerFilter> outgoingBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> outgoingFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable incoming;
	protected List<ViewerFilter> incomingBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> incomingFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable inPartition;
	protected List<ViewerFilter> inPartitionBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> inPartitionFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable inInterruptibleRegion;
	protected List<ViewerFilter> inInterruptibleRegionBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> inInterruptibleRegionFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable redefinedNode;
	protected List<ViewerFilter> redefinedNodeBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> redefinedNodeFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer decisionInput;
	protected EObjectFlatComboViewer decisionInputFlow;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public DecisionNodePropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence decisionNodeStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = decisionNodeStep.addStep(UmlViewsRepository.DecisionNode.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.DecisionNode.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.DecisionNode.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.DecisionNode.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.DecisionNode.Properties.isLeaf);
		propertiesStep.addStep(UmlViewsRepository.DecisionNode.Properties.inStructuredNode);
		propertiesStep.addStep(UmlViewsRepository.DecisionNode.Properties.activity);
		propertiesStep.addStep(UmlViewsRepository.DecisionNode.Properties.outgoing);
		propertiesStep.addStep(UmlViewsRepository.DecisionNode.Properties.incoming);
		propertiesStep.addStep(UmlViewsRepository.DecisionNode.Properties.inPartition);
		propertiesStep.addStep(UmlViewsRepository.DecisionNode.Properties.inInterruptibleRegion);
		propertiesStep.addStep(UmlViewsRepository.DecisionNode.Properties.redefinedNode);
		propertiesStep.addStep(UmlViewsRepository.DecisionNode.Properties.decisionInput);
		propertiesStep.addStep(UmlViewsRepository.DecisionNode.Properties.decisionInputFlow);
		
		
		composer = new PartComposer(decisionNodeStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.DecisionNode.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.DecisionNode.Properties.name) {
					return createNameText(parent);
				}
				if (key == UmlViewsRepository.DecisionNode.Properties.visibility) {
					return createVisibilityEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.DecisionNode.Properties.clientDependency) {
					return createClientDependencyAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.DecisionNode.Properties.isLeaf) {
					return createIsLeafCheckbox(parent);
				}
				if (key == UmlViewsRepository.DecisionNode.Properties.inStructuredNode) {
					return createInStructuredNodeFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.DecisionNode.Properties.activity) {
					return createActivityFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.DecisionNode.Properties.outgoing) {
					return createOutgoingAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.DecisionNode.Properties.incoming) {
					return createIncomingAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.DecisionNode.Properties.inPartition) {
					return createInPartitionAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.DecisionNode.Properties.inInterruptibleRegion) {
					return createInInterruptibleRegionAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.DecisionNode.Properties.redefinedNode) {
					return createRedefinedNodeAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.DecisionNode.Properties.decisionInput) {
					return createDecisionInputFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.DecisionNode.Properties.decisionInputFlow) {
					return createDecisionInputFlowFlatComboViewer(parent);
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
		propertiesGroup.setText(UmlMessages.DecisionNodePropertiesEditionPart_PropertiesGroupLabel);
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
		createDescription(parent, UmlViewsRepository.DecisionNode.Properties.name, UmlMessages.DecisionNodePropertiesEditionPart_NameLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, UmlViewsRepository.DecisionNode.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.DecisionNode.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.DecisionNode.Properties.visibility, UmlMessages.DecisionNodePropertiesEditionPart_VisibilityLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.DecisionNode.Properties.visibility);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.DecisionNode.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.DecisionNode.Properties.clientDependency, UmlMessages.DecisionNodePropertiesEditionPart_ClientDependencyLabel);		 
		this.clientDependency = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.DecisionNode.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
		this.clientDependency.createControls(parent);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.DecisionNode.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		isLeaf.setText(getDescription(UmlViewsRepository.DecisionNode.Properties.isLeaf, UmlMessages.DecisionNodePropertiesEditionPart_IsLeafLabel));
		isLeaf.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
			}

		});
		GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
		isLeafData.horizontalSpan = 2;
		isLeaf.setLayoutData(isLeafData);
		EditingUtils.setID(isLeaf, UmlViewsRepository.DecisionNode.Properties.isLeaf);
		EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.DecisionNode.Properties.isLeaf, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createInStructuredNodeFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.DecisionNode.Properties.inStructuredNode, UmlMessages.DecisionNodePropertiesEditionPart_InStructuredNodeLabel);
		inStructuredNode = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.DecisionNode.Properties.inStructuredNode, UmlViewsRepository.SWT_KIND));
		inStructuredNode.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		inStructuredNode.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.inStructuredNode, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getInStructuredNode()));
			}

		});
		GridData inStructuredNodeData = new GridData(GridData.FILL_HORIZONTAL);
		inStructuredNode.setLayoutData(inStructuredNodeData);
		inStructuredNode.setID(UmlViewsRepository.DecisionNode.Properties.inStructuredNode);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.DecisionNode.Properties.inStructuredNode, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createActivityFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.DecisionNode.Properties.activity, UmlMessages.DecisionNodePropertiesEditionPart_ActivityLabel);
		activity = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.DecisionNode.Properties.activity, UmlViewsRepository.SWT_KIND));
		activity.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		activity.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.activity, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getActivity()));
			}

		});
		GridData activityData = new GridData(GridData.FILL_HORIZONTAL);
		activity.setLayoutData(activityData);
		activity.setID(UmlViewsRepository.DecisionNode.Properties.activity);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.DecisionNode.Properties.activity, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createOutgoingAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.DecisionNode.Properties.outgoing, UmlMessages.DecisionNodePropertiesEditionPart_OutgoingLabel);		 
		this.outgoing = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addOutgoing(); }
			public void handleEdit(EObject element) { editOutgoing(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveOutgoing(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromOutgoing(element); }
			public void navigateTo(EObject element) { }
		});
		this.outgoing.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.DecisionNode.Properties.outgoing, UmlViewsRepository.SWT_KIND));
		this.outgoing.createControls(parent);
		this.outgoing.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.outgoing, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData outgoingData = new GridData(GridData.FILL_HORIZONTAL);
		outgoingData.horizontalSpan = 3;
		this.outgoing.setLayoutData(outgoingData);
		this.outgoing.disableMove();
		outgoing.setID(UmlViewsRepository.DecisionNode.Properties.outgoing);
		outgoing.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addOutgoing() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(outgoing.getInput(), outgoingFilters, outgoingBusinessFilters,
		"outgoing", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.outgoing,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				outgoing.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveOutgoing(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.outgoing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		outgoing.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromOutgoing(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.outgoing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		outgoing.refresh();
	}

	/**
	 * @generated
	 */
	protected void editOutgoing(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				outgoing.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	protected Composite createIncomingAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.DecisionNode.Properties.incoming, UmlMessages.DecisionNodePropertiesEditionPart_IncomingLabel);		 
		this.incoming = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addIncoming(); }
			public void handleEdit(EObject element) { editIncoming(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveIncoming(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromIncoming(element); }
			public void navigateTo(EObject element) { }
		});
		this.incoming.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.DecisionNode.Properties.incoming, UmlViewsRepository.SWT_KIND));
		this.incoming.createControls(parent);
		this.incoming.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.incoming, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData incomingData = new GridData(GridData.FILL_HORIZONTAL);
		incomingData.horizontalSpan = 3;
		this.incoming.setLayoutData(incomingData);
		this.incoming.disableMove();
		incoming.setID(UmlViewsRepository.DecisionNode.Properties.incoming);
		incoming.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addIncoming() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(incoming.getInput(), incomingFilters, incomingBusinessFilters,
		"incoming", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.incoming,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				incoming.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveIncoming(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.incoming, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		incoming.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromIncoming(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.incoming, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		incoming.refresh();
	}

	/**
	 * @generated
	 */
	protected void editIncoming(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				incoming.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	protected Composite createInPartitionAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.DecisionNode.Properties.inPartition, UmlMessages.DecisionNodePropertiesEditionPart_InPartitionLabel);		 
		this.inPartition = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addInPartition(); }
			public void handleEdit(EObject element) { editInPartition(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveInPartition(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromInPartition(element); }
			public void navigateTo(EObject element) { }
		});
		this.inPartition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.DecisionNode.Properties.inPartition, UmlViewsRepository.SWT_KIND));
		this.inPartition.createControls(parent);
		this.inPartition.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.inPartition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData inPartitionData = new GridData(GridData.FILL_HORIZONTAL);
		inPartitionData.horizontalSpan = 3;
		this.inPartition.setLayoutData(inPartitionData);
		this.inPartition.disableMove();
		inPartition.setID(UmlViewsRepository.DecisionNode.Properties.inPartition);
		inPartition.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addInPartition() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(inPartition.getInput(), inPartitionFilters, inPartitionBusinessFilters,
		"inPartition", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.inPartition,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				inPartition.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveInPartition(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		inPartition.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromInPartition(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		inPartition.refresh();
	}

	/**
	 * @generated
	 */
	protected void editInPartition(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				inPartition.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	protected Composite createInInterruptibleRegionAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.DecisionNode.Properties.inInterruptibleRegion, UmlMessages.DecisionNodePropertiesEditionPart_InInterruptibleRegionLabel);		 
		this.inInterruptibleRegion = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addInInterruptibleRegion(); }
			public void handleEdit(EObject element) { editInInterruptibleRegion(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveInInterruptibleRegion(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromInInterruptibleRegion(element); }
			public void navigateTo(EObject element) { }
		});
		this.inInterruptibleRegion.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.DecisionNode.Properties.inInterruptibleRegion, UmlViewsRepository.SWT_KIND));
		this.inInterruptibleRegion.createControls(parent);
		this.inInterruptibleRegion.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.inInterruptibleRegion, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData inInterruptibleRegionData = new GridData(GridData.FILL_HORIZONTAL);
		inInterruptibleRegionData.horizontalSpan = 3;
		this.inInterruptibleRegion.setLayoutData(inInterruptibleRegionData);
		this.inInterruptibleRegion.disableMove();
		inInterruptibleRegion.setID(UmlViewsRepository.DecisionNode.Properties.inInterruptibleRegion);
		inInterruptibleRegion.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addInInterruptibleRegion() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(inInterruptibleRegion.getInput(), inInterruptibleRegionFilters, inInterruptibleRegionBusinessFilters,
		"inInterruptibleRegion", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.inInterruptibleRegion,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				inInterruptibleRegion.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveInInterruptibleRegion(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.inInterruptibleRegion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		inInterruptibleRegion.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromInInterruptibleRegion(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.inInterruptibleRegion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		inInterruptibleRegion.refresh();
	}

	/**
	 * @generated
	 */
	protected void editInInterruptibleRegion(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				inInterruptibleRegion.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	protected Composite createRedefinedNodeAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.DecisionNode.Properties.redefinedNode, UmlMessages.DecisionNodePropertiesEditionPart_RedefinedNodeLabel);		 
		this.redefinedNode = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addRedefinedNode(); }
			public void handleEdit(EObject element) { editRedefinedNode(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedNode(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRedefinedNode(element); }
			public void navigateTo(EObject element) { }
		});
		this.redefinedNode.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.DecisionNode.Properties.redefinedNode, UmlViewsRepository.SWT_KIND));
		this.redefinedNode.createControls(parent);
		this.redefinedNode.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.redefinedNode, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData redefinedNodeData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedNodeData.horizontalSpan = 3;
		this.redefinedNode.setLayoutData(redefinedNodeData);
		this.redefinedNode.disableMove();
		redefinedNode.setID(UmlViewsRepository.DecisionNode.Properties.redefinedNode);
		redefinedNode.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addRedefinedNode() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(redefinedNode.getInput(), redefinedNodeFilters, redefinedNodeBusinessFilters,
		"redefinedNode", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.redefinedNode,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				redefinedNode.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveRedefinedNode(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.redefinedNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		redefinedNode.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromRedefinedNode(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.redefinedNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		redefinedNode.refresh();
	}

	/**
	 * @generated
	 */
	protected void editRedefinedNode(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				redefinedNode.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createDecisionInputFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.DecisionNode.Properties.decisionInput, UmlMessages.DecisionNodePropertiesEditionPart_DecisionInputLabel);
		decisionInput = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.DecisionNode.Properties.decisionInput, UmlViewsRepository.SWT_KIND));
		decisionInput.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		decisionInput.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.decisionInput, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getDecisionInput()));
			}

		});
		GridData decisionInputData = new GridData(GridData.FILL_HORIZONTAL);
		decisionInput.setLayoutData(decisionInputData);
		decisionInput.setID(UmlViewsRepository.DecisionNode.Properties.decisionInput);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.DecisionNode.Properties.decisionInput, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createDecisionInputFlowFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.DecisionNode.Properties.decisionInputFlow, UmlMessages.DecisionNodePropertiesEditionPart_DecisionInputFlowLabel);
		decisionInputFlow = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.DecisionNode.Properties.decisionInputFlow, UmlViewsRepository.SWT_KIND));
		decisionInputFlow.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		decisionInputFlow.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DecisionNodePropertiesEditionPartImpl.this, UmlViewsRepository.DecisionNode.Properties.decisionInputFlow, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getDecisionInputFlow()));
			}

		});
		GridData decisionInputFlowData = new GridData(GridData.FILL_HORIZONTAL);
		decisionInputFlow.setLayoutData(decisionInputFlowData);
		decisionInputFlow.setID(UmlViewsRepository.DecisionNode.Properties.decisionInputFlow);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.DecisionNode.Properties.decisionInputFlow, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.name);
		if (readOnly && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
		Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
		return Boolean.valueOf(isLeaf.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
		if (newValue != null) {
			isLeaf.setSelection(newValue.booleanValue());
		} else {
			isLeaf.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.isLeaf);
		if (readOnly && isLeaf.isEnabled()) {
			isLeaf.setEnabled(false);
			isLeaf.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !isLeaf.isEnabled()) {
			isLeaf.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#getInStructuredNode()
	 * @generated
	 */
	public EObject getInStructuredNode() {
		if (inStructuredNode.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) inStructuredNode.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#initInStructuredNode(EObjectFlatComboSettings)
	 */
	public void initInStructuredNode(EObjectFlatComboSettings settings) {
		inStructuredNode.setInput(settings);
		if (current != null) {
			inStructuredNode.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.inStructuredNode);
		if (readOnly && inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(false);
			inStructuredNode.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#setInStructuredNode(EObject newValue)
	 * @generated
	 */
	public void setInStructuredNode(EObject newValue) {
		if (newValue != null) {
			inStructuredNode.setSelection(new StructuredSelection(newValue));
		} else {
			inStructuredNode.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.inStructuredNode);
		if (readOnly && inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(false);
			inStructuredNode.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#setInStructuredNodeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInStructuredNodeButtonMode(ButtonsModeEnum newValue) {
		inStructuredNode.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addFilterInStructuredNode(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInStructuredNode(ViewerFilter filter) {
		inStructuredNode.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addBusinessFilterInStructuredNode(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInStructuredNode(ViewerFilter filter) {
		inStructuredNode.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#getActivity()
	 * @generated
	 */
	public EObject getActivity() {
		if (activity.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) activity.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#initActivity(EObjectFlatComboSettings)
	 */
	public void initActivity(EObjectFlatComboSettings settings) {
		activity.setInput(settings);
		if (current != null) {
			activity.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.activity);
		if (readOnly && activity.isEnabled()) {
			activity.setEnabled(false);
			activity.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !activity.isEnabled()) {
			activity.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#setActivity(EObject newValue)
	 * @generated
	 */
	public void setActivity(EObject newValue) {
		if (newValue != null) {
			activity.setSelection(new StructuredSelection(newValue));
		} else {
			activity.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.activity);
		if (readOnly && activity.isEnabled()) {
			activity.setEnabled(false);
			activity.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !activity.isEnabled()) {
			activity.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#setActivityButtonMode(ButtonsModeEnum newValue)
	 */
	public void setActivityButtonMode(ButtonsModeEnum newValue) {
		activity.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addFilterActivity(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToActivity(ViewerFilter filter) {
		activity.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addBusinessFilterActivity(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToActivity(ViewerFilter filter) {
		activity.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#initOutgoing(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initOutgoing(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		outgoing.setContentProvider(contentProvider);
		outgoing.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.outgoing);
		if (readOnly && outgoing.getTable().isEnabled()) {
			outgoing.setEnabled(false);
			outgoing.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !outgoing.getTable().isEnabled()) {
			outgoing.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#updateOutgoing()
	 * @generated
	 */
	public void updateOutgoing() {
	outgoing.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addFilterOutgoing(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOutgoing(ViewerFilter filter) {
		outgoingFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addBusinessFilterOutgoing(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOutgoing(ViewerFilter filter) {
		outgoingBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#isContainedInOutgoingTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInOutgoingTable(EObject element) {
		return ((ReferencesTableSettings)outgoing.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#initIncoming(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initIncoming(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		incoming.setContentProvider(contentProvider);
		incoming.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.incoming);
		if (readOnly && incoming.getTable().isEnabled()) {
			incoming.setEnabled(false);
			incoming.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !incoming.getTable().isEnabled()) {
			incoming.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#updateIncoming()
	 * @generated
	 */
	public void updateIncoming() {
	incoming.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addFilterIncoming(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToIncoming(ViewerFilter filter) {
		incomingFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addBusinessFilterIncoming(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToIncoming(ViewerFilter filter) {
		incomingBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#isContainedInIncomingTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInIncomingTable(EObject element) {
		return ((ReferencesTableSettings)incoming.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#initInPartition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInPartition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		inPartition.setContentProvider(contentProvider);
		inPartition.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.inPartition);
		if (readOnly && inPartition.getTable().isEnabled()) {
			inPartition.setEnabled(false);
			inPartition.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !inPartition.getTable().isEnabled()) {
			inPartition.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#updateInPartition()
	 * @generated
	 */
	public void updateInPartition() {
	inPartition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addFilterInPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInPartition(ViewerFilter filter) {
		inPartitionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addBusinessFilterInPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInPartition(ViewerFilter filter) {
		inPartitionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#isContainedInInPartitionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInInPartitionTable(EObject element) {
		return ((ReferencesTableSettings)inPartition.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#initInInterruptibleRegion(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInInterruptibleRegion(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		inInterruptibleRegion.setContentProvider(contentProvider);
		inInterruptibleRegion.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.inInterruptibleRegion);
		if (readOnly && inInterruptibleRegion.getTable().isEnabled()) {
			inInterruptibleRegion.setEnabled(false);
			inInterruptibleRegion.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !inInterruptibleRegion.getTable().isEnabled()) {
			inInterruptibleRegion.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#updateInInterruptibleRegion()
	 * @generated
	 */
	public void updateInInterruptibleRegion() {
	inInterruptibleRegion.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addFilterInInterruptibleRegion(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInInterruptibleRegion(ViewerFilter filter) {
		inInterruptibleRegionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addBusinessFilterInInterruptibleRegion(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInInterruptibleRegion(ViewerFilter filter) {
		inInterruptibleRegionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#isContainedInInInterruptibleRegionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInInInterruptibleRegionTable(EObject element) {
		return ((ReferencesTableSettings)inInterruptibleRegion.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#initRedefinedNode(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedNode(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedNode.setContentProvider(contentProvider);
		redefinedNode.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.redefinedNode);
		if (readOnly && redefinedNode.getTable().isEnabled()) {
			redefinedNode.setEnabled(false);
			redefinedNode.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !redefinedNode.getTable().isEnabled()) {
			redefinedNode.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#updateRedefinedNode()
	 * @generated
	 */
	public void updateRedefinedNode() {
	redefinedNode.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addFilterRedefinedNode(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedNode(ViewerFilter filter) {
		redefinedNodeFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addBusinessFilterRedefinedNode(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedNode(ViewerFilter filter) {
		redefinedNodeBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#isContainedInRedefinedNodeTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRedefinedNodeTable(EObject element) {
		return ((ReferencesTableSettings)redefinedNode.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#getDecisionInput()
	 * @generated
	 */
	public EObject getDecisionInput() {
		if (decisionInput.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) decisionInput.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#initDecisionInput(EObjectFlatComboSettings)
	 */
	public void initDecisionInput(EObjectFlatComboSettings settings) {
		decisionInput.setInput(settings);
		if (current != null) {
			decisionInput.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.decisionInput);
		if (readOnly && decisionInput.isEnabled()) {
			decisionInput.setEnabled(false);
			decisionInput.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !decisionInput.isEnabled()) {
			decisionInput.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#setDecisionInput(EObject newValue)
	 * @generated
	 */
	public void setDecisionInput(EObject newValue) {
		if (newValue != null) {
			decisionInput.setSelection(new StructuredSelection(newValue));
		} else {
			decisionInput.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.decisionInput);
		if (readOnly && decisionInput.isEnabled()) {
			decisionInput.setEnabled(false);
			decisionInput.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !decisionInput.isEnabled()) {
			decisionInput.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#setDecisionInputButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDecisionInputButtonMode(ButtonsModeEnum newValue) {
		decisionInput.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addFilterDecisionInput(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToDecisionInput(ViewerFilter filter) {
		decisionInput.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addBusinessFilterDecisionInput(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToDecisionInput(ViewerFilter filter) {
		decisionInput.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#getDecisionInputFlow()
	 * @generated
	 */
	public EObject getDecisionInputFlow() {
		if (decisionInputFlow.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) decisionInputFlow.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#initDecisionInputFlow(EObjectFlatComboSettings)
	 */
	public void initDecisionInputFlow(EObjectFlatComboSettings settings) {
		decisionInputFlow.setInput(settings);
		if (current != null) {
			decisionInputFlow.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.decisionInputFlow);
		if (readOnly && decisionInputFlow.isEnabled()) {
			decisionInputFlow.setEnabled(false);
			decisionInputFlow.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !decisionInputFlow.isEnabled()) {
			decisionInputFlow.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#setDecisionInputFlow(EObject newValue)
	 * @generated
	 */
	public void setDecisionInputFlow(EObject newValue) {
		if (newValue != null) {
			decisionInputFlow.setSelection(new StructuredSelection(newValue));
		} else {
			decisionInputFlow.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.DecisionNode.Properties.decisionInputFlow);
		if (readOnly && decisionInputFlow.isEnabled()) {
			decisionInputFlow.setEnabled(false);
			decisionInputFlow.setToolTipText(UmlMessages.DecisionNode_ReadOnly);
		} else if (!readOnly && !decisionInputFlow.isEnabled()) {
			decisionInputFlow.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#setDecisionInputFlowButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDecisionInputFlowButtonMode(ButtonsModeEnum newValue) {
		decisionInputFlow.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addFilterDecisionInputFlow(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToDecisionInputFlow(ViewerFilter filter) {
		decisionInputFlow.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DecisionNodePropertiesEditionPart#addBusinessFilterDecisionInputFlow(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToDecisionInputFlow(ViewerFilter filter) {
		decisionInputFlow.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.DecisionNode_Part_Title;
	}



}
