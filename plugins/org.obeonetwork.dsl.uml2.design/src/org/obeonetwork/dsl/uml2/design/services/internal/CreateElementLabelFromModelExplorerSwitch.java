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
package org.obeonetwork.dsl.uml2.design.services.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.util.UMLSwitch;

/**
 * A switch that handle the create element label from an UI command.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class CreateElementLabelFromModelExplorerSwitch extends UMLSwitch<String> {

	/**
	 * Interaction element name prefix.
	 */
	private static final String INTERACTION_PREFIX = "";

	/**
	 * State machine element name prefix.
	 */
	private static final String STATE_MACHINE_PREFIX = "";

	/**
	 * Activity element name prefix.
	 */
	private static final String ACTIVITY_PREFIX = "";

	/**
	 * Profile element name prefix.
	 */
	private static final String PROFILE_PREFIX = "";

	/**
	 * Return a new label for the given object.
	 * 
	 * @param object
	 *            the context
	 * @return a new label for the given object
	 */
	public String getNewLabel(EObject object) {
		return doSwitch(object);
	}

	@Override
	public String caseInteraction(Interaction object) {
		return genName(object.getPackage(), INTERACTION_PREFIX);
	}

	@Override
	public String caseStateMachine(StateMachine object) {
		return genName(object.getPackage(), STATE_MACHINE_PREFIX);
	}

	@Override
	public String caseActivity(Activity object) {
		return genName(object.getPackage(), ACTIVITY_PREFIX);
	}

	@Override
	public String caseProfile(Profile object) {
		return genName(object.getNestingPackage(), PROFILE_PREFIX);
	}

	private static String genName(NamedElement context, String prefix) {
		final StringBuffer result = new StringBuffer(prefix);
		result.append(context.getLabel());
		return result.toString();
	}
}
