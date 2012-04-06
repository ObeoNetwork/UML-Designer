package org.obeonetwork.dsl.uml2.design.ui.extension.commands;

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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.uml2.uml.Element;

import fr.obeo.dsl.viewpoint.DRepresentation;
import fr.obeo.dsl.viewpoint.DSemanticDiagram;
import fr.obeo.dsl.viewpoint.business.api.dialect.DialectManager;
import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.business.api.session.SessionManager;

public class DeleteUMLElement extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);

		final List<Element> selected = new ArrayList<Element>();
		final Session session = collectSessionAndFillSelection(selection, selected);

		if (session != null) {
			final RecordingCommand cmd = new RecordingCommand(session.getTransactionalEditingDomain(),
					"Delete from model") {
				protected void doExecute() {

					for (Element element : selected) {
						element.destroy();
					}
					// Refresh all diagrams
					if (session != null) {
						final Collection<DRepresentation> representations = DialectManager.INSTANCE
								.getAllRepresentations(session);
						for (DRepresentation representation : representations) {
							// We test if the root element still exists
							if (representation instanceof DSemanticDiagram) {
								final DSemanticDiagram diagram = (DSemanticDiagram)representation;
								if ((diagram.getTarget() != null)
										&& (DialectManager.INSTANCE.canRefresh(representation))) {
									DialectManager.INSTANCE
											.refresh(representation, new NullProgressMonitor());
								}
							}
						}
					}
				}
			};
			session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
		}
		return null;
	}

	private Session collectSessionAndFillSelection(ISelection selection, List<Element> selected) {
		Session session = null;

		if (selection instanceof StructuredSelection) {
			for (Object obj : ((StructuredSelection)selection).toArray()) {
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

}
