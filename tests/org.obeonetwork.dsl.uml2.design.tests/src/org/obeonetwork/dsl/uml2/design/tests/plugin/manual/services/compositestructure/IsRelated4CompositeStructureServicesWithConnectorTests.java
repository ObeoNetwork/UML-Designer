/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.tests.plugin.manual.services.compositestructure;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.dsl.uml2.design.tests.Activator;

/**
 * Unit tests on IsRelated4CompositeStructure services.
 * 
 * @author <a href="mailto:hugo.marchadour@obeo.fr">Hugo Marchadour</a>
 */
public class IsRelated4CompositeStructureServicesWithConnectorTests extends AbstractCompositeStructueTests {

	/**
	 * The test resource URI.
	 */
	private static final String RESOURCE_URI = Activator.PLUGIN_ID
			+ "/resources/compositeStructure/CSRelatedElements_WithConnectors/compositeStructure_relatedElements.uml";

	@Override
	public String getRessourceURI() {
		return RESOURCE_URI;
	}

	/**
	 * Test getRelatedForCompositeStructure service.
	 */
	public void testIsRelated_Root() {
		allRelated(rootPackage, new EObject[] {A, B, C});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_A() {
		allRelated(A, new EObject[] {Ap, B, Bp, C, Cp, Aap, Acp, Aa, Ab, Ac, AProp1, AProp2});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_B() {
		allRelated(B, new EObject[] {Bp, Ap, A});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_C() {
		allRelated(C, new EObject[] {Cp, Ap, A});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_Ap() {
		allRelated(Ap, new EObject[] {A, Bp, B, Cp, C, Aap, Aa, Acp, Ac});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_Bp() {
		allRelated(Bp, new EObject[] {B, Ap, A});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_Cp() {
		allRelated(Cp, new EObject[] {C, Ap, A});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_Aa() {
		allRelated(Aa, new EObject[] {Aap, Abp, Ab, Ap, A});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_Ab() {
		allRelated(Ab, new EObject[] {Abp, Aap, Aa, Acp, Ac});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_Ac() {
		allRelated(Ac, new EObject[] {Acp, Abp, Ab, Ap, A});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_Aap() {
		allRelated(Aap, new EObject[] {Aa, Abp, Ab, Ap, A});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_Abp() {
		allRelated(Abp, new EObject[] {Ab, Aap, Aa, Acp, Ac});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_Acp() {
		allRelated(Acp, new EObject[] {Ac, Abp, Ab, Ap, A});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_usageI1() {
		allRelated(usageI1, new EObject[] {Ap, A, Bp, B});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_irAI1() {
		allRelated(irAI1, new EObject[] {Ap, A, Bp, B});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_usageI2() {
		allRelated(usageI2, new EObject[] {Ap, A, Cp, C});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_irCI2() {
		allRelated(irCI2, new EObject[] {Ap, A, Cp, C});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_usageAI2() {
		allRelated(usageAI2, new EObject[] {A, Ap, Acp, Ac});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_irAaI1() {
		allRelated(irAaI1, new EObject[] {A, Ap, Aa, Aap});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_usageAI3() {
		allRelated(usageAI3, new EObject[] {Abp, Ab, Acp, Ac});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_irAcI3() {
		allRelated(irAcI3, new EObject[] {Abp, Ab, Acp, Ac});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_usageAI4() {
		allRelated(usageAI4, new EObject[] {Aap, Aa, Abp, Ab});
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated_irAbI4() {
		allRelated(irAbI4, new EObject[] {Aap, Aa, Abp, Ab});
	}

	private void allRelated(EObject context, EObject[] toFind) {
		Collection toFindCollection = Arrays.asList(toFind);
		Collection<EObject> related = services.getRelatedForCompositeStructure(context);

		for (EObject item : AllItems) {
			if (toFindCollection.contains(item)) {
				assertTrue("allRelated " + getName(context) + " not related with " + getName(item),
						related.contains(item));
			} else {
				assertFalse("allRelated " + getName(context) + " related with " + getName(item),
						related.contains(item));
			}
		}
	}
}
