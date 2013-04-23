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

import org.eclipse.uml2.uml.Connector;

import org.obeonetwork.dsl.uml2.properties.uml.parts.EndsPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ConnectorPropertiesEditionComponent extends ComposedPropertiesEditionComponent {

	/**
	 * The General part
	 * @generated
	 */
	private GeneralPropertiesEditionPart generalPart;

	/**
	 * The ConnectorGeneralPropertiesEditionComponent sub component
	 * @generated
	 */
	protected ConnectorGeneralPropertiesEditionComponent connectorGeneralPropertiesEditionComponent;

	/**
	 * The Ends part
	 * @generated
	 */
	private EndsPropertiesEditionPart endsPart;

	/**
	 * The ConnectorEndsPropertiesEditionComponent sub component
	 * @generated
	 */
	protected ConnectorEndsPropertiesEditionComponent connectorEndsPropertiesEditionComponent;

	/**
	 * Parameterized constructor
	 * 
	 * @param connector the EObject to edit
	 * @generated
	 */
	public ConnectorPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject connector, String editing_mode) {
    super(editingContext, editing_mode);
    if (connector instanceof Connector) {
      PropertiesEditingProvider provider = null;
      provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(connector, PropertiesEditingProvider.class);
      connectorGeneralPropertiesEditionComponent = (ConnectorGeneralPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, ConnectorGeneralPropertiesEditionComponent.GENERAL_PART, ConnectorGeneralPropertiesEditionComponent.class);
      addSubComponent(connectorGeneralPropertiesEditionComponent);
      provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(connector, PropertiesEditingProvider.class);
      connectorEndsPropertiesEditionComponent = (ConnectorEndsPropertiesEditionComponent)provider.getPropertiesEditingComponent(editingContext, editing_mode, ConnectorEndsPropertiesEditionComponent.ENDS_PART, ConnectorEndsPropertiesEditionComponent.class);
      addSubComponent(connectorEndsPropertiesEditionComponent);
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
    if (ConnectorGeneralPropertiesEditionComponent.GENERAL_PART.equals(key)) {
      generalPart = (GeneralPropertiesEditionPart)connectorGeneralPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
      return (IPropertiesEditionPart)generalPart;
    }
    if (ConnectorEndsPropertiesEditionComponent.ENDS_PART.equals(key)) {
      endsPart = (EndsPropertiesEditionPart)connectorEndsPropertiesEditionComponent.getPropertiesEditionPart(kind, key);
      return (IPropertiesEditionPart)endsPart;
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
    if (UmlViewsRepository.Ends.class == key) {
      super.setPropertiesEditionPart(key, kind, propertiesEditionPart);
      endsPart = (EndsPropertiesEditionPart)propertiesEditionPart;
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
    if (key == UmlViewsRepository.Ends.class) {
      super.initPart(key, kind, element, allResource);
    }
  }
}
