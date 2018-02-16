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
package org.obeonetwork.dsl.uml2.design.internal.wizards.newmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.obeonetwork.dsl.uml2.core.api.wizards.UmlProjectUtils;
import org.obeonetwork.dsl.uml2.design.internal.wizards.Messages;

/**
 * The model initialization wizard page.
 *
 * @author Stephane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo .fr</a>
 */
public class UmlModelWizardInitModelPage extends WizardPage {

	protected Combo initialObjectField;

	protected List<String> encodings;

	protected Combo encodingField;

	protected ModifyListener validator = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			setPageComplete(validatePage());
		}
	};

	/**
	 * Pass in the selection.
	 *
	 * @param pageId
	 *            Page Id
	 */
	public UmlModelWizardInitModelPage(String pageId) {
		super(pageId);
	}

	public void createControl(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		{
			final GridLayout layout = new GridLayout();
			layout.numColumns = 1;
			layout.verticalSpacing = 12;
			composite.setLayout(layout);

			final GridData data = new GridData();
			data.verticalAlignment = GridData.FILL;
			data.grabExcessVerticalSpace = true;
			data.horizontalAlignment = GridData.FILL;
			composite.setLayoutData(data);
		}

		final Label containerLabel = new Label(composite, SWT.LEFT);
		{
			containerLabel.setText(Messages.UmlModelWizardInitModelPage_ContainerLabel);

			final GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			containerLabel.setLayoutData(data);
		}

		initialObjectField = new Combo(composite, SWT.BORDER);
		{
			final GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			initialObjectField.setLayoutData(data);
		}

		for (final String objectName : getInitialObjectNames()) {
			initialObjectField.add(objectName);
		}

		initialObjectField.select(0);
		initialObjectField.addModifyListener(validator);

		final Label encodingLabel = new Label(composite, SWT.LEFT);
		{
			encodingLabel.setText(Messages.UmlModelWizardInitModelPage_XmlEncodingLabel);

			final GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			encodingLabel.setLayoutData(data);
		}
		encodingField = new Combo(composite, SWT.BORDER);
		{
			final GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			encodingField.setLayoutData(data);
		}

		for (final String encoding : getEncodings()) {
			encodingField.add(encoding);
		}

		encodingField.select(0);
		encodingField.addModifyListener(validator);

		setPageComplete(validatePage());
		setControl(composite);
	}

	/**
	 * Get encoding.
	 *
	 * @return Encoding
	 */
	public String getEncoding() {
		return encodingField.getText();
	}

	protected Collection<String> getEncodings() {
		if (encodings == null) {
			encodings = new ArrayList<String>();
			for (final StringTokenizer stringTokenizer = new StringTokenizer(
					Messages.UmlModelWizardInitModelPage_XmlEncodings); stringTokenizer.hasMoreTokens();) {
				encodings.add(stringTokenizer.nextToken());
			}
		}
		return encodings;
	}

	/**
	 * Get initial object name.
	 *
	 * @return Name
	 */
	public String getInitialObjectName() {
		return initialObjectField.getText();
	}

	private List<String> getInitialObjectNames() {
		final List<String> names = new ArrayList<String>();
		names.add(UmlProjectUtils.MODEL_OBJECT);
		names.add(UmlProjectUtils.PACKAGE_OBJECT);
		names.add(UmlProjectUtils.PROFILE_OBJECT);
		return names;
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			if (initialObjectField.getItemCount() == 1) {
				initialObjectField.clearSelection();
				encodingField.setFocus();
			} else {
				encodingField.clearSelection();
				initialObjectField.setFocus();
			}
		}
	}

	protected boolean validatePage() {
		return getInitialObjectName() != null && getEncodings().contains(encodingField.getText());
	}

}
