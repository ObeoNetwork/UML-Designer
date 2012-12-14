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
public interface ConnectableElementTemplateParameterPropertiesEditionPart {

	/**
	 * @return the signature
	 * @generated
	 */
	public EObject getSignature();

	/**
	 * Init the signature
	 * @param settings the combo setting
	 */
	public void initSignature(EObjectFlatComboSettings settings);

	/**
	 * Defines a new signature
	 * @param newValue the new signature to set
	 * @generated
	 */
	public void setSignature(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setSignatureButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the signature edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSignature(ViewerFilter filter);

	/**
	 * Adds the given filter to the signature edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSignature(ViewerFilter filter);


	/**
	 * @return the parameteredElement
	 * @generated
	 */
	public EObject getParameteredElement();

	/**
	 * Init the parameteredElement
	 * @param settings the combo setting
	 */
	public void initParameteredElement(EObjectFlatComboSettings settings);

	/**
	 * Defines a new parameteredElement
	 * @param newValue the new parameteredElement to set
	 * @generated
	 */
	public void setParameteredElement(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setParameteredElementButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the parameteredElement edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToParameteredElement(ViewerFilter filter);

	/**
	 * Adds the given filter to the parameteredElement edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToParameteredElement(ViewerFilter filter);


	/**
	 * @return the default
	 * @generated
	 */
	public EObject getDefault_();

	/**
	 * Init the default
	 * @param settings the combo setting
	 */
	public void initDefault_(EObjectFlatComboSettings settings);

	/**
	 * Defines a new default
	 * @param newValue the new default to set
	 * @generated
	 */
	public void setDefault_(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setDefault_ButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the default edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToDefault_(ViewerFilter filter);

	/**
	 * Adds the given filter to the default edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToDefault_(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
