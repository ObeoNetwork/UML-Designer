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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Usage;

/**
 * A set of services to handle the UML Composite Structure diagram.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class CompositeStructureServices {

	private static final String NOT_HANDLED = ") not handled";

	public List<InterfaceRealization> findInterfaceRealizationsToDelete(
			InterfaceRealization interfaceRealization, List<Interface> interfaces) {
		List<InterfaceRealization> result = new ArrayList<InterfaceRealization>();

		EList<NamedElement> suppliers = interfaceRealization.getSuppliers();
		for (NamedElement namedElement : suppliers) {
			if (namedElement instanceof Interface) {
				Interface anInterface = (Interface)namedElement;
				if (!interfaces.contains(anInterface)) {
					result.add(interfaceRealization);
					break;
				}
			}
		}

		return result;
	}

	public List<Usage> findUsagesToDelete(Usage usage, List<Interface> interfaces) {
		List<Usage> result = new ArrayList<Usage>();
		List<NamedElement> suppliers = usage.getSuppliers();
		for (NamedElement namedElement : suppliers) {
			if (namedElement instanceof Interface) {
				Interface anInterface = (Interface)namedElement;
				if (!interfaces.contains(anInterface)) {
					result.add(usage);
					break;
				}
			}
		}
		return result;
	}

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

	public List<EObject> getWizardServiceChildren(EObject object) {
		List<EObject> result = new ArrayList<EObject>();
		EList<EObject> eContents = object.eContents();
		for (EObject eObject : eContents) {
			if (eObject instanceof Classifier || eObject instanceof org.eclipse.uml2.uml.Package) {
				if (eObject instanceof Interface) {
					result.add(eObject);
				} else {
					for (Iterator<EObject> iterator = eObject.eAllContents(); iterator.hasNext();) {
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

	public List<EObject> getWizardRequiredServiceSelectedCandidates(EObject object) {
		List<EObject> result = new ArrayList<EObject>();

		if (object instanceof Property) {
			final Property property = (Property)object;
			if (property instanceof Port) {
				final Port port = (Port)property;
				if (port.isConjugated()) {
					// handles required interfaces that come from the type
					result.addAll(port.getRequireds());
				}
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

	public List<EObject> getWizardProvidedServiceSelectedCandidates(EObject object) {
		List<EObject> result = new ArrayList<EObject>();

		if (object instanceof Property) {
			final Property property = (Property)object;
			if (property instanceof Port) {
				final Port port = (Port)property;
				if (port.isConjugated()) {
					// handles provided interfaces that come from the type
					result.addAll(port.getProvideds());
				}
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

}
