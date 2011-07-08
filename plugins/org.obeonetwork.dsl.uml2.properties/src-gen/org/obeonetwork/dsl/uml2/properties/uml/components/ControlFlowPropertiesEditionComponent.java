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
import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.InterruptibleActivityRegion;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ControlFlowPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ControlFlowPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private	ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for source EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings sourceSettings;
	
	/**
	 * Settings for target EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings targetSettings;
	
	/**
	 * Settings for redefinedEdge ReferencesTable
	 */
	private	ReferencesTableSettings redefinedEdgeSettings;
	
	/**
	 * Settings for inPartition ReferencesTable
	 */
	private	ReferencesTableSettings inPartitionSettings;
	
	/**
	 * Settings for interrupts EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings interruptsSettings;
	
	/**
	 * Settings for inStructuredNode EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings inStructuredNodeSettings;
	
	/**
	 * Settings for activity EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings activitySettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public ControlFlowPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject controlFlow, String editing_mode) {
		super(editingContext, controlFlow, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.ControlFlow.class;
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
			final ControlFlow controlFlow = (ControlFlow)elt;
			final ControlFlowPropertiesEditionPart basePart = (ControlFlowPropertiesEditionPart)editingPart;
			// init values
			if (controlFlow.getName() != null && isAccessible(UmlViewsRepository.ControlFlow.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), controlFlow.getName()));
			
			if (isAccessible(UmlViewsRepository.ControlFlow.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), controlFlow.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.ControlFlow.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(controlFlow, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			basePart.setIsLeaf(controlFlow.isLeaf());
			
			if (isAccessible(UmlViewsRepository.ControlFlow.Properties.source)) {
				// init part
				sourceSettings = new EObjectFlatComboSettings(controlFlow, UMLPackage.eINSTANCE.getActivityEdge_Source());
				basePart.initSource(sourceSettings);
				// set the button mode
				basePart.setSourceButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ControlFlow.Properties.target)) {
				// init part
				targetSettings = new EObjectFlatComboSettings(controlFlow, UMLPackage.eINSTANCE.getActivityEdge_Target());
				basePart.initTarget(targetSettings);
				// set the button mode
				basePart.setTargetButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ControlFlow.Properties.redefinedEdge)) {
				redefinedEdgeSettings = new ReferencesTableSettings(controlFlow, UMLPackage.eINSTANCE.getActivityEdge_RedefinedEdge());
				basePart.initRedefinedEdge(redefinedEdgeSettings);
			}
			if (isAccessible(UmlViewsRepository.ControlFlow.Properties.inPartition)) {
				inPartitionSettings = new ReferencesTableSettings(controlFlow, UMLPackage.eINSTANCE.getActivityEdge_InPartition());
				basePart.initInPartition(inPartitionSettings);
			}
			if (isAccessible(UmlViewsRepository.ControlFlow.Properties.interrupts)) {
				// init part
				interruptsSettings = new EObjectFlatComboSettings(controlFlow, UMLPackage.eINSTANCE.getActivityEdge_Interrupts());
				basePart.initInterrupts(interruptsSettings);
				// set the button mode
				basePart.setInterruptsButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ControlFlow.Properties.inStructuredNode)) {
				// init part
				inStructuredNodeSettings = new EObjectFlatComboSettings(controlFlow, UMLPackage.eINSTANCE.getActivityEdge_InStructuredNode());
				basePart.initInStructuredNode(inStructuredNodeSettings);
				// set the button mode
				basePart.setInStructuredNodeButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ControlFlow.Properties.activity)) {
				// init part
				activitySettings = new EObjectFlatComboSettings(controlFlow, UMLPackage.eINSTANCE.getActivityEdge_Activity());
				basePart.initActivity(activitySettings);
				// set the button mode
				basePart.setActivityButtonMode(ButtonsModeEnum.BROWSE);
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
			
			
			basePart.addFilterToSource(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof ActivityNode);
				}
			
			});
			// Start of user code for additional businessfilters for source
			// End of user code
			
			basePart.addFilterToTarget(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof ActivityNode);
				}
			
			});
			// Start of user code for additional businessfilters for target
			// End of user code
			
			basePart.addFilterToRedefinedEdge(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInRedefinedEdgeTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToRedefinedEdge(new EObjectFilter(UMLPackage.eINSTANCE.getActivityEdge()));
			// Start of user code for additional businessfilters for redefinedEdge
			// End of user code
			
			basePart.addFilterToInPartition(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInInPartitionTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToInPartition(new EObjectFilter(UMLPackage.eINSTANCE.getActivityPartition()));
			// Start of user code for additional businessfilters for inPartition
			// End of user code
			
			basePart.addFilterToInterrupts(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof InterruptibleActivityRegion); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for interrupts
			// End of user code
			
			basePart.addFilterToInStructuredNode(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof StructuredActivityNode); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for inStructuredNode
			// End of user code
			
			basePart.addFilterToActivity(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof Activity); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for activity
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
		if (editorKey == UmlViewsRepository.ControlFlow.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.ControlFlow.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.ControlFlow.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.ControlFlow.Properties.isLeaf) {
			return UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf();
		}
		if (editorKey == UmlViewsRepository.ControlFlow.Properties.source) {
			return UMLPackage.eINSTANCE.getActivityEdge_Source();
		}
		if (editorKey == UmlViewsRepository.ControlFlow.Properties.target) {
			return UMLPackage.eINSTANCE.getActivityEdge_Target();
		}
		if (editorKey == UmlViewsRepository.ControlFlow.Properties.redefinedEdge) {
			return UMLPackage.eINSTANCE.getActivityEdge_RedefinedEdge();
		}
		if (editorKey == UmlViewsRepository.ControlFlow.Properties.inPartition) {
			return UMLPackage.eINSTANCE.getActivityEdge_InPartition();
		}
		if (editorKey == UmlViewsRepository.ControlFlow.Properties.interrupts) {
			return UMLPackage.eINSTANCE.getActivityEdge_Interrupts();
		}
		if (editorKey == UmlViewsRepository.ControlFlow.Properties.inStructuredNode) {
			return UMLPackage.eINSTANCE.getActivityEdge_InStructuredNode();
		}
		if (editorKey == UmlViewsRepository.ControlFlow.Properties.activity) {
			return UMLPackage.eINSTANCE.getActivityEdge_Activity();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		ControlFlow controlFlow = (ControlFlow)semanticObject;
		if (UmlViewsRepository.ControlFlow.Properties.name == event.getAffectedEditor()) {
			controlFlow.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.ControlFlow.Properties.visibility == event.getAffectedEditor()) {
			controlFlow.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.ControlFlow.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.ControlFlow.Properties.isLeaf == event.getAffectedEditor()) {
			controlFlow.setIsLeaf((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.ControlFlow.Properties.source == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				sourceSettings.setToReference((ActivityNode)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, sourceSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.ControlFlow.Properties.target == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				targetSettings.setToReference((ActivityNode)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, targetSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.ControlFlow.Properties.redefinedEdge == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof ActivityEdge) {
					redefinedEdgeSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					redefinedEdgeSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.ControlFlow.Properties.inPartition == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof ActivityPartition) {
					inPartitionSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					inPartitionSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.ControlFlow.Properties.interrupts == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				interruptsSettings.setToReference((InterruptibleActivityRegion)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				InterruptibleActivityRegion eObject = UMLFactory.eINSTANCE.createInterruptibleActivityRegion();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				interruptsSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.ControlFlow.Properties.inStructuredNode == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				inStructuredNodeSettings.setToReference((StructuredActivityNode)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				StructuredActivityNode eObject = UMLFactory.eINSTANCE.createStructuredActivityNode();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				inStructuredNodeSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.ControlFlow.Properties.activity == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				activitySettings.setToReference((Activity)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				Activity eObject = UMLFactory.eINSTANCE.createActivity();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				activitySettings.setToReference(eObject);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			ControlFlowPropertiesEditionPart basePart = (ControlFlowPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ControlFlow.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.ControlFlow.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ControlFlow.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ControlFlow.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getActivityEdge_Source().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ControlFlow.Properties.source))
				basePart.setSource((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActivityEdge_Target().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ControlFlow.Properties.target))
				basePart.setTarget((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActivityEdge_RedefinedEdge().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ControlFlow.Properties.redefinedEdge))
				basePart.updateRedefinedEdge();
			if (UMLPackage.eINSTANCE.getActivityEdge_InPartition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ControlFlow.Properties.inPartition))
				basePart.updateInPartition();
			if (UMLPackage.eINSTANCE.getActivityEdge_Interrupts().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ControlFlow.Properties.interrupts))
				basePart.setInterrupts((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActivityEdge_InStructuredNode().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ControlFlow.Properties.inStructuredNode))
				basePart.setInStructuredNode((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActivityEdge_Activity().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ControlFlow.Properties.activity))
				basePart.setActivity((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.ControlFlow.Properties.isLeaf || key == UmlViewsRepository.ControlFlow.Properties.source || key == UmlViewsRepository.ControlFlow.Properties.target;
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
				if (UmlViewsRepository.ControlFlow.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.ControlFlow.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.ControlFlow.Properties.isLeaf == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
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
