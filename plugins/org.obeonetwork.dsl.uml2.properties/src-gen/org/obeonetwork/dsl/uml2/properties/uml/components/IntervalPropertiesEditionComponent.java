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

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Interval;
import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.runtime.api.notify.EStructuralFeatureNotificationFilter;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.VisibilityKind;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.Diagnostician;

import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.uml2.types.TypesPackage;

import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class IntervalPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

    /**
     * @generated
     */

    public static String GENERAL_PART = "General"; //$NON-NLS-1$

    /**
     * Settings for min LinkEReferenceViewer
     */
    private EObjectFlatComboSettings minSettings;

    /**
     * Creation Settings for min LinkEReferenceViewer
     */
    private ReferencesTableSettings minCreateSettings;

    /**
     * Settings for max LinkEReferenceViewer
     */
    private EObjectFlatComboSettings maxSettings;

    /**
     * Creation Settings for max LinkEReferenceViewer
     */
    private ReferencesTableSettings maxCreateSettings;

    /**
     * Default constructor
     * 
     * @generated
     */
    public IntervalPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject interval, String editing_mode) {
        super(editingContext, interval, editing_mode);
        parts = new String[] { GENERAL_PART };
        repositoryKey = UmlViewsRepository.class;
        partKey = UmlViewsRepository.General.class;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object,
     *      int, org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.resource.ResourceSet)
     * @generated
     */
    public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
        setInitializing(true);
        if (editingPart != null && key == partKey) {
            editingPart.setContext(elt, allResource);

            final Interval interval = (Interval) elt;
            final GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart) editingPart;
            // init values
            if (isAccessible(UmlViewsRepository.General.name))
                generalPart.setName(EEFConverterUtil.convertToString(TypesPackage.Literals.STRING, interval.getName()));

            if (isAccessible(UmlViewsRepository.General.visibility)) {
                generalPart.initVisibility(EEFUtils.choiceOfValues(interval, UMLPackage.eINSTANCE.getNamedElement_Visibility()), interval.getVisibility());
            }
            if (isAccessible(UmlViewsRepository.General.min)) {
                // init part
                minSettings = new EObjectFlatComboSettings(interval, UMLPackage.eINSTANCE.getInterval_Min());
                minCreateSettings = new ReferencesTableSettings(getminCreateSettingsSource(), UMLPackage.eINSTANCE.getPackage_PackagedElement());
                generalPart.initMin(minSettings);
                // set the button mode
                generalPart.setMinButtonMode(ButtonsModeEnum.BROWSE);
            }
            if (isAccessible(UmlViewsRepository.General.max)) {
                // init part
                maxSettings = new EObjectFlatComboSettings(interval, UMLPackage.eINSTANCE.getInterval_Max());
                maxCreateSettings = new ReferencesTableSettings(getmaxCreateSettingsSource(), UMLPackage.eINSTANCE.getPackage_PackagedElement());
                generalPart.initMax(maxSettings);
                // set the button mode
                generalPart.setMaxButtonMode(ButtonsModeEnum.BROWSE);
            }
            // init filters

            // init values for referenced views

            // init filters for referenced views

        }
        setInitializing(false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
     */
    public EStructuralFeature associatedFeature(Object editorKey) {
        if (editorKey == UmlViewsRepository.General.name) {
            return UMLPackage.eINSTANCE.getNamedElement_Name();
        }
        if (editorKey == UmlViewsRepository.General.visibility) {
            return UMLPackage.eINSTANCE.getNamedElement_Visibility();
        }
        if (editorKey == UmlViewsRepository.General.min) {
            return UMLPackage.eINSTANCE.getInterval_Min();
        }
        if (editorKey == UmlViewsRepository.General.max) {
            return UMLPackage.eINSTANCE.getInterval_Max();
        }
        return super.associatedFeature(editorKey);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
     * @generated
     */
    public void updateSemanticModel(final IPropertiesEditionEvent event) {
        Interval interval = (Interval) semanticObject;
        if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
            interval.setName((java.lang.String) EEFConverterUtil.createFromString(TypesPackage.Literals.STRING, (String) event.getNewValue()));
        }
        if (UmlViewsRepository.General.visibility == event.getAffectedEditor()) {
            interval.setVisibility((VisibilityKind) event.getNewValue());
        }
        if (UmlViewsRepository.General.min == event.getAffectedEditor()) {
            if (event.getKind() == PropertiesEditionEvent.SET) {
                minSettings.setToReference((ValueSpecification) event.getNewValue());
            } else if (event.getKind() == PropertiesEditionEvent.EDIT) {
                EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, (EObject) event.getNewValue(), editingContext.getAdapterFactory());
                PropertiesEditingProvider provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt((EObject) event.getNewValue(), PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
                    if (editionPolicy != null) {
                        editionPolicy.execute();
                    }
                }
            } else if (event.getKind() == PropertiesEditionEvent.ADD) {
                EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, minSettings, editingContext.getAdapterFactory());
                PropertiesEditingProvider provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy policy = provider.getPolicy(context);
                    if (policy instanceof CreateEditingPolicy) {
                        policy.execute();
                    }
                }
            }
        }
        if (UmlViewsRepository.General.max == event.getAffectedEditor()) {
            if (event.getKind() == PropertiesEditionEvent.SET) {
                maxSettings.setToReference((ValueSpecification) event.getNewValue());
            } else if (event.getKind() == PropertiesEditionEvent.EDIT) {
                EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, (EObject) event.getNewValue(), editingContext.getAdapterFactory());
                PropertiesEditingProvider provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt((EObject) event.getNewValue(), PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
                    if (editionPolicy != null) {
                        editionPolicy.execute();
                    }
                }
            } else if (event.getKind() == PropertiesEditionEvent.ADD) {
                EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, maxSettings, editingContext.getAdapterFactory());
                PropertiesEditingProvider provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy policy = provider.getPolicy(context);
                    if (policy instanceof CreateEditingPolicy) {
                        policy.execute();
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
     */
    public void updatePart(Notification msg) {
        super.updatePart(msg);
        if (editingPart.isVisible()) {
            GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart) editingPart;
            if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null
                    && isAccessible(UmlViewsRepository.General.name)) {
                if (msg.getNewValue() != null) {
                    generalPart.setName(EcoreUtil.convertToString(TypesPackage.Literals.STRING, msg.getNewValue()));
                } else {
                    generalPart.setName("");
                }
            }
            if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.General.visibility))
                generalPart.setVisibility((VisibilityKind) msg.getNewValue());

            if (UMLPackage.eINSTANCE.getInterval_Min().equals(msg.getFeature()) && generalPart != null && isAccessible(UmlViewsRepository.General.min))
                generalPart.setMin((EObject) msg.getNewValue());
            if (UMLPackage.eINSTANCE.getInterval_Max().equals(msg.getFeature()) && generalPart != null && isAccessible(UmlViewsRepository.General.max))
                generalPart.setMax((EObject) msg.getNewValue());

        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
     */
    @Override
    protected NotificationFilter[] getNotificationFilters() {
        NotificationFilter filter = new EStructuralFeatureNotificationFilter(UMLPackage.eINSTANCE.getNamedElement_Name(), UMLPackage.eINSTANCE.getNamedElement_Visibility(),
                UMLPackage.eINSTANCE.getInterval_Min(), UMLPackage.eINSTANCE.getInterval_Max());
        return new NotificationFilter[] { filter, };
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#mustBeComposed(java.lang.Object,
     *      int)
     */
    public boolean mustBeComposed(Object key, int kind) {
        return key == UmlViewsRepository.General.name || key == UmlViewsRepository.General.visibility || key == UmlViewsRepository.General.min || key == UmlViewsRepository.General.max;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object,
     *      int)
     * @generated
     */
    public boolean isRequired(Object key, int kind) {
        return key == UmlViewsRepository.General.min || key == UmlViewsRepository.General.max;
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
                if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
                    Object newValue = event.getNewValue();
                    if (newValue instanceof String) {
                        newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String) newValue);
                    }
                    ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
                }
                if (UmlViewsRepository.General.visibility == event.getAffectedEditor()) {
                    Object newValue = event.getNewValue();
                    if (newValue instanceof String) {
                        newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String) newValue);
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

    /**
     * @ return source setting for minCreateSettings
     */
    public EObject getminCreateSettingsSource() {
        return org.obeonetwork.dsl.uml2.properties.service.EEFService.getParent(semanticObject);
    }

    /**
     * @ return source setting for maxCreateSettings
     */
    public EObject getmaxCreateSettingsSource() {
        return org.obeonetwork.dsl.uml2.properties.service.EEFService.getParent(semanticObject);
    }

}
