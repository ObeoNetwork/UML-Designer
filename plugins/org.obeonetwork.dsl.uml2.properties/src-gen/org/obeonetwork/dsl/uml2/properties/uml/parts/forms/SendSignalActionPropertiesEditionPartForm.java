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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.EcoreAdapterFactory;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.part.impl.SectionPropertiesEditingPart;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;

import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;

import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EMFComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
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

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class SendSignalActionPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, SendSignalActionPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected Button isLeaf;
	protected EObjectFlatComboViewer inStructuredNode;
	protected EObjectFlatComboViewer activity;
	protected ReferencesTable outgoing;
	protected List<ViewerFilter> outgoingBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> outgoingFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable incoming;
	protected List<ViewerFilter> incomingBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> incomingFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable inPartition;
	protected List<ViewerFilter> inPartitionBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> inPartitionFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable inInterruptibleRegion;
	protected List<ViewerFilter> inInterruptibleRegionBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> inInterruptibleRegionFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable redefinedNode;
	protected List<ViewerFilter> redefinedNodeBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> redefinedNodeFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer onPort;
	protected EObjectFlatComboViewer signal;



	/**
	 * For {@link ISection} use only.
	 */
	public SendSignalActionPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public SendSignalActionPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
	 *  createFigure(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
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
	 *  createControls(org.eclipse.ui.forms.widgets.FormToolkit, org.eclipse.swt.widgets.Composite)
	 * @generated
	 */
	public void createControls(final FormToolkit widgetFactory, Composite view) {
		CompositionSequence sendSignalActionStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = sendSignalActionStep.addStep(UmlViewsRepository.SendSignalAction.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.SendSignalAction.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.SendSignalAction.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.SendSignalAction.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.SendSignalAction.Properties.isLeaf);
		propertiesStep.addStep(UmlViewsRepository.SendSignalAction.Properties.inStructuredNode);
		propertiesStep.addStep(UmlViewsRepository.SendSignalAction.Properties.activity);
		propertiesStep.addStep(UmlViewsRepository.SendSignalAction.Properties.outgoing);
		propertiesStep.addStep(UmlViewsRepository.SendSignalAction.Properties.incoming);
		propertiesStep.addStep(UmlViewsRepository.SendSignalAction.Properties.inPartition);
		propertiesStep.addStep(UmlViewsRepository.SendSignalAction.Properties.inInterruptibleRegion);
		propertiesStep.addStep(UmlViewsRepository.SendSignalAction.Properties.redefinedNode);
		propertiesStep.addStep(UmlViewsRepository.SendSignalAction.Properties.onPort);
		propertiesStep.addStep(UmlViewsRepository.SendSignalAction.Properties.signal);
		
		
		composer = new PartComposer(sendSignalActionStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.SendSignalAction.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.SendSignalAction.Properties.name) {
					return createNameText(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.SendSignalAction.Properties.visibility) {
					return createVisibilityEMFComboViewer(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.SendSignalAction.Properties.clientDependency) {
					return createClientDependencyReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.SendSignalAction.Properties.isLeaf) {
					return createIsLeafCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.SendSignalAction.Properties.inStructuredNode) {
					return createInStructuredNodeFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.SendSignalAction.Properties.activity) {
					return createActivityFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.SendSignalAction.Properties.outgoing) {
					return createOutgoingReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.SendSignalAction.Properties.incoming) {
					return createIncomingReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.SendSignalAction.Properties.inPartition) {
					return createInPartitionReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.SendSignalAction.Properties.inInterruptibleRegion) {
					return createInInterruptibleRegionReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.SendSignalAction.Properties.redefinedNode) {
					return createRedefinedNodeReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.SendSignalAction.Properties.onPort) {
					return createOnPortFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.SendSignalAction.Properties.signal) {
					return createSignalFlatComboViewer(parent, widgetFactory);
				}
				return parent;
			}
		};
		composer.compose(view);
	}
	/**
	 * @generated
	 */
	protected Composite createPropertiesGroup(FormToolkit widgetFactory, final Composite parent) {
		Section propertiesSection = widgetFactory.createSection(parent, Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		propertiesSection.setText(UmlMessages.SendSignalActionPropertiesEditionPart_PropertiesGroupLabel);
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
	 * @generated
	 */
	
	protected Composite createNameText(FormToolkit widgetFactory, Composite parent) {
		createDescription(parent, UmlViewsRepository.SendSignalAction.Properties.name, UmlMessages.SendSignalActionPropertiesEditionPart_NameLabel);
		name = widgetFactory.createText(parent, ""); //$NON-NLS-1$
		name.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		widgetFactory.paintBordersFor(parent);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(nameData);
		name.addFocusListener(new FocusAdapter() {
			/**
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * @generated
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(
							SendSignalActionPropertiesEditionPartForm.this,
							UmlViewsRepository.SendSignalAction.Properties.name,
							PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									SendSignalActionPropertiesEditionPartForm.this,
									UmlViewsRepository.SendSignalAction.Properties.name,
									PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_LOST,
									null, name.getText()));
				}
			}

			/**
			 * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
			 */
			@Override
			public void focusGained(FocusEvent e) {
				if (propertiesEditionComponent != null) {
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									SendSignalActionPropertiesEditionPartForm.this,
									null,
									PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_GAINED,
									null, null));
				}
			}
		});
		name.addKeyListener(new KeyAdapter() {
			/**
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * @generated
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}
		});
		EditingUtils.setID(name, UmlViewsRepository.SendSignalAction.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.SendSignalAction.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		createDescription(parent, UmlViewsRepository.SendSignalAction.Properties.visibility, UmlMessages.SendSignalActionPropertiesEditionPart_VisibilityLabel);
		visibility = new EMFComboViewer(parent);
		visibility.setContentProvider(new ArrayContentProvider());
		visibility.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
		GridData visibilityData = new GridData(GridData.FILL_HORIZONTAL);
		visibility.getCombo().setLayoutData(visibilityData);
		visibility.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 * 	@generated
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.SendSignalAction.Properties.visibility);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.SendSignalAction.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.clientDependency = new ReferencesTable(getDescription(UmlViewsRepository.SendSignalAction.Properties.clientDependency, UmlMessages.SendSignalActionPropertiesEditionPart_ClientDependencyLabel), new ReferencesTableListener	() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.SendSignalAction.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
		this.clientDependency.createControls(parent, widgetFactory);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.SendSignalAction.Properties.clientDependency);
		clientDependency.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addClientDependency() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(clientDependency.getInput(), clientDependencyFilters, clientDependencyBusinessFilters,
		"clientDependency", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.clientDependency,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				clientDependency.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveClientDependency(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void editClientDependency(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				clientDependency.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsLeafCheckbox(FormToolkit widgetFactory, Composite parent) {
		isLeaf = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.SendSignalAction.Properties.isLeaf, UmlMessages.SendSignalActionPropertiesEditionPart_IsLeafLabel), SWT.CHECK);
		isLeaf.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
			}

		});
		GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
		isLeafData.horizontalSpan = 2;
		isLeaf.setLayoutData(isLeafData);
		EditingUtils.setID(isLeaf, UmlViewsRepository.SendSignalAction.Properties.isLeaf);
		EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.SendSignalAction.Properties.isLeaf, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createInStructuredNodeFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.SendSignalAction.Properties.inStructuredNode, UmlMessages.SendSignalActionPropertiesEditionPart_InStructuredNodeLabel);
		inStructuredNode = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.SendSignalAction.Properties.inStructuredNode, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(inStructuredNode);
		inStructuredNode.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData inStructuredNodeData = new GridData(GridData.FILL_HORIZONTAL);
		inStructuredNode.setLayoutData(inStructuredNodeData);
		inStructuredNode.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.inStructuredNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getInStructuredNode()));
			}

		});
		inStructuredNode.setID(UmlViewsRepository.SendSignalAction.Properties.inStructuredNode);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.SendSignalAction.Properties.inStructuredNode, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createActivityFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.SendSignalAction.Properties.activity, UmlMessages.SendSignalActionPropertiesEditionPart_ActivityLabel);
		activity = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.SendSignalAction.Properties.activity, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(activity);
		activity.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData activityData = new GridData(GridData.FILL_HORIZONTAL);
		activity.setLayoutData(activityData);
		activity.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.activity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getActivity()));
			}

		});
		activity.setID(UmlViewsRepository.SendSignalAction.Properties.activity);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.SendSignalAction.Properties.activity, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createOutgoingReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.outgoing = new ReferencesTable(getDescription(UmlViewsRepository.SendSignalAction.Properties.outgoing, UmlMessages.SendSignalActionPropertiesEditionPart_OutgoingLabel), new ReferencesTableListener	() {
			public void handleAdd() { addOutgoing(); }
			public void handleEdit(EObject element) { editOutgoing(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveOutgoing(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromOutgoing(element); }
			public void navigateTo(EObject element) { }
		});
		this.outgoing.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.SendSignalAction.Properties.outgoing, UmlViewsRepository.FORM_KIND));
		this.outgoing.createControls(parent, widgetFactory);
		this.outgoing.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.outgoing, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData outgoingData = new GridData(GridData.FILL_HORIZONTAL);
		outgoingData.horizontalSpan = 3;
		this.outgoing.setLayoutData(outgoingData);
		this.outgoing.disableMove();
		outgoing.setID(UmlViewsRepository.SendSignalAction.Properties.outgoing);
		outgoing.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addOutgoing() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(outgoing.getInput(), outgoingFilters, outgoingBusinessFilters,
		"outgoing", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.outgoing,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				outgoing.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveOutgoing(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.outgoing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		outgoing.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromOutgoing(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.outgoing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		outgoing.refresh();
	}

	/**
	 * @generated
	 */
	protected void editOutgoing(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				outgoing.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	protected Composite createIncomingReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.incoming = new ReferencesTable(getDescription(UmlViewsRepository.SendSignalAction.Properties.incoming, UmlMessages.SendSignalActionPropertiesEditionPart_IncomingLabel), new ReferencesTableListener	() {
			public void handleAdd() { addIncoming(); }
			public void handleEdit(EObject element) { editIncoming(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveIncoming(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromIncoming(element); }
			public void navigateTo(EObject element) { }
		});
		this.incoming.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.SendSignalAction.Properties.incoming, UmlViewsRepository.FORM_KIND));
		this.incoming.createControls(parent, widgetFactory);
		this.incoming.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.incoming, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData incomingData = new GridData(GridData.FILL_HORIZONTAL);
		incomingData.horizontalSpan = 3;
		this.incoming.setLayoutData(incomingData);
		this.incoming.disableMove();
		incoming.setID(UmlViewsRepository.SendSignalAction.Properties.incoming);
		incoming.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addIncoming() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(incoming.getInput(), incomingFilters, incomingBusinessFilters,
		"incoming", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.incoming,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				incoming.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveIncoming(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.incoming, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		incoming.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromIncoming(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.incoming, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		incoming.refresh();
	}

	/**
	 * @generated
	 */
	protected void editIncoming(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				incoming.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	protected Composite createInPartitionReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.inPartition = new ReferencesTable(getDescription(UmlViewsRepository.SendSignalAction.Properties.inPartition, UmlMessages.SendSignalActionPropertiesEditionPart_InPartitionLabel), new ReferencesTableListener	() {
			public void handleAdd() { addInPartition(); }
			public void handleEdit(EObject element) { editInPartition(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveInPartition(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromInPartition(element); }
			public void navigateTo(EObject element) { }
		});
		this.inPartition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.SendSignalAction.Properties.inPartition, UmlViewsRepository.FORM_KIND));
		this.inPartition.createControls(parent, widgetFactory);
		this.inPartition.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.inPartition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData inPartitionData = new GridData(GridData.FILL_HORIZONTAL);
		inPartitionData.horizontalSpan = 3;
		this.inPartition.setLayoutData(inPartitionData);
		this.inPartition.disableMove();
		inPartition.setID(UmlViewsRepository.SendSignalAction.Properties.inPartition);
		inPartition.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addInPartition() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(inPartition.getInput(), inPartitionFilters, inPartitionBusinessFilters,
		"inPartition", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.inPartition,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				inPartition.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveInPartition(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		inPartition.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromInPartition(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		inPartition.refresh();
	}

	/**
	 * @generated
	 */
	protected void editInPartition(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				inPartition.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	protected Composite createInInterruptibleRegionReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.inInterruptibleRegion = new ReferencesTable(getDescription(UmlViewsRepository.SendSignalAction.Properties.inInterruptibleRegion, UmlMessages.SendSignalActionPropertiesEditionPart_InInterruptibleRegionLabel), new ReferencesTableListener	() {
			public void handleAdd() { addInInterruptibleRegion(); }
			public void handleEdit(EObject element) { editInInterruptibleRegion(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveInInterruptibleRegion(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromInInterruptibleRegion(element); }
			public void navigateTo(EObject element) { }
		});
		this.inInterruptibleRegion.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.SendSignalAction.Properties.inInterruptibleRegion, UmlViewsRepository.FORM_KIND));
		this.inInterruptibleRegion.createControls(parent, widgetFactory);
		this.inInterruptibleRegion.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.inInterruptibleRegion, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData inInterruptibleRegionData = new GridData(GridData.FILL_HORIZONTAL);
		inInterruptibleRegionData.horizontalSpan = 3;
		this.inInterruptibleRegion.setLayoutData(inInterruptibleRegionData);
		this.inInterruptibleRegion.disableMove();
		inInterruptibleRegion.setID(UmlViewsRepository.SendSignalAction.Properties.inInterruptibleRegion);
		inInterruptibleRegion.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addInInterruptibleRegion() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(inInterruptibleRegion.getInput(), inInterruptibleRegionFilters, inInterruptibleRegionBusinessFilters,
		"inInterruptibleRegion", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.inInterruptibleRegion,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				inInterruptibleRegion.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveInInterruptibleRegion(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.inInterruptibleRegion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		inInterruptibleRegion.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromInInterruptibleRegion(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.inInterruptibleRegion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		inInterruptibleRegion.refresh();
	}

	/**
	 * @generated
	 */
	protected void editInInterruptibleRegion(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				inInterruptibleRegion.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	protected Composite createRedefinedNodeReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.redefinedNode = new ReferencesTable(getDescription(UmlViewsRepository.SendSignalAction.Properties.redefinedNode, UmlMessages.SendSignalActionPropertiesEditionPart_RedefinedNodeLabel), new ReferencesTableListener	() {
			public void handleAdd() { addRedefinedNode(); }
			public void handleEdit(EObject element) { editRedefinedNode(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedNode(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRedefinedNode(element); }
			public void navigateTo(EObject element) { }
		});
		this.redefinedNode.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.SendSignalAction.Properties.redefinedNode, UmlViewsRepository.FORM_KIND));
		this.redefinedNode.createControls(parent, widgetFactory);
		this.redefinedNode.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.redefinedNode, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData redefinedNodeData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedNodeData.horizontalSpan = 3;
		this.redefinedNode.setLayoutData(redefinedNodeData);
		this.redefinedNode.disableMove();
		redefinedNode.setID(UmlViewsRepository.SendSignalAction.Properties.redefinedNode);
		redefinedNode.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addRedefinedNode() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(redefinedNode.getInput(), redefinedNodeFilters, redefinedNodeBusinessFilters,
		"redefinedNode", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.redefinedNode,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				redefinedNode.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveRedefinedNode(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.redefinedNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		redefinedNode.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromRedefinedNode(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.redefinedNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		redefinedNode.refresh();
	}

	/**
	 * @generated
	 */
	protected void editRedefinedNode(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				redefinedNode.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createOnPortFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.SendSignalAction.Properties.onPort, UmlMessages.SendSignalActionPropertiesEditionPart_OnPortLabel);
		onPort = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.SendSignalAction.Properties.onPort, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(onPort);
		onPort.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData onPortData = new GridData(GridData.FILL_HORIZONTAL);
		onPort.setLayoutData(onPortData);
		onPort.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.onPort, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getOnPort()));
			}

		});
		onPort.setID(UmlViewsRepository.SendSignalAction.Properties.onPort);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.SendSignalAction.Properties.onPort, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createSignalFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.SendSignalAction.Properties.signal, UmlMessages.SendSignalActionPropertiesEditionPart_SignalLabel);
		signal = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.SendSignalAction.Properties.signal, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(signal);
		signal.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData signalData = new GridData(GridData.FILL_HORIZONTAL);
		signal.setLayoutData(signalData);
		signal.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(SendSignalActionPropertiesEditionPartForm.this, UmlViewsRepository.SendSignalAction.Properties.signal, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getSignal()));
			}

		});
		signal.setID(UmlViewsRepository.SendSignalAction.Properties.signal);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.SendSignalAction.Properties.signal, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.name);
		if (readOnly && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
		Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
		return Boolean.valueOf(isLeaf.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
		if (newValue != null) {
			isLeaf.setSelection(newValue.booleanValue());
		} else {
			isLeaf.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.isLeaf);
		if (readOnly && isLeaf.isEnabled()) {
			isLeaf.setEnabled(false);
			isLeaf.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !isLeaf.isEnabled()) {
			isLeaf.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#getInStructuredNode()
	 * @generated
	 */
	public EObject getInStructuredNode() {
		if (inStructuredNode.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) inStructuredNode.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#initInStructuredNode(EObjectFlatComboSettings)
	 */
	public void initInStructuredNode(EObjectFlatComboSettings settings) {
		inStructuredNode.setInput(settings);
		if (current != null) {
			inStructuredNode.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.inStructuredNode);
		if (readOnly && inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(false);
			inStructuredNode.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#setInStructuredNode(EObject newValue)
	 * @generated
	 */
	public void setInStructuredNode(EObject newValue) {
		if (newValue != null) {
			inStructuredNode.setSelection(new StructuredSelection(newValue));
		} else {
			inStructuredNode.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.inStructuredNode);
		if (readOnly && inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(false);
			inStructuredNode.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#setInStructuredNodeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInStructuredNodeButtonMode(ButtonsModeEnum newValue) {
		inStructuredNode.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addFilterInStructuredNode(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInStructuredNode(ViewerFilter filter) {
		inStructuredNode.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addBusinessFilterInStructuredNode(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInStructuredNode(ViewerFilter filter) {
		inStructuredNode.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#getActivity()
	 * @generated
	 */
	public EObject getActivity() {
		if (activity.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) activity.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#initActivity(EObjectFlatComboSettings)
	 */
	public void initActivity(EObjectFlatComboSettings settings) {
		activity.setInput(settings);
		if (current != null) {
			activity.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.activity);
		if (readOnly && activity.isEnabled()) {
			activity.setEnabled(false);
			activity.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !activity.isEnabled()) {
			activity.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#setActivity(EObject newValue)
	 * @generated
	 */
	public void setActivity(EObject newValue) {
		if (newValue != null) {
			activity.setSelection(new StructuredSelection(newValue));
		} else {
			activity.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.activity);
		if (readOnly && activity.isEnabled()) {
			activity.setEnabled(false);
			activity.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !activity.isEnabled()) {
			activity.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#setActivityButtonMode(ButtonsModeEnum newValue)
	 */
	public void setActivityButtonMode(ButtonsModeEnum newValue) {
		activity.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addFilterActivity(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToActivity(ViewerFilter filter) {
		activity.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addBusinessFilterActivity(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToActivity(ViewerFilter filter) {
		activity.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#initOutgoing(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initOutgoing(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		outgoing.setContentProvider(contentProvider);
		outgoing.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.outgoing);
		if (readOnly && outgoing.getTable().isEnabled()) {
			outgoing.setEnabled(false);
			outgoing.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !outgoing.getTable().isEnabled()) {
			outgoing.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#updateOutgoing()
	 * @generated
	 */
	public void updateOutgoing() {
	outgoing.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addFilterOutgoing(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOutgoing(ViewerFilter filter) {
		outgoingFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addBusinessFilterOutgoing(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOutgoing(ViewerFilter filter) {
		outgoingBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#isContainedInOutgoingTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInOutgoingTable(EObject element) {
		return ((ReferencesTableSettings)outgoing.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#initIncoming(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initIncoming(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		incoming.setContentProvider(contentProvider);
		incoming.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.incoming);
		if (readOnly && incoming.getTable().isEnabled()) {
			incoming.setEnabled(false);
			incoming.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !incoming.getTable().isEnabled()) {
			incoming.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#updateIncoming()
	 * @generated
	 */
	public void updateIncoming() {
	incoming.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addFilterIncoming(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToIncoming(ViewerFilter filter) {
		incomingFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addBusinessFilterIncoming(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToIncoming(ViewerFilter filter) {
		incomingBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#isContainedInIncomingTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInIncomingTable(EObject element) {
		return ((ReferencesTableSettings)incoming.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#initInPartition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInPartition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		inPartition.setContentProvider(contentProvider);
		inPartition.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.inPartition);
		if (readOnly && inPartition.getTable().isEnabled()) {
			inPartition.setEnabled(false);
			inPartition.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !inPartition.getTable().isEnabled()) {
			inPartition.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#updateInPartition()
	 * @generated
	 */
	public void updateInPartition() {
	inPartition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addFilterInPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInPartition(ViewerFilter filter) {
		inPartitionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addBusinessFilterInPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInPartition(ViewerFilter filter) {
		inPartitionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#isContainedInInPartitionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInInPartitionTable(EObject element) {
		return ((ReferencesTableSettings)inPartition.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#initInInterruptibleRegion(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInInterruptibleRegion(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		inInterruptibleRegion.setContentProvider(contentProvider);
		inInterruptibleRegion.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.inInterruptibleRegion);
		if (readOnly && inInterruptibleRegion.getTable().isEnabled()) {
			inInterruptibleRegion.setEnabled(false);
			inInterruptibleRegion.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !inInterruptibleRegion.getTable().isEnabled()) {
			inInterruptibleRegion.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#updateInInterruptibleRegion()
	 * @generated
	 */
	public void updateInInterruptibleRegion() {
	inInterruptibleRegion.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addFilterInInterruptibleRegion(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInInterruptibleRegion(ViewerFilter filter) {
		inInterruptibleRegionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addBusinessFilterInInterruptibleRegion(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInInterruptibleRegion(ViewerFilter filter) {
		inInterruptibleRegionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#isContainedInInInterruptibleRegionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInInInterruptibleRegionTable(EObject element) {
		return ((ReferencesTableSettings)inInterruptibleRegion.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#initRedefinedNode(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedNode(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedNode.setContentProvider(contentProvider);
		redefinedNode.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.redefinedNode);
		if (readOnly && redefinedNode.getTable().isEnabled()) {
			redefinedNode.setEnabled(false);
			redefinedNode.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !redefinedNode.getTable().isEnabled()) {
			redefinedNode.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#updateRedefinedNode()
	 * @generated
	 */
	public void updateRedefinedNode() {
	redefinedNode.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addFilterRedefinedNode(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedNode(ViewerFilter filter) {
		redefinedNodeFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addBusinessFilterRedefinedNode(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedNode(ViewerFilter filter) {
		redefinedNodeBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#isContainedInRedefinedNodeTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRedefinedNodeTable(EObject element) {
		return ((ReferencesTableSettings)redefinedNode.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#getOnPort()
	 * @generated
	 */
	public EObject getOnPort() {
		if (onPort.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) onPort.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#initOnPort(EObjectFlatComboSettings)
	 */
	public void initOnPort(EObjectFlatComboSettings settings) {
		onPort.setInput(settings);
		if (current != null) {
			onPort.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.onPort);
		if (readOnly && onPort.isEnabled()) {
			onPort.setEnabled(false);
			onPort.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !onPort.isEnabled()) {
			onPort.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#setOnPort(EObject newValue)
	 * @generated
	 */
	public void setOnPort(EObject newValue) {
		if (newValue != null) {
			onPort.setSelection(new StructuredSelection(newValue));
		} else {
			onPort.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.onPort);
		if (readOnly && onPort.isEnabled()) {
			onPort.setEnabled(false);
			onPort.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !onPort.isEnabled()) {
			onPort.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#setOnPortButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOnPortButtonMode(ButtonsModeEnum newValue) {
		onPort.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addFilterOnPort(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOnPort(ViewerFilter filter) {
		onPort.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addBusinessFilterOnPort(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOnPort(ViewerFilter filter) {
		onPort.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#getSignal()
	 * @generated
	 */
	public EObject getSignal() {
		if (signal.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) signal.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#initSignal(EObjectFlatComboSettings)
	 */
	public void initSignal(EObjectFlatComboSettings settings) {
		signal.setInput(settings);
		if (current != null) {
			signal.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.signal);
		if (readOnly && signal.isEnabled()) {
			signal.setEnabled(false);
			signal.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !signal.isEnabled()) {
			signal.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#setSignal(EObject newValue)
	 * @generated
	 */
	public void setSignal(EObject newValue) {
		if (newValue != null) {
			signal.setSelection(new StructuredSelection(newValue));
		} else {
			signal.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.SendSignalAction.Properties.signal);
		if (readOnly && signal.isEnabled()) {
			signal.setEnabled(false);
			signal.setToolTipText(UmlMessages.SendSignalAction_ReadOnly);
		} else if (!readOnly && !signal.isEnabled()) {
			signal.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#setSignalButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSignalButtonMode(ButtonsModeEnum newValue) {
		signal.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addFilterSignal(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSignal(ViewerFilter filter) {
		signal.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SendSignalActionPropertiesEditionPart#addBusinessFilterSignal(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSignal(ViewerFilter filter) {
		signal.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.SendSignalAction_Part_Title;
	}



}
