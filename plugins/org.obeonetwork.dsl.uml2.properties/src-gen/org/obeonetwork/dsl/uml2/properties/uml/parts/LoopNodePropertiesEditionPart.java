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
public interface LoopNodePropertiesEditionPart {

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
	 * @return the isLeaf
	 * @generated
	 */
	public Boolean getIsLeaf();

	/**
	 * Defines a new isLeaf
	 * @param newValue the new isLeaf to set
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue);


	/**
	 * @return the inStructuredNode
	 * @generated
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
	 * @generated
	 */
	public void setInStructuredNode(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setInStructuredNodeButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the inStructuredNode edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToInStructuredNode(ViewerFilter filter);

	/**
	 * Adds the given filter to the inStructuredNode edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToInStructuredNode(ViewerFilter filter);


	/**
	 * @return the activity
	 * @generated
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
	 * @generated
	 */
	public void setActivity(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setActivityButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the activity edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToActivity(ViewerFilter filter);

	/**
	 * Adds the given filter to the activity edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
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
	 * @generated
	 */
	public void updateOutgoing();

	/**
	 * Adds the given filter to the outgoing edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToOutgoing(ViewerFilter filter);

	/**
	 * Adds the given filter to the outgoing edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToOutgoing(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the outgoing table
	 * @generated
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
	 * @generated
	 */
	public void updateIncoming();

	/**
	 * Adds the given filter to the incoming edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToIncoming(ViewerFilter filter);

	/**
	 * Adds the given filter to the incoming edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToIncoming(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the incoming table
	 * @generated
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
	 * @generated
	 */
	public void updateInPartition();

	/**
	 * Adds the given filter to the inPartition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToInPartition(ViewerFilter filter);

	/**
	 * Adds the given filter to the inPartition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToInPartition(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the inPartition table
	 * @generated
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
	 * @generated
	 */
	public void updateInInterruptibleRegion();

	/**
	 * Adds the given filter to the inInterruptibleRegion edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToInInterruptibleRegion(ViewerFilter filter);

	/**
	 * Adds the given filter to the inInterruptibleRegion edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToInInterruptibleRegion(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the inInterruptibleRegion table
	 * @generated
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
	 * @generated
	 */
	public void updateRedefinedNode();

	/**
	 * Adds the given filter to the redefinedNode edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToRedefinedNode(ViewerFilter filter);

	/**
	 * Adds the given filter to the redefinedNode edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedNode(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the redefinedNode table
	 * @generated
	 */
	public boolean isContainedInRedefinedNodeTable(EObject element);


	/**
	 * @return the inActivity
	 * @generated
	 */
	public EObject getInActivity();

	/**
	 * Init the inActivity
	 * @param settings the combo setting
	 */
	public void initInActivity(EObjectFlatComboSettings settings);

	/**
	 * Defines a new inActivity
	 * @param newValue the new inActivity to set
	 * @generated
	 */
	public void setInActivity(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setInActivityButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the inActivity edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToInActivity(ViewerFilter filter);

	/**
	 * Adds the given filter to the inActivity edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToInActivity(ViewerFilter filter);


	/**
	 * @return the mustIsolate
	 * @generated
	 */
	public Boolean getMustIsolate();

	/**
	 * Defines a new mustIsolate
	 * @param newValue the new mustIsolate to set
	 * @generated
	 */
	public void setMustIsolate(Boolean newValue);


	/**
	 * @return the isTestedFirst
	 * @generated
	 */
	public Boolean getIsTestedFirst();

	/**
	 * Defines a new isTestedFirst
	 * @param newValue the new isTestedFirst to set
	 * @generated
	 */
	public void setIsTestedFirst(Boolean newValue);




	/**
	 * Init the bodyPart
	 * @param settings settings for the bodyPart ReferencesTable 
	 */
	public void initBodyPart(ReferencesTableSettings settings);

	/**
	 * Update the bodyPart
	 * @param newValue the bodyPart to update
	 * @generated
	 */
	public void updateBodyPart();

	/**
	 * Adds the given filter to the bodyPart edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToBodyPart(ViewerFilter filter);

	/**
	 * Adds the given filter to the bodyPart edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToBodyPart(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the bodyPart table
	 * @generated
	 */
	public boolean isContainedInBodyPartTable(EObject element);




	/**
	 * Init the setupPart
	 * @param settings settings for the setupPart ReferencesTable 
	 */
	public void initSetupPart(ReferencesTableSettings settings);

	/**
	 * Update the setupPart
	 * @param newValue the setupPart to update
	 * @generated
	 */
	public void updateSetupPart();

	/**
	 * Adds the given filter to the setupPart edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSetupPart(ViewerFilter filter);

	/**
	 * Adds the given filter to the setupPart edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSetupPart(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the setupPart table
	 * @generated
	 */
	public boolean isContainedInSetupPartTable(EObject element);


	/**
	 * @return the decider
	 * @generated
	 */
	public EObject getDecider();

	/**
	 * Init the decider
	 * @param settings the combo setting
	 */
	public void initDecider(EObjectFlatComboSettings settings);

	/**
	 * Defines a new decider
	 * @param newValue the new decider to set
	 * @generated
	 */
	public void setDecider(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setDeciderButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the decider edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToDecider(ViewerFilter filter);

	/**
	 * Adds the given filter to the decider edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToDecider(ViewerFilter filter);




	/**
	 * Init the test
	 * @param settings settings for the test ReferencesTable 
	 */
	public void initTest(ReferencesTableSettings settings);

	/**
	 * Update the test
	 * @param newValue the test to update
	 * @generated
	 */
	public void updateTest();

	/**
	 * Adds the given filter to the test edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToTest(ViewerFilter filter);

	/**
	 * Adds the given filter to the test edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToTest(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the test table
	 * @generated
	 */
	public boolean isContainedInTestTable(EObject element);




	/**
	 * Init the loopVariable
	 * @param settings settings for the loopVariable ReferencesTable 
	 */
	public void initLoopVariable(ReferencesTableSettings settings);

	/**
	 * Update the loopVariable
	 * @param newValue the loopVariable to update
	 * @generated
	 */
	public void updateLoopVariable();

	/**
	 * Adds the given filter to the loopVariable edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToLoopVariable(ViewerFilter filter);

	/**
	 * Adds the given filter to the loopVariable edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToLoopVariable(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the loopVariable table
	 * @generated
	 */
	public boolean isContainedInLoopVariableTable(EObject element);




	/**
	 * Init the bodyOutput
	 * @param settings settings for the bodyOutput ReferencesTable 
	 */
	public void initBodyOutput(ReferencesTableSettings settings);

	/**
	 * Update the bodyOutput
	 * @param newValue the bodyOutput to update
	 * @generated
	 */
	public void updateBodyOutput();

	/**
	 * Adds the given filter to the bodyOutput edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToBodyOutput(ViewerFilter filter);

	/**
	 * Adds the given filter to the bodyOutput edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToBodyOutput(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the bodyOutput table
	 * @generated
	 */
	public boolean isContainedInBodyOutputTable(EObject element);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
