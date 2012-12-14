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

import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.PartDecomposition;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class LifelinePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for represents EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings representsSettings;
	
	/**
	 * Settings for interaction EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings interactionSettings;
	
	/**
	 * Settings for decomposedAs EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings decomposedAsSettings;
	
	/**
	 * Settings for coveredBy ReferencesTable
	 */
	private ReferencesTableSettings coveredBySettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public LifelinePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject lifeline, String editing_mode) {
		super(editingContext, lifeline, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.Lifeline.class;
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
			
			final Lifeline lifeline = (Lifeline)elt;
			final LifelinePropertiesEditionPart basePart = (LifelinePropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.Lifeline.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.Literals.STRING, lifeline.getName()));
			
			if (isAccessible(UmlViewsRepository.Lifeline.Properties.visibility)) {
				basePart.initVisibility(EEFUtils.choiceOfValues(lifeline, UMLPackage.eINSTANCE.getNamedElement_Visibility()), lifeline.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.Lifeline.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(lifeline, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			if (isAccessible(UmlViewsRepository.Lifeline.Properties.represents)) {
				// init part
				representsSettings = new EObjectFlatComboSettings(lifeline, UMLPackage.eINSTANCE.getLifeline_Represents());
				basePart.initRepresents(representsSettings);
				// set the button mode
				basePart.setRepresentsButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Lifeline.Properties.interaction)) {
				// init part
				interactionSettings = new EObjectFlatComboSettings(lifeline, UMLPackage.eINSTANCE.getLifeline_Interaction());
				basePart.initInteraction(interactionSettings);
				// set the button mode
				basePart.setInteractionButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Lifeline.Properties.decomposedAs)) {
				// init part
				decomposedAsSettings = new EObjectFlatComboSettings(lifeline, UMLPackage.eINSTANCE.getLifeline_DecomposedAs());
				basePart.initDecomposedAs(decomposedAsSettings);
				// set the button mode
				basePart.setDecomposedAsButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Lifeline.Properties.coveredBy)) {
				coveredBySettings = new ReferencesTableSettings(lifeline, UMLPackage.eINSTANCE.getLifeline_CoveredBy());
				basePart.initCoveredBy(coveredBySettings);
			}
			// init filters
			
			
			if (isAccessible(UmlViewsRepository.Lifeline.Properties.clientDependency)) {
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
			if (isAccessible(UmlViewsRepository.Lifeline.Properties.represents)) {
				basePart.addFilterToRepresents(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof ConnectableElement); //$NON-NLS-1$ 
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.Lifeline.Properties.interaction)) {
				basePart.addFilterToInteraction(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof Interaction);
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.Lifeline.Properties.decomposedAs)) {
				basePart.addFilterToDecomposedAs(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof PartDecomposition); //$NON-NLS-1$ 
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.Lifeline.Properties.coveredBy)) {
				basePart.addFilterToCoveredBy(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof EObject)
							return (!basePart.isContainedInCoveredByTable((EObject)element));
						return element instanceof Resource;
					}
				
				});
				basePart.addFilterToCoveredBy(new EObjectFilter(UMLPackage.Literals.INTERACTION_FRAGMENT));
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
		if (editorKey == UmlViewsRepository.Lifeline.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.Lifeline.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.Lifeline.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.Lifeline.Properties.represents) {
			return UMLPackage.eINSTANCE.getLifeline_Represents();
		}
		if (editorKey == UmlViewsRepository.Lifeline.Properties.interaction) {
			return UMLPackage.eINSTANCE.getLifeline_Interaction();
		}
		if (editorKey == UmlViewsRepository.Lifeline.Properties.decomposedAs) {
			return UMLPackage.eINSTANCE.getLifeline_DecomposedAs();
		}
		if (editorKey == UmlViewsRepository.Lifeline.Properties.coveredBy) {
			return UMLPackage.eINSTANCE.getLifeline_CoveredBy();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		Lifeline lifeline = (Lifeline)semanticObject;
		if (UmlViewsRepository.Lifeline.Properties.name == event.getAffectedEditor()) {
			lifeline.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
		}
		if (UmlViewsRepository.Lifeline.Properties.visibility == event.getAffectedEditor()) {
			lifeline.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.Lifeline.Properties.clientDependency == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.Lifeline.Properties.represents == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				representsSettings.setToReference((ConnectableElement)event.getNewValue());
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
		if (UmlViewsRepository.Lifeline.Properties.interaction == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				interactionSettings.setToReference((Interaction)event.getNewValue());
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
				interactionSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.Lifeline.Properties.decomposedAs == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				decomposedAsSettings.setToReference((PartDecomposition)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				PartDecomposition eObject = UMLFactory.eINSTANCE.createPartDecomposition();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				decomposedAsSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.Lifeline.Properties.coveredBy == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof InteractionFragment) {
					coveredBySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				coveredBySettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				coveredBySettings.move(event.getNewIndex(), (InteractionFragment) event.getNewValue());
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
			LifelinePropertiesEditionPart basePart = (LifelinePropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Lifeline.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.Lifeline.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Lifeline.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getLifeline_Represents().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Lifeline.Properties.represents))
				basePart.setRepresents((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getLifeline_Interaction().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Lifeline.Properties.interaction))
				basePart.setInteraction((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getLifeline_DecomposedAs().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Lifeline.Properties.decomposedAs))
				basePart.setDecomposedAs((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getLifeline_CoveredBy().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Lifeline.Properties.coveredBy))
				basePart.updateCoveredBy();
			
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
			UMLPackage.eINSTANCE.getLifeline_Represents(),
			UMLPackage.eINSTANCE.getLifeline_Interaction(),
			UMLPackage.eINSTANCE.getLifeline_DecomposedAs(),
			UMLPackage.eINSTANCE.getLifeline_CoveredBy()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.Lifeline.Properties.interaction;
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
				if (UmlViewsRepository.Lifeline.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Lifeline.Properties.visibility == event.getAffectedEditor()) {
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
