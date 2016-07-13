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
package org.obeonetwork.dsl.uml2.dashboard.activities.extend;

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
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Link to create a Profile diagram.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class CreateProfileDiagram extends NewDiagramHyperLinkAdapter {
	private static final String PROFILE_DIAGRAM = "Profile Diagram"; //$NON-NLS-1$

	@Override
	protected boolean createDiagram(EObject project, Session session) {
		if (!(project instanceof Package)) {
			return false;
		}
		final Profile profile = getProfile((Package)project);
		final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
		final CommandStack commandStack = domain.getCommandStack();

		final List<Profile> profiles = new ArrayList<Profile>();
		profiles.add(profile);
		final Command command = AddCommand.create(domain, project, null, profiles);
		commandStack.execute(command);
		return super.createDiagram(profile, session);
	}

	/**
	 * Get a profile.
	 *
	 * @param parent
	 *            Parent
	 * @return Profile
	 */
	private Profile getProfile(NamedElement parent) {
		final Profile profile = UMLFactory.eINSTANCE.createProfile();
		final String profileLabel = parent.getName() + " profile"; //$NON-NLS-1$
		profile.setName(profileLabel);
		return profile;
	}

	@Override
	public String getRepresentationName() {
		return PROFILE_DIAGRAM;
	}
}
