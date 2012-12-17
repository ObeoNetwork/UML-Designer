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

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.resource.Resource;
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

import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.GeneralizationSet;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.OpaqueBehaviorPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class OpaqueBehaviorPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for owningTemplateParameter EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings owningTemplateParameterSettings;
	
	/**
	 * Settings for templateParameter EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings templateParameterSettings;
	
	/**
	 * Settings for powertypeExtent ReferencesTable
	 */
	private ReferencesTableSettings powertypeExtentSettings;
	
	/**
	 * Settings for redefinedClassifier ReferencesTable
	 */
	private ReferencesTableSettings redefinedClassifierSettings;
	
	/**
	 * Settings for representation EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings representationSettings;
	
	/**
	 * Settings for useCase ReferencesTable
	 */
	private ReferencesTableSettings useCaseSettings;
	
	/**
	 * Settings for classifierBehavior EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings classifierBehaviorSettings;
	
	/**
	 * Settings for redefinedBehavior ReferencesTable
	 */
	private ReferencesTableSettings redefinedBehaviorSettings;
	
	/**
	 * Settings for precondition ReferencesTable
	 */
	private ReferencesTableSettings preconditionSettings;
	
	/**
	 * Settings for postcondition ReferencesTable
	 */
	private ReferencesTableSettings postconditionSettings;
	
	/**
	 * Settings for specification EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings specificationSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public OpaqueBehaviorPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject opaqueBehavior, String editing_mode) {
    super(editingContext, opaqueBehavior, editing_mode);
    parts = new String[] { BASE_PART };
    repositoryKey = UmlViewsRepository.class;
    partKey = UmlViewsRepository.OpaqueBehavior.class;
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
      
      final OpaqueBehavior opaqueBehavior = (OpaqueBehavior)elt;
      final OpaqueBehaviorPropertiesEditionPart basePart = (OpaqueBehaviorPropertiesEditionPart)editingPart;
      // init values
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.name))
        basePart.setName(EEFConverterUtil.convertToString(UMLPackage.Literals.STRING, opaqueBehavior.getName()));
      
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.visibility)) {
        basePart.initVisibility(EEFUtils.choiceOfValues(opaqueBehavior, UMLPackage.eINSTANCE.getNamedElement_Visibility()), opaqueBehavior.getVisibility());
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.clientDependency)) {
        clientDependencySettings = new ReferencesTableSettings(opaqueBehavior, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
        basePart.initClientDependency(clientDependencySettings);
      }
      basePart.setIsLeaf(opaqueBehavior.isLeaf());
      
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.owningTemplateParameter)) {
        // init part
        owningTemplateParameterSettings = new EObjectFlatComboSettings(opaqueBehavior, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter());
        basePart.initOwningTemplateParameter(owningTemplateParameterSettings);
        // set the button mode
        basePart.setOwningTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.templateParameter)) {
        // init part
        templateParameterSettings = new EObjectFlatComboSettings(opaqueBehavior, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter());
        basePart.initTemplateParameter(templateParameterSettings);
        // set the button mode
        basePart.setTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
      }
      basePart.setIsAbstract(opaqueBehavior.isAbstract());
      
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.powertypeExtent)) {
        powertypeExtentSettings = new ReferencesTableSettings(opaqueBehavior, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
        basePart.initPowertypeExtent(powertypeExtentSettings);
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.redefinedClassifier)) {
        redefinedClassifierSettings = new ReferencesTableSettings(opaqueBehavior, UMLPackage.eINSTANCE.getClassifier_RedefinedClassifier());
        basePart.initRedefinedClassifier(redefinedClassifierSettings);
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.representation)) {
        // init part
        representationSettings = new EObjectFlatComboSettings(opaqueBehavior, UMLPackage.eINSTANCE.getClassifier_Representation());
        basePart.initRepresentation(representationSettings);
        // set the button mode
        basePart.setRepresentationButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.useCase)) {
        useCaseSettings = new ReferencesTableSettings(opaqueBehavior, UMLPackage.eINSTANCE.getClassifier_UseCase());
        basePart.initUseCase(useCaseSettings);
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.classifierBehavior)) {
        // init part
        classifierBehaviorSettings = new EObjectFlatComboSettings(opaqueBehavior, UMLPackage.eINSTANCE.getBehavioredClassifier_ClassifierBehavior());
        basePart.initClassifierBehavior(classifierBehaviorSettings);
        // set the button mode
        basePart.setClassifierBehaviorButtonMode(ButtonsModeEnum.BROWSE);
      }
      basePart.setIsActive(opaqueBehavior.isActive());
      
      basePart.setIsReentrant(opaqueBehavior.isReentrant());
      
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.redefinedBehavior)) {
        redefinedBehaviorSettings = new ReferencesTableSettings(opaqueBehavior, UMLPackage.eINSTANCE.getBehavior_RedefinedBehavior());
        basePart.initRedefinedBehavior(redefinedBehaviorSettings);
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.precondition)) {
        preconditionSettings = new ReferencesTableSettings(opaqueBehavior, UMLPackage.eINSTANCE.getBehavior_Precondition());
        basePart.initPrecondition(preconditionSettings);
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.postcondition)) {
        postconditionSettings = new ReferencesTableSettings(opaqueBehavior, UMLPackage.eINSTANCE.getBehavior_Postcondition());
        basePart.initPostcondition(postconditionSettings);
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.specification)) {
        // init part
        specificationSettings = new EObjectFlatComboSettings(opaqueBehavior, UMLPackage.eINSTANCE.getBehavior_Specification());
        basePart.initSpecification(specificationSettings);
        // set the button mode
        basePart.setSpecificationButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.body))
        basePart.setBody(opaqueBehavior.getBodies());
      
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.language))
        basePart.setLanguage(opaqueBehavior.getLanguages());
      
      // init filters
      
      
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.clientDependency)) {
        basePart.addFilterToClientDependency(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInClientDependencyTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToClientDependency(new EObjectFilter(UMLPackage.Literals.DEPENDENCY));
      }
      
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.owningTemplateParameter)) {
        basePart.addFilterToOwningTemplateParameter(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof TemplateParameter); //$NON-NLS-1$ 
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.templateParameter)) {
        basePart.addFilterToTemplateParameter(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof TemplateParameter); //$NON-NLS-1$ 
          }
          
        });
      }
      
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.powertypeExtent)) {
        basePart.addFilterToPowertypeExtent(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInPowertypeExtentTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToPowertypeExtent(new EObjectFilter(UMLPackage.Literals.GENERALIZATION_SET));
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.redefinedClassifier)) {
        basePart.addFilterToRedefinedClassifier(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInRedefinedClassifierTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToRedefinedClassifier(new EObjectFilter(UMLPackage.Literals.CLASSIFIER));
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.representation)) {
        basePart.addFilterToRepresentation(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof CollaborationUse); //$NON-NLS-1$ 
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.useCase)) {
        basePart.addFilterToUseCase(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInUseCaseTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToUseCase(new EObjectFilter(UMLPackage.Literals.USE_CASE));
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.classifierBehavior)) {
        basePart.addFilterToClassifierBehavior(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof Behavior); //$NON-NLS-1$ 
          }
          
        });
      }
      
      
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.redefinedBehavior)) {
        basePart.addFilterToRedefinedBehavior(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInRedefinedBehaviorTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToRedefinedBehavior(new EObjectFilter(UMLPackage.Literals.BEHAVIOR));
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.precondition)) {
        basePart.addFilterToPrecondition(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInPreconditionTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToPrecondition(new EObjectFilter(UMLPackage.Literals.CONSTRAINT));
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.postcondition)) {
        basePart.addFilterToPostcondition(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInPostconditionTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToPostcondition(new EObjectFilter(UMLPackage.Literals.CONSTRAINT));
      }
      if (isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.specification)) {
        basePart.addFilterToSpecification(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof BehavioralFeature); //$NON-NLS-1$ 
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
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.isLeaf) {
			return UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.owningTemplateParameter) {
			return UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.templateParameter) {
			return UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.isAbstract) {
			return UMLPackage.eINSTANCE.getClassifier_IsAbstract();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.powertypeExtent) {
			return UMLPackage.eINSTANCE.getClassifier_PowertypeExtent();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.redefinedClassifier) {
			return UMLPackage.eINSTANCE.getClassifier_RedefinedClassifier();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.representation) {
			return UMLPackage.eINSTANCE.getClassifier_Representation();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.useCase) {
			return UMLPackage.eINSTANCE.getClassifier_UseCase();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.classifierBehavior) {
			return UMLPackage.eINSTANCE.getBehavioredClassifier_ClassifierBehavior();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.isActive) {
			return UMLPackage.eINSTANCE.getClass_IsActive();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.isReentrant) {
			return UMLPackage.eINSTANCE.getBehavior_IsReentrant();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.redefinedBehavior) {
			return UMLPackage.eINSTANCE.getBehavior_RedefinedBehavior();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.precondition) {
			return UMLPackage.eINSTANCE.getBehavior_Precondition();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.postcondition) {
			return UMLPackage.eINSTANCE.getBehavior_Postcondition();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.specification) {
			return UMLPackage.eINSTANCE.getBehavior_Specification();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.body) {
			return UMLPackage.eINSTANCE.getOpaqueBehavior_Body();
		}
		if (editorKey == UmlViewsRepository.OpaqueBehavior.Properties.language) {
			return UMLPackage.eINSTANCE.getOpaqueBehavior_Language();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
    OpaqueBehavior opaqueBehavior = (OpaqueBehavior)semanticObject;
    if (UmlViewsRepository.OpaqueBehavior.Properties.name == event.getAffectedEditor()) {
      opaqueBehavior.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.visibility == event.getAffectedEditor()) {
      opaqueBehavior.setVisibility((VisibilityKind)event.getNewValue());
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.clientDependency == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof Dependency) {
          clientDependencySettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        clientDependencySettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        clientDependencySettings.move(event.getNewIndex(), (Dependency) event.getNewValue());
      }
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.isLeaf == event.getAffectedEditor()) {
      opaqueBehavior.setIsLeaf((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.owningTemplateParameter == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        owningTemplateParameterSettings.setToReference((TemplateParameter)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        TemplateParameter eObject = UMLFactory.eINSTANCE.createTemplateParameter();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        owningTemplateParameterSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.templateParameter == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        templateParameterSettings.setToReference((TemplateParameter)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        TemplateParameter eObject = UMLFactory.eINSTANCE.createTemplateParameter();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        templateParameterSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.isAbstract == event.getAffectedEditor()) {
      opaqueBehavior.setIsAbstract((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.powertypeExtent == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof GeneralizationSet) {
          powertypeExtentSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        powertypeExtentSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        powertypeExtentSettings.move(event.getNewIndex(), (GeneralizationSet) event.getNewValue());
      }
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.redefinedClassifier == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof Classifier) {
          redefinedClassifierSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        redefinedClassifierSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        redefinedClassifierSettings.move(event.getNewIndex(), (Classifier) event.getNewValue());
      }
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.representation == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        representationSettings.setToReference((CollaborationUse)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        CollaborationUse eObject = UMLFactory.eINSTANCE.createCollaborationUse();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        representationSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.useCase == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof UseCase) {
          useCaseSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        useCaseSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        useCaseSettings.move(event.getNewIndex(), (UseCase) event.getNewValue());
      }
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.classifierBehavior == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        classifierBehaviorSettings.setToReference((Behavior)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, classifierBehaviorSettings, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy instanceof CreateEditingPolicy) {
            policy.execute();
          }
        }
      }
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.isActive == event.getAffectedEditor()) {
      opaqueBehavior.setIsActive((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.isReentrant == event.getAffectedEditor()) {
      opaqueBehavior.setIsReentrant((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.redefinedBehavior == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof Behavior) {
          redefinedBehaviorSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        redefinedBehaviorSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        redefinedBehaviorSettings.move(event.getNewIndex(), (Behavior) event.getNewValue());
      }
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.precondition == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof Constraint) {
          preconditionSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        preconditionSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        preconditionSettings.move(event.getNewIndex(), (Constraint) event.getNewValue());
      }
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.postcondition == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof Constraint) {
          postconditionSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        postconditionSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        postconditionSettings.move(event.getNewIndex(), (Constraint) event.getNewValue());
      }
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.specification == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        specificationSettings.setToReference((BehavioralFeature)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, specificationSettings, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy instanceof CreateEditingPolicy) {
            policy.execute();
          }
        }
      }
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.body == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        opaqueBehavior.getBodies().clear();
        opaqueBehavior.getBodies().addAll(((EList) event.getNewValue()));
      }
    }
    if (UmlViewsRepository.OpaqueBehavior.Properties.language == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        opaqueBehavior.getLanguages().clear();
        opaqueBehavior.getLanguages().addAll(((EList) event.getNewValue()));
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
			OpaqueBehaviorPropertiesEditionPart basePart = (OpaqueBehaviorPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.owningTemplateParameter))
				basePart.setOwningTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.templateParameter))
				basePart.setTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getClassifier_IsAbstract().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.isAbstract))
				basePart.setIsAbstract((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getClassifier_PowertypeExtent().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.powertypeExtent))
				basePart.updatePowertypeExtent();
			if (UMLPackage.eINSTANCE.getClassifier_RedefinedClassifier().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.redefinedClassifier))
				basePart.updateRedefinedClassifier();
			if (UMLPackage.eINSTANCE.getClassifier_Representation().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.representation))
				basePart.setRepresentation((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getClassifier_UseCase().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.useCase))
				basePart.updateUseCase();
			if (UMLPackage.eINSTANCE.getBehavioredClassifier_ClassifierBehavior().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.classifierBehavior))
				basePart.setClassifierBehavior((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getClass_IsActive().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.isActive))
				basePart.setIsActive((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavior_IsReentrant().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.isReentrant))
				basePart.setIsReentrant((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavior_RedefinedBehavior().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.redefinedBehavior))
				basePart.updateRedefinedBehavior();
			if (UMLPackage.eINSTANCE.getBehavior_Precondition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.precondition))
				basePart.updatePrecondition();
			if (UMLPackage.eINSTANCE.getBehavior_Postcondition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.postcondition))
				basePart.updatePostcondition();
			if (UMLPackage.eINSTANCE.getBehavior_Specification().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.specification))
				basePart.setSpecification((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getOpaqueBehavior_Body().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.body)) {
				basePart.setBody((EList<?>)msg.getNewValue());
			}
			
			if (UMLPackage.eINSTANCE.getOpaqueBehavior_Language().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.OpaqueBehavior.Properties.language)) {
				basePart.setLanguage((EList<?>)msg.getNewValue());
			}
			
			
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
			UMLPackage.eINSTANCE.getNamedElement_ClientDependency(),
			UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(),
			UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(),
			UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(),
			UMLPackage.eINSTANCE.getClassifier_IsAbstract(),
			UMLPackage.eINSTANCE.getClassifier_PowertypeExtent(),
			UMLPackage.eINSTANCE.getClassifier_RedefinedClassifier(),
			UMLPackage.eINSTANCE.getClassifier_Representation(),
			UMLPackage.eINSTANCE.getClassifier_UseCase(),
			UMLPackage.eINSTANCE.getBehavioredClassifier_ClassifierBehavior(),
			UMLPackage.eINSTANCE.getClass_IsActive(),
			UMLPackage.eINSTANCE.getBehavior_IsReentrant(),
			UMLPackage.eINSTANCE.getBehavior_RedefinedBehavior(),
			UMLPackage.eINSTANCE.getBehavior_Precondition(),
			UMLPackage.eINSTANCE.getBehavior_Postcondition(),
			UMLPackage.eINSTANCE.getBehavior_Specification(),
			UMLPackage.eINSTANCE.getOpaqueBehavior_Body(),
			UMLPackage.eINSTANCE.getOpaqueBehavior_Language()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
    return key == UmlViewsRepository.OpaqueBehavior.Properties.isLeaf || key == UmlViewsRepository.OpaqueBehavior.Properties.isAbstract || key == UmlViewsRepository.OpaqueBehavior.Properties.isActive || key == UmlViewsRepository.OpaqueBehavior.Properties.isReentrant;
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
        if (UmlViewsRepository.OpaqueBehavior.Properties.name == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.OpaqueBehavior.Properties.visibility == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.OpaqueBehavior.Properties.isLeaf == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.OpaqueBehavior.Properties.isAbstract == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getClassifier_IsAbstract().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getClassifier_IsAbstract().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.OpaqueBehavior.Properties.isActive == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getClass_IsActive().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getClass_IsActive().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.OpaqueBehavior.Properties.isReentrant == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getBehavior_IsReentrant().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getBehavior_IsReentrant().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.OpaqueBehavior.Properties.body == event.getAffectedEditor()) {
          BasicDiagnostic chain = new BasicDiagnostic();
          for (Iterator iterator = ((List)event.getNewValue()).iterator(); iterator.hasNext();) {
            chain.add(Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getOpaqueBehavior_Body().getEAttributeType(), iterator.next()));
          }
          ret = chain;
        }
        if (UmlViewsRepository.OpaqueBehavior.Properties.language == event.getAffectedEditor()) {
          BasicDiagnostic chain = new BasicDiagnostic();
          for (Iterator iterator = ((List)event.getNewValue()).iterator(); iterator.hasNext();) {
            chain.add(Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getOpaqueBehavior_Language().getEAttributeType(), iterator.next()));
          }
          ret = chain;
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
