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

import org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ConnectableElementTemplateParameterPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, ConnectableElementTemplateParameterPropertiesEditionPart {

	protected EObjectFlatComboViewer signature;
	protected EObjectFlatComboViewer parameteredElement;
	protected EObjectFlatComboViewer default_;



	/**
	 * For {@link ISection} use only.
	 */
	public ConnectableElementTemplateParameterPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ConnectableElementTemplateParameterPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence connectableElementTemplateParameterStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = connectableElementTemplateParameterStep.addStep(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature);
		propertiesStep.addStep(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement);
		propertiesStep.addStep(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.default_);
		
		
		composer = new PartComposer(connectableElementTemplateParameterStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.ConnectableElementTemplateParameter.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature) {
					return createSignatureFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement) {
					return createParameteredElementFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ConnectableElementTemplateParameter.Properties.default_) {
					return createDefault_FlatComboViewer(parent, widgetFactory);
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
		propertiesSection.setText(UmlMessages.ConnectableElementTemplateParameterPropertiesEditionPart_PropertiesGroupLabel);
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
	protected Composite createSignatureFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature, UmlMessages.ConnectableElementTemplateParameterPropertiesEditionPart_SignatureLabel);
		signature = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(signature);
		signature.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData signatureData = new GridData(GridData.FILL_HORIZONTAL);
		signature.setLayoutData(signatureData);
		signature.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectableElementTemplateParameterPropertiesEditionPartForm.this, UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getSignature()));
			}

		});
		signature.setID(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createParameteredElementFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement, UmlMessages.ConnectableElementTemplateParameterPropertiesEditionPart_ParameteredElementLabel);
		parameteredElement = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(parameteredElement);
		parameteredElement.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData parameteredElementData = new GridData(GridData.FILL_HORIZONTAL);
		parameteredElement.setLayoutData(parameteredElementData);
		parameteredElement.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectableElementTemplateParameterPropertiesEditionPartForm.this, UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getParameteredElement()));
			}

		});
		parameteredElement.setID(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createDefault_FlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ConnectableElementTemplateParameter.Properties.default_, UmlMessages.ConnectableElementTemplateParameterPropertiesEditionPart_Default_Label);
		default_ = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.default_, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(default_);
		default_.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData default_Data = new GridData(GridData.FILL_HORIZONTAL);
		default_.setLayoutData(default_Data);
		default_.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectableElementTemplateParameterPropertiesEditionPartForm.this, UmlViewsRepository.ConnectableElementTemplateParameter.Properties.default_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getDefault_()));
			}

		});
		default_.setID(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.default_);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.default_, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#getSignature()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#initSignature(EObjectFlatComboSettings)
	 */
	public void initSignature(EObjectFlatComboSettings settings) {
		signature.setInput(settings);
		if (current != null) {
			signature.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature);
		if (readOnly && signature.isEnabled()) {
			signature.setEnabled(false);
			signature.setToolTipText(UmlMessages.ConnectableElementTemplateParameter_ReadOnly);
		} else if (!readOnly && !signature.isEnabled()) {
			signature.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#setSignature(EObject newValue)
	 * @generated
	 */
	public void setSignature(EObject newValue) {
		if (newValue != null) {
			signature.setSelection(new StructuredSelection(newValue));
		} else {
			signature.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature);
		if (readOnly && signature.isEnabled()) {
			signature.setEnabled(false);
			signature.setToolTipText(UmlMessages.ConnectableElementTemplateParameter_ReadOnly);
		} else if (!readOnly && !signature.isEnabled()) {
			signature.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#setSignatureButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSignatureButtonMode(ButtonsModeEnum newValue) {
		signature.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#addFilterSignature(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSignature(ViewerFilter filter) {
		signature.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#addBusinessFilterSignature(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSignature(ViewerFilter filter) {
		signature.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#getParameteredElement()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#initParameteredElement(EObjectFlatComboSettings)
	 */
	public void initParameteredElement(EObjectFlatComboSettings settings) {
		parameteredElement.setInput(settings);
		if (current != null) {
			parameteredElement.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement);
		if (readOnly && parameteredElement.isEnabled()) {
			parameteredElement.setEnabled(false);
			parameteredElement.setToolTipText(UmlMessages.ConnectableElementTemplateParameter_ReadOnly);
		} else if (!readOnly && !parameteredElement.isEnabled()) {
			parameteredElement.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#setParameteredElement(EObject newValue)
	 * @generated
	 */
	public void setParameteredElement(EObject newValue) {
		if (newValue != null) {
			parameteredElement.setSelection(new StructuredSelection(newValue));
		} else {
			parameteredElement.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement);
		if (readOnly && parameteredElement.isEnabled()) {
			parameteredElement.setEnabled(false);
			parameteredElement.setToolTipText(UmlMessages.ConnectableElementTemplateParameter_ReadOnly);
		} else if (!readOnly && !parameteredElement.isEnabled()) {
			parameteredElement.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#setParameteredElementButtonMode(ButtonsModeEnum newValue)
	 */
	public void setParameteredElementButtonMode(ButtonsModeEnum newValue) {
		parameteredElement.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#addFilterParameteredElement(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToParameteredElement(ViewerFilter filter) {
		parameteredElement.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#addBusinessFilterParameteredElement(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToParameteredElement(ViewerFilter filter) {
		parameteredElement.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#getDefault_()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#initDefault_(EObjectFlatComboSettings)
	 */
	public void initDefault_(EObjectFlatComboSettings settings) {
		default_.setInput(settings);
		if (current != null) {
			default_.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.default_);
		if (readOnly && default_.isEnabled()) {
			default_.setEnabled(false);
			default_.setToolTipText(UmlMessages.ConnectableElementTemplateParameter_ReadOnly);
		} else if (!readOnly && !default_.isEnabled()) {
			default_.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#setDefault_(EObject newValue)
	 * @generated
	 */
	public void setDefault_(EObject newValue) {
		if (newValue != null) {
			default_.setSelection(new StructuredSelection(newValue));
		} else {
			default_.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.default_);
		if (readOnly && default_.isEnabled()) {
			default_.setEnabled(false);
			default_.setToolTipText(UmlMessages.ConnectableElementTemplateParameter_ReadOnly);
		} else if (!readOnly && !default_.isEnabled()) {
			default_.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#setDefault_ButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDefault_ButtonMode(ButtonsModeEnum newValue) {
		default_.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#addFilterDefault_(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToDefault_(ViewerFilter filter) {
		default_.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart#addBusinessFilterDefault_(ViewerFilter filter)
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
		return UmlMessages.ConnectableElementTemplateParameter_Part_Title;
	}



}
