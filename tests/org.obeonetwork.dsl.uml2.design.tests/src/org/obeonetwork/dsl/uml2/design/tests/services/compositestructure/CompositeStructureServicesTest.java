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
package org.obeonetwork.dsl.uml2.design.tests.services.compositestructure;

import org.eclipse.uml2.uml.Dependency;
import org.obeonetwork.dsl.uml2.design.services.CompositeStructureServices;
import org.obeonetwork.dsl.uml2.design.tests.Activator;

public class CompositeStructureServicesTest extends AbstractCompositeStructueTests {

	private CompositeStructureServices cSService;

	/**
	 * The test resource URI.
	 */
	private static final String RESOURCE_URI = Activator.PLUGIN_ID
			+ "/resources/compositeStructure/CSRelatedElements/compositeStructure_relatedElements.uml";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		cSService = new CompositeStructureServices();
	}

	@Override
	public String getRessourceURI() {
		return RESOURCE_URI;
	}

	public void testGetClient() {
		for (Dependency dependency : AllDependencies) {
			assertNotNull(cSService.getClient(dependency));
		}
	}
	// TODO HMA
}
