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
package org.obeonetwork.dsl.uml2.design.services;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * A set of services to handle the UML Statemachine diagram.
 * 
 * @author Mélanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class StatemachineServices {
	/**
	 * Create an statemachine under a behaviored classifier (class, component, use case).
	 * 
	 * @param parent
	 *            The parent
	 * @return An statemachine
	 */
	public StateMachine initStatemachineForClass(org.eclipse.uml2.uml.BehavioredClassifier parent) {
		StateMachine statemachine = getStatemachine(parent);
		parent.getOwnedBehaviors().add(statemachine);
		return statemachine;
	}

	/**
	 * Create an statemachine under a package.
	 * 
	 * @param pkg
	 *            The package
	 * @return An statemachine
	 */
	public StateMachine initStatemachineForPackage(org.eclipse.uml2.uml.Package pkg) {
		StateMachine statemachine = getStatemachine(pkg);
		pkg.getPackagedElements().add(statemachine);
		return statemachine;
	}

	/**
	 * Get an statemachine.
	 * 
	 * @param parent
	 *            Parent
	 * @return Statemachine
	 */
	private StateMachine getStatemachine(NamedElement parent) {
		// Check if an statemachine already exists
		if (parent.eContents() != null && parent.eContents().size() > 0) {
			for (EObject obj : parent.eContents()) {
				if (obj instanceof StateMachine) {
					// There's already an statemachine
					// Do nothing
					return (StateMachine)obj;
				}
			}
		}
		StateMachine statemachine = UMLFactory.eINSTANCE.createStateMachine();
		String statemachineLabel = parent.getName() + " statemachine";
		Region region = UMLFactory.eINSTANCE.createRegion();
		region.setName("Region1");
		statemachine.getRegions().add(region);
		statemachine.setName(statemachineLabel);
		return statemachine;
	}
}
