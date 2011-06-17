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
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.ExecutableNode;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.parts.ExceptionHandlerPropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ExceptionHandlerPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for handlerBody EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings handlerBodySettings;
	
	/**
	 * Settings for exceptionInput EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings exceptionInputSettings;
	
	/**
	 * Settings for exceptionType ReferencesTable
	 */
	private	ReferencesTableSettings exceptionTypeSettings;
	
	/**
	 * Settings for protectedNode EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings protectedNodeSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public ExceptionHandlerPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject exceptionHandler, String editing_mode) {
		super(editingContext, exceptionHandler, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.ExceptionHandler.class;
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
			final ExceptionHandler exceptionHandler = (ExceptionHandler)elt;
			final ExceptionHandlerPropertiesEditionPart basePart = (ExceptionHandlerPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.ExceptionHandler.Properties.handlerBody)) {
				// init part
				handlerBodySettings = new EObjectFlatComboSettings(exceptionHandler, UMLPackage.eINSTANCE.getExceptionHandler_HandlerBody());
				basePart.initHandlerBody(handlerBodySettings);
				// set the button mode
				basePart.setHandlerBodyButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ExceptionHandler.Properties.exceptionInput)) {
				// init part
				exceptionInputSettings = new EObjectFlatComboSettings(exceptionHandler, UMLPackage.eINSTANCE.getExceptionHandler_ExceptionInput());
				basePart.initExceptionInput(exceptionInputSettings);
				// set the button mode
				basePart.setExceptionInputButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ExceptionHandler.Properties.exceptionType)) {
				exceptionTypeSettings = new ReferencesTableSettings(exceptionHandler, UMLPackage.eINSTANCE.getExceptionHandler_ExceptionType());
				basePart.initExceptionType(exceptionTypeSettings);
			}
			if (isAccessible(UmlViewsRepository.ExceptionHandler.Properties.protectedNode)) {
				// init part
				protectedNodeSettings = new EObjectFlatComboSettings(exceptionHandler, UMLPackage.eINSTANCE.getExceptionHandler_ProtectedNode());
				basePart.initProtectedNode(protectedNodeSettings);
				// set the button mode
				basePart.setProtectedNodeButtonMode(ButtonsModeEnum.BROWSE);
			}
			// init filters
			basePart.addFilterToHandlerBody(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof ExecutableNode);
				}
			
			});
			// Start of user code for additional businessfilters for handlerBody
			
			// End of user code
			
			basePart.addFilterToExceptionInput(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof ObjectNode);
				}
			
			});
			// Start of user code for additional businessfilters for exceptionInput
			
			// End of user code
			
			basePart.addFilterToExceptionType(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInExceptionTypeTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToExceptionType(new EObjectFilter(UMLPackage.eINSTANCE.getClassifier()));
			// Start of user code for additional businessfilters for exceptionType
			
			// End of user code
			
			basePart.addFilterToProtectedNode(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof ExecutableNode);
				}
			
			});
			// Start of user code for additional businessfilters for protectedNode
			
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
		ExceptionHandler exceptionHandler = (ExceptionHandler)semanticObject;
		if (UmlViewsRepository.ExceptionHandler.Properties.handlerBody == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				handlerBodySettings.setToReference((ExecutableNode)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, handlerBodySettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.ExceptionHandler.Properties.exceptionInput == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				exceptionInputSettings.setToReference((ObjectNode)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, exceptionInputSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.ExceptionHandler.Properties.exceptionType == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Classifier) {
					exceptionTypeSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					exceptionTypeSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.ExceptionHandler.Properties.protectedNode == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				protectedNodeSettings.setToReference((ExecutableNode)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, protectedNodeSettings, editingContext.getAdapterFactory());
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
			ExceptionHandlerPropertiesEditionPart basePart = (ExceptionHandlerPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getExceptionHandler_HandlerBody().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExceptionHandler.Properties.handlerBody))
				basePart.setHandlerBody((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getExceptionHandler_ExceptionInput().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExceptionHandler.Properties.exceptionInput))
				basePart.setExceptionInput((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getExceptionHandler_ExceptionType().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExceptionHandler.Properties.exceptionType))
				basePart.updateExceptionType();
			if (UMLPackage.eINSTANCE.getExceptionHandler_ProtectedNode().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExceptionHandler.Properties.protectedNode))
				basePart.setProtectedNode((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.ExceptionHandler.Properties.handlerBody || key == UmlViewsRepository.ExceptionHandler.Properties.exceptionInput || key == UmlViewsRepository.ExceptionHandler.Properties.exceptionType || key == UmlViewsRepository.ExceptionHandler.Properties.protectedNode;
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
