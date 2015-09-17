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
package org.eclipse.emf.eef.runtime.ui.widgets.referencestable;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * Utilities to get a checkbox image.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class CheckboxUtils {
    // Names of images used to represent checkboxes
    public static final String CHECKED_IMAGE = "checked";

    public static final String UNCHECKED_IMAGE = "unchecked";

    // For the checkbox images
    private static ImageRegistry imageRegistry = new ImageRegistry();

    /**
     * Note: An image registry owns all of the image objects registered with it,
     * and automatically disposes of them the SWT Display is disposed.
     */
    static {
        String iconPath = "icons/";
        imageRegistry.put(CHECKED_IMAGE, ImageDescriptor.createFromFile(CheckboxUtils.class, iconPath + CHECKED_IMAGE + ".gif"));
        imageRegistry.put(UNCHECKED_IMAGE, ImageDescriptor.createFromFile(CheckboxUtils.class, iconPath + UNCHECKED_IMAGE + ".gif"));
    }

    /**
     * Returns the image with the given key, or <code>null</code> if not found.
     */
    public static Image getCheckboxImage(boolean isSelected) {
        String key = isSelected ? CHECKED_IMAGE : UNCHECKED_IMAGE;
        return imageRegistry.get(key);
    }
}
