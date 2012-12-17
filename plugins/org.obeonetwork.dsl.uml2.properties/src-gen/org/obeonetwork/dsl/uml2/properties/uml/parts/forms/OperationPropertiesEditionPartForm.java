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

import org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class OperationPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, OperationPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected Button isLeaf;
	protected Button isStatic;
	protected Button isAbstract;
	protected ReferencesTable method;
	protected List<ViewerFilter> methodBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> methodFilters = new ArrayList<ViewerFilter>();
	protected EMFComboViewer concurrency;
	protected ReferencesTable raisedException;
	protected List<ViewerFilter> raisedExceptionBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> raisedExceptionFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer owningTemplateParameter;
	protected EObjectFlatComboViewer templateParameter;
	protected EObjectFlatComboViewer interface_;
	protected EObjectFlatComboViewer class_;
	protected Button isQuery;
	protected ReferencesTable precondition;
	protected List<ViewerFilter> preconditionBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> preconditionFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable postcondition;
	protected List<ViewerFilter> postconditionBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> postconditionFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable redefinedOperation;
	protected List<ViewerFilter> redefinedOperationBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> redefinedOperationFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer datatype;
	protected EObjectFlatComboViewer bodyCondition;



	protected ReferencesTable parameters;
  protected List<ViewerFilter> parametersBusinessFilters = new ArrayList<ViewerFilter>();
  protected List<ViewerFilter> parametersFilters = new ArrayList<ViewerFilter>();



  /**
	 * For {@link ISection} use only.
	 */
	public OperationPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public OperationPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence operationStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = operationStep.addStep(UmlViewsRepository.Operation.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.name);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.visibility);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.clientDependency);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.isLeaf);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.isStatic);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.isAbstract);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.method);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.concurrency);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.raisedException);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.owningTemplateParameter);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.templateParameter);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.interface_);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.class_);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.isQuery);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.precondition);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.postcondition);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.redefinedOperation);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.datatype);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.bodyCondition);
    propertiesStep.addStep(UmlViewsRepository.Operation.Properties.parameters);
    
    
    composer = new PartComposer(operationStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.Operation.Properties.class) {
          return createPropertiesGroup(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Operation.Properties.name) {
          return createNameText(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Operation.Properties.visibility) {
          return createVisibilityEMFComboViewer(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Operation.Properties.clientDependency) {
          return createClientDependencyReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Operation.Properties.isLeaf) {
          return createIsLeafCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Operation.Properties.isStatic) {
          return createIsStaticCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Operation.Properties.isAbstract) {
          return createIsAbstractCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Operation.Properties.method) {
          return createMethodReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Operation.Properties.concurrency) {
          return createConcurrencyEMFComboViewer(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Operation.Properties.raisedException) {
          return createRaisedExceptionReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Operation.Properties.owningTemplateParameter) {
          return createOwningTemplateParameterFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.Operation.Properties.templateParameter) {
          return createTemplateParameterFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.Operation.Properties.interface_) {
          return createInterface_FlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.Operation.Properties.class_) {
          return createClass_FlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.Operation.Properties.isQuery) {
          return createIsQueryCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Operation.Properties.precondition) {
          return createPreconditionReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Operation.Properties.postcondition) {
          return createPostconditionReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Operation.Properties.redefinedOperation) {
          return createRedefinedOperationReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Operation.Properties.datatype) {
          return createDatatypeFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.Operation.Properties.bodyCondition) {
          return createBodyConditionFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.Operation.Properties.parameters) {
          return createParametersTableComposition(widgetFactory, parent);
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
    propertiesSection.setText(UmlMessages.OperationPropertiesEditionPart_PropertiesGroupLabel);
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
    createDescription(parent, UmlViewsRepository.Operation.Properties.name, UmlMessages.OperationPropertiesEditionPart_NameLabel);
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
              OperationPropertiesEditionPartForm.this,
              UmlViewsRepository.Operation.Properties.name,
              PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
          propertiesEditionComponent
              .firePropertiesChanged(new PropertiesEditionEvent(
                  OperationPropertiesEditionPartForm.this,
                  UmlViewsRepository.Operation.Properties.name,
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
                  OperationPropertiesEditionPartForm.this,
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
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
        }
      }
    });
    EditingUtils.setID(name, UmlViewsRepository.Operation.Properties.name);
    EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
    createDescription(parent, UmlViewsRepository.Operation.Properties.visibility, UmlMessages.OperationPropertiesEditionPart_VisibilityLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
      }

    });
    visibility.setID(UmlViewsRepository.Operation.Properties.visibility);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.clientDependency = new ReferencesTable(getDescription(UmlViewsRepository.Operation.Properties.clientDependency, UmlMessages.OperationPropertiesEditionPart_ClientDependencyLabel), new ReferencesTableListener	() {
      public void handleAdd() { addClientDependency(); }
      public void handleEdit(EObject element) { editClientDependency(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromClientDependency(element); }
      public void navigateTo(EObject element) { }
    });
    this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
    this.clientDependency.createControls(parent, widgetFactory);
    this.clientDependency.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
    clientDependencyData.horizontalSpan = 3;
    this.clientDependency.setLayoutData(clientDependencyData);
    this.clientDependency.disableMove();
    clientDependency.setID(UmlViewsRepository.Operation.Properties.clientDependency);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.clientDependency,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    clientDependency.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
    isLeaf = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.Operation.Properties.isLeaf, UmlMessages.OperationPropertiesEditionPart_IsLeafLabel), SWT.CHECK);
    isLeaf.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
      }

    });
    GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
    isLeafData.horizontalSpan = 2;
    isLeaf.setLayoutData(isLeafData);
    EditingUtils.setID(isLeaf, UmlViewsRepository.Operation.Properties.isLeaf);
    EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.isLeaf, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createIsStaticCheckbox(FormToolkit widgetFactory, Composite parent) {
    isStatic = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.Operation.Properties.isStatic, UmlMessages.OperationPropertiesEditionPart_IsStaticLabel), SWT.CHECK);
    isStatic.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.isStatic, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isStatic.getSelection())));
      }

    });
    GridData isStaticData = new GridData(GridData.FILL_HORIZONTAL);
    isStaticData.horizontalSpan = 2;
    isStatic.setLayoutData(isStaticData);
    EditingUtils.setID(isStatic, UmlViewsRepository.Operation.Properties.isStatic);
    EditingUtils.setEEFtype(isStatic, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.isStatic, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createIsAbstractCheckbox(FormToolkit widgetFactory, Composite parent) {
    isAbstract = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.Operation.Properties.isAbstract, UmlMessages.OperationPropertiesEditionPart_IsAbstractLabel), SWT.CHECK);
    isAbstract.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.isAbstract, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isAbstract.getSelection())));
      }

    });
    GridData isAbstractData = new GridData(GridData.FILL_HORIZONTAL);
    isAbstractData.horizontalSpan = 2;
    isAbstract.setLayoutData(isAbstractData);
    EditingUtils.setID(isAbstract, UmlViewsRepository.Operation.Properties.isAbstract);
    EditingUtils.setEEFtype(isAbstract, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.isAbstract, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createMethodReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.method = new ReferencesTable(getDescription(UmlViewsRepository.Operation.Properties.method, UmlMessages.OperationPropertiesEditionPart_MethodLabel), new ReferencesTableListener	() {
      public void handleAdd() { addMethod(); }
      public void handleEdit(EObject element) { editMethod(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveMethod(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromMethod(element); }
      public void navigateTo(EObject element) { }
    });
    this.method.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.method, UmlViewsRepository.FORM_KIND));
    this.method.createControls(parent, widgetFactory);
    this.method.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.method, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData methodData = new GridData(GridData.FILL_HORIZONTAL);
    methodData.horizontalSpan = 3;
    this.method.setLayoutData(methodData);
    this.method.disableMove();
    method.setID(UmlViewsRepository.Operation.Properties.method);
    method.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addMethod() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(method.getInput(), methodFilters, methodBusinessFilters,
    "method", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.method,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        method.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveMethod(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.method, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    method.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromMethod(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.method, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    method.refresh();
  }

	/**
	 * @generated
	 */
	protected void editMethod(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        method.refresh();
      }
    }
  }

	/**
	 * @generated
	 */
	
	protected Composite createConcurrencyEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
    createDescription(parent, UmlViewsRepository.Operation.Properties.concurrency, UmlMessages.OperationPropertiesEditionPart_ConcurrencyLabel);
    concurrency = new EMFComboViewer(parent);
    concurrency.setContentProvider(new ArrayContentProvider());
    concurrency.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
    GridData concurrencyData = new GridData(GridData.FILL_HORIZONTAL);
    concurrency.getCombo().setLayoutData(concurrencyData);
    concurrency.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       * 	@generated
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.concurrency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getConcurrency()));
      }

    });
    concurrency.setID(UmlViewsRepository.Operation.Properties.concurrency);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.concurrency, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createRaisedExceptionReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.raisedException = new ReferencesTable(getDescription(UmlViewsRepository.Operation.Properties.raisedException, UmlMessages.OperationPropertiesEditionPart_RaisedExceptionLabel), new ReferencesTableListener	() {
      public void handleAdd() { addRaisedException(); }
      public void handleEdit(EObject element) { editRaisedException(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveRaisedException(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromRaisedException(element); }
      public void navigateTo(EObject element) { }
    });
    this.raisedException.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.raisedException, UmlViewsRepository.FORM_KIND));
    this.raisedException.createControls(parent, widgetFactory);
    this.raisedException.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.raisedException, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData raisedExceptionData = new GridData(GridData.FILL_HORIZONTAL);
    raisedExceptionData.horizontalSpan = 3;
    this.raisedException.setLayoutData(raisedExceptionData);
    this.raisedException.disableMove();
    raisedException.setID(UmlViewsRepository.Operation.Properties.raisedException);
    raisedException.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addRaisedException() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(raisedException.getInput(), raisedExceptionFilters, raisedExceptionBusinessFilters,
    "raisedException", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.raisedException,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        raisedException.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveRaisedException(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.raisedException, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    raisedException.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromRaisedException(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.raisedException, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    raisedException.refresh();
  }

	/**
	 * @generated
	 */
	protected void editRaisedException(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        raisedException.refresh();
      }
    }
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createOwningTemplateParameterFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.Operation.Properties.owningTemplateParameter, UmlMessages.OperationPropertiesEditionPart_OwningTemplateParameterLabel);
    owningTemplateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Operation.Properties.owningTemplateParameter, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(owningTemplateParameter);
    owningTemplateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData owningTemplateParameterData = new GridData(GridData.FILL_HORIZONTAL);
    owningTemplateParameter.setLayoutData(owningTemplateParameterData);
    owningTemplateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.owningTemplateParameter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getOwningTemplateParameter()));
      }

    });
    owningTemplateParameter.setID(UmlViewsRepository.Operation.Properties.owningTemplateParameter);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.owningTemplateParameter, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createTemplateParameterFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.Operation.Properties.templateParameter, UmlMessages.OperationPropertiesEditionPart_TemplateParameterLabel);
    templateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Operation.Properties.templateParameter, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(templateParameter);
    templateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData templateParameterData = new GridData(GridData.FILL_HORIZONTAL);
    templateParameter.setLayoutData(templateParameterData);
    templateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.templateParameter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getTemplateParameter()));
      }

    });
    templateParameter.setID(UmlViewsRepository.Operation.Properties.templateParameter);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.templateParameter, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createInterface_FlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.Operation.Properties.interface_, UmlMessages.OperationPropertiesEditionPart_Interface_Label);
    interface_ = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Operation.Properties.interface_, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(interface_);
    interface_.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData interface_Data = new GridData(GridData.FILL_HORIZONTAL);
    interface_.setLayoutData(interface_Data);
    interface_.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.interface_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getInterface_()));
      }

    });
    interface_.setID(UmlViewsRepository.Operation.Properties.interface_);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.interface_, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createClass_FlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.Operation.Properties.class_, UmlMessages.OperationPropertiesEditionPart_Class_Label);
    class_ = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Operation.Properties.class_, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(class_);
    class_.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData class_Data = new GridData(GridData.FILL_HORIZONTAL);
    class_.setLayoutData(class_Data);
    class_.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.class_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getClass_()));
      }

    });
    class_.setID(UmlViewsRepository.Operation.Properties.class_);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.class_, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createIsQueryCheckbox(FormToolkit widgetFactory, Composite parent) {
    isQuery = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.Operation.Properties.isQuery, UmlMessages.OperationPropertiesEditionPart_IsQueryLabel), SWT.CHECK);
    isQuery.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.isQuery, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isQuery.getSelection())));
      }

    });
    GridData isQueryData = new GridData(GridData.FILL_HORIZONTAL);
    isQueryData.horizontalSpan = 2;
    isQuery.setLayoutData(isQueryData);
    EditingUtils.setID(isQuery, UmlViewsRepository.Operation.Properties.isQuery);
    EditingUtils.setEEFtype(isQuery, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.isQuery, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createPreconditionReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.precondition = new ReferencesTable(getDescription(UmlViewsRepository.Operation.Properties.precondition, UmlMessages.OperationPropertiesEditionPart_PreconditionLabel), new ReferencesTableListener	() {
      public void handleAdd() { addPrecondition(); }
      public void handleEdit(EObject element) { editPrecondition(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { movePrecondition(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromPrecondition(element); }
      public void navigateTo(EObject element) { }
    });
    this.precondition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.precondition, UmlViewsRepository.FORM_KIND));
    this.precondition.createControls(parent, widgetFactory);
    this.precondition.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.precondition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData preconditionData = new GridData(GridData.FILL_HORIZONTAL);
    preconditionData.horizontalSpan = 3;
    this.precondition.setLayoutData(preconditionData);
    this.precondition.disableMove();
    precondition.setID(UmlViewsRepository.Operation.Properties.precondition);
    precondition.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addPrecondition() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(precondition.getInput(), preconditionFilters, preconditionBusinessFilters,
    "precondition", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.precondition,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        precondition.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void movePrecondition(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.precondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    precondition.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromPrecondition(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.precondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    precondition.refresh();
  }

	/**
	 * @generated
	 */
	protected void editPrecondition(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        precondition.refresh();
      }
    }
  }

	/**
	 * @generated
	 */
	protected Composite createPostconditionReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.postcondition = new ReferencesTable(getDescription(UmlViewsRepository.Operation.Properties.postcondition, UmlMessages.OperationPropertiesEditionPart_PostconditionLabel), new ReferencesTableListener	() {
      public void handleAdd() { addPostcondition(); }
      public void handleEdit(EObject element) { editPostcondition(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { movePostcondition(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromPostcondition(element); }
      public void navigateTo(EObject element) { }
    });
    this.postcondition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.postcondition, UmlViewsRepository.FORM_KIND));
    this.postcondition.createControls(parent, widgetFactory);
    this.postcondition.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.postcondition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData postconditionData = new GridData(GridData.FILL_HORIZONTAL);
    postconditionData.horizontalSpan = 3;
    this.postcondition.setLayoutData(postconditionData);
    this.postcondition.disableMove();
    postcondition.setID(UmlViewsRepository.Operation.Properties.postcondition);
    postcondition.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addPostcondition() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(postcondition.getInput(), postconditionFilters, postconditionBusinessFilters,
    "postcondition", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.postcondition,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        postcondition.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void movePostcondition(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.postcondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    postcondition.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromPostcondition(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.postcondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    postcondition.refresh();
  }

	/**
	 * @generated
	 */
	protected void editPostcondition(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        postcondition.refresh();
      }
    }
  }

	/**
	 * @generated
	 */
	protected Composite createRedefinedOperationReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.redefinedOperation = new ReferencesTable(getDescription(UmlViewsRepository.Operation.Properties.redefinedOperation, UmlMessages.OperationPropertiesEditionPart_RedefinedOperationLabel), new ReferencesTableListener	() {
      public void handleAdd() { addRedefinedOperation(); }
      public void handleEdit(EObject element) { editRedefinedOperation(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedOperation(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromRedefinedOperation(element); }
      public void navigateTo(EObject element) { }
    });
    this.redefinedOperation.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.redefinedOperation, UmlViewsRepository.FORM_KIND));
    this.redefinedOperation.createControls(parent, widgetFactory);
    this.redefinedOperation.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.redefinedOperation, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData redefinedOperationData = new GridData(GridData.FILL_HORIZONTAL);
    redefinedOperationData.horizontalSpan = 3;
    this.redefinedOperation.setLayoutData(redefinedOperationData);
    this.redefinedOperation.disableMove();
    redefinedOperation.setID(UmlViewsRepository.Operation.Properties.redefinedOperation);
    redefinedOperation.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addRedefinedOperation() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(redefinedOperation.getInput(), redefinedOperationFilters, redefinedOperationBusinessFilters,
    "redefinedOperation", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.redefinedOperation,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        redefinedOperation.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveRedefinedOperation(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.redefinedOperation, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    redefinedOperation.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromRedefinedOperation(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.redefinedOperation, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    redefinedOperation.refresh();
  }

	/**
	 * @generated
	 */
	protected void editRedefinedOperation(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        redefinedOperation.refresh();
      }
    }
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createDatatypeFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.Operation.Properties.datatype, UmlMessages.OperationPropertiesEditionPart_DatatypeLabel);
    datatype = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Operation.Properties.datatype, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(datatype);
    datatype.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData datatypeData = new GridData(GridData.FILL_HORIZONTAL);
    datatype.setLayoutData(datatypeData);
    datatype.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.datatype, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getDatatype()));
      }

    });
    datatype.setID(UmlViewsRepository.Operation.Properties.datatype);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.datatype, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createBodyConditionFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.Operation.Properties.bodyCondition, UmlMessages.OperationPropertiesEditionPart_BodyConditionLabel);
    bodyCondition = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Operation.Properties.bodyCondition, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(bodyCondition);
    bodyCondition.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData bodyConditionData = new GridData(GridData.FILL_HORIZONTAL);
    bodyCondition.setLayoutData(bodyConditionData);
    bodyCondition.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.bodyCondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getBodyCondition()));
      }

    });
    bodyCondition.setID(UmlViewsRepository.Operation.Properties.bodyCondition);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.bodyCondition, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }


	/**
   * @param container
   * @generated
   */
  protected Composite createParametersTableComposition(FormToolkit widgetFactory, Composite parent) {
    this.parameters = new ReferencesTable(getDescription(UmlViewsRepository.Operation.Properties.parameters, UmlMessages.OperationPropertiesEditionPart_ParametersLabel), new ReferencesTableListener() {
      public void handleAdd() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.parameters, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
        parameters.refresh();
      }
      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.parameters, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
        parameters.refresh();
      }
      public void handleMove(EObject element, int oldIndex, int newIndex) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.parameters, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
        parameters.refresh();
      }
      public void handleRemove(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.parameters, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
        parameters.refresh();
      }
      public void navigateTo(EObject element) { }
    });
    for (ViewerFilter filter : this.parametersFilters) {
      this.parameters.addFilter(filter);
    }
    this.parameters.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operation.Properties.parameters, UmlViewsRepository.FORM_KIND));
    this.parameters.createControls(parent, widgetFactory);
    this.parameters.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationPropertiesEditionPartForm.this, UmlViewsRepository.Operation.Properties.parameters, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData parametersData = new GridData(GridData.FILL_HORIZONTAL);
    parametersData.horizontalSpan = 3;
    this.parameters.setLayoutData(parametersData);
    this.parameters.setLowerBound(0);
    this.parameters.setUpperBound(-1);
    parameters.setID(UmlViewsRepository.Operation.Properties.parameters);
    parameters.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
    return name.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
    if (newValue != null) {
      name.setText(newValue);
    } else {
      name.setText(""); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.name);
    if (readOnly && name.isEnabled()) {
      name.setEnabled(false);
      name.setToolTipText(UmlMessages.Operation_ReadOnly);
    } else if (!readOnly && !name.isEnabled()) {
      name.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
    Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.Operation_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
    visibility.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.visibility);
    if (readOnly && visibility.isEnabled()) {
      visibility.setEnabled(false);
      visibility.setToolTipText(UmlMessages.Operation_ReadOnly);
    } else if (!readOnly && !visibility.isEnabled()) {
      visibility.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.Operation_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
  clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
    clientDependencyFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
    clientDependencyBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
    return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
    return Boolean.valueOf(isLeaf.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
    if (newValue != null) {
      isLeaf.setSelection(newValue.booleanValue());
    } else {
      isLeaf.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.isLeaf);
    if (readOnly && isLeaf.isEnabled()) {
      isLeaf.setEnabled(false);
      isLeaf.setToolTipText(UmlMessages.Operation_ReadOnly);
    } else if (!readOnly && !isLeaf.isEnabled()) {
      isLeaf.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#getIsStatic()
	 * @generated
	 */
	public Boolean getIsStatic() {
    return Boolean.valueOf(isStatic.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setIsStatic(Boolean newValue)
	 * @generated
	 */
	public void setIsStatic(Boolean newValue) {
    if (newValue != null) {
      isStatic.setSelection(newValue.booleanValue());
    } else {
      isStatic.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.isStatic);
    if (readOnly && isStatic.isEnabled()) {
      isStatic.setEnabled(false);
      isStatic.setToolTipText(UmlMessages.Operation_ReadOnly);
    } else if (!readOnly && !isStatic.isEnabled()) {
      isStatic.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#getIsAbstract()
	 * @generated
	 */
	public Boolean getIsAbstract() {
    return Boolean.valueOf(isAbstract.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setIsAbstract(Boolean newValue)
	 * @generated
	 */
	public void setIsAbstract(Boolean newValue) {
    if (newValue != null) {
      isAbstract.setSelection(newValue.booleanValue());
    } else {
      isAbstract.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.isAbstract);
    if (readOnly && isAbstract.isEnabled()) {
      isAbstract.setEnabled(false);
      isAbstract.setToolTipText(UmlMessages.Operation_ReadOnly);
    } else if (!readOnly && !isAbstract.isEnabled()) {
      isAbstract.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initMethod(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initMethod(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		method.setContentProvider(contentProvider);
		method.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.method);
		if (readOnly && method.getTable().isEnabled()) {
			method.setEnabled(false);
			method.setToolTipText(UmlMessages.Operation_ReadOnly);
		} else if (!readOnly && !method.getTable().isEnabled()) {
			method.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#updateMethod()
	 * @generated
	 */
	public void updateMethod() {
  method.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addFilterMethod(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToMethod(ViewerFilter filter) {
    methodFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addBusinessFilterMethod(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToMethod(ViewerFilter filter) {
    methodBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#isContainedInMethodTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInMethodTable(EObject element) {
    return ((ReferencesTableSettings)method.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#getConcurrency()
	 * @generated
	 */
	public Enumerator getConcurrency() {
    Enumerator selection = (Enumerator) ((StructuredSelection) concurrency.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initConcurrency(Object input, Enumerator current)
	 */
	public void initConcurrency(Object input, Enumerator current) {
		concurrency.setInput(input);
		concurrency.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.concurrency);
		if (readOnly && concurrency.isEnabled()) {
			concurrency.setEnabled(false);
			concurrency.setToolTipText(UmlMessages.Operation_ReadOnly);
		} else if (!readOnly && !concurrency.isEnabled()) {
			concurrency.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setConcurrency(Enumerator newValue)
	 * @generated
	 */
	public void setConcurrency(Enumerator newValue) {
    concurrency.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.concurrency);
    if (readOnly && concurrency.isEnabled()) {
      concurrency.setEnabled(false);
      concurrency.setToolTipText(UmlMessages.Operation_ReadOnly);
    } else if (!readOnly && !concurrency.isEnabled()) {
      concurrency.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initRaisedException(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRaisedException(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		raisedException.setContentProvider(contentProvider);
		raisedException.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.raisedException);
		if (readOnly && raisedException.getTable().isEnabled()) {
			raisedException.setEnabled(false);
			raisedException.setToolTipText(UmlMessages.Operation_ReadOnly);
		} else if (!readOnly && !raisedException.getTable().isEnabled()) {
			raisedException.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#updateRaisedException()
	 * @generated
	 */
	public void updateRaisedException() {
  raisedException.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addFilterRaisedException(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRaisedException(ViewerFilter filter) {
    raisedExceptionFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addBusinessFilterRaisedException(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRaisedException(ViewerFilter filter) {
    raisedExceptionBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#isContainedInRaisedExceptionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRaisedExceptionTable(EObject element) {
    return ((ReferencesTableSettings)raisedException.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#getOwningTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initOwningTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initOwningTemplateParameter(EObjectFlatComboSettings settings) {
		owningTemplateParameter.setInput(settings);
		if (current != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.owningTemplateParameter);
		if (readOnly && owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(false);
			owningTemplateParameter.setToolTipText(UmlMessages.Operation_ReadOnly);
		} else if (!readOnly && !owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setOwningTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setOwningTemplateParameter(EObject newValue) {
    if (newValue != null) {
      owningTemplateParameter.setSelection(new StructuredSelection(newValue));
    } else {
      owningTemplateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.owningTemplateParameter);
    if (readOnly && owningTemplateParameter.isEnabled()) {
      owningTemplateParameter.setEnabled(false);
      owningTemplateParameter.setToolTipText(UmlMessages.Operation_ReadOnly);
    } else if (!readOnly && !owningTemplateParameter.isEnabled()) {
      owningTemplateParameter.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		owningTemplateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter) {
    owningTemplateParameter.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addBusinessFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter) {
    owningTemplateParameter.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#getTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initTemplateParameter(EObjectFlatComboSettings settings) {
		templateParameter.setInput(settings);
		if (current != null) {
			templateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.templateParameter);
		if (readOnly && templateParameter.isEnabled()) {
			templateParameter.setEnabled(false);
			templateParameter.setToolTipText(UmlMessages.Operation_ReadOnly);
		} else if (!readOnly && !templateParameter.isEnabled()) {
			templateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setTemplateParameter(EObject newValue) {
    if (newValue != null) {
      templateParameter.setSelection(new StructuredSelection(newValue));
    } else {
      templateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.templateParameter);
    if (readOnly && templateParameter.isEnabled()) {
      templateParameter.setEnabled(false);
      templateParameter.setToolTipText(UmlMessages.Operation_ReadOnly);
    } else if (!readOnly && !templateParameter.isEnabled()) {
      templateParameter.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		templateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter) {
    templateParameter.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addBusinessFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter) {
    templateParameter.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#getInterface_()
	 * @generated
	 */
	public EObject getInterface_() {
    if (interface_.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) interface_.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initInterface_(EObjectFlatComboSettings)
	 */
	public void initInterface_(EObjectFlatComboSettings settings) {
		interface_.setInput(settings);
		if (current != null) {
			interface_.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.interface_);
		if (readOnly && interface_.isEnabled()) {
			interface_.setEnabled(false);
			interface_.setToolTipText(UmlMessages.Operation_ReadOnly);
		} else if (!readOnly && !interface_.isEnabled()) {
			interface_.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setInterface_(EObject newValue)
	 * @generated
	 */
	public void setInterface_(EObject newValue) {
    if (newValue != null) {
      interface_.setSelection(new StructuredSelection(newValue));
    } else {
      interface_.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.interface_);
    if (readOnly && interface_.isEnabled()) {
      interface_.setEnabled(false);
      interface_.setToolTipText(UmlMessages.Operation_ReadOnly);
    } else if (!readOnly && !interface_.isEnabled()) {
      interface_.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setInterface_ButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInterface_ButtonMode(ButtonsModeEnum newValue) {
		interface_.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addFilterInterface_(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInterface_(ViewerFilter filter) {
    interface_.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addBusinessFilterInterface_(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInterface_(ViewerFilter filter) {
    interface_.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#getClass_()
	 * @generated
	 */
	public EObject getClass_() {
    if (class_.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) class_.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initClass_(EObjectFlatComboSettings)
	 */
	public void initClass_(EObjectFlatComboSettings settings) {
		class_.setInput(settings);
		if (current != null) {
			class_.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.class_);
		if (readOnly && class_.isEnabled()) {
			class_.setEnabled(false);
			class_.setToolTipText(UmlMessages.Operation_ReadOnly);
		} else if (!readOnly && !class_.isEnabled()) {
			class_.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setClass_(EObject newValue)
	 * @generated
	 */
	public void setClass_(EObject newValue) {
    if (newValue != null) {
      class_.setSelection(new StructuredSelection(newValue));
    } else {
      class_.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.class_);
    if (readOnly && class_.isEnabled()) {
      class_.setEnabled(false);
      class_.setToolTipText(UmlMessages.Operation_ReadOnly);
    } else if (!readOnly && !class_.isEnabled()) {
      class_.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setClass_ButtonMode(ButtonsModeEnum newValue)
	 */
	public void setClass_ButtonMode(ButtonsModeEnum newValue) {
		class_.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addFilterClass_(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClass_(ViewerFilter filter) {
    class_.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addBusinessFilterClass_(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClass_(ViewerFilter filter) {
    class_.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#getIsQuery()
	 * @generated
	 */
	public Boolean getIsQuery() {
    return Boolean.valueOf(isQuery.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setIsQuery(Boolean newValue)
	 * @generated
	 */
	public void setIsQuery(Boolean newValue) {
    if (newValue != null) {
      isQuery.setSelection(newValue.booleanValue());
    } else {
      isQuery.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.isQuery);
    if (readOnly && isQuery.isEnabled()) {
      isQuery.setEnabled(false);
      isQuery.setToolTipText(UmlMessages.Operation_ReadOnly);
    } else if (!readOnly && !isQuery.isEnabled()) {
      isQuery.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initPrecondition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initPrecondition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		precondition.setContentProvider(contentProvider);
		precondition.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.precondition);
		if (readOnly && precondition.getTable().isEnabled()) {
			precondition.setEnabled(false);
			precondition.setToolTipText(UmlMessages.Operation_ReadOnly);
		} else if (!readOnly && !precondition.getTable().isEnabled()) {
			precondition.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#updatePrecondition()
	 * @generated
	 */
	public void updatePrecondition() {
  precondition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addFilterPrecondition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToPrecondition(ViewerFilter filter) {
    preconditionFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addBusinessFilterPrecondition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToPrecondition(ViewerFilter filter) {
    preconditionBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#isContainedInPreconditionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInPreconditionTable(EObject element) {
    return ((ReferencesTableSettings)precondition.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initPostcondition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initPostcondition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		postcondition.setContentProvider(contentProvider);
		postcondition.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.postcondition);
		if (readOnly && postcondition.getTable().isEnabled()) {
			postcondition.setEnabled(false);
			postcondition.setToolTipText(UmlMessages.Operation_ReadOnly);
		} else if (!readOnly && !postcondition.getTable().isEnabled()) {
			postcondition.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#updatePostcondition()
	 * @generated
	 */
	public void updatePostcondition() {
  postcondition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addFilterPostcondition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToPostcondition(ViewerFilter filter) {
    postconditionFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addBusinessFilterPostcondition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToPostcondition(ViewerFilter filter) {
    postconditionBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#isContainedInPostconditionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInPostconditionTable(EObject element) {
    return ((ReferencesTableSettings)postcondition.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initRedefinedOperation(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedOperation(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedOperation.setContentProvider(contentProvider);
		redefinedOperation.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.redefinedOperation);
		if (readOnly && redefinedOperation.getTable().isEnabled()) {
			redefinedOperation.setEnabled(false);
			redefinedOperation.setToolTipText(UmlMessages.Operation_ReadOnly);
		} else if (!readOnly && !redefinedOperation.getTable().isEnabled()) {
			redefinedOperation.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#updateRedefinedOperation()
	 * @generated
	 */
	public void updateRedefinedOperation() {
  redefinedOperation.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addFilterRedefinedOperation(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedOperation(ViewerFilter filter) {
    redefinedOperationFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addBusinessFilterRedefinedOperation(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedOperation(ViewerFilter filter) {
    redefinedOperationBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#isContainedInRedefinedOperationTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRedefinedOperationTable(EObject element) {
    return ((ReferencesTableSettings)redefinedOperation.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#getDatatype()
	 * @generated
	 */
	public EObject getDatatype() {
    if (datatype.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) datatype.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initDatatype(EObjectFlatComboSettings)
	 */
	public void initDatatype(EObjectFlatComboSettings settings) {
		datatype.setInput(settings);
		if (current != null) {
			datatype.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.datatype);
		if (readOnly && datatype.isEnabled()) {
			datatype.setEnabled(false);
			datatype.setToolTipText(UmlMessages.Operation_ReadOnly);
		} else if (!readOnly && !datatype.isEnabled()) {
			datatype.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setDatatype(EObject newValue)
	 * @generated
	 */
	public void setDatatype(EObject newValue) {
    if (newValue != null) {
      datatype.setSelection(new StructuredSelection(newValue));
    } else {
      datatype.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.datatype);
    if (readOnly && datatype.isEnabled()) {
      datatype.setEnabled(false);
      datatype.setToolTipText(UmlMessages.Operation_ReadOnly);
    } else if (!readOnly && !datatype.isEnabled()) {
      datatype.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setDatatypeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDatatypeButtonMode(ButtonsModeEnum newValue) {
		datatype.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addFilterDatatype(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToDatatype(ViewerFilter filter) {
    datatype.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addBusinessFilterDatatype(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToDatatype(ViewerFilter filter) {
    datatype.addBusinessRuleFilter(filter);
  }

	/**
   * {@inheritDoc}
   * 
   * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#getBodyCondition()
   * @generated
   */
  public EObject getBodyCondition() {
    if (bodyCondition.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) bodyCondition.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

  /**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initBodyCondition(EObjectFlatComboSettings)
	 */
	public void initBodyCondition(EObjectFlatComboSettings settings) {
		bodyCondition.setInput(settings);
		if (current != null) {
			bodyCondition.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.bodyCondition);
		if (readOnly && bodyCondition.isEnabled()) {
			bodyCondition.setEnabled(false);
			bodyCondition.setToolTipText(UmlMessages.Operation_ReadOnly);
		} else if (!readOnly && !bodyCondition.isEnabled()) {
			bodyCondition.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setBodyCondition(EObject newValue)
	 * @generated
	 */
	public void setBodyCondition(EObject newValue) {
    if (newValue != null) {
      bodyCondition.setSelection(new StructuredSelection(newValue));
    } else {
      bodyCondition.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.bodyCondition);
    if (readOnly && bodyCondition.isEnabled()) {
      bodyCondition.setEnabled(false);
      bodyCondition.setToolTipText(UmlMessages.Operation_ReadOnly);
    } else if (!readOnly && !bodyCondition.isEnabled()) {
      bodyCondition.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#setBodyConditionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setBodyConditionButtonMode(ButtonsModeEnum newValue) {
		bodyCondition.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addFilterBodyCondition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToBodyCondition(ViewerFilter filter) {
    bodyCondition.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addBusinessFilterBodyCondition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToBodyCondition(ViewerFilter filter) {
    bodyCondition.addBusinessRuleFilter(filter);
  }






	/**
   * {@inheritDoc}
   * 
   * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#initParameters(EObject current, EReference containingFeature, EReference feature)
   */
  public void initParameters(ReferencesTableSettings settings) {
    if (current.eResource() != null && current.eResource().getResourceSet() != null)
      this.resourceSet = current.eResource().getResourceSet();
    ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
    parameters.setContentProvider(contentProvider);
    parameters.setInput(settings);
    boolean readOnly = isReadOnly(UmlViewsRepository.Operation.Properties.parameters);
    if (readOnly && parameters.isEnabled()) {
      parameters.setEnabled(false);
      parameters.setToolTipText(UmlMessages.Operation_ReadOnly);
    } else if (!readOnly && !parameters.isEnabled()) {
      parameters.setEnabled(true);
    }	
    
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#updateParameters()
   * @generated
   */
  public void updateParameters() {
  parameters.refresh();
}

  /**
   * {@inheritDoc}
   * 
   * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addFilterParameters(ViewerFilter filter)
   * @generated
   */
  public void addFilterToParameters(ViewerFilter filter) {
    parametersFilters.add(filter);
    if (this.parameters != null) {
      this.parameters.addFilter(filter);
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#addBusinessFilterParameters(ViewerFilter filter)
   * @generated
   */
  public void addBusinessFilterToParameters(ViewerFilter filter) {
    parametersBusinessFilters.add(filter);
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart#isContainedInParametersTable(EObject element)
   * @generated
   */
  public boolean isContainedInParametersTable(EObject element) {
    return ((ReferencesTableSettings)parameters.getInput()).contains(element);
  }

  /**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.Operation_Part_Title;
  }



}
