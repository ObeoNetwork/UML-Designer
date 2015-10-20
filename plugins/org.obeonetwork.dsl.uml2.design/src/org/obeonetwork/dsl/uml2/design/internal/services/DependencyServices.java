/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class DependencyServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final DependencyServices INSTANCE = new DependencyServices();

	/**
	 * To avoid duplicate case Port to interface and Class/Component to interface we provide this service that
	 * return only the required client to ui needs.
	 *
	 * @param dependency
	 *            the dependency context
	 * @return needed clients to handle is the diagram ui
	 */
	public List<NamedElement> getClient(Dependency dependency) {
		final List<NamedElement> result = new ArrayList<NamedElement>();
		final List<NamedElement> clients = Lists
				.newArrayList(Iterables.filter(dependency.getClients(), new Predicate<EObject>() {
					public boolean apply(EObject input) {
						return !(input instanceof Connector);
					}
				}));
		if (clients.size() == 1) {
			result.addAll(clients);
			return result;
		}
		for (final NamedElement client : clients) {
			if (client instanceof Property) {
				final Property property = (Property)client;
				if (property instanceof Port) {
					final Port port = (Port)property;
					if (port.getType() != null) {
						if (port.eContainer() instanceof StructuredClassifier
								&& port.getType().equals(port.eContainer())) {
							// when the port type is the container type, add
							// directly the container.
							result.add(port.getType());
						} else if (port.isConjugated()) {
							// if conjugated add the port type and the port.
							result.add(port);
							result.add(port.getType());
						} else {
							// Else add the port
							result.add(port);
						}
					} else {
						// Else add the port
						result.add(port);
					}
				} else {
					// Else add the property
					result.add(property);
				}
			}
		}
		return result;
	}

}
