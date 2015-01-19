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
package org.obeonetwork.dsl.uml2.design.api.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;

/**
 * A set of services to handle the Package Hierarchy diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class PackageHierarchyServices extends AbstractDiagramServices {

	/**
	 * Delete package.
	 *
	 * @param model
	 *            Model
	 * @param pkg
	 *            Package to delete
	 */
	public void deletePackage(Model model, Package pkg) {
		// Check if package is an imported package
		final List<PackageImport> copy = new ArrayList<PackageImport>();
		copy.addAll(model.getPackageImports());
		for (final PackageImport pkgImport : copy) {
			if (pkg.equals(pkgImport.getImportedPackage())) {
				model.getPackageImports().remove(pkgImport);
				return;
			}
		}
		pkg.destroy();
	}

	/**
	 * Check if a reconnect is possible and is not involving creating a cycle in the model.
	 *
	 * @param host
	 *            the current element.
	 * @param source
	 *            potential source of the edge.
	 * @param target
	 *            potential target of the edge
	 * @param element
	 *            element represented by the edge.
	 * @return true if no cycle is detected.
	 */
	public boolean reconnectContainmentPrecondition(Element host, Element source, Element target,
			Element element) {
		if (element == target) {
			return false;
		}
		final Iterator<EObject> it = element.eAllContents();
		while (it.hasNext()) {
			final EObject child = it.next();
			if (child == target) {
				return false;
			}
		}
		return true;
	}
}
