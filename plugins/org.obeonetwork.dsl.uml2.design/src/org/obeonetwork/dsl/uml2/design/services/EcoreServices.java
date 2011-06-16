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
package org.obeonetwork.dsl.uml2.design.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Type;

/**
 * Utility services to manage Ecore UML ressources.
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class EcoreServices {
	
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final EcoreServices INSTANCE = new EcoreServices();

	/**
	 * Find a {@link Type} element that match the given name in the ResourceSet of the given element.
	 * 
	 * @param object the object for which to find a corresponding type.
	 * @param typeName the type name to match.
	 * @return the found {@link Type} element or <code>null</code>
	 */
	public Type findTypeByName(EObject object, String typeName) {
		final Type result = findTypeByName(getAllRootsInResourceSet(object), typeName);
		return result;
	}

	/**
	 * Iterate over the given {@link Collection} of root elements to find a {@link Type} element
	 * with the given name.
	 * 
	 * @param roots the elements to inspect
	 * @param typeName the name to match
	 * @return the found {@link Type} or <code>null</code>
	 */
	public Type findTypeByName(Iterable<EObject> roots, String typeName) {
		for (EObject root : roots) {
			final Type result = findTypeByNameFrom(root, typeName);
			if (result != null) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Retrieves all the root elements of the resource in the resource set of the given context object.
	 * 
	 * @param context
	 *            the context object on which to execute this service.
	 * @return a {@link Collection} of all the root element of the current resource set.
	 */
	public Collection<EObject> getAllRootsInResourceSet(EObject context) {
		final Resource res = context.eResource();
		if (res != null && res.getResourceSet() != null) {
			final Collection<EObject> roots = new ArrayList<EObject>();
			for (Resource childRes : res.getResourceSet().getResources()) {
				roots.addAll(childRes.getContents());
			}
			return roots;
		} else {
			return Collections.emptySet();
		}
	}

	/**
	 * Iterate over the root children to find a {@link Type} element with the given name.
	 * 
	 * @param root the root object to iterate.
	 * @param typeName the name to match
	 * @return the found {@link Type} or <code>null</code>
	 */
	private Type findTypeByNameFrom(EObject root, String typeName) {
		if (root instanceof Type && nameMatches((Type)root, typeName)) {
			return (Type)root;
		}

		for (final Iterator<EObject> i = root.eAllContents(); i.hasNext(); ) {
			final EObject obj = i.next();
			if (obj instanceof Type && nameMatches((Type)obj, typeName)) {
				return (Type)obj;
			}
		}

		return null;
	}

	/**
	 * Check if the given element's name match the given String.
	 * 
	 * @param namedElt the {@link NamedElement} to check.
	 * @param name the name to match.
	 * @return <code>true</code> if the name match, <code>false</code> otherwise.
	 */
	private boolean nameMatches(NamedElement namedElt, String name) {
		if (namedElt != null && namedElt.getName() != null && name != null) {
			return namedElt.getName().trim().equalsIgnoreCase(name.trim());
		} else {
			return false;
		}
	}
}
