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
package org.obeonetwork.dsl.uml2.design.services.internal;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.util.UMLSwitch;

/**
 * A switch implementation moving up the element position.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class MoveUpElementSwitch extends UMLSwitch<Boolean> {

	private static Boolean success = new Boolean(true);

	/**
	 * Move up the element position in it feature containment.
	 * 
	 * @param ctx
	 *            the element to move up
	 */
	public void moveUpElement(EObject ctx) {
		doSwitch(ctx);
	}

	@Override
	public Boolean casePackageableElement(PackageableElement packageableElement) {

		EObject eContainer = packageableElement.eContainer();

		if (eContainer instanceof org.eclipse.uml2.uml.Package) {
			EList<PackageableElement> packageableElements = ((org.eclipse.uml2.uml.Package)eContainer)
					.getPackagedElements();
			moveUpList(packageableElements, packageableElement);
			return success;
		} else if (eContainer instanceof Component) {
			EList<PackageableElement> packageableElements = ((Component)eContainer).getPackagedElements();
			moveUpList(packageableElements, packageableElement);
			return success;
		}
		return super.casePackageableElement(packageableElement);
	}

	@Override
	public Boolean caseProperty(Property property) {
		EObject eContainer = property.eContainer();

		if (eContainer instanceof StructuredClassifier) {
			EList<Property> properties = ((StructuredClassifier)eContainer).getOwnedAttributes();
			moveUpList(properties, property);
			return success;
		}
		return super.caseProperty(property);
	}

	@Override
	public Boolean caseOperation(Operation operation) {
		EObject eContainer = operation.eContainer();

		if (eContainer instanceof org.eclipse.uml2.uml.Class || eContainer instanceof Interface) {
			EList<Operation> operations = null;
			if (eContainer instanceof org.eclipse.uml2.uml.Class) {
				operations = ((org.eclipse.uml2.uml.Class)eContainer).getOwnedOperations();
			} else {
				operations = ((Interface)eContainer).getOwnedOperations();
			}
			moveUpList(operations, operation);
			return success;
		}
		return super.caseOperation(operation);
	}

	private void moveUpList(EList<?> elements, Object element) {
		if (elements.contains(element) && elements.size() > 1) {
			int oldIndex = elements.indexOf(element);
			int newIndex = oldIndex - 1;
			if (newIndex >= 0) {
				elements.move(newIndex, oldIndex);
			}
		}
	}
}
