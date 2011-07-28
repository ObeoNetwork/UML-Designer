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
package org.obeonetwork.dsl.uml2.properties.uml.parts;

// Start of user code for imports
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ViewerFilter;



// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public interface CommentPropertiesEditionPart {

	/**
	 * @return the body
	 * 
	 */
	public String getBody();

	/**
	 * Defines a new body
	 * @param newValue the new body to set
	 * 
	 */
	public void setBody(String newValue);




	/**
	 * Init the annotatedElement
	 * @param settings settings for the annotatedElement ReferencesTable 
	 */
	public void initAnnotatedElement(ReferencesTableSettings settings);

	/**
	 * Update the annotatedElement
	 * @param newValue the annotatedElement to update
	 * 
	 */
	public void updateAnnotatedElement();

	/**
	 * Adds the given filter to the annotatedElement edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToAnnotatedElement(ViewerFilter filter);

	/**
	 * Adds the given filter to the annotatedElement edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToAnnotatedElement(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the annotatedElement table
	 * 
	 */
	public boolean isContainedInAnnotatedElementTable(EObject element);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * 
	 */
	public String getTitle();

	// Start of user code for additional methods

// End of user code

}
