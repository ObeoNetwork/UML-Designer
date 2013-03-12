/*******************************************************************************
 * Copyright (c) 2013 Obeo.
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
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.OperationsTableSettings;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public interface ProfilesPropertiesEditionPart {

	/**
	 * Init the appliedProfiles
	 * 
	 * @param settings
	 *            settings for the appliedProfiles ReferencesTable
	 */
	public void initAppliedProfiles(OperationsTableSettings settings);

	/**
	 * Update the appliedProfiles
	 * 
	 * @param newValue
	 *            the appliedProfiles to update
	 */
	public void updateAppliedProfiles();

	/**
	 * Adds the given filter to the appliedProfiles edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 */
	public void addFilterToAppliedProfiles(ViewerFilter filter);

	/**
	 * Adds the given filter to the appliedProfiles edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 */
	public void addBusinessFilterToAppliedProfiles(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the
	 *         appliedProfiles table
	 */
	public boolean isContainedInAppliedProfilesTable(EObject element);

	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 */
	public String getTitle();
}
