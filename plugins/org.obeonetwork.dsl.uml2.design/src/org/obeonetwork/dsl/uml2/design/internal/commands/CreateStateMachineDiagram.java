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
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Create statemachine diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
@SuppressWarnings("restriction")
public class CreateStateMachineDiagram extends AbstractCreateDiagram<StateMachine> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected StateMachine createSemanticObject() {
		return UMLFactory.eINSTANCE.createStateMachine();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RepresentationDescription getDiagramDescription(Session session, StateMachine stateMachine) {
		for (final RepresentationDescription representation : DialectManager.INSTANCE
				.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), stateMachine)) {
			if ("State Machine Diagram".equals(representation.getName()) //$NON-NLS-1$
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
	protected String getDiagramLabel(StateMachine stateMachine) {
		return stateMachine.getName() + " " + "State Machine Diagram"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
