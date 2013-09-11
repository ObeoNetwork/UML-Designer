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
package org.obeonetwork.dsl.uml2.design.tests.plugin.manual.services;

import org.eclipse.uml2.uml.Dependency;
import org.obeonetwork.dsl.uml2.design.services.DependencyServices;
import org.obeonetwork.dsl.uml2.design.tests.Activator;
import org.obeonetwork.dsl.uml2.design.tests.plugin.manual.services.compositestructure.AbstractCompositeStructueTests;

/**
 * Unit tests on Connector services.
 * 
 * @author <a href="mailto:hugo.marchadour@obeo.fr">Hugo Marchadour</a>
 */
public class DependencyServicesTests extends AbstractCompositeStructueTests {

	/**
	 * The test resource URI.
	 */
	private static final String RESOURCE_URI = Activator.PLUGIN_ID
			+ "/resources/compositeStructure/CSRelatedElements/compositeStructure_relatedElements.uml";

	private DependencyServices dependencyServices;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dependencyServices = new DependencyServices();
	}

	@Override
	public String getRessourceURI() {
		return RESOURCE_URI;
	}

	public void testGetClient() {
		for (Dependency dependency : AllDependencies) {
			assertNotNull(dependencyServices.getClient(dependency));
		}
	}
}
