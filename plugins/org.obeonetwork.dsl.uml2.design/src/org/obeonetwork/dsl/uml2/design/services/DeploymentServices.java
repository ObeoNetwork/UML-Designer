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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.DeploymentTarget;

/**
 * A set of services to handle deployment diagram.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DeploymentServices {
	public boolean isDeploymentTarget(EObject eObj) {
		return eObj instanceof DeploymentTarget;
	}
}
