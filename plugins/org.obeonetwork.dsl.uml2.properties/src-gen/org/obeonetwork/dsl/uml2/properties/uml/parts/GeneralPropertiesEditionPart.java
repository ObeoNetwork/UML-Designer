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

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;

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
public interface GeneralPropertiesEditionPart {

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
	 * @return the ordered
	 * @generated
	 */
	public Boolean getOrdered();

	/**
	 * Defines a new ordered
	 * @param newValue the new ordered to set
	 * @generated
	 */
	public void setOrdered(Boolean newValue);


	/**
	 * @return the abstract
	 * @generated
	 */
	public Boolean getAbstract_();

	/**
	 * Defines a new abstract
	 * @param newValue the new abstract to set
	 * @generated
	 */
	public void setAbstract_(Boolean newValue);


	/**
	 * @return the leaf
	 * @generated
	 */
	public Boolean getLeaf();

	/**
	 * Defines a new leaf
	 * @param newValue the new leaf to set
	 * @generated
	 */
	public void setLeaf(Boolean newValue);


	/**
	 * @return the static
	 * @generated
	 */
	public Boolean getStatic_();

	/**
	 * Defines a new static
	 * @param newValue the new static to set
	 * @generated
	 */
	public void setStatic_(Boolean newValue);


	/**
	 * @return the unique
	 * @generated
	 */
	public Boolean getUnique();

	/**
	 * Defines a new unique
	 * @param newValue the new unique to set
	 * @generated
	 */
	public void setUnique(Boolean newValue);


	/**
	 * @return the query
	 * @generated
	 */
	public Boolean getQuery();

	/**
	 * Defines a new query
	 * @param newValue the new query to set
	 * @generated
	 */
	public void setQuery(Boolean newValue);


	/**
	 * @return the readOnly
	 * @generated
	 */
	public Boolean getReadOnly();

	/**
	 * Defines a new readOnly
	 * @param newValue the new readOnly to set
	 * @generated
	 */
	public void setReadOnly(Boolean newValue);


	/**
	 * @return the derived
	 * @generated
	 */
	public Boolean getDerived();

	/**
	 * Defines a new derived
	 * @param newValue the new derived to set
	 * @generated
	 */
	public void setDerived(Boolean newValue);


	/**
	 * @return the derivedUnion
	 * @generated
	 */
	public Boolean getDerivedUnion();

	/**
	 * Defines a new derivedUnion
	 * @param newValue the new derivedUnion to set
	 * @generated
	 */
	public void setDerivedUnion(Boolean newValue);


	/**
	 * @return the substitutable
	 * @generated
	 */
	public Boolean getSubstitutable();

	/**
	 * Defines a new substitutable
	 * @param newValue the new substitutable to set
	 * @generated
	 */
	public void setSubstitutable(Boolean newValue);


	/**
	 * @return the active
	 * @generated
	 */
	public Boolean getActive();

	/**
	 * Defines a new active
	 * @param newValue the new active to set
	 * @generated
	 */
	public void setActive(Boolean newValue);


	/**
	 * @return the behavior
	 * @generated
	 */
	public Boolean getBehavior();

	/**
	 * Defines a new behavior
	 * @param newValue the new behavior to set
	 * @generated
	 */
	public void setBehavior(Boolean newValue);


	/**
	 * @return the service
	 * @generated
	 */
	public Boolean getService();

	/**
	 * Defines a new service
	 * @param newValue the new service to set
	 * @generated
	 */
	public void setService(Boolean newValue);


	/**
	 * @return the reentrant
	 * @generated
	 */
	public Boolean getReentrant();

	/**
	 * Defines a new reentrant
	 * @param newValue the new reentrant to set
	 * @generated
	 */
	public void setReentrant(Boolean newValue);


	/**
	 * @return the indirectlyInstantiated
	 * @generated
	 */
	public Boolean getIndirectlyInstantiated();

	/**
	 * Defines a new indirectlyInstantiated
	 * @param newValue the new indirectlyInstantiated to set
	 * @generated
	 */
	public void setIndirectlyInstantiated(Boolean newValue);


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
	 * @return the direction
	 * @generated
	 */
	public Enumerator getDirection();

	/**
	 * Init the direction
	 * @param input the viewer input
	 * @param current the current value
	 */
	public void initDirection(Object input, Enumerator current);

	/**
	 * Defines a new direction
	 * @param newValue the new direction to set
	 * @generated
	 */
	public void setDirection(Enumerator newValue);


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
	 * @return the lowerValue
	 * @generated
	 */
	public String getLowerValue();

	/**
	 * Defines a new lowerValue
	 * @param newValue the new lowerValue to set
	 * @generated
	 */
	public void setLowerValue(String newValue);


	/**
	 * @return the upperValue
	 * @generated
	 */
	public String getUpperValue();

	/**
	 * Defines a new upperValue
	 * @param newValue the new upperValue to set
	 * @generated
	 */
	public void setUpperValue(String newValue);


	/**
	 * @return the defaultValue
	 * @generated
	 */
	public EObject getDefaultValue();

	/**
	 * Init the defaultValue
	 * @param settings the combo setting
	 */
	public void initDefaultValue(EObjectFlatComboSettings settings);

	/**
	 * Defines a new defaultValue
	 * @param newValue the new defaultValue to set
	 * @generated
	 */
	public void setDefaultValue(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setDefaultValueButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the defaultValue edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToDefaultValue(ViewerFilter filter);

	/**
	 * Adds the given filter to the defaultValue edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToDefaultValue(ViewerFilter filter);




	/**
	 * Init the memberEnd
	 * @param settings settings for the memberEnd ReferencesTable 
	 */
	public void initMemberEnd(ReferencesTableSettings settings);

	/**
	 * Update the memberEnd
	 * @param newValue the memberEnd to update
	 * @generated
	 */
	public void updateMemberEnd();

	/**
	 * Adds the given filter to the memberEnd edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToMemberEnd(ViewerFilter filter);

	/**
	 * Adds the given filter to the memberEnd edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToMemberEnd(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the memberEnd table
	 * @generated
	 */
	public boolean isContainedInMemberEndTable(EObject element);




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
	 * @return the kind
	 * @generated
	 */
	public Enumerator getKind();

	/**
	 * Init the kind
	 * @param input the viewer input
	 * @param current the current value
	 */
	public void initKind(Object input, Enumerator current);

	/**
	 * Defines a new kind
	 * @param newValue the new kind to set
	 * @generated
	 */
	public void setKind(Enumerator newValue);


	/**
	 * @return the kind_readonly
	 * @generated
	 */
	public Enumerator getKind_readonly();

	/**
	 * Init the kind_readonly
	 * @param input the viewer input
	 * @param current the current value
	 */
	public void initKind_readonly(Object input, Enumerator current);

	/**
	 * Defines a new kind_readonly
	 * @param newValue the new kind_readonly to set
	 * @generated
	 */
	public void setKind_readonly(Enumerator newValue);




	/**
	 * Init the trigger
	 * @param current the current value
	 * @param containgFeature the feature where to navigate if necessary
	 * @param feature the feature to manage
	 */
	public void initTrigger(ReferencesTableSettings settings);

	/**
	 * Update the trigger
	 * @param newValue the trigger to update
	 * @generated
	 */
	public void updateTrigger();

	/**
	 * Adds the given filter to the trigger edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToTrigger(ViewerFilter filter);

	/**
	 * Adds the given filter to the trigger edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToTrigger(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the trigger table
	 * @generated
	 */
	public boolean isContainedInTriggerTable(EObject element);


	/**
	 * @return the effect
	 * @generated
	 */
	public EObject getEffect();

	/**
	 * Init the effect
	 * @param settings the combo setting
	 */
	public void initEffect(EObjectFlatComboSettings settings);

	/**
	 * Defines a new effect
	 * @param newValue the new effect to set
	 * @generated
	 */
	public void setEffect(EObject newValue);


	/**
	 * @return the guard
	 * @generated
	 */
	public EObject getGuard();

	/**
	 * Init the guard
	 * @param settings the combo setting
	 */
	public void initGuard(EObjectFlatComboSettings settings);

	/**
	 * Defines a new guard
	 * @param newValue the new guard to set
	 * @generated
	 */
	public void setGuard(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setGuardButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the guard edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToGuard(ViewerFilter filter);

	/**
	 * Adds the given filter to the guard edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToGuard(ViewerFilter filter);


	/**
	 * @return the source
	 * @generated
	 */
	public EObject getSource();

	/**
	 * Init the source
	 * @param settings the combo setting
	 */
	public void initSource(EObjectFlatComboSettings settings);

	/**
	 * Defines a new source
	 * @param newValue the new source to set
	 * @generated
	 */
	public void setSource(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setSourceButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the source edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSource(ViewerFilter filter);

	/**
	 * Adds the given filter to the source edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSource(ViewerFilter filter);


	/**
	 * @return the target
	 * @generated
	 */
	public EObject getTarget();

	/**
	 * Init the target
	 * @param settings the combo setting
	 */
	public void initTarget(EObjectFlatComboSettings settings);

	/**
	 * Defines a new target
	 * @param newValue the new target to set
	 * @generated
	 */
	public void setTarget(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setTargetButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the target edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToTarget(ViewerFilter filter);

	/**
	 * Adds the given filter to the target edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToTarget(ViewerFilter filter);




	/**
	 * Init the ownedRule
	 * @param current the current value
	 * @param containgFeature the feature where to navigate if necessary
	 * @param feature the feature to manage
	 */
	public void initOwnedRule(ReferencesTableSettings settings);

	/**
	 * Update the ownedRule
	 * @param newValue the ownedRule to update
	 * @generated
	 */
	public void updateOwnedRule();

	/**
	 * Adds the given filter to the ownedRule edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToOwnedRule(ViewerFilter filter);

	/**
	 * Adds the given filter to the ownedRule edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToOwnedRule(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the ownedRule table
	 * @generated
	 */
	public boolean isContainedInOwnedRuleTable(EObject element);


	/**
	 * @return the icon
	 * @generated
	 */
	public String getIcon();

	/**
	 * Init the icon
	 * @param key the key of the editor 
	 * @param current the new path of the pics
	 */
	public void initIcon(String key, String newValue);

	/**
	 * Defines a new icon
	 * @param newValue the new icon to set
	 * @generated
	 */
	public void setIcon(String newValue);


	/**
	 * @return the body
	 * @generated
	 */
	public String getBody();

	/**
	 * Defines a new body
	 * @param newValue the new body to set
	 * @generated
	 */
	public void setBody(String newValue);


	/**
	 * @return the extendedCase
	 * @generated
	 */
	public Object getExtendedCase();

	/**
	 * Init the extendedCase
	 * @param input choice of values
	 * @param currentValue the current value
	 */
	public void initExtendedCase(Object input, Object currentValue);

	/**
	 * Defines a new extendedCase
	 * @param newValue the new extendedCase to set
	 * @generated
	 */
	public void setExtendedCase(Object newValue);

	/**
	 * Adds the given filter to the extendedCase edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToExtendedCase(ViewerFilter filter);


	/**
	 * @return the addition
	 * @generated
	 */
	public Object getAddition();

	/**
	 * Init the addition
	 * @param input choice of values
	 * @param currentValue the current value
	 */
	public void initAddition(Object input, Object currentValue);

	/**
	 * Defines a new addition
	 * @param newValue the new addition to set
	 * @generated
	 */
	public void setAddition(Object newValue);

	/**
	 * Adds the given filter to the addition edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToAddition(ViewerFilter filter);


	/**
	 * @return the role
	 * @generated
	 */
	public EObject getRole();

	/**
	 * Init the role
	 * @param settings the combo setting
	 */
	public void initRole(EObjectFlatComboSettings settings);

	/**
	 * Defines a new role
	 * @param newValue the new role to set
	 * @generated
	 */
	public void setRole(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setRoleButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the role edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToRole(ViewerFilter filter);

	/**
	 * Adds the given filter to the role edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToRole(ViewerFilter filter);




	/**
	 * Init the usecase
	 * @param settings settings for the usecase ReferencesTable 
	 */
	public void initUsecase(ReferencesTableSettings settings);

	/**
	 * Update the usecase
	 * @param newValue the usecase to update
	 * @generated
	 */
	public void updateUsecase();

	/**
	 * Adds the given filter to the usecase edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToUsecase(ViewerFilter filter);

	/**
	 * Adds the given filter to the usecase edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToUsecase(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the usecase table
	 * @generated
	 */
	public boolean isContainedInUsecaseTable(EObject element);




	/**
	 * Init the subjects
	 * @param settings settings for the subjects ReferencesTable 
	 */
	public void initSubjects(ReferencesTableSettings settings);

	/**
	 * Update the subjects
	 * @param newValue the subjects to update
	 * @generated
	 */
	public void updateSubjects();

	/**
	 * Adds the given filter to the subjects edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSubjects(ViewerFilter filter);

	/**
	 * Adds the given filter to the subjects edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSubjects(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the subjects table
	 * @generated
	 */
	public boolean isContainedInSubjectsTable(EObject element);


	/**
	 * @return the entry
	 * @generated
	 */
	public EObject getEntry();

	/**
	 * Init the entry
	 * @param settings the combo setting
	 */
	public void initEntry(EObjectFlatComboSettings settings);

	/**
	 * Defines a new entry
	 * @param newValue the new entry to set
	 * @generated
	 */
	public void setEntry(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setEntryButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the entry edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToEntry(ViewerFilter filter);

	/**
	 * Adds the given filter to the entry edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToEntry(ViewerFilter filter);


	/**
	 * @return the exit
	 * @generated
	 */
	public EObject getExit();

	/**
	 * Init the exit
	 * @param settings the combo setting
	 */
	public void initExit(EObjectFlatComboSettings settings);

	/**
	 * Defines a new exit
	 * @param newValue the new exit to set
	 * @generated
	 */
	public void setExit(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setExitButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the exit edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToExit(ViewerFilter filter);

	/**
	 * Adds the given filter to the exit edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToExit(ViewerFilter filter);


	/**
	 * @return the do
	 * @generated
	 */
	public EObject getDo_();

	/**
	 * Init the do
	 * @param settings the combo setting
	 */
	public void initDo_(EObjectFlatComboSettings settings);

	/**
	 * Defines a new do
	 * @param newValue the new do to set
	 * @generated
	 */
	public void setDo_(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setDo_ButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the do edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToDo_(ViewerFilter filter);

	/**
	 * Adds the given filter to the do edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToDo_(ViewerFilter filter);


	/**
	 * @return the submachine
	 * @generated
	 */
	public EObject getSubmachine();

	/**
	 * Init the submachine
	 * @param settings the combo setting
	 */
	public void initSubmachine(EObjectFlatComboSettings settings);

	/**
	 * Defines a new submachine
	 * @param newValue the new submachine to set
	 * @generated
	 */
	public void setSubmachine(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setSubmachineButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the submachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToSubmachine(ViewerFilter filter);

	/**
	 * Adds the given filter to the submachine edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToSubmachine(ViewerFilter filter);


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
	 * @return the instanceValue
	 * @generated
	 */
	public EObject getInstanceValue();

	/**
	 * Init the instanceValue
	 * @param settings the combo setting
	 */
	public void initInstanceValue(EObjectFlatComboSettings settings);

	/**
	 * Defines a new instanceValue
	 * @param newValue the new instanceValue to set
	 * @generated
	 */
	public void setInstanceValue(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setInstanceValueButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the instanceValue edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToInstanceValue(ViewerFilter filter);

	/**
	 * Adds the given filter to the instanceValue edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToInstanceValue(ViewerFilter filter);


	/**
	 * @return the min
	 * @generated
	 */
	public EObject getMin();

	/**
	 * Init the min
	 * @param settings the combo setting
	 */
	public void initMin(EObjectFlatComboSettings settings);

	/**
	 * Defines a new min
	 * @param newValue the new min to set
	 * @generated
	 */
	public void setMin(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setMinButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the min edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToMin(ViewerFilter filter);

	/**
	 * Adds the given filter to the min edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToMin(ViewerFilter filter);


	/**
	 * @return the instance
	 * @generated
	 */
	public EObject getInstance();

	/**
	 * Init the instance
	 * @param settings the combo setting
	 */
	public void initInstance(EObjectFlatComboSettings settings);

	/**
	 * Defines a new instance
	 * @param newValue the new instance to set
	 * @generated
	 */
	public void setInstance(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setInstanceButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the instance edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToInstance(ViewerFilter filter);

	/**
	 * Adds the given filter to the instance edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToInstance(ViewerFilter filter);


	/**
	 * @return the max
	 * @generated
	 */
	public EObject getMax();

	/**
	 * Init the max
	 * @param settings the combo setting
	 */
	public void initMax(EObjectFlatComboSettings settings);

	/**
	 * Defines a new max
	 * @param newValue the new max to set
	 * @generated
	 */
	public void setMax(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setMaxButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the max edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToMax(ViewerFilter filter);

	/**
	 * Adds the given filter to the max edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToMax(ViewerFilter filter);


	/**
	 * @return the event
	 * @generated
	 */
	public EObject getEvent();

	/**
	 * Init the event
	 * @param settings the combo setting
	 */
	public void initEvent(EObjectFlatComboSettings settings);

	/**
	 * Defines a new event
	 * @param newValue the new event to set
	 * @generated
	 */
	public void setEvent(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setEventButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the event edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToEvent(ViewerFilter filter);

	/**
	 * Adds the given filter to the event edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToEvent(ViewerFilter filter);


	/**
	 * @return the when
	 * @generated
	 */
	public EObject getWhen();

	/**
	 * Init the when
	 * @param settings the combo setting
	 */
	public void initWhen(EObjectFlatComboSettings settings);

	/**
	 * Defines a new when
	 * @param newValue the new when to set
	 * @generated
	 */
	public void setWhen(EObject newValue);


	/**
	 * @return the changeExpression
	 * @generated
	 */
	public EObject getChangeExpression();

	/**
	 * Init the changeExpression
	 * @param settings the combo setting
	 */
	public void initChangeExpression(EObjectFlatComboSettings settings);

	/**
	 * Defines a new changeExpression
	 * @param newValue the new changeExpression to set
	 * @generated
	 */
	public void setChangeExpression(EObject newValue);


	/**
	 * @return the region
	 * @generated
	 */
	public EObject getRegion();

	/**
	 * Init the region
	 * @param settings the combo setting
	 */
	public void initRegion(EObjectFlatComboSettings settings);

	/**
	 * Defines a new region
	 * @param newValue the new region to set
	 * @generated
	 */
	public void setRegion(EObject newValue);


	/**
	 * @return the behaviour
	 * @generated
	 */
	public EObject getBehaviour();

	/**
	 * Init the behaviour
	 * @param settings the combo setting
	 */
	public void initBehaviour(EObjectFlatComboSettings settings);

	/**
	 * Defines a new behaviour
	 * @param newValue the new behaviour to set
	 * @generated
	 */
	public void setBehaviour(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setBehaviourButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the behaviour edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToBehaviour(ViewerFilter filter);

	/**
	 * Adds the given filter to the behaviour edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToBehaviour(ViewerFilter filter);


	/**
	 * @return the unmarshall
	 * @generated
	 */
	public Boolean getUnmarshall();

	/**
	 * Defines a new unmarshall
	 * @param newValue the new unmarshall to set
	 * @generated
	 */
	public void setUnmarshall(Boolean newValue);


	/**
	 * @return the operation
	 * @generated
	 */
	public EObject getOperation();

	/**
	 * Init the operation
	 * @param settings the combo setting
	 */
	public void initOperation(EObjectFlatComboSettings settings);

	/**
	 * Defines a new operation
	 * @param newValue the new operation to set
	 * @generated
	 */
	public void setOperation(EObject newValue);

	/**
	 * Defines the button mode
	 * @param newValue the new mode to set
	 * @generated
	 */
	public void setOperationButtonMode(ButtonsModeEnum newValue);

	/**
	 * Adds the given filter to the operation edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addFilterToOperation(ViewerFilter filter);

	/**
	 * Adds the given filter to the operation edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * @generated
	 */
	public void addBusinessFilterToOperation(ViewerFilter filter);


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
