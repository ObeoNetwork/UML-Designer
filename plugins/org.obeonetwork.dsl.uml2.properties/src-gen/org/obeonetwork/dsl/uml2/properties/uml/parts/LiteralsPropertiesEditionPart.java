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

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public interface LiteralsPropertiesEditionPart {

	/**
	 * Init the literals
	 * 
	 * @param current
	 *            the current value
	 * @param containgFeature
	 *            the feature where to navigate if necessary
	 * @param feature
	 *            the feature to manage
	 */
	public void initLiterals(ReferencesTableSettings settings);

	/**
	 * Update the literals
	 * 
	 * @param newValue
	 *            the literals to update
	 * @generated
	 */
	public void updateLiterals();

	/**
	 * Adds the given filter to the literals edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToLiterals(ViewerFilter filter);

	/**
	 * Adds the given filter to the literals edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToLiterals(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the literals table
	 * @generated
	 */
	public boolean isContainedInLiteralsTable(EObject element);

	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();

}
