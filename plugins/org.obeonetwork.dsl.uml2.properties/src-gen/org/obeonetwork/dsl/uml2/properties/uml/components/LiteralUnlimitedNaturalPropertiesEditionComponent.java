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

import org.eclipse.uml2.types.TypesPackage;

import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class LiteralUnlimitedNaturalPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */

	public static String GENERAL_PART = "General"; //$NON-NLS-1$

	/**
	 * Default constructor
	 * 
	 * @generated
	 */
	public LiteralUnlimitedNaturalPropertiesEditionComponent(PropertiesEditingContext editingContext,
			EObject literalUnlimitedNatural, String editing_mode) {
		super(editingContext, literalUnlimitedNatural, editing_mode);
		parts = new String[] {GENERAL_PART};
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.General.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object,
	 *      int, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.resource.ResourceSet)
	 * @generated
	 */
	public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);

			final LiteralUnlimitedNatural literalUnlimitedNatural = (LiteralUnlimitedNatural)elt;
			final GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.General.integerValue)) {
				generalPart.setIntegerValue(EEFConverterUtil.convertToString(
						TypesPackage.Literals.UNLIMITED_NATURAL, literalUnlimitedNatural.getValue()));
			}

			if (isAccessible(UmlViewsRepository.General.name))
				generalPart.setName(EEFConverterUtil.convertToString(TypesPackage.Literals.STRING,
						literalUnlimitedNatural.getName()));

			if (isAccessible(UmlViewsRepository.General.visibility)) {
				generalPart.initVisibility(
						EEFUtils.choiceOfValues(literalUnlimitedNatural,
								UMLPackage.eINSTANCE.getNamedElement_Visibility()),
						literalUnlimitedNatural.getVisibility());
			}
			// init filters

			// init values for referenced views

			// init filters for referenced views

		}
		setInitializing(false);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
	 */
	public EStructuralFeature associatedFeature(Object editorKey) {
		if (editorKey == UmlViewsRepository.General.integerValue) {
			return UMLPackage.eINSTANCE.getLiteralUnlimitedNatural_Value();
		}
		if (editorKey == UmlViewsRepository.General.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.General.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		LiteralUnlimitedNatural literalUnlimitedNatural = (LiteralUnlimitedNatural)semanticObject;
		if (UmlViewsRepository.General.integerValue == event.getAffectedEditor()) {
			literalUnlimitedNatural
					.setValue((EEFConverterUtil.createIntFromString(TypesPackage.Literals.UNLIMITED_NATURAL,
							(String)event.getNewValue())));
		}
		if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
			literalUnlimitedNatural.setName((java.lang.String)EEFConverterUtil
					.createFromString(TypesPackage.Literals.STRING, (String)event.getNewValue()));
		}
		if (UmlViewsRepository.General.visibility == event.getAffectedEditor()) {
			literalUnlimitedNatural.setVisibility((VisibilityKind)event.getNewValue());
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		super.updatePart(msg);
		if (editingPart.isVisible()) {
			GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getLiteralUnlimitedNatural_Value().equals(msg.getFeature())
					&& msg.getNotifier().equals(semanticObject) && generalPart != null
					&& isAccessible(UmlViewsRepository.General.integerValue)) {
				if (msg.getNewValue() != null) {
					generalPart.setIntegerValue(EcoreUtil
							.convertToString(TypesPackage.Literals.UNLIMITED_NATURAL, msg.getNewValue()));
				} else {
					generalPart.setIntegerValue("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature())
					&& msg.getNotifier().equals(semanticObject) && generalPart != null
					&& isAccessible(UmlViewsRepository.General.name)) {
				if (msg.getNewValue() != null) {
					generalPart.setName(
							EcoreUtil.convertToString(TypesPackage.Literals.STRING, msg.getNewValue()));
				} else {
					generalPart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature())
					&& msg.getNotifier().equals(semanticObject)
					&& isAccessible(UmlViewsRepository.General.visibility))
				generalPart.setVisibility((VisibilityKind)msg.getNewValue());

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
				UMLPackage.eINSTANCE.getLiteralUnlimitedNatural_Value(),
				UMLPackage.eINSTANCE.getNamedElement_Name(),
				UMLPackage.eINSTANCE.getNamedElement_Visibility());
		return new NotificationFilter[] {filter,};
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#mustBeComposed(java.lang.Object,
	 *      int)
	 */
	public boolean mustBeComposed(Object key, int kind) {
		return key == UmlViewsRepository.General.integerValue || key == UmlViewsRepository.General.name
				|| key == UmlViewsRepository.General.visibility;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object,
	 *      int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.General.integerValue;
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
				if (UmlViewsRepository.General.integerValue == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(
								UMLPackage.eINSTANCE.getLiteralUnlimitedNatural_Value().getEAttributeType(),
								(String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(
							UMLPackage.eINSTANCE.getLiteralUnlimitedNatural_Value().getEAttributeType(),
							newValue);
				}
				if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(
								UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(),
								(String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(
							UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.General.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(
								UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(),
								(String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(
							UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
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
