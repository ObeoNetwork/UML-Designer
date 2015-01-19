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
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.util.List;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

import com.google.common.collect.Lists;

/**
 * Services to handle typed Association concerns.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class AssociationServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final AssociationServices INSTANCE = new AssociationServices();

	/**
	 * Hidden constructor.
	 */
	private AssociationServices() {

	}

	/**
	 * Return the source of an association.
	 *
	 * @param association
	 *            the {@link Association} context
	 * @return first end of the association
	 */
	public Property getSource(Association association) {
		if (association.getMemberEnds() != null && association.getMemberEnds().size() > 0) {
			return association.getMemberEnds().get(0);
		}
		return null;
	}

	/**
	 * Get the type of the association source end.
	 *
	 * @param association
	 *            Association
	 * @return Type of the source
	 */
	public Type getSourceType(Association association) {
		final Property source = getSource(association);
		if (source != null) {
			return source.getType();
		}
		return null;
	}

	/**
	 * Return the target of an association.
	 *
	 * @param association
	 *            the {@link Association} context
	 * @return second end of the association
	 */
	public Property getTarget(Association association) {
		if (association.getMemberEnds() != null && association.getMemberEnds().size() > 1) {
			return association.getMemberEnds().get(1);
		}
		return null;
	}

	/**
	 * Get the type of the association target end.
	 *
	 * @param association
	 *            Association
	 * @return Type of the target
	 */
	public Type getTargetType(Association association) {
		final Property target = getTarget(association);
		if (target != null) {
			return target.getType();
		}
		return null;
	}

	/**
	 * Get types.
	 *
	 * @param association
	 *            Association
	 * @return List of types
	 */
	public List<Type> getTypes(Association association) {
		final List<Type> types = Lists.newArrayList();
		types.add(getSourceType(association));
		types.add(getTargetType(association));
		return types;
	}
}
