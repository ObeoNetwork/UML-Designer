package org.obeonetwork.dsl.uml2.editor.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.uml2.uml.Element;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;
import org.obeonetwork.dsl.uml2.design.internal.listeners.Messages;

public class PasteCommandHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);
		if (selection instanceof IStructuredSelection) {
			@SuppressWarnings("unchecked")
			List<Object> selectionList = ((IStructuredSelection)selection).toList();
			int index = 0;
			EditingDomain memorizedDomain = null;
			boolean visiblebleCopyPaste = true;
			while (index < selectionList.size() && visiblebleCopyPaste == true) {
				if (!(selectionList.get(index) instanceof Element)) {
					visiblebleCopyPaste = false;
				} else {
					Session session = SessionManager.INSTANCE.getSession((Element)selectionList.get(index));

					// TransactionalEditingDomain tempDomain =
					// TransactionUtil.getEditingDomain((Element)selectionList.get(index));
					TransactionalEditingDomain tempDomain = session.getTransactionalEditingDomain();
					if (index == 0) {
						memorizedDomain = tempDomain;
					}
					if (memorizedDomain != tempDomain) {
						visiblebleCopyPaste = false;
					}
				}
				index++;
			}

			if (selectionList.size() == 1 && selectionList.get(0) instanceof Element) {
				TransactionalEditingDomain tempDomain = TransactionUtil
						.getEditingDomain((Element)selectionList.get(0));
				Command pasteFromClipBoardCommand = PasteFromClipboardCommand.create(memorizedDomain,
						(Element)selectionList.get(0), null);
				tempDomain.getCommandStack().execute(pasteFromClipBoardCommand);

				IViewPart modelExplorerView = null;
				try {
					modelExplorerView = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
							.showView("org.eclipse.sirius.ui.tools.views.model.explorer"); //$NON-NLS-1$
					if (modelExplorerView instanceof CommonNavigator) {
						final CommonNavigator modelExplorer = (CommonNavigator)modelExplorerView;
						final Collection<?> affectedObjects = pasteFromClipBoardCommand.getAffectedObjects();
						final Object affectedObject = affectedObjects.iterator().next();
						modelExplorer.getCommonViewer().refresh(((Element)affectedObject).getOwner());
						modelExplorer.selectReveal(new StructuredSelection(affectedObject));
					}
				} catch (final PartInitException e) {
					UMLDesignerPlugin.log(IStatus.ERROR,
							NLS.bind(Messages.UmlModelExplorer_UI_ErrorMsg_NotFound, modelExplorerView), e);
				}

			}
		}
		return null;
	}

	public boolean isEnabled() {

		return true;
	}

}
