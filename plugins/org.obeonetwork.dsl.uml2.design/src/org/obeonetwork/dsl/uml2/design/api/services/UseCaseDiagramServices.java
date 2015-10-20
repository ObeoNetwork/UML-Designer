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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.UseCase;
import org.obeonetwork.dsl.uml2.design.internal.services.NodeInverseRefsServices;
import org.obeonetwork.dsl.uml2.design.internal.services.RelatedServices;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

/**
 * A set of services to handle the Use case diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UseCaseDiagramServices extends AbstractDiagramServices {
	/**
	 * Retrieve the cross references of the association of all the UML elements displayed as node in a
	 * Diagram. Note that a Property cross reference will lead to retrieve the cross references of this
	 * property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getAssociationInverseRefs(DDiagram diagram) {
		return NodeInverseRefsServices.INSTANCE.getAssociationInverseRefs(diagram);
	}

	/**
	 * Retrieve the cross references of the extend of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getExtendInverseRefs(DDiagram diagram) {
		return NodeInverseRefsServices.INSTANCE.getNodeInverseRefs(diagram, "Extend"); //$NON-NLS-1$
	}

	/**
	 * Retrieve the cross references of the generalization of all the UML elements displayed as node in a
	 * Diagram. Note that a Property cross reference will lead to retrieve the cross references of this
	 * property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getGeneralizationInverseRefs(DDiagram diagram) {
		return NodeInverseRefsServices.INSTANCE.getGeneralizationInverseRefs(diagram);
	}

	/**
	 * Retrieve the cross references of the include of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getIncludeInverseRefs(DDiagram diagram) {
		return NodeInverseRefsServices.INSTANCE.getNodeInverseRefs(diagram, "Include"); //$NON-NLS-1$
	}

	/**
	 * Get related elements for use case diagram.
	 *
	 * @param cur
	 *            Element
	 * @return Related elements
	 */
	public Collection<EObject> getRelatedForUseCase(EObject cur) {
		if (!(cur instanceof UseCase || cur instanceof Actor)) {
			final List<EObject> result = ImmutableList.copyOf(
					Iterables.filter(RelatedServices.INSTANCE.getRelated(cur), new Predicate<EObject>() {

						public boolean apply(EObject input) {
							return input instanceof UseCase || input instanceof Actor;
						}
					}));
			return result;
		}
		return RelatedServices.INSTANCE.getRelated(cur);
	}

	/**
	 * Check if an element is a classifier.
	 *
	 * @param element
	 *            Element
	 * @return True if element is an instance of classifier
	 */
	public boolean isClassifier(EObject element) {
		return element instanceof Classifier;
	}
}
