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
public interface ControlFlowPropertiesEditionPart {

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
	 * @return the source
	 * @generated
	 */
	public EObject getSource();

	/**
	 * Init the source
	 * @param settings the combo setting
	 */
	public void initSource(EObjectFlatComboSettings settings);

	/**
	 * Defines a new source
	 * @param newValue the new source to set
	 * @generated
	 */
	public void setSource(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setSourceButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the source edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSource(ViewerFilter filter);

	/**
	 * Adds the given filter to the source edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSource(ViewerFilter filter);


	/**
	 * @return the target
	 * @generated
	 */
	public EObject getTarget();

	/**
	 * Init the target
	 * @param settings the combo setting
	 */
	public void initTarget(EObjectFlatComboSettings settings);

	/**
	 * Defines a new target
	 * @param newValue the new target to set
	 * @generated
	 */
	public void setTarget(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setTargetButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the target edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToTarget(ViewerFilter filter);

	/**
	 * Adds the given filter to the target edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToTarget(ViewerFilter filter);




	/**
	 * Init the redefinedEdge
	 * @param settings settings for the redefinedEdge ReferencesTable 
	 */
	public void initRedefinedEdge(ReferencesTableSettings settings);

	/**
	 * Update the redefinedEdge
	 * @param newValue the redefinedEdge to update
	 * @generated
	 */
	public void updateRedefinedEdge();

	/**
	 * Adds the given filter to the redefinedEdge edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToRedefinedEdge(ViewerFilter filter);

	/**
	 * Adds the given filter to the redefinedEdge edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedEdge(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the redefinedEdge table
	 * @generated
	 */
	public boolean isContainedInRedefinedEdgeTable(EObject element);




	/**
	 * Init the inPartition
	 * @param settings settings for the inPartition ReferencesTable 
	 */
	public void initInPartition(ReferencesTableSettings settings);

	/**
	 * Update the inPartition
	 * @param newValue the inPartition to update
	 * @generated
	 */
	public void updateInPartition();

	/**
	 * Adds the given filter to the inPartition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToInPartition(ViewerFilter filter);

	/**
	 * Adds the given filter to the inPartition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToInPartition(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the inPartition table
	 * @generated
	 */
	public boolean isContainedInInPartitionTable(EObject element);


	/**
	 * @return the interrupts
	 * @generated
	 */
	public EObject getInterrupts();

	/**
	 * Init the interrupts
	 * @param settings the combo setting
	 */
	public void initInterrupts(EObjectFlatComboSettings settings);

	/**
	 * Defines a new interrupts
	 * @param newValue the new interrupts to set
	 * @generated
	 */
	public void setInterrupts(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setInterruptsButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the interrupts edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToInterrupts(ViewerFilter filter);

	/**
	 * Adds the given filter to the interrupts edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToInterrupts(ViewerFilter filter);


	/**
	 * @return the inStructuredNode
	 * @generated
	 */
	public EObject getInStructuredNode();

	/**
	 * Init the inStructuredNode
	 * @param settings the combo setting
	 */
	public void initInStructuredNode(EObjectFlatComboSettings settings);

	/**
	 * Defines a new inStructuredNode
	 * @param newValue the new inStructuredNode to set
	 * @generated
	 */
	public void setInStructuredNode(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setInStructuredNodeButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the inStructuredNode edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToInStructuredNode(ViewerFilter filter);

	/**
	 * Adds the given filter to the inStructuredNode edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToInStructuredNode(ViewerFilter filter);


	/**
	 * @return the activity
	 * @generated
	 */
	public EObject getActivity();

	/**
	 * Init the activity
	 * @param settings the combo setting
	 */
	public void initActivity(EObjectFlatComboSettings settings);

	/**
	 * Defines a new activity
	 * @param newValue the new activity to set
	 * @generated
	 */
	public void setActivity(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setActivityButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the activity edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToActivity(ViewerFilter filter);

	/**
	 * Adds the given filter to the activity edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToActivity(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
