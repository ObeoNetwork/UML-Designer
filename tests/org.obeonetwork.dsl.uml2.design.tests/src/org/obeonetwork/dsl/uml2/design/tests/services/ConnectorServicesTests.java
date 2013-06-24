/*******************************************************************************
 * Copyright (c) 2009, 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.tests.services;

import org.obeonetwork.dsl.uml2.design.services.ConnectorServices;
import org.obeonetwork.dsl.uml2.design.tests.Activator;
import org.obeonetwork.dsl.uml2.design.tests.services.compositestructure.AbstractCompositeStructueTests;

/**
 * Unit tests on Connector services.
 * 
 * @author <a href="mailto:hugo.marchadour@obeo.fr">Hugo Marchadour</a>
 */
public class ConnectorServicesTests extends AbstractCompositeStructueTests {

	/**
	 * The test resource URI.
	 */
	private static final String RESOURCE_URI = Activator.PLUGIN_ID
			+ "/resources/compositeStructure/CSRelatedElements/compositeStructure_relatedElements.uml";

	private ConnectorServices connectorServices;

	/**
	 * Constructor.
	 */
	public ConnectorServicesTests() {

	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		connectorServices = new ConnectorServices();
	}

	@Override
	public String getRessourceURI() {
		return RESOURCE_URI;
	}

	public void testIsRequiredInterface() {
		assertFalse(connectorServices.isRequiredInterface(I1, Ap));
		assertFalse(connectorServices.isRequiredInterface(I1, Aap));
		assertFalse(connectorServices.isRequiredInterface(I1, Abp));
		assertFalse(connectorServices.isRequiredInterface(I1, Acp));
		assertTrue(connectorServices.isRequiredInterface(I1, Bp));
		assertFalse(connectorServices.isRequiredInterface(I1, Cp));

		assertTrue(connectorServices.isRequiredInterface(I2, Ap));
		assertFalse(connectorServices.isRequiredInterface(I2, Aap));
		assertFalse(connectorServices.isRequiredInterface(I2, Abp));
		assertTrue(connectorServices.isRequiredInterface(I2, Acp));
		assertFalse(connectorServices.isRequiredInterface(I2, Bp));
		assertFalse(connectorServices.isRequiredInterface(I2, Cp));

		assertFalse(connectorServices.isRequiredInterface(I3, Ap));
		assertFalse(connectorServices.isRequiredInterface(I3, Aap));
		assertTrue(connectorServices.isRequiredInterface(I3, Abp));
		assertFalse(connectorServices.isRequiredInterface(I3, Acp));
		assertFalse(connectorServices.isRequiredInterface(I3, Bp));
		assertFalse(connectorServices.isRequiredInterface(I3, Cp));

		assertFalse(connectorServices.isRequiredInterface(I4, Ap));
		assertTrue(connectorServices.isRequiredInterface(I4, Aap));
		assertFalse(connectorServices.isRequiredInterface(I4, Abp));
		assertFalse(connectorServices.isRequiredInterface(I4, Acp));
		assertFalse(connectorServices.isRequiredInterface(I4, Bp));
		assertFalse(connectorServices.isRequiredInterface(I4, Cp));
	}

	public void testIsProvidedInterface() {
		assertTrue(connectorServices.isProvidedInterface(I1, Ap));
		assertTrue(connectorServices.isProvidedInterface(I1, Aap));
		assertFalse(connectorServices.isProvidedInterface(I1, Abp));
		assertFalse(connectorServices.isProvidedInterface(I1, Acp));
		assertFalse(connectorServices.isProvidedInterface(I1, Bp));
		assertFalse(connectorServices.isProvidedInterface(I1, Cp));

		assertFalse(connectorServices.isProvidedInterface(I2, Ap));
		assertFalse(connectorServices.isProvidedInterface(I2, Aap));
		assertFalse(connectorServices.isProvidedInterface(I2, Abp));
		assertFalse(connectorServices.isProvidedInterface(I2, Acp));
		assertFalse(connectorServices.isProvidedInterface(I2, Bp));
		assertTrue(connectorServices.isProvidedInterface(I2, Cp));

		assertFalse(connectorServices.isProvidedInterface(I3, Ap));
		assertFalse(connectorServices.isProvidedInterface(I3, Aap));
		assertFalse(connectorServices.isProvidedInterface(I3, Abp));
		assertTrue(connectorServices.isProvidedInterface(I3, Acp));
		assertFalse(connectorServices.isProvidedInterface(I3, Bp));
		assertFalse(connectorServices.isProvidedInterface(I3, Cp));

		assertFalse(connectorServices.isProvidedInterface(I4, Ap));
		assertFalse(connectorServices.isProvidedInterface(I4, Aap));
		assertTrue(connectorServices.isProvidedInterface(I4, Abp));
		assertFalse(connectorServices.isProvidedInterface(I4, Acp));
		assertFalse(connectorServices.isProvidedInterface(I4, Bp));
		assertFalse(connectorServices.isProvidedInterface(I4, Cp));
	}

	public void testIsConnectableInterface2Interface_I1() {
		// IsConnectable Inter Inter
		assertTrue(connectorServices.isConnectable(I1, I1));
		assertFalse(connectorServices.isConnectable(I1, I2));
		assertFalse(connectorServices.isConnectable(I1, I3));
		assertFalse(connectorServices.isConnectable(I1, I4));
	}

	public void testIsConnectableInterface2Interface_I2() {
		assertTrue(connectorServices.isConnectable(I2, I1));
		assertTrue(connectorServices.isConnectable(I2, I2));
		assertFalse(connectorServices.isConnectable(I2, I3));
		assertFalse(connectorServices.isConnectable(I2, I4));
	}

	public void testIsConnectableInterface2Interface_I3() {
		assertTrue(connectorServices.isConnectable(I3, I1));
		assertTrue(connectorServices.isConnectable(I3, I2));
		assertTrue(connectorServices.isConnectable(I3, I3));
		assertFalse(connectorServices.isConnectable(I3, I4));
	}

	public void testIsConnectableInterface2Interface_I4() {
		assertFalse(connectorServices.isConnectable(I4, I1));
		assertFalse(connectorServices.isConnectable(I4, I2));
		assertFalse(connectorServices.isConnectable(I4, I3));
		assertTrue(connectorServices.isConnectable(I4, I4));
	}

	public void testIsConnectableInterface2Port_I1() {
		// IsConnectable Inter Port
		// I1, I2, I3, I4
		assertTrue(connectorServices.isConnectable(I1, Ap));
		assertFalse(connectorServices.isConnectable(I1, Bp));
		assertFalse(connectorServices.isConnectable(I1, Cp));
		assertTrue(connectorServices.isConnectable(I1, Aap));
		assertFalse(connectorServices.isConnectable(I1, Abp));
		assertFalse(connectorServices.isConnectable(I1, Acp));
	}

	public void testIsConnectablePort2Interface_I1() {
		assertTrue(connectorServices.isConnectable(Ap, I1));
		assertTrue(connectorServices.isConnectable(Bp, I1));
		assertFalse(connectorServices.isConnectable(Cp, I1));
		assertFalse(connectorServices.isConnectable(Aap, I1));
		assertTrue(connectorServices.isConnectable(Abp, I1));
		assertTrue(connectorServices.isConnectable(Acp, I1));
	}

	public void testIsConnectableInterface2Port_I2() {
		assertTrue(connectorServices.isConnectable(I2, Ap));
		assertFalse(connectorServices.isConnectable(I2, Bp));
		assertTrue(connectorServices.isConnectable(I2, Cp));
		assertTrue(connectorServices.isConnectable(I2, Aap));
		assertFalse(connectorServices.isConnectable(I2, Abp));
		assertFalse(connectorServices.isConnectable(I2, Acp));
	}

	public void testIsConnectablePort2Interface_I2() {
		assertTrue(connectorServices.isConnectable(Ap, I2));
		assertFalse(connectorServices.isConnectable(Bp, I2));
		assertFalse(connectorServices.isConnectable(Cp, I2));
		assertFalse(connectorServices.isConnectable(Aap, I2));
		assertTrue(connectorServices.isConnectable(Abp, I2));
		assertTrue(connectorServices.isConnectable(Acp, I2));
	}

	public void testIsConnectableInterface2Port_I3() {
		assertTrue(connectorServices.isConnectable(I3, Ap));
		assertFalse(connectorServices.isConnectable(I3, Bp));
		assertTrue(connectorServices.isConnectable(I3, Cp));
		assertTrue(connectorServices.isConnectable(I3, Aap));
		assertFalse(connectorServices.isConnectable(I3, Abp));
		assertTrue(connectorServices.isConnectable(I3, Acp));
	}

	public void testIsConnectablePort2Interface_I3() {
		assertFalse(connectorServices.isConnectable(Ap, I3));
		assertFalse(connectorServices.isConnectable(Bp, I3));
		assertFalse(connectorServices.isConnectable(Cp, I3));
		assertFalse(connectorServices.isConnectable(Aap, I3));
		assertTrue(connectorServices.isConnectable(Abp, I3));
		assertFalse(connectorServices.isConnectable(Acp, I3));
	}

	public void testIsConnectableInterface2Port_I4() {
		assertFalse(connectorServices.isConnectable(I4, Ap));
		assertFalse(connectorServices.isConnectable(I4, Bp));
		assertFalse(connectorServices.isConnectable(I4, Cp));
		assertFalse(connectorServices.isConnectable(I4, Aap));
		assertTrue(connectorServices.isConnectable(I4, Abp));
		assertFalse(connectorServices.isConnectable(I4, Acp));
	}

	public void testIsConnectablePort2Interface_I4() {
		assertFalse(connectorServices.isConnectable(Ap, I4));
		assertFalse(connectorServices.isConnectable(Bp, I4));
		assertFalse(connectorServices.isConnectable(Cp, I4));
		assertTrue(connectorServices.isConnectable(Aap, I4));
		assertFalse(connectorServices.isConnectable(Abp, I4));
		assertFalse(connectorServices.isConnectable(Acp, I4));
	}

	public void testIsConnectableProperty2Property() {
		// IsConnectable Property Property
		assertTrue(connectorServices.isConnectable(AProp1, AProp1));
		assertTrue(connectorServices.isConnectable(AProp1, AProp2));
		assertTrue(connectorServices.isConnectable(AProp2, AProp1));
		assertTrue(connectorServices.isConnectable(AProp2, AProp2));

	}
}
