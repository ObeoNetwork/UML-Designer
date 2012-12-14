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
public interface ProfileApplicationPropertiesEditionPart {

	/**
	 * @return the appliedProfile
	 * @generated
	 */
	public EObject getAppliedProfile();

	/**
	 * Init the appliedProfile
	 * @param settings the combo setting
	 */
	public void initAppliedProfile(EObjectFlatComboSettings settings);

	/**
	 * Defines a new appliedProfile
	 * @param newValue the new appliedProfile to set
	 * @generated
	 */
	public void setAppliedProfile(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setAppliedProfileButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the appliedProfile edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToAppliedProfile(ViewerFilter filter);

	/**
	 * Adds the given filter to the appliedProfile edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToAppliedProfile(ViewerFilter filter);


	/**
	 * @return the isStrict
	 * @generated
	 */
	public Boolean getIsStrict();

	/**
	 * Defines a new isStrict
	 * @param newValue the new isStrict to set
	 * @generated
	 */
	public void setIsStrict(Boolean newValue);


	/**
	 * @return the applyingPackage
	 * @generated
	 */
	public EObject getApplyingPackage();

	/**
	 * Init the applyingPackage
	 * @param settings the combo setting
	 */
	public void initApplyingPackage(EObjectFlatComboSettings settings);

	/**
	 * Defines a new applyingPackage
	 * @param newValue the new applyingPackage to set
	 * @generated
	 */
	public void setApplyingPackage(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setApplyingPackageButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the applyingPackage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToApplyingPackage(ViewerFilter filter);

	/**
	 * Adds the given filter to the applyingPackage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToApplyingPackage(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
