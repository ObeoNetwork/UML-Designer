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
package org.obeonetwork.dsl.uml2.design.services.internal;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.util.UMLSwitch;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

/**
 * A switch implementation retrieving all the elements which might be related to a single one.
 * 
 * @author Cedric Brun <a href="mailto:cedric.brun@obeo.fr">cedric.brun@obeo.fr</a>
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class RelatedCompositeStructureElementsSwitch extends UMLSwitch<List<EObject>> {

	private Set<EObject> relateds;

	private Collection<Setting> xRefs;

	private ECrossReferenceAdapter referencer;

	public RelatedCompositeStructureElementsSwitch() {

	}

	public RelatedCompositeStructureElementsSwitch(ECrossReferenceAdapter xRef) {
		this.referencer = xRef;

	}

	public List<EObject> getRelatedElements(EObject ctx) {
		Session sess = SessionManager.INSTANCE.getSession(ctx);
		relateds = Sets.newLinkedHashSet();
		if (sess != null) {
			xRefs = sess.getSemanticCrossReferencer().getInverseReferences(ctx);
		} else if (referencer != null) {
			xRefs = referencer.getInverseReferences(ctx);
		}
		doSwitch(ctx);
		relateds.remove(ctx);
		// hack to prevent some null element in relateds for a unknown reason.
		relateds.remove(null);

		return ImmutableList.copyOf(relateds);
	}

	@Override
	public List<EObject> casePackageableElement(PackageableElement object) {
		final List<Element> ownedElements = object.getOwnedElements();
		for (Element ownedElement : ownedElements) {
			if (ownedElement instanceof StructuredClassifier) {
				if (!(ownedElement instanceof Interaction || ownedElement instanceof StateMachine || ownedElement instanceof Activity)) {
					relateds.add(ownedElement);
				}
			}
		}
		return super.casePackageableElement(object);
	}

	@Override
	public List<EObject> caseDependency(Dependency object) {
		if (object instanceof InterfaceRealization || object instanceof Usage) {
			List<NamedElement> clients = object.getClients();
			for (NamedElement client : clients) {
				if (client instanceof Connector) {
					caseConnector((Connector)client);
				}
			}
		}
		return super.caseDependency(object);
	}

	@Override
	public List<EObject> casePort(Port object) {

		relateds.add(object.eContainer());
		List<Dependency> clientDependencies = object.getClientDependencies();

		for (Dependency clientDependency : clientDependencies) {
			List<NamedElement> clients = clientDependency.getClients();
			for (NamedElement client : clients) {
				if (client instanceof Connector) {
					caseConnector((Connector)client);
				}
			}
		}
		return super.casePort(object);
	}

	@Override
	public List<EObject> caseStructuredClassifier(StructuredClassifier object) {

		relateds.addAll(object.getOwnedAttributes());

		final List<Element> ownedElements = object.getOwnedElements();
		for (Element ownedElement : ownedElements) {
			if (ownedElement instanceof StructuredClassifier) {
				relateds.add(ownedElement);
			} else if (ownedElement instanceof Property) {
				relateds.add(ownedElement);
			}
		}

		return super.caseStructuredClassifier(object);
	}

	@Override
	public List<EObject> caseEncapsulatedClassifier(EncapsulatedClassifier object) {
		for (Port port : object.getOwnedPorts()) {
			if (!relateds.contains(port)) {
				relateds.add(port);
				casePort(port);
				caseConnectableElement(port);
			}
		}
		return super.caseEncapsulatedClassifier(object);
	}

	@Override
	public List<EObject> caseConnectableElement(ConnectableElement object) {

		for (ConnectorEnd end : object.getEnds()) {
			EObject connector = end.eContainer();
			if (connector != null && connector instanceof Connector && !relateds.contains(connector)) {
				relateds.add(connector);
				caseConnector((Connector)connector);
			}
		}

		return super.caseConnectableElement(object);
	}

	@Override
	public List<EObject> caseConnector(Connector object) {
		List<ConnectorEnd> ends = object.getEnds();
		for (ConnectorEnd end : ends) {
			ConnectableElement role = end.getRole();
			if (role != null && !relateds.contains(role)) {
				relateds.add(role);
				if (role instanceof Port) {
					relateds.add(((Port)role).getOwner());
				}
			}
		}
		return super.caseConnector(object);
	}
}
