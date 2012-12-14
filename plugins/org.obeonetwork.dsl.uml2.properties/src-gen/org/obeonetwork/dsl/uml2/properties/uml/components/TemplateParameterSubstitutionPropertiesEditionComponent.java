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

import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.eef.runtime.api.notify.EStructuralFeatureNotificationFilter;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;

import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;

import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateParameterSubstitution;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

import org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateParameterSubstitutionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class TemplateParameterSubstitutionPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for formal EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings formalSettings;
	
	/**
	 * Settings for actual EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings actualSettings;
	
	/**
	 * Settings for templateBinding EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings templateBindingSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public TemplateParameterSubstitutionPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject templateParameterSubstitution, String editing_mode) {
		super(editingContext, templateParameterSubstitution, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.TemplateParameterSubstitution.class;
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
			
			final TemplateParameterSubstitution templateParameterSubstitution = (TemplateParameterSubstitution)elt;
			final TemplateParameterSubstitutionPropertiesEditionPart basePart = (TemplateParameterSubstitutionPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.TemplateParameterSubstitution.Properties.formal)) {
				// init part
				formalSettings = new EObjectFlatComboSettings(templateParameterSubstitution, UMLPackage.eINSTANCE.getTemplateParameterSubstitution_Formal());
				basePart.initFormal(formalSettings);
				// set the button mode
				basePart.setFormalButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.TemplateParameterSubstitution.Properties.actual)) {
				// init part
				actualSettings = new EObjectFlatComboSettings(templateParameterSubstitution, UMLPackage.eINSTANCE.getTemplateParameterSubstitution_Actual());
				basePart.initActual(actualSettings);
				// set the button mode
				basePart.setActualButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding)) {
				// init part
				templateBindingSettings = new EObjectFlatComboSettings(templateParameterSubstitution, UMLPackage.eINSTANCE.getTemplateParameterSubstitution_TemplateBinding());
				basePart.initTemplateBinding(templateBindingSettings);
				// set the button mode
				basePart.setTemplateBindingButtonMode(ButtonsModeEnum.BROWSE);
			}
			// init filters
			if (isAccessible(UmlViewsRepository.TemplateParameterSubstitution.Properties.formal)) {
				basePart.addFilterToFormal(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof TemplateParameter);
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.TemplateParameterSubstitution.Properties.actual)) {
				basePart.addFilterToActual(new ViewerFilter() {
				
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
			if (isAccessible(UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding)) {
				basePart.addFilterToTemplateBinding(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof TemplateBinding);
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
		if (editorKey == UmlViewsRepository.TemplateParameterSubstitution.Properties.formal) {
			return UMLPackage.eINSTANCE.getTemplateParameterSubstitution_Formal();
		}
		if (editorKey == UmlViewsRepository.TemplateParameterSubstitution.Properties.actual) {
			return UMLPackage.eINSTANCE.getTemplateParameterSubstitution_Actual();
		}
		if (editorKey == UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding) {
			return UMLPackage.eINSTANCE.getTemplateParameterSubstitution_TemplateBinding();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		TemplateParameterSubstitution templateParameterSubstitution = (TemplateParameterSubstitution)semanticObject;
		if (UmlViewsRepository.TemplateParameterSubstitution.Properties.formal == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				formalSettings.setToReference((TemplateParameter)event.getNewValue());
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
				formalSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.TemplateParameterSubstitution.Properties.actual == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				actualSettings.setToReference((ParameterableElement)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, actualSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				templateBindingSettings.setToReference((TemplateBinding)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				TemplateBinding eObject = UMLFactory.eINSTANCE.createTemplateBinding();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				templateBindingSettings.setToReference(eObject);
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
			TemplateParameterSubstitutionPropertiesEditionPart basePart = (TemplateParameterSubstitutionPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getTemplateParameterSubstitution_Formal().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.TemplateParameterSubstitution.Properties.formal))
				basePart.setFormal((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTemplateParameterSubstitution_Actual().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.TemplateParameterSubstitution.Properties.actual))
				basePart.setActual((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTemplateParameterSubstitution_TemplateBinding().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding))
				basePart.setTemplateBinding((EObject)msg.getNewValue());
			
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
			UMLPackage.eINSTANCE.getTemplateParameterSubstitution_Formal(),
			UMLPackage.eINSTANCE.getTemplateParameterSubstitution_Actual(),
			UMLPackage.eINSTANCE.getTemplateParameterSubstitution_TemplateBinding()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.TemplateParameterSubstitution.Properties.formal || key == UmlViewsRepository.TemplateParameterSubstitution.Properties.actual || key == UmlViewsRepository.TemplateParameterSubstitution.Properties.templateBinding;
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
			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}


	

}
