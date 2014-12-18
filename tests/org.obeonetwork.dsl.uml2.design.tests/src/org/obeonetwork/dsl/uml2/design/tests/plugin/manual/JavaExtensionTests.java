/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.tests.plugin.manual;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.junit.Test;

/**
 * Check if a java extension referenced from the viewpoint specification exists.
 */
public class JavaExtensionTests {

	@Test
	public void existsOnlyValidJavaExtensions() {
		Set<JavaExtension> allExtensions = new HashSet<JavaExtension>();

		List<String> invalidJavaExtensions = new ArrayList<String>();
		ServiceTestsUtils.collectJavaExtensionsFromUmlDesignerViewpoints(allExtensions);
		for (JavaExtension extension : allExtensions) {
			try {
				Class.forName(extension.getQualifiedClassName());
			} catch (ClassNotFoundException e) {
				invalidJavaExtensions.add(e.getMessage());
			}
		}

		if (!invalidJavaExtensions.isEmpty()) {
			String message = "";
			for (String invalidJavaExtension : invalidJavaExtensions) {
				message += invalidJavaExtension + "\n";
			}
			fail("Java extensions not found : \n" + message);
		}
	}
}
