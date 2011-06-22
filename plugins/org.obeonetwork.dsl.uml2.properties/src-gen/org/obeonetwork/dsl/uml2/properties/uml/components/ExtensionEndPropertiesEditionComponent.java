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
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ExtensionEndPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ExtensionEndPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private	ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for type EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings typeSettings;
	
	/**
	 * Settings for owningTemplateParameter EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings owningTemplateParameterSettings;
	
	/**
	 * Settings for templateParameter EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings templateParameterSettings;
	
	/**
	 * Settings for class EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings class_Settings;
	
	/**
	 * Settings for datatype EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings datatypeSettings;
	
	/**
	 * Settings for redefinedProperty ReferencesTable
	 */
	private	ReferencesTableSettings redefinedPropertySettings;
	
	/**
	 * Settings for owningAssociation EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings owningAssociationSettings;
	
	/**
	 * Settings for subsettedProperty ReferencesTable
	 */
	private	ReferencesTableSettings subsettedPropertySettings;
	
	/**
	 * Settings for association EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings associationSettings;
	
	/**
	 * Settings for associationEnd EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings associationEndSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public ExtensionEndPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject extensionEnd, String editing_mode) {
		super(editingContext, extensionEnd, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.ExtensionEnd.class;
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
			final ExtensionEnd extensionEnd = (ExtensionEnd)elt;
			final ExtensionEndPropertiesEditionPart basePart = (ExtensionEndPropertiesEditionPart)editingPart;
			// init values
			if (extensionEnd.getName() != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), extensionEnd.getName()));
			
			if (isAccessible(UmlViewsRepository.ExtensionEnd.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), extensionEnd.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.ExtensionEnd.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(extensionEnd, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			basePart.setIsLeaf(extensionEnd.isLeaf());
			
			basePart.setIsStatic(extensionEnd.isStatic());
			
			if (isAccessible(UmlViewsRepository.ExtensionEnd.Properties.type)) {
				// init part
				typeSettings = new EObjectFlatComboSettings(extensionEnd, UMLPackage.eINSTANCE.getTypedElement_Type());
				basePart.initType(typeSettings);
				// set the button mode
				basePart.setTypeButtonMode(ButtonsModeEnum.BROWSE);
			}
			basePart.setIsOrdered(extensionEnd.isOrdered());
			
			basePart.setIsUnique(extensionEnd.isUnique());
			
			basePart.setIsReadOnly(extensionEnd.isReadOnly());
			
			if (isAccessible(UmlViewsRepository.ExtensionEnd.Properties.owningTemplateParameter)) {
				// init part
				owningTemplateParameterSettings = new EObjectFlatComboSettings(extensionEnd, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter());
				basePart.initOwningTemplateParameter(owningTemplateParameterSettings);
				// set the button mode
				basePart.setOwningTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ExtensionEnd.Properties.templateParameter)) {
				// init part
				templateParameterSettings = new EObjectFlatComboSettings(extensionEnd, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter());
				basePart.initTemplateParameter(templateParameterSettings);
				// set the button mode
				basePart.setTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ExtensionEnd.Properties.class_)) {
				// init part
				class_Settings = new EObjectFlatComboSettings(extensionEnd, UMLPackage.eINSTANCE.getProperty_Class());
				basePart.initClass_(class_Settings);
				// set the button mode
				basePart.setClass_ButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ExtensionEnd.Properties.datatype)) {
				// init part
				datatypeSettings = new EObjectFlatComboSettings(extensionEnd, UMLPackage.eINSTANCE.getProperty_Datatype());
				basePart.initDatatype(datatypeSettings);
				// set the button mode
				basePart.setDatatypeButtonMode(ButtonsModeEnum.BROWSE);
			}
			basePart.setIsDerived(extensionEnd.isDerived());
			
			basePart.setIsDerivedUnion(extensionEnd.isDerivedUnion());
			
			if (isAccessible(UmlViewsRepository.ExtensionEnd.Properties.aggregation)) {
				basePart.initAggregation((EEnum) UMLPackage.eINSTANCE.getProperty_Aggregation().getEType(), extensionEnd.getAggregation());
			}
			if (isAccessible(UmlViewsRepository.ExtensionEnd.Properties.redefinedProperty)) {
				redefinedPropertySettings = new ReferencesTableSettings(extensionEnd, UMLPackage.eINSTANCE.getProperty_RedefinedProperty());
				basePart.initRedefinedProperty(redefinedPropertySettings);
			}
			if (isAccessible(UmlViewsRepository.ExtensionEnd.Properties.owningAssociation)) {
				// init part
				owningAssociationSettings = new EObjectFlatComboSettings(extensionEnd, UMLPackage.eINSTANCE.getProperty_OwningAssociation());
				basePart.initOwningAssociation(owningAssociationSettings);
				// set the button mode
				basePart.setOwningAssociationButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ExtensionEnd.Properties.subsettedProperty)) {
				subsettedPropertySettings = new ReferencesTableSettings(extensionEnd, UMLPackage.eINSTANCE.getProperty_SubsettedProperty());
				basePart.initSubsettedProperty(subsettedPropertySettings);
			}
			if (isAccessible(UmlViewsRepository.ExtensionEnd.Properties.association)) {
				// init part
				associationSettings = new EObjectFlatComboSettings(extensionEnd, UMLPackage.eINSTANCE.getProperty_Association());
				basePart.initAssociation(associationSettings);
				// set the button mode
				basePart.setAssociationButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ExtensionEnd.Properties.associationEnd)) {
				// init part
				associationEndSettings = new EObjectFlatComboSettings(extensionEnd, UMLPackage.eINSTANCE.getProperty_AssociationEnd());
				basePart.initAssociationEnd(associationEndSettings);
				// set the button mode
				basePart.setAssociationEndButtonMode(ButtonsModeEnum.BROWSE);
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
			// Start of user code for additional businessfilters for class
			
			// End of user code
			
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
			// Start of user code for additional businessfilters for datatype
			
			// End of user code
			
			
			
			
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
			basePart.addFilterToRedefinedProperty(new EObjectFilter(UMLPackage.eINSTANCE.getProperty()));
			// Start of user code for additional businessfilters for redefinedProperty
			
			// End of user code
			
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
			// Start of user code for additional businessfilters for owningAssociation
			
			// End of user code
			
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
			basePart.addFilterToSubsettedProperty(new EObjectFilter(UMLPackage.eINSTANCE.getProperty()));
			// Start of user code for additional businessfilters for subsettedProperty
			
			// End of user code
			
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
			// Start of user code for additional businessfilters for association
			
			// End of user code
			
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
			// Start of user code for additional businessfilters for associationEnd
			
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
		ExtensionEnd extensionEnd = (ExtensionEnd)semanticObject;
		if (UmlViewsRepository.ExtensionEnd.Properties.name == event.getAffectedEditor()) {
			extensionEnd.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.ExtensionEnd.Properties.visibility == event.getAffectedEditor()) {
			extensionEnd.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.ExtensionEnd.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.ExtensionEnd.Properties.isLeaf == event.getAffectedEditor()) {
			extensionEnd.setIsLeaf((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.ExtensionEnd.Properties.isStatic == event.getAffectedEditor()) {
			extensionEnd.setIsStatic((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.ExtensionEnd.Properties.type == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.ExtensionEnd.Properties.isOrdered == event.getAffectedEditor()) {
			extensionEnd.setIsOrdered((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.ExtensionEnd.Properties.isUnique == event.getAffectedEditor()) {
			extensionEnd.setIsUnique((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.ExtensionEnd.Properties.isReadOnly == event.getAffectedEditor()) {
			extensionEnd.setIsReadOnly((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.ExtensionEnd.Properties.owningTemplateParameter == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.ExtensionEnd.Properties.templateParameter == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.ExtensionEnd.Properties.datatype == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				datatypeSettings.setToReference((DataType)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
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
		if (UmlViewsRepository.ExtensionEnd.Properties.isDerived == event.getAffectedEditor()) {
			extensionEnd.setIsDerived((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.ExtensionEnd.Properties.isDerivedUnion == event.getAffectedEditor()) {
			extensionEnd.setIsDerivedUnion((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.ExtensionEnd.Properties.aggregation == event.getAffectedEditor()) {
			extensionEnd.setAggregation((AggregationKind)event.getNewValue());
		}
		if (UmlViewsRepository.ExtensionEnd.Properties.redefinedProperty == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Property) {
					redefinedPropertySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					redefinedPropertySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.ExtensionEnd.Properties.owningAssociation == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				owningAssociationSettings.setToReference((Association)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
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
		if (UmlViewsRepository.ExtensionEnd.Properties.subsettedProperty == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Property) {
					subsettedPropertySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					subsettedPropertySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.ExtensionEnd.Properties.association == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				associationSettings.setToReference((Association)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
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
		if (UmlViewsRepository.ExtensionEnd.Properties.associationEnd == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				associationEndSettings.setToReference((Property)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
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
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			ExtensionEndPropertiesEditionPart basePart = (ExtensionEndPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getFeature_IsStatic().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.isStatic))
				basePart.setIsStatic((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getTypedElement_Type().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.type))
				basePart.setType((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.isOrdered))
				basePart.setIsOrdered((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.isUnique))
				basePart.setIsUnique((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.isReadOnly))
				basePart.setIsReadOnly((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.owningTemplateParameter))
				basePart.setOwningTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.templateParameter))
				basePart.setTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getProperty_Class().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.class_))
				basePart.setClass_((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getProperty_Datatype().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.datatype))
				basePart.setDatatype((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getProperty_IsDerived().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.isDerived))
				basePart.setIsDerived((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getProperty_IsDerivedUnion().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.isDerivedUnion))
				basePart.setIsDerivedUnion((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getProperty_Aggregation().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.aggregation))
				basePart.setAggregation((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getProperty_RedefinedProperty().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.redefinedProperty))
				basePart.updateRedefinedProperty();
			if (UMLPackage.eINSTANCE.getProperty_OwningAssociation().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.owningAssociation))
				basePart.setOwningAssociation((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getProperty_SubsettedProperty().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.subsettedProperty))
				basePart.updateSubsettedProperty();
			if (UMLPackage.eINSTANCE.getProperty_Association().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.association))
				basePart.setAssociation((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getProperty_AssociationEnd().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ExtensionEnd.Properties.associationEnd))
				basePart.setAssociationEnd((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.ExtensionEnd.Properties.isLeaf || key == UmlViewsRepository.ExtensionEnd.Properties.isStatic || key == UmlViewsRepository.ExtensionEnd.Properties.isOrdered || key == UmlViewsRepository.ExtensionEnd.Properties.isUnique || key == UmlViewsRepository.ExtensionEnd.Properties.isReadOnly || key == UmlViewsRepository.ExtensionEnd.Properties.isDerived || key == UmlViewsRepository.ExtensionEnd.Properties.isDerivedUnion || key == UmlViewsRepository.ExtensionEnd.Properties.aggregation;
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
				if (UmlViewsRepository.ExtensionEnd.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.ExtensionEnd.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.ExtensionEnd.Properties.isLeaf == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.ExtensionEnd.Properties.isStatic == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.ExtensionEnd.Properties.isOrdered == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.ExtensionEnd.Properties.isUnique == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.ExtensionEnd.Properties.isReadOnly == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.ExtensionEnd.Properties.isDerived == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getProperty_IsDerived().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getProperty_IsDerived().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.ExtensionEnd.Properties.isDerivedUnion == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getProperty_IsDerivedUnion().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getProperty_IsDerivedUnion().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.ExtensionEnd.Properties.aggregation == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getProperty_Aggregation().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getProperty_Aggregation().getEAttributeType(), newValue);
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
