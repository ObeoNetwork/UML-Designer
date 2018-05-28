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
package org.obeonetwork.dsl.uml2.core.internal.wizards;

import org.eclipse.osgi.util.NLS;

/**
 * Messages.
 *
 * @author Melanie Bats
 *         <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.obeonetwork.dsl.uml2.core.internal.wizards.messages"; //$NON-NLS-1$

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
	 * Bad file extension error.
	 */
	public static String UmlModelWizard_UI_ErrorMsg_BadFileExtension;

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

	/**
	 * Setting Project nature error.
	 */
	public static String UmlModelWizard_UI_Error_SettingProjectNature;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
