/*******************************************************************************
 * Copyright (c) 2009, 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.tests;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.obeonetwork.dsl.uml2.design.tests.services.ConnectorServicesTests;
import org.obeonetwork.dsl.uml2.design.tests.services.DiagramElementMappingSpecificationTests;
import org.obeonetwork.dsl.uml2.design.tests.services.compositestructure.CompositeStructureServicesTest;
import org.obeonetwork.dsl.uml2.design.tests.services.compositestructure.IsRelated4CompositeStructureServicesTests;
import org.obeonetwork.dsl.uml2.design.tests.services.compositestructure.IsRelated4CompositeStructureServicesWithConnectorTests;

/**
 * Test Suite including all the enabled tests.
 * 
 * @author Cedric Brun <a href="mailto:cedric.brun@obeo.fr">cedric.brun@obeo.fr</a>
 */
public final class CompositeStructureTests {

	/**
	 * Hidden constructor.
	 */
	private CompositeStructureTests() {
	}

	/**
	 * Launches the test with the given arguments.
	 * 
	 * @param args
	 *            Arguments of the testCase.
	 */
	public static void main(final String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * Creates the {@link junit.framework.TestSuite TestSuite} for all the test.
	 * 
	 * @return The testsuite containing all the tests
	 */
	public static Test suite() {
		final TestSuite suite = new TestSuite("UML Composite structure tests");
		suite.addTestSuite(IsRelated4CompositeStructureServicesTests.class);
		suite.addTestSuite(IsRelated4CompositeStructureServicesWithConnectorTests.class);
		suite.addTestSuite(ConnectorServicesTests.class);
		suite.addTestSuite(CompositeStructureServicesTest.class);
		suite.addTest(new JUnit4TestAdapter(DiagramElementMappingSpecificationTests.class));
		return suite;
	}

}
