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
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.gendoc.GendocProcess;
import org.eclipse.gendoc.services.GendocServices;
import org.eclipse.gendoc.services.IGendocDiagnostician;
import org.eclipse.gendoc.services.IProgressMonitorService;
import org.eclipse.gendoc.services.exception.GenDocException;
import org.eclipse.gendoc.tags.handlers.IConfigurationService;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * The Class Gendoc2Wizard. This class comes from :pserver:cvs.gforge.enseeiht.fr:/cvsroot/topcased-gendoc
 * ,plugins/gendoc2/org.topcased.gendoc2.wizard,org.topcased.gendoc2.wizard.
 */
public class Gendoc2Wizard extends Wizard {

	/** The Constant mymessage. */
	private static final String MY_MESSAGE = "Error generating documentation.\n";

	/** The runners. */
	private final List<IGendoc2Runner> runners;

	/** The file. */
	private final IFile file;

	/** The page. */
	private Gendoc2WizardPage page;

	/**
	 * Instantiates a new gendoc2 wizard.
	 * 
	 * @param runners
	 *            the runners
	 * @param file
	 *            the file
	 */
	public Gendoc2Wizard(final List<IGendoc2Runner> runners, final IFile file) {
		this.runners = runners;
		Collections.sort(this.runners, new Comparator<IGendoc2Runner>() {
			public int compare(IGendoc2Runner o1, IGendoc2Runner o2) {
				String label1 = o1.getLabel();
				if (label1 == null) {
					label1 = "";
				}
				String label2 = o2.getLabel();
				if (label2 == null) {
					label2 = "";
				}
				return label1.compareTo(label2);
			}
		});
		this.file = file;
		setWindowTitle("Generate Documentation");
		setNeedsProgressMonitor(true);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		page = new Gendoc2WizardPage();
		addPage(page);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		try {
			getContainer().run(false, false, new IRunnableWithProgress() {
				@SuppressWarnings("static-access")
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					final IGendocDiagnostician diagnostician = GendocServices.getDefault().getService(IGendocDiagnostician.class);
					diagnostician.init();
					final IProgressMonitorService monitorService = (IProgressMonitorService)GendocServices.getDefault().getService(IProgressMonitorService.class);
					monitorService.setMonitor(monitor);

					try {
						final IConfigurationService parameter = GendocServices.getDefault().getService(IConfigurationService.class);
						parameter.addParameter(replacePercentBySpace(page.getSelected().getOutputKey(), 3), replacePercentBySpace(page.getFullOutputPath(), 3));
						parameter.addParameter(replacePercentBySpace(page.getSelected().getModelKey(), 3), replacePercentBySpace(page.getModel(), 3));
						parameter.addParameter(replacePercentBySpace(page.getSelected().getAirdDiagramKey(), 3), replacePercentBySpace(page.getAirdDiagramPath(), 3));
						final GendocProcess gendocProcess = new GendocProcess();
						final String resultFile = gendocProcess.runProcess(page.getSelected().getTemplate());
						
						handleDiagnostic(diagnostician.getResultDiagnostic(),
								"The file has been generated but contains errors :\n", resultFile);
						
					} catch (GenDocException e) {
						Activator.getDefault().getLog()
								.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getUIMessage(), e));
						diagnostician.addDiagnostic(new BasicDiagnostic(Diagnostic.ERROR,
								Activator.PLUGIN_ID, 0, e.getUIMessage(), null));
						handleDiagnostic(diagnostician.getResultDiagnostic(),
								"An error occured during generation.", null);
					} catch (Throwable t) {
						Activator.getDefault().getLog()
								.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, t.getMessage(), t));
						diagnostician.addDiagnostic(new BasicDiagnostic(Diagnostic.ERROR,
								Activator.PLUGIN_ID, 0, t.getMessage(), t.getStackTrace()));
						handleDiagnostic(diagnostician.getResultDiagnostic(),
								"An unexpected error occured during the generation.", null);
					} finally {
						IPath projectPath = new Path(page.getFullOutputPath()).removeFileExtension().removeLastSegments(1);
						IWorkspace workspace = ResourcesPlugin.getWorkspace();
						IWorkspaceRoot root = workspace.getRoot();
						IPath rootPath = root.getRawLocation();
						projectPath = projectPath.makeRelativeTo(rootPath);
						IResource r = root.findMember(projectPath);
						try {
							r.refreshLocal(IResource.DEPTH_ONE, null);
						} catch (CoreException e) {
							Activator.getDefault().getLog().log(new Status(IStatus.INFO, Activator.PLUGIN_ID, "Unable to refresh folder : " + projectPath.toString(), e));
						}
						GendocServices.getDefault().clear();
					}
				}

				/**
				 * Handle diagnostic.
				 * 
				 * @param resultDiagnostic
				 *            the result diagnostic
				 */
				private void handleDiagnostic(final Diagnostic resultDiagnostic, final String message,
						final String resultFilePath) {
					if (resultDiagnostic.getSeverity() == Diagnostic.OK) {
						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								MessageDialog.openInformation(PlatformUI.getWorkbench()
										.getActiveWorkbenchWindow().getShell(), "Document generator",
										"The document has been generated successfully: \n" + resultFilePath);
							}
						});
					} else {
						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								ErrorDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
										.getShell(), "Document generator", MY_MESSAGE,
										BasicDiagnostic.toIStatus(resultDiagnostic));
							}
						});
					}
				}
			});
			return true;
		} catch (InvocationTargetException e) {
			return false;
		} catch (InterruptedException e) {
			return false;
		} catch (Exception e) {
			((WizardPage)getContainer().getCurrentPage())
					.setErrorMessage("Documentation generation error");

			return false;
		}
	}

	/**
	 * Gets the runners.
	 * 
	 * @return the runners contains all the possibles templates necessary for document generation
	 */
	public List<IGendoc2Runner> getRunners() {
		return runners;
	}

	/**
	 * Gets the input file.
	 * 
	 * @return the input file represent the model on witch user have made the click
	 */
	public final IFile getInputFile() {
		return file;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	/**
	 * Returns whether this wizard could be finished without further user interaction. The result of this
	 * method is typically used by the wizard container to enable or disable the Finish button.
	 * 
	 * @return true if the wizard could be finished, and false otherwise
	 */
	@Override
	public boolean canFinish() {
		boolean state = true;
		int cpteur = 1;
		final String space = " ";

		if (getContainer().getCurrentPage() == null) {
			return false;
		}
		((WizardPage)getContainer().getCurrentPage()).setErrorMessage(null);
		final StringBuffer message = new StringBuffer();
		final String path = page.getOutputPath();
		final File f = new File(path);
		if (!f.isDirectory() || !f.exists()) {
			message.append(cpteur + " : Please specify a directory path in the path field \n ");
			state = false;
			cpteur += 1;
		}

		if (!page.isCorrectExtension()) {
			message.append(space + cpteur + " : OutPut file extension is not correct\n ");
			cpteur += 1;
			state = false;
		}

		if (!page.allIsFilled()) {
			message.append(space + cpteur + " : Please fill all parameters fields \n ");
			cpteur += 1;
			state = false;
		}

		if (!existAird()) {
			message.append(space + cpteur + " : Please specify a correct aird diagram file path \n ");
			state = false;
		}

		if (!state) {
			((WizardPage)getContainer().getCurrentPage()).setErrorMessage(message.toString());
			return false;
		}
		return true;
	}

	/**
	 * Refresh. this method is use to refresh the wizard page
	 */
	public void refresh() {
		getContainer().updateButtons();
		getContainer().updateMessage();
	}

	/**
     * @param theString string contening percentage caracteres
     * @param pas the nomber of caractere that represent space in the système
     * @return theString with any percentage caractere
     */
    public String replacePercentBySpace(String theString, int pas)
    {
        StringBuffer buffer = new StringBuffer(theString);
        int pos = 1;
        while (theString.contains("%"))
        {
            pos = theString.indexOf("%"); // Position de la 1ére occurence
            theString = buffer.replace(pos, pos + pas, " ").toString();
        }
        return theString;
    }
    
	/**
	 * This method verify if the aird diagram field is not empty and has been filled with an .aird file.
	 * 
	 * @return true, if successful
	 */
	@SuppressWarnings("static-access")
	private boolean existAird() {
		boolean exist = false;

		if (page.getAirdDiagramPath().length() > 0) {
			final IPath airdModelPath = new Path(page.getAirdDiagramPath());
			final File airdFile = airdModelPath.toFile();
			if ("aird".equalsIgnoreCase(airdModelPath.getFileExtension()) && airdFile.exists()) {
				exist = true;
			}
		}

		return exist;
	}
}
