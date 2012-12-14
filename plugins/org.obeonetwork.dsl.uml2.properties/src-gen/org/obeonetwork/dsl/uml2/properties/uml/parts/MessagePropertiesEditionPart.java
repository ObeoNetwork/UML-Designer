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
public interface MessagePropertiesEditionPart {

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
	 * @return the messageSort
	 * @generated
	 */
	public Enumerator getMessageSort();

	/**
	 * Init the messageSort
	 * @param input the viewer input
	 * @param current the current value
	 */
	public void initMessageSort(Object input, Enumerator current);

	/**
	 * Defines a new messageSort
	 * @param newValue the new messageSort to set
	 * @generated
	 */
	public void setMessageSort(Enumerator newValue);


	/**
	 * @return the receiveEvent
	 * @generated
	 */
	public EObject getReceiveEvent();

	/**
	 * Init the receiveEvent
	 * @param settings the combo setting
	 */
	public void initReceiveEvent(EObjectFlatComboSettings settings);

	/**
	 * Defines a new receiveEvent
	 * @param newValue the new receiveEvent to set
	 * @generated
	 */
	public void setReceiveEvent(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setReceiveEventButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the receiveEvent edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToReceiveEvent(ViewerFilter filter);

	/**
	 * Adds the given filter to the receiveEvent edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToReceiveEvent(ViewerFilter filter);


	/**
	 * @return the sendEvent
	 * @generated
	 */
	public EObject getSendEvent();

	/**
	 * Init the sendEvent
	 * @param settings the combo setting
	 */
	public void initSendEvent(EObjectFlatComboSettings settings);

	/**
	 * Defines a new sendEvent
	 * @param newValue the new sendEvent to set
	 * @generated
	 */
	public void setSendEvent(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setSendEventButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the sendEvent edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSendEvent(ViewerFilter filter);

	/**
	 * Adds the given filter to the sendEvent edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSendEvent(ViewerFilter filter);


	/**
	 * @return the connector
	 * @generated
	 */
	public EObject getConnector();

	/**
	 * Init the connector
	 * @param settings the combo setting
	 */
	public void initConnector(EObjectFlatComboSettings settings);

	/**
	 * Defines a new connector
	 * @param newValue the new connector to set
	 * @generated
	 */
	public void setConnector(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setConnectorButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the connector edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToConnector(ViewerFilter filter);

	/**
	 * Adds the given filter to the connector edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToConnector(ViewerFilter filter);


	/**
	 * @return the interaction
	 * @generated
	 */
	public EObject getInteraction();

	/**
	 * Init the interaction
	 * @param settings the combo setting
	 */
	public void initInteraction(EObjectFlatComboSettings settings);

	/**
	 * Defines a new interaction
	 * @param newValue the new interaction to set
	 * @generated
	 */
	public void setInteraction(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setInteractionButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the interaction edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToInteraction(ViewerFilter filter);

	/**
	 * Adds the given filter to the interaction edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToInteraction(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
