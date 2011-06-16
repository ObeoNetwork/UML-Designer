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
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.InformationFlow;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.parts.InformationFlowPropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class InformationFlowPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private	ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for owningTemplateParameter EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings owningTemplateParameterSettings;
	
	/**
	 * Settings for templateParameter EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings templateParameterSettings;
	
	/**
	 * Settings for realization ReferencesTable
	 */
	private	ReferencesTableSettings realizationSettings;
	
	/**
	 * Settings for conveyed ReferencesTable
	 */
	private	ReferencesTableSettings conveyedSettings;
	
	/**
	 * Settings for informationSource ReferencesTable
	 */
	private	ReferencesTableSettings informationSourceSettings;
	
	/**
	 * Settings for informationTarget ReferencesTable
	 */
	private	ReferencesTableSettings informationTargetSettings;
	
	/**
	 * Settings for realizingActivityEdge ReferencesTable
	 */
	private	ReferencesTableSettings realizingActivityEdgeSettings;
	
	/**
	 * Settings for realizingConnector ReferencesTable
	 */
	private	ReferencesTableSettings realizingConnectorSettings;
	
	/**
	 * Settings for realizingMessage ReferencesTable
	 */
	private	ReferencesTableSettings realizingMessageSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public InformationFlowPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject informationFlow, String editing_mode) {
		super(editingContext, informationFlow, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.InformationFlow.class;
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
			final InformationFlow informationFlow = (InformationFlow)elt;
			final InformationFlowPropertiesEditionPart basePart = (InformationFlowPropertiesEditionPart)editingPart;
			// init values
			if (informationFlow.getName() != null && isAccessible(UmlViewsRepository.InformationFlow.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), informationFlow.getName()));
			
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), informationFlow.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(informationFlow, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter)) {
				// init part
				owningTemplateParameterSettings = new EObjectFlatComboSettings(informationFlow, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter());
				basePart.initOwningTemplateParameter(owningTemplateParameterSettings);
				// set the button mode
				basePart.setOwningTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.templateParameter)) {
				// init part
				templateParameterSettings = new EObjectFlatComboSettings(informationFlow, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter());
				basePart.initTemplateParameter(templateParameterSettings);
				// set the button mode
				basePart.setTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.realization)) {
				realizationSettings = new ReferencesTableSettings(informationFlow, UMLPackage.eINSTANCE.getInformationFlow_Realization());
				basePart.initRealization(realizationSettings);
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.conveyed)) {
				conveyedSettings = new ReferencesTableSettings(informationFlow, UMLPackage.eINSTANCE.getInformationFlow_Conveyed());
				basePart.initConveyed(conveyedSettings);
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.informationSource)) {
				informationSourceSettings = new ReferencesTableSettings(informationFlow, UMLPackage.eINSTANCE.getInformationFlow_InformationSource());
				basePart.initInformationSource(informationSourceSettings);
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.informationTarget)) {
				informationTargetSettings = new ReferencesTableSettings(informationFlow, UMLPackage.eINSTANCE.getInformationFlow_InformationTarget());
				basePart.initInformationTarget(informationTargetSettings);
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge)) {
				realizingActivityEdgeSettings = new ReferencesTableSettings(informationFlow, UMLPackage.eINSTANCE.getInformationFlow_RealizingActivityEdge());
				basePart.initRealizingActivityEdge(realizingActivityEdgeSettings);
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.realizingConnector)) {
				realizingConnectorSettings = new ReferencesTableSettings(informationFlow, UMLPackage.eINSTANCE.getInformationFlow_RealizingConnector());
				basePart.initRealizingConnector(realizingConnectorSettings);
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.realizingMessage)) {
				realizingMessageSettings = new ReferencesTableSettings(informationFlow, UMLPackage.eINSTANCE.getInformationFlow_RealizingMessage());
				basePart.initRealizingMessage(realizingMessageSettings);
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
			
			basePart.addFilterToOwningTemplateParameter(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof TemplateParameter); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for owningTemplateParameter
			
			// End of user code
			
			basePart.addFilterToTemplateParameter(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof TemplateParameter); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for templateParameter
			
			// End of user code
			
			basePart.addFilterToRealization(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInRealizationTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToRealization(new EObjectFilter(UMLPackage.eINSTANCE.getRelationship()));
			// Start of user code for additional businessfilters for realization
			
			// End of user code
			
			basePart.addFilterToConveyed(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInConveyedTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToConveyed(new EObjectFilter(UMLPackage.eINSTANCE.getClassifier()));
			// Start of user code for additional businessfilters for conveyed
			
			// End of user code
			
			basePart.addFilterToInformationSource(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInInformationSourceTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToInformationSource(new EObjectFilter(UMLPackage.eINSTANCE.getNamedElement()));
			// Start of user code for additional businessfilters for informationSource
			
			// End of user code
			
			basePart.addFilterToInformationTarget(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInInformationTargetTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToInformationTarget(new EObjectFilter(UMLPackage.eINSTANCE.getNamedElement()));
			// Start of user code for additional businessfilters for informationTarget
			
			// End of user code
			
			basePart.addFilterToRealizingActivityEdge(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInRealizingActivityEdgeTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToRealizingActivityEdge(new EObjectFilter(UMLPackage.eINSTANCE.getActivityEdge()));
			// Start of user code for additional businessfilters for realizingActivityEdge
			
			// End of user code
			
			basePart.addFilterToRealizingConnector(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInRealizingConnectorTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToRealizingConnector(new EObjectFilter(UMLPackage.eINSTANCE.getConnector()));
			// Start of user code for additional businessfilters for realizingConnector
			
			// End of user code
			
			basePart.addFilterToRealizingMessage(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInRealizingMessageTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToRealizingMessage(new EObjectFilter(UMLPackage.eINSTANCE.getMessage()));
			// Start of user code for additional businessfilters for realizingMessage
			
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
		InformationFlow informationFlow = (InformationFlow)semanticObject;
		if (UmlViewsRepository.InformationFlow.Properties.name == event.getAffectedEditor()) {
			informationFlow.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.InformationFlow.Properties.visibility == event.getAffectedEditor()) {
			informationFlow.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.InformationFlow.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				owningTemplateParameterSettings.setToReference((TemplateParameter)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				TemplateParameter eObject = UMLFactory.eINSTANCE.createTemplateParameter();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				owningTemplateParameterSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.templateParameter == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				templateParameterSettings.setToReference((TemplateParameter)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				TemplateParameter eObject = UMLFactory.eINSTANCE.createTemplateParameter();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				templateParameterSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.realization == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Relationship) {
					realizationSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					realizationSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.conveyed == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Classifier) {
					conveyedSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					conveyedSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.informationSource == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof NamedElement) {
					informationSourceSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					informationSourceSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.informationTarget == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof NamedElement) {
					informationTargetSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					informationTargetSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ActivityEdge) {
					realizingActivityEdgeSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					realizingActivityEdgeSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.realizingConnector == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Connector) {
					realizingConnectorSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					realizingConnectorSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.realizingMessage == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Message) {
					realizingMessageSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					realizingMessageSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			InformationFlowPropertiesEditionPart basePart = (InformationFlowPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.InformationFlow.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.InformationFlow.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InformationFlow.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter))
				basePart.setOwningTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.InformationFlow.Properties.templateParameter))
				basePart.setTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getInformationFlow_Realization().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InformationFlow.Properties.realization))
				basePart.updateRealization();
			if (UMLPackage.eINSTANCE.getInformationFlow_Conveyed().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InformationFlow.Properties.conveyed))
				basePart.updateConveyed();
			if (UMLPackage.eINSTANCE.getInformationFlow_InformationSource().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InformationFlow.Properties.informationSource))
				basePart.updateInformationSource();
			if (UMLPackage.eINSTANCE.getInformationFlow_InformationTarget().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InformationFlow.Properties.informationTarget))
				basePart.updateInformationTarget();
			if (UMLPackage.eINSTANCE.getInformationFlow_RealizingActivityEdge().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge))
				basePart.updateRealizingActivityEdge();
			if (UMLPackage.eINSTANCE.getInformationFlow_RealizingConnector().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InformationFlow.Properties.realizingConnector))
				basePart.updateRealizingConnector();
			if (UMLPackage.eINSTANCE.getInformationFlow_RealizingMessage().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.InformationFlow.Properties.realizingMessage))
				basePart.updateRealizingMessage();
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.InformationFlow.Properties.conveyed || key == UmlViewsRepository.InformationFlow.Properties.informationSource || key == UmlViewsRepository.InformationFlow.Properties.informationTarget;
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
				if (UmlViewsRepository.InformationFlow.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.InformationFlow.Properties.visibility == event.getAffectedEditor()) {
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
