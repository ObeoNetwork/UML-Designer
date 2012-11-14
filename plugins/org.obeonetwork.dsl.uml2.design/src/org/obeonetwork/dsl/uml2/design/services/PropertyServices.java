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
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;

/**
 * Utility services to manage property.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public final class PropertyServices {

	/**
	 * Change properties order in Class and Interface.
	 * @param propertiesToMove
	 */
	public void moveUpProperties(List<Property> propertiesToMove) {

		List<Property> propertiesInRightOrder = retrieveTheRightOrder(propertiesToMove);

		// move all properties contain in propertiesInRightOrder (to move in the right order)
		for (Iterator<Property> iterator = propertiesInRightOrder.iterator(); iterator.hasNext();) {
			Property operation = (Property)iterator.next();
			moveUpProperty(operation);
		}
	}
	
	/**
	 * Change properties order in Class and Interface.
	 * @param propertiesToMove
	 */
	public void moveDownProperties(List<Property> propertiesToMove) {
		
		List<Property> propertiesInRightOrder = retrieveTheRightOrder(propertiesToMove);
		Object[] propertiesArray = propertiesInRightOrder.toArray();
		
		for (int i = propertiesArray.length-1; i >=0; i--) {
			if(propertiesArray[i] instanceof Property) {
				Property operation = (Property)propertiesArray[i];
				if(propertiesToMove.contains(operation)) {
					moveDownProperty(operation);
				}
			}
		}
	}
	
	private void moveUpProperty(Property property) {
		// Create new operation
		EObject eContainer = property.eContainer();

		if (eContainer instanceof org.eclipse.uml2.uml.Class || eContainer instanceof Interface) {
			EList<Property> properties = null;
			if (eContainer instanceof org.eclipse.uml2.uml.Class) {
				org.eclipse.uml2.uml.Class eclass = (org.eclipse.uml2.uml.Class)eContainer;
				properties = eclass.getOwnedAttributes();
			} else {
				Interface eInterface = (Interface)eContainer;
				properties = eInterface.getOwnedAttributes();
			}

			int oldIndex = properties.indexOf(property);
			int newIndex = oldIndex-1;
			if(newIndex>=0) {
				properties.move(newIndex, oldIndex);
			}
		}
	}
	
	private void moveDownProperty(Property property) {
		// Create new operation
		EObject eContainer = property.eContainer();

		if (eContainer instanceof org.eclipse.uml2.uml.Class || eContainer instanceof Interface) {
			EList<Property> properties = null;
			if (eContainer instanceof org.eclipse.uml2.uml.Class) {
				org.eclipse.uml2.uml.Class eclass = (org.eclipse.uml2.uml.Class)eContainer;
				properties = eclass.getOwnedAttributes();
			} else {
				Interface eInterface = (Interface)eContainer;
				properties = eInterface.getOwnedAttributes();
			}

			int oldIndex = properties.indexOf(property);
			int newIndex = oldIndex+1;
			if(newIndex<properties.size()) {
				properties.move(newIndex, oldIndex);
			}
		}
	}

	private List<Property> retrieveTheRightOrder(List<Property> propertiesInWrongOrder) {

		List<Property> propertiesInRightOrder = new ArrayList<Property>();

		// retrieve all eContainers (property could be in different eContainers)
		List<EObject> eContainers = new ArrayList<EObject>();
		for (Property property : propertiesInWrongOrder) {
			EObject eContainer = property.eContainer();
			if(eContainer!=null && !eContainers.contains(eContainer)) {
				eContainers.add(eContainer);
			}
		}

		// on all eContainers found
		for (EObject eContainer : eContainers) {
			if (eContainer instanceof org.eclipse.uml2.uml.Class || eContainer instanceof Interface) {
				// get all properties for a specific eContainer
				EList<Property> properties = null;
				if (eContainer instanceof org.eclipse.uml2.uml.Class) {
					org.eclipse.uml2.uml.Class eclass = (org.eclipse.uml2.uml.Class)eContainer;
					properties = eclass.getOwnedAttributes();
				} else {
					Interface eInterface = (Interface)eContainer;
					properties = eInterface.getOwnedAttributes();
				}

				// add all properties contain in propertiesInWrongOrder (to retrieve the right order)
				for (Iterator<Property> iterator = properties.iterator(); iterator.hasNext();) {
					Property property = (Property)iterator.next();
					if(propertiesInWrongOrder.contains(property)) {
						propertiesInRightOrder.add(property);
					}
				}
			}
		}

		return propertiesInRightOrder;
	}
}
