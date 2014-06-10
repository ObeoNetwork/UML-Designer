/*****************************************************************************
 * Copyright (c) 2011 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Barthelemy HABA (Atos Origin) barthelemy.haba@atosorigin.com - initial 
 *     API and implementation
 *     Axel RICHARD (Obeo) - add aird diagram file management
 *******************************************************************************/
//CHECKSTYLE:OFF
package org.obeonetwork.dsl.sirius.gendoc.wizard;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * The Class Gendoc2WizardPage. This class comes from
 * :pserver:cvs.gforge.enseeiht.fr:/cvsroot/topcased-gendoc
 * ,plugins/gendoc2/org.topcased.gendoc2.wizard,org.topcased.gendoc2.wizard.
 */
public class Gendoc2WizardPage extends WizardPage {

	private Text text;

	private Text text_1;

	private Text text_2;

	private static Text text_3;

	private Combo combo_1;

	private ComboViewer comboViewer_1;

	private Combo combo;

	private ComboViewer comboViewer;

	private static String airdPath;

	/**
	 * Create the wizard.
	 */
	public Gendoc2WizardPage() {
		super("wizardPage");
		setTitle("Documentation Generation");
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 *            represent the composite passed automatically to this method by
	 *            the system
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		container.setLayout(new GridLayout(3, false));

		if (getGWizard().getRunners().size() > 1) {
			Label lblNewLabel_4 = new Label(container, SWT.NONE);
			GridData gd_lblNewLabel_4 = new GridData(SWT.LEFT, SWT.CENTER,
					false, false, 1, 1);
			gd_lblNewLabel_4.widthHint = 89;
			lblNewLabel_4.setLayoutData(gd_lblNewLabel_4);
			lblNewLabel_4.setText(" List of templates:");
			comboViewer_1 = new ComboViewer(container, SWT.NONE);
			combo_1 = comboViewer_1.getCombo();
			GridData gd_combo_1 = new GridData(SWT.FILL, SWT.CENTER, true,
					false, 2, 1);
			gd_combo_1.widthHint = 475;
			combo_1.setLayoutData(gd_combo_1);
			combo_1.select(0);
			manageTemplateCombo(comboViewer_1);

		}

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("Selected File:");

		text = new Text(container, SWT.BORDER);
		text.setEnabled(false);
		text
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
						2, 1));

		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setText("Output Format:");

		comboViewer = new ComboViewer(container, SWT.NONE);
		combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2,
				1));
		Label lblNewLabel_2 = new Label(container, SWT.NONE);
		lblNewLabel_2.setText("Output Path:");
		manageCombo(comboViewer);
		text_1 = new Text(container, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		btnNewButton.setText("browse...");

		Label lblNewLabel_3 = new Label(container, SWT.NONE);
		lblNewLabel_3.setText("File Name:");

		text_2 = new Text(container, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2,
				1));

		Label lblNewLabel_5 = new Label(container, SWT.NONE);
		lblNewLabel_5.setText("Aird Diagram Path:");

		text_3 = new Text(container, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Button btnNewButton_2 = new Button(container, SWT.NONE);
		btnNewButton_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		btnNewButton_2.setText("browse...");

		IFile fileName = getGWizard().getInputFile();
		StringTokenizer token = new StringTokenizer(fileName.getName(), ".");
		String nomFile = token.nextToken();
		text.setText(fileName.getName());
		text_1.setText(getUriText(URI.createURI(fileName.getLocationURI()
				.toString())));
		text_2.setText(nomFile);
		if (airdPath != null && !airdPath.isEmpty()) {
			text_3.setText(airdPath);
		} else {
			String defaultAird = searchDefaultAird(fileName);
			if (defaultAird != null && !defaultAird.isEmpty()) {
				airdPath = defaultAird;
				text_3.setText(defaultAird);
			}
		}
		if (!existAird()) {
			text_3.setText("browse aird diagram...");
		}

		manageListeners(comboViewer, btnNewButton);

		btnNewButton_2.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				IFile[] result = WorkspaceResourceDialog.openFileSelection(
						getShell(), "File Selection",
						"Select an aird diagram file", false, new Object[] {},
						null);
				if (result != null && result.length == 1) {
					IFile file = result[0];
					airdPath = URI.createURI(file.getLocationURI().toString())
							.devicePath().toString();
					text_3.setText(airdPath);
					getGWizard().refresh();
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		if (getGWizard().getRunners().size() > 1) {
			comboViewer_1
					.addSelectionChangedListener(new ISelectionChangedListener() {
						public void selectionChanged(SelectionChangedEvent event) {
							IGendoc2Runner runners = Gendoc2WizardPage.this
									.getSelectedRunner();
							Gendoc2WizardPage.this.comboViewer
									.setInput(runners);
							Gendoc2WizardPage.this.combo.select(0);
							Gendoc2WizardPage.this
									.setDescription(Gendoc2WizardPage.this
											.getSelected().getDescription());
							getGWizard().refresh();
						}
					});
		}
	}

	/**
	 * this method put all the necessary listeners to the comboviewer,
	 * btnnewButton, text_1, text_2.
	 * 
	 * @param comboViewer
	 *            the combo viewer used to output all kind of document to
	 *            generate
	 * @param btnNewButton
	 *            the btn new button that represent the browser on the wizard *
	 */
	private void manageListeners(ComboViewer comboViewer, Button btnNewButton) {
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IContainer[] result = WorkspaceResourceDialog
						.openFolderSelection(getShell(), "Folder Selection",
								"Select an output folder", false,
								new Object[] {}, null);
				if (result != null && result.length == 1) {
					IContainer folder = result[0];
					text_1.setText(URI.createURI(
							folder.getLocationURI().toString()).devicePath()
							.toString()
							+ "/");
					getGWizard().refresh();
				}
			}
		});

		text_1.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				getGWizard().refresh();
			}

		});
		text_2.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				getGWizard().refresh();
			}
		});
		comboViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						Gendoc2WizardPage.this
								.setDescription(Gendoc2WizardPage.this
										.getSelected().getDescription());
						getGWizard().refresh();
					}
				});

	}

	/**
	 * this method add all the necessary listeners to the comboviewer1
	 * 
	 * @param comboViewer1
	 *            the combo viewer1 that output all the possible template of
	 *            model
	 */
	private void manageTemplateCombo(ComboViewer comboViewer1) {
		comboViewer1.setContentProvider(new IStructuredContentProvider() {

			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}

			public void dispose() {
			}

			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof Collection<?>) {
					Collection<?> coll = (Collection<?>) inputElement;
					return coll.toArray();
				}
				return null;
			}
		});

		comboViewer1.setLabelProvider(new ILabelProvider() {

			public void removeListener(ILabelProviderListener listener) {
			}

			public boolean isLabelProperty(Object element, String property) {
				return true;
			}

			public void dispose() {
			}

			public void addListener(ILabelProviderListener listener) {
			}

			public Image getImage(Object element) {
				return null;
			}

			public String getText(Object element) {
				if (element instanceof IGendoc2Runner) {
					return ((IGendoc2Runner) element).getLabel();
				} else {
					return null;
				}

			}
		});

		comboViewer1.setInput(getGWizard().getRunners());
		combo_1.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				IGendoc2Runner selectedRunner = getSelectedRunner();

				if (comboViewer != null && selectedRunner != null) {
					comboViewer.setInput(selectedRunner);

					if (combo.getItemCount() > 0) {
						combo.select(0);
					}
				}
				getGWizard().refresh();
			}
		});
		if (combo_1.getItemCount() > 0) {
			combo_1.select(0);

		}
	}

	/**
	 * this method make all initialization of content of the comboviewer
	 * 
	 * @param comboViewer
	 */
	private void manageCombo(ComboViewer comboViewer) {
		comboViewer.setContentProvider(new IStructuredContentProvider() {

			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}

			public void dispose() {
			}

			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof IGendoc2Runner) {
					IGendoc2Runner runner = (IGendoc2Runner) inputElement;
					return runner.getGendoc2Templates().toArray();
				}
				return null;
			}
		});

		comboViewer.setLabelProvider(new ILabelProvider() {

			public void removeListener(ILabelProviderListener listener) {
			}

			public boolean isLabelProperty(Object element, String property) {
				return true;
			}

			public void dispose() {
			}

			public void addListener(ILabelProviderListener listener) {
			}

			public Image getImage(Object element) {
				return null;
			}

			public String getText(Object element) {
				if (element instanceof IGendoc2Template) {
					return ((IGendoc2Template) element).getOutPutExtension();
				} else {
					return null;
				}

			}
		});

		IGendoc2Runner selectedRunner = getSelectedRunner();

		if (selectedRunner != null) {
			comboViewer.setInput(getSelectedRunner());

		}
		combo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				getGWizard().refresh();
			}
		});
		if (combo.getItemCount() > 0) {
			combo.select(0);
		}
	}

	/**
	 * this method give the good path in String associated to an URI
	 * 
	 * @param uri
	 *            the uri contains the path to access to the file selected
	 * @return the uri text contains the adequate access path in String
	 */
	public String getUriText(URI uri) {
		return uri.trimSegments(1).devicePath().toString() + "/";
	}

	/**
	 * @return the wizard in witch this page is added
	 */
	public Gendoc2Wizard getGWizard() {
		return (Gendoc2Wizard) getWizard();
	}

	/**
	 * @return the output path in string contained in the component text_1
	 */
	public String getOutputPath() {
		return text_1.getText();
	}

	/**
	 * verify if the value in the displayed in the comboviewer is a correct
	 * extension.
	 * 
	 * @return true, if is correct extension
	 */
	public boolean isCorrectExtension() {
		return (Arrays.asList(combo.getItems()).contains(combo.getText()));
	}

	/**
	 * this method verify that all component is filled or get a value
	 * 
	 * @return true, if successful
	 */
	public boolean allIsFilled() {
		if (combo_1 != null) {
			return ((text_1.getText().length() > 0)
					&& (text_2.getText().length() > 0) && existAird()
					&& existeTemplate() && (combo.getText().length() > 0));
		} else {
			return ((text_1.getText().length() > 0)
					&& (text_2.getText().length() > 0) && existAird() && (combo
					.getText().length() > 0));
		}
	}

	/**
	 * this method verify if the aird diagram field is not empty and has been
	 * filled with an .aird file.
	 * 
	 * @return true, if successful
	 */
	public boolean existAird() {
		boolean exist = false;

		if (text_3.getText().length() > 0) {
			IPath airdModelPath = new Path(text_3.getText());
			File airdFile = airdModelPath.toFile();
			if (airdFile.exists()) {
				exist = true;
			}
		}

		return exist;
	}

	/**
	 * this method search if a single aird file exists in the parent folder of
	 * the given model file.
	 * 
	 * @param fileName
	 *            the given model file.
	 * @return the aird file path, or null if there is no or more than one aird
	 *         files in the parent folder of the given model file.
	 */
	private String searchDefaultAird(IFile fileName) {
		String defaultAirdPath = null;
		IContainer folderParent = fileName.getParent();
		IResource[] resources;
		int airdFiles = 0;
		try {
			resources = folderParent.members();
			for (int i = 0; i < resources.length; i++) {
				IResource resource = resources[i];
				String fileExtension = resource.getFileExtension();
				if (fileExtension != null && fileExtension.equals("aird")) {
					defaultAirdPath = resource.getLocation().toOSString();
					airdFiles++;
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		if (airdFiles == 1) {
			return defaultAirdPath;
		}
		return null;
	}

	/**
	 * this method verify if the comboviewer1 is not empty or get at least one
	 * template
	 * 
	 * @return true, if successful
	 */
	public boolean existeTemplate() {
		return (combo_1.getText().length() > 0);
	}

	/**
	 * @return the selected runner of the template selected at this time in the
	 *         comboviewer1
	 */
	public IGendoc2Runner getSelectedRunner() {
		if (getGWizard().getRunners().size() == 1) {
			return getGWizard().getRunners().get(0);
		} else {
			if (comboViewer_1.getSelection() instanceof IStructuredSelection) {
				IStructuredSelection select = (IStructuredSelection) comboViewer_1
						.getSelection();
				return ((IGendoc2Runner) select.getFirstElement());
			}
		}
		return null;
	}

	/**
	 * @return template associated to the format of document to generate, that
	 *         is selected at this time in the comboviewer
	 */
	public IGendoc2Template getSelected() {
		if (comboViewer.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection select = (IStructuredSelection) comboViewer
					.getSelection();
			return ((IGendoc2Template) select.getFirstElement());
		}

		return null;
	}

	/**
	 * @return the full output path. It represent the good path of where to put
	 *         document to generate
	 */
	public String getFullOutputPath() {
		return text_1.getText() + text_2.getText() + combo.getText();
	}

	/**
	 * @return the path of the repository of the model selected by the user.
	 */
	public String getModel() {
		return URI.createURI(
				getGWizard().getInputFile().getLocationURI().toString())
				.devicePath();
	}

	/**
	 * @return the path of the repository of the aird diagram selected by the
	 *         user.
	 */
	public static String getAirdDiagramPath() {
		return text_3.getText();
	}

	@Override
	public boolean canFlipToNextPage() {
		return super.canFlipToNextPage();
	}
}
