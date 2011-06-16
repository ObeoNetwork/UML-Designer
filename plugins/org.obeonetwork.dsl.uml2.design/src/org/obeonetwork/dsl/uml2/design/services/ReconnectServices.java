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
package org.obeonetwork.dsl.uml2.design.services;

import org.eclipse.uml2.uml.Element;
import org.obeonetwork.dsl.uml2.design.services.internal.ReconnectSwitch;

import fr.obeo.dsl.viewpoint.DEdge;
import fr.obeo.dsl.viewpoint.EdgeTarget;

/**
 * Utility services to manage edge reconnections.
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class ReconnectServices {

	/**
	 * Generic service used to process treatments on a reconnect The processing has to be defined by
	 * overriding the corresponding caseXXX.
	 * 
	 * @param context
	 *            Element attached to the existing edge
	 * @param edgeView
	 *            Represents the graphical new edge
	 * @param sourceView
	 *            Represents the graphical element pointed by the edge before reconnecting
	 * @param targetView
	 *            Represents the graphical element pointed by the edge after reconnecting
	 * @param source
	 *            Represents the semantic element pointed by the edge before reconnecting
	 * @param target
	 *            Represents the semantic element pointed by the edge after reconnecting
	 * @return the Element attached to the edge once it has been modified
	 */
	public Element reconnectEdge(Element context, DEdge edgeView, EdgeTarget sourceView,
			EdgeTarget targetView, Element source, Element target) {
		final ReconnectSwitch reconnectService = new ReconnectSwitch();

		// The edgeview represents the new graphical edge
		// with testing of its source and target nodes we can
		// know if the user reconnected the source or the target of the edge
		if (edgeView.getSourceNode().equals(targetView)) {
			reconnectService.setReconnectKind(ReconnectSwitch.RECONNECT_SOURCE);
		} else {
			reconnectService.setReconnectKind(ReconnectSwitch.RECONNECT_TARGET);
		}
		reconnectService.setOldPointedElement(source);
		reconnectService.setNewPointedElement(target);
		return reconnectService.doSwitch(context);
	}
}
