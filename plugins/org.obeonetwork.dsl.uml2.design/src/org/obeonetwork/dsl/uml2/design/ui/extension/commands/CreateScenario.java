package org.obeonetwork.dsl.uml2.design.ui.extension.commands;

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
import org.obeonetwork.dsl.uml2.design.services.LabelServices;
import org.obeonetwork.dsl.uml2.design.services.internal.NamedElementServices;

public class CreateScenario extends AbstractHandler {
	// TODO To remove
	/**
	 * {@inheritDoc}
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);

		final List<Package> packages = new ArrayList<Package>();
		final Session session = collectSessionAndFillSelection(selection, packages);

		if (session != null) {

			final RecordingCommand cmd = new RecordingCommand(session.getTransactionalEditingDomain(),
					"Create Scenario") {
				protected void doExecute() {
					for (Package pkg : packages) {
						// Create interaction
						final Interaction interaction = UMLFactory.eINSTANCE.createInteraction();
						interaction.setName(NamedElementServices.getNewInteractionName(pkg));
						pkg.getPackagedElements().add(interaction);

						// Get sequence diagram representation description
						final RepresentationDescription description = getSequenceDiagramDescription(session,
								interaction);

						// Create representation
						final DRepresentation representation = DialectManager.INSTANCE.createRepresentation(
								new LabelServices().getSequenceDiagramName(interaction), interaction,
								description, session, new NullProgressMonitor());

						// Open diagram
						DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
					}
				}

				private RepresentationDescription getSequenceDiagramDescription(Session session,
						Interaction interaction) {

					for (RepresentationDescription representation : DialectManager.INSTANCE
							.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false),
									interaction)) {
						if ("Sequence Diagram".equals(representation.getName())
								&& representation instanceof SequenceDiagramDescription)
							return representation;
					}
					return null;
				}
			};
			session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
		}
		return null;
	}

	private Session collectSessionAndFillSelection(ISelection selection, List<Package> selected) {
		Session session = null;

		if (selection instanceof StructuredSelection) {
			for (Object obj : ((StructuredSelection)selection).toArray()) {
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

	@Override
	public boolean isEnabled() {

		return super.isEnabled();
	}

}
