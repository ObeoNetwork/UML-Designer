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
package org.obeonetwork.dsl.uml2.design.internal.wizards;

import org.eclipse.osgi.util.NLS;

/**
 * Messages.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.obeonetwork.dsl.uml2.design.internal.wizards.messages"; //$NON-NLS-1$

	/**
	 * Pasting error.
	 */
	public static String UIServices_UI_Error_PastingElement;

	/**
	 * Open error.
	 */
	public static String UmlExampleWizard_UI_Error_Open;

	/**
	 * Default model name.
	 */
	public static String UmlModelWizard_DefaultModelName;

	/**
	 * Default package name.
	 */
	public static String UmlModelWizard_DefaultPackageName;

	/**
	 * Creating model error.
	 */
	public static String UmlModelWizard_UI_Error_CreatingUmlModel;

	/**
	 * creating model session error.
	 */

	public static String UmlModelWizard_UI_Error_CreatingUmlModelSession;

	/**
	 * Bad file extension error.
	 */
	public static String UmlModelWizard_UI_ErrorMsg_BadFileExtension;

	/**
	 * Init model page description.
	 */
	public static String UmlModelWizard_UI_InitModelPageDescription;

	/**
	 * Init model page id.
	 */
	public static String UmlModelWizard_UI_InitModelPageId;

	/**
	 * Init model page title.
	 */

	public static String UmlModelWizard_UI_InitModelPageTitle;

	/**
	 * Model file default name.
	 */
	public static String UmlModelWizard_UI_ModelFileDefaultName;

	/**
	 * Model file page description.
	 */
	public static String UmlModelWizard_UI_NewModelFilePageDescription;

	/**
	 * Model file page id.
	 */
	public static String UmlModelWizard_UI_NewModelFilePageId;

	/**
	 * Model file page title.
	 */
	public static String UmlModelWizard_UI_NewModelFilePageTitle;

	/**
	 * Wizard title.
	 */
	public static String UmlModelWizard_UI_WizardTitle;

	/**
	 * Container label.
	 */
	public static String UmlModelWizardInitModelPage_ContainerLabel;

	/**
	 * Encoding label.
	 */
	public static String UmlModelWizardInitModelPage_XmlEncodingLabel;

	/**
	 * Encodings.
	 */
	public static String UmlModelWizardInitModelPage_XmlEncodings;

	/**
	 * Call operation action error.
	 */
	public static String UmlValidationErrorOnCallOperationAction;

	/**
	 * Call operation action error.
	 */
	public static String UmlValidationErrorOnCallOperationAction2;

	/**
	 * Call operation pin action error.
	 */
	public static String UmlValidationErrorOnCallOperationActionPin;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
