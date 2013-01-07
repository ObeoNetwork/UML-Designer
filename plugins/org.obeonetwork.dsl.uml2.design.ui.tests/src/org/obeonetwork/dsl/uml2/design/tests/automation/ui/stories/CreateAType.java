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
package org.obeonetwork.dsl.uml2.design.tests.automation.ui.stories;

import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.automation.ui.contexts.TheReferenceClassDiagramOpened;

//@Story("CreateAType")
public class CreateAType extends TheReferenceClassDiagramOpened {
	// @Scenario("CreateAClass")
	@Test
	public void createAClass() throws Exception {
		actionCreateAClass();
		assertElementCreatedInUmlModel("Class16");
		assertElementExistsInTheReferenceClassDiagram("Class16");
	}

	// @Scenario("CreateAnEnumeration")
	@Test
	public void createAnEnumeration() throws Exception {
		actionCreateAnEnumeration();
		assertElementCreatedInUmlModel("Enumeration15");
		assertElementExistsInTheReferenceClassDiagram("Enumeration15");
	}
}
