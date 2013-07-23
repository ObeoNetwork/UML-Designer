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

package org.obeonetwork.dsl.uml2.design.tests.unit.stories.editlabels;
import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.contexts.NoneEndIsNavigable;

public class NoneEndIsNavigableUnitTests {
	@Rule
	public NoneEndIsNavigable context = new NoneEndIsNavigable();

	@Test
	public void classDiagramEditRolesNamesNoneNavigable() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("aNavigableProperty");
		context.assertTheFirstEndNameEquals("aNavigableProperty");
	}
	@Test
	public void classDiagramEditRolesNamesNoneNavigableCardspaces() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("aNavigableProperty  [ 2  .. 4  ]");
		context.assertTheFirstEndNameEquals("aNavigableProperty");
		context.assertTheFirstEndLowerCardinalityEquals("2");
		context.assertTheFirstEndUpperCardinalityEquals("4");
	}
}
