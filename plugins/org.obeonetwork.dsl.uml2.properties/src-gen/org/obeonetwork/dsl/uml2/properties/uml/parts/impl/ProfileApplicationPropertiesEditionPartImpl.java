/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.parts.impl;

// Start of user code for imports
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


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ProfileApplicationPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ProfileApplicationPropertiesEditionPart {

	protected EObjectFlatComboViewer appliedProfile;
	protected Button isStrict;
	protected EObjectFlatComboViewer applyingPackage;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public ProfileApplicationPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createFigure(org.eclipse.swt.widgets.Composite)
	 * 
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
	 * 
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
	 * 
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
	 * 
	 */
	protected Composite createAppliedProfileFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.ProfileApplicationPropertiesEditionPart_AppliedProfileLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ProfileApplication.Properties.appliedProfile, UmlViewsRepository.SWT_KIND));
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

	
	protected Composite createIsStrictCheckbox(Composite parent) {
		isStrict = new Button(parent, SWT.CHECK);
		isStrict.setText(UmlMessages.ProfileApplicationPropertiesEditionPart_IsStrictLabel);
		isStrict.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
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
	 * 
	 */
	protected Composite createApplyingPackageFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.ProfileApplicationPropertiesEditionPart_ApplyingPackageLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ProfileApplication.Properties.applyingPackage, UmlViewsRepository.SWT_KIND));
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
	 * 
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
		// Start of user code for tab synchronization
		
		// End of user code
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#getAppliedProfile()
	 * 
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
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#setAppliedProfile(EObject newValue)
	 * 
	 */
	public void setAppliedProfile(EObject newValue) {
		if (newValue != null) {
			appliedProfile.setSelection(new StructuredSelection(newValue));
		} else {
			appliedProfile.setSelection(new StructuredSelection()); //$NON-NLS-1$
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
	 * 
	 */
	public void addFilterToAppliedProfile(ViewerFilter filter) {
		appliedProfile.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#addBusinessFilterAppliedProfile(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToAppliedProfile(ViewerFilter filter) {
		appliedProfile.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#getIsStrict()
	 * 
	 */
	public Boolean getIsStrict() {
		return Boolean.valueOf(isStrict.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#setIsStrict(Boolean newValue)
	 * 
	 */
	public void setIsStrict(Boolean newValue) {
		if (newValue != null) {
			isStrict.setSelection(newValue.booleanValue());
		} else {
			isStrict.setSelection(false);
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#getApplyingPackage()
	 * 
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
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#setApplyingPackage(EObject newValue)
	 * 
	 */
	public void setApplyingPackage(EObject newValue) {
		if (newValue != null) {
			applyingPackage.setSelection(new StructuredSelection(newValue));
		} else {
			applyingPackage.setSelection(new StructuredSelection()); //$NON-NLS-1$
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
	 * 
	 */
	public void addFilterToApplyingPackage(ViewerFilter filter) {
		applyingPackage.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart#addBusinessFilterApplyingPackage(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToApplyingPackage(ViewerFilter filter) {
		applyingPackage.addBusinessRuleFilter(filter);
	}







	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.ProfileApplication_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
