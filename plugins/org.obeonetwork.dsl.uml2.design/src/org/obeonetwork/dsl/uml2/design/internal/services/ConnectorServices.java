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
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
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

import com.google.common.collect.Lists;

/**
 * A set of services to handle connectors.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ConnectorServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final ConnectorServices INSTANCE = new ConnectorServices();

	/**
	 * Hidden constructor.
	 */
	private ConnectorServices() {

	}

	/**
	 * This function adds connectorEnds and clientDependencies on a connector with given edges.
	 *
	 * @param connector
	 *            the connector
	 * @param edges
	 *            graphical edges than can map dependencies
	 */
	private void addConnectorEndsAndClientDependencies(Connector connector, Set<DEdge> edges) {
		for (final DEdge dEdge : edges) {
			final EObject target = dEdge.getTarget();
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
						final Port publicPort = getStructuredClassifierPublicPort((EncapsulatedClassifier)model);
						final ConnectorEnd connectorEnd = connector.createEnd();
						connectorEnd.setRole(publicPort);
					} else {
						// this edge does not handled
						continue;
					}

					// Add this dependency on the connector clientDependencies
					// Since UML 2.5 clientDependencies is derived so we have to add the connector as client
					// and automatically it will be calculated as client dependency
					dependency.getClients().add(connector);

				} else if (dependency instanceof InterfaceRealization) {
					// handle connectorEnd
					final AbstractDNode sourceNode = (AbstractDNode)dEdge.getSourceNode();
					final EObject model = sourceNode.getTarget();

					if (model instanceof ConnectableElement) {
						final ConnectorEnd connectorEnd = connector.createEnd();
						connectorEnd.setRole((ConnectableElement)model);
					} else if (model instanceof EncapsulatedClassifier) {
						final Port publicPort = getStructuredClassifierPublicPort((EncapsulatedClassifier)model);
						final ConnectorEnd connectorEnd = connector.createEnd();
						connectorEnd.setRole(publicPort);
					} else {
						// this edge does not handled
						continue;
					}

					// Add this dependency on the connector clientDependencies
					// Since UML 2.5 clientDependencies is derived so we have to add the connector as client
					// and automatically it will be calculated as client dependency
					dependency.getClients().add(connector);
				}
			} else if (target instanceof Port) {
				final ConnectorEnd connectorEnd = connector.createEnd();
				connectorEnd.setRole((ConnectableElement)target);
			} else if (target instanceof Interface) {
				final ConnectorEnd connectorEnd = connector.createEnd();
				final AbstractDNode targetNode = (AbstractDNode)dEdge.getTargetNode();
				final EObject model = targetNode.getTarget();
				connectorEnd.setRole((ConnectableElement)model);
			}
		}
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
	protected Connector connectInterface2Interface(DNode sourceView, Interface iSource, DNode targetView,
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
	protected Connector connectInterface2Port(DNode sourceView, Interface iSource, DNode targetView,
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
	 * @param pSource
	 *            the interface source
	 * @param targetView
	 *            the port target view
	 * @param iTarget
	 *            the port target
	 * @return the new connector
	 */
	protected Connector connectPort2Interface(DNode sourceView, Port pSource, DNode targetView,
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

	protected Connector connectPortInterface2PortInterface(DNode sourceView, Interface iSource,
			DNode targetView, Interface iTarget) {

		final StructuredClassifier structuredClassifier = getStructuredClassifierRelated2InterfaceView(targetView);
		final Connector connector = createConnector(structuredClassifier, iTarget, iSource);

		// add ConnectorEnds and clientDependencies
		final Set<DEdge> edges = new HashSet<DEdge>();
		edges.addAll(sourceView.getIncomingEdges());
		edges.addAll(targetView.getIncomingEdges());
		edges.addAll(targetView.getOutgoingEdges());
		edges.addAll(sourceView.getOutgoingEdges());

		// add ConnectorEnds and clientDependencies
		addConnectorEndsAndClientDependencies(connector, edges);

		return connector;
	}

	/**
	 * Create a connector between two properties.
	 *
	 * @param sourceView
	 * @param source
	 *            the property source view
	 * @param targetView
	 * @param target
	 *            the property target
	 * @return the new connector
	 */
	public Connector connectProperty2Property(DDiagramElement sourceView, Property source,
			DDiagramElement targetView, Property target) {

		final StructuredClassifier sourceContainer = (StructuredClassifier)source.eContainer();

		final Connector connector = createConnector(sourceContainer, source, target);

		final ConnectorEnd sourceConnectorEnd = connector.createEnd();
		final ConnectorEnd targetConnectorEnd = connector.createEnd();

		sourceConnectorEnd.setRole(source);
		targetConnectorEnd.setRole(target);

		final EObject sourcePart = ((DNodeContainer)sourceView.eContainer()).getTarget();
		if (source instanceof Port && sourcePart instanceof Property) {
			sourceConnectorEnd.setPartWithPort((Property)sourcePart);
		}
		final EObject targetPart = ((DNodeContainer)targetView.eContainer()).getTarget();
		if (target instanceof Port && targetPart instanceof Property) {
			targetConnectorEnd.setPartWithPort((Property)targetPart);
		}

		return connector;
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
				final List<DEdge> dEdges = new ArrayList<DEdge>(((DNode)sourceView).getIncomingEdges());
				dEdges.addAll(((DNode)sourceView).getOutgoingEdges());
				for (final DEdge dEdge : dEdges) {
					final EObject edgeTarget = dEdge.getTarget();
					if (edgeTarget instanceof Usage) {
						// Edge from a required interface to a provided Port
						// Make a new connector From Interface to Interface
						connectInterface2Interface((DNode)targetView, (Interface)target, (DNode)sourceView,
								(Interface)source);
						break;
					} else if (edgeTarget instanceof InterfaceRealization) {
						// Edge from a provided interface to a required Port
						// Make a new connector From Interface to Interface
						connectInterface2Interface((DNode)sourceView, (Interface)source, (DNode)targetView,
								(Interface)target);
						break;
					}
				}

			} else if (target instanceof Port) {

				final List<DEdge> dEdges = new ArrayList<DEdge>(((DNode)sourceView).getIncomingEdges());
				dEdges.addAll(((DNode)sourceView).getOutgoingEdges());
				for (final DEdge dEdge : dEdges) {
					final EObject edgeTarget = dEdge.getTarget();
					if (edgeTarget instanceof Usage) {
						// Edge from a required interface to a Port
						// Make a new connector from a Port to an Interface
						connectPort2Interface((DNode)targetView, (Port)target, (DNode)sourceView,
								(Interface)source);
						break;
					} else if (edgeTarget instanceof InterfaceRealization) {
						// Edge from a provided interface to a Port
						// Make a new connector From Interface to Port
						connectInterface2Port((DNode)sourceView, (Interface)source, (DNode)targetView,
								(Port)target);
						break;
					}
				}
			}
		} else if (source instanceof Port && target instanceof Interface) {

			final List<DEdge> dEdges = new ArrayList<DEdge>(((DNode)targetView).getIncomingEdges());
			dEdges.addAll(((DNode)targetView).getOutgoingEdges());
			for (final DEdge dEdge : dEdges) {
				final EObject edgeTarget = dEdge.getTarget();
				if (edgeTarget instanceof Usage) {
					// Edge from a required interface to a Port
					// Make a new connector From Port to Interface
					connectPort2Interface((DNode)sourceView, (Port)source, (DNode)targetView,
							(Interface)target);
					break;
				} else if (edgeTarget instanceof InterfaceRealization) {
					// Edge from a provided interface to a Port
					// Make a new connector From Interface to Port
					connectInterface2Port((DNode)targetView, (Interface)target, (DNode)targetView,
							(Port)source);
					break;
				}
			}
		} else if (source instanceof Property && target instanceof Property) {
			// Make a new connector From Port to Interface
			new org.obeonetwork.dsl.uml2.design.internal.services.ConnectorServices()
			.connectProperty2Property(sourceView, (Property)source, targetView, (Property)target);
		} else {
			LogServices.INSTANCE.error(
					"ConnectorServices.createConnector(" + source.getClass() + ", " + target.getClass() //$NON-NLS-1$ //$NON-NLS-2$
					+ ") not handled", null); //$NON-NLS-1$
		}
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

		final Connector connector = structuredClassifier.createOwnedConnector(elem1.getName() + "_" //$NON-NLS-1$
				+ elem2.getName() + "_connector"); //$NON-NLS-1$

		return connector;
	}

	/**
	 * Get a port with the same type of the structuredClassifier. Firstly, we search in the
	 * structuredClassifier ports. If nothing matches, we create a new port and we return it. Else, we return
	 * the first match.
	 *
	 * @param encapsulatedClassifier
	 *            the encapsulatedClassifier
	 * @return the found port or a new port
	 */
	private Port getStructuredClassifierPublicPort(EncapsulatedClassifier encapsulatedClassifier) {
		final List<Port> ownedPorts = encapsulatedClassifier.getOwnedPorts();
		for (final Port port : ownedPorts) {
			if (encapsulatedClassifier.equals(port.getType())) {
				return port;
			}
		}
		final Port newPort = encapsulatedClassifier.createOwnedPort(
				encapsulatedClassifier.getName() + "Port", encapsulatedClassifier); //$NON-NLS-1$
		return newPort;
	}

	/**
	 * Get the first Structured Classifier related to this interface view.
	 *
	 * @param interfaceView
	 *            the interface view
	 * @return the first Structured Classifier found
	 */
	protected StructuredClassifier getStructuredClassifierRelated2InterfaceView(DNode interfaceView) {
		StructuredClassifier res = null;
		// [sourceView.oclAsType(viewpoint::AbstractDNode).incomingEdges.sourceNode.eContainer(viewpoint::DNodeContainer).target->flatten()->first()/]
		final List<DEdge> dEdges = new ArrayList<DEdge>(interfaceView.getIncomingEdges());
		dEdges.addAll(interfaceView.getOutgoingEdges());
		for (final DEdge dEdge : dEdges) {
			final EdgeTarget sourceNode = dEdge.getSourceNode();
			if (sourceNode instanceof AbstractDNode) {
				final EObject source = ((AbstractDNode)sourceNode).getTarget();
				if (source instanceof Property && source.eContainer() instanceof StructuredClassifier) {
					res = (StructuredClassifier)source.eContainer();
				} else if (source instanceof StructuredClassifier) {
					res = (StructuredClassifier)source;
				}
			}
		}

		if (res == null) {
			LogServices.INSTANCE.error("ConnectorServices.getStructuredClassifierRelated2InterfaceView(" //$NON-NLS-1$
					+ interfaceView.getName() + ") not handled", null); //$NON-NLS-1$
		}
		return res;
	}

	/**
	 * Get the first Structured Classifier related to this interface view.
	 *
	 * @param interfaceView
	 *            the interface view
	 * @return the first Structured Classifier found
	 */
	protected StructuredClassifier getStructuredClassifierRelated2SubInterfaceView(DNode interfaceView) {
		final List<DEdge> dEdges = interfaceView.getOutgoingEdges();
		StructuredClassifier result = null;
		for (final DEdge dEdge : dEdges) {
			final EdgeTarget targetNode = dEdge.getTargetNode();
			if (targetNode instanceof AbstractDNode) {
				final AbstractDNode dNode = (AbstractDNode)targetNode;
				final EObject target = dNode.getTarget();
				if (target instanceof Property) {
					result = (StructuredClassifier)target.eContainer();
					break;
				} else if (target instanceof StructuredClassifier) {
					result = (StructuredClassifier)target;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Get the view eContainer semantic target.
	 *
	 * @param view
	 *            the current view
	 * @return the container semantic target or null if any.
	 */
	protected Element getViewContainerTarget(DNode view) {
		if (view != null && view.eContainer() instanceof DSemanticDecorator) {
			return (Element)((DSemanticDecorator)view.eContainer()).getTarget();
		}
		return null;

	}

	/**
	 * Test if a given couple of sourceView/targetView is valid to display for a dependency.
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
	public boolean isConnectable(Element source, DSemanticDecorator sourceView, Element target,
			DSemanticDecorator targetView) {
		boolean result = false;
		if (source instanceof Interface && target instanceof Interface) {
			result = isConnectable(source, target);
		} else {
			if (source instanceof Interface && sourceView instanceof DNode) {
				final List<DEdge> dEdges = new ArrayList<DEdge>(((DNode)sourceView).getIncomingEdges());
				dEdges.addAll(((DNode)sourceView).getOutgoingEdges());
				for (final DEdge dEdge : dEdges) {
					final EObject edgeTarget = dEdge.getTarget();
					if (edgeTarget instanceof Usage) {
						// Edge from a required interface to a Port
						result = isConnectable(target, source);
						if (result) {
							break;
						}
					} else if (edgeTarget instanceof InterfaceRealization) {
						// Edge from a provided interface to a Port
						result = isConnectable(source, target);
						if (result) {
							break;
						}
					}
				}
			} else if (target instanceof Interface && targetView instanceof DNode) {
				final List<DEdge> dEdges = new ArrayList<DEdge>(((DNode)sourceView).getIncomingEdges());
				dEdges.addAll(((DNode)sourceView).getOutgoingEdges());
				for (final DEdge dEdge : dEdges) {
					final EObject edgeTarget = dEdge.getTarget();
					if (edgeTarget instanceof Usage) {
						// Edge from a Port to a required interface
						result = isConnectable(source, target);
						if (result) {
							break;
						}
					} else if (edgeTarget instanceof InterfaceRealization) {
						// Edge from a Port to a provided interface
						result = isConnectable(target, source);
						if (result) {
							break;
						}
					}
				}
			}
		}
		return result;
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
				result = isConnectable((Port)source, (Port)target);
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
	protected boolean isConnectable(Interface source, Interface target) {
		boolean res = source.conformsTo(target);
		if (!res) {
			final List<Generalization> generalizations = source.getGeneralizations();

			for (final Generalization generalization : generalizations) {
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
	protected boolean isConnectable(Interface source, Port target) {

		boolean res = false;
		final List<Dependency> clientDependencies = Lists.newArrayList(target.getClientDependencies());

		if (target.getType() instanceof EncapsulatedClassifier) {
			clientDependencies.addAll(target.getType().getClientDependencies());
		}
		for (final Dependency dependency : clientDependencies) {
			if (dependency instanceof InterfaceRealization) {
				final List<NamedElement> suppliers = dependency.getSuppliers();
				for (final NamedElement interfaceSupplier : suppliers) {
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

	/**
	 * We have not handle this case. We have not any case with this scenario.
	 *
	 * @param source
	 *            the source element
	 * @param target
	 *            the target element
	 * @return true if connectable
	 */
	protected boolean isConnectable(Interface source, Property target) {
		return false;
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
	protected boolean isConnectable(Port source, Interface target) {

		boolean res = false;

		final List<Dependency> clientDependencies = Lists.newArrayList(source.getClientDependencies());
		if (source.getType() instanceof EncapsulatedClassifier) {
			clientDependencies.addAll(source.getType().getClientDependencies());
		}
		for (final Dependency dependency : clientDependencies) {
			if (dependency instanceof Usage) {
				final List<NamedElement> suppliers = dependency.getSuppliers();
				for (final NamedElement interfaceSupplier : suppliers) {
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
	protected boolean isConnectable(Port source, Port target) {
		boolean res = true;
		if (source.getType() != null && target.getType() != null) {
			res = source.getType().isCompatibleWith(target.getType());
		}
		return res;
	}

	/**
	 * We have not handle this case. We have not any case with this scenario.
	 *
	 * @param source
	 *            the source element
	 * @param target
	 *            the target element
	 * @return true if connectable
	 */
	protected boolean isConnectable(Port source, Property target) {
		return false;
	}

	/**
	 * We have not handle this case. We have not any case with this scenario.
	 *
	 * @param source
	 *            the source element
	 * @param target
	 *            the target element
	 * @return true if connectable
	 */
	protected boolean isConnectable(Property source, Interface target) {
		return false;
	}

	/**
	 * We have not handle this case. We have not any case with this scenario.
	 *
	 * @param source
	 *            the source element
	 * @param target
	 *            the target element
	 * @return true if connectable
	 */
	protected boolean isConnectable(Property source, Port target) {
		return false;
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

	/**
	 * Is valid connector.
	 *
	 * @param connector
	 *            Connector
	 * @param sourceView
	 *            Source view
	 * @param targetView
	 *            Target view
	 * @return True if a connector can be created between the source and the target
	 */
	public boolean isValidConnector(org.eclipse.uml2.uml.Connector connector, DNodeContainer sourceView,
			DNodeContainer targetView) {
		return sourceView.getTarget() instanceof Property && targetView.getTarget() instanceof Property;
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
	protected boolean isValidPart(ConnectorEnd end, Element viewSemanticTarget) {
		if (end != null && end.getPartWithPort() != null
				&& !EcoreUtil.equals(viewSemanticTarget, end.getPartWithPort())) {
			return false;
		}
		return true;

	}

}
