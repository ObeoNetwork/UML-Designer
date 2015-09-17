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
package org.obeonetwork.dsl.uml2.properties.uml.components;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

public class ConnectorEndCustomPropertiesEditionComponent extends ConnectorEndPropertiesEditionComponent {

    public ConnectorEndCustomPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject connectorEnd, String editing_mode) {
        super(editingContext, connectorEnd, editing_mode);
    }

    @Override
    public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
        setInitializing(true);
        if (editingPart != null && key == partKey) {
            editingPart.setContext(elt, allResource);

            final ConnectorEnd connectorEnd = (ConnectorEnd) elt;
            final GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart) editingPart;
            // init values
            generalPart.setOrdered(connectorEnd.isOrdered());

            generalPart.setUnique(connectorEnd.isUnique());

            if (isAccessible(UmlViewsRepository.General.role)) {
                // init part
                EObjectFlatComboSettings roleSettings = new EObjectFlatComboSettings(connectorEnd, UMLPackage.eINSTANCE.getConnectorEnd_Role());
                generalPart.initRole(roleSettings);
                // set the button mode
                generalPart.setRoleButtonMode(ButtonsModeEnum.BROWSE);
            }
            // init filters

            // init values for referenced views

            // init filters for referenced views

        }
        setInitializing(false);
    }
}
