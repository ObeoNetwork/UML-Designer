/*******************************************************************************
 * Copyright (c) 20018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
/**
 * Specific Project nature for UML projects.
 * @author Frederic Bats
 *         <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 *
 */
public class ProjectNatureUml implements IProjectNature {

    // ID of the natures, which consists of Bundle-SymbolicName + ID
    public static final String NATURE_ID = "org.obeonetwork.dsl.uml2.core.umlProjectNature";

    private IProject project;

    @Override
    public void configure() throws CoreException {
    	// Nothing to do
    }

    @Override
    public void deconfigure() throws CoreException {
        // Nothing to do
    }

    @Override
    public IProject getProject() {
        return project;
    }

    @Override
    public void setProject(IProject project) {
        this.project = project;
    }

}
