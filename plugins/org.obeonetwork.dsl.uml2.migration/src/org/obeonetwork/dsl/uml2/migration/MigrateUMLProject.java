/*******************************************************************************
 * Copyright (c) 2009, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.migration;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.common.collect.Lists;

public class MigrateUMLProject extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		List<IProject> projectsToMigrate = Lists.newArrayList();
		if (selection instanceof StructuredSelection) {
			for (Object obj : ((StructuredSelection) selection).toArray()) {
				if (obj instanceof IProject) {
					projectsToMigrate.add((IProject) obj);
				}
			}
		}

		for (IProject project : projectsToMigrate) {
			UmlProjectMigrationOperation migrate = new UmlProjectMigrationOperation(
					project);
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.run(true, false, migrate);
			} catch (InvocationTargetException e) {
				Activator.getDefault().logError(e);
			} catch (InterruptedException e) {
				Activator.getDefault().logError(e);
			}
		}
		return null;
	}
}
