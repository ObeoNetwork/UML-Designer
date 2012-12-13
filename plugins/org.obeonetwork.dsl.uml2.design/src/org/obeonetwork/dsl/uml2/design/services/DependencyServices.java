/*******************************************************************************
 * Copyright (c) 2009, 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.services;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.NamedElement;


/**
 * Utility services to manage operation creation.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public final class DependencyServices {

	public void removeSupplier(org.eclipse.uml2.uml.Dependency anDependency, org.eclipse.uml2.uml.NamedElement supplier) {
		
		EList<NamedElement> suppliers = anDependency.getSuppliers();
		suppliers.remove(supplier);
	}

}