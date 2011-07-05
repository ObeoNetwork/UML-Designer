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
public interface ReadIsClassifiedObjectActionPropertiesEditionPart {

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
	 * @return the isLeaf
	 * 
	 */
	public Boolean getIsLeaf();

	/**
	 * Defines a new isLeaf
	 * @param newValue the new isLeaf to set
	 * 
	 */
	public void setIsLeaf(Boolean newValue);


	/**
	 * @return the inStructuredNode
	 * 
	 */
	public EObject getInStructuredNode();

	/**
	 * Init the inStructuredNode
	 * @param settings the combo setting
	 */
	public void initInStructuredNode(EObjectFlatComboSettings settings);

	/**
	 * Defines a new inStructuredNode
	 * @param newValue the new inStructuredNode to set
	 * 
	 */
	public void setInStructuredNode(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setInStructuredNodeButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the inStructuredNode edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToInStructuredNode(ViewerFilter filter);

	/**
	 * Adds the given filter to the inStructuredNode edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToInStructuredNode(ViewerFilter filter);


	/**
	 * @return the activity
	 * 
	 */
	public EObject getActivity();

	/**
	 * Init the activity
	 * @param settings the combo setting
	 */
	public void initActivity(EObjectFlatComboSettings settings);

	/**
	 * Defines a new activity
	 * @param newValue the new activity to set
	 * 
	 */
	public void setActivity(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setActivityButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the activity edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToActivity(ViewerFilter filter);

	/**
	 * Adds the given filter to the activity edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToActivity(ViewerFilter filter);




	/**
	 * Init the outgoing
	 * @param settings settings for the outgoing ReferencesTable 
	 */
	public void initOutgoing(ReferencesTableSettings settings);

	/**
	 * Update the outgoing
	 * @param newValue the outgoing to update
	 * 
	 */
	public void updateOutgoing();

	/**
	 * Adds the given filter to the outgoing edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToOutgoing(ViewerFilter filter);

	/**
	 * Adds the given filter to the outgoing edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToOutgoing(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the outgoing table
	 * 
	 */
	public boolean isContainedInOutgoingTable(EObject element);




	/**
	 * Init the incoming
	 * @param settings settings for the incoming ReferencesTable 
	 */
	public void initIncoming(ReferencesTableSettings settings);

	/**
	 * Update the incoming
	 * @param newValue the incoming to update
	 * 
	 */
	public void updateIncoming();

	/**
	 * Adds the given filter to the incoming edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToIncoming(ViewerFilter filter);

	/**
	 * Adds the given filter to the incoming edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToIncoming(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the incoming table
	 * 
	 */
	public boolean isContainedInIncomingTable(EObject element);




	/**
	 * Init the inPartition
	 * @param settings settings for the inPartition ReferencesTable 
	 */
	public void initInPartition(ReferencesTableSettings settings);

	/**
	 * Update the inPartition
	 * @param newValue the inPartition to update
	 * 
	 */
	public void updateInPartition();

	/**
	 * Adds the given filter to the inPartition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToInPartition(ViewerFilter filter);

	/**
	 * Adds the given filter to the inPartition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToInPartition(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the inPartition table
	 * 
	 */
	public boolean isContainedInInPartitionTable(EObject element);




	/**
	 * Init the inInterruptibleRegion
	 * @param settings settings for the inInterruptibleRegion ReferencesTable 
	 */
	public void initInInterruptibleRegion(ReferencesTableSettings settings);

	/**
	 * Update the inInterruptibleRegion
	 * @param newValue the inInterruptibleRegion to update
	 * 
	 */
	public void updateInInterruptibleRegion();

	/**
	 * Adds the given filter to the inInterruptibleRegion edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToInInterruptibleRegion(ViewerFilter filter);

	/**
	 * Adds the given filter to the inInterruptibleRegion edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToInInterruptibleRegion(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the inInterruptibleRegion table
	 * 
	 */
	public boolean isContainedInInInterruptibleRegionTable(EObject element);




	/**
	 * Init the redefinedNode
	 * @param settings settings for the redefinedNode ReferencesTable 
	 */
	public void initRedefinedNode(ReferencesTableSettings settings);

	/**
	 * Update the redefinedNode
	 * @param newValue the redefinedNode to update
	 * 
	 */
	public void updateRedefinedNode();

	/**
	 * Adds the given filter to the redefinedNode edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToRedefinedNode(ViewerFilter filter);

	/**
	 * Adds the given filter to the redefinedNode edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToRedefinedNode(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the redefinedNode table
	 * 
	 */
	public boolean isContainedInRedefinedNodeTable(EObject element);


	/**
	 * @return the isDirect
	 * 
	 */
	public Boolean getIsDirect();

	/**
	 * Defines a new isDirect
	 * @param newValue the new isDirect to set
	 * 
	 */
	public void setIsDirect(Boolean newValue);


	/**
	 * @return the classifier
	 * 
	 */
	public EObject getClassifier();

	/**
	 * Init the classifier
	 * @param settings the combo setting
	 */
	public void initClassifier(EObjectFlatComboSettings settings);

	/**
	 * Defines a new classifier
	 * @param newValue the new classifier to set
	 * 
	 */
	public void setClassifier(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setClassifierButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the classifier edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToClassifier(ViewerFilter filter);

	/**
	 * Adds the given filter to the classifier edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToClassifier(ViewerFilter filter);





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
