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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.common.ui.tools.api.dialog.RenameDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * A {@link Action} to rename UML model element.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class RenameUmlModelElementAction extends Action {

	private EditingDomain editingDomain;

	private EObject selection;

	/**
	 * Defaut constructor.
	 * 
	 * @param editingDomain
	 *            The {@link EditingDomain} to used to rename the selection
	 * @param selection
	 *            the selection to rename
	 */
	public RenameUmlModelElementAction(EditingDomain editingDomain, EObject selection) {
		super("Rename");
		this.editingDomain = editingDomain;
		this.selection = selection;
	}

	@Override
	public void run() {
		if (selection != null) {
			String oldName = null;
			EAttribute eAttribute = null;
			if (selection instanceof NamedElement) {
				NamedElement element = (NamedElement)selection;
				oldName = element.getName();
				eAttribute = UMLPackage.Literals.NAMED_ELEMENT__NAME;
			}
			if (oldName == null) {
				oldName = "";
			}

			final RenameDialog dialog = new RenameDialog(Display.getCurrent().getActiveShell(), true,
					oldName);
			dialog.create();
			if (dialog.open() == Window.OK) {
				final String newName = dialog.getNewName();
				if (!oldName.equals(newName)) {
					Command setNameCmd = SetCommand.create(editingDomain, selection, eAttribute, newName);
					editingDomain.getCommandStack().execute(setNameCmd);
				}
			}
			selection = null;
		}
	}

	@Override
	public boolean isEnabled() {
		return selection != null;
	}
}
