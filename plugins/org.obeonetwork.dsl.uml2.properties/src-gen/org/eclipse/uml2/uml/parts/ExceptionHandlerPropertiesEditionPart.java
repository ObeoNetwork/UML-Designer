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
package org.eclipse.uml2.uml.parts;

// Start of user code for imports
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ViewerFilter;



// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public interface ExceptionHandlerPropertiesEditionPart {

	/**
	 * @return the handlerBody
	 * 
	 */
	public EObject getHandlerBody();

	/**
	 * Init the handlerBody
	 * @param settings the combo setting
	 */
	public void initHandlerBody(EObjectFlatComboSettings settings);

	/**
	 * Defines a new handlerBody
	 * @param newValue the new handlerBody to set
	 * 
	 */
	public void setHandlerBody(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setHandlerBodyButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the handlerBody edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToHandlerBody(ViewerFilter filter);

	/**
	 * Adds the given filter to the handlerBody edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToHandlerBody(ViewerFilter filter);


	/**
	 * @return the exceptionInput
	 * 
	 */
	public EObject getExceptionInput();

	/**
	 * Init the exceptionInput
	 * @param settings the combo setting
	 */
	public void initExceptionInput(EObjectFlatComboSettings settings);

	/**
	 * Defines a new exceptionInput
	 * @param newValue the new exceptionInput to set
	 * 
	 */
	public void setExceptionInput(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setExceptionInputButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the exceptionInput edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToExceptionInput(ViewerFilter filter);

	/**
	 * Adds the given filter to the exceptionInput edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToExceptionInput(ViewerFilter filter);




	/**
	 * Init the exceptionType
	 * @param settings settings for the exceptionType ReferencesTable 
	 */
	public void initExceptionType(ReferencesTableSettings settings);

	/**
	 * Update the exceptionType
	 * @param newValue the exceptionType to update
	 * 
	 */
	public void updateExceptionType();

	/**
	 * Adds the given filter to the exceptionType edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToExceptionType(ViewerFilter filter);

	/**
	 * Adds the given filter to the exceptionType edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToExceptionType(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the exceptionType table
	 * 
	 */
	public boolean isContainedInExceptionTypeTable(EObject element);


	/**
	 * @return the protectedNode
	 * 
	 */
	public EObject getProtectedNode();

	/**
	 * Init the protectedNode
	 * @param settings the combo setting
	 */
	public void initProtectedNode(EObjectFlatComboSettings settings);

	/**
	 * Defines a new protectedNode
	 * @param newValue the new protectedNode to set
	 * 
	 */
	public void setProtectedNode(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setProtectedNodeButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the protectedNode edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToProtectedNode(ViewerFilter filter);

	/**
	 * Adds the given filter to the protectedNode edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToProtectedNode(ViewerFilter filter);





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
