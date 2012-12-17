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

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;

import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;

import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.SWT;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import org.obeonetwork.dsl.uml2.properties.uml.parts.SlotPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class SlotPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, SlotPropertiesEditionPart {

	protected EObjectFlatComboViewer definingFeature;
	protected EObjectFlatComboViewer owningInstance;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public SlotPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence slotStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = slotStep.addStep(UmlViewsRepository.Slot.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.Slot.Properties.definingFeature);
    propertiesStep.addStep(UmlViewsRepository.Slot.Properties.owningInstance);
    
    
    composer = new PartComposer(slotStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.Slot.Properties.class) {
          return createPropertiesGroup(parent);
        }
        if (key == UmlViewsRepository.Slot.Properties.definingFeature) {
          return createDefiningFeatureFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.Slot.Properties.owningInstance) {
          return createOwningInstanceFlatComboViewer(parent);
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
    propertiesGroup.setText(UmlMessages.SlotPropertiesEditionPart_PropertiesGroupLabel);
    GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
    propertiesGroupData.horizontalSpan = 3;
    propertiesGroup.setLayoutData(propertiesGroupData);
    GridLayout propertiesGroupLayout = new GridLayout();
    propertiesGroupLayout.numColumns = 3;
    propertiesGroup.setLayout(propertiesGroupLayout);
    return propertiesGroup;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createDefiningFeatureFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.Slot.Properties.definingFeature, UmlMessages.SlotPropertiesEditionPart_DefiningFeatureLabel);
    definingFeature = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Slot.Properties.definingFeature, UmlViewsRepository.SWT_KIND));
    definingFeature.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    definingFeature.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SlotPropertiesEditionPartImpl.this, UmlViewsRepository.Slot.Properties.definingFeature, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getDefiningFeature()));
      }

    });
    GridData definingFeatureData = new GridData(GridData.FILL_HORIZONTAL);
    definingFeature.setLayoutData(definingFeatureData);
    definingFeature.setID(UmlViewsRepository.Slot.Properties.definingFeature);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Slot.Properties.definingFeature, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createOwningInstanceFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.Slot.Properties.owningInstance, UmlMessages.SlotPropertiesEditionPart_OwningInstanceLabel);
    owningInstance = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Slot.Properties.owningInstance, UmlViewsRepository.SWT_KIND));
    owningInstance.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    owningInstance.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SlotPropertiesEditionPartImpl.this, UmlViewsRepository.Slot.Properties.owningInstance, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getOwningInstance()));
      }

    });
    GridData owningInstanceData = new GridData(GridData.FILL_HORIZONTAL);
    owningInstance.setLayoutData(owningInstanceData);
    owningInstance.setID(UmlViewsRepository.Slot.Properties.owningInstance);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Slot.Properties.owningInstance, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
