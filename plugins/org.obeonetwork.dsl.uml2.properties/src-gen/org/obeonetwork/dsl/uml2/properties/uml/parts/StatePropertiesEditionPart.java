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
public interface StatePropertiesEditionPart {

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
	 * @return the isLeaf
	 * 
	 */
	public Boolean getIsLeaf();

	/**
	 * Defines a new isLeaf
	 * @param newValue the new isLeaf to set
	 * 
	 */
	public void setIsLeaf(Boolean newValue);


	/**
	 * @return the container
	 * 
	 */
	public EObject getContainer();

	/**
	 * Init the container
	 * @param settings the combo setting
	 */
	public void initContainer(EObjectFlatComboSettings settings);

	/**
	 * Defines a new container
	 * @param newValue the new container to set
	 * 
	 */
	public void setContainer(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setContainerButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the container edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToContainer(ViewerFilter filter);

	/**
	 * Adds the given filter to the container edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToContainer(ViewerFilter filter);


	/**
	 * @return the submachine
	 * 
	 */
	public EObject getSubmachine();

	/**
	 * Init the submachine
	 * @param settings the combo setting
	 */
	public void initSubmachine(EObjectFlatComboSettings settings);

	/**
	 * Defines a new submachine
	 * @param newValue the new submachine to set
	 * 
	 */
	public void setSubmachine(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setSubmachineButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the submachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToSubmachine(ViewerFilter filter);

	/**
	 * Adds the given filter to the submachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToSubmachine(ViewerFilter filter);


	/**
	 * @return the redefinedState
	 * 
	 */
	public EObject getRedefinedState();

	/**
	 * Init the redefinedState
	 * @param settings the combo setting
	 */
	public void initRedefinedState(EObjectFlatComboSettings settings);

	/**
	 * Defines a new redefinedState
	 * @param newValue the new redefinedState to set
	 * 
	 */
	public void setRedefinedState(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setRedefinedStateButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the redefinedState edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToRedefinedState(ViewerFilter filter);

	/**
	 * Adds the given filter to the redefinedState edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToRedefinedState(ViewerFilter filter);





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
