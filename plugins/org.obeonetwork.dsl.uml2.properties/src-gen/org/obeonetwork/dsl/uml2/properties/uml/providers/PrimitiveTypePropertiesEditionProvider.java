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
package org.obeonetwork.dsl.uml2.properties.uml.providers;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.VisibilityKind;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;

import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.jface.viewers.IFilter;

import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.UMLPackage;

import org.obeonetwork.dsl.uml2.properties.providers.UmlPropertiesEditionProvider;

import org.obeonetwork.dsl.uml2.properties.uml.components.PrimitiveTypeAttributesPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.components.PrimitiveTypeGeneralPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.components.PrimitiveTypeOperationsPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.components.PrimitiveTypePropertiesEditionComponent;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class PrimitiveTypePropertiesEditionProvider extends UmlPropertiesEditionProvider {

	/**
	 * Constructor without provider for super types.
	 */
	public PrimitiveTypePropertiesEditionProvider() {
		super();
	}

	/**
	 * Constructor with providers for super types.
	 * 
	 * @param superProviders
	 *            providers to use for super types.
	 */
	public PrimitiveTypePropertiesEditionProvider(List<PropertiesEditingProvider> superProviders) {
		super(superProviders);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext)
	 * @generated
	 */
	public boolean provides(PropertiesEditingContext editingContext) {
		return (editingContext.getEObject() instanceof PrimitiveType)
				&& (UMLPackage.Literals.PRIMITIVE_TYPE == editingContext.getEObject().eClass());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
	 *      java.lang.String)
	 * @generated
	 */
	public boolean provides(PropertiesEditingContext editingContext, String part) {
		return (editingContext.getEObject() instanceof PrimitiveType)
				&& (PrimitiveTypeGeneralPropertiesEditionComponent.GENERAL_PART.equals(part)
						|| PrimitiveTypeAttributesPropertiesEditionComponent.ATTRIBUTES_PART.equals(part)
						|| PrimitiveTypeOperationsPropertiesEditionComponent.OPERATIONS_PART.equals(part));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
	 *      java.lang.Class)
	 * @generated
	 */
	@SuppressWarnings("rawtypes")
	public boolean provides(PropertiesEditingContext editingContext, java.lang.Class refinement) {
		return (editingContext.getEObject() instanceof PrimitiveType)
				&& (refinement == PrimitiveTypeGeneralPropertiesEditionComponent.class
						|| refinement == PrimitiveTypeAttributesPropertiesEditionComponent.class
						|| refinement == PrimitiveTypeOperationsPropertiesEditionComponent.class);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
	 *      java.lang.String, java.lang.Class)
	 * @generated
	 */
	@SuppressWarnings("rawtypes")
	public boolean provides(PropertiesEditingContext editingContext, String part,
			java.lang.Class refinement) {
		return (editingContext.getEObject() instanceof PrimitiveType)
				&& ((PrimitiveTypeGeneralPropertiesEditionComponent.GENERAL_PART.equals(part)
						&& refinement == PrimitiveTypeGeneralPropertiesEditionComponent.class)
						|| (PrimitiveTypeAttributesPropertiesEditionComponent.ATTRIBUTES_PART.equals(part)
								&& refinement == PrimitiveTypeAttributesPropertiesEditionComponent.class)
						|| (PrimitiveTypeOperationsPropertiesEditionComponent.OPERATIONS_PART.equals(part)
								&& refinement == PrimitiveTypeOperationsPropertiesEditionComponent.class));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#getPropertiesEditingComponent(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
	 *      java.lang.String)
	 * @generated
	 */
	public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext,
			String mode) {
		if (editingContext.getEObject() instanceof PrimitiveType) {
			return new PrimitiveTypePropertiesEditionComponent(editingContext, editingContext.getEObject(),
					mode);
		}
		return super.getPropertiesEditingComponent(editingContext, mode);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#getPropertiesEditingComponent(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
	 *      java.lang.String, java.lang.String)
	 * @generated
	 */
	public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext,
			String mode, String part) {
		if (editingContext.getEObject() instanceof PrimitiveType) {
			if (PrimitiveTypeGeneralPropertiesEditionComponent.GENERAL_PART.equals(part))
				return new PrimitiveTypeGeneralPropertiesEditionComponent(editingContext,
						editingContext.getEObject(), mode);
			if (PrimitiveTypeAttributesPropertiesEditionComponent.ATTRIBUTES_PART.equals(part))
				return new PrimitiveTypeAttributesPropertiesEditionComponent(editingContext,
						editingContext.getEObject(), mode);
			if (PrimitiveTypeOperationsPropertiesEditionComponent.OPERATIONS_PART.equals(part))
				return new PrimitiveTypeOperationsPropertiesEditionComponent(editingContext,
						editingContext.getEObject(), mode);
		}
		return super.getPropertiesEditingComponent(editingContext, mode, part);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#getPropertiesEditingComponent(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
	 *      java.lang.String, java.lang.String, java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext,
			String mode, String part, java.lang.Class refinement) {
		if (editingContext.getEObject() instanceof PrimitiveType) {
			if (PrimitiveTypeGeneralPropertiesEditionComponent.GENERAL_PART.equals(part)
					&& refinement == PrimitiveTypeGeneralPropertiesEditionComponent.class)
				return new PrimitiveTypeGeneralPropertiesEditionComponent(editingContext,
						editingContext.getEObject(), mode);
			if (PrimitiveTypeAttributesPropertiesEditionComponent.ATTRIBUTES_PART.equals(part)
					&& refinement == PrimitiveTypeAttributesPropertiesEditionComponent.class)
				return new PrimitiveTypeAttributesPropertiesEditionComponent(editingContext,
						editingContext.getEObject(), mode);
			if (PrimitiveTypeOperationsPropertiesEditionComponent.OPERATIONS_PART.equals(part)
					&& refinement == PrimitiveTypeOperationsPropertiesEditionComponent.class)
				return new PrimitiveTypeOperationsPropertiesEditionComponent(editingContext,
						editingContext.getEObject(), mode);
		}
		return super.getPropertiesEditingComponent(editingContext, mode, part, refinement);
	}

	/**
	 * Provides the filter used by the plugin.xml to assign part forms.
	 */
	public static class EditionFilter implements IFilter {

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
		 */
		public boolean select(Object toTest) {
			EObject eObj = EEFUtils.resolveSemanticObject(toTest);
			return eObj != null && UMLPackage.Literals.PRIMITIVE_TYPE == eObj.eClass();
		}

	}

}
