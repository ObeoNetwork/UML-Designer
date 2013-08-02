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

package org.obeonetwork.dsl.uml2.design.tests.contexts;

import static org.junit.Assert.*;
	import org.obeonetwork.dsl.uml2.design.tests.automation.Context;

// Start of user code UnactivateUmlUsage imports
import org.eclipse.jface.dialogs.IDialogConstants;
import org.obeonetwork.dsl.uml2.usage.preferences.UsagePreferences;
// End of user code

/**
 * Context : Unactivate UML Usage
 */
public class UnactivateUmlUsage extends Context {
// Start of user code UnactivateUmlUsage variables
	// Nothing
	// End of user code

	@Override
	public void setup() {
		// Start of user code UnactivateUmlUsage setup
		UsagePreferences preferences = new UsagePreferences();
		preferences.storeUserAnswer(IDialogConstants.NO_ID);
		// End of user code
	}

	@Override
	public void tearDown() {
		// Start of user code UnactivateUmlUsage tear down
		UsagePreferences preferences = new UsagePreferences();
		preferences.storeUserAnswer(IDialogConstants.CANCEL_ID);
		// End of user code
	}

// Start of user code UnactivateUmlUsage private methods
	// Nothing
	// End of user code
}
