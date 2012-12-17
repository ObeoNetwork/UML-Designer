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

import org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateBindingPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class TemplateBindingPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, TemplateBindingPropertiesEditionPart {

	protected EObjectFlatComboViewer signature;
	protected EObjectFlatComboViewer boundElement;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public TemplateBindingPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence templateBindingStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = templateBindingStep.addStep(UmlViewsRepository.TemplateBinding.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.TemplateBinding.Properties.signature);
    propertiesStep.addStep(UmlViewsRepository.TemplateBinding.Properties.boundElement);
    
    
    composer = new PartComposer(templateBindingStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.TemplateBinding.Properties.class) {
          return createPropertiesGroup(parent);
        }
        if (key == UmlViewsRepository.TemplateBinding.Properties.signature) {
          return createSignatureFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.TemplateBinding.Properties.boundElement) {
          return createBoundElementFlatComboViewer(parent);
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
    propertiesGroup.setText(UmlMessages.TemplateBindingPropertiesEditionPart_PropertiesGroupLabel);
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
	protected Composite createSignatureFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.TemplateBinding.Properties.signature, UmlMessages.TemplateBindingPropertiesEditionPart_SignatureLabel);
    signature = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateBinding.Properties.signature, UmlViewsRepository.SWT_KIND));
    signature.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    signature.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(TemplateBindingPropertiesEditionPartImpl.this, UmlViewsRepository.TemplateBinding.Properties.signature, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getSignature()));
      }

    });
    GridData signatureData = new GridData(GridData.FILL_HORIZONTAL);
    signature.setLayoutData(signatureData);
    signature.setID(UmlViewsRepository.TemplateBinding.Properties.signature);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.TemplateBinding.Properties.signature, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createBoundElementFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.TemplateBinding.Properties.boundElement, UmlMessages.TemplateBindingPropertiesEditionPart_BoundElementLabel);
    boundElement = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateBinding.Properties.boundElement, UmlViewsRepository.SWT_KIND));
    boundElement.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    boundElement.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(TemplateBindingPropertiesEditionPartImpl.this, UmlViewsRepository.TemplateBinding.Properties.boundElement, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getBoundElement()));
      }

    });
    GridData boundElementData = new GridData(GridData.FILL_HORIZONTAL);
    boundElement.setLayoutData(boundElementData);
    boundElement.setID(UmlViewsRepository.TemplateBinding.Properties.boundElement);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.TemplateBinding.Properties.boundElement, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateBindingPropertiesEditionPart#getSignature()
	 * @generated
	 */
	public EObject getSignature() {
    if (signature.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) signature.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateBindingPropertiesEditionPart#initSignature(EObjectFlatComboSettings)
	 */
	public void initSignature(EObjectFlatComboSettings settings) {
		signature.setInput(settings);
		if (current != null) {
			signature.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.TemplateBinding.Properties.signature);
		if (readOnly && signature.isEnabled()) {
			signature.setEnabled(false);
			signature.setToolTipText(UmlMessages.TemplateBinding_ReadOnly);
		} else if (!readOnly && !signature.isEnabled()) {
			signature.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateBindingPropertiesEditionPart#setSignature(EObject newValue)
	 * @generated
	 */
	public void setSignature(EObject newValue) {
    if (newValue != null) {
      signature.setSelection(new StructuredSelection(newValue));
    } else {
      signature.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.TemplateBinding.Properties.signature);
    if (readOnly && signature.isEnabled()) {
      signature.setEnabled(false);
      signature.setToolTipText(UmlMessages.TemplateBinding_ReadOnly);
    } else if (!readOnly && !signature.isEnabled()) {
      signature.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateBindingPropertiesEditionPart#setSignatureButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSignatureButtonMode(ButtonsModeEnum newValue) {
		signature.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateBindingPropertiesEditionPart#addFilterSignature(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSignature(ViewerFilter filter) {
    signature.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateBindingPropertiesEditionPart#addBusinessFilterSignature(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSignature(ViewerFilter filter) {
    signature.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateBindingPropertiesEditionPart#getBoundElement()
	 * @generated
	 */
	public EObject getBoundElement() {
    if (boundElement.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) boundElement.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateBindingPropertiesEditionPart#initBoundElement(EObjectFlatComboSettings)
	 */
	public void initBoundElement(EObjectFlatComboSettings settings) {
		boundElement.setInput(settings);
		if (current != null) {
			boundElement.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.TemplateBinding.Properties.boundElement);
		if (readOnly && boundElement.isEnabled()) {
			boundElement.setEnabled(false);
			boundElement.setToolTipText(UmlMessages.TemplateBinding_ReadOnly);
		} else if (!readOnly && !boundElement.isEnabled()) {
			boundElement.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateBindingPropertiesEditionPart#setBoundElement(EObject newValue)
	 * @generated
	 */
	public void setBoundElement(EObject newValue) {
    if (newValue != null) {
      boundElement.setSelection(new StructuredSelection(newValue));
    } else {
      boundElement.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.TemplateBinding.Properties.boundElement);
    if (readOnly && boundElement.isEnabled()) {
      boundElement.setEnabled(false);
      boundElement.setToolTipText(UmlMessages.TemplateBinding_ReadOnly);
    } else if (!readOnly && !boundElement.isEnabled()) {
      boundElement.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateBindingPropertiesEditionPart#setBoundElementButtonMode(ButtonsModeEnum newValue)
	 */
	public void setBoundElementButtonMode(ButtonsModeEnum newValue) {
		boundElement.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateBindingPropertiesEditionPart#addFilterBoundElement(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToBoundElement(ViewerFilter filter) {
    boundElement.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateBindingPropertiesEditionPart#addBusinessFilterBoundElement(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToBoundElement(ViewerFilter filter) {
    boundElement.addBusinessRuleFilter(filter);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.TemplateBinding_Part_Title;
  }



}
