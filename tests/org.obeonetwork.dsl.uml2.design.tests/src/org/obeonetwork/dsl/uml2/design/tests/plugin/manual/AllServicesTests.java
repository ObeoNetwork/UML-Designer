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

package org.obeonetwork.dsl.uml2.design.tests.plugin.manual;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({JavaExtensionTests.class, DeclaredServiceTests.class,
		ServiceExpressionCaptureViewpointTests.class, ServiceExpressionDesignViewpointTests.class,
		ServiceExpressionExtendViewpointTests.class, ServiceExpressionReviewViewpointTests.class,
		ServiceExpressionDashboardViewpointTests.class})
/**
 * Testing : Hand written UML Designer plugin tests
 */
public class AllServicesTests {

}
