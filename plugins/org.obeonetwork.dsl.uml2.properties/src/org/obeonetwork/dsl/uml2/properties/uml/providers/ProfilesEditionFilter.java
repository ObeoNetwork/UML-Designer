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
package org.obeonetwork.dsl.uml2.properties.uml.providers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;
import org.eclipse.jface.viewers.IFilter;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ProfilesPropertiesEditionPart;

/**
 * Provides the filter used by the plugin.xml to assign
 * {@link ProfilesPropertiesEditionPart} to an element.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class ProfilesEditionFilter implements IFilter {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
     */
    public boolean select(Object toTest) {
        EObject eObj = EEFUtils.resolveSemanticObject(toTest);
        return eObj != null && (eObj instanceof org.eclipse.uml2.uml.Package);
    }

}
