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
package org.obeonetwork.dsl.uml2.core.api.utils;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.obeonetwork.dsl.uml2.core.UMLDesignerCorePlugin;

/**
 * UML Viewpoints.
 *
 * @author Melanie Bats
 *         <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlCoreViewpoints {

    /**
     * Enable the UML Reused viewpoint.
     *
     * @param session
     *            Session
     */
    public static void enableReused(final Session session) {
        if (session != null) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                @Override
                protected void doExecute() {
                    final ViewpointSelectionCallback selection = new ViewpointSelectionCallback();
					UmlCoreViewpoints.fromViewpointRegistry().reused();
					for (final Viewpoint previouslySelected : session.getSelectedViewpoints(false)) {
								if (isReusedViewpoint(previouslySelected)) {
                        return;
                    }
                }
					selection.selectViewpoint(UmlCoreViewpoints.fromViewpointRegistry().reused(), session,
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
    public static UmlCoreViewpoints fromViewpointRegistry() {
        return new UmlCoreViewpoints(ViewpointRegistry.getInstance());
    }

    /**
     * Check if a viewpoint is an uml designer viewpoints.
     *
     * @param viewpoint
     * @return True if the given viewpoint is an UML Designer viewpoint
     *         otherwise false.
     */
    public static boolean isUmlViewpoint(Viewpoint viewpoint) {
        final EObject group = viewpoint.eContainer();
        if (group != null && group instanceof Group & ((Group) group).getName() != null) {
            return ((Group) group).getName().contains("UML2 modelers"); //$NON-NLS-1$
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
    public UmlCoreViewpoints(ViewpointRegistry registry) {
        this.registry = registry;
    }

    /**
     * Reused.
     *
     * @return viewpoint
     */
    public Viewpoint reused() {
        return registry.getViewpoint(URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.core.design/Reused")); //$NON-NLS-1$
    }

    /**
     * Is it the UML Reused viewpoint
     *
     * @param viewpoint
     *            viewpoint to test
     * @return true if viewpoint is the Reused viewpoint
     */
    public static boolean isReusedViewpoint(Viewpoint viewpoint) {
        if (viewpoint != null) {
            final Viewpoint reused = UmlCoreViewpoints.fromViewpointRegistry().reused();

            final String name = viewpoint.getName();
            final Resource vpResource = viewpoint.eResource();
            if (name != null && vpResource != null) {
                return name.equals(reused.getName()) && UMLDesignerCorePlugin.ODESIGN_FILE_NAME.equals(vpResource.getURI().lastSegment());
            }
        }
        return false;
    }
}
