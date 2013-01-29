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
package org.obeonetwork.dsl.uml2.properties.uml.parts.forms;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class GeneralCustomPropertiesEditionPartForm extends
		GeneralPropertiesEditionPartForm {

	public GeneralCustomPropertiesEditionPartForm() {
		super();
	}

	public GeneralCustomPropertiesEditionPartForm(
			IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	@Override
	protected Composite createVisibilityRadioViewer(Composite parent) {
		Composite radio = super.createVisibilityRadioViewer(parent);
		GridData visibilityData = new GridData();
		visibilityData.horizontalSpan = 2;
		visibilityRadioViewer.setLayoutData(visibilityData);
		return radio;
	}
}
