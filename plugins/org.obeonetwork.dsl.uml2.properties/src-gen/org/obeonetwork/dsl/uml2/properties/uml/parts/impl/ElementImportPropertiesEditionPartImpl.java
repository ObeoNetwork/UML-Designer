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

import org.eclipse.emf.common.util.Enumerator;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;

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
import org.eclipse.emf.eef.runtime.ui.widgets.EMFComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ElementImportPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ElementImportPropertiesEditionPart {

	protected EMFComboViewer visibility;
	protected Text alias;
	protected EObjectFlatComboViewer importedElement;
	protected EObjectFlatComboViewer importingNamespace;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ElementImportPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence elementImportStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = elementImportStep.addStep(UmlViewsRepository.ElementImport.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.ElementImport.Properties.visibility);
    propertiesStep.addStep(UmlViewsRepository.ElementImport.Properties.alias);
    propertiesStep.addStep(UmlViewsRepository.ElementImport.Properties.importedElement);
    propertiesStep.addStep(UmlViewsRepository.ElementImport.Properties.importingNamespace);
    
    
    composer = new PartComposer(elementImportStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.ElementImport.Properties.class) {
          return createPropertiesGroup(parent);
        }
        if (key == UmlViewsRepository.ElementImport.Properties.visibility) {
          return createVisibilityEMFComboViewer(parent);
        }
        if (key == UmlViewsRepository.ElementImport.Properties.alias) {
          return createAliasText(parent);
        }
        if (key == UmlViewsRepository.ElementImport.Properties.importedElement) {
          return createImportedElementFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.ElementImport.Properties.importingNamespace) {
          return createImportingNamespaceFlatComboViewer(parent);
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
    propertiesGroup.setText(UmlMessages.ElementImportPropertiesEditionPart_PropertiesGroupLabel);
    GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
    propertiesGroupData.horizontalSpan = 3;
    propertiesGroup.setLayoutData(propertiesGroupData);
    GridLayout propertiesGroupLayout = new GridLayout();
    propertiesGroupLayout.numColumns = 3;
    propertiesGroup.setLayout(propertiesGroupLayout);
    return propertiesGroup;
  }

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.ElementImport.Properties.visibility, UmlMessages.ElementImportPropertiesEditionPart_VisibilityLabel);
    visibility = new EMFComboViewer(parent);
    visibility.setContentProvider(new ArrayContentProvider());
    visibility.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
    GridData visibilityData = new GridData(GridData.FILL_HORIZONTAL);
    visibility.getCombo().setLayoutData(visibilityData);
    visibility.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       * 	@generated
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ElementImportPropertiesEditionPartImpl.this, UmlViewsRepository.ElementImport.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
      }

    });
    visibility.setID(UmlViewsRepository.ElementImport.Properties.visibility);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ElementImport.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createAliasText(Composite parent) {
    createDescription(parent, UmlViewsRepository.ElementImport.Properties.alias, UmlMessages.ElementImportPropertiesEditionPart_AliasLabel);
    alias = SWTUtils.createScrollableText(parent, SWT.BORDER);
    GridData aliasData = new GridData(GridData.FILL_HORIZONTAL);
    alias.setLayoutData(aliasData);
    alias.addFocusListener(new FocusAdapter() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
       * @generated
       */
      @Override
      @SuppressWarnings("synthetic-access")
      public void focusLost(FocusEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ElementImportPropertiesEditionPartImpl.this, UmlViewsRepository.ElementImport.Properties.alias, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, alias.getText()));
      }

    });
    alias.addKeyListener(new KeyAdapter() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
       * @generated
       */
      @Override
      @SuppressWarnings("synthetic-access")
      public void keyPressed(KeyEvent e) {
        if (e.character == SWT.CR) {
          if (propertiesEditionComponent != null)
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ElementImportPropertiesEditionPartImpl.this, UmlViewsRepository.ElementImport.Properties.alias, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, alias.getText()));
        }
      }

    });
    EditingUtils.setID(alias, UmlViewsRepository.ElementImport.Properties.alias);
    EditingUtils.setEEFtype(alias, "eef::Text"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ElementImport.Properties.alias, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createImportedElementFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.ElementImport.Properties.importedElement, UmlMessages.ElementImportPropertiesEditionPart_ImportedElementLabel);
    importedElement = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ElementImport.Properties.importedElement, UmlViewsRepository.SWT_KIND));
    importedElement.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    importedElement.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ElementImportPropertiesEditionPartImpl.this, UmlViewsRepository.ElementImport.Properties.importedElement, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getImportedElement()));
      }

    });
    GridData importedElementData = new GridData(GridData.FILL_HORIZONTAL);
    importedElement.setLayoutData(importedElementData);
    importedElement.setID(UmlViewsRepository.ElementImport.Properties.importedElement);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ElementImport.Properties.importedElement, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createImportingNamespaceFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.ElementImport.Properties.importingNamespace, UmlMessages.ElementImportPropertiesEditionPart_ImportingNamespaceLabel);
    importingNamespace = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ElementImport.Properties.importingNamespace, UmlViewsRepository.SWT_KIND));
    importingNamespace.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    importingNamespace.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ElementImportPropertiesEditionPartImpl.this, UmlViewsRepository.ElementImport.Properties.importingNamespace, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getImportingNamespace()));
      }

    });
    GridData importingNamespaceData = new GridData(GridData.FILL_HORIZONTAL);
    importingNamespace.setLayoutData(importingNamespaceData);
    importingNamespace.setID(UmlViewsRepository.ElementImport.Properties.importingNamespace);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ElementImport.Properties.importingNamespace, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
    Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.ElementImport.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.ElementImport_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
    visibility.modelUpdating(new StructuredSelection(newValue));
    boolean readOnly = isReadOnly(UmlViewsRepository.ElementImport.Properties.visibility);
    if (readOnly && visibility.isEnabled()) {
      visibility.setEnabled(false);
      visibility.setToolTipText(UmlMessages.ElementImport_ReadOnly);
    } else if (!readOnly && !visibility.isEnabled()) {
      visibility.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#getAlias()
	 * @generated
	 */
	public String getAlias() {
    return alias.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#setAlias(String newValue)
	 * @generated
	 */
	public void setAlias(String newValue) {
    if (newValue != null) {
      alias.setText(newValue);
    } else {
      alias.setText(""); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ElementImport.Properties.alias);
    if (readOnly && alias.isEnabled()) {
      alias.setEnabled(false);
      alias.setToolTipText(UmlMessages.ElementImport_ReadOnly);
    } else if (!readOnly && !alias.isEnabled()) {
      alias.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#getImportedElement()
	 * @generated
	 */
	public EObject getImportedElement() {
    if (importedElement.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) importedElement.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#initImportedElement(EObjectFlatComboSettings)
	 */
	public void initImportedElement(EObjectFlatComboSettings settings) {
		importedElement.setInput(settings);
		if (current != null) {
			importedElement.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ElementImport.Properties.importedElement);
		if (readOnly && importedElement.isEnabled()) {
			importedElement.setEnabled(false);
			importedElement.setToolTipText(UmlMessages.ElementImport_ReadOnly);
		} else if (!readOnly && !importedElement.isEnabled()) {
			importedElement.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#setImportedElement(EObject newValue)
	 * @generated
	 */
	public void setImportedElement(EObject newValue) {
    if (newValue != null) {
      importedElement.setSelection(new StructuredSelection(newValue));
    } else {
      importedElement.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ElementImport.Properties.importedElement);
    if (readOnly && importedElement.isEnabled()) {
      importedElement.setEnabled(false);
      importedElement.setToolTipText(UmlMessages.ElementImport_ReadOnly);
    } else if (!readOnly && !importedElement.isEnabled()) {
      importedElement.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#setImportedElementButtonMode(ButtonsModeEnum newValue)
	 */
	public void setImportedElementButtonMode(ButtonsModeEnum newValue) {
		importedElement.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#addFilterImportedElement(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToImportedElement(ViewerFilter filter) {
    importedElement.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#addBusinessFilterImportedElement(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToImportedElement(ViewerFilter filter) {
    importedElement.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#getImportingNamespace()
	 * @generated
	 */
	public EObject getImportingNamespace() {
    if (importingNamespace.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) importingNamespace.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#initImportingNamespace(EObjectFlatComboSettings)
	 */
	public void initImportingNamespace(EObjectFlatComboSettings settings) {
		importingNamespace.setInput(settings);
		if (current != null) {
			importingNamespace.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ElementImport.Properties.importingNamespace);
		if (readOnly && importingNamespace.isEnabled()) {
			importingNamespace.setEnabled(false);
			importingNamespace.setToolTipText(UmlMessages.ElementImport_ReadOnly);
		} else if (!readOnly && !importingNamespace.isEnabled()) {
			importingNamespace.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#setImportingNamespace(EObject newValue)
	 * @generated
	 */
	public void setImportingNamespace(EObject newValue) {
    if (newValue != null) {
      importingNamespace.setSelection(new StructuredSelection(newValue));
    } else {
      importingNamespace.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.ElementImport.Properties.importingNamespace);
    if (readOnly && importingNamespace.isEnabled()) {
      importingNamespace.setEnabled(false);
      importingNamespace.setToolTipText(UmlMessages.ElementImport_ReadOnly);
    } else if (!readOnly && !importingNamespace.isEnabled()) {
      importingNamespace.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#setImportingNamespaceButtonMode(ButtonsModeEnum newValue)
	 */
	public void setImportingNamespaceButtonMode(ButtonsModeEnum newValue) {
		importingNamespace.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#addFilterImportingNamespace(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToImportingNamespace(ViewerFilter filter) {
    importingNamespace.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#addBusinessFilterImportingNamespace(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToImportingNamespace(ViewerFilter filter) {
    importingNamespace.addBusinessRuleFilter(filter);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.ElementImport_Part_Title;
  }



}
