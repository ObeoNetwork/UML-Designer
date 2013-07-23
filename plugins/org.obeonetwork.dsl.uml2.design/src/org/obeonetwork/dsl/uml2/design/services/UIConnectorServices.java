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
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
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
	protected EObject getViewContainerTarget(DNode view) {
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
	protected boolean isValidPart(ConnectorEnd end, EObject viewSemanticTarget) {
		if (end != null && end.getPartWithPort() != null
				&& !EcoreUtil.equals(viewSemanticTarget, end.getPartWithPort()))
			return false;
		return true;

	}

	public boolean validSourceTarget4DelegatedConnector(EObject source, EObject sourceView, EObject target,
			EObject targetView) {
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

	public boolean validSourceTarget4Connector(EObject source, EObject sourceView, EObject target,
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

	public boolean validSourceTarget4Dependency(EObject source, EObject sourceView, EObject target,
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
	protected StructuredClassifier getStructuredClassifierRelated2InterfaceView(DNode interfaceView) {
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
	 * @param iSource
	 *            the interface source
	 * @param targetView
	 *            the port target view
	 * @param pTarget
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
}
