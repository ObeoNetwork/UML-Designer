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
package org.obeonetwork.dsl.uml2.core.api.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TransitionKind;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.Vertex;
import org.obeonetwork.dsl.uml2.core.internal.services.LabelServices;

import com.google.common.collect.Lists;

/**
 * A set of services to handle the State Machine diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class StateMachineDiagramServices extends AbstractDiagramServices {
	/**
	 * Human Actor.
	 */
	public static final String VERTICAL = "Vertical"; //$NON-NLS-1$

	/**
	 * Add to a state the vertical keyword.
	 *
	 * @param state
	 *            State
	 * @param type
	 *            Keyword
	 */
	private void addKeyword(Element element, String type) {
		clearVerticalKeyword(element);
		element.addKeyword(type);
	}

	/**
	 * Set as Vertical.
	 *
	 * @param state
	 *            state to set
	 */
	public void addVerticalKeyword(State state) {
		addKeyword(state, VERTICAL);
	}

	/**
	 * Set as Vertical.
	 *
	 * @param stateMachine
	 *            state machine to set
	 */
	public void addVerticalKeyword(StateMachine stateMachine) {
		addKeyword(stateMachine, VERTICAL);
	}

	private void clearVerticalKeyword(Element element) {
		element.removeKeyword(VERTICAL);
	}

	/**
	 * Unset as Vertical.
	 *
	 * @param state
	 *            state to unset
	 */
	public void clearVerticalKeyword(State state) {
		clearVerticalKeyword((Element)state);
	}

	/**
	 * Unset as Vertical.
	 *
	 * @param stateMachine
	 *            state machine to unset
	 */
	public void clearVerticalKeyword(StateMachine stateMachine) {
		clearVerticalKeyword((Element)stateMachine);
	}

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
	 * Create a transition.
	 *
	 * @param self
	 *            region
	 * @param source_p
	 *            element source
	 * @param target_p
	 *            element target
	 */
	public void createTransition(Element  self, Element source_p, Element target_p){
		Element source = source_p;
		Element target = target_p;
		if (source instanceof Region){
			source = source.getOwner();
		}
		if (target instanceof Region){
			target = target.getOwner();
		}
		if (source instanceof Vertex
				&& target instanceof Vertex
				&& source.eContainer() == target.eContainer()) {
			createTransition((Region)source.eContainer(), (Vertex)source, (Vertex)target);
		}

	}

	private void createTransition(Region region, Vertex source, Vertex target) {
		final Transition transition = UMLFactory.eINSTANCE.createTransition();
		transition.setSource(source);
		transition.setTarget(target);
		region.getTransitions().add(transition);
	}

	/**
	 * Get connection point to display for a state machine.
	 *
	 * @param compositeState
	 *            the composite state
	 * @return list of connection point or empty list
	 */
	public List<Pseudostate> getConnectionPoints(State compositeState) {
		final List<Pseudostate> connectionPoints = new ArrayList<Pseudostate>();
		if (!compositeState.isSimple()) {
			if (!compositeState.getConnectionPoints().isEmpty()) {
				connectionPoints.addAll(compositeState.getConnectionPoints());
			}
		}
		return connectionPoints;
	}

	/**
	 * Get connection point to display for a state machine.
	 *
	 * @param stateMachine
	 *            the state machine
	 * @return list of connection point or empty list
	 */
	public List<Pseudostate> getConnectionPoints(StateMachine stateMachine) {
		final List<Pseudostate> connectionPoints = new ArrayList<Pseudostate>();
		if (!stateMachine.getConnectionPoints().isEmpty()) {
			connectionPoints.addAll(stateMachine.getConnectionPoints());
		}
		return connectionPoints;
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

	private boolean hasKeyword(Element element, String keyword) {
		return element.getKeywords().contains(keyword);
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
	 * Check if a state is a composite horizontal container.
	 *
	 * @param state
	 *            state
	 * @return True if element is horizontal
	 */
	public boolean isCompositeHorizontal(State state) {
		return !state.isSimple() && !isVertical(state);
	}

	/**
	 * Check if a state is a vertical container.
	 *
	 * @param state
	 *            state
	 * @return True if element is vertical
	 */
	public boolean isCompositeVertical(State state) {
		return !state.isSimple() && isVertical(state);
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
	 * Check if a State is a vertical container.
	 *
	 * @param state
	 *            element
	 * @return True if element is vertical
	 */
	public boolean isNotVertical(State state) {
		return !isVertical(state);
	}

	/**
	 * Check if an StateMachine is an horizontal container.
	 *
	 * @param stateMachine
	 *            element
	 * @return True if element is vertical
	 */
	public boolean isNotVertical(StateMachine stateMachine) {
		return !isVertical(stateMachine);
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

	/**
	 * Verify if selected elements to create transition are valid.
	 *
	 * @param self
	 *            region
	 * @param preSource
	 *            source element
	 * @param preTarget
	 *            target element
	 * @return true if valid
	 */
	public boolean isValidTransitionTarget(Element self, Element preSource, Element preTarget) {
		Element source = preSource;
		Element target = preTarget;
		if (source instanceof Region) {
			source = preSource.getOwner();
		}
		if (target instanceof Region) {
			target = preTarget.getOwner();
		}
		if (source.eContainer() == target.eContainer()) {
			return true;
		}
		return false;
	}

	/**
	 * Check if an element is a vertical container.
	 *
	 * @param element
	 *            element
	 * @return True if element is vertical
	 */
	private boolean isVertical(Element element) {
		return hasKeyword(element, VERTICAL);
	}

	/**
	 * Check if a StateMachine is a vertical container.
	 *
	 * @param stateMachine
	 *            element
	 * @return True if element is vertical
	 */
	public boolean isVertical(StateMachine stateMachine) {
		return isVertical((Element)stateMachine);
	}
}
