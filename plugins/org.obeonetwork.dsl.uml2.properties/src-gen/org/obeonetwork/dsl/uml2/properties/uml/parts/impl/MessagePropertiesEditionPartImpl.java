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

import org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class MessagePropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, MessagePropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected EMFComboViewer messageSort;
	protected EObjectFlatComboViewer receiveEvent;
	protected EObjectFlatComboViewer sendEvent;
	protected EObjectFlatComboViewer connector;
	protected EObjectFlatComboViewer interaction;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public MessagePropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence messageStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = messageStep.addStep(UmlViewsRepository.Message.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.Message.Properties.name);
    propertiesStep.addStep(UmlViewsRepository.Message.Properties.visibility);
    propertiesStep.addStep(UmlViewsRepository.Message.Properties.clientDependency);
    propertiesStep.addStep(UmlViewsRepository.Message.Properties.messageSort);
    propertiesStep.addStep(UmlViewsRepository.Message.Properties.receiveEvent);
    propertiesStep.addStep(UmlViewsRepository.Message.Properties.sendEvent);
    propertiesStep.addStep(UmlViewsRepository.Message.Properties.connector);
    propertiesStep.addStep(UmlViewsRepository.Message.Properties.interaction);
    
    
    composer = new PartComposer(messageStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.Message.Properties.class) {
          return createPropertiesGroup(parent);
        }
        if (key == UmlViewsRepository.Message.Properties.name) {
          return createNameText(parent);
        }
        if (key == UmlViewsRepository.Message.Properties.visibility) {
          return createVisibilityEMFComboViewer(parent);
        }
        if (key == UmlViewsRepository.Message.Properties.clientDependency) {
          return createClientDependencyAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.Message.Properties.messageSort) {
          return createMessageSortEMFComboViewer(parent);
        }
        if (key == UmlViewsRepository.Message.Properties.receiveEvent) {
          return createReceiveEventFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.Message.Properties.sendEvent) {
          return createSendEventFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.Message.Properties.connector) {
          return createConnectorFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.Message.Properties.interaction) {
          return createInteractionFlatComboViewer(parent);
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
    propertiesGroup.setText(UmlMessages.MessagePropertiesEditionPart_PropertiesGroupLabel);
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
    createDescription(parent, UmlViewsRepository.Message.Properties.name, UmlMessages.MessagePropertiesEditionPart_NameLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartImpl.this, UmlViewsRepository.Message.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartImpl.this, UmlViewsRepository.Message.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
        }
      }

    });
    EditingUtils.setID(name, UmlViewsRepository.Message.Properties.name);
    EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.Message.Properties.visibility, UmlMessages.MessagePropertiesEditionPart_VisibilityLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartImpl.this, UmlViewsRepository.Message.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
      }

    });
    visibility.setID(UmlViewsRepository.Message.Properties.visibility);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.Message.Properties.clientDependency, UmlMessages.MessagePropertiesEditionPart_ClientDependencyLabel);		 
    this.clientDependency = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addClientDependency(); }
      public void handleEdit(EObject element) { editClientDependency(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromClientDependency(element); }
      public void navigateTo(EObject element) { }
    });
    this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
    this.clientDependency.createControls(parent);
    this.clientDependency.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartImpl.this, UmlViewsRepository.Message.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
    clientDependencyData.horizontalSpan = 3;
    this.clientDependency.setLayoutData(clientDependencyData);
    this.clientDependency.disableMove();
    clientDependency.setID(UmlViewsRepository.Message.Properties.clientDependency);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartImpl.this, UmlViewsRepository.Message.Properties.clientDependency,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartImpl.this, UmlViewsRepository.Message.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    clientDependency.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartImpl.this, UmlViewsRepository.Message.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	
	protected Composite createMessageSortEMFComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.Message.Properties.messageSort, UmlMessages.MessagePropertiesEditionPart_MessageSortLabel);
    messageSort = new EMFComboViewer(parent);
    messageSort.setContentProvider(new ArrayContentProvider());
    messageSort.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
    GridData messageSortData = new GridData(GridData.FILL_HORIZONTAL);
    messageSort.getCombo().setLayoutData(messageSortData);
    messageSort.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       * 	@generated
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartImpl.this, UmlViewsRepository.Message.Properties.messageSort, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getMessageSort()));
      }

    });
    messageSort.setID(UmlViewsRepository.Message.Properties.messageSort);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.messageSort, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createReceiveEventFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.Message.Properties.receiveEvent, UmlMessages.MessagePropertiesEditionPart_ReceiveEventLabel);
    receiveEvent = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.receiveEvent, UmlViewsRepository.SWT_KIND));
    receiveEvent.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    receiveEvent.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartImpl.this, UmlViewsRepository.Message.Properties.receiveEvent, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getReceiveEvent()));
      }

    });
    GridData receiveEventData = new GridData(GridData.FILL_HORIZONTAL);
    receiveEvent.setLayoutData(receiveEventData);
    receiveEvent.setID(UmlViewsRepository.Message.Properties.receiveEvent);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.receiveEvent, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createSendEventFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.Message.Properties.sendEvent, UmlMessages.MessagePropertiesEditionPart_SendEventLabel);
    sendEvent = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.sendEvent, UmlViewsRepository.SWT_KIND));
    sendEvent.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    sendEvent.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartImpl.this, UmlViewsRepository.Message.Properties.sendEvent, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getSendEvent()));
      }

    });
    GridData sendEventData = new GridData(GridData.FILL_HORIZONTAL);
    sendEvent.setLayoutData(sendEventData);
    sendEvent.setID(UmlViewsRepository.Message.Properties.sendEvent);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.sendEvent, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createConnectorFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.Message.Properties.connector, UmlMessages.MessagePropertiesEditionPart_ConnectorLabel);
    connector = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.connector, UmlViewsRepository.SWT_KIND));
    connector.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    connector.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartImpl.this, UmlViewsRepository.Message.Properties.connector, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getConnector()));
      }

    });
    GridData connectorData = new GridData(GridData.FILL_HORIZONTAL);
    connector.setLayoutData(connectorData);
    connector.setID(UmlViewsRepository.Message.Properties.connector);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.connector, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createInteractionFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.Message.Properties.interaction, UmlMessages.MessagePropertiesEditionPart_InteractionLabel);
    interaction = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.interaction, UmlViewsRepository.SWT_KIND));
    interaction.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    interaction.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartImpl.this, UmlViewsRepository.Message.Properties.interaction, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getInteraction()));
      }

    });
    GridData interactionData = new GridData(GridData.FILL_HORIZONTAL);
    interaction.setLayoutData(interactionData);
    interaction.setID(UmlViewsRepository.Message.Properties.interaction);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.interaction, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
    return name.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
    if (newValue != null) {
      name.setText(newValue);
    } else {
      name.setText(""); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Message.Properties.name);
    if (readOnly && name.isEnabled()) {
      name.setEnabled(false);
      name.setToolTipText(UmlMessages.Message_ReadOnly);
    } else if (!readOnly && !name.isEnabled()) {
      name.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
    Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Message.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.Message_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
    visibility.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.Message.Properties.visibility);
    if (readOnly && visibility.isEnabled()) {
      visibility.setEnabled(false);
      visibility.setToolTipText(UmlMessages.Message_ReadOnly);
    } else if (!readOnly && !visibility.isEnabled()) {
      visibility.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Message.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.Message_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
  clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
    clientDependencyFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
    clientDependencyBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
    return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#getMessageSort()
	 * @generated
	 */
	public Enumerator getMessageSort() {
    Enumerator selection = (Enumerator) ((StructuredSelection) messageSort.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#initMessageSort(Object input, Enumerator current)
	 */
	public void initMessageSort(Object input, Enumerator current) {
		messageSort.setInput(input);
		messageSort.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Message.Properties.messageSort);
		if (readOnly && messageSort.isEnabled()) {
			messageSort.setEnabled(false);
			messageSort.setToolTipText(UmlMessages.Message_ReadOnly);
		} else if (!readOnly && !messageSort.isEnabled()) {
			messageSort.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setMessageSort(Enumerator newValue)
	 * @generated
	 */
	public void setMessageSort(Enumerator newValue) {
    messageSort.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.Message.Properties.messageSort);
    if (readOnly && messageSort.isEnabled()) {
      messageSort.setEnabled(false);
      messageSort.setToolTipText(UmlMessages.Message_ReadOnly);
    } else if (!readOnly && !messageSort.isEnabled()) {
      messageSort.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#getReceiveEvent()
	 * @generated
	 */
	public EObject getReceiveEvent() {
    if (receiveEvent.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) receiveEvent.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#initReceiveEvent(EObjectFlatComboSettings)
	 */
	public void initReceiveEvent(EObjectFlatComboSettings settings) {
		receiveEvent.setInput(settings);
		if (current != null) {
			receiveEvent.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Message.Properties.receiveEvent);
		if (readOnly && receiveEvent.isEnabled()) {
			receiveEvent.setEnabled(false);
			receiveEvent.setToolTipText(UmlMessages.Message_ReadOnly);
		} else if (!readOnly && !receiveEvent.isEnabled()) {
			receiveEvent.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setReceiveEvent(EObject newValue)
	 * @generated
	 */
	public void setReceiveEvent(EObject newValue) {
    if (newValue != null) {
      receiveEvent.setSelection(new StructuredSelection(newValue));
    } else {
      receiveEvent.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Message.Properties.receiveEvent);
    if (readOnly && receiveEvent.isEnabled()) {
      receiveEvent.setEnabled(false);
      receiveEvent.setToolTipText(UmlMessages.Message_ReadOnly);
    } else if (!readOnly && !receiveEvent.isEnabled()) {
      receiveEvent.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setReceiveEventButtonMode(ButtonsModeEnum newValue)
	 */
	public void setReceiveEventButtonMode(ButtonsModeEnum newValue) {
		receiveEvent.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addFilterReceiveEvent(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToReceiveEvent(ViewerFilter filter) {
    receiveEvent.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addBusinessFilterReceiveEvent(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToReceiveEvent(ViewerFilter filter) {
    receiveEvent.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#getSendEvent()
	 * @generated
	 */
	public EObject getSendEvent() {
    if (sendEvent.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) sendEvent.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#initSendEvent(EObjectFlatComboSettings)
	 */
	public void initSendEvent(EObjectFlatComboSettings settings) {
		sendEvent.setInput(settings);
		if (current != null) {
			sendEvent.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Message.Properties.sendEvent);
		if (readOnly && sendEvent.isEnabled()) {
			sendEvent.setEnabled(false);
			sendEvent.setToolTipText(UmlMessages.Message_ReadOnly);
		} else if (!readOnly && !sendEvent.isEnabled()) {
			sendEvent.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setSendEvent(EObject newValue)
	 * @generated
	 */
	public void setSendEvent(EObject newValue) {
    if (newValue != null) {
      sendEvent.setSelection(new StructuredSelection(newValue));
    } else {
      sendEvent.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Message.Properties.sendEvent);
    if (readOnly && sendEvent.isEnabled()) {
      sendEvent.setEnabled(false);
      sendEvent.setToolTipText(UmlMessages.Message_ReadOnly);
    } else if (!readOnly && !sendEvent.isEnabled()) {
      sendEvent.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setSendEventButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSendEventButtonMode(ButtonsModeEnum newValue) {
		sendEvent.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addFilterSendEvent(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSendEvent(ViewerFilter filter) {
    sendEvent.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addBusinessFilterSendEvent(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSendEvent(ViewerFilter filter) {
    sendEvent.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#getConnector()
	 * @generated
	 */
	public EObject getConnector() {
    if (connector.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) connector.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#initConnector(EObjectFlatComboSettings)
	 */
	public void initConnector(EObjectFlatComboSettings settings) {
		connector.setInput(settings);
		if (current != null) {
			connector.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Message.Properties.connector);
		if (readOnly && connector.isEnabled()) {
			connector.setEnabled(false);
			connector.setToolTipText(UmlMessages.Message_ReadOnly);
		} else if (!readOnly && !connector.isEnabled()) {
			connector.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setConnector(EObject newValue)
	 * @generated
	 */
	public void setConnector(EObject newValue) {
    if (newValue != null) {
      connector.setSelection(new StructuredSelection(newValue));
    } else {
      connector.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Message.Properties.connector);
    if (readOnly && connector.isEnabled()) {
      connector.setEnabled(false);
      connector.setToolTipText(UmlMessages.Message_ReadOnly);
    } else if (!readOnly && !connector.isEnabled()) {
      connector.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setConnectorButtonMode(ButtonsModeEnum newValue)
	 */
	public void setConnectorButtonMode(ButtonsModeEnum newValue) {
		connector.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addFilterConnector(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToConnector(ViewerFilter filter) {
    connector.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addBusinessFilterConnector(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToConnector(ViewerFilter filter) {
    connector.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#getInteraction()
	 * @generated
	 */
	public EObject getInteraction() {
    if (interaction.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) interaction.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#initInteraction(EObjectFlatComboSettings)
	 */
	public void initInteraction(EObjectFlatComboSettings settings) {
		interaction.setInput(settings);
		if (current != null) {
			interaction.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Message.Properties.interaction);
		if (readOnly && interaction.isEnabled()) {
			interaction.setEnabled(false);
			interaction.setToolTipText(UmlMessages.Message_ReadOnly);
		} else if (!readOnly && !interaction.isEnabled()) {
			interaction.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setInteraction(EObject newValue)
	 * @generated
	 */
	public void setInteraction(EObject newValue) {
    if (newValue != null) {
      interaction.setSelection(new StructuredSelection(newValue));
    } else {
      interaction.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Message.Properties.interaction);
    if (readOnly && interaction.isEnabled()) {
      interaction.setEnabled(false);
      interaction.setToolTipText(UmlMessages.Message_ReadOnly);
    } else if (!readOnly && !interaction.isEnabled()) {
      interaction.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setInteractionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInteractionButtonMode(ButtonsModeEnum newValue) {
		interaction.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addFilterInteraction(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInteraction(ViewerFilter filter) {
    interaction.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addBusinessFilterInteraction(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInteraction(ViewerFilter filter) {
    interaction.addBusinessRuleFilter(filter);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.Message_Part_Title;
  }



}
