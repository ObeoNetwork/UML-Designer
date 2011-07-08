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
public interface TemplateParameterSubstitutionPropertiesEditionPart {

	/**
	 * @return the formal
	 * 
	 */
	public EObject getFormal();

	/**
	 * Init the formal
	 * @param settings the combo setting
	 */
	public void initFormal(EObjectFlatComboSettings settings);

	/**
	 * Defines a new formal
	 * @param newValue the new formal to set
	 * 
	 */
	public void setFormal(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setFormalButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the formal edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToFormal(ViewerFilter filter);

	/**
	 * Adds the given filter to the formal edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToFormal(ViewerFilter filter);


	/**
	 * @return the actual
	 * 
	 */
	public EObject getActual();

	/**
	 * Init the actual
	 * @param settings the combo setting
	 */
	public void initActual(EObjectFlatComboSettings settings);

	/**
	 * Defines a new actual
	 * @param newValue the new actual to set
	 * 
	 */
	public void setActual(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setActualButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the actual edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToActual(ViewerFilter filter);

	/**
	 * Adds the given filter to the actual edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToActual(ViewerFilter filter);


	/**
	 * @return the templateBinding
	 * 
	 */
	public EObject getTemplateBinding();

	/**
	 * Init the templateBinding
	 * @param settings the combo setting
	 */
	public void initTemplateBinding(EObjectFlatComboSettings settings);

	/**
	 * Defines a new templateBinding
	 * @param newValue the new templateBinding to set
	 * 
	 */
	public void setTemplateBinding(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setTemplateBindingButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the templateBinding edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToTemplateBinding(ViewerFilter filter);

	/**
	 * Adds the given filter to the templateBinding edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToTemplateBinding(ViewerFilter filter);





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
