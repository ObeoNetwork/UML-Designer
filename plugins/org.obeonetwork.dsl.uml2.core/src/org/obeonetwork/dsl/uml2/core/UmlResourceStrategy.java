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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.resource.strategy.AbstractResourceStrategyImpl;
/**
 * Specific resource strategy for UML projects.
 * @author Frederic Bats
 *         <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 *
 */
import org.eclipse.uml2.uml.resource.UMLResource;
public class UmlResourceStrategy extends AbstractResourceStrategyImpl {

    public UmlResourceStrategy() {
    }

    @Override
    public boolean canHandle(URI resourceURI, ResourceStrategyType resourceStrategyType) {
        return ResourceStrategyType.SEMANTIC_RESOURCE.equals(resourceStrategyType);
    }

    @Override
    public boolean isPotentialSemanticResource(URI uri) {
        boolean result = super.isPotentialSemanticResource(uri);

        if (!uri.isPlatform()) {
            return result;
        }
        

        String filePath = uri.toPlatformString(true);
        IFile ifile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(filePath));
        IProject project = ifile.getProject();
        List<String> projectNatures = new ArrayList<>();
        if (uri.isPlatform()) {
            IProjectDescription description;
            try {
                description = project.getDescription();
                projectNatures = Arrays.asList(description.getNatureIds());
            } catch (CoreException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        if (projectNatures.contains(ProjectNatureUml.NATURE_ID) && result && uri != null) {
            result = UMLResource.FILE_EXTENSION.equals(uri.fileExtension());
        }
        return result;
    }
}
