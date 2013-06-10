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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.util.UMLSwitch;

/**
 * A switch that handle the containment relation of an UML element.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ContainerSwitch extends UMLSwitch<Element> implements ILabelConstants {
	private EObject element;

	@Override
	public Element casePackage(org.eclipse.uml2.uml.Package object) {
		if (element instanceof PackageableElement) {
			object.getPackagedElements().add((PackageableElement)element);
		}
		return object;
	};

	@Override
	public Element caseComponent(Component object) {
		if (element instanceof UseCase) {
			object.getOwnedUseCases().add((UseCase)element);
		} else if (element instanceof PackageableElement) {
			object.getPackagedElements().add((PackageableElement)element);
		}
		return object;
	}

	@Override
	public Element caseStructuredClassifier(StructuredClassifier object) {
		if (element instanceof Property) {
			object.getAllAttributes().add((Property)element);
		} else if (element instanceof StructuredClassifier) {
			object.getOwnedMembers().add((StructuredClassifier)element);
		}
		return object;
	}

	@Override
	public Element caseClassifier(Classifier object) {
		if (element instanceof UseCase) {
			object.getOwnedUseCases().add((UseCase)element);
		}
		return object;
	}

	public void setContainment(EObject element) {
		this.element = element;
	}
}
