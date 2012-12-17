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

import org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class InformationFlowPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, InformationFlowPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer owningTemplateParameter;
	protected EObjectFlatComboViewer templateParameter;
	protected ReferencesTable realization;
	protected List<ViewerFilter> realizationBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> realizationFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable conveyed;
	protected List<ViewerFilter> conveyedBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> conveyedFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable informationSource;
	protected List<ViewerFilter> informationSourceBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> informationSourceFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable informationTarget;
	protected List<ViewerFilter> informationTargetBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> informationTargetFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable realizingActivityEdge;
	protected List<ViewerFilter> realizingActivityEdgeBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> realizingActivityEdgeFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable realizingConnector;
	protected List<ViewerFilter> realizingConnectorBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> realizingConnectorFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable realizingMessage;
	protected List<ViewerFilter> realizingMessageBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> realizingMessageFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public InformationFlowPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence informationFlowStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = informationFlowStep.addStep(UmlViewsRepository.InformationFlow.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.name);
    propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.visibility);
    propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.clientDependency);
    propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter);
    propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.templateParameter);
    propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.realization);
    propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.conveyed);
    propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.informationSource);
    propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.informationTarget);
    propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge);
    propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.realizingConnector);
    propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.realizingMessage);
    
    
    composer = new PartComposer(informationFlowStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.InformationFlow.Properties.class) {
          return createPropertiesGroup(parent);
        }
        if (key == UmlViewsRepository.InformationFlow.Properties.name) {
          return createNameText(parent);
        }
        if (key == UmlViewsRepository.InformationFlow.Properties.visibility) {
          return createVisibilityEMFComboViewer(parent);
        }
        if (key == UmlViewsRepository.InformationFlow.Properties.clientDependency) {
          return createClientDependencyAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter) {
          return createOwningTemplateParameterFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.InformationFlow.Properties.templateParameter) {
          return createTemplateParameterFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.InformationFlow.Properties.realization) {
          return createRealizationAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.InformationFlow.Properties.conveyed) {
          return createConveyedAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.InformationFlow.Properties.informationSource) {
          return createInformationSourceAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.InformationFlow.Properties.informationTarget) {
          return createInformationTargetAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge) {
          return createRealizingActivityEdgeAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.InformationFlow.Properties.realizingConnector) {
          return createRealizingConnectorAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.InformationFlow.Properties.realizingMessage) {
          return createRealizingMessageAdvancedReferencesTable(parent);
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
    propertiesGroup.setText(UmlMessages.InformationFlowPropertiesEditionPart_PropertiesGroupLabel);
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
    createDescription(parent, UmlViewsRepository.InformationFlow.Properties.name, UmlMessages.InformationFlowPropertiesEditionPart_NameLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
        }
      }

    });
    EditingUtils.setID(name, UmlViewsRepository.InformationFlow.Properties.name);
    EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.InformationFlow.Properties.visibility, UmlMessages.InformationFlowPropertiesEditionPart_VisibilityLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
      }

    });
    visibility.setID(UmlViewsRepository.InformationFlow.Properties.visibility);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.InformationFlow.Properties.clientDependency, UmlMessages.InformationFlowPropertiesEditionPart_ClientDependencyLabel);		 
    this.clientDependency = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addClientDependency(); }
      public void handleEdit(EObject element) { editClientDependency(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromClientDependency(element); }
      public void navigateTo(EObject element) { }
    });
    this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
    this.clientDependency.createControls(parent);
    this.clientDependency.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
    clientDependencyData.horizontalSpan = 3;
    this.clientDependency.setLayoutData(clientDependencyData);
    this.clientDependency.disableMove();
    clientDependency.setID(UmlViewsRepository.InformationFlow.Properties.clientDependency);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.clientDependency,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    clientDependency.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	 * @generated
	 */
	protected Composite createOwningTemplateParameterFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter, UmlMessages.InformationFlowPropertiesEditionPart_OwningTemplateParameterLabel);
    owningTemplateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND));
    owningTemplateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    owningTemplateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getOwningTemplateParameter()));
      }

    });
    GridData owningTemplateParameterData = new GridData(GridData.FILL_HORIZONTAL);
    owningTemplateParameter.setLayoutData(owningTemplateParameterData);
    owningTemplateParameter.setID(UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createTemplateParameterFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.InformationFlow.Properties.templateParameter, UmlMessages.InformationFlowPropertiesEditionPart_TemplateParameterLabel);
    templateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.InformationFlow.Properties.templateParameter, UmlViewsRepository.SWT_KIND));
    templateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    templateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.templateParameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getTemplateParameter()));
      }

    });
    GridData templateParameterData = new GridData(GridData.FILL_HORIZONTAL);
    templateParameter.setLayoutData(templateParameterData);
    templateParameter.setID(UmlViewsRepository.InformationFlow.Properties.templateParameter);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.templateParameter, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createRealizationAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.InformationFlow.Properties.realization, UmlMessages.InformationFlowPropertiesEditionPart_RealizationLabel);		 
    this.realization = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addRealization(); }
      public void handleEdit(EObject element) { editRealization(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveRealization(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromRealization(element); }
      public void navigateTo(EObject element) { }
    });
    this.realization.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.realization, UmlViewsRepository.SWT_KIND));
    this.realization.createControls(parent);
    this.realization.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realization, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData realizationData = new GridData(GridData.FILL_HORIZONTAL);
    realizationData.horizontalSpan = 3;
    this.realization.setLayoutData(realizationData);
    this.realization.disableMove();
    realization.setID(UmlViewsRepository.InformationFlow.Properties.realization);
    realization.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addRealization() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(realization.getInput(), realizationFilters, realizationBusinessFilters,
    "realization", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realization,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        realization.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveRealization(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realization, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    realization.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromRealization(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realization, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    realization.refresh();
  }

	/**
	 * @generated
	 */
	protected void editRealization(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        realization.refresh();
      }
    }
  }

	/**
	 * @generated
	 */
	protected Composite createConveyedAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.InformationFlow.Properties.conveyed, UmlMessages.InformationFlowPropertiesEditionPart_ConveyedLabel);		 
    this.conveyed = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addConveyed(); }
      public void handleEdit(EObject element) { editConveyed(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveConveyed(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromConveyed(element); }
      public void navigateTo(EObject element) { }
    });
    this.conveyed.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.conveyed, UmlViewsRepository.SWT_KIND));
    this.conveyed.createControls(parent);
    this.conveyed.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.conveyed, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData conveyedData = new GridData(GridData.FILL_HORIZONTAL);
    conveyedData.horizontalSpan = 3;
    this.conveyed.setLayoutData(conveyedData);
    this.conveyed.disableMove();
    conveyed.setID(UmlViewsRepository.InformationFlow.Properties.conveyed);
    conveyed.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addConveyed() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(conveyed.getInput(), conveyedFilters, conveyedBusinessFilters,
    "conveyed", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.conveyed,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        conveyed.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveConveyed(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.conveyed, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    conveyed.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromConveyed(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.conveyed, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    conveyed.refresh();
  }

	/**
	 * @generated
	 */
	protected void editConveyed(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        conveyed.refresh();
      }
    }
  }

	/**
	 * @generated
	 */
	protected Composite createInformationSourceAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.InformationFlow.Properties.informationSource, UmlMessages.InformationFlowPropertiesEditionPart_InformationSourceLabel);		 
    this.informationSource = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addInformationSource(); }
      public void handleEdit(EObject element) { editInformationSource(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveInformationSource(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromInformationSource(element); }
      public void navigateTo(EObject element) { }
    });
    this.informationSource.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.informationSource, UmlViewsRepository.SWT_KIND));
    this.informationSource.createControls(parent);
    this.informationSource.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.informationSource, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData informationSourceData = new GridData(GridData.FILL_HORIZONTAL);
    informationSourceData.horizontalSpan = 3;
    this.informationSource.setLayoutData(informationSourceData);
    this.informationSource.disableMove();
    informationSource.setID(UmlViewsRepository.InformationFlow.Properties.informationSource);
    informationSource.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addInformationSource() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(informationSource.getInput(), informationSourceFilters, informationSourceBusinessFilters,
    "informationSource", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.informationSource,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        informationSource.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveInformationSource(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.informationSource, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    informationSource.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromInformationSource(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.informationSource, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    informationSource.refresh();
  }

	/**
	 * @generated
	 */
	protected void editInformationSource(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        informationSource.refresh();
      }
    }
  }

	/**
	 * @generated
	 */
	protected Composite createInformationTargetAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.InformationFlow.Properties.informationTarget, UmlMessages.InformationFlowPropertiesEditionPart_InformationTargetLabel);		 
    this.informationTarget = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addInformationTarget(); }
      public void handleEdit(EObject element) { editInformationTarget(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveInformationTarget(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromInformationTarget(element); }
      public void navigateTo(EObject element) { }
    });
    this.informationTarget.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.informationTarget, UmlViewsRepository.SWT_KIND));
    this.informationTarget.createControls(parent);
    this.informationTarget.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.informationTarget, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData informationTargetData = new GridData(GridData.FILL_HORIZONTAL);
    informationTargetData.horizontalSpan = 3;
    this.informationTarget.setLayoutData(informationTargetData);
    this.informationTarget.disableMove();
    informationTarget.setID(UmlViewsRepository.InformationFlow.Properties.informationTarget);
    informationTarget.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addInformationTarget() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(informationTarget.getInput(), informationTargetFilters, informationTargetBusinessFilters,
    "informationTarget", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.informationTarget,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        informationTarget.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveInformationTarget(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.informationTarget, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    informationTarget.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromInformationTarget(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.informationTarget, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    informationTarget.refresh();
  }

	/**
	 * @generated
	 */
	protected void editInformationTarget(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        informationTarget.refresh();
      }
    }
  }

	/**
	 * @generated
	 */
	protected Composite createRealizingActivityEdgeAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge, UmlMessages.InformationFlowPropertiesEditionPart_RealizingActivityEdgeLabel);		 
    this.realizingActivityEdge = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addRealizingActivityEdge(); }
      public void handleEdit(EObject element) { editRealizingActivityEdge(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveRealizingActivityEdge(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromRealizingActivityEdge(element); }
      public void navigateTo(EObject element) { }
    });
    this.realizingActivityEdge.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge, UmlViewsRepository.SWT_KIND));
    this.realizingActivityEdge.createControls(parent);
    this.realizingActivityEdge.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData realizingActivityEdgeData = new GridData(GridData.FILL_HORIZONTAL);
    realizingActivityEdgeData.horizontalSpan = 3;
    this.realizingActivityEdge.setLayoutData(realizingActivityEdgeData);
    this.realizingActivityEdge.disableMove();
    realizingActivityEdge.setID(UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge);
    realizingActivityEdge.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addRealizingActivityEdge() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(realizingActivityEdge.getInput(), realizingActivityEdgeFilters, realizingActivityEdgeBusinessFilters,
    "realizingActivityEdge", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        realizingActivityEdge.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveRealizingActivityEdge(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    realizingActivityEdge.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromRealizingActivityEdge(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    realizingActivityEdge.refresh();
  }

	/**
	 * @generated
	 */
	protected void editRealizingActivityEdge(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        realizingActivityEdge.refresh();
      }
    }
  }

	/**
	 * @generated
	 */
	protected Composite createRealizingConnectorAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.InformationFlow.Properties.realizingConnector, UmlMessages.InformationFlowPropertiesEditionPart_RealizingConnectorLabel);		 
    this.realizingConnector = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addRealizingConnector(); }
      public void handleEdit(EObject element) { editRealizingConnector(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveRealizingConnector(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromRealizingConnector(element); }
      public void navigateTo(EObject element) { }
    });
    this.realizingConnector.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.realizingConnector, UmlViewsRepository.SWT_KIND));
    this.realizingConnector.createControls(parent);
    this.realizingConnector.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realizingConnector, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData realizingConnectorData = new GridData(GridData.FILL_HORIZONTAL);
    realizingConnectorData.horizontalSpan = 3;
    this.realizingConnector.setLayoutData(realizingConnectorData);
    this.realizingConnector.disableMove();
    realizingConnector.setID(UmlViewsRepository.InformationFlow.Properties.realizingConnector);
    realizingConnector.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addRealizingConnector() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(realizingConnector.getInput(), realizingConnectorFilters, realizingConnectorBusinessFilters,
    "realizingConnector", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realizingConnector,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        realizingConnector.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveRealizingConnector(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realizingConnector, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    realizingConnector.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromRealizingConnector(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realizingConnector, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    realizingConnector.refresh();
  }

	/**
	 * @generated
	 */
	protected void editRealizingConnector(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        realizingConnector.refresh();
      }
    }
  }

	/**
	 * @generated
	 */
	protected Composite createRealizingMessageAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.InformationFlow.Properties.realizingMessage, UmlMessages.InformationFlowPropertiesEditionPart_RealizingMessageLabel);		 
    this.realizingMessage = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addRealizingMessage(); }
      public void handleEdit(EObject element) { editRealizingMessage(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveRealizingMessage(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromRealizingMessage(element); }
      public void navigateTo(EObject element) { }
    });
    this.realizingMessage.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.realizingMessage, UmlViewsRepository.SWT_KIND));
    this.realizingMessage.createControls(parent);
    this.realizingMessage.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realizingMessage, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData realizingMessageData = new GridData(GridData.FILL_HORIZONTAL);
    realizingMessageData.horizontalSpan = 3;
    this.realizingMessage.setLayoutData(realizingMessageData);
    this.realizingMessage.disableMove();
    realizingMessage.setID(UmlViewsRepository.InformationFlow.Properties.realizingMessage);
    realizingMessage.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addRealizingMessage() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(realizingMessage.getInput(), realizingMessageFilters, realizingMessageBusinessFilters,
    "realizingMessage", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realizingMessage,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        realizingMessage.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveRealizingMessage(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realizingMessage, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    realizingMessage.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromRealizingMessage(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartImpl.this, UmlViewsRepository.InformationFlow.Properties.realizingMessage, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    realizingMessage.refresh();
  }

	/**
	 * @generated
	 */
	protected void editRealizingMessage(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        realizingMessage.refresh();
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
    return name.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
    if (newValue != null) {
      name.setText(newValue);
    } else {
      name.setText(""); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.name);
    if (readOnly && name.isEnabled()) {
      name.setEnabled(false);
      name.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
    } else if (!readOnly && !name.isEnabled()) {
      name.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
    Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
    visibility.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.visibility);
    if (readOnly && visibility.isEnabled()) {
      visibility.setEnabled(false);
      visibility.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
    } else if (!readOnly && !visibility.isEnabled()) {
      visibility.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
  clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
    clientDependencyFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
    clientDependencyBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
    return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#getOwningTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initOwningTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initOwningTemplateParameter(EObjectFlatComboSettings settings) {
		owningTemplateParameter.setInput(settings);
		if (current != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter);
		if (readOnly && owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(false);
			owningTemplateParameter.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
		} else if (!readOnly && !owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#setOwningTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setOwningTemplateParameter(EObject newValue) {
    if (newValue != null) {
      owningTemplateParameter.setSelection(new StructuredSelection(newValue));
    } else {
      owningTemplateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter);
    if (readOnly && owningTemplateParameter.isEnabled()) {
      owningTemplateParameter.setEnabled(false);
      owningTemplateParameter.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
    } else if (!readOnly && !owningTemplateParameter.isEnabled()) {
      owningTemplateParameter.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		owningTemplateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter) {
    owningTemplateParameter.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter) {
    owningTemplateParameter.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#getTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initTemplateParameter(EObjectFlatComboSettings settings) {
		templateParameter.setInput(settings);
		if (current != null) {
			templateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.templateParameter);
		if (readOnly && templateParameter.isEnabled()) {
			templateParameter.setEnabled(false);
			templateParameter.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
		} else if (!readOnly && !templateParameter.isEnabled()) {
			templateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#setTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setTemplateParameter(EObject newValue) {
    if (newValue != null) {
      templateParameter.setSelection(new StructuredSelection(newValue));
    } else {
      templateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.templateParameter);
    if (readOnly && templateParameter.isEnabled()) {
      templateParameter.setEnabled(false);
      templateParameter.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
    } else if (!readOnly && !templateParameter.isEnabled()) {
      templateParameter.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#setTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		templateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter) {
    templateParameter.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter) {
    templateParameter.addBusinessRuleFilter(filter);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initRealization(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRealization(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		realization.setContentProvider(contentProvider);
		realization.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.realization);
		if (readOnly && realization.getTable().isEnabled()) {
			realization.setEnabled(false);
			realization.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
		} else if (!readOnly && !realization.getTable().isEnabled()) {
			realization.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateRealization()
	 * @generated
	 */
	public void updateRealization() {
  realization.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterRealization(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRealization(ViewerFilter filter) {
    realizationFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterRealization(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRealization(ViewerFilter filter) {
    realizationBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInRealizationTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRealizationTable(EObject element) {
    return ((ReferencesTableSettings)realization.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initConveyed(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initConveyed(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		conveyed.setContentProvider(contentProvider);
		conveyed.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.conveyed);
		if (readOnly && conveyed.getTable().isEnabled()) {
			conveyed.setEnabled(false);
			conveyed.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
		} else if (!readOnly && !conveyed.getTable().isEnabled()) {
			conveyed.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateConveyed()
	 * @generated
	 */
	public void updateConveyed() {
  conveyed.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterConveyed(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToConveyed(ViewerFilter filter) {
    conveyedFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterConveyed(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToConveyed(ViewerFilter filter) {
    conveyedBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInConveyedTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInConveyedTable(EObject element) {
    return ((ReferencesTableSettings)conveyed.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initInformationSource(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInformationSource(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		informationSource.setContentProvider(contentProvider);
		informationSource.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.informationSource);
		if (readOnly && informationSource.getTable().isEnabled()) {
			informationSource.setEnabled(false);
			informationSource.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
		} else if (!readOnly && !informationSource.getTable().isEnabled()) {
			informationSource.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateInformationSource()
	 * @generated
	 */
	public void updateInformationSource() {
  informationSource.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterInformationSource(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInformationSource(ViewerFilter filter) {
    informationSourceFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterInformationSource(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInformationSource(ViewerFilter filter) {
    informationSourceBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInInformationSourceTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInInformationSourceTable(EObject element) {
    return ((ReferencesTableSettings)informationSource.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initInformationTarget(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInformationTarget(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		informationTarget.setContentProvider(contentProvider);
		informationTarget.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.informationTarget);
		if (readOnly && informationTarget.getTable().isEnabled()) {
			informationTarget.setEnabled(false);
			informationTarget.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
		} else if (!readOnly && !informationTarget.getTable().isEnabled()) {
			informationTarget.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateInformationTarget()
	 * @generated
	 */
	public void updateInformationTarget() {
  informationTarget.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterInformationTarget(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInformationTarget(ViewerFilter filter) {
    informationTargetFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterInformationTarget(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInformationTarget(ViewerFilter filter) {
    informationTargetBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInInformationTargetTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInInformationTargetTable(EObject element) {
    return ((ReferencesTableSettings)informationTarget.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initRealizingActivityEdge(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRealizingActivityEdge(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		realizingActivityEdge.setContentProvider(contentProvider);
		realizingActivityEdge.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge);
		if (readOnly && realizingActivityEdge.getTable().isEnabled()) {
			realizingActivityEdge.setEnabled(false);
			realizingActivityEdge.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
		} else if (!readOnly && !realizingActivityEdge.getTable().isEnabled()) {
			realizingActivityEdge.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateRealizingActivityEdge()
	 * @generated
	 */
	public void updateRealizingActivityEdge() {
  realizingActivityEdge.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterRealizingActivityEdge(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRealizingActivityEdge(ViewerFilter filter) {
    realizingActivityEdgeFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterRealizingActivityEdge(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRealizingActivityEdge(ViewerFilter filter) {
    realizingActivityEdgeBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInRealizingActivityEdgeTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRealizingActivityEdgeTable(EObject element) {
    return ((ReferencesTableSettings)realizingActivityEdge.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initRealizingConnector(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRealizingConnector(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		realizingConnector.setContentProvider(contentProvider);
		realizingConnector.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.realizingConnector);
		if (readOnly && realizingConnector.getTable().isEnabled()) {
			realizingConnector.setEnabled(false);
			realizingConnector.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
		} else if (!readOnly && !realizingConnector.getTable().isEnabled()) {
			realizingConnector.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateRealizingConnector()
	 * @generated
	 */
	public void updateRealizingConnector() {
  realizingConnector.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterRealizingConnector(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRealizingConnector(ViewerFilter filter) {
    realizingConnectorFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterRealizingConnector(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRealizingConnector(ViewerFilter filter) {
    realizingConnectorBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInRealizingConnectorTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRealizingConnectorTable(EObject element) {
    return ((ReferencesTableSettings)realizingConnector.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initRealizingMessage(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRealizingMessage(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		realizingMessage.setContentProvider(contentProvider);
		realizingMessage.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.InformationFlow.Properties.realizingMessage);
		if (readOnly && realizingMessage.getTable().isEnabled()) {
			realizingMessage.setEnabled(false);
			realizingMessage.setToolTipText(UmlMessages.InformationFlow_ReadOnly);
		} else if (!readOnly && !realizingMessage.getTable().isEnabled()) {
			realizingMessage.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateRealizingMessage()
	 * @generated
	 */
	public void updateRealizingMessage() {
  realizingMessage.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterRealizingMessage(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRealizingMessage(ViewerFilter filter) {
    realizingMessageFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterRealizingMessage(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRealizingMessage(ViewerFilter filter) {
    realizingMessageBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInRealizingMessageTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRealizingMessageTable(EObject element) {
    return ((ReferencesTableSettings)realizingMessage.getInput()).contains(element);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.InformationFlow_Part_Title;
  }



}
