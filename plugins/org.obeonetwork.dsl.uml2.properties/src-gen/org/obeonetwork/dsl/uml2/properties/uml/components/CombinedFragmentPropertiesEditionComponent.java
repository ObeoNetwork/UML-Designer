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
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.InteractionOperatorKind;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CombinedFragmentPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class CombinedFragmentPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private	ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for covered ReferencesTable
	 */
	private	ReferencesTableSettings coveredSettings;
	
	/**
	 * Settings for enclosingInteraction EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings enclosingInteractionSettings;
	
	/**
	 * Settings for enclosingOperand EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings enclosingOperandSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public CombinedFragmentPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject combinedFragment, String editing_mode) {
		super(editingContext, combinedFragment, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.CombinedFragment.class;
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
			final CombinedFragment combinedFragment = (CombinedFragment)elt;
			final CombinedFragmentPropertiesEditionPart basePart = (CombinedFragmentPropertiesEditionPart)editingPart;
			// init values
			if (combinedFragment.getName() != null && isAccessible(UmlViewsRepository.CombinedFragment.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), combinedFragment.getName()));
			
			if (isAccessible(UmlViewsRepository.CombinedFragment.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), combinedFragment.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.CombinedFragment.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(combinedFragment, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			if (isAccessible(UmlViewsRepository.CombinedFragment.Properties.covered)) {
				coveredSettings = new ReferencesTableSettings(combinedFragment, UMLPackage.eINSTANCE.getInteractionFragment_Covered());
				basePart.initCovered(coveredSettings);
			}
			if (isAccessible(UmlViewsRepository.CombinedFragment.Properties.enclosingInteraction)) {
				// init part
				enclosingInteractionSettings = new EObjectFlatComboSettings(combinedFragment, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction());
				basePart.initEnclosingInteraction(enclosingInteractionSettings);
				// set the button mode
				basePart.setEnclosingInteractionButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.CombinedFragment.Properties.enclosingOperand)) {
				// init part
				enclosingOperandSettings = new EObjectFlatComboSettings(combinedFragment, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand());
				basePart.initEnclosingOperand(enclosingOperandSettings);
				// set the button mode
				basePart.setEnclosingOperandButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.CombinedFragment.Properties.interactionOperator)) {
				basePart.initInteractionOperator((EEnum) UMLPackage.eINSTANCE.getCombinedFragment_InteractionOperator().getEType(), combinedFragment.getInteractionOperator());
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
			
			basePart.addFilterToCovered(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInCoveredTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToCovered(new EObjectFilter(UMLPackage.eINSTANCE.getLifeline()));
			// Start of user code for additional businessfilters for covered
			
			// End of user code
			
			basePart.addFilterToEnclosingInteraction(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof Interaction); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for enclosingInteraction
			
			// End of user code
			
			basePart.addFilterToEnclosingOperand(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof InteractionOperand); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for enclosingOperand
			
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
		CombinedFragment combinedFragment = (CombinedFragment)semanticObject;
		if (UmlViewsRepository.CombinedFragment.Properties.name == event.getAffectedEditor()) {
			combinedFragment.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.CombinedFragment.Properties.visibility == event.getAffectedEditor()) {
			combinedFragment.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.CombinedFragment.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.CombinedFragment.Properties.covered == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Lifeline) {
					coveredSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					coveredSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.CombinedFragment.Properties.enclosingInteraction == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				enclosingInteractionSettings.setToReference((Interaction)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				Interaction eObject = UMLFactory.eINSTANCE.createInteraction();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				enclosingInteractionSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.CombinedFragment.Properties.enclosingOperand == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				enclosingOperandSettings.setToReference((InteractionOperand)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				InteractionOperand eObject = UMLFactory.eINSTANCE.createInteractionOperand();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				enclosingOperandSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.CombinedFragment.Properties.interactionOperator == event.getAffectedEditor()) {
			combinedFragment.setInteractionOperator((InteractionOperatorKind)event.getNewValue());
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			CombinedFragmentPropertiesEditionPart basePart = (CombinedFragmentPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.CombinedFragment.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.CombinedFragment.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.CombinedFragment.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getInteractionFragment_Covered().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.CombinedFragment.Properties.covered))
				basePart.updateCovered();
			if (UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.CombinedFragment.Properties.enclosingInteraction))
				basePart.setEnclosingInteraction((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.CombinedFragment.Properties.enclosingOperand))
				basePart.setEnclosingOperand((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getCombinedFragment_InteractionOperator().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.CombinedFragment.Properties.interactionOperator))
				basePart.setInteractionOperator((Enumerator)msg.getNewValue());
			
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.CombinedFragment.Properties.interactionOperator;
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
				if (UmlViewsRepository.CombinedFragment.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.CombinedFragment.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.CombinedFragment.Properties.interactionOperator == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getCombinedFragment_InteractionOperator().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getCombinedFragment_InteractionOperator().getEAttributeType(), newValue);
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
