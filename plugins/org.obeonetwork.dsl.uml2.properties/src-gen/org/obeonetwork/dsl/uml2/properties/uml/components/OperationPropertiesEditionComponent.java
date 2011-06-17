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
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallConcurrencyKind;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.parts.OperationPropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class OperationPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for clientDependency ReferencesTable
	 */
	private	ReferencesTableSettings clientDependencySettings;
	
	/**
	 * Settings for method ReferencesTable
	 */
	private	ReferencesTableSettings methodSettings;
	
	/**
	 * Settings for raisedException ReferencesTable
	 */
	private	ReferencesTableSettings raisedExceptionSettings;
	
	/**
	 * Settings for owningTemplateParameter EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings owningTemplateParameterSettings;
	
	/**
	 * Settings for templateParameter EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings templateParameterSettings;
	
	/**
	 * Settings for interface EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings interface_Settings;
	
	/**
	 * Settings for class EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings class_Settings;
	
	/**
	 * Settings for precondition ReferencesTable
	 */
	private	ReferencesTableSettings preconditionSettings;
	
	/**
	 * Settings for postcondition ReferencesTable
	 */
	private	ReferencesTableSettings postconditionSettings;
	
	/**
	 * Settings for redefinedOperation ReferencesTable
	 */
	private	ReferencesTableSettings redefinedOperationSettings;
	
	/**
	 * Settings for datatype EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings datatypeSettings;
	
	/**
	 * Settings for bodyCondition EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings bodyConditionSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public OperationPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject operation, String editing_mode) {
		super(editingContext, operation, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.Operation.class;
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
			final Operation operation = (Operation)elt;
			final OperationPropertiesEditionPart basePart = (OperationPropertiesEditionPart)editingPart;
			// init values
			if (operation.getName() != null && isAccessible(UmlViewsRepository.Operation.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), operation.getName()));
			
			if (isAccessible(UmlViewsRepository.Operation.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), operation.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.Operation.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(operation, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			basePart.setIsLeaf(operation.isLeaf());
			
			basePart.setIsStatic(operation.isStatic());
			
			basePart.setIsAbstract(operation.isAbstract());
			
			if (isAccessible(UmlViewsRepository.Operation.Properties.method)) {
				methodSettings = new ReferencesTableSettings(operation, UMLPackage.eINSTANCE.getBehavioralFeature_Method());
				basePart.initMethod(methodSettings);
			}
			if (isAccessible(UmlViewsRepository.Operation.Properties.concurrency)) {
				basePart.initConcurrency((EEnum) UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency().getEType(), operation.getConcurrency());
			}
			if (isAccessible(UmlViewsRepository.Operation.Properties.raisedException)) {
				raisedExceptionSettings = new ReferencesTableSettings(operation, UMLPackage.eINSTANCE.getBehavioralFeature_RaisedException());
				basePart.initRaisedException(raisedExceptionSettings);
			}
			if (isAccessible(UmlViewsRepository.Operation.Properties.owningTemplateParameter)) {
				// init part
				owningTemplateParameterSettings = new EObjectFlatComboSettings(operation, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter());
				basePart.initOwningTemplateParameter(owningTemplateParameterSettings);
				// set the button mode
				basePart.setOwningTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Operation.Properties.templateParameter)) {
				// init part
				templateParameterSettings = new EObjectFlatComboSettings(operation, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter());
				basePart.initTemplateParameter(templateParameterSettings);
				// set the button mode
				basePart.setTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Operation.Properties.interface_)) {
				// init part
				interface_Settings = new EObjectFlatComboSettings(operation, UMLPackage.eINSTANCE.getOperation_Interface());
				basePart.initInterface_(interface_Settings);
				// set the button mode
				basePart.setInterface_ButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Operation.Properties.class_)) {
				// init part
				class_Settings = new EObjectFlatComboSettings(operation, UMLPackage.eINSTANCE.getOperation_Class());
				basePart.initClass_(class_Settings);
				// set the button mode
				basePart.setClass_ButtonMode(ButtonsModeEnum.BROWSE);
			}
			basePart.setIsQuery(operation.isQuery());
			
			if (isAccessible(UmlViewsRepository.Operation.Properties.precondition)) {
				preconditionSettings = new ReferencesTableSettings(operation, UMLPackage.eINSTANCE.getOperation_Precondition());
				basePart.initPrecondition(preconditionSettings);
			}
			if (isAccessible(UmlViewsRepository.Operation.Properties.postcondition)) {
				postconditionSettings = new ReferencesTableSettings(operation, UMLPackage.eINSTANCE.getOperation_Postcondition());
				basePart.initPostcondition(postconditionSettings);
			}
			if (isAccessible(UmlViewsRepository.Operation.Properties.redefinedOperation)) {
				redefinedOperationSettings = new ReferencesTableSettings(operation, UMLPackage.eINSTANCE.getOperation_RedefinedOperation());
				basePart.initRedefinedOperation(redefinedOperationSettings);
			}
			if (isAccessible(UmlViewsRepository.Operation.Properties.datatype)) {
				// init part
				datatypeSettings = new EObjectFlatComboSettings(operation, UMLPackage.eINSTANCE.getOperation_Datatype());
				basePart.initDatatype(datatypeSettings);
				// set the button mode
				basePart.setDatatypeButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Operation.Properties.bodyCondition)) {
				// init part
				bodyConditionSettings = new EObjectFlatComboSettings(operation, UMLPackage.eINSTANCE.getOperation_BodyCondition());
				basePart.initBodyCondition(bodyConditionSettings);
				// set the button mode
				basePart.setBodyConditionButtonMode(ButtonsModeEnum.BROWSE);
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
			
			
			
			
			basePart.addFilterToMethod(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInMethodTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToMethod(new EObjectFilter(UMLPackage.eINSTANCE.getBehavior()));
			// Start of user code for additional businessfilters for method
			
			// End of user code
			
			
			basePart.addFilterToRaisedException(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInRaisedExceptionTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToRaisedException(new EObjectFilter(UMLPackage.eINSTANCE.getType()));
			// Start of user code for additional businessfilters for raisedException
			
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
			
			basePart.addFilterToInterface_(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof Interface); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for interface
			
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
			
			
			basePart.addFilterToPrecondition(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInPreconditionTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToPrecondition(new EObjectFilter(UMLPackage.eINSTANCE.getConstraint()));
			// Start of user code for additional businessfilters for precondition
			
			// End of user code
			
			basePart.addFilterToPostcondition(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInPostconditionTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToPostcondition(new EObjectFilter(UMLPackage.eINSTANCE.getConstraint()));
			// Start of user code for additional businessfilters for postcondition
			
			// End of user code
			
			basePart.addFilterToRedefinedOperation(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInRedefinedOperationTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToRedefinedOperation(new EObjectFilter(UMLPackage.eINSTANCE.getOperation()));
			// Start of user code for additional businessfilters for redefinedOperation
			
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
			
			basePart.addFilterToBodyCondition(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof Constraint); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for bodyCondition
			
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
		Operation operation = (Operation)semanticObject;
		if (UmlViewsRepository.Operation.Properties.name == event.getAffectedEditor()) {
			operation.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.Operation.Properties.visibility == event.getAffectedEditor()) {
			operation.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.Operation.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Operation.Properties.isLeaf == event.getAffectedEditor()) {
			operation.setIsLeaf((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Operation.Properties.isStatic == event.getAffectedEditor()) {
			operation.setIsStatic((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Operation.Properties.isAbstract == event.getAffectedEditor()) {
			operation.setIsAbstract((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Operation.Properties.method == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Behavior) {
					methodSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					methodSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Operation.Properties.concurrency == event.getAffectedEditor()) {
			operation.setConcurrency((CallConcurrencyKind)event.getNewValue());
		}
		if (UmlViewsRepository.Operation.Properties.raisedException == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Type) {
					raisedExceptionSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					raisedExceptionSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Operation.Properties.owningTemplateParameter == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.Operation.Properties.templateParameter == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.Operation.Properties.interface_ == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				interface_Settings.setToReference((Interface)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				Interface eObject = UMLFactory.eINSTANCE.createInterface();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				interface_Settings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.Operation.Properties.class_ == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				class_Settings.setToReference((Class)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				Class eObject = UMLFactory.eINSTANCE.createClass();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				class_Settings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.Operation.Properties.isQuery == event.getAffectedEditor()) {
			operation.setIsQuery((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Operation.Properties.precondition == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Constraint) {
					preconditionSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					preconditionSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Operation.Properties.postcondition == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Constraint) {
					postconditionSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					postconditionSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Operation.Properties.redefinedOperation == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Operation) {
					redefinedOperationSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					redefinedOperationSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Operation.Properties.datatype == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.Operation.Properties.bodyCondition == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				bodyConditionSettings.setToReference((Constraint)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				Constraint eObject = UMLFactory.eINSTANCE.createConstraint();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				bodyConditionSettings.setToReference(eObject);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			OperationPropertiesEditionPart basePart = (OperationPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.Operation.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Operation.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getFeature_IsStatic().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.isStatic))
				basePart.setIsStatic((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.isAbstract))
				basePart.setIsAbstract((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavioralFeature_Method().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Operation.Properties.method))
				basePart.updateMethod();
			if (UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.Operation.Properties.concurrency))
				basePart.setConcurrency((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getBehavioralFeature_RaisedException().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Operation.Properties.raisedException))
				basePart.updateRaisedException();
			if (UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.owningTemplateParameter))
				basePart.setOwningTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.templateParameter))
				basePart.setTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getOperation_Interface().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.interface_))
				basePart.setInterface_((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getOperation_Class().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.class_))
				basePart.setClass_((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getOperation_IsQuery().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.isQuery))
				basePart.setIsQuery((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getOperation_Precondition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Operation.Properties.precondition))
				basePart.updatePrecondition();
			if (UMLPackage.eINSTANCE.getOperation_Postcondition().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Operation.Properties.postcondition))
				basePart.updatePostcondition();
			if (UMLPackage.eINSTANCE.getOperation_RedefinedOperation().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Operation.Properties.redefinedOperation))
				basePart.updateRedefinedOperation();
			if (UMLPackage.eINSTANCE.getOperation_Datatype().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.datatype))
				basePart.setDatatype((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getOperation_BodyCondition().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Operation.Properties.bodyCondition))
				basePart.setBodyCondition((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.Operation.Properties.isLeaf || key == UmlViewsRepository.Operation.Properties.isStatic || key == UmlViewsRepository.Operation.Properties.isAbstract || key == UmlViewsRepository.Operation.Properties.concurrency || key == UmlViewsRepository.Operation.Properties.isQuery;
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
				if (UmlViewsRepository.Operation.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Operation.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Operation.Properties.isLeaf == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Operation.Properties.isStatic == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Operation.Properties.isAbstract == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Operation.Properties.concurrency == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getBehavioralFeature_Concurrency().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Operation.Properties.isQuery == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getOperation_IsQuery().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getOperation_IsQuery().getEAttributeType(), newValue);
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
