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

import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.jface.viewers.ISelectionChangedListener;
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
import org.eclipse.swt.widgets.Group;

import org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class LinkEndDestructionDataPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, LinkEndDestructionDataPropertiesEditionPart {

	protected EObjectFlatComboViewer value;
	protected EObjectFlatComboViewer end;
	protected Button isDestroyDuplicates;
	protected EObjectFlatComboViewer destroyAt;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public LinkEndDestructionDataPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence linkEndDestructionDataStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = linkEndDestructionDataStep.addStep(UmlViewsRepository.LinkEndDestructionData.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.LinkEndDestructionData.Properties.value);
		propertiesStep.addStep(UmlViewsRepository.LinkEndDestructionData.Properties.end);
		propertiesStep.addStep(UmlViewsRepository.LinkEndDestructionData.Properties.isDestroyDuplicates);
		propertiesStep.addStep(UmlViewsRepository.LinkEndDestructionData.Properties.destroyAt);
		
		
		composer = new PartComposer(linkEndDestructionDataStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.LinkEndDestructionData.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.LinkEndDestructionData.Properties.value) {
					return createValueFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.LinkEndDestructionData.Properties.end) {
					return createEndFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.LinkEndDestructionData.Properties.isDestroyDuplicates) {
					return createIsDestroyDuplicatesCheckbox(parent);
				}
				if (key == UmlViewsRepository.LinkEndDestructionData.Properties.destroyAt) {
					return createDestroyAtFlatComboViewer(parent);
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
		propertiesGroup.setText(UmlMessages.LinkEndDestructionDataPropertiesEditionPart_PropertiesGroupLabel);
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
		createDescription(parent, UmlViewsRepository.LinkEndDestructionData.Properties.value, UmlMessages.LinkEndDestructionDataPropertiesEditionPart_ValueLabel);
		value = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.LinkEndDestructionData.Properties.value, UmlViewsRepository.SWT_KIND));
		value.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		value.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LinkEndDestructionDataPropertiesEditionPartImpl.this, UmlViewsRepository.LinkEndDestructionData.Properties.value, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getValue()));
			}

		});
		GridData valueData = new GridData(GridData.FILL_HORIZONTAL);
		value.setLayoutData(valueData);
		value.setID(UmlViewsRepository.LinkEndDestructionData.Properties.value);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LinkEndDestructionData.Properties.value, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createEndFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.LinkEndDestructionData.Properties.end, UmlMessages.LinkEndDestructionDataPropertiesEditionPart_EndLabel);
		end = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.LinkEndDestructionData.Properties.end, UmlViewsRepository.SWT_KIND));
		end.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		end.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LinkEndDestructionDataPropertiesEditionPartImpl.this, UmlViewsRepository.LinkEndDestructionData.Properties.end, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getEnd()));
			}

		});
		GridData endData = new GridData(GridData.FILL_HORIZONTAL);
		end.setLayoutData(endData);
		end.setID(UmlViewsRepository.LinkEndDestructionData.Properties.end);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LinkEndDestructionData.Properties.end, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsDestroyDuplicatesCheckbox(Composite parent) {
		isDestroyDuplicates = new Button(parent, SWT.CHECK);
		isDestroyDuplicates.setText(getDescription(UmlViewsRepository.LinkEndDestructionData.Properties.isDestroyDuplicates, UmlMessages.LinkEndDestructionDataPropertiesEditionPart_IsDestroyDuplicatesLabel));
		isDestroyDuplicates.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LinkEndDestructionDataPropertiesEditionPartImpl.this, UmlViewsRepository.LinkEndDestructionData.Properties.isDestroyDuplicates, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isDestroyDuplicates.getSelection())));
			}

		});
		GridData isDestroyDuplicatesData = new GridData(GridData.FILL_HORIZONTAL);
		isDestroyDuplicatesData.horizontalSpan = 2;
		isDestroyDuplicates.setLayoutData(isDestroyDuplicatesData);
		EditingUtils.setID(isDestroyDuplicates, UmlViewsRepository.LinkEndDestructionData.Properties.isDestroyDuplicates);
		EditingUtils.setEEFtype(isDestroyDuplicates, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LinkEndDestructionData.Properties.isDestroyDuplicates, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createDestroyAtFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.LinkEndDestructionData.Properties.destroyAt, UmlMessages.LinkEndDestructionDataPropertiesEditionPart_DestroyAtLabel);
		destroyAt = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.LinkEndDestructionData.Properties.destroyAt, UmlViewsRepository.SWT_KIND));
		destroyAt.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		destroyAt.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LinkEndDestructionDataPropertiesEditionPartImpl.this, UmlViewsRepository.LinkEndDestructionData.Properties.destroyAt, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getDestroyAt()));
			}

		});
		GridData destroyAtData = new GridData(GridData.FILL_HORIZONTAL);
		destroyAt.setLayoutData(destroyAtData);
		destroyAt.setID(UmlViewsRepository.LinkEndDestructionData.Properties.destroyAt);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LinkEndDestructionData.Properties.destroyAt, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#getValue()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#initValue(EObjectFlatComboSettings)
	 */
	public void initValue(EObjectFlatComboSettings settings) {
		value.setInput(settings);
		if (current != null) {
			value.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndDestructionData.Properties.value);
		if (readOnly && value.isEnabled()) {
			value.setEnabled(false);
			value.setToolTipText(UmlMessages.LinkEndDestructionData_ReadOnly);
		} else if (!readOnly && !value.isEnabled()) {
			value.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#setValue(EObject newValue)
	 * @generated
	 */
	public void setValue(EObject newValue) {
		if (newValue != null) {
			value.setSelection(new StructuredSelection(newValue));
		} else {
			value.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndDestructionData.Properties.value);
		if (readOnly && value.isEnabled()) {
			value.setEnabled(false);
			value.setToolTipText(UmlMessages.LinkEndDestructionData_ReadOnly);
		} else if (!readOnly && !value.isEnabled()) {
			value.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#setValueButtonMode(ButtonsModeEnum newValue)
	 */
	public void setValueButtonMode(ButtonsModeEnum newValue) {
		value.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#addFilterValue(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToValue(ViewerFilter filter) {
		value.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#addBusinessFilterValue(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToValue(ViewerFilter filter) {
		value.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#getEnd()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#initEnd(EObjectFlatComboSettings)
	 */
	public void initEnd(EObjectFlatComboSettings settings) {
		end.setInput(settings);
		if (current != null) {
			end.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndDestructionData.Properties.end);
		if (readOnly && end.isEnabled()) {
			end.setEnabled(false);
			end.setToolTipText(UmlMessages.LinkEndDestructionData_ReadOnly);
		} else if (!readOnly && !end.isEnabled()) {
			end.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#setEnd(EObject newValue)
	 * @generated
	 */
	public void setEnd(EObject newValue) {
		if (newValue != null) {
			end.setSelection(new StructuredSelection(newValue));
		} else {
			end.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndDestructionData.Properties.end);
		if (readOnly && end.isEnabled()) {
			end.setEnabled(false);
			end.setToolTipText(UmlMessages.LinkEndDestructionData_ReadOnly);
		} else if (!readOnly && !end.isEnabled()) {
			end.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#setEndButtonMode(ButtonsModeEnum newValue)
	 */
	public void setEndButtonMode(ButtonsModeEnum newValue) {
		end.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#addFilterEnd(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEnd(ViewerFilter filter) {
		end.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#addBusinessFilterEnd(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEnd(ViewerFilter filter) {
		end.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#getIsDestroyDuplicates()
	 * @generated
	 */
	public Boolean getIsDestroyDuplicates() {
		return Boolean.valueOf(isDestroyDuplicates.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#setIsDestroyDuplicates(Boolean newValue)
	 * @generated
	 */
	public void setIsDestroyDuplicates(Boolean newValue) {
		if (newValue != null) {
			isDestroyDuplicates.setSelection(newValue.booleanValue());
		} else {
			isDestroyDuplicates.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndDestructionData.Properties.isDestroyDuplicates);
		if (readOnly && isDestroyDuplicates.isEnabled()) {
			isDestroyDuplicates.setEnabled(false);
			isDestroyDuplicates.setToolTipText(UmlMessages.LinkEndDestructionData_ReadOnly);
		} else if (!readOnly && !isDestroyDuplicates.isEnabled()) {
			isDestroyDuplicates.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#getDestroyAt()
	 * @generated
	 */
	public EObject getDestroyAt() {
		if (destroyAt.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) destroyAt.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#initDestroyAt(EObjectFlatComboSettings)
	 */
	public void initDestroyAt(EObjectFlatComboSettings settings) {
		destroyAt.setInput(settings);
		if (current != null) {
			destroyAt.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndDestructionData.Properties.destroyAt);
		if (readOnly && destroyAt.isEnabled()) {
			destroyAt.setEnabled(false);
			destroyAt.setToolTipText(UmlMessages.LinkEndDestructionData_ReadOnly);
		} else if (!readOnly && !destroyAt.isEnabled()) {
			destroyAt.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#setDestroyAt(EObject newValue)
	 * @generated
	 */
	public void setDestroyAt(EObject newValue) {
		if (newValue != null) {
			destroyAt.setSelection(new StructuredSelection(newValue));
		} else {
			destroyAt.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndDestructionData.Properties.destroyAt);
		if (readOnly && destroyAt.isEnabled()) {
			destroyAt.setEnabled(false);
			destroyAt.setToolTipText(UmlMessages.LinkEndDestructionData_ReadOnly);
		} else if (!readOnly && !destroyAt.isEnabled()) {
			destroyAt.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#setDestroyAtButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDestroyAtButtonMode(ButtonsModeEnum newValue) {
		destroyAt.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#addFilterDestroyAt(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToDestroyAt(ViewerFilter filter) {
		destroyAt.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDestructionDataPropertiesEditionPart#addBusinessFilterDestroyAt(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToDestroyAt(ViewerFilter filter) {
		destroyAt.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.LinkEndDestructionData_Part_Title;
	}



}
