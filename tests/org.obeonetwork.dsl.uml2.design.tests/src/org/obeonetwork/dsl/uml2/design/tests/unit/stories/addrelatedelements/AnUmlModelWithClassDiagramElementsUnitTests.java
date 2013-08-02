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

package org.obeonetwork.dsl.uml2.design.tests.unit.stories.addrelatedelements;
import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.contexts.AnUmlModelWithClassDiagramElements;

public class AnUmlModelWithClassDiagramElementsUnitTests {
	@Rule
	public AnUmlModelWithClassDiagramElements context = new AnUmlModelWithClassDiagramElements();

	@Test
	public void addRelatedElementsOfAClass() throws Exception {
		context.actionIAskForTheRelatedElementsOf("Class1");
		context.assertTheNumberOfRelatedElementsFoundIs("3");
		context.assertTheRelatedElementsAre("Class2","Interface1","Component1");
	}
}
