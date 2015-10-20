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
package org.obeonetwork.dsl.uml2.editor.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * Drop adapter assistant to contribute to the model explorer view.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class UmlCommonDropAdapterAssistant extends CommonDropAdapterAssistant {

	@Override
	public IStatus validateDrop(Object target, int operation, TransferData transferType) {
		IStatus status = Status.CANCEL_STATUS;
		if (DND.DROP_MOVE == operation && target instanceof EObject
				&& UMLPackage.eINSTANCE == ((EObject)target).eClass().getEPackage()) {
			Collection<EObject> extractDragSource = extractDragSource(
					LocalSelectionTransfer.getTransfer().getSelection());
			if (!extractDragSource.isEmpty()) {
				status = Status.OK_STATUS;
			}
		}

		return status;
	}

	@Override
	public IStatus handleDrop(CommonDropAdapter aDropAdapter, DropTargetEvent aDropTargetEvent,
			Object aTarget) {
		IStatus status = Status.CANCEL_STATUS;
		if (aTarget instanceof EObject) {
			EObject target = (EObject)aTarget;
			TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(target);
			if (domain != null && UMLPackage.eINSTANCE == target.eClass().getEPackage()) {
				Collection<EObject> droppedEObjects = extractDragSource(aDropTargetEvent.data);
				if (!droppedEObjects.isEmpty()) {
					Command dragAndDropCmd = DragAndDropCommand.create(domain, target,
							getLocation(aDropTargetEvent), aDropTargetEvent.operations, DND.DROP_MOVE,
							droppedEObjects);
					domain.getCommandStack().execute(dragAndDropCmd);
				}
			}
		}
		return status;
	}

	/**
	 * This returns the location of the mouse in the vertical direction, relative to the item widget, from 0
	 * (top) to 1 (bottom). NOTE : Copied from DragAndDropCommand.
	 * 
	 * @param event
	 *            the {@link DropTargetEvent}
	 * @return the float representing the vertical direction
	 */
	protected float getLocation(DropTargetEvent event) {
		float result = 0.0F;
		if (event.item instanceof TreeItem) {
			TreeItem treeItem = (TreeItem)event.item;
			Control control = treeItem.getParent();
			Point point = control.toControl(new Point(event.x, event.y));
			Rectangle bounds = treeItem.getBounds();
			result = (float)(point.y - bounds.y) / (float)bounds.height;
		} else if (event.item instanceof TableItem) {
			TableItem tableItem = (TableItem)event.item;
			Control control = tableItem.getParent();
			Point point = control.toControl(new Point(event.x, event.y));
			Rectangle bounds = tableItem.getBounds(0);
			result = (float)(point.y - bounds.y) / (float)bounds.height;
		}
		return result;
	}

	/**
	 * This extracts a collection of dragged source objects from the given object retrieved from the transfer
	 * agent. This default implementation converts a structured selection into a collection of elements. NOTE
	 * : Copied from DragAndDropCommand
	 * 
	 * @param object
	 *            the object representing the selection in drag
	 * @return the drag selection in form of collection
	 */
	protected Collection<EObject> extractDragSource(Object object) {
		Collection<EObject> droppedEObjects = new ArrayList<EObject>();
		if (object instanceof IStructuredSelection) {
			List<?> list = ((IStructuredSelection)object).toList();
			for (Object obj : list) {
				if (obj instanceof EObject) {
					EObject eObject = (EObject)obj;
					if (UMLPackage.eINSTANCE == eObject.eClass().getEPackage()) {
						droppedEObjects.add(eObject);
					}
				}

			}
		}
		return droppedEObjects;
	}
}
