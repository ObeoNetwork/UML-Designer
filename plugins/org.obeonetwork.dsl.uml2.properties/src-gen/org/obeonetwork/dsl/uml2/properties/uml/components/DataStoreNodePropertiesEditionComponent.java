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
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.InterruptibleActivityRegion;
import org.eclipse.uml2.uml.ObjectNodeOrderingKind;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.parts.DataStoreNodePropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class DataStoreNodePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
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
	 * Settings for type EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings typeSettings;
	
	/**
	 * Settings for inState ReferencesTable
	 */
	private	ReferencesTableSettings inStateSettings;
	
	/**
	 * Settings for selection EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings selectionSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public DataStoreNodePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject dataStoreNode, String editing_mode) {
		super(editingContext, dataStoreNode, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.DataStoreNode.class;
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
			final DataStoreNode dataStoreNode = (DataStoreNode)elt;
			final DataStoreNodePropertiesEditionPart basePart = (DataStoreNodePropertiesEditionPart)editingPart;
			// init values
			if (dataStoreNode.getName() != null && isAccessible(UmlViewsRepository.DataStoreNode.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), dataStoreNode.getName()));
			
			if (isAccessible(UmlViewsRepository.DataStoreNode.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), dataStoreNode.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.DataStoreNode.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(dataStoreNode, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			basePart.setIsLeaf(dataStoreNode.isLeaf());
			
			if (isAccessible(UmlViewsRepository.DataStoreNode.Properties.inStructuredNode)) {
				// init part
				inStructuredNodeSettings = new EObjectFlatComboSettings(dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_InStructuredNode());
				basePart.initInStructuredNode(inStructuredNodeSettings);
				// set the button mode
				basePart.setInStructuredNodeButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.DataStoreNode.Properties.activity)) {
				// init part
				activitySettings = new EObjectFlatComboSettings(dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_Activity());
				basePart.initActivity(activitySettings);
				// set the button mode
				basePart.setActivityButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.DataStoreNode.Properties.outgoing)) {
				outgoingSettings = new ReferencesTableSettings(dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_Outgoing());
				basePart.initOutgoing(outgoingSettings);
			}
			if (isAccessible(UmlViewsRepository.DataStoreNode.Properties.incoming)) {
				incomingSettings = new ReferencesTableSettings(dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_Incoming());
				basePart.initIncoming(incomingSettings);
			}
			if (isAccessible(UmlViewsRepository.DataStoreNode.Properties.inPartition)) {
				inPartitionSettings = new ReferencesTableSettings(dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_InPartition());
				basePart.initInPartition(inPartitionSettings);
			}
			if (isAccessible(UmlViewsRepository.DataStoreNode.Properties.inInterruptibleRegion)) {
				inInterruptibleRegionSettings = new ReferencesTableSettings(dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion());
				basePart.initInInterruptibleRegion(inInterruptibleRegionSettings);
			}
			if (isAccessible(UmlViewsRepository.DataStoreNode.Properties.redefinedNode)) {
				redefinedNodeSettings = new ReferencesTableSettings(dataStoreNode, UMLPackage.eINSTANCE.getActivityNode_RedefinedNode());
				basePart.initRedefinedNode(redefinedNodeSettings);
			}
			if (isAccessible(UmlViewsRepository.DataStoreNode.Properties.type)) {
				// init part
				typeSettings = new EObjectFlatComboSettings(dataStoreNode, UMLPackage.eINSTANCE.getTypedElement_Type());
				basePart.initType(typeSettings);
				// set the button mode
				basePart.setTypeButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.DataStoreNode.Properties.ordering)) {
				basePart.initOrdering((EEnum) UMLPackage.eINSTANCE.getObjectNode_Ordering().getEType(), dataStoreNode.getOrdering());
			}
			basePart.setIsControlType(dataStoreNode.isControlType());
			
			if (isAccessible(UmlViewsRepository.DataStoreNode.Properties.inState)) {
				inStateSettings = new ReferencesTableSettings(dataStoreNode, UMLPackage.eINSTANCE.getObjectNode_InState());
				basePart.initInState(inStateSettings);
			}
			if (isAccessible(UmlViewsRepository.DataStoreNode.Properties.selection)) {
				// init part
				selectionSettings = new EObjectFlatComboSettings(dataStoreNode, UMLPackage.eINSTANCE.getObjectNode_Selection());
				basePart.initSelection(selectionSettings);
				// set the button mode
				basePart.setSelectionButtonMode(ButtonsModeEnum.BROWSE);
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
			
			basePart.addFilterToType(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof Type); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for type
			
			// End of user code
			
			
			
			basePart.addFilterToInState(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInInStateTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToInState(new EObjectFilter(UMLPackage.eINSTANCE.getState()));
			// Start of user code for additional businessfilters for inState
			
			// End of user code
			
			basePart.addFilterToSelection(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof Behavior); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for selection
			
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
		DataStoreNode dataStoreNode = (DataStoreNode)semanticObject;
		if (UmlViewsRepository.DataStoreNode.Properties.name == event.getAffectedEditor()) {
			dataStoreNode.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.DataStoreNode.Properties.visibility == event.getAffectedEditor()) {
			dataStoreNode.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.DataStoreNode.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.DataStoreNode.Properties.isLeaf == event.getAffectedEditor()) {
			dataStoreNode.setIsLeaf((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.DataStoreNode.Properties.inStructuredNode == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.DataStoreNode.Properties.activity == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.DataStoreNode.Properties.outgoing == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ActivityEdge) {
					outgoingSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					outgoingSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.DataStoreNode.Properties.incoming == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ActivityEdge) {
					incomingSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					incomingSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.DataStoreNode.Properties.inPartition == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ActivityPartition) {
					inPartitionSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					inPartitionSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.DataStoreNode.Properties.inInterruptibleRegion == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof InterruptibleActivityRegion) {
					inInterruptibleRegionSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					inInterruptibleRegionSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.DataStoreNode.Properties.redefinedNode == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ActivityNode) {
					redefinedNodeSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					redefinedNodeSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.DataStoreNode.Properties.type == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				typeSettings.setToReference((Type)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, typeSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.DataStoreNode.Properties.ordering == event.getAffectedEditor()) {
			dataStoreNode.setOrdering((ObjectNodeOrderingKind)event.getNewValue());
		}
		if (UmlViewsRepository.DataStoreNode.Properties.isControlType == event.getAffectedEditor()) {
			dataStoreNode.setIsControlType((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.DataStoreNode.Properties.inState == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof State) {
					inStateSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					inStateSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.DataStoreNode.Properties.selection == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				selectionSettings.setToReference((Behavior)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, selectionSettings, editingContext.getAdapterFactory());
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
			DataStoreNodePropertiesEditionPart basePart = (DataStoreNodePropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.DataStoreNode.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.DataStoreNode.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.DataStoreNode.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.DataStoreNode.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getActivityNode_InStructuredNode().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.DataStoreNode.Properties.inStructuredNode))
				basePart.setInStructuredNode((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActivityNode_Activity().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.DataStoreNode.Properties.activity))
				basePart.setActivity((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getActivityNode_Outgoing().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.DataStoreNode.Properties.outgoing))
				basePart.updateOutgoing();
			if (UMLPackage.eINSTANCE.getActivityNode_Incoming().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.DataStoreNode.Properties.incoming))
				basePart.updateIncoming();
			if (UMLPackage.eINSTANCE.getActivityNode_InPartition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.DataStoreNode.Properties.inPartition))
				basePart.updateInPartition();
			if (UMLPackage.eINSTANCE.getActivityNode_InInterruptibleRegion().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.DataStoreNode.Properties.inInterruptibleRegion))
				basePart.updateInInterruptibleRegion();
			if (UMLPackage.eINSTANCE.getActivityNode_RedefinedNode().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.DataStoreNode.Properties.redefinedNode))
				basePart.updateRedefinedNode();
			if (UMLPackage.eINSTANCE.getTypedElement_Type().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.DataStoreNode.Properties.type))
				basePart.setType((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getObjectNode_Ordering().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.DataStoreNode.Properties.ordering))
				basePart.setOrdering((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getObjectNode_IsControlType().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.DataStoreNode.Properties.isControlType))
				basePart.setIsControlType((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getObjectNode_InState().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.DataStoreNode.Properties.inState))
				basePart.updateInState();
			if (UMLPackage.eINSTANCE.getObjectNode_Selection().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.DataStoreNode.Properties.selection))
				basePart.setSelection((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.DataStoreNode.Properties.isLeaf || key == UmlViewsRepository.DataStoreNode.Properties.ordering || key == UmlViewsRepository.DataStoreNode.Properties.isControlType;
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
				if (UmlViewsRepository.DataStoreNode.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.DataStoreNode.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.DataStoreNode.Properties.isLeaf == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.DataStoreNode.Properties.ordering == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getObjectNode_Ordering().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getObjectNode_Ordering().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.DataStoreNode.Properties.isControlType == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getObjectNode_IsControlType().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getObjectNode_IsControlType().getEAttributeType(), newValue);
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
