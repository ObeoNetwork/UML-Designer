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
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class MessagePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private	ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for receiveEvent EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings receiveEventSettings;
	
	/**
	 * Settings for sendEvent EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings sendEventSettings;
	
	/**
	 * Settings for connector EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings connectorSettings;
	
	/**
	 * Settings for interaction EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings interactionSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public MessagePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject message, String editing_mode) {
		super(editingContext, message, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.Message.class;
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
			final Message message = (Message)elt;
			final MessagePropertiesEditionPart basePart = (MessagePropertiesEditionPart)editingPart;
			// init values
			if (message.getName() != null && isAccessible(UmlViewsRepository.Message.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), message.getName()));
			
			if (isAccessible(UmlViewsRepository.Message.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), message.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.Message.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(message, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			if (isAccessible(UmlViewsRepository.Message.Properties.messageSort)) {
				basePart.initMessageSort((EEnum) UMLPackage.eINSTANCE.getMessage_MessageSort().getEType(), message.getMessageSort());
			}
			if (isAccessible(UmlViewsRepository.Message.Properties.receiveEvent)) {
				// init part
				receiveEventSettings = new EObjectFlatComboSettings(message, UMLPackage.eINSTANCE.getMessage_ReceiveEvent());
				basePart.initReceiveEvent(receiveEventSettings);
				// set the button mode
				basePart.setReceiveEventButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Message.Properties.sendEvent)) {
				// init part
				sendEventSettings = new EObjectFlatComboSettings(message, UMLPackage.eINSTANCE.getMessage_SendEvent());
				basePart.initSendEvent(sendEventSettings);
				// set the button mode
				basePart.setSendEventButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Message.Properties.connector)) {
				// init part
				connectorSettings = new EObjectFlatComboSettings(message, UMLPackage.eINSTANCE.getMessage_Connector());
				basePart.initConnector(connectorSettings);
				// set the button mode
				basePart.setConnectorButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Message.Properties.interaction)) {
				// init part
				interactionSettings = new EObjectFlatComboSettings(message, UMLPackage.eINSTANCE.getMessage_Interaction());
				basePart.initInteraction(interactionSettings);
				// set the button mode
				basePart.setInteractionButtonMode(ButtonsModeEnum.BROWSE);
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
			
			
			basePart.addFilterToReceiveEvent(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof MessageEnd); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for receiveEvent
			// End of user code
			
			basePart.addFilterToSendEvent(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof MessageEnd); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for sendEvent
			// End of user code
			
			basePart.addFilterToConnector(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof Connector); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for connector
			// End of user code
			
			basePart.addFilterToInteraction(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof Interaction);
				}
			
			});
			// Start of user code for additional businessfilters for interaction
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
		if (editorKey == UmlViewsRepository.Message.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.Message.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.Message.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.Message.Properties.messageSort) {
			return UMLPackage.eINSTANCE.getMessage_MessageSort();
		}
		if (editorKey == UmlViewsRepository.Message.Properties.receiveEvent) {
			return UMLPackage.eINSTANCE.getMessage_ReceiveEvent();
		}
		if (editorKey == UmlViewsRepository.Message.Properties.sendEvent) {
			return UMLPackage.eINSTANCE.getMessage_SendEvent();
		}
		if (editorKey == UmlViewsRepository.Message.Properties.connector) {
			return UMLPackage.eINSTANCE.getMessage_Connector();
		}
		if (editorKey == UmlViewsRepository.Message.Properties.interaction) {
			return UMLPackage.eINSTANCE.getMessage_Interaction();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		Message message = (Message)semanticObject;
		if (UmlViewsRepository.Message.Properties.name == event.getAffectedEditor()) {
			message.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.Message.Properties.visibility == event.getAffectedEditor()) {
			message.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.Message.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Message.Properties.messageSort == event.getAffectedEditor()) {
			message.setMessageSort((MessageSort)event.getNewValue());
		}
		if (UmlViewsRepository.Message.Properties.receiveEvent == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				receiveEventSettings.setToReference((MessageEnd)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, receiveEventSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.Message.Properties.sendEvent == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				sendEventSettings.setToReference((MessageEnd)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, sendEventSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.Message.Properties.connector == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				connectorSettings.setToReference((Connector)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				Connector eObject = UMLFactory.eINSTANCE.createConnector();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				connectorSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.Message.Properties.interaction == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				interactionSettings.setToReference((Interaction)event.getNewValue());
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
				interactionSettings.setToReference(eObject);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			MessagePropertiesEditionPart basePart = (MessagePropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Message.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.Message.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Message.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getMessage_MessageSort().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.Message.Properties.messageSort))
				basePart.setMessageSort((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getMessage_ReceiveEvent().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Message.Properties.receiveEvent))
				basePart.setReceiveEvent((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getMessage_SendEvent().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Message.Properties.sendEvent))
				basePart.setSendEvent((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getMessage_Connector().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Message.Properties.connector))
				basePart.setConnector((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getMessage_Interaction().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Message.Properties.interaction))
				basePart.setInteraction((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.Message.Properties.messageSort || key == UmlViewsRepository.Message.Properties.interaction;
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
				if (UmlViewsRepository.Message.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Message.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Message.Properties.messageSort == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getMessage_MessageSort().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getMessage_MessageSort().getEAttributeType(), newValue);
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
