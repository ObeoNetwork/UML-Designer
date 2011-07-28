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
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.jface.viewers.ViewerFilter;



// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public interface PackageImportPropertiesEditionPart {

	/**
	 * @return the visibility
	 * 
	 */
	public Enumerator getVisibility();

	/**
	 * Init the visibility
	 * @param eenum the enum to manage
	 * @param current the current value
	 */
	public void initVisibility(EEnum eenum, Enumerator current);

	/**
	 * Defines a new visibility
	 * @param newValue the new visibility to set
	 * 
	 */
	public void setVisibility(Enumerator newValue);


	/**
	 * @return the importedPackage
	 * 
	 */
	public EObject getImportedPackage();

	/**
	 * Init the importedPackage
	 * @param settings the combo setting
	 */
	public void initImportedPackage(EObjectFlatComboSettings settings);

	/**
	 * Defines a new importedPackage
	 * @param newValue the new importedPackage to set
	 * 
	 */
	public void setImportedPackage(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setImportedPackageButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the importedPackage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToImportedPackage(ViewerFilter filter);

	/**
	 * Adds the given filter to the importedPackage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToImportedPackage(ViewerFilter filter);


	/**
	 * @return the importingNamespace
	 * 
	 */
	public EObject getImportingNamespace();

	/**
	 * Init the importingNamespace
	 * @param settings the combo setting
	 */
	public void initImportingNamespace(EObjectFlatComboSettings settings);

	/**
	 * Defines a new importingNamespace
	 * @param newValue the new importingNamespace to set
	 * 
	 */
	public void setImportingNamespace(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setImportingNamespaceButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the importingNamespace edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToImportingNamespace(ViewerFilter filter);

	/**
	 * Adds the given filter to the importingNamespace edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToImportingNamespace(ViewerFilter filter);





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
