/*******************************************************************************
 * Copyright (c) 2009, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.parts;

import org.eclipse.emf.common.util.Enumerator;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.jface.viewers.ViewerFilter;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public interface PackageImportPropertiesEditionPart {

	/**
	 * @return the visibility
	 * @generated
	 */
	public Enumerator getVisibility();

	/**
	 * Init the visibility
	 * @param input the viewer input
	 * @param current the current value
	 */
	public void initVisibility(Object input, Enumerator current);

	/**
	 * Defines a new visibility
	 * @param newValue the new visibility to set
	 * @generated
	 */
	public void setVisibility(Enumerator newValue);


	/**
	 * @return the importedPackage
	 * @generated
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
	 * @generated
	 */
	public void setImportedPackage(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setImportedPackageButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the importedPackage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToImportedPackage(ViewerFilter filter);

	/**
	 * Adds the given filter to the importedPackage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToImportedPackage(ViewerFilter filter);


	/**
	 * @return the importingNamespace
	 * @generated
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
	 * @generated
	 */
	public void setImportingNamespace(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setImportingNamespaceButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the importingNamespace edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToImportingNamespace(ViewerFilter filter);

	/**
	 * Adds the given filter to the importingNamespace edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToImportingNamespace(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
