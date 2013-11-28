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
import org.eclipse.sirius.viewpoint.AbstractDNode;
import org.eclipse.sirius.viewpoint.DEdge;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.EdgeTarget;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Usage;

/**
 * A set of services to handle graphically Connector actions and tests.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class UIConnectorServices {

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
	protected Element getViewContainerTarget(DNode view) {
		if (view != null && view.eContainer() instanceof DSemanticDecorator) {
			return (Element)((DSemanticDecorator)view.eContainer()).getTarget();
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
	protected boolean isValidPart(ConnectorEnd end, Element viewSemanticTarget) {
		if (end != null && end.getPartWithPort() != null
				&& !EcoreUtil.equals(viewSemanticTarget, end.getPartWithPort()))
			return false;
		return true;

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
				for (DEdge dEdge : dEdges) {
					EObject edgeTarget = dEdge.getTarget();
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
				for (DEdge dEdge : dEdges) {
					EObject edgeTarget = dEdge.getTarget();
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
			for (DEdge dEdge : dEdges) {
				EObject edgeTarget = dEdge.getTarget();
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
			new ConnectorServices().connectProperty2Property((Property)source, (Property)target);
		} else {
			new LogServices().error(
					"ConnectorServices.createConnector(" + source.getClass() + ", " + target.getClass()
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
	protected StructuredClassifier getStructuredClassifierRelated2SubInterfaceView(DNode interfaceView) {
		final List<DEdge> dEdges = interfaceView.getOutgoingEdges();
		StructuredClassifier result = null;
		for (DEdge dEdge : dEdges) {
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
			new LogServices().error("ConnectorServices.getStructuredClassifierRelated2InterfaceView("
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
	protected Connector connectInterface2Interface(DNode sourceView, Interface iSource, DNode targetView,
			Interface iTarget) {

		final StructuredClassifier structuredClassifier = getStructuredClassifierRelated2InterfaceView(sourceView);
		final Connector connector = new ConnectorServices().createConnector(structuredClassifier, iSource,
				iTarget);

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
		final Connector connector = new ConnectorServices().createConnector(structuredClassifier, iSource,
				pTarget);

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
		final Connector connector = new ConnectorServices().createConnector(structuredClassifier, pSource,
				iTarget);

		final Set<DEdge> edges = new HashSet<DEdge>();
		edges.addAll(targetView.getOutgoingEdges());

		// add ConnectorEnds and clientDependencies
		addConnectorEndsAndClientDependencies(connector, edges);

		final ConnectorEnd iTargetConnectorEnd = connector.createEnd();
		iTargetConnectorEnd.setRole(pSource);

		return connector;
	}

	/**
	 * This function adds connectorEnds and clientDependencies on a connector with given edges.
	 * 
	 * @param connector
	 *            the connector
	 * @param edges
	 *            graphical edges than can map dependencies
	 */
	protected void addConnectorEndsAndClientDependencies(Connector connector, Set<DEdge> edges) {
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
						Port publicPort = new ConnectorServices()
								.getStructuredClassifierPublicPort((EncapsulatedClassifier)model);
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
						Port publicPort = new ConnectorServices()
								.getStructuredClassifierPublicPort((EncapsulatedClassifier)model);
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
		final ConnectorServices connectorServices = new ConnectorServices();
		if (source instanceof Interface && target instanceof Interface) {
			result = connectorServices.isConnectable((Element)source, (Element)target);
		} else {
			if (source instanceof Interface && sourceView instanceof DNode) {
				final List<DEdge> dEdges = new ArrayList<DEdge>(((DNode)sourceView).getIncomingEdges());
				dEdges.addAll(((DNode)sourceView).getOutgoingEdges());
				for (DEdge dEdge : dEdges) {
					EObject edgeTarget = dEdge.getTarget();
					if (edgeTarget instanceof Usage) {
						// Edge from a required interface to a Port
						result = connectorServices.isConnectable((Element)target, (Element)source);
						if (result) {
							break;
						}
					} else if (edgeTarget instanceof InterfaceRealization) {
						// Edge from a provided interface to a Port
						result = connectorServices.isConnectable((Element)source, (Element)target);
						if (result) {
							break;
						}
					}
				}
			} else if (target instanceof Interface && targetView instanceof DNode) {
				final List<DEdge> dEdges = new ArrayList<DEdge>(((DNode)sourceView).getIncomingEdges());
				dEdges.addAll(((DNode)sourceView).getOutgoingEdges());
				for (DEdge dEdge : dEdges) {
					EObject edgeTarget = dEdge.getTarget();
					if (edgeTarget instanceof Usage) {
						// Edge from a Port to a required interface
						result = connectorServices.isConnectable((Element)source, (Element)target);
						if (result) {
							break;
						}
					} else if (edgeTarget instanceof InterfaceRealization) {
						// Edge from a Port to a provided interface
						result = connectorServices.isConnectable((Element)target, (Element)source);
						if (result) {
							break;
						}
					}
				}
			}
		}
		return result;
	}
}
