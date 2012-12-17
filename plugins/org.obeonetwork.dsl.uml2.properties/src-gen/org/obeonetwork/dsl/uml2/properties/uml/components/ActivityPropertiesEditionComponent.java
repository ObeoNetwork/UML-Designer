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

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.GeneralizationSet;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ActivityPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

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
	 * Settings for partition ReferencesTable
	 */
	private ReferencesTableSettings partitionSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public ActivityPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject activity, String editing_mode) {
    super(editingContext, activity, editing_mode);
    parts = new String[] { BASE_PART };
    repositoryKey = UmlViewsRepository.class;
    partKey = UmlViewsRepository.Activity.class;
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
      
      final Activity activity = (Activity)elt;
      final ActivityPropertiesEditionPart basePart = (ActivityPropertiesEditionPart)editingPart;
      // init values
      if (isAccessible(UmlViewsRepository.Activity.Properties.name))
        basePart.setName(EEFConverterUtil.convertToString(UMLPackage.Literals.STRING, activity.getName()));
      
      if (isAccessible(UmlViewsRepository.Activity.Properties.visibility)) {
        basePart.initVisibility(EEFUtils.choiceOfValues(activity, UMLPackage.eINSTANCE.getNamedElement_Visibility()), activity.getVisibility());
      }
      if (isAccessible(UmlViewsRepository.Activity.Properties.clientDependency)) {
        clientDependencySettings = new ReferencesTableSettings(activity, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
        basePart.initClientDependency(clientDependencySettings);
      }
      basePart.setIsLeaf(activity.isLeaf());
      
      if (isAccessible(UmlViewsRepository.Activity.Properties.owningTemplateParameter)) {
        // init part
        owningTemplateParameterSettings = new EObjectFlatComboSettings(activity, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter());
        basePart.initOwningTemplateParameter(owningTemplateParameterSettings);
        // set the button mode
        basePart.setOwningTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.Activity.Properties.templateParameter)) {
        // init part
        templateParameterSettings = new EObjectFlatComboSettings(activity, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter());
        basePart.initTemplateParameter(templateParameterSettings);
        // set the button mode
        basePart.setTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
      }
      basePart.setIsAbstract(activity.isAbstract());
      
      if (isAccessible(UmlViewsRepository.Activity.Properties.powertypeExtent)) {
        powertypeExtentSettings = new ReferencesTableSettings(activity, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
        basePart.initPowertypeExtent(powertypeExtentSettings);
      }
      if (isAccessible(UmlViewsRepository.Activity.Properties.redefinedClassifier)) {
        redefinedClassifierSettings = new ReferencesTableSettings(activity, UMLPackage.eINSTANCE.getClassifier_RedefinedClassifier());
        basePart.initRedefinedClassifier(redefinedClassifierSettings);
      }
      if (isAccessible(UmlViewsRepository.Activity.Properties.representation)) {
        // init part
        representationSettings = new EObjectFlatComboSettings(activity, UMLPackage.eINSTANCE.getClassifier_Representation());
        basePart.initRepresentation(representationSettings);
        // set the button mode
        basePart.setRepresentationButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.Activity.Properties.useCase)) {
        useCaseSettings = new ReferencesTableSettings(activity, UMLPackage.eINSTANCE.getClassifier_UseCase());
        basePart.initUseCase(useCaseSettings);
      }
      if (isAccessible(UmlViewsRepository.Activity.Properties.classifierBehavior)) {
        // init part
        classifierBehaviorSettings = new EObjectFlatComboSettings(activity, UMLPackage.eINSTANCE.getBehavioredClassifier_ClassifierBehavior());
        basePart.initClassifierBehavior(classifierBehaviorSettings);
        // set the button mode
        basePart.setClassifierBehaviorButtonMode(ButtonsModeEnum.BROWSE);
      }
      basePart.setIsActive(activity.isActive());
      
      basePart.setIsReentrant(activity.isReentrant());
      
      if (isAccessible(UmlViewsRepository.Activity.Properties.redefinedBehavior)) {
        redefinedBehaviorSettings = new ReferencesTableSettings(activity, UMLPackage.eINSTANCE.getBehavior_RedefinedBehavior());
        basePart.initRedefinedBehavior(redefinedBehaviorSettings);
      }
      if (isAccessible(UmlViewsRepository.Activity.Properties.precondition)) {
        preconditionSettings = new ReferencesTableSettings(activity, UMLPackage.eINSTANCE.getBehavior_Precondition());
        basePart.initPrecondition(preconditionSettings);
      }
      if (isAccessible(UmlViewsRepository.Activity.Properties.postcondition)) {
        postconditionSettings = new ReferencesTableSettings(activity, UMLPackage.eINSTANCE.getBehavior_Postcondition());
        basePart.initPostcondition(postconditionSettings);
      }
      if (isAccessible(UmlViewsRepository.Activity.Properties.specification)) {
        // init part
        specificationSettings = new EObjectFlatComboSettings(activity, UMLPackage.eINSTANCE.getBehavior_Specification());
        basePart.initSpecification(specificationSettings);
        // set the button mode
        basePart.setSpecificationButtonMode(ButtonsModeEnum.BROWSE);
      }
      basePart.setIsReadOnly(activity.isReadOnly());
      
      if (isAccessible(UmlViewsRepository.Activity.Properties.partition)) {
        partitionSettings = new ReferencesTableSettings(activity, UMLPackage.eINSTANCE.getActivity_Partition());
        basePart.initPartition(partitionSettings);
      }
      basePart.setIsSingleExecution(activity.isSingleExecution());
      
      // init filters
      
      
      if (isAccessible(UmlViewsRepository.Activity.Properties.clientDependency)) {
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
      
      if (isAccessible(UmlViewsRepository.Activity.Properties.owningTemplateParameter)) {
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
      if (isAccessible(UmlViewsRepository.Activity.Properties.templateParameter)) {
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
      
      if (isAccessible(UmlViewsRepository.Activity.Properties.powertypeExtent)) {
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
      if (isAccessible(UmlViewsRepository.Activity.Properties.redefinedClassifier)) {
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
      if (isAccessible(UmlViewsRepository.Activity.Properties.representation)) {
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
      if (isAccessible(UmlViewsRepository.Activity.Properties.useCase)) {
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
      if (isAccessible(UmlViewsRepository.Activity.Properties.classifierBehavior)) {
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
      
      
      if (isAccessible(UmlViewsRepository.Activity.Properties.redefinedBehavior)) {
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
      if (isAccessible(UmlViewsRepository.Activity.Properties.precondition)) {
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
      if (isAccessible(UmlViewsRepository.Activity.Properties.postcondition)) {
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
      if (isAccessible(UmlViewsRepository.Activity.Properties.specification)) {
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
      
      if (isAccessible(UmlViewsRepository.Activity.Properties.partition)) {
        basePart.addFilterToPartition(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInPartitionTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToPartition(new EObjectFilter(UMLPackage.Literals.ACTIVITY_PARTITION));
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
		if (editorKey == UmlViewsRepository.Activity.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.isLeaf) {
			return UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.owningTemplateParameter) {
			return UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.templateParameter) {
			return UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.isAbstract) {
			return UMLPackage.eINSTANCE.getClassifier_IsAbstract();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.powertypeExtent) {
			return UMLPackage.eINSTANCE.getClassifier_PowertypeExtent();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.redefinedClassifier) {
			return UMLPackage.eINSTANCE.getClassifier_RedefinedClassifier();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.representation) {
			return UMLPackage.eINSTANCE.getClassifier_Representation();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.useCase) {
			return UMLPackage.eINSTANCE.getClassifier_UseCase();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.classifierBehavior) {
			return UMLPackage.eINSTANCE.getBehavioredClassifier_ClassifierBehavior();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.isActive) {
			return UMLPackage.eINSTANCE.getClass_IsActive();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.isReentrant) {
			return UMLPackage.eINSTANCE.getBehavior_IsReentrant();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.redefinedBehavior) {
			return UMLPackage.eINSTANCE.getBehavior_RedefinedBehavior();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.precondition) {
			return UMLPackage.eINSTANCE.getBehavior_Precondition();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.postcondition) {
			return UMLPackage.eINSTANCE.getBehavior_Postcondition();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.specification) {
			return UMLPackage.eINSTANCE.getBehavior_Specification();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.isReadOnly) {
			return UMLPackage.eINSTANCE.getActivity_IsReadOnly();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.partition) {
			return UMLPackage.eINSTANCE.getActivity_Partition();
		}
		if (editorKey == UmlViewsRepository.Activity.Properties.isSingleExecution) {
			return UMLPackage.eINSTANCE.getActivity_IsSingleExecution();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
    Activity activity = (Activity)semanticObject;
    if (UmlViewsRepository.Activity.Properties.name == event.getAffectedEditor()) {
      activity.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
    }
    if (UmlViewsRepository.Activity.Properties.visibility == event.getAffectedEditor()) {
      activity.setVisibility((VisibilityKind)event.getNewValue());
    }
    if (UmlViewsRepository.Activity.Properties.clientDependency == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Activity.Properties.isLeaf == event.getAffectedEditor()) {
      activity.setIsLeaf((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.Activity.Properties.owningTemplateParameter == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Activity.Properties.templateParameter == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Activity.Properties.isAbstract == event.getAffectedEditor()) {
      activity.setIsAbstract((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.Activity.Properties.powertypeExtent == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Activity.Properties.redefinedClassifier == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Activity.Properties.representation == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Activity.Properties.useCase == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Activity.Properties.classifierBehavior == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Activity.Properties.isActive == event.getAffectedEditor()) {
      activity.setIsActive((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.Activity.Properties.isReentrant == event.getAffectedEditor()) {
      activity.setIsReentrant((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.Activity.Properties.redefinedBehavior == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Activity.Properties.precondition == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Activity.Properties.postcondition == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Activity.Properties.specification == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Activity.Properties.isReadOnly == event.getAffectedEditor()) {
      activity.setIsReadOnly((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.Activity.Properties.partition == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof ActivityPartition) {
          partitionSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        partitionSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        partitionSettings.move(event.getNewIndex(), (ActivityPartition) event.getNewValue());
      }
    }
    if (UmlViewsRepository.Activity.Properties.isSingleExecution == event.getAffectedEditor()) {
      activity.setIsSingleExecution((Boolean)event.getNewValue());
    }
  }

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		super.updatePart(msg);
		if (editingPart.isVisible()) {
			ActivityPropertiesEditionPart basePart = (ActivityPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Activity.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.Activity.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Activity.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Activity.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Activity.Properties.owningTemplateParameter))
				basePart.setOwningTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Activity.Properties.templateParameter))
				basePart.setTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getClassifier_IsAbstract().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Activity.Properties.isAbstract))
				basePart.setIsAbstract((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getClassifier_PowertypeExtent().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Activity.Properties.powertypeExtent))
				basePart.updatePowertypeExtent();
			if (UMLPackage.eINSTANCE.getClassifier_RedefinedClassifier().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Activity.Properties.redefinedClassifier))
				basePart.updateRedefinedClassifier();
			if (UMLPackage.eINSTANCE.getClassifier_Representation().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Activity.Properties.representation))
				basePart.setRepresentation((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getClassifier_UseCase().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Activity.Properties.useCase))
				basePart.updateUseCase();
			if (UMLPackage.eINSTANCE.getBehavioredClassifier_ClassifierBehavior().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Activity.Properties.classifierBehavior))
				basePart.setClassifierBehavior((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getClass_IsActive().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Activity.Properties.isActive))
				basePart.setIsActive((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavior_IsReentrant().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Activity.Properties.isReentrant))
				basePart.setIsReentrant((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavior_RedefinedBehavior().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Activity.Properties.redefinedBehavior))
				basePart.updateRedefinedBehavior();
			if (UMLPackage.eINSTANCE.getBehavior_Precondition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Activity.Properties.precondition))
				basePart.updatePrecondition();
			if (UMLPackage.eINSTANCE.getBehavior_Postcondition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Activity.Properties.postcondition))
				basePart.updatePostcondition();
			if (UMLPackage.eINSTANCE.getBehavior_Specification().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Activity.Properties.specification))
				basePart.setSpecification((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActivity_IsReadOnly().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Activity.Properties.isReadOnly))
				basePart.setIsReadOnly((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getActivity_Partition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Activity.Properties.partition))
				basePart.updatePartition();
			if (UMLPackage.eINSTANCE.getActivity_IsSingleExecution().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Activity.Properties.isSingleExecution))
				basePart.setIsSingleExecution((Boolean)msg.getNewValue());
			
			
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
			UMLPackage.eINSTANCE.getActivity_IsReadOnly(),
			UMLPackage.eINSTANCE.getActivity_Partition(),
			UMLPackage.eINSTANCE.getActivity_IsSingleExecution()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
    return key == UmlViewsRepository.Activity.Properties.isLeaf || key == UmlViewsRepository.Activity.Properties.isAbstract || key == UmlViewsRepository.Activity.Properties.isActive || key == UmlViewsRepository.Activity.Properties.isReentrant || key == UmlViewsRepository.Activity.Properties.isReadOnly || key == UmlViewsRepository.Activity.Properties.isSingleExecution;
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
        if (UmlViewsRepository.Activity.Properties.name == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.Activity.Properties.visibility == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.Activity.Properties.isLeaf == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.Activity.Properties.isAbstract == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getClassifier_IsAbstract().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getClassifier_IsAbstract().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.Activity.Properties.isActive == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getClass_IsActive().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getClass_IsActive().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.Activity.Properties.isReentrant == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getBehavior_IsReentrant().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getBehavior_IsReentrant().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.Activity.Properties.isReadOnly == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getActivity_IsReadOnly().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getActivity_IsReadOnly().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.Activity.Properties.isSingleExecution == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getActivity_IsSingleExecution().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getActivity_IsSingleExecution().getEAttributeType(), newValue);
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
