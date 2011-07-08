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
public interface ConnectorEndPropertiesEditionPart {

	/**
	 * @return the isOrdered
	 * 
	 */
	public Boolean getIsOrdered();

	/**
	 * Defines a new isOrdered
	 * @param newValue the new isOrdered to set
	 * 
	 */
	public void setIsOrdered(Boolean newValue);


	/**
	 * @return the isUnique
	 * 
	 */
	public Boolean getIsUnique();

	/**
	 * Defines a new isUnique
	 * @param newValue the new isUnique to set
	 * 
	 */
	public void setIsUnique(Boolean newValue);


	/**
	 * @return the role
	 * 
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
	 * 
	 */
	public void setRole(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setRoleButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the role edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToRole(ViewerFilter filter);

	/**
	 * Adds the given filter to the role edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToRole(ViewerFilter filter);


	/**
	 * @return the partWithPort
	 * 
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
	 * 
	 */
	public void setPartWithPort(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setPartWithPortButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the partWithPort edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToPartWithPort(ViewerFilter filter);

	/**
	 * Adds the given filter to the partWithPort edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToPartWithPort(ViewerFilter filter);





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
