/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.profile.design.services;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.editor.presentation.UMLActionBarContributor;

/**
 * Used to invoke UML validator from UMLActionBarContributor.
 * 
 * @author Mohamed-Lamine BOUKHANOUFA <a
 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
 */
public class ValidateUMLModel extends UMLActionBarContributor {

	/**
	 * 
	 */
	public ValidateUMLModel() {
	}

	public void validateUMLmodel(EObject model) {
		model.eResource();
		IStructuredSelection selection = new StructuredSelection(model);
		validateAction.updateSelection(selection);
		validateAction.setActiveWorkbenchPart(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor());
		validateAction.run();
	}
}

