/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.profile.design.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Profile;
import org.obeonetwork.dsl.uml2.profile.design.dialogs.ImportMetaclassDialog;

/**
 * Add a list of Metaclass to a Profile.
 * 
 * @author Mohamed-Lamine BOUKHANOUFA <a
 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
 */
public class ImportMetaclass {

	/**
	 * Find the imported metaclass to the profile.
	 * 
	 * @param profile
	 *            to test.
	 * @return a list of class.
	 */
	public List<PackageableElement> importMetaclass(final Profile profile) {
		final List<PackageableElement> alreadyOnProfile = new ArrayList<PackageableElement>();
		final List<String> alreadyOnProfileNames = new ArrayList<String>();

		final ImportMetaclassDialog dialog = new ImportMetaclassDialog(
				PlatformUI.getWorkbench().getDisplay().getActiveShell(),
				profile, true);
		dialog.open();

		if (dialog.getResult() != null) {
			final List<Object> selectedObjects = Arrays.asList(dialog
					.getResult());
			for (Object object : selectedObjects) {
				if (object instanceof Class) {
					final Class selectedMetaclass = (Class) object;
					final PackageableElement importedMember = profile
							.getImportedMember(selectedMetaclass.getName());
					if (importedMember == null) {
						profile.createMetaclassReference(selectedMetaclass);
					} else {
						// reserved for a future utilisation
						alreadyOnProfile.add(importedMember);
						alreadyOnProfileNames.add(importedMember.getName());
					}
				}
			}
		}
		if (alreadyOnProfile.size() > 0) {
			final String[] buttonList = { "Ok" };
			final MessageDialog msgDialog = new MessageDialog(PlatformUI
					.getWorkbench().getDisplay().getActiveShell(),
					"Already imported Metaclasses", null,
					"The following Metaclasses are already imported:" + "\n"
							+ alreadyOnProfileNames, MessageDialog.INFORMATION,
					buttonList, 0);
			msgDialog.open();
		}
		return alreadyOnProfile;
	}
}
