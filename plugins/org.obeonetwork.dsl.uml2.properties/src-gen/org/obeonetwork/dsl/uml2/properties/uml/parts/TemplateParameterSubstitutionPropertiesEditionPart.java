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
public interface TemplateParameterSubstitutionPropertiesEditionPart {

	/**
	 * @return the formal
	 * @generated
	 */
	public EObject getFormal();

	/**
	 * Init the formal
	 * @param settings the combo setting
	 */
	public void initFormal(EObjectFlatComboSettings settings);

	/**
	 * Defines a new formal
	 * @param newValue the new formal to set
	 * @generated
	 */
	public void setFormal(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setFormalButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the formal edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToFormal(ViewerFilter filter);

	/**
	 * Adds the given filter to the formal edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToFormal(ViewerFilter filter);


	/**
	 * @return the actual
	 * @generated
	 */
	public EObject getActual();

	/**
	 * Init the actual
	 * @param settings the combo setting
	 */
	public void initActual(EObjectFlatComboSettings settings);

	/**
	 * Defines a new actual
	 * @param newValue the new actual to set
	 * @generated
	 */
	public void setActual(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setActualButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the actual edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToActual(ViewerFilter filter);

	/**
	 * Adds the given filter to the actual edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToActual(ViewerFilter filter);


	/**
	 * @return the templateBinding
	 * @generated
	 */
	public EObject getTemplateBinding();

	/**
	 * Init the templateBinding
	 * @param settings the combo setting
	 */
	public void initTemplateBinding(EObjectFlatComboSettings settings);

	/**
	 * Defines a new templateBinding
	 * @param newValue the new templateBinding to set
	 * @generated
	 */
	public void setTemplateBinding(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setTemplateBindingButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the templateBinding edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToTemplateBinding(ViewerFilter filter);

	/**
	 * Adds the given filter to the templateBinding edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToTemplateBinding(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
