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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.obeonetwork.dsl.uml2.design.services.internal.NamedElementServices;

/**
 * Utility services to manage operation.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public final class OperationServices {
	/**
	 * Create an operation in a class.
	 * 
	 * @param type
	 *            the container {@link OperationServices.eclipse.uml2.uml.Type} element
	 * @return New operation
	 */
	public Operation createOperation(org.eclipse.uml2.uml.Type type) {
		// Create new operation
		final String name = NamedElementServices.getNewOperationName(type);
		Operation operation = null;
		if (type instanceof org.eclipse.uml2.uml.Class) {
			operation = ((org.eclipse.uml2.uml.Class)type).createOwnedOperation(name, null, null, null);
		} else if (type instanceof Interface) {
			operation = ((Interface)type).createOwnedOperation(name, null, null, null);
		} else if (type instanceof Component) {
			operation = ((Component)type).createOwnedOperation(name, null, null, null);
		}
		return operation;
	}
	
	
	/**
	 * Change properties order in Class and Interface.
	 * @param operationsToMove
	 */
	public void moveUpOperations(List<Operation> operationsToMove) {
		
		List<Operation> operationsInRightOrder = retrieveTheRightOrder(operationsToMove);
		
		// move all operations contain in operationsInRightOrder (to move in the right order)
		for (Iterator<Operation> iterator = operationsInRightOrder.iterator(); iterator.hasNext();) {
			Operation operation = (Operation)iterator.next();
			moveUpOperation(operation);
		}
	}
	
	/**
	 * Change properties order in Class and Interface.
	 * @param operationsToMove
	 */
	public void moveDownOperations(List<Operation> operationsToMove) {
		
		List<Operation> operationsInRightOrder = retrieveTheRightOrder(operationsToMove);
		Object[] operationsArray = operationsInRightOrder.toArray();
		
		for (int i = operationsArray.length-1; i >=0; i--) {
			if(operationsArray[i] instanceof Operation) {
				Operation operation = (Operation)operationsArray[i];
				if(operationsToMove.contains(operation)) {
					moveDownOperation(operation);
				}
			}
		}
	}
	
	private void moveUpOperation(Operation operation) {

		EObject eContainer = operation.eContainer();
		
		if (eContainer instanceof org.eclipse.uml2.uml.Class || eContainer instanceof Interface) {
			EList<Operation> operations = null;
			if (eContainer instanceof org.eclipse.uml2.uml.Class) {
				org.eclipse.uml2.uml.Class eclass = (org.eclipse.uml2.uml.Class)eContainer;
				operations = eclass.getOwnedOperations();
			} else {
				Interface eInterface = (Interface)eContainer;
				operations = eInterface.getOwnedOperations();
			}
			
			int oldIndex = operations.indexOf(operation);
			int newIndex = oldIndex-1;
			if(newIndex>=0) {
				operations.move(newIndex, oldIndex);
			}
		}
	}
	
	private void moveDownOperation(Operation operation) {

		EObject eContainer = operation.eContainer();
		
		if (eContainer instanceof org.eclipse.uml2.uml.Class || eContainer instanceof Interface) {
			EList<Operation> operations = null;
			if (eContainer instanceof org.eclipse.uml2.uml.Class) {
				org.eclipse.uml2.uml.Class eclass = (org.eclipse.uml2.uml.Class)eContainer;
				operations = eclass.getOwnedOperations();
			} else {
				Interface eInterface = (Interface)eContainer;
				operations = eInterface.getOwnedOperations();
			}
			
			int oldIndex = operations.indexOf(operation);
			int newIndex = oldIndex+1;
			if(newIndex<operations.size()) {
				operations.move(newIndex, oldIndex);
			}
		}
	}
	
	private List<Operation> retrieveTheRightOrder(List<Operation> operationsInWrongOrder) {
		
		List<Operation> operationsInRightOrder = new ArrayList<Operation>();
		
		// retrieve all eContainers (operation could be in different eContainers)
		List<EObject> eContainers = new ArrayList<EObject>();
		for (Operation operation : operationsInWrongOrder) {
			EObject eContainer = operation.eContainer();
			if(eContainer!=null && !eContainers.contains(eContainer)) {
				eContainers.add(eContainer);
			}
		}
		
		// on all eContainers found
		for (EObject eContainer : eContainers) {
			if (eContainer instanceof org.eclipse.uml2.uml.Class || eContainer instanceof Interface) {
				// get all operations for a specific eContainer
				EList<Operation> operations = null;
				if (eContainer instanceof org.eclipse.uml2.uml.Class) {
					org.eclipse.uml2.uml.Class eclass = (org.eclipse.uml2.uml.Class)eContainer;
					operations = eclass.getOwnedOperations();
				} else {
					Interface eInterface = (Interface)eContainer;
					operations = eInterface.getOwnedOperations();
				}
				
				// add all operations contain in operationsInWrongOrder (to retrieve the right order)
				for (Iterator<Operation> iterator = operations.iterator(); iterator.hasNext();) {
					Operation operation = (Operation)iterator.next();
					if(operationsInWrongOrder.contains(operation)) {
						operationsInRightOrder.add(operation);
					}
				}
			}
		}
		
		return operationsInRightOrder;
	}
}
