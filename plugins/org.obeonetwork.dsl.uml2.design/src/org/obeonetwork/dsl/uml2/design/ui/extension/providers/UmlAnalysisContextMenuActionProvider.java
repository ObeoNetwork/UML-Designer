/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.ui.extension.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.obeonetwork.dsl.uml2.design.ui.extension.actions.CreateScenarioAction;
import org.obeonetwork.dsl.uml2.design.ui.extension.actions.DeleteUmlElementsAction;

import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.business.api.session.SessionManager;
import fr.obeo.dsl.viewpoint.description.Viewpoint;
import fr.obeo.oo1932oo.viewpoint.oo221oo.oo19oo.oo5606oo.oo1376oo.oo13598oo.oo1102oo.Oo20979oo;

/**
 * UML Designer model content view context menu action provider.
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
@SuppressWarnings("restriction")
public class UmlAnalysisContextMenuActionProvider implements Oo20979oo {

	/**
	 * {@inheritDoc}
	 */
	public Iterable<IAction> getContextMenuActions(ISelection selection) {
		final List<IAction> actions = new ArrayList<IAction>();
		final List<Element> elements = new ArrayList<Element>();
		final List<Package> packages = new ArrayList<Package>();
		if (selection instanceof IStructuredSelection) {
			for (Object selectedObject : ((IStructuredSelection)selection).toList()) {
				if (selectedObject instanceof Element) {
					elements.add((Element)selectedObject);
				}
				if (selectedObject instanceof Package) {
					packages.add((Package)selectedObject);
				}
			}
			if (!elements.isEmpty()) {
				actions.add(new DeleteUmlElementsAction(elements));
			}
			if (!packages.isEmpty()
					&& behavioralViewpointExists(((IStructuredSelection)selection).getFirstElement())) {
				actions.add(new CreateScenarioAction(packages));
			}
		}
		return actions;
	}

	private boolean behavioralViewpointExists(Object selectedObject) {
		if (selectedObject instanceof Package) {
			// Get session
			Session session = SessionManager.INSTANCE.getSession((EObject)selectedObject);

			// Get selected viewpoints
			for (Viewpoint vp : session.getSelectedViewpoints()) {
				if ("UML Behavioral Modeling".equals(vp.getName()))
					return true;
			}
		}
		return false;
	}
}
