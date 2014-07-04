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

import org.eclipse.emf.common.util.Diagnostic;
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
public class ValidateUMLElement extends UMLActionBarContributor {
	boolean diagnosticResult = false;

	protected UMLValidateActionWithResult validateAction;

	/**
	 * 
	 */
	public ValidateUMLElement() {
		super();
		validateAction = new UMLValidateActionWithResult();

	}

	/**
	 * Validate an EObject using the UML validator.
	 * 
	 * @param model
	 *            the EObject
	 */
	public void validateUMLmodel(EObject model) {
		IStructuredSelection selection = new StructuredSelection(model);
		validateAction.updateSelection(selection);
		validateAction.setActiveWorkbenchPart(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor());
		validateAction.run();
		diagnosticResult = validateAction.getDiagnisticResult();
	}

	public boolean getDiagnisticResult() {
		return diagnosticResult;
	}

	protected static class UMLValidateActionWithResult extends UMLValidateAction {
		boolean diagnosticResult = false;

		@Override
		protected void handleDiagnostic(Diagnostic diagnostic) {
			super.handleDiagnostic(diagnostic);
			diagnosticResult = diagnostic.getSeverity() == Diagnostic.OK;
		}

		public boolean getDiagnisticResult() {
			return diagnosticResult;
		}

	}

}

