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
package org.eclipse.umlgen.gen.java.ui.launch;

import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.umlgen.gen.java.ui.launch.tabs.UML2JavaClassLaunchConfigurationTab;
import org.eclipse.umlgen.gen.java.ui.launch.tabs.UML2JavaComponentLaunchConfigurationTab;
import org.eclipse.umlgen.gen.java.ui.launch.tabs.UML2JavaGeneralLaunchConfigurationTab;
import org.eclipse.umlgen.gen.java.ui.launch.tabs.UML2JavaTypeLaunchConfigurationTab;

/**
 * The UML to Java tab groups creator.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 1.0
 */
public class UML2JavaLaunchConfigurationTabGroup extends org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog,
     *      java.lang.String)
     */
    public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
        setTabs(new ILaunchConfigurationTab[] {new UML2JavaGeneralLaunchConfigurationTab(),
                new UML2JavaClassLaunchConfigurationTab(), new UML2JavaComponentLaunchConfigurationTab(),
                new UML2JavaTypeLaunchConfigurationTab(), new CommonTab(), });
    }

}
