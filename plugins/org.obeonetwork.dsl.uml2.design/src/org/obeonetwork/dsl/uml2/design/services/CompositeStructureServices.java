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
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.Usage;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

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
public class CompositeStructureServices {

	private static final String NOT_HANDLED = ") not handled";

	/**
	 * Logger.
	 */
	private LogServices logger = new LogServices();

	private DependencyServices dependencyServices = new DependencyServices();

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
		List<Dependency> availableDependencies = dependencyServices.getAvailableDependencies(diagram);
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
	public List<Connector> getAvailableSubConnectors(DDiagram diagram) {
		List<Connector> result = new ArrayList<Connector>();
		List<Dependency> availableDependencies = dependencyServices.getAvailableSubDependencies(diagram);
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
							+ object.getClass() + NOT_HANDLED, null);
		}
		return result;
	}

	/**
	 * Create an usage.
	 * 
	 * @param context
	 *            the context to create the an usage
	 * @param contract
	 *            the contract to respect
	 * @return the new usage
	 */
	public Usage createHelperUsage(EObject context, Interface contract) {
		Usage result = null;
		if (context instanceof Property) {
			final Property property = (Property)context;
			boolean isPortWithValidType = false;
			if (context instanceof Port && ((Port)context).isConjugated()) {
				final Port port = (Port)property;
				// create InterfaceRealization on the type
				Type type = port.getType();
				if (type instanceof NamedElement) {
					isPortWithValidType = true;
					NamedElement namedElement = (NamedElement)type;
					result = namedElement.createUsage(contract);
					result.setName(genDependencyName(contract, namedElement));
					result.getClients().add(port);
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
			logger.error("CompositeStructureServices.createUsage(" + context.getClass() + NOT_HANDLED, null);
		}

		return result;
	}

	/**
	 * Create an interface realization.
	 * 
	 * @param context
	 *            the context to create the an interface realization
	 * @param contract
	 *            the contract to respect
	 * @return the new interface realization
	 */
	public InterfaceRealization createHelperInterfaceRealization(EObject context, Interface contract) {
		InterfaceRealization result = null;

		if (context instanceof Property) {
			final Property property = (Property)context;
			boolean isPortWithValidType = false;
			if (context instanceof Port && ((Port)context).isConjugated()) {
				final Port port = (Port)property;
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
					+ NOT_HANDLED, null);
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
	 * return only the required client to ui needs.
	 * 
	 * @param dependency
	 *            the dependency context
	 * @return needed clients to handle is the diagram ui
	 */
	public List<NamedElement> getClient(Dependency dependency) {
		List<NamedElement> result = new ArrayList<NamedElement>();
		List<NamedElement> clients = Lists.newArrayList(Iterables.filter(dependency.getClients(),
				new Predicate<EObject>() {
					public boolean apply(EObject input) {
						return !(input instanceof Connector);
					}
				}));

		if (clients.size() == 1) {
			result.addAll(clients);
		} else if (clients.size() > 0) {

			for (NamedElement client : clients) {
				if (client instanceof Property) {
					Property property = (Property)client;
					if (property instanceof Port) {
						Port port = (Port)property;
						if (port.getType() != null) {
							if (port.eContainer() instanceof StructuredClassifier
									&& port.getType().equals(port.eContainer())) {
								// when the port type is the container type, add directly the container.
								result.add(port.getType());
							} else if (port.isConjugated()) {
								// if conjugated add the port type and the port.
								result.add(port);
								result.add(port.getType());
							} else {
								// Else add the port
								result.add(port);
							}
						} else {
							// Else add the port
							result.add(port);
						}
					} else {
						// Else add the property
						result.add(property);
					}
				}
			}
		}
		return result;
	}

	private static boolean isInterfaceView(EObject view) {
		return view instanceof DNode && ((DNode)view).getTarget() != null
				&& ((DNode)view).getTarget() instanceof Interface;
	}
}
