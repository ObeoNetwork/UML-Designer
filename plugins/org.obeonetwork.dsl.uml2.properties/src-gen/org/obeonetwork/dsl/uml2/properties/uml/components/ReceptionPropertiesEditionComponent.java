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
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallConcurrencyKind;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ReceptionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ReceptionPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private	ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for method ReferencesTable
	 */
	private	ReferencesTableSettings methodSettings;
	
	/**
	 * Settings for raisedException ReferencesTable
	 */
	private	ReferencesTableSettings raisedExceptionSettings;
	
	/**
	 * Settings for signal EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings signalSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public ReceptionPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject reception, String editing_mode) {
		super(editingContext, reception, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.Reception.class;
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
			final Reception reception = (Reception)elt;
			final ReceptionPropertiesEditionPart basePart = (ReceptionPropertiesEditionPart)editingPart;
			// init values
			if (reception.getName() != null && isAccessible(UmlViewsRepository.Reception.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), reception.getName()));
			
			if (isAccessible(UmlViewsRepository.Reception.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), reception.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.Reception.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(reception, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			basePart.setIsLeaf(reception.isLeaf());
			
			basePart.setIsStatic(reception.isStatic());
			
			basePart.setIsAbstract(reception.isAbstract());
			
			if (isAccessible(UmlViewsRepository.Reception.Properties.method)) {
				methodSettings = new ReferencesTableSettings(reception, UMLPackage.eINSTANCE.getBehavioralFeature_Method());
				basePart.initMethod(methodSettings);
			}
			if (isAccessible(UmlViewsRepository.Reception.Properties.concurrency)) {
				basePart.initConcurrency((EEnum) UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency().getEType(), reception.getConcurrency());
			}
			if (isAccessible(UmlViewsRepository.Reception.Properties.raisedException)) {
				raisedExceptionSettings = new ReferencesTableSettings(reception, UMLPackage.eINSTANCE.getBehavioralFeature_RaisedException());
				basePart.initRaisedException(raisedExceptionSettings);
			}
			if (isAccessible(UmlViewsRepository.Reception.Properties.signal)) {
				// init part
				signalSettings = new EObjectFlatComboSettings(reception, UMLPackage.eINSTANCE.getReception_Signal());
				basePart.initSignal(signalSettings);
				// set the button mode
				basePart.setSignalButtonMode(ButtonsModeEnum.BROWSE);
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
			
			
			
			
			basePart.addFilterToMethod(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInMethodTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToMethod(new EObjectFilter(UMLPackage.eINSTANCE.getBehavior()));
			// Start of user code for additional businessfilters for method
			
			// End of user code
			
			
			basePart.addFilterToRaisedException(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInRaisedExceptionTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToRaisedException(new EObjectFilter(UMLPackage.eINSTANCE.getType()));
			// Start of user code for additional businessfilters for raisedException
			
			// End of user code
			
			basePart.addFilterToSignal(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof Signal); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for signal
			
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
		Reception reception = (Reception)semanticObject;
		if (UmlViewsRepository.Reception.Properties.name == event.getAffectedEditor()) {
			reception.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.Reception.Properties.visibility == event.getAffectedEditor()) {
			reception.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.Reception.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Reception.Properties.isLeaf == event.getAffectedEditor()) {
			reception.setIsLeaf((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Reception.Properties.isStatic == event.getAffectedEditor()) {
			reception.setIsStatic((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Reception.Properties.isAbstract == event.getAffectedEditor()) {
			reception.setIsAbstract((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Reception.Properties.method == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Behavior) {
					methodSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					methodSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Reception.Properties.concurrency == event.getAffectedEditor()) {
			reception.setConcurrency((CallConcurrencyKind)event.getNewValue());
		}
		if (UmlViewsRepository.Reception.Properties.raisedException == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Type) {
					raisedExceptionSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					raisedExceptionSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Reception.Properties.signal == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				signalSettings.setToReference((Signal)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				Signal eObject = UMLFactory.eINSTANCE.createSignal();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				signalSettings.setToReference(eObject);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			ReceptionPropertiesEditionPart basePart = (ReceptionPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Reception.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.Reception.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Reception.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Reception.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getFeature_IsStatic().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Reception.Properties.isStatic))
				basePart.setIsStatic((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Reception.Properties.isAbstract))
				basePart.setIsAbstract((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavioralFeature_Method().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Reception.Properties.method))
				basePart.updateMethod();
			if (UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.Reception.Properties.concurrency))
				basePart.setConcurrency((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavioralFeature_RaisedException().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Reception.Properties.raisedException))
				basePart.updateRaisedException();
			if (UMLPackage.eINSTANCE.getReception_Signal().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Reception.Properties.signal))
				basePart.setSignal((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.Reception.Properties.isLeaf || key == UmlViewsRepository.Reception.Properties.isStatic || key == UmlViewsRepository.Reception.Properties.isAbstract || key == UmlViewsRepository.Reception.Properties.concurrency;
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
				if (UmlViewsRepository.Reception.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Reception.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Reception.Properties.isLeaf == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Reception.Properties.isStatic == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Reception.Properties.isAbstract == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Reception.Properties.concurrency == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency().getEAttributeType(), newValue);
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
