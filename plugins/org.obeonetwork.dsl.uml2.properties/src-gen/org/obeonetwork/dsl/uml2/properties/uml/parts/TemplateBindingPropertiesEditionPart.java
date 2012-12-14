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
public interface TemplateBindingPropertiesEditionPart {

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
	 * @return the boundElement
	 * @generated
	 */
	public EObject getBoundElement();

	/**
	 * Init the boundElement
	 * @param settings the combo setting
	 */
	public void initBoundElement(EObjectFlatComboSettings settings);

	/**
	 * Defines a new boundElement
	 * @param newValue the new boundElement to set
	 * @generated
	 */
	public void setBoundElement(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setBoundElementButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the boundElement edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToBoundElement(ViewerFilter filter);

	/**
	 * Adds the given filter to the boundElement edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToBoundElement(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
