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
package org.eclipse.emf.eef.runtime.ui.widgets.referencestable;

import org.eclipse.emf.ecore.EObject;

/**
 * Defines the operations available on {@link OperationsTableSettings}.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public interface ITableOperations {
    /**
     * Operation called to fill the table elements.
     * 
     * @return All elements that should appear in the table
     */
    public Object[] getValues();

    /**
     * Operation called when an element is added into the table.
     * 
     * @param newValue
     *            New element to add in table
     */
    public void add(EObject newValue);

    /**
     * Operation called when an element is removed from the table.
     * 
     * @param valueToRemove
     *            Element to remove from table
     */
    public void remove(EObject valueToRemove);

    /**
     * Operation called to fill the selection element wizard when creating a new
     * element in table.
     * 
     * @return List of values available to add a new element in table
     */
    public Object choiceOfValues();

}
