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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ITableOperations;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.OperationsTableSettings;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.obeonetwork.dsl.uml2.properties.uml.components.StereotypeApplicationsPropertiesEditionComponent.StereotypeApplication.StereotypeApplicationProperty;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.StereotypeApplicationsPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

/**
 * @author <a href="mailto:cedric.notot@obeo.fr">Cédric Notot</a>
 */
public class StereotypeApplicationsPropertiesEditionComponent extends SinglePartPropertiesEditingComponent implements ITableOperations {

    public static String STEREOTYPE_APPLICATIONS_PART = "TaggedValues"; //$NON-NLS-1$

    /**
     * Settings for stereotypeApplications ReferencesTable
     */
    protected OperationsTableSettings stereotypeApplicationsSettings;

    /**
     * Default constructor
     */
    public StereotypeApplicationsPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject class_, String editing_mode) {
        super(editingContext, class_, editing_mode);
        parts = new String[] { STEREOTYPE_APPLICATIONS_PART };
        repositoryKey = CustomUmlViewsRepository.class;
        partKey = CustomUmlViewsRepository.StereotypeApplications.class;
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

            final StereotypeApplicationsPropertiesEditionPart stereotypesPart = (StereotypeApplicationsPropertiesEditionPart) editingPart;
            // init values
            if (isAccessible(CustomUmlViewsRepository.StereotypeApplications.stereotypeApplications)) {
                stereotypeApplicationsSettings = new OperationsTableSettings(semanticObject, this);

                stereotypesPart.initStereotypeApplications((OperationsTableSettings) stereotypeApplicationsSettings);
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
        // TODO To implement
        return super.associatedFeature(editorKey);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
     */
    public void updateSemanticModel(final IPropertiesEditionEvent event) {
        Object newValue = event.getNewValue();
        if (CustomUmlViewsRepository.StereotypeApplications.stereotypeApplications == event.getAffectedEditor()) {
            if (event.getKind() == PropertiesEditionEvent.EDIT) {
                StereotypeApplicationProperty element = (StereotypeApplicationProperty) newValue;

                // TODO get the related stereotype application (EObject) as
                // element
                EObject stAppli = element.getContainer();

                EObjectPropertiesEditionContext context = new SAPropertyContext(editingContext, this, (EObject) stAppli, editingContext.getAdapterFactory(), element);
                PropertiesEditingProvider provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt((EObject) stAppli, PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
                    if (editionPolicy != null) {
                        editionPolicy.execute();
                    }
                }
            }
        }
    }

    /**
     * @author <a href="mailto:cedric.notot@obeo.fr">Cédric Notot</a>
     *
     */
    public class SAPropertyContext extends EObjectPropertiesEditionContext {

        private StereotypeApplicationProperty property;

        /**
         * @param parentContext
         * @param propertiesEditionComponent
         * @param eObject
         * @param adapterFactory
         * @param property
         */
        public SAPropertyContext(PropertiesEditingContext parentContext, IPropertiesEditionComponent propertiesEditionComponent, EObject eObject, AdapterFactory adapterFactory,
                StereotypeApplicationProperty property) {
            super(parentContext, propertiesEditionComponent, eObject, adapterFactory);
            this.property = property;
        }

        /**
         * @return
         */
        public StereotypeApplicationProperty getStereotypeApplicationProperty() {
            return property;
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
            StereotypeApplicationsPropertiesEditionPart stereotypesPart = (StereotypeApplicationsPropertiesEditionPart) editingPart;
            if (isAccessible(CustomUmlViewsRepository.StereotypeApplications.stereotypeApplications))
                stereotypesPart.updateStereotypeApplications();

        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
     */
    @Override
    protected NotificationFilter[] getNotificationFilters() {
        // TODO To implement
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ITableOperations#getValues()
     */
    public Object[] getValues() {

        List<Object> result = new ArrayList<Object>();

        if (semanticObject instanceof Element) {

            for (Stereotype st : ((Element) semanticObject).getAppliedStereotypes()) {

                EObject stAppli = ((Element) semanticObject).getStereotypeApplication(st);

                StereotypeApplication bean = new StereotypeApplication((Element) semanticObject, st, stAppli);

                result.addAll(bean.getProperties());
            }

        }

        return result.isEmpty() ? new Object[] { result } : result.toArray();
    }

    /**
     * {@inheritDoc}
     */
    public void add(EObject newValue) {

    }

    /**
     * {@inheritDoc}
     */
    public void remove(EObject valueToRemove) {

    }

    /**
     * {@inheritDoc}
     */
    public Object choiceOfValues() {
        return null;
    }

    /**
     * @author <a href="mailto:cedric.notot@obeo.fr">Cédric Notot</a>
     *
     */
    public class StereotypeApplication {

        private Element stereotypedElement;

        private Stereotype stereotype;

        private EObject container;

        private List<StereotypeApplicationProperty> properties = new ArrayList<StereotypeApplicationProperty>();

        public StereotypeApplication(Element stereotypedElement, Stereotype stereotype, EObject container) {
            this.stereotypedElement = stereotypedElement;
            this.stereotype = stereotype;
            this.container = container;
            for (EStructuralFeature feature : container.eClass().getEAllStructuralFeatures()) {
                if (!feature.getName().startsWith("base_")) {
                    StereotypeApplicationProperty property = new StereotypeApplicationProperty(this, container, feature);
                    properties.add(property);
                }
            }
        }

        public Element getStereotypedElement() {
            return stereotypedElement;
        }

        public void setStereotypedElement(Element stereotypedElement) {
            this.stereotypedElement = stereotypedElement;
        }

        public Stereotype getStereotype() {
            return stereotype;
        }

        public void setStereotype(Stereotype stereotype) {
            this.stereotype = stereotype;
        }

        public EObject getContainer() {
            return container;
        }

        public void setContainer(EObject container) {
            this.container = container;
        }

        public List<StereotypeApplicationProperty> getProperties() {
            return properties;
        }

        public void setProperties(List<StereotypeApplicationProperty> properties) {
            this.properties = properties;
        }

        /**
         * @author <a href="mailto:cedric.notot@obeo.fr">Cédric Notot</a>
         *
         */
        public class StereotypeApplicationProperty {

            private StereotypeApplication stereotypeApplication;

            private EObject container;

            private EStructuralFeature feature;

            public StereotypeApplicationProperty(StereotypeApplication stereotypeApplication, EObject container, EStructuralFeature feature) {
                this.stereotypeApplication = stereotypeApplication;
                this.container = container;
                this.feature = feature;
            }

            public StereotypeApplication getStereotypeApplication() {
                return stereotypeApplication;
            }

            public EObject getContainer() {
                return container;
            }

            public Stereotype getStereotype() {
                return stereotype;
            }

            public EStructuralFeature getFeature() {
                return feature;
            }

            public Object getValue() {
                return container.eGet(feature, true);
            }

        }

    }

}
