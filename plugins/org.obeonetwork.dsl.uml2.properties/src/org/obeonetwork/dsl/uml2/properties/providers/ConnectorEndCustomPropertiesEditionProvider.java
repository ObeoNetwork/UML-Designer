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
import org.eclipse.uml2.uml.ConnectorEnd;
import org.obeonetwork.dsl.uml2.properties.uml.components.ConnectorEndCustomPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.components.ConnectorEndPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.providers.ConnectorEndPropertiesEditionProvider;

public class ConnectorEndCustomPropertiesEditionProvider extends ConnectorEndPropertiesEditionProvider {
    @Override
    public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext, String mode, String part, java.lang.Class refinement) {
        if (editingContext.getEObject() instanceof ConnectorEnd) {
            if (ConnectorEndPropertiesEditionComponent.GENERAL_PART.equals(part) && refinement == ConnectorEndPropertiesEditionComponent.class)
                return new ConnectorEndCustomPropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
        }
        return super.getPropertiesEditingComponent(editingContext, mode, part, refinement);
    }

    @Override
    public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext, String mode, String part) {
        if (editingContext.getEObject() instanceof ConnectorEnd) {
            if (ConnectorEndPropertiesEditionComponent.GENERAL_PART.equals(part))
                return new ConnectorEndCustomPropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
        }
        return super.getPropertiesEditingComponent(editingContext, mode, part);
    }

    @Override
    public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext, String mode) {
        if (editingContext.getEObject() instanceof ConnectorEnd) {
            return new ConnectorEndCustomPropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
        }
        return super.getPropertiesEditingComponent(editingContext, mode);
    }
}
