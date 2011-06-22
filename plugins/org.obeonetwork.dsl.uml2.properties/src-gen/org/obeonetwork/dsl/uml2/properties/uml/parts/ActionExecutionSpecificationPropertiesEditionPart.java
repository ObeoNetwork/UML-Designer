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
public interface ActionExecutionSpecificationPropertiesEditionPart {

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
	 * Init the covered
	 * @param settings settings for the covered ReferencesTable 
	 */
	public void initCovered(ReferencesTableSettings settings);

	/**
	 * Update the covered
	 * @param newValue the covered to update
	 * 
	 */
	public void updateCovered();

	/**
	 * Adds the given filter to the covered edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToCovered(ViewerFilter filter);

	/**
	 * Adds the given filter to the covered edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToCovered(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the covered table
	 * 
	 */
	public boolean isContainedInCoveredTable(EObject element);


	/**
	 * @return the enclosingInteraction
	 * 
	 */
	public EObject getEnclosingInteraction();

	/**
	 * Init the enclosingInteraction
	 * @param settings the combo setting
	 */
	public void initEnclosingInteraction(EObjectFlatComboSettings settings);

	/**
	 * Defines a new enclosingInteraction
	 * @param newValue the new enclosingInteraction to set
	 * 
	 */
	public void setEnclosingInteraction(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setEnclosingInteractionButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the enclosingInteraction edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToEnclosingInteraction(ViewerFilter filter);

	/**
	 * Adds the given filter to the enclosingInteraction edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToEnclosingInteraction(ViewerFilter filter);


	/**
	 * @return the enclosingOperand
	 * 
	 */
	public EObject getEnclosingOperand();

	/**
	 * Init the enclosingOperand
	 * @param settings the combo setting
	 */
	public void initEnclosingOperand(EObjectFlatComboSettings settings);

	/**
	 * Defines a new enclosingOperand
	 * @param newValue the new enclosingOperand to set
	 * 
	 */
	public void setEnclosingOperand(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setEnclosingOperandButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the enclosingOperand edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToEnclosingOperand(ViewerFilter filter);

	/**
	 * Adds the given filter to the enclosingOperand edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToEnclosingOperand(ViewerFilter filter);


	/**
	 * @return the start
	 * 
	 */
	public EObject getStart();

	/**
	 * Init the start
	 * @param settings the combo setting
	 */
	public void initStart(EObjectFlatComboSettings settings);

	/**
	 * Defines a new start
	 * @param newValue the new start to set
	 * 
	 */
	public void setStart(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setStartButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the start edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToStart(ViewerFilter filter);

	/**
	 * Adds the given filter to the start edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToStart(ViewerFilter filter);


	/**
	 * @return the finish
	 * 
	 */
	public EObject getFinish();

	/**
	 * Init the finish
	 * @param settings the combo setting
	 */
	public void initFinish(EObjectFlatComboSettings settings);

	/**
	 * Defines a new finish
	 * @param newValue the new finish to set
	 * 
	 */
	public void setFinish(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setFinishButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the finish edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToFinish(ViewerFilter filter);

	/**
	 * Adds the given filter to the finish edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToFinish(ViewerFilter filter);


	/**
	 * @return the action
	 * 
	 */
	public EObject getAction();

	/**
	 * Init the action
	 * @param settings the combo setting
	 */
	public void initAction(EObjectFlatComboSettings settings);

	/**
	 * Defines a new action
	 * @param newValue the new action to set
	 * 
	 */
	public void setAction(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setActionButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the action edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToAction(ViewerFilter filter);

	/**
	 * Adds the given filter to the action edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToAction(ViewerFilter filter);





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
