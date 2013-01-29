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
import org.eclipse.uml2.uml.UMLFactory;

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
		}
		return comment;
	}
}
