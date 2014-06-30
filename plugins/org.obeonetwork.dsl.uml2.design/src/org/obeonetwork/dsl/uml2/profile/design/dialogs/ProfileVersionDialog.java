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
package org.obeonetwork.dsl.uml2.profile.design.dialogs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.Profile;
import org.obeonetwork.dsl.uml2.design.services.LogServices;
import org.obeonetwork.dsl.uml2.profile.design.services.UMLDesignerProfileVersion;
import org.obeonetwork.dsl.uml2.profile.design.services.Version;

/**
 * A dialog message for the versioning information of a profile.
 * 
 * @author Mohamed-Lamine BOUKHANOUFA <a
 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
 */
public class ProfileVersionDialog extends TitleAreaDialog {

	/**
	 * path to the banner image .
	 */
	private final String PROFILE_VERSIONING = "Profile Versioning";

	/**
	 * button to select the new version number (revision).
	 */
	private Button revisionVersionButton;

	/**
	 * button to select the new version number (minor).
	 */
	private Button minorVersionButton;

	/**
	 * button to select the new version number (major).
	 */
	private Button majorVersionButton;

	/**
	 * button to select the new version number (custom).
	 */
	private Button customVersionButton;

	/**
	 * Text area where custom version number can be entered.
	 */
	private Text customVersionText;

	/**
	 * Target profile for versioning.
	 */
	private final Profile targetProfile;

	/**
	 * revision version
	 */
	private Version revisionVersionValue;

	/** minor version */
	private Version minorVersionValue;

	/** major release version */
	private Version majorVersionValue;

	/** oldVersion Value */
	private Version oldVersionValue;

	/** custom Version Value */
	private Version customVersionValue;

	/** new Version Value */
	private Version newVersionValue;

	/** Profile version information. */
	private UMLDesignerProfileVersion umlDesignerProfileVersion;

	/** old Profile version information */
	private UMLDesignerProfileVersion oldUMLDesignerProfileVersion;

	/** Comment text area */
	private Text commentText;

	/** Copyright text area */
	private Text copyrightText;

	/** Author text area */
	private Text authorText;

	/** Date text Area */
	private Text dateText;

	/**
	 * Creates a new ProfileVersionDialog.
	 * 
	 * @param parentShell
	 *            the parent shell for this dialog
	 * @param targetProfile
	 *            the target profile.
	 */
	public ProfileVersionDialog(final Shell parentShell,
			final Profile targetProfile) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		this.targetProfile = targetProfile;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void okPressed() {

		// creates the new UMLDesigner profile version information
		umlDesignerProfileVersion = new UMLDesignerProfileVersion(
				newVersionValue, dateText.getText(), authorText.getText(),
				copyrightText.getText(), commentText.getText());
		super.okPressed();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createDialogArea(final Composite parent) {

		// top level composite.
		final Composite parentComposite = (Composite) super
				.createDialogArea(parent);

		setTitle("Information about profile versioning");

		// create a composite with standard margins and spacing.
		final Composite composite = new Composite(parentComposite, SWT.NONE);
		final GridLayout layout = new GridLayout(2, true);
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setFont(parentComposite.getFont());

		// compute initial values for Profile version information
		computeVersionValues();

		// // fill composite with information about new version:
		// // 1. version
		// // 2. author
		// // 3. comment
		// // 4. date
		// // 5. copyright

		GridData gd;

		final Composite versionArea = createVersionArea(composite);
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		versionArea.setLayoutData(gd);

		final Composite infoArea = createInfoArea(composite);
		gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		infoArea.setLayoutData(gd);

		final Composite commentArea = createCommentArea(composite);
		gd = new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 1);
		commentArea.setLayoutData(gd);

		final Composite copyrightArea = createCopyrightArea(composite);
		gd = new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 1);
		copyrightArea.setLayoutData(gd);

		applyDialogFont(parentComposite);
		return parentComposite;
	}

	/**
	 * Creates and returns the content of the copyright area.
	 * 
	 * @param composite
	 *            The parent composite to contain the copyright area
	 */
	private Composite createCopyrightArea(final Composite composite) {
		final Group group = new Group(composite, SWT.CENTER);
		group.setText("Copyright");
		final GridLayout layout = new GridLayout();
		group.setLayout(layout);

		// new copyright area
		copyrightText = new Text(group, SWT.MULTI | SWT.BORDER | SWT.WRAP
				| SWT.V_SCROLL);
		copyrightText.setText(oldUMLDesignerProfileVersion.getCopyRight());
		final GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, true);
		gd.heightHint = 60;
		copyrightText.setLayoutData(gd);
		return group;
	}

	/**
	 * Creates and returns the content of the information area.
	 * 
	 * @param composite
	 *            The parent composite to contain the information area
	 */
	private Composite createInfoArea(final Composite composite) {
		final Group group = new Group(composite, SWT.CENTER);
		group.setText("Info");
		final GridLayout layout = new GridLayout(2, false);
		group.setLayout(layout);

		final Label dateLabel = new Label(group, SWT.LEFT);
		dateLabel.setText("Date");
		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		dateLabel.setLayoutData(gd);
		dateText = new Text(group, SWT.SINGLE | SWT.BORDER);
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		final TimeZone local = TimeZone.getDefault();
		simpleDateFormat.setTimeZone(local);
		final String dateString = simpleDateFormat.format(new Date());
		dateText.setText(dateString);
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		dateText.setLayoutData(gd);

		final Label authorLabel = new Label(group, SWT.LEFT);
		authorLabel.setText("Author");
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		authorLabel.setLayoutData(gd);
		authorText = new Text(group, SWT.SINGLE | SWT.BORDER);
		authorText.setText(oldUMLDesignerProfileVersion.getAuthor());
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		authorText.setLayoutData(gd);

		return group;
	}

	/**
	 * Creates and returns the content of the comment area.
	 * 
	 * @param composite
	 *            The parent composite to contain the comment area
	 */
	private Composite createCommentArea(final Composite composite) {
		final Group group = new Group(composite, SWT.CENTER);
		group.setText("Comment");
		final GridLayout layout = new GridLayout(1, false);
		group.setLayout(layout);

		// new comment area
		commentText = new Text(group, SWT.MULTI | SWT.BORDER | SWT.WRAP
				| SWT.V_SCROLL);
		commentText.setText(oldUMLDesignerProfileVersion.getComment());
		final GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, true);
		gd.heightHint = 60;
		commentText.setLayoutData(gd);

		return group;
	}

	/**
	 * Creates and returns the content of the version area.
	 * 
	 * @param composite
	 *            The parent composite to contain the version area
	 */
	private Composite createVersionArea(Composite composite) {
		final Group versionGroup = new Group(composite, SWT.CENTER);
		versionGroup.setText("Version");
		final GridLayout layout = new GridLayout(2, false);
		versionGroup.setLayout(layout);

		// old version label
		final Label oldVersionLabel = new Label(versionGroup, SWT.LEFT);
		oldVersionLabel.setText("Previous Version");
		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		oldVersionLabel.setLayoutData(gd);
		final Text oldVersionText = new Text(versionGroup, SWT.SINGLE
				| SWT.READ_ONLY | SWT.BORDER);
		oldVersionText.setText(oldVersionValue.toString());
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		oldVersionText.setLayoutData(gd);

		// new version:
		// 1. Revision version
		// 2. Minor version
		// 3. Major version
		// 4. Custom version
		revisionVersionButton = new Button(versionGroup, SWT.CHECK);
		revisionVersionButton.setText("Revision Version");
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		revisionVersionButton.setLayoutData(gd);
		revisionVersionButton.setSelection(true);
		revisionVersionButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(final SelectionEvent e) {
				revisionVersionButtonPressed();
			}

			public void widgetDefaultSelected(final SelectionEvent e) {
			}
		});
		final Text devVersionText = new Text(versionGroup, SWT.SINGLE
				| SWT.READ_ONLY | SWT.BORDER);
		devVersionText.setText(revisionVersionValue.toString());
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		devVersionText.setLayoutData(gd);

		// Minor area
		minorVersionButton = new Button(versionGroup, SWT.CHECK);
		minorVersionButton.setText("Minor Version");
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		minorVersionButton.setLayoutData(gd);
		minorVersionButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(final SelectionEvent e) {
				minorVersionButtonPressed();
			}

			public void widgetDefaultSelected(final SelectionEvent e) {
			}
		});
		final Text releaseVersionText = new Text(versionGroup, SWT.SINGLE
				| SWT.READ_ONLY | SWT.BORDER);
		releaseVersionText.setText(minorVersionValue.toString());
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		releaseVersionText.setLayoutData(gd);

		// Major area
		majorVersionButton = new Button(versionGroup, SWT.CHECK);
		majorVersionButton.setText("Major Version");
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		majorVersionButton.setLayoutData(gd);
		majorVersionButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(final SelectionEvent e) {
				majorVersionButtonPressed();
			}

			public void widgetDefaultSelected(final SelectionEvent e) {
			}
		});
		final Text majorReleaseVersionText = new Text(versionGroup, SWT.SINGLE
				| SWT.READ_ONLY | SWT.BORDER);
		majorReleaseVersionText.setText(majorVersionValue.toString());
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		majorReleaseVersionText.setLayoutData(gd);

		// Custom version area
		customVersionButton = new Button(versionGroup, SWT.CHECK);
		customVersionButton.setText("Custom");
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		customVersionButton.setLayoutData(gd);
		customVersionButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(final SelectionEvent e) {
				customVersionButtonPressed();
			}

			public void widgetDefaultSelected(final SelectionEvent e) {
			}
		});
		customVersionText = new Text(versionGroup, SWT.SINGLE | SWT.BORDER);
		customVersionText.setEditable(false);
		customVersionText.setText(customVersionValue.toString());
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		customVersionText.setLayoutData(gd);
		customVersionText.addFocusListener(new FocusListener() {
			public void focusGained(final FocusEvent e) {
				customVersionText.setText(customVersionValue.toString());
			}

			public void focusLost(final FocusEvent e) {
				try {
					customVersionValue = Version.parseVersion(customVersionText
							.getText());
					newVersionValue = customVersionValue;
					setErrorMessage(null);
				} catch (final IllegalArgumentException iae) {
					new LogServices().warning(
							"Custom version number format should be X.Y.Z, not "
									+ customVersionText.getText(), iae);
					customVersionValue = revisionVersionValue; // default value
					revisionVersionButtonPressed();
				}
			}
		});

		return versionGroup;
	}

	/**
	 * compute the value of the versions
	 */
	private void computeVersionValues() {
		final UMLDesignerProfileVersion umlDesignerProfileVersion = new UMLDesignerProfileVersion();
		oldUMLDesignerProfileVersion = umlDesignerProfileVersion
				.getProfileVersion(targetProfile);
		oldVersionValue = oldUMLDesignerProfileVersion.getVersion();
		revisionVersionValue = new Version(oldVersionValue.getMajor(),
				oldVersionValue.getMinor(), oldVersionValue.getMicro() + 1);
		minorVersionValue = new Version(oldVersionValue.getMajor(),
				oldVersionValue.getMinor() + 1, 0);
		majorVersionValue = new Version(oldVersionValue.getMajor() + 1, 0, 0);
		customVersionValue = revisionVersionValue;
		newVersionValue = revisionVersionValue;
	}

	/**
	 * Action called as the revision version button is pressed
	 * 
	 * @param e
	 *            the selection event that triggers this behavior
	 */
	private void revisionVersionButtonPressed() {
		revisionVersionButton.setSelection(true);
		minorVersionButton.setSelection(false);
		majorVersionButton.setSelection(false);
		customVersionButton.setSelection(false);
		newVersionValue = revisionVersionValue;
		customVersionText.setEditable(false);
	}

	/**
	 * Action called as the minor version button is pressed
	 * 
	 * @param e
	 *            the selection event that triggers this behavior
	 */
	private void minorVersionButtonPressed() {
		revisionVersionButton.setSelection(false);
		minorVersionButton.setSelection(true);
		majorVersionButton.setSelection(false);
		customVersionButton.setSelection(false);
		newVersionValue = minorVersionValue;
		customVersionText.setEditable(false);
	}

	/**
	 * Action called as the major version button is pressed
	 * 
	 * @param e
	 *            the selection event that triggers this behavior
	 */
	private void majorVersionButtonPressed() {
		revisionVersionButton.setSelection(false);
		minorVersionButton.setSelection(false);
		majorVersionButton.setSelection(true);
		customVersionButton.setSelection(false);
		newVersionValue = majorVersionValue;
		customVersionText.setEditable(false);
	}

	/**
	 * Action called as the custom version button is pressed
	 * 
	 * @param e
	 *            the selection event that triggers this behavior
	 */
	private void customVersionButtonPressed() {
		revisionVersionButton.setSelection(false);
		minorVersionButton.setSelection(false);
		majorVersionButton.setSelection(false);
		customVersionButton.setSelection(true);
		newVersionValue = customVersionValue;
		customVersionText.setEditable(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configureShell(final Shell shell) {
		super.configureShell(shell);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		shell.setText(PROFILE_VERSIONING);
	}

	/**
	 * Returns the defined UMLDesignerProfileVersion.
	 * 
	 * @return the umlDesignerProfileVersion
	 */
	public UMLDesignerProfileVersion getUMLDesignerProfileVersion() {
		return umlDesignerProfileVersion;
	}
}
