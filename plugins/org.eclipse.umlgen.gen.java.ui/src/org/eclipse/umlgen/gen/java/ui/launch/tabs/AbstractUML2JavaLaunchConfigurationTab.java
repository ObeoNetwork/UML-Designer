/*******************************************************************************
 * Copyright (c) 2008, 2014 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephane Begaudeau (Obeo) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.gen.java.ui.launch.tabs;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * The common superclass of all UML2Java launch configuration tabs.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 2.0
 */
public abstract class AbstractUML2JavaLaunchConfigurationTab extends AbstractLaunchConfigurationTab {

    /**
     * Creates a Group widget.
     *
     * @param parent
     *            the parent composite to add this group to
     * @param text
     *            the text for the heading of the group
     * @param columns
     *            the number of columns within the group
     * @param hspan
     *            the horizontal span the group should take up on the parent
     * @param fill
     *            the style for how this composite should fill into its parent Can be one of
     *            <code>GridData.FILL_HORIZONAL</code>, <code>GridData.FILL_BOTH</code> or
     *            <code>GridData.FILL_VERTICAL</code>
     * @return the new group
     */
    protected Group createGroup(Composite parent, String text, int columns, int hspan, int fill) {
        Group g = new Group(parent, SWT.NONE);
        g.setLayout(new GridLayout(columns, false));
        g.setText(text);
        g.setFont(parent.getFont());
        GridData gd = new GridData(fill);
        gd.horizontalSpan = hspan;
        g.setLayoutData(gd);
        return g;
    }

    /**
     * Creates a help button.
     *
     * @param parent
     *            The composite parent
     * @param helpMessage
     *            the help message
     */
    protected void createHelpButton(Composite parent, String helpMessage) {
        Image image = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_LCL_LINKTO_HELP);
        ToolBar result = new ToolBar(parent, SWT.FLAT | SWT.NO_FOCUS);
        result.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        ToolItem item = new ToolItem(result, SWT.NONE);
        item.setImage(image);
        if (helpMessage != null && !"".equals(helpMessage)) { //$NON-NLS-1$
            item.setToolTipText(helpMessage);
        }
    }
}
