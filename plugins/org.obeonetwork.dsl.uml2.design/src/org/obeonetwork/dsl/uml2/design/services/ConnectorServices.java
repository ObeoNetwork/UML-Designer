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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Dependency;
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
import fr.obeo.dsl.viewpoint.DNodeContainer;
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
			final ConnectorEnd connectorSource = self.getEnds().get(0);
			if (!isValidPart(connectorSource, getViewContainerTarget(sourceView)))
				return false;

			final ConnectorEnd connectorTarget = self.getEnds().get(1);
			if (!isValidPart(connectorTarget, getViewContainerTarget(targetView)))
				return false;

			return true;

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
		return aPort.getRequireds().contains(anInterface);
	}

	/**
	 * Check if an interface is provided for a specific port.
	 * 
	 * @param anInterface
	 *            the interface
	 * @param aPort
	 * @return the boolean result
	 */
	public boolean isProvidedInterface(Interface anInterface, Port aPort) {
		return aPort.getProvideds().contains(anInterface);
	}

	/**
	 * check that source and target are connectable We explore recursively source generalizations to handle
	 * super type cases.
	 * 
	 * @param target
	 * @param source
	 * @return true if connectable
	 */
	public boolean isConnectable(Interface source, Interface target) {

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

	/**
	 * check that source and target are connectable
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public boolean isConnectable(Interface source, Port target) {

		boolean res = false;

		List<Dependency> clientDependencies = target.getType().getClientDependencies();
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

	/**
	 * check that source and target are connectable
	 * 
	 * @param target
	 * @param source
	 * @return
	 */
	public boolean isConnectable(Port source, Interface target) {

		boolean res = false;

		List<Dependency> clientDependencies = source.getType().getClientDependencies();
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

	public boolean isConnectable(Property source, Property target) {
		return !source.equals(target);
	}

	public boolean isConnectable(Interface source, Property target) {
		return false;
	}

	public boolean isConnectable(Property source, Interface target) {
		return false;
	}

	public boolean isConnectable(Port source, Property target) {
		return false;
	}

	public boolean isConnectable(Property source, Port target) {
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
			if (targetNode instanceof DNode) {
				final DNode dNode = (DNode)targetNode;
				final EObject target = dNode.getTarget();
				if (target instanceof Port) {
					return (StructuredClassifier)target.eContainer();
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

		// [sourceView.oclAsType(viewpoint::AbstractDNode).incomingEdges.sourceNode.eContainer(viewpoint::DNodeContainer).target->flatten()->first()/]
		final List<DEdge> dEdges = interfaceView.getIncomingEdges();
		dEdges.addAll(interfaceView.getOutgoingEdges());
		for (DEdge dEdge : dEdges) {
			final EdgeTarget sourceNode = dEdge.getSourceNode();
			final EObject eSourceContainer = sourceNode.eContainer();
			final EdgeTarget targetNode = dEdge.getTargetNode();
			final EObject eTargetContainer = targetNode.eContainer();
			if (eSourceContainer instanceof DNodeContainer) {
				final DNodeContainer dNodeContainer = (DNodeContainer)eSourceContainer;
				final EObject target = dNodeContainer.getTarget();
				if (target instanceof StructuredClassifier) {
					return (StructuredClassifier)target;
				}
			}
			if (sourceNode instanceof DNode) {
				final DNode dNode = (DNode)sourceNode;
				final EObject target = dNode.getTarget();
				if (target instanceof StructuredClassifier) {
					return (StructuredClassifier)target;
				}
			}
			if (eTargetContainer instanceof DNodeContainer) {
				final DNodeContainer dNodeContainer = (DNodeContainer)eTargetContainer;
				final EObject target = dNodeContainer.getTarget();
				if (target instanceof StructuredClassifier) {
					return (StructuredClassifier)target;
				}
			}
			if (targetNode instanceof DNode) {
				final DNode dNode = (DNode)targetNode;
				final EObject target = dNode.getTarget();
				if (target instanceof StructuredClassifier) {
					return (StructuredClassifier)target;
				}
			}
		}
		return null;
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
	private Connector connectProperty2Property(StructuredClassifier structuredClassifier, Property pSource,
			Property pTarget) {

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
			if (port.getType().equals(encapsulatedClassifier)) {
				return port;
			}
		}
		Port newPort = encapsulatedClassifier.createOwnedPort(encapsulatedClassifier.getName() + "Port",
				encapsulatedClassifier);
		return newPort;
	}
}
