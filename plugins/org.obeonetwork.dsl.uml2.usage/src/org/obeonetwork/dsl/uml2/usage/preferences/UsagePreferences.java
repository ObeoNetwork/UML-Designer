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
package org.obeonetwork.dsl.uml2.usage.preferences;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * Preferences for usage report through google analytics.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UsagePreferences {
	/**
	 * Key for user answer preference in preference store.
	 */
	public static final String USAGE_USER_ANSWER_ID = "usage_user_answer_preference";

	/**
	 * Key for usage report enabled preference in preference store.
	 */
	public static final String USAGE_ENABLED_ID = "usage_enabled_preference";

	/**
	 * Key for diagrams usage preference in preference store.
	 */
	public static final String USAGE_DIAGRAMS_ID = "usage_diagrams_preference";

	/**
	 * The preference store.
	 */
	private IPreferenceStore preferenceStore = UMLDesignerPlugin.getDefault().getPreferenceStore();

	/**
	 * Check if the user has answered to the question.
	 * 
	 * @return True if answered the question (by yes or no) otherwise false
	 */
	public boolean hasAnswered() {
		if (preferenceStore.getInt(USAGE_USER_ANSWER_ID) == IDialogConstants.YES_ID
				|| (preferenceStore.getInt(USAGE_USER_ANSWER_ID) == IDialogConstants.NO_ID)) {
			return true;
		}
		return false;
	}

	/**
	 * Check if the usage report is enabled.
	 * 
	 * @return True if enabled otherwise false
	 */
	public boolean isEnabled() {
		if (preferenceStore.getBoolean(USAGE_ENABLED_ID))
			return true;
		return false;
	}

	/**
	 * Store if the user as answer to the question.
	 * 
	 * @param answerId
	 *            The answer could be {@link IDialogConstants.YES_ID} if the user clicked on yes,
	 *            {@link IDialogConstants.NO_ID} if he clicked on no or {@link IDialogConstants.OK_ID} if he
	 *            does not answered
	 */
	public void storeUserAnswer(int answerId) {
		preferenceStore.setValue(USAGE_USER_ANSWER_ID, answerId);
		if (answerId == IDialogConstants.YES_ID)
			preferenceStore.setValue(USAGE_ENABLED_ID, true);
		if (answerId == IDialogConstants.NO_ID)
			preferenceStore.setValue(USAGE_ENABLED_ID, false);
	}

	/**
	 * Store the diagrams usage. It is stored as a string with format : Diagram Type Name1=42;Diagram Type
	 * Name2=1;Diagram Type Name3=24.
	 * 
	 * @param diagramName
	 *            Diagram Name
	 */
	public void storeDiagramsUsage(String diagramName) {
		// Get existing preference
		String storedDiagrams = preferenceStore.getString(USAGE_DIAGRAMS_ID);

		Map<String, String> map = new HashMap<String, String>();

		char keySeparator = ';';
		String valueSeparator = "=";

		if (storedDiagrams.length() > 0) {
			// Split string to map
			Map<String, String> unmodifiableMap = Splitter.on(keySeparator).trimResults()
					.withKeyValueSeparator(valueSeparator).split(storedDiagrams);
			map.putAll(unmodifiableMap);
		}

		// Update map value
		int value = 0;
		if (map.containsKey(diagramName)) {
			String storedValue = map.get(diagramName);
			value = Integer.parseInt(storedValue);
		}
		value++;
		map.put(diagramName, String.valueOf(value));

		// Join string to map
		storedDiagrams = Joiner.on(keySeparator).withKeyValueSeparator(valueSeparator).join(map);

		// Set new preference
		preferenceStore.setValue(USAGE_DIAGRAMS_ID, storedDiagrams);
	}

	/**
	 * Get diagrams usage.
	 * 
	 * @return String representing the diagrams usage, see {@link UsagePreferences#storeDiagramsUsage(String)}
	 *         to get the string format
	 */
	public String getDiagramsUsage() {
		return preferenceStore.getString(USAGE_DIAGRAMS_ID);
	}

}
