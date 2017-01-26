/*******************************************************************************
 * Copyright (c) 2015 CNES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cedric Notot (Obeo) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.ui.properties;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * Property page on UML models to group a set of pages to configure the code generation.
 *
 * @author Cedric Notot (Obeo)
 */
public class UMLGeneratorsProperties extends PropertyPage {

    /**
     * Constructor.
     */
    public UMLGeneratorsProperties() {
        super();
        noDefaultAndApplyButton();
    }

    @Override
    protected Control createContents(Composite parent) {
        // TODO Auto-generated method stub
        return null;
    }

}
