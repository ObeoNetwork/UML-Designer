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
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class PseudostatePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private	ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for container EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings containerSettings;
	
	/**
	 * Settings for stateMachine EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings stateMachineSettings;
	
	/**
	 * Settings for state EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings stateSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public PseudostatePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject pseudostate, String editing_mode) {
		super(editingContext, pseudostate, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.Pseudostate.class;
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
			final Pseudostate pseudostate = (Pseudostate)elt;
			final PseudostatePropertiesEditionPart basePart = (PseudostatePropertiesEditionPart)editingPart;
			// init values
			if (pseudostate.getName() != null && isAccessible(UmlViewsRepository.Pseudostate.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), pseudostate.getName()));
			
			if (isAccessible(UmlViewsRepository.Pseudostate.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), pseudostate.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.Pseudostate.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(pseudostate, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			if (isAccessible(UmlViewsRepository.Pseudostate.Properties.container)) {
				// init part
				containerSettings = new EObjectFlatComboSettings(pseudostate, UMLPackage.eINSTANCE.getVertex_Container());
				basePart.initContainer(containerSettings);
				// set the button mode
				basePart.setContainerButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Pseudostate.Properties.kind)) {
				basePart.initKind((EEnum) UMLPackage.eINSTANCE.getPseudostate_Kind().getEType(), pseudostate.getKind());
			}
			if (isAccessible(UmlViewsRepository.Pseudostate.Properties.stateMachine)) {
				// init part
				stateMachineSettings = new EObjectFlatComboSettings(pseudostate, UMLPackage.eINSTANCE.getPseudostate_StateMachine());
				basePart.initStateMachine(stateMachineSettings);
				// set the button mode
				basePart.setStateMachineButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Pseudostate.Properties.state)) {
				// init part
				stateSettings = new EObjectFlatComboSettings(pseudostate, UMLPackage.eINSTANCE.getPseudostate_State());
				basePart.initState(stateSettings);
				// set the button mode
				basePart.setStateButtonMode(ButtonsModeEnum.BROWSE);
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
			
			basePart.addFilterToContainer(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof Region); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for container
			
			// End of user code
			
			
			basePart.addFilterToStateMachine(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof StateMachine); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for stateMachine
			
			// End of user code
			
			basePart.addFilterToState(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof State); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for state
			
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
		Pseudostate pseudostate = (Pseudostate)semanticObject;
		if (UmlViewsRepository.Pseudostate.Properties.name == event.getAffectedEditor()) {
			pseudostate.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.Pseudostate.Properties.visibility == event.getAffectedEditor()) {
			pseudostate.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.Pseudostate.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Pseudostate.Properties.container == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				containerSettings.setToReference((Region)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				Region eObject = UMLFactory.eINSTANCE.createRegion();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				containerSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.Pseudostate.Properties.kind == event.getAffectedEditor()) {
			pseudostate.setKind((PseudostateKind)event.getNewValue());
		}
		if (UmlViewsRepository.Pseudostate.Properties.stateMachine == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				stateMachineSettings.setToReference((StateMachine)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				StateMachine eObject = UMLFactory.eINSTANCE.createStateMachine();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				stateMachineSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.Pseudostate.Properties.state == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				stateSettings.setToReference((State)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				State eObject = UMLFactory.eINSTANCE.createState();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				stateSettings.setToReference(eObject);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			PseudostatePropertiesEditionPart basePart = (PseudostatePropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Pseudostate.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.Pseudostate.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Pseudostate.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getVertex_Container().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Pseudostate.Properties.container))
				basePart.setContainer((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getPseudostate_Kind().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.Pseudostate.Properties.kind))
				basePart.setKind((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getPseudostate_StateMachine().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Pseudostate.Properties.stateMachine))
				basePart.setStateMachine((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getPseudostate_State().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Pseudostate.Properties.state))
				basePart.setState((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.Pseudostate.Properties.kind;
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
				if (UmlViewsRepository.Pseudostate.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Pseudostate.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Pseudostate.Properties.kind == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getPseudostate_Kind().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getPseudostate_Kind().getEAttributeType(), newValue);
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
