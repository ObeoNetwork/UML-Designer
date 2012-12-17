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

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;

import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.ExpansionKind;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.InterruptibleActivityRegion;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ExpansionRegionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ExpansionRegionPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for inStructuredNode EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings inStructuredNodeSettings;
	
	/**
	 * Settings for activity EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings activitySettings;
	
	/**
	 * Settings for outgoing ReferencesTable
	 */
	private ReferencesTableSettings outgoingSettings;
	
	/**
	 * Settings for incoming ReferencesTable
	 */
	private ReferencesTableSettings incomingSettings;
	
	/**
	 * Settings for inPartition ReferencesTable
	 */
	private ReferencesTableSettings inPartitionSettings;
	
	/**
	 * Settings for inInterruptibleRegion ReferencesTable
	 */
	private ReferencesTableSettings inInterruptibleRegionSettings;
	
	/**
	 * Settings for redefinedNode ReferencesTable
	 */
	private ReferencesTableSettings redefinedNodeSettings;
	
	/**
	 * Settings for inActivity EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings inActivitySettings;
	
	/**
	 * Settings for inputElement ReferencesTable
	 */
	private ReferencesTableSettings inputElementSettings;
	
	/**
	 * Settings for outputElement ReferencesTable
	 */
	private ReferencesTableSettings outputElementSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public ExpansionRegionPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject expansionRegion, String editing_mode) {
    super(editingContext, expansionRegion, editing_mode);
    parts = new String[] { BASE_PART };
    repositoryKey = UmlViewsRepository.class;
    partKey = UmlViewsRepository.ExpansionRegion.class;
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
      
      final ExpansionRegion expansionRegion = (ExpansionRegion)elt;
      final ExpansionRegionPropertiesEditionPart basePart = (ExpansionRegionPropertiesEditionPart)editingPart;
      // init values
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.name))
        basePart.setName(EEFConverterUtil.convertToString(UMLPackage.Literals.STRING, expansionRegion.getName()));
      
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.visibility)) {
        basePart.initVisibility(EEFUtils.choiceOfValues(expansionRegion, UMLPackage.eINSTANCE.getNamedElement_Visibility()), expansionRegion.getVisibility());
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.clientDependency)) {
        clientDependencySettings = new ReferencesTableSettings(expansionRegion, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
        basePart.initClientDependency(clientDependencySettings);
      }
      basePart.setIsLeaf(expansionRegion.isLeaf());
      
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inStructuredNode)) {
        // init part
        inStructuredNodeSettings = new EObjectFlatComboSettings(expansionRegion, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode());
        basePart.initInStructuredNode(inStructuredNodeSettings);
        // set the button mode
        basePart.setInStructuredNodeButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.activity)) {
        // init part
        activitySettings = new EObjectFlatComboSettings(expansionRegion, UMLPackage.eINSTANCE.getActivityNode_Activity());
        basePart.initActivity(activitySettings);
        // set the button mode
        basePart.setActivityButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.outgoing)) {
        outgoingSettings = new ReferencesTableSettings(expansionRegion, UMLPackage.eINSTANCE.getActivityNode_Outgoing());
        basePart.initOutgoing(outgoingSettings);
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.incoming)) {
        incomingSettings = new ReferencesTableSettings(expansionRegion, UMLPackage.eINSTANCE.getActivityNode_Incoming());
        basePart.initIncoming(incomingSettings);
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inPartition)) {
        inPartitionSettings = new ReferencesTableSettings(expansionRegion, UMLPackage.eINSTANCE.getActivityNode_InPartition());
        basePart.initInPartition(inPartitionSettings);
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inInterruptibleRegion)) {
        inInterruptibleRegionSettings = new ReferencesTableSettings(expansionRegion, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
        basePart.initInInterruptibleRegion(inInterruptibleRegionSettings);
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.redefinedNode)) {
        redefinedNodeSettings = new ReferencesTableSettings(expansionRegion, UMLPackage.eINSTANCE.getActivityNode_RedefinedNode());
        basePart.initRedefinedNode(redefinedNodeSettings);
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inActivity)) {
        // init part
        inActivitySettings = new EObjectFlatComboSettings(expansionRegion, UMLPackage.eINSTANCE.getActivityGroup_InActivity());
        basePart.initInActivity(inActivitySettings);
        // set the button mode
        basePart.setInActivityButtonMode(ButtonsModeEnum.BROWSE);
      }
      basePart.setMustIsolate(expansionRegion.isMustIsolate());
      
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.mode)) {
        basePart.initMode(EEFUtils.choiceOfValues(expansionRegion, UMLPackage.eINSTANCE.getExpansionRegion_Mode()), expansionRegion.getMode());
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inputElement)) {
        inputElementSettings = new ReferencesTableSettings(expansionRegion, UMLPackage.eINSTANCE.getExpansionRegion_InputElement());
        basePart.initInputElement(inputElementSettings);
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.outputElement)) {
        outputElementSettings = new ReferencesTableSettings(expansionRegion, UMLPackage.eINSTANCE.getExpansionRegion_OutputElement());
        basePart.initOutputElement(outputElementSettings);
      }
      // init filters
      
      
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.clientDependency)) {
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
      
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inStructuredNode)) {
        basePart.addFilterToInStructuredNode(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof StructuredActivityNode); //$NON-NLS-1$ 
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.activity)) {
        basePart.addFilterToActivity(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof Activity); //$NON-NLS-1$ 
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.outgoing)) {
        basePart.addFilterToOutgoing(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInOutgoingTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToOutgoing(new EObjectFilter(UMLPackage.Literals.ACTIVITY_EDGE));
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.incoming)) {
        basePart.addFilterToIncoming(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInIncomingTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToIncoming(new EObjectFilter(UMLPackage.Literals.ACTIVITY_EDGE));
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inPartition)) {
        basePart.addFilterToInPartition(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInInPartitionTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToInPartition(new EObjectFilter(UMLPackage.Literals.ACTIVITY_PARTITION));
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inInterruptibleRegion)) {
        basePart.addFilterToInInterruptibleRegion(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInInInterruptibleRegionTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToInInterruptibleRegion(new EObjectFilter(UMLPackage.Literals.INTERRUPTIBLE_ACTIVITY_REGION));
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.redefinedNode)) {
        basePart.addFilterToRedefinedNode(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInRedefinedNodeTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToRedefinedNode(new EObjectFilter(UMLPackage.Literals.ACTIVITY_NODE));
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inActivity)) {
        basePart.addFilterToInActivity(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof Activity); //$NON-NLS-1$ 
          }
          
        });
      }
      
      
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inputElement)) {
        basePart.addFilterToInputElement(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInInputElementTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToInputElement(new EObjectFilter(UMLPackage.Literals.EXPANSION_NODE));
      }
      if (isAccessible(UmlViewsRepository.ExpansionRegion.Properties.outputElement)) {
        basePart.addFilterToOutputElement(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInOutputElementTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToOutputElement(new EObjectFilter(UMLPackage.Literals.EXPANSION_NODE));
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
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.isLeaf) {
			return UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.inStructuredNode) {
			return UMLPackage.eINSTANCE.getActivityNode_InStructuredNode();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.activity) {
			return UMLPackage.eINSTANCE.getActivityNode_Activity();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.outgoing) {
			return UMLPackage.eINSTANCE.getActivityNode_Outgoing();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.incoming) {
			return UMLPackage.eINSTANCE.getActivityNode_Incoming();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.inPartition) {
			return UMLPackage.eINSTANCE.getActivityNode_InPartition();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.inInterruptibleRegion) {
			return UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.redefinedNode) {
			return UMLPackage.eINSTANCE.getActivityNode_RedefinedNode();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.inActivity) {
			return UMLPackage.eINSTANCE.getActivityGroup_InActivity();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.mustIsolate) {
			return UMLPackage.eINSTANCE.getStructuredActivityNode_MustIsolate();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.mode) {
			return UMLPackage.eINSTANCE.getExpansionRegion_Mode();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.inputElement) {
			return UMLPackage.eINSTANCE.getExpansionRegion_InputElement();
		}
		if (editorKey == UmlViewsRepository.ExpansionRegion.Properties.outputElement) {
			return UMLPackage.eINSTANCE.getExpansionRegion_OutputElement();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
    ExpansionRegion expansionRegion = (ExpansionRegion)semanticObject;
    if (UmlViewsRepository.ExpansionRegion.Properties.name == event.getAffectedEditor()) {
      expansionRegion.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
    }
    if (UmlViewsRepository.ExpansionRegion.Properties.visibility == event.getAffectedEditor()) {
      expansionRegion.setVisibility((VisibilityKind)event.getNewValue());
    }
    if (UmlViewsRepository.ExpansionRegion.Properties.clientDependency == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.ExpansionRegion.Properties.isLeaf == event.getAffectedEditor()) {
      expansionRegion.setIsLeaf((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.ExpansionRegion.Properties.inStructuredNode == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        inStructuredNodeSettings.setToReference((StructuredActivityNode)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        StructuredActivityNode eObject = UMLFactory.eINSTANCE.createStructuredActivityNode();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        inStructuredNodeSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.ExpansionRegion.Properties.activity == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        activitySettings.setToReference((Activity)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        Activity eObject = UMLFactory.eINSTANCE.createActivity();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        activitySettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.ExpansionRegion.Properties.outgoing == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof ActivityEdge) {
          outgoingSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        outgoingSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        outgoingSettings.move(event.getNewIndex(), (ActivityEdge) event.getNewValue());
      }
    }
    if (UmlViewsRepository.ExpansionRegion.Properties.incoming == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof ActivityEdge) {
          incomingSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        incomingSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        incomingSettings.move(event.getNewIndex(), (ActivityEdge) event.getNewValue());
      }
    }
    if (UmlViewsRepository.ExpansionRegion.Properties.inPartition == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof ActivityPartition) {
          inPartitionSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        inPartitionSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        inPartitionSettings.move(event.getNewIndex(), (ActivityPartition) event.getNewValue());
      }
    }
    if (UmlViewsRepository.ExpansionRegion.Properties.inInterruptibleRegion == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof InterruptibleActivityRegion) {
          inInterruptibleRegionSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        inInterruptibleRegionSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        inInterruptibleRegionSettings.move(event.getNewIndex(), (InterruptibleActivityRegion) event.getNewValue());
      }
    }
    if (UmlViewsRepository.ExpansionRegion.Properties.redefinedNode == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof ActivityNode) {
          redefinedNodeSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        redefinedNodeSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        redefinedNodeSettings.move(event.getNewIndex(), (ActivityNode) event.getNewValue());
      }
    }
    if (UmlViewsRepository.ExpansionRegion.Properties.inActivity == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        inActivitySettings.setToReference((Activity)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        Activity eObject = UMLFactory.eINSTANCE.createActivity();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        inActivitySettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.ExpansionRegion.Properties.mustIsolate == event.getAffectedEditor()) {
      expansionRegion.setMustIsolate((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.ExpansionRegion.Properties.mode == event.getAffectedEditor()) {
      expansionRegion.setMode((ExpansionKind)event.getNewValue());
    }
    if (UmlViewsRepository.ExpansionRegion.Properties.inputElement == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof ExpansionNode) {
          inputElementSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        inputElementSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        inputElementSettings.move(event.getNewIndex(), (ExpansionNode) event.getNewValue());
      }
    }
    if (UmlViewsRepository.ExpansionRegion.Properties.outputElement == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof ExpansionNode) {
          outputElementSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        outputElementSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        outputElementSettings.move(event.getNewIndex(), (ExpansionNode) event.getNewValue());
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
			ExpansionRegionPropertiesEditionPart basePart = (ExpansionRegionPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getActivityNode_InStructuredNode().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inStructuredNode))
				basePart.setInStructuredNode((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActivityNode_Activity().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.activity))
				basePart.setActivity((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActivityNode_Outgoing().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.outgoing))
				basePart.updateOutgoing();
			if (UMLPackage.eINSTANCE.getActivityNode_Incoming().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.incoming))
				basePart.updateIncoming();
			if (UMLPackage.eINSTANCE.getActivityNode_InPartition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inPartition))
				basePart.updateInPartition();
			if (UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inInterruptibleRegion))
				basePart.updateInInterruptibleRegion();
			if (UMLPackage.eINSTANCE.getActivityNode_RedefinedNode().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.redefinedNode))
				basePart.updateRedefinedNode();
			if (UMLPackage.eINSTANCE.getActivityGroup_InActivity().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inActivity))
				basePart.setInActivity((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getStructuredActivityNode_MustIsolate().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.mustIsolate))
				basePart.setMustIsolate((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getExpansionRegion_Mode().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.mode))
				basePart.setMode((ExpansionKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getExpansionRegion_InputElement().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.inputElement))
				basePart.updateInputElement();
			if (UMLPackage.eINSTANCE.getExpansionRegion_OutputElement().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExpansionRegion.Properties.outputElement))
				basePart.updateOutputElement();
			
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
			UMLPackage.eINSTANCE.getActivityNode_InStructuredNode(),
			UMLPackage.eINSTANCE.getActivityNode_Activity(),
			UMLPackage.eINSTANCE.getActivityNode_Outgoing(),
			UMLPackage.eINSTANCE.getActivityNode_Incoming(),
			UMLPackage.eINSTANCE.getActivityNode_InPartition(),
			UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion(),
			UMLPackage.eINSTANCE.getActivityNode_RedefinedNode(),
			UMLPackage.eINSTANCE.getActivityGroup_InActivity(),
			UMLPackage.eINSTANCE.getStructuredActivityNode_MustIsolate(),
			UMLPackage.eINSTANCE.getExpansionRegion_Mode(),
			UMLPackage.eINSTANCE.getExpansionRegion_InputElement(),
			UMLPackage.eINSTANCE.getExpansionRegion_OutputElement()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
    return key == UmlViewsRepository.ExpansionRegion.Properties.isLeaf || key == UmlViewsRepository.ExpansionRegion.Properties.mustIsolate || key == UmlViewsRepository.ExpansionRegion.Properties.mode || key == UmlViewsRepository.ExpansionRegion.Properties.inputElement;
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
        if (UmlViewsRepository.ExpansionRegion.Properties.name == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.ExpansionRegion.Properties.visibility == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.ExpansionRegion.Properties.isLeaf == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.ExpansionRegion.Properties.mustIsolate == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getStructuredActivityNode_MustIsolate().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getStructuredActivityNode_MustIsolate().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.ExpansionRegion.Properties.mode == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getExpansionRegion_Mode().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getExpansionRegion_Mode().getEAttributeType(), newValue);
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
