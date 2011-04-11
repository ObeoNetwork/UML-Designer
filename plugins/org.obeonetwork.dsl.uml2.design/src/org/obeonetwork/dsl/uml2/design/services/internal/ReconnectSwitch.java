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

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.obeonetwork.dsl.uml2.design.services.UMLServices;

public class ReconnectSwitch extends UMLSwitch<Element> {
	public static final int RECONNECT_SOURCE = 0;
	public static final int RECONNECT_TARGET = 1;
	
	private int reconnectKind;
	private Element oldPointedElement;
	private Element newPointedElement;
	
	public void setReconnectKind(int reconnectKind) {
		this.reconnectKind = reconnectKind;
	}
	
	public void setOldPointedElement(Element oldPointedElement) {
		this.oldPointedElement = oldPointedElement;
	}
	
	public void setNewPointedElement(Element newPointedElement) {
		this.newPointedElement = newPointedElement;
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
}
