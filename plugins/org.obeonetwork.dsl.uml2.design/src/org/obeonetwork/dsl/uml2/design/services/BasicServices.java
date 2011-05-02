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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

import fr.obeo.acceleo.ecore.factories.EFactory;
import fr.obeo.acceleo.ecore.factories.FactoryException;

/**
 * Basic services for advance.
 * 
 * @author ymortier
 */
public class BasicServices {

    /**
     * Delete the specified element.
     * 
     * @param element
     *            the element to delete
     */
    public void deleteOldContainmentElement(EObject element) {
        EcoreUtil.delete(element);
    }

    /**
     * Return <code>true</code> if <code>mayBeChild</code> is a child of
     * <code>current</code>.
     * 
     * @param current
     *            the current object.
     * @param mayBeChild
     *            the object that may be the child.
     * @return <code>true</code> if <code>mayBeChild</code> is a child of
     *         <code>current</code>.
     */
    public boolean isChild(EObject current, EObject mayBeChild) {
        while (mayBeChild.eContainer() != null) {
            if (mayBeChild.eContainer().equals(current)) {
                return true;
            }
            mayBeChild = mayBeChild.eContainer();
        }
        return false;
    }

    /**
     * Return the first {@link DSemanticDecorator} that have the
     * <code>semanticObject</code> as target.
     * 
     * @param semanticObject
     *            the semantic object.
     * @param rootView
     *            the root view.
     * @return the first {@link DSemanticDecorator} that have the
     *         <code>semanticObject</code> as target or null if the
     *         {@link DSemanticDecorator} cannot be found.
     */
    public EObject getViewPointElement(EObject semanticObject, EObject rootView) {
        EObject foundObject = null;
        if (EFactory.eInstanceOf(rootView, "DSemanticDecorator")) {
            try {
                EObject target = EFactory.eGetAsEObject(rootView, "target");
                if (target != null && EcoreUtil.equals(semanticObject, target)) {
                    foundObject = rootView;
                }
            } catch (FactoryException e) {
                LogServices.error("error while evaluating the view model", e);
            }
        }
        Iterator allContents = rootView.eAllContents();
        while (allContents.hasNext() && foundObject == null) {
            EObject next = (EObject) allContents.next();
            if (EFactory.eInstanceOf(next, "DSemanticDecorator")) {
                EObject target = null;
                try {
                    target = EFactory.eGetAsEObject(next, "target");
                } catch (FactoryException e) {
                    LogServices.error("error while evaluating the view model", e);
                }
                if (target != null && semanticObject.equals(target)) {
                    foundObject = next;
                }
            }
        }
        return foundObject;
    }

    /**
     * Return <code>true</code> if the viewNode is contained by the
     * {@link DDiagram}.
     * 
     * @param eObject
     *            the object.
     * @param viewNode
     *            the view node.
     * @return <code>true</code> if the viewNode is contained by the
     *         {@link DDiagram}.
     */
    public boolean isAttachToViewpoint(EObject eObject, EObject viewNode) {
        if (EFactory.eInstanceOf(viewNode, "DNode")) {
            EObject viewPoint = null;
            try {
                viewPoint = (EObject) EFactory.eGet(viewNode, "getParentViewPoint");
            } catch (FactoryException e) {
                LogServices.error("error while evaluating the view model", e);
            }
            if (EFactory.eInstanceOf(viewPoint, "DSemanticDiagram")) {
                try {
                    return eObject.eContainer().equals(EFactory.eGet(viewPoint, "target"));
                } catch (FactoryException e) {
                    LogServices.error("error while evaluating the view model", e);
                }
            }
        }
        return false;
    }

    /**
     * Return <code>true</code> if the node is the {@link DDiagram}.
     * 
     * @param eObject
     *            the object.
     * @return <code>true</code> if the node is the {@link DDiagram}.
     */
    public boolean isViewPoint(EObject eObject) {
        return EFactory.eInstanceOf(eObject, "DDiagram");
    }

    /**
     * Return an unique name in the reference <code>referenceName</code> of
     * the object <code>instance</code>.
     * 
     * @param instance
     *            the eObject.
     * @param base
     *            the base name.
     * @param referenceName
     *            the name of the reference.
     * @param nameAttribute
     * @return an unique name.
     */
    public String getUniqueName(EObject instance, String base, String referenceName, String nameAttribute) {
        String uniqueName = base;
        EStructuralFeature feature = instance.eClass().getEStructuralFeature(referenceName);
        if (feature != null) {
            if (feature.isMany()) {
                Collection values = (Collection) instance.eGet(feature);
                Set names = new HashSet();
                if (nameAttribute != null && !nameAttribute.trim().equals("")) {
                    Iterator iterValues = values.iterator();
                    while (iterValues.hasNext()) {
                        EObject next = (EObject) iterValues.next();
                        EStructuralFeature nameFeature = next.eClass().getEStructuralFeature(nameAttribute);
                        if (nameFeature != null && nameFeature.getEType().getName().equalsIgnoreCase("string")) {
                            String currentName = (String) next.eGet(nameFeature);
                            if (currentName != null)
                                names.add(currentName);
                        }
                    }
                } else if (feature.getEType().equals(EcorePackage.eINSTANCE.getEString())) {
                    names = new HashSet(values);
                }
                int i = 1;
                while (names.contains(uniqueName)) {
                    uniqueName = base + i++;
                }
            }
        }
        return uniqueName;
    }

    /**
     * TODO comment
     * 
     * @param element
     * @return
     */
    public EObject getTargetViewPoint(EObject element) {
        EObject result = null;
        try {
            EObject viewPoint = (EObject) EFactory.eGet(element, "getParentViewPoint");
            if (viewPoint != null) {
                result = EFactory.eGetAsEObject(viewPoint, "target");
            }
        } catch (FactoryException e) {
            LogServices.error("error while evaluating the view model", e);
        }
        return result;
    }
}
