/*******************************************************************************
 * Copyright (c) 2009, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.components;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.eef.runtime.api.notify.EStructuralFeatureNotificationFilter;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;

import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;

import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;

import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;

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

import org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class InformationFlowPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for owningTemplateParameter EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings owningTemplateParameterSettings;
	
	/**
	 * Settings for templateParameter EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings templateParameterSettings;
	
	/**
	 * Settings for realization ReferencesTable
	 */
	private ReferencesTableSettings realizationSettings;
	
	/**
	 * Settings for conveyed ReferencesTable
	 */
	private ReferencesTableSettings conveyedSettings;
	
	/**
	 * Settings for informationSource ReferencesTable
	 */
	private ReferencesTableSettings informationSourceSettings;
	
	/**
	 * Settings for informationTarget ReferencesTable
	 */
	private ReferencesTableSettings informationTargetSettings;
	
	/**
	 * Settings for realizingActivityEdge ReferencesTable
	 */
	private ReferencesTableSettings realizingActivityEdgeSettings;
	
	/**
	 * Settings for realizingConnector ReferencesTable
	 */
	private ReferencesTableSettings realizingConnectorSettings;
	
	/**
	 * Settings for realizingMessage ReferencesTable
	 */
	private ReferencesTableSettings realizingMessageSettings;
	
	
	/**
	 * Default constructor
	 * @generated
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
	 * @generated
	 */
	public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);
			
			final InformationFlow informationFlow = (InformationFlow)elt;
			final InformationFlowPropertiesEditionPart basePart = (InformationFlowPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.Literals.STRING, informationFlow.getName()));
			
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.visibility)) {
				basePart.initVisibility(EEFUtils.choiceOfValues(informationFlow, UMLPackage.eINSTANCE.getNamedElement_Visibility()), informationFlow.getVisibility());
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
			
			
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.clientDependency)) {
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
				basePart.addFilterToClientDependency(new EObjectFilter(UMLPackage.Literals.DEPENDENCY));
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter)) {
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
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.templateParameter)) {
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
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.realization)) {
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
				basePart.addFilterToRealization(new EObjectFilter(UMLPackage.Literals.RELATIONSHIP));
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.conveyed)) {
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
				basePart.addFilterToConveyed(new EObjectFilter(UMLPackage.Literals.CLASSIFIER));
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.informationSource)) {
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
				basePart.addFilterToInformationSource(new EObjectFilter(UMLPackage.Literals.NAMED_ELEMENT));
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.informationTarget)) {
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
				basePart.addFilterToInformationTarget(new EObjectFilter(UMLPackage.Literals.NAMED_ELEMENT));
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge)) {
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
				basePart.addFilterToRealizingActivityEdge(new EObjectFilter(UMLPackage.Literals.ACTIVITY_EDGE));
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.realizingConnector)) {
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
				basePart.addFilterToRealizingConnector(new EObjectFilter(UMLPackage.Literals.CONNECTOR));
			}
			if (isAccessible(UmlViewsRepository.InformationFlow.Properties.realizingMessage)) {
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
				basePart.addFilterToRealizingMessage(new EObjectFilter(UMLPackage.Literals.MESSAGE));
			}
			// init values for referenced views
			
			// init filters for referenced views
			
		}
		setInitializing(false);
	}















	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
	 */
	public EStructuralFeature associatedFeature(Object editorKey) {
		if (editorKey == UmlViewsRepository.InformationFlow.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.InformationFlow.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.InformationFlow.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter) {
			return UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter();
		}
		if (editorKey == UmlViewsRepository.InformationFlow.Properties.templateParameter) {
			return UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter();
		}
		if (editorKey == UmlViewsRepository.InformationFlow.Properties.realization) {
			return UMLPackage.eINSTANCE.getInformationFlow_Realization();
		}
		if (editorKey == UmlViewsRepository.InformationFlow.Properties.conveyed) {
			return UMLPackage.eINSTANCE.getInformationFlow_Conveyed();
		}
		if (editorKey == UmlViewsRepository.InformationFlow.Properties.informationSource) {
			return UMLPackage.eINSTANCE.getInformationFlow_InformationSource();
		}
		if (editorKey == UmlViewsRepository.InformationFlow.Properties.informationTarget) {
			return UMLPackage.eINSTANCE.getInformationFlow_InformationTarget();
		}
		if (editorKey == UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge) {
			return UMLPackage.eINSTANCE.getInformationFlow_RealizingActivityEdge();
		}
		if (editorKey == UmlViewsRepository.InformationFlow.Properties.realizingConnector) {
			return UMLPackage.eINSTANCE.getInformationFlow_RealizingConnector();
		}
		if (editorKey == UmlViewsRepository.InformationFlow.Properties.realizingMessage) {
			return UMLPackage.eINSTANCE.getInformationFlow_RealizingMessage();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		InformationFlow informationFlow = (InformationFlow)semanticObject;
		if (UmlViewsRepository.InformationFlow.Properties.name == event.getAffectedEditor()) {
			informationFlow.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
		}
		if (UmlViewsRepository.InformationFlow.Properties.visibility == event.getAffectedEditor()) {
			informationFlow.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.InformationFlow.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				clientDependencySettings.move(event.getNewIndex(), (Dependency) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				owningTemplateParameterSettings.setToReference((TemplateParameter)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
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
			if (event.getKind() == PropertiesEditionEvent.SET) {
				templateParameterSettings.setToReference((TemplateParameter)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
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
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Relationship) {
					realizationSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				realizationSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				realizationSettings.move(event.getNewIndex(), (Relationship) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.conveyed == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Classifier) {
					conveyedSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				conveyedSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				conveyedSettings.move(event.getNewIndex(), (Classifier) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.informationSource == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof NamedElement) {
					informationSourceSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				informationSourceSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				informationSourceSettings.move(event.getNewIndex(), (NamedElement) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.informationTarget == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof NamedElement) {
					informationTargetSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				informationTargetSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				informationTargetSettings.move(event.getNewIndex(), (NamedElement) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof ActivityEdge) {
					realizingActivityEdgeSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				realizingActivityEdgeSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				realizingActivityEdgeSettings.move(event.getNewIndex(), (ActivityEdge) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.realizingConnector == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Connector) {
					realizingConnectorSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				realizingConnectorSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				realizingConnectorSettings.move(event.getNewIndex(), (Connector) event.getNewValue());
			}
		}
		if (UmlViewsRepository.InformationFlow.Properties.realizingMessage == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Message) {
					realizingMessageSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				realizingMessageSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				realizingMessageSettings.move(event.getNewIndex(), (Message) event.getNewValue());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		super.updatePart(msg);
		if (editingPart.isVisible()) {
			InformationFlowPropertiesEditionPart basePart = (InformationFlowPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.InformationFlow.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.InformationFlow.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
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
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
	 */
	@Override
	protected NotificationFilter[] getNotificationFilters() {
		NotificationFilter filter = new EStructuralFeatureNotificationFilter(
			UMLPackage.eINSTANCE.getNamedElement_Name(),
			UMLPackage.eINSTANCE.getNamedElement_Visibility(),
			UMLPackage.eINSTANCE.getNamedElement_ClientDependency(),
			UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(),
			UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(),
			UMLPackage.eINSTANCE.getInformationFlow_Realization(),
			UMLPackage.eINSTANCE.getInformationFlow_Conveyed(),
			UMLPackage.eINSTANCE.getInformationFlow_InformationSource(),
			UMLPackage.eINSTANCE.getInformationFlow_InformationTarget(),
			UMLPackage.eINSTANCE.getInformationFlow_RealizingActivityEdge(),
			UMLPackage.eINSTANCE.getInformationFlow_RealizingConnector(),
			UMLPackage.eINSTANCE.getInformationFlow_RealizingMessage()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.InformationFlow.Properties.conveyed || key == UmlViewsRepository.InformationFlow.Properties.informationSource || key == UmlViewsRepository.InformationFlow.Properties.informationTarget;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
		Diagnostic ret = Diagnostic.OK_INSTANCE;
		if (event.getNewValue() != null) {
			try {
				if (UmlViewsRepository.InformationFlow.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.InformationFlow.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
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
