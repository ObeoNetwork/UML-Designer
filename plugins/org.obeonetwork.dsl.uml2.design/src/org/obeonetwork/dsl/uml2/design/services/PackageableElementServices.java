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
package org.obeonetwork.dsl.uml2.design.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.PackageableElement;
import org.obeonetwork.dsl.uml2.design.services.internal.MoveDownElementSwitch;
import org.obeonetwork.dsl.uml2.design.services.internal.MoveUpElementSwitch;

/**
 * Utility services to manage element.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public final class PackageableElementServices {

	/**
	 * Change properties order in Class and Interface.
	 * 
	 * @param packageableElementsToMove
	 *            packageableElements to move
	 */
	public void moveUpPackageableElements(List<PackageableElement> packageableElementsToMove) {

		MoveUpElementSwitch moveUpElementsSwitch = new MoveUpElementSwitch();
		List<PackageableElement> packageableElementsInRightOrder = retrieveTheRightOrder(packageableElementsToMove);
		Iterator<PackageableElement> iterator = packageableElementsInRightOrder.iterator();
		while (iterator.hasNext()) {
			Element element = iterator.next();
			moveUpElementsSwitch.moveUpElement(element);
		}
	}

	/**
	 * Change properties order in Class and Interface.
	 * 
	 * @param packageableElementsToMove
	 *            packageableElements to move
	 */
	public void moveDownPackageableElements(List<PackageableElement> packageableElementsToMove) {

		MoveDownElementSwitch moveDownElementSwitch = new MoveDownElementSwitch();
		List<PackageableElement> packageableElementsInRightOrder = retrieveTheRightOrder(packageableElementsToMove);
		Object[] elementsArray = packageableElementsInRightOrder.toArray();
		for (int i = elementsArray.length - 1; i >= 0; i--) {
			if (elementsArray[i] instanceof Element) {
				moveDownElementSwitch.moveDownElement((Element)elementsArray[i]);
			}
		}
	}

	private List<PackageableElement> retrieveTheRightOrder(
			List<PackageableElement> packageableElementsInWrongOrder) {

		List<PackageableElement> packageableElementsInRightOrder = new ArrayList<PackageableElement>();

		// retrieve all eContainers (packageableElement could be in different eContainers)
		List<EObject> eContainers = new ArrayList<EObject>();
		for (PackageableElement packageableElement : packageableElementsInWrongOrder) {
			EObject eContainer = packageableElement.eContainer();
			if (eContainer != null && !eContainers.contains(eContainer)) {
				eContainers.add(eContainer);
			}
		}

		// on all eContainers found
		for (EObject eContainer : eContainers) {
			if (eContainer instanceof org.eclipse.uml2.uml.Package || eContainer instanceof Component) {
				// get all packageableElements for a specific eContainer
				EList<PackageableElement> packageableElements = null;
				if (eContainer instanceof org.eclipse.uml2.uml.Package) {
					org.eclipse.uml2.uml.Package ePackage = (org.eclipse.uml2.uml.Package)eContainer;
					packageableElements = ePackage.getPackagedElements();
				} else {
					Component component = (Component)eContainer;
					packageableElements = component.getPackagedElements();
				}

				// add all packageableElements contain in packageableElementsInWrongOrder (to retrieve the
				// right order)
				Iterator<PackageableElement> iterator = packageableElements.iterator();
				while (iterator.hasNext()) {
					PackageableElement packageableElement = iterator.next();
					if (packageableElementsInWrongOrder.contains(packageableElement)) {
						packageableElementsInRightOrder.add(packageableElement);
					}
				}
			}
		}

		return packageableElementsInRightOrder;
	}
}
