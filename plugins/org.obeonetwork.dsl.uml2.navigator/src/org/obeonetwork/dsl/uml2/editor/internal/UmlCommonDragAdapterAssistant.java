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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.navigator.CommonDragAdapterAssistant;

/**
 * Drag adapter assistant to contribute to the model explorer view.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class UmlCommonDragAdapterAssistant extends CommonDragAdapterAssistant {

	private static final Transfer[] SUPPORTED_TRANSFERS = new Transfer[] {
			LocalSelectionTransfer.getTransfer(), LocalTransfer.getInstance()};

	/**
	 * Default constructor.
	 */
	public UmlCommonDragAdapterAssistant() {
	}

	@Override
	public Transfer[] getSupportedTransferTypes() {
		return SUPPORTED_TRANSFERS;
	}

	@Override
	public boolean setDragData(DragSourceEvent event, IStructuredSelection selection) {
		boolean canBeSet = false;
		Object o = selection.getFirstElement();
		if (o instanceof EObject) {
			event.data = o;
			event.detail = DND.DROP_MOVE;
			canBeSet = true;
		}
		return canBeSet;
	}
}
