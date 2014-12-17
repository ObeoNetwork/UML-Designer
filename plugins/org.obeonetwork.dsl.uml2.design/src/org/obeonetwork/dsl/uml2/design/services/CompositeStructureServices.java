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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
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

/**
 * A set of services to handle the UML Composite Structure diagram.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class CompositeStructureServices {

	private static final String NOT_HANDLED = ") not handled";

	/**
	 * This query return all available candidates for the wizard tool.
	 * 
	 * @param object
	 *            the wizard context.
	 * @return all available candidates
	 */
	public List<EObject> getWizardServiceCandidateExpression(EObject object) {
		List<EObject> result = new ArrayList<EObject>();
		UMLServices service = new UMLServices();
		List<EObject> validsForCompositeDiagram = service.getValidsForCompositeDiagram(object);
		for (EObject eObject : validsForCompositeDiagram) {
			if (eObject instanceof Classifier || eObject instanceof org.eclipse.uml2.uml.Package) {
				result.add(eObject);
			}
		}

		return result;
	}

	/**
	 * This query return all available children for the wizard tool.
	 * 
	 * @param object
	 *            the wizard context.
	 * @return all available children
	 */
	public List<EObject> getWizardServiceChildren(EObject object) {
		List<EObject> result = new ArrayList<EObject>();
		List<EObject> eContents = object.eContents();
		for (EObject eObject : eContents) {
			if (eObject instanceof Classifier || eObject instanceof org.eclipse.uml2.uml.Package) {
				if (eObject instanceof Interface) {
					result.add(eObject);
				} else {
					Iterator<EObject> iterator = eObject.eAllContents();
					while (iterator.hasNext()) {
						EObject eSubObject = (EObject)iterator.next();
						if (eSubObject instanceof Interface) {
							result.add(eObject);
							break;
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * This query return all required services already selected.
	 * 
	 * @param object
	 *            the wizard context.
	 * @return all required services already selected
	 */
	public List<EObject> getWizardRequiredServiceSelectedCandidates(EObject object) {
		List<EObject> result = new ArrayList<EObject>();

		if (object instanceof Property) {
			final Property property = (Property)object;
			if (property instanceof Port) {
				final Port port = (Port)property;
				result.addAll(port.getRequireds());
			}
			// Handles required interfaces that come from InterfaceRealizations
			List<Dependency> clientDependencies = property.getClientDependencies();
			for (Dependency dependency : clientDependencies) {
				if (dependency instanceof Usage) {
					result.addAll(dependency.getSuppliers());
				}
			}
		} else if (object instanceof BehavioredClassifier) {
			List<Dependency> dependencies = ((BehavioredClassifier)object).getClientDependencies();
			// Get only Usage that does not come from a property
			for (Dependency dependency : dependencies) {
				if (dependency instanceof Usage) {
					boolean isCommingFromAProperty = false;
					List<NamedElement> clients = dependency.getClients();
					for (NamedElement client : clients) {
						if (client instanceof Property) {
							isCommingFromAProperty = true;
							break;
						}
					}
					if (!isCommingFromAProperty) {
						result.addAll(dependency.getSuppliers());
					}
				}
			}
		}
		return result;
	}

	/**
	 * This query return all provided services already selected.
	 * 
	 * @param object
	 *            the wizard context.
	 * @return all provided services already selected
	 */
	public List<EObject> getWizardProvidedServiceSelectedCandidates(EObject object) {
		List<EObject> result = new ArrayList<EObject>();

		if (object instanceof Property) {
			final Property property = (Property)object;
			if (property instanceof Port) {
				final Port port = (Port)property;
				result.addAll(port.getProvideds());
			}
			// Handles provided interfaces that come from InterfaceRealizations
			List<Dependency> clientDependencies = property.getClientDependencies();
			for (Dependency dependency : clientDependencies) {
				if (dependency instanceof InterfaceRealization) {
					result.addAll(dependency.getSuppliers());
				}
			}
		} else if (object instanceof BehavioredClassifier) {
			List<InterfaceRealization> interfaceRealizations = ((BehavioredClassifier)object)
					.getInterfaceRealizations();
			// Get only InterfaceRealizations that does not come from a property
			for (InterfaceRealization interfaceRealization : interfaceRealizations) {

				boolean isCommingFromAProperty = false;
				List<NamedElement> clients = interfaceRealization.getClients();
				for (NamedElement client : clients) {
					if (client instanceof Property) {
						isCommingFromAProperty = true;
						break;
					}
				}
				if (!isCommingFromAProperty) {
					result.addAll(interfaceRealization.getSuppliers());
				}
			}
		} else {
			new LogServices().error("CompositeStructureServices.getWizardProvidedServiceSelectedCandidates("
					+ object.getClass() + NOT_HANDLED, null);
		}
		return result;
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
			Type type = ((Property)element).getType();
			if (type != null && type instanceof StructuredClassifier) {
				return (StructuredClassifier)type;
			}
		}
		return null;
	}

	/**
	 * Check if the selected element is a valid port container.
	 * 
	 * @param element
	 *            Element could be a part or a structured classifier
	 * @return True if an associated structured classifier exists
	 */
	public Boolean isValidPortContainer(Element element) {
		return getStructuredClassifier(element) != null;
	}

	public List<Element> getConnectorSource(Connector connector, DDiagram diagram) {
		List<Element> connectableElements = new ArrayList<Element>();
		List<ConnectorEnd> connectorEnds = connector.getEnds();
		if (connectorEnds != null && connectorEnds.size() >0) {
			ConnectorEnd connectorEnd = connectorEnds.get(0);
			ConnectableElement role = connectorEnd.getRole();
			ConnectorEnd connectorEnd2 = connectorEnds.get(1);
			ConnectableElement role2 = connectorEnd2.getRole();
			// Interfaces layer
			if (UIConnectorServices.isInterfacesLayerActive(diagram.getOwnedDiagramElements().get(0))) {
				if (role instanceof Port && ((Port)role).getProvideds().size() > 0) {
					connectableElements.addAll(((Port)role).getProvideds());
				} else if (role2 instanceof Port && ((Port)role2).getProvideds().size() > 0) {
					connectableElements.addAll(((Port)role2).getProvideds());
				}
				if (connectableElements.size() != 0) {
					return connectableElements;
				}
			}
			// Default layer or no interfaces defined
			connectableElements.add(role);
		}

		return connectableElements;
	}

	public List<Element> getConnectorTarget(Connector connector, DDiagram diagram) {
		List<Element> connectableElements = new ArrayList<Element>();
		List<ConnectorEnd> connectorEnds = connector.getEnds();
		if (connectorEnds != null && connectorEnds.size() >0) {
			ConnectorEnd connectorEnd = connectorEnds.get(0);
			ConnectableElement role = connectorEnd.getRole();
			ConnectorEnd connectorEnd2 = connectorEnds.get(1);
			ConnectableElement role2 = connectorEnd2.getRole();
			// Interfaces layer
			if (UIConnectorServices.isInterfacesLayerActive(diagram.getOwnedDiagramElements().get(0))) {
				if (role instanceof Port && ((Port)role).getRequireds().size() > 0) {
					connectableElements.addAll(((Port)role).getRequireds());
				} else if (role2 instanceof Port && ((Port)role2).getRequireds().size() > 0) {
					connectableElements.addAll(((Port)role2).getRequireds());
				}
				if (connectableElements.size() != 0) {
					return connectableElements;
				}
			}
			// Default layer or no interfaces defined
			connectableElements.add(role2);
		}

		return connectableElements;
	}

	public boolean isValidPortUsage(Interface source, Port target) {
		return target.getRequireds() != null && target.getRequireds().size() > 0
				&& target.getRequireds().contains(source);
	}

	public boolean isValidPortInterfaceRealization(Port source, Interface target) {
		return source.getProvideds() != null && source.getProvideds().size() > 0
				&& source.getProvideds().contains(target);
	}
}
