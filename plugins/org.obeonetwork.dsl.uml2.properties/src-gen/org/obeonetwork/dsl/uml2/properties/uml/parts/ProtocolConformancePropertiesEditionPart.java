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
import org.eclipse.jface.viewers.ViewerFilter;



// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public interface ProtocolConformancePropertiesEditionPart {

	/**
	 * @return the generalMachine
	 * 
	 */
	public EObject getGeneralMachine();

	/**
	 * Init the generalMachine
	 * @param settings the combo setting
	 */
	public void initGeneralMachine(EObjectFlatComboSettings settings);

	/**
	 * Defines a new generalMachine
	 * @param newValue the new generalMachine to set
	 * 
	 */
	public void setGeneralMachine(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setGeneralMachineButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the generalMachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToGeneralMachine(ViewerFilter filter);

	/**
	 * Adds the given filter to the generalMachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToGeneralMachine(ViewerFilter filter);


	/**
	 * @return the specificMachine
	 * 
	 */
	public EObject getSpecificMachine();

	/**
	 * Init the specificMachine
	 * @param settings the combo setting
	 */
	public void initSpecificMachine(EObjectFlatComboSettings settings);

	/**
	 * Defines a new specificMachine
	 * @param newValue the new specificMachine to set
	 * 
	 */
	public void setSpecificMachine(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setSpecificMachineButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the specificMachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToSpecificMachine(ViewerFilter filter);

	/**
	 * Adds the given filter to the specificMachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToSpecificMachine(ViewerFilter filter);





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
