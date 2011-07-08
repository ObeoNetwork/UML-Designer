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
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.PartDecomposition;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.obeonetwork.dsl.uml2.properties.uml.parts.PartDecompositionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class PartDecompositionPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
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
	 * Settings for refersTo EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings refersToSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public PartDecompositionPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject partDecomposition, String editing_mode) {
		super(editingContext, partDecomposition, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.PartDecomposition.class;
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
			final PartDecomposition partDecomposition = (PartDecomposition)elt;
			final PartDecompositionPropertiesEditionPart basePart = (PartDecompositionPropertiesEditionPart)editingPart;
			// init values
			if (partDecomposition.getName() != null && isAccessible(UmlViewsRepository.PartDecomposition.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), partDecomposition.getName()));
			
			if (isAccessible(UmlViewsRepository.PartDecomposition.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), partDecomposition.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.PartDecomposition.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(partDecomposition, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			if (isAccessible(UmlViewsRepository.PartDecomposition.Properties.covered)) {
				coveredSettings = new ReferencesTableSettings(partDecomposition, UMLPackage.eINSTANCE.getInteractionFragment_Covered());
				basePart.initCovered(coveredSettings);
			}
			if (isAccessible(UmlViewsRepository.PartDecomposition.Properties.enclosingInteraction)) {
				// init part
				enclosingInteractionSettings = new EObjectFlatComboSettings(partDecomposition, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction());
				basePart.initEnclosingInteraction(enclosingInteractionSettings);
				// set the button mode
				basePart.setEnclosingInteractionButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.PartDecomposition.Properties.enclosingOperand)) {
				// init part
				enclosingOperandSettings = new EObjectFlatComboSettings(partDecomposition, UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand());
				basePart.initEnclosingOperand(enclosingOperandSettings);
				// set the button mode
				basePart.setEnclosingOperandButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.PartDecomposition.Properties.refersTo)) {
				// init part
				refersToSettings = new EObjectFlatComboSettings(partDecomposition, UMLPackage.eINSTANCE.getInteractionUse_RefersTo());
				basePart.initRefersTo(refersToSettings);
				// set the button mode
				basePart.setRefersToButtonMode(ButtonsModeEnum.BROWSE);
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
			
			basePart.addFilterToRefersTo(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof Interaction);
				}
			
			});
			// Start of user code for additional businessfilters for refersTo
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
		if (editorKey == UmlViewsRepository.PartDecomposition.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.PartDecomposition.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.PartDecomposition.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.PartDecomposition.Properties.covered) {
			return UMLPackage.eINSTANCE.getInteractionFragment_Covered();
		}
		if (editorKey == UmlViewsRepository.PartDecomposition.Properties.enclosingInteraction) {
			return UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction();
		}
		if (editorKey == UmlViewsRepository.PartDecomposition.Properties.enclosingOperand) {
			return UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand();
		}
		if (editorKey == UmlViewsRepository.PartDecomposition.Properties.refersTo) {
			return UMLPackage.eINSTANCE.getInteractionUse_RefersTo();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		PartDecomposition partDecomposition = (PartDecomposition)semanticObject;
		if (UmlViewsRepository.PartDecomposition.Properties.name == event.getAffectedEditor()) {
			partDecomposition.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.PartDecomposition.Properties.visibility == event.getAffectedEditor()) {
			partDecomposition.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.PartDecomposition.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.PartDecomposition.Properties.covered == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Lifeline) {
					coveredSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					coveredSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.PartDecomposition.Properties.enclosingInteraction == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				enclosingInteractionSettings.setToReference((Interaction)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
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
		if (UmlViewsRepository.PartDecomposition.Properties.enclosingOperand == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				enclosingOperandSettings.setToReference((InteractionOperand)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
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
		if (UmlViewsRepository.PartDecomposition.Properties.refersTo == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				refersToSettings.setToReference((Interaction)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				Interaction eObject = UMLFactory.eINSTANCE.createInteraction();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				refersToSettings.setToReference(eObject);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			PartDecompositionPropertiesEditionPart basePart = (PartDecompositionPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.PartDecomposition.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.PartDecomposition.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.PartDecomposition.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getInteractionFragment_Covered().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.PartDecomposition.Properties.covered))
				basePart.updateCovered();
			if (UMLPackage.eINSTANCE.getInteractionFragment_EnclosingInteraction().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.PartDecomposition.Properties.enclosingInteraction))
				basePart.setEnclosingInteraction((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getInteractionFragment_EnclosingOperand().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.PartDecomposition.Properties.enclosingOperand))
				basePart.setEnclosingOperand((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getInteractionUse_RefersTo().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.PartDecomposition.Properties.refersTo))
				basePart.setRefersTo((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.PartDecomposition.Properties.refersTo;
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
				if (UmlViewsRepository.PartDecomposition.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.PartDecomposition.Properties.visibility == event.getAffectedEditor()) {
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
