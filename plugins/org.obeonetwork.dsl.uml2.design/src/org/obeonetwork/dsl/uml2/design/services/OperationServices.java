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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.obeonetwork.dsl.uml2.design.services.internal.NamedElementServices;

/**
 * Utility services to manage operation creation.
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
}
