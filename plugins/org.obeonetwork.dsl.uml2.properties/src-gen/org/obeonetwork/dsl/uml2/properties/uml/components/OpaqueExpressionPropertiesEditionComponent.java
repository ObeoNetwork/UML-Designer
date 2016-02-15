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

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
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

import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.uml2.types.TypesPackage;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class OpaqueExpressionPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */

	public static String GENERAL_PART = "General"; //$NON-NLS-1$

	/**
	 * Settings for behavior EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings behaviorSettings;

	/**
	 * Default constructor
	 * 
	 * @generated
	 */
	public OpaqueExpressionPropertiesEditionComponent(PropertiesEditingContext editingContext,
			EObject opaqueExpression, String editing_mode) {
		super(editingContext, opaqueExpression, editing_mode);
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

			final OpaqueExpression opaqueExpression = (OpaqueExpression)elt;
			final GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.General.name))
				generalPart.setName(EEFConverterUtil.convertToString(TypesPackage.Literals.STRING,
						opaqueExpression.getName()));

			if (isAccessible(UmlViewsRepository.General.visibility)) {
				generalPart.initVisibility(
						EEFUtils.choiceOfValues(opaqueExpression,
								UMLPackage.eINSTANCE.getNamedElement_Visibility()),
						opaqueExpression.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.General.behaviour)) {
				// init part
				behaviorSettings = new EObjectFlatComboSettings(opaqueExpression,
						UMLPackage.eINSTANCE.getOpaqueExpression_Behavior());
				generalPart.initBehaviour(behaviorSettings);
				// set the button mode
				generalPart.setBehaviourButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.General.language))
				generalPart.setLanguage(opaqueExpression.getLanguages());

			if (isAccessible(UmlViewsRepository.General.body))
				generalPart.setBody(opaqueExpression.getBodies());

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
		if (editorKey == UmlViewsRepository.General.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.General.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.General.behaviour) {
			return UMLPackage.eINSTANCE.getOpaqueExpression_Behavior();
		}
		if (editorKey == UmlViewsRepository.General.language) {
			return UMLPackage.eINSTANCE.getOpaqueExpression_Language();
		}
		if (editorKey == UmlViewsRepository.General.body) {
			return UMLPackage.eINSTANCE.getOpaqueExpression_Body();
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
		OpaqueExpression opaqueExpression = (OpaqueExpression)semanticObject;
		if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
			opaqueExpression.setName((java.lang.String)EEFConverterUtil
					.createFromString(TypesPackage.Literals.STRING, (String)event.getNewValue()));
		}
		if (UmlViewsRepository.General.visibility == event.getAffectedEditor()) {
			opaqueExpression.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.General.behaviour == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				behaviorSettings.setToReference((Behavior)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(
						editingContext, this, behaviorSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext
						.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.General.language == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				opaqueExpression.getLanguages().clear();
				opaqueExpression.getLanguages().addAll(((EList)event.getNewValue()));
			}
		}
		if (UmlViewsRepository.General.body == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				opaqueExpression.getBodies().clear();
				opaqueExpression.getBodies().addAll(((EList)event.getNewValue()));
			}
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

			if (UMLPackage.eINSTANCE.getOpaqueExpression_Behavior().equals(msg.getFeature())
					&& generalPart != null && isAccessible(UmlViewsRepository.General.behaviour))
				generalPart.setBehaviour((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getOpaqueExpression_Language().equals(msg.getFeature())
					&& msg.getNotifier().equals(semanticObject) && generalPart != null
					&& isAccessible(UmlViewsRepository.General.language)) {
				if (msg.getNewValue() instanceof EList<?>) {
					generalPart.setLanguage((EList<?>)msg.getNewValue());
				} else if (msg.getNewValue() == null) {
					generalPart.setLanguage(new BasicEList<Object>());
				} else {
					BasicEList<Object> newValueAsList = new BasicEList<Object>();
					newValueAsList.add(msg.getNewValue());
					generalPart.setLanguage(newValueAsList);
				}
			}

			if (UMLPackage.eINSTANCE.getOpaqueExpression_Body().equals(msg.getFeature())
					&& msg.getNotifier().equals(semanticObject) && generalPart != null
					&& isAccessible(UmlViewsRepository.General.body)) {
				if (msg.getNewValue() instanceof EList<?>) {
					generalPart.setBody((EList<?>)msg.getNewValue());
				} else if (msg.getNewValue() == null) {
					generalPart.setBody(new BasicEList<Object>());
				} else {
					BasicEList<Object> newValueAsList = new BasicEList<Object>();
					newValueAsList.add(msg.getNewValue());
					generalPart.setBody(newValueAsList);
				}
			}

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
				UMLPackage.eINSTANCE.getOpaqueExpression_Behavior(),
				UMLPackage.eINSTANCE.getOpaqueExpression_Language(),
				UMLPackage.eINSTANCE.getOpaqueExpression_Body());
		return new NotificationFilter[] {filter,};
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#mustBeComposed(java.lang.Object,
	 *      int)
	 */
	public boolean mustBeComposed(Object key, int kind) {
		return key == UmlViewsRepository.General.name || key == UmlViewsRepository.General.visibility
				|| key == UmlViewsRepository.General.behaviour || key == UmlViewsRepository.General.language
				|| key == UmlViewsRepository.General.body;
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
				if (UmlViewsRepository.General.language == event.getAffectedEditor()) {
					BasicDiagnostic chain = new BasicDiagnostic();
					for (Iterator iterator = ((List)event.getNewValue()).iterator(); iterator.hasNext();) {
						chain.add(Diagnostician.INSTANCE.validate(
								UMLPackage.eINSTANCE.getOpaqueExpression_Language().getEAttributeType(),
								iterator.next()));
					}
					ret = chain;
				}
				if (UmlViewsRepository.General.body == event.getAffectedEditor()) {
					BasicDiagnostic chain = new BasicDiagnostic();
					for (Iterator iterator = ((List)event.getNewValue()).iterator(); iterator.hasNext();) {
						chain.add(Diagnostician.INSTANCE.validate(
								UMLPackage.eINSTANCE.getOpaqueExpression_Body().getEAttributeType(),
								iterator.next()));
					}
					ret = chain;
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
