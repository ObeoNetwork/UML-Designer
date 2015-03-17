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
import org.obeonetwork.dsl.uml2.design.internal.services.DashboardServices;

/**
 * Check if dashboards exist (dashboard viewpoint is activated and the representation was created).
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DashboardPropertyTester extends PropertyTester {

	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		return !DashboardServices.INSTANCE.getUmlModelsWithDashboard().isEmpty();
	}
}
