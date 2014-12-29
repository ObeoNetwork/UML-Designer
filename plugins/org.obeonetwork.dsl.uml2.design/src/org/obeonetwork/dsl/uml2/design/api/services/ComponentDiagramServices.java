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
package org.obeonetwork.dsl.uml2.design.api.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ComponentRealization;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.Usage;
import org.obeonetwork.dsl.uml2.design.internal.services.ConnectorServices;
import org.obeonetwork.dsl.uml2.design.internal.services.ElementServices;
import org.obeonetwork.dsl.uml2.design.internal.services.LogServices;
import org.obeonetwork.dsl.uml2.design.internal.services.NodeInverseRefsServices;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A set of services to handle the Component diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ComponentDiagramServices extends AbstractDiagramServices {
	/**
	 * Create a component realization.
	 *
	 * @param realizingClassifier
	 *            Classifier that realizes the component
	 * @param abstraction
	 *            the component realized
	 * @return the component realization
	 */
	public ComponentRealization createComponentRealization(Classifier realizingClassifier,
			Component abstraction) {
		// The name is computed by the item provider.
		final ComponentRealization result = abstraction.createRealization(null);
		result.getRealizingClassifiers().add(realizingClassifier);
		return result;
	}

	/**
	 * Create a new named connector.
	 *
	 * @param structuredClassifier
	 *            the connector container
	 * @param elem1
	 *            the first element
	 * @param elem2
	 *            the second element
	 * @return a new named connector
	 */
	public Connector createConnector(StructuredClassifier structuredClassifier, NamedElement elem1,
			NamedElement elem2) {
		return ConnectorServices.INSTANCE.createConnector(structuredClassifier, elem1, elem2);
	}

	/**
	 * Boolean query for create Dependency Connection Complete Precondition.
	 *
	 * @param preSource
	 *            the source
	 * @param preSourceView
	 *            the source view
	 * @param preTarget
	 *            the target
	 * @param preTargetView
	 *            the target view
	 * @param container
	 *            the container
	 * @param diagram
	 *            the diagram
	 * @return true if valid source and target
	 */
	public boolean createDependencyConnectionCompletePrecondition(Element preSource,
			DSemanticDecorator preSourceView, Element preTarget, DSemanticDecorator preTargetView,
			Element container, DSemanticDiagram diagram) {

		final boolean fromInterfaceToClassOrPort = preSource instanceof org.eclipse.uml2.uml.Interface
				&& (preTarget instanceof org.eclipse.uml2.uml.Class || preTarget instanceof Port);

		final boolean fromClassOrPortToInterface = (preSource instanceof org.eclipse.uml2.uml.Class || preSource instanceof Port)
				&& preTarget instanceof org.eclipse.uml2.uml.Interface;

		return fromInterfaceToClassOrPort || fromClassOrPortToInterface;
	}

	/**
	 * Boolean query for create Dependency Connection Start Precondition.
	 *
	 * @param preSource
	 *            the source
	 * @param preSourceView
	 *            the source view
	 * @param container
	 *            the container
	 * @param diagram
	 *            the diagram
	 * @return true if valid source
	 */
	public boolean createDependencyConnectionStartPrecondition(Element preSource,
			DSemanticDecorator preSourceView, Element container, DSemanticDiagram diagram) {
		final boolean fromClassOrPort = preSource instanceof org.eclipse.uml2.uml.Class
				|| preSource instanceof Port;
		final boolean fromInterface = preSource instanceof org.eclipse.uml2.uml.Interface;
		return fromClassOrPort || fromInterface;
	}

	/**
	 * Create an interface realization.
	 *
	 * @param context
	 *            the context to create the interface realization. It can be a Property, a Port or a
	 *            BehavioredClassifier.
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
				final Type type = port.getType();
				if (type instanceof BehavioredClassifier) {
					isPortWithValidType = true;
					final BehavioredClassifier behavioredClassifier = (BehavioredClassifier)type;
					// The name is provided by the item provider.
					result = behavioredClassifier.createInterfaceRealization(null, contract);
					result.getClients().add(port);
				}
			}
			if (!isPortWithValidType) {
				final EObject eContainer = context.eContainer();
				if (eContainer instanceof BehavioredClassifier) {
					final BehavioredClassifier behavioredClassifier = (BehavioredClassifier)eContainer;
					// The name is provided by the item provider.
					result = behavioredClassifier.createInterfaceRealization(null, contract);
					result.getClients().add(property);
				}
			}
		} else if (context instanceof BehavioredClassifier) {
			final BehavioredClassifier behavioredClassifier = (BehavioredClassifier)context;
			// The name is computed by the item provider.
			result = behavioredClassifier.createInterfaceRealization(null, contract);
		} else {
			LogServices.INSTANCE.error(
					"CompositeStructureServices.createInterfaceRealization(" + context.getClass() //$NON-NLS-1$
					+ ") not handled", null); //$NON-NLS-1$
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
				final Type type = port.getType();
				isPortWithValidType = true;
				final NamedElement namedElement = type;
				result = namedElement.createUsage(contract);
				// The name is provided by the item provider.
				result.getClients().add(port);
			}
			if (!isPortWithValidType) {
				final EObject eContainer = context.eContainer();
				if (eContainer instanceof NamedElement) {
					final NamedElement namedElement = (NamedElement)eContainer;
					result = namedElement.createUsage(contract);
					// The name is provided by the item provider.
					result.getClients().add(property);
				}
			}
		} else if (context instanceof NamedElement) {
			final NamedElement namedElement = (NamedElement)context;
			result = namedElement.createUsage(contract);
			// The name is provided by the item provider.
		} else {
			LogServices.INSTANCE.error("CompositeStructureServices.createUsage(" + context.getClass() //$NON-NLS-1$
					+ ") not handled", null); //$NON-NLS-1$
		}

		return result;
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
		final List<NamedElement> result = new ArrayList<NamedElement>();
		final List<NamedElement> clients = Lists.newArrayList(Iterables.filter(dependency.getClients(),
				new Predicate<EObject>() {
			public boolean apply(EObject input) {
				return !(input instanceof Connector);
			}
		}));
		if (clients.size() == 1) {
			result.addAll(clients);
			return result;
		}
		for (final NamedElement client : clients) {
			if (client instanceof Property) {
				final Property property = (Property)client;
				if (property instanceof Port) {
					final Port port = (Port)property;
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
		return result;
	}

	/**
	 * Retrieve the cross references of the component realization of all the UML elements displayed as node in
	 * a Diagram. Note that a Property cross reference will lead to retrieve the cross references of this
	 * property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getComponentRealizationInverseRefs(DDiagram diagram) {
		return NodeInverseRefsServices.INSTANCE.getComponentRealizationInverseRefs(diagram);
	}

	/**
	 * Retrieve the cross references of the connector of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getConnectorInverseRefs(DDiagram diagram) {
		return NodeInverseRefsServices.INSTANCE.getConnectorInverseRefs(diagram);
	}

	/**
	 * Retrieve the cross references of the dependency of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getDependencyOnlyInverseRefs(DDiagram diagram) {
		return NodeInverseRefsServices.INSTANCE.getDependencyOnlyInverseRefs(diagram);
	}

	/**
	 * Get the source edge mapping for a connector from a port to a required interface.
	 *
	 * @param connector
	 *            the connector context
	 * @return interfaces
	 */
	public List<Port> getSource4Port2SubRequiredInterface(Connector connector) {
		final List<Port> result = new ArrayList<Port>();
		final List<Dependency> clientDependencies = connector.getClientDependencies();

		for (final ConnectorEnd connectorEnd : connector.getEnds()) {
			if (connectorEnd.getRole() instanceof Port) {
				final Port portUnderTest = (Port)connectorEnd.getRole();
				boolean sourcePort = true;
				for (final Dependency dependency : clientDependencies) {
					if (!sourcePort) {
						break;
					}
					if (dependency instanceof Usage) {
						for (final NamedElement client : dependency.getClients()) {
							if (client instanceof Port && client.equals(portUnderTest)) {
								sourcePort = false;
								break;
							}
						}
					}
					if (sourcePort) {
						result.add(portUnderTest);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get the source edge mapping for a connector from a provided interface to a required interface.
	 *
	 * @param connector
	 *            the connector context
	 * @return interfaces
	 */
	public List<Interface> getSource4ProvidedInterface2RequiredInterface(Connector connector) {
		final List<Interface> result = new ArrayList<Interface>();
		for (final Dependency dependency : connector.getClientDependencies()) {
			if (dependency instanceof InterfaceRealization) {
				final List<NamedElement> suppliers = dependency.getSuppliers();
				for (final NamedElement supplier : suppliers) {
					if (supplier instanceof Interface) {
						result.add((Interface)supplier);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get the source edge mapping for a connector from a provided interface to a delegate port.
	 *
	 * @param connector
	 *            the connector context
	 * @return interfaces
	 */
	public List<Interface> getSource4SubProvidedInterface2Port(Connector connector) {
		final List<Interface> result = new ArrayList<Interface>();
		for (final Dependency dependency : connector.getClientDependencies()) {
			if (dependency instanceof InterfaceRealization) {
				final List<NamedElement> suppliers = dependency.getSuppliers();
				for (final NamedElement supplier : suppliers) {
					if (supplier instanceof Interface) {
						result.add((Interface)supplier);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get the target edge mapping for a connector from a port to a required interface.
	 *
	 * @param connector
	 *            the connector context
	 * @return interfaces
	 */
	public List<Interface> getTarget4Port2SubRequiredInterface(Connector connector) {
		final List<Interface> result = new ArrayList<Interface>();
		for (final Dependency dependency : connector.getClientDependencies()) {
			if (dependency instanceof Usage) {
				final List<NamedElement> suppliers = dependency.getSuppliers();
				for (final NamedElement supplier : suppliers) {
					if (supplier instanceof Interface) {
						result.add((Interface)supplier);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get the target edge mapping for a connector from a provided interface to a required interface.
	 *
	 * @param connector
	 *            the connector context
	 * @return interfaces
	 */
	public List<Interface> getTarget4ProvidedInterface2RequiredInterface(Connector connector) {
		final List<Interface> result = new ArrayList<Interface>();
		for (final Dependency dependency : connector.getClientDependencies()) {
			if (dependency instanceof Usage) {
				final List<NamedElement> suppliers = dependency.getSuppliers();
				for (final NamedElement supplier : suppliers) {
					if (supplier instanceof Interface) {
						result.add((Interface)supplier);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get the target edge mapping for a connector from a provided interface to a delegate port.
	 *
	 * @param connector
	 *            the connector context
	 * @return interfaces
	 */
	public List<Port> getTarget4SubProvidedInterface2Port(Connector connector) {
		final List<Port> result = new ArrayList<Port>();
		final List<Dependency> clientDependencies = connector.getClientDependencies();
		for (final ConnectorEnd connectorEnd : connector.getEnds()) {
			if (connectorEnd.getRole() instanceof Port) {
				final Port portUnderTest = (Port)connectorEnd.getRole();
				boolean targetPort = true;
				for (final Dependency dependency : clientDependencies) {
					if (!targetPort) {
						break;
					}
					if (dependency instanceof InterfaceRealization) {
						for (final NamedElement client : dependency.getClients()) {
							if (client instanceof Port && client.equals(portUnderTest)) {
								targetPort = false;
								break;
							}
						}
					}
					if (targetPort) {
						result.add(portUnderTest);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Retrieve the cross references of the usage of all the UML elements displayed as node in a Diagram. Note
	 * that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getUsageInverseRefs(DDiagram diagram) {
		return NodeInverseRefsServices.INSTANCE.getUsageInverseRefs(diagram);
	}

	/**
	 * Check if an element is a component.
	 *
	 * @param element
	 *            Element
	 * @return True if element is an instance of component
	 */
	public boolean isComponent(EObject element) {
		return ElementServices.INSTANCE.isComponent(element);
	}

	/**
	 * Check that source and target are connectable. We explore recursively source generalizations to handle
	 * super type cases.
	 *
	 * @param source
	 *            the source element
	 * @param target
	 *            the target element
	 * @return true if connectable
	 */
	public boolean isConnectable(Element source, Element target) {
		return ConnectorServices.INSTANCE.isConnectable(source, target);
	}

	/**
	 * Test if a given couple of source/target is valid to display for a connector.
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
	public boolean validSourceTarget4Connector(Element source, DSemanticDecorator sourceView, Element target,
			DSemanticDecorator targetView) {
		boolean result = false;

		if (source instanceof org.eclipse.uml2.uml.Interface
				&& target instanceof org.eclipse.uml2.uml.Interface) {
			result = targetView.eContainer().equals(sourceView.eContainer());
		}
		return result;
	}

	/**
	 * Test if a given couple of source/target is valid to display for a delegated connector.
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
	public boolean validSourceTarget4DelegatedConnector(Element source, DSemanticDecorator sourceView,
			Element target, DSemanticDecorator targetView) {
		boolean result = false;

		if (source instanceof org.eclipse.uml2.uml.Interface) {
			if (target instanceof Port) {
				result = targetView.eContainer().equals(sourceView.eContainer());
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
