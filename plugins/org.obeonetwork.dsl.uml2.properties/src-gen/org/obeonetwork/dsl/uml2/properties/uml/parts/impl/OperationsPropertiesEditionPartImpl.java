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

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;

import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;

import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;

import org.obeonetwork.dsl.uml2.properties.uml.parts.OperationsPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class OperationsPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, OperationsPropertiesEditionPart {

	protected ReferencesTable operations;
	protected List<ViewerFilter> operationsBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> operationsFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public OperationsPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence operationsStep = new BindingCompositionSequence(propertiesEditionComponent);
    operationsStep.addStep(UmlViewsRepository.Operations.operations_);
    
    composer = new PartComposer(operationsStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.Operations.operations_) {
          return createOperationsAdvancedTableComposition(parent);
        }
        return parent;
      }
    };
    composer.compose(view);
  }

	/**
	 * @param container
	 * @generated
	 */
	protected Composite createOperationsAdvancedTableComposition(Composite parent) {
    this.operations = new ReferencesTable(getDescription(UmlViewsRepository.Operations.operations_, UmlMessages.OperationsPropertiesEditionPart_OperationsLabel), new ReferencesTableListener() {
      public void handleAdd() { 
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationsPropertiesEditionPartImpl.this, UmlViewsRepository.Operations.operations_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
        operations.refresh();
      }
      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationsPropertiesEditionPartImpl.this, UmlViewsRepository.Operations.operations_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
        operations.refresh();
      }
      public void handleMove(EObject element, int oldIndex, int newIndex) { 
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationsPropertiesEditionPartImpl.this, UmlViewsRepository.Operations.operations_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
        operations.refresh();
      }
      public void handleRemove(EObject element) { 
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationsPropertiesEditionPartImpl.this, UmlViewsRepository.Operations.operations_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
        operations.refresh();
      }
      public void navigateTo(EObject element) { }
    });
    for (ViewerFilter filter : this.operationsFilters) {
      this.operations.addFilter(filter);
    }
    this.operations.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Operations.operations_, UmlViewsRepository.SWT_KIND));
    this.operations.createControls(parent);
    this.operations.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OperationsPropertiesEditionPartImpl.this, UmlViewsRepository.Operations.operations_, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData operationsData = new GridData(GridData.FILL_HORIZONTAL);
    operationsData.horizontalSpan = 3;
    this.operations.setLayoutData(operationsData);
    operations.setID(UmlViewsRepository.Operations.operations_);
    operations.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
    // Start of user code for createOperationsAdvancedTableComposition

    // End of user code
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationsPropertiesEditionPart#initOperations(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initOperations(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		operations.setContentProvider(contentProvider);
		operations.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.Operations.operations_);
		if (eefElementEditorReadOnlyState && operations.isEnabled()) {
			operations.setEnabled(false);
			operations.setToolTipText(UmlMessages.Operations_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !operations.isEnabled()) {
			operations.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationsPropertiesEditionPart#updateOperations()
	 * @generated
	 */
	public void updateOperations() {
  operations.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationsPropertiesEditionPart#addFilterOperations(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOperations(ViewerFilter filter) {
    operationsFilters.add(filter);
    if (this.operations != null) {
      this.operations.addFilter(filter);
    }
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationsPropertiesEditionPart#addBusinessFilterOperations(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOperations(ViewerFilter filter) {
    operationsBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.OperationsPropertiesEditionPart#isContainedInOperationsTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInOperationsTable(EObject element) {
    return ((ReferencesTableSettings)operations.getInput()).contains(element);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.Operations_Part_Title;
  }



}
