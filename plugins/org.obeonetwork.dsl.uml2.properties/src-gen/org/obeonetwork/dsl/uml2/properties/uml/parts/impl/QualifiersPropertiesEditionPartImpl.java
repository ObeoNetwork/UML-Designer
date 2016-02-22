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

import org.obeonetwork.dsl.uml2.properties.uml.parts.QualifiersPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class QualifiersPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, QualifiersPropertiesEditionPart {

	protected ReferencesTable qualifiers;

	protected List<ViewerFilter> qualifiersBusinessFilters = new ArrayList<ViewerFilter>();

	protected List<ViewerFilter> qualifiersFilters = new ArrayList<ViewerFilter>();

	/**
	 * Default constructor
	 * 
	 * @param editionComponent
	 *            the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public QualifiersPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 *      createFigure(org.eclipse.swt.widgets.Composite)
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
	 *      createControls(org.eclipse.swt.widgets.Composite)
	 * @generated
	 */
	public void createControls(Composite view) {
		CompositionSequence qualifiersStep = new BindingCompositionSequence(propertiesEditionComponent);
		qualifiersStep.addStep(UmlViewsRepository.Qualifiers.qualifiers_);

		composer = new PartComposer(qualifiersStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.Qualifiers.qualifiers_) {
					return createQualifiersAdvancedTableComposition(parent);
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
	protected Composite createQualifiersAdvancedTableComposition(Composite parent) {
		this.qualifiers = new ReferencesTable(
				getDescription(UmlViewsRepository.Qualifiers.qualifiers_,
						UmlMessages.QualifiersPropertiesEditionPart_QualifiersLabel),
				new ReferencesTableListener() {
					public void handleAdd() {
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(
								QualifiersPropertiesEditionPartImpl.this,
								UmlViewsRepository.Qualifiers.qualifiers_, PropertiesEditionEvent.COMMIT,
								PropertiesEditionEvent.ADD, null, null));
						qualifiers.refresh();
					}

					public void handleEdit(EObject element) {
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(
								QualifiersPropertiesEditionPartImpl.this,
								UmlViewsRepository.Qualifiers.qualifiers_, PropertiesEditionEvent.COMMIT,
								PropertiesEditionEvent.EDIT, null, element));
						qualifiers.refresh();
					}

					public void handleMove(EObject element, int oldIndex, int newIndex) {
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(
								QualifiersPropertiesEditionPartImpl.this,
								UmlViewsRepository.Qualifiers.qualifiers_, PropertiesEditionEvent.COMMIT,
								PropertiesEditionEvent.MOVE, element, newIndex));
						qualifiers.refresh();
					}

					public void handleRemove(EObject element) {
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(
								QualifiersPropertiesEditionPartImpl.this,
								UmlViewsRepository.Qualifiers.qualifiers_, PropertiesEditionEvent.COMMIT,
								PropertiesEditionEvent.REMOVE, null, element));
						qualifiers.refresh();
					}

					public void navigateTo(EObject element) {
					}
				});
		for (ViewerFilter filter : this.qualifiersFilters) {
			this.qualifiers.addFilter(filter);
		}
		this.qualifiers.setHelpText(propertiesEditionComponent
				.getHelpContent(UmlViewsRepository.Qualifiers.qualifiers_, UmlViewsRepository.SWT_KIND));
		this.qualifiers.createControls(parent);
		this.qualifiers.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(
							new PropertiesEditionEvent(QualifiersPropertiesEditionPartImpl.this,
									UmlViewsRepository.Qualifiers.qualifiers_, PropertiesEditionEvent.CHANGE,
									PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}

		});
		GridData qualifiersData = new GridData(GridData.FILL_HORIZONTAL);
		qualifiersData.horizontalSpan = 3;
		this.qualifiers.setLayoutData(qualifiersData);
		this.qualifiers.setLowerBound(0);
		this.qualifiers.setUpperBound(-1);
		qualifiers.setID(UmlViewsRepository.Qualifiers.qualifiers_);
		qualifiers.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		// Start of user code for createQualifiersAdvancedTableComposition

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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifiersPropertiesEditionPart#initQualifiers(EObject
	 *      current, EReference containingFeature, EReference feature)
	 */
	public void initQualifiers(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		qualifiers.setContentProvider(contentProvider);
		qualifiers.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.Qualifiers.qualifiers_);
		if (eefElementEditorReadOnlyState && qualifiers.isEnabled()) {
			qualifiers.setEnabled(false);
			qualifiers.setToolTipText(UmlMessages.Qualifiers_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !qualifiers.isEnabled()) {
			qualifiers.setEnabled(true);
		}

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifiersPropertiesEditionPart#updateQualifiers()
	 * @generated
	 */
	public void updateQualifiers() {
		qualifiers.refresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifiersPropertiesEditionPart#addFilterQualifiers(ViewerFilter
	 *      filter)
	 * @generated
	 */
	public void addFilterToQualifiers(ViewerFilter filter) {
		qualifiersFilters.add(filter);
		if (this.qualifiers != null) {
			this.qualifiers.addFilter(filter);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifiersPropertiesEditionPart#addBusinessFilterQualifiers(ViewerFilter
	 *      filter)
	 * @generated
	 */
	public void addBusinessFilterToQualifiers(ViewerFilter filter) {
		qualifiersBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.QualifiersPropertiesEditionPart#isContainedInQualifiersTable(EObject
	 *      element)
	 * @generated
	 */
	public boolean isContainedInQualifiersTable(EObject element) {
		return ((ReferencesTableSettings)qualifiers.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.Qualifiers_Part_Title;
	}

}
