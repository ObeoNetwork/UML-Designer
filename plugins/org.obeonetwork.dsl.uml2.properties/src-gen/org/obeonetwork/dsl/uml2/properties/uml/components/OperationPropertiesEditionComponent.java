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

import org.eclipse.uml2.uml.Operation;

import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ParametersPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class OperationPropertiesEditionComponent extends ComposedPropertiesEditionComponent {

	/**
	 * The General part
	 * 
	 * @generated
	 */
	private GeneralPropertiesEditionPart generalPart;

	/**
	 * The OperationGeneralPropertiesEditionComponent sub component
	 * 
	 * @generated
	 */
	protected OperationGeneralPropertiesEditionComponent operationGeneralPropertiesEditionComponent;

	/**
	 * The Parameters part
	 * 
	 * @generated
	 */
	private ParametersPropertiesEditionPart parametersPart;

	/**
	 * The OperationParametersPropertiesEditionComponent sub component
	 * 
	 * @generated
	 */
	protected OperationParametersPropertiesEditionComponent operationParametersPropertiesEditionComponent;

	/**
	 * Parameterized constructor
	 * 
	 * @param operation
	 *            the EObject to edit
	 * @generated
	 */
	public OperationPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject operation,
			String editing_mode) {
		super(editingContext, editing_mode);
		if (operation instanceof Operation) {
			PropertiesEditingProvider provider = null;
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(operation,
					PropertiesEditingProvider.class);
			operationGeneralPropertiesEditionComponent = (OperationGeneralPropertiesEditionComponent)provider
					.getPropertiesEditingComponent(editingContext, editing_mode,
							OperationGeneralPropertiesEditionComponent.GENERAL_PART,
							OperationGeneralPropertiesEditionComponent.class);
			addSubComponent(operationGeneralPropertiesEditionComponent);
			provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(operation,
					PropertiesEditingProvider.class);
			operationParametersPropertiesEditionComponent = (OperationParametersPropertiesEditionComponent)provider
					.getPropertiesEditingComponent(editingContext, editing_mode,
							OperationParametersPropertiesEditionComponent.PARAMETERS_PART,
							OperationParametersPropertiesEditionComponent.class);
			addSubComponent(operationParametersPropertiesEditionComponent);
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
		if (OperationGeneralPropertiesEditionComponent.GENERAL_PART.equals(key)) {
			generalPart = (GeneralPropertiesEditionPart)operationGeneralPropertiesEditionComponent
					.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)generalPart;
		}
		if (OperationParametersPropertiesEditionComponent.PARAMETERS_PART.equals(key)) {
			parametersPart = (ParametersPropertiesEditionPart)operationParametersPropertiesEditionComponent
					.getPropertiesEditionPart(kind, key);
			return (IPropertiesEditionPart)parametersPart;
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
		if (UmlViewsRepository.Parameters.class == key) {
			super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
			parametersPart = (ParametersPropertiesEditionPart)propertiesEditionPart;
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
		if (key == UmlViewsRepository.Parameters.class) {
			super.initPart(key, kind, element, allResource);
		}
	}
}
