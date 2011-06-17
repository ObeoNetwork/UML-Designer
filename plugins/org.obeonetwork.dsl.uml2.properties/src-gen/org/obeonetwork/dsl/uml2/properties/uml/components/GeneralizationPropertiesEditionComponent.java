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
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.GeneralizationSet;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.parts.GeneralizationPropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class GeneralizationPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for general EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings generalSettings;
	
	/**
	 * Settings for generalizationSet ReferencesTable
	 */
	private	ReferencesTableSettings generalizationSetSettings;
	
	/**
	 * Settings for specific EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings specificSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public GeneralizationPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject generalization, String editing_mode) {
		super(editingContext, generalization, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.Generalization.class;
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
			final Generalization generalization = (Generalization)elt;
			final GeneralizationPropertiesEditionPart basePart = (GeneralizationPropertiesEditionPart)editingPart;
			// init values
			basePart.setIsSubstitutable(generalization.isSubstitutable());
			
			if (isAccessible(UmlViewsRepository.Generalization.Properties.general)) {
				// init part
				generalSettings = new EObjectFlatComboSettings(generalization, UMLPackage.eINSTANCE.getGeneralization_General());
				basePart.initGeneral(generalSettings);
				// set the button mode
				basePart.setGeneralButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Generalization.Properties.generalizationSet)) {
				generalizationSetSettings = new ReferencesTableSettings(generalization, UMLPackage.eINSTANCE.getGeneralization_GeneralizationSet());
				basePart.initGeneralizationSet(generalizationSetSettings);
			}
			if (isAccessible(UmlViewsRepository.Generalization.Properties.specific)) {
				// init part
				specificSettings = new EObjectFlatComboSettings(generalization, UMLPackage.eINSTANCE.getGeneralization_Specific());
				basePart.initSpecific(specificSettings);
				// set the button mode
				basePart.setSpecificButtonMode(ButtonsModeEnum.BROWSE);
			}
			// init filters
			
			basePart.addFilterToGeneral(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof Classifier);
				}
			
			});
			// Start of user code for additional businessfilters for general
			
			// End of user code
			
			basePart.addFilterToGeneralizationSet(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInGeneralizationSetTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToGeneralizationSet(new EObjectFilter(UMLPackage.eINSTANCE.getGeneralizationSet()));
			// Start of user code for additional businessfilters for generalizationSet
			
			// End of user code
			
			basePart.addFilterToSpecific(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof Classifier);
				}
			
			});
			// Start of user code for additional businessfilters for specific
			
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
		Generalization generalization = (Generalization)semanticObject;
		if (UmlViewsRepository.Generalization.Properties.isSubstitutable == event.getAffectedEditor()) {
			generalization.setIsSubstitutable((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Generalization.Properties.general == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				generalSettings.setToReference((Classifier)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, generalSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.Generalization.Properties.generalizationSet == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof GeneralizationSet) {
					generalizationSetSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					generalizationSetSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Generalization.Properties.specific == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				specificSettings.setToReference((Classifier)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, specificSettings, editingContext.getAdapterFactory());
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
		if (editingPart.isVisible()) {	
			GeneralizationPropertiesEditionPart basePart = (GeneralizationPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getGeneralization_IsSubstitutable().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Generalization.Properties.isSubstitutable))
				basePart.setIsSubstitutable((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getGeneralization_General().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Generalization.Properties.general))
				basePart.setGeneral((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getGeneralization_GeneralizationSet().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Generalization.Properties.generalizationSet))
				basePart.updateGeneralizationSet();
			if (UMLPackage.eINSTANCE.getGeneralization_Specific().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Generalization.Properties.specific))
				basePart.setSpecific((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.Generalization.Properties.general || key == UmlViewsRepository.Generalization.Properties.specific;
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
				if (UmlViewsRepository.Generalization.Properties.isSubstitutable == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getGeneralization_IsSubstitutable().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getGeneralization_IsSubstitutable().getEAttributeType(), newValue);
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
