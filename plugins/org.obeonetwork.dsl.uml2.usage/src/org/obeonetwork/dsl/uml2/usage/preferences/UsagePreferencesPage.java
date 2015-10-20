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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.obeonetwork.dsl.uml2.design.UMLDesignerPlugin;
import org.obeonetwork.dsl.uml2.usage.analytics.EclipseUserAgent;

/**
 * Usage preference page.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class UsagePreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public UsagePreferencesPage() {
		super(GRID);
	}

	@Override
	protected Control createContents(Composite parent) {
		Control control = super.createContents(parent);
		createReportingData((Composite)control);
		return control;
	}

	private void createReportingData(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setText(PreferencesMessages.Usage_PreferencePage_ReportedValues);
		GridDataFactory.fillDefaults().grab(true, true).hint(SWT.FILL, SWT.FILL).applyTo(group);
		FillLayout fillLayout = new FillLayout();
		fillLayout.marginHeight = 4;
		fillLayout.marginWidth = 8;
		group.setLayout(fillLayout);
		StyledText text = new StyledText(group, SWT.BORDER | SWT.V_SCROLL);
		text.setEditable(false);
		createText(text);
	}

	private void createText(StyledText text) {
		List<StyleRange> styles = new ArrayList<StyleRange>();
		StringBuilder builder = new StringBuilder();

		EclipseUserAgent eclipseUserAgent = new EclipseUserAgent();
		appendLabeledValue(PreferencesMessages.Usage_PreferencePage_ProductId,
				eclipseUserAgent.getApplicationName(), builder, styles);

		appendLabeledValue(PreferencesMessages.Usage_PreferencePage_ProductVersion,
				eclipseUserAgent.getApplicationVersion(), builder, styles);
		builder.append(StringUtils.getLineSeparator());

		appendLabeledValue(PreferencesMessages.Usage_PreferencePage_OperatingSystem, eclipseUserAgent.getOS(),
				builder, styles);
		appendLabeledValue(PreferencesMessages.Usage_PreferencePage_OperatingSystemVersion,
				eclipseUserAgent.getOSVersion(), builder, styles);
		builder.append(StringUtils.getLineSeparator());

		appendLabeledValue(PreferencesMessages.Usage_PreferencePage_Locale,
				eclipseUserAgent.getBrowserLanguage(), builder, styles);
		builder.append(StringUtils.getLineSeparator());

		appendLabeledValue(PreferencesMessages.Usage_PreferencePage_Diagrams, UMLDesignerPlugin.getDefault()
				.getPreferenceStore().getString(UsagePreferences.USAGE_DIAGRAMS_ID), builder, styles);
		builder.append(StringUtils.getLineSeparator());

		text.setText(builder.toString());

		for (StyleRange style : styles) {
			text.setStyleRange(style);
		}

	}

	/**
	 * Appends a labeled value to the given string builder and adds a bold font style range to the given
	 * styles.
	 * 
	 * @param label
	 *            the label to append
	 * @param value
	 *            the value to append
	 * @param builder
	 *            the builder to append the strings (label, value) to
	 * @param styles
	 *            the styles list to add the style range to
	 */
	private void appendLabeledValue(String label, String value, StringBuilder builder,
			List<StyleRange> styles) {
		StyleRange styleRange = startLabelStyleRange(builder);
		builder.append(label);
		finishLabelStyleRange(builder, styleRange);
		builder.append(value).append(StringUtils.getLineSeparator());
		styles.add(styleRange);
	}

	private StyleRange startLabelStyleRange(StringBuilder builder) {
		StyleRange styleRange = new StyleRange();
		styleRange.start = builder.length();
		styleRange.fontStyle = SWT.BOLD;
		return styleRange;
	}

	private StyleRange finishLabelStyleRange(StringBuilder builder, StyleRange styleRange) {
		styleRange.length = builder.length() - styleRange.start;
		return styleRange;
	}

	public void createFieldEditors() {
		addField(new BooleanFieldEditor(UsagePreferences.USAGE_ENABLED_ID,
				PreferencesMessages.Usage_PreferencePage_AllowReporting, getFieldEditorParent()));
	}

	public void init(IWorkbench workbench) {
		setPreferenceStore(UMLDesignerPlugin.getDefault().getPreferenceStore());
		setDescription(PreferencesMessages.Usage_PreferencePage_Description);
	}
}
