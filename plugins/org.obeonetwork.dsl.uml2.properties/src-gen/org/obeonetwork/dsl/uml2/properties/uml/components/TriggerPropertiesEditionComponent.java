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
									import org.eclipse.emf.ecore.util.Diagnostician;
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
									import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
									import org.eclipse.uml2.types.TypesPackage;
									import org.eclipse.uml2.uml.Event;
									import org.eclipse.uml2.uml.Trigger;
									import org.eclipse.uml2.uml.UMLPackage;
									import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
									import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.VisibilityKind;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.Diagnostician;

import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.uml2.types.TypesPackage;

import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.UMLPackage;

import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class TriggerPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String GENERAL_PART = "General"; //$NON-NLS-1$

	
	/**
	 * Settings for event LinkEReferenceViewer
	 */
	private EObjectFlatComboSettings eventSettings;
	
	/**
	 * Creation Settings for event LinkEReferenceViewer
	 */
	private ReferencesTableSettings eventCreateSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public TriggerPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject trigger, String editing_mode) {
		super(editingContext, trigger, editing_mode);
		parts = new String[] { GENERAL_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.General.class;
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
			
			final Trigger trigger = (Trigger)elt;
			final GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.General.name))
				generalPart.setName(EEFConverterUtil.convertToString(TypesPackage.Literals.STRING, trigger.getName()));
			
			if (isAccessible(UmlViewsRepository.General.event)) {
				// init part
				eventSettings = new EObjectFlatComboSettings(trigger, UMLPackage.eINSTANCE.getTrigger_Event());
				eventCreateSettings = new ReferencesTableSettings(geteventCreateSettingsSource(), UMLPackage.eINSTANCE.getPackage_PackagedElement());
				generalPart.initEvent(eventSettings);
				// set the button mode
				generalPart.setEventButtonMode(ButtonsModeEnum.BROWSE);
			}
			// init filters
			
			
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
		if (editorKey == UmlViewsRepository.General.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.General.event) {
			return UMLPackage.eINSTANCE.getTrigger_Event();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		Trigger trigger = (Trigger)semanticObject;
		if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
			trigger.setName((java.lang.String)EEFConverterUtil.createFromString(TypesPackage.Literals.STRING, (String)event.getNewValue()));
		}
		if (UmlViewsRepository.General.event == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				eventSettings.setToReference((Event)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.EDIT) {
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, (EObject) event.getNewValue(), editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt((EObject) event.getNewValue(), PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
					if (editionPolicy != null) {
						editionPolicy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, eventSettings, editingContext.getAdapterFactory());
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
			GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null && isAccessible(UmlViewsRepository.General.name)) {
				if (msg.getNewValue() != null) {
					generalPart.setName(EcoreUtil.convertToString(TypesPackage.Literals.STRING, msg.getNewValue()));
				} else {
					generalPart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getTrigger_Event().equals(msg.getFeature()) && generalPart != null && isAccessible(UmlViewsRepository.General.event))
				generalPart.setEvent((EObject)msg.getNewValue());
			
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
			UMLPackage.eINSTANCE.getTrigger_Event()		);
		return new NotificationFilter[] {filter,};
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#mustBeComposed(java.lang.Object, int)
	 */
	public boolean mustBeComposed(Object key, int kind) {
		return key == UmlViewsRepository.General.name || key == UmlViewsRepository.General.event;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.General.event;
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
				if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}


	

	
	/**
	 * @ return source setting for eventCreateSettings
	 */
	public EObject geteventCreateSettingsSource() {
				return org.obeonetwork.dsl.uml2.properties.service.EEFService.getParent(semanticObject);
	}	
	

}
