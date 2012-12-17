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

import org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ReadStructuralFeatureActionPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, ReadStructuralFeatureActionPropertiesEditionPart {

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
	protected EObjectFlatComboViewer structuralFeature;



	/**
	 * For {@link ISection} use only.
	 */
	public ReadStructuralFeatureActionPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ReadStructuralFeatureActionPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence readStructuralFeatureActionStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = readStructuralFeatureActionStep.addStep(UmlViewsRepository.ReadStructuralFeatureAction.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.ReadStructuralFeatureAction.Properties.name);
    propertiesStep.addStep(UmlViewsRepository.ReadStructuralFeatureAction.Properties.visibility);
    propertiesStep.addStep(UmlViewsRepository.ReadStructuralFeatureAction.Properties.clientDependency);
    propertiesStep.addStep(UmlViewsRepository.ReadStructuralFeatureAction.Properties.isLeaf);
    propertiesStep.addStep(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inStructuredNode);
    propertiesStep.addStep(UmlViewsRepository.ReadStructuralFeatureAction.Properties.activity);
    propertiesStep.addStep(UmlViewsRepository.ReadStructuralFeatureAction.Properties.outgoing);
    propertiesStep.addStep(UmlViewsRepository.ReadStructuralFeatureAction.Properties.incoming);
    propertiesStep.addStep(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inPartition);
    propertiesStep.addStep(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inInterruptibleRegion);
    propertiesStep.addStep(UmlViewsRepository.ReadStructuralFeatureAction.Properties.redefinedNode);
    propertiesStep.addStep(UmlViewsRepository.ReadStructuralFeatureAction.Properties.structuralFeature);
    
    
    composer = new PartComposer(readStructuralFeatureActionStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.ReadStructuralFeatureAction.Properties.class) {
          return createPropertiesGroup(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ReadStructuralFeatureAction.Properties.name) {
          return createNameText(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ReadStructuralFeatureAction.Properties.visibility) {
          return createVisibilityEMFComboViewer(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ReadStructuralFeatureAction.Properties.clientDependency) {
          return createClientDependencyReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ReadStructuralFeatureAction.Properties.isLeaf) {
          return createIsLeafCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ReadStructuralFeatureAction.Properties.inStructuredNode) {
          return createInStructuredNodeFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.ReadStructuralFeatureAction.Properties.activity) {
          return createActivityFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.ReadStructuralFeatureAction.Properties.outgoing) {
          return createOutgoingReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ReadStructuralFeatureAction.Properties.incoming) {
          return createIncomingReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ReadStructuralFeatureAction.Properties.inPartition) {
          return createInPartitionReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ReadStructuralFeatureAction.Properties.inInterruptibleRegion) {
          return createInInterruptibleRegionReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ReadStructuralFeatureAction.Properties.redefinedNode) {
          return createRedefinedNodeReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ReadStructuralFeatureAction.Properties.structuralFeature) {
          return createStructuralFeatureFlatComboViewer(parent, widgetFactory);
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
    propertiesSection.setText(UmlMessages.ReadStructuralFeatureActionPropertiesEditionPart_PropertiesGroupLabel);
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
    createDescription(parent, UmlViewsRepository.ReadStructuralFeatureAction.Properties.name, UmlMessages.ReadStructuralFeatureActionPropertiesEditionPart_NameLabel);
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
              ReadStructuralFeatureActionPropertiesEditionPartForm.this,
              UmlViewsRepository.ReadStructuralFeatureAction.Properties.name,
              PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
          propertiesEditionComponent
              .firePropertiesChanged(new PropertiesEditionEvent(
                  ReadStructuralFeatureActionPropertiesEditionPartForm.this,
                  UmlViewsRepository.ReadStructuralFeatureAction.Properties.name,
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
                  ReadStructuralFeatureActionPropertiesEditionPartForm.this,
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
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
        }
      }
    });
    EditingUtils.setID(name, UmlViewsRepository.ReadStructuralFeatureAction.Properties.name);
    EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ReadStructuralFeatureAction.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
    createDescription(parent, UmlViewsRepository.ReadStructuralFeatureAction.Properties.visibility, UmlMessages.ReadStructuralFeatureActionPropertiesEditionPart_VisibilityLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
      }

    });
    visibility.setID(UmlViewsRepository.ReadStructuralFeatureAction.Properties.visibility);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ReadStructuralFeatureAction.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.clientDependency = new ReferencesTable(getDescription(UmlViewsRepository.ReadStructuralFeatureAction.Properties.clientDependency, UmlMessages.ReadStructuralFeatureActionPropertiesEditionPart_ClientDependencyLabel), new ReferencesTableListener	() {
      public void handleAdd() { addClientDependency(); }
      public void handleEdit(EObject element) { editClientDependency(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromClientDependency(element); }
      public void navigateTo(EObject element) { }
    });
    this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ReadStructuralFeatureAction.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
    this.clientDependency.createControls(parent, widgetFactory);
    this.clientDependency.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
    clientDependencyData.horizontalSpan = 3;
    this.clientDependency.setLayoutData(clientDependencyData);
    this.clientDependency.disableMove();
    clientDependency.setID(UmlViewsRepository.ReadStructuralFeatureAction.Properties.clientDependency);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.clientDependency,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    clientDependency.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    isLeaf = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.ReadStructuralFeatureAction.Properties.isLeaf, UmlMessages.ReadStructuralFeatureActionPropertiesEditionPart_IsLeafLabel), SWT.CHECK);
    isLeaf.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
      }

    });
    GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
    isLeafData.horizontalSpan = 2;
    isLeaf.setLayoutData(isLeafData);
    EditingUtils.setID(isLeaf, UmlViewsRepository.ReadStructuralFeatureAction.Properties.isLeaf);
    EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ReadStructuralFeatureAction.Properties.isLeaf, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createInStructuredNodeFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inStructuredNode, UmlMessages.ReadStructuralFeatureActionPropertiesEditionPart_InStructuredNodeLabel);
    inStructuredNode = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inStructuredNode, UmlViewsRepository.FORM_KIND));
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inStructuredNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getInStructuredNode()));
      }

    });
    inStructuredNode.setID(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inStructuredNode);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inStructuredNode, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createActivityFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.ReadStructuralFeatureAction.Properties.activity, UmlMessages.ReadStructuralFeatureActionPropertiesEditionPart_ActivityLabel);
    activity = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ReadStructuralFeatureAction.Properties.activity, UmlViewsRepository.FORM_KIND));
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.activity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getActivity()));
      }

    });
    activity.setID(UmlViewsRepository.ReadStructuralFeatureAction.Properties.activity);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ReadStructuralFeatureAction.Properties.activity, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createOutgoingReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.outgoing = new ReferencesTable(getDescription(UmlViewsRepository.ReadStructuralFeatureAction.Properties.outgoing, UmlMessages.ReadStructuralFeatureActionPropertiesEditionPart_OutgoingLabel), new ReferencesTableListener	() {
      public void handleAdd() { addOutgoing(); }
      public void handleEdit(EObject element) { editOutgoing(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveOutgoing(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromOutgoing(element); }
      public void navigateTo(EObject element) { }
    });
    this.outgoing.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ReadStructuralFeatureAction.Properties.outgoing, UmlViewsRepository.FORM_KIND));
    this.outgoing.createControls(parent, widgetFactory);
    this.outgoing.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.outgoing, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData outgoingData = new GridData(GridData.FILL_HORIZONTAL);
    outgoingData.horizontalSpan = 3;
    this.outgoing.setLayoutData(outgoingData);
    this.outgoing.disableMove();
    outgoing.setID(UmlViewsRepository.ReadStructuralFeatureAction.Properties.outgoing);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.outgoing,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.outgoing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    outgoing.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromOutgoing(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.outgoing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    this.incoming = new ReferencesTable(getDescription(UmlViewsRepository.ReadStructuralFeatureAction.Properties.incoming, UmlMessages.ReadStructuralFeatureActionPropertiesEditionPart_IncomingLabel), new ReferencesTableListener	() {
      public void handleAdd() { addIncoming(); }
      public void handleEdit(EObject element) { editIncoming(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveIncoming(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromIncoming(element); }
      public void navigateTo(EObject element) { }
    });
    this.incoming.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ReadStructuralFeatureAction.Properties.incoming, UmlViewsRepository.FORM_KIND));
    this.incoming.createControls(parent, widgetFactory);
    this.incoming.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.incoming, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData incomingData = new GridData(GridData.FILL_HORIZONTAL);
    incomingData.horizontalSpan = 3;
    this.incoming.setLayoutData(incomingData);
    this.incoming.disableMove();
    incoming.setID(UmlViewsRepository.ReadStructuralFeatureAction.Properties.incoming);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.incoming,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.incoming, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    incoming.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromIncoming(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.incoming, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    this.inPartition = new ReferencesTable(getDescription(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inPartition, UmlMessages.ReadStructuralFeatureActionPropertiesEditionPart_InPartitionLabel), new ReferencesTableListener	() {
      public void handleAdd() { addInPartition(); }
      public void handleEdit(EObject element) { editInPartition(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveInPartition(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromInPartition(element); }
      public void navigateTo(EObject element) { }
    });
    this.inPartition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inPartition, UmlViewsRepository.FORM_KIND));
    this.inPartition.createControls(parent, widgetFactory);
    this.inPartition.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inPartition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData inPartitionData = new GridData(GridData.FILL_HORIZONTAL);
    inPartitionData.horizontalSpan = 3;
    this.inPartition.setLayoutData(inPartitionData);
    this.inPartition.disableMove();
    inPartition.setID(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inPartition);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inPartition,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    inPartition.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromInPartition(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    this.inInterruptibleRegion = new ReferencesTable(getDescription(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inInterruptibleRegion, UmlMessages.ReadStructuralFeatureActionPropertiesEditionPart_InInterruptibleRegionLabel), new ReferencesTableListener	() {
      public void handleAdd() { addInInterruptibleRegion(); }
      public void handleEdit(EObject element) { editInInterruptibleRegion(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveInInterruptibleRegion(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromInInterruptibleRegion(element); }
      public void navigateTo(EObject element) { }
    });
    this.inInterruptibleRegion.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inInterruptibleRegion, UmlViewsRepository.FORM_KIND));
    this.inInterruptibleRegion.createControls(parent, widgetFactory);
    this.inInterruptibleRegion.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inInterruptibleRegion, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData inInterruptibleRegionData = new GridData(GridData.FILL_HORIZONTAL);
    inInterruptibleRegionData.horizontalSpan = 3;
    this.inInterruptibleRegion.setLayoutData(inInterruptibleRegionData);
    this.inInterruptibleRegion.disableMove();
    inInterruptibleRegion.setID(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inInterruptibleRegion);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inInterruptibleRegion,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inInterruptibleRegion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    inInterruptibleRegion.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromInInterruptibleRegion(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.inInterruptibleRegion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    this.redefinedNode = new ReferencesTable(getDescription(UmlViewsRepository.ReadStructuralFeatureAction.Properties.redefinedNode, UmlMessages.ReadStructuralFeatureActionPropertiesEditionPart_RedefinedNodeLabel), new ReferencesTableListener	() {
      public void handleAdd() { addRedefinedNode(); }
      public void handleEdit(EObject element) { editRedefinedNode(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedNode(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromRedefinedNode(element); }
      public void navigateTo(EObject element) { }
    });
    this.redefinedNode.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ReadStructuralFeatureAction.Properties.redefinedNode, UmlViewsRepository.FORM_KIND));
    this.redefinedNode.createControls(parent, widgetFactory);
    this.redefinedNode.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.redefinedNode, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData redefinedNodeData = new GridData(GridData.FILL_HORIZONTAL);
    redefinedNodeData.horizontalSpan = 3;
    this.redefinedNode.setLayoutData(redefinedNodeData);
    this.redefinedNode.disableMove();
    redefinedNode.setID(UmlViewsRepository.ReadStructuralFeatureAction.Properties.redefinedNode);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.redefinedNode,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.redefinedNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    redefinedNode.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromRedefinedNode(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.redefinedNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createStructuralFeatureFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.ReadStructuralFeatureAction.Properties.structuralFeature, UmlMessages.ReadStructuralFeatureActionPropertiesEditionPart_StructuralFeatureLabel);
    structuralFeature = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ReadStructuralFeatureAction.Properties.structuralFeature, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(structuralFeature);
    structuralFeature.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData structuralFeatureData = new GridData(GridData.FILL_HORIZONTAL);
    structuralFeature.setLayoutData(structuralFeatureData);
    structuralFeature.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ReadStructuralFeatureActionPropertiesEditionPartForm.this, UmlViewsRepository.ReadStructuralFeatureAction.Properties.structuralFeature, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getStructuralFeature()));
      }

    });
    structuralFeature.setID(UmlViewsRepository.ReadStructuralFeatureAction.Properties.structuralFeature);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ReadStructuralFeatureAction.Properties.structuralFeature, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
    return name.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
    if (newValue != null) {
      name.setText(newValue);
    } else {
      name.setText(""); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.name);
    if (readOnly && name.isEnabled()) {
      name.setEnabled(false);
      name.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
    } else if (!readOnly && !name.isEnabled()) {
      name.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
    Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
    visibility.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.visibility);
    if (readOnly && visibility.isEnabled()) {
      visibility.setEnabled(false);
      visibility.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
    } else if (!readOnly && !visibility.isEnabled()) {
      visibility.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
  clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
    clientDependencyFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
    clientDependencyBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
    return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
    return Boolean.valueOf(isLeaf.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
    if (newValue != null) {
      isLeaf.setSelection(newValue.booleanValue());
    } else {
      isLeaf.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.isLeaf);
    if (readOnly && isLeaf.isEnabled()) {
      isLeaf.setEnabled(false);
      isLeaf.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
    } else if (!readOnly && !isLeaf.isEnabled()) {
      isLeaf.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#getInStructuredNode()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#initInStructuredNode(EObjectFlatComboSettings)
	 */
	public void initInStructuredNode(EObjectFlatComboSettings settings) {
		inStructuredNode.setInput(settings);
		if (current != null) {
			inStructuredNode.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inStructuredNode);
		if (readOnly && inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(false);
			inStructuredNode.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
		} else if (!readOnly && !inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#setInStructuredNode(EObject newValue)
	 * @generated
	 */
	public void setInStructuredNode(EObject newValue) {
    if (newValue != null) {
      inStructuredNode.setSelection(new StructuredSelection(newValue));
    } else {
      inStructuredNode.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inStructuredNode);
    if (readOnly && inStructuredNode.isEnabled()) {
      inStructuredNode.setEnabled(false);
      inStructuredNode.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
    } else if (!readOnly && !inStructuredNode.isEnabled()) {
      inStructuredNode.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#setInStructuredNodeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInStructuredNodeButtonMode(ButtonsModeEnum newValue) {
		inStructuredNode.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addFilterInStructuredNode(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInStructuredNode(ViewerFilter filter) {
    inStructuredNode.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addBusinessFilterInStructuredNode(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInStructuredNode(ViewerFilter filter) {
    inStructuredNode.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#getActivity()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#initActivity(EObjectFlatComboSettings)
	 */
	public void initActivity(EObjectFlatComboSettings settings) {
		activity.setInput(settings);
		if (current != null) {
			activity.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.activity);
		if (readOnly && activity.isEnabled()) {
			activity.setEnabled(false);
			activity.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
		} else if (!readOnly && !activity.isEnabled()) {
			activity.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#setActivity(EObject newValue)
	 * @generated
	 */
	public void setActivity(EObject newValue) {
    if (newValue != null) {
      activity.setSelection(new StructuredSelection(newValue));
    } else {
      activity.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.activity);
    if (readOnly && activity.isEnabled()) {
      activity.setEnabled(false);
      activity.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
    } else if (!readOnly && !activity.isEnabled()) {
      activity.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#setActivityButtonMode(ButtonsModeEnum newValue)
	 */
	public void setActivityButtonMode(ButtonsModeEnum newValue) {
		activity.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addFilterActivity(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToActivity(ViewerFilter filter) {
    activity.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addBusinessFilterActivity(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToActivity(ViewerFilter filter) {
    activity.addBusinessRuleFilter(filter);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#initOutgoing(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initOutgoing(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		outgoing.setContentProvider(contentProvider);
		outgoing.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.outgoing);
		if (readOnly && outgoing.getTable().isEnabled()) {
			outgoing.setEnabled(false);
			outgoing.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
		} else if (!readOnly && !outgoing.getTable().isEnabled()) {
			outgoing.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#updateOutgoing()
	 * @generated
	 */
	public void updateOutgoing() {
  outgoing.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addFilterOutgoing(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOutgoing(ViewerFilter filter) {
    outgoingFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addBusinessFilterOutgoing(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOutgoing(ViewerFilter filter) {
    outgoingBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#isContainedInOutgoingTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInOutgoingTable(EObject element) {
    return ((ReferencesTableSettings)outgoing.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#initIncoming(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initIncoming(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		incoming.setContentProvider(contentProvider);
		incoming.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.incoming);
		if (readOnly && incoming.getTable().isEnabled()) {
			incoming.setEnabled(false);
			incoming.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
		} else if (!readOnly && !incoming.getTable().isEnabled()) {
			incoming.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#updateIncoming()
	 * @generated
	 */
	public void updateIncoming() {
  incoming.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addFilterIncoming(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToIncoming(ViewerFilter filter) {
    incomingFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addBusinessFilterIncoming(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToIncoming(ViewerFilter filter) {
    incomingBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#isContainedInIncomingTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInIncomingTable(EObject element) {
    return ((ReferencesTableSettings)incoming.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#initInPartition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInPartition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		inPartition.setContentProvider(contentProvider);
		inPartition.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inPartition);
		if (readOnly && inPartition.getTable().isEnabled()) {
			inPartition.setEnabled(false);
			inPartition.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
		} else if (!readOnly && !inPartition.getTable().isEnabled()) {
			inPartition.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#updateInPartition()
	 * @generated
	 */
	public void updateInPartition() {
  inPartition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addFilterInPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInPartition(ViewerFilter filter) {
    inPartitionFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addBusinessFilterInPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInPartition(ViewerFilter filter) {
    inPartitionBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#isContainedInInPartitionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInInPartitionTable(EObject element) {
    return ((ReferencesTableSettings)inPartition.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#initInInterruptibleRegion(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInInterruptibleRegion(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		inInterruptibleRegion.setContentProvider(contentProvider);
		inInterruptibleRegion.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.inInterruptibleRegion);
		if (readOnly && inInterruptibleRegion.getTable().isEnabled()) {
			inInterruptibleRegion.setEnabled(false);
			inInterruptibleRegion.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
		} else if (!readOnly && !inInterruptibleRegion.getTable().isEnabled()) {
			inInterruptibleRegion.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#updateInInterruptibleRegion()
	 * @generated
	 */
	public void updateInInterruptibleRegion() {
  inInterruptibleRegion.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addFilterInInterruptibleRegion(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInInterruptibleRegion(ViewerFilter filter) {
    inInterruptibleRegionFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addBusinessFilterInInterruptibleRegion(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInInterruptibleRegion(ViewerFilter filter) {
    inInterruptibleRegionBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#isContainedInInInterruptibleRegionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInInInterruptibleRegionTable(EObject element) {
    return ((ReferencesTableSettings)inInterruptibleRegion.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#initRedefinedNode(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedNode(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedNode.setContentProvider(contentProvider);
		redefinedNode.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.redefinedNode);
		if (readOnly && redefinedNode.getTable().isEnabled()) {
			redefinedNode.setEnabled(false);
			redefinedNode.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
		} else if (!readOnly && !redefinedNode.getTable().isEnabled()) {
			redefinedNode.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#updateRedefinedNode()
	 * @generated
	 */
	public void updateRedefinedNode() {
  redefinedNode.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addFilterRedefinedNode(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedNode(ViewerFilter filter) {
    redefinedNodeFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addBusinessFilterRedefinedNode(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedNode(ViewerFilter filter) {
    redefinedNodeBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#isContainedInRedefinedNodeTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRedefinedNodeTable(EObject element) {
    return ((ReferencesTableSettings)redefinedNode.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#getStructuralFeature()
	 * @generated
	 */
	public EObject getStructuralFeature() {
    if (structuralFeature.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) structuralFeature.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#initStructuralFeature(EObjectFlatComboSettings)
	 */
	public void initStructuralFeature(EObjectFlatComboSettings settings) {
		structuralFeature.setInput(settings);
		if (current != null) {
			structuralFeature.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.structuralFeature);
		if (readOnly && structuralFeature.isEnabled()) {
			structuralFeature.setEnabled(false);
			structuralFeature.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
		} else if (!readOnly && !structuralFeature.isEnabled()) {
			structuralFeature.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#setStructuralFeature(EObject newValue)
	 * @generated
	 */
	public void setStructuralFeature(EObject newValue) {
    if (newValue != null) {
      structuralFeature.setSelection(new StructuredSelection(newValue));
    } else {
      structuralFeature.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ReadStructuralFeatureAction.Properties.structuralFeature);
    if (readOnly && structuralFeature.isEnabled()) {
      structuralFeature.setEnabled(false);
      structuralFeature.setToolTipText(UmlMessages.ReadStructuralFeatureAction_ReadOnly);
    } else if (!readOnly && !structuralFeature.isEnabled()) {
      structuralFeature.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#setStructuralFeatureButtonMode(ButtonsModeEnum newValue)
	 */
	public void setStructuralFeatureButtonMode(ButtonsModeEnum newValue) {
		structuralFeature.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addFilterStructuralFeature(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToStructuralFeature(ViewerFilter filter) {
    structuralFeature.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ReadStructuralFeatureActionPropertiesEditionPart#addBusinessFilterStructuralFeature(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToStructuralFeature(ViewerFilter filter) {
    structuralFeature.addBusinessRuleFilter(filter);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.ReadStructuralFeatureAction_Part_Title;
  }



}
