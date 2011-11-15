/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.services;

import org.eclipse.core.runtime.NullProgressMonitor;

import fr.obeo.dsl.viewpoint.DRepresentation;
import fr.obeo.dsl.viewpoint.business.api.dialect.DialectManager;

/**
 * Services to handle UI concerns.
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class UIServices {

    /**
     * Refresh a representation.
     * This service has been created to handle a bug on the generic tool :
     * the force refresh option does not work as expected
     * @param representation Representation to be refreshed
     * @return the specified representation
     */
    public DRepresentation refreshDiagram(DRepresentation representation) {
    	DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
    	return representation;
    }
}
