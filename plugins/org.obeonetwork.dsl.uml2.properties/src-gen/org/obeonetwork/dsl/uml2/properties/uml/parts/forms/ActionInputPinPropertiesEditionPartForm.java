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

import org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ActionInputPinPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, ActionInputPinPropertiesEditionPart {

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
	protected EObjectFlatComboViewer type;
	protected EMFComboViewer ordering;
	protected Button isControlType;
	protected ReferencesTable inState;
	protected List<ViewerFilter> inStateBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> inStateFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer selection;
	protected Button isOrdered;
	protected Button isUnique;
	protected Button isControl;



	/**
	 * For {@link ISection} use only.
	 */
	public ActionInputPinPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ActionInputPinPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence actionInputPinStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = actionInputPinStep.addStep(UmlViewsRepository.ActionInputPin.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.name);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.visibility);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.clientDependency);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.isLeaf);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.inStructuredNode);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.activity);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.outgoing);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.incoming);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.inPartition);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.inInterruptibleRegion);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.redefinedNode);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.type);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.ordering);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.isControlType);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.inState);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.selection);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.isOrdered);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.isUnique);
    propertiesStep.addStep(UmlViewsRepository.ActionInputPin.Properties.isControl);
    
    
    composer = new PartComposer(actionInputPinStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.ActionInputPin.Properties.class) {
          return createPropertiesGroup(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.name) {
          return createNameText(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.visibility) {
          return createVisibilityEMFComboViewer(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.clientDependency) {
          return createClientDependencyReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.isLeaf) {
          return createIsLeafCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.inStructuredNode) {
          return createInStructuredNodeFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.activity) {
          return createActivityFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.outgoing) {
          return createOutgoingReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.incoming) {
          return createIncomingReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.inPartition) {
          return createInPartitionReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.inInterruptibleRegion) {
          return createInInterruptibleRegionReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.redefinedNode) {
          return createRedefinedNodeReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.type) {
          return createTypeFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.ordering) {
          return createOrderingEMFComboViewer(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.isControlType) {
          return createIsControlTypeCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.inState) {
          return createInStateReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.selection) {
          return createSelectionFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.isOrdered) {
          return createIsOrderedCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.isUnique) {
          return createIsUniqueCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ActionInputPin.Properties.isControl) {
          return createIsControlCheckbox(widgetFactory, parent);
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
    propertiesSection.setText(UmlMessages.ActionInputPinPropertiesEditionPart_PropertiesGroupLabel);
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
    createDescription(parent, UmlViewsRepository.ActionInputPin.Properties.name, UmlMessages.ActionInputPinPropertiesEditionPart_NameLabel);
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
              ActionInputPinPropertiesEditionPartForm.this,
              UmlViewsRepository.ActionInputPin.Properties.name,
              PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
          propertiesEditionComponent
              .firePropertiesChanged(new PropertiesEditionEvent(
                  ActionInputPinPropertiesEditionPartForm.this,
                  UmlViewsRepository.ActionInputPin.Properties.name,
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
                  ActionInputPinPropertiesEditionPartForm.this,
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
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
        }
      }
    });
    EditingUtils.setID(name, UmlViewsRepository.ActionInputPin.Properties.name);
    EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
    createDescription(parent, UmlViewsRepository.ActionInputPin.Properties.visibility, UmlMessages.ActionInputPinPropertiesEditionPart_VisibilityLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
      }

    });
    visibility.setID(UmlViewsRepository.ActionInputPin.Properties.visibility);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.clientDependency = new ReferencesTable(getDescription(UmlViewsRepository.ActionInputPin.Properties.clientDependency, UmlMessages.ActionInputPinPropertiesEditionPart_ClientDependencyLabel), new ReferencesTableListener	() {
      public void handleAdd() { addClientDependency(); }
      public void handleEdit(EObject element) { editClientDependency(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromClientDependency(element); }
      public void navigateTo(EObject element) { }
    });
    this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
    this.clientDependency.createControls(parent, widgetFactory);
    this.clientDependency.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
    clientDependencyData.horizontalSpan = 3;
    this.clientDependency.setLayoutData(clientDependencyData);
    this.clientDependency.disableMove();
    clientDependency.setID(UmlViewsRepository.ActionInputPin.Properties.clientDependency);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.clientDependency,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    clientDependency.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    isLeaf = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.ActionInputPin.Properties.isLeaf, UmlMessages.ActionInputPinPropertiesEditionPart_IsLeafLabel), SWT.CHECK);
    isLeaf.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
      }

    });
    GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
    isLeafData.horizontalSpan = 2;
    isLeaf.setLayoutData(isLeafData);
    EditingUtils.setID(isLeaf, UmlViewsRepository.ActionInputPin.Properties.isLeaf);
    EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.isLeaf, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createInStructuredNodeFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.ActionInputPin.Properties.inStructuredNode, UmlMessages.ActionInputPinPropertiesEditionPart_InStructuredNodeLabel);
    inStructuredNode = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ActionInputPin.Properties.inStructuredNode, UmlViewsRepository.FORM_KIND));
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.inStructuredNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getInStructuredNode()));
      }

    });
    inStructuredNode.setID(UmlViewsRepository.ActionInputPin.Properties.inStructuredNode);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.inStructuredNode, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createActivityFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.ActionInputPin.Properties.activity, UmlMessages.ActionInputPinPropertiesEditionPart_ActivityLabel);
    activity = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ActionInputPin.Properties.activity, UmlViewsRepository.FORM_KIND));
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.activity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getActivity()));
      }

    });
    activity.setID(UmlViewsRepository.ActionInputPin.Properties.activity);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.activity, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createOutgoingReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.outgoing = new ReferencesTable(getDescription(UmlViewsRepository.ActionInputPin.Properties.outgoing, UmlMessages.ActionInputPinPropertiesEditionPart_OutgoingLabel), new ReferencesTableListener	() {
      public void handleAdd() { addOutgoing(); }
      public void handleEdit(EObject element) { editOutgoing(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveOutgoing(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromOutgoing(element); }
      public void navigateTo(EObject element) { }
    });
    this.outgoing.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.outgoing, UmlViewsRepository.FORM_KIND));
    this.outgoing.createControls(parent, widgetFactory);
    this.outgoing.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.outgoing, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData outgoingData = new GridData(GridData.FILL_HORIZONTAL);
    outgoingData.horizontalSpan = 3;
    this.outgoing.setLayoutData(outgoingData);
    this.outgoing.disableMove();
    outgoing.setID(UmlViewsRepository.ActionInputPin.Properties.outgoing);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.outgoing,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.outgoing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    outgoing.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromOutgoing(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.outgoing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    this.incoming = new ReferencesTable(getDescription(UmlViewsRepository.ActionInputPin.Properties.incoming, UmlMessages.ActionInputPinPropertiesEditionPart_IncomingLabel), new ReferencesTableListener	() {
      public void handleAdd() { addIncoming(); }
      public void handleEdit(EObject element) { editIncoming(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveIncoming(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromIncoming(element); }
      public void navigateTo(EObject element) { }
    });
    this.incoming.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.incoming, UmlViewsRepository.FORM_KIND));
    this.incoming.createControls(parent, widgetFactory);
    this.incoming.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.incoming, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData incomingData = new GridData(GridData.FILL_HORIZONTAL);
    incomingData.horizontalSpan = 3;
    this.incoming.setLayoutData(incomingData);
    this.incoming.disableMove();
    incoming.setID(UmlViewsRepository.ActionInputPin.Properties.incoming);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.incoming,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.incoming, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    incoming.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromIncoming(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.incoming, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    this.inPartition = new ReferencesTable(getDescription(UmlViewsRepository.ActionInputPin.Properties.inPartition, UmlMessages.ActionInputPinPropertiesEditionPart_InPartitionLabel), new ReferencesTableListener	() {
      public void handleAdd() { addInPartition(); }
      public void handleEdit(EObject element) { editInPartition(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveInPartition(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromInPartition(element); }
      public void navigateTo(EObject element) { }
    });
    this.inPartition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.inPartition, UmlViewsRepository.FORM_KIND));
    this.inPartition.createControls(parent, widgetFactory);
    this.inPartition.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.inPartition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData inPartitionData = new GridData(GridData.FILL_HORIZONTAL);
    inPartitionData.horizontalSpan = 3;
    this.inPartition.setLayoutData(inPartitionData);
    this.inPartition.disableMove();
    inPartition.setID(UmlViewsRepository.ActionInputPin.Properties.inPartition);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.inPartition,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    inPartition.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromInPartition(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    this.inInterruptibleRegion = new ReferencesTable(getDescription(UmlViewsRepository.ActionInputPin.Properties.inInterruptibleRegion, UmlMessages.ActionInputPinPropertiesEditionPart_InInterruptibleRegionLabel), new ReferencesTableListener	() {
      public void handleAdd() { addInInterruptibleRegion(); }
      public void handleEdit(EObject element) { editInInterruptibleRegion(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveInInterruptibleRegion(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromInInterruptibleRegion(element); }
      public void navigateTo(EObject element) { }
    });
    this.inInterruptibleRegion.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.inInterruptibleRegion, UmlViewsRepository.FORM_KIND));
    this.inInterruptibleRegion.createControls(parent, widgetFactory);
    this.inInterruptibleRegion.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.inInterruptibleRegion, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData inInterruptibleRegionData = new GridData(GridData.FILL_HORIZONTAL);
    inInterruptibleRegionData.horizontalSpan = 3;
    this.inInterruptibleRegion.setLayoutData(inInterruptibleRegionData);
    this.inInterruptibleRegion.disableMove();
    inInterruptibleRegion.setID(UmlViewsRepository.ActionInputPin.Properties.inInterruptibleRegion);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.inInterruptibleRegion,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.inInterruptibleRegion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    inInterruptibleRegion.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromInInterruptibleRegion(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.inInterruptibleRegion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    this.redefinedNode = new ReferencesTable(getDescription(UmlViewsRepository.ActionInputPin.Properties.redefinedNode, UmlMessages.ActionInputPinPropertiesEditionPart_RedefinedNodeLabel), new ReferencesTableListener	() {
      public void handleAdd() { addRedefinedNode(); }
      public void handleEdit(EObject element) { editRedefinedNode(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedNode(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromRedefinedNode(element); }
      public void navigateTo(EObject element) { }
    });
    this.redefinedNode.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.redefinedNode, UmlViewsRepository.FORM_KIND));
    this.redefinedNode.createControls(parent, widgetFactory);
    this.redefinedNode.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.redefinedNode, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData redefinedNodeData = new GridData(GridData.FILL_HORIZONTAL);
    redefinedNodeData.horizontalSpan = 3;
    this.redefinedNode.setLayoutData(redefinedNodeData);
    this.redefinedNode.disableMove();
    redefinedNode.setID(UmlViewsRepository.ActionInputPin.Properties.redefinedNode);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.redefinedNode,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.redefinedNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    redefinedNode.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromRedefinedNode(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.redefinedNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	protected Composite createTypeFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.ActionInputPin.Properties.type, UmlMessages.ActionInputPinPropertiesEditionPart_TypeLabel);
    type = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ActionInputPin.Properties.type, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(type);
    type.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData typeData = new GridData(GridData.FILL_HORIZONTAL);
    type.setLayoutData(typeData);
    type.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.type, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getType()));
      }

    });
    type.setID(UmlViewsRepository.ActionInputPin.Properties.type);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.type, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createOrderingEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
    createDescription(parent, UmlViewsRepository.ActionInputPin.Properties.ordering, UmlMessages.ActionInputPinPropertiesEditionPart_OrderingLabel);
    ordering = new EMFComboViewer(parent);
    ordering.setContentProvider(new ArrayContentProvider());
    ordering.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
    GridData orderingData = new GridData(GridData.FILL_HORIZONTAL);
    ordering.getCombo().setLayoutData(orderingData);
    ordering.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       * 	@generated
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.ordering, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getOrdering()));
      }

    });
    ordering.setID(UmlViewsRepository.ActionInputPin.Properties.ordering);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.ordering, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createIsControlTypeCheckbox(FormToolkit widgetFactory, Composite parent) {
    isControlType = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.ActionInputPin.Properties.isControlType, UmlMessages.ActionInputPinPropertiesEditionPart_IsControlTypeLabel), SWT.CHECK);
    isControlType.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.isControlType, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isControlType.getSelection())));
      }

    });
    GridData isControlTypeData = new GridData(GridData.FILL_HORIZONTAL);
    isControlTypeData.horizontalSpan = 2;
    isControlType.setLayoutData(isControlTypeData);
    EditingUtils.setID(isControlType, UmlViewsRepository.ActionInputPin.Properties.isControlType);
    EditingUtils.setEEFtype(isControlType, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.isControlType, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createInStateReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.inState = new ReferencesTable(getDescription(UmlViewsRepository.ActionInputPin.Properties.inState, UmlMessages.ActionInputPinPropertiesEditionPart_InStateLabel), new ReferencesTableListener	() {
      public void handleAdd() { addInState(); }
      public void handleEdit(EObject element) { editInState(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveInState(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromInState(element); }
      public void navigateTo(EObject element) { }
    });
    this.inState.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.inState, UmlViewsRepository.FORM_KIND));
    this.inState.createControls(parent, widgetFactory);
    this.inState.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.inState, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData inStateData = new GridData(GridData.FILL_HORIZONTAL);
    inStateData.horizontalSpan = 3;
    this.inState.setLayoutData(inStateData);
    this.inState.disableMove();
    inState.setID(UmlViewsRepository.ActionInputPin.Properties.inState);
    inState.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addInState() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(inState.getInput(), inStateFilters, inStateBusinessFilters,
    "inState", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.inState,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        inState.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveInState(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.inState, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    inState.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromInState(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.inState, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    inState.refresh();
  }

	/**
	 * @generated
	 */
	protected void editInState(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        inState.refresh();
      }
    }
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createSelectionFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.ActionInputPin.Properties.selection, UmlMessages.ActionInputPinPropertiesEditionPart_SelectionLabel);
    selection = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ActionInputPin.Properties.selection, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(selection);
    selection.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData selectionData = new GridData(GridData.FILL_HORIZONTAL);
    selection.setLayoutData(selectionData);
    selection.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.selection, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getSelection()));
      }

    });
    selection.setID(UmlViewsRepository.ActionInputPin.Properties.selection);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.selection, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createIsOrderedCheckbox(FormToolkit widgetFactory, Composite parent) {
    isOrdered = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.ActionInputPin.Properties.isOrdered, UmlMessages.ActionInputPinPropertiesEditionPart_IsOrderedLabel), SWT.CHECK);
    isOrdered.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.isOrdered, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isOrdered.getSelection())));
      }

    });
    GridData isOrderedData = new GridData(GridData.FILL_HORIZONTAL);
    isOrderedData.horizontalSpan = 2;
    isOrdered.setLayoutData(isOrderedData);
    EditingUtils.setID(isOrdered, UmlViewsRepository.ActionInputPin.Properties.isOrdered);
    EditingUtils.setEEFtype(isOrdered, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.isOrdered, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createIsUniqueCheckbox(FormToolkit widgetFactory, Composite parent) {
    isUnique = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.ActionInputPin.Properties.isUnique, UmlMessages.ActionInputPinPropertiesEditionPart_IsUniqueLabel), SWT.CHECK);
    isUnique.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.isUnique, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isUnique.getSelection())));
      }

    });
    GridData isUniqueData = new GridData(GridData.FILL_HORIZONTAL);
    isUniqueData.horizontalSpan = 2;
    isUnique.setLayoutData(isUniqueData);
    EditingUtils.setID(isUnique, UmlViewsRepository.ActionInputPin.Properties.isUnique);
    EditingUtils.setEEFtype(isUnique, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.isUnique, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createIsControlCheckbox(FormToolkit widgetFactory, Composite parent) {
    isControl = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.ActionInputPin.Properties.isControl, UmlMessages.ActionInputPinPropertiesEditionPart_IsControlLabel), SWT.CHECK);
    isControl.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActionInputPinPropertiesEditionPartForm.this, UmlViewsRepository.ActionInputPin.Properties.isControl, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isControl.getSelection())));
      }

    });
    GridData isControlData = new GridData(GridData.FILL_HORIZONTAL);
    isControlData.horizontalSpan = 2;
    isControl.setLayoutData(isControlData);
    EditingUtils.setID(isControl, UmlViewsRepository.ActionInputPin.Properties.isControl);
    EditingUtils.setEEFtype(isControl, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActionInputPin.Properties.isControl, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
    return name.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
    if (newValue != null) {
      name.setText(newValue);
    } else {
      name.setText(""); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.name);
    if (readOnly && name.isEnabled()) {
      name.setEnabled(false);
      name.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
    } else if (!readOnly && !name.isEnabled()) {
      name.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
    Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
    visibility.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.visibility);
    if (readOnly && visibility.isEnabled()) {
      visibility.setEnabled(false);
      visibility.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
    } else if (!readOnly && !visibility.isEnabled()) {
      visibility.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
  clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
    clientDependencyFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
    clientDependencyBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
    return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
    return Boolean.valueOf(isLeaf.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
    if (newValue != null) {
      isLeaf.setSelection(newValue.booleanValue());
    } else {
      isLeaf.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.isLeaf);
    if (readOnly && isLeaf.isEnabled()) {
      isLeaf.setEnabled(false);
      isLeaf.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
    } else if (!readOnly && !isLeaf.isEnabled()) {
      isLeaf.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#getInStructuredNode()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#initInStructuredNode(EObjectFlatComboSettings)
	 */
	public void initInStructuredNode(EObjectFlatComboSettings settings) {
		inStructuredNode.setInput(settings);
		if (current != null) {
			inStructuredNode.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.inStructuredNode);
		if (readOnly && inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(false);
			inStructuredNode.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
		} else if (!readOnly && !inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setInStructuredNode(EObject newValue)
	 * @generated
	 */
	public void setInStructuredNode(EObject newValue) {
    if (newValue != null) {
      inStructuredNode.setSelection(new StructuredSelection(newValue));
    } else {
      inStructuredNode.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.inStructuredNode);
    if (readOnly && inStructuredNode.isEnabled()) {
      inStructuredNode.setEnabled(false);
      inStructuredNode.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
    } else if (!readOnly && !inStructuredNode.isEnabled()) {
      inStructuredNode.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setInStructuredNodeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInStructuredNodeButtonMode(ButtonsModeEnum newValue) {
		inStructuredNode.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addFilterInStructuredNode(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInStructuredNode(ViewerFilter filter) {
    inStructuredNode.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addBusinessFilterInStructuredNode(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInStructuredNode(ViewerFilter filter) {
    inStructuredNode.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#getActivity()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#initActivity(EObjectFlatComboSettings)
	 */
	public void initActivity(EObjectFlatComboSettings settings) {
		activity.setInput(settings);
		if (current != null) {
			activity.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.activity);
		if (readOnly && activity.isEnabled()) {
			activity.setEnabled(false);
			activity.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
		} else if (!readOnly && !activity.isEnabled()) {
			activity.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setActivity(EObject newValue)
	 * @generated
	 */
	public void setActivity(EObject newValue) {
    if (newValue != null) {
      activity.setSelection(new StructuredSelection(newValue));
    } else {
      activity.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.activity);
    if (readOnly && activity.isEnabled()) {
      activity.setEnabled(false);
      activity.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
    } else if (!readOnly && !activity.isEnabled()) {
      activity.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setActivityButtonMode(ButtonsModeEnum newValue)
	 */
	public void setActivityButtonMode(ButtonsModeEnum newValue) {
		activity.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addFilterActivity(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToActivity(ViewerFilter filter) {
    activity.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addBusinessFilterActivity(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToActivity(ViewerFilter filter) {
    activity.addBusinessRuleFilter(filter);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#initOutgoing(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initOutgoing(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		outgoing.setContentProvider(contentProvider);
		outgoing.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.outgoing);
		if (readOnly && outgoing.getTable().isEnabled()) {
			outgoing.setEnabled(false);
			outgoing.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
		} else if (!readOnly && !outgoing.getTable().isEnabled()) {
			outgoing.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#updateOutgoing()
	 * @generated
	 */
	public void updateOutgoing() {
  outgoing.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addFilterOutgoing(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOutgoing(ViewerFilter filter) {
    outgoingFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addBusinessFilterOutgoing(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOutgoing(ViewerFilter filter) {
    outgoingBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#isContainedInOutgoingTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInOutgoingTable(EObject element) {
    return ((ReferencesTableSettings)outgoing.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#initIncoming(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initIncoming(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		incoming.setContentProvider(contentProvider);
		incoming.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.incoming);
		if (readOnly && incoming.getTable().isEnabled()) {
			incoming.setEnabled(false);
			incoming.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
		} else if (!readOnly && !incoming.getTable().isEnabled()) {
			incoming.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#updateIncoming()
	 * @generated
	 */
	public void updateIncoming() {
  incoming.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addFilterIncoming(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToIncoming(ViewerFilter filter) {
    incomingFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addBusinessFilterIncoming(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToIncoming(ViewerFilter filter) {
    incomingBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#isContainedInIncomingTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInIncomingTable(EObject element) {
    return ((ReferencesTableSettings)incoming.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#initInPartition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInPartition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		inPartition.setContentProvider(contentProvider);
		inPartition.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.inPartition);
		if (readOnly && inPartition.getTable().isEnabled()) {
			inPartition.setEnabled(false);
			inPartition.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
		} else if (!readOnly && !inPartition.getTable().isEnabled()) {
			inPartition.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#updateInPartition()
	 * @generated
	 */
	public void updateInPartition() {
  inPartition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addFilterInPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInPartition(ViewerFilter filter) {
    inPartitionFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addBusinessFilterInPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInPartition(ViewerFilter filter) {
    inPartitionBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#isContainedInInPartitionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInInPartitionTable(EObject element) {
    return ((ReferencesTableSettings)inPartition.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#initInInterruptibleRegion(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInInterruptibleRegion(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		inInterruptibleRegion.setContentProvider(contentProvider);
		inInterruptibleRegion.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.inInterruptibleRegion);
		if (readOnly && inInterruptibleRegion.getTable().isEnabled()) {
			inInterruptibleRegion.setEnabled(false);
			inInterruptibleRegion.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
		} else if (!readOnly && !inInterruptibleRegion.getTable().isEnabled()) {
			inInterruptibleRegion.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#updateInInterruptibleRegion()
	 * @generated
	 */
	public void updateInInterruptibleRegion() {
  inInterruptibleRegion.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addFilterInInterruptibleRegion(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInInterruptibleRegion(ViewerFilter filter) {
    inInterruptibleRegionFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addBusinessFilterInInterruptibleRegion(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInInterruptibleRegion(ViewerFilter filter) {
    inInterruptibleRegionBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#isContainedInInInterruptibleRegionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInInInterruptibleRegionTable(EObject element) {
    return ((ReferencesTableSettings)inInterruptibleRegion.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#initRedefinedNode(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedNode(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedNode.setContentProvider(contentProvider);
		redefinedNode.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.redefinedNode);
		if (readOnly && redefinedNode.getTable().isEnabled()) {
			redefinedNode.setEnabled(false);
			redefinedNode.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
		} else if (!readOnly && !redefinedNode.getTable().isEnabled()) {
			redefinedNode.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#updateRedefinedNode()
	 * @generated
	 */
	public void updateRedefinedNode() {
  redefinedNode.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addFilterRedefinedNode(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedNode(ViewerFilter filter) {
    redefinedNodeFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addBusinessFilterRedefinedNode(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedNode(ViewerFilter filter) {
    redefinedNodeBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#isContainedInRedefinedNodeTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRedefinedNodeTable(EObject element) {
    return ((ReferencesTableSettings)redefinedNode.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#getType()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#initType(EObjectFlatComboSettings)
	 */
	public void initType(EObjectFlatComboSettings settings) {
		type.setInput(settings);
		if (current != null) {
			type.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.type);
		if (readOnly && type.isEnabled()) {
			type.setEnabled(false);
			type.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
		} else if (!readOnly && !type.isEnabled()) {
			type.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setType(EObject newValue)
	 * @generated
	 */
	public void setType(EObject newValue) {
    if (newValue != null) {
      type.setSelection(new StructuredSelection(newValue));
    } else {
      type.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.type);
    if (readOnly && type.isEnabled()) {
      type.setEnabled(false);
      type.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
    } else if (!readOnly && !type.isEnabled()) {
      type.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setTypeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTypeButtonMode(ButtonsModeEnum newValue) {
		type.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addFilterType(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToType(ViewerFilter filter) {
    type.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addBusinessFilterType(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToType(ViewerFilter filter) {
    type.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#getOrdering()
	 * @generated
	 */
	public Enumerator getOrdering() {
    Enumerator selection = (Enumerator) ((StructuredSelection) ordering.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#initOrdering(Object input, Enumerator current)
	 */
	public void initOrdering(Object input, Enumerator current) {
		ordering.setInput(input);
		ordering.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.ordering);
		if (readOnly && ordering.isEnabled()) {
			ordering.setEnabled(false);
			ordering.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
		} else if (!readOnly && !ordering.isEnabled()) {
			ordering.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setOrdering(Enumerator newValue)
	 * @generated
	 */
	public void setOrdering(Enumerator newValue) {
    ordering.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.ordering);
    if (readOnly && ordering.isEnabled()) {
      ordering.setEnabled(false);
      ordering.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
    } else if (!readOnly && !ordering.isEnabled()) {
      ordering.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#getIsControlType()
	 * @generated
	 */
	public Boolean getIsControlType() {
    return Boolean.valueOf(isControlType.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setIsControlType(Boolean newValue)
	 * @generated
	 */
	public void setIsControlType(Boolean newValue) {
    if (newValue != null) {
      isControlType.setSelection(newValue.booleanValue());
    } else {
      isControlType.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.isControlType);
    if (readOnly && isControlType.isEnabled()) {
      isControlType.setEnabled(false);
      isControlType.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
    } else if (!readOnly && !isControlType.isEnabled()) {
      isControlType.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#initInState(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInState(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		inState.setContentProvider(contentProvider);
		inState.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.inState);
		if (readOnly && inState.getTable().isEnabled()) {
			inState.setEnabled(false);
			inState.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
		} else if (!readOnly && !inState.getTable().isEnabled()) {
			inState.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#updateInState()
	 * @generated
	 */
	public void updateInState() {
  inState.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addFilterInState(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInState(ViewerFilter filter) {
    inStateFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addBusinessFilterInState(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInState(ViewerFilter filter) {
    inStateBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#isContainedInInStateTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInInStateTable(EObject element) {
    return ((ReferencesTableSettings)inState.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#getSelection()
	 * @generated
	 */
	public EObject getSelection() {
    if (selection.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) selection.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#initSelection(EObjectFlatComboSettings)
	 */
	public void initSelection(EObjectFlatComboSettings settings) {
		selection.setInput(settings);
		if (current != null) {
			selection.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.selection);
		if (readOnly && selection.isEnabled()) {
			selection.setEnabled(false);
			selection.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
		} else if (!readOnly && !selection.isEnabled()) {
			selection.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setSelection(EObject newValue)
	 * @generated
	 */
	public void setSelection(EObject newValue) {
    if (newValue != null) {
      selection.setSelection(new StructuredSelection(newValue));
    } else {
      selection.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.selection);
    if (readOnly && selection.isEnabled()) {
      selection.setEnabled(false);
      selection.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
    } else if (!readOnly && !selection.isEnabled()) {
      selection.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setSelectionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSelectionButtonMode(ButtonsModeEnum newValue) {
		selection.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addFilterSelection(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSelection(ViewerFilter filter) {
    selection.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#addBusinessFilterSelection(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSelection(ViewerFilter filter) {
    selection.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#getIsOrdered()
	 * @generated
	 */
	public Boolean getIsOrdered() {
    return Boolean.valueOf(isOrdered.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setIsOrdered(Boolean newValue)
	 * @generated
	 */
	public void setIsOrdered(Boolean newValue) {
    if (newValue != null) {
      isOrdered.setSelection(newValue.booleanValue());
    } else {
      isOrdered.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.isOrdered);
    if (readOnly && isOrdered.isEnabled()) {
      isOrdered.setEnabled(false);
      isOrdered.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
    } else if (!readOnly && !isOrdered.isEnabled()) {
      isOrdered.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#getIsUnique()
	 * @generated
	 */
	public Boolean getIsUnique() {
    return Boolean.valueOf(isUnique.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setIsUnique(Boolean newValue)
	 * @generated
	 */
	public void setIsUnique(Boolean newValue) {
    if (newValue != null) {
      isUnique.setSelection(newValue.booleanValue());
    } else {
      isUnique.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.isUnique);
    if (readOnly && isUnique.isEnabled()) {
      isUnique.setEnabled(false);
      isUnique.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
    } else if (!readOnly && !isUnique.isEnabled()) {
      isUnique.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#getIsControl()
	 * @generated
	 */
	public Boolean getIsControl() {
    return Boolean.valueOf(isControl.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActionInputPinPropertiesEditionPart#setIsControl(Boolean newValue)
	 * @generated
	 */
	public void setIsControl(Boolean newValue) {
    if (newValue != null) {
      isControl.setSelection(newValue.booleanValue());
    } else {
      isControl.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ActionInputPin.Properties.isControl);
    if (readOnly && isControl.isEnabled()) {
      isControl.setEnabled(false);
      isControl.setToolTipText(UmlMessages.ActionInputPin_ReadOnly);
    } else if (!readOnly && !isControl.isEnabled()) {
      isControl.setEnabled(true);
    }	
    
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.ActionInputPin_Part_Title;
  }



}
