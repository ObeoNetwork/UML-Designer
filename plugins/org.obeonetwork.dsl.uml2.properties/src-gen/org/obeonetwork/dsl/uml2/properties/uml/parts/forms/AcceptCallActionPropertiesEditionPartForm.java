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
package org.obeonetwork.dsl.uml2.properties.uml.parts.forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.EcoreAdapterFactory;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.part.impl.SectionPropertiesEditingPart;

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
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

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
import org.eclipse.swt.widgets.Text;

import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class AcceptCallActionPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, AcceptCallActionPropertiesEditionPart {

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
	protected Button isUnmarshall;



	/**
	 * For {@link ISection} use only.
	 */
	public AcceptCallActionPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public AcceptCallActionPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
    super(editionComponent);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
	 *  createFigure(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 * @generated
	 */
	public Composite createFigure(final Composite parent, final FormToolkit widgetFactory) {
    ScrolledForm scrolledForm = widgetFactory.createScrolledForm(parent);
    Form form = scrolledForm.getForm();
    view = form.getBody();
    GridLayout layout = new GridLayout();
    layout.numColumns = 3;
    view.setLayout(layout);
    createControls(widgetFactory, view);
    return scrolledForm;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
	 *  createControls(org.eclipse.ui.forms.widgets.FormToolkit, org.eclipse.swt.widgets.Composite)
	 * @generated
	 */
	public void createControls(final FormToolkit widgetFactory, Composite view) {
    CompositionSequence acceptCallActionStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = acceptCallActionStep.addStep(UmlViewsRepository.AcceptCallAction.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.AcceptCallAction.Properties.name);
    propertiesStep.addStep(UmlViewsRepository.AcceptCallAction.Properties.visibility);
    propertiesStep.addStep(UmlViewsRepository.AcceptCallAction.Properties.clientDependency);
    propertiesStep.addStep(UmlViewsRepository.AcceptCallAction.Properties.isLeaf);
    propertiesStep.addStep(UmlViewsRepository.AcceptCallAction.Properties.inStructuredNode);
    propertiesStep.addStep(UmlViewsRepository.AcceptCallAction.Properties.activity);
    propertiesStep.addStep(UmlViewsRepository.AcceptCallAction.Properties.outgoing);
    propertiesStep.addStep(UmlViewsRepository.AcceptCallAction.Properties.incoming);
    propertiesStep.addStep(UmlViewsRepository.AcceptCallAction.Properties.inPartition);
    propertiesStep.addStep(UmlViewsRepository.AcceptCallAction.Properties.inInterruptibleRegion);
    propertiesStep.addStep(UmlViewsRepository.AcceptCallAction.Properties.redefinedNode);
    propertiesStep.addStep(UmlViewsRepository.AcceptCallAction.Properties.isUnmarshall);
    
    
    composer = new PartComposer(acceptCallActionStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.AcceptCallAction.Properties.class) {
          return createPropertiesGroup(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.AcceptCallAction.Properties.name) {
          return createNameText(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.AcceptCallAction.Properties.visibility) {
          return createVisibilityEMFComboViewer(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.AcceptCallAction.Properties.clientDependency) {
          return createClientDependencyReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.AcceptCallAction.Properties.isLeaf) {
          return createIsLeafCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.AcceptCallAction.Properties.inStructuredNode) {
          return createInStructuredNodeFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.AcceptCallAction.Properties.activity) {
          return createActivityFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.AcceptCallAction.Properties.outgoing) {
          return createOutgoingReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.AcceptCallAction.Properties.incoming) {
          return createIncomingReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.AcceptCallAction.Properties.inPartition) {
          return createInPartitionReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.AcceptCallAction.Properties.inInterruptibleRegion) {
          return createInInterruptibleRegionReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.AcceptCallAction.Properties.redefinedNode) {
          return createRedefinedNodeReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.AcceptCallAction.Properties.isUnmarshall) {
          return createIsUnmarshallCheckbox(widgetFactory, parent);
        }
        return parent;
      }
    };
    composer.compose(view);
  }
	/**
	 * @generated
	 */
	protected Composite createPropertiesGroup(FormToolkit widgetFactory, final Composite parent) {
    Section propertiesSection = widgetFactory.createSection(parent, Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
    propertiesSection.setText(UmlMessages.AcceptCallActionPropertiesEditionPart_PropertiesGroupLabel);
    GridData propertiesSectionData = new GridData(GridData.FILL_HORIZONTAL);
    propertiesSectionData.horizontalSpan = 3;
    propertiesSection.setLayoutData(propertiesSectionData);
    Composite propertiesGroup = widgetFactory.createComposite(propertiesSection);
    GridLayout propertiesGroupLayout = new GridLayout();
    propertiesGroupLayout.numColumns = 3;
    propertiesGroup.setLayout(propertiesGroupLayout);
    propertiesSection.setClient(propertiesGroup);
    return propertiesGroup;
  }

	/**
	 * @generated
	 */
	
	protected Composite createNameText(FormToolkit widgetFactory, Composite parent) {
    createDescription(parent, UmlViewsRepository.AcceptCallAction.Properties.name, UmlMessages.AcceptCallActionPropertiesEditionPart_NameLabel);
    name = widgetFactory.createText(parent, ""); //$NON-NLS-1$
    name.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
    widgetFactory.paintBordersFor(parent);
    GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
    name.setLayoutData(nameData);
    name.addFocusListener(new FocusAdapter() {
      /**
       * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
       * @generated
       */
      @Override
      @SuppressWarnings("synthetic-access")
      public void focusLost(FocusEvent e) {
        if (propertiesEditionComponent != null) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(
              AcceptCallActionPropertiesEditionPartForm.this,
              UmlViewsRepository.AcceptCallAction.Properties.name,
              PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
          propertiesEditionComponent
              .firePropertiesChanged(new PropertiesEditionEvent(
                  AcceptCallActionPropertiesEditionPartForm.this,
                  UmlViewsRepository.AcceptCallAction.Properties.name,
                  PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_LOST,
                  null, name.getText()));
        }
      }

      /**
       * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
       */
      @Override
      public void focusGained(FocusEvent e) {
        if (propertiesEditionComponent != null) {
          propertiesEditionComponent
              .firePropertiesChanged(new PropertiesEditionEvent(
                  AcceptCallActionPropertiesEditionPartForm.this,
                  null,
                  PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_GAINED,
                  null, null));
        }
      }
    });
    name.addKeyListener(new KeyAdapter() {
      /**
       * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
       * @generated
       */
      @Override
      @SuppressWarnings("synthetic-access")
      public void keyPressed(KeyEvent e) {
        if (e.character == SWT.CR) {
          if (propertiesEditionComponent != null)
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
        }
      }
    });
    EditingUtils.setID(name, UmlViewsRepository.AcceptCallAction.Properties.name);
    EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.AcceptCallAction.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
    createDescription(parent, UmlViewsRepository.AcceptCallAction.Properties.visibility, UmlMessages.AcceptCallActionPropertiesEditionPart_VisibilityLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
      }

    });
    visibility.setID(UmlViewsRepository.AcceptCallAction.Properties.visibility);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.AcceptCallAction.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.clientDependency = new ReferencesTable(getDescription(UmlViewsRepository.AcceptCallAction.Properties.clientDependency, UmlMessages.AcceptCallActionPropertiesEditionPart_ClientDependencyLabel), new ReferencesTableListener	() {
      public void handleAdd() { addClientDependency(); }
      public void handleEdit(EObject element) { editClientDependency(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromClientDependency(element); }
      public void navigateTo(EObject element) { }
    });
    this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.AcceptCallAction.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
    this.clientDependency.createControls(parent, widgetFactory);
    this.clientDependency.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
    clientDependencyData.horizontalSpan = 3;
    this.clientDependency.setLayoutData(clientDependencyData);
    this.clientDependency.disableMove();
    clientDependency.setID(UmlViewsRepository.AcceptCallAction.Properties.clientDependency);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.clientDependency,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    clientDependency.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	
	protected Composite createIsLeafCheckbox(FormToolkit widgetFactory, Composite parent) {
    isLeaf = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.AcceptCallAction.Properties.isLeaf, UmlMessages.AcceptCallActionPropertiesEditionPart_IsLeafLabel), SWT.CHECK);
    isLeaf.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
      }

    });
    GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
    isLeafData.horizontalSpan = 2;
    isLeaf.setLayoutData(isLeafData);
    EditingUtils.setID(isLeaf, UmlViewsRepository.AcceptCallAction.Properties.isLeaf);
    EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.AcceptCallAction.Properties.isLeaf, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createInStructuredNodeFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.AcceptCallAction.Properties.inStructuredNode, UmlMessages.AcceptCallActionPropertiesEditionPart_InStructuredNodeLabel);
    inStructuredNode = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.AcceptCallAction.Properties.inStructuredNode, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(inStructuredNode);
    inStructuredNode.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData inStructuredNodeData = new GridData(GridData.FILL_HORIZONTAL);
    inStructuredNode.setLayoutData(inStructuredNodeData);
    inStructuredNode.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.inStructuredNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getInStructuredNode()));
      }

    });
    inStructuredNode.setID(UmlViewsRepository.AcceptCallAction.Properties.inStructuredNode);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.AcceptCallAction.Properties.inStructuredNode, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createActivityFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.AcceptCallAction.Properties.activity, UmlMessages.AcceptCallActionPropertiesEditionPart_ActivityLabel);
    activity = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.AcceptCallAction.Properties.activity, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(activity);
    activity.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData activityData = new GridData(GridData.FILL_HORIZONTAL);
    activity.setLayoutData(activityData);
    activity.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.activity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getActivity()));
      }

    });
    activity.setID(UmlViewsRepository.AcceptCallAction.Properties.activity);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.AcceptCallAction.Properties.activity, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createOutgoingReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.outgoing = new ReferencesTable(getDescription(UmlViewsRepository.AcceptCallAction.Properties.outgoing, UmlMessages.AcceptCallActionPropertiesEditionPart_OutgoingLabel), new ReferencesTableListener	() {
      public void handleAdd() { addOutgoing(); }
      public void handleEdit(EObject element) { editOutgoing(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveOutgoing(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromOutgoing(element); }
      public void navigateTo(EObject element) { }
    });
    this.outgoing.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.AcceptCallAction.Properties.outgoing, UmlViewsRepository.FORM_KIND));
    this.outgoing.createControls(parent, widgetFactory);
    this.outgoing.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.outgoing, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData outgoingData = new GridData(GridData.FILL_HORIZONTAL);
    outgoingData.horizontalSpan = 3;
    this.outgoing.setLayoutData(outgoingData);
    this.outgoing.disableMove();
    outgoing.setID(UmlViewsRepository.AcceptCallAction.Properties.outgoing);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.outgoing,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.outgoing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    outgoing.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromOutgoing(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.outgoing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	protected Composite createIncomingReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.incoming = new ReferencesTable(getDescription(UmlViewsRepository.AcceptCallAction.Properties.incoming, UmlMessages.AcceptCallActionPropertiesEditionPart_IncomingLabel), new ReferencesTableListener	() {
      public void handleAdd() { addIncoming(); }
      public void handleEdit(EObject element) { editIncoming(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveIncoming(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromIncoming(element); }
      public void navigateTo(EObject element) { }
    });
    this.incoming.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.AcceptCallAction.Properties.incoming, UmlViewsRepository.FORM_KIND));
    this.incoming.createControls(parent, widgetFactory);
    this.incoming.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.incoming, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData incomingData = new GridData(GridData.FILL_HORIZONTAL);
    incomingData.horizontalSpan = 3;
    this.incoming.setLayoutData(incomingData);
    this.incoming.disableMove();
    incoming.setID(UmlViewsRepository.AcceptCallAction.Properties.incoming);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.incoming,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.incoming, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    incoming.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromIncoming(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.incoming, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	protected Composite createInPartitionReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.inPartition = new ReferencesTable(getDescription(UmlViewsRepository.AcceptCallAction.Properties.inPartition, UmlMessages.AcceptCallActionPropertiesEditionPart_InPartitionLabel), new ReferencesTableListener	() {
      public void handleAdd() { addInPartition(); }
      public void handleEdit(EObject element) { editInPartition(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveInPartition(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromInPartition(element); }
      public void navigateTo(EObject element) { }
    });
    this.inPartition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.AcceptCallAction.Properties.inPartition, UmlViewsRepository.FORM_KIND));
    this.inPartition.createControls(parent, widgetFactory);
    this.inPartition.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.inPartition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData inPartitionData = new GridData(GridData.FILL_HORIZONTAL);
    inPartitionData.horizontalSpan = 3;
    this.inPartition.setLayoutData(inPartitionData);
    this.inPartition.disableMove();
    inPartition.setID(UmlViewsRepository.AcceptCallAction.Properties.inPartition);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.inPartition,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    inPartition.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromInPartition(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	protected Composite createInInterruptibleRegionReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.inInterruptibleRegion = new ReferencesTable(getDescription(UmlViewsRepository.AcceptCallAction.Properties.inInterruptibleRegion, UmlMessages.AcceptCallActionPropertiesEditionPart_InInterruptibleRegionLabel), new ReferencesTableListener	() {
      public void handleAdd() { addInInterruptibleRegion(); }
      public void handleEdit(EObject element) { editInInterruptibleRegion(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveInInterruptibleRegion(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromInInterruptibleRegion(element); }
      public void navigateTo(EObject element) { }
    });
    this.inInterruptibleRegion.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.AcceptCallAction.Properties.inInterruptibleRegion, UmlViewsRepository.FORM_KIND));
    this.inInterruptibleRegion.createControls(parent, widgetFactory);
    this.inInterruptibleRegion.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.inInterruptibleRegion, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData inInterruptibleRegionData = new GridData(GridData.FILL_HORIZONTAL);
    inInterruptibleRegionData.horizontalSpan = 3;
    this.inInterruptibleRegion.setLayoutData(inInterruptibleRegionData);
    this.inInterruptibleRegion.disableMove();
    inInterruptibleRegion.setID(UmlViewsRepository.AcceptCallAction.Properties.inInterruptibleRegion);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.inInterruptibleRegion,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.inInterruptibleRegion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    inInterruptibleRegion.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromInInterruptibleRegion(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.inInterruptibleRegion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	protected Composite createRedefinedNodeReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.redefinedNode = new ReferencesTable(getDescription(UmlViewsRepository.AcceptCallAction.Properties.redefinedNode, UmlMessages.AcceptCallActionPropertiesEditionPart_RedefinedNodeLabel), new ReferencesTableListener	() {
      public void handleAdd() { addRedefinedNode(); }
      public void handleEdit(EObject element) { editRedefinedNode(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedNode(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromRedefinedNode(element); }
      public void navigateTo(EObject element) { }
    });
    this.redefinedNode.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.AcceptCallAction.Properties.redefinedNode, UmlViewsRepository.FORM_KIND));
    this.redefinedNode.createControls(parent, widgetFactory);
    this.redefinedNode.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.redefinedNode, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData redefinedNodeData = new GridData(GridData.FILL_HORIZONTAL);
    redefinedNodeData.horizontalSpan = 3;
    this.redefinedNode.setLayoutData(redefinedNodeData);
    this.redefinedNode.disableMove();
    redefinedNode.setID(UmlViewsRepository.AcceptCallAction.Properties.redefinedNode);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.redefinedNode,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.redefinedNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    redefinedNode.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromRedefinedNode(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.redefinedNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	 * @generated
	 */
	
	protected Composite createIsUnmarshallCheckbox(FormToolkit widgetFactory, Composite parent) {
    isUnmarshall = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.AcceptCallAction.Properties.isUnmarshall, UmlMessages.AcceptCallActionPropertiesEditionPart_IsUnmarshallLabel), SWT.CHECK);
    isUnmarshall.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AcceptCallActionPropertiesEditionPartForm.this, UmlViewsRepository.AcceptCallAction.Properties.isUnmarshall, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isUnmarshall.getSelection())));
      }

    });
    GridData isUnmarshallData = new GridData(GridData.FILL_HORIZONTAL);
    isUnmarshallData.horizontalSpan = 2;
    isUnmarshall.setLayoutData(isUnmarshallData);
    EditingUtils.setID(isUnmarshall, UmlViewsRepository.AcceptCallAction.Properties.isUnmarshall);
    EditingUtils.setEEFtype(isUnmarshall, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.AcceptCallAction.Properties.isUnmarshall, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
    return name.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
    if (newValue != null) {
      name.setText(newValue);
    } else {
      name.setText(""); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.name);
    if (readOnly && name.isEnabled()) {
      name.setEnabled(false);
      name.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
    } else if (!readOnly && !name.isEnabled()) {
      name.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
    Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
    visibility.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.visibility);
    if (readOnly && visibility.isEnabled()) {
      visibility.setEnabled(false);
      visibility.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
    } else if (!readOnly && !visibility.isEnabled()) {
      visibility.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
  clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
    clientDependencyFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
    clientDependencyBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
    return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
    return Boolean.valueOf(isLeaf.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
    if (newValue != null) {
      isLeaf.setSelection(newValue.booleanValue());
    } else {
      isLeaf.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.isLeaf);
    if (readOnly && isLeaf.isEnabled()) {
      isLeaf.setEnabled(false);
      isLeaf.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
    } else if (!readOnly && !isLeaf.isEnabled()) {
      isLeaf.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#getInStructuredNode()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#initInStructuredNode(EObjectFlatComboSettings)
	 */
	public void initInStructuredNode(EObjectFlatComboSettings settings) {
		inStructuredNode.setInput(settings);
		if (current != null) {
			inStructuredNode.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.inStructuredNode);
		if (readOnly && inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(false);
			inStructuredNode.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
		} else if (!readOnly && !inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#setInStructuredNode(EObject newValue)
	 * @generated
	 */
	public void setInStructuredNode(EObject newValue) {
    if (newValue != null) {
      inStructuredNode.setSelection(new StructuredSelection(newValue));
    } else {
      inStructuredNode.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.inStructuredNode);
    if (readOnly && inStructuredNode.isEnabled()) {
      inStructuredNode.setEnabled(false);
      inStructuredNode.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
    } else if (!readOnly && !inStructuredNode.isEnabled()) {
      inStructuredNode.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#setInStructuredNodeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInStructuredNodeButtonMode(ButtonsModeEnum newValue) {
		inStructuredNode.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addFilterInStructuredNode(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInStructuredNode(ViewerFilter filter) {
    inStructuredNode.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addBusinessFilterInStructuredNode(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInStructuredNode(ViewerFilter filter) {
    inStructuredNode.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#getActivity()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#initActivity(EObjectFlatComboSettings)
	 */
	public void initActivity(EObjectFlatComboSettings settings) {
		activity.setInput(settings);
		if (current != null) {
			activity.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.activity);
		if (readOnly && activity.isEnabled()) {
			activity.setEnabled(false);
			activity.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
		} else if (!readOnly && !activity.isEnabled()) {
			activity.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#setActivity(EObject newValue)
	 * @generated
	 */
	public void setActivity(EObject newValue) {
    if (newValue != null) {
      activity.setSelection(new StructuredSelection(newValue));
    } else {
      activity.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.activity);
    if (readOnly && activity.isEnabled()) {
      activity.setEnabled(false);
      activity.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
    } else if (!readOnly && !activity.isEnabled()) {
      activity.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#setActivityButtonMode(ButtonsModeEnum newValue)
	 */
	public void setActivityButtonMode(ButtonsModeEnum newValue) {
		activity.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addFilterActivity(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToActivity(ViewerFilter filter) {
    activity.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addBusinessFilterActivity(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToActivity(ViewerFilter filter) {
    activity.addBusinessRuleFilter(filter);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#initOutgoing(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initOutgoing(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		outgoing.setContentProvider(contentProvider);
		outgoing.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.outgoing);
		if (readOnly && outgoing.getTable().isEnabled()) {
			outgoing.setEnabled(false);
			outgoing.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
		} else if (!readOnly && !outgoing.getTable().isEnabled()) {
			outgoing.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#updateOutgoing()
	 * @generated
	 */
	public void updateOutgoing() {
  outgoing.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addFilterOutgoing(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOutgoing(ViewerFilter filter) {
    outgoingFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addBusinessFilterOutgoing(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOutgoing(ViewerFilter filter) {
    outgoingBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#isContainedInOutgoingTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInOutgoingTable(EObject element) {
    return ((ReferencesTableSettings)outgoing.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#initIncoming(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initIncoming(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		incoming.setContentProvider(contentProvider);
		incoming.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.incoming);
		if (readOnly && incoming.getTable().isEnabled()) {
			incoming.setEnabled(false);
			incoming.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
		} else if (!readOnly && !incoming.getTable().isEnabled()) {
			incoming.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#updateIncoming()
	 * @generated
	 */
	public void updateIncoming() {
  incoming.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addFilterIncoming(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToIncoming(ViewerFilter filter) {
    incomingFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addBusinessFilterIncoming(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToIncoming(ViewerFilter filter) {
    incomingBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#isContainedInIncomingTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInIncomingTable(EObject element) {
    return ((ReferencesTableSettings)incoming.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#initInPartition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInPartition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		inPartition.setContentProvider(contentProvider);
		inPartition.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.inPartition);
		if (readOnly && inPartition.getTable().isEnabled()) {
			inPartition.setEnabled(false);
			inPartition.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
		} else if (!readOnly && !inPartition.getTable().isEnabled()) {
			inPartition.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#updateInPartition()
	 * @generated
	 */
	public void updateInPartition() {
  inPartition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addFilterInPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInPartition(ViewerFilter filter) {
    inPartitionFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addBusinessFilterInPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInPartition(ViewerFilter filter) {
    inPartitionBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#isContainedInInPartitionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInInPartitionTable(EObject element) {
    return ((ReferencesTableSettings)inPartition.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#initInInterruptibleRegion(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInInterruptibleRegion(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		inInterruptibleRegion.setContentProvider(contentProvider);
		inInterruptibleRegion.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.inInterruptibleRegion);
		if (readOnly && inInterruptibleRegion.getTable().isEnabled()) {
			inInterruptibleRegion.setEnabled(false);
			inInterruptibleRegion.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
		} else if (!readOnly && !inInterruptibleRegion.getTable().isEnabled()) {
			inInterruptibleRegion.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#updateInInterruptibleRegion()
	 * @generated
	 */
	public void updateInInterruptibleRegion() {
  inInterruptibleRegion.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addFilterInInterruptibleRegion(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInInterruptibleRegion(ViewerFilter filter) {
    inInterruptibleRegionFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addBusinessFilterInInterruptibleRegion(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInInterruptibleRegion(ViewerFilter filter) {
    inInterruptibleRegionBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#isContainedInInInterruptibleRegionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInInInterruptibleRegionTable(EObject element) {
    return ((ReferencesTableSettings)inInterruptibleRegion.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#initRedefinedNode(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedNode(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedNode.setContentProvider(contentProvider);
		redefinedNode.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.redefinedNode);
		if (readOnly && redefinedNode.getTable().isEnabled()) {
			redefinedNode.setEnabled(false);
			redefinedNode.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
		} else if (!readOnly && !redefinedNode.getTable().isEnabled()) {
			redefinedNode.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#updateRedefinedNode()
	 * @generated
	 */
	public void updateRedefinedNode() {
  redefinedNode.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addFilterRedefinedNode(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedNode(ViewerFilter filter) {
    redefinedNodeFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#addBusinessFilterRedefinedNode(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedNode(ViewerFilter filter) {
    redefinedNodeBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#isContainedInRedefinedNodeTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRedefinedNodeTable(EObject element) {
    return ((ReferencesTableSettings)redefinedNode.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#getIsUnmarshall()
	 * @generated
	 */
	public Boolean getIsUnmarshall() {
    return Boolean.valueOf(isUnmarshall.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AcceptCallActionPropertiesEditionPart#setIsUnmarshall(Boolean newValue)
	 * @generated
	 */
	public void setIsUnmarshall(Boolean newValue) {
    if (newValue != null) {
      isUnmarshall.setSelection(newValue.booleanValue());
    } else {
      isUnmarshall.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.AcceptCallAction.Properties.isUnmarshall);
    if (readOnly && isUnmarshall.isEnabled()) {
      isUnmarshall.setEnabled(false);
      isUnmarshall.setToolTipText(UmlMessages.AcceptCallAction_ReadOnly);
    } else if (!readOnly && !isUnmarshall.isEnabled()) {
      isUnmarshall.setEnabled(true);
    }	
    
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.AcceptCallAction_Part_Title;
  }



}
