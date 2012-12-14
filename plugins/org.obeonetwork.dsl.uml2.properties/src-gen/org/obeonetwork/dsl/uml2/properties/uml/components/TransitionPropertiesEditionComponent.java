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

import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TransitionKind;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Vertex;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.TransitionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class TransitionPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

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
	 * Settings for source EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings sourceSettings;
	
	/**
	 * Settings for target EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings targetSettings;
	
	/**
	 * Settings for redefinedTransition EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings redefinedTransitionSettings;
	
	/**
	 * Settings for guard EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings guardSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public TransitionPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject transition, String editing_mode) {
		super(editingContext, transition, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.Transition.class;
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
			
			final Transition transition = (Transition)elt;
			final TransitionPropertiesEditionPart basePart = (TransitionPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.Transition.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.Literals.STRING, transition.getName()));
			
			if (isAccessible(UmlViewsRepository.Transition.Properties.visibility)) {
				basePart.initVisibility(EEFUtils.choiceOfValues(transition, UMLPackage.eINSTANCE.getNamedElement_Visibility()), transition.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.Transition.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(transition, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			if (isAccessible(UmlViewsRepository.Transition.Properties.isLeaf)) {
				basePart.setIsLeaf(transition.isLeaf());
			}
			if (isAccessible(UmlViewsRepository.Transition.Properties.kind)) {
				basePart.initKind(EEFUtils.choiceOfValues(transition, UMLPackage.eINSTANCE.getTransition_Kind()), transition.getKind());
			}
			if (isAccessible(UmlViewsRepository.Transition.Properties.container)) {
				// init part
				containerSettings = new EObjectFlatComboSettings(transition, UMLPackage.eINSTANCE.getTransition_Container());
				basePart.initContainer(containerSettings);
				// set the button mode
				basePart.setContainerButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Transition.Properties.source)) {
				// init part
				sourceSettings = new EObjectFlatComboSettings(transition, UMLPackage.eINSTANCE.getTransition_Source());
				basePart.initSource(sourceSettings);
				// set the button mode
				basePart.setSourceButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Transition.Properties.target)) {
				// init part
				targetSettings = new EObjectFlatComboSettings(transition, UMLPackage.eINSTANCE.getTransition_Target());
				basePart.initTarget(targetSettings);
				// set the button mode
				basePart.setTargetButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Transition.Properties.redefinedTransition)) {
				// init part
				redefinedTransitionSettings = new EObjectFlatComboSettings(transition, UMLPackage.eINSTANCE.getTransition_RedefinedTransition());
				basePart.initRedefinedTransition(redefinedTransitionSettings);
				// set the button mode
				basePart.setRedefinedTransitionButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Transition.Properties.guard)) {
				// init part
				guardSettings = new EObjectFlatComboSettings(transition, UMLPackage.eINSTANCE.getTransition_Guard());
				basePart.initGuard(guardSettings);
				// set the button mode
				basePart.setGuardButtonMode(ButtonsModeEnum.BROWSE);
			}
			// init filters
			
			
			if (isAccessible(UmlViewsRepository.Transition.Properties.clientDependency)) {
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
			
			
			if (isAccessible(UmlViewsRepository.Transition.Properties.container)) {
				basePart.addFilterToContainer(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof Region);
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.Transition.Properties.source)) {
				basePart.addFilterToSource(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof Vertex);
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.Transition.Properties.target)) {
				basePart.addFilterToTarget(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof Vertex);
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.Transition.Properties.redefinedTransition)) {
				basePart.addFilterToRedefinedTransition(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof Transition); //$NON-NLS-1$ 
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.Transition.Properties.guard)) {
				basePart.addFilterToGuard(new ViewerFilter() {
				
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
		if (editorKey == UmlViewsRepository.Transition.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.Transition.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.Transition.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.Transition.Properties.isLeaf) {
			return UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf();
		}
		if (editorKey == UmlViewsRepository.Transition.Properties.kind) {
			return UMLPackage.eINSTANCE.getTransition_Kind();
		}
		if (editorKey == UmlViewsRepository.Transition.Properties.container) {
			return UMLPackage.eINSTANCE.getTransition_Container();
		}
		if (editorKey == UmlViewsRepository.Transition.Properties.source) {
			return UMLPackage.eINSTANCE.getTransition_Source();
		}
		if (editorKey == UmlViewsRepository.Transition.Properties.target) {
			return UMLPackage.eINSTANCE.getTransition_Target();
		}
		if (editorKey == UmlViewsRepository.Transition.Properties.redefinedTransition) {
			return UMLPackage.eINSTANCE.getTransition_RedefinedTransition();
		}
		if (editorKey == UmlViewsRepository.Transition.Properties.guard) {
			return UMLPackage.eINSTANCE.getTransition_Guard();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		Transition transition = (Transition)semanticObject;
		if (UmlViewsRepository.Transition.Properties.name == event.getAffectedEditor()) {
			transition.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
		}
		if (UmlViewsRepository.Transition.Properties.visibility == event.getAffectedEditor()) {
			transition.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.Transition.Properties.clientDependency == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.Transition.Properties.isLeaf == event.getAffectedEditor()) {
			transition.setIsLeaf((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Transition.Properties.kind == event.getAffectedEditor()) {
			transition.setKind((TransitionKind)event.getNewValue());
		}
		if (UmlViewsRepository.Transition.Properties.container == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.Transition.Properties.source == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				sourceSettings.setToReference((Vertex)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, sourceSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.Transition.Properties.target == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				targetSettings.setToReference((Vertex)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, targetSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.Transition.Properties.redefinedTransition == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				redefinedTransitionSettings.setToReference((Transition)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				Transition eObject = UMLFactory.eINSTANCE.createTransition();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				redefinedTransitionSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.Transition.Properties.guard == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				guardSettings.setToReference((Constraint)event.getNewValue());
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
				guardSettings.setToReference(eObject);
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
			TransitionPropertiesEditionPart basePart = (TransitionPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Transition.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.Transition.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Transition.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Transition.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getTransition_Kind().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.Transition.Properties.kind))
				basePart.setKind((TransitionKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getTransition_Container().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Transition.Properties.container))
				basePart.setContainer((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTransition_Source().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Transition.Properties.source))
				basePart.setSource((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTransition_Target().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Transition.Properties.target))
				basePart.setTarget((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTransition_RedefinedTransition().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Transition.Properties.redefinedTransition))
				basePart.setRedefinedTransition((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTransition_Guard().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Transition.Properties.guard))
				basePart.setGuard((EObject)msg.getNewValue());
			
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
			UMLPackage.eINSTANCE.getTransition_Kind(),
			UMLPackage.eINSTANCE.getTransition_Container(),
			UMLPackage.eINSTANCE.getTransition_Source(),
			UMLPackage.eINSTANCE.getTransition_Target(),
			UMLPackage.eINSTANCE.getTransition_RedefinedTransition(),
			UMLPackage.eINSTANCE.getTransition_Guard()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.Transition.Properties.isLeaf || key == UmlViewsRepository.Transition.Properties.kind || key == UmlViewsRepository.Transition.Properties.container || key == UmlViewsRepository.Transition.Properties.source || key == UmlViewsRepository.Transition.Properties.target;
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
				if (UmlViewsRepository.Transition.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Transition.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Transition.Properties.isLeaf == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Transition.Properties.kind == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getTransition_Kind().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getTransition_Kind().getEAttributeType(), newValue);
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
