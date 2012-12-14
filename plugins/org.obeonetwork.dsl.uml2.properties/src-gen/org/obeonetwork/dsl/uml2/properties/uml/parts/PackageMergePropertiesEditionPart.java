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
public interface PackageMergePropertiesEditionPart {

	/**
	 * @return the mergedPackage
	 * @generated
	 */
	public EObject getMergedPackage();

	/**
	 * Init the mergedPackage
	 * @param settings the combo setting
	 */
	public void initMergedPackage(EObjectFlatComboSettings settings);

	/**
	 * Defines a new mergedPackage
	 * @param newValue the new mergedPackage to set
	 * @generated
	 */
	public void setMergedPackage(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setMergedPackageButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the mergedPackage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToMergedPackage(ViewerFilter filter);

	/**
	 * Adds the given filter to the mergedPackage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToMergedPackage(ViewerFilter filter);


	/**
	 * @return the receivingPackage
	 * @generated
	 */
	public EObject getReceivingPackage();

	/**
	 * Init the receivingPackage
	 * @param settings the combo setting
	 */
	public void initReceivingPackage(EObjectFlatComboSettings settings);

	/**
	 * Defines a new receivingPackage
	 * @param newValue the new receivingPackage to set
	 * @generated
	 */
	public void setReceivingPackage(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setReceivingPackageButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the receivingPackage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToReceivingPackage(ViewerFilter filter);

	/**
	 * Adds the given filter to the receivingPackage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToReceivingPackage(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
