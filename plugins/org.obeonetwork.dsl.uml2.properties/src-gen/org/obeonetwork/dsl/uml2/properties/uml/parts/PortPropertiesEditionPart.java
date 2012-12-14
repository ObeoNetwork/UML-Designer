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
public interface PortPropertiesEditionPart {

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
	 * @return the type
	 * @generated
	 */
	public EObject getType();

	/**
	 * Init the type
	 * @param settings the combo setting
	 */
	public void initType(EObjectFlatComboSettings settings);

	/**
	 * Defines a new type
	 * @param newValue the new type to set
	 * @generated
	 */
	public void setType(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setTypeButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the type edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToType(ViewerFilter filter);

	/**
	 * Adds the given filter to the type edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToType(ViewerFilter filter);


	/**
	 * @return the isOrdered
	 * @generated
	 */
	public Boolean getIsOrdered();

	/**
	 * Defines a new isOrdered
	 * @param newValue the new isOrdered to set
	 * @generated
	 */
	public void setIsOrdered(Boolean newValue);


	/**
	 * @return the isUnique
	 * @generated
	 */
	public Boolean getIsUnique();

	/**
	 * Defines a new isUnique
	 * @param newValue the new isUnique to set
	 * @generated
	 */
	public void setIsUnique(Boolean newValue);


	/**
	 * @return the isReadOnly
	 * @generated
	 */
	public Boolean getIsReadOnly();

	/**
	 * Defines a new isReadOnly
	 * @param newValue the new isReadOnly to set
	 * @generated
	 */
	public void setIsReadOnly(Boolean newValue);


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
	 * @return the isDerived
	 * @generated
	 */
	public Boolean getIsDerived();

	/**
	 * Defines a new isDerived
	 * @param newValue the new isDerived to set
	 * @generated
	 */
	public void setIsDerived(Boolean newValue);


	/**
	 * @return the isDerivedUnion
	 * @generated
	 */
	public Boolean getIsDerivedUnion();

	/**
	 * Defines a new isDerivedUnion
	 * @param newValue the new isDerivedUnion to set
	 * @generated
	 */
	public void setIsDerivedUnion(Boolean newValue);


	/**
	 * @return the aggregation
	 * @generated
	 */
	public Enumerator getAggregation();

	/**
	 * Init the aggregation
	 * @param input the viewer input
	 * @param current the current value
	 */
	public void initAggregation(Object input, Enumerator current);

	/**
	 * Defines a new aggregation
	 * @param newValue the new aggregation to set
	 * @generated
	 */
	public void setAggregation(Enumerator newValue);




	/**
	 * Init the redefinedProperty
	 * @param settings settings for the redefinedProperty ReferencesTable 
	 */
	public void initRedefinedProperty(ReferencesTableSettings settings);

	/**
	 * Update the redefinedProperty
	 * @param newValue the redefinedProperty to update
	 * @generated
	 */
	public void updateRedefinedProperty();

	/**
	 * Adds the given filter to the redefinedProperty edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToRedefinedProperty(ViewerFilter filter);

	/**
	 * Adds the given filter to the redefinedProperty edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedProperty(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the redefinedProperty table
	 * @generated
	 */
	public boolean isContainedInRedefinedPropertyTable(EObject element);


	/**
	 * @return the owningAssociation
	 * @generated
	 */
	public EObject getOwningAssociation();

	/**
	 * Init the owningAssociation
	 * @param settings the combo setting
	 */
	public void initOwningAssociation(EObjectFlatComboSettings settings);

	/**
	 * Defines a new owningAssociation
	 * @param newValue the new owningAssociation to set
	 * @generated
	 */
	public void setOwningAssociation(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setOwningAssociationButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the owningAssociation edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToOwningAssociation(ViewerFilter filter);

	/**
	 * Adds the given filter to the owningAssociation edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToOwningAssociation(ViewerFilter filter);




	/**
	 * Init the subsettedProperty
	 * @param settings settings for the subsettedProperty ReferencesTable 
	 */
	public void initSubsettedProperty(ReferencesTableSettings settings);

	/**
	 * Update the subsettedProperty
	 * @param newValue the subsettedProperty to update
	 * @generated
	 */
	public void updateSubsettedProperty();

	/**
	 * Adds the given filter to the subsettedProperty edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSubsettedProperty(ViewerFilter filter);

	/**
	 * Adds the given filter to the subsettedProperty edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSubsettedProperty(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the subsettedProperty table
	 * @generated
	 */
	public boolean isContainedInSubsettedPropertyTable(EObject element);


	/**
	 * @return the association
	 * @generated
	 */
	public EObject getAssociation();

	/**
	 * Init the association
	 * @param settings the combo setting
	 */
	public void initAssociation(EObjectFlatComboSettings settings);

	/**
	 * Defines a new association
	 * @param newValue the new association to set
	 * @generated
	 */
	public void setAssociation(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setAssociationButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the association edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToAssociation(ViewerFilter filter);

	/**
	 * Adds the given filter to the association edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToAssociation(ViewerFilter filter);


	/**
	 * @return the associationEnd
	 * @generated
	 */
	public EObject getAssociationEnd();

	/**
	 * Init the associationEnd
	 * @param settings the combo setting
	 */
	public void initAssociationEnd(EObjectFlatComboSettings settings);

	/**
	 * Defines a new associationEnd
	 * @param newValue the new associationEnd to set
	 * @generated
	 */
	public void setAssociationEnd(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setAssociationEndButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the associationEnd edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToAssociationEnd(ViewerFilter filter);

	/**
	 * Adds the given filter to the associationEnd edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToAssociationEnd(ViewerFilter filter);


	/**
	 * @return the isBehavior
	 * @generated
	 */
	public Boolean getIsBehavior();

	/**
	 * Defines a new isBehavior
	 * @param newValue the new isBehavior to set
	 * @generated
	 */
	public void setIsBehavior(Boolean newValue);


	/**
	 * @return the isService
	 * @generated
	 */
	public Boolean getIsService();

	/**
	 * Defines a new isService
	 * @param newValue the new isService to set
	 * @generated
	 */
	public void setIsService(Boolean newValue);




	/**
	 * Init the redefinedPort
	 * @param settings settings for the redefinedPort ReferencesTable 
	 */
	public void initRedefinedPort(ReferencesTableSettings settings);

	/**
	 * Update the redefinedPort
	 * @param newValue the redefinedPort to update
	 * @generated
	 */
	public void updateRedefinedPort();

	/**
	 * Adds the given filter to the redefinedPort edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToRedefinedPort(ViewerFilter filter);

	/**
	 * Adds the given filter to the redefinedPort edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedPort(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the redefinedPort table
	 * @generated
	 */
	public boolean isContainedInRedefinedPortTable(EObject element);


	/**
	 * @return the protocol
	 * @generated
	 */
	public EObject getProtocol();

	/**
	 * Init the protocol
	 * @param settings the combo setting
	 */
	public void initProtocol(EObjectFlatComboSettings settings);

	/**
	 * Defines a new protocol
	 * @param newValue the new protocol to set
	 * @generated
	 */
	public void setProtocol(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setProtocolButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the protocol edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToProtocol(ViewerFilter filter);

	/**
	 * Adds the given filter to the protocol edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToProtocol(ViewerFilter filter);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * @generated
	 */
	public String getTitle();


}
