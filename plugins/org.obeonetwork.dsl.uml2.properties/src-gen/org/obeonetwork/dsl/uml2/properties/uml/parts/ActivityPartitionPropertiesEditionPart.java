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
public interface ActivityPartitionPropertiesEditionPart {

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
	 * @return the isDimension
	 * @generated
	 */
	public Boolean getIsDimension();

	/**
	 * Defines a new isDimension
	 * @param newValue the new isDimension to set
	 * @generated
	 */
	public void setIsDimension(Boolean newValue);


	/**
	 * @return the isExternal
	 * @generated
	 */
	public Boolean getIsExternal();

	/**
	 * Defines a new isExternal
	 * @param newValue the new isExternal to set
	 * @generated
	 */
	public void setIsExternal(Boolean newValue);




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
	 * @return the superPartition
	 * @generated
	 */
	public EObject getSuperPartition();

	/**
	 * Init the superPartition
	 * @param settings the combo setting
	 */
	public void initSuperPartition(EObjectFlatComboSettings settings);

	/**
	 * Defines a new superPartition
	 * @param newValue the new superPartition to set
	 * @generated
	 */
	public void setSuperPartition(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setSuperPartitionButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the superPartition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSuperPartition(ViewerFilter filter);

	/**
	 * Adds the given filter to the superPartition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSuperPartition(ViewerFilter filter);


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
	 * Init the edge
	 * @param settings settings for the edge ReferencesTable 
	 */
	public void initEdge(ReferencesTableSettings settings);

	/**
	 * Update the edge
	 * @param newValue the edge to update
	 * @generated
	 */
	public void updateEdge();

	/**
	 * Adds the given filter to the edge edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToEdge(ViewerFilter filter);

	/**
	 * Adds the given filter to the edge edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToEdge(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the edge table
	 * @generated
	 */
	public boolean isContainedInEdgeTable(EObject element);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
