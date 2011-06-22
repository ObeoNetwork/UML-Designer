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
public interface ActivityPropertiesEditionPart {

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
	 * @return the owningTemplateParameter
	 * 
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
	 * 
	 */
	public void setOwningTemplateParameter(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the owningTemplateParameter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter);

	/**
	 * Adds the given filter to the owningTemplateParameter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter);


	/**
	 * @return the templateParameter
	 * 
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
	 * 
	 */
	public void setTemplateParameter(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the templateParameter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter);

	/**
	 * Adds the given filter to the templateParameter edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter);


	/**
	 * @return the isAbstract
	 * 
	 */
	public Boolean getIsAbstract();

	/**
	 * Defines a new isAbstract
	 * @param newValue the new isAbstract to set
	 * 
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
	 * 
	 */
	public void updatePowertypeExtent();

	/**
	 * Adds the given filter to the powertypeExtent edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToPowertypeExtent(ViewerFilter filter);

	/**
	 * Adds the given filter to the powertypeExtent edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToPowertypeExtent(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the powertypeExtent table
	 * 
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
	 * 
	 */
	public void updateRedefinedClassifier();

	/**
	 * Adds the given filter to the redefinedClassifier edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToRedefinedClassifier(ViewerFilter filter);

	/**
	 * Adds the given filter to the redefinedClassifier edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToRedefinedClassifier(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the redefinedClassifier table
	 * 
	 */
	public boolean isContainedInRedefinedClassifierTable(EObject element);


	/**
	 * @return the representation
	 * 
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
	 * 
	 */
	public void setRepresentation(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setRepresentationButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the representation edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToRepresentation(ViewerFilter filter);

	/**
	 * Adds the given filter to the representation edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
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
	 * 
	 */
	public void updateUseCase();

	/**
	 * Adds the given filter to the useCase edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToUseCase(ViewerFilter filter);

	/**
	 * Adds the given filter to the useCase edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToUseCase(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the useCase table
	 * 
	 */
	public boolean isContainedInUseCaseTable(EObject element);


	/**
	 * @return the classifierBehavior
	 * 
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
	 * 
	 */
	public void setClassifierBehavior(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setClassifierBehaviorButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the classifierBehavior edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToClassifierBehavior(ViewerFilter filter);

	/**
	 * Adds the given filter to the classifierBehavior edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToClassifierBehavior(ViewerFilter filter);


	/**
	 * @return the isActive
	 * 
	 */
	public Boolean getIsActive();

	/**
	 * Defines a new isActive
	 * @param newValue the new isActive to set
	 * 
	 */
	public void setIsActive(Boolean newValue);


	/**
	 * @return the isReentrant
	 * 
	 */
	public Boolean getIsReentrant();

	/**
	 * Defines a new isReentrant
	 * @param newValue the new isReentrant to set
	 * 
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
	 * 
	 */
	public void updateRedefinedBehavior();

	/**
	 * Adds the given filter to the redefinedBehavior edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToRedefinedBehavior(ViewerFilter filter);

	/**
	 * Adds the given filter to the redefinedBehavior edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToRedefinedBehavior(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the redefinedBehavior table
	 * 
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
	 * 
	 */
	public void updatePrecondition();

	/**
	 * Adds the given filter to the precondition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToPrecondition(ViewerFilter filter);

	/**
	 * Adds the given filter to the precondition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToPrecondition(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the precondition table
	 * 
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
	 * 
	 */
	public void updatePostcondition();

	/**
	 * Adds the given filter to the postcondition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToPostcondition(ViewerFilter filter);

	/**
	 * Adds the given filter to the postcondition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToPostcondition(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the postcondition table
	 * 
	 */
	public boolean isContainedInPostconditionTable(EObject element);


	/**
	 * @return the specification
	 * 
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
	 * 
	 */
	public void setSpecification(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setSpecificationButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the specification edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToSpecification(ViewerFilter filter);

	/**
	 * Adds the given filter to the specification edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToSpecification(ViewerFilter filter);


	/**
	 * @return the isReadOnly
	 * 
	 */
	public Boolean getIsReadOnly();

	/**
	 * Defines a new isReadOnly
	 * @param newValue the new isReadOnly to set
	 * 
	 */
	public void setIsReadOnly(Boolean newValue);




	/**
	 * Init the partition
	 * @param settings settings for the partition ReferencesTable 
	 */
	public void initPartition(ReferencesTableSettings settings);

	/**
	 * Update the partition
	 * @param newValue the partition to update
	 * 
	 */
	public void updatePartition();

	/**
	 * Adds the given filter to the partition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToPartition(ViewerFilter filter);

	/**
	 * Adds the given filter to the partition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToPartition(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the partition table
	 * 
	 */
	public boolean isContainedInPartitionTable(EObject element);


	/**
	 * @return the isSingleExecution
	 * 
	 */
	public Boolean getIsSingleExecution();

	/**
	 * Defines a new isSingleExecution
	 * @param newValue the new isSingleExecution to set
	 * 
	 */
	public void setIsSingleExecution(Boolean newValue);





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
