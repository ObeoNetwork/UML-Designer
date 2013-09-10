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

package org.obeonetwork.dsl.uml2.design.tests.ui.stories.showgraphicalelements;
import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.contexts.TheTestAllMappingsRepresentationsAreOpenedAndRefreshed;

public class TheTestAllMappingsRepresentationsAreOpenedAndRefreshedUiTests {
	@Rule
	public TheTestAllMappingsRepresentationsAreOpenedAndRefreshed context = new TheTestAllMappingsRepresentationsAreOpenedAndRefreshed();

	@Test
	public void showAllDefinedMappings() throws Exception {
		context.actionIHaveALookToAllTheDiagrams();
		context.assertAllTheGraphicalElementsAreStillVisible();
	}
}
