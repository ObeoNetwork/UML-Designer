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
package org.obeonetwork.dsl.uml2.design.ui.extension.editor;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

public class UmlViewpoints {

	private ViewpointRegistry registry;

	public UmlViewpoints(ViewpointRegistry registry) {
		this.registry = registry;
	}

	public Viewpoint capture() {
		return registry.getViewpoint(URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/Capture"));
	}

	public Viewpoint design() {
		return registry.getViewpoint(URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/Design"));
	}

	public Viewpoint review() {
		return registry.getViewpoint(URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/Review"));
	}

	public Viewpoint dashboard() {
		return registry.getViewpoint(URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/Dashboard"));
	}

	public Viewpoint extend() {
		return registry.getViewpoint(URI.createURI("viewpoint:/org.obeonetwork.dsl.uml2.design/Extend"));
	}

	public static UmlViewpoints fromViewpointRegistry() {
		return new UmlViewpoints(ViewpointRegistry.getInstance());
	}
}
