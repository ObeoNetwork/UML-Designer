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
import org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class TemplateParameterSubstitutionPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, TemplateParameterSubstitutionPropertiesEditionPart {

	protected EObjectFlatComboViewer formal;
	protected EObjectFlatComboViewer actual;
	protected EObjectFlatComboViewer templateBinding;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public TemplateParameterSubstitutionPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence templateParameterSubstitutionStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = templateParameterSubstitutionStep.addStep(UmlViewsRepository.TemplateParameterSubstitution.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.TemplateParameterSubstitution.Properties.formal);
		propertiesStep.addStep(UmlViewsRepository.TemplateParameterSubstitution.Properties.actual);
		propertiesStep.addStep(UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding);
		
		
		composer = new PartComposer(templateParameterSubstitutionStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.TemplateParameterSubstitution.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.TemplateParameterSubstitution.Properties.formal) {
					return createFormalFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.TemplateParameterSubstitution.Properties.actual) {
					return createActualFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding) {
					return createTemplateBindingFlatComboViewer(parent);
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
		propertiesGroup.setText(UmlMessages.TemplateParameterSubstitutionPropertiesEditionPart_PropertiesGroupLabel);
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
	protected Composite createFormalFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.TemplateParameterSubstitutionPropertiesEditionPart_FormalLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateParameterSubstitution.Properties.formal, UmlViewsRepository.SWT_KIND));
		formal = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateParameterSubstitution.Properties.formal, UmlViewsRepository.SWT_KIND));
		formal.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		formal.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(TemplateParameterSubstitutionPropertiesEditionPartImpl.this, UmlViewsRepository.TemplateParameterSubstitution.Properties.formal, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getFormal()));
			}

		});
		GridData formalData = new GridData(GridData.FILL_HORIZONTAL);
		formal.setLayoutData(formalData);
		formal.setID(UmlViewsRepository.TemplateParameterSubstitution.Properties.formal);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.TemplateParameterSubstitution.Properties.formal, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createActualFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.TemplateParameterSubstitutionPropertiesEditionPart_ActualLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateParameterSubstitution.Properties.actual, UmlViewsRepository.SWT_KIND));
		actual = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateParameterSubstitution.Properties.actual, UmlViewsRepository.SWT_KIND));
		actual.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		actual.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(TemplateParameterSubstitutionPropertiesEditionPartImpl.this, UmlViewsRepository.TemplateParameterSubstitution.Properties.actual, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getActual()));
			}

		});
		GridData actualData = new GridData(GridData.FILL_HORIZONTAL);
		actual.setLayoutData(actualData);
		actual.setID(UmlViewsRepository.TemplateParameterSubstitution.Properties.actual);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.TemplateParameterSubstitution.Properties.actual, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createTemplateBindingFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.TemplateParameterSubstitutionPropertiesEditionPart_TemplateBindingLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding, UmlViewsRepository.SWT_KIND));
		templateBinding = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding, UmlViewsRepository.SWT_KIND));
		templateBinding.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		templateBinding.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(TemplateParameterSubstitutionPropertiesEditionPartImpl.this, UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getTemplateBinding()));
			}

		});
		GridData templateBindingData = new GridData(GridData.FILL_HORIZONTAL);
		templateBinding.setLayoutData(templateBindingData);
		templateBinding.setID(UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#getFormal()
	 * 
	 */
	public EObject getFormal() {
		if (formal.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) formal.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#initFormal(EObjectFlatComboSettings)
	 */
	public void initFormal(EObjectFlatComboSettings settings) {
		formal.setInput(settings);
		if (current != null) {
			formal.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#setFormal(EObject newValue)
	 * 
	 */
	public void setFormal(EObject newValue) {
		if (newValue != null) {
			formal.setSelection(new StructuredSelection(newValue));
		} else {
			formal.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#setFormalButtonMode(ButtonsModeEnum newValue)
	 */
	public void setFormalButtonMode(ButtonsModeEnum newValue) {
		formal.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#addFilterFormal(ViewerFilter filter)
	 * 
	 */
	public void addFilterToFormal(ViewerFilter filter) {
		formal.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#addBusinessFilterFormal(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToFormal(ViewerFilter filter) {
		formal.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#getActual()
	 * 
	 */
	public EObject getActual() {
		if (actual.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) actual.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#initActual(EObjectFlatComboSettings)
	 */
	public void initActual(EObjectFlatComboSettings settings) {
		actual.setInput(settings);
		if (current != null) {
			actual.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#setActual(EObject newValue)
	 * 
	 */
	public void setActual(EObject newValue) {
		if (newValue != null) {
			actual.setSelection(new StructuredSelection(newValue));
		} else {
			actual.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#setActualButtonMode(ButtonsModeEnum newValue)
	 */
	public void setActualButtonMode(ButtonsModeEnum newValue) {
		actual.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#addFilterActual(ViewerFilter filter)
	 * 
	 */
	public void addFilterToActual(ViewerFilter filter) {
		actual.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#addBusinessFilterActual(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToActual(ViewerFilter filter) {
		actual.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#getTemplateBinding()
	 * 
	 */
	public EObject getTemplateBinding() {
		if (templateBinding.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) templateBinding.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#initTemplateBinding(EObjectFlatComboSettings)
	 */
	public void initTemplateBinding(EObjectFlatComboSettings settings) {
		templateBinding.setInput(settings);
		if (current != null) {
			templateBinding.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#setTemplateBinding(EObject newValue)
	 * 
	 */
	public void setTemplateBinding(EObject newValue) {
		if (newValue != null) {
			templateBinding.setSelection(new StructuredSelection(newValue));
		} else {
			templateBinding.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#setTemplateBindingButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateBindingButtonMode(ButtonsModeEnum newValue) {
		templateBinding.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#addFilterTemplateBinding(ViewerFilter filter)
	 * 
	 */
	public void addFilterToTemplateBinding(ViewerFilter filter) {
		templateBinding.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart#addBusinessFilterTemplateBinding(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToTemplateBinding(ViewerFilter filter) {
		templateBinding.addBusinessRuleFilter(filter);
	}







	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.TemplateParameterSubstitution_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
