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

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.GeneralizationSet;
import org.eclipse.uml2.uml.InformationItem;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.InformationItemPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class InformationItemPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

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
	 * Settings for represented ReferencesTable
	 */
	private ReferencesTableSettings representedSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public InformationItemPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject informationItem, String editing_mode) {
		super(editingContext, informationItem, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.InformationItem.class;
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
			
			final InformationItem informationItem = (InformationItem)elt;
			final InformationItemPropertiesEditionPart basePart = (InformationItemPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.Literals.STRING, informationItem.getName()));
			
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.visibility)) {
				basePart.initVisibility(EEFUtils.choiceOfValues(informationItem, UMLPackage.eINSTANCE.getNamedElement_Visibility()), informationItem.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(informationItem, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.isLeaf)) {
				basePart.setIsLeaf(informationItem.isLeaf());
			}
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.owningTemplateParameter)) {
				// init part
				owningTemplateParameterSettings = new EObjectFlatComboSettings(informationItem, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter());
				basePart.initOwningTemplateParameter(owningTemplateParameterSettings);
				// set the button mode
				basePart.setOwningTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.templateParameter)) {
				// init part
				templateParameterSettings = new EObjectFlatComboSettings(informationItem, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter());
				basePart.initTemplateParameter(templateParameterSettings);
				// set the button mode
				basePart.setTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.isAbstract)) {
				basePart.setIsAbstract(informationItem.isAbstract());
			}
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.powertypeExtent)) {
				powertypeExtentSettings = new ReferencesTableSettings(informationItem, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
				basePart.initPowertypeExtent(powertypeExtentSettings);
			}
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.redefinedClassifier)) {
				redefinedClassifierSettings = new ReferencesTableSettings(informationItem, UMLPackage.eINSTANCE.getClassifier_RedefinedClassifier());
				basePart.initRedefinedClassifier(redefinedClassifierSettings);
			}
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.representation)) {
				// init part
				representationSettings = new EObjectFlatComboSettings(informationItem, UMLPackage.eINSTANCE.getClassifier_Representation());
				basePart.initRepresentation(representationSettings);
				// set the button mode
				basePart.setRepresentationButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.useCase)) {
				useCaseSettings = new ReferencesTableSettings(informationItem, UMLPackage.eINSTANCE.getClassifier_UseCase());
				basePart.initUseCase(useCaseSettings);
			}
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.represented)) {
				representedSettings = new ReferencesTableSettings(informationItem, UMLPackage.eINSTANCE.getInformationItem_Represented());
				basePart.initRepresented(representedSettings);
			}
			// init filters
			
			
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.clientDependency)) {
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
			
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.owningTemplateParameter)) {
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
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.templateParameter)) {
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
			
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.powertypeExtent)) {
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
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.redefinedClassifier)) {
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
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.representation)) {
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
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.useCase)) {
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
			if (isAccessible(UmlViewsRepository.InformationItem.Properties.represented)) {
				basePart.addFilterToRepresented(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof EObject)
							return (!basePart.isContainedInRepresentedTable((EObject)element));
						return element instanceof Resource;
					}
				
				});
				basePart.addFilterToRepresented(new EObjectFilter(UMLPackage.Literals.CLASSIFIER));
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
		if (editorKey == UmlViewsRepository.InformationItem.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.InformationItem.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.InformationItem.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.InformationItem.Properties.isLeaf) {
			return UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf();
		}
		if (editorKey == UmlViewsRepository.InformationItem.Properties.owningTemplateParameter) {
			return UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter();
		}
		if (editorKey == UmlViewsRepository.InformationItem.Properties.templateParameter) {
			return UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter();
		}
		if (editorKey == UmlViewsRepository.InformationItem.Properties.isAbstract) {
			return UMLPackage.eINSTANCE.getClassifier_IsAbstract();
		}
		if (editorKey == UmlViewsRepository.InformationItem.Properties.powertypeExtent) {
			return UMLPackage.eINSTANCE.getClassifier_PowertypeExtent();
		}
		if (editorKey == UmlViewsRepository.InformationItem.Properties.redefinedClassifier) {
			return UMLPackage.eINSTANCE.getClassifier_RedefinedClassifier();
		}
		if (editorKey == UmlViewsRepository.InformationItem.Properties.representation) {
			return UMLPackage.eINSTANCE.getClassifier_Representation();
		}
		if (editorKey == UmlViewsRepository.InformationItem.Properties.useCase) {
			return UMLPackage.eINSTANCE.getClassifier_UseCase();
		}
		if (editorKey == UmlViewsRepository.InformationItem.Properties.represented) {
			return UMLPackage.eINSTANCE.getInformationItem_Represented();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		InformationItem informationItem = (InformationItem)semanticObject;
		if (UmlViewsRepository.InformationItem.Properties.name == event.getAffectedEditor()) {
			informationItem.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
		}
		if (UmlViewsRepository.InformationItem.Properties.visibility == event.getAffectedEditor()) {
			informationItem.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.InformationItem.Properties.clientDependency == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.InformationItem.Properties.isLeaf == event.getAffectedEditor()) {
			informationItem.setIsLeaf((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.InformationItem.Properties.owningTemplateParameter == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.InformationItem.Properties.templateParameter == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.InformationItem.Properties.isAbstract == event.getAffectedEditor()) {
			informationItem.setIsAbstract((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.InformationItem.Properties.powertypeExtent == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.InformationItem.Properties.redefinedClassifier == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.InformationItem.Properties.representation == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.InformationItem.Properties.useCase == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.InformationItem.Properties.represented == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Classifier) {
					representedSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				representedSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				representedSettings.move(event.getNewIndex(), (Classifier) event.getNewValue());
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
			InformationItemPropertiesEditionPart basePart = (InformationItemPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.InformationItem.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.InformationItem.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InformationItem.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.InformationItem.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.InformationItem.Properties.owningTemplateParameter))
				basePart.setOwningTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.InformationItem.Properties.templateParameter))
				basePart.setTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getClassifier_IsAbstract().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.InformationItem.Properties.isAbstract))
				basePart.setIsAbstract((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getClassifier_PowertypeExtent().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InformationItem.Properties.powertypeExtent))
				basePart.updatePowertypeExtent();
			if (UMLPackage.eINSTANCE.getClassifier_RedefinedClassifier().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InformationItem.Properties.redefinedClassifier))
				basePart.updateRedefinedClassifier();
			if (UMLPackage.eINSTANCE.getClassifier_Representation().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.InformationItem.Properties.representation))
				basePart.setRepresentation((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getClassifier_UseCase().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InformationItem.Properties.useCase))
				basePart.updateUseCase();
			if (UMLPackage.eINSTANCE.getInformationItem_Represented().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InformationItem.Properties.represented))
				basePart.updateRepresented();
			
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
			UMLPackage.eINSTANCE.getInformationItem_Represented()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.InformationItem.Properties.isLeaf || key == UmlViewsRepository.InformationItem.Properties.isAbstract;
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
				if (UmlViewsRepository.InformationItem.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.InformationItem.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.InformationItem.Properties.isLeaf == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.InformationItem.Properties.isAbstract == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getClassifier_IsAbstract().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getClassifier_IsAbstract().getEAttributeType(), newValue);
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
