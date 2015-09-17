/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.components;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.OperationsTableSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.RelationshipsOriginatingTableSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.RelationshipsTargetingTableSettings;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.RelationshipsPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class NamedElementRelationshipsPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

    public static String RELATIONSHIPS_PART = "Relationships"; //$NON-NLS-1$

    /**
     * Settings for relationshipsOriginating ReferencesTable
     */
    protected ReferencesTableSettings relationshipsOriginatingSettings;

    /**
     * Settings for relationshipsTargeting ReferencesTable
     */
    protected ReferencesTableSettings relationshipsTargetingSettings;

    /**
     * Default constructor
     */
    public NamedElementRelationshipsPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject class_, String editing_mode) {
        super(editingContext, class_, editing_mode);
        parts = new String[] { RELATIONSHIPS_PART };
        repositoryKey = UmlViewsRepository.class;
        partKey = CustomUmlViewsRepository.Relationships.class;
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
            final RelationshipsPropertiesEditionPart relationshipsPart = (RelationshipsPropertiesEditionPart) editingPart;
            // init values
            if (isAccessible(CustomUmlViewsRepository.Relationships.relationshipsOriginating)) {
                relationshipsOriginatingSettings = new OperationsTableSettings(semanticObject, new RelationshipsOriginatingTableSettings(semanticObject));
                relationshipsPart.initRelationshipsOriginating((OperationsTableSettings) relationshipsOriginatingSettings);
            }
            if (isAccessible(CustomUmlViewsRepository.Relationships.relationshipsTargeting)) {
                relationshipsTargetingSettings = new OperationsTableSettings(semanticObject, new RelationshipsTargetingTableSettings(semanticObject));
                relationshipsPart.initRelationshipsTargeting((OperationsTableSettings) relationshipsTargetingSettings);
            }

            // init filters
            if (isAccessible(CustomUmlViewsRepository.Relationships.relationshipsOriginating)) {
                relationshipsPart.addFilterToRelationshipsOriginating(new EObjectFilter(UMLPackage.Literals.RELATIONSHIP));
            }
            if (isAccessible(CustomUmlViewsRepository.Relationships.relationshipsTargeting)) {
                relationshipsPart.addFilterToRelationshipsTargeting(new EObjectFilter(UMLPackage.Literals.RELATIONSHIP));
            }
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
        // TODO To implement
        // if (editorKey ==
        // CustomUmlViewsRepository.Relationships.relationshipsOriginating) {
        // return UMLPackage.eINSTANCE.getClass_SuperClass();
        // }
        // if (editorKey ==
        // CustomUmlViewsRepository.Relationships.relationshipsTargeting) {
        // return UMLPackage.eINSTANCE.getClass_SuperClass();
        // }
        return super.associatedFeature(editorKey);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
     */
    public void updateSemanticModel(final IPropertiesEditionEvent event) {
        Object newValue = event.getNewValue();
        if (CustomUmlViewsRepository.Relationships.relationshipsOriginating == event.getAffectedEditor()) {
            if (event.getKind() == PropertiesEditionEvent.ADD) {
                if (newValue instanceof NamedElement) {
                    ((OperationsTableSettings) relationshipsOriginatingSettings).addToReference((EObject) newValue);
                }
            } else if (event.getKind() == PropertiesEditionEvent.EDIT) {
                AdapterFactory adapterFactory = getEditingContext().getAdapterFactory();
                EObject element = (EObject) newValue;
                EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(getEditingContext(), this, element, getEditingContext().getAdapterFactory());
                PropertiesEditingProvider provider = (PropertiesEditingProvider) adapterFactory.adapt(element, PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy policy = provider.getPolicy(context);
                    if (policy != null) {
                        policy.execute();
                    }
                }

            } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
                if (newValue instanceof Relationship)
                    ((OperationsTableSettings) relationshipsOriginatingSettings).removeFromReference((EObject) newValue);
            }
        }
        if (CustomUmlViewsRepository.Relationships.relationshipsTargeting == event.getAffectedEditor()) {
            if (event.getKind() == PropertiesEditionEvent.ADD) {
                if (event.getNewValue() instanceof NamedElement) {
                    ((OperationsTableSettings) relationshipsTargetingSettings).addToReference((EObject) newValue);
                }
            } else if (event.getKind() == PropertiesEditionEvent.EDIT) {
                AdapterFactory adapterFactory = getEditingContext().getAdapterFactory();
                EObject element = (EObject) newValue;
                EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(getEditingContext(), this, element, getEditingContext().getAdapterFactory());
                PropertiesEditingProvider provider = (PropertiesEditingProvider) adapterFactory.adapt(element, PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy policy = provider.getPolicy(context);
                    if (policy != null) {
                        policy.execute();
                    }
                }

            } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
                ((OperationsTableSettings) relationshipsTargetingSettings).removeFromReference((EObject) newValue);
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
            RelationshipsPropertiesEditionPart relationshipsPart = (RelationshipsPropertiesEditionPart) editingPart;
            // if (UMLPackage.eINSTANCE.getClass_SuperClass().equals(
            // msg.getFeature())
            // &&
            // isAccessible(CustomUmlViewsRepository.Relationships.relationshipsOriginating))
            // relationshipsPart.updateRelationshipsOriginating();
            // if (UMLPackage.eINSTANCE.getClass_SuperClass().equals(
            // msg.getFeature())
            // &&
            // isAccessible(CustomUmlViewsRepository.Relationships.relationshipsTargeting))
            // relationshipsPart.updateRelationshipsTargeting();
            if (isAccessible(CustomUmlViewsRepository.Relationships.relationshipsOriginating))
                relationshipsPart.updateRelationshipsOriginating();
            if (isAccessible(CustomUmlViewsRepository.Relationships.relationshipsTargeting))
                relationshipsPart.updateRelationshipsTargeting();

        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
     */
    @Override
    protected NotificationFilter[] getNotificationFilters() {
        // TODO to implement
        // NotificationFilter filter = new EStructuralFeatureNotificationFilter(
        // UMLPackage.eINSTANCE.getClass_SuperClass(),
        // UMLPackage.eINSTANCE.getClass_SuperClass());
        // return new NotificationFilter[] { filter, };
        return new NotificationFilter[] {};
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object,
     *      int)
     */
    public boolean isRequired(Object key, int kind) {
        return key == UmlViewsRepository.General.Qualifiers.abstract_ || key == UmlViewsRepository.General.Qualifiers.leaf;
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

    @Override
    protected boolean shouldProcess(IPropertiesEditionEvent event) {
        return true;
    }

}
