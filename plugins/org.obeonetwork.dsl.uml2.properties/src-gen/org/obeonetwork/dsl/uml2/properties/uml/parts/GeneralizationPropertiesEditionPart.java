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
public interface GeneralizationPropertiesEditionPart {

	/**
	 * @return the isSubstitutable
	 * @generated
	 */
	public Boolean getIsSubstitutable();

	/**
	 * Defines a new isSubstitutable
	 * @param newValue the new isSubstitutable to set
	 * @generated
	 */
	public void setIsSubstitutable(Boolean newValue);


	/**
	 * @return the general
	 * @generated
	 */
	public EObject getGeneral();

	/**
	 * Init the general
	 * @param settings the combo setting
	 */
	public void initGeneral(EObjectFlatComboSettings settings);

	/**
	 * Defines a new general
	 * @param newValue the new general to set
	 * @generated
	 */
	public void setGeneral(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setGeneralButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the general edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToGeneral(ViewerFilter filter);

	/**
	 * Adds the given filter to the general edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToGeneral(ViewerFilter filter);




	/**
	 * Init the generalizationSet
	 * @param settings settings for the generalizationSet ReferencesTable 
	 */
	public void initGeneralizationSet(ReferencesTableSettings settings);

	/**
	 * Update the generalizationSet
	 * @param newValue the generalizationSet to update
	 * @generated
	 */
	public void updateGeneralizationSet();

	/**
	 * Adds the given filter to the generalizationSet edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToGeneralizationSet(ViewerFilter filter);

	/**
	 * Adds the given filter to the generalizationSet edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToGeneralizationSet(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the generalizationSet table
	 * @generated
	 */
	public boolean isContainedInGeneralizationSetTable(EObject element);


	/**
	 * @return the specific
	 * @generated
	 */
	public EObject getSpecific();

	/**
	 * Init the specific
	 * @param settings the combo setting
	 */
	public void initSpecific(EObjectFlatComboSettings settings);

	/**
	 * Defines a new specific
	 * @param newValue the new specific to set
	 * @generated
	 */
	public void setSpecific(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setSpecificButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the specific edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSpecific(ViewerFilter filter);

	/**
	 * Adds the given filter to the specific edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSpecific(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
