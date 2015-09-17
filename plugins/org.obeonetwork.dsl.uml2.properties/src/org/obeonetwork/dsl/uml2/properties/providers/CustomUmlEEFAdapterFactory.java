/*******************************************************************************
 * Copyright (c) 2009, 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.providers;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.obeonetwork.dsl.uml2.properties.uml.providers.SAPropertyPropertiesEditionProvider;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlEEFAdapterFactory;

public class CustomUmlEEFAdapterFactory extends UmlEEFAdapterFactory {

    @Override
    public Adapter createConnectorEndAdapter() {
        return new ConnectorEndCustomPropertiesEditionProvider();
    }

    @Override
    public Adapter createAssociationAdapter() {
        return new AssociationCustomPropertiesEditionProvider();
    }

    @Override
    public Adapter createPinAdapter() {
        return new PinCustomPropertiesEditionProvider();
    }

    @Override
    public Adapter createEObjectAdapter() {
        return new SAPropertyPropertiesEditionProvider();
    }

    @Override
    public boolean isFactoryForType(Object object) {
        return (object instanceof EObject && ((EObject) object).eClass().getEPackage() == EcorePackage.eINSTANCE || object == PropertiesEditingProvider.class) || super.isFactoryForType(object);
    }

}
