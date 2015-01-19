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
package org.obeonetwork.dsl.uml2.design.api.services;

import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;


/**
 * A set of services to handle the Documentation table.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class DocumentationTableServices {
	/**
	 * Exists comment.
	 *
	 * @param element
	 *            Element
	 * @return True if comments exists for the given element
	 */
	public boolean existComments(Element element) {
		return element.getOwnedComments() != null && element.getOwnedComments().size() > 0;
	}

	/**
	 * Get comment.
	 *
	 * @param element
	 *            Element
	 * @return Comment associated to the given element
	 */
	public Comment getComment(Element element) {
		if (element.getOwnedComments().size() > 0) {
			return element.getOwnedComments().get(0);
		}
		return null;
	}

	/**
	 * Get comment body.
	 *
	 * @param element
	 *            Element
	 * @return Comment body associated to the given element
	 */
	public String getCommentBody(Element element) {
		final Comment comment = getComment(element);
		if (comment != null) {
			return comment.getBody();
		}
		return null;
	}

	/**
	 * Check if an element does not define comments.
	 *
	 * @param element
	 *            Element
	 * @return True if element does not define comments
	 */
	public boolean notExistComments(Element element) {
		return !existComments(element);
	}
}
