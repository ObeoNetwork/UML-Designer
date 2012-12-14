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

import org.eclipse.jface.viewers.ViewerFilter;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public interface ProtocolConformancePropertiesEditionPart {

	/**
	 * @return the generalMachine
	 * @generated
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
	 * @generated
	 */
	public void setGeneralMachine(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setGeneralMachineButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the generalMachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToGeneralMachine(ViewerFilter filter);

	/**
	 * Adds the given filter to the generalMachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToGeneralMachine(ViewerFilter filter);


	/**
	 * @return the specificMachine
	 * @generated
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
	 * @generated
	 */
	public void setSpecificMachine(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setSpecificMachineButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the specificMachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSpecificMachine(ViewerFilter filter);

	/**
	 * Adds the given filter to the specificMachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSpecificMachine(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
