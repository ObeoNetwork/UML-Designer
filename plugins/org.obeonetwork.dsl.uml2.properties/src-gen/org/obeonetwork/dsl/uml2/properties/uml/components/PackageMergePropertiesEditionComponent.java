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

import org.eclipse.uml2.uml.PackageMerge;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

import org.obeonetwork.dsl.uml2.properties.uml.parts.PackageMergePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class PackageMergePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for mergedPackage EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings mergedPackageSettings;
	
	/**
	 * Settings for receivingPackage EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings receivingPackageSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public PackageMergePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject packageMerge, String editing_mode) {
		super(editingContext, packageMerge, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.PackageMerge.class;
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
			
			final PackageMerge packageMerge = (PackageMerge)elt;
			final PackageMergePropertiesEditionPart basePart = (PackageMergePropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.PackageMerge.Properties.mergedPackage)) {
				// init part
				mergedPackageSettings = new EObjectFlatComboSettings(packageMerge, UMLPackage.eINSTANCE.getPackageMerge_MergedPackage());
				basePart.initMergedPackage(mergedPackageSettings);
				// set the button mode
				basePart.setMergedPackageButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.PackageMerge.Properties.receivingPackage)) {
				// init part
				receivingPackageSettings = new EObjectFlatComboSettings(packageMerge, UMLPackage.eINSTANCE.getPackageMerge_ReceivingPackage());
				basePart.initReceivingPackage(receivingPackageSettings);
				// set the button mode
				basePart.setReceivingPackageButtonMode(ButtonsModeEnum.BROWSE);
			}
			// init filters
			if (isAccessible(UmlViewsRepository.PackageMerge.Properties.mergedPackage)) {
				basePart.addFilterToMergedPackage(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof Package);
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.PackageMerge.Properties.receivingPackage)) {
				basePart.addFilterToReceivingPackage(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof Package);
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
		if (editorKey == UmlViewsRepository.PackageMerge.Properties.mergedPackage) {
			return UMLPackage.eINSTANCE.getPackageMerge_MergedPackage();
		}
		if (editorKey == UmlViewsRepository.PackageMerge.Properties.receivingPackage) {
			return UMLPackage.eINSTANCE.getPackageMerge_ReceivingPackage();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		PackageMerge packageMerge = (PackageMerge)semanticObject;
		if (UmlViewsRepository.PackageMerge.Properties.mergedPackage == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				mergedPackageSettings.setToReference((Package)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				org.eclipse.uml2.uml.Package eObject = UMLFactory.eINSTANCE.createPackage();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				mergedPackageSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.PackageMerge.Properties.receivingPackage == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				receivingPackageSettings.setToReference((Package)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				org.eclipse.uml2.uml.Package eObject = UMLFactory.eINSTANCE.createPackage();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				receivingPackageSettings.setToReference(eObject);
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
			PackageMergePropertiesEditionPart basePart = (PackageMergePropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getPackageMerge_MergedPackage().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.PackageMerge.Properties.mergedPackage))
				basePart.setMergedPackage((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getPackageMerge_ReceivingPackage().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.PackageMerge.Properties.receivingPackage))
				basePart.setReceivingPackage((EObject)msg.getNewValue());
			
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
			UMLPackage.eINSTANCE.getPackageMerge_MergedPackage(),
			UMLPackage.eINSTANCE.getPackageMerge_ReceivingPackage()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.PackageMerge.Properties.mergedPackage || key == UmlViewsRepository.PackageMerge.Properties.receivingPackage;
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
