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
package org.obeonetwork.dsl.uml2.design.tests.services;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.obeonetwork.dsl.uml2.design.services.FilterServices;
import org.obeonetwork.dsl.uml2.design.tests.Activator;

/**
 * Unit tests on filter services.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class FilterServicesTests extends TestCase {
	/**
	 * Upper package name.
	 */
	private static final String UPPER_PACKAGE_OPOSITE_GENERALIZATION_RELATED_CLASS = "UpperPackageOpositeGeneralizationRelatedClass";

	/**
	 * The test resource URI.
	 */
	private static final String RESOURCE_URI = Activator.PLUGIN_ID + "/resources/Test.uml";
	
	/**
	 * Instance of the services class used during tests.
	 */
	private final FilterServices services = new FilterServices();

	/**
	 * The root package of the semantic resource.
	 */
	private Package rootPackage;

	/**
	 * Constructor.
	 */
	public FilterServicesTests() {
		final ResourceSet rset = new ResourceSetImpl();
		final Resource resource = rset.getResource(URI.createPlatformPluginURI(RESOURCE_URI, true), true);

		rootPackage = (Package)((Model)resource.getContents().get(0)).getOwnedMember("root");
	}

	/**
	 * Test isRelated() service.
	 */
	public void testIsRelated() {
		final Package subPackage1 = (Package)rootPackage.getOwnedMember("sub_package1");
		final Package subPackage2 = (Package)subPackage1.getOwnedMember("sub_package2");
		final Class contextCls = (Class)subPackage1.getOwnedMember("ContextClass");

		try {
			assertFalse(services.isRelated(null, null));
		} catch (Exception e) {
			fail("isRelated(null, null) should not raises an exception : " + e.getMessage());
		}

		try {
			services.isRelated(contextCls, null);
			fail("isRelated(contextCls, null) should raises a null pointer exception.");
		} catch (Exception e) {
			// OK
		}

		try {
			assertFalse(services.isRelated(null, contextCls));
		} catch (Exception e) {
			fail("isRelated(null, contextCls) should not raises an exception : " + e.getMessage());
		}
		assertTrue(services.isRelated(
				rootPackage.getOwnedMember(UPPER_PACKAGE_OPOSITE_GENERALIZATION_RELATED_CLASS), contextCls));

		// root package
		assertTrue(services.isRelated(rootPackage, contextCls));
		assertTrue(services.isRelated(
				rootPackage.getOwnedMember(UPPER_PACKAGE_OPOSITE_GENERALIZATION_RELATED_CLASS), contextCls));
		assertTrue(services.isRelated(
				rootPackage.getOwnedMember(UPPER_PACKAGE_OPOSITE_GENERALIZATION_RELATED_CLASS), contextCls));
		assertTrue(services.isRelated(rootPackage.getOwnedMember("UpperPackageGeneralizationRelatedClass"),
				contextCls));
		assertTrue(services.isRelated(rootPackage.getOwnedMember("UpperPackageAssociationRelatedClass"),
				contextCls));
		assertTrue(services.isRelated(rootPackage.getOwnedMember("UpperPackageRealizationRelatedInterface"),
				contextCls));
		assertTrue(services.isRelated(rootPackage.getOwnedMember("upperPackageClassAssociation"), contextCls));
		assertFalse(services.isRelated(rootPackage.getOwnedMember("not_related_package"), contextCls));

		// sub_package1 package
		assertTrue(services.isRelated(subPackage1, contextCls));
		assertTrue(services.isRelated(contextCls, contextCls));
		assertFalse(services.isRelated(subPackage1.getOwnedMember("NotRelatedClass"), contextCls));
		assertTrue(services.isRelated(subPackage1.getOwnedMember("AssociationRelatedClass"), contextCls));
		assertTrue(services.isRelated(subPackage1.getOwnedMember("GeneralizationRelatedClass"), contextCls));
		assertTrue(services.isRelated(subPackage1.getOwnedMember("OpositeGeneralizationRelatedClass"),
				contextCls));
		assertTrue(services.isRelated(subPackage1.getOwnedMember("RealizationRelatedInterface"), contextCls));
		assertTrue(services.isRelated(subPackage1.getOwnedMember("classAssocitation"), contextCls));

		// sub_package2 package
		assertTrue(services.isRelated(subPackage2, contextCls));
		assertTrue(services.isRelated(
				subPackage2.getOwnedMember("SubPackageOpositeGeneralizationRelatedClass"), contextCls));
		assertTrue(services.isRelated(subPackage2.getOwnedMember("SubPackageGeneralizationRelatedClass"),
				contextCls));
		assertTrue(services.isRelated(subPackage2.getOwnedMember("SubPackageAssociationRelatedClass"),
				contextCls));
		assertTrue(services.isRelated(subPackage2.getOwnedMember("SubPackageRealizationRelatedInterface"),
				contextCls));
		assertTrue(services.isRelated(subPackage2.getOwnedMember("subPackageClassAssociation"), contextCls));
	}

}
