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
package org.obeonetwork.dsl.uml2.design.internal.services;

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
	protected static class UMLValidateActionWithResult extends UMLValidateAction {
		boolean diagnosticResult = false;

		public boolean getDiagnosticResult() {
			return diagnosticResult;
		}

		@Override
		protected void handleDiagnostic(Diagnostic diagnostic) {
			super.handleDiagnostic(diagnostic);
			diagnosticResult = diagnostic.getSeverity() == Diagnostic.OK;
		}

	}

	boolean diagnosticResult = false;

	/**
	 *
	 */
	public ValidateUMLElement() {
		super();
		validateAction = new UMLValidateActionWithResult();

	}

	/**
	 * Get diagnostic result.
	 *
	 * @return Diagnostic
	 */
	public boolean getDiagnosticResult() {
		return diagnosticResult;
	}

	/**
	 * Validate an EObject using the UML validator.
	 *
	 * @param model
	 *            the EObject
	 */
	public void validateUMLmodel(EObject model) {
		final IStructuredSelection selection = new StructuredSelection(model);
		validateAction.updateSelection(selection);
		validateAction.setActiveWorkbenchPart(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor());
		validateAction.run();
		diagnosticResult = ((UMLValidateActionWithResult)validateAction).getDiagnosticResult();
	}

}

