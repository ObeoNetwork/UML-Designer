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
public interface ReceptionPropertiesEditionPart {

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
	 * @return the isStatic
	 * @generated
	 */
	public Boolean getIsStatic();

	/**
	 * Defines a new isStatic
	 * @param newValue the new isStatic to set
	 * @generated
	 */
	public void setIsStatic(Boolean newValue);


	/**
	 * @return the isAbstract
	 * @generated
	 */
	public Boolean getIsAbstract();

	/**
	 * Defines a new isAbstract
	 * @param newValue the new isAbstract to set
	 * @generated
	 */
	public void setIsAbstract(Boolean newValue);




	/**
	 * Init the method
	 * @param settings settings for the method ReferencesTable 
	 */
	public void initMethod(ReferencesTableSettings settings);

	/**
	 * Update the method
	 * @param newValue the method to update
	 * @generated
	 */
	public void updateMethod();

	/**
	 * Adds the given filter to the method edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToMethod(ViewerFilter filter);

	/**
	 * Adds the given filter to the method edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToMethod(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the method table
	 * @generated
	 */
	public boolean isContainedInMethodTable(EObject element);


	/**
	 * @return the concurrency
	 * @generated
	 */
	public Enumerator getConcurrency();

	/**
	 * Init the concurrency
	 * @param input the viewer input
	 * @param current the current value
	 */
	public void initConcurrency(Object input, Enumerator current);

	/**
	 * Defines a new concurrency
	 * @param newValue the new concurrency to set
	 * @generated
	 */
	public void setConcurrency(Enumerator newValue);




	/**
	 * Init the raisedException
	 * @param settings settings for the raisedException ReferencesTable 
	 */
	public void initRaisedException(ReferencesTableSettings settings);

	/**
	 * Update the raisedException
	 * @param newValue the raisedException to update
	 * @generated
	 */
	public void updateRaisedException();

	/**
	 * Adds the given filter to the raisedException edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToRaisedException(ViewerFilter filter);

	/**
	 * Adds the given filter to the raisedException edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToRaisedException(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the raisedException table
	 * @generated
	 */
	public boolean isContainedInRaisedExceptionTable(EObject element);


	/**
	 * @return the signal
	 * @generated
	 */
	public EObject getSignal();

	/**
	 * Init the signal
	 * @param settings the combo setting
	 */
	public void initSignal(EObjectFlatComboSettings settings);

	/**
	 * Defines a new signal
	 * @param newValue the new signal to set
	 * @generated
	 */
	public void setSignal(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setSignalButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the signal edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSignal(ViewerFilter filter);

	/**
	 * Adds the given filter to the signal edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSignal(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
