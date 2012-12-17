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

import org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class QualifierValuePropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, QualifierValuePropertiesEditionPart {

	protected EObjectFlatComboViewer qualifier;
	protected EObjectFlatComboViewer value;



	/**
	 * For {@link ISection} use only.
	 */
	public QualifierValuePropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public QualifierValuePropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence qualifierValueStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = qualifierValueStep.addStep(UmlViewsRepository.QualifierValue.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.QualifierValue.Properties.qualifier);
    propertiesStep.addStep(UmlViewsRepository.QualifierValue.Properties.value);
    
    
    composer = new PartComposer(qualifierValueStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.QualifierValue.Properties.class) {
          return createPropertiesGroup(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.QualifierValue.Properties.qualifier) {
          return createQualifierFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.QualifierValue.Properties.value) {
          return createValueFlatComboViewer(parent, widgetFactory);
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
    propertiesSection.setText(UmlMessages.QualifierValuePropertiesEditionPart_PropertiesGroupLabel);
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
	protected Composite createQualifierFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.QualifierValue.Properties.qualifier, UmlMessages.QualifierValuePropertiesEditionPart_QualifierLabel);
    qualifier = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.QualifierValue.Properties.qualifier, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(qualifier);
    qualifier.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData qualifierData = new GridData(GridData.FILL_HORIZONTAL);
    qualifier.setLayoutData(qualifierData);
    qualifier.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(QualifierValuePropertiesEditionPartForm.this, UmlViewsRepository.QualifierValue.Properties.qualifier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getQualifier()));
      }

    });
    qualifier.setID(UmlViewsRepository.QualifierValue.Properties.qualifier);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.QualifierValue.Properties.qualifier, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createValueFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.QualifierValue.Properties.value, UmlMessages.QualifierValuePropertiesEditionPart_ValueLabel);
    value = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.QualifierValue.Properties.value, UmlViewsRepository.FORM_KIND));
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(QualifierValuePropertiesEditionPartForm.this, UmlViewsRepository.QualifierValue.Properties.value, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getValue()));
      }

    });
    value.setID(UmlViewsRepository.QualifierValue.Properties.value);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.QualifierValue.Properties.value, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart#getQualifier()
	 * @generated
	 */
	public EObject getQualifier() {
    if (qualifier.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) qualifier.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart#initQualifier(EObjectFlatComboSettings)
	 */
	public void initQualifier(EObjectFlatComboSettings settings) {
		qualifier.setInput(settings);
		if (current != null) {
			qualifier.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.QualifierValue.Properties.qualifier);
		if (readOnly && qualifier.isEnabled()) {
			qualifier.setEnabled(false);
			qualifier.setToolTipText(UmlMessages.QualifierValue_ReadOnly);
		} else if (!readOnly && !qualifier.isEnabled()) {
			qualifier.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart#setQualifier(EObject newValue)
	 * @generated
	 */
	public void setQualifier(EObject newValue) {
    if (newValue != null) {
      qualifier.setSelection(new StructuredSelection(newValue));
    } else {
      qualifier.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.QualifierValue.Properties.qualifier);
    if (readOnly && qualifier.isEnabled()) {
      qualifier.setEnabled(false);
      qualifier.setToolTipText(UmlMessages.QualifierValue_ReadOnly);
    } else if (!readOnly && !qualifier.isEnabled()) {
      qualifier.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart#setQualifierButtonMode(ButtonsModeEnum newValue)
	 */
	public void setQualifierButtonMode(ButtonsModeEnum newValue) {
		qualifier.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart#addFilterQualifier(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToQualifier(ViewerFilter filter) {
    qualifier.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart#addBusinessFilterQualifier(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToQualifier(ViewerFilter filter) {
    qualifier.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart#getValue()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart#initValue(EObjectFlatComboSettings)
	 */
	public void initValue(EObjectFlatComboSettings settings) {
		value.setInput(settings);
		if (current != null) {
			value.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.QualifierValue.Properties.value);
		if (readOnly && value.isEnabled()) {
			value.setEnabled(false);
			value.setToolTipText(UmlMessages.QualifierValue_ReadOnly);
		} else if (!readOnly && !value.isEnabled()) {
			value.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart#setValue(EObject newValue)
	 * @generated
	 */
	public void setValue(EObject newValue) {
    if (newValue != null) {
      value.setSelection(new StructuredSelection(newValue));
    } else {
      value.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.QualifierValue.Properties.value);
    if (readOnly && value.isEnabled()) {
      value.setEnabled(false);
      value.setToolTipText(UmlMessages.QualifierValue_ReadOnly);
    } else if (!readOnly && !value.isEnabled()) {
      value.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart#setValueButtonMode(ButtonsModeEnum newValue)
	 */
	public void setValueButtonMode(ButtonsModeEnum newValue) {
		value.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart#addFilterValue(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToValue(ViewerFilter filter) {
    value.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart#addBusinessFilterValue(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToValue(ViewerFilter filter) {
    value.addBusinessRuleFilter(filter);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.QualifierValue_Part_Title;
  }



}
