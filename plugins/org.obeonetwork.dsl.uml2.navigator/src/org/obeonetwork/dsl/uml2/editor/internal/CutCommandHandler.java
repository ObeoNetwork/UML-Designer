package org.obeonetwork.dsl.uml2.editor.internal;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.uml2.uml.Element;

public class CutCommandHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);
		if (selection instanceof IStructuredSelection){
			@SuppressWarnings("unchecked")
			List<Object> selectionList = ((IStructuredSelection)selection).toList();
			int index = 0;
			EditingDomain memorizedDomain = null;
			boolean copy = true;
			while (index<selectionList.size() && copy  == true){
				if (!(selectionList.get(index) instanceof Element)){
					copy = false;
				}else{
					TransactionalEditingDomain tempDomain = TransactionUtil.getEditingDomain((Element)selectionList.get(index));
					if (index == 0){
						memorizedDomain = tempDomain;
					}
					if (memorizedDomain != tempDomain){
						copy = false;
					}
				}
				index ++;
			}
			if (copy){
				CompoundCommand cutCommand = new CompoundCommand();
				Command copyToClipBoardCommand = CopyToClipboardCommand.create(memorizedDomain, selectionList);
				cutCommand.append(copyToClipBoardCommand);
				Command remove = RemoveCommand.create(memorizedDomain, selectionList);
				cutCommand.append(remove);
				memorizedDomain.getCommandStack().execute(cutCommand);
				
			}
		}
		return null;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
