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
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.RedefinableTemplateSignature;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateableElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class RedefinableTemplateSignaturePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private	ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for parameter ReferencesTable
	 */
	private	ReferencesTableSettings parameterSettings;
	
	/**
	 * Settings for template EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings templateSettings;
	
	/**
	 * Settings for extendedSignature ReferencesTable
	 */
	private	ReferencesTableSettings extendedSignatureSettings;
	
	/**
	 * Settings for classifier EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings classifierSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public RedefinableTemplateSignaturePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject redefinableTemplateSignature, String editing_mode) {
		super(editingContext, redefinableTemplateSignature, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.RedefinableTemplateSignature.class;
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
			final RedefinableTemplateSignature redefinableTemplateSignature = (RedefinableTemplateSignature)elt;
			final RedefinableTemplateSignaturePropertiesEditionPart basePart = (RedefinableTemplateSignaturePropertiesEditionPart)editingPart;
			// init values
			if (redefinableTemplateSignature.getName() != null && isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), redefinableTemplateSignature.getName()));
			
			if (isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), redefinableTemplateSignature.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(redefinableTemplateSignature, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			basePart.setIsLeaf(redefinableTemplateSignature.isLeaf());
			
			if (isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter)) {
				parameterSettings = new ReferencesTableSettings(redefinableTemplateSignature, UMLPackage.eINSTANCE.getTemplateSignature_Parameter());
				basePart.initParameter(parameterSettings);
			}
			if (isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.template)) {
				// init part
				templateSettings = new EObjectFlatComboSettings(redefinableTemplateSignature, UMLPackage.eINSTANCE.getTemplateSignature_Template());
				basePart.initTemplate(templateSettings);
				// set the button mode
				basePart.setTemplateButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature)) {
				extendedSignatureSettings = new ReferencesTableSettings(redefinableTemplateSignature, UMLPackage.eINSTANCE.getRedefinableTemplateSignature_ExtendedSignature());
				basePart.initExtendedSignature(extendedSignatureSettings);
			}
			if (isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.classifier)) {
				// init part
				classifierSettings = new EObjectFlatComboSettings(redefinableTemplateSignature, UMLPackage.eINSTANCE.getRedefinableTemplateSignature_Classifier());
				basePart.initClassifier(classifierSettings);
				// set the button mode
				basePart.setClassifierButtonMode(ButtonsModeEnum.BROWSE);
			}
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
			
			
			basePart.addFilterToParameter(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInParameterTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToParameter(new EObjectFilter(UMLPackage.eINSTANCE.getTemplateParameter()));
			// Start of user code for additional businessfilters for parameter
			// End of user code
			
			basePart.addFilterToTemplate(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof TemplateableElement);
				}
			
			});
			// Start of user code for additional businessfilters for template
			// End of user code
			
			basePart.addFilterToExtendedSignature(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInExtendedSignatureTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToExtendedSignature(new EObjectFilter(UMLPackage.eINSTANCE.getRedefinableTemplateSignature()));
			// Start of user code for additional businessfilters for extendedSignature
			// End of user code
			
			basePart.addFilterToClassifier(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof Classifier);
				}
			
			});
			// Start of user code for additional businessfilters for classifier
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
		if (editorKey == UmlViewsRepository.RedefinableTemplateSignature.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.RedefinableTemplateSignature.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.RedefinableTemplateSignature.Properties.isLeaf) {
			return UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf();
		}
		if (editorKey == UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter) {
			return UMLPackage.eINSTANCE.getTemplateSignature_Parameter();
		}
		if (editorKey == UmlViewsRepository.RedefinableTemplateSignature.Properties.template) {
			return UMLPackage.eINSTANCE.getTemplateSignature_Template();
		}
		if (editorKey == UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature) {
			return UMLPackage.eINSTANCE.getRedefinableTemplateSignature_ExtendedSignature();
		}
		if (editorKey == UmlViewsRepository.RedefinableTemplateSignature.Properties.classifier) {
			return UMLPackage.eINSTANCE.getRedefinableTemplateSignature_Classifier();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		RedefinableTemplateSignature redefinableTemplateSignature = (RedefinableTemplateSignature)semanticObject;
		if (UmlViewsRepository.RedefinableTemplateSignature.Properties.name == event.getAffectedEditor()) {
			redefinableTemplateSignature.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.RedefinableTemplateSignature.Properties.visibility == event.getAffectedEditor()) {
			redefinableTemplateSignature.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.RedefinableTemplateSignature.Properties.isLeaf == event.getAffectedEditor()) {
			redefinableTemplateSignature.setIsLeaf((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof TemplateParameter) {
					parameterSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					parameterSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.RedefinableTemplateSignature.Properties.template == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				templateSettings.setToReference((TemplateableElement)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, templateSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof RedefinableTemplateSignature) {
					extendedSignatureSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					extendedSignatureSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			RedefinableTemplateSignaturePropertiesEditionPart basePart = (RedefinableTemplateSignaturePropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getTemplateSignature_Parameter().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter))
				basePart.updateParameter();
			if (UMLPackage.eINSTANCE.getTemplateSignature_Template().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.template))
				basePart.setTemplate((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getRedefinableTemplateSignature_ExtendedSignature().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature))
				basePart.updateExtendedSignature();
			if (UMLPackage.eINSTANCE.getRedefinableTemplateSignature_Classifier().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.RedefinableTemplateSignature.Properties.classifier))
				basePart.setClassifier((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.RedefinableTemplateSignature.Properties.isLeaf || key == UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter || key == UmlViewsRepository.RedefinableTemplateSignature.Properties.template || key == UmlViewsRepository.RedefinableTemplateSignature.Properties.classifier;
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
				if (UmlViewsRepository.RedefinableTemplateSignature.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.RedefinableTemplateSignature.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.RedefinableTemplateSignature.Properties.isLeaf == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
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
