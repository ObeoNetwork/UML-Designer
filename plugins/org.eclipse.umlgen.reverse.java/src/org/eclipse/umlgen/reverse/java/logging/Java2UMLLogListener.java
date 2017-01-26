/*******************************************************************************
 * Copyright (c) 2008 Anyware Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Philippe Roland (Atos) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.reverse.java.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;

/**
 * Listens to log calls and feeds them into a text file.
 */
public class Java2UMLLogListener implements ILogListener {

    /** File of the log. */
    private File logFile;

    /**
     * Constructor.
     *
     * @param dir
     *            : directory of the file
     * @param name
     * @throws CoreException
     */
    public Java2UMLLogListener(File dir, String name) throws CoreException {
        if (dir != null && dir.isDirectory()) {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance()
                    .getTime());
            logFile = new File(dir, name + "_" + timeStamp + ".txt");
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                LogUtils.logThrowable(e);
            }
        }
    }

    /**
     * Log statuses to the file.
     *
     * @param status
     * @param plugin
     */
    public void logging(IStatus status, String plugin) {
        if (logFile == null) {
            return;
        }
        if (!logFile.exists()) {
            return;
        }
        try {
            FileWriter fstream = new FileWriter(logFile, true);
            BufferedWriter fbw = new BufferedWriter(fstream);
            String severityString = null;
            int severity = status.getSeverity();
            if (severity == IStatus.OK) {
                severityString = "OK"; //$NON-NLS-1$
            } else if (severity == IStatus.ERROR) {
                severityString = "ERROR"; //$NON-NLS-1$
            } else if (severity == IStatus.WARNING) {
                severityString = "WARNING"; //$NON-NLS-1$
            } else if (severity == IStatus.INFO) {
                severityString = "INFO"; //$NON-NLS-1$
            } else if (severity == IStatus.CANCEL) {
                severityString = "CANCEL"; //$NON-NLS-1$
            }
            String logMessage = severityString + " - " + status.getMessage();
            fbw.write(logMessage);
            fbw.newLine();
            fbw.close();
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.logThrowable(e);
        }
    }
}
