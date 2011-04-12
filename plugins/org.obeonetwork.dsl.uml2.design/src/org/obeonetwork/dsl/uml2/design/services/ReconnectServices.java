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
package org.obeonetwork.dsl.uml2.design.services;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.util.UMLSwitch;

import fr.obeo.dsl.viewpoint.DEdge;
import fr.obeo.dsl.viewpoint.EdgeTarget;


/**
 * Utility services to manage edges reconnections
 * 
 * @author St�phane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class ReconnectServices extends UMLSwitch<Element>{
	private static final int RECONNECT_SOURCE = 0;
	private static final int RECONNECT_TARGET = 1;
	
	private int reconnectKind;
	private Element oldPointedElement;
	private Element newPointedElement;
	
	/**
	 * Generic service used to process treatments on a reconnect
	 * The processing has to be defined by overriding the corresponding caseXXX
	 * @param context Element attached to the existing edge
	 * @param edgeView Represents the graphical new edge
	 * @param sourceView Represents the graphical element pointed by the edge before reconnecting
	 * @param targetView Represents the graphical element pointed by the edge after reconnecting
	 * @param source Represents the semantic element pointed by the edge before reconnecting
	 * @param target Represents the semantic element pointed by the edge after reconnecting
	 * @return the Element attached to the edge once it has been modified
	 */
	public Element reconnectEdge(Element context, DEdge edgeView,
								EdgeTarget sourceView, EdgeTarget targetView,
								Element source, Element target) {
		// The edgeview represents the new graphical edge
		// with testing of its source and target nodes we can
		// know if the user reconnected the source or the target of the edge
		if (edgeView.getSourceNode().equals(targetView)) {
			this.reconnectKind = RECONNECT_SOURCE;
		} else {
			this.reconnectKind = RECONNECT_TARGET;
		}
		this.oldPointedElement = source;
		this.newPointedElement = target;
		return doSwitch(context);
	}


	public Element caseGeneralization(Generalization generalization) {
		if (newPointedElement instanceof Classifier) {
			if (RECONNECT_SOURCE == reconnectKind) {
				generalization.setSpecific((Classifier) newPointedElement);
			} else {
				generalization.setGeneral((Classifier) newPointedElement);
			}
		}
		return generalization;
	}

	public Element caseInterfaceRealization(InterfaceRealization interfaceRealization) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Class) {
				interfaceRealization.getClients().clear();
				((Class)newPointedElement).getInterfaceRealizations().add(interfaceRealization);
			}
		} else {
			if (newPointedElement instanceof Interface) {
				interfaceRealization.getSuppliers().clear();
				interfaceRealization.getSuppliers().add((Interface) newPointedElement);
			}
		}
		return interfaceRealization;
	}

	public Element caseDependency(Dependency dependency) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Classifier) {
				dependency.getClients().clear();
				((Classifier)newPointedElement).getClientDependencies().add(dependency);
			}
		} else {
			if (newPointedElement instanceof Classifier) {
				dependency.getSuppliers().clear();
				dependency.getSuppliers().add((Classifier) newPointedElement);
			}	
		}
		return dependency;
	}

	public Element caseAssociation(Association association) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Type) {
				Property source = UMLServices.getSource(association);
				source.setType((Type) newPointedElement);
			}
		} else {
			if (newPointedElement instanceof Type) {
				Property target = UMLServices.getTarget(association);
				target.setType((Type) newPointedElement);
			}
		}
		return association;
	}
	
	public Element caseConnector(Connector connector) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof ConnectableElement) {
				connector.getEnds().get(0).setRole((ConnectableElement) newPointedElement);
			}
		} else {
			if (newPointedElement instanceof ConnectableElement) {
				connector.getEnds().get(1).setRole((ConnectableElement) newPointedElement);
			}
		}
		return connector;
	}
}
