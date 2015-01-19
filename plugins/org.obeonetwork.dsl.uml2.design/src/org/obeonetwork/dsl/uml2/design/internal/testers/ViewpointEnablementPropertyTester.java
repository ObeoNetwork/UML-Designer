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
package org.obeonetwork.dsl.uml2.design.internal.testers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Tester to check viewpoints enablement.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ViewpointEnablementPropertyTester extends PropertyTester {

	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (receiver instanceof EObject) {
			final Session foundSession = SessionManager.INSTANCE.getSession((EObject)receiver);
			if (foundSession != null) {
				for (final Viewpoint vp : foundSession.getSelectedViewpoints(false)) {
					if (vp.getName().contains(property)) {
						return true;
					}

				}
			}

		}
		return false;
	}
}
