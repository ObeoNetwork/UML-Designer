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
public interface LinkEndDestructionDataPropertiesEditionPart {

	/**
	 * @return the value
	 * 
	 */
	public EObject getValue();

	/**
	 * Init the value
	 * @param settings the combo setting
	 */
	public void initValue(EObjectFlatComboSettings settings);

	/**
	 * Defines a new value
	 * @param newValue the new value to set
	 * 
	 */
	public void setValue(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setValueButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the value edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToValue(ViewerFilter filter);

	/**
	 * Adds the given filter to the value edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToValue(ViewerFilter filter);


	/**
	 * @return the end
	 * 
	 */
	public EObject getEnd();

	/**
	 * Init the end
	 * @param settings the combo setting
	 */
	public void initEnd(EObjectFlatComboSettings settings);

	/**
	 * Defines a new end
	 * @param newValue the new end to set
	 * 
	 */
	public void setEnd(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setEndButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the end edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToEnd(ViewerFilter filter);

	/**
	 * Adds the given filter to the end edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToEnd(ViewerFilter filter);


	/**
	 * @return the isDestroyDuplicates
	 * 
	 */
	public Boolean getIsDestroyDuplicates();

	/**
	 * Defines a new isDestroyDuplicates
	 * @param newValue the new isDestroyDuplicates to set
	 * 
	 */
	public void setIsDestroyDuplicates(Boolean newValue);


	/**
	 * @return the destroyAt
	 * 
	 */
	public EObject getDestroyAt();

	/**
	 * Init the destroyAt
	 * @param settings the combo setting
	 */
	public void initDestroyAt(EObjectFlatComboSettings settings);

	/**
	 * Defines a new destroyAt
	 * @param newValue the new destroyAt to set
	 * 
	 */
	public void setDestroyAt(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setDestroyAtButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the destroyAt edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToDestroyAt(ViewerFilter filter);

	/**
	 * Adds the given filter to the destroyAt edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToDestroyAt(ViewerFilter filter);





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
