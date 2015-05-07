/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.internal.dialogs;

/**
 * Is thrown if an operation is canceled.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class CancelOperationException extends RuntimeException {
	private static final long serialVersionUID = -1417450835151008886L;

	/**
	 * Constructor.
	 *
	 * @param message
	 *            the message
	 */
	public CancelOperationException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the causing exception
	 */
	public CancelOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Default constructor.
	 *
	 * @param cause
	 *            the causing exception
	 */
	public CancelOperationException(Throwable cause) {
		super(cause);
	}
}
