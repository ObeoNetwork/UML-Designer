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

import java.util.List;

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.NewDiagramHyperLinkAdapter;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Link to create a Sequence diagram.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class CreateSequenceDiagram extends NewDiagramHyperLinkAdapter {
	private static final String SEQUENCE_DIAGRAM = "Sequence Diagram"; //$NON-NLS-1$

	@Override
	protected boolean createDiagram(EObject project, Session session) {
		if (!(project instanceof Package)) {
			return false;
		}

		final Interaction interaction = createInteraction(project);
		final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
		final CommandStack commandStack = domain.getCommandStack();

		final Command command = AddCommand.create(domain, project, null, interaction);
		commandStack.execute(command);
		return super.createDiagram(interaction, session);
	}

	/**
	 * Create interaction a new interaction in package.
	 *
	 * @param pkg
	 *            Package containing new interaction.
	 * @return Interaction
	 */
	public Interaction createInteraction(EObject pkg) {
		final UMLFactory factory = UMLFactory.eINSTANCE;
		final Interaction interaction = factory.createInteraction();
		interaction.setName(getNewInteractionName(pkg));
		return interaction;
	}

	@Override
	public String getRepresentationName() {
		return SEQUENCE_DIAGRAM;
	}
	
	/**
	 * Scenario element name prefix.
	 */
	private static final String SCENARIO_PREFIX = "Scenario_"; //$NON-NLS-1$
	/**
	 * Parse the edited package to find the name of the new interaction.
	 *
	 * @param pkg
	 *            the container {@link Package} object.
	 * @return Name for new interaction.
	 */
	public String getNewInteractionName(EObject pkg) {
		final StringBuffer name = new StringBuffer(SCENARIO_PREFIX);
		name.append(getNumberOfElements(((Package)pkg).getPackagedElements(), SCENARIO_PREFIX));
		return name.toString();
	}
	/**
	 * Search the index of the last created element.
	 *
	 * @param elements
	 *            List of elements.
	 * @param prefix
	 *            Prefix defining the index
	 * @return The index to use for a new element
	 */
	@SuppressWarnings("rawtypes")
	private int getNumberOfElements(List elements, String prefix) {
		int lastUsedIndex = -1;
		for (final Object element : elements) {
			final String name = ((NamedElement)element).getName();
			if (name != null && name.startsWith(prefix)) {
				final int index = Integer.valueOf(name.substring(name.lastIndexOf("_") + 1)).intValue(); //$NON-NLS-1$
				if (index > lastUsedIndex) {
					lastUsedIndex = index;
				}
			}
		}

		return lastUsedIndex + 1;
	}
}
