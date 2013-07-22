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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.ConnectableElement;
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

import fr.obeo.dsl.viewpoint.AbstractDNode;
import fr.obeo.dsl.viewpoint.DEdge;
import fr.obeo.dsl.viewpoint.DNode;
import fr.obeo.dsl.viewpoint.DSemanticDecorator;
import fr.obeo.dsl.viewpoint.EdgeTarget;

/**
 * Utility services to manage operation creation.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public final class ConnectorServices {

	/**
	 * Logger.
	 */
	private LogServices logger = new LogServices();

	/**
	 * Test if a given connector should be displayed from the given ports.
	 * 
	 * @param self
	 *            the connector
	 * @param sourceView
	 *            the source view
	 * @param targetView
	 *            the target view
	 * @return true if the connector must be displayed.
	 */
	public boolean isValidConnector(org.eclipse.uml2.uml.Connector self, DNode sourceView, DNode targetView) {
		if (self.getEnds().size() == 2) {
			boolean ret = true;
			final ConnectorEnd connectorSource = self.getEnds().get(0);
			if (!isValidPart(connectorSource, getViewContainerTarget(sourceView)))
				ret = false;
			final ConnectorEnd connectorTarget = self.getEnds().get(1);
			if (!isValidPart(connectorTarget, getViewContainerTarget(targetView)))
				ret = false;
			return ret;
		}
		return false;
	}

	/**
	 * Get the view eContainer semantic target.
	 * 
	 * @param view
	 *            the current view
	 * @return the container semantic target or null if any.
	 */
	private EObject getViewContainerTarget(DNode view) {
		if (view != null && view.eContainer() instanceof DSemanticDecorator) {
			return ((DSemanticDecorator)view.eContainer()).getTarget();
		}
		return null;

	}

	/**
	 * Tests if the semantic target is the same as the connector end part reference if one part is described.
	 * 
	 * @param end
	 *            the end part
	 * @param viewSemanticTarget
	 *            the semantic target
	 * @return true is it is the valid part.
	 */
	private boolean isValidPart(ConnectorEnd end, EObject viewSemanticTarget) {
		if (end != null && end.getPartWithPort() != null
				&& !EcoreUtil.equals(viewSemanticTarget, end.getPartWithPort()))
			return false;
		return true;

	}

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
				if (result) {
					break;
				}
				if (dependency instanceof Usage) {
					result = dependency.getSuppliers().contains(anInterface);
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
				if (result) {
					break;
				}
				if (dependency instanceof InterfaceRealization) {
					result = dependency.getSuppliers().contains(anInterface);
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

	private boolean isConnectable(Interface source, Interface target) {
		boolean res = source.conformsTo(target);
		if (!res) {
			List<Generalization> generalizations = source.getGeneralizations();

			for (Generalization generalization : generalizations) {
				if (generalization.getGeneral() instanceof Interface) {
					res = isConnectable((Interface)generalization.getGeneral(), target);
				}
				if (res) {
					break;
				}
			}
		}

		return res;
	}

	private boolean isConnectable(Interface source, Port target) {

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
					}
					if (res) {
						break;
					}
				}
				if (res) {
					break;
				}
			}
		}

		return res;
	}

	private boolean isConnectable(Port source, Interface target) {

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
					}
					if (res) {
						break;
					}
				}
				if (res) {
					break;
				}
			}
		}

		return res;
	}

	private boolean isConnectable(Property source, Property target) {
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

	private boolean isConnectable(Interface source, Property target) {
		// TODO HMA
		return false;
	}

	private boolean isConnectable(Property source, Interface target) {
		// TODO HMA
		return false;
	}

	private boolean isConnectable(Port source, Property target) {
		// TODO HMA
		return false;
	}

	private boolean isConnectable(Property source, Port target) {
		// TODO HMA
		return false;
	}

	/**
	 * Connect two elements with a connector. Enable features : From Interface to Interface From Property to
	 * Property From Interface to Port (Delegation)
	 * 
	 * @param sourceView
	 *            the current source view
	 * @param targetView
	 *            the current target view
	 */
	public void createConnector(AbstractDNode sourceView, AbstractDNode targetView) {

		final EObject source = sourceView.getTarget();
		final EObject target = targetView.getTarget();

		if (source instanceof Interface) {
			if (target instanceof Interface) {
				// Make a new connector From Interface to Interface
				connectInterface2Interface((DNode)sourceView, (Interface)source, (DNode)targetView,
						(Interface)target);
			} else if (target instanceof Port) {
				// Make a new connector From Interface to Port
				connectInterface2Port((DNode)sourceView, (Interface)source, (DNode)targetView, (Port)target);
			}
		} else if (source instanceof Port && target instanceof Interface) {
			// Make a new connector From Port to Interface
			connectPort2Interface((DNode)sourceView, (Port)source, (DNode)targetView, (Interface)target);
		} else if (source instanceof Property && target instanceof Property) {
			// Make a new connector From Port to Interface
			connectProperty2Property((Property)source, (Property)target);
		} else {
			logger.error("ConnectorServices.createConnector(" + source.getClass() + ", " + target.getClass()
					+ ") not handled", null);
		}
	}

	/**
	 * Get the first Structured Classifier related to this interface view.
	 * 
	 * @param interfaceView
	 *            the interface view
	 * @return the first Structured Classifier found
	 */
	private StructuredClassifier getStructuredClassifierRelated2SubInterfaceView(DNode interfaceView) {
		final List<DEdge> dEdges = interfaceView.getOutgoingEdges();
		for (DEdge dEdge : dEdges) {
			final EdgeTarget targetNode = dEdge.getTargetNode();
			if (targetNode instanceof AbstractDNode) {
				final AbstractDNode dNode = (AbstractDNode)targetNode;
				final EObject target = dNode.getTarget();
				if (target instanceof Property) {
					return (StructuredClassifier)target.eContainer();
				} else if (target instanceof StructuredClassifier) {
					return (StructuredClassifier)target;
				}
			}
		}
		return null;
	}

	/**
	 * Get the first Structured Classifier related to this interface view.
	 * 
	 * @param interfaceView
	 *            the interface view
	 * @return the first Structured Classifier found
	 */
	private StructuredClassifier getStructuredClassifierRelated2InterfaceView(DNode interfaceView) {
		StructuredClassifier res = null;
		// [sourceView.oclAsType(viewpoint::AbstractDNode).incomingEdges.sourceNode.eContainer(viewpoint::DNodeContainer).target->flatten()->first()/]
		final List<DEdge> dEdges = interfaceView.getIncomingEdges();
		dEdges.addAll(interfaceView.getOutgoingEdges());
		for (DEdge dEdge : dEdges) {
			final EdgeTarget sourceNode = dEdge.getSourceNode();
			if (sourceNode instanceof AbstractDNode) {
				EObject source = ((AbstractDNode)sourceNode).getTarget();
				if (source instanceof Property && source.eContainer() instanceof StructuredClassifier) {
					res = (StructuredClassifier)source.eContainer();
				} else if (source instanceof StructuredClassifier) {
					res = (StructuredClassifier)source;
				}
			}
		}

		if (res == null) {
			logger.error(
					"ConnectorServices.getStructuredClassifierRelated2InterfaceView("
							+ interfaceView.getName() + ") not handled", null);
		}
		return res;
	}

	/**
	 * Create a connector between two interfaces.
	 * 
	 * @param sourceView
	 *            the interface source view
	 * @param iSource
	 *            the interface source
	 * @param targetView
	 *            the interface target view
	 * @param iTarget
	 *            the interface target
	 * @return the new connector
	 */
	private Connector connectInterface2Interface(DNode sourceView, Interface iSource, DNode targetView,
			Interface iTarget) {

		final StructuredClassifier structuredClassifier = getStructuredClassifierRelated2InterfaceView(sourceView);
		final Connector connector = createConnector(structuredClassifier, iSource, iTarget);

		// add ConnectorEnds and clientDependencies
		final Set<DEdge> edges = new HashSet<DEdge>();
		edges.addAll(sourceView.getIncomingEdges());
		edges.addAll(targetView.getOutgoingEdges());

		// add ConnectorEnds and clientDependencies
		addConnectorEndsAndClientDependencies(connector, edges);

		return connector;
	}

	/**
	 * Create a connector from an interface to a port.
	 * 
	 * @param sourceView
	 *            the interface source view
	 * @param iSource
	 *            the interface source
	 * @param targetView
	 *            the port target view
	 * @param pTarget
	 *            the port target
	 * @return the new connector
	 */
	private Connector connectInterface2Port(DNode sourceView, Interface iSource, DNode targetView,
			Port pTarget) {

		final StructuredClassifier structuredClassifier = getStructuredClassifierRelated2InterfaceView(sourceView);
		final Connector connector = createConnector(structuredClassifier, iSource, pTarget);

		final Set<DEdge> edges = new HashSet<DEdge>();
		edges.addAll(sourceView.getIncomingEdges());

		// add ConnectorEnds and clientDependencies
		addConnectorEndsAndClientDependencies(connector, edges);

		final ConnectorEnd iTargetConnectorEnd = connector.createEnd();
		iTargetConnectorEnd.setRole(pTarget);

		return connector;
	}

	/**
	 * Create a connector from an interface to a port.
	 * 
	 * @param sourceView
	 *            the interface source view
	 * @param iSource
	 *            the interface source
	 * @param targetView
	 *            the port target view
	 * @param pTarget
	 *            the port target
	 * @return the new connector
	 */
	private Connector connectPort2Interface(DNode sourceView, Port pSource, DNode targetView,
			Interface iTarget) {

		final StructuredClassifier structuredClassifier = getStructuredClassifierRelated2SubInterfaceView(targetView);
		final Connector connector = createConnector(structuredClassifier, pSource, iTarget);

		final Set<DEdge> edges = new HashSet<DEdge>();
		edges.addAll(targetView.getOutgoingEdges());

		// add ConnectorEnds and clientDependencies
		addConnectorEndsAndClientDependencies(connector, edges);

		final ConnectorEnd iTargetConnectorEnd = connector.createEnd();
		iTargetConnectorEnd.setRole(pSource);

		return connector;
	}

	private void addConnectorEndsAndClientDependencies(Connector connector, Set<DEdge> edges) {
		final List<Dependency> clientDependencies = connector.getClientDependencies();
		for (DEdge dEdge : edges) {
			EObject target = dEdge.getTarget();
			if (target instanceof Dependency) {
				final Dependency dependency = (Dependency)target;
				if (dependency instanceof Usage) {
					// handle connectorEnd
					final AbstractDNode targetNode = (AbstractDNode)dEdge.getTargetNode();
					final EObject model = targetNode.getTarget();
					if (model instanceof ConnectableElement) {
						final ConnectorEnd connectorEnd = connector.createEnd();
						connectorEnd.setRole((ConnectableElement)model);
					} else if (model instanceof EncapsulatedClassifier) {
						Port publicPort = getStructuredClassifierPublicPort((EncapsulatedClassifier)model);
						final ConnectorEnd connectorEnd = connector.createEnd();
						connectorEnd.setRole(publicPort);
					} else {
						// this edge does not handled
						continue;
					}

					// Add this dependency on the connector clientDependencies
					clientDependencies.add(dependency);

				} else if (dependency instanceof InterfaceRealization) {
					// handle connectorEnd
					final AbstractDNode sourceNode = (AbstractDNode)dEdge.getSourceNode();
					final EObject model = sourceNode.getTarget();

					if (model instanceof ConnectableElement) {
						final ConnectorEnd connectorEnd = connector.createEnd();
						connectorEnd.setRole((ConnectableElement)model);
					} else if (model instanceof EncapsulatedClassifier) {
						Port publicPort = getStructuredClassifierPublicPort((EncapsulatedClassifier)model);
						final ConnectorEnd connectorEnd = connector.createEnd();
						connectorEnd.setRole(publicPort);
					} else {
						// this edge does not handled
						continue;
					}

					// Add this dependency on the connector clientDependencies
					clientDependencies.add(dependency);
				}
			}
		}
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
	private Connector connectProperty2Property(Property pSource, Property pTarget) {

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
	private Connector createConnector(StructuredClassifier structuredClassifier, NamedElement elem1,
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
	private Port getStructuredClassifierPublicPort(EncapsulatedClassifier encapsulatedClassifier) {
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

	public static boolean validSourceTarget4DelegatedConnector(EObject source, EObject sourceView,
			EObject target, EObject targetView) {
		boolean result = true;

		if (source instanceof org.eclipse.uml2.uml.Interface) {
			if (target instanceof Port) {
				result &= targetView.eContainer().equals(sourceView.eContainer());
			}
		} else {
			result = false;
		}
		return result;
	}

	public static boolean validSourceTarget4Connector(EObject source, EObject sourceView, EObject target,
			EObject targetView) {
		boolean result = true;

		if (source instanceof org.eclipse.uml2.uml.Interface
				&& target instanceof org.eclipse.uml2.uml.Interface) {
			result &= targetView.eContainer().equals(sourceView.eContainer());
		} else {
			result = false;
		}
		return result;
	}

	public static boolean validSourceTarget4Dependency(EObject source, EObject sourceView, EObject target,
			EObject targetView) {
		boolean result = true;

		if (source instanceof org.eclipse.uml2.uml.Class) {
			result &= targetView.eContainer().equals(sourceView.eContainer());
		} else if (source instanceof Port) {
			result &= targetView.eContainer().equals(sourceView.eContainer().eContainer());
		} else {
			result = false;
		}
		return result;
	}
}
