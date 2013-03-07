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
package org.obeonetwork.dsl.uml2.properties.uml.providers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.obeonetwork.dsl.uml2.properties.uml.parts.RelationshipsPropertiesEditionPart;

/**
 * Provides the filter used by the plugin.xml to assign
 * {@link RelationshipsPropertiesEditionPart} to an element.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class RelationshipsEditionFilter implements IFilter {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
	 */
	public boolean select(Object toTest) {
		EObject eObj = EEFUtils.resolveSemanticObject(toTest);
		return eObj != null
				&& (eObj instanceof Class || eObj instanceof Interface
						|| eObj instanceof DataType
						|| eObj instanceof PrimitiveType
						|| eObj instanceof Enumeration
						|| eObj instanceof Operation
						|| eObj instanceof Parameter
						|| eObj instanceof Property
						|| eObj instanceof Component || eObj instanceof Port
						|| eObj instanceof Actor || eObj instanceof Artifact
						|| eObj instanceof CallOperationAction || eObj instanceof Collaboration);
	}

}
