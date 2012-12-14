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
public interface RegionPropertiesEditionPart {

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
	 * @return the isLeaf
	 * @generated
	 */
	public Boolean getIsLeaf();

	/**
	 * Defines a new isLeaf
	 * @param newValue the new isLeaf to set
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue);


	/**
	 * @return the state
	 * @generated
	 */
	public EObject getState();

	/**
	 * Init the state
	 * @param settings the combo setting
	 */
	public void initState(EObjectFlatComboSettings settings);

	/**
	 * Defines a new state
	 * @param newValue the new state to set
	 * @generated
	 */
	public void setState(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setStateButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the state edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToState(ViewerFilter filter);

	/**
	 * Adds the given filter to the state edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToState(ViewerFilter filter);


	/**
	 * @return the extendedRegion
	 * @generated
	 */
	public EObject getExtendedRegion();

	/**
	 * Init the extendedRegion
	 * @param settings the combo setting
	 */
	public void initExtendedRegion(EObjectFlatComboSettings settings);

	/**
	 * Defines a new extendedRegion
	 * @param newValue the new extendedRegion to set
	 * @generated
	 */
	public void setExtendedRegion(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setExtendedRegionButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the extendedRegion edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToExtendedRegion(ViewerFilter filter);

	/**
	 * Adds the given filter to the extendedRegion edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToExtendedRegion(ViewerFilter filter);


	/**
	 * @return the stateMachine
	 * @generated
	 */
	public EObject getStateMachine();

	/**
	 * Init the stateMachine
	 * @param settings the combo setting
	 */
	public void initStateMachine(EObjectFlatComboSettings settings);

	/**
	 * Defines a new stateMachine
	 * @param newValue the new stateMachine to set
	 * @generated
	 */
	public void setStateMachine(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setStateMachineButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the stateMachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToStateMachine(ViewerFilter filter);

	/**
	 * Adds the given filter to the stateMachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToStateMachine(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
