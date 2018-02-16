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
package org.obeonetwork.dsl.uml2.design.internal.commands;

import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.DiagramDescriptionSpec;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.core.internal.commands.AbstractCreateDiagram;

/**
 * Create profile diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
@SuppressWarnings("restriction")
public class CreateProfileDiagram extends AbstractCreateDiagram<Profile> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Profile createSemanticObject() {
		return UMLFactory.eINSTANCE.createProfile();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RepresentationDescription getDiagramDescription(Session session, Profile profile) {
		for (final RepresentationDescription representation : DialectManager.INSTANCE
				.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), profile)) {
			if ("Profile Diagram".equals(representation.getName()) //$NON-NLS-1$
					&& representation instanceof DiagramDescriptionSpec) {
				return representation;
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getDiagramLabel(Profile profile) {
		return profile.getName() + " " + "Profile Diagram"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
