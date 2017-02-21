/*******************************************************************************
 * Copyright (c) 2009, 2017 Obeo.
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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
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
	 * Human Actor.
	 */
	public static final String HUMAN = "Human"; //$NON-NLS-1$

	/**
	 * Set as a Human.
	 *
	 * @param actor
	 *            Actor to set
	 */
	public void addActorHumanKeyword(Actor actor) {
		addActorKeyword(actor, UseCaseDiagramServices.HUMAN);
	}

	/**
	 * Add an actor keyword.
	 *
	 * @param actor
	 *            Actor
	 * @param type
	 *            Keyword
	 */
	private void addActorKeyword(Actor actor, String type) {
		clearActorKeywords(actor);
		actor.addKeyword(type);
	}

	private void clearActorKeywords(Actor actor) {
		actor.removeKeyword(HUMAN);
	}

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
	 * Get the Package containing the given UseCase, or create an new Package if it is a sub-UseCase. In this
	 * case, the new Package will be put under the parent Package of the UseCase.
	 *
	 * @param useCase
	 *            the given UseCase.
	 * @return the Package containing the given UseCase, or create an new Package if it is a sub-UseCase. In
	 *         this case, the new Package will be put under the parent Package of the UseCase.
	 */
	public Package getOrCreatePackage(UseCase useCase) {
		Package pkg = null;
		final EObject container = useCase.eContainer();
		// If it is a sub-UseCase, then create a sub-Package to be the root element of the new UseCase diagram.
		// But try to search for such an existing Package first.
		if (container instanceof UseCase) {
			final Package containerPackage = getPackage(container);
			pkg = getSubPackage(containerPackage, useCase.getName());
			if (pkg == null) {
				pkg = (Package)EcoreUtil.create(UMLPackage.Literals.PACKAGE);
				pkg.setName(useCase.getName());
				containerPackage.getPackagedElements().add(pkg);
			}
		} else {
			pkg = getPackage(useCase);
		}
		return pkg;
	}

	/**
	 * Get the Package containing the given EObject.
	 *
	 * @param object
	 *            the given EObject.
	 * @return the Package containing the given EObject.uuh
	 */
	private Package getPackage(EObject object) {
		Package pkg = null;
		if (object != null) {
			final EObject container = object.eContainer();
			if (container instanceof Package) {
				pkg = (Package)container;
			} else {
				pkg = getPackage(container);
			}
		}
		return pkg;
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
			final List<EObject> result = ImmutableList.copyOf(Iterables.filter(
					RelatedServices.INSTANCE.getRelated(cur), new Predicate<EObject>() {

						public boolean apply(EObject input) {
							return input instanceof UseCase || input instanceof Actor;
						}
					}));
			return result;
		}
		return RelatedServices.INSTANCE.getRelated(cur);
	}

	/**
	 * Get the sub-Package with the given name if it exists.
	 *
	 * @param container
	 *            the Package container to search into.
	 * @param name
	 *            the name of the Package to get.
	 * @return the sub-Package with the given name if it exists, <code>null</code> otherwise.
	 */
	private Package getSubPackage(Package container, String name) {
		final EList<Element> ownedElements = container.getOwnedElements();
		for (final Element element : ownedElements) {
			if (element instanceof Package && name.equals(((Package)element).getName())) {
				return (Package)element;
			}
		}
		return null;
	}

	private boolean hasKeyword(EObject actor, String keyword) {
		if (actor instanceof Actor) {
			return ((Actor)actor).getKeywords().contains(keyword);
		}
		return false;
	}

	/**
	 * Check if an object is an Actor.
	 *
	 * @param actor
	 *            actor
	 * @return True if element is an actor
	 */
	public boolean isActor(EObject actor) {

		return actor instanceof Actor;
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

	/**
	 * Check if an actor is a human.
	 *
	 * @param actor
	 *            actor
	 * @return True if element is a human
	 */
	public boolean isHumanActor(EObject actor) {
		return hasKeyword(actor, HUMAN);
	}

	/**
	 * Check if an object is not an Actor.
	 *
	 * @param actor
	 *            actor
	 * @return True if element is not an actor
	 */
	public boolean isNotAnActor(EObject actor) {

		return !isActor(actor);
	}

	/**
	 * Check if an actor is not a human.
	 *
	 * @param actor
	 *            actor
	 * @return True if element is not a human
	 */
	public boolean isNotHumanActor(EObject actor) {
		return !isHumanActor(actor);
	}

	/**
	 * Set as a non Human.
	 *
	 * @param actor
	 *            Actor to set
	 */
	public void removeActorHumanKeyword(Actor actor) {
		if (isHumanActor(actor)) {
			clearActorKeywords(actor);
		}
	}
}
