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
package org.obeonetwork.dsl.uml2.properties.uml.parts.forms;

// Start of user code for imports
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;
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
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class CommentPropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, CommentPropertiesEditionPart {

	protected Text body;
		protected ReferencesTable annotatedElement;
		protected List<ViewerFilter> annotatedElementBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> annotatedElementFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public CommentPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
	 *  createFigure(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 * 
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
	 *  createControls(org.eclipse.ui.forms.widgets.FormToolkit, org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public void createControls(final FormToolkit widgetFactory, Composite view) {
		CompositionSequence commentStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = commentStep.addStep(UmlViewsRepository.Comment.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.Comment.Properties.body);
		propertiesStep.addStep(UmlViewsRepository.Comment.Properties.annotatedElement);
		
		
		composer = new PartComposer(commentStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.Comment.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Comment.Properties.body) {
					return 		createBodyText(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Comment.Properties.annotatedElement) {
					return createAnnotatedElementReferencesTable(widgetFactory, parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}
	/**
	 * 
	 */
	protected Composite createPropertiesGroup(FormToolkit widgetFactory, final Composite parent) {
		Section propertiesSection = widgetFactory.createSection(parent, Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		propertiesSection.setText(UmlMessages.CommentPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesSectionData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesSectionData.horizontalSpan = 3;
		propertiesSection.setLayoutData(propertiesSectionData);
		Composite propertiesGroup = widgetFactory.createComposite(propertiesSection);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		propertiesSection.setClient(propertiesGroup);
		return propertiesGroup;
	}

	
	protected Composite createBodyText(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.CommentPropertiesEditionPart_BodyLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Comment.Properties.body, UmlViewsRepository.FORM_KIND));
		body = widgetFactory.createText(parent, ""); //$NON-NLS-1$
		body.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		widgetFactory.paintBordersFor(parent);
		GridData bodyData = new GridData(GridData.FILL_HORIZONTAL);
		body.setLayoutData(bodyData);
		body.addFocusListener(new FocusAdapter() {
			/**
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CommentPropertiesEditionPartForm.this, UmlViewsRepository.Comment.Properties.body, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, body.getText()));
			}
		});
		body.addKeyListener(new KeyAdapter() {
			/**
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CommentPropertiesEditionPartForm.this, UmlViewsRepository.Comment.Properties.body, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, body.getText()));
				}
			}
		});
		EditingUtils.setID(body, UmlViewsRepository.Comment.Properties.body);
		EditingUtils.setEEFtype(body, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Comment.Properties.body, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createAnnotatedElementReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.annotatedElement = new ReferencesTable(UmlMessages.CommentPropertiesEditionPart_AnnotatedElementLabel, new ReferencesTableListener	() {
			public void handleAdd() { addAnnotatedElement(); }
			public void handleEdit(EObject element) { editAnnotatedElement(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveAnnotatedElement(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromAnnotatedElement(element); }
			public void navigateTo(EObject element) { }
		});
		this.annotatedElement.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Comment.Properties.annotatedElement, UmlViewsRepository.FORM_KIND));
		this.annotatedElement.createControls(parent, widgetFactory);
		this.annotatedElement.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CommentPropertiesEditionPartForm.this, UmlViewsRepository.Comment.Properties.annotatedElement, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
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
	 * 
	 */
	protected void addAnnotatedElement() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(annotatedElement.getInput(), annotatedElementFilters, annotatedElementBusinessFilters,
		"annotatedElement", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CommentPropertiesEditionPartForm.this, UmlViewsRepository.Comment.Properties.annotatedElement,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				annotatedElement.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveAnnotatedElement(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CommentPropertiesEditionPartForm.this, UmlViewsRepository.Comment.Properties.annotatedElement, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		annotatedElement.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromAnnotatedElement(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CommentPropertiesEditionPartForm.this, UmlViewsRepository.Comment.Properties.annotatedElement, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		annotatedElement.refresh();
	}

	/**
	 * 
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
	 * 
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
		// Start of user code for tab synchronization
		
		// End of user code
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart#getBody()
	 * 
	 */
	public String getBody() {
		return body.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart#setBody(String newValue)
	 * 
	 */
	public void setBody(String newValue) {
		if (newValue != null) {
			body.setText(newValue);
		} else {
			body.setText(""); //$NON-NLS-1$
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
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart#updateAnnotatedElement()
	 * 
	 */
	public void updateAnnotatedElement() {
	annotatedElement.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart#addFilterAnnotatedElement(ViewerFilter filter)
	 * 
	 */
	public void addFilterToAnnotatedElement(ViewerFilter filter) {
		annotatedElementFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart#addBusinessFilterAnnotatedElement(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToAnnotatedElement(ViewerFilter filter) {
		annotatedElementBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.CommentPropertiesEditionPart#isContainedInAnnotatedElementTable(EObject element)
	 * 
	 */
	public boolean isContainedInAnnotatedElementTable(EObject element) {
		return ((ReferencesTableSettings)annotatedElement.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.Comment_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
