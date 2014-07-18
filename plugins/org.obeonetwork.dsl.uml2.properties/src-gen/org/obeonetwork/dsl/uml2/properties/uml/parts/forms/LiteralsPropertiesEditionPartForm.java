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

import org.obeonetwork.dsl.uml2.properties.parts.CustomSectionPropertiesEditingPart;

import org.obeonetwork.dsl.uml2.properties.uml.parts.LiteralsPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class LiteralsPropertiesEditionPartForm extends CustomSectionPropertiesEditingPart implements IFormPropertiesEditionPart, LiteralsPropertiesEditionPart {

	protected ReferencesTable literals;
	protected List<ViewerFilter> literalsBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> literalsFilters = new ArrayList<ViewerFilter>();



	/**
	 * For {@link ISection} use only.
	 */
	public LiteralsPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public LiteralsPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence literalsStep = new BindingCompositionSequence(propertiesEditionComponent);
		literalsStep.addStep(UmlViewsRepository.Literals.literals_);
		
		composer = new PartComposer(literalsStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.Literals.literals_) {
					return createLiteralsTableComposition(widgetFactory, parent);
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
	protected Composite createLiteralsTableComposition(FormToolkit widgetFactory, Composite parent) {
		this.literals = new ReferencesTable(getDescription(UmlViewsRepository.Literals.literals_, UmlMessages.LiteralsPropertiesEditionPart_LiteralsLabel), new ReferencesTableListener() {
			public void handleAdd() {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LiteralsPropertiesEditionPartForm.this, UmlViewsRepository.Literals.literals_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
				literals.refresh();
			}
			public void handleEdit(EObject element) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LiteralsPropertiesEditionPartForm.this, UmlViewsRepository.Literals.literals_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
				literals.refresh();
			}
			public void handleMove(EObject element, int oldIndex, int newIndex) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LiteralsPropertiesEditionPartForm.this, UmlViewsRepository.Literals.literals_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
				literals.refresh();
			}
			public void handleRemove(EObject element) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LiteralsPropertiesEditionPartForm.this, UmlViewsRepository.Literals.literals_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
				literals.refresh();
			}
			public void navigateTo(EObject element) { }
		});
		for (ViewerFilter filter : this.literalsFilters) {
			this.literals.addFilter(filter);
		}
		this.literals.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Literals.literals_, UmlViewsRepository.FORM_KIND));
		this.literals.createControls(parent, widgetFactory);
		this.literals.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LiteralsPropertiesEditionPartForm.this, UmlViewsRepository.Literals.literals_, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData literalsData = new GridData(GridData.FILL_HORIZONTAL);
		literalsData.horizontalSpan = 3;
		this.literals.setLayoutData(literalsData);
		this.literals.setLowerBound(0);
		this.literals.setUpperBound(-1);
		literals.setID(UmlViewsRepository.Literals.literals_);
		literals.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		// Start of user code for createLiteralsTableComposition

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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LiteralsPropertiesEditionPart#initLiterals(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initLiterals(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		literals.setContentProvider(contentProvider);
		literals.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.Literals.literals_);
		if (eefElementEditorReadOnlyState && literals.isEnabled()) {
			literals.setEnabled(false);
			literals.setToolTipText(UmlMessages.Literals_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !literals.isEnabled()) {
			literals.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LiteralsPropertiesEditionPart#updateLiterals()
	 * @generated
	 */
	public void updateLiterals() {
	literals.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LiteralsPropertiesEditionPart#addFilterLiterals(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToLiterals(ViewerFilter filter) {
		literalsFilters.add(filter);
		if (this.literals != null) {
			this.literals.addFilter(filter);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LiteralsPropertiesEditionPart#addBusinessFilterLiterals(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToLiterals(ViewerFilter filter) {
		literalsBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LiteralsPropertiesEditionPart#isContainedInLiteralsTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInLiteralsTable(EObject element) {
		return ((ReferencesTableSettings)literals.getInput()).contains(element);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.Literals_Part_Title;
	}



}
