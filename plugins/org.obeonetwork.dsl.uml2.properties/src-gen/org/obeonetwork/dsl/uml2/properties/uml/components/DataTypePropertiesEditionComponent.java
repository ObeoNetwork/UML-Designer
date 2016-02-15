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

import org.eclipse.uml2.uml.DataType;

import org.obeonetwork.dsl.uml2.properties.uml.parts.AttributesPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.OperationsPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class DataTypePropertiesEditionComponent extends ComposedPropertiesEditionComponent {

	/**
	 * The General part
	 * 
	 * @generated
	 */
	private GeneralPropertiesEditionPart generalPart;

	/**
	 * The DataTypeGeneralPropertiesEditionComponent sub component
	 * 
	 * @generated
	 */
	protected DataTypeGeneralPropertiesEditionComponent dataTypeGeneralPropertiesEditionComponent;

	/**
	 * The Attributes part
	 * 
	 * @generated
	 */
	private AttributesPropertiesEditionPart attributesPart;

	/**
	 * The DataTypeAttributesPropertiesEditionComponent sub component
	 * 
	 * @generated
	 */
	protected DataTypeAttributesPropertiesEditionComponent dataTypeAttributesPropertiesEditionComponent;

	/**
	 * The Operations part
	 * 
	 * @generated
	 */
	private OperationsPropertiesEditionPart operationsPart;

	/**
	 * The DataTypeOperationsPropertiesEditionComponent sub component
	 * 
	 * @generated
	 */
	protected DataTypeOperationsPropertiesEditionComponent dataTypeOperationsPropertiesEditionComponent;

	/**
	 * Parameterized constructor
	 * 
	 * @param dataType
	 *            the EObject to edit
	 * @generated
	 */
	public DataTypePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject dataType,
			String editing_mode) {
		super(editingContext, editing_mode);
		if (dataType instanceof DataType) {
			PropertiesEditingProvider provider = null;
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(dataType,
					PropertiesEditingProvider.class);
			dataTypeGeneralPropertiesEditionComponent = (DataTypeGeneralPropertiesEditionComponent)provider
					.getPropertiesEditingComponent(editingContext, editing_mode,
							DataTypeGeneralPropertiesEditionComponent.GENERAL_PART,
							DataTypeGeneralPropertiesEditionComponent.class);
			addSubComponent(dataTypeGeneralPropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(dataType,
					PropertiesEditingProvider.class);
			dataTypeAttributesPropertiesEditionComponent = (DataTypeAttributesPropertiesEditionComponent)provider
					.getPropertiesEditingComponent(editingContext, editing_mode,
							DataTypeAttributesPropertiesEditionComponent.ATTRIBUTES_PART,
							DataTypeAttributesPropertiesEditionComponent.class);
			addSubComponent(dataTypeAttributesPropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(dataType,
					PropertiesEditingProvider.class);
			dataTypeOperationsPropertiesEditionComponent = (DataTypeOperationsPropertiesEditionComponent)provider
					.getPropertiesEditingComponent(editingContext, editing_mode,
							DataTypeOperationsPropertiesEditionComponent.OPERATIONS_PART,
							DataTypeOperationsPropertiesEditionComponent.class);
			addSubComponent(dataTypeOperationsPropertiesEditionComponent);
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
		if (DataTypeGeneralPropertiesEditionComponent.GENERAL_PART.equals(key)) {
			generalPart = (GeneralPropertiesEditionPart)dataTypeGeneralPropertiesEditionComponent
					.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)generalPart;
		}
		if (DataTypeAttributesPropertiesEditionComponent.ATTRIBUTES_PART.equals(key)) {
			attributesPart = (AttributesPropertiesEditionPart)dataTypeAttributesPropertiesEditionComponent
					.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)attributesPart;
		}
		if (DataTypeOperationsPropertiesEditionComponent.OPERATIONS_PART.equals(key)) {
			operationsPart = (OperationsPropertiesEditionPart)dataTypeOperationsPropertiesEditionComponent
					.getPropertiesEditionPart(kind, key);
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
	public void setPropertiesEditionPart(java.lang.Object key, int kind,
			IPropertiesEditionPart propertiesEditionPart) {
		if (UmlViewsRepository.General.class == key) {
			super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
			generalPart = (GeneralPropertiesEditionPart)propertiesEditionPart;
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
		if (key == UmlViewsRepository.Attributes.class) {
			super.initPart(key, kind, element, allResource);
		}
		if (key == UmlViewsRepository.Operations.class) {
			super.initPart(key, kind, element, allResource);
		}
	}
}
