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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ComponentRealization;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.parts.ComponentRealizationPropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ComponentRealizationPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
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
	 * Settings for supplier ReferencesTable
	 */
	private	ReferencesTableSettings supplierSettings;
	
	/**
	 * Settings for client ReferencesTable
	 */
	private	ReferencesTableSettings clientSettings;
	
	/**
	 * Settings for abstraction EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings abstractionSettings;
	
	/**
	 * Settings for realizingClassifier ReferencesTable
	 */
	private	ReferencesTableSettings realizingClassifierSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public ComponentRealizationPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject componentRealization, String editing_mode) {
		super(editingContext, componentRealization, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.ComponentRealization.class;
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
			final ComponentRealization componentRealization = (ComponentRealization)elt;
			final ComponentRealizationPropertiesEditionPart basePart = (ComponentRealizationPropertiesEditionPart)editingPart;
			// init values
			if (componentRealization.getName() != null && isAccessible(UmlViewsRepository.ComponentRealization.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), componentRealization.getName()));
			
			if (isAccessible(UmlViewsRepository.ComponentRealization.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), componentRealization.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.ComponentRealization.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(componentRealization, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			if (isAccessible(UmlViewsRepository.ComponentRealization.Properties.owningTemplateParameter)) {
				// init part
				owningTemplateParameterSettings = new EObjectFlatComboSettings(componentRealization, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter());
				basePart.initOwningTemplateParameter(owningTemplateParameterSettings);
				// set the button mode
				basePart.setOwningTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ComponentRealization.Properties.templateParameter)) {
				// init part
				templateParameterSettings = new EObjectFlatComboSettings(componentRealization, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter());
				basePart.initTemplateParameter(templateParameterSettings);
				// set the button mode
				basePart.setTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ComponentRealization.Properties.supplier)) {
				supplierSettings = new ReferencesTableSettings(componentRealization, UMLPackage.eINSTANCE.getDependency_Supplier());
				basePart.initSupplier(supplierSettings);
			}
			if (isAccessible(UmlViewsRepository.ComponentRealization.Properties.client)) {
				clientSettings = new ReferencesTableSettings(componentRealization, UMLPackage.eINSTANCE.getDependency_Client());
				basePart.initClient(clientSettings);
			}
			if (isAccessible(UmlViewsRepository.ComponentRealization.Properties.abstraction)) {
				// init part
				abstractionSettings = new EObjectFlatComboSettings(componentRealization, UMLPackage.eINSTANCE.getComponentRealization_Abstraction());
				basePart.initAbstraction(abstractionSettings);
				// set the button mode
				basePart.setAbstractionButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ComponentRealization.Properties.realizingClassifier)) {
				realizingClassifierSettings = new ReferencesTableSettings(componentRealization, UMLPackage.eINSTANCE.getComponentRealization_RealizingClassifier());
				basePart.initRealizingClassifier(realizingClassifierSettings);
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
			
			basePart.addFilterToSupplier(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInSupplierTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToSupplier(new EObjectFilter(UMLPackage.eINSTANCE.getNamedElement()));
			// Start of user code for additional businessfilters for supplier
			
			// End of user code
			
			basePart.addFilterToClient(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInClientTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToClient(new EObjectFilter(UMLPackage.eINSTANCE.getNamedElement()));
			// Start of user code for additional businessfilters for client
			
			// End of user code
			
			basePart.addFilterToAbstraction(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof Component); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for abstraction
			
			// End of user code
			
			basePart.addFilterToRealizingClassifier(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInRealizingClassifierTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToRealizingClassifier(new EObjectFilter(UMLPackage.eINSTANCE.getClassifier()));
			// Start of user code for additional businessfilters for realizingClassifier
			
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
		ComponentRealization componentRealization = (ComponentRealization)semanticObject;
		if (UmlViewsRepository.ComponentRealization.Properties.name == event.getAffectedEditor()) {
			componentRealization.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.ComponentRealization.Properties.visibility == event.getAffectedEditor()) {
			componentRealization.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.ComponentRealization.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.ComponentRealization.Properties.owningTemplateParameter == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				owningTemplateParameterSettings.setToReference((TemplateParameter)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
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
		if (UmlViewsRepository.ComponentRealization.Properties.templateParameter == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				templateParameterSettings.setToReference((TemplateParameter)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
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
		if (UmlViewsRepository.ComponentRealization.Properties.supplier == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof NamedElement) {
					supplierSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					supplierSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.ComponentRealization.Properties.client == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof NamedElement) {
					clientSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.ComponentRealization.Properties.abstraction == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				abstractionSettings.setToReference((Component)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				Component eObject = UMLFactory.eINSTANCE.createComponent();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				abstractionSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.ComponentRealization.Properties.realizingClassifier == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Classifier) {
					realizingClassifierSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					realizingClassifierSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			ComponentRealizationPropertiesEditionPart basePart = (ComponentRealizationPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ComponentRealization.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.ComponentRealization.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ComponentRealization.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ComponentRealization.Properties.owningTemplateParameter))
				basePart.setOwningTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ComponentRealization.Properties.templateParameter))
				basePart.setTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getDependency_Supplier().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ComponentRealization.Properties.supplier))
				basePart.updateSupplier();
			if (UMLPackage.eINSTANCE.getDependency_Client().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ComponentRealization.Properties.client))
				basePart.updateClient();
			if (UMLPackage.eINSTANCE.getComponentRealization_Abstraction().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ComponentRealization.Properties.abstraction))
				basePart.setAbstraction((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getComponentRealization_RealizingClassifier().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ComponentRealization.Properties.realizingClassifier))
				basePart.updateRealizingClassifier();
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.ComponentRealization.Properties.supplier || key == UmlViewsRepository.ComponentRealization.Properties.client || key == UmlViewsRepository.ComponentRealization.Properties.realizingClassifier;
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
				if (UmlViewsRepository.ComponentRealization.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.ComponentRealization.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
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
