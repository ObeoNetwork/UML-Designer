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
import org.eclipse.jface.viewers.ViewerFilter;



// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public interface PackageMergePropertiesEditionPart {

	/**
	 * @return the mergedPackage
	 * 
	 */
	public EObject getMergedPackage();

	/**
	 * Init the mergedPackage
	 * @param settings the combo setting
	 */
	public void initMergedPackage(EObjectFlatComboSettings settings);

	/**
	 * Defines a new mergedPackage
	 * @param newValue the new mergedPackage to set
	 * 
	 */
	public void setMergedPackage(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setMergedPackageButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the mergedPackage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToMergedPackage(ViewerFilter filter);

	/**
	 * Adds the given filter to the mergedPackage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToMergedPackage(ViewerFilter filter);


	/**
	 * @return the receivingPackage
	 * 
	 */
	public EObject getReceivingPackage();

	/**
	 * Init the receivingPackage
	 * @param settings the combo setting
	 */
	public void initReceivingPackage(EObjectFlatComboSettings settings);

	/**
	 * Defines a new receivingPackage
	 * @param newValue the new receivingPackage to set
	 * 
	 */
	public void setReceivingPackage(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setReceivingPackageButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the receivingPackage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToReceivingPackage(ViewerFilter filter);

	/**
	 * Adds the given filter to the receivingPackage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToReceivingPackage(ViewerFilter filter);





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
