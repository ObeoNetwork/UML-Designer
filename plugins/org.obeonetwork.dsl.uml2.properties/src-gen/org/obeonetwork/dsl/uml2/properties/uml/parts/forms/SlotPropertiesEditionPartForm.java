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

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.part.impl.SectionPropertiesEditingPart;

import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;

import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import org.obeonetwork.dsl.uml2.properties.uml.parts.SlotPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class SlotPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, SlotPropertiesEditionPart {

	protected EObjectFlatComboViewer definingFeature;
	protected EObjectFlatComboViewer owningInstance;



	/**
	 * For {@link ISection} use only.
	 */
	public SlotPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public SlotPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence slotStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = slotStep.addStep(UmlViewsRepository.Slot.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.Slot.Properties.definingFeature);
    propertiesStep.addStep(UmlViewsRepository.Slot.Properties.owningInstance);
    
    
    composer = new PartComposer(slotStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.Slot.Properties.class) {
          return createPropertiesGroup(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.Slot.Properties.definingFeature) {
          return createDefiningFeatureFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.Slot.Properties.owningInstance) {
          return createOwningInstanceFlatComboViewer(parent, widgetFactory);
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
    propertiesSection.setText(UmlMessages.SlotPropertiesEditionPart_PropertiesGroupLabel);
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
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createDefiningFeatureFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.Slot.Properties.definingFeature, UmlMessages.SlotPropertiesEditionPart_DefiningFeatureLabel);
    definingFeature = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Slot.Properties.definingFeature, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(definingFeature);
    definingFeature.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData definingFeatureData = new GridData(GridData.FILL_HORIZONTAL);
    definingFeature.setLayoutData(definingFeatureData);
    definingFeature.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SlotPropertiesEditionPartForm.this, UmlViewsRepository.Slot.Properties.definingFeature, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getDefiningFeature()));
      }

    });
    definingFeature.setID(UmlViewsRepository.Slot.Properties.definingFeature);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Slot.Properties.definingFeature, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createOwningInstanceFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.Slot.Properties.owningInstance, UmlMessages.SlotPropertiesEditionPart_OwningInstanceLabel);
    owningInstance = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Slot.Properties.owningInstance, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(owningInstance);
    owningInstance.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData owningInstanceData = new GridData(GridData.FILL_HORIZONTAL);
    owningInstance.setLayoutData(owningInstanceData);
    owningInstance.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SlotPropertiesEditionPartForm.this, UmlViewsRepository.Slot.Properties.owningInstance, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getOwningInstance()));
      }

    });
    owningInstance.setID(UmlViewsRepository.Slot.Properties.owningInstance);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Slot.Properties.owningInstance, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SlotPropertiesEditionPart#getDefiningFeature()
	 * @generated
	 */
	public EObject getDefiningFeature() {
    if (definingFeature.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) definingFeature.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SlotPropertiesEditionPart#initDefiningFeature(EObjectFlatComboSettings)
	 */
	public void initDefiningFeature(EObjectFlatComboSettings settings) {
		definingFeature.setInput(settings);
		if (current != null) {
			definingFeature.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Slot.Properties.definingFeature);
		if (readOnly && definingFeature.isEnabled()) {
			definingFeature.setEnabled(false);
			definingFeature.setToolTipText(UmlMessages.Slot_ReadOnly);
		} else if (!readOnly && !definingFeature.isEnabled()) {
			definingFeature.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SlotPropertiesEditionPart#setDefiningFeature(EObject newValue)
	 * @generated
	 */
	public void setDefiningFeature(EObject newValue) {
    if (newValue != null) {
      definingFeature.setSelection(new StructuredSelection(newValue));
    } else {
      definingFeature.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Slot.Properties.definingFeature);
    if (readOnly && definingFeature.isEnabled()) {
      definingFeature.setEnabled(false);
      definingFeature.setToolTipText(UmlMessages.Slot_ReadOnly);
    } else if (!readOnly && !definingFeature.isEnabled()) {
      definingFeature.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SlotPropertiesEditionPart#setDefiningFeatureButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDefiningFeatureButtonMode(ButtonsModeEnum newValue) {
		definingFeature.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SlotPropertiesEditionPart#addFilterDefiningFeature(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToDefiningFeature(ViewerFilter filter) {
    definingFeature.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SlotPropertiesEditionPart#addBusinessFilterDefiningFeature(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToDefiningFeature(ViewerFilter filter) {
    definingFeature.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SlotPropertiesEditionPart#getOwningInstance()
	 * @generated
	 */
	public EObject getOwningInstance() {
    if (owningInstance.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) owningInstance.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SlotPropertiesEditionPart#initOwningInstance(EObjectFlatComboSettings)
	 */
	public void initOwningInstance(EObjectFlatComboSettings settings) {
		owningInstance.setInput(settings);
		if (current != null) {
			owningInstance.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Slot.Properties.owningInstance);
		if (readOnly && owningInstance.isEnabled()) {
			owningInstance.setEnabled(false);
			owningInstance.setToolTipText(UmlMessages.Slot_ReadOnly);
		} else if (!readOnly && !owningInstance.isEnabled()) {
			owningInstance.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SlotPropertiesEditionPart#setOwningInstance(EObject newValue)
	 * @generated
	 */
	public void setOwningInstance(EObject newValue) {
    if (newValue != null) {
      owningInstance.setSelection(new StructuredSelection(newValue));
    } else {
      owningInstance.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Slot.Properties.owningInstance);
    if (readOnly && owningInstance.isEnabled()) {
      owningInstance.setEnabled(false);
      owningInstance.setToolTipText(UmlMessages.Slot_ReadOnly);
    } else if (!readOnly && !owningInstance.isEnabled()) {
      owningInstance.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SlotPropertiesEditionPart#setOwningInstanceButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningInstanceButtonMode(ButtonsModeEnum newValue) {
		owningInstance.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SlotPropertiesEditionPart#addFilterOwningInstance(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOwningInstance(ViewerFilter filter) {
    owningInstance.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SlotPropertiesEditionPart#addBusinessFilterOwningInstance(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOwningInstance(ViewerFilter filter) {
    owningInstance.addBusinessRuleFilter(filter);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.Slot_Part_Title;
  }



}
