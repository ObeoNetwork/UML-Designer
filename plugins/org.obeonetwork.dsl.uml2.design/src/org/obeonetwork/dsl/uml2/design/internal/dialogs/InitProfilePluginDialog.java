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
package org.obeonetwork.dsl.uml2.design.internal.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This class provide an interface to initiate the parameters of profile plug-in creation.
 *
 * @author Mohamed-Lamine BOUKHANOUFA <a href="mailto:mohamed-lamine.boukhanoufa@obeo.fr" >mohamed-lamine.
 *         boukhanoufa@obeo.fr</a>
 */
public class InitProfilePluginDialog extends TitleAreaDialog {

	/**
	 * Dialog title: Profile Export.
	 */
	private final String PROFILE_EXPORT = "Profile Export"; //$NON-NLS-1$

	private Text profileNameField;

	private Text rootProfileURIField;

	private Text profilePluginNameField;

	private boolean profileNameFieldState;

	private boolean rootProfileURIFieldState;

	private boolean profilePluginNameFieldState;

	private String profileName;

	private String rootProfileURI;

	private String profilePluginName;

	/**
	 * Create an instance of this class.
	 *
	 * @param profileName
	 *            the profile name.
	 * @param rootProfileURI
	 *            the profile URI.
	 * @param profilePluginName
	 *            the profile plug-in name.
	 */
	public InitProfilePluginDialog(final String profileName, final String rootProfileURI,
			final String profilePluginName) {
		super(Display.getCurrent().getActiveShell());
		this.profileName = profileName;
		this.rootProfileURI = rootProfileURI;
		this.profilePluginName = profilePluginName;
		profileNameFieldState = false; // default value
		rootProfileURIFieldState = false; // default value
		profilePluginNameFieldState = false; // default value
		setTitle("Export a profile ..."); //$NON-NLS-1$

	}

	private void applyButtonFilter() {
		final Control button = getButton(IDialogConstants.OK_ID);
		if (button != null) {
			button.setEnabled(
					profileNameFieldState && rootProfileURIFieldState && profilePluginNameFieldState);
		}
	}

	@Override
	protected void configureShell(final Shell shell) {
		super.configureShell(shell);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		shell.setText(PROFILE_EXPORT);
	}

	@Override
	protected Control createContents(final Composite parent) {
		final Composite composite = (Composite)super.createContents(parent);
		return composite;
	}

	@Override
	protected Control createDialogArea(final Composite parent) {

		final Composite parentComposite = (Composite)super.createDialogArea(parent);

		setTitle("Information about profile versioning"); //$NON-NLS-1$

		// create a composite with standard margins and spacing.
		final Composite composite = new Composite(parentComposite, SWT.NONE);
		final GridLayout layout = new GridLayout(1, true);
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setFont(parentComposite.getFont());

		GridData gd;
		final Composite commentArea = createExportArea(composite);
		gd = new GridData(SWT.FILL, SWT.CENTER, true, true);
		commentArea.setLayoutData(gd);

		applyDialogFont(parentComposite);
		return parentComposite;
	}

	/**
	 * Creates and returns the content of the profile uri area.
	 *
	 * @param composite
	 *            The parent composite to contain the comment area
	 */
	private Composite createExportArea(final Composite composite) {
		final Group group = new Group(composite, SWT.CENTER);
		group.setText("Export"); //$NON-NLS-1$
		final GridLayout layout = new GridLayout(1, false);
		group.setLayout(layout);

		// new comment area
		// commentText = new Text(group, SWT.MULTI | SWT.BORDER | SWT.WRAP |
		// SWT.V_SCROLL);
		// commentText.setText(oldUMLDesignerProfileVersion.getComment());
		// GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, true);
		// gd.heightHint = 60;
		// commentText.setLayoutData(gd);

		final Label header1 = new Label(group, SWT.NONE);
		header1.setText("Enter the data required to generate the plug-in."); //$NON-NLS-1$
		final Label header2 = new Label(group, SWT.NONE);
		header2.setText("All text zones must be specified."); //$NON-NLS-1$

		final Label profileNameLabel = new Label(group, SWT.SEARCH);
		profileNameLabel.setText("profile name, editable in the profile (model/diagrams):"); //$NON-NLS-1$

		profileNameField = new Text(group, SWT.SINGLE | SWT.BORDER);
		profileNameField.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				if (e.getSource() instanceof Text) {
					profileName = ((Text)e.getSource()).getText();
				}
				profileNameFieldState = isEmpty(e);
				applyButtonFilter();
			}
		});

		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		profileNameField.setLayoutData(data);
		profileNameField.setText(profileName);
		profileNameField.setEnabled(false);

		final Label rootProfileURILabel = new Label(group, SWT.SEARCH);
		rootProfileURILabel.setText("Root profile URI: "); //$NON-NLS-1$

		rootProfileURIField = new Text(group, SWT.SINGLE | SWT.BORDER);
		rootProfileURIField.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				if (e.getSource() instanceof Text) {
					rootProfileURI = ((Text)e.getSource()).getText();
				}
				rootProfileURIFieldState = isEmpty(e);
				applyButtonFilter();
			}
		});
		data = new GridData(GridData.FILL_HORIZONTAL);
		rootProfileURIField.setLayoutData(data);
		rootProfileURIField.setText(rootProfileURI);

		final Label profilePluginNameLabel = new Label(group, SWT.RIGHT);
		profilePluginNameLabel.setText("Profile plug-in name: "); //$NON-NLS-1$

		profilePluginNameField = new Text(group, SWT.SINGLE | SWT.BORDER);
		profilePluginNameField.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				if (e.getSource() instanceof Text) {
					profilePluginName = ((Text)e.getSource()).getText();
				}
				profilePluginNameFieldState = isEmpty(e);
				applyButtonFilter();
			}
		});
		data = new GridData(GridData.FILL_HORIZONTAL);
		profilePluginNameField.setText(profilePluginName);
		profilePluginNameField.setLayoutData(data);

		return group;
	}

	/**
	 * Get profile name.
	 * 
	 * @return Name
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * Get profile plugin name.
	 *
	 * @return Plugin name
	 */
	public String getProfilePluginName() {
		return profilePluginName;
	}

	/**
	 * Get profile uri.
	 *
	 * @return Profile uri
	 */
	public String getRootProfileURI() {
		return rootProfileURI;
	}

	private boolean isEmpty(final ModifyEvent e) {
		final Object src = e.getSource();
		if (src instanceof Text) {
			final Text txt = (Text)src;
			return txt.getText().length() != 0 && txt.getText().length() != 0;
		}

		return true;
	}
}
