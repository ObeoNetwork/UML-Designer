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
public interface InterfaceRealizationPropertiesEditionPart {

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
	 * @return the owningTemplateParameter
	 * @generated
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
	 * @generated
	 */
	public void setOwningTemplateParameter(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the owningTemplateParameter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter);

	/**
	 * Adds the given filter to the owningTemplateParameter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter);


	/**
	 * @return the templateParameter
	 * @generated
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
	 * @generated
	 */
	public void setTemplateParameter(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the templateParameter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter);

	/**
	 * Adds the given filter to the templateParameter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter);




	/**
	 * Init the supplier
	 * @param settings settings for the supplier ReferencesTable 
	 */
	public void initSupplier(ReferencesTableSettings settings);

	/**
	 * Update the supplier
	 * @param newValue the supplier to update
	 * @generated
	 */
	public void updateSupplier();

	/**
	 * Adds the given filter to the supplier edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSupplier(ViewerFilter filter);

	/**
	 * Adds the given filter to the supplier edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSupplier(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the supplier table
	 * @generated
	 */
	public boolean isContainedInSupplierTable(EObject element);




	/**
	 * Init the client
	 * @param settings settings for the client ReferencesTable 
	 */
	public void initClient(ReferencesTableSettings settings);

	/**
	 * Update the client
	 * @param newValue the client to update
	 * @generated
	 */
	public void updateClient();

	/**
	 * Adds the given filter to the client edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToClient(ViewerFilter filter);

	/**
	 * Adds the given filter to the client edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToClient(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the client table
	 * @generated
	 */
	public boolean isContainedInClientTable(EObject element);


	/**
	 * @return the contract
	 * @generated
	 */
	public EObject getContract();

	/**
	 * Init the contract
	 * @param settings the combo setting
	 */
	public void initContract(EObjectFlatComboSettings settings);

	/**
	 * Defines a new contract
	 * @param newValue the new contract to set
	 * @generated
	 */
	public void setContract(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setContractButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the contract edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToContract(ViewerFilter filter);

	/**
	 * Adds the given filter to the contract edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToContract(ViewerFilter filter);


	/**
	 * @return the implementingClassifier
	 * @generated
	 */
	public EObject getImplementingClassifier();

	/**
	 * Init the implementingClassifier
	 * @param settings the combo setting
	 */
	public void initImplementingClassifier(EObjectFlatComboSettings settings);

	/**
	 * Defines a new implementingClassifier
	 * @param newValue the new implementingClassifier to set
	 * @generated
	 */
	public void setImplementingClassifier(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setImplementingClassifierButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the implementingClassifier edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToImplementingClassifier(ViewerFilter filter);

	/**
	 * Adds the given filter to the implementingClassifier edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToImplementingClassifier(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
