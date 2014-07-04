package org.obeonetwork.dsl.uml2.design.ui.extension.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.Messages;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.obeonetwork.dsl.uml2.design.services.internal.CreateElementLabelFromModelExplorerSwitch;

public abstract class AbstractCreateDiagram<T extends PackageableElement> extends AbstractHandler {

	/**
	 * {@inheritDoc}
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);

		final List<Package> packages = new ArrayList<Package>();
		final Session session = collectSessionAndFillSelection(selection, packages);

		if (session != null) {

			final RecordingCommand cmd = new RecordingCommand(session.getTransactionalEditingDomain(),
					"Create Diagram") {
				protected void doExecute() {
					for (Package pkg : packages) {
						// Create activity
						final T semanticElement = createSemanticObject();
						pkg.getPackagedElements().add(semanticElement);
						semanticElement.setName(new CreateElementLabelFromModelExplorerSwitch()
								.getNewLabel(semanticElement));

						// Get sequence diagram representation description
						final RepresentationDescription description = getDiagramDescription(session,
								semanticElement);

						String representationName = getRepresentationName(description,
								getDiagramLabel(semanticElement));
						if (representationName != null) {
							// Create representation
							final DRepresentation representation = DialectManager.INSTANCE
									.createRepresentation(representationName, semanticElement, description,
											session, new NullProgressMonitor());

							// Open diagram
							DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
						}
					}
				}
			};
			session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
		}
		return null;
	}

	/**
	 * Get the new diagram label.
	 * 
	 * @param semanticElement
	 *            the semantic element
	 * @return the new label
	 */
	protected abstract String getDiagramLabel(T semanticElement);

	/**
	 * Create the new semantic Object.
	 * 
	 * @return the new semantic object
	 */
	protected abstract T createSemanticObject();

	/**
	 * Retrieves the corresponding representation description.
	 * 
	 * @param session
	 *            the session
	 * @param semanticElement
	 *            the semantic element
	 * @return the corresponding representation description
	 */
	protected abstract RepresentationDescription getDiagramDescription(Session session, T semanticElement);

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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {

		return super.isEnabled();
	}

	/**
	 * Get the representation name for the created representation. The default implementation open a dialog,
	 * subclass if needed.
	 * 
	 * @param description
	 *            the description
	 * @param defaultName
	 *            the default name
	 * @return the representation, <code>null</code> if it could not be chosen or the creation is cancelled.
	 */
	protected String getRepresentationName(RepresentationDescription description, String defaultName) {
		String descriptionLabel = null;
		if (description.getEndUserDocumentation() != null
				&& description.getEndUserDocumentation().trim().length() > 0) {
			descriptionLabel = Messages.createRepresentationInputDialog_RepresentationDescriptionLabel
					+ description.getEndUserDocumentation();
		}
		if (descriptionLabel == null) {
			descriptionLabel = ""; //$NON-NLS-1$
		} else {
			descriptionLabel += "\n\n"; //$NON-NLS-1$
		}
		descriptionLabel += Messages.createRepresentationInputDialog_NewRepresentationNameLabel;
		final InputDialog askViewPointName = new InputDialog(Display.getDefault().getActiveShell(),
				Messages.createRepresentationInputDialog_Title, descriptionLabel, defaultName,
				new IInputValidator() {
					public String isValid(final String newText) {
						return null;
					}
				});
		if (askViewPointName.open() == Window.OK)
			return askViewPointName.getValue();
		return null;
	}

}
