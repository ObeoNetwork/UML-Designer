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

import org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class MessageOccurrenceSpecificationPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, MessageOccurrenceSpecificationPropertiesEditionPart {

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
	protected ReferencesTable toBefore;
	protected List<ViewerFilter> toBeforeBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> toBeforeFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer event;
	protected ReferencesTable toAfter;
	protected List<ViewerFilter> toAfterBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> toAfterFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer message;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public MessageOccurrenceSpecificationPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence messageOccurrenceSpecificationStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = messageOccurrenceSpecificationStep.addStep(UmlViewsRepository.MessageOccurrenceSpecification.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.MessageOccurrenceSpecification.Properties.name);
    propertiesStep.addStep(UmlViewsRepository.MessageOccurrenceSpecification.Properties.visibility);
    propertiesStep.addStep(UmlViewsRepository.MessageOccurrenceSpecification.Properties.clientDependency);
    propertiesStep.addStep(UmlViewsRepository.MessageOccurrenceSpecification.Properties.covered);
    propertiesStep.addStep(UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingInteraction);
    propertiesStep.addStep(UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingOperand);
    propertiesStep.addStep(UmlViewsRepository.MessageOccurrenceSpecification.Properties.toBefore);
    propertiesStep.addStep(UmlViewsRepository.MessageOccurrenceSpecification.Properties.event);
    propertiesStep.addStep(UmlViewsRepository.MessageOccurrenceSpecification.Properties.toAfter);
    propertiesStep.addStep(UmlViewsRepository.MessageOccurrenceSpecification.Properties.message);
    
    
    composer = new PartComposer(messageOccurrenceSpecificationStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.MessageOccurrenceSpecification.Properties.class) {
          return createPropertiesGroup(parent);
        }
        if (key == UmlViewsRepository.MessageOccurrenceSpecification.Properties.name) {
          return createNameText(parent);
        }
        if (key == UmlViewsRepository.MessageOccurrenceSpecification.Properties.visibility) {
          return createVisibilityEMFComboViewer(parent);
        }
        if (key == UmlViewsRepository.MessageOccurrenceSpecification.Properties.clientDependency) {
          return createClientDependencyAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.MessageOccurrenceSpecification.Properties.covered) {
          return createCoveredAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingInteraction) {
          return createEnclosingInteractionFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingOperand) {
          return createEnclosingOperandFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.MessageOccurrenceSpecification.Properties.toBefore) {
          return createToBeforeAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.MessageOccurrenceSpecification.Properties.event) {
          return createEventFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.MessageOccurrenceSpecification.Properties.toAfter) {
          return createToAfterAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.MessageOccurrenceSpecification.Properties.message) {
          return createMessageFlatComboViewer(parent);
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
    propertiesGroup.setText(UmlMessages.MessageOccurrenceSpecificationPropertiesEditionPart_PropertiesGroupLabel);
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
    createDescription(parent, UmlViewsRepository.MessageOccurrenceSpecification.Properties.name, UmlMessages.MessageOccurrenceSpecificationPropertiesEditionPart_NameLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
        }
      }

    });
    EditingUtils.setID(name, UmlViewsRepository.MessageOccurrenceSpecification.Properties.name);
    EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.MessageOccurrenceSpecification.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.MessageOccurrenceSpecification.Properties.visibility, UmlMessages.MessageOccurrenceSpecificationPropertiesEditionPart_VisibilityLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
      }

    });
    visibility.setID(UmlViewsRepository.MessageOccurrenceSpecification.Properties.visibility);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.MessageOccurrenceSpecification.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.MessageOccurrenceSpecification.Properties.clientDependency, UmlMessages.MessageOccurrenceSpecificationPropertiesEditionPart_ClientDependencyLabel);		 
    this.clientDependency = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addClientDependency(); }
      public void handleEdit(EObject element) { editClientDependency(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromClientDependency(element); }
      public void navigateTo(EObject element) { }
    });
    this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.MessageOccurrenceSpecification.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
    this.clientDependency.createControls(parent);
    this.clientDependency.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
    clientDependencyData.horizontalSpan = 3;
    this.clientDependency.setLayoutData(clientDependencyData);
    this.clientDependency.disableMove();
    clientDependency.setID(UmlViewsRepository.MessageOccurrenceSpecification.Properties.clientDependency);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.clientDependency,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    clientDependency.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    String label = getDescription(UmlViewsRepository.MessageOccurrenceSpecification.Properties.covered, UmlMessages.MessageOccurrenceSpecificationPropertiesEditionPart_CoveredLabel);		 
    this.covered = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addCovered(); }
      public void handleEdit(EObject element) { editCovered(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveCovered(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromCovered(element); }
      public void navigateTo(EObject element) { }
    });
    this.covered.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.MessageOccurrenceSpecification.Properties.covered, UmlViewsRepository.SWT_KIND));
    this.covered.createControls(parent);
    this.covered.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.covered, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData coveredData = new GridData(GridData.FILL_HORIZONTAL);
    coveredData.horizontalSpan = 3;
    this.covered.setLayoutData(coveredData);
    this.covered.disableMove();
    covered.setID(UmlViewsRepository.MessageOccurrenceSpecification.Properties.covered);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.covered,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.covered, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    covered.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromCovered(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.covered, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    createDescription(parent, UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingInteraction, UmlMessages.MessageOccurrenceSpecificationPropertiesEditionPart_EnclosingInteractionLabel);
    enclosingInteraction = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingInteraction, UmlViewsRepository.SWT_KIND));
    enclosingInteraction.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    enclosingInteraction.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingInteraction, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getEnclosingInteraction()));
      }

    });
    GridData enclosingInteractionData = new GridData(GridData.FILL_HORIZONTAL);
    enclosingInteraction.setLayoutData(enclosingInteractionData);
    enclosingInteraction.setID(UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingInteraction);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingInteraction, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createEnclosingOperandFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingOperand, UmlMessages.MessageOccurrenceSpecificationPropertiesEditionPart_EnclosingOperandLabel);
    enclosingOperand = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingOperand, UmlViewsRepository.SWT_KIND));
    enclosingOperand.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    enclosingOperand.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingOperand, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getEnclosingOperand()));
      }

    });
    GridData enclosingOperandData = new GridData(GridData.FILL_HORIZONTAL);
    enclosingOperand.setLayoutData(enclosingOperandData);
    enclosingOperand.setID(UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingOperand);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingOperand, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createToBeforeAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.MessageOccurrenceSpecification.Properties.toBefore, UmlMessages.MessageOccurrenceSpecificationPropertiesEditionPart_ToBeforeLabel);		 
    this.toBefore = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addToBefore(); }
      public void handleEdit(EObject element) { editToBefore(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveToBefore(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromToBefore(element); }
      public void navigateTo(EObject element) { }
    });
    this.toBefore.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.MessageOccurrenceSpecification.Properties.toBefore, UmlViewsRepository.SWT_KIND));
    this.toBefore.createControls(parent);
    this.toBefore.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.toBefore, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData toBeforeData = new GridData(GridData.FILL_HORIZONTAL);
    toBeforeData.horizontalSpan = 3;
    this.toBefore.setLayoutData(toBeforeData);
    this.toBefore.disableMove();
    toBefore.setID(UmlViewsRepository.MessageOccurrenceSpecification.Properties.toBefore);
    toBefore.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addToBefore() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(toBefore.getInput(), toBeforeFilters, toBeforeBusinessFilters,
    "toBefore", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.toBefore,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        toBefore.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveToBefore(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.toBefore, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    toBefore.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromToBefore(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.toBefore, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    toBefore.refresh();
  }

	/**
	 * @generated
	 */
	protected void editToBefore(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        toBefore.refresh();
      }
    }
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createEventFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.MessageOccurrenceSpecification.Properties.event, UmlMessages.MessageOccurrenceSpecificationPropertiesEditionPart_EventLabel);
    event = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.MessageOccurrenceSpecification.Properties.event, UmlViewsRepository.SWT_KIND));
    event.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    event.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.event, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getEvent()));
      }

    });
    GridData eventData = new GridData(GridData.FILL_HORIZONTAL);
    event.setLayoutData(eventData);
    event.setID(UmlViewsRepository.MessageOccurrenceSpecification.Properties.event);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.MessageOccurrenceSpecification.Properties.event, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createToAfterAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.MessageOccurrenceSpecification.Properties.toAfter, UmlMessages.MessageOccurrenceSpecificationPropertiesEditionPart_ToAfterLabel);		 
    this.toAfter = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addToAfter(); }
      public void handleEdit(EObject element) { editToAfter(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveToAfter(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromToAfter(element); }
      public void navigateTo(EObject element) { }
    });
    this.toAfter.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.MessageOccurrenceSpecification.Properties.toAfter, UmlViewsRepository.SWT_KIND));
    this.toAfter.createControls(parent);
    this.toAfter.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.toAfter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData toAfterData = new GridData(GridData.FILL_HORIZONTAL);
    toAfterData.horizontalSpan = 3;
    this.toAfter.setLayoutData(toAfterData);
    this.toAfter.disableMove();
    toAfter.setID(UmlViewsRepository.MessageOccurrenceSpecification.Properties.toAfter);
    toAfter.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addToAfter() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(toAfter.getInput(), toAfterFilters, toAfterBusinessFilters,
    "toAfter", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.toAfter,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        toAfter.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveToAfter(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.toAfter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    toAfter.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromToAfter(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.toAfter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    toAfter.refresh();
  }

	/**
	 * @generated
	 */
	protected void editToAfter(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        toAfter.refresh();
      }
    }
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createMessageFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.MessageOccurrenceSpecification.Properties.message, UmlMessages.MessageOccurrenceSpecificationPropertiesEditionPart_MessageLabel);
    message = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.MessageOccurrenceSpecification.Properties.message, UmlViewsRepository.SWT_KIND));
    message.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    message.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessageOccurrenceSpecificationPropertiesEditionPartImpl.this, UmlViewsRepository.MessageOccurrenceSpecification.Properties.message, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getMessage()));
      }

    });
    GridData messageData = new GridData(GridData.FILL_HORIZONTAL);
    message.setLayoutData(messageData);
    message.setID(UmlViewsRepository.MessageOccurrenceSpecification.Properties.message);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.MessageOccurrenceSpecification.Properties.message, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
    return name.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
    if (newValue != null) {
      name.setText(newValue);
    } else {
      name.setText(""); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.name);
    if (readOnly && name.isEnabled()) {
      name.setEnabled(false);
      name.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
    } else if (!readOnly && !name.isEnabled()) {
      name.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
    Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
    visibility.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.visibility);
    if (readOnly && visibility.isEnabled()) {
      visibility.setEnabled(false);
      visibility.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
    } else if (!readOnly && !visibility.isEnabled()) {
      visibility.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
  clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
    clientDependencyFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
    clientDependencyBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
    return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#initCovered(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initCovered(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		covered.setContentProvider(contentProvider);
		covered.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.covered);
		if (readOnly && covered.getTable().isEnabled()) {
			covered.setEnabled(false);
			covered.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !covered.getTable().isEnabled()) {
			covered.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#updateCovered()
	 * @generated
	 */
	public void updateCovered() {
  covered.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addFilterCovered(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToCovered(ViewerFilter filter) {
    coveredFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterCovered(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToCovered(ViewerFilter filter) {
    coveredBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#isContainedInCoveredTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInCoveredTable(EObject element) {
    return ((ReferencesTableSettings)covered.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#getEnclosingInteraction()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#initEnclosingInteraction(EObjectFlatComboSettings)
	 */
	public void initEnclosingInteraction(EObjectFlatComboSettings settings) {
		enclosingInteraction.setInput(settings);
		if (current != null) {
			enclosingInteraction.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingInteraction);
		if (readOnly && enclosingInteraction.isEnabled()) {
			enclosingInteraction.setEnabled(false);
			enclosingInteraction.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !enclosingInteraction.isEnabled()) {
			enclosingInteraction.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#setEnclosingInteraction(EObject newValue)
	 * @generated
	 */
	public void setEnclosingInteraction(EObject newValue) {
    if (newValue != null) {
      enclosingInteraction.setSelection(new StructuredSelection(newValue));
    } else {
      enclosingInteraction.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingInteraction);
    if (readOnly && enclosingInteraction.isEnabled()) {
      enclosingInteraction.setEnabled(false);
      enclosingInteraction.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
    } else if (!readOnly && !enclosingInteraction.isEnabled()) {
      enclosingInteraction.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#setEnclosingInteractionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setEnclosingInteractionButtonMode(ButtonsModeEnum newValue) {
		enclosingInteraction.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addFilterEnclosingInteraction(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEnclosingInteraction(ViewerFilter filter) {
    enclosingInteraction.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterEnclosingInteraction(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEnclosingInteraction(ViewerFilter filter) {
    enclosingInteraction.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#getEnclosingOperand()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#initEnclosingOperand(EObjectFlatComboSettings)
	 */
	public void initEnclosingOperand(EObjectFlatComboSettings settings) {
		enclosingOperand.setInput(settings);
		if (current != null) {
			enclosingOperand.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingOperand);
		if (readOnly && enclosingOperand.isEnabled()) {
			enclosingOperand.setEnabled(false);
			enclosingOperand.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !enclosingOperand.isEnabled()) {
			enclosingOperand.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#setEnclosingOperand(EObject newValue)
	 * @generated
	 */
	public void setEnclosingOperand(EObject newValue) {
    if (newValue != null) {
      enclosingOperand.setSelection(new StructuredSelection(newValue));
    } else {
      enclosingOperand.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.enclosingOperand);
    if (readOnly && enclosingOperand.isEnabled()) {
      enclosingOperand.setEnabled(false);
      enclosingOperand.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
    } else if (!readOnly && !enclosingOperand.isEnabled()) {
      enclosingOperand.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#setEnclosingOperandButtonMode(ButtonsModeEnum newValue)
	 */
	public void setEnclosingOperandButtonMode(ButtonsModeEnum newValue) {
		enclosingOperand.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addFilterEnclosingOperand(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEnclosingOperand(ViewerFilter filter) {
    enclosingOperand.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterEnclosingOperand(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEnclosingOperand(ViewerFilter filter) {
    enclosingOperand.addBusinessRuleFilter(filter);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#initToBefore(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initToBefore(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		toBefore.setContentProvider(contentProvider);
		toBefore.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.toBefore);
		if (readOnly && toBefore.getTable().isEnabled()) {
			toBefore.setEnabled(false);
			toBefore.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !toBefore.getTable().isEnabled()) {
			toBefore.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#updateToBefore()
	 * @generated
	 */
	public void updateToBefore() {
  toBefore.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addFilterToBefore(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToToBefore(ViewerFilter filter) {
    toBeforeFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterToBefore(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToToBefore(ViewerFilter filter) {
    toBeforeBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#isContainedInToBeforeTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInToBeforeTable(EObject element) {
    return ((ReferencesTableSettings)toBefore.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#getEvent()
	 * @generated
	 */
	public EObject getEvent() {
    if (event.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) event.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#initEvent(EObjectFlatComboSettings)
	 */
	public void initEvent(EObjectFlatComboSettings settings) {
		event.setInput(settings);
		if (current != null) {
			event.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.event);
		if (readOnly && event.isEnabled()) {
			event.setEnabled(false);
			event.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !event.isEnabled()) {
			event.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#setEvent(EObject newValue)
	 * @generated
	 */
	public void setEvent(EObject newValue) {
    if (newValue != null) {
      event.setSelection(new StructuredSelection(newValue));
    } else {
      event.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.event);
    if (readOnly && event.isEnabled()) {
      event.setEnabled(false);
      event.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
    } else if (!readOnly && !event.isEnabled()) {
      event.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#setEventButtonMode(ButtonsModeEnum newValue)
	 */
	public void setEventButtonMode(ButtonsModeEnum newValue) {
		event.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addFilterEvent(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEvent(ViewerFilter filter) {
    event.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterEvent(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEvent(ViewerFilter filter) {
    event.addBusinessRuleFilter(filter);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#initToAfter(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initToAfter(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		toAfter.setContentProvider(contentProvider);
		toAfter.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.toAfter);
		if (readOnly && toAfter.getTable().isEnabled()) {
			toAfter.setEnabled(false);
			toAfter.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !toAfter.getTable().isEnabled()) {
			toAfter.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#updateToAfter()
	 * @generated
	 */
	public void updateToAfter() {
  toAfter.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addFilterToAfter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToToAfter(ViewerFilter filter) {
    toAfterFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterToAfter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToToAfter(ViewerFilter filter) {
    toAfterBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#isContainedInToAfterTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInToAfterTable(EObject element) {
    return ((ReferencesTableSettings)toAfter.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#getMessage()
	 * @generated
	 */
	public EObject getMessage() {
    if (message.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) message.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#initMessage(EObjectFlatComboSettings)
	 */
	public void initMessage(EObjectFlatComboSettings settings) {
		message.setInput(settings);
		if (current != null) {
			message.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.message);
		if (readOnly && message.isEnabled()) {
			message.setEnabled(false);
			message.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !message.isEnabled()) {
			message.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#setMessage(EObject newValue)
	 * @generated
	 */
	public void setMessage(EObject newValue) {
    if (newValue != null) {
      message.setSelection(new StructuredSelection(newValue));
    } else {
      message.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.MessageOccurrenceSpecification.Properties.message);
    if (readOnly && message.isEnabled()) {
      message.setEnabled(false);
      message.setToolTipText(UmlMessages.MessageOccurrenceSpecification_ReadOnly);
    } else if (!readOnly && !message.isEnabled()) {
      message.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#setMessageButtonMode(ButtonsModeEnum newValue)
	 */
	public void setMessageButtonMode(ButtonsModeEnum newValue) {
		message.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addFilterMessage(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToMessage(ViewerFilter filter) {
    message.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessageOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterMessage(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToMessage(ViewerFilter filter) {
    message.addBusinessRuleFilter(filter);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.MessageOccurrenceSpecification_Part_Title;
  }



}
