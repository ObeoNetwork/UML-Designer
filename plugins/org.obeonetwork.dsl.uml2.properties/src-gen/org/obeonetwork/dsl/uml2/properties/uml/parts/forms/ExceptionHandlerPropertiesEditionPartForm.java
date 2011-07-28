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
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
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
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ExceptionHandlerPropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, ExceptionHandlerPropertiesEditionPart {

	protected EObjectFlatComboViewer handlerBody;
	protected EObjectFlatComboViewer exceptionInput;
		protected ReferencesTable exceptionType;
		protected List<ViewerFilter> exceptionTypeBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> exceptionTypeFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer protectedNode;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public ExceptionHandlerPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence exceptionHandlerStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = exceptionHandlerStep.addStep(UmlViewsRepository.ExceptionHandler.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.ExceptionHandler.Properties.handlerBody);
		propertiesStep.addStep(UmlViewsRepository.ExceptionHandler.Properties.exceptionInput);
		propertiesStep.addStep(UmlViewsRepository.ExceptionHandler.Properties.exceptionType);
		propertiesStep.addStep(UmlViewsRepository.ExceptionHandler.Properties.protectedNode);
		
		
		composer = new PartComposer(exceptionHandlerStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.ExceptionHandler.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ExceptionHandler.Properties.handlerBody) {
					return createHandlerBodyFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ExceptionHandler.Properties.exceptionInput) {
					return createExceptionInputFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ExceptionHandler.Properties.exceptionType) {
					return createExceptionTypeReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ExceptionHandler.Properties.protectedNode) {
					return createProtectedNodeFlatComboViewer(parent, widgetFactory);
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
		propertiesSection.setText(UmlMessages.ExceptionHandlerPropertiesEditionPart_PropertiesGroupLabel);
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

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createHandlerBodyFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ExceptionHandlerPropertiesEditionPart_HandlerBodyLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ExceptionHandler.Properties.handlerBody, UmlViewsRepository.FORM_KIND));
		handlerBody = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ExceptionHandler.Properties.handlerBody, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(handlerBody);
		handlerBody.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData handlerBodyData = new GridData(GridData.FILL_HORIZONTAL);
		handlerBody.setLayoutData(handlerBodyData);
		handlerBody.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExceptionHandlerPropertiesEditionPartForm.this, UmlViewsRepository.ExceptionHandler.Properties.handlerBody, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getHandlerBody()));
			}

		});
		handlerBody.setID(UmlViewsRepository.ExceptionHandler.Properties.handlerBody);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExceptionHandler.Properties.handlerBody, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createExceptionInputFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ExceptionHandlerPropertiesEditionPart_ExceptionInputLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ExceptionHandler.Properties.exceptionInput, UmlViewsRepository.FORM_KIND));
		exceptionInput = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ExceptionHandler.Properties.exceptionInput, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(exceptionInput);
		exceptionInput.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData exceptionInputData = new GridData(GridData.FILL_HORIZONTAL);
		exceptionInput.setLayoutData(exceptionInputData);
		exceptionInput.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExceptionHandlerPropertiesEditionPartForm.this, UmlViewsRepository.ExceptionHandler.Properties.exceptionInput, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getExceptionInput()));
			}

		});
		exceptionInput.setID(UmlViewsRepository.ExceptionHandler.Properties.exceptionInput);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExceptionHandler.Properties.exceptionInput, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createExceptionTypeReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.exceptionType = new ReferencesTable(UmlMessages.ExceptionHandlerPropertiesEditionPart_ExceptionTypeLabel, new ReferencesTableListener	() {
			public void handleAdd() { addExceptionType(); }
			public void handleEdit(EObject element) { editExceptionType(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveExceptionType(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromExceptionType(element); }
			public void navigateTo(EObject element) { }
		});
		this.exceptionType.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExceptionHandler.Properties.exceptionType, UmlViewsRepository.FORM_KIND));
		this.exceptionType.createControls(parent, widgetFactory);
		this.exceptionType.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExceptionHandlerPropertiesEditionPartForm.this, UmlViewsRepository.ExceptionHandler.Properties.exceptionType, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData exceptionTypeData = new GridData(GridData.FILL_HORIZONTAL);
		exceptionTypeData.horizontalSpan = 3;
		this.exceptionType.setLayoutData(exceptionTypeData);
		this.exceptionType.disableMove();
		exceptionType.setID(UmlViewsRepository.ExceptionHandler.Properties.exceptionType);
		exceptionType.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addExceptionType() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(exceptionType.getInput(), exceptionTypeFilters, exceptionTypeBusinessFilters,
		"exceptionType", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExceptionHandlerPropertiesEditionPartForm.this, UmlViewsRepository.ExceptionHandler.Properties.exceptionType,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				exceptionType.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveExceptionType(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExceptionHandlerPropertiesEditionPartForm.this, UmlViewsRepository.ExceptionHandler.Properties.exceptionType, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		exceptionType.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromExceptionType(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExceptionHandlerPropertiesEditionPartForm.this, UmlViewsRepository.ExceptionHandler.Properties.exceptionType, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		exceptionType.refresh();
	}

	/**
	 * 
	 */
	protected void editExceptionType(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				exceptionType.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createProtectedNodeFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ExceptionHandlerPropertiesEditionPart_ProtectedNodeLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ExceptionHandler.Properties.protectedNode, UmlViewsRepository.FORM_KIND));
		protectedNode = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ExceptionHandler.Properties.protectedNode, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(protectedNode);
		protectedNode.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData protectedNodeData = new GridData(GridData.FILL_HORIZONTAL);
		protectedNode.setLayoutData(protectedNodeData);
		protectedNode.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExceptionHandlerPropertiesEditionPartForm.this, UmlViewsRepository.ExceptionHandler.Properties.protectedNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getProtectedNode()));
			}

		});
		protectedNode.setID(UmlViewsRepository.ExceptionHandler.Properties.protectedNode);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExceptionHandler.Properties.protectedNode, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#getHandlerBody()
	 * 
	 */
	public EObject getHandlerBody() {
		if (handlerBody.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) handlerBody.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#initHandlerBody(EObjectFlatComboSettings)
	 */
	public void initHandlerBody(EObjectFlatComboSettings settings) {
		handlerBody.setInput(settings);
		if (current != null) {
			handlerBody.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#setHandlerBody(EObject newValue)
	 * 
	 */
	public void setHandlerBody(EObject newValue) {
		if (newValue != null) {
			handlerBody.setSelection(new StructuredSelection(newValue));
		} else {
			handlerBody.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#setHandlerBodyButtonMode(ButtonsModeEnum newValue)
	 */
	public void setHandlerBodyButtonMode(ButtonsModeEnum newValue) {
		handlerBody.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#addFilterHandlerBody(ViewerFilter filter)
	 * 
	 */
	public void addFilterToHandlerBody(ViewerFilter filter) {
		handlerBody.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#addBusinessFilterHandlerBody(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToHandlerBody(ViewerFilter filter) {
		handlerBody.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#getExceptionInput()
	 * 
	 */
	public EObject getExceptionInput() {
		if (exceptionInput.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) exceptionInput.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#initExceptionInput(EObjectFlatComboSettings)
	 */
	public void initExceptionInput(EObjectFlatComboSettings settings) {
		exceptionInput.setInput(settings);
		if (current != null) {
			exceptionInput.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#setExceptionInput(EObject newValue)
	 * 
	 */
	public void setExceptionInput(EObject newValue) {
		if (newValue != null) {
			exceptionInput.setSelection(new StructuredSelection(newValue));
		} else {
			exceptionInput.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#setExceptionInputButtonMode(ButtonsModeEnum newValue)
	 */
	public void setExceptionInputButtonMode(ButtonsModeEnum newValue) {
		exceptionInput.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#addFilterExceptionInput(ViewerFilter filter)
	 * 
	 */
	public void addFilterToExceptionInput(ViewerFilter filter) {
		exceptionInput.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#addBusinessFilterExceptionInput(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToExceptionInput(ViewerFilter filter) {
		exceptionInput.addBusinessRuleFilter(filter);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#initExceptionType(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initExceptionType(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		exceptionType.setContentProvider(contentProvider);
		exceptionType.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#updateExceptionType()
	 * 
	 */
	public void updateExceptionType() {
	exceptionType.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#addFilterExceptionType(ViewerFilter filter)
	 * 
	 */
	public void addFilterToExceptionType(ViewerFilter filter) {
		exceptionTypeFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#addBusinessFilterExceptionType(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToExceptionType(ViewerFilter filter) {
		exceptionTypeBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#isContainedInExceptionTypeTable(EObject element)
	 * 
	 */
	public boolean isContainedInExceptionTypeTable(EObject element) {
		return ((ReferencesTableSettings)exceptionType.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#getProtectedNode()
	 * 
	 */
	public EObject getProtectedNode() {
		if (protectedNode.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) protectedNode.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#initProtectedNode(EObjectFlatComboSettings)
	 */
	public void initProtectedNode(EObjectFlatComboSettings settings) {
		protectedNode.setInput(settings);
		if (current != null) {
			protectedNode.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#setProtectedNode(EObject newValue)
	 * 
	 */
	public void setProtectedNode(EObject newValue) {
		if (newValue != null) {
			protectedNode.setSelection(new StructuredSelection(newValue));
		} else {
			protectedNode.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#setProtectedNodeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setProtectedNodeButtonMode(ButtonsModeEnum newValue) {
		protectedNode.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#addFilterProtectedNode(ViewerFilter filter)
	 * 
	 */
	public void addFilterToProtectedNode(ViewerFilter filter) {
		protectedNode.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart#addBusinessFilterProtectedNode(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToProtectedNode(ViewerFilter filter) {
		protectedNode.addBusinessRuleFilter(filter);
	}




	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.ExceptionHandler_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
