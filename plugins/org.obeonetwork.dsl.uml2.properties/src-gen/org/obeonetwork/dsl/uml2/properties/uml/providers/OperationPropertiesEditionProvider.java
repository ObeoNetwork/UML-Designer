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

import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLPackage;

import org.obeonetwork.dsl.uml2.properties.providers.UmlPropertiesEditionProvider;

import org.obeonetwork.dsl.uml2.properties.uml.components.OperationGeneralPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.components.OperationParametersPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.components.OperationPropertiesEditionComponent;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class OperationPropertiesEditionProvider extends UmlPropertiesEditionProvider {

	/**
	 * Constructor without provider for super types.
	 */
	public OperationPropertiesEditionProvider() {
		super();
	}

	/**
	 * Constructor with providers for super types.
	 * 
	 * @param superProviders
	 *            providers to use for super types.
	 */
	public OperationPropertiesEditionProvider(List<PropertiesEditingProvider> superProviders) {
		super(superProviders);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext)
	 * @generated
	 */
	public boolean provides(PropertiesEditingContext editingContext) {
		return (editingContext.getEObject() instanceof Operation)
				&& (UMLPackage.Literals.OPERATION == editingContext.getEObject().eClass());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
	 *      java.lang.String)
	 * @generated
	 */
	public boolean provides(PropertiesEditingContext editingContext, String part) {
		return (editingContext.getEObject() instanceof Operation)
				&& (OperationGeneralPropertiesEditionComponent.GENERAL_PART.equals(part)
						|| OperationParametersPropertiesEditionComponent.PARAMETERS_PART.equals(part));
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
		return (editingContext.getEObject() instanceof Operation)
				&& (refinement == OperationGeneralPropertiesEditionComponent.class
						|| refinement == OperationParametersPropertiesEditionComponent.class);
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
		return (editingContext.getEObject() instanceof Operation)
				&& ((OperationGeneralPropertiesEditionComponent.GENERAL_PART.equals(part)
						&& refinement == OperationGeneralPropertiesEditionComponent.class)
						|| (OperationParametersPropertiesEditionComponent.PARAMETERS_PART.equals(part)
								&& refinement == OperationParametersPropertiesEditionComponent.class));
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
		if (editingContext.getEObject() instanceof Operation) {
			return new OperationPropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
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
		if (editingContext.getEObject() instanceof Operation) {
			if (OperationGeneralPropertiesEditionComponent.GENERAL_PART.equals(part))
				return new OperationGeneralPropertiesEditionComponent(editingContext,
						editingContext.getEObject(), mode);
			if (OperationParametersPropertiesEditionComponent.PARAMETERS_PART.equals(part))
				return new OperationParametersPropertiesEditionComponent(editingContext,
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
		if (editingContext.getEObject() instanceof Operation) {
			if (OperationGeneralPropertiesEditionComponent.GENERAL_PART.equals(part)
					&& refinement == OperationGeneralPropertiesEditionComponent.class)
				return new OperationGeneralPropertiesEditionComponent(editingContext,
						editingContext.getEObject(), mode);
			if (OperationParametersPropertiesEditionComponent.PARAMETERS_PART.equals(part)
					&& refinement == OperationParametersPropertiesEditionComponent.class)
				return new OperationParametersPropertiesEditionComponent(editingContext,
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
			return eObj != null && UMLPackage.Literals.OPERATION == eObj.eClass();
		}

	}

}
