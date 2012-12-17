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

import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.GeneralOrdering;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ExecutionOccurrenceSpecificationPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for covered ReferencesTable
	 */
	private ReferencesTableSettings coveredSettings;
	
	/**
	 * Settings for enclosingInteraction EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings enclosingInteractionSettings;
	
	/**
	 * Settings for enclosingOperand EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings enclosingOperandSettings;
	
	/**
	 * Settings for toBefore ReferencesTable
	 */
	private ReferencesTableSettings toBeforeSettings;
	
	/**
	 * Settings for event EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings eventSettings;
	
	/**
	 * Settings for toAfter ReferencesTable
	 */
	private ReferencesTableSettings toAfterSettings;
	
	/**
	 * Settings for execution EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings executionSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public ExecutionOccurrenceSpecificationPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject executionOccurrenceSpecification, String editing_mode) {
    super(editingContext, executionOccurrenceSpecification, editing_mode);
    parts = new String[] { BASE_PART };
    repositoryKey = UmlViewsRepository.class;
    partKey = UmlViewsRepository.ExecutionOccurrenceSpecification.class;
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
      
      final ExecutionOccurrenceSpecification executionOccurrenceSpecification = (ExecutionOccurrenceSpecification)elt;
      final ExecutionOccurrenceSpecificationPropertiesEditionPart basePart = (ExecutionOccurrenceSpecificationPropertiesEditionPart)editingPart;
      // init values
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name))
        basePart.setName(EEFConverterUtil.convertToString(UMLPackage.Literals.STRING, executionOccurrenceSpecification.getName()));
      
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.visibility)) {
        basePart.initVisibility(EEFUtils.choiceOfValues(executionOccurrenceSpecification, UMLPackage.eINSTANCE.getNamedElement_Visibility()), executionOccurrenceSpecification.getVisibility());
      }
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency)) {
        clientDependencySettings = new ReferencesTableSettings(executionOccurrenceSpecification, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
        basePart.initClientDependency(clientDependencySettings);
      }
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered)) {
        coveredSettings = new ReferencesTableSettings(executionOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_Covered());
        basePart.initCovered(coveredSettings);
      }
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction)) {
        // init part
        enclosingInteractionSettings = new EObjectFlatComboSettings(executionOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction());
        basePart.initEnclosingInteraction(enclosingInteractionSettings);
        // set the button mode
        basePart.setEnclosingInteractionButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand)) {
        // init part
        enclosingOperandSettings = new EObjectFlatComboSettings(executionOccurrenceSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand());
        basePart.initEnclosingOperand(enclosingOperandSettings);
        // set the button mode
        basePart.setEnclosingOperandButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore)) {
        toBeforeSettings = new ReferencesTableSettings(executionOccurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_ToBefore());
        basePart.initToBefore(toBeforeSettings);
      }
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event)) {
        // init part
        eventSettings = new EObjectFlatComboSettings(executionOccurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_Event());
        basePart.initEvent(eventSettings);
        // set the button mode
        basePart.setEventButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter)) {
        toAfterSettings = new ReferencesTableSettings(executionOccurrenceSpecification, UMLPackage.eINSTANCE.getOccurrenceSpecification_ToAfter());
        basePart.initToAfter(toAfterSettings);
      }
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution)) {
        // init part
        executionSettings = new EObjectFlatComboSettings(executionOccurrenceSpecification, UMLPackage.eINSTANCE.getExecutionOccurrenceSpecification_Execution());
        basePart.initExecution(executionSettings);
        // set the button mode
        basePart.setExecutionButtonMode(ButtonsModeEnum.BROWSE);
      }
      // init filters
      
      
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency)) {
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
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered)) {
        basePart.addFilterToCovered(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInCoveredTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToCovered(new EObjectFilter(UMLPackage.Literals.LIFELINE));
      }
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction)) {
        basePart.addFilterToEnclosingInteraction(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof Interaction); //$NON-NLS-1$ 
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand)) {
        basePart.addFilterToEnclosingOperand(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof InteractionOperand); //$NON-NLS-1$ 
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore)) {
        basePart.addFilterToToBefore(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInToBeforeTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToToBefore(new EObjectFilter(UMLPackage.Literals.GENERAL_ORDERING));
      }
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event)) {
        basePart.addFilterToEvent(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof Event);
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter)) {
        basePart.addFilterToToAfter(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInToAfterTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToToAfter(new EObjectFilter(UMLPackage.Literals.GENERAL_ORDERING));
      }
      if (isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution)) {
        basePart.addFilterToExecution(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof ExecutionSpecification);
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
		if (editorKey == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered) {
			return UMLPackage.eINSTANCE.getInteractionFragment_Covered();
		}
		if (editorKey == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction) {
			return UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction();
		}
		if (editorKey == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand) {
			return UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand();
		}
		if (editorKey == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore) {
			return UMLPackage.eINSTANCE.getOccurrenceSpecification_ToBefore();
		}
		if (editorKey == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event) {
			return UMLPackage.eINSTANCE.getOccurrenceSpecification_Event();
		}
		if (editorKey == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter) {
			return UMLPackage.eINSTANCE.getOccurrenceSpecification_ToAfter();
		}
		if (editorKey == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution) {
			return UMLPackage.eINSTANCE.getExecutionOccurrenceSpecification_Execution();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
    ExecutionOccurrenceSpecification executionOccurrenceSpecification = (ExecutionOccurrenceSpecification)semanticObject;
    if (UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name == event.getAffectedEditor()) {
      executionOccurrenceSpecification.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
    }
    if (UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.visibility == event.getAffectedEditor()) {
      executionOccurrenceSpecification.setVisibility((VisibilityKind)event.getNewValue());
    }
    if (UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof Lifeline) {
          coveredSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        coveredSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        coveredSettings.move(event.getNewIndex(), (Lifeline) event.getNewValue());
      }
    }
    if (UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        enclosingInteractionSettings.setToReference((Interaction)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        Interaction eObject = UMLFactory.eINSTANCE.createInteraction();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        enclosingInteractionSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        enclosingOperandSettings.setToReference((InteractionOperand)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        InteractionOperand eObject = UMLFactory.eINSTANCE.createInteractionOperand();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        enclosingOperandSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof GeneralOrdering) {
          toBeforeSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        toBeforeSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        toBeforeSettings.move(event.getNewIndex(), (GeneralOrdering) event.getNewValue());
      }
    }
    if (UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        eventSettings.setToReference((Event)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, eventSettings, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy instanceof CreateEditingPolicy) {
            policy.execute();
          }
        }
      }
    }
    if (UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof GeneralOrdering) {
          toAfterSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        toAfterSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        toAfterSettings.move(event.getNewIndex(), (GeneralOrdering) event.getNewValue());
      }
    }
    if (UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        executionSettings.setToReference((ExecutionSpecification)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, executionSettings, editingContext.getAdapterFactory());
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
			ExecutionOccurrenceSpecificationPropertiesEditionPart basePart = (ExecutionOccurrenceSpecificationPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getInteractionFragment_Covered().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered))
				basePart.updateCovered();
			if (UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction))
				basePart.setEnclosingInteraction((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand))
				basePart.setEnclosingOperand((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getOccurrenceSpecification_ToBefore().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore))
				basePart.updateToBefore();
			if (UMLPackage.eINSTANCE.getOccurrenceSpecification_Event().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event))
				basePart.setEvent((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getOccurrenceSpecification_ToAfter().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter))
				basePart.updateToAfter();
			if (UMLPackage.eINSTANCE.getExecutionOccurrenceSpecification_Execution().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution))
				basePart.setExecution((EObject)msg.getNewValue());
			
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
			UMLPackage.eINSTANCE.getInteractionFragment_Covered(),
			UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction(),
			UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand(),
			UMLPackage.eINSTANCE.getOccurrenceSpecification_ToBefore(),
			UMLPackage.eINSTANCE.getOccurrenceSpecification_Event(),
			UMLPackage.eINSTANCE.getOccurrenceSpecification_ToAfter(),
			UMLPackage.eINSTANCE.getExecutionOccurrenceSpecification_Execution()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
    return key == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event || key == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution;
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
        if (UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.visibility == event.getAffectedEditor()) {
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
