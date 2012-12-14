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

import org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class LinkEndDataPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, LinkEndDataPropertiesEditionPart {

	protected EObjectFlatComboViewer value;
	protected EObjectFlatComboViewer end;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public LinkEndDataPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence linkEndDataStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = linkEndDataStep.addStep(UmlViewsRepository.LinkEndData.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.LinkEndData.Properties.value);
		propertiesStep.addStep(UmlViewsRepository.LinkEndData.Properties.end);
		
		
		composer = new PartComposer(linkEndDataStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.LinkEndData.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.LinkEndData.Properties.value) {
					return createValueFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.LinkEndData.Properties.end) {
					return createEndFlatComboViewer(parent);
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
		propertiesGroup.setText(UmlMessages.LinkEndDataPropertiesEditionPart_PropertiesGroupLabel);
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
	protected Composite createValueFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.LinkEndData.Properties.value, UmlMessages.LinkEndDataPropertiesEditionPart_ValueLabel);
		value = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.LinkEndData.Properties.value, UmlViewsRepository.SWT_KIND));
		value.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		value.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LinkEndDataPropertiesEditionPartImpl.this, UmlViewsRepository.LinkEndData.Properties.value, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getValue()));
			}

		});
		GridData valueData = new GridData(GridData.FILL_HORIZONTAL);
		value.setLayoutData(valueData);
		value.setID(UmlViewsRepository.LinkEndData.Properties.value);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LinkEndData.Properties.value, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createEndFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.LinkEndData.Properties.end, UmlMessages.LinkEndDataPropertiesEditionPart_EndLabel);
		end = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.LinkEndData.Properties.end, UmlViewsRepository.SWT_KIND));
		end.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		end.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LinkEndDataPropertiesEditionPartImpl.this, UmlViewsRepository.LinkEndData.Properties.end, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getEnd()));
			}

		});
		GridData endData = new GridData(GridData.FILL_HORIZONTAL);
		end.setLayoutData(endData);
		end.setID(UmlViewsRepository.LinkEndData.Properties.end);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LinkEndData.Properties.end, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#getValue()
	 * @generated
	 */
	public EObject getValue() {
		if (value.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) value.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#initValue(EObjectFlatComboSettings)
	 */
	public void initValue(EObjectFlatComboSettings settings) {
		value.setInput(settings);
		if (current != null) {
			value.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndData.Properties.value);
		if (readOnly && value.isEnabled()) {
			value.setEnabled(false);
			value.setToolTipText(UmlMessages.LinkEndData_ReadOnly);
		} else if (!readOnly && !value.isEnabled()) {
			value.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#setValue(EObject newValue)
	 * @generated
	 */
	public void setValue(EObject newValue) {
		if (newValue != null) {
			value.setSelection(new StructuredSelection(newValue));
		} else {
			value.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndData.Properties.value);
		if (readOnly && value.isEnabled()) {
			value.setEnabled(false);
			value.setToolTipText(UmlMessages.LinkEndData_ReadOnly);
		} else if (!readOnly && !value.isEnabled()) {
			value.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#setValueButtonMode(ButtonsModeEnum newValue)
	 */
	public void setValueButtonMode(ButtonsModeEnum newValue) {
		value.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#addFilterValue(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToValue(ViewerFilter filter) {
		value.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#addBusinessFilterValue(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToValue(ViewerFilter filter) {
		value.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#getEnd()
	 * @generated
	 */
	public EObject getEnd() {
		if (end.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) end.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#initEnd(EObjectFlatComboSettings)
	 */
	public void initEnd(EObjectFlatComboSettings settings) {
		end.setInput(settings);
		if (current != null) {
			end.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndData.Properties.end);
		if (readOnly && end.isEnabled()) {
			end.setEnabled(false);
			end.setToolTipText(UmlMessages.LinkEndData_ReadOnly);
		} else if (!readOnly && !end.isEnabled()) {
			end.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#setEnd(EObject newValue)
	 * @generated
	 */
	public void setEnd(EObject newValue) {
		if (newValue != null) {
			end.setSelection(new StructuredSelection(newValue));
		} else {
			end.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndData.Properties.end);
		if (readOnly && end.isEnabled()) {
			end.setEnabled(false);
			end.setToolTipText(UmlMessages.LinkEndData_ReadOnly);
		} else if (!readOnly && !end.isEnabled()) {
			end.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#setEndButtonMode(ButtonsModeEnum newValue)
	 */
	public void setEndButtonMode(ButtonsModeEnum newValue) {
		end.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#addFilterEnd(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEnd(ViewerFilter filter) {
		end.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#addBusinessFilterEnd(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEnd(ViewerFilter filter) {
		end.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.LinkEndData_Part_Title;
	}



}
