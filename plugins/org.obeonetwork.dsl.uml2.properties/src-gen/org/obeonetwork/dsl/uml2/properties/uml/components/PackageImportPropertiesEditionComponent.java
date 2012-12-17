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

import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
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

import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class PackageImportPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for importedPackage EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings importedPackageSettings;
	
	/**
	 * Settings for importingNamespace EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings importingNamespaceSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public PackageImportPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject packageImport, String editing_mode) {
    super(editingContext, packageImport, editing_mode);
    parts = new String[] { BASE_PART };
    repositoryKey = UmlViewsRepository.class;
    partKey = UmlViewsRepository.PackageImport.class;
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
      
      final PackageImport packageImport = (PackageImport)elt;
      final PackageImportPropertiesEditionPart basePart = (PackageImportPropertiesEditionPart)editingPart;
      // init values
      if (isAccessible(UmlViewsRepository.PackageImport.Properties.visibility)) {
        basePart.initVisibility(EEFUtils.choiceOfValues(packageImport, UMLPackage.eINSTANCE.getPackageImport_Visibility()), packageImport.getVisibility());
      }
      if (isAccessible(UmlViewsRepository.PackageImport.Properties.importedPackage)) {
        // init part
        importedPackageSettings = new EObjectFlatComboSettings(packageImport, UMLPackage.eINSTANCE.getPackageImport_ImportedPackage());
        basePart.initImportedPackage(importedPackageSettings);
        // set the button mode
        basePart.setImportedPackageButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.PackageImport.Properties.importingNamespace)) {
        // init part
        importingNamespaceSettings = new EObjectFlatComboSettings(packageImport, UMLPackage.eINSTANCE.getPackageImport_ImportingNamespace());
        basePart.initImportingNamespace(importingNamespaceSettings);
        // set the button mode
        basePart.setImportingNamespaceButtonMode(ButtonsModeEnum.BROWSE);
      }
      // init filters
      
      if (isAccessible(UmlViewsRepository.PackageImport.Properties.importedPackage)) {
        basePart.addFilterToImportedPackage(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof Package);
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.PackageImport.Properties.importingNamespace)) {
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
		if (editorKey == UmlViewsRepository.PackageImport.Properties.visibility) {
			return UMLPackage.eINSTANCE.getPackageImport_Visibility();
		}
		if (editorKey == UmlViewsRepository.PackageImport.Properties.importedPackage) {
			return UMLPackage.eINSTANCE.getPackageImport_ImportedPackage();
		}
		if (editorKey == UmlViewsRepository.PackageImport.Properties.importingNamespace) {
			return UMLPackage.eINSTANCE.getPackageImport_ImportingNamespace();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
    PackageImport packageImport = (PackageImport)semanticObject;
    if (UmlViewsRepository.PackageImport.Properties.visibility == event.getAffectedEditor()) {
      packageImport.setVisibility((VisibilityKind)event.getNewValue());
    }
    if (UmlViewsRepository.PackageImport.Properties.importedPackage == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        importedPackageSettings.setToReference((Package)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        org.eclipse.uml2.uml.Package eObject = UMLFactory.eINSTANCE.createPackage();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        importedPackageSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.PackageImport.Properties.importingNamespace == event.getAffectedEditor()) {
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
			PackageImportPropertiesEditionPart basePart = (PackageImportPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getPackageImport_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.PackageImport.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getPackageImport_ImportedPackage().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.PackageImport.Properties.importedPackage))
				basePart.setImportedPackage((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getPackageImport_ImportingNamespace().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.PackageImport.Properties.importingNamespace))
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
			UMLPackage.eINSTANCE.getPackageImport_Visibility(),
			UMLPackage.eINSTANCE.getPackageImport_ImportedPackage(),
			UMLPackage.eINSTANCE.getPackageImport_ImportingNamespace()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
    return key == UmlViewsRepository.PackageImport.Properties.visibility || key == UmlViewsRepository.PackageImport.Properties.importedPackage || key == UmlViewsRepository.PackageImport.Properties.importingNamespace;
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
        if (UmlViewsRepository.PackageImport.Properties.visibility == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getPackageImport_Visibility().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getPackageImport_Visibility().getEAttributeType(), newValue);
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
