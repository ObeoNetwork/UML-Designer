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
package org.obeonetwork.dsl.uml2.design.services.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * Utility services to manage name creation on interactions.
 * 
 * @author Mélanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public final class InteractionServices {

	private static final String SCENARIO_PREFIX = "Scenario_";

	/**
	 * Hidden constructor.
	 */
	private InteractionServices() {
	}

	/**
	 * Parse the edited label content and update the underlying {@link Operation}.
	 * 
	 * @param pkg
	 *            the container {@link Package} object.
	 */
	public static String getNewInteractionName(EObject pkg) {
		StringBuffer name = new StringBuffer(SCENARIO_PREFIX);
		name.append(getNumberOfInteractions((Package)pkg));
		return name.toString();
	}

	private static int getNumberOfInteractions(Package pkg) {
		int lastUsedIndex = -1;
		for (PackageableElement element : pkg.getPackagedElements()) {
			String name = element.getName();
			if (element instanceof Interaction && name != null && name.startsWith(SCENARIO_PREFIX)) {
				int index = Integer.valueOf(name.substring(name.lastIndexOf("_") + 1));
				if (index > lastUsedIndex)
					lastUsedIndex = index;
			}
		}

		return lastUsedIndex + 1;
	}
}
