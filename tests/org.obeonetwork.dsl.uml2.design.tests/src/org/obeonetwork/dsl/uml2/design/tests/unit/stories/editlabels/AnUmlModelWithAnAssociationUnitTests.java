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
import org.obeonetwork.dsl.uml2.design.tests.contexts.AnUmlModelWithAnAssociation;

public class AnUmlModelWithAnAssociationUnitTests {
	@Rule
	public AnUmlModelWithAnAssociation context = new AnUmlModelWithAnAssociation();

	@Test
	public void classDiagramEditRolesNames() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("firstRole");
		context.actionIEditTheLabelOfTheSecondRoleTo("secondRole");
		context.assertTheFirstEndNameEquals("firstRole");
		context.assertTheSecondEndNameEquals("secondRole");
	}
	@Test
	public void classDiagramEditRolesNamesAndTrimSpaces() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("  firstRole  ");
		context.actionIEditTheLabelOfTheSecondRoleTo("  secondRole ");
		context.assertTheFirstEndNameEquals("firstRole");
		context.assertTheSecondEndNameEquals("secondRole");
	}
	@Test
	public void classDiagramEditRolesNames2() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("firstRoleRenamed ");
		context.actionIEditTheLabelOfTheSecondRoleTo("secondRole");
		context.assertTheFirstEndNameEquals("firstRoleRenamed");
		context.assertTheSecondEndNameEquals("secondRole");
	}
	@Test
	public void cardinality() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("firstRole[0..1]");
		context.actionIEditTheLabelOfTheSecondRoleTo("secondRole[*]");
		context.assertTheFirstEndLowerCardinalityEquals("0");
		context.assertTheFirstEndUpperCardinalityEquals("1");
		context.assertTheSecondEndLowerCardinalityEquals("0");
		context.assertTheSecondEndUpperCardinalityEquals("-1");
	}
	@Test
	public void cardinalityWithSpaces() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("firstRole [ 0 .. 1 ] ");
		context.actionIEditTheLabelOfTheSecondRoleTo(" secondRole [ * ]");
		context.assertTheFirstEndLowerCardinalityEquals("0");
		context.assertTheFirstEndUpperCardinalityEquals("1");
		context.assertTheSecondEndLowerCardinalityEquals("0");
		context.assertTheSecondEndUpperCardinalityEquals("-1");
	}
	@Test
	public void cardinalityWithSpaces2() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("firstRole[5..5] ");
		context.actionIEditTheLabelOfTheSecondRoleTo(" secondRole ");
		context.assertTheFirstEndLowerCardinalityEquals("5");
		context.assertTheFirstEndUpperCardinalityEquals("5");
		context.assertTheSecondEndLowerCardinalityEquals("1");
		context.assertTheSecondEndUpperCardinalityEquals("1");
	}
	@Test
	public void cardinalityOneEndChange() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("firstRole");
		context.actionIEditTheLabelOfTheSecondRoleTo(" secondRole[1..5]");
		context.assertTheFirstEndLowerCardinalityEquals("1");
		context.assertTheFirstEndUpperCardinalityEquals("1");
		context.assertTheSecondEndLowerCardinalityEquals("1");
		context.assertTheSecondEndUpperCardinalityEquals("5");
	}
	@Test
	public void cardinalityOneEndChange2() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("firstRole[*]");
		context.actionIEditTheLabelOfTheSecondRoleTo(" secondRole");
		context.assertTheFirstEndLowerCardinalityEquals("0");
		context.assertTheFirstEndUpperCardinalityEquals("-1");
		context.assertTheSecondEndLowerCardinalityEquals("1");
		context.assertTheSecondEndUpperCardinalityEquals("1");
	}
	@Test
	public void derivedNone() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("firstRole[0..1]");
		context.actionIEditTheLabelOfTheSecondRoleTo(" secondRole[*]");
		context.assertTheFirstEndIsNotDerived();
		context.assertTheSecondEndIsNotDerived();
	}
	@Test
	public void derivedFirst() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("/firstRole[0..1] ");
		context.actionIEditTheLabelOfTheSecondRoleTo(" secondRole[*]");
		context.assertTheFirstEndIsDerived();
		context.assertTheSecondEndIsNotDerived();
	}
	@Test
	public void derivedSecond() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("firstRole[0..1]");
		context.actionIEditTheLabelOfTheSecondRoleTo(" /secondRole[*]");
		context.assertTheFirstEndIsNotDerived();
		context.assertTheSecondEndIsDerived();
	}
	@Test
	public void derivedBoth() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("/firstRole[0..1] ");
		context.actionIEditTheLabelOfTheSecondRoleTo(" /secondRole[*]");
		context.assertTheFirstEndIsDerived();
		context.assertTheSecondEndIsDerived();
	}
	@Test
	public void derivedBothWithSpaces() throws Exception {
		context.actionIEditTheLabelOfTheFirstRoleTo("/ firstRole ");
		context.actionIEditTheLabelOfTheSecondRoleTo(" / secondRole[*] ");
		context.assertTheFirstEndIsDerived();
		context.assertTheSecondEndIsDerived();
	}
}
