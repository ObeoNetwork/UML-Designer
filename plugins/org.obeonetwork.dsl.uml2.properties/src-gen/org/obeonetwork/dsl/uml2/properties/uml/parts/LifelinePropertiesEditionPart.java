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
package org.obeonetwork.dsl.uml2.properties.uml.parts;

import org.eclipse.emf.common.util.Enumerator;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ViewerFilter;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public interface LifelinePropertiesEditionPart {

	/**
	 * @return the name
	 * @generated
	 */
	public String getName();

	/**
	 * Defines a new name
	 * @param newValue the new name to set
	 * @generated
	 */
	public void setName(String newValue);


	/**
	 * @return the visibility
	 * @generated
	 */
	public Enumerator getVisibility();

	/**
	 * Init the visibility
	 * @param input the viewer input
	 * @param current the current value
	 */
	public void initVisibility(Object input, Enumerator current);

	/**
	 * Defines a new visibility
	 * @param newValue the new visibility to set
	 * @generated
	 */
	public void setVisibility(Enumerator newValue);




	/**
	 * Init the clientDependency
	 * @param settings settings for the clientDependency ReferencesTable 
	 */
	public void initClientDependency(ReferencesTableSettings settings);

	/**
	 * Update the clientDependency
	 * @param newValue the clientDependency to update
	 * @generated
	 */
	public void updateClientDependency();

	/**
	 * Adds the given filter to the clientDependency edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter);

	/**
	 * Adds the given filter to the clientDependency edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the clientDependency table
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element);


	/**
	 * @return the represents
	 * @generated
	 */
	public EObject getRepresents();

	/**
	 * Init the represents
	 * @param settings the combo setting
	 */
	public void initRepresents(EObjectFlatComboSettings settings);

	/**
	 * Defines a new represents
	 * @param newValue the new represents to set
	 * @generated
	 */
	public void setRepresents(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setRepresentsButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the represents edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToRepresents(ViewerFilter filter);

	/**
	 * Adds the given filter to the represents edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToRepresents(ViewerFilter filter);


	/**
	 * @return the interaction
	 * @generated
	 */
	public EObject getInteraction();

	/**
	 * Init the interaction
	 * @param settings the combo setting
	 */
	public void initInteraction(EObjectFlatComboSettings settings);

	/**
	 * Defines a new interaction
	 * @param newValue the new interaction to set
	 * @generated
	 */
	public void setInteraction(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setInteractionButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the interaction edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToInteraction(ViewerFilter filter);

	/**
	 * Adds the given filter to the interaction edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToInteraction(ViewerFilter filter);


	/**
	 * @return the decomposedAs
	 * @generated
	 */
	public EObject getDecomposedAs();

	/**
	 * Init the decomposedAs
	 * @param settings the combo setting
	 */
	public void initDecomposedAs(EObjectFlatComboSettings settings);

	/**
	 * Defines a new decomposedAs
	 * @param newValue the new decomposedAs to set
	 * @generated
	 */
	public void setDecomposedAs(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setDecomposedAsButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the decomposedAs edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToDecomposedAs(ViewerFilter filter);

	/**
	 * Adds the given filter to the decomposedAs edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToDecomposedAs(ViewerFilter filter);




	/**
	 * Init the coveredBy
	 * @param settings settings for the coveredBy ReferencesTable 
	 */
	public void initCoveredBy(ReferencesTableSettings settings);

	/**
	 * Update the coveredBy
	 * @param newValue the coveredBy to update
	 * @generated
	 */
	public void updateCoveredBy();

	/**
	 * Adds the given filter to the coveredBy edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToCoveredBy(ViewerFilter filter);

	/**
	 * Adds the given filter to the coveredBy edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToCoveredBy(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the coveredBy table
	 * @generated
	 */
	public boolean isContainedInCoveredByTable(EObject element);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
