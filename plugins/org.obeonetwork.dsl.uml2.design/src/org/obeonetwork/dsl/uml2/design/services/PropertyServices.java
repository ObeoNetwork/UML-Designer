/*******************************************************************************
 * Copyright (c) 2009, 2012 Obeo.
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
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.obeonetwork.dsl.uml2.design.services.internal.MoveDownElementSwitch;
import org.obeonetwork.dsl.uml2.design.services.internal.MoveUpElementSwitch;

/**
 * Utility services to manage property.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public final class PropertyServices {

	/**
	 * Change properties order in Class and Interface.
	 * 
	 * @param propertiesToMove
	 *            properties to move
	 */
	public void moveUpProperties(Property currentProperty, List<Property> propertiesToMove) {

		List<Property> propertiesInRightOrder = retrieveTheRightOrder(propertiesToMove);
		MoveUpElementSwitch moveUpElementsSwitch = new MoveUpElementSwitch();
		// move all properties contain in propertiesInRightOrder (to move in the right order)
		Iterator<Property> iterator = propertiesInRightOrder.iterator();
		while (iterator.hasNext()) {
			Property property = iterator.next();
			moveUpElementsSwitch.moveUpElement(property);
		}
	}

	/**
	 * Change properties order in Class and Interface.
	 * 
	 * @param propertiesToMove
	 *            properties to move
	 */
	public void moveDownProperties(Property currentProperty, List<Property> propertiesToMove) {

		List<Property> propertiesInRightOrder = retrieveTheRightOrder(propertiesToMove);
		Object[] propertiesArray = propertiesInRightOrder.toArray();
		MoveDownElementSwitch moveDownElementSwitch = new MoveDownElementSwitch();
		for (int i = propertiesArray.length - 1; i >= 0; i--) {
			if (propertiesArray[i] instanceof Property) {
				moveDownElementSwitch.moveDownElement((Property)propertiesArray[i]);
			}
		}
	}

	private List<Property> retrieveTheRightOrder(List<Property> propertiesInWrongOrder) {

		List<Property> propertiesInRightOrder = new ArrayList<Property>();

		// retrieve all eContainers (property could be in different eContainers)
		List<EObject> eContainers = new ArrayList<EObject>();
		for (Property property : propertiesInWrongOrder) {
			EObject eContainer = property.eContainer();
			if (eContainer != null && !eContainers.contains(eContainer)) {
				eContainers.add(eContainer);
			}
		}

		// on all eContainers found
		for (EObject eContainer : eContainers) {
			if (eContainer instanceof StructuredClassifier) {
				// get all properties for a specific eContainer
				EList<Property> properties = ((StructuredClassifier)eContainer).getOwnedAttributes();

				// add all properties contain in propertiesInWrongOrder (to retrieve the right order)
				Iterator<Property> iterator = properties.iterator();
				while (iterator.hasNext()) {
					Property property = iterator.next();
					if (propertiesInWrongOrder.contains(property)) {
						propertiesInRightOrder.add(property);
					}
				}
			}
		}

		return propertiesInRightOrder;
	}
}
