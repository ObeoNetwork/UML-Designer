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
package org.obeonetwork.dsl.uml2.design.api.utils;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * UML Viewpoints.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlViewpoints {

	/**
	 * Enable UML viewpoints.
	 *
	 * @param session
	 *            Session
	 */
	public static void enable(final Session session) {
		if (session != null) {
			session.getTransactionalEditingDomain().getCommandStack()
			.execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
				@Override
				protected void doExecute() {
					final ViewpointSelectionCallback selection = new ViewpointSelectionCallback();
					for (final Viewpoint previouslySelected : session.getSelectedViewpoints(false)) {
						selection.deselectViewpoint(previouslySelected, session,
								new NullProgressMonitor());
					}
					selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().reused(), session,
							new NullProgressMonitor());
					selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().capture(),
							session, new NullProgressMonitor());
					selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().design(), session,
							new NullProgressMonitor());
					selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().review(), session,
							new NullProgressMonitor());
					selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().dashboard(),
							session, new NullProgressMonitor());
					selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().extend(), session,
							new NullProgressMonitor());
				}
			});
		}
	}

	/**
	 * Enable the UML Reused viewpoint.
	 *
	 * @param session
	 *            Session
	 */
	public static void enableReused(final Session session) {
		if (session != null) {
			session.getTransactionalEditingDomain().getCommandStack()
			.execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
				@Override
				protected void doExecute() {
					final ViewpointSelectionCallback selection = new ViewpointSelectionCallback();
					final Viewpoint reused = UmlViewpoints.fromViewpointRegistry().reused();
					for (final Viewpoint previouslySelected : session.getSelectedViewpoints(false)) {
						if (previouslySelected.getName().equals(reused.getName())) {
							return;
						}
					}
					selection.selectViewpoint(UmlViewpoints.fromViewpointRegistry().reused(), session,
							new NullProgressMonitor());
				}
			});
		}
	}

	/**
	 * UML viewpoints from viewpoint registry.
	 *
	 * @return UML viewpoints from viewpoint registry
	 */
	public static UmlViewpoints fromViewpointRegistry() {
		return new UmlViewpoints(ViewpointRegistry.getInstance());
	}

	/**
	 * Check if a viewpoint is an uml designer viewpoints.
	 *
	 * @param viewpoint
	 * @return True if the given viewpoint is an UML Designer viewpoint otherwise false.
	 */
	public static boolean isUmlViewpoint(Viewpoint viewpoint) {
		final EObject group = viewpoint.eContainer();
		if (group != null && group instanceof Group) {
			return "UML2 modelers".equals(((Group)group).getName()); //$NON-NLS-1$
		}
		return false;
	}

	private final ViewpointRegistry registry;

	/**
	 * Constructor.
	 *
	 * @param registry
	 *            Viewpoint registry
	 */
	public UmlViewpoints(ViewpointRegistry registry) {
		this.registry = registry;
	}

	/**
	 * Capture.
	 *
	 * @return viewpoint
	 */
	public Viewpoint capture() {
		return registry.getViewpoint(URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/Capture")); //$NON-NLS-1$
	}

	/**
	 * Dashboard.
	 *
	 * @return viewpoint
	 */
	public Viewpoint dashboard() {
		return registry.getViewpoint(URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/Dashboard")); //$NON-NLS-1$
	}

	/**
	 * Design.
	 *
	 * @return viewpoint
	 */
	public Viewpoint design() {
		return registry.getViewpoint(URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/Design")); //$NON-NLS-1$
	}

	/**
	 * Extend.
	 *
	 * @return viewpoint
	 */
	public Viewpoint extend() {
		return registry.getViewpoint(URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/Extend")); //$NON-NLS-1$
	}

	/**
	 * Reused.
	 *
	 * @return viewpoint
	 */
	public Viewpoint reused() {
		return registry.getViewpoint(URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/Reused")); //$NON-NLS-1$
	}

	/**
	 * Review.
	 *
	 * @return viewpoint
	 */
	public Viewpoint review() {
		return registry.getViewpoint(URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/Review")); //$NON-NLS-1$
	}
}
