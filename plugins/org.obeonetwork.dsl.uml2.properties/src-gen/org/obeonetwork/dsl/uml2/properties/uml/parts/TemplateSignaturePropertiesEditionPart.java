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
public interface TemplateSignaturePropertiesEditionPart {



	/**
	 * Init the parameter
	 * @param settings settings for the parameter ReferencesTable 
	 */
	public void initParameter(ReferencesTableSettings settings);

	/**
	 * Update the parameter
	 * @param newValue the parameter to update
	 * @generated
	 */
	public void updateParameter();

	/**
	 * Adds the given filter to the parameter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToParameter(ViewerFilter filter);

	/**
	 * Adds the given filter to the parameter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToParameter(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the parameter table
	 * @generated
	 */
	public boolean isContainedInParameterTable(EObject element);


	/**
	 * @return the template
	 * @generated
	 */
	public EObject getTemplate();

	/**
	 * Init the template
	 * @param settings the combo setting
	 */
	public void initTemplate(EObjectFlatComboSettings settings);

	/**
	 * Defines a new template
	 * @param newValue the new template to set
	 * @generated
	 */
	public void setTemplate(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setTemplateButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the template edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToTemplate(ViewerFilter filter);

	/**
	 * Adds the given filter to the template edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToTemplate(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
