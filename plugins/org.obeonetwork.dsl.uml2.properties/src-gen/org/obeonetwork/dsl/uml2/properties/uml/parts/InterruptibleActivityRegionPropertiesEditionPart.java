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
package org.obeonetwork.dsl.uml2.properties.uml.parts;

// Start of user code for imports
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
public interface InterruptibleActivityRegionPropertiesEditionPart {

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
	 * Init the interruptingEdge
	 * @param settings settings for the interruptingEdge ReferencesTable 
	 */
	public void initInterruptingEdge(ReferencesTableSettings settings);

	/**
	 * Update the interruptingEdge
	 * @param newValue the interruptingEdge to update
	 * 
	 */
	public void updateInterruptingEdge();

	/**
	 * Adds the given filter to the interruptingEdge edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToInterruptingEdge(ViewerFilter filter);

	/**
	 * Adds the given filter to the interruptingEdge edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToInterruptingEdge(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the interruptingEdge table
	 * 
	 */
	public boolean isContainedInInterruptingEdgeTable(EObject element);





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
