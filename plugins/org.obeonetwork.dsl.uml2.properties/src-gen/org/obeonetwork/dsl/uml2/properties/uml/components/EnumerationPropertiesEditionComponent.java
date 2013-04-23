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

import org.eclipse.uml2.uml.Enumeration;

import org.obeonetwork.dsl.uml2.properties.uml.parts.AttributesPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.LiteralsPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.OperationsPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class EnumerationPropertiesEditionComponent extends ComposedPropertiesEditionComponent {

	/**
	 * The General part
	 * @generated
	 */
	private GeneralPropertiesEditionPart generalPart;

	/**
	 * The EnumerationGeneralPropertiesEditionComponent sub component
	 * @generated
	 */
	protected EnumerationGeneralPropertiesEditionComponent enumerationGeneralPropertiesEditionComponent;

	/**
	 * The Literals part
	 * @generated
	 */
	private LiteralsPropertiesEditionPart literalsPart;

	/**
	 * The EnumerationLiteralsPropertiesEditionComponent sub component
	 * @generated
	 */
	protected EnumerationLiteralsPropertiesEditionComponent enumerationLiteralsPropertiesEditionComponent;

	/**
	 * The Attributes part
	 * @generated
	 */
	private AttributesPropertiesEditionPart attributesPart;

	/**
	 * The EnumerationAttributesPropertiesEditionComponent sub component
	 * @generated
	 */
	protected EnumerationAttributesPropertiesEditionComponent enumerationAttributesPropertiesEditionComponent;

	/**
	 * The Operations part
	 * @generated
	 */
	private OperationsPropertiesEditionPart operationsPart;

	/**
	 * The EnumerationOperationsPropertiesEditionComponent sub component
	 * @generated
	 */
	protected EnumerationOperationsPropertiesEditionComponent enumerationOperationsPropertiesEditionComponent;

	/**
	 * Parameterized constructor
	 * 
	 * @param enumeration the EObject to edit
	 * @generated
	 */
	public EnumerationPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject enumeration, String editing_mode) {
    super(editingContext, editing_mode);
    if (enumeration instanceof Enumeration) {
      PropertiesEditingProvider provider = null;
      provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(enumeration, PropertiesEditingProvider.class);
      enumerationGeneralPropertiesEditionComponent = (EnumerationGeneralPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, EnumerationGeneralPropertiesEditionComponent.GENERAL_PART, EnumerationGeneralPropertiesEditionComponent.class);
      addSubComponent(enumerationGeneralPropertiesEditionComponent);
      provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(enumeration, PropertiesEditingProvider.class);
      enumerationLiteralsPropertiesEditionComponent = (EnumerationLiteralsPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, EnumerationLiteralsPropertiesEditionComponent.LITERALS_PART, EnumerationLiteralsPropertiesEditionComponent.class);
      addSubComponent(enumerationLiteralsPropertiesEditionComponent);
      provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(enumeration, PropertiesEditingProvider.class);
      enumerationAttributesPropertiesEditionComponent = (EnumerationAttributesPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, EnumerationAttributesPropertiesEditionComponent.ATTRIBUTES_PART, EnumerationAttributesPropertiesEditionComponent.class);
      addSubComponent(enumerationAttributesPropertiesEditionComponent);
      provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(enumeration, PropertiesEditingProvider.class);
      enumerationOperationsPropertiesEditionComponent = (EnumerationOperationsPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, EnumerationOperationsPropertiesEditionComponent.OPERATIONS_PART, EnumerationOperationsPropertiesEditionComponent.class);
      addSubComponent(enumerationOperationsPropertiesEditionComponent);
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
    if (EnumerationGeneralPropertiesEditionComponent.GENERAL_PART.equals(key)) {
      generalPart = (GeneralPropertiesEditionPart)enumerationGeneralPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
      return (IPropertiesEditionPart)generalPart;
    }
    if (EnumerationLiteralsPropertiesEditionComponent.LITERALS_PART.equals(key)) {
      literalsPart = (LiteralsPropertiesEditionPart)enumerationLiteralsPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
      return (IPropertiesEditionPart)literalsPart;
    }
    if (EnumerationAttributesPropertiesEditionComponent.ATTRIBUTES_PART.equals(key)) {
      attributesPart = (AttributesPropertiesEditionPart)enumerationAttributesPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
      return (IPropertiesEditionPart)attributesPart;
    }
    if (EnumerationOperationsPropertiesEditionComponent.OPERATIONS_PART.equals(key)) {
      operationsPart = (OperationsPropertiesEditionPart)enumerationOperationsPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
      return (IPropertiesEditionPart)operationsPart;
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
    if (UmlViewsRepository.Literals.class == key) {
      super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
      literalsPart = (LiteralsPropertiesEditionPart)propertiesEditionPart;
    }
    if (UmlViewsRepository.Attributes.class == key) {
      super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
      attributesPart = (AttributesPropertiesEditionPart)propertiesEditionPart;
    }
    if (UmlViewsRepository.Operations.class == key) {
      super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
      operationsPart = (OperationsPropertiesEditionPart)propertiesEditionPart;
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
    if (key == UmlViewsRepository.Literals.class) {
      super.initPart(key, kind, element, allResource);
    }
    if (key == UmlViewsRepository.Attributes.class) {
      super.initPart(key, kind, element, allResource);
    }
    if (key == UmlViewsRepository.Operations.class) {
      super.initPart(key, kind, element, allResource);
    }
  }
}
