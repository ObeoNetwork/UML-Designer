/*******************************************************************************
 * Copyright (c) 2009, 2017 Obeo.
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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 *
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 */
@SuppressWarnings("restriction")
public class UMLDesignerPlugin extends AbstractUIPlugin {

	/**
	 * The plug-in ID.
	 */
	public static final String PLUGIN_ID = "org.obeonetwork.dsl.uml2.design"; //$NON-NLS-1$

	/**
	 * Name of the odesign file.
	 */
	public static final String ODESIGN_FILE_NAME = "uml2.odesign"; //$NON-NLS-1$

	/**
	 * The shared instance.
	 */
	private static UMLDesignerPlugin plugin;

	/**
	 * The {@link Viewpoint}s registered in this plug-in.
	 */
	private static Set<Viewpoint> viewpoints;

	/**
	 * Returns the shared instance.
	 *
	 * @return the shared instance
	 */
	public static UMLDesignerPlugin getDefault() {
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

	private SessionManagerListener notifWhenSessionAreCreated;

	/**
	 * The constructor.
	 */
	public UMLDesignerPlugin() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		viewpoints = new HashSet<Viewpoint>();
		viewpoints.addAll(
				ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/description/uml2.odesign")); //$NON-NLS-1$
		notifWhenSessionAreCreated = new SessionManagerListener.Stub() {
			@Override
			public void notifyAddSession(Session newSession) {
				final ResourceSet set = newSession.getTransactionalEditingDomain().getResourceSet();
				UMLResourcesUtil.init(set);
			}
		};
		SessionManager.INSTANCE.addSessionsListener(notifWhenSessionAreCreated);
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
		if (notifWhenSessionAreCreated != null) {
			SessionManager.INSTANCE.removeSessionsListener(notifWhenSessionAreCreated);
		}
		super.stop(context);
	}
}
