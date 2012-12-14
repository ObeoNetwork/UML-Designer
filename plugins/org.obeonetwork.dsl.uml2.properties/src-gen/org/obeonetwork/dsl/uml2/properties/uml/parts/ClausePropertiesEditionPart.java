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

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ViewerFilter;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public interface ClausePropertiesEditionPart {



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
	 * Init the body
	 * @param settings settings for the body ReferencesTable 
	 */
	public void initBody(ReferencesTableSettings settings);

	/**
	 * Update the body
	 * @param newValue the body to update
	 * @generated
	 */
	public void updateBody();

	/**
	 * Adds the given filter to the body edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToBody(ViewerFilter filter);

	/**
	 * Adds the given filter to the body edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToBody(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the body table
	 * @generated
	 */
	public boolean isContainedInBodyTable(EObject element);




	/**
	 * Init the predecessorClause
	 * @param settings settings for the predecessorClause ReferencesTable 
	 */
	public void initPredecessorClause(ReferencesTableSettings settings);

	/**
	 * Update the predecessorClause
	 * @param newValue the predecessorClause to update
	 * @generated
	 */
	public void updatePredecessorClause();

	/**
	 * Adds the given filter to the predecessorClause edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToPredecessorClause(ViewerFilter filter);

	/**
	 * Adds the given filter to the predecessorClause edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToPredecessorClause(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the predecessorClause table
	 * @generated
	 */
	public boolean isContainedInPredecessorClauseTable(EObject element);




	/**
	 * Init the successorClause
	 * @param settings settings for the successorClause ReferencesTable 
	 */
	public void initSuccessorClause(ReferencesTableSettings settings);

	/**
	 * Update the successorClause
	 * @param newValue the successorClause to update
	 * @generated
	 */
	public void updateSuccessorClause();

	/**
	 * Adds the given filter to the successorClause edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSuccessorClause(ViewerFilter filter);

	/**
	 * Adds the given filter to the successorClause edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSuccessorClause(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the successorClause table
	 * @generated
	 */
	public boolean isContainedInSuccessorClauseTable(EObject element);


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
