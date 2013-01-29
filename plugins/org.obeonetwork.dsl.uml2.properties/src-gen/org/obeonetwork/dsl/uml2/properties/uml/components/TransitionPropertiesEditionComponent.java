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

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;

import org.eclipse.emf.eef.runtime.impl.components.ComposedPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.uml2.uml.Transition;

import org.obeonetwork.dsl.uml2.properties.uml.parts.DocumentationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class TransitionPropertiesEditionComponent extends ComposedPropertiesEditionComponent {

	/**
	 * The General part
	 * @generated
	 */
	private GeneralPropertiesEditionPart generalPart;

	/**
	 * The TransitionGeneralPropertiesEditionComponent sub component
	 * @generated
	 */
	protected TransitionGeneralPropertiesEditionComponent transitionGeneralPropertiesEditionComponent;

	/**
	 * The Documentation part
	 * @generated
	 */
	private DocumentationPropertiesEditionPart documentationPart;

	/**
	 * The TransitionDocumentationPropertiesEditionComponent sub component
	 * @generated
	 */
	protected TransitionDocumentationPropertiesEditionComponent transitionDocumentationPropertiesEditionComponent;

	/**
	 * Parameterized constructor
	 * 
	 * @param transition the EObject to edit
	 * @generated
	 */
	public TransitionPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject transition, String editing_mode) {
		super(editingContext, editing_mode);
		if (transition instanceof Transition) {
			PropertiesEditingProvider provider = null;
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(transition, PropertiesEditingProvider.class);
			transitionGeneralPropertiesEditionComponent = (TransitionGeneralPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, TransitionGeneralPropertiesEditionComponent.GENERAL_PART, TransitionGeneralPropertiesEditionComponent.class);
			addSubComponent(transitionGeneralPropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(transition, PropertiesEditingProvider.class);
			transitionDocumentationPropertiesEditionComponent = (TransitionDocumentationPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, TransitionDocumentationPropertiesEditionComponent.DOCUMENTATION_PART, TransitionDocumentationPropertiesEditionComponent.class);
			addSubComponent(transitionDocumentationPropertiesEditionComponent);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.ComposedPropertiesEditionComponent#
	 *      getPropertiesEditionPart(int, java.lang.String)
	 * @generated
	 */
	public IPropertiesEditionPart getPropertiesEditionPart(int kind, String key) {
		if (TransitionGeneralPropertiesEditionComponent.GENERAL_PART.equals(key)) {
			generalPart = (GeneralPropertiesEditionPart)transitionGeneralPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)generalPart;
		}
		if (TransitionDocumentationPropertiesEditionComponent.DOCUMENTATION_PART.equals(key)) {
			documentationPart = (DocumentationPropertiesEditionPart)transitionDocumentationPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)documentationPart;
		}
		return super.getPropertiesEditionPart(kind, key);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.ComposedPropertiesEditionComponent#
	 *      setPropertiesEditionPart(java.lang.Object, int,
	 *      org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart)
	 * @generated
	 */
	public void setPropertiesEditionPart(java.lang.Object key, int kind, IPropertiesEditionPart propertiesEditionPart) {
		if (UmlViewsRepository.General.class == key) {
			super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
			generalPart = (GeneralPropertiesEditionPart)propertiesEditionPart;
		}
		if (UmlViewsRepository.Documentation.class == key) {
			super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
			documentationPart = (DocumentationPropertiesEditionPart)propertiesEditionPart;
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.ComposedPropertiesEditionComponent#
	 *      initPart(java.lang.Object, int, org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * @generated
	 */
	public void initPart(java.lang.Object key, int kind, EObject element, ResourceSet allResource) {
		if (key == UmlViewsRepository.General.class) {
			super.initPart(key, kind, element, allResource);
		}
		if (key == UmlViewsRepository.Documentation.class) {
			super.initPart(key, kind, element, allResource);
		}
	}
}
