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

import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.UMLPackage;

import org.obeonetwork.dsl.uml2.properties.providers.UmlPropertiesEditionProvider;

import org.obeonetwork.dsl.uml2.properties.uml.components.InterfaceAttributesPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.components.InterfaceGeneralPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.components.InterfaceOperationsPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.components.InterfacePropertiesEditionComponent;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class Interface_PropertiesEditionProvider extends UmlPropertiesEditionProvider {

	/**
	 * Constructor without provider for super types.
	 */
	public Interface_PropertiesEditionProvider() {
		super();
	}

	/**
	 * Constructor with providers for super types.
	 * 
	 * @param superProviders
	 *            providers to use for super types.
	 */
	public Interface_PropertiesEditionProvider(List<PropertiesEditingProvider> superProviders) {
		super(superProviders);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext)
	 * @generated
	 */
	public boolean provides(PropertiesEditingContext editingContext) {
		return (editingContext.getEObject() instanceof Interface)
				&& (UMLPackage.Literals.INTERFACE == editingContext.getEObject().eClass());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
	 *      java.lang.String)
	 * @generated
	 */
	public boolean provides(PropertiesEditingContext editingContext, String part) {
		return (editingContext.getEObject() instanceof Interface)
				&& (InterfaceGeneralPropertiesEditionComponent.GENERAL_PART.equals(part)
						|| InterfaceOperationsPropertiesEditionComponent.OPERATIONS_PART.equals(part)
						|| InterfaceAttributesPropertiesEditionComponent.ATTRIBUTES_PART.equals(part));
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
		return (editingContext.getEObject() instanceof Interface)
				&& (refinement == InterfaceGeneralPropertiesEditionComponent.class
						|| refinement == InterfaceOperationsPropertiesEditionComponent.class
						|| refinement == InterfaceAttributesPropertiesEditionComponent.class);
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
		return (editingContext.getEObject() instanceof Interface)
				&& ((InterfaceGeneralPropertiesEditionComponent.GENERAL_PART.equals(part)
						&& refinement == InterfaceGeneralPropertiesEditionComponent.class)
						|| (InterfaceOperationsPropertiesEditionComponent.OPERATIONS_PART.equals(part)
								&& refinement == InterfaceOperationsPropertiesEditionComponent.class)
						|| (InterfaceAttributesPropertiesEditionComponent.ATTRIBUTES_PART.equals(part)
								&& refinement == InterfaceAttributesPropertiesEditionComponent.class));
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
		if (editingContext.getEObject() instanceof Interface) {
			return new InterfacePropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
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
		if (editingContext.getEObject() instanceof Interface) {
			if (InterfaceGeneralPropertiesEditionComponent.GENERAL_PART.equals(part))
				return new InterfaceGeneralPropertiesEditionComponent(editingContext,
						editingContext.getEObject(), mode);
			if (InterfaceOperationsPropertiesEditionComponent.OPERATIONS_PART.equals(part))
				return new InterfaceOperationsPropertiesEditionComponent(editingContext,
						editingContext.getEObject(), mode);
			if (InterfaceAttributesPropertiesEditionComponent.ATTRIBUTES_PART.equals(part))
				return new InterfaceAttributesPropertiesEditionComponent(editingContext,
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
		if (editingContext.getEObject() instanceof Interface) {
			if (InterfaceGeneralPropertiesEditionComponent.GENERAL_PART.equals(part)
					&& refinement == InterfaceGeneralPropertiesEditionComponent.class)
				return new InterfaceGeneralPropertiesEditionComponent(editingContext,
						editingContext.getEObject(), mode);
			if (InterfaceOperationsPropertiesEditionComponent.OPERATIONS_PART.equals(part)
					&& refinement == InterfaceOperationsPropertiesEditionComponent.class)
				return new InterfaceOperationsPropertiesEditionComponent(editingContext,
						editingContext.getEObject(), mode);
			if (InterfaceAttributesPropertiesEditionComponent.ATTRIBUTES_PART.equals(part)
					&& refinement == InterfaceAttributesPropertiesEditionComponent.class)
				return new InterfaceAttributesPropertiesEditionComponent(editingContext,
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
			return eObj != null && UMLPackage.Literals.INTERFACE == eObj.eClass();
		}

	}

}
