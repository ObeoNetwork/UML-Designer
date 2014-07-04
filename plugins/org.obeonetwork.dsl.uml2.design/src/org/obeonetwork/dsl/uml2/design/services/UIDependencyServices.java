/*******************************************************************************
 * Copyright (c) 2009, 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;

/**
 * A set of services to handle graphically Dependency actions and tests.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class UIDependencyServices {

	/**
	 * Get available dependencies at the first level of the diagram.
	 * 
	 * @param diagram
	 *            The diagram
	 * @return Dependencies
	 */
	public List<Dependency> getAvailableDependencies(DDiagram diagram) {
		List<Dependency> result = new ArrayList<Dependency>();
		List<DDiagramElement> ownedDiagramElements = diagram.getOwnedDiagramElements();
		for (DDiagramElement dDiagramElement : ownedDiagramElements) {
			if (dDiagramElement.isVisible() && dDiagramElement instanceof DNodeContainer) {
				EObject target = ((DNodeContainer)dDiagramElement).getTarget();
				if (target instanceof StructuredClassifier) {
					result.addAll(new DependencyServices()
							.getAvailableDependencies((StructuredClassifier)target));
				}
			}
		}
		return result;
	}

	/**
	 * Get available dependencies at the current level of the dNode.
	 * 
	 * @param viewContext
	 *            The view context
	 * @return Dependencies
	 */
	public List<Dependency> getAvailableSubDependencies(EObject viewContext) {
		List<Dependency> result = new ArrayList<Dependency>();
		if (viewContext instanceof DDiagramElementContainer) {
			DDiagramElementContainer dDiagramElement = (DDiagramElementContainer)viewContext;
			if (dDiagramElement.isVisible()) {
				List<DDiagramElement> subDiagramElements = dDiagramElement.getElements();
				for (DDiagramElement subDiagramElement : subDiagramElements) {
					if (subDiagramElement.isVisible() && subDiagramElement instanceof AbstractDNode) {
						EObject target = ((AbstractDNode)subDiagramElement).getTarget();
						if (target instanceof StructuredClassifier) {
							result.addAll(new DependencyServices()
									.getAvailableDependencies((StructuredClassifier)target));
						} else if (target instanceof Property && !(target instanceof Port)) {
							result.addAll(((Property)target).getClientDependencies());
						}
					}
				}
			}
		} else if (viewContext instanceof DDiagram) {
			List<DDiagramElement> subDiagramElements = ((DDiagram)viewContext).getDiagramElements();
			for (DDiagramElement dDiagramElement : subDiagramElements) {
				result.addAll(getAvailableSubDependencies(dDiagramElement));
			}
		}

		return result;
	}

	/**
	 * Test if a given couple of source/target is valid to display for a dependency.
	 * 
	 * @param source
	 *            the source
	 * @param sourceView
	 *            the source view
	 * @param target
	 *            the target
	 * @param targetView
	 *            the target view
	 * @return true if valid to display
	 */
	public boolean validSourceTarget4Dependency(Element source, DSemanticDecorator sourceView,
			Element target, DSemanticDecorator targetView) {
		boolean result = false;

		if (source instanceof org.eclipse.uml2.uml.Class) {
			result = targetView.eContainer().equals(sourceView.eContainer());
		} else if (source instanceof Port) {
			result = targetView.eContainer().equals(sourceView.eContainer().eContainer());
		}
		return result;
	}
}
