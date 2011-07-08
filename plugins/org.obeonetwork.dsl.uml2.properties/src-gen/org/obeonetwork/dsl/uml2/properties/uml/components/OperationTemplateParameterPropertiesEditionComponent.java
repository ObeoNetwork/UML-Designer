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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.uml2.uml.OperationTemplateParameter;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.OperationTemplateParameterPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class OperationTemplateParameterPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for signature EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings signatureSettings;
	
	/**
	 * Settings for parameteredElement EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings parameteredElementSettings;
	
	/**
	 * Settings for default EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings default_Settings;
	
	/**
	 * Default constructor
	 * 
	 */
	public OperationTemplateParameterPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject operationTemplateParameter, String editing_mode) {
		super(editingContext, operationTemplateParameter, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.OperationTemplateParameter.class;
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
			final OperationTemplateParameter operationTemplateParameter = (OperationTemplateParameter)elt;
			final OperationTemplateParameterPropertiesEditionPart basePart = (OperationTemplateParameterPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.OperationTemplateParameter.Properties.signature)) {
				// init part
				signatureSettings = new EObjectFlatComboSettings(operationTemplateParameter, UMLPackage.eINSTANCE.getTemplateParameter_Signature());
				basePart.initSignature(signatureSettings);
				// set the button mode
				basePart.setSignatureButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.OperationTemplateParameter.Properties.parameteredElement)) {
				// init part
				parameteredElementSettings = new EObjectFlatComboSettings(operationTemplateParameter, UMLPackage.eINSTANCE.getTemplateParameter_ParameteredElement());
				basePart.initParameteredElement(parameteredElementSettings);
				// set the button mode
				basePart.setParameteredElementButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.OperationTemplateParameter.Properties.default_)) {
				// init part
				default_Settings = new EObjectFlatComboSettings(operationTemplateParameter, UMLPackage.eINSTANCE.getTemplateParameter_Default());
				basePart.initDefault_(default_Settings);
				// set the button mode
				basePart.setDefault_ButtonMode(ButtonsModeEnum.BROWSE);
			}
			// init filters
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
			// Start of user code for additional businessfilters for signature
			// End of user code
			
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
			// Start of user code for additional businessfilters for parameteredElement
			// End of user code
			
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
			// Start of user code for additional businessfilters for default
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
		if (editorKey == UmlViewsRepository.OperationTemplateParameter.Properties.signature) {
			return UMLPackage.eINSTANCE.getTemplateParameter_Signature();
		}
		if (editorKey == UmlViewsRepository.OperationTemplateParameter.Properties.parameteredElement) {
			return UMLPackage.eINSTANCE.getTemplateParameter_ParameteredElement();
		}
		if (editorKey == UmlViewsRepository.OperationTemplateParameter.Properties.default_) {
			return UMLPackage.eINSTANCE.getTemplateParameter_Default();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		OperationTemplateParameter operationTemplateParameter = (OperationTemplateParameter)semanticObject;
		if (UmlViewsRepository.OperationTemplateParameter.Properties.signature == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.OperationTemplateParameter.Properties.parameteredElement == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.OperationTemplateParameter.Properties.default_ == event.getAffectedEditor()) {
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
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			OperationTemplateParameterPropertiesEditionPart basePart = (OperationTemplateParameterPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getTemplateParameter_Signature().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.OperationTemplateParameter.Properties.signature))
				basePart.setSignature((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTemplateParameter_ParameteredElement().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.OperationTemplateParameter.Properties.parameteredElement))
				basePart.setParameteredElement((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTemplateParameter_Default().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.OperationTemplateParameter.Properties.default_))
				basePart.setDefault_((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.OperationTemplateParameter.Properties.signature || key == UmlViewsRepository.OperationTemplateParameter.Properties.parameteredElement;
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
			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}

}
