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

package org.obeonetwork.dsl.uml2.design.tests.plugin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.obeonetwork.dsl.uml2.design.tests.plugin.manual.UmlDesignerManualPluginTests;

@RunWith(Suite.class)
@SuiteClasses({UmlDesignerPluginTests.class, UmlDesignerManualPluginTests.class})
/**
 * Testing : All generated and manual plugin UML Designer tests
 */
public class AllPluginTests {

}
