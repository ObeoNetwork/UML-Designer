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
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Create activity diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
@SuppressWarnings("restriction")
public class CreateActivityDiagram extends AbstractCreateDiagram<Activity> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Activity createSemanticObject() {
		return UMLFactory.eINSTANCE.createActivity();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RepresentationDescription getDiagramDescription(Session session, Activity activity) {
		for (final RepresentationDescription representation : DialectManager.INSTANCE
				.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), activity)) {
			if ("Activity Diagram".equals(representation.getName()) //$NON-NLS-1$
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
	protected String getDiagramLabel(Activity activity) {
		return activity.getName() + " " + "Activity Diagram"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
