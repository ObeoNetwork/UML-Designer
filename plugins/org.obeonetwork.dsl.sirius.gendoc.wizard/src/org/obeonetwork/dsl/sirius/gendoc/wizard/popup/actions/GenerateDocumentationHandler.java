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
 *  Barthelemy HABA (Atos Origin) barthelemy.haba@atosorigin.com - 
 *
 *****************************************************************************/
//CHECKSTYLE:OFF
package org.obeonetwork.dsl.sirius.gendoc.wizard.popup.actions;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.dsl.sirius.gendoc.wizard.Gendoc2Wizard;
import org.obeonetwork.dsl.sirius.gendoc.wizard.IGendoc2Runner;
import org.obeonetwork.dsl.sirius.gendoc.wizard.Utils;



/**
 * This class comes from :pserver:cvs.gforge.enseeiht.fr:/cvsroot/topcased-gendoc
 * ,plugins/gendoc2/org.topcased.gendoc2.wizard,org.topcased.gendoc2.wizard.
 */
public class GenerateDocumentationHandler extends org.eclipse.core.commands.AbstractHandler
{

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws org.eclipse.core.commands.ExecutionException
    {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof IStructuredSelection)
        {
            IStructuredSelection selec = (IStructuredSelection) selection;
            IFile file = (IFile) selec.getFirstElement();
            List<IGendoc2Runner> runners = null;

            runners = Utils.getRunners(file.getName());
            if (runners != null)
            {
                Gendoc2Wizard wizard = new Gendoc2Wizard(runners, file);
                WizardDialog wizardDialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
                wizardDialog.open();
            }
        }
        return null;
    }
}
