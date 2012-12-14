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

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.jface.viewers.ViewerFilter;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public interface ConnectorEndPropertiesEditionPart {

	/**
	 * @return the isOrdered
	 * @generated
	 */
	public Boolean getIsOrdered();

	/**
	 * Defines a new isOrdered
	 * @param newValue the new isOrdered to set
	 * @generated
	 */
	public void setIsOrdered(Boolean newValue);


	/**
	 * @return the isUnique
	 * @generated
	 */
	public Boolean getIsUnique();

	/**
	 * Defines a new isUnique
	 * @param newValue the new isUnique to set
	 * @generated
	 */
	public void setIsUnique(Boolean newValue);


	/**
	 * @return the role
	 * @generated
	 */
	public EObject getRole();

	/**
	 * Init the role
	 * @param settings the combo setting
	 */
	public void initRole(EObjectFlatComboSettings settings);

	/**
	 * Defines a new role
	 * @param newValue the new role to set
	 * @generated
	 */
	public void setRole(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setRoleButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the role edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToRole(ViewerFilter filter);

	/**
	 * Adds the given filter to the role edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToRole(ViewerFilter filter);


	/**
	 * @return the partWithPort
	 * @generated
	 */
	public EObject getPartWithPort();

	/**
	 * Init the partWithPort
	 * @param settings the combo setting
	 */
	public void initPartWithPort(EObjectFlatComboSettings settings);

	/**
	 * Defines a new partWithPort
	 * @param newValue the new partWithPort to set
	 * @generated
	 */
	public void setPartWithPort(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setPartWithPortButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the partWithPort edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToPartWithPort(ViewerFilter filter);

	/**
	 * Adds the given filter to the partWithPort edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToPartWithPort(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
