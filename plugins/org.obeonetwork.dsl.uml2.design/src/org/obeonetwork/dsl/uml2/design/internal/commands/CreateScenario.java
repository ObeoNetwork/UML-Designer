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
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLFactory;
import org.obeonetwork.dsl.uml2.core.internal.services.ElementServices;
import org.obeonetwork.dsl.uml2.core.internal.services.LabelServices;

/**
 * Create scenario.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class CreateScenario extends AbstractHandler {
	private Session collectSessionAndFillSelection(ISelection selection, List<Package> selected) {
		Session session = null;

		if (selection instanceof StructuredSelection) {
			for (final Object obj : ((StructuredSelection)selection).toArray()) {
				if (obj instanceof Package) {
					selected.add((Package)obj);
					if (session == null) {
						session = SessionManager.INSTANCE.getSession((EObject)obj);
					}
				}
			}
		}
		return session;
	}

	// TODO To remove
	/**
	 * {@inheritDoc}
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);

		final List<Package> packages = new ArrayList<Package>();
		final Session session = collectSessionAndFillSelection(selection, packages);

		if (session != null) {

			final RecordingCommand cmd = new RecordingCommand(session.getTransactionalEditingDomain(),
					"Create Scenario") { //$NON-NLS-1$
				@Override
				protected void doExecute() {
					for (final Package pkg : packages) {
						// Create interaction
						final Interaction interaction = UMLFactory.eINSTANCE.createInteraction();
						interaction.setName(ElementServices.INSTANCE.getNewInteractionName(pkg));
						pkg.getPackagedElements().add(interaction);

						// Get sequence diagram representation description
						final RepresentationDescription diagDescription = getSequenceDiagramDescription(
								session, interaction);

						// Create representation
						final DRepresentation representation = DialectManager.INSTANCE.createRepresentation(
								LabelServices.INSTANCE.getSequenceDiagramName(interaction), interaction,
								diagDescription, session, new NullProgressMonitor());

						// Open diagram
						DialectUIManager.INSTANCE.openEditor(session, representation,
								new NullProgressMonitor());
					}
				}

				private RepresentationDescription getSequenceDiagramDescription(Session curSession,
						Interaction interaction) {

					for (final RepresentationDescription representation : DialectManager.INSTANCE
							.getAvailableRepresentationDescriptions(curSession.getSelectedViewpoints(false),
									interaction)) {
						if ("Sequence Diagram".equals(representation.getName()) //$NON-NLS-1$
								&& representation instanceof SequenceDiagramDescription) {
							return representation;
						}
					}
					return null;
				}
			};
			session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
		}
		return null;
	}

	@Override
	public boolean isEnabled() {

		return super.isEnabled();
	}

}
