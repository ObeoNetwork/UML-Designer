/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.components;

// Start of user code for imports
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.GeneralizationSet;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.VisibilityKind;
import org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class FunctionBehaviorPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private	ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for owningTemplateParameter EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings owningTemplateParameterSettings;
	
	/**
	 * Settings for templateParameter EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings templateParameterSettings;
	
	/**
	 * Settings for powertypeExtent ReferencesTable
	 */
	private	ReferencesTableSettings powertypeExtentSettings;
	
	/**
	 * Settings for redefinedClassifier ReferencesTable
	 */
	private	ReferencesTableSettings redefinedClassifierSettings;
	
	/**
	 * Settings for representation EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings representationSettings;
	
	/**
	 * Settings for useCase ReferencesTable
	 */
	private	ReferencesTableSettings useCaseSettings;
	
	/**
	 * Settings for classifierBehavior EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings classifierBehaviorSettings;
	
	/**
	 * Settings for redefinedBehavior ReferencesTable
	 */
	private	ReferencesTableSettings redefinedBehaviorSettings;
	
	/**
	 * Settings for precondition ReferencesTable
	 */
	private	ReferencesTableSettings preconditionSettings;
	
	/**
	 * Settings for postcondition ReferencesTable
	 */
	private	ReferencesTableSettings postconditionSettings;
	
	/**
	 * Settings for specification EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings specificationSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public FunctionBehaviorPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject functionBehavior, String editing_mode) {
		super(editingContext, functionBehavior, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.FunctionBehavior.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object, int, org.eclipse.emf.ecore.EObject, 
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 */
	public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);
			final FunctionBehavior functionBehavior = (FunctionBehavior)elt;
			final FunctionBehaviorPropertiesEditionPart basePart = (FunctionBehaviorPropertiesEditionPart)editingPart;
			// init values
			if (functionBehavior.getName() != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), functionBehavior.getName()));
			
			if (isAccessible(UmlViewsRepository.FunctionBehavior.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), functionBehavior.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.FunctionBehavior.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(functionBehavior, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			basePart.setIsLeaf(functionBehavior.isLeaf());
			
			if (isAccessible(UmlViewsRepository.FunctionBehavior.Properties.owningTemplateParameter)) {
				// init part
				owningTemplateParameterSettings = new EObjectFlatComboSettings(functionBehavior, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter());
				basePart.initOwningTemplateParameter(owningTemplateParameterSettings);
				// set the button mode
				basePart.setOwningTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.FunctionBehavior.Properties.templateParameter)) {
				// init part
				templateParameterSettings = new EObjectFlatComboSettings(functionBehavior, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter());
				basePart.initTemplateParameter(templateParameterSettings);
				// set the button mode
				basePart.setTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			basePart.setIsAbstract(functionBehavior.isAbstract());
			
			if (isAccessible(UmlViewsRepository.FunctionBehavior.Properties.powertypeExtent)) {
				powertypeExtentSettings = new ReferencesTableSettings(functionBehavior, UMLPackage.eINSTANCE.getClassifier_PowertypeExtent());
				basePart.initPowertypeExtent(powertypeExtentSettings);
			}
			if (isAccessible(UmlViewsRepository.FunctionBehavior.Properties.redefinedClassifier)) {
				redefinedClassifierSettings = new ReferencesTableSettings(functionBehavior, UMLPackage.eINSTANCE.getClassifier_RedefinedClassifier());
				basePart.initRedefinedClassifier(redefinedClassifierSettings);
			}
			if (isAccessible(UmlViewsRepository.FunctionBehavior.Properties.representation)) {
				// init part
				representationSettings = new EObjectFlatComboSettings(functionBehavior, UMLPackage.eINSTANCE.getClassifier_Representation());
				basePart.initRepresentation(representationSettings);
				// set the button mode
				basePart.setRepresentationButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.FunctionBehavior.Properties.useCase)) {
				useCaseSettings = new ReferencesTableSettings(functionBehavior, UMLPackage.eINSTANCE.getClassifier_UseCase());
				basePart.initUseCase(useCaseSettings);
			}
			if (isAccessible(UmlViewsRepository.FunctionBehavior.Properties.classifierBehavior)) {
				// init part
				classifierBehaviorSettings = new EObjectFlatComboSettings(functionBehavior, UMLPackage.eINSTANCE.getBehavioredClassifier_ClassifierBehavior());
				basePart.initClassifierBehavior(classifierBehaviorSettings);
				// set the button mode
				basePart.setClassifierBehaviorButtonMode(ButtonsModeEnum.BROWSE);
			}
			basePart.setIsActive(functionBehavior.isActive());
			
			basePart.setIsReentrant(functionBehavior.isReentrant());
			
			if (isAccessible(UmlViewsRepository.FunctionBehavior.Properties.redefinedBehavior)) {
				redefinedBehaviorSettings = new ReferencesTableSettings(functionBehavior, UMLPackage.eINSTANCE.getBehavior_RedefinedBehavior());
				basePart.initRedefinedBehavior(redefinedBehaviorSettings);
			}
			if (isAccessible(UmlViewsRepository.FunctionBehavior.Properties.precondition)) {
				preconditionSettings = new ReferencesTableSettings(functionBehavior, UMLPackage.eINSTANCE.getBehavior_Precondition());
				basePart.initPrecondition(preconditionSettings);
			}
			if (isAccessible(UmlViewsRepository.FunctionBehavior.Properties.postcondition)) {
				postconditionSettings = new ReferencesTableSettings(functionBehavior, UMLPackage.eINSTANCE.getBehavior_Postcondition());
				basePart.initPostcondition(postconditionSettings);
			}
			if (isAccessible(UmlViewsRepository.FunctionBehavior.Properties.specification)) {
				// init part
				specificationSettings = new EObjectFlatComboSettings(functionBehavior, UMLPackage.eINSTANCE.getBehavior_Specification());
				basePart.initSpecification(specificationSettings);
				// set the button mode
				basePart.setSpecificationButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (functionBehavior.getBodies() != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.body))
				basePart.setBody(functionBehavior.getBodies());
			
			if (functionBehavior.getLanguages() != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.language))
				basePart.setLanguage(functionBehavior.getLanguages());
			
			// init filters
			
			
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
			basePart.addFilterToClientDependency(new EObjectFilter(UMLPackage.eINSTANCE.getDependency()));
			// Start of user code for additional businessfilters for clientDependency
			// End of user code
			
			
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
			// Start of user code for additional businessfilters for owningTemplateParameter
			// End of user code
			
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
			// Start of user code for additional businessfilters for templateParameter
			// End of user code
			
			
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
			basePart.addFilterToPowertypeExtent(new EObjectFilter(UMLPackage.eINSTANCE.getGeneralizationSet()));
			// Start of user code for additional businessfilters for powertypeExtent
			// End of user code
			
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
			basePart.addFilterToRedefinedClassifier(new EObjectFilter(UMLPackage.eINSTANCE.getClassifier()));
			// Start of user code for additional businessfilters for redefinedClassifier
			// End of user code
			
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
			// Start of user code for additional businessfilters for representation
			// End of user code
			
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
			basePart.addFilterToUseCase(new EObjectFilter(UMLPackage.eINSTANCE.getUseCase()));
			// Start of user code for additional businessfilters for useCase
			// End of user code
			
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
			// Start of user code for additional businessfilters for classifierBehavior
			// End of user code
			
			
			
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
			basePart.addFilterToRedefinedBehavior(new EObjectFilter(UMLPackage.eINSTANCE.getBehavior()));
			// Start of user code for additional businessfilters for redefinedBehavior
			// End of user code
			
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
			basePart.addFilterToPrecondition(new EObjectFilter(UMLPackage.eINSTANCE.getConstraint()));
			// Start of user code for additional businessfilters for precondition
			// End of user code
			
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
			basePart.addFilterToPostcondition(new EObjectFilter(UMLPackage.eINSTANCE.getConstraint()));
			// Start of user code for additional businessfilters for postcondition
			// End of user code
			
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
			// Start of user code for additional businessfilters for specification
			// End of user code
			
			
			
			// init values for referenced views
			
			// init filters for referenced views
			
		}
		setInitializing(false);
	}























	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
	 */
	protected EStructuralFeature associatedFeature(Object editorKey) {
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.isLeaf) {
			return UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.owningTemplateParameter) {
			return UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.templateParameter) {
			return UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.isAbstract) {
			return UMLPackage.eINSTANCE.getClassifier_IsAbstract();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.powertypeExtent) {
			return UMLPackage.eINSTANCE.getClassifier_PowertypeExtent();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.redefinedClassifier) {
			return UMLPackage.eINSTANCE.getClassifier_RedefinedClassifier();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.representation) {
			return UMLPackage.eINSTANCE.getClassifier_Representation();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.useCase) {
			return UMLPackage.eINSTANCE.getClassifier_UseCase();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.classifierBehavior) {
			return UMLPackage.eINSTANCE.getBehavioredClassifier_ClassifierBehavior();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.isActive) {
			return UMLPackage.eINSTANCE.getClass_IsActive();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.isReentrant) {
			return UMLPackage.eINSTANCE.getBehavior_IsReentrant();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.redefinedBehavior) {
			return UMLPackage.eINSTANCE.getBehavior_RedefinedBehavior();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.precondition) {
			return UMLPackage.eINSTANCE.getBehavior_Precondition();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.postcondition) {
			return UMLPackage.eINSTANCE.getBehavior_Postcondition();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.specification) {
			return UMLPackage.eINSTANCE.getBehavior_Specification();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.body) {
			return UMLPackage.eINSTANCE.getOpaqueBehavior_Body();
		}
		if (editorKey == UmlViewsRepository.FunctionBehavior.Properties.language) {
			return UMLPackage.eINSTANCE.getOpaqueBehavior_Language();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		FunctionBehavior functionBehavior = (FunctionBehavior)semanticObject;
		if (UmlViewsRepository.FunctionBehavior.Properties.name == event.getAffectedEditor()) {
			functionBehavior.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.FunctionBehavior.Properties.visibility == event.getAffectedEditor()) {
			functionBehavior.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.FunctionBehavior.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.FunctionBehavior.Properties.isLeaf == event.getAffectedEditor()) {
			functionBehavior.setIsLeaf((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.FunctionBehavior.Properties.owningTemplateParameter == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.FunctionBehavior.Properties.templateParameter == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.FunctionBehavior.Properties.isAbstract == event.getAffectedEditor()) {
			functionBehavior.setIsAbstract((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.FunctionBehavior.Properties.powertypeExtent == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof GeneralizationSet) {
					powertypeExtentSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					powertypeExtentSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.FunctionBehavior.Properties.redefinedClassifier == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Classifier) {
					redefinedClassifierSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					redefinedClassifierSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.FunctionBehavior.Properties.representation == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.FunctionBehavior.Properties.useCase == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof UseCase) {
					useCaseSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					useCaseSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.FunctionBehavior.Properties.classifierBehavior == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.FunctionBehavior.Properties.isActive == event.getAffectedEditor()) {
			functionBehavior.setIsActive((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.FunctionBehavior.Properties.isReentrant == event.getAffectedEditor()) {
			functionBehavior.setIsReentrant((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.FunctionBehavior.Properties.redefinedBehavior == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Behavior) {
					redefinedBehaviorSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					redefinedBehaviorSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.FunctionBehavior.Properties.precondition == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Constraint) {
					preconditionSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					preconditionSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.FunctionBehavior.Properties.postcondition == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Constraint) {
					postconditionSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					postconditionSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.FunctionBehavior.Properties.specification == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.FunctionBehavior.Properties.body == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				functionBehavior.getBodies().clear();
				functionBehavior.getBodies().addAll(((List) event.getNewValue()));
			}
		}
		if (UmlViewsRepository.FunctionBehavior.Properties.language == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				functionBehavior.getLanguages().clear();
				functionBehavior.getLanguages().addAll(((List) event.getNewValue()));
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			FunctionBehaviorPropertiesEditionPart basePart = (FunctionBehaviorPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.owningTemplateParameter))
				basePart.setOwningTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.templateParameter))
				basePart.setTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getClassifier_IsAbstract().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.isAbstract))
				basePart.setIsAbstract((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getClassifier_PowertypeExtent().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.powertypeExtent))
				basePart.updatePowertypeExtent();
			if (UMLPackage.eINSTANCE.getClassifier_RedefinedClassifier().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.redefinedClassifier))
				basePart.updateRedefinedClassifier();
			if (UMLPackage.eINSTANCE.getClassifier_Representation().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.representation))
				basePart.setRepresentation((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getClassifier_UseCase().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.useCase))
				basePart.updateUseCase();
			if (UMLPackage.eINSTANCE.getBehavioredClassifier_ClassifierBehavior().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.classifierBehavior))
				basePart.setClassifierBehavior((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getClass_IsActive().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.isActive))
				basePart.setIsActive((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavior_IsReentrant().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.isReentrant))
				basePart.setIsReentrant((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavior_RedefinedBehavior().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.redefinedBehavior))
				basePart.updateRedefinedBehavior();
			if (UMLPackage.eINSTANCE.getBehavior_Precondition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.precondition))
				basePart.updatePrecondition();
			if (UMLPackage.eINSTANCE.getBehavior_Postcondition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.postcondition))
				basePart.updatePostcondition();
			if (UMLPackage.eINSTANCE.getBehavior_Specification().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.specification))
				basePart.setSpecification((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getOpaqueBehavior_Body().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.body)) {
				basePart.setBody(((FunctionBehavior)semanticObject).getBodies());
			}
			
			if (UMLPackage.eINSTANCE.getOpaqueBehavior_Language().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.FunctionBehavior.Properties.language)) {
				basePart.setLanguage(((FunctionBehavior)semanticObject).getLanguages());
			}
			
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.FunctionBehavior.Properties.isLeaf || key == UmlViewsRepository.FunctionBehavior.Properties.isAbstract || key == UmlViewsRepository.FunctionBehavior.Properties.isActive || key == UmlViewsRepository.FunctionBehavior.Properties.isReentrant;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
		Diagnostic ret = Diagnostic.OK_INSTANCE;
		if (event.getNewValue() != null) {
			try {
				if (UmlViewsRepository.FunctionBehavior.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.FunctionBehavior.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.FunctionBehavior.Properties.isLeaf == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.FunctionBehavior.Properties.isAbstract == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getClassifier_IsAbstract().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getClassifier_IsAbstract().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.FunctionBehavior.Properties.isActive == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getClass_IsActive().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getClass_IsActive().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.FunctionBehavior.Properties.isReentrant == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getBehavior_IsReentrant().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getBehavior_IsReentrant().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.FunctionBehavior.Properties.body == event.getAffectedEditor()) {
					BasicDiagnostic chain = new BasicDiagnostic();
					for (Iterator iterator = ((List)event.getNewValue()).iterator(); iterator.hasNext();) {
						chain.add(Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getOpaqueBehavior_Body().getEAttributeType(), iterator.next()));
					}
					ret = chain;
				}
				if (UmlViewsRepository.FunctionBehavior.Properties.language == event.getAffectedEditor()) {
					BasicDiagnostic chain = new BasicDiagnostic();
					for (Iterator iterator = ((List)event.getNewValue()).iterator(); iterator.hasNext();) {
						chain.add(Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getOpaqueBehavior_Language().getEAttributeType(), iterator.next()));
					}
					ret = chain;
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
