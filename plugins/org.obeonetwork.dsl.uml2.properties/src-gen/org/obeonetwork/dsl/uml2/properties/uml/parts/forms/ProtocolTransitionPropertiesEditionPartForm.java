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

import org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ProtocolTransitionPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, ProtocolTransitionPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected Button isLeaf;
	protected EMFComboViewer kind;
	protected EObjectFlatComboViewer container;
	protected EObjectFlatComboViewer source;
	protected EObjectFlatComboViewer target;
	protected EObjectFlatComboViewer redefinedTransition;
	protected EObjectFlatComboViewer guard;
	protected EObjectFlatComboViewer postCondition;
	protected EObjectFlatComboViewer preCondition;



	/**
	 * For {@link ISection} use only.
	 */
	public ProtocolTransitionPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ProtocolTransitionPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence protocolTransitionStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = protocolTransitionStep.addStep(UmlViewsRepository.ProtocolTransition.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.ProtocolTransition.Properties.name);
    propertiesStep.addStep(UmlViewsRepository.ProtocolTransition.Properties.visibility);
    propertiesStep.addStep(UmlViewsRepository.ProtocolTransition.Properties.clientDependency);
    propertiesStep.addStep(UmlViewsRepository.ProtocolTransition.Properties.isLeaf);
    propertiesStep.addStep(UmlViewsRepository.ProtocolTransition.Properties.kind);
    propertiesStep.addStep(UmlViewsRepository.ProtocolTransition.Properties.container);
    propertiesStep.addStep(UmlViewsRepository.ProtocolTransition.Properties.source);
    propertiesStep.addStep(UmlViewsRepository.ProtocolTransition.Properties.target);
    propertiesStep.addStep(UmlViewsRepository.ProtocolTransition.Properties.redefinedTransition);
    propertiesStep.addStep(UmlViewsRepository.ProtocolTransition.Properties.guard);
    propertiesStep.addStep(UmlViewsRepository.ProtocolTransition.Properties.postCondition);
    propertiesStep.addStep(UmlViewsRepository.ProtocolTransition.Properties.preCondition);
    
    
    composer = new PartComposer(protocolTransitionStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.ProtocolTransition.Properties.class) {
          return createPropertiesGroup(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ProtocolTransition.Properties.name) {
          return createNameText(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ProtocolTransition.Properties.visibility) {
          return createVisibilityEMFComboViewer(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ProtocolTransition.Properties.clientDependency) {
          return createClientDependencyReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ProtocolTransition.Properties.isLeaf) {
          return createIsLeafCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ProtocolTransition.Properties.kind) {
          return createKindEMFComboViewer(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.ProtocolTransition.Properties.container) {
          return createContainerFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.ProtocolTransition.Properties.source) {
          return createSourceFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.ProtocolTransition.Properties.target) {
          return createTargetFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.ProtocolTransition.Properties.redefinedTransition) {
          return createRedefinedTransitionFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.ProtocolTransition.Properties.guard) {
          return createGuardFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.ProtocolTransition.Properties.postCondition) {
          return createPostConditionFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.ProtocolTransition.Properties.preCondition) {
          return createPreConditionFlatComboViewer(parent, widgetFactory);
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
    propertiesSection.setText(UmlMessages.ProtocolTransitionPropertiesEditionPart_PropertiesGroupLabel);
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
    createDescription(parent, UmlViewsRepository.ProtocolTransition.Properties.name, UmlMessages.ProtocolTransitionPropertiesEditionPart_NameLabel);
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
              ProtocolTransitionPropertiesEditionPartForm.this,
              UmlViewsRepository.ProtocolTransition.Properties.name,
              PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
          propertiesEditionComponent
              .firePropertiesChanged(new PropertiesEditionEvent(
                  ProtocolTransitionPropertiesEditionPartForm.this,
                  UmlViewsRepository.ProtocolTransition.Properties.name,
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
                  ProtocolTransitionPropertiesEditionPartForm.this,
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
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
        }
      }
    });
    EditingUtils.setID(name, UmlViewsRepository.ProtocolTransition.Properties.name);
    EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProtocolTransition.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
    createDescription(parent, UmlViewsRepository.ProtocolTransition.Properties.visibility, UmlMessages.ProtocolTransitionPropertiesEditionPart_VisibilityLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
      }

    });
    visibility.setID(UmlViewsRepository.ProtocolTransition.Properties.visibility);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProtocolTransition.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.clientDependency = new ReferencesTable(getDescription(UmlViewsRepository.ProtocolTransition.Properties.clientDependency, UmlMessages.ProtocolTransitionPropertiesEditionPart_ClientDependencyLabel), new ReferencesTableListener	() {
      public void handleAdd() { addClientDependency(); }
      public void handleEdit(EObject element) { editClientDependency(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromClientDependency(element); }
      public void navigateTo(EObject element) { }
    });
    this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProtocolTransition.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
    this.clientDependency.createControls(parent, widgetFactory);
    this.clientDependency.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
    clientDependencyData.horizontalSpan = 3;
    this.clientDependency.setLayoutData(clientDependencyData);
    this.clientDependency.disableMove();
    clientDependency.setID(UmlViewsRepository.ProtocolTransition.Properties.clientDependency);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.clientDependency,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    clientDependency.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    isLeaf = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.ProtocolTransition.Properties.isLeaf, UmlMessages.ProtocolTransitionPropertiesEditionPart_IsLeafLabel), SWT.CHECK);
    isLeaf.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
      }

    });
    GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
    isLeafData.horizontalSpan = 2;
    isLeaf.setLayoutData(isLeafData);
    EditingUtils.setID(isLeaf, UmlViewsRepository.ProtocolTransition.Properties.isLeaf);
    EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProtocolTransition.Properties.isLeaf, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createKindEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
    createDescription(parent, UmlViewsRepository.ProtocolTransition.Properties.kind, UmlMessages.ProtocolTransitionPropertiesEditionPart_KindLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.kind, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getKind()));
      }

    });
    kind.setID(UmlViewsRepository.ProtocolTransition.Properties.kind);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProtocolTransition.Properties.kind, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createContainerFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.ProtocolTransition.Properties.container, UmlMessages.ProtocolTransitionPropertiesEditionPart_ContainerLabel);
    container = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ProtocolTransition.Properties.container, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(container);
    container.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData containerData = new GridData(GridData.FILL_HORIZONTAL);
    container.setLayoutData(containerData);
    container.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.container, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getContainer()));
      }

    });
    container.setID(UmlViewsRepository.ProtocolTransition.Properties.container);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProtocolTransition.Properties.container, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createSourceFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.ProtocolTransition.Properties.source, UmlMessages.ProtocolTransitionPropertiesEditionPart_SourceLabel);
    source = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ProtocolTransition.Properties.source, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(source);
    source.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData sourceData = new GridData(GridData.FILL_HORIZONTAL);
    source.setLayoutData(sourceData);
    source.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.source, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getSource()));
      }

    });
    source.setID(UmlViewsRepository.ProtocolTransition.Properties.source);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProtocolTransition.Properties.source, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createTargetFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.ProtocolTransition.Properties.target, UmlMessages.ProtocolTransitionPropertiesEditionPart_TargetLabel);
    target = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ProtocolTransition.Properties.target, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(target);
    target.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData targetData = new GridData(GridData.FILL_HORIZONTAL);
    target.setLayoutData(targetData);
    target.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.target, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getTarget()));
      }

    });
    target.setID(UmlViewsRepository.ProtocolTransition.Properties.target);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProtocolTransition.Properties.target, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createRedefinedTransitionFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.ProtocolTransition.Properties.redefinedTransition, UmlMessages.ProtocolTransitionPropertiesEditionPart_RedefinedTransitionLabel);
    redefinedTransition = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ProtocolTransition.Properties.redefinedTransition, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(redefinedTransition);
    redefinedTransition.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData redefinedTransitionData = new GridData(GridData.FILL_HORIZONTAL);
    redefinedTransition.setLayoutData(redefinedTransitionData);
    redefinedTransition.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.redefinedTransition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getRedefinedTransition()));
      }

    });
    redefinedTransition.setID(UmlViewsRepository.ProtocolTransition.Properties.redefinedTransition);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProtocolTransition.Properties.redefinedTransition, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createGuardFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.ProtocolTransition.Properties.guard, UmlMessages.ProtocolTransitionPropertiesEditionPart_GuardLabel);
    guard = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ProtocolTransition.Properties.guard, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(guard);
    guard.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData guardData = new GridData(GridData.FILL_HORIZONTAL);
    guard.setLayoutData(guardData);
    guard.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.guard, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getGuard()));
      }

    });
    guard.setID(UmlViewsRepository.ProtocolTransition.Properties.guard);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProtocolTransition.Properties.guard, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createPostConditionFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.ProtocolTransition.Properties.postCondition, UmlMessages.ProtocolTransitionPropertiesEditionPart_PostConditionLabel);
    postCondition = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ProtocolTransition.Properties.postCondition, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(postCondition);
    postCondition.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData postConditionData = new GridData(GridData.FILL_HORIZONTAL);
    postCondition.setLayoutData(postConditionData);
    postCondition.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.postCondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getPostCondition()));
      }

    });
    postCondition.setID(UmlViewsRepository.ProtocolTransition.Properties.postCondition);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProtocolTransition.Properties.postCondition, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createPreConditionFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.ProtocolTransition.Properties.preCondition, UmlMessages.ProtocolTransitionPropertiesEditionPart_PreConditionLabel);
    preCondition = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ProtocolTransition.Properties.preCondition, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(preCondition);
    preCondition.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData preConditionData = new GridData(GridData.FILL_HORIZONTAL);
    preCondition.setLayoutData(preConditionData);
    preCondition.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolTransitionPropertiesEditionPartForm.this, UmlViewsRepository.ProtocolTransition.Properties.preCondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getPreCondition()));
      }

    });
    preCondition.setID(UmlViewsRepository.ProtocolTransition.Properties.preCondition);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProtocolTransition.Properties.preCondition, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
    return name.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
    if (newValue != null) {
      name.setText(newValue);
    } else {
      name.setText(""); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.name);
    if (readOnly && name.isEnabled()) {
      name.setEnabled(false);
      name.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
    } else if (!readOnly && !name.isEnabled()) {
      name.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
    Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
    visibility.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.visibility);
    if (readOnly && visibility.isEnabled()) {
      visibility.setEnabled(false);
      visibility.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
    } else if (!readOnly && !visibility.isEnabled()) {
      visibility.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
  clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
    clientDependencyFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
    clientDependencyBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
    return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
    return Boolean.valueOf(isLeaf.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
    if (newValue != null) {
      isLeaf.setSelection(newValue.booleanValue());
    } else {
      isLeaf.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.isLeaf);
    if (readOnly && isLeaf.isEnabled()) {
      isLeaf.setEnabled(false);
      isLeaf.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
    } else if (!readOnly && !isLeaf.isEnabled()) {
      isLeaf.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#getKind()
	 * @generated
	 */
	public Enumerator getKind() {
    Enumerator selection = (Enumerator) ((StructuredSelection) kind.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#initKind(Object input, Enumerator current)
	 */
	public void initKind(Object input, Enumerator current) {
		kind.setInput(input);
		kind.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.kind);
		if (readOnly && kind.isEnabled()) {
			kind.setEnabled(false);
			kind.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
		} else if (!readOnly && !kind.isEnabled()) {
			kind.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setKind(Enumerator newValue)
	 * @generated
	 */
	public void setKind(Enumerator newValue) {
    kind.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.kind);
    if (readOnly && kind.isEnabled()) {
      kind.setEnabled(false);
      kind.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
    } else if (!readOnly && !kind.isEnabled()) {
      kind.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#getContainer()
	 * @generated
	 */
	public EObject getContainer() {
    if (container.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) container.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#initContainer(EObjectFlatComboSettings)
	 */
	public void initContainer(EObjectFlatComboSettings settings) {
		container.setInput(settings);
		if (current != null) {
			container.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.container);
		if (readOnly && container.isEnabled()) {
			container.setEnabled(false);
			container.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
		} else if (!readOnly && !container.isEnabled()) {
			container.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setContainer(EObject newValue)
	 * @generated
	 */
	public void setContainer(EObject newValue) {
    if (newValue != null) {
      container.setSelection(new StructuredSelection(newValue));
    } else {
      container.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.container);
    if (readOnly && container.isEnabled()) {
      container.setEnabled(false);
      container.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
    } else if (!readOnly && !container.isEnabled()) {
      container.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setContainerButtonMode(ButtonsModeEnum newValue)
	 */
	public void setContainerButtonMode(ButtonsModeEnum newValue) {
		container.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addFilterContainer(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToContainer(ViewerFilter filter) {
    container.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addBusinessFilterContainer(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToContainer(ViewerFilter filter) {
    container.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#getSource()
	 * @generated
	 */
	public EObject getSource() {
    if (source.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) source.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#initSource(EObjectFlatComboSettings)
	 */
	public void initSource(EObjectFlatComboSettings settings) {
		source.setInput(settings);
		if (current != null) {
			source.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.source);
		if (readOnly && source.isEnabled()) {
			source.setEnabled(false);
			source.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
		} else if (!readOnly && !source.isEnabled()) {
			source.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setSource(EObject newValue)
	 * @generated
	 */
	public void setSource(EObject newValue) {
    if (newValue != null) {
      source.setSelection(new StructuredSelection(newValue));
    } else {
      source.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.source);
    if (readOnly && source.isEnabled()) {
      source.setEnabled(false);
      source.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
    } else if (!readOnly && !source.isEnabled()) {
      source.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setSourceButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSourceButtonMode(ButtonsModeEnum newValue) {
		source.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addFilterSource(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSource(ViewerFilter filter) {
    source.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addBusinessFilterSource(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSource(ViewerFilter filter) {
    source.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#getTarget()
	 * @generated
	 */
	public EObject getTarget() {
    if (target.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) target.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#initTarget(EObjectFlatComboSettings)
	 */
	public void initTarget(EObjectFlatComboSettings settings) {
		target.setInput(settings);
		if (current != null) {
			target.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.target);
		if (readOnly && target.isEnabled()) {
			target.setEnabled(false);
			target.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
		} else if (!readOnly && !target.isEnabled()) {
			target.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setTarget(EObject newValue)
	 * @generated
	 */
	public void setTarget(EObject newValue) {
    if (newValue != null) {
      target.setSelection(new StructuredSelection(newValue));
    } else {
      target.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.target);
    if (readOnly && target.isEnabled()) {
      target.setEnabled(false);
      target.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
    } else if (!readOnly && !target.isEnabled()) {
      target.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setTargetButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTargetButtonMode(ButtonsModeEnum newValue) {
		target.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addFilterTarget(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTarget(ViewerFilter filter) {
    target.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addBusinessFilterTarget(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTarget(ViewerFilter filter) {
    target.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#getRedefinedTransition()
	 * @generated
	 */
	public EObject getRedefinedTransition() {
    if (redefinedTransition.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) redefinedTransition.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#initRedefinedTransition(EObjectFlatComboSettings)
	 */
	public void initRedefinedTransition(EObjectFlatComboSettings settings) {
		redefinedTransition.setInput(settings);
		if (current != null) {
			redefinedTransition.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.redefinedTransition);
		if (readOnly && redefinedTransition.isEnabled()) {
			redefinedTransition.setEnabled(false);
			redefinedTransition.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
		} else if (!readOnly && !redefinedTransition.isEnabled()) {
			redefinedTransition.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setRedefinedTransition(EObject newValue)
	 * @generated
	 */
	public void setRedefinedTransition(EObject newValue) {
    if (newValue != null) {
      redefinedTransition.setSelection(new StructuredSelection(newValue));
    } else {
      redefinedTransition.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.redefinedTransition);
    if (readOnly && redefinedTransition.isEnabled()) {
      redefinedTransition.setEnabled(false);
      redefinedTransition.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
    } else if (!readOnly && !redefinedTransition.isEnabled()) {
      redefinedTransition.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setRedefinedTransitionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setRedefinedTransitionButtonMode(ButtonsModeEnum newValue) {
		redefinedTransition.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addFilterRedefinedTransition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedTransition(ViewerFilter filter) {
    redefinedTransition.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addBusinessFilterRedefinedTransition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedTransition(ViewerFilter filter) {
    redefinedTransition.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#getGuard()
	 * @generated
	 */
	public EObject getGuard() {
    if (guard.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) guard.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#initGuard(EObjectFlatComboSettings)
	 */
	public void initGuard(EObjectFlatComboSettings settings) {
		guard.setInput(settings);
		if (current != null) {
			guard.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.guard);
		if (readOnly && guard.isEnabled()) {
			guard.setEnabled(false);
			guard.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
		} else if (!readOnly && !guard.isEnabled()) {
			guard.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setGuard(EObject newValue)
	 * @generated
	 */
	public void setGuard(EObject newValue) {
    if (newValue != null) {
      guard.setSelection(new StructuredSelection(newValue));
    } else {
      guard.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.guard);
    if (readOnly && guard.isEnabled()) {
      guard.setEnabled(false);
      guard.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
    } else if (!readOnly && !guard.isEnabled()) {
      guard.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setGuardButtonMode(ButtonsModeEnum newValue)
	 */
	public void setGuardButtonMode(ButtonsModeEnum newValue) {
		guard.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addFilterGuard(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToGuard(ViewerFilter filter) {
    guard.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addBusinessFilterGuard(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToGuard(ViewerFilter filter) {
    guard.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#getPostCondition()
	 * @generated
	 */
	public EObject getPostCondition() {
    if (postCondition.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) postCondition.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#initPostCondition(EObjectFlatComboSettings)
	 */
	public void initPostCondition(EObjectFlatComboSettings settings) {
		postCondition.setInput(settings);
		if (current != null) {
			postCondition.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.postCondition);
		if (readOnly && postCondition.isEnabled()) {
			postCondition.setEnabled(false);
			postCondition.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
		} else if (!readOnly && !postCondition.isEnabled()) {
			postCondition.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setPostCondition(EObject newValue)
	 * @generated
	 */
	public void setPostCondition(EObject newValue) {
    if (newValue != null) {
      postCondition.setSelection(new StructuredSelection(newValue));
    } else {
      postCondition.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.postCondition);
    if (readOnly && postCondition.isEnabled()) {
      postCondition.setEnabled(false);
      postCondition.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
    } else if (!readOnly && !postCondition.isEnabled()) {
      postCondition.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setPostConditionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setPostConditionButtonMode(ButtonsModeEnum newValue) {
		postCondition.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addFilterPostCondition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToPostCondition(ViewerFilter filter) {
    postCondition.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addBusinessFilterPostCondition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToPostCondition(ViewerFilter filter) {
    postCondition.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#getPreCondition()
	 * @generated
	 */
	public EObject getPreCondition() {
    if (preCondition.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) preCondition.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#initPreCondition(EObjectFlatComboSettings)
	 */
	public void initPreCondition(EObjectFlatComboSettings settings) {
		preCondition.setInput(settings);
		if (current != null) {
			preCondition.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.preCondition);
		if (readOnly && preCondition.isEnabled()) {
			preCondition.setEnabled(false);
			preCondition.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
		} else if (!readOnly && !preCondition.isEnabled()) {
			preCondition.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setPreCondition(EObject newValue)
	 * @generated
	 */
	public void setPreCondition(EObject newValue) {
    if (newValue != null) {
      preCondition.setSelection(new StructuredSelection(newValue));
    } else {
      preCondition.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ProtocolTransition.Properties.preCondition);
    if (readOnly && preCondition.isEnabled()) {
      preCondition.setEnabled(false);
      preCondition.setToolTipText(UmlMessages.ProtocolTransition_ReadOnly);
    } else if (!readOnly && !preCondition.isEnabled()) {
      preCondition.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#setPreConditionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setPreConditionButtonMode(ButtonsModeEnum newValue) {
		preCondition.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addFilterPreCondition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToPreCondition(ViewerFilter filter) {
    preCondition.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolTransitionPropertiesEditionPart#addBusinessFilterPreCondition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToPreCondition(ViewerFilter filter) {
    preCondition.addBusinessRuleFilter(filter);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.ProtocolTransition_Part_Title;
  }



}
