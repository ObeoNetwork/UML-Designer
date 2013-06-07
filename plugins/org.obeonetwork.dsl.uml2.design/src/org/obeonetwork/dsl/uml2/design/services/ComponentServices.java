/*******************************************************************************
 * Copyright (c) 2009, 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.services;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ComponentRealization;

/**
 * Utility services to manage operation creation.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public final class ComponentServices {

	/**
	 * Create a component realization.
	 * 
	 * @param realizingClassifier
	 *            Classifier that realizes the component
	 * @param abstraction
	 *            the component realized
	 * @return the component realization
	 */
	public ComponentRealization createComponentRealization(Classifier realizingClassifier,
			Component abstraction) {
		ComponentRealization result = abstraction.createRealization(realizingClassifier.getName() + "To"
				+ abstraction.getName());
		result.getRealizingClassifiers().add(realizingClassifier);
		return result;
	}

}
