package org.obeonetwork.dsl.uml2.dashboard;

import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class DashboardPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.obeonetwork.dsl.uml2.dashboard"; //$NON-NLS-1$

	// The shared instance
	private static DashboardPlugin plugin;
	
	/**
	 * The constructor
	 */
	public DashboardPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
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
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
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
	public static DashboardPlugin getDefault() {
		return plugin;
	}

}
