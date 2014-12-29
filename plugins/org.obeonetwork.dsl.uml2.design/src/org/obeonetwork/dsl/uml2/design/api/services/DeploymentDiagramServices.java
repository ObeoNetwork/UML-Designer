/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.api.services;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.uml2.uml.DeploymentTarget;
import org.obeonetwork.dsl.uml2.design.internal.services.NodeInverseRefsServices;

/**
 * A set of services to handle the Deployment diagram.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DeploymentDiagramServices extends AbstractDiagramServices {
	/**
	 * Retrieve the cross references of the dependency of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getDependencyOrManifestationInverseRefs(DDiagram diagram) {
		return NodeInverseRefsServices.INSTANCE.getDependencyOrManifestationInverseRefs(diagram);
	}

	/**
	 * Retrieve the cross references of the deployment of all the UML elements displayed as node in a Diagram.
	 * Note that a Property cross reference will lead to retrieve the cross references of this property.
	 *
	 * @param diagram
	 *            a diagram.
	 * @return the list of cross reference of the given
	 */
	public Collection<EObject> getDeploymentInverseRefs(DDiagram diagram) {
		return NodeInverseRefsServices.INSTANCE.getDeploymentInverseRefs(diagram);
	}

	/**
	 * Check if element is a deployment target.
	 *
	 * @param eObj
	 *            Element
	 * @return True if is a deployment target
	 */
	public boolean isDeploymentTarget(EObject eObj) {
		return eObj instanceof DeploymentTarget;
	}
}
