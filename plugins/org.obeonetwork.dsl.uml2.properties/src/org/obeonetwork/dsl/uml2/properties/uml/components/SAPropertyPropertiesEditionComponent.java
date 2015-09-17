/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.components;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.runtime.api.notify.EStructuralFeatureNotificationFilter;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.obeonetwork.dsl.uml2.properties.uml.components.StereotypeApplicationsPropertiesEditionComponent.SAPropertyContext;
import org.obeonetwork.dsl.uml2.properties.uml.components.StereotypeApplicationsPropertiesEditionComponent.StereotypeApplication.StereotypeApplicationProperty;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.SAPropertyPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

/**
 * @author <a href="mailto:cedric.notot@obeo.fr">Cédric Notot</a>
 */
public class SAPropertyPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

    public static String SA_PROPERTY_PART = "SAProperty"; //$NON-NLS-1$

    protected StereotypeApplicationProperty property;

    /**
     * Default constructor
     */
    public SAPropertyPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject class_, String editing_mode) {
        super(editingContext, class_, editing_mode);
        parts = new String[] { SA_PROPERTY_PART };
        repositoryKey = UmlViewsRepository.class;
        partKey = CustomUmlViewsRepository.SAProperty.class;
        if (editingContext.getParentContext() instanceof SAPropertyContext) {
            property = ((SAPropertyContext) editingContext.getParentContext()).getStereotypeApplicationProperty();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object,
     *      int, org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.resource.ResourceSet)
     */
    public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
        setInitializing(true);
        if (editingPart != null && key == partKey) {
            editingPart.setContext(elt, allResource);

            final SAPropertyPropertiesEditionPart saPropertyPart = (SAPropertyPropertiesEditionPart) editingPart;
            // init values
            if (isAccessible(CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty)) {
                saPropertyPart.setValue(property.getValue());
            }
        }
        setInitializing(false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
     */
    public EStructuralFeature associatedFeature(Object editorKey) {
        if (editorKey == CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty) {
            return property.getFeature();
        }
        return super.associatedFeature(editorKey);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
     */
    public void updateSemanticModel(final IPropertiesEditionEvent event) {
        if (CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty == event.getAffectedEditor()) {
            if (event.getNewValue() instanceof String) {
                // SINGLE ATTRIBUTE
                property.getContainer().eSet(property.getFeature(), transformStringValues((String) event.getNewValue(), property));
            } else if (event.getNewValue() instanceof Boolean) {
                // BOOLEAN ATTRIBUTE
                property.getContainer().eSet(property.getFeature(), event.getNewValue());
            } else if (event.getNewValue() instanceof EEnumLiteral) {
                // ENUM ATTRIBUTE
                property.getContainer().eSet(property.getFeature(), event.getNewValue());
            } else if (property.getFeature().isMany() && property.getFeature() instanceof EReference && !((EReference) property.getFeature()).isContainment()) {
                // MANY REFERENCES
                if (event.getKind() == PropertiesEditionEvent.ADD) {
                    List<Object> result = (List<Object>) property.getContainer().eGet(property.getFeature(), true);
                    result.add(event.getNewValue());
                } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
                    EcoreUtil.remove(property.getContainer(), property.getFeature(), event.getNewValue());
                }
            } else if (!property.getFeature().isMany() && property.getFeature() instanceof EReference && !((EReference) property.getFeature()).isContainment()) {
                // SINGLE REFERENCE
                if (event.getKind() == PropertiesEditionEvent.SET && event.getNewValue() != null) {
                    property.getContainer().eSet(property.getFeature(), event.getNewValue());
                } else if (event.getKind() == PropertiesEditionEvent.SET && event.getNewValue() == null) {
                    property.getContainer().eSet(property.getFeature(), null);
                }
            } else if (!property.getFeature().isMany() && property.getFeature() instanceof EReference && ((EReference) property.getFeature()).isContainment()
                    && property.getFeature().getEType() instanceof EClass) {
                // SINGLE CONTAINMENT
                if (event.getKind() == PropertiesEditionEvent.ADD) {
                    if (property.getValue() != null) {
                        EcoreUtil.delete((EObject) property.getValue(), true);
                    }
                    EObject value = property.getFeature().getEType().getEPackage().getEFactoryInstance().create((EClass) property.getFeature().getEType());
                    property.getContainer().eSet(property.getFeature(), value);
                } else if (event.getKind() == PropertiesEditionEvent.SET && event.getNewValue() == null) {
                    EcoreUtil.delete((EObject) property.getValue(), true);
                }
            } else if (property.getFeature().isMany() && property.getFeature() instanceof EReference && ((EReference) property.getFeature()).isContainment()
                    && property.getFeature().getEType() instanceof EClass) {
                // MANY CONTAINMENTS
                if (event.getKind() == PropertiesEditionEvent.ADD) {
                    EObject value = property.getFeature().getEType().getEPackage().getEFactoryInstance().create((EClass) property.getFeature().getEType());
                    List<Object> result = (List<Object>) property.getContainer().eGet(property.getFeature(), true);
                    result.add(value);
                } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
                    EcoreUtil.delete((EObject) event.getNewValue(), true);
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
            SAPropertyPropertiesEditionPart saPropertyPart = (SAPropertyPropertiesEditionPart) editingPart;
            if (property.getFeature().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && saPropertyPart != null
                    && isAccessible(CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty)) {
                saPropertyPart.setValue(msg.getNewValue());
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
     */
    @Override
    protected NotificationFilter[] getNotificationFilters() {
        NotificationFilter filter = new EStructuralFeatureNotificationFilter(property.getFeature());
        return new NotificationFilter[] { filter };
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object,
     *      int)
     */
    public boolean isRequired(Object key, int kind) {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
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

    /**
     * @param value
     * @param property
     * @return
     */
    private static Object transformStringValues(String value, StereotypeApplicationProperty property) {
        Object result = value;
        if (!property.getFeature().isMany()) {
            result = transformStringValue(value, property);
        } else {
            String[] values = value.split(" ");
            result = new ArrayList<Object>();
            for (String val : values) {
                ((List) result).add(transformStringValue(val, property));
            }
        }
        return result;
    }

    /**
     * @param value
     * @param property
     * @return
     */
    private static Object transformStringValue(String value, StereotypeApplicationProperty property) {
        String result = value;
        if (value == null || value.trim().equals("")) {
            result = "0";
        }
        if (property.getFeature().getEType() instanceof EDataType) {
            return property.getFeature().getEType().getEPackage().getEFactoryInstance().createFromString((EDataType) property.getFeature().getEType(), result);
        } else {
            // Some cases otherwise... but we should not be here.
            EClassifier eType = property.getFeature().getEType();
            Class<?> instanceClass = eType.getInstanceClass();
            Class<?> dataClass = EcoreUtil.wrapperClassFor(instanceClass);

            if (dataClass.isAssignableFrom(Integer.class)) {
                return Integer.parseInt(result);
            }
            if (dataClass.isAssignableFrom(Double.class)) {
                return Double.parseDouble(result);
            }
            if (dataClass.isAssignableFrom(Long.class)) {
                return Long.parseLong(result);
            }
            if (dataClass.isAssignableFrom(Boolean.class)) {
                return Boolean.parseBoolean(result);
            }
            return result;
        }
    }

}
