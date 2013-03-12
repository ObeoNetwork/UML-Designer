/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.providers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.providers.impl.PropertiesEditingProviderImpl;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.components.ElementDocumentationPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.components.NamedElementRelationshipsPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.components.NamedElementStereotypesPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.components.PackageProfilesPropertiesEditionComponent;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class UmlPropertiesEditionProvider extends PropertiesEditingProviderImpl {

	/**
	 * Constructor without provider for super types.
	 */
	public UmlPropertiesEditionProvider() {
		super();
	}

	/**
	 * Constructor with providers for super types.
	 * 
	 * @param superProviders
	 *            providers to use for super types.
	 */
	public UmlPropertiesEditionProvider(
			List<PropertiesEditingProvider> superProviders) {
		super(superProviders);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext)
	 */
	public boolean provides(PropertiesEditingContext editingContext) {
		return (editingContext.getEObject() instanceof NamedElement)
				&& (UMLPackage.Literals.NAMED_ELEMENT == editingContext
						.getEObject().eClass());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
	 *      java.lang.String)
	 */
	public boolean provides(PropertiesEditingContext editingContext, String part) {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
	 *      java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public boolean provides(PropertiesEditingContext editingContext,
			java.lang.Class refinement) {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
	 *      java.lang.String, java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public boolean provides(PropertiesEditingContext editingContext,
			String part, java.lang.Class refinement) {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#getPropertiesEditingComponent(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
	 *      java.lang.String)
	 */
	public IPropertiesEditionComponent getPropertiesEditingComponent(
			PropertiesEditingContext editingContext, String mode) {
		if (editingContext.getEObject() instanceof NamedElement) {
			// return new NamedElementPropertiesEditionComponent(editingContext,
			// editingContext.getEObject(), mode);
			throw new UnsupportedOperationException();
		}
		return super.getPropertiesEditingComponent(editingContext, mode);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#getPropertiesEditingComponent(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
	 *      java.lang.String, java.lang.String)
	 */
	public IPropertiesEditionComponent getPropertiesEditingComponent(
			PropertiesEditingContext editingContext, String mode, String part) {
		if (editingContext.getEObject() instanceof org.eclipse.uml2.uml.Package) {
			if (PackageProfilesPropertiesEditionComponent.PROFILES_PART
					.equals(part))
				return new PackageProfilesPropertiesEditionComponent(
						editingContext, editingContext.getEObject(), mode);
		}

		if (editingContext.getEObject() instanceof NamedElement) {
			if (NamedElementStereotypesPropertiesEditionComponent.STEREOTYPES_PART
					.equals(part))
				return new NamedElementStereotypesPropertiesEditionComponent(
						editingContext, editingContext.getEObject(), mode);
			if (NamedElementRelationshipsPropertiesEditionComponent.RELATIONSHIPS_PART
					.equals(part))
				return new NamedElementRelationshipsPropertiesEditionComponent(
						editingContext, editingContext.getEObject(), mode);
		}

		if (editingContext.getEObject() instanceof Element) {
			if (ElementDocumentationPropertiesEditionComponent.DOCUMENTATION_PART
					.equals(part))
				return new ElementDocumentationPropertiesEditionComponent(
						editingContext, editingContext.getEObject(), mode);

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
	public IPropertiesEditionComponent getPropertiesEditingComponent(
			PropertiesEditingContext editingContext, String mode, String part,
			java.lang.Class refinement) {

		if (editingContext.getEObject() instanceof org.eclipse.uml2.uml.Package) {
			if (PackageProfilesPropertiesEditionComponent.PROFILES_PART
					.equals(part))
				return new PackageProfilesPropertiesEditionComponent(
						editingContext, editingContext.getEObject(), mode);
		}

		if (editingContext.getEObject() instanceof NamedElement) {
			if (NamedElementStereotypesPropertiesEditionComponent.STEREOTYPES_PART
					.equals(part)
					&& refinement == NamedElementStereotypesPropertiesEditionComponent.class)
				return new NamedElementStereotypesPropertiesEditionComponent(
						editingContext, editingContext.getEObject(), mode);

			if (NamedElementRelationshipsPropertiesEditionComponent.RELATIONSHIPS_PART
					.equals(part))
				return new NamedElementRelationshipsPropertiesEditionComponent(
						editingContext, editingContext.getEObject(), mode);
		}

		if (editingContext.getEObject() instanceof Element) {
			if (ElementDocumentationPropertiesEditionComponent.DOCUMENTATION_PART
					.equals(part))
				return new ElementDocumentationPropertiesEditionComponent(
						editingContext, editingContext.getEObject(), mode);
		}

		return super.getPropertiesEditingComponent(editingContext, mode, part,
				refinement);
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
			return (eObj != null && UMLPackage.Literals.NAMED_ELEMENT == eObj
					.eClass())
					|| (eObj != null && UMLPackage.Literals.ELEMENT == eObj
							.eClass());
		}

	}

}
