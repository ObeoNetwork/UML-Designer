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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
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
import fr.obeo.dsl.viewpoint.DNodeContainer;
import fr.obeo.dsl.viewpoint.EdgeTarget;

/**
 * A set of services to handle the UML Composite Structure diagram.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class CompositeStructureServices {

	/**
	 * Find provided interfaces to add.
	 * 
	 * @param view
	 *            The view context
	 * @param interfaces
	 *            selected interfaces
	 * @return the list of provided interfaces to add
	 */
	public List<Interface> findProvidedInterfacesToAdd(EdgeTarget view, List<Interface> interfaces) {
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
	public List<InterfaceRealization> findInterfaceRealizationsToDelete(EdgeTarget view,
			List<Interface> interfaces) {
		List<InterfaceRealization> result = new ArrayList<InterfaceRealization>();
		List<DEdge> outgoingEdges = view.getOutgoingEdges();

		for (DEdge outgoingEdge : outgoingEdges) {
			EObject target = outgoingEdge.getTarget();

			if (target instanceof InterfaceRealization) {
				result.addAll(findInterfaceRealizationsToDelete((InterfaceRealization)target, interfaces));
			}
		}
		return result;
	}

	private List<InterfaceRealization> findInterfaceRealizationsToDelete(
			InterfaceRealization interfaceRealization, List<Interface> interfaces) {
		List<InterfaceRealization> result = new ArrayList<InterfaceRealization>();

		EList<NamedElement> suppliers = interfaceRealization.getSuppliers();
		for (NamedElement namedElement : suppliers) {
			if (namedElement instanceof Interface) {
				Interface anInterface = (Interface)namedElement;
				if (!interfaces.contains(anInterface)) {
					result.add(interfaceRealization);
					break;
				}
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
	public List<Interface> findRequiredInterfacesToAdd(EdgeTarget view, List<Interface> interfaces) {
		List<Interface> result = new ArrayList<Interface>(interfaces);
		List<DEdge> incomingEdges = view.getIncomingEdges();

		for (DEdge incomingEdge : incomingEdges) {
			EObject target = (incomingEdge).getTarget();

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
	public List<Usage> findUsagesToDelete(EdgeTarget view, List<Interface> interfaces) {
		List<Usage> result = new ArrayList<Usage>();
		List<DEdge> incomingEdges = view.getIncomingEdges();

		for (DEdge incomingEdge : incomingEdges) {
			EObject target = incomingEdge.getTarget();

			if (target instanceof Usage) {
				result.addAll(findUsagesToDelete((Usage)target, interfaces));
			}
		}
		return result;
	}

	private List<Usage> findUsagesToDelete(Usage usage, List<Interface> interfaces) {
		List<Usage> result = new ArrayList<Usage>();
		List<NamedElement> suppliers = usage.getSuppliers();
		for (NamedElement namedElement : suppliers) {
			if (namedElement instanceof Interface) {
				Interface anInterface = (Interface)namedElement;
				if (!interfaces.contains(anInterface)) {
					result.add(usage);
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Several element views does not need to be handled in composite structure, Especially interfaces. In
	 * another hand, interface usages/interface realizations are returned.
	 * 
	 * @param views
	 *            Views to handle
	 * @return the handle result
	 */
	public List<EObject> handleUnmeaningViews(List<EObject> views) {
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
	 * Get available provided interfaces at the first level of the diagram.
	 * 
	 * @param diagram
	 *            The diagram
	 * @return Dependencies
	 */
	public List<Interface> getAvailableProvidedInterfaces(DDiagram diagram) {
		List<Interface> result = new ArrayList<Interface>();
		List<Dependency> availableDependencyViews = getAvailableDependencies(diagram);
		for (Dependency dependency : availableDependencyViews) {
			if (dependency instanceof InterfaceRealization) {
				List<NamedElement> suppliers = dependency.getSuppliers();
				for (NamedElement supplier : suppliers) {
					if (supplier instanceof Interface) {
						result.add((Interface)supplier);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get available required interfaces at the first level of the diagram.
	 * 
	 * @param diagram
	 *            The diagram
	 * @return Dependencies
	 */
	public List<Interface> getAvailableRequiredInterfaces(DDiagram diagram) {
		List<Interface> result = new ArrayList<Interface>();
		List<Dependency> availableDependencyViews = getAvailableDependencies(diagram);
		for (Dependency dependency : availableDependencyViews) {
			if (dependency instanceof Usage) {
				List<NamedElement> suppliers = dependency.getSuppliers();
				for (NamedElement supplier : suppliers) {
					if (supplier instanceof Interface) {
						result.add((Interface)supplier);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get available provided dependencies at the current level of the container.
	 * 
	 * @param container
	 *            The current view container
	 * @return Dependencies
	 */
	public List<Interface> getAvailableProvidedInterfaces(DDiagramElementContainer container) {
		List<Interface> result = new ArrayList<Interface>();

		EList<DDiagramElement> elements = container.getElements();
		for (DDiagramElement dDiagramElement : elements) {

			if (dDiagramElement.getTarget() instanceof StructuredClassifier) {
				List<Dependency> availableDependencyViews = getAvailableDependencies((StructuredClassifier)dDiagramElement
						.getTarget());
				for (Dependency dependency : availableDependencyViews) {
					if (dependency instanceof InterfaceRealization) {
						List<NamedElement> suppliers = dependency.getSuppliers();
						for (NamedElement supplier : suppliers) {
							if (supplier instanceof Interface) {
								result.add((Interface)supplier);
							}
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get available required dependencies at the current level of the container.
	 * 
	 * @param container
	 *            The current view container
	 * @return Dependencies
	 */
	public List<Interface> getAvailableRequiredInterfaces(DDiagramElementContainer container) {
		List<Interface> result = new ArrayList<Interface>();

		EList<DDiagramElement> elements = container.getElements();
		for (DDiagramElement dDiagramElement : elements) {

			if (dDiagramElement.getTarget() instanceof StructuredClassifier) {
				List<Dependency> availableDependencyViews = getAvailableDependencies((StructuredClassifier)dDiagramElement
						.getTarget());
				for (Dependency dependency : availableDependencyViews) {
					if (dependency instanceof Usage) {
						List<NamedElement> suppliers = dependency.getSuppliers();
						for (NamedElement supplier : suppliers) {
							if (supplier instanceof Interface) {
								result.add((Interface)supplier);
							}
						}
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
	public List<Dependency> getAvailableDependencies(DDiagram diagram) {
		List<Dependency> result = new ArrayList<Dependency>();
		List<DDiagramElement> ownedDiagramElements = diagram.getOwnedDiagramElements();
		for (DDiagramElement dDiagramElement : ownedDiagramElements) {
			if (dDiagramElement instanceof DNodeContainer) {
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
	 * @param dNode
	 *            The current view item
	 * @return Dependencies
	 */
	public List<Dependency> getAvailableSubDependencies(DDiagram diagram) {
		List<Dependency> result = new ArrayList<Dependency>();

		List<DDiagramElement> diagramElements = diagram.getDiagramElements();
		for (DDiagramElement dDiagramElement : diagramElements) {
			if (dDiagramElement instanceof AbstractDNode) {
				EObject target = ((AbstractDNode)dDiagramElement).getTarget();
				if (target instanceof StructuredClassifier) {
					result.addAll(getAvailableDependencies((StructuredClassifier)target));
				}
			}
		}
		return result;
	}

	public List<Dependency> getAvailableDependencies(StructuredClassifier structuredClassifier) {
		List<Dependency> result = new ArrayList<Dependency>();
		// find interesting dependencies
		List<Dependency> clientDependencies = structuredClassifier.getClientDependencies();
		List<Property> ownedAttributes = structuredClassifier.getOwnedAttributes();
		for (Property property : ownedAttributes) {
			if (property instanceof Port) {
				List<Dependency> portClientDependencies = property.getClientDependencies();
				clientDependencies.addAll(portClientDependencies);
			}
		}

		// handle dependencies
		for (Dependency dependency : clientDependencies) {
			if (isHandled(dependency)) {
				result.add(dependency);
			}
		}

		return result;
	}

	public List<EObject> getWizardInterfaceCandidateExpression(EObject object) {
		List<EObject> result = new ArrayList<EObject>();
		UMLServices service = new UMLServices();
		List<EObject> validsForCompositeDiagram = service.getValidsForCompositeDiagram(object);
		for (EObject eObject : validsForCompositeDiagram) {
			if (eObject instanceof Classifier || eObject instanceof Model) {
				result.add(eObject);
			}
		}

		return result;
	}

	public List<EObject> getWizardInterfaceChildren(EObject object) {
		List<EObject> result = new ArrayList<EObject>();
		EList<EObject> eContents = object.eContents();
		for (EObject eObject : eContents) {
			if (eObject instanceof Classifier || eObject instanceof Model) {
				if (eObject instanceof Interface) {
					result.add(eObject);
				} else {
					for (Iterator<EObject> iterator = eObject.eAllContents(); iterator.hasNext();) {
						EObject eSubObject = (EObject)iterator.next();
						if (eSubObject instanceof Interface) {
							result.add(eObject);
							break;
						}

					}
				}
			}
		}
		return result;
	}

	public InterfaceRealization createInterfaceRealization(NamedElement context, Interface contract) {
		InterfaceRealization result = null;
		if (context instanceof BehavioredClassifier) {
			BehavioredClassifier behavioredClassifier = (BehavioredClassifier)context;
			result = behavioredClassifier.createInterfaceRealization(genDependencyName(context, contract),
					contract);
		}

		return result;
	}

	public String genDependencyName(NamedElement source, NamedElement target) {
		return source.getName() + "To" + target.getName();
	}

	/**
	 * To avoid duplicate case Port to interface and Class/Component to interface we provide this service that
	 * return only one client by dependency. We have a priority on port.
	 * 
	 * @param dependency
	 * @return
	 */
	public NamedElement getClient(Dependency dependency) {
		NamedElement result = null;
		List<NamedElement> clients = dependency.getClients();
		if (clients.size() > 0) {
			result = clients.get(0);
			if (clients.size() > 1) {
				for (NamedElement client : clients) {
					if (client instanceof Port) {
						result = client;
						break;
					}
				}
			}
		}

		return result;
	}

	private boolean isInterfaceView(EObject view) {
		return view instanceof DNode && ((DNode)view).getTarget() != null
				&& ((DNode)view).getTarget() instanceof Interface;
	}

	private boolean isHandled(Dependency dependency) {
		return dependency instanceof Usage || dependency instanceof InterfaceRealization;
	}
}
