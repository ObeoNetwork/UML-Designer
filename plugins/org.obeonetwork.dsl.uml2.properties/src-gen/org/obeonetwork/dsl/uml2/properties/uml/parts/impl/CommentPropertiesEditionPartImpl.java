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
package org.obeonetwork.dsl.uml2.properties.uml.parts.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;

import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;

import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 * @generated
 */
public class CommentPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, CommentPropertiesEditionPart {

	protected Text body;
	protected ReferencesTable annotatedElement;
	protected List<ViewerFilter> annotatedElementBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> annotatedElementFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public CommentPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence commentStep = new BindingCompositionSequence(propertiesEditionComponent);
    CompositionStep propertiesStep = commentStep.addStep(UmlViewsRepository.Comment.Properties.class);
    propertiesStep.addStep(UmlViewsRepository.Comment.Properties.body);
    propertiesStep.addStep(UmlViewsRepository.Comment.Properties.annotatedElement);
    
    
    composer = new PartComposer(commentStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.Comment.Properties.class) {
          return createPropertiesGroup(parent);
        }
        if (key == UmlViewsRepository.Comment.Properties.body) {
          return createBodyText(parent);
        }
        if (key == UmlViewsRepository.Comment.Properties.annotatedElement) {
          return createAnnotatedElementAdvancedReferencesTable(parent);
        }
        return parent;
      }
    };
    composer.compose(view);
  }

	/**
	 * @generated
	 */
	protected Composite createPropertiesGroup(Composite parent) {
    Group propertiesGroup = new Group(parent, SWT.NONE);
    propertiesGroup.setText(UmlMessages.CommentPropertiesEditionPart_PropertiesGroupLabel);
    GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
    propertiesGroupData.horizontalSpan = 3;
    propertiesGroup.setLayoutData(propertiesGroupData);
    GridLayout propertiesGroupLayout = new GridLayout();
    propertiesGroupLayout.numColumns = 3;
    propertiesGroup.setLayout(propertiesGroupLayout);
    return propertiesGroup;
  }

	/**
   * @generated
   */
  
  protected Composite createBodyText(Composite parent) {
    createDescription(parent, UmlViewsRepository.Comment.Properties.body, UmlMessages.CommentPropertiesEditionPart_BodyLabel);
    body = SWTUtils.createScrollableText(parent, SWT.BORDER);
    GridData bodyData = new GridData(GridData.FILL_HORIZONTAL);
    body.setLayoutData(bodyData);
    body.addFocusListener(new FocusAdapter() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
       * @generated
       */
      @Override
      @SuppressWarnings("synthetic-access")
      public void focusLost(FocusEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CommentPropertiesEditionPartImpl.this, UmlViewsRepository.Comment.Properties.body, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, body.getText()));
      }

    });
    body.addKeyListener(new KeyAdapter() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
       * @generated
       */
      @Override
      @SuppressWarnings("synthetic-access")
      public void keyPressed(KeyEvent e) {
        if (e.character == SWT.CR) {
          if (propertiesEditionComponent != null)
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CommentPropertiesEditionPartImpl.this, UmlViewsRepository.Comment.Properties.body, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, body.getText()));
        }
      }

    });
    EditingUtils.setID(body, UmlViewsRepository.Comment.Properties.body);
    EditingUtils.setEEFtype(body, "eef::Text"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Comment.Properties.body, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    return parent;
  }

  /**
	 * @generated
	 */
	protected Composite createAnnotatedElementAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.Comment.Properties.annotatedElement, UmlMessages.CommentPropertiesEditionPart_AnnotatedElementLabel);		 
    this.annotatedElement = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addAnnotatedElement(); }
      public void handleEdit(EObject element) { editAnnotatedElement(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveAnnotatedElement(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromAnnotatedElement(element); }
      public void navigateTo(EObject element) { }
    });
    this.annotatedElement.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Comment.Properties.annotatedElement, UmlViewsRepository.SWT_KIND));
    this.annotatedElement.createControls(parent);
    this.annotatedElement.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CommentPropertiesEditionPartImpl.this, UmlViewsRepository.Comment.Properties.annotatedElement, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData annotatedElementData = new GridData(GridData.FILL_HORIZONTAL);
    annotatedElementData.horizontalSpan = 3;
    this.annotatedElement.setLayoutData(annotatedElementData);
    this.annotatedElement.disableMove();
    annotatedElement.setID(UmlViewsRepository.Comment.Properties.annotatedElement);
    annotatedElement.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addAnnotatedElement() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(annotatedElement.getInput(), annotatedElementFilters, annotatedElementBusinessFilters,
    "annotatedElement", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CommentPropertiesEditionPartImpl.this, UmlViewsRepository.Comment.Properties.annotatedElement,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        annotatedElement.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveAnnotatedElement(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CommentPropertiesEditionPartImpl.this, UmlViewsRepository.Comment.Properties.annotatedElement, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    annotatedElement.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromAnnotatedElement(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CommentPropertiesEditionPartImpl.this, UmlViewsRepository.Comment.Properties.annotatedElement, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    annotatedElement.refresh();
  }

	/**
	 * @generated
	 */
	protected void editAnnotatedElement(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        annotatedElement.refresh();
      }
    }
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart#getBodies()
	 * @generated
	 */
	public String getBodies() {
    return body.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart#setBody(String newValue)
	 * @generated
	 */
	public void setBody(String newValue) {
    if (newValue != null) {
      body.setText(newValue);
    } else {
      body.setText(""); //$NON-NLS-1$
    }
    boolean readOnly = isReadOnly(UmlViewsRepository.Comment.Properties.body);
    if (readOnly && body.isEnabled()) {
      body.setEnabled(false);
      body.setToolTipText(UmlMessages.Comment_ReadOnly);
    } else if (!readOnly && !body.isEnabled()) {
      body.setEnabled(true);
    }	
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart#initAnnotatedElement(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initAnnotatedElement(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		annotatedElement.setContentProvider(contentProvider);
		annotatedElement.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Comment.Properties.annotatedElement);
		if (readOnly && annotatedElement.getTable().isEnabled()) {
			annotatedElement.setEnabled(false);
			annotatedElement.setToolTipText(UmlMessages.Comment_ReadOnly);
		} else if (!readOnly && !annotatedElement.getTable().isEnabled()) {
			annotatedElement.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart#updateAnnotatedElement()
	 * @generated
	 */
	public void updateAnnotatedElement() {
  annotatedElement.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart#addFilterAnnotatedElement(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToAnnotatedElement(ViewerFilter filter) {
    annotatedElementFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart#addBusinessFilterAnnotatedElement(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToAnnotatedElement(ViewerFilter filter) {
    annotatedElementBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart#isContainedInAnnotatedElementTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInAnnotatedElementTable(EObject element) {
    return ((ReferencesTableSettings)annotatedElement.getInput()).contains(element);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.Comment_Part_Title;
  }



}
