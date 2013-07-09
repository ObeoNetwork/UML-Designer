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

import org.eclipse.uml2.uml.UMLPackage;
import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.automation.ui.contexts.TheReferenceClassDiagramOpened;

public class CreateAClass {
	@Rule
	public TheReferenceClassDiagramOpened context = new TheReferenceClassDiagramOpened();
	
	

	@Test
	public void createAClass() throws Exception {
		context.actionCreateAClass();		
		context.assertElementCreatedInUmlModel("Class1",UMLPackage.eINSTANCE.getClass_());
		context.assertElementExistsInTheReferenceClassDiagram("Class1");
	}
}
