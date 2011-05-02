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
package org.obeonetwork.dsl.uml2.design.ui.wizards.newmodel;

import org.eclipse.jface.viewers.IStructuredSelection;

public class UmlModelWizardNewSessionFilePage extends AbstractUmlModelWizardNewFileCreationPage {

	public UmlModelWizardNewSessionFilePage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
	}
	
	protected String getRequiredExtension() {
		return UmlModelWizard.SESSION_FILE_EXTENSION;
	}
}
