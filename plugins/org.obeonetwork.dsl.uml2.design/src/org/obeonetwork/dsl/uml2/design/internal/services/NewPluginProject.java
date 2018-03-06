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
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.pde.core.project.IBundleProjectDescription;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.obeonetwork.dsl.uml2.core.internal.services.LogServices;

/**
 * This class provide some static methods for the creation of plug-in project programmatically.
 *
 * @author Mohamed-Lamine BOUKHANOUFA <a href="mailto:mohamed-lamine.boukhanoufa@obeo.fr" >mohamed-lamine.
 *         boukhanoufa@obeo.fr</a>
 */
public class NewPluginProject {
	String newLine = "\n"; //$NON-NLS-1$

	/**
	 * Manifest builder.
	 */
	private final String PDE_MANIFEST_BUILDER = "org.eclipse.pde.ManifestBuilder"; //$NON-NLS-1$

	/**
	 * PDE schema builder.
	 */
	private final String PDE_SCHEMA_BUILDER = "org.eclipse.pde.SchemaBuilder"; //$NON-NLS-1$

	/**
	 * Create an instance of this class.
	 */
	public NewPluginProject() {
		super();
	}

	private void assertExist(final IContainer c) {
		if (!c.exists()) {
			if (!c.getParent().exists()) {
				assertExist(c.getParent());
			}
			if (c instanceof IFolder) {
				try {
					((IFolder)c).create(false, true, new NullProgressMonitor());
				} catch (final CoreException e) {
					LogServices.INSTANCE.error("create(false, true,NullProgressMonitor) not handled", //$NON-NLS-1$
							e);
				}
			}

		}

	}

	private void createBuildProps(final IProgressMonitor progressMonitor, final IProject project,
			final List<String> srcFolders) {
		final StringBuilder bpContent = new StringBuilder("source.. = "); //$NON-NLS-1$
		for (final Iterator<String> iterator = srcFolders.iterator(); iterator.hasNext();) {
			bpContent.append(iterator.next()).append('/');
			if (iterator.hasNext()) {
				bpContent.append(","); //$NON-NLS-1$
			}
		}
		bpContent.append(newLine);
		bpContent.append("bin.includes = META-INF/,.\n"); //$NON-NLS-1$
		createFile("build.properties", project, bpContent.toString(), progressMonitor); //$NON-NLS-1$
	}

	/**
	 * Create a new file.
	 *
	 * @param name
	 *            of the file
	 * @param container
	 *            of the file
	 * @param content
	 *            content
	 * @param progressMonitor
	 *            progress monitor
	 * @return A new file
	 */
	public IFile createFile(final String name, final IContainer container, final String content,
			final IProgressMonitor progressMonitor) {
		final IFile file = container.getFile(new Path(name));
		assertExist(file.getParent());
		try {
			final InputStream stream = new ByteArrayInputStream(content.getBytes(file.getCharset()));
			if (file.exists()) {
				file.setContents(stream, true, true, progressMonitor);
			} else {
				file.create(stream, true, progressMonitor);
			}
			stream.close();
		} catch (final Exception e) {
			LogServices.INSTANCE.error("createFile(" + name.getClass() + "," //$NON-NLS-1$ //$NON-NLS-2$
					+ container.getClass() + "," + content.getClass() + "," //$NON-NLS-1$ //$NON-NLS-2$
					+ progressMonitor.getClass() + ") not handled", e); //$NON-NLS-1$
			e.printStackTrace();
		}
		progressMonitor.worked(1);

		return file;
	}

	/**
	 * Create a new file.
	 *
	 * @param name
	 *            of the file
	 * @param container
	 *            of the file
	 * @param content
	 *            content
	 * @param charSet
	 * @param progressMonitor
	 *            progress monitor
	 * @return a new file
	 * @throws CoreException
	 */
	public IFile createFile(final String name, final IContainer container, final String content,
			final String charSet, final IProgressMonitor progressMonitor) throws CoreException {
		final IFile file = createFile(name, container, content, progressMonitor);
		if (file != null && charSet != null) {
			file.setCharset(charSet, progressMonitor);
		}

		return file;
	}

	/**
	 * Create a new file.
	 *
	 * @param name
	 *            of the destination file
	 * @param container
	 *            directory containing the the destination file
	 * @param contentUrl
	 *            Url pointing to the src of the content
	 * @param progressMonitor
	 *            used to interact with and show the user the current operation status
	 * @return a new file
	 */
	public IFile createFile(final String name, final IContainer container, final URL contentUrl,
			final IProgressMonitor progressMonitor) {

		final IFile file = container.getFile(new Path(name));
		InputStream inputStream = null;
		try {
			inputStream = contentUrl.openStream();
			if (file.exists()) {
				file.setContents(inputStream, true, true, progressMonitor);
			} else {
				file.create(inputStream, true, progressMonitor);
			}
			inputStream.close();
		} catch (final Exception e) {

			LogServices.INSTANCE.error("createFile(" + name.getClass() + "," //$NON-NLS-1$ //$NON-NLS-2$
					+ container.getClass() + "," + contentUrl.getClass() + "," //$NON-NLS-1$ //$NON-NLS-2$
					+ progressMonitor.getClass() + "," + ") not handled", e); //$NON-NLS-1$ //$NON-NLS-2$
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (final IOException e) {
					LogServices.INSTANCE.error("inputStream.close() not handled", //$NON-NLS-1$
							e);
				}
			}
		}
		progressMonitor.worked(1);

		return file;
	}

	private void createManifest(final String projectName, final Set<String> requiredBundles,
			final List<String> exportedPackages, final IProgressMonitor progressMonitor,
			final IProject project) throws CoreException {
		final StringBuilder maniContent = new StringBuilder("Manifest-Version: 1.0\n"); //$NON-NLS-1$
		maniContent.append("Bundle-ManifestVersion: 2\n"); //$NON-NLS-1$
		maniContent.append("Bundle-Name: " + projectName + newLine); //$NON-NLS-1$
		maniContent.append("Bundle-SymbolicName: " + projectName + "; singleton:=true\n"); //$NON-NLS-1$ //$NON-NLS-2$
		maniContent.append("Bundle-Version: 1.0.0.qualifier\n"); //$NON-NLS-1$
		// maniContent.append("Bundle-Localization: plugin\n");
		// maniContent.append("Require-Bundle: ");
		for (final String entry : requiredBundles) {
			maniContent.append(" " + entry + ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (exportedPackages != null && !exportedPackages.isEmpty()) {
			maniContent.append("Require-Bundle: " + exportedPackages.get(0)); //$NON-NLS-1$
			for (int i = 1, x = exportedPackages.size(); i < x; i++) {
				maniContent.append(",\n " + exportedPackages.get(i)); //$NON-NLS-1$
			}
			maniContent.append(newLine);
		}
		maniContent.append("Bundle-ActivationPolicy: lazy\n"); //$NON-NLS-1$

		maniContent.append("Bundle-RequiredExecutionEnvironment: J2SE-1.5\r\n"); //$NON-NLS-1$

		final IFolder metaInf = project.getFolder("META-INF"); //$NON-NLS-1$
		metaInf.create(false, true, new SubProgressMonitor(progressMonitor, 1));
		createFile("MANIFEST.MF", metaInf, maniContent.toString(), progressMonitor); //$NON-NLS-1$
	}

	/**
	 * Create a new plug-in project.
	 *
	 * @param pluginProjectName
	 *            plug-in name
	 * @param srcFolders
	 *            source folder
	 * @param referencedProjects
	 *            referenced projects
	 * @param requiredBundles
	 *            required bundles
	 * @param exportedPackages
	 *            exported packages
	 * @param progressMonitor
	 *            progress monitor
	 * @param theShell
	 *            shell
	 * @return a new project.
	 */
	public IProject createPluginProject(final String pluginProjectName, final List<String> srcFolders,
			final List<IProject> referencedProjects, final Set<String> requiredBundles,
			final List<String> exportedPackages, final IProgressMonitor progressMonitor,
			final Shell theShell) {
		IProject project = null;
		try {
			progressMonitor.beginTask("", 10); //$NON-NLS-1$
			progressMonitor.subTask("Creating project " + pluginProjectName); //$NON-NLS-1$
			final IWorkspace workspace = ResourcesPlugin.getWorkspace();
			project = workspace.getRoot().getProject(pluginProjectName);

			// Clean up any old project information.
			if (project.exists()) {
				project.delete(true, true, new SubProgressMonitor(progressMonitor, 1));
			}

			final IJavaProject javaProject = JavaCore.create(project);
			final IProjectDescription projectDescription = ResourcesPlugin.getWorkspace()
					.newProjectDescription(pluginProjectName);
			projectDescription.setLocation(null);
			project.create(projectDescription, new SubProgressMonitor(progressMonitor, 1));
			final List<IClasspathEntry> classpathEntries = new ArrayList<IClasspathEntry>();
			if (referencedProjects.size() != 0) {
				projectDescription.setReferencedProjects(
						referencedProjects.toArray(new IProject[referencedProjects.size()]));
				for (final IProject referencedProject : referencedProjects) {
					final IClasspathEntry referencedProjectClasspathEntry = JavaCore
							.newProjectEntry(referencedProject.getFullPath());
					classpathEntries.add(referencedProjectClasspathEntry);
				}
			}

			projectDescription
			.setNatureIds(new String[] {JavaCore.NATURE_ID, IBundleProjectDescription.PLUGIN_NATURE});

			final ICommand java = projectDescription.newCommand();
			java.setBuilderName(JavaCore.BUILDER_ID);

			final ICommand manifest = projectDescription.newCommand();
			manifest.setBuilderName(PDE_MANIFEST_BUILDER);

			final ICommand schema = projectDescription.newCommand();
			schema.setBuilderName(PDE_SCHEMA_BUILDER);

			projectDescription.setBuildSpec(new ICommand[] {java, manifest, schema});

			project.open(new SubProgressMonitor(progressMonitor, 1));
			project.setDescription(projectDescription, new SubProgressMonitor(progressMonitor, 1));

			Collections.reverse(srcFolders);
			for (final String src : srcFolders) {
				final IFolder srcContainer = project.getFolder(src);
				if (!srcContainer.exists()) {
					srcContainer.create(false, true, new SubProgressMonitor(progressMonitor, 1));
				}
				final IClasspathEntry srcClasspathEntry = JavaCore.newSourceEntry(srcContainer.getFullPath());
				classpathEntries.add(0, srcClasspathEntry);
			}

			classpathEntries.add(JavaCore.newContainerEntry(new Path(
					"org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/J2SE-1.5"))); //$NON-NLS-1$
			classpathEntries
			.add(JavaCore.newContainerEntry(new Path("org.eclipse.pde.core.requiredPlugins"))); //$NON-NLS-1$

			javaProject.setRawClasspath(
					classpathEntries.toArray(new IClasspathEntry[classpathEntries.size()]),
					new SubProgressMonitor(progressMonitor, 1));

			javaProject.setOutputLocation(new Path("/" + pluginProjectName + "/bin"), new SubProgressMonitor( //$NON-NLS-1$ //$NON-NLS-2$
					progressMonitor, 1));
			createManifest(pluginProjectName, requiredBundles, exportedPackages, progressMonitor, project);
			createBuildProps(progressMonitor, project, srcFolders);
		} catch (final Exception exception) {
			LogServices.INSTANCE.error("createPluginProject(" + pluginProjectName.getClass() + "," //$NON-NLS-1$ //$NON-NLS-2$
					+ srcFolders.getClass() + "," //$NON-NLS-1$
					+ referencedProjects.getClass() + "," //$NON-NLS-1$
					+ requiredBundles.getClass() + "," //$NON-NLS-1$
					+ exportedPackages.getClass() + "," //$NON-NLS-1$
					+ progressMonitor.getClass() + "," //$NON-NLS-1$
					+ theShell.getClass() + ") not handled", exception); //$NON-NLS-1$
			exception.printStackTrace();
		} finally {
			progressMonitor.done();
		}

		return project;
	}

	/**
	 * Open file to edit.
	 *
	 * @param s
	 *            a shell
	 * @param file
	 *            the file to edit
	 */
	public void openFileToEdit(final Shell s, final IFile file) {
		s.getDisplay().asyncExec(new Runnable() {
			public void run() {
				final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (final PartInitException e) {
					LogServices.INSTANCE.error("openFileToEdit(" + s.getClass() //$NON-NLS-1$
					+ "," + file.getClass() + ") not handled", e); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		});
	}
}
