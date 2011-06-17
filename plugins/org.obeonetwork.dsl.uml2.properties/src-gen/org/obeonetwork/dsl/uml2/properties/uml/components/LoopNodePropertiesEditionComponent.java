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
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.ExecutableNode;
import org.eclipse.uml2.uml.InterruptibleActivityRegion;
import org.eclipse.uml2.uml.LoopNode;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.parts.LoopNodePropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class LoopNodePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private	ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for inStructuredNode EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings inStructuredNodeSettings;
	
	/**
	 * Settings for activity EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings activitySettings;
	
	/**
	 * Settings for outgoing ReferencesTable
	 */
	private	ReferencesTableSettings outgoingSettings;
	
	/**
	 * Settings for incoming ReferencesTable
	 */
	private	ReferencesTableSettings incomingSettings;
	
	/**
	 * Settings for inPartition ReferencesTable
	 */
	private	ReferencesTableSettings inPartitionSettings;
	
	/**
	 * Settings for inInterruptibleRegion ReferencesTable
	 */
	private	ReferencesTableSettings inInterruptibleRegionSettings;
	
	/**
	 * Settings for redefinedNode ReferencesTable
	 */
	private	ReferencesTableSettings redefinedNodeSettings;
	
	/**
	 * Settings for inActivity EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings inActivitySettings;
	
	/**
	 * Settings for bodyPart ReferencesTable
	 */
	private	ReferencesTableSettings bodyPartSettings;
	
	/**
	 * Settings for setupPart ReferencesTable
	 */
	private	ReferencesTableSettings setupPartSettings;
	
	/**
	 * Settings for decider EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings deciderSettings;
	
	/**
	 * Settings for test ReferencesTable
	 */
	private	ReferencesTableSettings testSettings;
	
	/**
	 * Settings for loopVariable ReferencesTable
	 */
	private	ReferencesTableSettings loopVariableSettings;
	
	/**
	 * Settings for bodyOutput ReferencesTable
	 */
	private	ReferencesTableSettings bodyOutputSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public LoopNodePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject loopNode, String editing_mode) {
		super(editingContext, loopNode, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.LoopNode.class;
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
			final LoopNode loopNode = (LoopNode)elt;
			final LoopNodePropertiesEditionPart basePart = (LoopNodePropertiesEditionPart)editingPart;
			// init values
			if (loopNode.getName() != null && isAccessible(UmlViewsRepository.LoopNode.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), loopNode.getName()));
			
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), loopNode.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(loopNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			basePart.setIsLeaf(loopNode.isLeaf());
			
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.inStructuredNode)) {
				// init part
				inStructuredNodeSettings = new EObjectFlatComboSettings(loopNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode());
				basePart.initInStructuredNode(inStructuredNodeSettings);
				// set the button mode
				basePart.setInStructuredNodeButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.activity)) {
				// init part
				activitySettings = new EObjectFlatComboSettings(loopNode, UMLPackage.eINSTANCE.getActivityNode_Activity());
				basePart.initActivity(activitySettings);
				// set the button mode
				basePart.setActivityButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.outgoing)) {
				outgoingSettings = new ReferencesTableSettings(loopNode, UMLPackage.eINSTANCE.getActivityNode_Outgoing());
				basePart.initOutgoing(outgoingSettings);
			}
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.incoming)) {
				incomingSettings = new ReferencesTableSettings(loopNode, UMLPackage.eINSTANCE.getActivityNode_Incoming());
				basePart.initIncoming(incomingSettings);
			}
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.inPartition)) {
				inPartitionSettings = new ReferencesTableSettings(loopNode, UMLPackage.eINSTANCE.getActivityNode_InPartition());
				basePart.initInPartition(inPartitionSettings);
			}
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.inInterruptibleRegion)) {
				inInterruptibleRegionSettings = new ReferencesTableSettings(loopNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
				basePart.initInInterruptibleRegion(inInterruptibleRegionSettings);
			}
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.redefinedNode)) {
				redefinedNodeSettings = new ReferencesTableSettings(loopNode, UMLPackage.eINSTANCE.getActivityNode_RedefinedNode());
				basePart.initRedefinedNode(redefinedNodeSettings);
			}
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.inActivity)) {
				// init part
				inActivitySettings = new EObjectFlatComboSettings(loopNode, UMLPackage.eINSTANCE.getActivityGroup_InActivity());
				basePart.initInActivity(inActivitySettings);
				// set the button mode
				basePart.setInActivityButtonMode(ButtonsModeEnum.BROWSE);
			}
			basePart.setMustIsolate(loopNode.isMustIsolate());
			
			basePart.setIsTestedFirst(loopNode.isTestedFirst());
			
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.bodyPart)) {
				bodyPartSettings = new ReferencesTableSettings(loopNode, UMLPackage.eINSTANCE.getLoopNode_BodyPart());
				basePart.initBodyPart(bodyPartSettings);
			}
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.setupPart)) {
				setupPartSettings = new ReferencesTableSettings(loopNode, UMLPackage.eINSTANCE.getLoopNode_SetupPart());
				basePart.initSetupPart(setupPartSettings);
			}
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.decider)) {
				// init part
				deciderSettings = new EObjectFlatComboSettings(loopNode, UMLPackage.eINSTANCE.getLoopNode_Decider());
				basePart.initDecider(deciderSettings);
				// set the button mode
				basePart.setDeciderButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.test)) {
				testSettings = new ReferencesTableSettings(loopNode, UMLPackage.eINSTANCE.getLoopNode_Test());
				basePart.initTest(testSettings);
			}
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.loopVariable)) {
				loopVariableSettings = new ReferencesTableSettings(loopNode, UMLPackage.eINSTANCE.getLoopNode_LoopVariable());
				basePart.initLoopVariable(loopVariableSettings);
			}
			if (isAccessible(UmlViewsRepository.LoopNode.Properties.bodyOutput)) {
				bodyOutputSettings = new ReferencesTableSettings(loopNode, UMLPackage.eINSTANCE.getLoopNode_BodyOutput());
				basePart.initBodyOutput(bodyOutputSettings);
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
			
			basePart.addFilterToOutgoing(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInOutgoingTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToOutgoing(new EObjectFilter(UMLPackage.eINSTANCE.getActivityEdge()));
			// Start of user code for additional businessfilters for outgoing
			
			// End of user code
			
			basePart.addFilterToIncoming(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInIncomingTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToIncoming(new EObjectFilter(UMLPackage.eINSTANCE.getActivityEdge()));
			// Start of user code for additional businessfilters for incoming
			
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
			
			basePart.addFilterToInInterruptibleRegion(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInInInterruptibleRegionTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToInInterruptibleRegion(new EObjectFilter(UMLPackage.eINSTANCE.getInterruptibleActivityRegion()));
			// Start of user code for additional businessfilters for inInterruptibleRegion
			
			// End of user code
			
			basePart.addFilterToRedefinedNode(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInRedefinedNodeTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToRedefinedNode(new EObjectFilter(UMLPackage.eINSTANCE.getActivityNode()));
			// Start of user code for additional businessfilters for redefinedNode
			
			// End of user code
			
			basePart.addFilterToInActivity(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof Activity); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for inActivity
			
			// End of user code
			
			
			
			basePart.addFilterToBodyPart(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInBodyPartTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToBodyPart(new EObjectFilter(UMLPackage.eINSTANCE.getExecutableNode()));
			// Start of user code for additional businessfilters for bodyPart
			
			// End of user code
			
			basePart.addFilterToSetupPart(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInSetupPartTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToSetupPart(new EObjectFilter(UMLPackage.eINSTANCE.getExecutableNode()));
			// Start of user code for additional businessfilters for setupPart
			
			// End of user code
			
			basePart.addFilterToDecider(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof OutputPin);
				}
			
			});
			// Start of user code for additional businessfilters for decider
			
			// End of user code
			
			basePart.addFilterToTest(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInTestTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToTest(new EObjectFilter(UMLPackage.eINSTANCE.getExecutableNode()));
			// Start of user code for additional businessfilters for test
			
			// End of user code
			
			basePart.addFilterToLoopVariable(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInLoopVariableTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToLoopVariable(new EObjectFilter(UMLPackage.eINSTANCE.getOutputPin()));
			// Start of user code for additional businessfilters for loopVariable
			
			// End of user code
			
			basePart.addFilterToBodyOutput(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInBodyOutputTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToBodyOutput(new EObjectFilter(UMLPackage.eINSTANCE.getOutputPin()));
			// Start of user code for additional businessfilters for bodyOutput
			
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
		LoopNode loopNode = (LoopNode)semanticObject;
		if (UmlViewsRepository.LoopNode.Properties.name == event.getAffectedEditor()) {
			loopNode.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.LoopNode.Properties.visibility == event.getAffectedEditor()) {
			loopNode.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.LoopNode.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.LoopNode.Properties.isLeaf == event.getAffectedEditor()) {
			loopNode.setIsLeaf((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.LoopNode.Properties.inStructuredNode == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				inStructuredNodeSettings.setToReference((StructuredActivityNode)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
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
		if (UmlViewsRepository.LoopNode.Properties.activity == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				activitySettings.setToReference((Activity)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
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
		if (UmlViewsRepository.LoopNode.Properties.outgoing == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ActivityEdge) {
					outgoingSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					outgoingSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.LoopNode.Properties.incoming == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ActivityEdge) {
					incomingSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					incomingSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.LoopNode.Properties.inPartition == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ActivityPartition) {
					inPartitionSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					inPartitionSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.LoopNode.Properties.inInterruptibleRegion == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof InterruptibleActivityRegion) {
					inInterruptibleRegionSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					inInterruptibleRegionSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.LoopNode.Properties.redefinedNode == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ActivityNode) {
					redefinedNodeSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					redefinedNodeSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.LoopNode.Properties.inActivity == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				inActivitySettings.setToReference((Activity)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				Activity eObject = UMLFactory.eINSTANCE.createActivity();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				inActivitySettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.LoopNode.Properties.mustIsolate == event.getAffectedEditor()) {
			loopNode.setMustIsolate((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.LoopNode.Properties.isTestedFirst == event.getAffectedEditor()) {
			loopNode.setIsTestedFirst((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.LoopNode.Properties.bodyPart == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ExecutableNode) {
					bodyPartSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					bodyPartSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.LoopNode.Properties.setupPart == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ExecutableNode) {
					setupPartSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					setupPartSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.LoopNode.Properties.decider == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				deciderSettings.setToReference((OutputPin)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				OutputPin eObject = UMLFactory.eINSTANCE.createOutputPin();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				deciderSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.LoopNode.Properties.test == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ExecutableNode) {
					testSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					testSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.LoopNode.Properties.loopVariable == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof OutputPin) {
					loopVariableSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					loopVariableSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.LoopNode.Properties.bodyOutput == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof OutputPin) {
					bodyOutputSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					bodyOutputSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			LoopNodePropertiesEditionPart basePart = (LoopNodePropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LoopNode.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.LoopNode.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.LoopNode.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LoopNode.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getActivityNode_InStructuredNode().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LoopNode.Properties.inStructuredNode))
				basePart.setInStructuredNode((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActivityNode_Activity().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LoopNode.Properties.activity))
				basePart.setActivity((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActivityNode_Outgoing().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.LoopNode.Properties.outgoing))
				basePart.updateOutgoing();
			if (UMLPackage.eINSTANCE.getActivityNode_Incoming().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.LoopNode.Properties.incoming))
				basePart.updateIncoming();
			if (UMLPackage.eINSTANCE.getActivityNode_InPartition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.LoopNode.Properties.inPartition))
				basePart.updateInPartition();
			if (UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.LoopNode.Properties.inInterruptibleRegion))
				basePart.updateInInterruptibleRegion();
			if (UMLPackage.eINSTANCE.getActivityNode_RedefinedNode().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.LoopNode.Properties.redefinedNode))
				basePart.updateRedefinedNode();
			if (UMLPackage.eINSTANCE.getActivityGroup_InActivity().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LoopNode.Properties.inActivity))
				basePart.setInActivity((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getStructuredActivityNode_MustIsolate().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LoopNode.Properties.mustIsolate))
				basePart.setMustIsolate((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getLoopNode_IsTestedFirst().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LoopNode.Properties.isTestedFirst))
				basePart.setIsTestedFirst((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getLoopNode_BodyPart().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.LoopNode.Properties.bodyPart))
				basePart.updateBodyPart();
			if (UMLPackage.eINSTANCE.getLoopNode_SetupPart().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.LoopNode.Properties.setupPart))
				basePart.updateSetupPart();
			if (UMLPackage.eINSTANCE.getLoopNode_Decider().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.LoopNode.Properties.decider))
				basePart.setDecider((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getLoopNode_Test().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.LoopNode.Properties.test))
				basePart.updateTest();
			if (UMLPackage.eINSTANCE.getLoopNode_LoopVariable().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.LoopNode.Properties.loopVariable))
				basePart.updateLoopVariable();
			if (UMLPackage.eINSTANCE.getLoopNode_BodyOutput().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.LoopNode.Properties.bodyOutput))
				basePart.updateBodyOutput();
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.LoopNode.Properties.isLeaf || key == UmlViewsRepository.LoopNode.Properties.mustIsolate || key == UmlViewsRepository.LoopNode.Properties.isTestedFirst || key == UmlViewsRepository.LoopNode.Properties.decider;
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
				if (UmlViewsRepository.LoopNode.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.LoopNode.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.LoopNode.Properties.isLeaf == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.LoopNode.Properties.mustIsolate == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getStructuredActivityNode_MustIsolate().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getStructuredActivityNode_MustIsolate().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.LoopNode.Properties.isTestedFirst == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getLoopNode_IsTestedFirst().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getLoopNode_IsTestedFirst().getEAttributeType(), newValue);
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
