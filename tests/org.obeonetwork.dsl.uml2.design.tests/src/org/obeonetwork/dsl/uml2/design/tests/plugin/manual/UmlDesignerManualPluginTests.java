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
import org.obeonetwork.dsl.uml2.design.tests.plugin.manual.services.ConnectorServicesTests;
import org.obeonetwork.dsl.uml2.design.tests.plugin.manual.services.SequenceServiceTests;
import org.obeonetwork.dsl.uml2.design.tests.plugin.manual.services.compositestructure.IsRelated4CompositeStructureServicesTests;
import org.obeonetwork.dsl.uml2.design.tests.plugin.manual.services.compositestructure.IsRelated4CompositeStructureServicesWithConnectorTests;

@RunWith(Suite.class)
@SuiteClasses({SequenceServiceTests.class, IsRelated4CompositeStructureServicesTests.class,
		IsRelated4CompositeStructureServicesWithConnectorTests.class, ConnectorServicesTests.class,
		DiagramElementMappingSpecificationTests.class, InterpretedExpressionTests.class,
		VsmValidationTests.class, JavaExtensionTests.class, ServiceExpressionTests.class, DeclaredServiceTests.class})
/**
 * Testing : Hand written UML Designer plugin tests
 */
public class UmlDesignerManualPluginTests {

}
