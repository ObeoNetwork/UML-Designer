package org.obeonetwork.dsl.uml2.usage;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class UsageActivator extends Plugin implements BundleActivator {

	private static BundleContext context;
	
	/**
	 * The shared instance.
	 */
	private static UsageActivator plugin;


	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		UsageActivator.context = bundleContext;
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		UsageActivator.context = null;
	}

	
	/**
	 * Returns the shared instance.
	 * 
	 * @return the shared instance
	 */
	public static UsageActivator getDefault() {
		return plugin;
	}

}
