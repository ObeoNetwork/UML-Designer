/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.core.internal.services;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.obeonetwork.dsl.uml2.core.internal.services.ILabelConstants;

/**
 * A switch that handle the tooltip computation for each UML types.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class TooltipSwitch extends UMLSwitch<String> implements ILabelConstants {

	@Override
	public String caseModel(Model object) {
		return object.getName();
	}

	@Override
	public String caseNamedElement(NamedElement object) {
		Element pkg = object;
		String tooltip = object.getName();
		while (pkg != null) {
			pkg = pkg.getOwner();
			if (pkg instanceof NamedElement) {
				tooltip = ((NamedElement)pkg).getName() + "::" + tooltip; //$NON-NLS-1$
			}
		}
		return tooltip;
	}
}
