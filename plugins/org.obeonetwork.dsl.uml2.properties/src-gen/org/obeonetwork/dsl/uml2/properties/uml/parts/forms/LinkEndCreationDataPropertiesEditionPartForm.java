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

import org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class LinkEndCreationDataPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, LinkEndCreationDataPropertiesEditionPart {

	protected EObjectFlatComboViewer value;
	protected EObjectFlatComboViewer end;
	protected Button isReplaceAll;
	protected EObjectFlatComboViewer insertAt;



	/**
	 * For {@link ISection} use only.
	 */
	public LinkEndCreationDataPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public LinkEndCreationDataPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence linkEndCreationDataStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = linkEndCreationDataStep.addStep(UmlViewsRepository.LinkEndCreationData.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.LinkEndCreationData.Properties.value);
    propertiesStep.addStep(UmlViewsRepository.LinkEndCreationData.Properties.end);
    propertiesStep.addStep(UmlViewsRepository.LinkEndCreationData.Properties.isReplaceAll);
    propertiesStep.addStep(UmlViewsRepository.LinkEndCreationData.Properties.insertAt);
    
    
    composer = new PartComposer(linkEndCreationDataStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.LinkEndCreationData.Properties.class) {
          return createPropertiesGroup(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.LinkEndCreationData.Properties.value) {
          return createValueFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.LinkEndCreationData.Properties.end) {
          return createEndFlatComboViewer(parent, widgetFactory);
        }
        if (key == UmlViewsRepository.LinkEndCreationData.Properties.isReplaceAll) {
          return createIsReplaceAllCheckbox(widgetFactory, parent);
        }
        if (key == UmlViewsRepository.LinkEndCreationData.Properties.insertAt) {
          return createInsertAtFlatComboViewer(parent, widgetFactory);
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
    propertiesSection.setText(UmlMessages.LinkEndCreationDataPropertiesEditionPart_PropertiesGroupLabel);
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
	protected Composite createValueFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.LinkEndCreationData.Properties.value, UmlMessages.LinkEndCreationDataPropertiesEditionPart_ValueLabel);
    value = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.LinkEndCreationData.Properties.value, UmlViewsRepository.FORM_KIND));
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LinkEndCreationDataPropertiesEditionPartForm.this, UmlViewsRepository.LinkEndCreationData.Properties.value, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getValue()));
      }

    });
    value.setID(UmlViewsRepository.LinkEndCreationData.Properties.value);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LinkEndCreationData.Properties.value, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createEndFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.LinkEndCreationData.Properties.end, UmlMessages.LinkEndCreationDataPropertiesEditionPart_EndLabel);
    end = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.LinkEndCreationData.Properties.end, UmlViewsRepository.FORM_KIND));
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LinkEndCreationDataPropertiesEditionPartForm.this, UmlViewsRepository.LinkEndCreationData.Properties.end, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getEnd()));
      }

    });
    end.setID(UmlViewsRepository.LinkEndCreationData.Properties.end);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LinkEndCreationData.Properties.end, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createIsReplaceAllCheckbox(FormToolkit widgetFactory, Composite parent) {
    isReplaceAll = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.LinkEndCreationData.Properties.isReplaceAll, UmlMessages.LinkEndCreationDataPropertiesEditionPart_IsReplaceAllLabel), SWT.CHECK);
    isReplaceAll.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LinkEndCreationDataPropertiesEditionPartForm.this, UmlViewsRepository.LinkEndCreationData.Properties.isReplaceAll, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isReplaceAll.getSelection())));
      }

    });
    GridData isReplaceAllData = new GridData(GridData.FILL_HORIZONTAL);
    isReplaceAllData.horizontalSpan = 2;
    isReplaceAll.setLayoutData(isReplaceAllData);
    EditingUtils.setID(isReplaceAll, UmlViewsRepository.LinkEndCreationData.Properties.isReplaceAll);
    EditingUtils.setEEFtype(isReplaceAll, "eef::Checkbox"); //$NON-NLS-1$
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LinkEndCreationData.Properties.isReplaceAll, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createInsertAtFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
    createDescription(parent, UmlViewsRepository.LinkEndCreationData.Properties.insertAt, UmlMessages.LinkEndCreationDataPropertiesEditionPart_InsertAtLabel);
    insertAt = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.LinkEndCreationData.Properties.insertAt, UmlViewsRepository.FORM_KIND));
    widgetFactory.adapt(insertAt);
    insertAt.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    GridData insertAtData = new GridData(GridData.FILL_HORIZONTAL);
    insertAt.setLayoutData(insertAtData);
    insertAt.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LinkEndCreationDataPropertiesEditionPartForm.this, UmlViewsRepository.LinkEndCreationData.Properties.insertAt, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getInsertAt()));
      }

    });
    insertAt.setID(UmlViewsRepository.LinkEndCreationData.Properties.insertAt);
    FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LinkEndCreationData.Properties.insertAt, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#getValue()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#initValue(EObjectFlatComboSettings)
	 */
	public void initValue(EObjectFlatComboSettings settings) {
		value.setInput(settings);
		if (current != null) {
			value.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndCreationData.Properties.value);
		if (readOnly && value.isEnabled()) {
			value.setEnabled(false);
			value.setToolTipText(UmlMessages.LinkEndCreationData_ReadOnly);
		} else if (!readOnly && !value.isEnabled()) {
			value.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#setValue(EObject newValue)
	 * @generated
	 */
	public void setValue(EObject newValue) {
    if (newValue != null) {
      value.setSelection(new StructuredSelection(newValue));
    } else {
      value.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndCreationData.Properties.value);
    if (readOnly && value.isEnabled()) {
      value.setEnabled(false);
      value.setToolTipText(UmlMessages.LinkEndCreationData_ReadOnly);
    } else if (!readOnly && !value.isEnabled()) {
      value.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#setValueButtonMode(ButtonsModeEnum newValue)
	 */
	public void setValueButtonMode(ButtonsModeEnum newValue) {
		value.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#addFilterValue(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToValue(ViewerFilter filter) {
    value.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#addBusinessFilterValue(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToValue(ViewerFilter filter) {
    value.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#getEnd()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#initEnd(EObjectFlatComboSettings)
	 */
	public void initEnd(EObjectFlatComboSettings settings) {
		end.setInput(settings);
		if (current != null) {
			end.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndCreationData.Properties.end);
		if (readOnly && end.isEnabled()) {
			end.setEnabled(false);
			end.setToolTipText(UmlMessages.LinkEndCreationData_ReadOnly);
		} else if (!readOnly && !end.isEnabled()) {
			end.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#setEnd(EObject newValue)
	 * @generated
	 */
	public void setEnd(EObject newValue) {
    if (newValue != null) {
      end.setSelection(new StructuredSelection(newValue));
    } else {
      end.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndCreationData.Properties.end);
    if (readOnly && end.isEnabled()) {
      end.setEnabled(false);
      end.setToolTipText(UmlMessages.LinkEndCreationData_ReadOnly);
    } else if (!readOnly && !end.isEnabled()) {
      end.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#setEndButtonMode(ButtonsModeEnum newValue)
	 */
	public void setEndButtonMode(ButtonsModeEnum newValue) {
		end.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#addFilterEnd(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEnd(ViewerFilter filter) {
    end.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#addBusinessFilterEnd(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEnd(ViewerFilter filter) {
    end.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#getIsReplaceAll()
	 * @generated
	 */
	public Boolean getIsReplaceAll() {
    return Boolean.valueOf(isReplaceAll.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#setIsReplaceAll(Boolean newValue)
	 * @generated
	 */
	public void setIsReplaceAll(Boolean newValue) {
    if (newValue != null) {
      isReplaceAll.setSelection(newValue.booleanValue());
    } else {
      isReplaceAll.setSelection(false);
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndCreationData.Properties.isReplaceAll);
    if (readOnly && isReplaceAll.isEnabled()) {
      isReplaceAll.setEnabled(false);
      isReplaceAll.setToolTipText(UmlMessages.LinkEndCreationData_ReadOnly);
    } else if (!readOnly && !isReplaceAll.isEnabled()) {
      isReplaceAll.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#getInsertAt()
	 * @generated
	 */
	public EObject getInsertAt() {
    if (insertAt.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) insertAt.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#initInsertAt(EObjectFlatComboSettings)
	 */
	public void initInsertAt(EObjectFlatComboSettings settings) {
		insertAt.setInput(settings);
		if (current != null) {
			insertAt.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndCreationData.Properties.insertAt);
		if (readOnly && insertAt.isEnabled()) {
			insertAt.setEnabled(false);
			insertAt.setToolTipText(UmlMessages.LinkEndCreationData_ReadOnly);
		} else if (!readOnly && !insertAt.isEnabled()) {
			insertAt.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#setInsertAt(EObject newValue)
	 * @generated
	 */
	public void setInsertAt(EObject newValue) {
    if (newValue != null) {
      insertAt.setSelection(new StructuredSelection(newValue));
    } else {
      insertAt.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.LinkEndCreationData.Properties.insertAt);
    if (readOnly && insertAt.isEnabled()) {
      insertAt.setEnabled(false);
      insertAt.setToolTipText(UmlMessages.LinkEndCreationData_ReadOnly);
    } else if (!readOnly && !insertAt.isEnabled()) {
      insertAt.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#setInsertAtButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInsertAtButtonMode(ButtonsModeEnum newValue) {
		insertAt.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#addFilterInsertAt(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInsertAt(ViewerFilter filter) {
    insertAt.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart#addBusinessFilterInsertAt(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInsertAt(ViewerFilter filter) {
    insertAt.addBusinessRuleFilter(filter);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.LinkEndCreationData_Part_Title;
  }



}
