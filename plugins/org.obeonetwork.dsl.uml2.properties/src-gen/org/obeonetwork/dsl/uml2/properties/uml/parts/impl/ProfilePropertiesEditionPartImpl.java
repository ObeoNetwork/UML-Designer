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

import org.obeonetwork.dsl.uml2.properties.uml.components.PackageProfilePropertiesEditionComponent;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ProfilePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ProfilePropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ProfilePropertiesEditionPart {

	protected ReferencesTable appliedProfiles;
	protected List<ViewerFilter> appliedProfilesBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> appliedProfilesFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ProfilePropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence profileStep = new BindingCompositionSequence(propertiesEditionComponent);
		profileStep.addStep(UmlViewsRepository.Profile.appliedProfiles);
		
		composer = new PartComposer(profileStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.Profile.appliedProfiles) {
					return createAppliedProfilesAdvancedTableComposition(parent);
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
	protected Composite createAppliedProfilesAdvancedTableComposition(Composite parent) {
		this.appliedProfiles = new ReferencesTable(getDescription(UmlViewsRepository.Profile.appliedProfiles, UmlMessages.ProfilePropertiesEditionPart_AppliedProfilesLabel), new ReferencesTableListener() {
			public void handleAdd() { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProfilePropertiesEditionPartImpl.this, UmlViewsRepository.Profile.appliedProfiles, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
				appliedProfiles.refresh();
			}
			public void handleEdit(EObject element) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProfilePropertiesEditionPartImpl.this, UmlViewsRepository.Profile.appliedProfiles, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
				appliedProfiles.refresh();
			}
			public void handleMove(EObject element, int oldIndex, int newIndex) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProfilePropertiesEditionPartImpl.this, UmlViewsRepository.Profile.appliedProfiles, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
				appliedProfiles.refresh();
			}
			public void handleRemove(EObject element) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProfilePropertiesEditionPartImpl.this, UmlViewsRepository.Profile.appliedProfiles, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
				appliedProfiles.refresh();
			}
			public void navigateTo(EObject element) { }
		});
		for (ViewerFilter filter : this.appliedProfilesFilters) {
			this.appliedProfiles.addFilter(filter);
		}
		this.appliedProfiles.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Profile.appliedProfiles, UmlViewsRepository.SWT_KIND));
		this.appliedProfiles.createControls(parent);
		this.appliedProfiles.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProfilePropertiesEditionPartImpl.this, UmlViewsRepository.Profile.appliedProfiles, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData appliedProfilesData = new GridData(GridData.FILL_HORIZONTAL);
		appliedProfilesData.horizontalSpan = 3;
		this.appliedProfiles.setLayoutData(appliedProfilesData);
		this.appliedProfiles.setLowerBound(1);
		this.appliedProfiles.setUpperBound(1);
		appliedProfiles.setID(UmlViewsRepository.Profile.appliedProfiles);
		appliedProfiles.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		// Start of user code for createAppliedProfilesAdvancedTableComposition

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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfilePropertiesEditionPart#initAppliedProfiles(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initAppliedProfiles(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		appliedProfiles.setContentProvider(contentProvider);
		appliedProfiles.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.Profile.appliedProfiles, ((PackageProfilePropertiesEditionComponent) propertiesEditionComponent).getAppliedProfileSettings().getOrCreateSignificantObject());
		if (eefElementEditorReadOnlyState && appliedProfiles.isEnabled()) {
			appliedProfiles.setEnabled(false);
			appliedProfiles.setToolTipText(UmlMessages.Profile_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !appliedProfiles.isEnabled()) {
			appliedProfiles.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfilePropertiesEditionPart#updateAppliedProfiles()
	 * @generated
	 */
	public void updateAppliedProfiles() {
	appliedProfiles.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfilePropertiesEditionPart#addFilterAppliedProfiles(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToAppliedProfiles(ViewerFilter filter) {
		appliedProfilesFilters.add(filter);
		if (this.appliedProfiles != null) {
			this.appliedProfiles.addFilter(filter);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfilePropertiesEditionPart#addBusinessFilterAppliedProfiles(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToAppliedProfiles(ViewerFilter filter) {
		appliedProfilesBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProfilePropertiesEditionPart#isContainedInAppliedProfilesTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInAppliedProfilesTable(EObject element) {
		return ((ReferencesTableSettings)appliedProfiles.getInput()).contains(element);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.Profile_Part_Title;
	}



}
