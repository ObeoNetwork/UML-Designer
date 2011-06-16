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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OccurrenceSpecification;

import fr.obeo.dsl.viewpoint.DDiagram;
import fr.obeo.dsl.viewpoint.DEdge;
import fr.obeo.dsl.viewpoint.DNode;

/**
 * Utility services to manage sequence diagrams.
 * 
 * @author Gonzague Reydet <a href="mailto:gonzague.reydet@obeo.fr">gonzague.reydet@obeo.fr</a>
 */
public class SequenceServices {
	/**
	 * Retrieves the context element ({@link Lifeline} or {@link ExecutionSpecification}) of the given
	 * {@link OccurrenceSpecification}.
	 * 
	 * @param occurrenceSpecification
	 *            the {@link OccurrenceSpecification} for which to find the context
	 * @return the {@link ExecutionSpecification} on which the given {@link OccurrenceSpecification} is
	 *         attached or otherwise, the {@link Lifeline}otherwise it is attached to.
	 */
	public NamedElement findOccurrenceSpecificationContext(
			OccurrenceSpecification occurrenceSpecification) {
		// Construct a list of the previous fragments of the given occurrence specification
		List<InteractionFragment> fragments = occurrenceSpecification.getEnclosingInteraction()
				.getFragments();
		fragments = new ArrayList<InteractionFragment>(fragments.subList(0,
				fragments.indexOf(occurrenceSpecification)));
		Collections.reverse(fragments);

		// The context of the given occurrence specification will be the first
		// ExecutionOccurrenceSpecification on the list
		// which is attached to the same lifeline and which is not a sub execution.
		final Lifeline lifeline = occurrenceSpecification.getCovereds().get(0);
		final List<ExecutionSpecification> subExecution = new ArrayList<ExecutionSpecification>();
		for (InteractionFragment interactionFragment : fragments) {
			if (interactionFragment instanceof ExecutionOccurrenceSpecification
					&& interactionFragment.getCovered(lifeline.getName()) != null) {
				final ExecutionOccurrenceSpecification occurence = (ExecutionOccurrenceSpecification)interactionFragment;
				final ExecutionSpecification execution = occurence.getExecution();

				if (execution.getFinish() == occurence) {
					// Occurrence is a termination of a sub execution
					subExecution.add(execution);
					continue;
				} else if (subExecution.contains(execution)) {
					// Occurrence is a start of a sub execution
					subExecution.remove(execution);
					continue;
				}

				// Occurrence is the start end of the current execution context
				return ((ExecutionOccurrenceSpecification)interactionFragment).getExecution();
			}
		}

		// If we did not find an execution for the context of the given occurrenceSpecification,
		// then the context is the lifeline on which it is attached
		return lifeline;
	}

	/**
	 * Finds the first level of {@link ExecutionSpecification} in the context of the given {@link Lifeline}.
	 * 
	 * @param lifeline
	 *            the context.
	 * @return the {@link ExecutionSpecification} semantic candidates.
	 */
	public List<ExecutionSpecification> executionSemanticCandidates(Lifeline lifeline) {
		return getFirstLevelExecutions(lifeline, lifeline.getInteraction().getFragments());
	}

	/**
	 * Finds the first level of {@link ExecutionSpecification} in the context of the given
	 * {@link ExecutionSpecification}.
	 * 
	 * @param execution
	 *            the context.
	 * @return the {@link ExecutionSpecification} semantic candidates.
	 */
	public List<ExecutionSpecification> executionSemanticCandidates(ExecutionSpecification execution) {
		final List<InteractionFragment> fragments = execution.getEnclosingInteraction().getFragments();

		final int startIndex = fragments.indexOf(execution.getStart());
		final int finishIndex = fragments.indexOf(execution.getFinish());
		final List<InteractionFragment> candidateFragments = new ArrayList<InteractionFragment>(fragments.subList(
				startIndex + 1, finishIndex));

		return getFirstLevelExecutions(execution.getCovereds().get(0), candidateFragments);
	}

	/**
	 * Find the first level of {@link ExecutionSpecification} in the given {@link InteractionFragment} list.
	 * 
	 * @param lifeline
	 *            the {@link Lifeline} which is covered by the searched {@link ExecutionSpecification}
	 * @param subFragments
	 *            a sub-list of {@link InteractionFragment} to inspect for the first
	 *            {@link ExecutionSpecification} level.
	 * @return {@link List} of the {@link ExecutionSpecification} or <code>null</code> if the model is
	 *         inconsistent
	 */
	private static List<ExecutionSpecification> getFirstLevelExecutions(Lifeline lifeline,
			List<InteractionFragment> subFragments) {
		final List<ExecutionSpecification> executions = new ArrayList<ExecutionSpecification>();

		for (int i = 0; i < subFragments.size(); i++) {
			final InteractionFragment fragment = subFragments.get(i);

			if (fragment instanceof ExecutionOccurrenceSpecification
					&& fragment.getCovereds().contains(lifeline)) {
				// We found a good execution candidate.
				final ExecutionSpecification execution = ((ExecutionOccurrenceSpecification)fragment)
						.getExecution();

				if (execution.getStart() == fragment) {
					executions.add(execution);

					// The next execution candidate will be after the termination of the current one.

					// CHECKSTYLE:OFF
					i = subFragments.indexOf(execution.getFinish());
					// CHECKSTYLE:ON
					if (i == -1) {
						return null;
					}
				} else {
					return null;
				}
			}
		}

		return executions;
	}

	/**
	 * Delete the all the semantic elements attached to the given node. This will find the semantic elements
	 * of the current node and those coming recursively from sub-bordered nodes and linked edges.
	 * 
	 * @param node
	 *            the root node to delete.
	 * @return the parent diagram.
	 */
	public DDiagram fullDelete(DNode node) {
		final Collection<EObject> elementsToDelete = getSemanticElementsToDelete(node);

		for (EObject eObject : elementsToDelete) {
			EcoreUtil.remove(eObject);
		}

		return node.getParentDiagram();
	}

	/**
	 * Retreives all the semantic elements of the current node, those of the incoming and outgoing edges and
	 * recursively along the sub-bordered node tree.
	 * 
	 * @param node
	 *            the root {@link DNode}
	 * @return the list of attached semantic elements
	 */
	private static Collection<EObject> getSemanticElementsToDelete(DNode node) {
		final Collection<EObject> elementsToDelete = new LinkedHashSet<EObject>();

		elementsToDelete.addAll(node.getSemanticElements());

		for (DEdge incomingEdge : node.getIncomingEdges()) {
			elementsToDelete.addAll(incomingEdge.getSemanticElements());
		}

		for (DEdge outgoingEdges : node.getOutgoingEdges()) {
			elementsToDelete.addAll(outgoingEdges.getSemanticElements());
		}

		for (DNode borderedNode : node.getOwnedBorderedNodes()) {
			elementsToDelete.addAll(getSemanticElementsToDelete(borderedNode));
		}

		return elementsToDelete;
	}
}
