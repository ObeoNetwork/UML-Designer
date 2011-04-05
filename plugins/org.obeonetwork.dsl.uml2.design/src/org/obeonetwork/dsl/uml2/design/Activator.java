/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.obeonetwork.dsl.uml2.design.ui.extension.UmlSessionExtension;
import org.osgi.framework.BundleContext;

import fr.obeo.dsl.viewpoint.business.api.componentization.ViewpointRegistry;
import fr.obeo.dsl.viewpoint.description.Viewpoint;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.obeonetwork.dsl.uml2.design";

	// The shared instance
	private static Activator plugin;

	private static Set<Viewpoint> viewpoints; 

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		viewpoints = new HashSet<Viewpoint>();
		viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/description/uml2.odesign"));
		
		// Add extensions to Model Content view
		UmlSessionExtension.addExtension();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		if (viewpoints != null) {
			for (final Viewpoint viewpoint: viewpoints) {
				ViewpointRegistry.getInstance().disposeFromPlugin(viewpoint);
			}
			viewpoints.clear();
			viewpoints = null; 
		}
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static void log(int severity, String message, Throwable exception) {
		getDefault().getLog().log(new Status(severity, PLUGIN_ID, message, exception));
	}
}