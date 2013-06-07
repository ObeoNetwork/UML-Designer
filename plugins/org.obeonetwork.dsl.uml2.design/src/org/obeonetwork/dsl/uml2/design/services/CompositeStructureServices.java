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
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Type;
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
	 * Logger.
	 */
	private LogServices logger = new LogServices();

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
	 * Get available dependencies at the first level of the diagram.
	 * 
	 * @param diagram
	 *            The diagram
	 * @return Dependencies
	 */
	public List<Connector> getAvailableConnectors(DDiagram diagram) {
		List<Connector> result = new ArrayList<Connector>();
		List<Dependency> availableDependencies = getAvailableDependencies(diagram);
		for (Dependency dependency : availableDependencies) {
			List<NamedElement> clients = dependency.getClients();
			for (NamedElement client : clients) {
				if (client instanceof Connector) {
					result.add((Connector)client);
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
	public List<Connector> getAvailableSubConnectors(DDiagram diagram) {
		List<Connector> result = new ArrayList<Connector>();
		List<Dependency> availableDependencies = getAvailableSubDependencies(diagram);
		for (Dependency dependency : availableDependencies) {
			List<NamedElement> clients = dependency.getClients();
			for (NamedElement client : clients) {
				if (client instanceof Connector) {
					result.add((Connector)client);
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
	 * @param diagram
	 *            The diagram view
	 * @return Dependencies
	 */
	public List<Dependency> getAvailableSubDependencies(DDiagram diagram) {
		List<Dependency> result = new ArrayList<Dependency>();

		List<DDiagramElement> diagramElements = diagram.getDiagramElements();
		for (DDiagramElement dDiagramElement : diagramElements) {
			if (dDiagramElement.isVisible() && dDiagramElement instanceof DDiagramElementContainer) {
				List<DDiagramElement> subDiagramElements = ((DDiagramElementContainer)dDiagramElement)
						.getElements();
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
		}
		return result;
	}

	private List<Dependency> getAvailableDependencies(StructuredClassifier structuredClassifier) {
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

	public List<EObject> getWizardServiceCandidateExpression(EObject object) {
		List<EObject> result = new ArrayList<EObject>();
		UMLServices service = new UMLServices();
		List<EObject> validsForCompositeDiagram = service.getValidsForCompositeDiagram(object);
		for (EObject eObject : validsForCompositeDiagram) {
			if (eObject instanceof Classifier || eObject instanceof org.eclipse.uml2.uml.Package) {
				result.add(eObject);
			}
		}

		return result;
	}

	public List<EObject> getWizardServiceChildren(EObject object) {
		List<EObject> result = new ArrayList<EObject>();
		EList<EObject> eContents = object.eContents();
		for (EObject eObject : eContents) {
			if (eObject instanceof Classifier || eObject instanceof org.eclipse.uml2.uml.Package) {
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

	public List<EObject> getWizardRequiredServiceSelectedCandidates(EObject object) {
		List<EObject> result = new ArrayList<EObject>();

		if (object instanceof Property) {
			final Property property = (Property)object;
			if (property instanceof Port) {
				final Port port = (Port)property;
				if (port.isConjugated()) {
					// handles required interfaces that come from the type
					result.addAll(port.getRequireds());
				}
			}
			// Handles required interfaces that come from InterfaceRealizations
			List<Dependency> clientDependencies = property.getClientDependencies();
			for (Dependency dependency : clientDependencies) {
				if (dependency instanceof Usage) {
					result.addAll(dependency.getSuppliers());
				}
			}
		} else if (object instanceof BehavioredClassifier) {
			List<Dependency> dependencies = ((BehavioredClassifier)object).getClientDependencies();
			// Get only Usage that does not come from a property
			for (Dependency dependency : dependencies) {
				if (dependency instanceof Usage) {
					boolean isCommingFromAProperty = false;
					List<NamedElement> clients = dependency.getClients();
					for (NamedElement client : clients) {
						if (client instanceof Property) {
							isCommingFromAProperty = true;
							break;
						}
					}
					if (!isCommingFromAProperty) {
						result.addAll(dependency.getSuppliers());
					}
				}
			}
		}
		return result;
	}

	public List<EObject> getWizardProvidedServiceSelectedCandidates(EObject object) {
		List<EObject> result = new ArrayList<EObject>();

		if (object instanceof Property) {
			final Property property = (Property)object;
			if (property instanceof Port) {
				final Port port = (Port)property;
				if (port.isConjugated()) {
					// handles provided interfaces that come from the type
					result.addAll(port.getProvideds());
				}
			}
			// Handles provided interfaces that come from InterfaceRealizations
			List<Dependency> clientDependencies = property.getClientDependencies();
			for (Dependency dependency : clientDependencies) {
				if (dependency instanceof InterfaceRealization) {
					result.addAll(dependency.getSuppliers());
				}
			}
		} else if (object instanceof BehavioredClassifier) {
			List<InterfaceRealization> interfaceRealizations = ((BehavioredClassifier)object)
					.getInterfaceRealizations();
			// Get only InterfaceRealizations that does not come from a property
			for (InterfaceRealization interfaceRealization : interfaceRealizations) {

				boolean isCommingFromAProperty = false;
				List<NamedElement> clients = interfaceRealization.getClients();
				for (NamedElement client : clients) {
					if (client instanceof Property) {
						isCommingFromAProperty = true;
						break;
					}
				}
				if (!isCommingFromAProperty) {
					result.addAll(interfaceRealization.getSuppliers());
				}
			}
		} else {
			logger.error(
					"CompositeStructureServices.getWizardProvidedServiceSelectedCandidates("
							+ object.getClass() + ") not handled", null);
		}
		return result;
	}

	public Usage createUsage(EObject context, Interface contract) {
		Usage result = null;
		if (context instanceof Property) {
			final Property property = (Property)context;
			boolean isPortWithValidType = false;
			if (context instanceof Port) {
				final Port port = (Port)property;
				// TODO HMA handles this case graphically
				if (false) {
					if (port.isConjugated()) {
						// create InterfaceRealization on the type
						Type type = port.getType();
						if (type instanceof NamedElement) {
							isPortWithValidType = true;
							NamedElement namedElement = (NamedElement)type;
							result = namedElement.createUsage(contract);
							result.setName(genDependencyName(contract, namedElement));
						}
					}
				}
			}
			if (!isPortWithValidType) {
				EObject eContainer = context.eContainer();
				if (eContainer instanceof NamedElement) {
					NamedElement namedElement = (NamedElement)eContainer;
					result = namedElement.createUsage(contract);
					result.setName(genDependencyName(contract, property));
					result.getClients().add(property);
				}
			}
		} else if (context instanceof NamedElement) {
			NamedElement namedElement = (NamedElement)context;
			result = namedElement.createUsage(contract);
			result.setName(genDependencyName(contract, namedElement));
		} else {
			logger.error("CompositeStructureServices.createUsage(" + context.getClass() + ") not handled",
					null);
		}

		return result;
	}

	public InterfaceRealization createInterfaceRealization(EObject context, Interface contract) {
		InterfaceRealization result = null;

		if (context instanceof Property) {
			final Property property = (Property)context;
			boolean isPortWithValidType = false;
			if (context instanceof Port) {
				final Port port = (Port)property;
				// TODO HMA handles this case graphically
				if (false) {
					if (port.isConjugated()) {
						// create InterfaceRealization on the type
						Type type = port.getType();
						if (type instanceof BehavioredClassifier) {
							isPortWithValidType = true;
							BehavioredClassifier behavioredClassifier = (BehavioredClassifier)type;
							result = behavioredClassifier.createInterfaceRealization(
									genDependencyName(behavioredClassifier, contract), contract);
							result.getClients().add(port);
						}
					}
				}
			}
			if (!isPortWithValidType) {
				EObject eContainer = context.eContainer();
				if (eContainer instanceof BehavioredClassifier) {
					BehavioredClassifier behavioredClassifier = (BehavioredClassifier)eContainer;
					result = behavioredClassifier.createInterfaceRealization(
							genDependencyName(property, contract), contract);
					result.getClients().add(property);
				}
			}
		} else if (context instanceof BehavioredClassifier) {
			BehavioredClassifier behavioredClassifier = (BehavioredClassifier)context;
			result = behavioredClassifier.createInterfaceRealization(
					genDependencyName(behavioredClassifier, contract), contract);
		} else {
			logger.error("CompositeStructureServices.createInterfaceRealization(" + context.getClass()
					+ ") not handled", null);
		}

		return result;
	}

	/**
	 * Generate a dependency label.
	 * 
	 * @param source
	 *            the source
	 * @param target
	 *            the target
	 * @return a dependency label
	 */
	public String genDependencyName(NamedElement source, NamedElement target) {
		return source.getName() + "To" + target.getName();
	}

	/**
	 * To avoid duplicate case Port to interface and Class/Component to interface we provide this service that
	 * return only one client by dependency. We have a priority on port.
	 * 
	 * @param dependency
	 *            the dependency
	 * @return the wanted client to handle is the diagram
	 */
	public NamedElement getClient(Dependency dependency) {
		NamedElement result = null;
		List<NamedElement> clients = dependency.getClients();
		if (clients.size() > 0) {
			result = clients.get(0);
			if (clients.size() > 1) {
				for (NamedElement client : clients) {
					if (client instanceof Property) {
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
