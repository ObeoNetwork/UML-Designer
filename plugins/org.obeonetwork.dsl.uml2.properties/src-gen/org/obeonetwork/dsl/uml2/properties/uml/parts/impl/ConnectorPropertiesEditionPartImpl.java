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

import org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ConnectorPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ConnectorPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected Button isLeaf;
	protected Button isStatic;
	protected EObjectFlatComboViewer type;
	protected ReferencesTable redefinedConnector;
	protected List<ViewerFilter> redefinedConnectorBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> redefinedConnectorFilters = new ArrayList<ViewerFilter>();
	protected EMFComboViewer kind;
	protected ReferencesTable contract;
	protected List<ViewerFilter> contractBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> contractFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ConnectorPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence connectorStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = connectorStep.addStep(UmlViewsRepository.Connector.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.Connector.Properties.name);
    propertiesStep.addStep(UmlViewsRepository.Connector.Properties.visibility);
    propertiesStep.addStep(UmlViewsRepository.Connector.Properties.clientDependency);
    propertiesStep.addStep(UmlViewsRepository.Connector.Properties.isLeaf);
    propertiesStep.addStep(UmlViewsRepository.Connector.Properties.isStatic);
    propertiesStep.addStep(UmlViewsRepository.Connector.Properties.type);
    propertiesStep.addStep(UmlViewsRepository.Connector.Properties.redefinedConnector);
    propertiesStep.addStep(UmlViewsRepository.Connector.Properties.kind);
    propertiesStep.addStep(UmlViewsRepository.Connector.Properties.contract);
    
    
    composer = new PartComposer(connectorStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.Connector.Properties.class) {
          return createPropertiesGroup(parent);
        }
        if (key == UmlViewsRepository.Connector.Properties.name) {
          return createNameText(parent);
        }
        if (key == UmlViewsRepository.Connector.Properties.visibility) {
          return createVisibilityEMFComboViewer(parent);
        }
        if (key == UmlViewsRepository.Connector.Properties.clientDependency) {
          return createClientDependencyAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.Connector.Properties.isLeaf) {
          return createIsLeafCheckbox(parent);
        }
        if (key == UmlViewsRepository.Connector.Properties.isStatic) {
          return createIsStaticCheckbox(parent);
        }
        if (key == UmlViewsRepository.Connector.Properties.type) {
          return createTypeFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.Connector.Properties.redefinedConnector) {
          return createRedefinedConnectorAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.Connector.Properties.kind) {
          return createKindEMFComboViewer(parent);
        }
        if (key == UmlViewsRepository.Connector.Properties.contract) {
          return createContractAdvancedReferencesTable(parent);
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
    propertiesGroup.setText(UmlMessages.ConnectorPropertiesEditionPart_PropertiesGroupLabel);
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
    createDescription(parent, UmlViewsRepository.Connector.Properties.name, UmlMessages.ConnectorPropertiesEditionPart_NameLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
        }
      }

    });
    EditingUtils.setID(name, UmlViewsRepository.Connector.Properties.name);
    EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Connector.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.Connector.Properties.visibility, UmlMessages.ConnectorPropertiesEditionPart_VisibilityLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
      }

    });
    visibility.setID(UmlViewsRepository.Connector.Properties.visibility);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Connector.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.Connector.Properties.clientDependency, UmlMessages.ConnectorPropertiesEditionPart_ClientDependencyLabel);		 
    this.clientDependency = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addClientDependency(); }
      public void handleEdit(EObject element) { editClientDependency(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromClientDependency(element); }
      public void navigateTo(EObject element) { }
    });
    this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Connector.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
    this.clientDependency.createControls(parent);
    this.clientDependency.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
    clientDependencyData.horizontalSpan = 3;
    this.clientDependency.setLayoutData(clientDependencyData);
    this.clientDependency.disableMove();
    clientDependency.setID(UmlViewsRepository.Connector.Properties.clientDependency);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.clientDependency,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    clientDependency.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    isLeaf.setText(getDescription(UmlViewsRepository.Connector.Properties.isLeaf, UmlMessages.ConnectorPropertiesEditionPart_IsLeafLabel));
    isLeaf.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
      }

    });
    GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
    isLeafData.horizontalSpan = 2;
    isLeaf.setLayoutData(isLeafData);
    EditingUtils.setID(isLeaf, UmlViewsRepository.Connector.Properties.isLeaf);
    EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Connector.Properties.isLeaf, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createIsStaticCheckbox(Composite parent) {
    isStatic = new Button(parent, SWT.CHECK);
    isStatic.setText(getDescription(UmlViewsRepository.Connector.Properties.isStatic, UmlMessages.ConnectorPropertiesEditionPart_IsStaticLabel));
    isStatic.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.isStatic, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isStatic.getSelection())));
      }

    });
    GridData isStaticData = new GridData(GridData.FILL_HORIZONTAL);
    isStaticData.horizontalSpan = 2;
    isStatic.setLayoutData(isStaticData);
    EditingUtils.setID(isStatic, UmlViewsRepository.Connector.Properties.isStatic);
    EditingUtils.setEEFtype(isStatic, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Connector.Properties.isStatic, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createTypeFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.Connector.Properties.type, UmlMessages.ConnectorPropertiesEditionPart_TypeLabel);
    type = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Connector.Properties.type, UmlViewsRepository.SWT_KIND));
    type.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    type.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.type, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getType()));
      }

    });
    GridData typeData = new GridData(GridData.FILL_HORIZONTAL);
    type.setLayoutData(typeData);
    type.setID(UmlViewsRepository.Connector.Properties.type);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Connector.Properties.type, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createRedefinedConnectorAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.Connector.Properties.redefinedConnector, UmlMessages.ConnectorPropertiesEditionPart_RedefinedConnectorLabel);		 
    this.redefinedConnector = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addRedefinedConnector(); }
      public void handleEdit(EObject element) { editRedefinedConnector(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedConnector(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromRedefinedConnector(element); }
      public void navigateTo(EObject element) { }
    });
    this.redefinedConnector.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Connector.Properties.redefinedConnector, UmlViewsRepository.SWT_KIND));
    this.redefinedConnector.createControls(parent);
    this.redefinedConnector.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.redefinedConnector, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData redefinedConnectorData = new GridData(GridData.FILL_HORIZONTAL);
    redefinedConnectorData.horizontalSpan = 3;
    this.redefinedConnector.setLayoutData(redefinedConnectorData);
    this.redefinedConnector.disableMove();
    redefinedConnector.setID(UmlViewsRepository.Connector.Properties.redefinedConnector);
    redefinedConnector.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addRedefinedConnector() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(redefinedConnector.getInput(), redefinedConnectorFilters, redefinedConnectorBusinessFilters,
    "redefinedConnector", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.redefinedConnector,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        redefinedConnector.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveRedefinedConnector(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.redefinedConnector, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    redefinedConnector.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromRedefinedConnector(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.redefinedConnector, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    redefinedConnector.refresh();
  }

	/**
	 * @generated
	 */
	protected void editRedefinedConnector(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        redefinedConnector.refresh();
      }
    }
  }

	/**
	 * @generated
	 */
	
	protected Composite createKindEMFComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.Connector.Properties.kind, UmlMessages.ConnectorPropertiesEditionPart_KindLabel);
    kind = new EMFComboViewer(parent);
    kind.setContentProvider(new ArrayContentProvider());
    kind.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
    GridData kindData = new GridData(GridData.FILL_HORIZONTAL);
    kind.getCombo().setLayoutData(kindData);
    kind.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       * 	@generated
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.kind, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getKind()));
      }

    });
    kind.setID(UmlViewsRepository.Connector.Properties.kind);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Connector.Properties.kind, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createContractAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.Connector.Properties.contract, UmlMessages.ConnectorPropertiesEditionPart_ContractLabel);		 
    this.contract = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addContract(); }
      public void handleEdit(EObject element) { editContract(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveContract(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromContract(element); }
      public void navigateTo(EObject element) { }
    });
    this.contract.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Connector.Properties.contract, UmlViewsRepository.SWT_KIND));
    this.contract.createControls(parent);
    this.contract.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.contract, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData contractData = new GridData(GridData.FILL_HORIZONTAL);
    contractData.horizontalSpan = 3;
    this.contract.setLayoutData(contractData);
    this.contract.disableMove();
    contract.setID(UmlViewsRepository.Connector.Properties.contract);
    contract.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addContract() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(contract.getInput(), contractFilters, contractBusinessFilters,
    "contract", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.contract,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        contract.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveContract(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.contract, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    contract.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromContract(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorPropertiesEditionPartImpl.this, UmlViewsRepository.Connector.Properties.contract, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    contract.refresh();
  }

	/**
	 * @generated
	 */
	protected void editContract(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        contract.refresh();
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
    return name.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
    if (newValue != null) {
      name.setText(newValue);
    } else {
      name.setText(""); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Connector.Properties.name);
    if (readOnly && name.isEnabled()) {
      name.setEnabled(false);
      name.setToolTipText(UmlMessages.Connector_ReadOnly);
    } else if (!readOnly && !name.isEnabled()) {
      name.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
    Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Connector.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.Connector_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
    visibility.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.Connector.Properties.visibility);
    if (readOnly && visibility.isEnabled()) {
      visibility.setEnabled(false);
      visibility.setToolTipText(UmlMessages.Connector_ReadOnly);
    } else if (!readOnly && !visibility.isEnabled()) {
      visibility.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Connector.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.Connector_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
  clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
    clientDependencyFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
    clientDependencyBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
    return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
    return Boolean.valueOf(isLeaf.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
    if (newValue != null) {
      isLeaf.setSelection(newValue.booleanValue());
    } else {
      isLeaf.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Connector.Properties.isLeaf);
    if (readOnly && isLeaf.isEnabled()) {
      isLeaf.setEnabled(false);
      isLeaf.setToolTipText(UmlMessages.Connector_ReadOnly);
    } else if (!readOnly && !isLeaf.isEnabled()) {
      isLeaf.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#getIsStatic()
	 * @generated
	 */
	public Boolean getIsStatic() {
    return Boolean.valueOf(isStatic.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#setIsStatic(Boolean newValue)
	 * @generated
	 */
	public void setIsStatic(Boolean newValue) {
    if (newValue != null) {
      isStatic.setSelection(newValue.booleanValue());
    } else {
      isStatic.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Connector.Properties.isStatic);
    if (readOnly && isStatic.isEnabled()) {
      isStatic.setEnabled(false);
      isStatic.setToolTipText(UmlMessages.Connector_ReadOnly);
    } else if (!readOnly && !isStatic.isEnabled()) {
      isStatic.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#getType()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#initType(EObjectFlatComboSettings)
	 */
	public void initType(EObjectFlatComboSettings settings) {
		type.setInput(settings);
		if (current != null) {
			type.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Connector.Properties.type);
		if (readOnly && type.isEnabled()) {
			type.setEnabled(false);
			type.setToolTipText(UmlMessages.Connector_ReadOnly);
		} else if (!readOnly && !type.isEnabled()) {
			type.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#setType(EObject newValue)
	 * @generated
	 */
	public void setType(EObject newValue) {
    if (newValue != null) {
      type.setSelection(new StructuredSelection(newValue));
    } else {
      type.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Connector.Properties.type);
    if (readOnly && type.isEnabled()) {
      type.setEnabled(false);
      type.setToolTipText(UmlMessages.Connector_ReadOnly);
    } else if (!readOnly && !type.isEnabled()) {
      type.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#setTypeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTypeButtonMode(ButtonsModeEnum newValue) {
		type.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#addFilterType(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToType(ViewerFilter filter) {
    type.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#addBusinessFilterType(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToType(ViewerFilter filter) {
    type.addBusinessRuleFilter(filter);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#initRedefinedConnector(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedConnector(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedConnector.setContentProvider(contentProvider);
		redefinedConnector.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Connector.Properties.redefinedConnector);
		if (readOnly && redefinedConnector.getTable().isEnabled()) {
			redefinedConnector.setEnabled(false);
			redefinedConnector.setToolTipText(UmlMessages.Connector_ReadOnly);
		} else if (!readOnly && !redefinedConnector.getTable().isEnabled()) {
			redefinedConnector.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#updateRedefinedConnector()
	 * @generated
	 */
	public void updateRedefinedConnector() {
  redefinedConnector.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#addFilterRedefinedConnector(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedConnector(ViewerFilter filter) {
    redefinedConnectorFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#addBusinessFilterRedefinedConnector(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedConnector(ViewerFilter filter) {
    redefinedConnectorBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#isContainedInRedefinedConnectorTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRedefinedConnectorTable(EObject element) {
    return ((ReferencesTableSettings)redefinedConnector.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#getKind()
	 * @generated
	 */
	public Enumerator getKind() {
    Enumerator selection = (Enumerator) ((StructuredSelection) kind.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#initKind(Object input, Enumerator current)
	 */
	public void initKind(Object input, Enumerator current) {
		kind.setInput(input);
		kind.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Connector.Properties.kind);
		if (readOnly && kind.isEnabled()) {
			kind.setEnabled(false);
			kind.setToolTipText(UmlMessages.Connector_ReadOnly);
		} else if (!readOnly && !kind.isEnabled()) {
			kind.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#setKind(Enumerator newValue)
	 * @generated
	 */
	public void setKind(Enumerator newValue) {
    kind.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.Connector.Properties.kind);
    if (readOnly && kind.isEnabled()) {
      kind.setEnabled(false);
      kind.setToolTipText(UmlMessages.Connector_ReadOnly);
    } else if (!readOnly && !kind.isEnabled()) {
      kind.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#initContract(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initContract(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		contract.setContentProvider(contentProvider);
		contract.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Connector.Properties.contract);
		if (readOnly && contract.getTable().isEnabled()) {
			contract.setEnabled(false);
			contract.setToolTipText(UmlMessages.Connector_ReadOnly);
		} else if (!readOnly && !contract.getTable().isEnabled()) {
			contract.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#updateContract()
	 * @generated
	 */
	public void updateContract() {
  contract.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#addFilterContract(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToContract(ViewerFilter filter) {
    contractFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#addBusinessFilterContract(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToContract(ViewerFilter filter) {
    contractBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart#isContainedInContractTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInContractTable(EObject element) {
    return ((ReferencesTableSettings)contract.getInput()).contains(element);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.Connector_Part_Title;
  }



}
