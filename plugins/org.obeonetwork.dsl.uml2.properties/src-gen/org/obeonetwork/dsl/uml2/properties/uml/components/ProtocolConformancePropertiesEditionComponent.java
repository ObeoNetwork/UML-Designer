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

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.uml2.uml.ProtocolConformance;
import org.eclipse.uml2.uml.ProtocolStateMachine;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolConformancePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ProtocolConformancePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for generalMachine EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings generalMachineSettings;
	
	/**
	 * Settings for specificMachine EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings specificMachineSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public ProtocolConformancePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject protocolConformance, String editing_mode) {
		super(editingContext, protocolConformance, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.ProtocolConformance.class;
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
			
			final ProtocolConformance protocolConformance = (ProtocolConformance)elt;
			final ProtocolConformancePropertiesEditionPart basePart = (ProtocolConformancePropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.ProtocolConformance.Properties.generalMachine)) {
				// init part
				generalMachineSettings = new EObjectFlatComboSettings(protocolConformance, UMLPackage.eINSTANCE.getProtocolConformance_GeneralMachine());
				basePart.initGeneralMachine(generalMachineSettings);
				// set the button mode
				basePart.setGeneralMachineButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ProtocolConformance.Properties.specificMachine)) {
				// init part
				specificMachineSettings = new EObjectFlatComboSettings(protocolConformance, UMLPackage.eINSTANCE.getProtocolConformance_SpecificMachine());
				basePart.initSpecificMachine(specificMachineSettings);
				// set the button mode
				basePart.setSpecificMachineButtonMode(ButtonsModeEnum.BROWSE);
			}
			// init filters
			if (isAccessible(UmlViewsRepository.ProtocolConformance.Properties.generalMachine)) {
				basePart.addFilterToGeneralMachine(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof ProtocolStateMachine);
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.ProtocolConformance.Properties.specificMachine)) {
				basePart.addFilterToSpecificMachine(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof ProtocolStateMachine);
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
		if (editorKey == UmlViewsRepository.ProtocolConformance.Properties.generalMachine) {
			return UMLPackage.eINSTANCE.getProtocolConformance_GeneralMachine();
		}
		if (editorKey == UmlViewsRepository.ProtocolConformance.Properties.specificMachine) {
			return UMLPackage.eINSTANCE.getProtocolConformance_SpecificMachine();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		ProtocolConformance protocolConformance = (ProtocolConformance)semanticObject;
		if (UmlViewsRepository.ProtocolConformance.Properties.generalMachine == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				generalMachineSettings.setToReference((ProtocolStateMachine)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				ProtocolStateMachine eObject = UMLFactory.eINSTANCE.createProtocolStateMachine();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				generalMachineSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.ProtocolConformance.Properties.specificMachine == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				specificMachineSettings.setToReference((ProtocolStateMachine)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				ProtocolStateMachine eObject = UMLFactory.eINSTANCE.createProtocolStateMachine();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				specificMachineSettings.setToReference(eObject);
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
			ProtocolConformancePropertiesEditionPart basePart = (ProtocolConformancePropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getProtocolConformance_GeneralMachine().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ProtocolConformance.Properties.generalMachine))
				basePart.setGeneralMachine((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getProtocolConformance_SpecificMachine().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ProtocolConformance.Properties.specificMachine))
				basePart.setSpecificMachine((EObject)msg.getNewValue());
			
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
			UMLPackage.eINSTANCE.getProtocolConformance_GeneralMachine(),
			UMLPackage.eINSTANCE.getProtocolConformance_SpecificMachine()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.ProtocolConformance.Properties.generalMachine || key == UmlViewsRepository.ProtocolConformance.Properties.specificMachine;
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
