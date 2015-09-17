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
public interface RelationshipsPropertiesEditionPart {

    /**
     * Init the relationships
     * 
     * @param settings
     *            settings for the relationships ReferencesTable
     */
    public void initRelationshipsOriginating(OperationsTableSettings settings);

    /**
     * Update the relationships
     * 
     * @param newValue
     *            the relationships to update
     */
    public void updateRelationshipsOriginating();

    /**
     * Adds the given filter to the relationships edition editor.
     * 
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     */
    public void addFilterToRelationshipsOriginating(ViewerFilter filter);

    /**
     * Adds the given filter to the relationships edition editor.
     * 
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     */
    public void addBusinessFilterToRelationshipsOriginating(ViewerFilter filter);

    /**
     * @return true if the given element is contained inside the relationships
     *         table
     */
    public boolean isContainedInRelationshipsOriginatingTable(EObject element);

    /**
     * Init the relationshipsTargeting
     * 
     * @param settings
     *            settings for the relationshipsTargeting ReferencesTable
     */
    public void initRelationshipsTargeting(OperationsTableSettings settings);

    /**
     * Update the relationshipsTargeting
     * 
     * @param newValue
     *            the relationshipsTargeting to update
     */
    public void updateRelationshipsTargeting();

    /**
     * Adds the given filter to the relationshipsTargeting edition editor.
     * 
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     */
    public void addFilterToRelationshipsTargeting(ViewerFilter filter);

    /**
     * Adds the given filter to the relationshipsTargeting edition editor.
     * 
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     */
    public void addBusinessFilterToRelationshipsTargeting(ViewerFilter filter);

    /**
     * @return true if the given element is contained inside the
     *         relationshipsTargeting table
     */
    public boolean isContainedInRelationshipsTargetingTable(EObject element);

    /**
     * Returns the internationalized title text.
     * 
     * @return the internationalized title text.
     */
    public String getTitle();
}
