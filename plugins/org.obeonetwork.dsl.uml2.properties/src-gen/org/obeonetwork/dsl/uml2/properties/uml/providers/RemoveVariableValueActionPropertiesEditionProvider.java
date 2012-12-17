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

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;

import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.providers.impl.PropertiesEditingProviderImpl;

import org.eclipse.jface.viewers.IFilter;

import org.eclipse.uml2.uml.RemoveVariableValueAction;
import org.eclipse.uml2.uml.UMLPackage;

import org.obeonetwork.dsl.uml2.properties.uml.components.RemoveVariableValueActionPropertiesEditionComponent;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class RemoveVariableValueActionPropertiesEditionProvider extends PropertiesEditingProviderImpl {

	/**
	 * Constructor without provider for super types.
	 */
	public RemoveVariableValueActionPropertiesEditionProvider() {
		super();
	}

	/**
	 * Constructor with providers for super types.
	 * @param superProviders providers to use for super types.
	 */
	public RemoveVariableValueActionPropertiesEditionProvider(List<PropertiesEditingProvider> superProviders) {
		super(superProviders);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext)
	 * @generated
	 */
	public boolean provides(PropertiesEditingContext editingContext) {
    return (editingContext.getEObject() instanceof RemoveVariableValueAction) 
          && (UMLPackage.Literals.REMOVE_VARIABLE_VALUE_ACTION == editingContext.getEObject().eClass());
  }

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext, java.lang.String)
	 * @generated
	 */
	public boolean provides(PropertiesEditingContext editingContext, String part) {
    return (editingContext.getEObject() instanceof RemoveVariableValueAction) && (RemoveVariableValueActionPropertiesEditionComponent.BASE_PART.equals(part));
  }

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext, java.lang.Class)
	 * @generated
	 */
	@SuppressWarnings("rawtypes")
	public boolean provides(PropertiesEditingContext editingContext, java.lang.Class refinement) {
    return (editingContext.getEObject() instanceof RemoveVariableValueAction) && (refinement == RemoveVariableValueActionPropertiesEditionComponent.class);
  }

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext, java.lang.String, java.lang.Class)
	 * @generated
	 */
	@SuppressWarnings("rawtypes")
	public boolean provides(PropertiesEditingContext editingContext, String part, java.lang.Class refinement) {
    return (editingContext.getEObject() instanceof RemoveVariableValueAction) && ((RemoveVariableValueActionPropertiesEditionComponent.BASE_PART.equals(part) && refinement == RemoveVariableValueActionPropertiesEditionComponent.class));
  }

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#getPropertiesEditingComponent(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext, java.lang.String)
	 * @generated
	 */
	public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext, String mode) {
    if (editingContext.getEObject() instanceof RemoveVariableValueAction) {
      return new RemoveVariableValueActionPropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
    }
    return super.getPropertiesEditingComponent(editingContext, mode);
  }

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#getPropertiesEditingComponent(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext, java.lang.String, java.lang.String)
	 * @generated
	 */
	public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext, String mode, String part) {
    if (editingContext.getEObject() instanceof RemoveVariableValueAction) {
      if (RemoveVariableValueActionPropertiesEditionComponent.BASE_PART.equals(part))
        return new RemoveVariableValueActionPropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
    }
    return super.getPropertiesEditingComponent(editingContext, mode, part);
  }

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#getPropertiesEditingComponent(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext, java.lang.String, java.lang.String, java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext, String mode, String part, java.lang.Class refinement) {
		if (editingContext.getEObject() instanceof RemoveVariableValueAction) {
			if (RemoveVariableValueActionPropertiesEditionComponent.BASE_PART.equals(part)
				&& refinement == RemoveVariableValueActionPropertiesEditionComponent.class)
				return new RemoveVariableValueActionPropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
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
			return eObj != null && UMLPackage.Literals.REMOVE_VARIABLE_VALUE_ACTION == eObj.eClass();
		}
		
	}

}
