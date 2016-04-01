/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.dashboard.activities.design;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.NewDiagramHyperLinkAdapter;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Link to create a State Machine diagram.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class CreateStateMachineDiagram extends NewDiagramHyperLinkAdapter {
	private static final String STATEMACHINE_DIAGRAM = "State Machine Diagram"; //$NON-NLS-1$

	@Override
	protected boolean createDiagram(EObject project, Session session) {
		if (!(project instanceof Package)) {
			return false;
		}
		final StateMachine stateMachine = getStatemachine((NamedElement)project);
		final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
		final CommandStack commandStack = domain.getCommandStack();

		final List<StateMachine> stateMachines = new ArrayList<StateMachine>();
		stateMachines.add(stateMachine);
		final Command command = AddCommand.create(domain, project, null, stateMachines);
		commandStack.execute(command);
		return super.createDiagram(stateMachine, session);
	}

	@Override
	public String getRepresentationName() {
		return STATEMACHINE_DIAGRAM;
	}

	/**
	 * Get an statemachine.
	 *
	 * @param parent
	 *            Parent
	 * @return Statemachine
	 */
	private StateMachine getStatemachine(NamedElement parent) {
		// Check if an statemachine already exists
		if (parent.eContents() != null && parent.eContents().size() > 0) {
			for (final EObject obj : parent.eContents()) {
				if (obj instanceof StateMachine) {
					// There's already an statemachine
					// Do nothing
					return (StateMachine)obj;
				}
			}
		}
		final StateMachine statemachine = UMLFactory.eINSTANCE.createStateMachine();
		final String statemachineLabel = parent.getName() + " statemachine"; //$NON-NLS-1$
		final Region region = UMLFactory.eINSTANCE.createRegion();
		region.setName("Region1"); //$NON-NLS-1$
		statemachine.getRegions().add(region);
		statemachine.setName(statemachineLabel);
		return statemachine;
	}
}
