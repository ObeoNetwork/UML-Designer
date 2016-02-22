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

import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.UMLPackage;

import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ConnectorEndPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */

	public static String GENERAL_PART = "General"; //$NON-NLS-1$

	/**
	 * Settings for role EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings roleSettings;

	/**
	 * Default constructor
	 * 
	 * @generated
	 */
	public ConnectorEndPropertiesEditionComponent(PropertiesEditingContext editingContext,
			EObject connectorEnd, String editing_mode) {
		super(editingContext, connectorEnd, editing_mode);
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

			final ConnectorEnd connectorEnd = (ConnectorEnd)elt;
			final GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart)editingPart;
			// init values
			generalPart.setOrdered(connectorEnd.isOrdered());

			generalPart.setUnique(connectorEnd.isUnique());

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
		if (editorKey == UmlViewsRepository.General.Qualifiers.ordered) {
			return UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered();
		}
		if (editorKey == UmlViewsRepository.General.Qualifiers.unique) {
			return UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique();
		}
		if (editorKey == UmlViewsRepository.General.role) {
			return UMLPackage.eINSTANCE.getConnectorEnd_Role();
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
		ConnectorEnd connectorEnd = (ConnectorEnd)semanticObject;
		if (UmlViewsRepository.General.Qualifiers.ordered == event.getAffectedEditor()) {
			connectorEnd.setIsOrdered((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.General.Qualifiers.unique == event.getAffectedEditor()) {
			connectorEnd.setIsUnique((Boolean)event.getNewValue());
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
			if (UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().equals(msg.getFeature())
					&& msg.getNotifier().equals(semanticObject) && generalPart != null
					&& isAccessible(UmlViewsRepository.General.Qualifiers.ordered))
				generalPart.setOrdered((Boolean)msg.getNewValue());

			if (UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().equals(msg.getFeature())
					&& msg.getNotifier().equals(semanticObject) && generalPart != null
					&& isAccessible(UmlViewsRepository.General.Qualifiers.unique))
				generalPart.setUnique((Boolean)msg.getNewValue());

			if (UMLPackage.eINSTANCE.getConnectorEnd_Role().equals(msg.getFeature()) && generalPart != null
					&& isAccessible(UmlViewsRepository.General.role))
				generalPart.setRole((EObject)msg.getNewValue());

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
				UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(),
				UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(),
				UMLPackage.eINSTANCE.getConnectorEnd_Role());
		return new NotificationFilter[] {filter,};
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#mustBeComposed(java.lang.Object,
	 *      int)
	 */
	public boolean mustBeComposed(Object key, int kind) {
		return key == UmlViewsRepository.General.Qualifiers.ordered
				|| key == UmlViewsRepository.General.Qualifiers.unique
				|| key == UmlViewsRepository.General.role
				|| key == UmlViewsRepository.General.Qualifiers.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object,
	 *      int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.General.Qualifiers.ordered
				|| key == UmlViewsRepository.General.Qualifiers.unique;
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
				if (UmlViewsRepository.General.Qualifiers.ordered == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(
								UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().getEAttributeType(),
								(String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(
							UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().getEAttributeType(),
							newValue);
				}
				if (UmlViewsRepository.General.Qualifiers.unique == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(
								UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().getEAttributeType(),
								(String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(
							UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().getEAttributeType(),
							newValue);
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
