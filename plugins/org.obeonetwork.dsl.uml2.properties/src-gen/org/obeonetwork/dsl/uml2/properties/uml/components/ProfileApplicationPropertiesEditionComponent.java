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
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ProfileApplicationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ProfileApplicationPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for appliedProfile EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings appliedProfileSettings;
	
	/**
	 * Settings for applyingPackage EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings applyingPackageSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public ProfileApplicationPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject profileApplication, String editing_mode) {
		super(editingContext, profileApplication, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.ProfileApplication.class;
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
			final ProfileApplication profileApplication = (ProfileApplication)elt;
			final ProfileApplicationPropertiesEditionPart basePart = (ProfileApplicationPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.ProfileApplication.Properties.appliedProfile)) {
				// init part
				appliedProfileSettings = new EObjectFlatComboSettings(profileApplication, UMLPackage.eINSTANCE.getProfileApplication_AppliedProfile());
				basePart.initAppliedProfile(appliedProfileSettings);
				// set the button mode
				basePart.setAppliedProfileButtonMode(ButtonsModeEnum.BROWSE);
			}
			basePart.setIsStrict(profileApplication.isStrict());
			
			if (isAccessible(UmlViewsRepository.ProfileApplication.Properties.applyingPackage)) {
				// init part
				applyingPackageSettings = new EObjectFlatComboSettings(profileApplication, UMLPackage.eINSTANCE.getProfileApplication_ApplyingPackage());
				basePart.initApplyingPackage(applyingPackageSettings);
				// set the button mode
				basePart.setApplyingPackageButtonMode(ButtonsModeEnum.BROWSE);
			}
			// init filters
			basePart.addFilterToAppliedProfile(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof Profile);
				}
			
			});
			// Start of user code for additional businessfilters for appliedProfile
			
			// End of user code
			
			
			basePart.addFilterToApplyingPackage(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof Package);
				}
			
			});
			// Start of user code for additional businessfilters for applyingPackage
			
			// End of user code
			
			// init values for referenced views
			
			// init filters for referenced views
			
		}
		setInitializing(false);
	}






	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		ProfileApplication profileApplication = (ProfileApplication)semanticObject;
		if (UmlViewsRepository.ProfileApplication.Properties.appliedProfile == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				appliedProfileSettings.setToReference((Profile)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				Profile eObject = UMLFactory.eINSTANCE.createProfile();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				appliedProfileSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.ProfileApplication.Properties.isStrict == event.getAffectedEditor()) {
			profileApplication.setIsStrict((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.ProfileApplication.Properties.applyingPackage == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				applyingPackageSettings.setToReference((Package)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				Package eObject = UMLFactory.eINSTANCE.createPackage();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				applyingPackageSettings.setToReference(eObject);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			ProfileApplicationPropertiesEditionPart basePart = (ProfileApplicationPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getProfileApplication_AppliedProfile().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ProfileApplication.Properties.appliedProfile))
				basePart.setAppliedProfile((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getProfileApplication_IsStrict().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ProfileApplication.Properties.isStrict))
				basePart.setIsStrict((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getProfileApplication_ApplyingPackage().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ProfileApplication.Properties.applyingPackage))
				basePart.setApplyingPackage((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.ProfileApplication.Properties.appliedProfile || key == UmlViewsRepository.ProfileApplication.Properties.isStrict || key == UmlViewsRepository.ProfileApplication.Properties.applyingPackage;
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
				if (UmlViewsRepository.ProfileApplication.Properties.isStrict == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getProfileApplication_IsStrict().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getProfileApplication_IsStrict().getEAttributeType(), newValue);
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
