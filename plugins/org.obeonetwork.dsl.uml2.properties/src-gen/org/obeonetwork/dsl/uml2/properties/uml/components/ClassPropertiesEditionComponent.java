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

import org.obeonetwork.dsl.uml2.properties.uml.parts.AttributesPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.DocumentationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.OperationsPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ClassPropertiesEditionComponent extends ComposedPropertiesEditionComponent {

	/**
	 * The General part
	 * @generated
	 */
	private GeneralPropertiesEditionPart generalPart;

	/**
	 * The ClassGeneralPropertiesEditionComponent sub component
	 * @generated
	 */
	protected ClassGeneralPropertiesEditionComponent classGeneralPropertiesEditionComponent;

	/**
	 * The Documentation part
	 * @generated
	 */
	private DocumentationPropertiesEditionPart documentationPart;

	/**
	 * The ClassDocumentationPropertiesEditionComponent sub component
	 * @generated
	 */
	protected ClassDocumentationPropertiesEditionComponent classDocumentationPropertiesEditionComponent;

	/**
	 * The Operations part
	 * @generated
	 */
	private OperationsPropertiesEditionPart operationsPart;

	/**
	 * The ClassOperationsPropertiesEditionComponent sub component
	 * @generated
	 */
	protected ClassOperationsPropertiesEditionComponent classOperationsPropertiesEditionComponent;

	/**
	 * The Attributes part
	 * @generated
	 */
	private AttributesPropertiesEditionPart attributesPart;

	/**
	 * The ClassAttributesPropertiesEditionComponent sub component
	 * @generated
	 */
	protected ClassAttributesPropertiesEditionComponent classAttributesPropertiesEditionComponent;

	/**
	 * Parameterized constructor
	 * 
	 * @param class_ the EObject to edit
	 * @generated
	 */
	public ClassPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject class_, String editing_mode) {
		super(editingContext, editing_mode);
		if (class_ instanceof Class) {
			PropertiesEditingProvider provider = null;
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(class_, PropertiesEditingProvider.class);
			classGeneralPropertiesEditionComponent = (ClassGeneralPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, ClassGeneralPropertiesEditionComponent.GENERAL_PART, ClassGeneralPropertiesEditionComponent.class);
			addSubComponent(classGeneralPropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(class_, PropertiesEditingProvider.class);
			classDocumentationPropertiesEditionComponent = (ClassDocumentationPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, ClassDocumentationPropertiesEditionComponent.DOCUMENTATION_PART, ClassDocumentationPropertiesEditionComponent.class);
			addSubComponent(classDocumentationPropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(class_, PropertiesEditingProvider.class);
			classOperationsPropertiesEditionComponent = (ClassOperationsPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, ClassOperationsPropertiesEditionComponent.OPERATIONS_PART, ClassOperationsPropertiesEditionComponent.class);
			addSubComponent(classOperationsPropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(class_, PropertiesEditingProvider.class);
			classAttributesPropertiesEditionComponent = (ClassAttributesPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, ClassAttributesPropertiesEditionComponent.ATTRIBUTES_PART, ClassAttributesPropertiesEditionComponent.class);
			addSubComponent(classAttributesPropertiesEditionComponent);
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
		if (ClassGeneralPropertiesEditionComponent.GENERAL_PART.equals(key)) {
			generalPart = (GeneralPropertiesEditionPart)classGeneralPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)generalPart;
		}
		if (ClassDocumentationPropertiesEditionComponent.DOCUMENTATION_PART.equals(key)) {
			documentationPart = (DocumentationPropertiesEditionPart)classDocumentationPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)documentationPart;
		}
		if (ClassOperationsPropertiesEditionComponent.OPERATIONS_PART.equals(key)) {
			operationsPart = (OperationsPropertiesEditionPart)classOperationsPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)operationsPart;
		}
		if (ClassAttributesPropertiesEditionComponent.ATTRIBUTES_PART.equals(key)) {
			attributesPart = (AttributesPropertiesEditionPart)classAttributesPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)attributesPart;
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
		if (UmlViewsRepository.Operations.class == key) {
			super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
			operationsPart = (OperationsPropertiesEditionPart)propertiesEditionPart;
		}
		if (UmlViewsRepository.Attributes.class == key) {
			super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
			attributesPart = (AttributesPropertiesEditionPart)propertiesEditionPart;
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
		if (key == UmlViewsRepository.Operations.class) {
			super.initPart(key, kind, element, allResource);
		}
		if (key == UmlViewsRepository.Attributes.class) {
			super.initPart(key, kind, element, allResource);
		}
	}
}
