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

import java.util.List;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TransitionKind;
import org.obeonetwork.dsl.uml2.design.internal.services.LabelServices;

import com.google.common.collect.Lists;

/**
 * A set of services to handle the State Machine diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class StateMachineDiagramServices extends AbstractDiagramServices {
	/**
	 * Get label for the 'do' instruction.
	 *
	 * @param element
	 *            Element
	 * @return Label
	 */
	public String computeDoLabel(Element element) {
		return "do " + LabelServices.INSTANCE.computeUmlLabel(element); //$NON-NLS-1$
	}

	/**
	 * Get label for the 'entry' instruction.
	 *
	 * @param element
	 *            Element
	 * @return Label
	 */
	public String computeEntryLabel(Element element) {
		return "entry " + LabelServices.INSTANCE.computeUmlLabel(element); //$NON-NLS-1$
	}

	/**
	 * Get label for the 'entry' instruction.
	 *
	 * @param element
	 *            Element
	 * @return Label
	 */
	public String computeExitLabel(Element element) {
		return "exit " + LabelServices.INSTANCE.computeUmlLabel(element); //$NON-NLS-1$
	}

	/**
	 * Get all internal transitions of a state.
	 *
	 * @param state
	 *            State
	 * @return Internal transitions
	 */
	public List<Transition> getInternalTransitions(State state) {
		final List<Transition> result = Lists.newArrayList();
		final List<Transition> incomings = Lists.newArrayList(state.getIncomings());
		if (incomings == null) {
			return result;
		}

		final List<Transition> outgoings = Lists.newArrayList(state.getOutgoings());
		if (outgoings == null) {
			return result;
		}

		incomings.retainAll(outgoings);
		for (final Transition transition : incomings) {
			if (TransitionKind.INTERNAL_LITERAL.equals(transition.getKind())) {
				result.add(transition);
			}
		}
		return result;
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
	 * Is a choice state.
	 *
	 * @param state
	 *            Pseudo state to test
	 * @return True if the pseudo state is a choice state
	 */
	public boolean isChoiceState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.CHOICE_LITERAL);
	}

	/**
	 * Is a deep history state.
	 *
	 * @param state
	 *            Pseudo state to test
	 * @return True if the pseudo state is a deep history state
	 */
	public boolean isDeepHistoryState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.DEEP_HISTORY_LITERAL);
	}

	/**
	 * Is an exit point.
	 *
	 * @param state
	 *            Pseudo state to test
	 * @return True if the pseudo state is an exit point
	 */
	public boolean isExitPoint(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.EXIT_POINT_LITERAL);
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
	 * Is a fork state.
	 *
	 * @param state
	 *            Pseudo state to test
	 * @return True if the pseudo state is a fork state
	 */
	public boolean isForkState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.FORK_LITERAL);
	}

	/**
	 * Is an initial state.
	 *
	 * @param state
	 *            Pseudo state to test
	 * @return True if the pseudo state is an initial state
	 */
	public boolean isInitialState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.INITIAL_LITERAL);
	}

	/**
	 * Is a join state.
	 *
	 * @param state
	 *            Pseudo state to test
	 * @return True if the pseudo state is a join state
	 */
	public boolean isJoinState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.JOIN_LITERAL);
	}

	/**
	 * Is a junction state.
	 *
	 * @param state
	 *            Pseudo state to test
	 * @return True if the pseudo state is a junction state
	 */
	public boolean isJunctionState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.JUNCTION_LITERAL);
	}

	/**
	 * Is a shallow history state.
	 *
	 * @param state
	 *            Pseudo state to test
	 * @return True if the pseudo state is a shallow history state
	 */
	public boolean isShallowHistoryState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.SHALLOW_HISTORY_LITERAL);
	}

	/**
	 * Is a terminate state.
	 *
	 * @param state
	 *            Pseudo state to test
	 * @return True if the pseudo state is a terminate state
	 */
	public boolean isTerminateState(Pseudostate state) {
		return state.getKind().equals(PseudostateKind.TERMINATE_LITERAL);
	}
}
