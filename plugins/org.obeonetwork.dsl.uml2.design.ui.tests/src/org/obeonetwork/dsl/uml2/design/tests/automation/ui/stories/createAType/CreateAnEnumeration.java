/*******************************************************************************
 * Copyright (c) 2009, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.tests.automation.ui.stories.createAType;

import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.automation.ui.contexts.TheReferenceClassDiagramOpened;

public class CreateAnEnumeration {
	@Rule
	public TheReferenceClassDiagramOpened context = new TheReferenceClassDiagramOpened();

	@Test
	public void test() throws Exception {
		action();
		asserts();
	}

	private void action() {
		context.actionCreateAnEnumeration();
	}

	private void asserts() {
		context.assertElementCreatedInUmlModel("Enumeration15");
		context.assertElementExistsInTheReferenceClassDiagram("Enumeration15");
	}
}
