/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.discovery;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.core.net.proxy.IProxyData;
import org.eclipse.core.net.proxy.IProxyService;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlDiscoveryActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.obeonetwork.dsl.uml2.discovery"; //$NON-NLS-1$

	// The shared instance
	private static UmlDiscoveryActivator plugin;

	private final ServiceTracker<?, ?> proxyTracker;

	/**
	 * The constructor
	 */
	public UmlDiscoveryActivator() {
		proxyTracker = new ServiceTracker<Object, Object>(
				FrameworkUtil.getBundle(this.getClass()).getBundleContext(), IProxyService.class.getName(),
				null);
		proxyTracker.open();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext )
	 */
	public void stop(BundleContext context) throws Exception {
		proxyTracker.close();
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static UmlDiscoveryActivator getDefault() {
		return plugin;
	}

	public void prepareProxySettings(String uriString) {
		URI uri;
		try {
			uri = new URI(uriString);
			IProxyService proxyService = UmlDiscoveryActivator.getDefault().getProxyService();
			IProxyData[] proxyDataForHost = proxyService.select(uri);
			for (IProxyData data : proxyDataForHost) {
				if (data.getHost() != null) {
					System.setProperty("http.proxySet", "true");
					System.setProperty("http.proxyHost", data.getHost());
				}
				if (data.getHost() != null) {
					System.setProperty("http.proxyPort", String.valueOf(data.getPort()));
				}
			}
			// Close the service and close the service tracker
			proxyService = null;
		} catch (URISyntaxException e) {
			getLog().log(new Status(IStatus.WARNING, PLUGIN_ID, e.getMessage(), e));
		}
	}

	public IProxyService getProxyService() {
		return (IProxyService)proxyTracker.getService();
	}

}
