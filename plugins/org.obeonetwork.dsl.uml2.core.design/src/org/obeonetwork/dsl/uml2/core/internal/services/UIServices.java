/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.core.internal.services;

import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.obeonetwork.dsl.uml2.core.internal.triggers.AutosizeTrigger;

import com.google.common.collect.Sets;

/**
 * Services to handle UI concerns.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UIServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final UIServices INSTANCE = new UIServices();

	/**
	 * Hidden constructor.
	 */
	private UIServices() {

	}

	/**
	 * Default height.
	 *
	 * @return The default height.
	 */
	public int defaultHeight() {
		return 10;
	}

	/**
	 * Default width.
	 *
	 * @return The default width.
	 */
	public int defaultWidth() {
		return 12;
	}

	/**
	 * Get displayed node in a diagram
	 *
	 * @param diagram
	 *            Diagram
	 * @return List of displayed semantic objects.
	 */
	public Collection<EObject> getDisplayedNodes(DDiagram diagram) {
		final Set<EObject> result = Sets.newLinkedHashSet();
		final DDiagramQuery query = new DDiagramQuery(diagram);
		for (final DDiagramElement diagramElement : query.getAllDiagramElements()) {
			result.add(diagramElement.getTarget());
		}
		return result;
	}

	/**
	 * Mark for auto size.
	 *
	 * @param any
	 *            Any
	 * @return the given auto sized object
	 */
	public EObject markForAutosize(EObject any) {
		if (any != null) {
			any.eAdapters().add(AutosizeTrigger.AUTO_SIZE_MARKER);
		}
		return any;
	}
}
