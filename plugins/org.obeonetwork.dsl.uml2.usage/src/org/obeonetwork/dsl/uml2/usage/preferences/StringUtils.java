/*******************************************************************************
 * Copyright (c) 2010 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.obeonetwork.dsl.uml2.usage.preferences;

/**
 * @author André Dietisheim
 */
public class StringUtils {

	private static final String LINE_SEPARATOR_KEY = "line.separator";

	public StringUtils() {
	}	

	public static String getLineSeparator() {
		return System.getProperty(LINE_SEPARATOR_KEY);
	}
}
