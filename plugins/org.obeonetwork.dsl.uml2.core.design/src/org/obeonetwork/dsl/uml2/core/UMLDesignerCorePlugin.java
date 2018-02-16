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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 *
 * @author Frederic Bats
 *         <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
@SuppressWarnings("restriction")
public class UMLDesignerCorePlugin extends AbstractUIPlugin {

    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "org.obeonetwork.dsl.uml2.core.design"; //$NON-NLS-1$

    /**
     * Name of the odesign file.
     */
    public static final String ODESIGN_FILE_NAME = "uml2core.odesign"; //$NON-NLS-1$

    /**
     * The shared instance.
     */
    private static UMLDesignerCorePlugin plugin;

    private static Set<Viewpoint> viewpoints;

    /**
     * Label provider.
     */
    private ICommonLabelProvider labelProvider = null;

    /**
     * The constructor.
     */
    public UMLDesignerCorePlugin() {
    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance
     */
    public static UMLDesignerCorePlugin getDefault() {
        return plugin;
    }

    /**
     * A helper to log plugin errors.
     *
     * @param severity
     *            the error severity.
     * @param message
     *            the error message.
     * @param exception
     *            the error exception.
     */
    public static void log(int severity, String message, Throwable exception) {
        getDefault().getLog().log(new Status(severity, PLUGIN_ID, message, exception));
    }

    /**
     * Returns the label provider to use for displaying locked elements.
     *
     * @return the label provider to use for displaying locked elements.
     */
    public ICommonLabelProvider getLabelProvider() {
        if (labelProvider == null) {
            labelProvider = new SiriusCommonLabelProvider();
        }
        return labelProvider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;

        viewpoints = new HashSet<Viewpoint>();
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/description/" + ODESIGN_FILE_NAME));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        if (viewpoints != null) {
            for (final Viewpoint viewpoint : viewpoints) {
                ViewpointRegistry.getInstance().disposeFromPlugin(viewpoint);
            }
            viewpoints.clear();
            viewpoints = null;
        }
        super.stop(context);
    }
}
