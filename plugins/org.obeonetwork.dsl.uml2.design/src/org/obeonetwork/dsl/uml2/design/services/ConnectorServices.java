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

import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Usage;

/**
 * Utility services to manage operation creation.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public final class ConnectorServices {

	/**
	 * Check if an interface is required for a specific port.
	 * 
	 * @param anInterface
	 *            the interface
	 * @param aPort
	 *            the port
	 * @return the boolean result
	 */
	public boolean isRequiredInterface(Interface anInterface, Port aPort) {
		boolean result = aPort.getRequireds().contains(anInterface);
		if (!result) {
			for (Dependency dependency : aPort.getClientDependencies()) {
				if (dependency instanceof Usage) {
					result = dependency.getSuppliers().contains(anInterface);
					if (result) {
						break;
					}
				}
			}
		}
		return result;
	}

	/**
	 * Check if an interface is provided for a specific port.
	 * 
	 * @param anInterface
	 *            the interface
	 * @param aPort
	 *            the port
	 * @return the boolean result
	 */
	public boolean isProvidedInterface(Interface anInterface, Port aPort) {
		boolean result = aPort.getProvideds().contains(anInterface);
		if (!result) {
			for (Dependency dependency : aPort.getClientDependencies()) {
				if (dependency instanceof InterfaceRealization) {
					result = dependency.getSuppliers().contains(anInterface);
					if (result) {
						break;
					}
				}
			}
		}
		return result;
	}

	/**
	 * check that source and target are connectable. We explore recursively source generalizations to handle
	 * super type cases.
	 * 
	 * @param source
	 *            the source element
	 * @param target
	 *            the target element
	 * @return true if connectable
	 */
	public boolean isConnectable(Element source, Element target) {
		boolean result = false;

		if (source instanceof Interface) {
			if (target instanceof Interface) {
				result = isConnectable((Interface)source, (Interface)target);
			} else if (target instanceof Port) {
				result = isConnectable((Interface)source, (Port)target);
			} else if (target instanceof Property) {
				result = isConnectable((Interface)source, (Property)target);
			}
		} else if (source instanceof Port) {
			if (target instanceof Interface) {
				result = isConnectable((Port)source, (Interface)target);
			} else if (target instanceof Port) {
				result = false;
			} else if (target instanceof Property) {
				result = isConnectable((Port)source, (Property)target);
			}
		} else if (source instanceof Property) {
			if (target instanceof Interface) {
				result = isConnectable((Property)source, (Interface)target);
			} else if (target instanceof Port) {
				result = isConnectable((Property)source, (Port)target);
			} else if (target instanceof Property) {
				result = isConnectable((Property)source, (Property)target);
			}
		}
		return result;
	}

	protected boolean isConnectable(Interface source, Interface target) {
		boolean res = source.conformsTo(target);
		if (!res) {
			List<Generalization> generalizations = source.getGeneralizations();

			for (Generalization generalization : generalizations) {
				if (generalization.getGeneral() instanceof Interface) {
					res = isConnectable((Interface)generalization.getGeneral(), target);
					if (res) {
						break;
					}
				}
			}
		}

		return res;
	}

	protected boolean isConnectable(Interface source, Port target) {

		boolean res = false;
		List<Dependency> clientDependencies = target.getClientDependencies();

		if (target.getType() instanceof EncapsulatedClassifier) {
			clientDependencies.addAll(target.getType().getClientDependencies());
		}
		for (Dependency dependency : clientDependencies) {
			if (dependency instanceof InterfaceRealization) {
				List<NamedElement> suppliers = dependency.getSuppliers();
				for (NamedElement interfaceSupplier : suppliers) {
					if (interfaceSupplier instanceof Interface) {
						res = isConnectable(source, (Interface)interfaceSupplier);
						if (res) {
							break;
						}
					}
				}
				if (res) {
					break;
				}
			}
		}

		return res;
	}

	protected boolean isConnectable(Port source, Interface target) {

		boolean res = false;

		List<Dependency> clientDependencies = source.getClientDependencies();
		if (source.getType() instanceof EncapsulatedClassifier) {
			clientDependencies.addAll(source.getType().getClientDependencies());
		}
		for (Dependency dependency : clientDependencies) {
			if (dependency instanceof Usage) {
				List<NamedElement> suppliers = dependency.getSuppliers();
				for (NamedElement interfaceSupplier : suppliers) {
					if (interfaceSupplier instanceof Interface) {
						res = isConnectable((Interface)interfaceSupplier, target);
						if (res) {
							break;
						}
					}
				}
				if (res) {
					break;
				}
			}
		}

		return res;
	}

	protected boolean isConnectable(Property source, Property target) {
		boolean res = true;
		if (source.getType() != null && target.getType() != null) {
			if (source.getType() instanceof Interface && target.getType() instanceof Interface) {
				res = isConnectable((Interface)source.getType(), (Interface)target.getType());
			} else {
				res = source.isCompatibleWith(target);
			}
		}
		return res;
	}

	protected boolean isConnectable(Interface source, Property target) {
		// TODO HMA
		return false;
	}

	protected boolean isConnectable(Property source, Interface target) {
		// TODO HMA
		return false;
	}

	protected boolean isConnectable(Port source, Property target) {
		// TODO HMA
		return false;
	}

	protected boolean isConnectable(Property source, Port target) {
		// TODO HMA
		return false;
	}

	/**
	 * Create a connector between two properties.
	 * 
	 * @param structuredClassifier
	 *            the connector container
	 * @param pSource
	 *            the property source view
	 * @param pTarget
	 *            the property target
	 * @return the new connector
	 */
	public Connector connectProperty2Property(Property pSource, Property pTarget) {

		final StructuredClassifier structuredClassifier = (StructuredClassifier)pSource.eContainer();

		final Connector connector = createConnector(structuredClassifier, pSource, pTarget);

		final ConnectorEnd iSourceConnectorEnd = connector.createEnd();
		final ConnectorEnd iTargetConnectorEnd = connector.createEnd();

		iSourceConnectorEnd.setRole(pSource);
		iTargetConnectorEnd.setRole(pTarget);

		return connector;
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

		final Connector connector = structuredClassifier.createOwnedConnector(elem1.getName() + "_"
				+ elem2.getName() + "_connector");

		return connector;
	}

	/**
	 * Get a port with the same type of the structuredClassifier. Firstly, we search in the
	 * structuredClassifier ports. If nothing matches, we create a new port and we return it. Else, we return
	 * the first match.
	 * 
	 * @param encapsulatedClassifier
	 * @return
	 */
	public Port getStructuredClassifierPublicPort(EncapsulatedClassifier encapsulatedClassifier) {
		List<Port> ownedPorts = encapsulatedClassifier.getOwnedPorts();
		for (Port port : ownedPorts) {
			if (encapsulatedClassifier.equals(port.getType())) {
				return port;
			}
		}
		Port newPort = encapsulatedClassifier.createOwnedPort(encapsulatedClassifier.getName() + "Port",
				encapsulatedClassifier);
		return newPort;
	}

	public List<Interface> getSource4ProvidedInterface2RequiredInterface(Connector connector) {
		List<Interface> result = new ArrayList<Interface>();
		for (Dependency dependency : connector.getClientDependencies()) {
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

	public List<Interface> getTarget4ProvidedInterface2RequiredInterface(Connector connector) {
		List<Interface> result = new ArrayList<Interface>();
		for (Dependency dependency : connector.getClientDependencies()) {
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
	 * get the edge source for the Port2SubRequiredInterface edge.
	 * 
	 * @param connector
	 *            the connector context
	 * @return the edge source
	 */
	public List<Port> getSource4Port2SubRequiredInterface(Connector connector) {
		// [end.role.oclAsType(uml::Port)/]
		List<Port> result = new ArrayList<Port>();
		List<Dependency> clientDependencies = connector.getClientDependencies();

		for (ConnectorEnd connectorEnd : connector.getEnds()) {
			if (connectorEnd.getRole() instanceof Port) {
				Port portUnderTest = (Port)connectorEnd.getRole();
				boolean sourcePort = true;
				for (Dependency dependency : clientDependencies) {
					if (!sourcePort) {
						break;
					}
					if (dependency instanceof Usage) {
						for (NamedElement client : dependency.getClients()) {
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

	public List<Interface> getTarget4Port2SubRequiredInterface(Connector connector) {
		List<Interface> result = new ArrayList<Interface>();
		for (Dependency dependency : connector.getClientDependencies()) {
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

	public List<Interface> getSource4SubProvidedInterface2Port(Connector connector) {
		List<Interface> result = new ArrayList<Interface>();
		for (Dependency dependency : connector.getClientDependencies()) {
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

	public List<Port> getTarget4SubProvidedInterface2Port(Connector connector) {
		List<Port> result = new ArrayList<Port>();
		List<Dependency> clientDependencies = connector.getClientDependencies();
		for (ConnectorEnd connectorEnd : connector.getEnds()) {
			if (connectorEnd.getRole() instanceof Port) {
				Port portUnderTest = (Port)connectorEnd.getRole();
				boolean targetPort = true;
				for (Dependency dependency : clientDependencies) {
					if (!targetPort) {
						break;
					}
					if (dependency instanceof InterfaceRealization) {
						for (NamedElement client : dependency.getClients()) {
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

}
