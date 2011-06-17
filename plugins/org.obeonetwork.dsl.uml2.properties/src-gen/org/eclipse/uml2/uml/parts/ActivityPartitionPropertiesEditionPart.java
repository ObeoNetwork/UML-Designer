/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.uml2.uml.parts;

// Start of user code for imports
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ViewerFilter;



// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public interface ActivityPartitionPropertiesEditionPart {

	/**
	 * @return the name
	 * 
	 */
	public String getName();

	/**
	 * Defines a new name
	 * @param newValue the new name to set
	 * 
	 */
	public void setName(String newValue);


	/**
	 * @return the visibility
	 * 
	 */
	public Enumerator getVisibility();

	/**
	 * Init the visibility
	 * @param eenum the enum to manage
	 * @param current the current value
	 */
	public void initVisibility(EEnum eenum, Enumerator current);

	/**
	 * Defines a new visibility
	 * @param newValue the new visibility to set
	 * 
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
	 * 
	 */
	public void updateClientDependency();

	/**
	 * Adds the given filter to the clientDependency edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToClientDependency(ViewerFilter filter);

	/**
	 * Adds the given filter to the clientDependency edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the clientDependency table
	 * 
	 */
	public boolean isContainedInClientDependencyTable(EObject element);


	/**
	 * @return the inActivity
	 * 
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
	 * 
	 */
	public void setInActivity(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setInActivityButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the inActivity edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToInActivity(ViewerFilter filter);

	/**
	 * Adds the given filter to the inActivity edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToInActivity(ViewerFilter filter);


	/**
	 * @return the isDimension
	 * 
	 */
	public Boolean getIsDimension();

	/**
	 * Defines a new isDimension
	 * @param newValue the new isDimension to set
	 * 
	 */
	public void setIsDimension(Boolean newValue);


	/**
	 * @return the isExternal
	 * 
	 */
	public Boolean getIsExternal();

	/**
	 * Defines a new isExternal
	 * @param newValue the new isExternal to set
	 * 
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
	 * 
	 */
	public void updateNode();

	/**
	 * Adds the given filter to the node edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToNode(ViewerFilter filter);

	/**
	 * Adds the given filter to the node edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToNode(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the node table
	 * 
	 */
	public boolean isContainedInNodeTable(EObject element);


	/**
	 * @return the superPartition
	 * 
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
	 * 
	 */
	public void setSuperPartition(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setSuperPartitionButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the superPartition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToSuperPartition(ViewerFilter filter);

	/**
	 * Adds the given filter to the superPartition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToSuperPartition(ViewerFilter filter);


	/**
	 * @return the represents
	 * 
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
	 * 
	 */
	public void setRepresents(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setRepresentsButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the represents edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToRepresents(ViewerFilter filter);

	/**
	 * Adds the given filter to the represents edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
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
	 * 
	 */
	public void updateEdge();

	/**
	 * Adds the given filter to the edge edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToEdge(ViewerFilter filter);

	/**
	 * Adds the given filter to the edge edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToEdge(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the edge table
	 * 
	 */
	public boolean isContainedInEdgeTable(EObject element);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * 
	 */
	public String getTitle();

	// Start of user code for additional methods
	
	// End of user code

}
