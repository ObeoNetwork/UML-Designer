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
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.jface.viewers.ViewerFilter;



// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public interface TemplateBindingPropertiesEditionPart {

	/**
	 * @return the signature
	 * 
	 */
	public EObject getSignature();

	/**
	 * Init the signature
	 * @param settings the combo setting
	 */
	public void initSignature(EObjectFlatComboSettings settings);

	/**
	 * Defines a new signature
	 * @param newValue the new signature to set
	 * 
	 */
	public void setSignature(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setSignatureButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the signature edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToSignature(ViewerFilter filter);

	/**
	 * Adds the given filter to the signature edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToSignature(ViewerFilter filter);


	/**
	 * @return the boundElement
	 * 
	 */
	public EObject getBoundElement();

	/**
	 * Init the boundElement
	 * @param settings the combo setting
	 */
	public void initBoundElement(EObjectFlatComboSettings settings);

	/**
	 * Defines a new boundElement
	 * @param newValue the new boundElement to set
	 * 
	 */
	public void setBoundElement(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setBoundElementButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the boundElement edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToBoundElement(ViewerFilter filter);

	/**
	 * Adds the given filter to the boundElement edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToBoundElement(ViewerFilter filter);





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
