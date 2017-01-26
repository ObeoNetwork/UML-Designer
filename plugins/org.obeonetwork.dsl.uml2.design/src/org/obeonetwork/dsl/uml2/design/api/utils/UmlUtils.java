/*******************************************************************************
 * Copyright (c) 2015, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.api.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.internal.operations.ElementOperations;
import org.obeonetwork.dsl.uml2.design.internal.services.ElementServices;

/**
 * Utilities to handle UML elements.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
@SuppressWarnings("restriction")
public class UmlUtils {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final UmlUtils INSTANCE = new UmlUtils();

	/**
	 * Destroy a semantic element and all its references.
	 *
	 * @param ancestorEObject
	 *            Element to destroy
	 * @see ElementOperations#destroy(Element)
	 */
	public void destroy(EObject ancestorEObject) {
		if (ancestorEObject.eContents().isEmpty()) {
			if (ancestorEObject instanceof Element) {
				destroyAll(((Element)ancestorEObject).getStereotypeApplications());
				removeReferences(ancestorEObject, ancestorEObject);
				ancestorEObject.eAdapters().clear();
			} else {
				removeReferences(ancestorEObject, null);
			}
		} else {

			for (final TreeIterator<EObject> allContents = UML2Util.getAllContents(ancestorEObject, true,
					true); allContents.hasNext();) {

				final EObject eObject = allContents.next();

				if (eObject instanceof Element) {
					destroyAll(((Element)eObject).getStereotypeApplications());
				}
			}

			for (final TreeIterator<EObject> allContents = UML2Util.getAllContents(ancestorEObject, true,
					true); allContents.hasNext();) {

				final EObject eObject = allContents.next();

				if (eObject instanceof Element) {
					removeReferences(eObject, ancestorEObject);
				} else {
					removeReferences(eObject, null);
				}
			}

			for (final TreeIterator<EObject> allContents = UML2Util.getAllContents(ancestorEObject, true,
					true); allContents.hasNext();) {

				final EObject eObject = allContents.next();

				if (eObject instanceof Element) {
					eObject.eAdapters().clear();
				}
			}
		}

		EcoreUtil.remove(ancestorEObject);
	}

	/**
	 * @see ElementOperations#destroy(Element)
	 */
	private void destroyAll(Collection<EObject> eObjects) {
		for (final Iterator<EObject> o = eObjects.iterator(); o.hasNext();) {
			destroy(o.next());
		}
	}

	/**
	 * Find a {@link Type} element that match the given name in the ResourceSet of the given element.
	 *
	 * @param object
	 *            the object for which to find a corresponding type.
	 * @param typeName
	 *            the type name to match.
	 * @return the found {@link Type} element or <code>null</code>
	 */
	public Type findTypeByName(EObject object, String typeName) {
		return ElementServices.INSTANCE.findTypeByName(object, typeName);
	}

	/**
	 * @see ElementOperations#destroy(Element)
	 */
	private Collection<EStructuralFeature.Setting> getInverseReferences(EObject eObject) {
		final ECrossReferenceAdapter crossReferenceAdapter = ECrossReferenceAdapter
				.getCrossReferenceAdapter(eObject);
		return crossReferenceAdapter == null ? Collections.<EStructuralFeature.Setting> emptyList()
				: crossReferenceAdapter.getInverseReferences(eObject);
	}

	/**
	 * @see ElementOperations#destroy(Element)
	 */
	private void removeReferences(EObject eObject, EObject ancestorEObject) {
		for (final EStructuralFeature.Setting inverseReference : new ArrayList<EStructuralFeature.Setting>(
				getInverseReferences(eObject))) {

			if (inverseReference.getEStructuralFeature().isChangeable() && (ancestorEObject == null
					|| !EcoreUtil.isAncestor(ancestorEObject, inverseReference.getEObject()))) {
				EcoreUtil.remove(inverseReference, eObject);
				if (!(inverseReference.getEObject() instanceof AssociationClass)
						&& inverseReference.getEObject() instanceof Relationship) {
					destroy(inverseReference.getEObject());
				}
			}
		}

		for (final EReference eReference : eObject.eClass().getEAllReferences()) {
			if (eReference.isChangeable() && !eReference.isContainer() && !eReference.isContainment()
					&& !eReference.isDerived() && eObject.eIsSet(eReference)) {

				if (eReference.isMany()) {
					final List<?> values = (List<?>)eObject.eGet(eReference);

					for (int i = 0; i < values.size(); i++) {
						final Object value = values.get(i);

						if (ancestorEObject == null
								|| !EcoreUtil.isAncestor(ancestorEObject, (EObject)value)) {

							values.remove(i);
						}
					}
				} else {

					if (ancestorEObject == null
							|| !EcoreUtil.isAncestor(ancestorEObject, (EObject)eObject.eGet(eReference))) {
						eObject.eUnset(eReference);
					}
				}
			}
		}
	}
}
