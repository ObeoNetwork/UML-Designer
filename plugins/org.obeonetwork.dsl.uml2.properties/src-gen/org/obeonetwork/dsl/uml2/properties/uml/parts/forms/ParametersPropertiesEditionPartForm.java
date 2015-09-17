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
package org.obeonetwork.dsl.uml2.properties.uml.parts.forms;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.part.impl.SectionPropertiesEditingPart;

import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;

import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import org.obeonetwork.dsl.uml2.properties.parts.CustomSectionPropertiesEditingPart;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ParametersPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ParametersPropertiesEditionPartForm extends CustomSectionPropertiesEditingPart implements IFormPropertiesEditionPart, ParametersPropertiesEditionPart {

    protected ReferencesTable parameters;

    protected List<ViewerFilter> parametersBusinessFilters = new ArrayList<ViewerFilter>();

    protected List<ViewerFilter> parametersFilters = new ArrayList<ViewerFilter>();

    /**
     * For {@link ISection} use only.
     */
    public ParametersPropertiesEditionPartForm() {
        super();
    }

    /**
     * Default constructor
     * 
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     * @generated
     */
    public ParametersPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
        super(editionComponent);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
     *      createFigure(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.forms.widgets.FormToolkit)
     * @generated
     */
    public Composite createFigure(final Composite parent, final FormToolkit widgetFactory) {
        ScrolledForm scrolledForm = widgetFactory.createScrolledForm(parent);
        Form form = scrolledForm.getForm();
        view = form.getBody();
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        view.setLayout(layout);
        createControls(widgetFactory, view);
        return scrolledForm;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
     *      createControls(org.eclipse.ui.forms.widgets.FormToolkit,
     *      org.eclipse.swt.widgets.Composite)
     * @generated
     */
    public void createControls(final FormToolkit widgetFactory, Composite view) {
        CompositionSequence parametersStep = new BindingCompositionSequence(propertiesEditionComponent);
        parametersStep.addStep(UmlViewsRepository.Parameters.parameters_);

        composer = new PartComposer(parametersStep) {

            @Override
            public Composite addToPart(Composite parent, Object key) {
                if (key == UmlViewsRepository.Parameters.parameters_) {
                    return createParametersTableComposition(widgetFactory, parent);
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
    protected Composite createParametersTableComposition(FormToolkit widgetFactory, Composite parent) {
        this.parameters = new ReferencesTable(getDescription(UmlViewsRepository.Parameters.parameters_, UmlMessages.ParametersPropertiesEditionPart_ParametersLabel), new ReferencesTableListener() {
            public void handleAdd() {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParametersPropertiesEditionPartForm.this, UmlViewsRepository.Parameters.parameters_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
                parameters.refresh();
            }

            public void handleEdit(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParametersPropertiesEditionPartForm.this, UmlViewsRepository.Parameters.parameters_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
                parameters.refresh();
            }

            public void handleMove(EObject element, int oldIndex, int newIndex) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParametersPropertiesEditionPartForm.this, UmlViewsRepository.Parameters.parameters_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
                parameters.refresh();
            }

            public void handleRemove(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParametersPropertiesEditionPartForm.this, UmlViewsRepository.Parameters.parameters_,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
                parameters.refresh();
            }

            public void navigateTo(EObject element) {
            }
        });
        for (ViewerFilter filter : this.parametersFilters) {
            this.parameters.addFilter(filter);
        }
        this.parameters.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Parameters.parameters_, UmlViewsRepository.FORM_KIND));
        this.parameters.createControls(parent, widgetFactory);
        this.parameters.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                if (e.item != null && e.item.getData() instanceof EObject) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ParametersPropertiesEditionPartForm.this, UmlViewsRepository.Parameters.parameters_,
                            PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
                }
            }

        });
        GridData parametersData = new GridData(GridData.FILL_HORIZONTAL);
        parametersData.horizontalSpan = 3;
        this.parameters.setLayoutData(parametersData);
        this.parameters.setLowerBound(0);
        this.parameters.setUpperBound(-1);
        parameters.setID(UmlViewsRepository.Parameters.parameters_);
        parameters.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
        // Start of user code for createParametersTableComposition

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
     * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParametersPropertiesEditionPart#initParameters(EObject
     *      current, EReference containingFeature, EReference feature)
     */
    public void initParameters(ReferencesTableSettings settings) {
        if (current.eResource() != null && current.eResource().getResourceSet() != null)
            this.resourceSet = current.eResource().getResourceSet();
        ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
        parameters.setContentProvider(contentProvider);
        parameters.setInput(settings);
        boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.Parameters.parameters_);
        if (eefElementEditorReadOnlyState && parameters.isEnabled()) {
            parameters.setEnabled(false);
            parameters.setToolTipText(UmlMessages.Parameters_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !parameters.isEnabled()) {
            parameters.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParametersPropertiesEditionPart#updateParameters()
     * @generated
     */
    public void updateParameters() {
        parameters.refresh();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParametersPropertiesEditionPart#addFilterParameters(ViewerFilter
     *      filter)
     * @generated
     */
    public void addFilterToParameters(ViewerFilter filter) {
        parametersFilters.add(filter);
        if (this.parameters != null) {
            this.parameters.addFilter(filter);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParametersPropertiesEditionPart#addBusinessFilterParameters(ViewerFilter
     *      filter)
     * @generated
     */
    public void addBusinessFilterToParameters(ViewerFilter filter) {
        parametersBusinessFilters.add(filter);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ParametersPropertiesEditionPart#isContainedInParametersTable(EObject
     *      element)
     * @generated
     */
    public boolean isContainedInParametersTable(EObject element) {
        return ((ReferencesTableSettings) parameters.getInput()).contains(element);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
     * @generated
     */
    public String getTitle() {
        return UmlMessages.Parameters_Part_Title;
    }

}
