/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.ui.wizards.newmodel;

import org.eclipse.osgi.util.NLS;

// CHECKSTYLE:OFF
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.obeonetwork.dsl.uml2.design.ui.wizards.newmodel.messages"; //$NON-NLS-1$

	public static String UmlModelWizard_DefaultModelName;

	public static String UmlModelWizard_DefaultPackageName;

	public static String UmlModelWizard_UI_Error_CreatingUmlModel;

	public static String UmlModelWizard_UI_Error_CreatingUmlModelSession;

	public static String UmlModelWizard_UI_ErrorMsg_BadFileExtension;

	public static String UmlModelWizard_UI_InitModelPageDescription;

	public static String UmlModelWizard_UI_InitModelPageId;

	public static String UmlModelWizard_UI_InitModelPageTitle;

	public static String UmlModelWizard_UI_ModelFileDefaultName;

	public static String UmlModelWizard_UI_NewModelFilePageDescription;

	public static String UmlModelWizard_UI_NewModelFilePageId;

	public static String UmlModelWizard_UI_NewModelFilePageTitle;

	public static String UmlModelWizard_UI_WizardTitle;

	public static String UmlModelWizardInitModelPage_ContainerLabel;

	public static String UmlModelWizardInitModelPage_XmlEncodingLabel;

	public static String UmlModelWizardInitModelPage_XmlEncodings;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
