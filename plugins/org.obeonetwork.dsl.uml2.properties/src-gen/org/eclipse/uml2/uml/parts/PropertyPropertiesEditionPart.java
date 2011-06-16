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
package org.eclipse.uml2.uml.parts;

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
public interface PropertyPropertiesEditionPart {

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
	 * @return the isStatic
	 * 
	 */
	public Boolean getIsStatic();

	/**
	 * Defines a new isStatic
	 * @param newValue the new isStatic to set
	 * 
	 */
	public void setIsStatic(Boolean newValue);


	/**
	 * @return the type
	 * 
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
	 * 
	 */
	public void setType(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setTypeButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the type edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToType(ViewerFilter filter);

	/**
	 * Adds the given filter to the type edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToType(ViewerFilter filter);


	/**
	 * @return the isOrdered
	 * 
	 */
	public Boolean getIsOrdered();

	/**
	 * Defines a new isOrdered
	 * @param newValue the new isOrdered to set
	 * 
	 */
	public void setIsOrdered(Boolean newValue);


	/**
	 * @return the isUnique
	 * 
	 */
	public Boolean getIsUnique();

	/**
	 * Defines a new isUnique
	 * @param newValue the new isUnique to set
	 * 
	 */
	public void setIsUnique(Boolean newValue);


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
	 * @return the class
	 * 
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
	 * 
	 */
	public void setClass_(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setClass_ButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the class edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToClass_(ViewerFilter filter);

	/**
	 * Adds the given filter to the class edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToClass_(ViewerFilter filter);


	/**
	 * @return the datatype
	 * 
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
	 * 
	 */
	public void setDatatype(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setDatatypeButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the datatype edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToDatatype(ViewerFilter filter);

	/**
	 * Adds the given filter to the datatype edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToDatatype(ViewerFilter filter);


	/**
	 * @return the isDerived
	 * 
	 */
	public Boolean getIsDerived();

	/**
	 * Defines a new isDerived
	 * @param newValue the new isDerived to set
	 * 
	 */
	public void setIsDerived(Boolean newValue);


	/**
	 * @return the isDerivedUnion
	 * 
	 */
	public Boolean getIsDerivedUnion();

	/**
	 * Defines a new isDerivedUnion
	 * @param newValue the new isDerivedUnion to set
	 * 
	 */
	public void setIsDerivedUnion(Boolean newValue);


	/**
	 * @return the aggregation
	 * 
	 */
	public Enumerator getAggregation();

	/**
	 * Init the aggregation
	 * @param eenum the enum to manage
	 * @param current the current value
	 */
	public void initAggregation(EEnum eenum, Enumerator current);

	/**
	 * Defines a new aggregation
	 * @param newValue the new aggregation to set
	 * 
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
	 * 
	 */
	public void updateRedefinedProperty();

	/**
	 * Adds the given filter to the redefinedProperty edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToRedefinedProperty(ViewerFilter filter);

	/**
	 * Adds the given filter to the redefinedProperty edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToRedefinedProperty(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the redefinedProperty table
	 * 
	 */
	public boolean isContainedInRedefinedPropertyTable(EObject element);


	/**
	 * @return the owningAssociation
	 * 
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
	 * 
	 */
	public void setOwningAssociation(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setOwningAssociationButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the owningAssociation edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToOwningAssociation(ViewerFilter filter);

	/**
	 * Adds the given filter to the owningAssociation edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
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
	 * 
	 */
	public void updateSubsettedProperty();

	/**
	 * Adds the given filter to the subsettedProperty edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToSubsettedProperty(ViewerFilter filter);

	/**
	 * Adds the given filter to the subsettedProperty edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToSubsettedProperty(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the subsettedProperty table
	 * 
	 */
	public boolean isContainedInSubsettedPropertyTable(EObject element);


	/**
	 * @return the association
	 * 
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
	 * 
	 */
	public void setAssociation(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setAssociationButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the association edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToAssociation(ViewerFilter filter);

	/**
	 * Adds the given filter to the association edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToAssociation(ViewerFilter filter);


	/**
	 * @return the associationEnd
	 * 
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
	 * 
	 */
	public void setAssociationEnd(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * 
	 */
	public void setAssociationEndButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the associationEnd edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToAssociationEnd(ViewerFilter filter);

	/**
	 * Adds the given filter to the associationEnd edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToAssociationEnd(ViewerFilter filter);





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
