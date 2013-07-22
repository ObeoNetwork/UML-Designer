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
import org.eclipse.uml2.uml.BehavioredClassifier;
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

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

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

	private static final String NOT_HANDLED = ") not handled";

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
			new LogServices().error(
					"CompositeStructureServices.createInterfaceRealization(" + context.getClass()
							+ NOT_HANDLED, null);
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
			new LogServices().error("CompositeStructureServices.createUsage(" + context.getClass()
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
	public static String genDependencyName(NamedElement source, NamedElement target) {
		return source.getName() + "To" + target.getName();
	}

	private static boolean isHandled(Dependency dependency) {
		return dependency instanceof Usage || dependency instanceof InterfaceRealization;
	}

	/**
	 * To avoid duplicate case Port to interface and Class/Component to interface we provide this service that
	 * return only the required client to ui needs.
	 * 
	 * @param dependency
	 *            the dependency context
	 * @return needed clients to handle is the diagram ui
	 */
	public static List<NamedElement> getClient(Dependency dependency) {
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

}
