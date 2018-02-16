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
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.core.internal.commands.AbstractCreateDiagram;

/**
 * Create sequence diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class CreateSequenceDiagram extends AbstractCreateDiagram<Interaction> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Interaction createSemanticObject() {
		return UMLFactory.eINSTANCE.createInteraction();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RepresentationDescription getDiagramDescription(Session session, Interaction interaction) {
		for (final RepresentationDescription representation : DialectManager.INSTANCE
				.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), interaction)) {
			if ("Sequence Diagram".equals(representation.getName()) //$NON-NLS-1$
					&& representation instanceof SequenceDiagramDescription) {
				return representation;
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getDiagramLabel(Interaction interaction) {
		return interaction.getName() + " " + "Sequence Diagram"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
