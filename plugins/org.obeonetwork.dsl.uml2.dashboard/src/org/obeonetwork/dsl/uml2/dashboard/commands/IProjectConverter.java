/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.dashboard.commands;

import org.eclipse.core.commands.AbstractParameterValueConverter;
import org.eclipse.core.commands.ParameterValueConversionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * Used to convert an IProject to a string and inversely.
 *
 * @author Frederic Bats <a href="mailto:frederic.bats@obeo.fr">frederic.bats@obeo.fr</a>
 */
public class IProjectConverter extends AbstractParameterValueConverter {

	/**
	 * Constructor.
	 */
	public IProjectConverter() {
	}

	@Override
	public Object convertToObject(String parameterValue) throws ParameterValueConversionException {
		if (parameterValue==null || parameterValue.isEmpty()){
			return null;
		}
		final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(parameterValue);
		if (project.exists()) {
			return project;
		}
		return null;
	}

	@Override
	public String convertToString(Object parameterValue) throws ParameterValueConversionException {
		if (parameterValue instanceof String) {
			return (String)parameterValue;
		}
		return null;
	}

}
