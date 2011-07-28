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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.QualifierValue;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.QualifierValuePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class QualifierValuePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for qualifier EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings qualifierSettings;
	
	/**
	 * Settings for value EObjectFlatComboViewer
	 */
	private	EObjectFlatComboSettings valueSettings;
	
	/**
	 * Default constructor
	 * 
	 */
	public QualifierValuePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject qualifierValue, String editing_mode) {
		super(editingContext, qualifierValue, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.QualifierValue.class;
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
			final QualifierValue qualifierValue = (QualifierValue)elt;
			final QualifierValuePropertiesEditionPart basePart = (QualifierValuePropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.QualifierValue.Properties.qualifier)) {
				// init part
				qualifierSettings = new EObjectFlatComboSettings(qualifierValue, UMLPackage.eINSTANCE.getQualifierValue_Qualifier());
				basePart.initQualifier(qualifierSettings);
				// set the button mode
				basePart.setQualifierButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.QualifierValue.Properties.value)) {
				// init part
				valueSettings = new EObjectFlatComboSettings(qualifierValue, UMLPackage.eINSTANCE.getQualifierValue_Value());
				basePart.initValue(valueSettings);
				// set the button mode
				basePart.setValueButtonMode(ButtonsModeEnum.BROWSE);
			}
			// init filters
			basePart.addFilterToQualifier(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof Property);
				}
			
			});
			// Start of user code for additional businessfilters for qualifier
			// End of user code
			
			basePart.addFilterToValue(new ViewerFilter() {
			
			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof InputPin);
				}
			
			});
			// Start of user code for additional businessfilters for value
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
		if (editorKey == UmlViewsRepository.QualifierValue.Properties.qualifier) {
			return UMLPackage.eINSTANCE.getQualifierValue_Qualifier();
		}
		if (editorKey == UmlViewsRepository.QualifierValue.Properties.value) {
			return UMLPackage.eINSTANCE.getQualifierValue_Value();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		QualifierValue qualifierValue = (QualifierValue)semanticObject;
		if (UmlViewsRepository.QualifierValue.Properties.qualifier == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				qualifierSettings.setToReference((Property)event.getNewValue());
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
				qualifierSettings.setToReference(eObject);
			}
		}
		if (UmlViewsRepository.QualifierValue.Properties.value == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				valueSettings.setToReference((InputPin)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				InputPin eObject = UMLFactory.eINSTANCE.createInputPin();
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, eObject, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				valueSettings.setToReference(eObject);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		if (editingPart.isVisible()) {	
			QualifierValuePropertiesEditionPart basePart = (QualifierValuePropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getQualifierValue_Qualifier().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.QualifierValue.Properties.qualifier))
				basePart.setQualifier((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getQualifierValue_Value().equals(msg.getFeature()) && basePart != null && isAccessible(UmlViewsRepository.QualifierValue.Properties.value))
				basePart.setValue((EObject)msg.getNewValue());
			
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object, int)
	 * 
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.QualifierValue.Properties.qualifier || key == UmlViewsRepository.QualifierValue.Properties.value;
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
			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}

}
