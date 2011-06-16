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
package org.obeonetwork.dsl.uml2.design.services.internal;

import org.eclipse.uml2.uml.Type;

/**
 * Utility class used to handle information about a name and a type.
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class NameAndType {
	/**
	 * The name.
	 */
	private String name;

	/**
	 * The type.
	 */
	private Type type;

	/**
	 * Constructor.
	 *
	 * @param name the name.
	 * @param type the type.
	 */
	public NameAndType(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}
}
