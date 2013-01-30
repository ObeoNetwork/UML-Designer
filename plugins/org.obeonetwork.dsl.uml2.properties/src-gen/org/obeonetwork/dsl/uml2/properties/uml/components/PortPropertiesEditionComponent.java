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
package org.obeonetwork.dsl.uml2.properties.uml.components;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.VisibilityKind;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.eef.runtime.api.notify.EStructuralFeatureNotificationFilter;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;

import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.uml2.types.TypesPackage;

import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class PortPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String GENERAL_PART = "General"; //$NON-NLS-1$

	
	/**
	 * Settings for type EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings typeSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public PortPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject port, String editing_mode) {
		super(editingContext, port, editing_mode);
		parts = new String[] { GENERAL_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.General.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object, int, org.eclipse.emf.ecore.EObject, 
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * @generated
	 */
	public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);
			
			final Port port = (Port)elt;
			final GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.General.name))
				generalPart.setName(EEFConverterUtil.convertToString(TypesPackage.Literals.STRING, port.getName()));
			
			if (isAccessible(UmlViewsRepository.General.visibility)) {
				generalPart.initVisibility(EEFUtils.choiceOfValues(port, UMLPackage.eINSTANCE.getNamedElement_Visibility()), port.getVisibility());
			}
			generalPart.setStatic_(port.isStatic());
			
			generalPart.setLeaf(port.isLeaf());
			
			generalPart.setOrdered(port.isOrdered());
			
			generalPart.setUnique(port.isUnique());
			
			generalPart.setReadOnly(port.isReadOnly());
			
			generalPart.setDerived(port.isDerived());
			
			generalPart.setDerivedUnion(port.isDerivedUnion());
			
			if (isAccessible(UmlViewsRepository.General.aggregation)) {
				generalPart.initAggregation(EEFUtils.choiceOfValues(port, UMLPackage.eINSTANCE.getProperty_Aggregation()), port.getAggregation());
			}
			if (isAccessible(UmlViewsRepository.General.type)) {
				// init part
				typeSettings = new EObjectFlatComboSettings(port, UMLPackage.eINSTANCE.getOperation_Type());
				generalPart.initType(typeSettings);
				// set the button mode
				generalPart.setTypeButtonMode(ButtonsModeEnum.BROWSE);
			}
			// FIXME NO VALID CASE INTO template public updater(editionElement : PropertiesEditionElement, view : View, pec : PropertiesEditionComponent) in widgetControl.mtl module, with the values : upperValue, General, Port.
			// FIXME NO VALID CASE INTO template public updater(editionElement : PropertiesEditionElement, view : View, pec : PropertiesEditionComponent) in widgetControl.mtl module, with the values : lowerValue, General, Port.
			// FIXME NO VALID CASE INTO template public updater(editionElement : PropertiesEditionElement, view : View, pec : PropertiesEditionComponent) in widgetControl.mtl module, with the values : defaultValue, General, Port.
			generalPart.setBehavior(port.isBehavior());
			
			generalPart.setService(port.isService());
			
			// init filters
			
			
			
			
			
			
			
			
			
			
			if (isAccessible(UmlViewsRepository.General.type)) {
				generalPart.addFilterToType(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof Type); //$NON-NLS-1$ 
					}
					
				});
			}
			// FIXME NO VALID CASE INTO template public filterUpdater(editionElement : PropertiesEditionElement, view : View, pec : PropertiesEditionComponent) in widgetControl.mtl module, with the values : upperValue, General, Port.
			// FIXME NO VALID CASE INTO template public filterUpdater(editionElement : PropertiesEditionElement, view : View, pec : PropertiesEditionComponent) in widgetControl.mtl module, with the values : lowerValue, General, Port.
			// FIXME NO VALID CASE INTO template public filterUpdater(editionElement : PropertiesEditionElement, view : View, pec : PropertiesEditionComponent) in widgetControl.mtl module, with the values : defaultValue, General, Port.
			
			
			// init values for referenced views
			
			// init filters for referenced views
			
		}
		setInitializing(false);
	}



















	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
	 */
	public EStructuralFeature associatedFeature(Object editorKey) {
		if (editorKey == UmlViewsRepository.General.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.General.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.General.Qualifiers.static_) {
			return UMLPackage.eINSTANCE.getFeature_IsStatic();
		}
		if (editorKey == UmlViewsRepository.General.Qualifiers.leaf) {
			return UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf();
		}
		if (editorKey == UmlViewsRepository.General.Qualifiers.ordered) {
			return UMLPackage.eINSTANCE.getOperation_IsOrdered();
		}
		if (editorKey == UmlViewsRepository.General.Qualifiers.unique) {
			return UMLPackage.eINSTANCE.getOperation_IsUnique();
		}
		if (editorKey == UmlViewsRepository.General.readOnly) {
			return UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly();
		}
		if (editorKey == UmlViewsRepository.General.derived) {
			return UMLPackage.eINSTANCE.getProperty_IsDerived();
		}
		if (editorKey == UmlViewsRepository.General.derivedUnion) {
			return UMLPackage.eINSTANCE.getProperty_IsDerivedUnion();
		}
		if (editorKey == UmlViewsRepository.General.aggregation) {
			return UMLPackage.eINSTANCE.getProperty_Aggregation();
		}
		if (editorKey == UmlViewsRepository.General.type) {
			return UMLPackage.eINSTANCE.getOperation_Type();
		}
		if (editorKey == UmlViewsRepository.General.upperValue) {
			return UMLPackage.eINSTANCE.getMultiplicityElement_UpperValue();
		}
		if (editorKey == UmlViewsRepository.General.lowerValue) {
			return UMLPackage.eINSTANCE.getMultiplicityElement_LowerValue();
		}
		if (editorKey == UmlViewsRepository.General.defaultValue) {
			return UMLPackage.eINSTANCE.getProperty_DefaultValue();
		}
		if (editorKey == UmlViewsRepository.General.behavior) {
			return UMLPackage.eINSTANCE.getPort_IsBehavior();
		}
		if (editorKey == UmlViewsRepository.General.service) {
			return UMLPackage.eINSTANCE.getPort_IsService();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		Port port = (Port)semanticObject;

		if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
			port.setName((java.lang.String)EEFConverterUtil.createFromString(TypesPackage.Literals.STRING, (String)event.getNewValue()));
		}
		if (UmlViewsRepository.General.visibility == event.getAffectedEditor()) {
			port.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.General.Qualifiers.static_ == event.getAffectedEditor()) {
			port.setIsStatic((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.General.Qualifiers.leaf == event.getAffectedEditor()) {
			port.setIsLeaf((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.General.readOnly == event.getAffectedEditor()) {
			port.setIsReadOnly((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.General.derived == event.getAffectedEditor()) {
			port.setIsDerived((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.General.derivedUnion == event.getAffectedEditor()) {
			port.setIsDerivedUnion((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.General.aggregation == event.getAffectedEditor()) {
			port.setAggregation((AggregationKind)event.getNewValue());
		}
		if (UmlViewsRepository.General.upperValue == event.getAffectedEditor()) {
			// FIXME INVALID CASE you must override the template 'declareEObjectUpdater' for the case : upperValue, General, Port.
		}
		if (UmlViewsRepository.General.lowerValue == event.getAffectedEditor()) {
			// FIXME INVALID CASE you must override the template 'declareEObjectUpdater' for the case : lowerValue, General, Port.
		}
		if (UmlViewsRepository.General.defaultValue == event.getAffectedEditor()) {
			// FIXME INVALID CASE you must override the template 'declareEObjectUpdater' for the case : defaultValue, General, Port.
		}
		if (UmlViewsRepository.General.behavior == event.getAffectedEditor()) {
			port.setIsBehavior((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.General.service == event.getAffectedEditor()) {
			port.setIsService((Boolean)event.getNewValue());
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		super.updatePart(msg);
		if (editingPart.isVisible()) {
			GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.name)) {
				if (msg.getNewValue() != null) {
					generalPart.setName(EcoreUtil.convertToString(TypesPackage.Literals.STRING, msg.getNewValue()));
				} else {
					generalPart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.General.visibility))
				generalPart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getFeature_IsStatic().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.Qualifiers.static_))
				generalPart.setStatic_((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.Qualifiers.leaf))
				generalPart.setLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getOperation_IsOrdered().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.Qualifiers.ordered))
				generalPart.setOrdered((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getOperation_IsUnique().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.Qualifiers.unique))
				generalPart.setUnique((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.readOnly))
				generalPart.setReadOnly((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getProperty_IsDerived().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.derived))
				generalPart.setDerived((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getProperty_IsDerivedUnion().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.derivedUnion))
				generalPart.setDerivedUnion((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getProperty_Aggregation().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.General.aggregation))
				generalPart.setAggregation((AggregationKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getOperation_Type().equals(msg.getFeature()) && generalPart != null && isAccessible(UmlViewsRepository.General.type))
				generalPart.setType((EObject)msg.getNewValue());
			// FIXME INVALID CASE INTO template public liveUpdater(editionElement : PropertiesEditionElement, view : View, pec : PropertiesEditionComponent) in widgetControl.mtl module, with the values : upperValue, General, Port.
			// FIXME INVALID CASE INTO template public liveUpdater(editionElement : PropertiesEditionElement, view : View, pec : PropertiesEditionComponent) in widgetControl.mtl module, with the values : lowerValue, General, Port.
			// FIXME INVALID CASE INTO template public liveUpdater(editionElement : PropertiesEditionElement, view : View, pec : PropertiesEditionComponent) in widgetControl.mtl module, with the values : defaultValue, General, Port.
			if (UMLPackage.eINSTANCE.getPort_IsBehavior().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.behavior))
				generalPart.setBehavior((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getPort_IsService().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.service))
				generalPart.setService((Boolean)msg.getNewValue());
			
			
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
	 */
	@Override
	protected NotificationFilter[] getNotificationFilters() {
		NotificationFilter filter = new EStructuralFeatureNotificationFilter(
			UMLPackage.eINSTANCE.getNamedElement_Name(),
			UMLPackage.eINSTANCE.getNamedElement_Visibility(),
			UMLPackage.eINSTANCE.getFeature_IsStatic(),
			UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(),
			UMLPackage.eINSTANCE.getOperation_IsOrdered(),
			UMLPackage.eINSTANCE.getOperation_IsUnique(),
			UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly(),
			UMLPackage.eINSTANCE.getProperty_IsDerived(),
			UMLPackage.eINSTANCE.getProperty_IsDerivedUnion(),
			UMLPackage.eINSTANCE.getProperty_Aggregation(),
			UMLPackage.eINSTANCE.getOperation_Type(),
			UMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
			UMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
			UMLPackage.eINSTANCE.getProperty_DefaultValue(),
			UMLPackage.eINSTANCE.getPort_IsBehavior(),
			UMLPackage.eINSTANCE.getPort_IsService()		);
		return new NotificationFilter[] {filter,};
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#mustBeComposed(java.lang.Object, int)
	 */
	public boolean mustBeComposed(Object key, int kind) {
		return key == UmlViewsRepository.General.name || key == UmlViewsRepository.General.visibility || key == UmlViewsRepository.General.Qualifiers.static_ || key == UmlViewsRepository.General.Qualifiers.leaf || key == UmlViewsRepository.General.Qualifiers.ordered || key == UmlViewsRepository.General.Qualifiers.unique || key == UmlViewsRepository.General.readOnly || key == UmlViewsRepository.General.derived || key == UmlViewsRepository.General.derivedUnion || key == UmlViewsRepository.General.aggregation || key == UmlViewsRepository.General.type || key == UmlViewsRepository.General.upperValue || key == UmlViewsRepository.General.lowerValue || key == UmlViewsRepository.General.defaultValue || key == UmlViewsRepository.General.behavior || key == UmlViewsRepository.General.service || key == UmlViewsRepository.General.Qualifiers.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.General.Qualifiers.static_ || key == UmlViewsRepository.General.Qualifiers.leaf || key == UmlViewsRepository.General.Qualifiers.ordered || key == UmlViewsRepository.General.Qualifiers.unique || key == UmlViewsRepository.General.readOnly || key == UmlViewsRepository.General.derived || key == UmlViewsRepository.General.derivedUnion || key == UmlViewsRepository.General.aggregation || key == UmlViewsRepository.General.behavior || key == UmlViewsRepository.General.service;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
		Diagnostic ret = Diagnostic.OK_INSTANCE;
		if (event.getNewValue() != null) {
			try {
				if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.General.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.General.Qualifiers.static_ == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.General.Qualifiers.leaf == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.General.Qualifiers.ordered == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getOperation_IsOrdered().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getOperation_IsOrdered().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.General.Qualifiers.unique == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getOperation_IsUnique().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getOperation_IsUnique().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.General.readOnly == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.General.derived == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getProperty_IsDerived().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getProperty_IsDerived().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.General.derivedUnion == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getProperty_IsDerivedUnion().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getProperty_IsDerivedUnion().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.General.aggregation == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getProperty_Aggregation().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getProperty_Aggregation().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.General.behavior == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getPort_IsBehavior().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getPort_IsBehavior().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.General.service == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getPort_IsService().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getPort_IsService().getEAttributeType(), newValue);
				}
			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}


	

}
