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
import org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class LinkEndDataPropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, LinkEndDataPropertiesEditionPart {

	protected EObjectFlatComboViewer value;
	protected EObjectFlatComboViewer end;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public LinkEndDataPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence linkEndDataStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = linkEndDataStep.addStep(UmlViewsRepository.LinkEndData.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.LinkEndData.Properties.value);
		propertiesStep.addStep(UmlViewsRepository.LinkEndData.Properties.end);
		
		
		composer = new PartComposer(linkEndDataStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.LinkEndData.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LinkEndData.Properties.value) {
					return createValueFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.LinkEndData.Properties.end) {
					return createEndFlatComboViewer(parent, widgetFactory);
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
		propertiesSection.setText(UmlMessages.LinkEndDataPropertiesEditionPart_PropertiesGroupLabel);
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
	protected Composite createValueFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.LinkEndDataPropertiesEditionPart_ValueLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.LinkEndData.Properties.value, UmlViewsRepository.FORM_KIND));
		value = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.LinkEndData.Properties.value, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(value);
		value.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData valueData = new GridData(GridData.FILL_HORIZONTAL);
		value.setLayoutData(valueData);
		value.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LinkEndDataPropertiesEditionPartForm.this, UmlViewsRepository.LinkEndData.Properties.value, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getValue()));
			}

		});
		value.setID(UmlViewsRepository.LinkEndData.Properties.value);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LinkEndData.Properties.value, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createEndFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.LinkEndDataPropertiesEditionPart_EndLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.LinkEndData.Properties.end, UmlViewsRepository.FORM_KIND));
		end = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.LinkEndData.Properties.end, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(end);
		end.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData endData = new GridData(GridData.FILL_HORIZONTAL);
		end.setLayoutData(endData);
		end.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LinkEndDataPropertiesEditionPartForm.this, UmlViewsRepository.LinkEndData.Properties.end, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getEnd()));
			}

		});
		end.setID(UmlViewsRepository.LinkEndData.Properties.end);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LinkEndData.Properties.end, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#getValue()
	 * 
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
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#setValue(EObject newValue)
	 * 
	 */
	public void setValue(EObject newValue) {
		if (newValue != null) {
			value.setSelection(new StructuredSelection(newValue));
		} else {
			value.setSelection(new StructuredSelection()); //$NON-NLS-1$
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
	 * 
	 */
	public void addFilterToValue(ViewerFilter filter) {
		value.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#addBusinessFilterValue(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToValue(ViewerFilter filter) {
		value.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#getEnd()
	 * 
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
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#setEnd(EObject newValue)
	 * 
	 */
	public void setEnd(EObject newValue) {
		if (newValue != null) {
			end.setSelection(new StructuredSelection(newValue));
		} else {
			end.setSelection(new StructuredSelection()); //$NON-NLS-1$
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
	 * 
	 */
	public void addFilterToEnd(ViewerFilter filter) {
		end.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndDataPropertiesEditionPart#addBusinessFilterEnd(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToEnd(ViewerFilter filter) {
		end.addBusinessRuleFilter(filter);
	}




	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.LinkEndData_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
