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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ComponentRealization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Port;

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

	public boolean createUsageConnectionStartPrecondition(EObject preSource, EObject preSourceView,
			EObject Container, EObject diagram) {
		boolean result = preSource instanceof org.eclipse.uml2.uml.Class || preSource instanceof Port;
		return result;
	}

	public boolean createUsageConnectionCompletePrecondition(EObject preSource, EObject preSourceView,
			EObject preTarget, EObject preTargetView, EObject Container, EObject diagram) {
		boolean result = preTarget instanceof Interface;
		if (preSource instanceof org.eclipse.uml2.uml.Class || preSource instanceof Port) {
			result &= validSourceTarget(preSource, preSourceView, preTarget, preTargetView);
		} else {
			result = false;
		}
		return result;
	}

	public boolean createIRConnectionStartPrecondition(EObject preSource, EObject preSourceView,
			EObject Container, EObject diagram) {
		boolean result = preSource instanceof org.eclipse.uml2.uml.Class || preSource instanceof Port;
		return result;
	}

	public boolean createIRConnectionCompletePrecondition(EObject preSource, EObject preSourceView,
			EObject preTarget, EObject preTargetView, EObject Container, EObject diagram) {
		boolean result = preTarget instanceof Interface;
		if (preSource instanceof org.eclipse.uml2.uml.Class || preSource instanceof Port) {
			result &= validSourceTarget(preSource, preSourceView, preTarget, preTargetView);
		} else {
			result = false;
		}
		return result;
	}

	public boolean validSourceTarget(EObject source, EObject sourceView, EObject target, EObject targetView) {
		boolean result = true;

		if (source instanceof org.eclipse.uml2.uml.Class) {
			result &= targetView.eContainer().equals(sourceView.eContainer());
		} else if (source instanceof Port) {
			result &= targetView.eContainer().equals(sourceView.eContainer().eContainer());
		} else {
			result = false;
		}
		return result;
	}
}
