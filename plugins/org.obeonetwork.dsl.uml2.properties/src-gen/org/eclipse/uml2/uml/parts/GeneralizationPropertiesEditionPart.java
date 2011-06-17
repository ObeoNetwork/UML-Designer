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
public interface GeneralizationPropertiesEditionPart {

	/**
	 * @return the isSubstitutable
	 * 
	 */
	public Boolean getIsSubstitutable();

	/**
	 * Defines a new isSubstitutable
	 * @param newValue the new isSubstitutable to set
	 * 
	 */
	public void setIsSubstitutable(Boolean newValue);


	/**
	 * @return the general
	 * 
	 */
	public EObject getGeneral();

	/**
	 * Init the general
	 * @param settings the combo setting
	 */
	public void initGeneral(EObjectFlatComboSettings settings);

	/**
	 * Defines a new general
	 * @param newValue the new general to set
	 * 
	 */
	public void setGeneral(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setGeneralButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the general edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToGeneral(ViewerFilter filter);

	/**
	 * Adds the given filter to the general edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToGeneral(ViewerFilter filter);




	/**
	 * Init the generalizationSet
	 * @param settings settings for the generalizationSet ReferencesTable 
	 */
	public void initGeneralizationSet(ReferencesTableSettings settings);

	/**
	 * Update the generalizationSet
	 * @param newValue the generalizationSet to update
	 * 
	 */
	public void updateGeneralizationSet();

	/**
	 * Adds the given filter to the generalizationSet edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToGeneralizationSet(ViewerFilter filter);

	/**
	 * Adds the given filter to the generalizationSet edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToGeneralizationSet(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the generalizationSet table
	 * 
	 */
	public boolean isContainedInGeneralizationSetTable(EObject element);


	/**
	 * @return the specific
	 * 
	 */
	public EObject getSpecific();

	/**
	 * Init the specific
	 * @param settings the combo setting
	 */
	public void initSpecific(EObjectFlatComboSettings settings);

	/**
	 * Defines a new specific
	 * @param newValue the new specific to set
	 * 
	 */
	public void setSpecific(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setSpecificButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the specific edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToSpecific(ViewerFilter filter);

	/**
	 * Adds the given filter to the specific edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToSpecific(ViewerFilter filter);





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
