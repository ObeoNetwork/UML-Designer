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
package org.obeonetwork.dsl.uml2.properties.uml.parts.forms;

// Start of user code for imports
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
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
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ProfileApplicationPropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, ProfileApplicationPropertiesEditionPart {

	protected EObjectFlatComboViewer appliedProfile;
	protected Button isStrict;
	protected EObjectFlatComboViewer applyingPackage;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public ProfileApplicationPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
	 *  createFigure(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 * 
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
	 * 
	 */
	public void createControls(final FormToolkit widgetFactory, Composite view) {
		CompositionSequence profileApplicationStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = profileApplicationStep.addStep(UmlViewsRepository.ProfileApplication.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.ProfileApplication.Properties.appliedProfile);
		propertiesStep.addStep(UmlViewsRepository.ProfileApplication.Properties.isStrict);
		propertiesStep.addStep(UmlViewsRepository.ProfileApplication.Properties.applyingPackage);
		
		
		composer = new PartComposer(profileApplicationStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.ProfileApplication.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ProfileApplication.Properties.appliedProfile) {
					return createAppliedProfileFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ProfileApplication.Properties.isStrict) {
					return createIsStrictCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ProfileApplication.Properties.applyingPackage) {
					return createApplyingPackageFlatComboViewer(parent, widgetFactory);
				}
				return parent;
			}
		};
		composer.compose(view);
	}
	/**
	 * 
	 */
	protected Composite createPropertiesGroup(FormToolkit widgetFactory, final Composite parent) {
		Section propertiesSection = widgetFactory.createSection(parent, Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		propertiesSection.setText(UmlMessages.ProfileApplicationPropertiesEditionPart_PropertiesGroupLabel);
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
	 * 
	 */
	protected Composite createAppliedProfileFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ProfileApplicationPropertiesEditionPart_AppliedProfileLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ProfileApplication.Properties.appliedProfile, UmlViewsRepository.FORM_KIND));
		appliedProfile = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ProfileApplication.Properties.appliedProfile, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(appliedProfile);
		appliedProfile.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData appliedProfileData = new GridData(GridData.FILL_HORIZONTAL);
		appliedProfile.setLayoutData(appliedProfileData);
		appliedProfile.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProfileApplicationPropertiesEditionPartForm.this, UmlViewsRepository.ProfileApplication.Properties.appliedProfile, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getAppliedProfile()));
			}

		});
		appliedProfile.setID(UmlViewsRepository.ProfileApplication.Properties.appliedProfile);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProfileApplication.Properties.appliedProfile, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsStrictCheckbox(FormToolkit widgetFactory, Composite parent) {
		isStrict = widgetFactory.createButton(parent, UmlMessages.ProfileApplicationPropertiesEditionPart_IsStrictLabel, SWT.CHECK);
		isStrict.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProfileApplicationPropertiesEditionPartForm.this, UmlViewsRepository.ProfileApplication.Properties.isStrict, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isStrict.getSelection())));
			}

		});
		GridData isStrictData = new GridData(GridData.FILL_HORIZONTAL);
		isStrictData.horizontalSpan = 2;
		isStrict.setLayoutData(isStrictData);
		EditingUtils.setID(isStrict, UmlViewsRepository.ProfileApplication.Properties.isStrict);
		EditingUtils.setEEFtype(isStrict, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProfileApplication.Properties.isStrict, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createApplyingPackageFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ProfileApplicationPropertiesEditionPart_ApplyingPackageLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ProfileApplication.Properties.applyingPackage, UmlViewsRepository.FORM_KIND));
		applyingPackage = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ProfileApplication.Properties.applyingPackage, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(applyingPackage);
		applyingPackage.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData applyingPackageData = new GridData(GridData.FILL_HORIZONTAL);
		applyingPackage.setLayoutData(applyingPackageData);
		applyingPackage.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProfileApplicationPropertiesEditionPartForm.this, UmlViewsRepository.ProfileApplication.Properties.applyingPackage, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getApplyingPackage()));
			}

		});
		applyingPackage.setID(UmlViewsRepository.ProfileApplication.Properties.applyingPackage);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProfileApplication.Properties.applyingPackage, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
