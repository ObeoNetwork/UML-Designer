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
package org.obeonetwork.dsl.uml2.properties.uml.components;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.util.UMLSwitch;

/**
 * @author <a href="mailto:frederic.bats@obeo.fr">Frederic Bats</a>
 */
public class ChangePropertyOwnerSwitch extends UMLSwitch<Element>{

	/**
	 * Constructor.
	 */
	public ChangePropertyOwnerSwitch(Property property) {
		this.property = property;
	}

	/**
	 * The property to change owner.
	 */
	private Property property;

	@Override
	public Element caseClass(Class object) {
		object.getOwnedAttributes().add(property);
		return object;
	}
	@Override
	public Element caseInterface(Interface object) {
		object.getOwnedAttributes().add(property);
		return object;
	}
	@Override
	public Element caseDataType(DataType object) {
		object.getOwnedAttributes().add(property);
		return object;
	}
}
