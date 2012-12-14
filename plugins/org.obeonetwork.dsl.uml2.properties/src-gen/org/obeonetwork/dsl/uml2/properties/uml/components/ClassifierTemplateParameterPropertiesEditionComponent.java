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

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ClassifierTemplateParameter;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ClassifierTemplateParameterPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for signature EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings signatureSettings;
	
	/**
	 * Settings for parameteredElement EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings parameteredElementSettings;
	
	/**
	 * Settings for default EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings default_Settings;
	
	/**
	 * Settings for constrainingClassifier ReferencesTable
	 */
	private ReferencesTableSettings constrainingClassifierSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public ClassifierTemplateParameterPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject classifierTemplateParameter, String editing_mode) {
		super(editingContext, classifierTemplateParameter, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.ClassifierTemplateParameter.class;
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
			
			final ClassifierTemplateParameter classifierTemplateParameter = (ClassifierTemplateParameter)elt;
			final ClassifierTemplateParameterPropertiesEditionPart basePart = (ClassifierTemplateParameterPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.ClassifierTemplateParameter.Properties.signature)) {
				// init part
				signatureSettings = new EObjectFlatComboSettings(classifierTemplateParameter, UMLPackage.eINSTANCE.getTemplateParameter_Signature());
				basePart.initSignature(signatureSettings);
				// set the button mode
				basePart.setSignatureButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ClassifierTemplateParameter.Properties.parameteredElement)) {
				// init part
				parameteredElementSettings = new EObjectFlatComboSettings(classifierTemplateParameter, UMLPackage.eINSTANCE.getTemplateParameter_ParameteredElement());
				basePart.initParameteredElement(parameteredElementSettings);
				// set the button mode
				basePart.setParameteredElementButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ClassifierTemplateParameter.Properties.default_)) {
				// init part
				default_Settings = new EObjectFlatComboSettings(classifierTemplateParameter, UMLPackage.eINSTANCE.getTemplateParameter_Default());
				basePart.initDefault_(default_Settings);
				// set the button mode
				basePart.setDefault_ButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ClassifierTemplateParameter.Properties.allowSubstitutable)) {
				basePart.setAllowSubstitutable(classifierTemplateParameter.isAllowSubstitutable());
			}
			if (isAccessible(UmlViewsRepository.ClassifierTemplateParameter.Properties.constrainingClassifier)) {
				constrainingClassifierSettings = new ReferencesTableSettings(classifierTemplateParameter, UMLPackage.eINSTANCE.getClassifierTemplateParameter_ConstrainingClassifier());
				basePart.initConstrainingClassifier(constrainingClassifierSettings);
			}
			// init filters
			if (isAccessible(UmlViewsRepository.ClassifierTemplateParameter.Properties.signature)) {
				basePart.addFilterToSignature(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof TemplateSignature);
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.ClassifierTemplateParameter.Properties.parameteredElement)) {
				basePart.addFilterToParameteredElement(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof ParameterableElement);
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.ClassifierTemplateParameter.Properties.default_)) {
				basePart.addFilterToDefault_(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof ParameterableElement); //$NON-NLS-1$ 
					}
					
				});
			}
			
			if (isAccessible(UmlViewsRepository.ClassifierTemplateParameter.Properties.constrainingClassifier)) {
				basePart.addFilterToConstrainingClassifier(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof EObject)
							return (!basePart.isContainedInConstrainingClassifierTable((EObject)element));
						return element instanceof Resource;
					}
				
				});
				basePart.addFilterToConstrainingClassifier(new EObjectFilter(UMLPackage.Literals.CLASSIFIER));
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
		if (editorKey == UmlViewsRepository.ClassifierTemplateParameter.Properties.signature) {
			return UMLPackage.eINSTANCE.getTemplateParameter_Signature();
		}
		if (editorKey == UmlViewsRepository.ClassifierTemplateParameter.Properties.parameteredElement) {
			return UMLPackage.eINSTANCE.getTemplateParameter_ParameteredElement();
		}
		if (editorKey == UmlViewsRepository.ClassifierTemplateParameter.Properties.default_) {
			return UMLPackage.eINSTANCE.getTemplateParameter_Default();
		}
		if (editorKey == UmlViewsRepository.ClassifierTemplateParameter.Properties.allowSubstitutable) {
			return UMLPackage.eINSTANCE.getClassifierTemplateParameter_AllowSubstitutable();
		}
		if (editorKey == UmlViewsRepository.ClassifierTemplateParameter.Properties.constrainingClassifier) {
			return UMLPackage.eINSTANCE.getClassifierTemplateParameter_ConstrainingClassifier();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		ClassifierTemplateParameter classifierTemplateParameter = (ClassifierTemplateParameter)semanticObject;
		if (UmlViewsRepository.ClassifierTemplateParameter.Properties.signature == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				signatureSettings.setToReference((TemplateSignature)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				TemplateSignature eObject = UMLFactory.eINSTANCE.createTemplateSignature();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				signatureSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.ClassifierTemplateParameter.Properties.parameteredElement == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				parameteredElementSettings.setToReference((ParameterableElement)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, parameteredElementSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.ClassifierTemplateParameter.Properties.default_ == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				default_Settings.setToReference((ParameterableElement)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, default_Settings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.ClassifierTemplateParameter.Properties.allowSubstitutable == event.getAffectedEditor()) {
			classifierTemplateParameter.setAllowSubstitutable((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.ClassifierTemplateParameter.Properties.constrainingClassifier == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Classifier) {
					constrainingClassifierSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				constrainingClassifierSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				constrainingClassifierSettings.move(event.getNewIndex(), (Classifier) event.getNewValue());
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
			ClassifierTemplateParameterPropertiesEditionPart basePart = (ClassifierTemplateParameterPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getTemplateParameter_Signature().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ClassifierTemplateParameter.Properties.signature))
				basePart.setSignature((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTemplateParameter_ParameteredElement().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ClassifierTemplateParameter.Properties.parameteredElement))
				basePart.setParameteredElement((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTemplateParameter_Default().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ClassifierTemplateParameter.Properties.default_))
				basePart.setDefault_((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getClassifierTemplateParameter_AllowSubstitutable().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.ClassifierTemplateParameter.Properties.allowSubstitutable))
				basePart.setAllowSubstitutable((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getClassifierTemplateParameter_ConstrainingClassifier().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ClassifierTemplateParameter.Properties.constrainingClassifier))
				basePart.updateConstrainingClassifier();
			
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
			UMLPackage.eINSTANCE.getTemplateParameter_Signature(),
			UMLPackage.eINSTANCE.getTemplateParameter_ParameteredElement(),
			UMLPackage.eINSTANCE.getTemplateParameter_Default(),
			UMLPackage.eINSTANCE.getClassifierTemplateParameter_AllowSubstitutable(),
			UMLPackage.eINSTANCE.getClassifierTemplateParameter_ConstrainingClassifier()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.ClassifierTemplateParameter.Properties.signature || key == UmlViewsRepository.ClassifierTemplateParameter.Properties.parameteredElement || key == UmlViewsRepository.ClassifierTemplateParameter.Properties.allowSubstitutable;
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
				if (UmlViewsRepository.ClassifierTemplateParameter.Properties.allowSubstitutable == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getClassifierTemplateParameter_AllowSubstitutable().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getClassifierTemplateParameter_AllowSubstitutable().getEAttributeType(), newValue);
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
