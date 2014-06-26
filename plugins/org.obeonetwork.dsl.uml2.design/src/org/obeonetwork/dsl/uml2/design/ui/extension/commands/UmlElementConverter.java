/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.ui.extension.commands;

import java.util.Collection;

import org.eclipse.core.commands.AbstractParameterValueConverter;
import org.eclipse.core.commands.ParameterValueConversionException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.uml2.uml.NamedElement;

/**
 * Used to convert an UML model to a string and inversely.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlElementConverter extends AbstractParameterValueConverter {

	@Override
	public Object convertToObject(String parameterValue) throws ParameterValueConversionException {
		Collection<Session> sessions = SessionManager.INSTANCE.getSessions();
		for (Session session : sessions) {
			Collection<Resource> resources = session.getSemanticResources();
			for (Resource resource : resources) {
				EList<EObject> contents = resource.getContents();
				for (EObject content : contents) {
					if (content instanceof NamedElement && parameterValue != null
							&& parameterValue.equals(((NamedElement)content).getName())) {
						return content;
					}
				}
			}
		}
		return null;
	}

	@Override
	public String convertToString(Object parameterValue) throws ParameterValueConversionException {
		if (parameterValue instanceof String) {
			return (String)parameterValue;
		} else if (parameterValue instanceof NamedElement) {
			return ((NamedElement)parameterValue).getName();
		}
		return null;
	}
}
