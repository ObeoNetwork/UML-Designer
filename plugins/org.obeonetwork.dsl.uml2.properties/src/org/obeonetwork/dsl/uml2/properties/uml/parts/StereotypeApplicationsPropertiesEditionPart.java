/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.parts;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.OperationsTableSettings;

/**
 * @author <a href="mailto:cedric.notot@obeo.fr">Cédric Notot</a>
 */
public interface StereotypeApplicationsPropertiesEditionPart {

	/**
	 * Init the stereotypeApplications applications
	 * 
	 * @param settings
	 *            settings for the stereotypeApplications ReferencesTable
	 */
	public void initStereotypeApplications(OperationsTableSettings settings);

	/**
	 * Update the stereotypeApplications
	 * 
	 * @param newValue
	 *            the stereotypeApplications to update
	 */
	public void updateStereotypeApplications();

	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 */
	public String getTitle();
}
