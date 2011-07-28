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
public interface InformationFlowPropertiesEditionPart {

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
	 * Init the realization
	 * @param settings settings for the realization ReferencesTable 
	 */
	public void initRealization(ReferencesTableSettings settings);

	/**
	 * Update the realization
	 * @param newValue the realization to update
	 * 
	 */
	public void updateRealization();

	/**
	 * Adds the given filter to the realization edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToRealization(ViewerFilter filter);

	/**
	 * Adds the given filter to the realization edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToRealization(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the realization table
	 * 
	 */
	public boolean isContainedInRealizationTable(EObject element);




	/**
	 * Init the conveyed
	 * @param settings settings for the conveyed ReferencesTable 
	 */
	public void initConveyed(ReferencesTableSettings settings);

	/**
	 * Update the conveyed
	 * @param newValue the conveyed to update
	 * 
	 */
	public void updateConveyed();

	/**
	 * Adds the given filter to the conveyed edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToConveyed(ViewerFilter filter);

	/**
	 * Adds the given filter to the conveyed edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToConveyed(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the conveyed table
	 * 
	 */
	public boolean isContainedInConveyedTable(EObject element);




	/**
	 * Init the informationSource
	 * @param settings settings for the informationSource ReferencesTable 
	 */
	public void initInformationSource(ReferencesTableSettings settings);

	/**
	 * Update the informationSource
	 * @param newValue the informationSource to update
	 * 
	 */
	public void updateInformationSource();

	/**
	 * Adds the given filter to the informationSource edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToInformationSource(ViewerFilter filter);

	/**
	 * Adds the given filter to the informationSource edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToInformationSource(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the informationSource table
	 * 
	 */
	public boolean isContainedInInformationSourceTable(EObject element);




	/**
	 * Init the informationTarget
	 * @param settings settings for the informationTarget ReferencesTable 
	 */
	public void initInformationTarget(ReferencesTableSettings settings);

	/**
	 * Update the informationTarget
	 * @param newValue the informationTarget to update
	 * 
	 */
	public void updateInformationTarget();

	/**
	 * Adds the given filter to the informationTarget edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToInformationTarget(ViewerFilter filter);

	/**
	 * Adds the given filter to the informationTarget edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToInformationTarget(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the informationTarget table
	 * 
	 */
	public boolean isContainedInInformationTargetTable(EObject element);




	/**
	 * Init the realizingActivityEdge
	 * @param settings settings for the realizingActivityEdge ReferencesTable 
	 */
	public void initRealizingActivityEdge(ReferencesTableSettings settings);

	/**
	 * Update the realizingActivityEdge
	 * @param newValue the realizingActivityEdge to update
	 * 
	 */
	public void updateRealizingActivityEdge();

	/**
	 * Adds the given filter to the realizingActivityEdge edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToRealizingActivityEdge(ViewerFilter filter);

	/**
	 * Adds the given filter to the realizingActivityEdge edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToRealizingActivityEdge(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the realizingActivityEdge table
	 * 
	 */
	public boolean isContainedInRealizingActivityEdgeTable(EObject element);




	/**
	 * Init the realizingConnector
	 * @param settings settings for the realizingConnector ReferencesTable 
	 */
	public void initRealizingConnector(ReferencesTableSettings settings);

	/**
	 * Update the realizingConnector
	 * @param newValue the realizingConnector to update
	 * 
	 */
	public void updateRealizingConnector();

	/**
	 * Adds the given filter to the realizingConnector edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToRealizingConnector(ViewerFilter filter);

	/**
	 * Adds the given filter to the realizingConnector edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToRealizingConnector(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the realizingConnector table
	 * 
	 */
	public boolean isContainedInRealizingConnectorTable(EObject element);




	/**
	 * Init the realizingMessage
	 * @param settings settings for the realizingMessage ReferencesTable 
	 */
	public void initRealizingMessage(ReferencesTableSettings settings);

	/**
	 * Update the realizingMessage
	 * @param newValue the realizingMessage to update
	 * 
	 */
	public void updateRealizingMessage();

	/**
	 * Adds the given filter to the realizingMessage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToRealizingMessage(ViewerFilter filter);

	/**
	 * Adds the given filter to the realizingMessage edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToRealizingMessage(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the realizingMessage table
	 * 
	 */
	public boolean isContainedInRealizingMessageTable(EObject element);





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
