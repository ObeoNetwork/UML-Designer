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
package org.obeonetwork.dsl.uml2.design.internal.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.uml2.uml.Element;
import org.obeonetwork.dsl.uml2.core.api.utils.UmlUtils;

/**
 * Delete UML Element.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DeleteUMLElement extends AbstractHandler {

	private Session collectSessionAndFillSelection(ISelection selection, List<Element> selected) {
		Session session = null;

		if (selection instanceof StructuredSelection) {
			for (final Object obj : ((StructuredSelection)selection).toArray()) {
				if (obj instanceof Element) {
					selected.add((Element)obj);
					if (session == null) {
						session = SessionManager.INSTANCE.getSession((EObject)obj);
					}
				}
			}
		}
		return session;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);

		final List<Element> selected = new ArrayList<Element>();
		final Session session = collectSessionAndFillSelection(selection, selected);

		if (session != null) {
			final RecordingCommand cmd = new RecordingCommand(session.getTransactionalEditingDomain(),
					"Delete from model") { //$NON-NLS-1$
				@Override
				protected void doExecute() {

					for (final Element element : selected) {
						UmlUtils.INSTANCE.destroy(element);
					}

					// Refresh all diagrams
					final Collection<DRepresentation> representations = DialectManager.INSTANCE
							.getAllRepresentations(session);
					for (final DRepresentation representation : representations) {
						// We test if the root element still exists
						if (representation instanceof DSemanticDiagram) {
							final DSemanticDiagram diagram = (DSemanticDiagram)representation;
							if (diagram.getTarget() != null
									&& DialectManager.INSTANCE.canRefresh(representation)) {
								DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
							}
						}
					}
				}
			};
			session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
		}
		return null;
	}

}
