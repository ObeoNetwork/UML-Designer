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

import org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ProfileApplicationPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ProfileApplicationPropertiesEditionPart {

	protected EObjectFlatComboViewer appliedProfile;
	protected Button isStrict;
	protected EObjectFlatComboViewer applyingPackage;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ProfileApplicationPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence profileApplicationStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = profileApplicationStep.addStep(UmlViewsRepository.ProfileApplication.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.ProfileApplication.Properties.appliedProfile);
		propertiesStep.addStep(UmlViewsRepository.ProfileApplication.Properties.isStrict);
		propertiesStep.addStep(UmlViewsRepository.ProfileApplication.Properties.applyingPackage);
		
		
		composer = new PartComposer(profileApplicationStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.ProfileApplication.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.ProfileApplication.Properties.appliedProfile) {
					return createAppliedProfileFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.ProfileApplication.Properties.isStrict) {
					return createIsStrictCheckbox(parent);
				}
				if (key == UmlViewsRepository.ProfileApplication.Properties.applyingPackage) {
					return createApplyingPackageFlatComboViewer(parent);
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
		propertiesGroup.setText(UmlMessages.ProfileApplicationPropertiesEditionPart_PropertiesGroupLabel);
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
	protected Composite createAppliedProfileFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.ProfileApplication.Properties.appliedProfile, UmlMessages.ProfileApplicationPropertiesEditionPart_AppliedProfileLabel);
		appliedProfile = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ProfileApplication.Properties.appliedProfile, UmlViewsRepository.SWT_KIND));
		appliedProfile.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		appliedProfile.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProfileApplicationPropertiesEditionPartImpl.this, UmlViewsRepository.ProfileApplication.Properties.appliedProfile, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getAppliedProfile()));
			}

		});
		GridData appliedProfileData = new GridData(GridData.FILL_HORIZONTAL);
		appliedProfile.setLayoutData(appliedProfileData);
		appliedProfile.setID(UmlViewsRepository.ProfileApplication.Properties.appliedProfile);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProfileApplication.Properties.appliedProfile, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsStrictCheckbox(Composite parent) {
		isStrict = new Button(parent, SWT.CHECK);
		isStrict.setText(getDescription(UmlViewsRepository.ProfileApplication.Properties.isStrict, UmlMessages.ProfileApplicationPropertiesEditionPart_IsStrictLabel));
		isStrict.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProfileApplicationPropertiesEditionPartImpl.this, UmlViewsRepository.ProfileApplication.Properties.isStrict, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isStrict.getSelection())));
			}

		});
		GridData isStrictData = new GridData(GridData.FILL_HORIZONTAL);
		isStrictData.horizontalSpan = 2;
		isStrict.setLayoutData(isStrictData);
		EditingUtils.setID(isStrict, UmlViewsRepository.ProfileApplication.Properties.isStrict);
		EditingUtils.setEEFtype(isStrict, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProfileApplication.Properties.isStrict, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createApplyingPackageFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.ProfileApplication.Properties.applyingPackage, UmlMessages.ProfileApplicationPropertiesEditionPart_ApplyingPackageLabel);
		applyingPackage = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ProfileApplication.Properties.applyingPackage, UmlViewsRepository.SWT_KIND));
		applyingPackage.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		applyingPackage.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProfileApplicationPropertiesEditionPartImpl.this, UmlViewsRepository.ProfileApplication.Properties.applyingPackage, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getApplyingPackage()));
			}

		});
		GridData applyingPackageData = new GridData(GridData.FILL_HORIZONTAL);
		applyingPackage.setLayoutData(applyingPackageData);
		applyingPackage.setID(UmlViewsRepository.ProfileApplication.Properties.applyingPackage);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProfileApplication.Properties.applyingPackage, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#getAppliedProfile()
	 * @generated
	 */
	public EObject getAppliedProfile() {
		if (appliedProfile.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) appliedProfile.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#initAppliedProfile(EObjectFlatComboSettings)
	 */
	public void initAppliedProfile(EObjectFlatComboSettings settings) {
		appliedProfile.setInput(settings);
		if (current != null) {
			appliedProfile.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ProfileApplication.Properties.appliedProfile);
		if (readOnly && appliedProfile.isEnabled()) {
			appliedProfile.setEnabled(false);
			appliedProfile.setToolTipText(UmlMessages.ProfileApplication_ReadOnly);
		} else if (!readOnly && !appliedProfile.isEnabled()) {
			appliedProfile.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#setAppliedProfile(EObject newValue)
	 * @generated
	 */
	public void setAppliedProfile(EObject newValue) {
		if (newValue != null) {
			appliedProfile.setSelection(new StructuredSelection(newValue));
		} else {
			appliedProfile.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ProfileApplication.Properties.appliedProfile);
		if (readOnly && appliedProfile.isEnabled()) {
			appliedProfile.setEnabled(false);
			appliedProfile.setToolTipText(UmlMessages.ProfileApplication_ReadOnly);
		} else if (!readOnly && !appliedProfile.isEnabled()) {
			appliedProfile.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#setAppliedProfileButtonMode(ButtonsModeEnum newValue)
	 */
	public void setAppliedProfileButtonMode(ButtonsModeEnum newValue) {
		appliedProfile.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#addFilterAppliedProfile(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToAppliedProfile(ViewerFilter filter) {
		appliedProfile.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#addBusinessFilterAppliedProfile(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToAppliedProfile(ViewerFilter filter) {
		appliedProfile.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#getIsStrict()
	 * @generated
	 */
	public Boolean getIsStrict() {
		return Boolean.valueOf(isStrict.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#setIsStrict(Boolean newValue)
	 * @generated
	 */
	public void setIsStrict(Boolean newValue) {
		if (newValue != null) {
			isStrict.setSelection(newValue.booleanValue());
		} else {
			isStrict.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ProfileApplication.Properties.isStrict);
		if (readOnly && isStrict.isEnabled()) {
			isStrict.setEnabled(false);
			isStrict.setToolTipText(UmlMessages.ProfileApplication_ReadOnly);
		} else if (!readOnly && !isStrict.isEnabled()) {
			isStrict.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#getApplyingPackage()
	 * @generated
	 */
	public EObject getApplyingPackage() {
		if (applyingPackage.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) applyingPackage.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#initApplyingPackage(EObjectFlatComboSettings)
	 */
	public void initApplyingPackage(EObjectFlatComboSettings settings) {
		applyingPackage.setInput(settings);
		if (current != null) {
			applyingPackage.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ProfileApplication.Properties.applyingPackage);
		if (readOnly && applyingPackage.isEnabled()) {
			applyingPackage.setEnabled(false);
			applyingPackage.setToolTipText(UmlMessages.ProfileApplication_ReadOnly);
		} else if (!readOnly && !applyingPackage.isEnabled()) {
			applyingPackage.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#setApplyingPackage(EObject newValue)
	 * @generated
	 */
	public void setApplyingPackage(EObject newValue) {
		if (newValue != null) {
			applyingPackage.setSelection(new StructuredSelection(newValue));
		} else {
			applyingPackage.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ProfileApplication.Properties.applyingPackage);
		if (readOnly && applyingPackage.isEnabled()) {
			applyingPackage.setEnabled(false);
			applyingPackage.setToolTipText(UmlMessages.ProfileApplication_ReadOnly);
		} else if (!readOnly && !applyingPackage.isEnabled()) {
			applyingPackage.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#setApplyingPackageButtonMode(ButtonsModeEnum newValue)
	 */
	public void setApplyingPackageButtonMode(ButtonsModeEnum newValue) {
		applyingPackage.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#addFilterApplyingPackage(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToApplyingPackage(ViewerFilter filter) {
		applyingPackage.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#addBusinessFilterApplyingPackage(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToApplyingPackage(ViewerFilter filter) {
		applyingPackage.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.ProfileApplication_Part_Title;
	}



}
