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
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorKind;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ConnectorPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
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
	 * Settings for redefinedConnector ReferencesTable
	 */
	private	ReferencesTableSettings redefinedConnectorSettings;
	
	/**
	 * Settings for contract ReferencesTable
	 */
	private	ReferencesTableSettings contractSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public ConnectorPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject connector, String editing_mode) {
		super(editingContext, connector, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.Connector.class;
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
			final Connector connector = (Connector)elt;
			final ConnectorPropertiesEditionPart basePart = (ConnectorPropertiesEditionPart)editingPart;
			// init values
			if (connector.getName() != null && isAccessible(UmlViewsRepository.Connector.Properties.name))
				basePart.setName(EEFConverterUtil.convertToString(UMLPackage.eINSTANCE.getString(), connector.getName()));
			
			if (isAccessible(UmlViewsRepository.Connector.Properties.visibility)) {
				basePart.initVisibility((EEnum) UMLPackage.eINSTANCE.getNamedElement_Visibility().getEType(), connector.getVisibility());
			}
			if (isAccessible(UmlViewsRepository.Connector.Properties.clientDependency)) {
				clientDependencySettings = new ReferencesTableSettings(connector, UMLPackage.eINSTANCE.getNamedElement_ClientDependency());
				basePart.initClientDependency(clientDependencySettings);
			}
			basePart.setIsLeaf(connector.isLeaf());
			
			basePart.setIsStatic(connector.isStatic());
			
			if (isAccessible(UmlViewsRepository.Connector.Properties.type)) {
				// init part
				typeSettings = new EObjectFlatComboSettings(connector, UMLPackage.eINSTANCE.getConnector_Type());
				basePart.initType(typeSettings);
				// set the button mode
				basePart.setTypeButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.Connector.Properties.redefinedConnector)) {
				redefinedConnectorSettings = new ReferencesTableSettings(connector, UMLPackage.eINSTANCE.getConnector_RedefinedConnector());
				basePart.initRedefinedConnector(redefinedConnectorSettings);
			}
			if (isAccessible(UmlViewsRepository.Connector.Properties.kind)) {
				basePart.initKind((EEnum) UMLPackage.eINSTANCE.getConnector_Kind().getEType(), connector.getKind());
			}
			if (isAccessible(UmlViewsRepository.Connector.Properties.contract)) {
				contractSettings = new ReferencesTableSettings(connector, UMLPackage.eINSTANCE.getConnector_Contract());
				basePart.initContract(contractSettings);
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
				return (element instanceof String && element.equals("")) || (element instanceof Association); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for type
			
			// End of user code
			
			basePart.addFilterToRedefinedConnector(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInRedefinedConnectorTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToRedefinedConnector(new EObjectFilter(UMLPackage.eINSTANCE.getConnector()));
			// Start of user code for additional businessfilters for redefinedConnector
			
			// End of user code
			
			
			basePart.addFilterToContract(new ViewerFilter() {
			
				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInContractTable((EObject)element));
					return element instanceof Resource;
				}
			
			});
			basePart.addFilterToContract(new EObjectFilter(UMLPackage.eINSTANCE.getBehavior()));
			// Start of user code for additional businessfilters for contract
			
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
		Connector connector = (Connector)semanticObject;
		if (UmlViewsRepository.Connector.Properties.name == event.getAffectedEditor()) {
			connector.setName((java.lang.String)EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getString(), (String)event.getNewValue()));
		}
		if (UmlViewsRepository.Connector.Properties.visibility == event.getAffectedEditor()) {
			connector.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.Connector.Properties.clientDependency == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Dependency) {
					clientDependencySettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					clientDependencySettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Connector.Properties.isLeaf == event.getAffectedEditor()) {
			connector.setIsLeaf((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Connector.Properties.isStatic == event.getAffectedEditor()) {
			connector.setIsStatic((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.Connector.Properties.type == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				typeSettings.setToReference((Association)event.getNewValue());
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
				typeSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.Connector.Properties.redefinedConnector == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Connector) {
					redefinedConnectorSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					redefinedConnectorSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
		if (UmlViewsRepository.Connector.Properties.kind == event.getAffectedEditor()) {
			connector.setKind((ConnectorKind)event.getNewValue());
		}
		if (UmlViewsRepository.Connector.Properties.contract == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD)  {
				if (event.getNewValue() instanceof Behavior) {
					contractSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
					contractSettings.removeFromReference((EObject) event.getNewValue());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			ConnectorPropertiesEditionPart basePart = (ConnectorPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Connector.Properties.name)) {
				if (msg.getNewValue() != null) {
					basePart.setName(EcoreUtil.convertToString(UMLPackage.eINSTANCE.getString(), msg.getNewValue()));
				} else {
					basePart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.Connector.Properties.visibility))
				basePart.setVisibility((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getNamedElement_ClientDependency().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Connector.Properties.clientDependency))
				basePart.updateClientDependency();
			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Connector.Properties.isLeaf))
				basePart.setIsLeaf((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getFeature_IsStatic().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Connector.Properties.isStatic))
				basePart.setIsStatic((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getConnector_Type().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.Connector.Properties.type))
				basePart.setType((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getConnector_RedefinedConnector().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Connector.Properties.redefinedConnector))
				basePart.updateRedefinedConnector();
			if (UMLPackage.eINSTANCE.getConnector_Kind().equals(msg.getFeature()) && isAccessible(UmlViewsRepository.Connector.Properties.kind))
				basePart.setKind((Enumerator)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getConnector_Contract().equals(msg.getFeature())  && isAccessible(UmlViewsRepository.Connector.Properties.contract))
				basePart.updateContract();
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.Connector.Properties.isLeaf || key == UmlViewsRepository.Connector.Properties.isStatic;
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
				if (UmlViewsRepository.Connector.Properties.name == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Connector.Properties.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Connector.Properties.isLeaf == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Connector.Properties.isStatic == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getFeature_IsStatic().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.Connector.Properties.kind == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getConnector_Kind().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getConnector_Kind().getEAttributeType(), newValue);
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
