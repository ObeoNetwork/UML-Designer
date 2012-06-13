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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.ConnectorEnd;

import fr.obeo.dsl.viewpoint.DNode;
import fr.obeo.dsl.viewpoint.DSemanticDecorator;

/**
 * Utility services to manage operation creation.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public final class ConnectorServices {
	/**
	 * Test if a given connector should be displayed from the given ports.
	 * 
	 * @param self
	 *            the connector
	 * @param sourceView
	 *            the source view
	 * @param targetView
	 *            the target view
	 * @return true if the connector must be displayed.
	 */
	public boolean isValidConnector(org.eclipse.uml2.uml.Connector self, DNode sourceView, DNode targetView) {

		if (self.getEnds().size() == 2) {
			final ConnectorEnd connectorSource = self.getEnds().get(0);
			if (!isValidPart(connectorSource, getViewContainerTarget(sourceView)))
				return false;

			final ConnectorEnd connectorTarget = self.getEnds().get(1);
			if (!isValidPart(connectorTarget, getViewContainerTarget(targetView)))
				return false;
			
			return true;

		}
		return false;
	}

	/**
	 * Get the view eContainer semantic target.
	 * 
	 * @param view
	 *            the current view
	 * @return the container semantic target or null if any.
	 */
	private EObject getViewContainerTarget(DNode view) {
		if (view != null && view.eContainer() instanceof DSemanticDecorator) {
			return ((DSemanticDecorator)view.eContainer()).getTarget();
		}
		return null;

	}

	/**
	 * Tests if the semantic target is the same as the connector end part reference if one part is described.
	 * 
	 * @param end
	 *            the end part
	 * @param viewSemanticTarget
	 *            the semantic target
	 * @return true is it is the valid part.
	 */
	private boolean isValidPart(ConnectorEnd end, EObject viewSemanticTarget) {
		if (end != null && end.getPartWithPort() != null
				&& !EcoreUtil.equals(viewSemanticTarget, end.getPartWithPort()))
			return false;
		return true;

	}
}
