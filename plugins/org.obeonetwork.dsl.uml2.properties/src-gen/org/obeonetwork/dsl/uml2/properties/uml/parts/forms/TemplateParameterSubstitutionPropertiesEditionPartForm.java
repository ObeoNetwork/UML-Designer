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
import org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class TemplateParameterSubstitutionPropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, TemplateParameterSubstitutionPropertiesEditionPart {

	protected EObjectFlatComboViewer formal;
	protected EObjectFlatComboViewer actual;
	protected EObjectFlatComboViewer templateBinding;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public TemplateParameterSubstitutionPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence templateParameterSubstitutionStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = templateParameterSubstitutionStep.addStep(UmlViewsRepository.TemplateParameterSubstitution.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.TemplateParameterSubstitution.Properties.formal);
		propertiesStep.addStep(UmlViewsRepository.TemplateParameterSubstitution.Properties.actual);
		propertiesStep.addStep(UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding);
		
		
		composer = new PartComposer(templateParameterSubstitutionStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.TemplateParameterSubstitution.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.TemplateParameterSubstitution.Properties.formal) {
					return createFormalFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.TemplateParameterSubstitution.Properties.actual) {
					return createActualFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding) {
					return createTemplateBindingFlatComboViewer(parent, widgetFactory);
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
		propertiesSection.setText(UmlMessages.TemplateParameterSubstitutionPropertiesEditionPart_PropertiesGroupLabel);
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
	protected Composite createFormalFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.TemplateParameterSubstitutionPropertiesEditionPart_FormalLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateParameterSubstitution.Properties.formal, UmlViewsRepository.FORM_KIND));
		formal = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateParameterSubstitution.Properties.formal, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(formal);
		formal.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData formalData = new GridData(GridData.FILL_HORIZONTAL);
		formal.setLayoutData(formalData);
		formal.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(TemplateParameterSubstitutionPropertiesEditionPartForm.this, UmlViewsRepository.TemplateParameterSubstitution.Properties.formal, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getFormal()));
			}

		});
		formal.setID(UmlViewsRepository.TemplateParameterSubstitution.Properties.formal);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.TemplateParameterSubstitution.Properties.formal, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createActualFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.TemplateParameterSubstitutionPropertiesEditionPart_ActualLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateParameterSubstitution.Properties.actual, UmlViewsRepository.FORM_KIND));
		actual = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateParameterSubstitution.Properties.actual, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(actual);
		actual.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData actualData = new GridData(GridData.FILL_HORIZONTAL);
		actual.setLayoutData(actualData);
		actual.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(TemplateParameterSubstitutionPropertiesEditionPartForm.this, UmlViewsRepository.TemplateParameterSubstitution.Properties.actual, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getActual()));
			}

		});
		actual.setID(UmlViewsRepository.TemplateParameterSubstitution.Properties.actual);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.TemplateParameterSubstitution.Properties.actual, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createTemplateBindingFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.TemplateParameterSubstitutionPropertiesEditionPart_TemplateBindingLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding, UmlViewsRepository.FORM_KIND));
		templateBinding = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(templateBinding);
		templateBinding.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData templateBindingData = new GridData(GridData.FILL_HORIZONTAL);
		templateBinding.setLayoutData(templateBindingData);
		templateBinding.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(TemplateParameterSubstitutionPropertiesEditionPartForm.this, UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getTemplateBinding()));
			}

		});
		templateBinding.setID(UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
