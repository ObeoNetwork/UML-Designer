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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.obeonetwork.dsl.uml2.core.api.services.AbstractDiagramServices;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * A set of services to handle the Package containment diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class PackageContainmentServices extends AbstractDiagramServices {
	/**
	 * Get all the packageable elements available in the semantic resources.
	 *
	 * @param element
	 *            Semantic element
	 * @return All the packageable elements
	 */
	public List<EObject> getAllPackageableElements(Element element) {
		final List<EObject> result = Lists.newArrayList();
		final List<Package> rootPkgs = getAllAvailableRootPackages(element);
		result.addAll(rootPkgs);
		for (final Package pkg : rootPkgs) {
			Iterators.addAll(result,
					Iterators.filter(pkg.eAllContents(), Predicates.instanceOf(PackageableElement.class)));
		}
		if (element instanceof Package) {
			result.removeAll(((Package)element).getPackagedElements());
		}

		return result;
	}

	public boolean isValidSelection(Element selection, Element container) {
		// To avoid circular references, check if selection is not an ancestor of container
		if (EcoreUtil.isAncestor(selection, container)) {
			String selName = "Selection";
			String contName = "container";

			if (selection instanceof NamedElement) {
				selName = ((NamedElement)selection).getName();
			}
			if (container instanceof NamedElement) {
				contName = ((NamedElement)container).getName();
			}

			MessageDialog.openError(Workbench.getInstance().getDisplay().getActiveShell(), "Selection Error",
					selName + " is an ancestor of " + contName + ", it could not be moved in " + contName
							+ ".");
			return false;
		}
		return true;
	}
}
