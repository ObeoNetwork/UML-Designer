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
public interface SlotPropertiesEditionPart {

	/**
	 * @return the definingFeature
	 * 
	 */
	public EObject getDefiningFeature();

	/**
	 * Init the definingFeature
	 * @param settings the combo setting
	 */
	public void initDefiningFeature(EObjectFlatComboSettings settings);

	/**
	 * Defines a new definingFeature
	 * @param newValue the new definingFeature to set
	 * 
	 */
	public void setDefiningFeature(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setDefiningFeatureButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the definingFeature edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToDefiningFeature(ViewerFilter filter);

	/**
	 * Adds the given filter to the definingFeature edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToDefiningFeature(ViewerFilter filter);


	/**
	 * @return the owningInstance
	 * 
	 */
	public EObject getOwningInstance();

	/**
	 * Init the owningInstance
	 * @param settings the combo setting
	 */
	public void initOwningInstance(EObjectFlatComboSettings settings);

	/**
	 * Defines a new owningInstance
	 * @param newValue the new owningInstance to set
	 * 
	 */
	public void setOwningInstance(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setOwningInstanceButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the owningInstance edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToOwningInstance(ViewerFilter filter);

	/**
	 * Adds the given filter to the owningInstance edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToOwningInstance(ViewerFilter filter);





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
