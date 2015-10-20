/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;

/**
 * Uml Designer preference page.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UmlDesignerPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	/**
	 * Constructor.
	 */
	public UmlDesignerPreferencePage() {
		super(GRID);
	}

	@Override
	protected void createFieldEditors() {
		addField(new BooleanFieldEditor(UmlDesignerPreferences.DELETION_CONFIRMATION_ENABLED_ID,
				PreferencesMessages.UmlDesigner_PreferencePage_EnableDeletionConfirm,
				getFieldEditorParent()));
	}

	public void init(IWorkbench workbench) {
		setPreferenceStore(UMLDesignerPlugin.getDefault().getPreferenceStore());
		setDescription(PreferencesMessages.UmlDesigner_PreferencePage_Description);
	}

}
