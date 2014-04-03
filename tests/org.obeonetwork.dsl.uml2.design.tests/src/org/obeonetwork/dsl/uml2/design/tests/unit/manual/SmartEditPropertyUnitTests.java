package org.obeonetwork.dsl.uml2.design.tests.unit.manual;

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

import org.junit.Rule;
import org.junit.Test;
import org.obeonetwork.dsl.uml2.design.tests.contexts.AnUmlModelWithAProperty;
import org.obeonetwork.dsl.uml2.design.tests.unit.stories.editlabels.AnUmlModelWithAPropertyUnitTests;

public class SmartEditPropertyUnitTests {
	@Rule
	public AnUmlModelWithAProperty context = new AnUmlModelWithAProperty();

	// Case x
	@Test
	public void propertySmartEditLowerAndUpperMultiplicityOneDigit() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("1");
		context.assertTheLowerBoundOfThePropertyEquals("1");
		context.assertTheUpperBoundOfThePropertyEquals("1");
	}

	@Test
	public void propertySmartEditLowerAndUpperMultiplicity() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("42");
		context.assertTheLowerBoundOfThePropertyEquals("42");
		context.assertTheUpperBoundOfThePropertyEquals("42");
	}

	// Case x y
	@Test
	public void propertySmartEditLowerAndUpperMultiplicityOneDigitWithSpace3() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("1 3");
		context.assertTheLowerBoundOfThePropertyEquals("1");
		context.assertTheUpperBoundOfThePropertyEquals("3");
	}

	@Test
	public void propertySmartEditLowerAndUpperMultiplicityWithSpace3() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("16 42");
		context.assertTheLowerBoundOfThePropertyEquals("16");
		context.assertTheUpperBoundOfThePropertyEquals("42");
	}

	// Case x..y
	@Test
	public void propertySmartEditLowerAndUpperMultiplicityOneDigit3() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("1..3");
		context.assertTheLowerBoundOfThePropertyEquals("1");
		context.assertTheUpperBoundOfThePropertyEquals("3");
	}

	@Test
	public void propertySmartEditLowerAndUpperMultiplicity3() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("16..42");
		context.assertTheLowerBoundOfThePropertyEquals("16");
		context.assertTheUpperBoundOfThePropertyEquals("42");
	}

	// Case [x]
	@Test
	public void propertySmartEditLowerAndUpperMultiplicityOneDigit2() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("[1]");
		context.assertTheLowerBoundOfThePropertyEquals("1");
		context.assertTheUpperBoundOfThePropertyEquals("1");
	}

	@Test
	public void propertySmartEditLowerAndUpperMultiplicity2() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("[42]");
		context.assertTheLowerBoundOfThePropertyEquals("42");
		context.assertTheUpperBoundOfThePropertyEquals("42");
	}

	// Case [x..y]
	@Test
	public void propertySmartEditLowerAndUpperMultiplicityOneDigit4() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("[1..3]");
		context.assertTheLowerBoundOfThePropertyEquals("1");
		context.assertTheUpperBoundOfThePropertyEquals("3");
	}

	@Test
	public void propertySmartEditLowerAndUpperMultiplicity4() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("[16..42]");
		context.assertTheLowerBoundOfThePropertyEquals("16");
		context.assertTheUpperBoundOfThePropertyEquals("42");
	}

	// Case [x y]

	@Test
	public void propertySmartEditLowerAndUpperMultiplicityOneDigitWithSpace4() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("[1  3]");
		context.assertTheLowerBoundOfThePropertyEquals("1");
		context.assertTheUpperBoundOfThePropertyEquals("3");
	}

	@Test
	public void propertySmartEditLowerAndUpperMultiplicityWithSpace4() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("[16 42]");
		context.assertTheLowerBoundOfThePropertyEquals("16");
		context.assertTheUpperBoundOfThePropertyEquals("42");
	}

	// Case x..*
	@Test
	public void propertySmartEditUnboundMultiplicityOneDigit() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("1..*");
		context.assertTheLowerBoundOfThePropertyEquals("1");
		context.assertTheUpperBoundOfThePropertyEquals("-1");
	}

	@Test
	public void propertySmartEditUnboundMultiplicity() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("42..*");
		context.assertTheLowerBoundOfThePropertyEquals("42");
		context.assertTheUpperBoundOfThePropertyEquals("-1");
	}

	// Case [x..*]
	@Test
	public void propertySmartEditUnboundMultiplicityOneDigit2() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("[1..*]");
		context.assertTheLowerBoundOfThePropertyEquals("1");
		context.assertTheUpperBoundOfThePropertyEquals("-1");
	}

	@Test
	public void propertySmartEditUnboundMultiplicity2() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("[42..*]");
		context.assertTheLowerBoundOfThePropertyEquals("42");
		context.assertTheUpperBoundOfThePropertyEquals("-1");
	}

	// Case *
	@Test
	public void propertySmartEditUnboundMultiplicity3() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("*");
		context.assertTheLowerBoundOfThePropertyEquals("0");
		context.assertTheUpperBoundOfThePropertyEquals("-1");
	}

	// Case [*]
	@Test
	public void propertySmartEditUnboundMultiplicity4() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("[*]");
		context.assertTheLowerBoundOfThePropertyEquals("0");
		context.assertTheUpperBoundOfThePropertyEquals("-1");
	}

	// Case -1
	@Test
	public void propertySmartEditUnboundMultiplicity5() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("-1");
		context.assertTheLowerBoundOfThePropertyEquals("0");
		context.assertTheUpperBoundOfThePropertyEquals("-1");
	}

	// Case [*]
	@Test
	public void propertySmartEditUnboundMultiplicity6() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("[-1]");
		context.assertTheLowerBoundOfThePropertyEquals("0");
		context.assertTheUpperBoundOfThePropertyEquals("-1");
	}

	/**
	 * Case name
	 * 
	 * @see AnUmlModelWithAPropertyUnitTests#propertyName()
	 **/

	/**
	 * Case name:String
	 * 
	 * @see AnUmlModelWithAPropertyUnitTests#propertyNameAndType()
	 **/
	@Test
	public void propertyNameAndTypeWithoutWhitespace() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo("toto:Integer");
		context.assertThePropertyNameEquals("toto");
		context.assertThePropertyTypeEquals("Integer");
	}

	/**
	 * Case :String
	 * 
	 * @see AnUmlModelWithAPropertyUnitTests#propertyTypeOnly()
	 **/
	@Test
	public void propertyTypeOnlyWithoutWhitespace() throws Exception {
		context.actionIEditTheLabelOfThePropertyTo(":Integer");
		context.assertThePropertyNameEquals("oldName");
		context.assertThePropertyTypeEquals("Integer");
	}
}
