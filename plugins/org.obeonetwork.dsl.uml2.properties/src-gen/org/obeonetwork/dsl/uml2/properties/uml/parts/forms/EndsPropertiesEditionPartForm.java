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

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.part.impl.SectionPropertiesEditingPart;

import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;

import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import org.obeonetwork.dsl.uml2.properties.uml.parts.EndsPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class EndsPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, EndsPropertiesEditionPart {

	protected ReferencesTable ends;
	protected List<ViewerFilter> endsBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> endsFilters = new ArrayList<ViewerFilter>();



	/**
	 * For {@link ISection} use only.
	 */
	public EndsPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public EndsPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence endsStep = new BindingCompositionSequence(propertiesEditionComponent);
    endsStep.addStep(UmlViewsRepository.Ends.ends_);
    
    composer = new PartComposer(endsStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.Ends.ends_) {
          return createEndsTableComposition(widgetFactory, parent);
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
	protected Composite createEndsTableComposition(FormToolkit widgetFactory, Composite parent) {
    this.ends = new ReferencesTable(getDescription(UmlViewsRepository.Ends.ends_, UmlMessages.EndsPropertiesEditionPart_EndsLabel), new ReferencesTableListener() {
      public void handleAdd() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EndsPropertiesEditionPartForm.this, UmlViewsRepository.Ends.ends_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
        ends.refresh();
      }
      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EndsPropertiesEditionPartForm.this, UmlViewsRepository.Ends.ends_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
        ends.refresh();
      }
      public void handleMove(EObject element, int oldIndex, int newIndex) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EndsPropertiesEditionPartForm.this, UmlViewsRepository.Ends.ends_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
        ends.refresh();
      }
      public void handleRemove(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EndsPropertiesEditionPartForm.this, UmlViewsRepository.Ends.ends_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
        ends.refresh();
      }
      public void navigateTo(EObject element) { }
    });
    for (ViewerFilter filter : this.endsFilters) {
      this.ends.addFilter(filter);
    }
    this.ends.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Ends.ends_, UmlViewsRepository.FORM_KIND));
    this.ends.createControls(parent, widgetFactory);
    this.ends.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EndsPropertiesEditionPartForm.this, UmlViewsRepository.Ends.ends_, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData endsData = new GridData(GridData.FILL_HORIZONTAL);
    endsData.horizontalSpan = 3;
    this.ends.setLayoutData(endsData);
    this.ends.setLowerBound(2);
    this.ends.setUpperBound(-1);
    ends.setID(UmlViewsRepository.Ends.ends_);
    ends.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
    // Start of user code for createEndsTableComposition

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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.EndsPropertiesEditionPart#initEnds(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initEnds(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		ends.setContentProvider(contentProvider);
		ends.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.Ends.ends_);
		if (eefElementEditorReadOnlyState && ends.isEnabled()) {
			ends.setEnabled(false);
			ends.setToolTipText(UmlMessages.Ends_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !ends.isEnabled()) {
			ends.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.EndsPropertiesEditionPart#updateEnds()
	 * @generated
	 */
	public void updateEnds() {
  ends.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.EndsPropertiesEditionPart#addFilterEnds(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEnds(ViewerFilter filter) {
    endsFilters.add(filter);
    if (this.ends != null) {
      this.ends.addFilter(filter);
    }
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.EndsPropertiesEditionPart#addBusinessFilterEnds(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEnds(ViewerFilter filter) {
    endsBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.EndsPropertiesEditionPart#isContainedInEndsTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInEndsTable(EObject element) {
    return ((ReferencesTableSettings)ends.getInput()).contains(element);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.Ends_Part_Title;
  }



}
