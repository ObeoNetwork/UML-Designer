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
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.LinkEndDestructionData;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.parts.LinkEndDestructionDataPropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class LinkEndDestructionDataPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for value EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings valueSettings;
	
	/**
	 * Settings for end EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings endSettings;
	
	/**
	 * Settings for destroyAt EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings destroyAtSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public LinkEndDestructionDataPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject linkEndDestructionData, String editing_mode) {
		super(editingContext, linkEndDestructionData, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.LinkEndDestructionData.class;
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
			final LinkEndDestructionData linkEndDestructionData = (LinkEndDestructionData)elt;
			final LinkEndDestructionDataPropertiesEditionPart basePart = (LinkEndDestructionDataPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.LinkEndDestructionData.Properties.value)) {
				// init part
				valueSettings = new EObjectFlatComboSettings(linkEndDestructionData, UMLPackage.eINSTANCE.getLinkEndData_Value());
				basePart.initValue(valueSettings);
				// set the button mode
				basePart.setValueButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.LinkEndDestructionData.Properties.end)) {
				// init part
				endSettings = new EObjectFlatComboSettings(linkEndDestructionData, UMLPackage.eINSTANCE.getLinkEndData_End());
				basePart.initEnd(endSettings);
				// set the button mode
				basePart.setEndButtonMode(ButtonsModeEnum.BROWSE);
			}
			basePart.setIsDestroyDuplicates(linkEndDestructionData.isDestroyDuplicates());
			
			if (isAccessible(UmlViewsRepository.LinkEndDestructionData.Properties.destroyAt)) {
				// init part
				destroyAtSettings = new EObjectFlatComboSettings(linkEndDestructionData, UMLPackage.eINSTANCE.getLinkEndDestructionData_DestroyAt());
				basePart.initDestroyAt(destroyAtSettings);
				// set the button mode
				basePart.setDestroyAtButtonMode(ButtonsModeEnum.BROWSE);
			}
			// init filters
			basePart.addFilterToValue(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof InputPin); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for value
			
			// End of user code
			
			basePart.addFilterToEnd(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof Property);
				}
			
			});
			// Start of user code for additional businessfilters for end
			
			// End of user code
			
			
			basePart.addFilterToDestroyAt(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof InputPin); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for destroyAt
			
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
		LinkEndDestructionData linkEndDestructionData = (LinkEndDestructionData)semanticObject;
		if (UmlViewsRepository.LinkEndDestructionData.Properties.value == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				valueSettings.setToReference((InputPin)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				InputPin eObject = UMLFactory.eINSTANCE.createInputPin();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				valueSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.LinkEndDestructionData.Properties.end == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				endSettings.setToReference((Property)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				Property eObject = UMLFactory.eINSTANCE.createProperty();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				endSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.LinkEndDestructionData.Properties.isDestroyDuplicates == event.getAffectedEditor()) {
			linkEndDestructionData.setIsDestroyDuplicates((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.LinkEndDestructionData.Properties.destroyAt == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				destroyAtSettings.setToReference((InputPin)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				InputPin eObject = UMLFactory.eINSTANCE.createInputPin();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				destroyAtSettings.setToReference(eObject);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			LinkEndDestructionDataPropertiesEditionPart basePart = (LinkEndDestructionDataPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getLinkEndData_Value().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LinkEndDestructionData.Properties.value))
				basePart.setValue((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getLinkEndData_End().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LinkEndDestructionData.Properties.end))
				basePart.setEnd((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getLinkEndDestructionData_IsDestroyDuplicates().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LinkEndDestructionData.Properties.isDestroyDuplicates))
				basePart.setIsDestroyDuplicates((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getLinkEndDestructionData_DestroyAt().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LinkEndDestructionData.Properties.destroyAt))
				basePart.setDestroyAt((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.LinkEndDestructionData.Properties.end || key == UmlViewsRepository.LinkEndDestructionData.Properties.isDestroyDuplicates;
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
				if (UmlViewsRepository.LinkEndDestructionData.Properties.isDestroyDuplicates == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getLinkEndDestructionData_IsDestroyDuplicates().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getLinkEndDestructionData_IsDestroyDuplicates().getEAttributeType(), newValue);
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
