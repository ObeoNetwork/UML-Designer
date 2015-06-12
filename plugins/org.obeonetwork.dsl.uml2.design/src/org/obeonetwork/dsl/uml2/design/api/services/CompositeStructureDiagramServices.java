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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.ConnectableElement;
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
import org.obeonetwork.dsl.uml2.design.internal.services.DependencyServices;
import org.obeonetwork.dsl.uml2.design.internal.services.NodeInverseRefsServices;
import org.obeonetwork.dsl.uml2.design.internal.services.RelatedCompositeStructureElementsSwitch;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

/**
 * A set of services to handle the Composite Structure diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class CompositeStructureDiagramServices extends AbstractDiagramServices {

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
		ConnectorServices.INSTANCE.createConnector(sourceView, targetView);
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
	 * Get aggregation kind composite.
	 *
	 * @param object
	 *            Object
	 * @return Composite
	 */
	public AggregationKind getAggregationKindComposite(EObject object) {
		return AggregationKind.COMPOSITE_LITERAL;
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

		return DependencyServices.INSTANCE.getClient(dependency);
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
	 * Get connector sources.
	 *
	 * @param connector
	 *            Connector
	 * @param diagram
	 *            Diagram
	 * @return List of sources
	 */
	public List<Element> getConnectorSource(Connector connector, DDiagram diagram) {
		final List<Element> connectableElements = new ArrayList<Element>();
		final List<ConnectorEnd> connectorEnds = connector.getEnds();
		if (connectorEnds != null && connectorEnds.size() > 0) {
			final ConnectorEnd connectorEnd = connectorEnds.get(0);
			final ConnectableElement role = connectorEnd.getRole();
			// Interfaces layer
			if (isInterfacesLayerActive(diagram.getOwnedDiagramElements().get(0))) {
				if (connector.getClientDependencies().size() > 0) {
					// check if connector is between interface.
					for (final Dependency dependency : connector.getClientDependencies()) {
						if (dependency instanceof InterfaceRealization || dependency instanceof Usage) {
							final List<NamedElement> suppliers = dependency.getSuppliers();
							for (final NamedElement supplier : suppliers) {
								if (supplier instanceof Interface) {
									connectableElements.add(supplier);
								}
							}
						}
					}
				} else {
					connectableElements.add(role);
				}
			} else {
				// Interface layer is inactive provide connector between ports, parts, and components
				/*
				 * A connector could be connect to a port or to an interface. An interface could be connect to
				 * a component throw a port or directly. In this case a "public" port was created but is not
				 * displayed. We have to return the component
				 */
				/*
				 * If several components have usage or interface realization link to the same interface in
				 * this case the connector will have several ends.
				 */
				final List<NamedElement> listOfInterfaceRealization = new ArrayList<NamedElement>();
				for (final Dependency dependency : connector.getClientDependencies()) {
					if (dependency instanceof InterfaceRealization) {
						for (final NamedElement elem : getClient(dependency)) {
							if (elem instanceof Port || elem instanceof StructuredClassifier) {
								listOfInterfaceRealization.add(elem);
							}
						}
					}
				}
				if (listOfInterfaceRealization.size() > 0) {
					for (final ConnectorEnd locConnectorEnd : connectorEnds) {
						if (locConnectorEnd.getRole() instanceof Port
								&& locConnectorEnd.getRole().getType() instanceof StructuredClassifier) {
							if (listOfInterfaceRealization
									.contains(locConnectorEnd.getRole().getOwner())) {
								connectableElements.add(locConnectorEnd.getRole().getOwner());
							} else {
								connectableElements.add(locConnectorEnd.getOwner());
							}
						} else if (listOfInterfaceRealization.contains(locConnectorEnd.getRole())) {
							connectableElements.add(locConnectorEnd.getRole());
						}
					}
				}
				connectableElements.add(role);

			}
		}
		return connectableElements;
	}

	/**
	 * Get connector targets.
	 *
	 * @param connector
	 *            Connector
	 * @param diagram
	 *            Diagram
	 * @return List of targets
	 */
	public List<Element> getConnectorTarget(Connector connector, DDiagram diagram) {
		final List<Element> connectableElements = new ArrayList<Element>();
		final List<ConnectorEnd> connectorEnds = connector.getEnds();
		if (connectorEnds != null && connectorEnds.size() > 0) {
			final ConnectorEnd connectorEnd2 = connectorEnds.get(1);
			final ConnectableElement role2 = connectorEnd2.getRole();
			// Interfaces layer
			if (isInterfacesLayerActive(diagram.getOwnedDiagramElements().get(0))) {
				if (connector.getClientDependencies().size() > 0) {
					// check if connector is between interface.
					for (final Dependency dependency : connector.getClientDependencies()) {
						if (dependency instanceof Usage || dependency instanceof InterfaceRealization) {
							final List<NamedElement> suppliers = dependency.getSuppliers();
							for (final NamedElement supplier : suppliers) {
								if (supplier instanceof Interface) {
									// result.add(supplier);
									connectableElements.add(supplier);
								}
							}
						}
					}
				} else {
					connectableElements.add(role2);
				}
			} else {
				// Interface layer is inactive provide connector between ports, parts, and components
				/*
				 * A connector could be connect to a port or to an interface. An interface could be connect to
				 * a component throw a port or directly. In this case a "public" port was created but is not
				 * displayed. We have to return the component
				 */
				/*
				 * If several components have usage or interface realization link to the same interface in
				 * this case the connector will have several ends.
				 */
		final List<NamedElement> listOfUsage = new ArrayList<NamedElement>();
				for (final Dependency dependency : connector.getClientDependencies()) {
					if (dependency instanceof Usage) {
						for (final NamedElement elem : getClient(dependency)) {
							if (elem instanceof Port || elem instanceof StructuredClassifier) {
				listOfUsage.add(elem);
							}
						}
					}
				}
		if (listOfUsage.size() > 0) {
					for (final ConnectorEnd locConnectorEnd : connectorEnds) {
						if (locConnectorEnd.getRole() instanceof Port
								&& locConnectorEnd.getRole().getType() instanceof StructuredClassifier) {
				if (listOfUsage.contains(locConnectorEnd.getRole().getOwner())) {
								connectableElements.add(locConnectorEnd.getRole().getOwner());
							} else {
								connectableElements.add(locConnectorEnd.getOwner());
							}
			} else if (listOfUsage.contains(locConnectorEnd.getRole())) {
							connectableElements.add(locConnectorEnd.getRole());
						}
					}
				}
				connectableElements.add(role2);

			}
		}
		return connectableElements;
	}

	/**
	 * Return a set of StructuredClassifier according to the parent diagram.
	 *
	 * @param diagram
	 *            current diagram
	 * @param element
	 *            element
	 * @param containerView
	 *            container
	 * @return Set of StructuredClassifier could be empty not null.
	 */
	public Set<EObject> getMappingForStructuredClassifier(DSemanticDiagram diagram, Element element,
			DSemanticDecorator containerView) {
		final Set<EObject> returnList = new HashSet<EObject>();
		if (diagram.getTarget() instanceof StructuredClassifier) {
			// this case is for diagram create under a StructuredClassifier
			if (EcoreUtil.isAncestor(diagram.getTarget(), element) && element instanceof StructuredClassifier) {
				// only children of the Structured Classifier could be displayed
				final TreeIterator<Object> iterator = EcoreUtil.getAllContents(diagram.getTarget(), true);
				while (iterator.hasNext()) {
					final EObject object = (EObject)iterator.next();
					if (object instanceof StructuredClassifier && containerView != diagram) {
						returnList.add(object);
					}
				}
			}
		} else {
			final TreeIterator<Object> iterator = UML2Util.getAllContents(element.getModel(), false, false);
			while (iterator.hasNext()) {
				final EObject object = (EObject)iterator.next();
				if (object instanceof StructuredClassifier) {
					returnList.add(object);
				}
			}
		}
		return returnList;
	}

	/**
	 * Get provided interfaces.
	 *
	 * @param diagram
	 *            Diagram
	 * @return The interfaces realized by ports visible on the diagram
	 */
	public Collection<EObject> getPortInterfaceRealization(DDiagram diagram) {
		final Set<EObject> result = Sets.newLinkedHashSet();
		if (diagram instanceof DSemanticDecorator) {
			final Session sess = SessionManager.INSTANCE
					.getSession(((DSemanticDecorator)diagram).getTarget());

			final Iterator<EObject> it = Iterators.transform(
					Iterators.filter(diagram.eAllContents(), AbstractDNode.class),
					new Function<AbstractDNode, EObject>() {

						public EObject apply(AbstractDNode input) {
							return input.getTarget();
						}
					});
			while (it.hasNext()) {
				final EObject displayedAsANode = it.next();
				if (displayedAsANode != null) {
					for (final Setting xRef : sess.getSemanticCrossReferencer()
							.getInverseReferences(displayedAsANode)) {
						EObject eObject = xRef.getEObject();
						if (eObject instanceof DNode) {
							eObject = ((DNode)eObject).getTarget();
						}
						if (sess.getModelAccessor().eInstanceOf(eObject, "Port")) { //$NON-NLS-1$
							final Port port = (Port)eObject;
							result.addAll(port.getProvideds());
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get required interfaces.
	 *
	 * @param diagram
	 *            Diagram
	 * @return The interfaces used by ports visible on the diagram
	 */
	public Collection<EObject> getPortUsage(DDiagram diagram) {
		final Set<EObject> result = Sets.newLinkedHashSet();
		if (diagram instanceof DSemanticDecorator) {
			final Session sess = SessionManager.INSTANCE
					.getSession(((DSemanticDecorator)diagram).getTarget());

			final Iterator<EObject> it = Iterators.transform(
					Iterators.filter(diagram.eAllContents(), AbstractDNode.class),
					new Function<AbstractDNode, EObject>() {

						public EObject apply(AbstractDNode input) {
							return input.getTarget();
						}
					});
			while (it.hasNext()) {
				final EObject displayedAsANode = it.next();
				if (displayedAsANode != null) {
					for (final Setting xRef : sess.getSemanticCrossReferencer()
							.getInverseReferences(displayedAsANode)) {
						EObject eObject = xRef.getEObject();
						if (eObject instanceof DNode) {
							eObject = ((DNode)eObject).getTarget();
						}
						if (sess.getModelAccessor().eInstanceOf(eObject, "Port")) { //$NON-NLS-1$
							final Port port = (Port)eObject;
							if (port.getRequireds().size() > 0) {
								result.add(port);
							}
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get related elements for composite structure diagram.
	 *
	 * @param cur
	 *            Element
	 * @return Related elements
	 */
	public Collection<EObject> getRelatedForCompositeStructure(EObject cur) {
		return new RelatedCompositeStructureElementsSwitch().getRelatedElements(cur);
	}

	/**
	 * Get the associated structured classifier.
	 *
	 * @param element
	 *            Element could be a part or a structured classifier
	 * @return The associated structured classifier
	 */
	public StructuredClassifier getStructuredClassifier(Element element) {
		if (element instanceof StructuredClassifier) {
			return (StructuredClassifier)element;
		} else if (element instanceof Property) {
			final Type type = ((Property)element).getType();
			if (type != null && type instanceof StructuredClassifier) {
				return (StructuredClassifier)type;
			}
		}
		return null;
	}

	/**
	 * Retrieve the cross references of the usage of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getUsageInverseRefs(DDiagram diagram) {
		return NodeInverseRefsServices.INSTANCE.getUsageInverseRefs(diagram);
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
		final List<EObject> result = new ArrayList<EObject>();

		for (final EObject view : views) {
			if (isInterfaceView(view)) {
				final DNode interfaceView = (DNode)view;
				final List<DEdge> edges = new ArrayList<DEdge>(interfaceView.getIncomingEdges());
				edges.addAll(interfaceView.getOutgoingEdges());
				for (final DEdge edge : edges) {
					final EObject target = edge.getTarget();
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
	 * Check that source and target are connectable. We explore recursively source generalizations to
	 * handle super type cases.
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
	 * Is the interfaces layer active.
	 *
	 * @param diagramElement
	 *            Diagram element
	 * @return True if the layer named "Interfaces" is active otherwise false
	 */
	private boolean isInterfacesLayerActive(DDiagramElement diagramElement) {
		final DDiagram diagram = diagramElement.getParentDiagram();
		for (final Layer activeLayer : diagram.getActivatedLayers()) {
			if ("Interfaces".equals(activeLayer.getName())) { //$NON-NLS-1$
				return true;
			}
		}
		return false;
	}

	/**
	 * Test if the view corresponds to an interface mapping.
	 *
	 * @param view
	 *            the view
	 * @return true if it is an interface view
	 */
	private boolean isInterfaceView(EObject view) {
		return view instanceof DNode && ((DNode)view).getTarget() != null
				&& ((DNode)view).getTarget() instanceof Interface;
	}

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
	public boolean isValidConnector(org.eclipse.uml2.uml.Connector self, AbstractDNode sourceView,
			AbstractDNode targetView) {
		if (sourceView != targetView && self.getEnds().size() > 0) {
			self.getEnds().get(0);
			self.getEnds().get(1);

			final Element source = (Element)sourceView.getTarget();
			final Element target = (Element)targetView.getTarget();
			if (isInterfacesLayerActive(sourceView)) {
				if (source instanceof Interface && target instanceof Interface) {
					final List<ConnectorEnd> ends = self.getEnds();
					final ConnectorEnd end1 = ends.get(0);
					final ConnectorEnd end2 = ends.get(1);
					final ConnectableElement role1 = end1.getRole();
					final ConnectableElement role2 = end2.getRole();
					if (role1 instanceof Port && role2 instanceof Port) {
						final Port port1 = (Port)role1;
						final List<Interface> port1Provideds = port1.getProvideds();
						final List<Interface> port1Requireds = port1.getRequireds();
						final Port port2 = (Port)role2;
						final List<Interface> port2Provideds = port2.getProvideds();
						final List<Interface> port2Requireds = port2.getRequireds();
						if (port1Provideds != null && port1Requireds != null && port2Provideds != null
								&& port2Requireds != null) {
							// port1 provided && port2 required
							if (port1Provideds.contains(source) && port2Requireds.contains(target)
									|| port1Provideds.contains(target) && port2Requireds.contains(source)
									|| port2Provideds.contains(source) && port1Requireds.contains(target)
									|| port2Provideds.contains(target)
									&& port1Requireds.contains(source)) {
								return true;
							}
						}
					}
				} else if (source instanceof Port && target instanceof Port) {
					final Port port1 = (Port)source;
					final Port port2 = (Port)target;
					return port1.getProvideds().size() == 0 && port2.getRequireds().size() == 0
							&& port2.getProvideds().size() == 0 && port1.getRequireds().size() == 0;
				}
			}
		}
		return true;
	}


	/**
	 * Check if the selected element is a valid port container.
	 *
	 * @param element
	 *            Element could be a part or a structured classifier
	 * @return True if an associated structured classifier exists
	 */
	public boolean isValidPortContainer(Element element) {
		return getStructuredClassifier(element) != null;
	}

	/**
	 * Check if is a valid port interface realization.
	 *
	 * @param source
	 *            Port
	 * @param target
	 *            Interface
	 * @return True if port provides the given interface
	 */
	public boolean isValidPortInterfaceRealization(Port source, Interface target) {
		return source.getProvideds() != null && source.getProvideds().size() > 0
				&& source.getProvideds().contains(target);
	}

	/**
	 * Check if is a valid port usage.
	 *
	 * @param source
	 *            Interface
	 * @param target
	 *            Port
	 * @return True if port uses the given interface
	 */
	public boolean isValidPortUsage(Interface source, Port target) {
		return target.getRequireds() != null && target.getRequireds().size() > 0
				&& target.getRequireds().contains(source);
	}
}
