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

import org.eclipse.uml2.uml.EnumerationLiteral;

import org.obeonetwork.dsl.uml2.properties.uml.parts.DocumentationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class EnumerationLiteralPropertiesEditionComponent extends ComposedPropertiesEditionComponent {

	/**
	 * The General part
	 * @generated
	 */
	private GeneralPropertiesEditionPart generalPart;

	/**
	 * The EnumerationLiteralGeneralPropertiesEditionComponent sub component
	 * @generated
	 */
	protected EnumerationLiteralGeneralPropertiesEditionComponent enumerationLiteralGeneralPropertiesEditionComponent;

	/**
	 * The Documentation part
	 * @generated
	 */
	private DocumentationPropertiesEditionPart documentationPart;

	/**
	 * The EnumerationLiteralDocumentationPropertiesEditionComponent sub component
	 * @generated
	 */
	protected EnumerationLiteralDocumentationPropertiesEditionComponent enumerationLiteralDocumentationPropertiesEditionComponent;

	/**
	 * Parameterized constructor
	 * 
	 * @param enumerationLiteral the EObject to edit
	 * @generated
	 */
	public EnumerationLiteralPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject enumerationLiteral, String editing_mode) {
		super(editingContext, editing_mode);
		if (enumerationLiteral instanceof EnumerationLiteral) {
			PropertiesEditingProvider provider = null;
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(enumerationLiteral, PropertiesEditingProvider.class);
			enumerationLiteralGeneralPropertiesEditionComponent = (EnumerationLiteralGeneralPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, EnumerationLiteralGeneralPropertiesEditionComponent.GENERAL_PART, EnumerationLiteralGeneralPropertiesEditionComponent.class);
			addSubComponent(enumerationLiteralGeneralPropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(enumerationLiteral, PropertiesEditingProvider.class);
			enumerationLiteralDocumentationPropertiesEditionComponent = (EnumerationLiteralDocumentationPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, EnumerationLiteralDocumentationPropertiesEditionComponent.DOCUMENTATION_PART, EnumerationLiteralDocumentationPropertiesEditionComponent.class);
			addSubComponent(enumerationLiteralDocumentationPropertiesEditionComponent);
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
		if (EnumerationLiteralGeneralPropertiesEditionComponent.GENERAL_PART.equals(key)) {
			generalPart = (GeneralPropertiesEditionPart)enumerationLiteralGeneralPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)generalPart;
		}
		if (EnumerationLiteralDocumentationPropertiesEditionComponent.DOCUMENTATION_PART.equals(key)) {
			documentationPart = (DocumentationPropertiesEditionPart)enumerationLiteralDocumentationPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
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
