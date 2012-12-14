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

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ViewerFilter;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public interface MessageOccurrenceSpecificationPropertiesEditionPart {

	/**
	 * @return the name
	 * @generated
	 */
	public String getName();

	/**
	 * Defines a new name
	 * @param newValue the new name to set
	 * @generated
	 */
	public void setName(String newValue);


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
	 * Init the clientDependency
	 * @param settings settings for the clientDependency ReferencesTable 
	 */
	public void initClientDependency(ReferencesTableSettings settings);

	/**
	 * Update the clientDependency
	 * @param newValue the clientDependency to update
	 * @generated
	 */
	public void updateClientDependency();

	/**
	 * Adds the given filter to the clientDependency edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter);

	/**
	 * Adds the given filter to the clientDependency edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the clientDependency table
	 * @generated
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
	 * @generated
	 */
	public void updateCovered();

	/**
	 * Adds the given filter to the covered edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToCovered(ViewerFilter filter);

	/**
	 * Adds the given filter to the covered edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToCovered(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the covered table
	 * @generated
	 */
	public boolean isContainedInCoveredTable(EObject element);


	/**
	 * @return the enclosingInteraction
	 * @generated
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
	 * @generated
	 */
	public void setEnclosingInteraction(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setEnclosingInteractionButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the enclosingInteraction edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToEnclosingInteraction(ViewerFilter filter);

	/**
	 * Adds the given filter to the enclosingInteraction edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToEnclosingInteraction(ViewerFilter filter);


	/**
	 * @return the enclosingOperand
	 * @generated
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
	 * @generated
	 */
	public void setEnclosingOperand(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setEnclosingOperandButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the enclosingOperand edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToEnclosingOperand(ViewerFilter filter);

	/**
	 * Adds the given filter to the enclosingOperand edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToEnclosingOperand(ViewerFilter filter);




	/**
	 * Init the toBefore
	 * @param settings settings for the toBefore ReferencesTable 
	 */
	public void initToBefore(ReferencesTableSettings settings);

	/**
	 * Update the toBefore
	 * @param newValue the toBefore to update
	 * @generated
	 */
	public void updateToBefore();

	/**
	 * Adds the given filter to the toBefore edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToToBefore(ViewerFilter filter);

	/**
	 * Adds the given filter to the toBefore edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToToBefore(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the toBefore table
	 * @generated
	 */
	public boolean isContainedInToBeforeTable(EObject element);


	/**
	 * @return the event
	 * @generated
	 */
	public EObject getEvent();

	/**
	 * Init the event
	 * @param settings the combo setting
	 */
	public void initEvent(EObjectFlatComboSettings settings);

	/**
	 * Defines a new event
	 * @param newValue the new event to set
	 * @generated
	 */
	public void setEvent(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setEventButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the event edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToEvent(ViewerFilter filter);

	/**
	 * Adds the given filter to the event edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToEvent(ViewerFilter filter);




	/**
	 * Init the toAfter
	 * @param settings settings for the toAfter ReferencesTable 
	 */
	public void initToAfter(ReferencesTableSettings settings);

	/**
	 * Update the toAfter
	 * @param newValue the toAfter to update
	 * @generated
	 */
	public void updateToAfter();

	/**
	 * Adds the given filter to the toAfter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToToAfter(ViewerFilter filter);

	/**
	 * Adds the given filter to the toAfter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToToAfter(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the toAfter table
	 * @generated
	 */
	public boolean isContainedInToAfterTable(EObject element);


	/**
	 * @return the message
	 * @generated
	 */
	public EObject getMessage();

	/**
	 * Init the message
	 * @param settings the combo setting
	 */
	public void initMessage(EObjectFlatComboSettings settings);

	/**
	 * Defines a new message
	 * @param newValue the new message to set
	 * @generated
	 */
	public void setMessage(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setMessageButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the message edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToMessage(ViewerFilter filter);

	/**
	 * Adds the given filter to the message edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToMessage(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
