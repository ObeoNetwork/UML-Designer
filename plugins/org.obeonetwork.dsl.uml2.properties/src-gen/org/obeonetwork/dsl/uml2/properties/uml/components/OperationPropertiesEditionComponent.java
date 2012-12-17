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

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallConcurrencyKind;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.OperationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class OperationPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for method ReferencesTable
	 */
	private ReferencesTableSettings methodSettings;
	
	/**
	 * Settings for raisedException ReferencesTable
	 */
	private ReferencesTableSettings raisedExceptionSettings;
	
	/**
	 * Settings for owningTemplateParameter EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings owningTemplateParameterSettings;
	
	/**
	 * Settings for templateParameter EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings templateParameterSettings;
	
	/**
	 * Settings for interface EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings interface_Settings;
	
	/**
	 * Settings for class EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings class_Settings;
	
	/**
	 * Settings for precondition ReferencesTable
	 */
	private ReferencesTableSettings preconditionSettings;
	
	/**
	 * Settings for postcondition ReferencesTable
	 */
	private ReferencesTableSettings postconditionSettings;
	
	/**
	 * Settings for redefinedOperation ReferencesTable
	 */
	private ReferencesTableSettings redefinedOperationSettings;
	
	/**
	 * Settings for datatype EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings datatypeSettings;
	
	/**
	 * Settings for bodyCondition EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings bodyConditionSettings;
	
	
	/**
   * Settings for parameters ReferencesTable
   */
  protected ReferencesTableSettings parametersSettings;


  /**
	 * Default constructor
	 * @generated
	 */
	public OperationPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject operation, String editing_mode) {
    super(editingContext, operation, editing_mode);
    parts = new String[] { BASE_PART };
    repositoryKey = UmlViewsRepository.class;
    partKey = UmlViewsRepository.Operation.class;
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
      final OperationPropertiesEditionPart basePart = (OperationPropertiesEditionPart)editingPart;
      // init values
      if (isAccessible(UmlViewsRepository.Operation.Properties.name))
        basePart.setName(EEFConverterUtil.convertToString(UMLPackage.Literals.STRING, operation.getName()));
      
      if (isAccessible(UmlViewsRepository.Operation.Properties.visibility)) {
        basePart.initVisibility(EEFUtils.choiceOfValues(operation, UMLPackage.eINSTANCE.getNamedElement_Visibility()), operation.getVisibility());
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.clientDependency)) {
        clientDependencySettings = new ReferencesTableSettings(operation, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
        basePart.initClientDependency(clientDependencySettings);
      }
      basePart.setIsLeaf(operation.isLeaf());
      
      basePart.setIsStatic(operation.isStatic());
      
      basePart.setIsAbstract(operation.isAbstract());
      
      if (isAccessible(UmlViewsRepository.Operation.Properties.method)) {
        methodSettings = new ReferencesTableSettings(operation, UMLPackage.eINSTANCE.getBehavioralFeature_Method());
        basePart.initMethod(methodSettings);
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.concurrency)) {
        basePart.initConcurrency(EEFUtils.choiceOfValues(operation, UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency()), operation.getConcurrency());
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.raisedException)) {
        raisedExceptionSettings = new ReferencesTableSettings(operation, UMLPackage.eINSTANCE.getBehavioralFeature_RaisedException());
        basePart.initRaisedException(raisedExceptionSettings);
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.owningTemplateParameter)) {
        // init part
        owningTemplateParameterSettings = new EObjectFlatComboSettings(operation, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter());
        basePart.initOwningTemplateParameter(owningTemplateParameterSettings);
        // set the button mode
        basePart.setOwningTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.templateParameter)) {
        // init part
        templateParameterSettings = new EObjectFlatComboSettings(operation, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter());
        basePart.initTemplateParameter(templateParameterSettings);
        // set the button mode
        basePart.setTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.interface_)) {
        // init part
        interface_Settings = new EObjectFlatComboSettings(operation, UMLPackage.eINSTANCE.getOperation_Interface());
        basePart.initInterface_(interface_Settings);
        // set the button mode
        basePart.setInterface_ButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.class_)) {
        // init part
        class_Settings = new EObjectFlatComboSettings(operation, UMLPackage.eINSTANCE.getOperation_Class());
        basePart.initClass_(class_Settings);
        // set the button mode
        basePart.setClass_ButtonMode(ButtonsModeEnum.BROWSE);
      }
      basePart.setIsQuery(operation.isQuery());
      
      if (isAccessible(UmlViewsRepository.Operation.Properties.precondition)) {
        preconditionSettings = new ReferencesTableSettings(operation, UMLPackage.eINSTANCE.getOperation_Precondition());
        basePart.initPrecondition(preconditionSettings);
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.postcondition)) {
        postconditionSettings = new ReferencesTableSettings(operation, UMLPackage.eINSTANCE.getOperation_Postcondition());
        basePart.initPostcondition(postconditionSettings);
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.redefinedOperation)) {
        redefinedOperationSettings = new ReferencesTableSettings(operation, UMLPackage.eINSTANCE.getOperation_RedefinedOperation());
        basePart.initRedefinedOperation(redefinedOperationSettings);
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.datatype)) {
        // init part
        datatypeSettings = new EObjectFlatComboSettings(operation, UMLPackage.eINSTANCE.getOperation_Datatype());
        basePart.initDatatype(datatypeSettings);
        // set the button mode
        basePart.setDatatypeButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.bodyCondition)) {
        // init part
        bodyConditionSettings = new EObjectFlatComboSettings(operation, UMLPackage.eINSTANCE.getOperation_BodyCondition());
        basePart.initBodyCondition(bodyConditionSettings);
        // set the button mode
        basePart.setBodyConditionButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.parameters)) {
        parametersSettings = new ReferencesTableSettings(operation, UMLPackage.eINSTANCE.getBehavioralFeature_OwnedParameter());
        basePart.initParameters(parametersSettings);
      }
      // init filters
      
      
      if (isAccessible(UmlViewsRepository.Operation.Properties.clientDependency)) {
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
      
      
      
      if (isAccessible(UmlViewsRepository.Operation.Properties.method)) {
        basePart.addFilterToMethod(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInMethodTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToMethod(new EObjectFilter(UMLPackage.Literals.BEHAVIOR));
      }
      
      if (isAccessible(UmlViewsRepository.Operation.Properties.raisedException)) {
        basePart.addFilterToRaisedException(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInRaisedExceptionTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToRaisedException(new EObjectFilter(UMLPackage.Literals.TYPE));
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.owningTemplateParameter)) {
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
      if (isAccessible(UmlViewsRepository.Operation.Properties.templateParameter)) {
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
      if (isAccessible(UmlViewsRepository.Operation.Properties.interface_)) {
        basePart.addFilterToInterface_(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof Interface); //$NON-NLS-1$ 
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.class_)) {
        basePart.addFilterToClass_(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof Class); //$NON-NLS-1$ 
          }
          
        });
      }
      
      if (isAccessible(UmlViewsRepository.Operation.Properties.precondition)) {
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
      if (isAccessible(UmlViewsRepository.Operation.Properties.postcondition)) {
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
      if (isAccessible(UmlViewsRepository.Operation.Properties.redefinedOperation)) {
        basePart.addFilterToRedefinedOperation(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInRedefinedOperationTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToRedefinedOperation(new EObjectFilter(UMLPackage.Literals.OPERATION));
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.datatype)) {
        basePart.addFilterToDatatype(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof DataType); //$NON-NLS-1$ 
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.bodyCondition)) {
        basePart.addFilterToBodyCondition(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof Constraint); //$NON-NLS-1$ 
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.Operation.Properties.parameters)) {
        basePart.addFilterToParameters(new ViewerFilter() {
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof Parameter); //$NON-NLS-1$ 
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
		if (editorKey == UmlViewsRepository.Operation.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.isLeaf) {
			return UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.isStatic) {
			return UMLPackage.eINSTANCE.getFeature_IsStatic();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.isAbstract) {
			return UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.method) {
			return UMLPackage.eINSTANCE.getBehavioralFeature_Method();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.concurrency) {
			return UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.raisedException) {
			return UMLPackage.eINSTANCE.getBehavioralFeature_RaisedException();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.owningTemplateParameter) {
			return UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.templateParameter) {
			return UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.interface_) {
			return UMLPackage.eINSTANCE.getOperation_Interface();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.class_) {
			return UMLPackage.eINSTANCE.getOperation_Class();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.isQuery) {
			return UMLPackage.eINSTANCE.getOperation_IsQuery();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.precondition) {
			return UMLPackage.eINSTANCE.getOperation_Precondition();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.postcondition) {
			return UMLPackage.eINSTANCE.getOperation_Postcondition();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.redefinedOperation) {
			return UMLPackage.eINSTANCE.getOperation_RedefinedOperation();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.datatype) {
			return UMLPackage.eINSTANCE.getOperation_Datatype();
		}
		if (editorKey == UmlViewsRepository.Operation.Properties.bodyCondition) {
			return UMLPackage.eINSTANCE.getOperation_BodyCondition();
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
    if (UmlViewsRepository.Operation.Properties.name == event.getAffectedEditor()) {
      operation.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
    }
    if (UmlViewsRepository.Operation.Properties.visibility == event.getAffectedEditor()) {
      operation.setVisibility((VisibilityKind)event.getNewValue());
    }
    if (UmlViewsRepository.Operation.Properties.clientDependency == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Operation.Properties.isLeaf == event.getAffectedEditor()) {
      operation.setIsLeaf((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.Operation.Properties.isStatic == event.getAffectedEditor()) {
      operation.setIsStatic((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.Operation.Properties.isAbstract == event.getAffectedEditor()) {
      operation.setIsAbstract((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.Operation.Properties.method == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof Behavior) {
          methodSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        methodSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        methodSettings.move(event.getNewIndex(), (Behavior) event.getNewValue());
      }
    }
    if (UmlViewsRepository.Operation.Properties.concurrency == event.getAffectedEditor()) {
      operation.setConcurrency((CallConcurrencyKind)event.getNewValue());
    }
    if (UmlViewsRepository.Operation.Properties.raisedException == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof Type) {
          raisedExceptionSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        raisedExceptionSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        raisedExceptionSettings.move(event.getNewIndex(), (Type) event.getNewValue());
      }
    }
    if (UmlViewsRepository.Operation.Properties.owningTemplateParameter == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Operation.Properties.templateParameter == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Operation.Properties.interface_ == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        interface_Settings.setToReference((Interface)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        Interface eObject = UMLFactory.eINSTANCE.createInterface();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        interface_Settings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.Operation.Properties.class_ == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        class_Settings.setToReference((Class)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        org.eclipse.uml2.uml.Class eObject = UMLFactory.eINSTANCE.createClass();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        class_Settings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.Operation.Properties.isQuery == event.getAffectedEditor()) {
      operation.setIsQuery((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.Operation.Properties.precondition == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Operation.Properties.postcondition == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.Operation.Properties.redefinedOperation == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof Operation) {
          redefinedOperationSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        redefinedOperationSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        redefinedOperationSettings.move(event.getNewIndex(), (Operation) event.getNewValue());
      }
    }
    if (UmlViewsRepository.Operation.Properties.datatype == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        datatypeSettings.setToReference((DataType)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        DataType eObject = UMLFactory.eINSTANCE.createDataType();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        datatypeSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.Operation.Properties.bodyCondition == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        bodyConditionSettings.setToReference((Constraint)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        Constraint eObject = UMLFactory.eINSTANCE.createConstraint();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        bodyConditionSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.Operation.Properties.parameters == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, parametersSettings, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy instanceof CreateEditingPolicy) {
            policy.execute();
          }
        }
      } else if (event.getKind() == PropertiesEditionEvent.EDIT) {
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, (EObject) event.getNewValue(), editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt((EObject) event.getNewValue(), PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
          if (editionPolicy != null) {
            editionPolicy.execute();
          }
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        parametersSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        parametersSettings.move(event.getNewIndex(), (Parameter) event.getNewValue());
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
			OperationPropertiesEditionPart basePart = (OperationPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.Operation.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Operation.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getFeature_IsStatic().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.isStatic))
				basePart.setIsStatic((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.isAbstract))
				basePart.setIsAbstract((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavioralFeature_Method().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Operation.Properties.method))
				basePart.updateMethod();
			if (UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.Operation.Properties.concurrency))
				basePart.setConcurrency((CallConcurrencyKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavioralFeature_RaisedException().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Operation.Properties.raisedException))
				basePart.updateRaisedException();
			if (UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.owningTemplateParameter))
				basePart.setOwningTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.templateParameter))
				basePart.setTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getOperation_Interface().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.interface_))
				basePart.setInterface_((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getOperation_Class().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.class_))
				basePart.setClass_((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getOperation_IsQuery().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.isQuery))
				basePart.setIsQuery((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getOperation_Precondition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Operation.Properties.precondition))
				basePart.updatePrecondition();
			if (UMLPackage.eINSTANCE.getOperation_Postcondition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Operation.Properties.postcondition))
				basePart.updatePostcondition();
			if (UMLPackage.eINSTANCE.getOperation_RedefinedOperation().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Operation.Properties.redefinedOperation))
				basePart.updateRedefinedOperation();
			if (UMLPackage.eINSTANCE.getOperation_Datatype().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.datatype))
				basePart.setDatatype((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getOperation_BodyCondition().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.bodyCondition))
				basePart.setBodyCondition((EObject)msg.getNewValue());
			
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
			UMLPackage.eINSTANCE.getFeature_IsStatic(),
			UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract(),
			UMLPackage.eINSTANCE.getBehavioralFeature_Method(),
			UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency(),
			UMLPackage.eINSTANCE.getBehavioralFeature_RaisedException(),
			UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(),
			UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(),
			UMLPackage.eINSTANCE.getOperation_Interface(),
			UMLPackage.eINSTANCE.getOperation_Class(),
			UMLPackage.eINSTANCE.getOperation_IsQuery(),
			UMLPackage.eINSTANCE.getOperation_Precondition(),
			UMLPackage.eINSTANCE.getOperation_Postcondition(),
			UMLPackage.eINSTANCE.getOperation_RedefinedOperation(),
			UMLPackage.eINSTANCE.getOperation_Datatype(),
			UMLPackage.eINSTANCE.getOperation_BodyCondition()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
    return key == UmlViewsRepository.Operation.Properties.isLeaf || key == UmlViewsRepository.Operation.Properties.isStatic || key == UmlViewsRepository.Operation.Properties.isAbstract || key == UmlViewsRepository.Operation.Properties.concurrency || key == UmlViewsRepository.Operation.Properties.isQuery;
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
        if (UmlViewsRepository.Operation.Properties.name == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.Operation.Properties.visibility == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.Operation.Properties.isLeaf == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.Operation.Properties.isStatic == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.Operation.Properties.isAbstract == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.Operation.Properties.concurrency == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.Operation.Properties.isQuery == event.getAffectedEditor()) {
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
