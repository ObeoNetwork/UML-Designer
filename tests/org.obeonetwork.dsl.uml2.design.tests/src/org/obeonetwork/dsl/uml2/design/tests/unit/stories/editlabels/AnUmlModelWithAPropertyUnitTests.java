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
import org.obeonetwork.dsl.uml2.design.tests.contexts.AnUmlModelWithAProperty;

public class AnUmlModelWithAPropertyUnitTests {
	@Rule
	public AnUmlModelWithAProperty context = new AnUmlModelWithAProperty();

	@Test
	public void propertyName() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("toto");
		context.assertThePropertyNameEquals("toto");
	}
	@Test
	public void propertyNameWithWhitespaces() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("   	toto  	");
		context.assertThePropertyNameEquals("toto");
	}
	@Test
	public void propertyNameAndType() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("toto : Integer");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("Integer");
	}
	@Test
	public void propertyNameAndNotExistingType() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("toto : NonExistingType");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("String");
	}
	@Test
	public void propertyTypeOnly() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo(" : Integer");
		context.assertThePropertyNameEquals("");
		context.assertThePropertyTypeEquals("Integer");
	}
	@Test
	public void propertyIsDerivedAndName() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("/toto");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("String");
		context.assertThePropertyIsDerived();
	}
	@Test
	public void propertyIsDerivedAndWithoutName() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("/");
		context.assertThePropertyNameEquals("");
		context.assertThePropertyTypeEquals("String");
		context.assertThePropertyIsDerived();
	}
	@Test
	public void propertyIsDerivedNameAndType() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("/toto : Integer");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("Integer");
		context.assertThePropertyIsDerived();
	}
	@Test
	public void propertyIsDerivedNameAndNonExistingType() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("/toto : NonExistingType");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("String");
		context.assertThePropertyIsDerived();
	}
	@Test
	public void propertyNotIsDerivedAndName() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("toto  ");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyIsNotDerived();
	}
	@Test
	public void propertyNameTypeAndMultiplicityUsingOneBound() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("toto : Integer [1] ");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("Integer");
		context.assertTheLowerBoundOfThePropertyEquals("1");
		context.assertTheUpperBoundOfThePropertyEquals("1");
		context.assertThePropertyIsNotDerived();
	}
	@Test
	public void propertyNameTypeAndMultiplicityUsingOneBoundStar() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("toto : Integer [*] ");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("Integer");
		context.assertTheLowerBoundOfThePropertyEquals("0");
		context.assertTheUpperBoundOfThePropertyEquals("-1");
		context.assertThePropertyIsNotDerived();
	}
	@Test
	public void propertyNameTypeAndMultiplicityUsingOneBoundMinusOne() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("toto : Integer [-1] ");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("Integer");
		context.assertTheLowerBoundOfThePropertyEquals("0");
		context.assertTheUpperBoundOfThePropertyEquals("-1");
		context.assertThePropertyIsNotDerived();
	}
	@Test
	public void propertyNameTypeAndMultiplicityWithDifferentBounds() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("toto : Integer [1..5] ");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("Integer");
		context.assertTheLowerBoundOfThePropertyEquals("1");
		context.assertTheUpperBoundOfThePropertyEquals("5");
		context.assertThePropertyIsNotDerived();
	}
	@Test
	public void propertyNameTypeAndMultiplicityWithDifferentBoundsUsingStar() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("toto : Integer [1..*] ");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("Integer");
		context.assertTheLowerBoundOfThePropertyEquals("1");
		context.assertTheUpperBoundOfThePropertyEquals("-1");
		context.assertThePropertyIsNotDerived();
	}
	@Test
	public void propertyNameTypeAndMultiplicityWithDifferentBoundsUsingMinusOne() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("toto : Integer [5..-1] ");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("Integer");
		context.assertTheLowerBoundOfThePropertyEquals("5");
		context.assertTheUpperBoundOfThePropertyEquals("-1");
		context.assertThePropertyIsNotDerived();
	}
	@Test
	public void propertyFullWithLotsOfSpaces() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("  /   to   to    :    Integer     [    5    ..   -1   ]    ");
		context.assertThePropertyNameEquals("to   to");
		context.assertThePropertyTypeEquals("Integer");
		context.assertTheLowerBoundOfThePropertyEquals("5");
		context.assertTheUpperBoundOfThePropertyEquals("-1");
		context.assertThePropertyIsDerived();
	}
	@Test
	public void propertyNameTypeAndMultiplicityWithIncorrectBounds() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("  toto : Integer [*..*] ");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("Integer");
		context.assertTheLowerBoundOfThePropertyEquals("2");
		context.assertTheUpperBoundOfThePropertyEquals("4");
		context.assertThePropertyIsNotDerived();
	}
	@Test
	public void propertyNameTypeAndMultiplicityWithIncorrectBounds2() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("  toto : Integer [-1..2] ");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("Integer");
		context.assertTheLowerBoundOfThePropertyEquals("2");
		context.assertTheUpperBoundOfThePropertyEquals("4");
		context.assertThePropertyIsNotDerived();
	}
	@Test
	public void propertyNameTypeAndMultiplicityWithIncorrectBounds3() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("  toto : Integer [5..3] ");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("Integer");
		context.assertTheLowerBoundOfThePropertyEquals("2");
		context.assertTheUpperBoundOfThePropertyEquals("4");
		context.assertThePropertyIsNotDerived();
	}
}
