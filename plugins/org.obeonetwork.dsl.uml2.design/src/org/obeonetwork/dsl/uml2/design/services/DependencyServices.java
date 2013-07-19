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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Usage;

import fr.obeo.dsl.viewpoint.AbstractDNode;
import fr.obeo.dsl.viewpoint.DDiagram;
import fr.obeo.dsl.viewpoint.DDiagramElement;
import fr.obeo.dsl.viewpoint.DDiagramElementContainer;
import fr.obeo.dsl.viewpoint.DNodeContainer;

/**
 * Utility services to manage operation creation.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class DependencyServices {

	/**
	 * Remove remove a specific supplier in this dependency.
	 * 
	 * @param aDependency
	 *            the dependency context
	 * @param supplier
	 *            the supplier to remove
	 */
	public static void removeSupplier(org.eclipse.uml2.uml.Dependency aDependency,
			org.eclipse.uml2.uml.NamedElement supplier) {

		EList<NamedElement> suppliers = aDependency.getSuppliers();
		suppliers.remove(supplier);
	}

	/**
	 * Get available dependencies at the first level of the diagram.
	 * 
	 * @param diagram
	 *            The diagram
	 * @return Dependencies
	 */
	public static List<Dependency> getAvailableDependencies(DDiagram diagram) {
		List<Dependency> result = new ArrayList<Dependency>();
		List<DDiagramElement> ownedDiagramElements = diagram.getOwnedDiagramElements();
		for (DDiagramElement dDiagramElement : ownedDiagramElements) {
			if (dDiagramElement.isVisible() && dDiagramElement instanceof DNodeContainer) {
				EObject target = ((DNodeContainer)dDiagramElement).getTarget();
				if (target instanceof StructuredClassifier) {
					result.addAll(getAvailableDependencies((StructuredClassifier)target));
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
	public static List<Dependency> getAvailableSubDependencies(EObject viewContext) {
		List<Dependency> result = new ArrayList<Dependency>();
		if (viewContext instanceof DDiagramElementContainer) {
			DDiagramElementContainer dDiagramElement = (DDiagramElementContainer)viewContext;
			if (dDiagramElement.isVisible()) {
				List<DDiagramElement> subDiagramElements = (dDiagramElement).getElements();
				for (DDiagramElement subDiagramElement : subDiagramElements) {
					if (subDiagramElement.isVisible() && subDiagramElement instanceof AbstractDNode) {
						EObject target = ((AbstractDNode)subDiagramElement).getTarget();
						if (target instanceof StructuredClassifier) {
							result.addAll(getAvailableDependencies((StructuredClassifier)target));
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

	public static List<Dependency> getAvailableDependencies(StructuredClassifier structuredClassifier) {
		List<Dependency> result = new ArrayList<Dependency>();
		// find interesting dependencies
		List<Dependency> clientDependencies = structuredClassifier.getClientDependencies();
		List<Property> ownedAttributes = structuredClassifier.getOwnedAttributes();
		for (Property property : ownedAttributes) {
			if (property instanceof Port) {
				Port port = (Port)property;
				List<Dependency> portClientDependencies = port.getClientDependencies();
				clientDependencies.addAll(portClientDependencies);
				if (port.isConjugated()) {
					clientDependencies.addAll(port.getType().getClientDependencies());
				}
			}
		}

		// handle dependencies
		for (Dependency dependency : clientDependencies) {
			boolean isCommingFromAProperty = false;
			List<NamedElement> clients = dependency.getClients();
			for (NamedElement client : clients) {
				if (client instanceof Property && !(client instanceof Port)) {
					isCommingFromAProperty = true;
					break;
				}
			}

			if (isHandled(dependency) && !isCommingFromAProperty) {
				result.add(dependency);
			}
		}

		return result;
	}

	private static boolean isHandled(Dependency dependency) {
		return dependency instanceof Usage || dependency instanceof InterfaceRealization;
	}

}
