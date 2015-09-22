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
package org.obeonetwork.dsl.uml2.properties.uml.parts.impl;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;

import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;

import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;

import org.obeonetwork.dsl.uml2.properties.uml.parts.AttributesPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class AttributesPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, AttributesPropertiesEditionPart {

    protected ReferencesTable attributes;

    protected List<ViewerFilter> attributesBusinessFilters = new ArrayList<ViewerFilter>();

    protected List<ViewerFilter> attributesFilters = new ArrayList<ViewerFilter>();

    /**
     * Default constructor
     * 
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     * @generated
     */
    public AttributesPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
        super(editionComponent);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
     *      createFigure(org.eclipse.swt.widgets.Composite)
     * @generated
     */
    public Composite createFigure(final Composite parent) {
        view = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        view.setLayout(layout);
        createControls(view);
        return view;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
     *      createControls(org.eclipse.swt.widgets.Composite)
     * @generated
     */
    public void createControls(Composite view) {
        CompositionSequence attributesStep = new BindingCompositionSequence(propertiesEditionComponent);
        attributesStep.addStep(UmlViewsRepository.Attributes.attributes_);

        composer = new PartComposer(attributesStep) {

            @Override
            public Composite addToPart(Composite parent, Object key) {
                if (key == UmlViewsRepository.Attributes.attributes_) {
                    return createAttributesAdvancedTableComposition(parent);
                }
                return parent;
            }
        };
        composer.compose(view);
    }

    /**
     * @param container
     * @generated
     */
    protected Composite createAttributesAdvancedTableComposition(Composite parent) {
        this.attributes = new ReferencesTable(getDescription(UmlViewsRepository.Attributes.attributes_, UmlMessages.AttributesPropertiesEditionPart_AttributesLabel), new ReferencesTableListener() {
            public void handleAdd() {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AttributesPropertiesEditionPartImpl.this, UmlViewsRepository.Attributes.attributes_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
                attributes.refresh();
            }

            public void handleEdit(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AttributesPropertiesEditionPartImpl.this, UmlViewsRepository.Attributes.attributes_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
                attributes.refresh();
            }

            public void handleMove(EObject element, int oldIndex, int newIndex) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AttributesPropertiesEditionPartImpl.this, UmlViewsRepository.Attributes.attributes_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
                attributes.refresh();
            }

            public void handleRemove(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AttributesPropertiesEditionPartImpl.this, UmlViewsRepository.Attributes.attributes_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
                attributes.refresh();
            }

            public void navigateTo(EObject element) {
            }
        });
        for (ViewerFilter filter : this.attributesFilters) {
            this.attributes.addFilter(filter);
        }
        this.attributes.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Attributes.attributes_, UmlViewsRepository.SWT_KIND));
        this.attributes.createControls(parent);
        this.attributes.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                if (e.item != null && e.item.getData() instanceof EObject) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AttributesPropertiesEditionPartImpl.this, UmlViewsRepository.Attributes.attributes_,
                            PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
                }
            }

        });
        GridData attributesData = new GridData(GridData.FILL_HORIZONTAL);
        attributesData.horizontalSpan = 3;
        this.attributes.setLayoutData(attributesData);
        this.attributes.setLowerBound(0);
        this.attributes.setUpperBound(-1);
        attributes.setID(UmlViewsRepository.Attributes.attributes_);
        attributes.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
        // Start of user code for createAttributesAdvancedTableComposition

        // End of user code
        return parent;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
     * @generated
     */
    public void firePropertiesChanged(IPropertiesEditionEvent event) {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AttributesPropertiesEditionPart#initAttributes(EObject
     *      current, EReference containingFeature, EReference feature)
     */
    public void initAttributes(ReferencesTableSettings settings) {
        if (current.eResource() != null && current.eResource().getResourceSet() != null)
            this.resourceSet = current.eResource().getResourceSet();
        ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
        attributes.setContentProvider(contentProvider);
        attributes.setInput(settings);
        boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.Attributes.attributes_);
        if (eefElementEditorReadOnlyState && attributes.isEnabled()) {
            attributes.setEnabled(false);
            attributes.setToolTipText(UmlMessages.Attributes_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !attributes.isEnabled()) {
            attributes.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AttributesPropertiesEditionPart#updateAttributes()
     * @generated
     */
    public void updateAttributes() {
        attributes.refresh();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AttributesPropertiesEditionPart#addFilterAttributes(ViewerFilter
     *      filter)
     * @generated
     */
    public void addFilterToAttributes(ViewerFilter filter) {
        attributesFilters.add(filter);
        if (this.attributes != null) {
            this.attributes.addFilter(filter);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AttributesPropertiesEditionPart#addBusinessFilterAttributes(ViewerFilter
     *      filter)
     * @generated
     */
    public void addBusinessFilterToAttributes(ViewerFilter filter) {
        attributesBusinessFilters.add(filter);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.dsl.uml2.properties.uml.parts.AttributesPropertiesEditionPart#isContainedInAttributesTable(EObject
     *      element)
     * @generated
     */
    public boolean isContainedInAttributesTable(EObject element) {
        return ((ReferencesTableSettings) attributes.getInput()).contains(element);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
     * @generated
     */
    public String getTitle() {
        return UmlMessages.Attributes_Part_Title;
    }

}
