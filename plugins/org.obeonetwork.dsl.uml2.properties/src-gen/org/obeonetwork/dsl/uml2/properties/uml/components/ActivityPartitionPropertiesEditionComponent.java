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
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ActivityPartitionPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for inActivity EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings inActivitySettings;
	
	/**
	 * Settings for node ReferencesTable
	 */
	private ReferencesTableSettings nodeSettings;
	
	/**
	 * Settings for superPartition EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings superPartitionSettings;
	
	/**
	 * Settings for represents EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings representsSettings;
	
	/**
	 * Settings for edge ReferencesTable
	 */
	private ReferencesTableSettings edgeSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public ActivityPartitionPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject activityPartition, String editing_mode) {
    super(editingContext, activityPartition, editing_mode);
    parts = new String[] { BASE_PART };
    repositoryKey = UmlViewsRepository.class;
    partKey = UmlViewsRepository.ActivityPartition.class;
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
      
      final ActivityPartition activityPartition = (ActivityPartition)elt;
      final ActivityPartitionPropertiesEditionPart basePart = (ActivityPartitionPropertiesEditionPart)editingPart;
      // init values
      if (isAccessible(UmlViewsRepository.ActivityPartition.Properties.name))
        basePart.setName(EEFConverterUtil.convertToString(UMLPackage.Literals.STRING, activityPartition.getName()));
      
      if (isAccessible(UmlViewsRepository.ActivityPartition.Properties.visibility)) {
        basePart.initVisibility(EEFUtils.choiceOfValues(activityPartition, UMLPackage.eINSTANCE.getNamedElement_Visibility()), activityPartition.getVisibility());
      }
      if (isAccessible(UmlViewsRepository.ActivityPartition.Properties.clientDependency)) {
        clientDependencySettings = new ReferencesTableSettings(activityPartition, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
        basePart.initClientDependency(clientDependencySettings);
      }
      if (isAccessible(UmlViewsRepository.ActivityPartition.Properties.inActivity)) {
        // init part
        inActivitySettings = new EObjectFlatComboSettings(activityPartition, UMLPackage.eINSTANCE.getActivityGroup_InActivity());
        basePart.initInActivity(inActivitySettings);
        // set the button mode
        basePart.setInActivityButtonMode(ButtonsModeEnum.BROWSE);
      }
      basePart.setIsDimension(activityPartition.isDimension());
      
      basePart.setIsExternal(activityPartition.isExternal());
      
      if (isAccessible(UmlViewsRepository.ActivityPartition.Properties.node)) {
        nodeSettings = new ReferencesTableSettings(activityPartition, UMLPackage.eINSTANCE.getActivityPartition_Node());
        basePart.initNode(nodeSettings);
      }
      if (isAccessible(UmlViewsRepository.ActivityPartition.Properties.superPartition)) {
        // init part
        superPartitionSettings = new EObjectFlatComboSettings(activityPartition, UMLPackage.eINSTANCE.getActivityPartition_SuperPartition());
        basePart.initSuperPartition(superPartitionSettings);
        // set the button mode
        basePart.setSuperPartitionButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ActivityPartition.Properties.represents)) {
        // init part
        representsSettings = new EObjectFlatComboSettings(activityPartition, UMLPackage.eINSTANCE.getActivityPartition_Represents());
        basePart.initRepresents(representsSettings);
        // set the button mode
        basePart.setRepresentsButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ActivityPartition.Properties.edge)) {
        edgeSettings = new ReferencesTableSettings(activityPartition, UMLPackage.eINSTANCE.getActivityPartition_Edge());
        basePart.initEdge(edgeSettings);
      }
      // init filters
      
      
      if (isAccessible(UmlViewsRepository.ActivityPartition.Properties.clientDependency)) {
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
      if (isAccessible(UmlViewsRepository.ActivityPartition.Properties.inActivity)) {
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
      
      
      if (isAccessible(UmlViewsRepository.ActivityPartition.Properties.node)) {
        basePart.addFilterToNode(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInNodeTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToNode(new EObjectFilter(UMLPackage.Literals.ACTIVITY_NODE));
      }
      if (isAccessible(UmlViewsRepository.ActivityPartition.Properties.superPartition)) {
        basePart.addFilterToSuperPartition(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof ActivityPartition); //$NON-NLS-1$ 
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.ActivityPartition.Properties.represents)) {
        basePart.addFilterToRepresents(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof Element); //$NON-NLS-1$ 
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.ActivityPartition.Properties.edge)) {
        basePart.addFilterToEdge(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInEdgeTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToEdge(new EObjectFilter(UMLPackage.Literals.ACTIVITY_EDGE));
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
		if (editorKey == UmlViewsRepository.ActivityPartition.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.ActivityPartition.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.ActivityPartition.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.ActivityPartition.Properties.inActivity) {
			return UMLPackage.eINSTANCE.getActivityGroup_InActivity();
		}
		if (editorKey == UmlViewsRepository.ActivityPartition.Properties.isDimension) {
			return UMLPackage.eINSTANCE.getActivityPartition_IsDimension();
		}
		if (editorKey == UmlViewsRepository.ActivityPartition.Properties.isExternal) {
			return UMLPackage.eINSTANCE.getActivityPartition_IsExternal();
		}
		if (editorKey == UmlViewsRepository.ActivityPartition.Properties.node) {
			return UMLPackage.eINSTANCE.getActivityPartition_Node();
		}
		if (editorKey == UmlViewsRepository.ActivityPartition.Properties.superPartition) {
			return UMLPackage.eINSTANCE.getActivityPartition_SuperPartition();
		}
		if (editorKey == UmlViewsRepository.ActivityPartition.Properties.represents) {
			return UMLPackage.eINSTANCE.getActivityPartition_Represents();
		}
		if (editorKey == UmlViewsRepository.ActivityPartition.Properties.edge) {
			return UMLPackage.eINSTANCE.getActivityPartition_Edge();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
    ActivityPartition activityPartition = (ActivityPartition)semanticObject;
    if (UmlViewsRepository.ActivityPartition.Properties.name == event.getAffectedEditor()) {
      activityPartition.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
    }
    if (UmlViewsRepository.ActivityPartition.Properties.visibility == event.getAffectedEditor()) {
      activityPartition.setVisibility((VisibilityKind)event.getNewValue());
    }
    if (UmlViewsRepository.ActivityPartition.Properties.clientDependency == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.ActivityPartition.Properties.inActivity == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.ActivityPartition.Properties.isDimension == event.getAffectedEditor()) {
      activityPartition.setIsDimension((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.ActivityPartition.Properties.isExternal == event.getAffectedEditor()) {
      activityPartition.setIsExternal((Boolean)event.getNewValue());
    }
    if (UmlViewsRepository.ActivityPartition.Properties.node == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof ActivityNode) {
          nodeSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        nodeSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        nodeSettings.move(event.getNewIndex(), (ActivityNode) event.getNewValue());
      }
    }
    if (UmlViewsRepository.ActivityPartition.Properties.superPartition == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        superPartitionSettings.setToReference((ActivityPartition)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        ActivityPartition eObject = UMLFactory.eINSTANCE.createActivityPartition();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        superPartitionSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.ActivityPartition.Properties.represents == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        representsSettings.setToReference((Element)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, representsSettings, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy instanceof CreateEditingPolicy) {
            policy.execute();
          }
        }
      }
    }
    if (UmlViewsRepository.ActivityPartition.Properties.edge == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof ActivityEdge) {
          edgeSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        edgeSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        edgeSettings.move(event.getNewIndex(), (ActivityEdge) event.getNewValue());
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
			ActivityPartitionPropertiesEditionPart basePart = (ActivityPartitionPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.ActivityPartition.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.ActivityPartition.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ActivityPartition.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getActivityGroup_InActivity().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ActivityPartition.Properties.inActivity))
				basePart.setInActivity((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActivityPartition_IsDimension().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.ActivityPartition.Properties.isDimension))
				basePart.setIsDimension((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getActivityPartition_IsExternal().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.ActivityPartition.Properties.isExternal))
				basePart.setIsExternal((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getActivityPartition_Node().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ActivityPartition.Properties.node))
				basePart.updateNode();
			if (UMLPackage.eINSTANCE.getActivityPartition_SuperPartition().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ActivityPartition.Properties.superPartition))
				basePart.setSuperPartition((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActivityPartition_Represents().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ActivityPartition.Properties.represents))
				basePart.setRepresents((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActivityPartition_Edge().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ActivityPartition.Properties.edge))
				basePart.updateEdge();
			
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
			UMLPackage.eINSTANCE.getActivityGroup_InActivity(),
			UMLPackage.eINSTANCE.getActivityPartition_IsDimension(),
			UMLPackage.eINSTANCE.getActivityPartition_IsExternal(),
			UMLPackage.eINSTANCE.getActivityPartition_Node(),
			UMLPackage.eINSTANCE.getActivityPartition_SuperPartition(),
			UMLPackage.eINSTANCE.getActivityPartition_Represents(),
			UMLPackage.eINSTANCE.getActivityPartition_Edge()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
    return key == UmlViewsRepository.ActivityPartition.Properties.isDimension || key == UmlViewsRepository.ActivityPartition.Properties.isExternal;
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
        if (UmlViewsRepository.ActivityPartition.Properties.name == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.ActivityPartition.Properties.visibility == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.ActivityPartition.Properties.isDimension == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getActivityPartition_IsDimension().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getActivityPartition_IsDimension().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.ActivityPartition.Properties.isExternal == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getActivityPartition_IsExternal().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getActivityPartition_IsExternal().getEAttributeType(), newValue);
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
