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

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.obeonetwork.dsl.uml2.properties.parts.CustomSectionPropertiesEditingPart;
import org.obeonetwork.dsl.uml2.properties.uml.components.ElementDocumentationPropertiesEditionComponent;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.DocumentationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.CustomUmlMessages;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class DocumentationPropertiesEditionPartForm extends CustomSectionPropertiesEditingPart implements IFormPropertiesEditionPart, DocumentationPropertiesEditionPart {

    protected Text comment;

    /**
     * For {@link ISection} use only.
     */
    public DocumentationPropertiesEditionPartForm() {
        super();
    }

    /**
     * Default constructor
     * 
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     * @generated
     */
    public DocumentationPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
        CompositionSequence documentationStep = new BindingCompositionSequence(propertiesEditionComponent);
        documentationStep.addStep(CustomUmlViewsRepository.Documentation.comment);

        composer = new PartComposer(documentationStep) {

            @Override
            public Composite addToPart(Composite parent, Object key) {
                if (key == CustomUmlViewsRepository.Documentation.comment) {
                    return createCommentTextarea(widgetFactory, parent);
                }
                return parent;
            }
        };
        composer.compose(view);
    }

    /**
     * @generated
     */

    protected Composite createCommentTextarea(FormToolkit widgetFactory, Composite parent) {
        Label commentLabel = createDescription(parent, CustomUmlViewsRepository.Documentation.comment, CustomUmlMessages.DocumentationPropertiesEditionPart_CommentLabel);
        GridData commentLabelData = new GridData(GridData.FILL_HORIZONTAL);
        commentLabelData.horizontalSpan = 3;
        commentLabel.setLayoutData(commentLabelData);
        comment = widgetFactory.createText(parent, "", SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL); //$NON-NLS-1$
        GridData commentData = new GridData(GridData.FILL_HORIZONTAL);
        commentData.horizontalSpan = 2;
        commentData.heightHint = 80;
        commentData.widthHint = 200;
        comment.setLayoutData(commentData);
        comment.addFocusListener(new FocusAdapter() {

            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
             * @generated
             */
            public void focusLost(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DocumentationPropertiesEditionPartForm.this, CustomUmlViewsRepository.Documentation.comment,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, comment.getText()));
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DocumentationPropertiesEditionPartForm.this, CustomUmlViewsRepository.Documentation.comment,
                            PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_LOST, null, comment.getText()));
                }
            }

            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
             */
            @Override
            public void focusGained(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DocumentationPropertiesEditionPartForm.this, null, PropertiesEditionEvent.FOCUS_CHANGED,
                            PropertiesEditionEvent.FOCUS_GAINED, null, null));
                }
            }
        });
        EditingUtils.setID(comment, CustomUmlViewsRepository.Documentation.comment);
        EditingUtils.setEEFtype(comment, "eef::Textarea"); //$NON-NLS-1$
        FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(CustomUmlViewsRepository.Documentation.comment, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
        // Start of user code for createCommentTextArea

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
     * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DocumentationPropertiesEditionPart#getComment()
     * @generated
     */
    public String getComment() {
        return comment.getText();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DocumentationPropertiesEditionPart#setComment(String
     *      newValue)
     * @generated
     */
    public void setComment(String newValue) {
        if (newValue != null) {
            comment.setText(newValue);
        } else {
            comment.setText(""); //$NON-NLS-1$
        }
        boolean eefElementEditorReadOnlyState = isReadOnly(CustomUmlViewsRepository.Documentation.comment, ((ElementDocumentationPropertiesEditionComponent) propertiesEditionComponent)
                .getCommentSettings().getOrCreateSignificantObject());
        if (eefElementEditorReadOnlyState && comment.isEnabled()) {
            comment.setEnabled(false);
            comment.setBackground(comment.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
            comment.setToolTipText(CustomUmlMessages.Documentation_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !comment.isEnabled()) {
            comment.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
     * @generated
     */
    public String getTitle() {
        return CustomUmlMessages.Documentation_Part_Title;
    }

}
