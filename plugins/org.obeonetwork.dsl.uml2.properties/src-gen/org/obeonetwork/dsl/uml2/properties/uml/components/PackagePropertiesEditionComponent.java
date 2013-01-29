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

import org.obeonetwork.dsl.uml2.properties.uml.parts.DocumentationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ProfilePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class PackagePropertiesEditionComponent extends ComposedPropertiesEditionComponent {

	/**
	 * The General part
	 * @generated
	 */
	private GeneralPropertiesEditionPart generalPart;

	/**
	 * The PackageGeneralPropertiesEditionComponent sub component
	 * @generated
	 */
	protected PackageGeneralPropertiesEditionComponent packageGeneralPropertiesEditionComponent;

	/**
	 * The Documentation part
	 * @generated
	 */
	private DocumentationPropertiesEditionPart documentationPart;

	/**
	 * The PackageDocumentationPropertiesEditionComponent sub component
	 * @generated
	 */
	protected PackageDocumentationPropertiesEditionComponent packageDocumentationPropertiesEditionComponent;

	/**
	 * The Profile part
	 * @generated
	 */
	private ProfilePropertiesEditionPart profilePart;

	/**
	 * The PackageProfilePropertiesEditionComponent sub component
	 * @generated
	 */
	protected PackageProfilePropertiesEditionComponent packageProfilePropertiesEditionComponent;

	/**
	 * Parameterized constructor
	 * 
	 * @param package_ the EObject to edit
	 * @generated
	 */
	public PackagePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject package_, String editing_mode) {
		super(editingContext, editing_mode);
		if (package_ instanceof Package) {
			PropertiesEditingProvider provider = null;
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(package_, PropertiesEditingProvider.class);
			packageGeneralPropertiesEditionComponent = (PackageGeneralPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, PackageGeneralPropertiesEditionComponent.GENERAL_PART, PackageGeneralPropertiesEditionComponent.class);
			addSubComponent(packageGeneralPropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(package_, PropertiesEditingProvider.class);
			packageDocumentationPropertiesEditionComponent = (PackageDocumentationPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, PackageDocumentationPropertiesEditionComponent.DOCUMENTATION_PART, PackageDocumentationPropertiesEditionComponent.class);
			addSubComponent(packageDocumentationPropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(package_, PropertiesEditingProvider.class);
			packageProfilePropertiesEditionComponent = (PackageProfilePropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, PackageProfilePropertiesEditionComponent.PROFILE_PART, PackageProfilePropertiesEditionComponent.class);
			addSubComponent(packageProfilePropertiesEditionComponent);
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
		if (PackageGeneralPropertiesEditionComponent.GENERAL_PART.equals(key)) {
			generalPart = (GeneralPropertiesEditionPart)packageGeneralPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)generalPart;
		}
		if (PackageDocumentationPropertiesEditionComponent.DOCUMENTATION_PART.equals(key)) {
			documentationPart = (DocumentationPropertiesEditionPart)packageDocumentationPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)documentationPart;
		}
		if (PackageProfilePropertiesEditionComponent.PROFILE_PART.equals(key)) {
			profilePart = (ProfilePropertiesEditionPart)packageProfilePropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)profilePart;
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
		if (UmlViewsRepository.Profile.class == key) {
			super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
			profilePart = (ProfilePropertiesEditionPart)propertiesEditionPart;
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
		if (key == UmlViewsRepository.Profile.class) {
			super.initPart(key, kind, element, allResource);
		}
	}
}
