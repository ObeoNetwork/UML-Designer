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
package org.obeonetwork.dsl.uml2.design.tests.automation.unit.stories.editElements;

import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.automation.unit.contexts.TheUmlModelWithAnAssociationAndTwoEndProperties;

public class EditAssociationRole {
	@Rule
	public TheUmlModelWithAnAssociationAndTwoEndProperties context = new TheUmlModelWithAnAssociationAndTwoEndProperties();

	@Test
	public void test() throws Exception {
		action();
		asserts();
	}

	private void action() {
		context.editFirstRole("firstRole");
		context.editSecondRole("secondRole");
	}

	private void asserts() {
		context.assertfirstEndEquals("firstRole");
		context.assertSecondEndEquals("secondRole");
	}
}
