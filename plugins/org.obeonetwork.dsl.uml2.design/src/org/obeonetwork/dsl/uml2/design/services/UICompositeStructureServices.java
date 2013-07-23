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
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interface;
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
import fr.obeo.dsl.viewpoint.DEdge;
import fr.obeo.dsl.viewpoint.DNode;
import fr.obeo.dsl.viewpoint.EdgeTarget;

/**
 * A set of services to handle the UML Composite Structure diagram.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class UICompositeStructureServices {

	public static boolean isInterfaceView(EObject view) {
		return view instanceof DNode && ((DNode)view).getTarget() != null
				&& ((DNode)view).getTarget() instanceof Interface;
	}

	/**
	 * Several element views does not need to be handled in composite structure, Especially interfaces. In
	 * another hand, interface usages/interface realizations are returned.
	 * 
	 * @param views
	 *            Views to handle
	 * @return the handle result
	 */
	public static List<EObject> handleUnmeaningViews(List<EObject> views) {
		List<EObject> result = new ArrayList<EObject>();

		for (EObject view : views) {
			if (isInterfaceView(view)) {
				DNode interfaceView = (DNode)view;
				EList<DEdge> edges = interfaceView.getIncomingEdges();
				edges.addAll(interfaceView.getOutgoingEdges());
				for (DEdge edge : edges) {
					EObject target = edge.getTarget();
					if (target instanceof InterfaceRealization || target instanceof Usage) {
						result.add(edge);
					}
				}
			} else {
				result.add(view);
			}
		}

		return result;
	}

	/**
	 * Get available dependencies at the first level of the diagram.
	 * 
	 * @param diagram
	 *            The diagram
	 * @return Dependencies
	 */
	public static List<Connector> getAvailableConnectors(DDiagram diagram) {
		List<Connector> result = new ArrayList<Connector>();
		List<Dependency> availableDependencies = DependencyServices.getAvailableDependencies(diagram);
		for (Dependency dependency : availableDependencies) {
			List<NamedElement> clients = dependency.getClients();
			for (NamedElement client : clients) {
				if (client instanceof Connector) {
					if (!result.contains(client)) {
						result.add((Connector)client);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get available dependencies at the first level of the diagram.
	 * 
	 * @param diagram
	 *            The diagram
	 * @return Dependencies
	 */
	public static List<Connector> getAvailableSubConnectors(DDiagram diagram) {
		List<Connector> result = new ArrayList<Connector>();
		List<Dependency> availableDependencies = DependencyServices.getAvailableSubDependencies(diagram);
		for (Dependency dependency : availableDependencies) {
			List<NamedElement> clients = dependency.getClients();
			for (NamedElement client : clients) {
				if (client instanceof Connector) {
					if (!result.contains(client)) {
						result.add((Connector)client);
					}
				}
			}
		}
		// get connector without dependencies
		List<DDiagramElement> diagramElements = diagram.getDiagramElements();
		for (DDiagramElement dDiagramElement : diagramElements) {
			if (dDiagramElement.isVisible() && dDiagramElement instanceof DDiagramElementContainer) {
				EObject target = ((AbstractDNode)dDiagramElement).getTarget();
				if (target instanceof StructuredClassifier) {
					List<Connector> ownedConnectors = ((StructuredClassifier)target).getOwnedConnectors();
					for (Connector connector : ownedConnectors) {
						if (connector.getClientDependencies().isEmpty() && connector.getEnds().size() == 2) {
							List<ConnectorEnd> ends = connector.getEnds();
							if (ends.get(0).getRole() instanceof Property
									&& !(ends.get(0).getRole() instanceof Port)) {
								if (ends.get(1).getRole() instanceof Property
										&& !(ends.get(1).getRole() instanceof Port)) {
									if (!result.contains(connector)) {
										result.add(connector);
									}
								}
							}

						}
					}

				}
			}
		}
		return result;
	}

	/**
	 * Find provided interfaces to add.
	 * 
	 * @param view
	 *            The view context
	 * @param interfaces
	 *            selected interfaces
	 * @return the list of provided interfaces to add
	 */
	public static List<Interface> findProvidedInterfacesToAdd(EdgeTarget view, List<Interface> interfaces) {
		List<Interface> result = new ArrayList<Interface>(interfaces);
		List<DEdge> outgoingEdges = view.getOutgoingEdges();

		for (DEdge outgoingEdge : outgoingEdges) {
			EObject target = outgoingEdge.getTarget();

			if (target instanceof InterfaceRealization) {
				InterfaceRealization targetIR = (InterfaceRealization)target;
				EList<NamedElement> suppliers = targetIR.getSuppliers();
				for (NamedElement namedElement : suppliers) {
					if (namedElement instanceof Interface) {
						result.remove(namedElement);
					}

				}
			}
		}
		return result;
	}

	/**
	 * Find interface realizations to delete.
	 * 
	 * @param view
	 *            The view context
	 * @param interfaces
	 *            selected interfaces
	 * @return the list of interface realizations to delete
	 */
	public static List<InterfaceRealization> findInterfaceRealizationsToDelete(EdgeTarget view,
			List<Interface> interfaces) {
		List<InterfaceRealization> result = new ArrayList<InterfaceRealization>();
		List<DEdge> outgoingEdges = view.getOutgoingEdges();

		for (DEdge outgoingEdge : outgoingEdges) {
			EObject target = outgoingEdge.getTarget();

			if (target instanceof InterfaceRealization) {
				result.addAll(CompositeStructureServices.findInterfaceRealizationsToDelete(
						(InterfaceRealization)target, interfaces));
			}
		}
		return result;
	}

	/**
	 * find required interfaces to add.
	 * 
	 * @param view
	 *            The view context
	 * @param interfaces
	 *            selected interfaces
	 * @return the list of required interfaces to add
	 */
	public static List<Interface> findRequiredInterfacesToAdd(EdgeTarget view, List<Interface> interfaces) {
		List<Interface> result = new ArrayList<Interface>(interfaces);
		List<DEdge> incomingEdges = view.getIncomingEdges();

		for (DEdge incomingEdge : incomingEdges) {
			EObject target = incomingEdge.getTarget();

			if (target instanceof Usage) {
				Usage targetU = (Usage)target;
				EList<NamedElement> suppliers = targetU.getSuppliers();
				for (NamedElement namedElement : suppliers) {
					if (namedElement instanceof Interface) {
						result.remove(namedElement);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Find usages to delete.
	 * 
	 * @param view
	 *            The view context
	 * @param interfaces
	 *            selected interfaces
	 * @return the list of usages to delete
	 */
	public static List<Usage> findUsagesToDelete(EdgeTarget view, List<Interface> interfaces) {
		List<Usage> result = new ArrayList<Usage>();
		List<DEdge> incomingEdges = view.getIncomingEdges();

		for (DEdge incomingEdge : incomingEdges) {
			EObject target = incomingEdge.getTarget();

			if (target instanceof Usage) {
				result.addAll(CompositeStructureServices.findUsagesToDelete((Usage)target, interfaces));
			}
		}
		return result;
	}
}
