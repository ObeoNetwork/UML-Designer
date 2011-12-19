package org.obeonetwork.dsl.uml2.design.services;

import org.eclipse.uml2.uml.Operation;
import org.obeonetwork.dsl.uml2.design.services.internal.NamedElementServices;

/**
 * Utility services to manage operation creation.
 * 
 * @author Mélanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class OperationServices {

	/**
	 * Create an operation in a class.
	 * 
	 * @param type
	 *            the container {@link OperationServices.eclipse.uml2.uml.Class} element
	 * @return New operation
	 */
	public static Operation createOperation(org.eclipse.uml2.uml.Class type) {
		// Create new operation
		String name = NamedElementServices.getNewOperationName(type);
		Operation operation = type.createOwnedOperation(name, null, null, null);
		return operation;
	}
}
