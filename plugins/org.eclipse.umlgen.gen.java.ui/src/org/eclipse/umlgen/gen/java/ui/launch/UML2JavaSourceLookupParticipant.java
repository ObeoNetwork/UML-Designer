/*******************************************************************************
 * Copyright (c) 2011, 2014 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephane Begaudeau (Obeo) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.gen.java.ui.launch;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;
import org.eclipse.umlgen.gen.java.utils.IUML2JavaConstants;

/**
 * This class will look for UML models in the workspace.
 *
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 * @since 1.0
 */
public class UML2JavaSourceLookupParticipant extends AbstractSourceLookupParticipant {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant#getSourceName(java.lang.Object)
     */
    public String getSourceName(Object object) throws CoreException {
        String result = null;

        if (object instanceof String) {
            result = (String)object;
        } else if (object instanceof IResource) {
            result = ((IResource)object).getName();
        } else if (object instanceof IAdaptable) {
            IResource resource = (IResource)((IAdaptable)object).getAdapter(IResource.class);
            if (resource != null) {
                result = resource.getName();
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant#findSourceElements(java.lang.Object)
     */
    @Override
    public Object[] findSourceElements(Object object) throws CoreException {
        Object[] result = super.findSourceElements(object);
        List<IResource> umlModels = new ArrayList<IResource>();
        for (Object obj : result) {
            if (obj instanceof IFile && ((IFile)obj).getFileExtension() != null
                    && IUML2JavaConstants.UML_FILE_EXTENSION.equals(((IFile)obj).getFileExtension())) {
                umlModels.add((IFile)obj);
            }
        }
        return umlModels.toArray(new Object[umlModels.size()]);
    }

}
