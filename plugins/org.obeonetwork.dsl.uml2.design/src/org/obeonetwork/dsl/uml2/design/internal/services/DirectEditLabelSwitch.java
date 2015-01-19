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
package org.obeonetwork.dsl.uml2.design.internal.services;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.NamedElement;

/**
 * A switch that handle the label computation for each UML types for direct edit
 * operation.
 *
 * @author Melanie Bats <a
 *         href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DirectEditLabelSwitch extends DisplayLabelSwitch {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseClass(Class object) {
		final String templateParameters = LabelServices.INSTANCE
				.getTemplatedParameters(object);
		if (templateParameters != null) {
			return object.getName() + templateParameters;
		}

		return object.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseNamedElement(NamedElement object) {
		return object.getName();
	}
}
