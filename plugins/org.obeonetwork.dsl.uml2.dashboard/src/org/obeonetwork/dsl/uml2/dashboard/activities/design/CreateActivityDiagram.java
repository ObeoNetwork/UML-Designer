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
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Link to create an activity diagram.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class CreateActivityDiagram extends NewDiagramHyperLinkAdapter {
	private static final String ACTIVITY_DIAGRAM = "Activity Diagram"; //$NON-NLS-1$

	@Override
	protected boolean createDiagram(EObject project, Session session) {
		if (!(project instanceof Package)) {
			return false;
		}
		final Activity activity = getActivity((Package)project);
		final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
		final CommandStack commandStack = domain.getCommandStack();

		final List<Activity> activities = new ArrayList<Activity>();
		activities.add(activity);
		final Command command = AddCommand.create(domain, project, null, activities);
		commandStack.execute(command);
		return super.createDiagram(activity, session);
	}

	/**
	 * Get an activity.
	 *
	 * @param parent
	 *            Parent
	 * @return Activity
	 */
	private Activity getActivity(NamedElement parent) {
		// Check if an activity already exists
		if (parent.eContents() != null && parent.eContents().size() > 0) {
			for (final EObject obj : parent.eContents()) {
				if (obj instanceof Activity) {
					// There's already an activity
					// Do nothing
					return (Activity)obj;
				}
			}
		}
		final Activity activity = UMLFactory.eINSTANCE.createActivity();
		final String activityLabel = parent.getName(); // $NON-NLS-1$
		activity.setName(activityLabel);
		return activity;
	}

	@Override
	public String getRepresentationName() {
		return ACTIVITY_DIAGRAM;
	}
}
