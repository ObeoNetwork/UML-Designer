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

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;

/**
 * Content provider for OperationTables.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class OperationsTableContentProvider extends AdapterFactoryContentProvider {
    public static final int CURRENT_VALUES_KIND = 0;

    public static final int MATCHING_VALUES_KIND = 1;

    public int kind = CURRENT_VALUES_KIND;

    public OperationsTableContentProvider() {
        super(EEFRuntimePlugin.getDefault().getAdapterFactory());
    }

    public OperationsTableContentProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof OperationsTableSettings) {
            if (kind == CURRENT_VALUES_KIND)
                return ((OperationsTableSettings) inputElement).getValue();
            else if (kind == MATCHING_VALUES_KIND) {
                Object choiceOfValues = ((OperationsTableSettings) inputElement).choiceOfValues(adapterFactory);
                return choiceOfValues instanceof List ? ((List<?>) choiceOfValues).toArray() : new Object[] { choiceOfValues };
            }
        }
        return super.getElements(inputElement);
    }

}
