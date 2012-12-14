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
public interface ProtocolStateMachinePropertiesEditionPart {

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
	 * Init the powertypeExtent
	 * @param settings settings for the powertypeExtent ReferencesTable 
	 */
	public void initPowertypeExtent(ReferencesTableSettings settings);

	/**
	 * Update the powertypeExtent
	 * @param newValue the powertypeExtent to update
	 * @generated
	 */
	public void updatePowertypeExtent();

	/**
	 * Adds the given filter to the powertypeExtent edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToPowertypeExtent(ViewerFilter filter);

	/**
	 * Adds the given filter to the powertypeExtent edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToPowertypeExtent(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the powertypeExtent table
	 * @generated
	 */
	public boolean isContainedInPowertypeExtentTable(EObject element);




	/**
	 * Init the redefinedClassifier
	 * @param settings settings for the redefinedClassifier ReferencesTable 
	 */
	public void initRedefinedClassifier(ReferencesTableSettings settings);

	/**
	 * Update the redefinedClassifier
	 * @param newValue the redefinedClassifier to update
	 * @generated
	 */
	public void updateRedefinedClassifier();

	/**
	 * Adds the given filter to the redefinedClassifier edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToRedefinedClassifier(ViewerFilter filter);

	/**
	 * Adds the given filter to the redefinedClassifier edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedClassifier(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the redefinedClassifier table
	 * @generated
	 */
	public boolean isContainedInRedefinedClassifierTable(EObject element);


	/**
	 * @return the representation
	 * @generated
	 */
	public EObject getRepresentation();

	/**
	 * Init the representation
	 * @param settings the combo setting
	 */
	public void initRepresentation(EObjectFlatComboSettings settings);

	/**
	 * Defines a new representation
	 * @param newValue the new representation to set
	 * @generated
	 */
	public void setRepresentation(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setRepresentationButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the representation edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToRepresentation(ViewerFilter filter);

	/**
	 * Adds the given filter to the representation edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToRepresentation(ViewerFilter filter);




	/**
	 * Init the useCase
	 * @param settings settings for the useCase ReferencesTable 
	 */
	public void initUseCase(ReferencesTableSettings settings);

	/**
	 * Update the useCase
	 * @param newValue the useCase to update
	 * @generated
	 */
	public void updateUseCase();

	/**
	 * Adds the given filter to the useCase edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToUseCase(ViewerFilter filter);

	/**
	 * Adds the given filter to the useCase edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToUseCase(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the useCase table
	 * @generated
	 */
	public boolean isContainedInUseCaseTable(EObject element);


	/**
	 * @return the classifierBehavior
	 * @generated
	 */
	public EObject getClassifierBehavior();

	/**
	 * Init the classifierBehavior
	 * @param settings the combo setting
	 */
	public void initClassifierBehavior(EObjectFlatComboSettings settings);

	/**
	 * Defines a new classifierBehavior
	 * @param newValue the new classifierBehavior to set
	 * @generated
	 */
	public void setClassifierBehavior(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setClassifierBehaviorButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the classifierBehavior edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToClassifierBehavior(ViewerFilter filter);

	/**
	 * Adds the given filter to the classifierBehavior edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToClassifierBehavior(ViewerFilter filter);


	/**
	 * @return the isActive
	 * @generated
	 */
	public Boolean getIsActive();

	/**
	 * Defines a new isActive
	 * @param newValue the new isActive to set
	 * @generated
	 */
	public void setIsActive(Boolean newValue);


	/**
	 * @return the isReentrant
	 * @generated
	 */
	public Boolean getIsReentrant();

	/**
	 * Defines a new isReentrant
	 * @param newValue the new isReentrant to set
	 * @generated
	 */
	public void setIsReentrant(Boolean newValue);




	/**
	 * Init the redefinedBehavior
	 * @param settings settings for the redefinedBehavior ReferencesTable 
	 */
	public void initRedefinedBehavior(ReferencesTableSettings settings);

	/**
	 * Update the redefinedBehavior
	 * @param newValue the redefinedBehavior to update
	 * @generated
	 */
	public void updateRedefinedBehavior();

	/**
	 * Adds the given filter to the redefinedBehavior edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToRedefinedBehavior(ViewerFilter filter);

	/**
	 * Adds the given filter to the redefinedBehavior edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedBehavior(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the redefinedBehavior table
	 * @generated
	 */
	public boolean isContainedInRedefinedBehaviorTable(EObject element);




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
	 * @return the specification
	 * @generated
	 */
	public EObject getSpecification();

	/**
	 * Init the specification
	 * @param settings the combo setting
	 */
	public void initSpecification(EObjectFlatComboSettings settings);

	/**
	 * Defines a new specification
	 * @param newValue the new specification to set
	 * @generated
	 */
	public void setSpecification(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setSpecificationButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the specification edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSpecification(ViewerFilter filter);

	/**
	 * Adds the given filter to the specification edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSpecification(ViewerFilter filter);




	/**
	 * Init the submachineState
	 * @param settings settings for the submachineState ReferencesTable 
	 */
	public void initSubmachineState(ReferencesTableSettings settings);

	/**
	 * Update the submachineState
	 * @param newValue the submachineState to update
	 * @generated
	 */
	public void updateSubmachineState();

	/**
	 * Adds the given filter to the submachineState edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSubmachineState(ViewerFilter filter);

	/**
	 * Adds the given filter to the submachineState edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSubmachineState(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the submachineState table
	 * @generated
	 */
	public boolean isContainedInSubmachineStateTable(EObject element);




	/**
	 * Init the extendedStateMachine
	 * @param settings settings for the extendedStateMachine ReferencesTable 
	 */
	public void initExtendedStateMachine(ReferencesTableSettings settings);

	/**
	 * Update the extendedStateMachine
	 * @param newValue the extendedStateMachine to update
	 * @generated
	 */
	public void updateExtendedStateMachine();

	/**
	 * Adds the given filter to the extendedStateMachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToExtendedStateMachine(ViewerFilter filter);

	/**
	 * Adds the given filter to the extendedStateMachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToExtendedStateMachine(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the extendedStateMachine table
	 * @generated
	 */
	public boolean isContainedInExtendedStateMachineTable(EObject element);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
