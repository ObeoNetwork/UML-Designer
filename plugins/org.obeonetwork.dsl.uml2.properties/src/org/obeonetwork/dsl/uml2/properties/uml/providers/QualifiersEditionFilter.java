/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.providers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.Property;
import org.obeonetwork.dsl.uml2.properties.uml.parts.AttributesPropertiesEditionPart;

/**
 * Provides the filter used by the plugin.xml to assign
 * {@link AttributesPropertiesEditionPart} to an element.
 * 
 * @author <a href="mailto:frederic.bats@obeo.fr">Frederic Bats</a>
 */
public class QualifiersEditionFilter implements IFilter {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
     */
    public boolean select(Object toTest) {
        EObject eObj = EEFUtils.resolveSemanticObject(toTest);
        return eObj != null
                && ("Property".equals(eObj.eClass().getName()) || eObj instanceof Property);
    }
}
