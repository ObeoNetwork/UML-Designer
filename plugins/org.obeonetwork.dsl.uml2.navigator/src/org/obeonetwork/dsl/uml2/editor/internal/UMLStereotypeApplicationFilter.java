/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.editor.internal;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Filter to enable the user to hide Stereotype applications displayed as children of UML resources.
 * 
 * @author <a href="mailto:cedric.notot@obeo.fr">Cédric Notot</a>
 */
public class UMLStereotypeApplicationFilter extends ViewerFilter {

	public UMLStereotypeApplicationFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return !(element instanceof EObject && ((EObject)element).eContainer() == null
				&& containsBaseReference(((EObject)element).eClass().getEAllReferences()));
	}

	private boolean containsBaseReference(List<EReference> references) {
		for (EReference eReference : references) {
			if (eReference.getName().startsWith("base_")) {
				return true;
			}
		}
		return false;
	}

}
