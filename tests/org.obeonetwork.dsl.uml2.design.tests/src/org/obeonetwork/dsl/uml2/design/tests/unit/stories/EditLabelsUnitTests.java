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

package org.obeonetwork.dsl.uml2.design.tests.unit.stories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.obeonetwork.dsl.uml2.design.tests.unit.stories.editlabels.AnUmlModelWithAPropertyUnitTests;
import org.obeonetwork.dsl.uml2.design.tests.unit.stories.editlabels.AnUmlModelWithAnOperationUnitTests;
import org.obeonetwork.dsl.uml2.design.tests.unit.stories.editlabels.AnUmlModelWithAnAssociationUnitTests;
import org.obeonetwork.dsl.uml2.design.tests.unit.stories.editlabels.NoneEndIsNavigableUnitTests;
import org.obeonetwork.dsl.uml2.design.tests.unit.stories.editlabels.TheSecondEndIsNavigableUnitTests;

@RunWith(Suite.class)
@SuiteClasses({TheSecondEndIsNavigableUnitTests.class,AnUmlModelWithAnAssociationUnitTests.class,AnUmlModelWithAPropertyUnitTests.class,NoneEndIsNavigableUnitTests.class,AnUmlModelWithAnOperationUnitTests.class})
/**
 * Testing : Edit labels
 */
public class EditLabelsUnitTests {

}
