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
package org.obeonetwork.dsl.uml2.design.api.services;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.UseCase;

/**
 * A set of services to handle the Use case cross table.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UseCaseCrossTableServices {
	/**
	 * Edit associated use case.
	 *
	 * @param element
	 *            Classifier
	 * @param useCase
	 *            Use case
	 * @param param
	 *            Parameter
	 */
	public void editAssociatedUseCase(Classifier element, UseCase useCase, String param) {
		if (param != null && param.length() > 0) {
			element.getUseCases().add(useCase);
		} else {
			element.getUseCases().remove(useCase);
		}
	}
}
