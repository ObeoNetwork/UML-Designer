/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.service;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValueSpecification;

/**
 * Contains all the Java services called by a Java Declaration Step in the
 * all.components file.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class EEFService {

	/**
	 * Create a comment. EEF will be in charge of adding the comment to the
	 * parent element, so the function just as to create the new Comment and
	 * return it.
	 * 
	 * @param ownerElement
	 *            UML element
	 * @return New comment
	 */
	public static Comment createComment(EObject ownerElement) {
		Comment comment = null;
		if (ownerElement instanceof Element) {
			comment = UMLFactory.eINSTANCE.createComment();
			comment.getAnnotatedElements().add((Element) ownerElement);
		}
		return comment;
	}

    /**
     * Return the parent package of a state.
     * 
     * @param semanticObject
     *            State
     * @return Parent package
     */
    public static Element getParent(EObject semanticObject) {
        if (semanticObject instanceof Transition) {
            return ((Transition) semanticObject);
        }
        if (semanticObject instanceof OpaqueExpression) {
            return ((Element) semanticObject).getNearestPackage();
        }
        if (semanticObject instanceof ValueSpecification) {
            return ((Element) semanticObject).getOwner();
        }
        if (semanticObject instanceof Element) {
            return ((Element) semanticObject).getNearestPackage();
        }
        return null;
    }
}
