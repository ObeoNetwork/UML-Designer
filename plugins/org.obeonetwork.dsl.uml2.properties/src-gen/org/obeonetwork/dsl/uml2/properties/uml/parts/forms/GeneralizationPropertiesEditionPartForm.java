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

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

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
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class GeneralizationPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, GeneralizationPropertiesEditionPart {

	protected Button isSubstitutable;
	protected EObjectFlatComboViewer general;
	protected ReferencesTable generalizationSet;
	protected List<ViewerFilter> generalizationSetBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> generalizationSetFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer specific;



	/**
	 * For {@link ISection} use only.
	 */
	public GeneralizationPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public GeneralizationPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence generalizationStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = generalizationStep.addStep(UmlViewsRepository.Generalization.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.Generalization.Properties.isSubstitutable);
    propertiesStep.addStep(UmlViewsRepository.Generalization.Properties.general);
    propertiesStep.addStep(UmlViewsRepository.Generalization.Properties.generalizationSet);
    propertiesStep.addStep(UmlViewsRepository.Generalization.Properties.specific);
    
    
    composer = new PartComposer(generalizationStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.Generalization.Properties.class) {
          return createPropertiesGroup(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Generalization.Properties.isSubstitutable) {
          return createIsSubstitutableCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Generalization.Properties.general) {
          return createGeneralFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.Generalization.Properties.generalizationSet) {
          return createGeneralizationSetReferencesTable(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Generalization.Properties.specific) {
          return createSpecificFlatComboViewer(parent, widgetFactory);
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
    propertiesSection.setText(UmlMessages.GeneralizationPropertiesEditionPart_PropertiesGroupLabel);
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
	
	protected Composite createIsSubstitutableCheckbox(FormToolkit widgetFactory, Composite parent) {
    isSubstitutable = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.Generalization.Properties.isSubstitutable, UmlMessages.GeneralizationPropertiesEditionPart_IsSubstitutableLabel), SWT.CHECK);
    isSubstitutable.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationPropertiesEditionPartForm.this, UmlViewsRepository.Generalization.Properties.isSubstitutable, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isSubstitutable.getSelection())));
      }

    });
    GridData isSubstitutableData = new GridData(GridData.FILL_HORIZONTAL);
    isSubstitutableData.horizontalSpan = 2;
    isSubstitutable.setLayoutData(isSubstitutableData);
    EditingUtils.setID(isSubstitutable, UmlViewsRepository.Generalization.Properties.isSubstitutable);
    EditingUtils.setEEFtype(isSubstitutable, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Generalization.Properties.isSubstitutable, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createGeneralFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.Generalization.Properties.general, UmlMessages.GeneralizationPropertiesEditionPart_GeneralLabel);
    general = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Generalization.Properties.general, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(general);
    general.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData generalData = new GridData(GridData.FILL_HORIZONTAL);
    general.setLayoutData(generalData);
    general.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationPropertiesEditionPartForm.this, UmlViewsRepository.Generalization.Properties.general, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getGeneral()));
      }

    });
    general.setID(UmlViewsRepository.Generalization.Properties.general);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Generalization.Properties.general, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createGeneralizationSetReferencesTable(FormToolkit widgetFactory, Composite parent) {
    this.generalizationSet = new ReferencesTable(getDescription(UmlViewsRepository.Generalization.Properties.generalizationSet, UmlMessages.GeneralizationPropertiesEditionPart_GeneralizationSetLabel), new ReferencesTableListener	() {
      public void handleAdd() { addGeneralizationSet(); }
      public void handleEdit(EObject element) { editGeneralizationSet(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveGeneralizationSet(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromGeneralizationSet(element); }
      public void navigateTo(EObject element) { }
    });
    this.generalizationSet.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Generalization.Properties.generalizationSet, UmlViewsRepository.FORM_KIND));
    this.generalizationSet.createControls(parent, widgetFactory);
    this.generalizationSet.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationPropertiesEditionPartForm.this, UmlViewsRepository.Generalization.Properties.generalizationSet, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData generalizationSetData = new GridData(GridData.FILL_HORIZONTAL);
    generalizationSetData.horizontalSpan = 3;
    this.generalizationSet.setLayoutData(generalizationSetData);
    this.generalizationSet.disableMove();
    generalizationSet.setID(UmlViewsRepository.Generalization.Properties.generalizationSet);
    generalizationSet.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addGeneralizationSet() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(generalizationSet.getInput(), generalizationSetFilters, generalizationSetBusinessFilters,
    "generalizationSet", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationPropertiesEditionPartForm.this, UmlViewsRepository.Generalization.Properties.generalizationSet,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        generalizationSet.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveGeneralizationSet(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationPropertiesEditionPartForm.this, UmlViewsRepository.Generalization.Properties.generalizationSet, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    generalizationSet.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromGeneralizationSet(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationPropertiesEditionPartForm.this, UmlViewsRepository.Generalization.Properties.generalizationSet, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    generalizationSet.refresh();
  }

	/**
	 * @generated
	 */
	protected void editGeneralizationSet(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        generalizationSet.refresh();
      }
    }
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createSpecificFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.Generalization.Properties.specific, UmlMessages.GeneralizationPropertiesEditionPart_SpecificLabel);
    specific = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Generalization.Properties.specific, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(specific);
    specific.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData specificData = new GridData(GridData.FILL_HORIZONTAL);
    specific.setLayoutData(specificData);
    specific.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationPropertiesEditionPartForm.this, UmlViewsRepository.Generalization.Properties.specific, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getSpecific()));
      }

    });
    specific.setID(UmlViewsRepository.Generalization.Properties.specific);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Generalization.Properties.specific, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#getIsSubstitutable()
	 * @generated
	 */
	public Boolean getIsSubstitutable() {
    return Boolean.valueOf(isSubstitutable.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#setIsSubstitutable(Boolean newValue)
	 * @generated
	 */
	public void setIsSubstitutable(Boolean newValue) {
    if (newValue != null) {
      isSubstitutable.setSelection(newValue.booleanValue());
    } else {
      isSubstitutable.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Generalization.Properties.isSubstitutable);
    if (readOnly && isSubstitutable.isEnabled()) {
      isSubstitutable.setEnabled(false);
      isSubstitutable.setToolTipText(UmlMessages.Generalization_ReadOnly);
    } else if (!readOnly && !isSubstitutable.isEnabled()) {
      isSubstitutable.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#getGeneral()
	 * @generated
	 */
	public EObject getGeneral() {
    if (general.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) general.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#initGeneral(EObjectFlatComboSettings)
	 */
	public void initGeneral(EObjectFlatComboSettings settings) {
		general.setInput(settings);
		if (current != null) {
			general.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Generalization.Properties.general);
		if (readOnly && general.isEnabled()) {
			general.setEnabled(false);
			general.setToolTipText(UmlMessages.Generalization_ReadOnly);
		} else if (!readOnly && !general.isEnabled()) {
			general.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#setGeneral(EObject newValue)
	 * @generated
	 */
	public void setGeneral(EObject newValue) {
    if (newValue != null) {
      general.setSelection(new StructuredSelection(newValue));
    } else {
      general.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Generalization.Properties.general);
    if (readOnly && general.isEnabled()) {
      general.setEnabled(false);
      general.setToolTipText(UmlMessages.Generalization_ReadOnly);
    } else if (!readOnly && !general.isEnabled()) {
      general.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#setGeneralButtonMode(ButtonsModeEnum newValue)
	 */
	public void setGeneralButtonMode(ButtonsModeEnum newValue) {
		general.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#addFilterGeneral(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToGeneral(ViewerFilter filter) {
    general.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#addBusinessFilterGeneral(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToGeneral(ViewerFilter filter) {
    general.addBusinessRuleFilter(filter);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#initGeneralizationSet(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initGeneralizationSet(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		generalizationSet.setContentProvider(contentProvider);
		generalizationSet.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Generalization.Properties.generalizationSet);
		if (readOnly && generalizationSet.getTable().isEnabled()) {
			generalizationSet.setEnabled(false);
			generalizationSet.setToolTipText(UmlMessages.Generalization_ReadOnly);
		} else if (!readOnly && !generalizationSet.getTable().isEnabled()) {
			generalizationSet.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#updateGeneralizationSet()
	 * @generated
	 */
	public void updateGeneralizationSet() {
  generalizationSet.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#addFilterGeneralizationSet(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToGeneralizationSet(ViewerFilter filter) {
    generalizationSetFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#addBusinessFilterGeneralizationSet(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToGeneralizationSet(ViewerFilter filter) {
    generalizationSetBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#isContainedInGeneralizationSetTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInGeneralizationSetTable(EObject element) {
    return ((ReferencesTableSettings)generalizationSet.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#getSpecific()
	 * @generated
	 */
	public EObject getSpecific() {
    if (specific.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) specific.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#initSpecific(EObjectFlatComboSettings)
	 */
	public void initSpecific(EObjectFlatComboSettings settings) {
		specific.setInput(settings);
		if (current != null) {
			specific.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Generalization.Properties.specific);
		if (readOnly && specific.isEnabled()) {
			specific.setEnabled(false);
			specific.setToolTipText(UmlMessages.Generalization_ReadOnly);
		} else if (!readOnly && !specific.isEnabled()) {
			specific.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#setSpecific(EObject newValue)
	 * @generated
	 */
	public void setSpecific(EObject newValue) {
    if (newValue != null) {
      specific.setSelection(new StructuredSelection(newValue));
    } else {
      specific.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Generalization.Properties.specific);
    if (readOnly && specific.isEnabled()) {
      specific.setEnabled(false);
      specific.setToolTipText(UmlMessages.Generalization_ReadOnly);
    } else if (!readOnly && !specific.isEnabled()) {
      specific.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#setSpecificButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSpecificButtonMode(ButtonsModeEnum newValue) {
		specific.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#addFilterSpecific(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSpecific(ViewerFilter filter) {
    specific.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationPropertiesEditionPart#addBusinessFilterSpecific(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSpecific(ViewerFilter filter) {
    specific.addBusinessRuleFilter(filter);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.Generalization_Part_Title;
  }



}
