package org.obeonetwork.gmf.runtime.diagram.ui.extension;

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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.NodeImageExtension;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author nlepine
 * @author hmarchadour
 */
public class ExtensionActivator extends AbstractUIPlugin {

	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "org.obeonetwork.gmf.runtime.diagram.ui.extension";

	/**
	 * The shared instance
	 */
	private static ExtensionActivator plugin;

	/**
	 * List of image extensions
	 */
	private List<NodeImageExtension> imageExtensions;

	/**
	 * The constructor
	 */
	public ExtensionActivator() {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		addImageExtensions();

	}

	/**
	 * add image extensions.
	 */
	private void addImageExtensions() {
		imageExtensions = new ArrayList<NodeImageExtension>();

		IConfigurationElement[] configurationElements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(NodeImageExtension.IMAGE_EXTENSION_ID);
		for (IConfigurationElement configElement : configurationElements) {
			String id = configElement.getAttribute(NodeImageExtension.ID);
			String topImage = configElement.getAttribute(NodeImageExtension.TOP_IMAGE);
			String bottomImage = configElement.getAttribute(NodeImageExtension.BOTTOM_IMAGE);
			String leftImage = configElement.getAttribute(NodeImageExtension.LEFT_IMAGE);
			String rightImage = configElement.getAttribute(NodeImageExtension.RIGHT_IMAGE);
			imageExtensions.add(new NodeImageExtension(id, topImage, bottomImage, leftImage, rightImage));
		}
	}

	/**
	 * {@inheritDoc} @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static ExtensionActivator getDefault() {
		return plugin;
	}

	/**
	 * @return the image extensions
	 */
	public List<NodeImageExtension> getImageExtensions() {
		return imageExtensions;
	}

	/**
	 * @param id
	 *            id extension
	 * @return the image extension corresponding to the id
	 */
	public NodeImageExtension getBestImageExtension(String id) {
		if (id == null)
			return null;
		for (NodeImageExtension extension : imageExtensions) {
			if (id.equals(extension.getId()))
				return extension;
		}
		return null;
	}
}
