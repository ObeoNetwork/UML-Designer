/*******************************************************************************
 * Copyright (c) 2009, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.providers;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.uml2.uml.Pin;
import org.obeonetwork.dsl.uml2.properties.components.PinCustomPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.components.PinPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.providers.PinPropertiesEditionProvider;

public class PinCustomPropertiesEditionProvider extends
		PinPropertiesEditionProvider {
	@Override
	public IPropertiesEditionComponent getPropertiesEditingComponent(
			PropertiesEditingContext editingContext, String mode) {
		if (editingContext.getEObject() instanceof Pin) {
			return new PinCustomPropertiesEditionComponent(editingContext,
					editingContext.getEObject(), mode);
		}
		return super.getPropertiesEditingComponent(editingContext, mode);
	}

	@Override
	public IPropertiesEditionComponent getPropertiesEditingComponent(
			PropertiesEditingContext editingContext, String mode, String part) {
		if (editingContext.getEObject() instanceof Pin) {
			if (PinPropertiesEditionComponent.GENERAL_PART.equals(part))
				return new PinCustomPropertiesEditionComponent(editingContext,
						editingContext.getEObject(), mode);
		}
		return super.getPropertiesEditingComponent(editingContext, mode, part);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public IPropertiesEditionComponent getPropertiesEditingComponent(
			PropertiesEditingContext editingContext, String mode, String part,
			java.lang.Class refinement) {
		if (editingContext.getEObject() instanceof Pin) {
			if (PinPropertiesEditionComponent.GENERAL_PART.equals(part)
					&& refinement == PinPropertiesEditionComponent.class)
				return new PinCustomPropertiesEditionComponent(editingContext,
						editingContext.getEObject(), mode);
		}
		return super.getPropertiesEditingComponent(editingContext, mode, part,
				refinement);
	}
}
