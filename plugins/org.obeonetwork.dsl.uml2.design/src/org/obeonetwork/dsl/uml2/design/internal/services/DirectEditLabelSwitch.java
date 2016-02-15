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
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TemplateableElement;

/**
 * A switch that handle the label computation for each UML types for direct edit operation.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DirectEditLabelSwitch extends DisplayLabelSwitch {

	/**
	 * Qualifier separator used for direct edit.
	 */
	public static final String QUALIFIER_SEPARATOR = ","; //$NON-NLS-1$

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String caseClass(Class object) {
		final String templateParameters = LabelServices.INSTANCE.getTemplatedParameters(object);
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
		if (object instanceof TemplateableElement){
			final String templateParameters = LabelServices.INSTANCE
					.getTemplatedParameters((TemplateableElement)object);
			if (templateParameters != null) {
				return object.getName() + templateParameters;
			}
		}
		return object.getName();
	}

	@Override
	public String caseProperty(Property property) {
		if (!property.getQualifiers().isEmpty()) {
			String label = ""; //$NON-NLS-1$
			boolean first = true;
			final DisplayLabelSwitch displayLabelSwitch = new DisplayLabelSwitch();
			for (final Property qualifier : property.getQualifiers()) {
				if (first) {
					label += displayLabelSwitch.doSwitch(qualifier);
					first = false;
				} else {
					label += QUALIFIER_SEPARATOR;
					label += displayLabelSwitch.doSwitch(qualifier);
				}
			}
			return label;
		}
		return super.caseProperty(property);
	}
}
