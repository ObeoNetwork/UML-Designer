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

import org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ParameterPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, ParameterPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer type;
	protected EObjectFlatComboViewer owningTemplateParameter;
	protected EObjectFlatComboViewer templateParameter;
	protected Button isOrdered;
	protected Button isUnique;
	protected ReferencesTable parameterSet;
	protected List<ViewerFilter> parameterSetBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> parameterSetFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer operation;
	protected EMFComboViewer direction;
	protected Button isException;
	protected Button isStream;
	protected EMFComboViewer effect;



	/**
	 * For {@link ISection} use only.
	 */
	public ParameterPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ParameterPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence parameterStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = parameterStep.addStep(UmlViewsRepository.Parameter.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.Parameter.Properties.name);
    propertiesStep.addStep(UmlViewsRepository.Parameter.Properties.visibility);
    propertiesStep.addStep(UmlViewsRepository.Parameter.Properties.clientDependency);
    propertiesStep.addStep(UmlViewsRepository.Parameter.Properties.type);
    propertiesStep.addStep(UmlViewsRepository.Parameter.Properties.owningTemplateParameter);
    propertiesStep.addStep(UmlViewsRepository.Parameter.Properties.templateParameter);
    propertiesStep.addStep(UmlViewsRepository.Parameter.Properties.isOrdered);
    propertiesStep.addStep(UmlViewsRepository.Parameter.Properties.isUnique);
    propertiesStep.addStep(UmlViewsRepository.Parameter.Properties.parameterSet);
    propertiesStep.addStep(UmlViewsRepository.Parameter.Properties.operation);
    propertiesStep.addStep(UmlViewsRepository.Parameter.Properties.direction);
    propertiesStep.addStep(UmlViewsRepository.Parameter.Properties.isException);
    propertiesStep.addStep(UmlViewsRepository.Parameter.Properties.isStream);
    propertiesStep.addStep(UmlViewsRepository.Parameter.Properties.effect);
    
    
    composer = new PartComposer(parameterStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.Parameter.Properties.class) {
          return createPropertiesGroup(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Parameter.Properties.name) {
          return createNameText(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Parameter.Properties.visibility) {
          return createVisibilityEMFComboViewer(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Parameter.Properties.clientDependency) {
          return createClientDependencyReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Parameter.Properties.type) {
          return createTypeFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.Parameter.Properties.owningTemplateParameter) {
          return createOwningTemplateParameterFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.Parameter.Properties.templateParameter) {
          return createTemplateParameterFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.Parameter.Properties.isOrdered) {
          return createIsOrderedCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Parameter.Properties.isUnique) {
          return createIsUniqueCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Parameter.Properties.parameterSet) {
          return createParameterSetReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Parameter.Properties.operation) {
          return createOperationFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.Parameter.Properties.direction) {
          return createDirectionEMFComboViewer(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Parameter.Properties.isException) {
          return createIsExceptionCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Parameter.Properties.isStream) {
          return createIsStreamCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Parameter.Properties.effect) {
          return createEffectEMFComboViewer(widgetFactory, parent);
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
    propertiesSection.setText(UmlMessages.ParameterPropertiesEditionPart_PropertiesGroupLabel);
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
    createDescription(parent, UmlViewsRepository.Parameter.Properties.name, UmlMessages.ParameterPropertiesEditionPart_NameLabel);
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
              ParameterPropertiesEditionPartForm.this,
              UmlViewsRepository.Parameter.Properties.name,
              PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
          propertiesEditionComponent
              .firePropertiesChanged(new PropertiesEditionEvent(
                  ParameterPropertiesEditionPartForm.this,
                  UmlViewsRepository.Parameter.Properties.name,
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
                  ParameterPropertiesEditionPartForm.this,
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
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
        }
      }
    });
    EditingUtils.setID(name, UmlViewsRepository.Parameter.Properties.name);
    EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameter.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
    createDescription(parent, UmlViewsRepository.Parameter.Properties.visibility, UmlMessages.ParameterPropertiesEditionPart_VisibilityLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
      }

    });
    visibility.setID(UmlViewsRepository.Parameter.Properties.visibility);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameter.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.clientDependency = new ReferencesTable(getDescription(UmlViewsRepository.Parameter.Properties.clientDependency, UmlMessages.ParameterPropertiesEditionPart_ClientDependencyLabel), new ReferencesTableListener	() {
      public void handleAdd() { addClientDependency(); }
      public void handleEdit(EObject element) { editClientDependency(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromClientDependency(element); }
      public void navigateTo(EObject element) { }
    });
    this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameter.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
    this.clientDependency.createControls(parent, widgetFactory);
    this.clientDependency.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
    clientDependencyData.horizontalSpan = 3;
    this.clientDependency.setLayoutData(clientDependencyData);
    this.clientDependency.disableMove();
    clientDependency.setID(UmlViewsRepository.Parameter.Properties.clientDependency);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.clientDependency,
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
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    clientDependency.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createTypeFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.Parameter.Properties.type, UmlMessages.ParameterPropertiesEditionPart_TypeLabel);
    type = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Parameter.Properties.type, UmlViewsRepository.FORM_KIND));
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.type, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getType()));
      }

    });
    type.setID(UmlViewsRepository.Parameter.Properties.type);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameter.Properties.type, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createOwningTemplateParameterFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.Parameter.Properties.owningTemplateParameter, UmlMessages.ParameterPropertiesEditionPart_OwningTemplateParameterLabel);
    owningTemplateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Parameter.Properties.owningTemplateParameter, UmlViewsRepository.FORM_KIND));
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.owningTemplateParameter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getOwningTemplateParameter()));
      }

    });
    owningTemplateParameter.setID(UmlViewsRepository.Parameter.Properties.owningTemplateParameter);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameter.Properties.owningTemplateParameter, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createTemplateParameterFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.Parameter.Properties.templateParameter, UmlMessages.ParameterPropertiesEditionPart_TemplateParameterLabel);
    templateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Parameter.Properties.templateParameter, UmlViewsRepository.FORM_KIND));
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.templateParameter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getTemplateParameter()));
      }

    });
    templateParameter.setID(UmlViewsRepository.Parameter.Properties.templateParameter);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameter.Properties.templateParameter, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createIsOrderedCheckbox(FormToolkit widgetFactory, Composite parent) {
    isOrdered = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.Parameter.Properties.isOrdered, UmlMessages.ParameterPropertiesEditionPart_IsOrderedLabel), SWT.CHECK);
    isOrdered.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.isOrdered, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isOrdered.getSelection())));
      }

    });
    GridData isOrderedData = new GridData(GridData.FILL_HORIZONTAL);
    isOrderedData.horizontalSpan = 2;
    isOrdered.setLayoutData(isOrderedData);
    EditingUtils.setID(isOrdered, UmlViewsRepository.Parameter.Properties.isOrdered);
    EditingUtils.setEEFtype(isOrdered, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameter.Properties.isOrdered, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createIsUniqueCheckbox(FormToolkit widgetFactory, Composite parent) {
    isUnique = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.Parameter.Properties.isUnique, UmlMessages.ParameterPropertiesEditionPart_IsUniqueLabel), SWT.CHECK);
    isUnique.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.isUnique, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isUnique.getSelection())));
      }

    });
    GridData isUniqueData = new GridData(GridData.FILL_HORIZONTAL);
    isUniqueData.horizontalSpan = 2;
    isUnique.setLayoutData(isUniqueData);
    EditingUtils.setID(isUnique, UmlViewsRepository.Parameter.Properties.isUnique);
    EditingUtils.setEEFtype(isUnique, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameter.Properties.isUnique, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createParameterSetReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.parameterSet = new ReferencesTable(getDescription(UmlViewsRepository.Parameter.Properties.parameterSet, UmlMessages.ParameterPropertiesEditionPart_ParameterSetLabel), new ReferencesTableListener	() {
      public void handleAdd() { addParameterSet(); }
      public void handleEdit(EObject element) { editParameterSet(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveParameterSet(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromParameterSet(element); }
      public void navigateTo(EObject element) { }
    });
    this.parameterSet.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameter.Properties.parameterSet, UmlViewsRepository.FORM_KIND));
    this.parameterSet.createControls(parent, widgetFactory);
    this.parameterSet.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.parameterSet, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData parameterSetData = new GridData(GridData.FILL_HORIZONTAL);
    parameterSetData.horizontalSpan = 3;
    this.parameterSet.setLayoutData(parameterSetData);
    this.parameterSet.disableMove();
    parameterSet.setID(UmlViewsRepository.Parameter.Properties.parameterSet);
    parameterSet.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addParameterSet() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(parameterSet.getInput(), parameterSetFilters, parameterSetBusinessFilters,
    "parameterSet", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.parameterSet,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        parameterSet.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveParameterSet(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.parameterSet, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    parameterSet.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromParameterSet(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.parameterSet, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    parameterSet.refresh();
  }

	/**
	 * @generated
	 */
	protected void editParameterSet(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        parameterSet.refresh();
      }
    }
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createOperationFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.Parameter.Properties.operation, UmlMessages.ParameterPropertiesEditionPart_OperationLabel);
    operation = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Parameter.Properties.operation, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(operation);
    operation.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData operationData = new GridData(GridData.FILL_HORIZONTAL);
    operation.setLayoutData(operationData);
    operation.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.operation, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getOperation()));
      }

    });
    operation.setID(UmlViewsRepository.Parameter.Properties.operation);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameter.Properties.operation, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createDirectionEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
    createDescription(parent, UmlViewsRepository.Parameter.Properties.direction, UmlMessages.ParameterPropertiesEditionPart_DirectionLabel);
    direction = new EMFComboViewer(parent);
    direction.setContentProvider(new ArrayContentProvider());
    direction.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
    GridData directionData = new GridData(GridData.FILL_HORIZONTAL);
    direction.getCombo().setLayoutData(directionData);
    direction.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       * 	@generated
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.direction, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getDirection()));
      }

    });
    direction.setID(UmlViewsRepository.Parameter.Properties.direction);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameter.Properties.direction, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createIsExceptionCheckbox(FormToolkit widgetFactory, Composite parent) {
    isException = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.Parameter.Properties.isException, UmlMessages.ParameterPropertiesEditionPart_IsExceptionLabel), SWT.CHECK);
    isException.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.isException, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isException.getSelection())));
      }

    });
    GridData isExceptionData = new GridData(GridData.FILL_HORIZONTAL);
    isExceptionData.horizontalSpan = 2;
    isException.setLayoutData(isExceptionData);
    EditingUtils.setID(isException, UmlViewsRepository.Parameter.Properties.isException);
    EditingUtils.setEEFtype(isException, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameter.Properties.isException, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createIsStreamCheckbox(FormToolkit widgetFactory, Composite parent) {
    isStream = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.Parameter.Properties.isStream, UmlMessages.ParameterPropertiesEditionPart_IsStreamLabel), SWT.CHECK);
    isStream.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.isStream, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isStream.getSelection())));
      }

    });
    GridData isStreamData = new GridData(GridData.FILL_HORIZONTAL);
    isStreamData.horizontalSpan = 2;
    isStream.setLayoutData(isStreamData);
    EditingUtils.setID(isStream, UmlViewsRepository.Parameter.Properties.isStream);
    EditingUtils.setEEFtype(isStream, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameter.Properties.isStream, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createEffectEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
    createDescription(parent, UmlViewsRepository.Parameter.Properties.effect, UmlMessages.ParameterPropertiesEditionPart_EffectLabel);
    effect = new EMFComboViewer(parent);
    effect.setContentProvider(new ArrayContentProvider());
    effect.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
    GridData effectData = new GridData(GridData.FILL_HORIZONTAL);
    effect.getCombo().setLayoutData(effectData);
    effect.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       * 	@generated
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParameterPropertiesEditionPartForm.this, UmlViewsRepository.Parameter.Properties.effect, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getEffect()));
      }

    });
    effect.setID(UmlViewsRepository.Parameter.Properties.effect);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameter.Properties.effect, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
    return name.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
    if (newValue != null) {
      name.setText(newValue);
    } else {
      name.setText(""); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.name);
    if (readOnly && name.isEnabled()) {
      name.setEnabled(false);
      name.setToolTipText(UmlMessages.Parameter_ReadOnly);
    } else if (!readOnly && !name.isEnabled()) {
      name.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
    Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.Parameter_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
    visibility.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.visibility);
    if (readOnly && visibility.isEnabled()) {
      visibility.setEnabled(false);
      visibility.setToolTipText(UmlMessages.Parameter_ReadOnly);
    } else if (!readOnly && !visibility.isEnabled()) {
      visibility.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.Parameter_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
  clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
    clientDependencyFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
    clientDependencyBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
    return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#getType()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#initType(EObjectFlatComboSettings)
	 */
	public void initType(EObjectFlatComboSettings settings) {
		type.setInput(settings);
		if (current != null) {
			type.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.type);
		if (readOnly && type.isEnabled()) {
			type.setEnabled(false);
			type.setToolTipText(UmlMessages.Parameter_ReadOnly);
		} else if (!readOnly && !type.isEnabled()) {
			type.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setType(EObject newValue)
	 * @generated
	 */
	public void setType(EObject newValue) {
    if (newValue != null) {
      type.setSelection(new StructuredSelection(newValue));
    } else {
      type.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.type);
    if (readOnly && type.isEnabled()) {
      type.setEnabled(false);
      type.setToolTipText(UmlMessages.Parameter_ReadOnly);
    } else if (!readOnly && !type.isEnabled()) {
      type.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setTypeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTypeButtonMode(ButtonsModeEnum newValue) {
		type.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#addFilterType(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToType(ViewerFilter filter) {
    type.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#addBusinessFilterType(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToType(ViewerFilter filter) {
    type.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#getOwningTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#initOwningTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initOwningTemplateParameter(EObjectFlatComboSettings settings) {
		owningTemplateParameter.setInput(settings);
		if (current != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.owningTemplateParameter);
		if (readOnly && owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(false);
			owningTemplateParameter.setToolTipText(UmlMessages.Parameter_ReadOnly);
		} else if (!readOnly && !owningTemplateParameter.isEnabled()) {
			owningTemplateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setOwningTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setOwningTemplateParameter(EObject newValue) {
    if (newValue != null) {
      owningTemplateParameter.setSelection(new StructuredSelection(newValue));
    } else {
      owningTemplateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.owningTemplateParameter);
    if (readOnly && owningTemplateParameter.isEnabled()) {
      owningTemplateParameter.setEnabled(false);
      owningTemplateParameter.setToolTipText(UmlMessages.Parameter_ReadOnly);
    } else if (!readOnly && !owningTemplateParameter.isEnabled()) {
      owningTemplateParameter.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		owningTemplateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#addFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter) {
    owningTemplateParameter.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#addBusinessFilterOwningTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter) {
    owningTemplateParameter.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#getTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#initTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initTemplateParameter(EObjectFlatComboSettings settings) {
		templateParameter.setInput(settings);
		if (current != null) {
			templateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.templateParameter);
		if (readOnly && templateParameter.isEnabled()) {
			templateParameter.setEnabled(false);
			templateParameter.setToolTipText(UmlMessages.Parameter_ReadOnly);
		} else if (!readOnly && !templateParameter.isEnabled()) {
			templateParameter.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setTemplateParameter(EObject newValue)
	 * @generated
	 */
	public void setTemplateParameter(EObject newValue) {
    if (newValue != null) {
      templateParameter.setSelection(new StructuredSelection(newValue));
    } else {
      templateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.templateParameter);
    if (readOnly && templateParameter.isEnabled()) {
      templateParameter.setEnabled(false);
      templateParameter.setToolTipText(UmlMessages.Parameter_ReadOnly);
    } else if (!readOnly && !templateParameter.isEnabled()) {
      templateParameter.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		templateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#addFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter) {
    templateParameter.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#addBusinessFilterTemplateParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter) {
    templateParameter.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#getIsOrdered()
	 * @generated
	 */
	public Boolean getIsOrdered() {
    return Boolean.valueOf(isOrdered.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setIsOrdered(Boolean newValue)
	 * @generated
	 */
	public void setIsOrdered(Boolean newValue) {
    if (newValue != null) {
      isOrdered.setSelection(newValue.booleanValue());
    } else {
      isOrdered.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.isOrdered);
    if (readOnly && isOrdered.isEnabled()) {
      isOrdered.setEnabled(false);
      isOrdered.setToolTipText(UmlMessages.Parameter_ReadOnly);
    } else if (!readOnly && !isOrdered.isEnabled()) {
      isOrdered.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#getIsUnique()
	 * @generated
	 */
	public Boolean getIsUnique() {
    return Boolean.valueOf(isUnique.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setIsUnique(Boolean newValue)
	 * @generated
	 */
	public void setIsUnique(Boolean newValue) {
    if (newValue != null) {
      isUnique.setSelection(newValue.booleanValue());
    } else {
      isUnique.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.isUnique);
    if (readOnly && isUnique.isEnabled()) {
      isUnique.setEnabled(false);
      isUnique.setToolTipText(UmlMessages.Parameter_ReadOnly);
    } else if (!readOnly && !isUnique.isEnabled()) {
      isUnique.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#initParameterSet(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initParameterSet(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		parameterSet.setContentProvider(contentProvider);
		parameterSet.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.parameterSet);
		if (readOnly && parameterSet.getTable().isEnabled()) {
			parameterSet.setEnabled(false);
			parameterSet.setToolTipText(UmlMessages.Parameter_ReadOnly);
		} else if (!readOnly && !parameterSet.getTable().isEnabled()) {
			parameterSet.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#updateParameterSet()
	 * @generated
	 */
	public void updateParameterSet() {
  parameterSet.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#addFilterParameterSet(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToParameterSet(ViewerFilter filter) {
    parameterSetFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#addBusinessFilterParameterSet(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToParameterSet(ViewerFilter filter) {
    parameterSetBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#isContainedInParameterSetTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInParameterSetTable(EObject element) {
    return ((ReferencesTableSettings)parameterSet.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#getOperation()
	 * @generated
	 */
	public EObject getOperation() {
    if (operation.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) operation.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#initOperation(EObjectFlatComboSettings)
	 */
	public void initOperation(EObjectFlatComboSettings settings) {
		operation.setInput(settings);
		if (current != null) {
			operation.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.operation);
		if (readOnly && operation.isEnabled()) {
			operation.setEnabled(false);
			operation.setToolTipText(UmlMessages.Parameter_ReadOnly);
		} else if (!readOnly && !operation.isEnabled()) {
			operation.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setOperation(EObject newValue)
	 * @generated
	 */
	public void setOperation(EObject newValue) {
    if (newValue != null) {
      operation.setSelection(new StructuredSelection(newValue));
    } else {
      operation.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.operation);
    if (readOnly && operation.isEnabled()) {
      operation.setEnabled(false);
      operation.setToolTipText(UmlMessages.Parameter_ReadOnly);
    } else if (!readOnly && !operation.isEnabled()) {
      operation.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setOperationButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOperationButtonMode(ButtonsModeEnum newValue) {
		operation.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#addFilterOperation(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOperation(ViewerFilter filter) {
    operation.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#addBusinessFilterOperation(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOperation(ViewerFilter filter) {
    operation.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#getDirection()
	 * @generated
	 */
	public Enumerator getDirection() {
    Enumerator selection = (Enumerator) ((StructuredSelection) direction.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#initDirection(Object input, Enumerator current)
	 */
	public void initDirection(Object input, Enumerator current) {
		direction.setInput(input);
		direction.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.direction);
		if (readOnly && direction.isEnabled()) {
			direction.setEnabled(false);
			direction.setToolTipText(UmlMessages.Parameter_ReadOnly);
		} else if (!readOnly && !direction.isEnabled()) {
			direction.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setDirection(Enumerator newValue)
	 * @generated
	 */
	public void setDirection(Enumerator newValue) {
    direction.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.direction);
    if (readOnly && direction.isEnabled()) {
      direction.setEnabled(false);
      direction.setToolTipText(UmlMessages.Parameter_ReadOnly);
    } else if (!readOnly && !direction.isEnabled()) {
      direction.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#getIsException()
	 * @generated
	 */
	public Boolean getIsException() {
    return Boolean.valueOf(isException.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setIsException(Boolean newValue)
	 * @generated
	 */
	public void setIsException(Boolean newValue) {
    if (newValue != null) {
      isException.setSelection(newValue.booleanValue());
    } else {
      isException.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.isException);
    if (readOnly && isException.isEnabled()) {
      isException.setEnabled(false);
      isException.setToolTipText(UmlMessages.Parameter_ReadOnly);
    } else if (!readOnly && !isException.isEnabled()) {
      isException.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#getIsStream()
	 * @generated
	 */
	public Boolean getIsStream() {
    return Boolean.valueOf(isStream.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setIsStream(Boolean newValue)
	 * @generated
	 */
	public void setIsStream(Boolean newValue) {
    if (newValue != null) {
      isStream.setSelection(newValue.booleanValue());
    } else {
      isStream.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.isStream);
    if (readOnly && isStream.isEnabled()) {
      isStream.setEnabled(false);
      isStream.setToolTipText(UmlMessages.Parameter_ReadOnly);
    } else if (!readOnly && !isStream.isEnabled()) {
      isStream.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#getEffect()
	 * @generated
	 */
	public Enumerator getEffect() {
    Enumerator selection = (Enumerator) ((StructuredSelection) effect.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#initEffect(Object input, Enumerator current)
	 */
	public void initEffect(Object input, Enumerator current) {
		effect.setInput(input);
		effect.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.effect);
		if (readOnly && effect.isEnabled()) {
			effect.setEnabled(false);
			effect.setToolTipText(UmlMessages.Parameter_ReadOnly);
		} else if (!readOnly && !effect.isEnabled()) {
			effect.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParameterPropertiesEditionPart#setEffect(Enumerator newValue)
	 * @generated
	 */
	public void setEffect(Enumerator newValue) {
    effect.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.Parameter.Properties.effect);
    if (readOnly && effect.isEnabled()) {
      effect.setEnabled(false);
      effect.setToolTipText(UmlMessages.Parameter_ReadOnly);
    } else if (!readOnly && !effect.isEnabled()) {
      effect.setEnabled(true);
    }	
    
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.Parameter_Part_Title;
  }



}
