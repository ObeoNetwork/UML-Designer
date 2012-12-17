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

import org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class QualifierValuePropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, QualifierValuePropertiesEditionPart {

	protected EObjectFlatComboViewer qualifier;
	protected EObjectFlatComboViewer value;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public QualifierValuePropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence qualifierValueStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = qualifierValueStep.addStep(UmlViewsRepository.QualifierValue.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.QualifierValue.Properties.qualifier);
    propertiesStep.addStep(UmlViewsRepository.QualifierValue.Properties.value);
    
    
    composer = new PartComposer(qualifierValueStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.QualifierValue.Properties.class) {
          return createPropertiesGroup(parent);
        }
        if (key == UmlViewsRepository.QualifierValue.Properties.qualifier) {
          return createQualifierFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.QualifierValue.Properties.value) {
          return createValueFlatComboViewer(parent);
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
    propertiesGroup.setText(UmlMessages.QualifierValuePropertiesEditionPart_PropertiesGroupLabel);
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
	protected Composite createQualifierFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.QualifierValue.Properties.qualifier, UmlMessages.QualifierValuePropertiesEditionPart_QualifierLabel);
    qualifier = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.QualifierValue.Properties.qualifier, UmlViewsRepository.SWT_KIND));
    qualifier.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    qualifier.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(QualifierValuePropertiesEditionPartImpl.this, UmlViewsRepository.QualifierValue.Properties.qualifier, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getQualifier()));
      }

    });
    GridData qualifierData = new GridData(GridData.FILL_HORIZONTAL);
    qualifier.setLayoutData(qualifierData);
    qualifier.setID(UmlViewsRepository.QualifierValue.Properties.qualifier);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.QualifierValue.Properties.qualifier, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createValueFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.QualifierValue.Properties.value, UmlMessages.QualifierValuePropertiesEditionPart_ValueLabel);
    value = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.QualifierValue.Properties.value, UmlViewsRepository.SWT_KIND));
    value.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    value.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(QualifierValuePropertiesEditionPartImpl.this, UmlViewsRepository.QualifierValue.Properties.value, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getValue()));
      }

    });
    GridData valueData = new GridData(GridData.FILL_HORIZONTAL);
    value.setLayoutData(valueData);
    value.setID(UmlViewsRepository.QualifierValue.Properties.value);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.QualifierValue.Properties.value, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
