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

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;

import org.eclipse.uml2.types.TypesPackage;

import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class OperationGeneralPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String GENERAL_PART = "General"; //$NON-NLS-1$

	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public OperationGeneralPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject operation, String editing_mode) {
    super(editingContext, operation, editing_mode);
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
      
      final Operation operation = (Operation)elt;
      final GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart)editingPart;
      // init values
      if (isAccessible(UmlViewsRepository.General.name))
        generalPart.setName(EEFConverterUtil.convertToString(TypesPackage.Literals.STRING, operation.getName()));
      
      if (isAccessible(UmlViewsRepository.General.visibility)) {
        generalPart.initVisibility(EEFUtils.choiceOfValues(operation, UMLPackage.eINSTANCE.getNamedElement_Visibility()), operation.getVisibility());
      }
      generalPart.setLeaf(operation.isLeaf());
      
      generalPart.setStatic_(operation.isStatic());
      
      generalPart.setUnique(operation.isUnique());
      
      generalPart.setAbstract_(operation.isAbstract());
      
      generalPart.setQuery(operation.isQuery());
      
      // init filters
      
      
      
      
      
      
      
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
		if (editorKey == UmlViewsRepository.General.Qualifiers.leaf) {
			return UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf();
		}
		if (editorKey == UmlViewsRepository.General.Qualifiers.static_) {
			return UMLPackage.eINSTANCE.getFeature_IsStatic();
		}
		if (editorKey == UmlViewsRepository.General.Qualifiers.unique) {
			return UMLPackage.eINSTANCE.getOperation_IsUnique();
		}
		if (editorKey == UmlViewsRepository.General.Qualifiers.abstract_) {
			return UMLPackage.eINSTANCE.getClassifier_IsAbstract();
		}
		if (editorKey == UmlViewsRepository.General.Qualifiers.query) {
			return UMLPackage.eINSTANCE.getOperation_IsQuery();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
    Operation operation = (Operation)semanticObject;
    if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
      operation.setName((java.lang.String)EEFConverterUtil.createFromString(TypesPackage.Literals.STRING, (String)event.getNewValue()));
    }
    if (UmlViewsRepository.General.visibility == event.getAffectedEditor()) {
      operation.setVisibility((VisibilityKind)event.getNewValue());
    }
    if (UmlViewsRepository.General.Qualifiers.leaf == event.getAffectedEditor()) {
      operation.setIsLeaf((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.General.Qualifiers.static_ == event.getAffectedEditor()) {
      operation.setIsStatic((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.General.Qualifiers.abstract_ == event.getAffectedEditor()) {
      operation.setIsAbstract((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.General.Qualifiers.query == event.getAffectedEditor()) {
      operation.setIsQuery((Boolean)event.getNewValue());
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
			
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.Qualifiers.leaf))
				generalPart.setLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getFeature_IsStatic().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.Qualifiers.static_))
				generalPart.setStatic_((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getOperation_IsUnique().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.Qualifiers.unique))
				generalPart.setUnique((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getClassifier_IsAbstract().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.Qualifiers.abstract_))
				generalPart.setAbstract_((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getOperation_IsQuery().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.Qualifiers.query))
				generalPart.setQuery((Boolean)msg.getNewValue());
			
			
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
			UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(),
			UMLPackage.eINSTANCE.getFeature_IsStatic(),
			UMLPackage.eINSTANCE.getOperation_IsUnique(),
			UMLPackage.eINSTANCE.getClassifier_IsAbstract(),
			UMLPackage.eINSTANCE.getOperation_IsQuery()		);
		return new NotificationFilter[] {filter,};
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#mustBeComposed(java.lang.Object, int)
	 */
	public boolean mustBeComposed(Object key, int kind) {
		return key == UmlViewsRepository.General.name || key == UmlViewsRepository.General.visibility || key == UmlViewsRepository.General.Qualifiers.leaf || key == UmlViewsRepository.General.Qualifiers.static_ || key == UmlViewsRepository.General.Qualifiers.unique || key == UmlViewsRepository.General.Qualifiers.abstract_ || key == UmlViewsRepository.General.Qualifiers.query || key == UmlViewsRepository.General.Qualifiers.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
    return key == UmlViewsRepository.General.Qualifiers.leaf || key == UmlViewsRepository.General.Qualifiers.static_ || key == UmlViewsRepository.General.Qualifiers.unique || key == UmlViewsRepository.General.Qualifiers.abstract_ || key == UmlViewsRepository.General.Qualifiers.query;
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
        if (UmlViewsRepository.General.Qualifiers.leaf == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.General.Qualifiers.static_ == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.General.Qualifiers.unique == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getOperation_IsUnique().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getOperation_IsUnique().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.General.Qualifiers.abstract_ == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getClassifier_IsAbstract().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getClassifier_IsAbstract().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.General.Qualifiers.query == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getOperation_IsQuery().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getOperation_IsQuery().getEAttributeType(), newValue);
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
