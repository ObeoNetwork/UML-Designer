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
public interface OperationPropertiesEditionPart {

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
	 * @return the interface
	 * @generated
	 */
	public EObject getInterface_();

	/**
	 * Init the interface
	 * @param settings the combo setting
	 */
	public void initInterface_(EObjectFlatComboSettings settings);

	/**
	 * Defines a new interface
	 * @param newValue the new interface to set
	 * @generated
	 */
	public void setInterface_(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setInterface_ButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the interface edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToInterface_(ViewerFilter filter);

	/**
	 * Adds the given filter to the interface edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToInterface_(ViewerFilter filter);


	/**
	 * @return the class
	 * @generated
	 */
	public EObject getClass_();

	/**
	 * Init the class
	 * @param settings the combo setting
	 */
	public void initClass_(EObjectFlatComboSettings settings);

	/**
	 * Defines a new class
	 * @param newValue the new class to set
	 * @generated
	 */
	public void setClass_(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setClass_ButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the class edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToClass_(ViewerFilter filter);

	/**
	 * Adds the given filter to the class edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToClass_(ViewerFilter filter);


	/**
	 * @return the isQuery
	 * @generated
	 */
	public Boolean getIsQuery();

	/**
	 * Defines a new isQuery
	 * @param newValue the new isQuery to set
	 * @generated
	 */
	public void setIsQuery(Boolean newValue);




	/**
	 * Init the precondition
	 * @param settings settings for the precondition ReferencesTable 
	 */
	public void initPrecondition(ReferencesTableSettings settings);

	/**
	 * Update the precondition
	 * @param newValue the precondition to update
	 * @generated
	 */
	public void updatePrecondition();

	/**
	 * Adds the given filter to the precondition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToPrecondition(ViewerFilter filter);

	/**
	 * Adds the given filter to the precondition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToPrecondition(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the precondition table
	 * @generated
	 */
	public boolean isContainedInPreconditionTable(EObject element);




	/**
	 * Init the postcondition
	 * @param settings settings for the postcondition ReferencesTable 
	 */
	public void initPostcondition(ReferencesTableSettings settings);

	/**
	 * Update the postcondition
	 * @param newValue the postcondition to update
	 * @generated
	 */
	public void updatePostcondition();

	/**
	 * Adds the given filter to the postcondition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToPostcondition(ViewerFilter filter);

	/**
	 * Adds the given filter to the postcondition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToPostcondition(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the postcondition table
	 * @generated
	 */
	public boolean isContainedInPostconditionTable(EObject element);




	/**
	 * Init the redefinedOperation
	 * @param settings settings for the redefinedOperation ReferencesTable 
	 */
	public void initRedefinedOperation(ReferencesTableSettings settings);

	/**
	 * Update the redefinedOperation
	 * @param newValue the redefinedOperation to update
	 * @generated
	 */
	public void updateRedefinedOperation();

	/**
	 * Adds the given filter to the redefinedOperation edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToRedefinedOperation(ViewerFilter filter);

	/**
	 * Adds the given filter to the redefinedOperation edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedOperation(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the redefinedOperation table
	 * @generated
	 */
	public boolean isContainedInRedefinedOperationTable(EObject element);


	/**
	 * @return the datatype
	 * @generated
	 */
	public EObject getDatatype();

	/**
	 * Init the datatype
	 * @param settings the combo setting
	 */
	public void initDatatype(EObjectFlatComboSettings settings);

	/**
	 * Defines a new datatype
	 * @param newValue the new datatype to set
	 * @generated
	 */
	public void setDatatype(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setDatatypeButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the datatype edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToDatatype(ViewerFilter filter);

	/**
	 * Adds the given filter to the datatype edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToDatatype(ViewerFilter filter);


	/**
	 * @return the bodyCondition
	 * @generated
	 */
	public EObject getBodiesCondition();

	/**
	 * Init the bodyCondition
	 * @param settings the combo setting
	 */
	public void initBodyCondition(EObjectFlatComboSettings settings);

	/**
	 * Defines a new bodyCondition
	 * @param newValue the new bodyCondition to set
	 * @generated
	 */
	public void setBodyCondition(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setBodyConditionButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the bodyCondition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToBodyCondition(ViewerFilter filter);

	/**
	 * Adds the given filter to the bodyCondition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToBodyCondition(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
