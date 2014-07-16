/*******************************************************************************
 * Copyright (c) 2009, 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;

/**
 * Listen the first time UML editors are opened and open automatically the corresponding contextual help
 * section.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class OpenHelpContextListener implements IPartListener2 {

	private final IPreferenceStore preferenceStore = UMLDesignerPlugin.getDefault().getPreferenceStore();

	public void partActivated(IWorkbenchPartReference partRef) {
		// Nothing
	}

	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		// Nothing
	}

	public void partClosed(IWorkbenchPartReference partRef) {
		// Nothing
	}

	public void partDeactivated(IWorkbenchPartReference partRef) {
		// Nothing
	}

	public void partOpened(IWorkbenchPartReference partRef) {
		IWorkbenchPart part = partRef.getPart(false);
		if (part instanceof DialectEditor) {
			String representationId = DialectManager.INSTANCE.getDescription(
					((DialectEditor)part).getRepresentation()).getName();

			if (!preferenceStore.getBoolean(representationId)) {
				preferenceStore.setValue(representationId, Boolean.TRUE);

				// Context ids are defined in the html/contexts.xml file in
				// org.obeonetwork.dsl.uml2.design.doc project.
				// The representationId is equal to the id defined in the uml.odesign
				// description.
				String contextId = "org.obeonetwork.dsl.uml2.design.doc." + representationId;

				PlatformUI.getWorkbench().getHelpSystem()
						.setHelp(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), contextId);
				PlatformUI.getWorkbench().getHelpSystem().displayDynamicHelp();
			}
		}
	}

	public void partHidden(IWorkbenchPartReference partRef) {
		// Nothing
	}

	public void partVisible(IWorkbenchPartReference partRef) {
		// Nothing
	}

	public void partInputChanged(IWorkbenchPartReference partRef) {
		// Nothing
	}

}
