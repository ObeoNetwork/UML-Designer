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

import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ElementImportPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for importedElement EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings importedElementSettings;
	
	/**
	 * Settings for importingNamespace EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings importingNamespaceSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public ElementImportPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject elementImport, String editing_mode) {
    super(editingContext, elementImport, editing_mode);
    parts = new String[] { BASE_PART };
    repositoryKey = UmlViewsRepository.class;
    partKey = UmlViewsRepository.ElementImport.class;
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
      
      final ElementImport elementImport = (ElementImport)elt;
      final ElementImportPropertiesEditionPart basePart = (ElementImportPropertiesEditionPart)editingPart;
      // init values
      if (isAccessible(UmlViewsRepository.ElementImport.Properties.visibility)) {
        basePart.initVisibility(EEFUtils.choiceOfValues(elementImport, UMLPackage.eINSTANCE.getElementImport_Visibility()), elementImport.getVisibility());
      }
      if (isAccessible(UmlViewsRepository.ElementImport.Properties.alias))
        basePart.setAlias(EEFConverterUtil.convertToString(UMLPackage.Literals.STRING, elementImport.getAlias()));
      
      if (isAccessible(UmlViewsRepository.ElementImport.Properties.importedElement)) {
        // init part
        importedElementSettings = new EObjectFlatComboSettings(elementImport, UMLPackage.eINSTANCE.getElementImport_ImportedElement());
        basePart.initImportedElement(importedElementSettings);
        // set the button mode
        basePart.setImportedElementButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ElementImport.Properties.importingNamespace)) {
        // init part
        importingNamespaceSettings = new EObjectFlatComboSettings(elementImport, UMLPackage.eINSTANCE.getElementImport_ImportingNamespace());
        basePart.initImportingNamespace(importingNamespaceSettings);
        // set the button mode
        basePart.setImportingNamespaceButtonMode(ButtonsModeEnum.BROWSE);
      }
      // init filters
      
      
      if (isAccessible(UmlViewsRepository.ElementImport.Properties.importedElement)) {
        basePart.addFilterToImportedElement(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof PackageableElement);
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.ElementImport.Properties.importingNamespace)) {
        basePart.addFilterToImportingNamespace(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof Namespace);
          }
          
        });
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
		if (editorKey == UmlViewsRepository.ElementImport.Properties.visibility) {
			return UMLPackage.eINSTANCE.getElementImport_Visibility();
		}
		if (editorKey == UmlViewsRepository.ElementImport.Properties.alias) {
			return UMLPackage.eINSTANCE.getElementImport_Alias();
		}
		if (editorKey == UmlViewsRepository.ElementImport.Properties.importedElement) {
			return UMLPackage.eINSTANCE.getElementImport_ImportedElement();
		}
		if (editorKey == UmlViewsRepository.ElementImport.Properties.importingNamespace) {
			return UMLPackage.eINSTANCE.getElementImport_ImportingNamespace();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
    ElementImport elementImport = (ElementImport)semanticObject;
    if (UmlViewsRepository.ElementImport.Properties.visibility == event.getAffectedEditor()) {
      elementImport.setVisibility((VisibilityKind)event.getNewValue());
    }
    if (UmlViewsRepository.ElementImport.Properties.alias == event.getAffectedEditor()) {
      elementImport.setAlias((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
    }
    if (UmlViewsRepository.ElementImport.Properties.importedElement == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        importedElementSettings.setToReference((PackageableElement)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, importedElementSettings, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy instanceof CreateEditingPolicy) {
            policy.execute();
          }
        }
      }
    }
    if (UmlViewsRepository.ElementImport.Properties.importingNamespace == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        importingNamespaceSettings.setToReference((Namespace)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, importingNamespaceSettings, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy instanceof CreateEditingPolicy) {
            policy.execute();
          }
        }
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
			ElementImportPropertiesEditionPart basePart = (ElementImportPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getElementImport_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.ElementImport.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getElementImport_Alias().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.ElementImport.Properties.alias)) {
				if (msg.getNewValue() != null) {
					basePart.setAlias(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setAlias("");
				}
			}
			if (UMLPackage.eINSTANCE.getElementImport_ImportedElement().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ElementImport.Properties.importedElement))
				basePart.setImportedElement((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getElementImport_ImportingNamespace().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ElementImport.Properties.importingNamespace))
				basePart.setImportingNamespace((EObject)msg.getNewValue());
			
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
			UMLPackage.eINSTANCE.getElementImport_Visibility(),
			UMLPackage.eINSTANCE.getElementImport_Alias(),
			UMLPackage.eINSTANCE.getElementImport_ImportedElement(),
			UMLPackage.eINSTANCE.getElementImport_ImportingNamespace()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
    return key == UmlViewsRepository.ElementImport.Properties.visibility || key == UmlViewsRepository.ElementImport.Properties.importedElement || key == UmlViewsRepository.ElementImport.Properties.importingNamespace;
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
        if (UmlViewsRepository.ElementImport.Properties.visibility == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getElementImport_Visibility().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getElementImport_Visibility().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.ElementImport.Properties.alias == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getElementImport_Alias().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getElementImport_Alias().getEAttributeType(), newValue);
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
