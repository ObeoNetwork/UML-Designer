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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.eef.runtime.api.notify.EStructuralFeatureNotificationFilter;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;

import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;

import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.uml2.types.TypesPackage;

import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class DependencyPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String GENERAL_PART = "General"; //$NON-NLS-1$

	
	/**
	 * Settings for supplier ReferencesTable
	 */
	protected ReferencesTableSettings supplierSettings;
	
	/**
	 * Settings for client ReferencesTable
	 */
	protected ReferencesTableSettings clientSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public DependencyPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject dependency, String editing_mode) {
    super(editingContext, dependency, editing_mode);
    parts = new String[] { GENERAL_PART };
    repositoryKey = UmlViewsRepository.class;
    partKey = UmlViewsRepository.General.class;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object, int, org.eclipse.emf.ecore.EObject, 
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * @generated
	 */
	public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
    setInitializing(true);
    if (editingPart != null && key == partKey) {
      editingPart.setContext(elt, allResource);
      
      final Dependency dependency = (Dependency)elt;
      final GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart)editingPart;
      // init values
      if (isAccessible(UmlViewsRepository.General.name))
        generalPart.setName(EEFConverterUtil.convertToString(TypesPackage.Literals.STRING, dependency.getName()));
      
      if (isAccessible(UmlViewsRepository.General.visibility)) {
        generalPart.initVisibility(EEFUtils.choiceOfValues(dependency, UMLPackage.eINSTANCE.getNamedElement_Visibility()), dependency.getVisibility());
      }
      if (isAccessible(UmlViewsRepository.General.supplier)) {
        supplierSettings = new ReferencesTableSettings(dependency, UMLPackage.eINSTANCE.getDependency_Supplier());
        generalPart.initSupplier(supplierSettings);
      }
      if (isAccessible(UmlViewsRepository.General.client)) {
        clientSettings = new ReferencesTableSettings(dependency, UMLPackage.eINSTANCE.getDependency_Client());
        generalPart.initClient(clientSettings);
      }
      // init filters
      
      
      if (isAccessible(UmlViewsRepository.General.supplier)) {
        generalPart.addFilterToSupplier(new EObjectFilter(UMLPackage.Literals.NAMED_ELEMENT));
      }
      if (isAccessible(UmlViewsRepository.General.client)) {
        generalPart.addFilterToClient(new EObjectFilter(UMLPackage.Literals.NAMED_ELEMENT));
      }
      // init values for referenced views
      
      // init filters for referenced views
      
    }
    setInitializing(false);
  }







	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
	 */
	public EStructuralFeature associatedFeature(Object editorKey) {
		if (editorKey == UmlViewsRepository.General.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.General.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.General.supplier) {
			return UMLPackage.eINSTANCE.getDependency_Supplier();
		}
		if (editorKey == UmlViewsRepository.General.client) {
			return UMLPackage.eINSTANCE.getDependency_Client();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
    Dependency dependency = (Dependency)semanticObject;
    if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
      dependency.setName((java.lang.String)EEFConverterUtil.createFromString(TypesPackage.Literals.STRING, (String)event.getNewValue()));
    }
    if (UmlViewsRepository.General.visibility == event.getAffectedEditor()) {
      dependency.setVisibility((VisibilityKind)event.getNewValue());
    }
    if (UmlViewsRepository.General.supplier == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof NamedElement) {
          supplierSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        supplierSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        supplierSettings.move(event.getNewIndex(), (NamedElement) event.getNewValue());
      }
    }
    if (UmlViewsRepository.General.client == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof NamedElement) {
          clientSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        clientSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        clientSettings.move(event.getNewIndex(), (NamedElement) event.getNewValue());
      }
    }
  }

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		super.updatePart(msg);
		if (editingPart.isVisible()) {
			GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.name)) {
				if (msg.getNewValue() != null) {
					generalPart.setName(EcoreUtil.convertToString(TypesPackage.Literals.STRING, msg.getNewValue()));
				} else {
					generalPart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.General.visibility))
				generalPart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getDependency_Supplier().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.General.supplier))
				generalPart.updateSupplier();
			if (UMLPackage.eINSTANCE.getDependency_Client().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.General.client))
				generalPart.updateClient();
			
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
	 */
	@Override
	protected NotificationFilter[] getNotificationFilters() {
		NotificationFilter filter = new EStructuralFeatureNotificationFilter(
			UMLPackage.eINSTANCE.getNamedElement_Name(),
			UMLPackage.eINSTANCE.getNamedElement_Visibility(),
			UMLPackage.eINSTANCE.getDependency_Supplier(),
			UMLPackage.eINSTANCE.getDependency_Client()		);
		return new NotificationFilter[] {filter,};
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#mustBeComposed(java.lang.Object, int)
	 */
	public boolean mustBeComposed(Object key, int kind) {
		return key == UmlViewsRepository.General.name || key == UmlViewsRepository.General.visibility || key == UmlViewsRepository.General.supplier || key == UmlViewsRepository.General.client;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
    return key == UmlViewsRepository.General.supplier || key == UmlViewsRepository.General.client;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
    Diagnostic ret = Diagnostic.OK_INSTANCE;
    if (event.getNewValue() != null) {
      try {
        if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.General.visibility == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
        }
      } catch (IllegalArgumentException iae) {
        ret = BasicDiagnostic.toDiagnostic(iae);
      } catch (WrappedException we) {
        ret = BasicDiagnostic.toDiagnostic(we);
      }
    }
    return ret;
  }


	

}
