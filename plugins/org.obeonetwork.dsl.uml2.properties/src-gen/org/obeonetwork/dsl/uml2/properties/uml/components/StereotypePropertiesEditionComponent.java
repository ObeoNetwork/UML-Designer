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

import org.eclipse.uml2.uml.Stereotype;

import org.obeonetwork.dsl.uml2.properties.uml.parts.AttributesPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.OperationsPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class StereotypePropertiesEditionComponent extends ComposedPropertiesEditionComponent {

	/**
	 * The General part
	 * @generated
	 */
	private GeneralPropertiesEditionPart generalPart;

	/**
	 * The StereotypeGeneralPropertiesEditionComponent sub component
	 * @generated
	 */
	protected StereotypeGeneralPropertiesEditionComponent stereotypeGeneralPropertiesEditionComponent;

	/**
	 * The Operations part
	 * @generated
	 */
	private OperationsPropertiesEditionPart operationsPart;

	/**
	 * The StereotypeOperationsPropertiesEditionComponent sub component
	 * @generated
	 */
	protected StereotypeOperationsPropertiesEditionComponent stereotypeOperationsPropertiesEditionComponent;

	/**
	 * The Attributes part
	 * @generated
	 */
	private AttributesPropertiesEditionPart attributesPart;

	/**
	 * The StereotypeAttributesPropertiesEditionComponent sub component
	 * @generated
	 */
	protected StereotypeAttributesPropertiesEditionComponent stereotypeAttributesPropertiesEditionComponent;

	/**
	 * Parameterized constructor
	 * 
	 * @param stereotype the EObject to edit
	 * @generated
	 */
	public StereotypePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject stereotype, String editing_mode) {
    super(editingContext, editing_mode);
    if (stereotype instanceof Stereotype) {
      PropertiesEditingProvider provider = null;
      provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(stereotype, PropertiesEditingProvider.class);
      stereotypeGeneralPropertiesEditionComponent = (StereotypeGeneralPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, StereotypeGeneralPropertiesEditionComponent.GENERAL_PART, StereotypeGeneralPropertiesEditionComponent.class);
      addSubComponent(stereotypeGeneralPropertiesEditionComponent);
      provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(stereotype, PropertiesEditingProvider.class);
      stereotypeOperationsPropertiesEditionComponent = (StereotypeOperationsPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, StereotypeOperationsPropertiesEditionComponent.OPERATIONS_PART, StereotypeOperationsPropertiesEditionComponent.class);
      addSubComponent(stereotypeOperationsPropertiesEditionComponent);
      provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(stereotype, PropertiesEditingProvider.class);
      stereotypeAttributesPropertiesEditionComponent = (StereotypeAttributesPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, StereotypeAttributesPropertiesEditionComponent.ATTRIBUTES_PART, StereotypeAttributesPropertiesEditionComponent.class);
      addSubComponent(stereotypeAttributesPropertiesEditionComponent);
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
    if (StereotypeGeneralPropertiesEditionComponent.GENERAL_PART.equals(key)) {
      generalPart = (GeneralPropertiesEditionPart)stereotypeGeneralPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
      return (IPropertiesEditionPart)generalPart;
    }
    if (StereotypeOperationsPropertiesEditionComponent.OPERATIONS_PART.equals(key)) {
      operationsPart = (OperationsPropertiesEditionPart)stereotypeOperationsPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
      return (IPropertiesEditionPart)operationsPart;
    }
    if (StereotypeAttributesPropertiesEditionComponent.ATTRIBUTES_PART.equals(key)) {
      attributesPart = (AttributesPropertiesEditionPart)stereotypeAttributesPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
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
    if (key == UmlViewsRepository.Operations.class) {
      super.initPart(key, kind, element, allResource);
    }
    if (key == UmlViewsRepository.Attributes.class) {
      super.initPart(key, kind, element, allResource);
    }
  }
}
