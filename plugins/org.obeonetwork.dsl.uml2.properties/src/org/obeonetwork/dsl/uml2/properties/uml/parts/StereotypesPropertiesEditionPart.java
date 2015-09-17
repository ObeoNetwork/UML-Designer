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
public interface StereotypesPropertiesEditionPart {

    /**
     * Init the appliedStereotypes
     * 
     * @param settings
     *            settings for the appliedStereotypes ReferencesTable
     */
    public void initAppliedStereotypes(OperationsTableSettings settings);

    /**
     * Update the appliedStereotypes
     * 
     * @param newValue
     *            the appliedStereotypes to update
     */
    public void updateAppliedStereotypes();

    /**
     * Adds the given filter to the appliedStereotypes edition editor.
     * 
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     */
    public void addFilterToAppliedStereotypes(ViewerFilter filter);

    /**
     * Adds the given filter to the appliedStereotypes edition editor.
     * 
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     */
    public void addBusinessFilterToAppliedStereotypes(ViewerFilter filter);

    /**
     * @return true if the given element is contained inside the
     *         appliedStereotypes table
     */
    public boolean isContainedInAppliedStereotypesTable(EObject element);

    /**
     * Returns the internationalized title text.
     * 
     * @return the internationalized title text.
     */
    public String getTitle();
}
