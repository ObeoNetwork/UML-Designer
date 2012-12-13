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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.Usage;

import fr.obeo.dsl.common.tools.api.util.Option;
import fr.obeo.dsl.viewpoint.DEdge;
import fr.obeo.dsl.viewpoint.DNode;
import fr.obeo.dsl.viewpoint.DNodeContainer;
import fr.obeo.dsl.viewpoint.DSemanticDecorator;
import fr.obeo.dsl.viewpoint.EdgeTarget;
import fr.obeo.dsl.viewpoint.business.api.query.EObjectQuery;
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
	 * @param anInterface the interface
	 * @param aPort the port
	 * @return the boolean result
	 */
	public boolean checkRequiredInterface(Interface anInterface, Port aPort) {
		return aPort.getRequireds().contains(anInterface);
	}
	
	/**
	 * Check if an interface is provided for a specific port.
	 * @param anInterface the interface
	 * @param aPort 
	 * @return the boolean result
	 */
	public boolean checkProvidedInterface(Interface anInterface, Port aPort) {
		return aPort.getProvideds().contains(anInterface);
	}
	
	/**
	 * Connect two elements with a connector.
	 * 
	 * Enable features :
	 * From Interface to Interface
	 * From Property to Property
	 * From Interface to Port (Delegation)
	 * 
	 * @param sourceView the current source view
	 * @param targetView the current target view
	 */
	public void createConnector(DNode sourceView, DNode targetView) {

		final EObject source = sourceView.getTarget();
		final EObject target = targetView.getTarget();

		if (source instanceof Interface) {
			
			final StructuredClassifier structuredClassifier = getFirstStructuredClassifierRelated2InterfaceView(sourceView);
			
			if (target instanceof Interface) {
				// Make a new connector From Interface to Interface
				connectInterface2Interface(structuredClassifier, sourceView, (Interface)source, targetView, (Interface)target);
			} else if (target instanceof Port) {
				// Make a new connector From Interface to Port
				connectInterface2Port(structuredClassifier, sourceView, (Interface)source, targetView, (Port)target);
			}
		} else if (source instanceof Property && target instanceof Property) {

			final EObject eContainer = source.eContainer();
			if (eContainer instanceof StructuredClassifier) {
				final StructuredClassifier structuredClassifier = (StructuredClassifier)eContainer;
				// Make a new connector From Property to Property
				connectProperty2Property(structuredClassifier, (Property)source, (Property)target);
			}
		}
	}
	
	/**
	 * Get the first Structured Classifier related to this interface view.
	 * 
	 * @param interfaceView the interface view
	 * @return the first Structured Classifier found
	 */
	private StructuredClassifier getFirstStructuredClassifierRelated2InterfaceView(DNode interfaceView) {
		
		// [sourceView.oclAsType(viewpoint::DNode).incomingEdges.sourceNode.eContainer(viewpoint::DNodeContainer).target->flatten()->first()/]
		final List<DEdge> incomingEdges = interfaceView.getIncomingEdges();
		for (DEdge dEdge : incomingEdges) {
			final EdgeTarget sourceNode = dEdge.getSourceNode();
			final EObject eContainer = sourceNode.eContainer();
			if (eContainer instanceof DNodeContainer) {
				final DNodeContainer dNodeContainer = (DNodeContainer)eContainer;
				final EObject target = dNodeContainer.getTarget();
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
	 * @param structuredClassifier the connector container
	 * @param sourceView the interface source view
	 * @param iSource the interface source
	 * @param targetView the interface target view
	 * @param iTarget the interface target
	 * @return the new connector
	 */
	private Connector connectInterface2Interface(StructuredClassifier structuredClassifier, DNode sourceView, Interface iSource, DNode targetView, Interface iTarget) {

		final Connector connector = createConnector(structuredClassifier, (Interface)iSource, (Interface)iTarget);

		// add ConnectorEnd
		final Set<DEdge> incomingEdges = new HashSet<DEdge>();
		incomingEdges.addAll(sourceView.getIncomingEdges());
		incomingEdges.addAll(targetView.getIncomingEdges());
		
		for (DEdge dEdge : incomingEdges) {
			final DNode sourceNode = (DNode)dEdge.getSourceNode();
			final EObject targetSourceNode = sourceNode.getTarget();
			if (targetSourceNode instanceof Port) {
				final ConnectorEnd connectorEnd = connector.createEnd();
				connectorEnd.setRole((Port)targetSourceNode);
			}
		}

		// set Dependencies
		// [connector.eContainer(uml::Package).eAllContents(uml::Dependency)
		final Option<EObject> packageContext = new EObjectQuery(connector).getFirstAncestorOfType(UMLFactory.eINSTANCE.getUMLPackage().getPackage());
		if (packageContext.some()) {
			final Package eContainerPackage = (Package)packageContext.get();
			
			final EList<Dependency> dependencies = connector.getClientDependencies();
			final Iterator<EObject> eAllContents = eContainerPackage.eAllContents();
			while (eAllContents.hasNext()) {
				final EObject eObject = (EObject)eAllContents.next();
				if (eObject instanceof Dependency) {
					final Dependency dependency = (Dependency)eObject;
					//select(aDependency : uml::Dependency | ((aDependency.oclIsKindOf(uml::Usage) or aDependency.oclIsKindOf(uml::InterfaceRealization)) and
					if (dependency instanceof Usage) {
						//  (aDependency.supplier->includes(source) or (aDependency.supplier->includes(target))) ))/]
						final List<NamedElement> suppliers = dependency.getSuppliers();
						if (suppliers.contains(iSource)) {
							dependencies.add(dependency);
						}
					} else if (dependency instanceof InterfaceRealization) {
						//  (aDependency.supplier->includes(source) or (aDependency.supplier->includes(target))) ))/]
						final List<NamedElement> suppliers = dependency.getSuppliers();
						if (suppliers.contains(iTarget)) {
							dependencies.add(dependency);
						}
					}
				}
			}
		}
		return connector;
	}
	
	/**
	 * Create a connector from an interface to a port.
	 * 
	 * @param structuredClassifier the connector container
	 * @param sourceView the interface source view
	 * @param iSource the interface source
	 * @param targetView the port target view
	 * @param pTarget the port target
	 * @return the new connector
	 */
	private Connector connectInterface2Port(StructuredClassifier structuredClassifier, DNode sourceView, Interface iSource, DNode targetView, Port pTarget) {
		
		final Connector connector = createConnector(structuredClassifier, iSource, pTarget);

		final Set<DEdge> incomingEdges = new HashSet<DEdge>();
		incomingEdges.addAll(sourceView.getIncomingEdges());
		
		// add ConnectorEnd
		for (DEdge dEdge : incomingEdges) {
			final DNode sourceNode = (DNode)dEdge.getSourceNode();
			final EObject targetSourceNode = sourceNode.getTarget();
			if (targetSourceNode instanceof Port) {
				final ConnectorEnd connectorEnd = connector.createEnd();
				connectorEnd.setRole((Port)targetSourceNode);
			}
		}
		
		final ConnectorEnd iTargetConnectorEnd = connector.createEnd();
		iTargetConnectorEnd.setRole(pTarget);
		
		// set Dependencies
		// [connector.eContainer(uml::Package).eAllContents(uml::Dependency)
		final Option<EObject> packageContext = new EObjectQuery(connector).getFirstAncestorOfType(UMLFactory.eINSTANCE.getUMLPackage().getPackage());
		if (packageContext.some()) {
			final Package eContainerPackage = (Package)packageContext.get();
			
			final EList<Dependency> dependencies = connector.getClientDependencies();
			final Iterator<EObject> eAllContents = eContainerPackage.eAllContents();
			while (eAllContents.hasNext()) {
				final EObject eObject = (EObject)eAllContents.next();
				if (eObject instanceof Dependency) {
					final Dependency dependency = (Dependency)eObject;
					if ((checkProvidedInterface(iSource, pTarget) && dependency instanceof InterfaceRealization)
							|| (checkRequiredInterface(iSource, pTarget) && dependency instanceof Usage)) {
						final List<NamedElement> suppliers = dependency.getSuppliers();
						if (suppliers.contains(iSource)) {
							dependencies.add(dependency);
						}
					}
				}
			}
		}
		return connector;
	}
	
	/**
	 * Create a connector between two properties.
	 * 
	 * @param structuredClassifier the connector container
	 * @param pSource the property source view
	 * @param pTarget the property target
	 * @return the new connector
	 */
	private Connector connectProperty2Property(StructuredClassifier structuredClassifier, Property pSource, Property pTarget) {

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
	 * @param structuredClassifier the connector container
	 * @param elem1 the first element
	 * @param elem2 the second element
	 * @return a new named connector
	 */
	private Connector createConnector(StructuredClassifier structuredClassifier, NamedElement elem1, NamedElement elem2) {
		
		final Connector connector = structuredClassifier.createOwnedConnector(elem1.getName() + "_" + elem2.getName() + "_connector");
		
		return connector;
	}
}
