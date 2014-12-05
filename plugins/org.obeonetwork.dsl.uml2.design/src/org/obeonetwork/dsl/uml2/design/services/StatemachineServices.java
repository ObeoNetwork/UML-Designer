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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TransitionKind;
import org.eclipse.uml2.uml.UMLFactory;

import com.google.common.collect.Lists;

/**
 * A set of services to handle the UML Statemachine diagram.
 * 
 * @author Mélanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class StatemachineServices {
	/**
	 * Label service.
	 */
	private LabelServices labelService = new LabelServices();
	
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

	public boolean isInitialState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.INITIAL_LITERAL);
	}

	public boolean isForkState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.FORK_LITERAL);
	}

	public boolean isJoinState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.JOIN_LITERAL);
	}

	public boolean isChoiceState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.CHOICE_LITERAL);
	}

	public boolean isJunctionState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.JUNCTION_LITERAL);
	}

	public boolean isTerminateState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.TERMINATE_LITERAL);
	}

	public boolean isShallowHistoryState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.SHALLOW_HISTORY_LITERAL);
	}

	public boolean isDeepHistoryState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.DEEP_HISTORY_LITERAL);
	}

	public boolean isExitPoint(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.EXIT_POINT_LITERAL);
	}

	/**
	 * Check if a state has an entry defined.
	 * 
	 * @param state
	 *            State
	 * @return True if an entry is defined
	 */
	public boolean hasEntry(State state) {
		return state.getEntry() != null;
	}

	/**
	 * Check if a state has an exit defined.
	 * 
	 * @param state
	 *            State
	 * @return True if an exit is defined
	 */
	public boolean hasExit(State state) {
		return state.getExit() != null;
	}

	/**
	 * Check if a state has a do activity defined.
	 * 
	 * @param state
	 *            State
	 * @return True if a do activity is defined
	 */
	public boolean hasDo(State state) {
		return state.getDoActivity() != null;
	}

	/**
	 * Check if a state has no entry defined.
	 * 
	 * @param state
	 *            State
	 * @return True if none entry is defined
	 */
	public boolean hasNoEntry(State state) {
		return !hasEntry(state);
	}

	/**
	 * Check if a state has no exit defined.
	 * 
	 * @param state
	 *            State
	 * @return True if none exit is defined
	 */
	public boolean hasNoExit(State state) {
		return !hasExit(state);
	}

	/**
	 * Check if a state has no do activity defined.
	 * 
	 * @param state
	 *            State
	 * @return True if none do activity is defined
	 */
	public boolean hasNoDo(State state) {
		return !hasDo(state);
	}

	/**
	 * Check if a transition is an external transition.
	 * 
	 * @param transition
	 *            Transition
	 * @return True if transition is external or local
	 */
	public boolean isExternalTransition(Transition transition) {
		return !TransitionKind.INTERNAL_LITERAL.equals(transition.getKind());
	}

	/**
	 * Get all internal transitions of a state.
	 * 
	 * @param state
	 *            State
	 * @return Internal transitions
	 */
	public List<Transition> getInternalTransitions(State state) {
		List<Transition> result = Lists.newArrayList();
		List<Transition> incomings = Lists.newArrayList(state.getIncomings());
		if (incomings == null)
			return result;

		List<Transition> outgoings = Lists.newArrayList(state.getOutgoings());
		if (outgoings == null)
			return result;

		incomings.retainAll(outgoings);
		for (Transition transition : incomings) {
			if (TransitionKind.INTERNAL_LITERAL.equals(transition.getKind())) {
				result.add(transition);
			}
		}
		return result;
	}
	
	/**
	 * Get label for the 'do' instruction.
	 * @param element Element
	 * @return Label
	 */
	public String computeDoLabel(Element element){
		return "do " + labelService.computeUmlLabel(element);
	}
	
	/**
	 * Get label for the 'entry' instruction.
	 * @param element Element
	 * @return Label
	 */
	public String computeEntryLabel(Element element){
		return "entry " + labelService.computeUmlLabel(element);
	}
	
	/**
	 * Get label for the 'entry' instruction.
	 * @param element Element
	 * @return Label
	 */
	public String computeExitLabel(Element element){
		return "exit " + labelService.computeUmlLabel(element);
	}
}
