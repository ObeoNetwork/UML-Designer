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

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ViewerFilter;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public interface InterruptibleActivityRegionPropertiesEditionPart {

	/**
	 * @return the inActivity
	 * @generated
	 */
	public EObject getInActivity();

	/**
	 * Init the inActivity
	 * @param settings the combo setting
	 */
	public void initInActivity(EObjectFlatComboSettings settings);

	/**
	 * Defines a new inActivity
	 * @param newValue the new inActivity to set
	 * @generated
	 */
	public void setInActivity(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setInActivityButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the inActivity edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToInActivity(ViewerFilter filter);

	/**
	 * Adds the given filter to the inActivity edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToInActivity(ViewerFilter filter);




	/**
	 * Init the node
	 * @param settings settings for the node ReferencesTable 
	 */
	public void initNode(ReferencesTableSettings settings);

	/**
	 * Update the node
	 * @param newValue the node to update
	 * @generated
	 */
	public void updateNode();

	/**
	 * Adds the given filter to the node edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToNode(ViewerFilter filter);

	/**
	 * Adds the given filter to the node edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToNode(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the node table
	 * @generated
	 */
	public boolean isContainedInNodeTable(EObject element);




	/**
	 * Init the interruptingEdge
	 * @param settings settings for the interruptingEdge ReferencesTable 
	 */
	public void initInterruptingEdge(ReferencesTableSettings settings);

	/**
	 * Update the interruptingEdge
	 * @param newValue the interruptingEdge to update
	 * @generated
	 */
	public void updateInterruptingEdge();

	/**
	 * Adds the given filter to the interruptingEdge edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToInterruptingEdge(ViewerFilter filter);

	/**
	 * Adds the given filter to the interruptingEdge edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToInterruptingEdge(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the interruptingEdge table
	 * @generated
	 */
	public boolean isContainedInInterruptingEdgeTable(EObject element);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
