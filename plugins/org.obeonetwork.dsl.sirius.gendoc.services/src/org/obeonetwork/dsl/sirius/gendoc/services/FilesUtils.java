/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.sirius.gendoc.services;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Utility class to manage Files.
 * 
 * @author Axel Richard <a
 *         href="mailto:axel.richard@obeo.fr">axel.richard@obeo.fr</a>
 */
public final class FilesUtils {

	/** Number of temporary directory creation attempts. */
	private static final int TEMP_DIR_ATTEMPTS = 10000;

	/** Private constructor. */
	private FilesUtils() {

	}

	/**
	 * Creates a temporary directory in the system temp directory and deletes it
	 * on shutdown.
	 * 
	 * @return the new created directory.
	 */
	public static File createTempDirAndDeleteOnShutdown() {
		final File tempDir = createTempDir();
		deleteOnShutdown(tempDir);
		return tempDir;
	}

	/**
	 * Creates a temporary directory in the system temp directory.
	 * 
	 * @return the new created directory.
	 */
	private static File createTempDir() {
		final File baseDir = new File(System.getProperty("java.io.tmpdir"));
		final String baseName = System.currentTimeMillis() + "-";

		for (int counter = 0; counter < TEMP_DIR_ATTEMPTS; counter++) {
			final File tempDir = new File(baseDir, baseName + counter);
			if (tempDir.mkdir()) {
				return tempDir;
			}
		}
		throw new IllegalStateException("Failed to create directory within "
				+ TEMP_DIR_ATTEMPTS + " attempts (tried " + baseName + "0 to "
				+ baseName + (TEMP_DIR_ATTEMPTS - 1) + ')');
	}

	/**
	 * Delete the given file when the virtual machine shutdown.
	 * 
	 * @param fileToDelete
	 *            the given file.
	 */
	private static void deleteOnShutdown(final File fileToDelete) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				super.run();
				try {
					deleteRecursively(fileToDelete);
				} catch (IOException e) {
					final IStatus message = new Status(Status.ERROR,
							Activator.PLUGIN_ID,
							"Can't delete the temp directory !");
					Activator.getDefault().getLog().log(message);
				}
			}
		});
	}

	/**
	 * Deletes all the files within a directory. Does not delete the directory
	 * itself.
	 * 
	 * <p>
	 * Symbolic links within the directory are followed.
	 * 
	 * @param directory
	 *            the directory to delete the contents of
	 * @throws IOException
	 *             if an I/O error occurs
	 * @see #deleteRecursively
	 */
	private static void deleteDirectoryContents(File directory)
			throws IOException {
		if (!directory.isDirectory()) {
			return;
		}
		final File[] files = directory.listFiles();
		if (files == null) {
			throw new IOException("Error listing files for " + directory);
		}
		for (File file : files) {
			deleteRecursively(file);
		}
	}

	/**
	 * Deletes a file or directory and all contents recursively.
	 * 
	 * <p>
	 * If the file argument is a symbolic link the link will be deleted but not
	 * the target of the link. If the argument is a directory, symbolic links
	 * within the directory will be followed.
	 * 
	 * @param file
	 *            the file to delete
	 * @throws IOException
	 *             if an I/O error occurs
	 * @see #deleteDirectoryContents
	 */
	private static void deleteRecursively(File file) throws IOException {
		if (file.isDirectory()) {
			deleteDirectoryContents(file);
		}
		if (!file.delete()) {
			throw new IOException("Failed to delete " + file);
		}
	}
}
