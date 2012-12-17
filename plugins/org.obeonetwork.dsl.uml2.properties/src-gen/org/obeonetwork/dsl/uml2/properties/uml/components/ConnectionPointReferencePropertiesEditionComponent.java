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

import org.eclipse.uml2.uml.ConnectionPointReference;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectionPointReferencePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ConnectionPointReferencePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for container EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings containerSettings;
	
	/**
	 * Settings for entry ReferencesTable
	 */
	private ReferencesTableSettings entrySettings;
	
	/**
	 * Settings for exit ReferencesTable
	 */
	private ReferencesTableSettings exitSettings;
	
	/**
	 * Settings for state EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings stateSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public ConnectionPointReferencePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject connectionPointReference, String editing_mode) {
    super(editingContext, connectionPointReference, editing_mode);
    parts = new String[] { BASE_PART };
    repositoryKey = UmlViewsRepository.class;
    partKey = UmlViewsRepository.ConnectionPointReference.class;
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
      
      final ConnectionPointReference connectionPointReference = (ConnectionPointReference)elt;
      final ConnectionPointReferencePropertiesEditionPart basePart = (ConnectionPointReferencePropertiesEditionPart)editingPart;
      // init values
      if (isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.name))
        basePart.setName(EEFConverterUtil.convertToString(UMLPackage.Literals.STRING, connectionPointReference.getName()));
      
      if (isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.visibility)) {
        basePart.initVisibility(EEFUtils.choiceOfValues(connectionPointReference, UMLPackage.eINSTANCE.getNamedElement_Visibility()), connectionPointReference.getVisibility());
      }
      if (isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.clientDependency)) {
        clientDependencySettings = new ReferencesTableSettings(connectionPointReference, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
        basePart.initClientDependency(clientDependencySettings);
      }
      if (isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.container)) {
        // init part
        containerSettings = new EObjectFlatComboSettings(connectionPointReference, UMLPackage.eINSTANCE.getVertex_Container());
        basePart.initContainer(containerSettings);
        // set the button mode
        basePart.setContainerButtonMode(ButtonsModeEnum.BROWSE);
      }
      if (isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.entry)) {
        entrySettings = new ReferencesTableSettings(connectionPointReference, UMLPackage.eINSTANCE.getConnectionPointReference_Entry());
        basePart.initEntry(entrySettings);
      }
      if (isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.exit)) {
        exitSettings = new ReferencesTableSettings(connectionPointReference, UMLPackage.eINSTANCE.getConnectionPointReference_Exit());
        basePart.initExit(exitSettings);
      }
      if (isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.state)) {
        // init part
        stateSettings = new EObjectFlatComboSettings(connectionPointReference, UMLPackage.eINSTANCE.getConnectionPointReference_State());
        basePart.initState(stateSettings);
        // set the button mode
        basePart.setStateButtonMode(ButtonsModeEnum.BROWSE);
      }
      // init filters
      
      
      if (isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.clientDependency)) {
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
      if (isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.container)) {
        basePart.addFilterToContainer(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof Region); //$NON-NLS-1$ 
          }
          
        });
      }
      if (isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.entry)) {
        basePart.addFilterToEntry(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInEntryTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToEntry(new EObjectFilter(UMLPackage.Literals.PSEUDOSTATE));
      }
      if (isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.exit)) {
        basePart.addFilterToExit(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element instanceof EObject)
              return (!basePart.isContainedInExitTable((EObject)element));
            return element instanceof Resource;
          }
        
        });
        basePart.addFilterToExit(new EObjectFilter(UMLPackage.Literals.PSEUDOSTATE));
      }
      if (isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.state)) {
        basePart.addFilterToState(new ViewerFilter() {
        
          /**
           * {@inheritDoc}
           * 
           * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
           */
          public boolean select(Viewer viewer, Object parentElement, Object element) {
            return (element instanceof String && element.equals("")) || (element instanceof State); //$NON-NLS-1$ 
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
		if (editorKey == UmlViewsRepository.ConnectionPointReference.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.ConnectionPointReference.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.ConnectionPointReference.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.ConnectionPointReference.Properties.container) {
			return UMLPackage.eINSTANCE.getVertex_Container();
		}
		if (editorKey == UmlViewsRepository.ConnectionPointReference.Properties.entry) {
			return UMLPackage.eINSTANCE.getConnectionPointReference_Entry();
		}
		if (editorKey == UmlViewsRepository.ConnectionPointReference.Properties.exit) {
			return UMLPackage.eINSTANCE.getConnectionPointReference_Exit();
		}
		if (editorKey == UmlViewsRepository.ConnectionPointReference.Properties.state) {
			return UMLPackage.eINSTANCE.getConnectionPointReference_State();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
    ConnectionPointReference connectionPointReference = (ConnectionPointReference)semanticObject;
    if (UmlViewsRepository.ConnectionPointReference.Properties.name == event.getAffectedEditor()) {
      connectionPointReference.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
    }
    if (UmlViewsRepository.ConnectionPointReference.Properties.visibility == event.getAffectedEditor()) {
      connectionPointReference.setVisibility((VisibilityKind)event.getNewValue());
    }
    if (UmlViewsRepository.ConnectionPointReference.Properties.clientDependency == event.getAffectedEditor()) {
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
    if (UmlViewsRepository.ConnectionPointReference.Properties.container == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        containerSettings.setToReference((Region)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        Region eObject = UMLFactory.eINSTANCE.createRegion();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        containerSettings.setToReference(eObject);
      }
    }
    if (UmlViewsRepository.ConnectionPointReference.Properties.entry == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof Pseudostate) {
          entrySettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        entrySettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        entrySettings.move(event.getNewIndex(), (Pseudostate) event.getNewValue());
      }
    }
    if (UmlViewsRepository.ConnectionPointReference.Properties.exit == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.ADD) {
        if (event.getNewValue() instanceof Pseudostate) {
          exitSettings.addToReference((EObject) event.getNewValue());
        }
      } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
        exitSettings.removeFromReference((EObject) event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
        exitSettings.move(event.getNewIndex(), (Pseudostate) event.getNewValue());
      }
    }
    if (UmlViewsRepository.ConnectionPointReference.Properties.state == event.getAffectedEditor()) {
      if (event.getKind() == PropertiesEditionEvent.SET) {
        stateSettings.setToReference((State)event.getNewValue());
      } else if (event.getKind() == PropertiesEditionEvent.ADD) {
        State eObject = UMLFactory.eINSTANCE.createState();
        EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
        PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
        if (provider != null) {
          PropertiesEditingPolicy policy = provider.getPolicy(context);
          if (policy != null) {
            policy.execute();
          }
        }
        stateSettings.setToReference(eObject);
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
			ConnectionPointReferencePropertiesEditionPart basePart = (ConnectionPointReferencePropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getVertex_Container().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.container))
				basePart.setContainer((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getConnectionPointReference_Entry().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.entry))
				basePart.updateEntry();
			if (UMLPackage.eINSTANCE.getConnectionPointReference_Exit().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.exit))
				basePart.updateExit();
			if (UMLPackage.eINSTANCE.getConnectionPointReference_State().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ConnectionPointReference.Properties.state))
				basePart.setState((EObject)msg.getNewValue());
			
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
			UMLPackage.eINSTANCE.getVertex_Container(),
			UMLPackage.eINSTANCE.getConnectionPointReference_Entry(),
			UMLPackage.eINSTANCE.getConnectionPointReference_Exit(),
			UMLPackage.eINSTANCE.getConnectionPointReference_State()		);
		return new NotificationFilter[] {filter,};
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
        if (UmlViewsRepository.ConnectionPointReference.Properties.name == event.getAffectedEditor()) {
          Object newValue = event.getNewValue();
          if (newValue instanceof String) {
            newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
          }
          ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
        }
        if (UmlViewsRepository.ConnectionPointReference.Properties.visibility == event.getAffectedEditor()) {
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
