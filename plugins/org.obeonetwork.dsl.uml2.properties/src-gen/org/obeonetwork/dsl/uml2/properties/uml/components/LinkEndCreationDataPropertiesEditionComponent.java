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

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.LinkEndCreationData;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

import org.obeonetwork.dsl.uml2.properties.uml.parts.LinkEndCreationDataPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class LinkEndCreationDataPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for value EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings valueSettings;
	
	/**
	 * Settings for end EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings endSettings;
	
	/**
	 * Settings for insertAt EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings insertAtSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public LinkEndCreationDataPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject linkEndCreationData, String editing_mode) {
    super(editingContext, linkEndCreationData, editing_mode);
    parts = new String[] { BASE_PART };
    repositoryKey = UmlViewsRepository.class;
    partKey = UmlViewsRepository.LinkEndCreationData.class;
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
      
      final LinkEndCreationData linkEndCreationData = (LinkEndCreationData)elt;
      final LinkEndCreationDataPropertiesEditionPart basePart = (LinkEndCreationDataPropertiesEditionPart)editingPart;
      // init values
      if (isAccessible(UmlViewsRepository.LinkEndCreationData.Properties.value)) {
        // init part
        valueSettings = new EObjectFlatComboSettings(linkEndCreationData, UMLPackage.eINSTANCE.getLinkEndData_Value());
        basePart.initValue(valueSettings);
        // set the button mode
        basePart.setValueButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.LinkEndCreationData.Properties.end)) {
        // init part
        endSettings = new EObjectFlatComboSettings(linkEndCreationData, UMLPackage.eINSTANCE.getLinkEndData_End());
        basePart.initEnd(endSettings);
        // set the button mode
        basePart.setEndButtonMode(ButtonsModeEnum.BROWSE);
      }
      basePart.setIsReplaceAll(linkEndCreationData.isReplaceAll());
      
      if (isAccessible(UmlViewsRepository.LinkEndCreationData.Properties.insertAt)) {
        // init part
        insertAtSettings = new EObjectFlatComboSettings(linkEndCreationData, UMLPackage.eINSTANCE.getLinkEndCreationData_InsertAt());
        basePart.initInsertAt(insertAtSettings);
        // set the button mode
        basePart.setInsertAtButtonMode(ButtonsModeEnum.BROWSE);
      }
      // init filters
      if (isAccessible(UmlViewsRepository.LinkEndCreationData.Properties.value)) {
        basePart.addFilterToValue(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof InputPin); //$NON-NLS-1$ 
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.LinkEndCreationData.Properties.end)) {
        basePart.addFilterToEnd(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof Property);
          }
          
        });
      }
      
      if (isAccessible(UmlViewsRepository.LinkEndCreationData.Properties.insertAt)) {
        basePart.addFilterToInsertAt(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof InputPin); //$NON-NLS-1$ 
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
		if (editorKey == UmlViewsRepository.LinkEndCreationData.Properties.value) {
			return UMLPackage.eINSTANCE.getLinkEndData_Value();
		}
		if (editorKey == UmlViewsRepository.LinkEndCreationData.Properties.end) {
			return UMLPackage.eINSTANCE.getLinkEndData_End();
		}
		if (editorKey == UmlViewsRepository.LinkEndCreationData.Properties.isReplaceAll) {
			return UMLPackage.eINSTANCE.getLinkEndCreationData_IsReplaceAll();
		}
		if (editorKey == UmlViewsRepository.LinkEndCreationData.Properties.insertAt) {
			return UMLPackage.eINSTANCE.getLinkEndCreationData_InsertAt();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
    LinkEndCreationData linkEndCreationData = (LinkEndCreationData)semanticObject;
    if (UmlViewsRepository.LinkEndCreationData.Properties.value == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        valueSettings.setToReference((InputPin)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        InputPin eObject = UMLFactory.eINSTANCE.createInputPin();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        valueSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.LinkEndCreationData.Properties.end == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        endSettings.setToReference((Property)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        Property eObject = UMLFactory.eINSTANCE.createProperty();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        endSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.LinkEndCreationData.Properties.isReplaceAll == event.getAffectedEditor()) {
      linkEndCreationData.setIsReplaceAll((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.LinkEndCreationData.Properties.insertAt == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        insertAtSettings.setToReference((InputPin)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        InputPin eObject = UMLFactory.eINSTANCE.createInputPin();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        insertAtSettings.setToReference(eObject);
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
			LinkEndCreationDataPropertiesEditionPart basePart = (LinkEndCreationDataPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getLinkEndData_Value().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LinkEndCreationData.Properties.value))
				basePart.setValue((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getLinkEndData_End().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LinkEndCreationData.Properties.end))
				basePart.setEnd((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getLinkEndCreationData_IsReplaceAll().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.LinkEndCreationData.Properties.isReplaceAll))
				basePart.setIsReplaceAll((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getLinkEndCreationData_InsertAt().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LinkEndCreationData.Properties.insertAt))
				basePart.setInsertAt((EObject)msg.getNewValue());
			
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
			UMLPackage.eINSTANCE.getLinkEndData_Value(),
			UMLPackage.eINSTANCE.getLinkEndData_End(),
			UMLPackage.eINSTANCE.getLinkEndCreationData_IsReplaceAll(),
			UMLPackage.eINSTANCE.getLinkEndCreationData_InsertAt()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
    return key == UmlViewsRepository.LinkEndCreationData.Properties.end || key == UmlViewsRepository.LinkEndCreationData.Properties.isReplaceAll;
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
        if (UmlViewsRepository.LinkEndCreationData.Properties.isReplaceAll == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getLinkEndCreationData_IsReplaceAll().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getLinkEndCreationData_IsReplaceAll().getEAttributeType(), newValue);
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
