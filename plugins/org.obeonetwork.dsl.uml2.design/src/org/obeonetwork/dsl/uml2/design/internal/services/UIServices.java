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
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.obeonetwork.dsl.uml2.design.internal.triggers.AutosizeTrigger;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
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
	 * @param diagram Diagram
	 * @return List of displayed semantic objects.
	 */
	public Collection<EObject> getDisplayedNodes(DDiagram diagram) {
		final Set<EObject> result = Sets.newLinkedHashSet();
		if (diagram instanceof DSemanticDecorator) {
			SessionManager.INSTANCE.getSession(((DSemanticDecorator)diagram).getTarget());

			final Iterator<EObject> it = Iterators.transform(
					Iterators.filter(diagram.eAllContents(), AbstractDNode.class),
					new Function<AbstractDNode, EObject>() {

						public EObject apply(AbstractDNode input) {
							return input.getTarget();
						}
					});
			while (it.hasNext()) {
				final EObject displayedAsANode = it.next();
				result.add(displayedAsANode);
			}
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
