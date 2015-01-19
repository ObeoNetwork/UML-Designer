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
package org.obeonetwork.dsl.uml2.design.internal.wizards.newmodel;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.obeonetwork.dsl.uml2.design.internal.wizards.UmlProjectUtils;

/**
 * The semantic model file creation page.
 *
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class UmlModelWizardNewModelFilePage extends AbstractUmlModelWizardNewFileCreationPage {

	/**
	 * Constructor.
	 *
	 * @param pageName
	 *            the page name
	 * @param selection
	 *            the selection
	 */
	public UmlModelWizardNewModelFilePage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getRequiredExtension() {
		return UmlProjectUtils.MODEL_FILE_EXTENSION;
	}
}
