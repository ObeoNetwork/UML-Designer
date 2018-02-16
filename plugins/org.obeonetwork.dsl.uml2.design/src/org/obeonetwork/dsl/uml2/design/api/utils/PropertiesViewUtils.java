/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.api.utils;

import org.eclipse.uml2.uml.Element;
import org.obeonetwork.dsl.uml2.core.internal.services.LabelServices;

/**
 * Computes labels for the EEF properties view.
 */
public class PropertiesViewUtils {

	/**
	 * Compute the label of the given element.
	 *
	 * @param element
	 *            the {@link Element} for which to retrieve a label.
	 * @return the computed label.
	 */
	public static String computeUmlLabel(Element element) {
		return LabelServices.INSTANCE.computeUmlLabel(element);
	}
}
