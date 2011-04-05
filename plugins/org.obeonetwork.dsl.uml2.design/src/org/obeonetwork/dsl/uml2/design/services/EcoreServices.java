/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Type;

/**
 * Utility services to manage Ecore UML ressources
 * 
 * @author St�phane Thibaudeau <a href="mailto:stephane.thibaudeau@obeo.fr">stephane.thibaudeau@obeo.fr</a>
 */
public class EcoreServices {
    
    static public Type findTypeByName(EObject object, String typeName) {
		Type result = findTypeByName(getAllRootsInResourceSet(object), typeName);
        return result;
	}
	
    static public Type findTypeByName(Iterable<EObject> roots, String typeName) {
        for (EObject root : roots) {
        	Type result = findTypeByNameFrom(root, typeName);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
    
    static public Collection<EObject> getAllRootsInResourceSet(EObject any) {
        Resource res = any.eResource();
        if (res != null && res.getResourceSet() != null) {
            Collection<EObject> roots = new ArrayList<EObject>();
            for (Resource childRes : res.getResourceSet().getResources()) {
                roots.addAll(childRes.getContents());
            }
            return roots;
        } else {
            return Collections.emptySet();
        }
    }
    
    static private Type findTypeByNameFrom(EObject root, String typeName) {
        if (root instanceof Type && nameMatches((Type)root, typeName)) {
            return (Type)root;
        }
        
        for (Iterator<EObject> i =root.eAllContents(); i.hasNext();) {
        	EObject obj = i.next();
        	if (obj instanceof Type && nameMatches((Type)obj, typeName)) {
        		return (Type)obj;
        	}
        }
        
        return null;
    }
    
    static private boolean nameMatches(Type type, String name) {
        if (type != null && type.getName() != null && name != null) {
            return type.getName().trim().equalsIgnoreCase(name.trim());
        } else {
            return false;
        }
    }
}
