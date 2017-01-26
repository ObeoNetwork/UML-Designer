/*******************************************************************************
 * Copyright (c) 2011, 2014 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephane Begaudeau (Obeo) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.gen.java.ui.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.umlgen.gen.java.main.Uml2java;

/**
 * Main entry point of the 'UML2 to Java' generation module.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 0.1
 */
public class GenerateAll {

    /**
     * The model URI.
     */
    private URI modelURI;

    /**
     * The output folder.
     */
    private File targetFolder;

    /**
     * The other arguments.
     */
    private List<? extends Object> arguments;

    /**
     * Constructor.
     * 
     * @param modelURI
     *            is the URI of the model.
     * @param targetFolder
     *            is the output folder
     * @param arguments
     *            are the other arguments
     */
    public GenerateAll(URI modelURI, File targetFolder, List<? extends Object> arguments) {
        this.modelURI = modelURI;
        this.targetFolder = targetFolder;
        this.arguments = arguments;
    }

    /**
     * Launches the generation.
     * 
     * @param monitor
     *            This will be used to display progress information to the user.
     * @throws IOException
     *             Thrown when the output cannot be saved.
     */
    public void doGenerate(IProgressMonitor monitor) throws IOException {
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }

        Uml2java generator = new Uml2java(modelURI, targetFolder, arguments);
        generator.doGenerate(BasicMonitor.toMonitor(monitor));

    }
}
