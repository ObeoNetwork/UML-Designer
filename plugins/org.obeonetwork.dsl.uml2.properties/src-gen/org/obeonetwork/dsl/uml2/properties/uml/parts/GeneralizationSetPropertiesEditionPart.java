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
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ViewerFilter;



// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public interface GeneralizationSetPropertiesEditionPart {

	/**
	 * @return the name
	 * 
	 */
	public String getName();

	/**
	 * Defines a new name
	 * @param newValue the new name to set
	 * 
	 */
	public void setName(String newValue);


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
	 * Init the clientDependency
	 * @param settings settings for the clientDependency ReferencesTable 
	 */
	public void initClientDependency(ReferencesTableSettings settings);

	/**
	 * Update the clientDependency
	 * @param newValue the clientDependency to update
	 * 
	 */
	public void updateClientDependency();

	/**
	 * Adds the given filter to the clientDependency edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToClientDependency(ViewerFilter filter);

	/**
	 * Adds the given filter to the clientDependency edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the clientDependency table
	 * 
	 */
	public boolean isContainedInClientDependencyTable(EObject element);


	/**
	 * @return the owningTemplateParameter
	 * 
	 */
	public EObject getOwningTemplateParameter();

	/**
	 * Init the owningTemplateParameter
	 * @param settings the combo setting
	 */
	public void initOwningTemplateParameter(EObjectFlatComboSettings settings);

	/**
	 * Defines a new owningTemplateParameter
	 * @param newValue the new owningTemplateParameter to set
	 * 
	 */
	public void setOwningTemplateParameter(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the owningTemplateParameter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter);

	/**
	 * Adds the given filter to the owningTemplateParameter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter);


	/**
	 * @return the templateParameter
	 * 
	 */
	public EObject getTemplateParameter();

	/**
	 * Init the templateParameter
	 * @param settings the combo setting
	 */
	public void initTemplateParameter(EObjectFlatComboSettings settings);

	/**
	 * Defines a new templateParameter
	 * @param newValue the new templateParameter to set
	 * 
	 */
	public void setTemplateParameter(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the templateParameter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter);

	/**
	 * Adds the given filter to the templateParameter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter);


	/**
	 * @return the isCovering
	 * 
	 */
	public Boolean getIsCovering();

	/**
	 * Defines a new isCovering
	 * @param newValue the new isCovering to set
	 * 
	 */
	public void setIsCovering(Boolean newValue);


	/**
	 * @return the isDisjoint
	 * 
	 */
	public Boolean getIsDisjoint();

	/**
	 * Defines a new isDisjoint
	 * @param newValue the new isDisjoint to set
	 * 
	 */
	public void setIsDisjoint(Boolean newValue);


	/**
	 * @return the powertype
	 * 
	 */
	public EObject getPowertype();

	/**
	 * Init the powertype
	 * @param settings the combo setting
	 */
	public void initPowertype(EObjectFlatComboSettings settings);

	/**
	 * Defines a new powertype
	 * @param newValue the new powertype to set
	 * 
	 */
	public void setPowertype(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setPowertypeButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the powertype edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToPowertype(ViewerFilter filter);

	/**
	 * Adds the given filter to the powertype edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToPowertype(ViewerFilter filter);




	/**
	 * Init the generalization
	 * @param settings settings for the generalization ReferencesTable 
	 */
	public void initGeneralization(ReferencesTableSettings settings);

	/**
	 * Update the generalization
	 * @param newValue the generalization to update
	 * 
	 */
	public void updateGeneralization();

	/**
	 * Adds the given filter to the generalization edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToGeneralization(ViewerFilter filter);

	/**
	 * Adds the given filter to the generalization edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToGeneralization(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the generalization table
	 * 
	 */
	public boolean isContainedInGeneralizationTable(EObject element);





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
