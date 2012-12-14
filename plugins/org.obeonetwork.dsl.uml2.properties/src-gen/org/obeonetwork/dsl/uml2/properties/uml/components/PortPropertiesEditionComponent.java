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
import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;

import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ProtocolStateMachine;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class PortPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */
	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for type EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings typeSettings;
	
	/**
	 * Settings for owningTemplateParameter EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings owningTemplateParameterSettings;
	
	/**
	 * Settings for templateParameter EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings templateParameterSettings;
	
	/**
	 * Settings for class EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings class_Settings;
	
	/**
	 * Settings for datatype EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings datatypeSettings;
	
	/**
	 * Settings for redefinedProperty ReferencesTable
	 */
	private ReferencesTableSettings redefinedPropertySettings;
	
	/**
	 * Settings for owningAssociation EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings owningAssociationSettings;
	
	/**
	 * Settings for subsettedProperty ReferencesTable
	 */
	private ReferencesTableSettings subsettedPropertySettings;
	
	/**
	 * Settings for association EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings associationSettings;
	
	/**
	 * Settings for associationEnd EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings associationEndSettings;
	
	/**
	 * Settings for redefinedPort ReferencesTable
	 */
	private ReferencesTableSettings redefinedPortSettings;
	
	/**
	 * Settings for protocol EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings protocolSettings;
	
	
	/**
	 * Default constructor
	 * @generated
	 */
	public PortPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject port, String editing_mode) {
		super(editingContext, port, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.Port.class;
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
			
			final Port port = (Port)elt;
			final PortPropertiesEditionPart basePart = (PortPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.Port.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.Literals.STRING, port.getName()));
			
			if (isAccessible(UmlViewsRepository.Port.Properties.visibility)) {
				basePart.initVisibility(EEFUtils.choiceOfValues(port, UMLPackage.eINSTANCE.getNamedElement_Visibility()), port.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(port, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.isLeaf)) {
				basePart.setIsLeaf(port.isLeaf());
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.isStatic)) {
				basePart.setIsStatic(port.isStatic());
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.type)) {
				// init part
				typeSettings = new EObjectFlatComboSettings(port, UMLPackage.eINSTANCE.getTypedElement_Type());
				basePart.initType(typeSettings);
				// set the button mode
				basePart.setTypeButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.isOrdered)) {
				basePart.setIsOrdered(port.isOrdered());
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.isUnique)) {
				basePart.setIsUnique(port.isUnique());
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.isReadOnly)) {
				basePart.setIsReadOnly(port.isReadOnly());
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.owningTemplateParameter)) {
				// init part
				owningTemplateParameterSettings = new EObjectFlatComboSettings(port, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter());
				basePart.initOwningTemplateParameter(owningTemplateParameterSettings);
				// set the button mode
				basePart.setOwningTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.templateParameter)) {
				// init part
				templateParameterSettings = new EObjectFlatComboSettings(port, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter());
				basePart.initTemplateParameter(templateParameterSettings);
				// set the button mode
				basePart.setTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.class_)) {
				// init part
				class_Settings = new EObjectFlatComboSettings(port, UMLPackage.eINSTANCE.getProperty_Class());
				basePart.initClass_(class_Settings);
				// set the button mode
				basePart.setClass_ButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.datatype)) {
				// init part
				datatypeSettings = new EObjectFlatComboSettings(port, UMLPackage.eINSTANCE.getProperty_Datatype());
				basePart.initDatatype(datatypeSettings);
				// set the button mode
				basePart.setDatatypeButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.isDerived)) {
				basePart.setIsDerived(port.isDerived());
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.isDerivedUnion)) {
				basePart.setIsDerivedUnion(port.isDerivedUnion());
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.aggregation)) {
				basePart.initAggregation(EEFUtils.choiceOfValues(port, UMLPackage.eINSTANCE.getProperty_Aggregation()), port.getAggregation());
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.redefinedProperty)) {
				redefinedPropertySettings = new ReferencesTableSettings(port, UMLPackage.eINSTANCE.getProperty_RedefinedProperty());
				basePart.initRedefinedProperty(redefinedPropertySettings);
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.owningAssociation)) {
				// init part
				owningAssociationSettings = new EObjectFlatComboSettings(port, UMLPackage.eINSTANCE.getProperty_OwningAssociation());
				basePart.initOwningAssociation(owningAssociationSettings);
				// set the button mode
				basePart.setOwningAssociationButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.subsettedProperty)) {
				subsettedPropertySettings = new ReferencesTableSettings(port, UMLPackage.eINSTANCE.getProperty_SubsettedProperty());
				basePart.initSubsettedProperty(subsettedPropertySettings);
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.association)) {
				// init part
				associationSettings = new EObjectFlatComboSettings(port, UMLPackage.eINSTANCE.getProperty_Association());
				basePart.initAssociation(associationSettings);
				// set the button mode
				basePart.setAssociationButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.associationEnd)) {
				// init part
				associationEndSettings = new EObjectFlatComboSettings(port, UMLPackage.eINSTANCE.getProperty_AssociationEnd());
				basePart.initAssociationEnd(associationEndSettings);
				// set the button mode
				basePart.setAssociationEndButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.isBehavior)) {
				basePart.setIsBehavior(port.isBehavior());
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.isService)) {
				basePart.setIsService(port.isService());
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.redefinedPort)) {
				redefinedPortSettings = new ReferencesTableSettings(port, UMLPackage.eINSTANCE.getPort_RedefinedPort());
				basePart.initRedefinedPort(redefinedPortSettings);
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.protocol)) {
				// init part
				protocolSettings = new EObjectFlatComboSettings(port, UMLPackage.eINSTANCE.getPort_Protocol());
				basePart.initProtocol(protocolSettings);
				// set the button mode
				basePart.setProtocolButtonMode(ButtonsModeEnum.BROWSE);
			}
			// init filters
			
			
			if (isAccessible(UmlViewsRepository.Port.Properties.clientDependency)) {
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
			
			
			if (isAccessible(UmlViewsRepository.Port.Properties.type)) {
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
			}
			
			
			
			if (isAccessible(UmlViewsRepository.Port.Properties.owningTemplateParameter)) {
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
			if (isAccessible(UmlViewsRepository.Port.Properties.templateParameter)) {
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
			if (isAccessible(UmlViewsRepository.Port.Properties.class_)) {
				basePart.addFilterToClass_(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof Class); //$NON-NLS-1$ 
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.datatype)) {
				basePart.addFilterToDatatype(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof DataType); //$NON-NLS-1$ 
					}
					
				});
			}
			
			
			
			if (isAccessible(UmlViewsRepository.Port.Properties.redefinedProperty)) {
				basePart.addFilterToRedefinedProperty(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof EObject)
							return (!basePart.isContainedInRedefinedPropertyTable((EObject)element));
						return element instanceof Resource;
					}
				
				});
				basePart.addFilterToRedefinedProperty(new EObjectFilter(UMLPackage.Literals.PROPERTY));
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.owningAssociation)) {
				basePart.addFilterToOwningAssociation(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof Association); //$NON-NLS-1$ 
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.subsettedProperty)) {
				basePart.addFilterToSubsettedProperty(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof EObject)
							return (!basePart.isContainedInSubsettedPropertyTable((EObject)element));
						return element instanceof Resource;
					}
				
				});
				basePart.addFilterToSubsettedProperty(new EObjectFilter(UMLPackage.Literals.PROPERTY));
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.association)) {
				basePart.addFilterToAssociation(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof Association); //$NON-NLS-1$ 
					}
					
				});
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.associationEnd)) {
				basePart.addFilterToAssociationEnd(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof Property); //$NON-NLS-1$ 
					}
					
				});
			}
			
			
			if (isAccessible(UmlViewsRepository.Port.Properties.redefinedPort)) {
				basePart.addFilterToRedefinedPort(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof EObject)
							return (!basePart.isContainedInRedefinedPortTable((EObject)element));
						return element instanceof Resource;
					}
				
				});
				basePart.addFilterToRedefinedPort(new EObjectFilter(UMLPackage.Literals.PORT));
			}
			if (isAccessible(UmlViewsRepository.Port.Properties.protocol)) {
				basePart.addFilterToProtocol(new ViewerFilter() {
				
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof ProtocolStateMachine); //$NON-NLS-1$ 
					}
					
				});
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
		if (editorKey == UmlViewsRepository.Port.Properties.name) {
			return UMLPackage.eINSTANCE.getNamedElement_Name();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.visibility) {
			return UMLPackage.eINSTANCE.getNamedElement_Visibility();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.clientDependency) {
			return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.isLeaf) {
			return UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.isStatic) {
			return UMLPackage.eINSTANCE.getFeature_IsStatic();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.type) {
			return UMLPackage.eINSTANCE.getTypedElement_Type();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.isOrdered) {
			return UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.isUnique) {
			return UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.isReadOnly) {
			return UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.owningTemplateParameter) {
			return UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.templateParameter) {
			return UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.class_) {
			return UMLPackage.eINSTANCE.getProperty_Class();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.datatype) {
			return UMLPackage.eINSTANCE.getProperty_Datatype();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.isDerived) {
			return UMLPackage.eINSTANCE.getProperty_IsDerived();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.isDerivedUnion) {
			return UMLPackage.eINSTANCE.getProperty_IsDerivedUnion();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.aggregation) {
			return UMLPackage.eINSTANCE.getProperty_Aggregation();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.redefinedProperty) {
			return UMLPackage.eINSTANCE.getProperty_RedefinedProperty();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.owningAssociation) {
			return UMLPackage.eINSTANCE.getProperty_OwningAssociation();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.subsettedProperty) {
			return UMLPackage.eINSTANCE.getProperty_SubsettedProperty();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.association) {
			return UMLPackage.eINSTANCE.getProperty_Association();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.associationEnd) {
			return UMLPackage.eINSTANCE.getProperty_AssociationEnd();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.isBehavior) {
			return UMLPackage.eINSTANCE.getPort_IsBehavior();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.isService) {
			return UMLPackage.eINSTANCE.getPort_IsService();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.redefinedPort) {
			return UMLPackage.eINSTANCE.getPort_RedefinedPort();
		}
		if (editorKey == UmlViewsRepository.Port.Properties.protocol) {
			return UMLPackage.eINSTANCE.getPort_Protocol();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * @generated
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		Port port = (Port)semanticObject;
		if (UmlViewsRepository.Port.Properties.name == event.getAffectedEditor()) {
			port.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.Literals.STRING, (String)event.getNewValue()));
		}
		if (UmlViewsRepository.Port.Properties.visibility == event.getAffectedEditor()) {
			port.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.Port.Properties.clientDependency == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.Port.Properties.isLeaf == event.getAffectedEditor()) {
			port.setIsLeaf((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Port.Properties.isStatic == event.getAffectedEditor()) {
			port.setIsStatic((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Port.Properties.type == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				typeSettings.setToReference((Type)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
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
		if (UmlViewsRepository.Port.Properties.isOrdered == event.getAffectedEditor()) {
			port.setIsOrdered((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Port.Properties.isUnique == event.getAffectedEditor()) {
			port.setIsUnique((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Port.Properties.isReadOnly == event.getAffectedEditor()) {
			port.setIsReadOnly((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Port.Properties.owningTemplateParameter == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.Port.Properties.templateParameter == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.Port.Properties.datatype == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				datatypeSettings.setToReference((DataType)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				DataType eObject = UMLFactory.eINSTANCE.createDataType();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				datatypeSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.Port.Properties.isDerived == event.getAffectedEditor()) {
			port.setIsDerived((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Port.Properties.isDerivedUnion == event.getAffectedEditor()) {
			port.setIsDerivedUnion((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Port.Properties.aggregation == event.getAffectedEditor()) {
			port.setAggregation((AggregationKind)event.getNewValue());
		}
		if (UmlViewsRepository.Port.Properties.redefinedProperty == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Property) {
					redefinedPropertySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				redefinedPropertySettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				redefinedPropertySettings.move(event.getNewIndex(), (Property) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Port.Properties.owningAssociation == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				owningAssociationSettings.setToReference((Association)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				Association eObject = UMLFactory.eINSTANCE.createAssociation();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				owningAssociationSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.Port.Properties.subsettedProperty == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Property) {
					subsettedPropertySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				subsettedPropertySettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				subsettedPropertySettings.move(event.getNewIndex(), (Property) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Port.Properties.association == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				associationSettings.setToReference((Association)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				Association eObject = UMLFactory.eINSTANCE.createAssociation();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				associationSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.Port.Properties.associationEnd == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				associationEndSettings.setToReference((Property)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				Property eObject = UMLFactory.eINSTANCE.createProperty();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				associationEndSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.Port.Properties.isBehavior == event.getAffectedEditor()) {
			port.setIsBehavior((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Port.Properties.isService == event.getAffectedEditor()) {
			port.setIsService((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Port.Properties.redefinedPort == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Port) {
					redefinedPortSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				redefinedPortSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				redefinedPortSettings.move(event.getNewIndex(), (Port) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Port.Properties.protocol == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				protocolSettings.setToReference((ProtocolStateMachine)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				ProtocolStateMachine eObject = UMLFactory.eINSTANCE.createProtocolStateMachine();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				protocolSettings.setToReference(eObject);
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
			PortPropertiesEditionPart basePart = (PortPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.Literals.STRING, msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.Port.Properties.visibility))
				basePart.setVisibility((VisibilityKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Port.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getFeature_IsStatic().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.isStatic))
				basePart.setIsStatic((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getTypedElement_Type().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.type))
				basePart.setType((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.isOrdered))
				basePart.setIsOrdered((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.isUnique))
				basePart.setIsUnique((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.isReadOnly))
				basePart.setIsReadOnly((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.owningTemplateParameter))
				basePart.setOwningTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.templateParameter))
				basePart.setTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getProperty_Class().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.class_))
				basePart.setClass_((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getProperty_Datatype().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.datatype))
				basePart.setDatatype((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getProperty_IsDerived().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.isDerived))
				basePart.setIsDerived((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getProperty_IsDerivedUnion().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.isDerivedUnion))
				basePart.setIsDerivedUnion((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getProperty_Aggregation().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.Port.Properties.aggregation))
				basePart.setAggregation((AggregationKind)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getProperty_RedefinedProperty().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Port.Properties.redefinedProperty))
				basePart.updateRedefinedProperty();
			if (UMLPackage.eINSTANCE.getProperty_OwningAssociation().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.owningAssociation))
				basePart.setOwningAssociation((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getProperty_SubsettedProperty().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Port.Properties.subsettedProperty))
				basePart.updateSubsettedProperty();
			if (UMLPackage.eINSTANCE.getProperty_Association().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.association))
				basePart.setAssociation((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getProperty_AssociationEnd().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.associationEnd))
				basePart.setAssociationEnd((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getPort_IsBehavior().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.isBehavior))
				basePart.setIsBehavior((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getPort_IsService().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.isService))
				basePart.setIsService((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getPort_RedefinedPort().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Port.Properties.redefinedPort))
				basePart.updateRedefinedPort();
			if (UMLPackage.eINSTANCE.getPort_Protocol().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Port.Properties.protocol))
				basePart.setProtocol((EObject)msg.getNewValue());
			
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
			UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(),
			UMLPackage.eINSTANCE.getFeature_IsStatic(),
			UMLPackage.eINSTANCE.getTypedElement_Type(),
			UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(),
			UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(),
			UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly(),
			UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter(),
			UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter(),
			UMLPackage.eINSTANCE.getProperty_Class(),
			UMLPackage.eINSTANCE.getProperty_Datatype(),
			UMLPackage.eINSTANCE.getProperty_IsDerived(),
			UMLPackage.eINSTANCE.getProperty_IsDerivedUnion(),
			UMLPackage.eINSTANCE.getProperty_Aggregation(),
			UMLPackage.eINSTANCE.getProperty_RedefinedProperty(),
			UMLPackage.eINSTANCE.getProperty_OwningAssociation(),
			UMLPackage.eINSTANCE.getProperty_SubsettedProperty(),
			UMLPackage.eINSTANCE.getProperty_Association(),
			UMLPackage.eINSTANCE.getProperty_AssociationEnd(),
			UMLPackage.eINSTANCE.getPort_IsBehavior(),
			UMLPackage.eINSTANCE.getPort_IsService(),
			UMLPackage.eINSTANCE.getPort_RedefinedPort(),
			UMLPackage.eINSTANCE.getPort_Protocol()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.Port.Properties.isLeaf || key == UmlViewsRepository.Port.Properties.isStatic || key == UmlViewsRepository.Port.Properties.isOrdered || key == UmlViewsRepository.Port.Properties.isUnique || key == UmlViewsRepository.Port.Properties.isReadOnly || key == UmlViewsRepository.Port.Properties.isDerived || key == UmlViewsRepository.Port.Properties.isDerivedUnion || key == UmlViewsRepository.Port.Properties.aggregation || key == UmlViewsRepository.Port.Properties.isBehavior || key == UmlViewsRepository.Port.Properties.isService;
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
				if (UmlViewsRepository.Port.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Port.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Port.Properties.isLeaf == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Port.Properties.isStatic == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Port.Properties.isOrdered == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Port.Properties.isUnique == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Port.Properties.isReadOnly == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Port.Properties.isDerived == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getProperty_IsDerived().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getProperty_IsDerived().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Port.Properties.isDerivedUnion == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getProperty_IsDerivedUnion().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getProperty_IsDerivedUnion().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Port.Properties.aggregation == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getProperty_Aggregation().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getProperty_Aggregation().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Port.Properties.isBehavior == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getPort_IsBehavior().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getPort_IsBehavior().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Port.Properties.isService == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getPort_IsService().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getPort_IsService().getEAttributeType(), newValue);
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
