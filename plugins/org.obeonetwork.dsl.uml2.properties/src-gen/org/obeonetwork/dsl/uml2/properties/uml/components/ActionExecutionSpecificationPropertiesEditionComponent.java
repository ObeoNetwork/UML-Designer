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

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActionExecutionSpecification;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.OccurrenceSpecification;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ActionExecutionSpecificationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ActionExecutionSpecificationPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

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
	 * Settings for start EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings startSettings;
	
	/**
	 * Settings for finish EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings finishSettings;
	
	/**
	 * Settings for action EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings actionSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public ActionExecutionSpecificationPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject actionExecutionSpecification, String editing_mode) {
    super(editingContext, actionExecutionSpecification, editing_mode);
    parts = new String[] { BASE_PART };
    repositoryKey = UmlViewsRepository.class;
    partKey = UmlViewsRepository.ActionExecutionSpecification.class;
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
      
      final ActionExecutionSpecification actionExecutionSpecification = (ActionExecutionSpecification)elt;
      final ActionExecutionSpecificationPropertiesEditionPart basePart = (ActionExecutionSpecificationPropertiesEditionPart)editingPart;
      // init values
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.name))
        basePart.setName(EEFConverterUtil.convertToString(UMLPackage.Literals.STRING, actionExecutionSpecification.getName()));
      
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.visibility)) {
        basePart.initVisibility(EEFUtils.choiceOfValues(actionExecutionSpecification, UMLPackage.eINSTANCE.getNamedElement_Visibility()), actionExecutionSpecification.getVisibility());
      }
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency)) {
        clientDependencySettings = new ReferencesTableSettings(actionExecutionSpecification, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
        basePart.initClientDependency(clientDependencySettings);
      }
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.covered)) {
        coveredSettings = new ReferencesTableSettings(actionExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_Covered());
        basePart.initCovered(coveredSettings);
      }
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction)) {
        // init part
        enclosingInteractionSettings = new EObjectFlatComboSettings(actionExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction());
        basePart.initEnclosingInteraction(enclosingInteractionSettings);
        // set the button mode
        basePart.setEnclosingInteractionButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand)) {
        // init part
        enclosingOperandSettings = new EObjectFlatComboSettings(actionExecutionSpecification, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand());
        basePart.initEnclosingOperand(enclosingOperandSettings);
        // set the button mode
        basePart.setEnclosingOperandButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.start)) {
        // init part
        startSettings = new EObjectFlatComboSettings(actionExecutionSpecification, UMLPackage.eINSTANCE.getExecutionSpecification_Start());
        basePart.initStart(startSettings);
        // set the button mode
        basePart.setStartButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.finish)) {
        // init part
        finishSettings = new EObjectFlatComboSettings(actionExecutionSpecification, UMLPackage.eINSTANCE.getExecutionSpecification_Finish());
        basePart.initFinish(finishSettings);
        // set the button mode
        basePart.setFinishButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.action)) {
        // init part
        actionSettings = new EObjectFlatComboSettings(actionExecutionSpecification, UMLPackage.eINSTANCE.getActionExecutionSpecification_Action());
        basePart.initAction(actionSettings);
        // set the button mode
        basePart.setActionButtonMode(ButtonsModeEnum.BROWSE);
      }
      // init filters
      
      
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency)) {
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
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.covered)) {
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
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction)) {
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
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand)) {
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
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.start)) {
        basePart.addFilterToStart(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof OccurrenceSpecification);
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.finish)) {
        basePart.addFilterToFinish(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof OccurrenceSpecification);
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.action)) {
        basePart.addFilterToAction(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof Action);
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
		if (editorKey == UmlViewsRepository.ActionExecutionSpecification.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.ActionExecutionSpecification.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.ActionExecutionSpecification.Properties.covered) {
			return UMLPackage.eINSTANCE.getInteractionFragment_Covered();
		}
		if (editorKey == UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction) {
			return UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction();
		}
		if (editorKey == UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand) {
			return UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand();
		}
		if (editorKey == UmlViewsRepository.ActionExecutionSpecification.Properties.start) {
			return UMLPackage.eINSTANCE.getExecutionSpecification_Start();
		}
		if (editorKey == UmlViewsRepository.ActionExecutionSpecification.Properties.finish) {
			return UMLPackage.eINSTANCE.getExecutionSpecification_Finish();
		}
		if (editorKey == UmlViewsRepository.ActionExecutionSpecification.Properties.action) {
			return UMLPackage.eINSTANCE.getActionExecutionSpecification_Action();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
    ActionExecutionSpecification actionExecutionSpecification = (ActionExecutionSpecification)semanticObject;
    if (UmlViewsRepository.ActionExecutionSpecification.Properties.name == event.getAffectedEditor()) {
      actionExecutionSpecification.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
    }
    if (UmlViewsRepository.ActionExecutionSpecification.Properties.visibility == event.getAffectedEditor()) {
      actionExecutionSpecification.setVisibility((VisibilityKind)event.getNewValue());
    }
    if (UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.ActionExecutionSpecification.Properties.covered == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.ActionExecutionSpecification.Properties.start == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        startSettings.setToReference((OccurrenceSpecification)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        OccurrenceSpecification eObject = UMLFactory.eINSTANCE.createOccurrenceSpecification();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        startSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.ActionExecutionSpecification.Properties.finish == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        finishSettings.setToReference((OccurrenceSpecification)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        OccurrenceSpecification eObject = UMLFactory.eINSTANCE.createOccurrenceSpecification();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        finishSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.ActionExecutionSpecification.Properties.action == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        actionSettings.setToReference((Action)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, actionSettings, editingContext.getAdapterFactory());
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
			ActionExecutionSpecificationPropertiesEditionPart basePart = (ActionExecutionSpecificationPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getInteractionFragment_Covered().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.covered))
				basePart.updateCovered();
			if (UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingInteraction))
				basePart.setEnclosingInteraction((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.enclosingOperand))
				basePart.setEnclosingOperand((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getExecutionSpecification_Start().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.start))
				basePart.setStart((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getExecutionSpecification_Finish().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.finish))
				basePart.setFinish((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActionExecutionSpecification_Action().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ActionExecutionSpecification.Properties.action))
				basePart.setAction((EObject)msg.getNewValue());
			
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
			UMLPackage.eINSTANCE.getExecutionSpecification_Start(),
			UMLPackage.eINSTANCE.getExecutionSpecification_Finish(),
			UMLPackage.eINSTANCE.getActionExecutionSpecification_Action()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
    return key == UmlViewsRepository.ActionExecutionSpecification.Properties.start || key == UmlViewsRepository.ActionExecutionSpecification.Properties.finish || key == UmlViewsRepository.ActionExecutionSpecification.Properties.action;
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
        if (UmlViewsRepository.ActionExecutionSpecification.Properties.name == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.ActionExecutionSpecification.Properties.visibility == event.getAffectedEditor()) {
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
