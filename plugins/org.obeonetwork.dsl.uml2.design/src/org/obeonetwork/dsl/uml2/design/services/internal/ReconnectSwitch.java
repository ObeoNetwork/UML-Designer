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

import java.util.List;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ComponentRealization;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.obeonetwork.dsl.uml2.design.services.UMLServices;

/**
 * A switch that handle the edge reconnections for each UML types.
 * 
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class ReconnectSwitch extends UMLSwitch<Element> {
	/**
	 * Source reconnection kind constant.
	 */
	public static final int RECONNECT_SOURCE = 0;

	/**
	 * Target reconnection kind constant.
	 */
	public static final int RECONNECT_TARGET = 1;

	/**
	 * The current reconnection kind.
	 */
	private int reconnectKind;

	/**
	 * The old pointed element.
	 */
	private Element oldPointedElement;

	/**
	 * The new pointed element.
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseGeneralization(Generalization generalization) {
		if (newPointedElement instanceof Classifier) {
			if (RECONNECT_SOURCE == reconnectKind) {
				generalization.setSpecific((Classifier)newPointedElement);
			} else {
				generalization.setGeneral((Classifier)newPointedElement);
			}
		}
		return generalization;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseInterfaceRealization(InterfaceRealization interfaceRealization) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Class) {
				interfaceRealization.getClients().clear();
				((Class)newPointedElement).getInterfaceRealizations().add(interfaceRealization);
			}
		} else {
			if (newPointedElement instanceof Interface) {
				interfaceRealization.getSuppliers().clear();
				interfaceRealization.getSuppliers().add((Interface)newPointedElement);
			}
		}
		return interfaceRealization;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseDependency(Dependency dependency) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Classifier) {
				dependency.getClients().clear();
				((Classifier)newPointedElement).getClientDependencies().add(dependency);
			}
		} else {
			if (newPointedElement instanceof Classifier) {
				dependency.getSuppliers().clear();
				dependency.getSuppliers().add((Classifier)newPointedElement);
			}
		}
		return dependency;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseComponentRealization(ComponentRealization cRealization) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Classifier) {
				Classifier realizingClassifier = (Classifier)newPointedElement;
				cRealization.getRealizingClassifiers().clear();
				cRealization.getRealizingClassifiers().add(realizingClassifier);
				cRealization.setName(realizingClassifier.getName() + "To"
						+ cRealization.getAbstraction().getName());
			}
		} else {
			if (newPointedElement instanceof Component) {
				Component abstraction = (Component)newPointedElement;
				List<Classifier> realizingClassifiers = cRealization.getRealizingClassifiers();
				String realizingClassifierName = "";
				if (!realizingClassifiers.isEmpty()) {
					realizingClassifierName = realizingClassifiers.get(0).getName();
				}
				cRealization.setAbstraction(abstraction);
				cRealization.setName(realizingClassifierName + "To" + abstraction.getName());
			}
		}
		return cRealization;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseExtension(Extension extension) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Stereotype) {
				for (int i = 0; i < extension.getMemberEnds().size(); i++) {
					if (extension.getMemberEnds().get(i) instanceof ExtensionEnd) {
						((ExtensionEnd)extension.getMemberEnds().get(i)).setType((Type)newPointedElement);
					} else {
						((Stereotype)newPointedElement).getOwnedAttributes().add(0,
								extension.getMemberEnds().get(i));
					}
				}
			}
		} else {
			if (newPointedElement instanceof ElementImport) {
				Property baseProperty = extension.getStereotype().getOwnedAttribute(
						"base_" + extension.getMetaclass().getName(), extension.getMetaclass());
				Class newPointedMetaClass = (Class)((ElementImport)newPointedElement).getImportedElement();

				if (baseProperty != null && newPointedMetaClass != null) {
					baseProperty.setType((Class)((ElementImport)newPointedElement).getImportedElement());
					baseProperty.setName("base_" + newPointedMetaClass.getName());
				}
			}
		}
		return extension;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseAssociation(Association association) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Type) {
				final Property source = new UMLServices().getSource(association);
				source.setType((Type)newPointedElement);
			}
		} else {
			if (newPointedElement instanceof Type) {
				final Property target = new UMLServices().getTarget(association);
				target.setType((Type)newPointedElement);
			}
		}
		return association;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseConnector(Connector connector) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof ConnectableElement) {
				connector.getEnds().get(0).setRole((ConnectableElement)newPointedElement);
			}
		} else {
			if (newPointedElement instanceof ConnectableElement) {
				connector.getEnds().get(1).setRole((ConnectableElement)newPointedElement);
			}
		}
		return connector;
	}
}
