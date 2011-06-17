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
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.ParameterEffectKind;
import org.eclipse.uml2.uml.ParameterSet;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.parts.ParameterPropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ParameterPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
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
	 * Settings for parameterSet ReferencesTable
	 */
	private	ReferencesTableSettings parameterSetSettings;
	
	/**
	 * Settings for operation EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings operationSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public ParameterPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject parameter, String editing_mode) {
		super(editingContext, parameter, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.Parameter.class;
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
			final Parameter parameter = (Parameter)elt;
			final ParameterPropertiesEditionPart basePart = (ParameterPropertiesEditionPart)editingPart;
			// init values
			if (parameter.getName() != null && isAccessible(UmlViewsRepository.Parameter.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), parameter.getName()));
			
			if (isAccessible(UmlViewsRepository.Parameter.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), parameter.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.Parameter.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(parameter, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			if (isAccessible(UmlViewsRepository.Parameter.Properties.type)) {
				// init part
				typeSettings = new EObjectFlatComboSettings(parameter, UMLPackage.eINSTANCE.getTypedElement_Type());
				basePart.initType(typeSettings);
				// set the button mode
				basePart.setTypeButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Parameter.Properties.owningTemplateParameter)) {
				// init part
				owningTemplateParameterSettings = new EObjectFlatComboSettings(parameter, UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter());
				basePart.initOwningTemplateParameter(owningTemplateParameterSettings);
				// set the button mode
				basePart.setOwningTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Parameter.Properties.templateParameter)) {
				// init part
				templateParameterSettings = new EObjectFlatComboSettings(parameter, UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter());
				basePart.initTemplateParameter(templateParameterSettings);
				// set the button mode
				basePart.setTemplateParameterButtonMode(ButtonsModeEnum.BROWSE);
			}
			basePart.setIsOrdered(parameter.isOrdered());
			
			basePart.setIsUnique(parameter.isUnique());
			
			if (isAccessible(UmlViewsRepository.Parameter.Properties.parameterSet)) {
				parameterSetSettings = new ReferencesTableSettings(parameter, UMLPackage.eINSTANCE.getParameter_ParameterSet());
				basePart.initParameterSet(parameterSetSettings);
			}
			if (isAccessible(UmlViewsRepository.Parameter.Properties.operation)) {
				// init part
				operationSettings = new EObjectFlatComboSettings(parameter, UMLPackage.eINSTANCE.getParameter_Operation());
				basePart.initOperation(operationSettings);
				// set the button mode
				basePart.setOperationButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Parameter.Properties.direction)) {
				basePart.initDirection((EEnum) UMLPackage.eINSTANCE.getParameter_Direction().getEType(), parameter.getDirection());
			}
			basePart.setIsException(parameter.isException());
			
			basePart.setIsStream(parameter.isStream());
			
			if (isAccessible(UmlViewsRepository.Parameter.Properties.effect)) {
				basePart.initEffect((EEnum) UMLPackage.eINSTANCE.getParameter_Effect().getEType(), parameter.getEffect());
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
			
			
			
			basePart.addFilterToParameterSet(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInParameterSetTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToParameterSet(new EObjectFilter(UMLPackage.eINSTANCE.getParameterSet()));
			// Start of user code for additional businessfilters for parameterSet
			
			// End of user code
			
			basePart.addFilterToOperation(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof Operation); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for operation
			
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
		Parameter parameter = (Parameter)semanticObject;
		if (UmlViewsRepository.Parameter.Properties.name == event.getAffectedEditor()) {
			parameter.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.Parameter.Properties.visibility == event.getAffectedEditor()) {
			parameter.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.Parameter.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Parameter.Properties.type == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.Parameter.Properties.owningTemplateParameter == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.Parameter.Properties.templateParameter == event.getAffectedEditor()) {
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
		if (UmlViewsRepository.Parameter.Properties.isOrdered == event.getAffectedEditor()) {
			parameter.setIsOrdered((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Parameter.Properties.isUnique == event.getAffectedEditor()) {
			parameter.setIsUnique((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Parameter.Properties.parameterSet == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof ParameterSet) {
					parameterSetSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					parameterSetSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Parameter.Properties.direction == event.getAffectedEditor()) {
			parameter.setDirection((ParameterDirectionKind)event.getNewValue());
		}
		if (UmlViewsRepository.Parameter.Properties.isException == event.getAffectedEditor()) {
			parameter.setIsException((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Parameter.Properties.isStream == event.getAffectedEditor()) {
			parameter.setIsStream((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Parameter.Properties.effect == event.getAffectedEditor()) {
			parameter.setEffect((ParameterEffectKind)event.getNewValue());
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			ParameterPropertiesEditionPart basePart = (ParameterPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Parameter.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.Parameter.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Parameter.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getTypedElement_Type().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Parameter.Properties.type))
				basePart.setType((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getParameterableElement_OwningTemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Parameter.Properties.owningTemplateParameter))
				basePart.setOwningTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getParameterableElement_TemplateParameter().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Parameter.Properties.templateParameter))
				basePart.setTemplateParameter((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Parameter.Properties.isOrdered))
				basePart.setIsOrdered((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Parameter.Properties.isUnique))
				basePart.setIsUnique((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getParameter_ParameterSet().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Parameter.Properties.parameterSet))
				basePart.updateParameterSet();
			if (UMLPackage.eINSTANCE.getParameter_Operation().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Parameter.Properties.operation))
				basePart.setOperation((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getParameter_Direction().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.Parameter.Properties.direction))
				basePart.setDirection((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getParameter_IsException().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Parameter.Properties.isException))
				basePart.setIsException((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getParameter_IsStream().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Parameter.Properties.isStream))
				basePart.setIsStream((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getParameter_Effect().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.Parameter.Properties.effect))
				basePart.setEffect((Enumerator)msg.getNewValue());
			
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.Parameter.Properties.isOrdered || key == UmlViewsRepository.Parameter.Properties.isUnique || key == UmlViewsRepository.Parameter.Properties.direction || key == UmlViewsRepository.Parameter.Properties.isException || key == UmlViewsRepository.Parameter.Properties.isStream;
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
				if (UmlViewsRepository.Parameter.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Parameter.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Parameter.Properties.isOrdered == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Parameter.Properties.isUnique == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Parameter.Properties.direction == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getParameter_Direction().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getParameter_Direction().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Parameter.Properties.isException == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getParameter_IsException().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getParameter_IsException().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Parameter.Properties.isStream == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getParameter_IsStream().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getParameter_IsStream().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Parameter.Properties.effect == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getParameter_Effect().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getParameter_Effect().getEAttributeType(), newValue);
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
