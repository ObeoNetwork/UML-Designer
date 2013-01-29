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

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;

import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;

import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;

import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import org.obeonetwork.dsl.uml2.properties.uml.components.PackageDocumentationPropertiesEditionComponent;

import org.obeonetwork.dsl.uml2.properties.uml.parts.DocumentationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class DocumentationPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, DocumentationPropertiesEditionPart {

	protected Text comment;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public DocumentationPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
    super(editionComponent);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createFigure(org.eclipse.swt.widgets.Composite)
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
	 * 			createControls(org.eclipse.swt.widgets.Composite)
	 * @generated
	 */
	public void createControls(Composite view) { 
    CompositionSequence documentationStep = new BindingCompositionSequence(propertiesEditionComponent);
    documentationStep.addStep(UmlViewsRepository.Documentation.comment);
    
    composer = new PartComposer(documentationStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.Documentation.comment) {
          return createCommentTextarea(parent);
        }
        return parent;
      }
    };
    composer.compose(view);
  }

	/**
	 * @generated
	 */
	
	protected Composite createCommentTextarea(Composite parent) {
    Label commentLabel = createDescription(parent, UmlViewsRepository.Documentation.comment, UmlMessages.DocumentationPropertiesEditionPart_CommentLabel);
    GridData commentLabelData = new GridData(GridData.FILL_HORIZONTAL);
    commentLabelData.horizontalSpan = 3;
    commentLabel.setLayoutData(commentLabelData);
    comment = SWTUtils.createScrollableText(parent, SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL);
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
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(DocumentationPropertiesEditionPartImpl.this, UmlViewsRepository.Documentation.comment, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, comment.getText()));
      }

    });
    EditingUtils.setID(comment, UmlViewsRepository.Documentation.comment);
    EditingUtils.setEEFtype(comment, "eef::Textarea"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Documentation.comment, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.DocumentationPropertiesEditionPart#setComment(String newValue)
	 * @generated
	 */
	public void setComment(String newValue) {
    if (newValue != null) {
      comment.setText(newValue);
    } else {
      comment.setText(""); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.Documentation.comment, ((PackageDocumentationPropertiesEditionComponent) propertiesEditionComponent).getCommentSettings().getOrCreateSignificantObject());
    if (eefElementEditorReadOnlyState && comment.isEnabled()) {
      comment.setEnabled(false);
      comment.setBackground(comment.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
      comment.setToolTipText(UmlMessages.Documentation_ReadOnly);
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
    return UmlMessages.Documentation_Part_Title;
  }



}
