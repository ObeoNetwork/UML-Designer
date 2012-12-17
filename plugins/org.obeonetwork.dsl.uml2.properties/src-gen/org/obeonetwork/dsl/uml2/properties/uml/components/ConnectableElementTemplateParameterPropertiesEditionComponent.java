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

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.uml2.uml.ConnectableElementTemplateParameter;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectableElementTemplateParameterPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ConnectableElementTemplateParameterPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for signature EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings signatureSettings;
	
	/**
	 * Settings for parameteredElement EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings parameteredElementSettings;
	
	/**
	 * Settings for default EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings default_Settings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public ConnectableElementTemplateParameterPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject connectableElementTemplateParameter, String editing_mode) {
    super(editingContext, connectableElementTemplateParameter, editing_mode);
    parts = new String[] { BASE_PART };
    repositoryKey = UmlViewsRepository.class;
    partKey = UmlViewsRepository.ConnectableElementTemplateParameter.class;
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
      
      final ConnectableElementTemplateParameter connectableElementTemplateParameter = (ConnectableElementTemplateParameter)elt;
      final ConnectableElementTemplateParameterPropertiesEditionPart basePart = (ConnectableElementTemplateParameterPropertiesEditionPart)editingPart;
      // init values
      if (isAccessible(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature)) {
        // init part
        signatureSettings = new EObjectFlatComboSettings(connectableElementTemplateParameter, UMLPackage.eINSTANCE.getTemplateParameter_Signature());
        basePart.initSignature(signatureSettings);
        // set the button mode
        basePart.setSignatureButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement)) {
        // init part
        parameteredElementSettings = new EObjectFlatComboSettings(connectableElementTemplateParameter, UMLPackage.eINSTANCE.getTemplateParameter_ParameteredElement());
        basePart.initParameteredElement(parameteredElementSettings);
        // set the button mode
        basePart.setParameteredElementButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.default_)) {
        // init part
        default_Settings = new EObjectFlatComboSettings(connectableElementTemplateParameter, UMLPackage.eINSTANCE.getTemplateParameter_Default());
        basePart.initDefault_(default_Settings);
        // set the button mode
        basePart.setDefault_ButtonMode(ButtonsModeEnum.BROWSE);
      }
      // init filters
      if (isAccessible(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature)) {
        basePart.addFilterToSignature(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof TemplateSignature);
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement)) {
        basePart.addFilterToParameteredElement(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof ParameterableElement);
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.default_)) {
        basePart.addFilterToDefault_(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof ParameterableElement); //$NON-NLS-1$ 
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
		if (editorKey == UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature) {
			return UMLPackage.eINSTANCE.getTemplateParameter_Signature();
		}
		if (editorKey == UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement) {
			return UMLPackage.eINSTANCE.getTemplateParameter_ParameteredElement();
		}
		if (editorKey == UmlViewsRepository.ConnectableElementTemplateParameter.Properties.default_) {
			return UMLPackage.eINSTANCE.getTemplateParameter_Default();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
    ConnectableElementTemplateParameter connectableElementTemplateParameter = (ConnectableElementTemplateParameter)semanticObject;
    if (UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        signatureSettings.setToReference((TemplateSignature)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        TemplateSignature eObject = UMLFactory.eINSTANCE.createTemplateSignature();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        signatureSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        parameteredElementSettings.setToReference((ParameterableElement)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, parameteredElementSettings, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy instanceof CreateEditingPolicy) {
            policy.execute();
          }
        }
      }
    }
    if (UmlViewsRepository.ConnectableElementTemplateParameter.Properties.default_ == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        default_Settings.setToReference((ParameterableElement)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, default_Settings, editingContext.getAdapterFactory());
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
			ConnectableElementTemplateParameterPropertiesEditionPart basePart = (ConnectableElementTemplateParameterPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getTemplateParameter_Signature().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature))
				basePart.setSignature((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTemplateParameter_ParameteredElement().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement))
				basePart.setParameteredElement((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTemplateParameter_Default().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ConnectableElementTemplateParameter.Properties.default_))
				basePart.setDefault_((EObject)msg.getNewValue());
			
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
			UMLPackage.eINSTANCE.getTemplateParameter_Signature(),
			UMLPackage.eINSTANCE.getTemplateParameter_ParameteredElement(),
			UMLPackage.eINSTANCE.getTemplateParameter_Default()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
    return key == UmlViewsRepository.ConnectableElementTemplateParameter.Properties.signature || key == UmlViewsRepository.ConnectableElementTemplateParameter.Properties.parameteredElement;
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
      } catch (IllegalArgumentException iae) {
        ret = BasicDiagnostic.toDiagnostic(iae);
      } catch (WrappedException we) {
        ret = BasicDiagnostic.toDiagnostic(we);
      }
    }
    return ret;
  }


	

}
