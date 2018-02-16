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
package org.obeonetwork.dsl.uml2.core.internal.services;

/**
 * Describes the common label constants.
 *
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 */
public interface ILabelConstants {

	/**
	 * New line constant.
	 */
	public static final String NL = System.getProperty("line.separator"); //$NON-NLS-1$

	/**
	 * Open quote mark.
	 */
	public static final String OPEN_QUOTE_MARK = "\u00AB"; //$NON-NLS-1$

	/**
	 * Close quote mark.
	 */
	public static final String CLOSE_QUOTE_MARK = "\u00BB"; //$NON-NLS-1$

	/**
	 * Receiver element name suffix.
	 */
	public static final String RECEIVER_SUFFIX = "_receiver"; //$NON-NLS-1$

	/**
	 * Sender element name suffix.
	 */
	public static final String SENDER_SUFFIX = "_sender"; //$NON-NLS-1$

	/**
	 * Finish element name suffix.
	 */
	public static final String FINISH_SUFFIX = "_finish"; //$NON-NLS-1$

	/**
	 * Start element name suffix.
	 */
	public static final String START_SUFFIX = "_start"; //$NON-NLS-1$

	/**
	 * The guard suffix constant.
	 */
	public static final String GUARD_SUFFIX = "_guard"; //$NON-NLS-1$
}
