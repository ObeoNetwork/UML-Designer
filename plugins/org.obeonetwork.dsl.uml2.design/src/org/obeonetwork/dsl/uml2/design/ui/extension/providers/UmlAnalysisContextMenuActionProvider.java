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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Element;
import org.obeonetwork.dsl.uml2.design.ui.extension.actions.DeleteUmlElementsAction;

import fr.obeo.oo1932oo.viewpoint.oo221oo.oo19oo.oo5606oo.oo1376oo.oo13598oo.oo1102oo.Oo20979oo;

@SuppressWarnings("restriction")
public class UmlAnalysisContextMenuActionProvider implements Oo20979oo {

	public Iterable<IAction> getContextMenuActions(ISelection selection) {
		List<IAction> actions = new ArrayList<IAction>();
		List<Element> elements = new ArrayList<Element>();
		if (selection instanceof IStructuredSelection) {
			for (Object selectedObject : ((IStructuredSelection) selection).toList()) {
				if (selectedObject instanceof Element) {
					elements.add((Element)selectedObject);
				}
			}
			if (!elements.isEmpty()) {
				actions.add(new DeleteUmlElementsAction(elements));
			}
		}
		return actions;
	}
}
