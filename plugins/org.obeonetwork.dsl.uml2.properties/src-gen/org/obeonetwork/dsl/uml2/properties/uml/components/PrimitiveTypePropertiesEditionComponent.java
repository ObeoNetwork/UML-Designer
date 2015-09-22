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

import org.eclipse.uml2.uml.PrimitiveType;

import org.obeonetwork.dsl.uml2.properties.uml.parts.AttributesPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.OperationsPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class PrimitiveTypePropertiesEditionComponent extends ComposedPropertiesEditionComponent {

    /**
     * The General part
     * 
     * @generated
     */
    private GeneralPropertiesEditionPart generalPart;

    /**
     * The PrimitiveTypeGeneralPropertiesEditionComponent sub component
     * 
     * @generated
     */
    protected PrimitiveTypeGeneralPropertiesEditionComponent primitiveTypeGeneralPropertiesEditionComponent;

    /**
     * The Attributes part
     * 
     * @generated
     */
    private AttributesPropertiesEditionPart attributesPart;

    /**
     * The PrimitiveTypeAttributesPropertiesEditionComponent sub component
     * 
     * @generated
     */
    protected PrimitiveTypeAttributesPropertiesEditionComponent primitiveTypeAttributesPropertiesEditionComponent;

    /**
     * The Operations part
     * 
     * @generated
     */
    private OperationsPropertiesEditionPart operationsPart;

    /**
     * The PrimitiveTypeOperationsPropertiesEditionComponent sub component
     * 
     * @generated
     */
    protected PrimitiveTypeOperationsPropertiesEditionComponent primitiveTypeOperationsPropertiesEditionComponent;

    /**
     * Parameterized constructor
     * 
     * @param primitiveType
     *            the EObject to edit
     * @generated
     */
    public PrimitiveTypePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject primitiveType, String editing_mode) {
        super(editingContext, editing_mode);
        if (primitiveType instanceof PrimitiveType) {
            PropertiesEditingProvider provider = null;
            provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt(primitiveType, PropertiesEditingProvider.class);
            primitiveTypeGeneralPropertiesEditionComponent = (PrimitiveTypeGeneralPropertiesEditionComponent) provider.getPropertiesEditingComponent(editingContext, editing_mode,
                    PrimitiveTypeGeneralPropertiesEditionComponent.GENERAL_PART, PrimitiveTypeGeneralPropertiesEditionComponent.class);
            addSubComponent(primitiveTypeGeneralPropertiesEditionComponent);
            provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt(primitiveType, PropertiesEditingProvider.class);
            primitiveTypeAttributesPropertiesEditionComponent = (PrimitiveTypeAttributesPropertiesEditionComponent) provider.getPropertiesEditingComponent(editingContext, editing_mode,
                    PrimitiveTypeAttributesPropertiesEditionComponent.ATTRIBUTES_PART, PrimitiveTypeAttributesPropertiesEditionComponent.class);
            addSubComponent(primitiveTypeAttributesPropertiesEditionComponent);
            provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt(primitiveType, PropertiesEditingProvider.class);
            primitiveTypeOperationsPropertiesEditionComponent = (PrimitiveTypeOperationsPropertiesEditionComponent) provider.getPropertiesEditingComponent(editingContext, editing_mode,
                    PrimitiveTypeOperationsPropertiesEditionComponent.OPERATIONS_PART, PrimitiveTypeOperationsPropertiesEditionComponent.class);
            addSubComponent(primitiveTypeOperationsPropertiesEditionComponent);
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
        if (PrimitiveTypeGeneralPropertiesEditionComponent.GENERAL_PART.equals(key)) {
            generalPart = (GeneralPropertiesEditionPart) primitiveTypeGeneralPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
            return (IPropertiesEditionPart) generalPart;
        }
        if (PrimitiveTypeAttributesPropertiesEditionComponent.ATTRIBUTES_PART.equals(key)) {
            attributesPart = (AttributesPropertiesEditionPart) primitiveTypeAttributesPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
            return (IPropertiesEditionPart) attributesPart;
        }
        if (PrimitiveTypeOperationsPropertiesEditionComponent.OPERATIONS_PART.equals(key)) {
            operationsPart = (OperationsPropertiesEditionPart) primitiveTypeOperationsPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
            return (IPropertiesEditionPart) operationsPart;
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
            generalPart = (GeneralPropertiesEditionPart) propertiesEditionPart;
        }
        if (UmlViewsRepository.Attributes.class == key) {
            super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
            attributesPart = (AttributesPropertiesEditionPart) propertiesEditionPart;
        }
        if (UmlViewsRepository.Operations.class == key) {
            super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
            operationsPart = (OperationsPropertiesEditionPart) propertiesEditionPart;
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
