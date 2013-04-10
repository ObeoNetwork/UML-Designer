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
package org.obeonetwork.dsl.uml2.usage.analytics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.obeonetwork.dsl.uml2.usage.UsageActivator;
import org.obeonetwork.dsl.uml2.usage.preferences.UsagePreferences;

import com.dmurph.tracking.AnalyticsConfigData;
import com.dmurph.tracking.JGoogleAnalyticsTracker;
import com.dmurph.tracking.JGoogleAnalyticsTracker.GoogleAnalyticsVersion;
import com.google.common.io.Files;

import fr.obeo.dsl.viewpoint.DRepresentation;
import fr.obeo.dsl.viewpoint.business.api.dialect.DialectManager;
import fr.obeo.dsl.viewpoint.description.RepresentationDescription;
import fr.obeo.dsl.viewpoint.description.Viewpoint;
import fr.obeo.dsl.viewpoint.ui.business.api.dialect.DialectEditor;

/**
 * Detect when a Sirius editor is used and send information to google analytics.
 * 
 * @author Cedric Brun <a
 *         href="mailto:cedric.brun@obeo.fr">cedric.brun@obeo.fr</a>
 * @author Melanie Bats <a
 *         href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class SiriusEditorsListener implements IPartListener2 {

	private static final String ANALYTICS_ID = UsageMessages.Usage_GoogleAnalytics;
	private JGoogleAnalyticsTracker tracker;
	private EclipseUserAgent eclipseUserAgent;
	private String hostname;
	private UsagePreferences preferences = new UsagePreferences();

	public SiriusEditorsListener() {
		AnalyticsConfigData config = new AnalyticsConfigData(ANALYTICS_ID);
		// if you want to set your own config parameters:
		eclipseUserAgent = new EclipseUserAgent();
		config.setUserAgent(eclipseUserAgent.toString());
		tracker = new JGoogleAnalyticsTracker(config,
				GoogleAnalyticsVersion.V_4_7_2);
		hostname = getOrCreateHostname();
	}

	private String getOrCreateHostname() {
		UUID idOne = UUID.randomUUID();
		String found = idOne.toString();
		try {
			found = retrieveMarker(found);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return found;
	}

	private String retrieveMarker(String found) throws IOException {
		IPath path = Platform.getStateLocation(UsageActivator.getDefault()
				.getBundle());
		if (path != null) {
			path = path.append("hostname");
			if (!path.toFile().exists()) {
				Files.write(found, path.toFile(), Charset.forName("UTF-8"));
			} else {
				if (path.toFile().canRead()) {
					return Files.readFirstLine(path.toFile(),
							Charset.forName("UTF-8"));
				}
			}
		}
		return found;
	}

	public void partActivated(IWorkbenchPartReference partRef) {
	}

	public void partBroughtToTop(IWorkbenchPartReference partRef) {
	}

	public void partClosed(IWorkbenchPartReference partRef) {
	}

	public void partDeactivated(IWorkbenchPartReference partRef) {
	}

	public void partOpened(IWorkbenchPartReference partRef) {
		if (!preferences.isEnabled())
			return;

		IWorkbenchPart part = partRef.getPart(false);
		if (part instanceof DialectEditor) {
			DialectEditor dEditor = (DialectEditor) part;
			DRepresentation rep = dEditor.getRepresentation();
			if (rep != null
					&& DialectManager.INSTANCE.getDescription(rep) != null) {
				RepresentationDescription description = DialectManager.INSTANCE
						.getDescription(rep);
				if (description != null) {
					String diagURI = EcoreUtil.getURI(description).toString();
					String diagTypeName = description.getName();
					String viewpointName = ((Viewpoint) description
							.eContainer()).getName();
					preferences.storeDiagramsUsage(diagTypeName);
					tracker.trackPageViewFromReferrer(viewpointName + "/"
							+ diagTypeName, diagURI.toString(), hostname,
							eclipseUserAgent.getApplicationName() + ":"
									+ eclipseUserAgent.getApplicationVersion(),
							"");
				}
			}
		}

	}

	public void partHidden(IWorkbenchPartReference partRef) {
	}

	public void partVisible(IWorkbenchPartReference partRef) {
	}

	public void partInputChanged(IWorkbenchPartReference partRef) {
	}

}
