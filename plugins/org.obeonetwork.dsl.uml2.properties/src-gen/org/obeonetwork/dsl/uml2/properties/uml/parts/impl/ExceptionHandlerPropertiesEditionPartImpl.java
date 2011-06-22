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

// Start of user code for imports
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
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
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ExceptionHandlerPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ExceptionHandlerPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ExceptionHandlerPropertiesEditionPart {

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
	public ExceptionHandlerPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createFigure(org.eclipse.swt.widgets.Composite)
	 * 
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
	 * 
	 */
	public void createControls(Composite view) { 
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
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.ExceptionHandler.Properties.handlerBody) {
					return createHandlerBodyFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.ExceptionHandler.Properties.exceptionInput) {
					return createExceptionInputFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.ExceptionHandler.Properties.exceptionType) {
					return createExceptionTypeAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.ExceptionHandler.Properties.protectedNode) {
					return createProtectedNodeFlatComboViewer(parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}

	/**
	 * 
	 */
	protected Composite createPropertiesGroup(Composite parent) {
		Group propertiesGroup = new Group(parent, SWT.NONE);
		propertiesGroup.setText(UmlMessages.ExceptionHandlerPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createHandlerBodyFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.ExceptionHandlerPropertiesEditionPart_HandlerBodyLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ExceptionHandler.Properties.handlerBody, UmlViewsRepository.SWT_KIND));
		handlerBody = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ExceptionHandler.Properties.handlerBody, UmlViewsRepository.SWT_KIND));
		handlerBody.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		handlerBody.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExceptionHandlerPropertiesEditionPartImpl.this, UmlViewsRepository.ExceptionHandler.Properties.handlerBody, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getHandlerBody()));
			}

		});
		GridData handlerBodyData = new GridData(GridData.FILL_HORIZONTAL);
		handlerBody.setLayoutData(handlerBodyData);
		handlerBody.setID(UmlViewsRepository.ExceptionHandler.Properties.handlerBody);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExceptionHandler.Properties.handlerBody, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createExceptionInputFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.ExceptionHandlerPropertiesEditionPart_ExceptionInputLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ExceptionHandler.Properties.exceptionInput, UmlViewsRepository.SWT_KIND));
		exceptionInput = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ExceptionHandler.Properties.exceptionInput, UmlViewsRepository.SWT_KIND));
		exceptionInput.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		exceptionInput.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExceptionHandlerPropertiesEditionPartImpl.this, UmlViewsRepository.ExceptionHandler.Properties.exceptionInput, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getExceptionInput()));
			}

		});
		GridData exceptionInputData = new GridData(GridData.FILL_HORIZONTAL);
		exceptionInput.setLayoutData(exceptionInputData);
		exceptionInput.setID(UmlViewsRepository.ExceptionHandler.Properties.exceptionInput);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExceptionHandler.Properties.exceptionInput, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createExceptionTypeAdvancedReferencesTable(Composite parent) {
		this.exceptionType = new ReferencesTable(UmlMessages.ExceptionHandlerPropertiesEditionPart_ExceptionTypeLabel, new ReferencesTableListener() {
			public void handleAdd() { addExceptionType(); }
			public void handleEdit(EObject element) { editExceptionType(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveExceptionType(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromExceptionType(element); }
			public void navigateTo(EObject element) { }
		});
		this.exceptionType.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExceptionHandler.Properties.exceptionType, UmlViewsRepository.SWT_KIND));
		this.exceptionType.createControls(parent);
		this.exceptionType.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExceptionHandlerPropertiesEditionPartImpl.this, UmlViewsRepository.ExceptionHandler.Properties.exceptionType, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExceptionHandlerPropertiesEditionPartImpl.this, UmlViewsRepository.ExceptionHandler.Properties.exceptionType,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExceptionHandlerPropertiesEditionPartImpl.this, UmlViewsRepository.ExceptionHandler.Properties.exceptionType, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		exceptionType.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromExceptionType(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExceptionHandlerPropertiesEditionPartImpl.this, UmlViewsRepository.ExceptionHandler.Properties.exceptionType, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	 * 
	 */
	protected Composite createProtectedNodeFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.ExceptionHandlerPropertiesEditionPart_ProtectedNodeLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ExceptionHandler.Properties.protectedNode, UmlViewsRepository.SWT_KIND));
		protectedNode = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ExceptionHandler.Properties.protectedNode, UmlViewsRepository.SWT_KIND));
		protectedNode.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		protectedNode.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExceptionHandlerPropertiesEditionPartImpl.this, UmlViewsRepository.ExceptionHandler.Properties.protectedNode, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getProtectedNode()));
			}

		});
		GridData protectedNodeData = new GridData(GridData.FILL_HORIZONTAL);
		protectedNode.setLayoutData(protectedNodeData);
		protectedNode.setID(UmlViewsRepository.ExceptionHandler.Properties.protectedNode);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExceptionHandler.Properties.protectedNode, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
