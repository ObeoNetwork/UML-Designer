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

/**
 * Utility services to manage direct label edition on objects diagrams.
 * 
 * @author Melanie Bats<a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public final class ObjectServices {

	/**
	 * Hidden constructor.
	 */
	private ObjectServices() {
	}

	/**
	 * Get value.
	 * 
	 * @param inputLabel
	 *            Input representing a property value definition with format : name=value
	 * @return The value
	 */
	public static String getValue(String inputLabel) {
		final String value = inputLabel.trim().substring(inputLabel.indexOf("=") + 1).trim();
		return value;
	}

	/**
	 * Get instance name.
	 * 
	 * @param inputLabel
	 *            Input representing an instance definition with format : name : classifier
	 * @return The instance name
	 */
	public static String getInstanceName(String inputLabel) {
		final String name = inputLabel.substring(0, inputLabel.indexOf(":")).trim();
		return name;
	}
}
