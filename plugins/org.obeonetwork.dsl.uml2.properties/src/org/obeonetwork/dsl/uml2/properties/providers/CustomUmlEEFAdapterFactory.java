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

import org.eclipse.emf.common.notify.Adapter;
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
}
