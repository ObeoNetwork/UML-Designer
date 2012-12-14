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

import org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class OperationTemplateParameterPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, OperationTemplateParameterPropertiesEditionPart {

	protected EObjectFlatComboViewer signature;
	protected EObjectFlatComboViewer parameteredElement;
	protected EObjectFlatComboViewer default_;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public OperationTemplateParameterPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence operationTemplateParameterStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = operationTemplateParameterStep.addStep(UmlViewsRepository.OperationTemplateParameter.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.OperationTemplateParameter.Properties.signature);
		propertiesStep.addStep(UmlViewsRepository.OperationTemplateParameter.Properties.parameteredElement);
		propertiesStep.addStep(UmlViewsRepository.OperationTemplateParameter.Properties.default_);
		
		
		composer = new PartComposer(operationTemplateParameterStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.OperationTemplateParameter.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.OperationTemplateParameter.Properties.signature) {
					return createSignatureFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.OperationTemplateParameter.Properties.parameteredElement) {
					return createParameteredElementFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.OperationTemplateParameter.Properties.default_) {
					return createDefault_FlatComboViewer(parent);
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
		propertiesGroup.setText(UmlMessages.OperationTemplateParameterPropertiesEditionPart_PropertiesGroupLabel);
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
		createDescription(parent, UmlViewsRepository.OperationTemplateParameter.Properties.signature, UmlMessages.OperationTemplateParameterPropertiesEditionPart_SignatureLabel);
		signature = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.OperationTemplateParameter.Properties.signature, UmlViewsRepository.SWT_KIND));
		signature.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		signature.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationTemplateParameterPropertiesEditionPartImpl.this, UmlViewsRepository.OperationTemplateParameter.Properties.signature, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getSignature()));
			}

		});
		GridData signatureData = new GridData(GridData.FILL_HORIZONTAL);
		signature.setLayoutData(signatureData);
		signature.setID(UmlViewsRepository.OperationTemplateParameter.Properties.signature);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.OperationTemplateParameter.Properties.signature, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createParameteredElementFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.OperationTemplateParameter.Properties.parameteredElement, UmlMessages.OperationTemplateParameterPropertiesEditionPart_ParameteredElementLabel);
		parameteredElement = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.OperationTemplateParameter.Properties.parameteredElement, UmlViewsRepository.SWT_KIND));
		parameteredElement.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		parameteredElement.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationTemplateParameterPropertiesEditionPartImpl.this, UmlViewsRepository.OperationTemplateParameter.Properties.parameteredElement, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getParameteredElement()));
			}

		});
		GridData parameteredElementData = new GridData(GridData.FILL_HORIZONTAL);
		parameteredElement.setLayoutData(parameteredElementData);
		parameteredElement.setID(UmlViewsRepository.OperationTemplateParameter.Properties.parameteredElement);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.OperationTemplateParameter.Properties.parameteredElement, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createDefault_FlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.OperationTemplateParameter.Properties.default_, UmlMessages.OperationTemplateParameterPropertiesEditionPart_Default_Label);
		default_ = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.OperationTemplateParameter.Properties.default_, UmlViewsRepository.SWT_KIND));
		default_.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		default_.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationTemplateParameterPropertiesEditionPartImpl.this, UmlViewsRepository.OperationTemplateParameter.Properties.default_, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getDefault_()));
			}

		});
		GridData default_Data = new GridData(GridData.FILL_HORIZONTAL);
		default_.setLayoutData(default_Data);
		default_.setID(UmlViewsRepository.OperationTemplateParameter.Properties.default_);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.OperationTemplateParameter.Properties.default_, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#getSignature()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#initSignature(EObjectFlatComboSettings)
	 */
	public void initSignature(EObjectFlatComboSettings settings) {
		signature.setInput(settings);
		if (current != null) {
			signature.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.OperationTemplateParameter.Properties.signature);
		if (readOnly && signature.isEnabled()) {
			signature.setEnabled(false);
			signature.setToolTipText(UmlMessages.OperationTemplateParameter_ReadOnly);
		} else if (!readOnly && !signature.isEnabled()) {
			signature.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#setSignature(EObject newValue)
	 * @generated
	 */
	public void setSignature(EObject newValue) {
		if (newValue != null) {
			signature.setSelection(new StructuredSelection(newValue));
		} else {
			signature.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.OperationTemplateParameter.Properties.signature);
		if (readOnly && signature.isEnabled()) {
			signature.setEnabled(false);
			signature.setToolTipText(UmlMessages.OperationTemplateParameter_ReadOnly);
		} else if (!readOnly && !signature.isEnabled()) {
			signature.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#setSignatureButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSignatureButtonMode(ButtonsModeEnum newValue) {
		signature.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#addFilterSignature(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSignature(ViewerFilter filter) {
		signature.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#addBusinessFilterSignature(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSignature(ViewerFilter filter) {
		signature.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#getParameteredElement()
	 * @generated
	 */
	public EObject getParameteredElement() {
		if (parameteredElement.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) parameteredElement.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#initParameteredElement(EObjectFlatComboSettings)
	 */
	public void initParameteredElement(EObjectFlatComboSettings settings) {
		parameteredElement.setInput(settings);
		if (current != null) {
			parameteredElement.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.OperationTemplateParameter.Properties.parameteredElement);
		if (readOnly && parameteredElement.isEnabled()) {
			parameteredElement.setEnabled(false);
			parameteredElement.setToolTipText(UmlMessages.OperationTemplateParameter_ReadOnly);
		} else if (!readOnly && !parameteredElement.isEnabled()) {
			parameteredElement.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#setParameteredElement(EObject newValue)
	 * @generated
	 */
	public void setParameteredElement(EObject newValue) {
		if (newValue != null) {
			parameteredElement.setSelection(new StructuredSelection(newValue));
		} else {
			parameteredElement.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.OperationTemplateParameter.Properties.parameteredElement);
		if (readOnly && parameteredElement.isEnabled()) {
			parameteredElement.setEnabled(false);
			parameteredElement.setToolTipText(UmlMessages.OperationTemplateParameter_ReadOnly);
		} else if (!readOnly && !parameteredElement.isEnabled()) {
			parameteredElement.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#setParameteredElementButtonMode(ButtonsModeEnum newValue)
	 */
	public void setParameteredElementButtonMode(ButtonsModeEnum newValue) {
		parameteredElement.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#addFilterParameteredElement(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToParameteredElement(ViewerFilter filter) {
		parameteredElement.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#addBusinessFilterParameteredElement(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToParameteredElement(ViewerFilter filter) {
		parameteredElement.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#getDefault_()
	 * @generated
	 */
	public EObject getDefault_() {
		if (default_.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) default_.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#initDefault_(EObjectFlatComboSettings)
	 */
	public void initDefault_(EObjectFlatComboSettings settings) {
		default_.setInput(settings);
		if (current != null) {
			default_.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.OperationTemplateParameter.Properties.default_);
		if (readOnly && default_.isEnabled()) {
			default_.setEnabled(false);
			default_.setToolTipText(UmlMessages.OperationTemplateParameter_ReadOnly);
		} else if (!readOnly && !default_.isEnabled()) {
			default_.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#setDefault_(EObject newValue)
	 * @generated
	 */
	public void setDefault_(EObject newValue) {
		if (newValue != null) {
			default_.setSelection(new StructuredSelection(newValue));
		} else {
			default_.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.OperationTemplateParameter.Properties.default_);
		if (readOnly && default_.isEnabled()) {
			default_.setEnabled(false);
			default_.setToolTipText(UmlMessages.OperationTemplateParameter_ReadOnly);
		} else if (!readOnly && !default_.isEnabled()) {
			default_.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#setDefault_ButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDefault_ButtonMode(ButtonsModeEnum newValue) {
		default_.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#addFilterDefault_(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToDefault_(ViewerFilter filter) {
		default_.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart#addBusinessFilterDefault_(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToDefault_(ViewerFilter filter) {
		default_.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.OperationTemplateParameter_Part_Title;
	}



}
