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
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.parts.ConnectorEndPropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ConnectorEndPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for role EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings roleSettings;
	
	/**
	 * Settings for partWithPort EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings partWithPortSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public ConnectorEndPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject connectorEnd, String editing_mode) {
		super(editingContext, connectorEnd, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.ConnectorEnd.class;
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
			final ConnectorEnd connectorEnd = (ConnectorEnd)elt;
			final ConnectorEndPropertiesEditionPart basePart = (ConnectorEndPropertiesEditionPart)editingPart;
			// init values
			basePart.setIsOrdered(connectorEnd.isOrdered());
			
			basePart.setIsUnique(connectorEnd.isUnique());
			
			if (isAccessible(UmlViewsRepository.ConnectorEnd.Properties.role)) {
				// init part
				roleSettings = new EObjectFlatComboSettings(connectorEnd, UMLPackage.eINSTANCE.getConnectorEnd_Role());
				basePart.initRole(roleSettings);
				// set the button mode
				basePart.setRoleButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.ConnectorEnd.Properties.partWithPort)) {
				// init part
				partWithPortSettings = new EObjectFlatComboSettings(connectorEnd, UMLPackage.eINSTANCE.getConnectorEnd_PartWithPort());
				basePart.initPartWithPort(partWithPortSettings);
				// set the button mode
				basePart.setPartWithPortButtonMode(ButtonsModeEnum.BROWSE);
			}
			// init filters
			
			
			basePart.addFilterToRole(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof ConnectableElement);
				}
			
			});
			// Start of user code for additional businessfilters for role
			
			// End of user code
			
			basePart.addFilterToPartWithPort(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String && element.equals("")) || (element instanceof Property); //$NON-NLS-1$ 
				}
			
			});
			// Start of user code for additional businessfilters for partWithPort
			
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
		ConnectorEnd connectorEnd = (ConnectorEnd)semanticObject;
		if (UmlViewsRepository.ConnectorEnd.Properties.isOrdered == event.getAffectedEditor()) {
			connectorEnd.setIsOrdered((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.ConnectorEnd.Properties.isUnique == event.getAffectedEditor()) {
			connectorEnd.setIsUnique((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.ConnectorEnd.Properties.role == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				roleSettings.setToReference((ConnectableElement)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD)  {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, roleSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.ConnectorEnd.Properties.partWithPort == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET)  {
				partWithPortSettings.setToReference((Property)event.getNewValue());
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
				partWithPortSettings.setToReference(eObject);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			ConnectorEndPropertiesEditionPart basePart = (ConnectorEndPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ConnectorEnd.Properties.isOrdered))
				basePart.setIsOrdered((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ConnectorEnd.Properties.isUnique))
				basePart.setIsUnique((Boolean)msg.getNewValue());
			
			if (UMLPackage.eINSTANCE.getConnectorEnd_Role().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ConnectorEnd.Properties.role))
				basePart.setRole((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getConnectorEnd_PartWithPort().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.ConnectorEnd.Properties.partWithPort))
				basePart.setPartWithPort((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.ConnectorEnd.Properties.isOrdered || key == UmlViewsRepository.ConnectorEnd.Properties.isUnique || key == UmlViewsRepository.ConnectorEnd.Properties.role;
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
				if (UmlViewsRepository.ConnectorEnd.Properties.isOrdered == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.ConnectorEnd.Properties.isUnique == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EcoreUtil.createFromString(UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().getEAttributeType(), (String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique().getEAttributeType(), newValue);
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
