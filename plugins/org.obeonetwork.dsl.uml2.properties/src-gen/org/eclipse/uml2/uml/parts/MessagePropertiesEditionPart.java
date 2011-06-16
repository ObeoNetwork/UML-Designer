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
public interface MessagePropertiesEditionPart {

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
	 * @return the messageSort
	 * 
	 */
	public Enumerator getMessageSort();

	/**
	 * Init the messageSort
	 * @param eenum the enum to manage
	 * @param current the current value
	 */
	public void initMessageSort(EEnum eenum, Enumerator current);

	/**
	 * Defines a new messageSort
	 * @param newValue the new messageSort to set
	 * 
	 */
	public void setMessageSort(Enumerator newValue);


	/**
	 * @return the receiveEvent
	 * 
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
	 * 
	 */
	public void setReceiveEvent(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setReceiveEventButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the receiveEvent edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToReceiveEvent(ViewerFilter filter);

	/**
	 * Adds the given filter to the receiveEvent edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToReceiveEvent(ViewerFilter filter);


	/**
	 * @return the sendEvent
	 * 
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
	 * 
	 */
	public void setSendEvent(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setSendEventButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the sendEvent edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToSendEvent(ViewerFilter filter);

	/**
	 * Adds the given filter to the sendEvent edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToSendEvent(ViewerFilter filter);


	/**
	 * @return the connector
	 * 
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
	 * 
	 */
	public void setConnector(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setConnectorButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the connector edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToConnector(ViewerFilter filter);

	/**
	 * Adds the given filter to the connector edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToConnector(ViewerFilter filter);


	/**
	 * @return the interaction
	 * 
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
	 * 
	 */
	public void setInteraction(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setInteractionButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the interaction edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToInteraction(ViewerFilter filter);

	/**
	 * Adds the given filter to the interaction edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToInteraction(ViewerFilter filter);





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
