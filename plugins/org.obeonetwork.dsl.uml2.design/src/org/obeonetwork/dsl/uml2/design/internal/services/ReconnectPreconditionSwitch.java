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

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateableElement;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.Vertex;
import org.eclipse.uml2.uml.util.UMLSwitch;

/**
 * A switch that handle the edge precondition reconnections for each UML types.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class ReconnectPreconditionSwitch extends UMLSwitch<Element> {
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
	 * The new pointed element.
	 */
	private Element newPointedElement;

	/**
	 * The old pointed element.
	 */
	private Element oldPointedElement;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseAssociation(Association association) {
		if (association instanceof Extension) {
			return null;
		}
		if (newPointedElement instanceof Type) {
			return association;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseDependency(Dependency dependency) {
		if (dependency instanceof InterfaceRealization) {
			return null;
		}
		if (newPointedElement instanceof NamedElement) {
			return dependency;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseExtension(Extension extension) {
		if (RECONNECT_SOURCE == reconnectKind) {
			final ElementImport target = ExtensionServices.INSTANCE.getElementImport(extension);
			if (ExtensionServices.INSTANCE.canCreateExtension(newPointedElement, target)) {
				return extension;
			}
		} else {
			final Stereotype source = extension.getStereotype();
			if (ExtensionServices.INSTANCE.canCreateExtension(source, newPointedElement)) {
				return extension;
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseGeneralization(Generalization generalization) {
		if (newPointedElement instanceof Classifier) {
			return generalization;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseInterfaceRealization(InterfaceRealization interfaceRealization) {
		if (RECONNECT_SOURCE == reconnectKind) {
			if (newPointedElement instanceof Class) {
				return interfaceRealization;
			}
		} else {
			if (newPointedElement instanceof Interface) {
				return interfaceRealization;
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element caseTemplateBinding(TemplateBinding tmplBinding) {
		if (newPointedElement instanceof TemplateableElement && newPointedElement instanceof Classifier
				&& oldPointedElement instanceof TemplateableElement
				&& oldPointedElement instanceof Classifier) {
			return tmplBinding;
		}
		return null;
	}

	@Override
	public Element caseTransition(Transition transition) {
		Element newElement = newPointedElement;
		if (newElement instanceof Region) {
			newElement = (Element)newElement.eContainer();
		}
		if (newElement instanceof Vertex && newElement.eContainer() == oldPointedElement.eContainer()) {
			return transition;
		}
		return null;
	}

	/**
	 * Check if select end could be reconnected.
	 *
	 * @param context
	 *            edge to reconnect
	 * @return true if Select end could be reconnected to new end
	 */
	public boolean isReconnectable(Element context) {
		doSwitch(context);
		return doSwitch(context) != null;
	}

	/**
	 * Set new pointed element.
	 *
	 * @param newPointedElement
	 *            New pointed element
	 */
	public void setNewPointedElement(Element newPointedElement) {
		this.newPointedElement = newPointedElement;
	}

	/**
	 * Set old pointed element.
	 *
	 * @param oldPointedElement
	 *            Old pointed element
	 */
	public void setOldPointedElement(Element oldPointedElement) {
		this.oldPointedElement = oldPointedElement;
	}

	/**
	 * Set reconnect kind.
	 *
	 * @param reconnectKind
	 *            Reconnect kind
	 */
	public void setReconnectKind(int reconnectKind) {
		this.reconnectKind = reconnectKind;
	}
}
