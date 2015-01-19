/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.api.services;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

/**
 * Manage the UML in colors stereotypes.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ArchetypeServices {
	/**
	 * Role.
	 */
	public static final String ROLE = "Role"; //$NON-NLS-1$

	/**
	 * Thing.
	 */
	public static final String THING = "Thing"; //$NON-NLS-1$

	/**
	 * Description.
	 */
	public static final String DESCRIPTION = "Description"; //$NON-NLS-1$

	/**
	 * Moment interval.
	 */
	public static final String MOMENT_INTERVAL = "MomentInterval"; //$NON-NLS-1$

	/**
	 * Set as an archetype description.
	 *
	 * @param clazz
	 *            Class to set
	 */
	public void addArchetypeDescriptionKeyword(Class clazz) {
		addArchetypeKeyword(clazz, ArchetypeServices.DESCRIPTION);
	}

	/**
	 * Add an archetype keyword.
	 *
	 * @param clazz
	 *            Class
	 * @param archetype
	 *            Keyword
	 */
	private void addArchetypeKeyword(Class clazz, String archetype) {
		clearArchetypesKeywords(clazz);
		clazz.addKeyword(archetype);
	}

	/**
	 * Set as an archetype moment interval.
	 *
	 * @param clazz
	 *            Class to set
	 */
	public void addArchetypeMomentIntervalKeyword(Class clazz) {
		addArchetypeKeyword(clazz, ArchetypeServices.MOMENT_INTERVAL);
	}

	/**
	 * Set as an archetype role.
	 *
	 * @param clazz
	 *            Class to set
	 */
	public void addArchetypeRoleKeyword(Class clazz) {
		addArchetypeKeyword(clazz, ArchetypeServices.ROLE);
	}

	/**
	 * Set as an archetype thing.
	 *
	 * @param clazz
	 *            Class to set
	 */
	public void addArchetypeThingKeyword(Class clazz) {
		addArchetypeKeyword(clazz, ArchetypeServices.THING);
	}

	private void clearArchetypesKeywords(Class clazz) {
		clazz.removeKeyword(MOMENT_INTERVAL);
		clazz.removeKeyword(DESCRIPTION);
		clazz.removeKeyword(THING);
		clazz.removeKeyword(ROLE);
	}

	private boolean hasKeyword(EObject clazz, String keyword) {
		if (clazz instanceof Class) {
			return ((Class)clazz).getKeywords().contains(keyword);
		}
		return false;
	}

	/**
	 * Check if a class is a description.
	 *
	 * @param clazz
	 *            class
	 * @return True if element is a description
	 */
	public boolean isDescription(EObject clazz) {
		return hasKeyword(clazz, DESCRIPTION);
	}

	/**
	 * Check if an association is a valid archetype link.
	 *
	 * @param association
	 *            Association
	 * @return True if the association is a valid archetype link
	 */
	public boolean isInvalidArchetypesLink(Association association) {
		final EList<Property> ends = association.getMemberEnds();
		final Property end1 = ends.get(0);
		final Property end2 = ends.get(1);
		final Type end1Type = end1.getType();
		final Type end2Type = end2.getType();

		if (isDescription(end1Type) && isRole(end2Type) || isDescription(end2Type) && isRole(end1Type)) {
			return true;
		} else if (isDescription(end1Type) && isMomentInterval(end2Type) || isDescription(end2Type)
				&& isMomentInterval(end1Type)) {
			return true;
		} else if (isThing(end1Type) && isMomentInterval(end2Type) || isThing(end2Type)
				&& isMomentInterval(end1Type)) {
			return true;
		} else if (isRole(end1Type) && isDescription(end2Type) || isRole(end2Type) && isDescription(end1Type)) {
			return true;
		} else if (isRole(end1Type) && isRole(end2Type) || isRole(end2Type) && isRole(end1Type)) {
			return true;
		} else if (isMomentInterval(end1Type) && isDescription(end2Type) || isMomentInterval(end2Type)
				&& isDescription(end1Type)) {
			return true;
		} else if (isMomentInterval(end1Type) && isThing(end2Type) || isMomentInterval(end2Type)
				&& isThing(end1Type)) {
			return true;
		} else if (isDescription(end1Type) && isThing(end2Type)
				&& !end1.getAggregation().equals(AggregationKind.NONE_LITERAL) || isDescription(end2Type)
				&& isThing(end1Type) && !end2.getAggregation().equals(AggregationKind.NONE_LITERAL)) {
			return true;
		} else if (isThing(end1Type) && isRole(end2Type)
				&& !end1.getAggregation().equals(AggregationKind.NONE_LITERAL) || isThing(end2Type)
				&& isRole(end1Type) && !end2.getAggregation().equals(AggregationKind.NONE_LITERAL)) {
			return true;
		}
		return false;
	}

	/**
	 * Check if a class is a moment interval.
	 *
	 * @param clazz
	 *            class
	 * @return True if element is a moment interval
	 */
	public boolean isMomentInterval(EObject clazz) {
		return hasKeyword(clazz, MOMENT_INTERVAL);
	}

	/**
	 * Check if a class is a role.
	 *
	 * @param clazz
	 *            class
	 * @return True if element is a role
	 */
	public boolean isRole(EObject clazz) {
		return hasKeyword(clazz, ROLE);
	}

	/**
	 * Check if a class is a thing.
	 *
	 * @param clazz
	 *            class
	 * @return True if element is a thing
	 */
	public boolean isThing(EObject clazz) {
		return hasKeyword(clazz, THING);
	}
}
