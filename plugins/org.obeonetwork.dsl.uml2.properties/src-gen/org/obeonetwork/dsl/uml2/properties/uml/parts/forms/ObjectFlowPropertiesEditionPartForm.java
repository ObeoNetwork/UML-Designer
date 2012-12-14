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

import org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ObjectFlowPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, ObjectFlowPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected Button isLeaf;
	protected EObjectFlatComboViewer source;
	protected EObjectFlatComboViewer target;
	protected ReferencesTable redefinedEdge;
	protected List<ViewerFilter> redefinedEdgeBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> redefinedEdgeFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable inPartition;
	protected List<ViewerFilter> inPartitionBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> inPartitionFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer interrupts;
	protected EObjectFlatComboViewer inStructuredNode;
	protected EObjectFlatComboViewer activity;
	protected Button isMulticast;
	protected Button isMultireceive;
	protected EObjectFlatComboViewer transformation;
	protected EObjectFlatComboViewer selection;



	/**
	 * For {@link ISection} use only.
	 */
	public ObjectFlowPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ObjectFlowPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence objectFlowStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = objectFlowStep.addStep(UmlViewsRepository.ObjectFlow.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.isLeaf);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.source);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.target);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.redefinedEdge);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.inPartition);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.interrupts);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.inStructuredNode);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.activity);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.isMulticast);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.isMultireceive);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.transformation);
		propertiesStep.addStep(UmlViewsRepository.ObjectFlow.Properties.selection);
		
		
		composer = new PartComposer(objectFlowStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.ObjectFlow.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.name) {
					return createNameText(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.visibility) {
					return createVisibilityEMFComboViewer(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.clientDependency) {
					return createClientDependencyReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.isLeaf) {
					return createIsLeafCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.source) {
					return createSourceFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.target) {
					return createTargetFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.redefinedEdge) {
					return createRedefinedEdgeReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.inPartition) {
					return createInPartitionReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.interrupts) {
					return createInterruptsFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.inStructuredNode) {
					return createInStructuredNodeFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.activity) {
					return createActivityFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.isMulticast) {
					return createIsMulticastCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.isMultireceive) {
					return createIsMultireceiveCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.transformation) {
					return createTransformationFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ObjectFlow.Properties.selection) {
					return createSelectionFlatComboViewer(parent, widgetFactory);
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
		propertiesSection.setText(UmlMessages.ObjectFlowPropertiesEditionPart_PropertiesGroupLabel);
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
		createDescription(parent, UmlViewsRepository.ObjectFlow.Properties.name, UmlMessages.ObjectFlowPropertiesEditionPart_NameLabel);
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
							ObjectFlowPropertiesEditionPartForm.this,
							UmlViewsRepository.ObjectFlow.Properties.name,
							PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									ObjectFlowPropertiesEditionPartForm.this,
									UmlViewsRepository.ObjectFlow.Properties.name,
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
									ObjectFlowPropertiesEditionPartForm.this,
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}
		});
		EditingUtils.setID(name, UmlViewsRepository.ObjectFlow.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		createDescription(parent, UmlViewsRepository.ObjectFlow.Properties.visibility, UmlMessages.ObjectFlowPropertiesEditionPart_VisibilityLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.ObjectFlow.Properties.visibility);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.clientDependency = new ReferencesTable(getDescription(UmlViewsRepository.ObjectFlow.Properties.clientDependency, UmlMessages.ObjectFlowPropertiesEditionPart_ClientDependencyLabel), new ReferencesTableListener	() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
		this.clientDependency.createControls(parent, widgetFactory);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.ObjectFlow.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		isLeaf = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.ObjectFlow.Properties.isLeaf, UmlMessages.ObjectFlowPropertiesEditionPart_IsLeafLabel), SWT.CHECK);
		isLeaf.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
			}

		});
		GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
		isLeafData.horizontalSpan = 2;
		isLeaf.setLayoutData(isLeafData);
		EditingUtils.setID(isLeaf, UmlViewsRepository.ObjectFlow.Properties.isLeaf);
		EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.isLeaf, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createSourceFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ObjectFlow.Properties.source, UmlMessages.ObjectFlowPropertiesEditionPart_SourceLabel);
		source = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ObjectFlow.Properties.source, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(source);
		source.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData sourceData = new GridData(GridData.FILL_HORIZONTAL);
		source.setLayoutData(sourceData);
		source.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.source, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getSource()));
			}

		});
		source.setID(UmlViewsRepository.ObjectFlow.Properties.source);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.source, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createTargetFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ObjectFlow.Properties.target, UmlMessages.ObjectFlowPropertiesEditionPart_TargetLabel);
		target = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ObjectFlow.Properties.target, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(target);
		target.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData targetData = new GridData(GridData.FILL_HORIZONTAL);
		target.setLayoutData(targetData);
		target.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.target, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getTarget()));
			}

		});
		target.setID(UmlViewsRepository.ObjectFlow.Properties.target);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.target, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createRedefinedEdgeReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.redefinedEdge = new ReferencesTable(getDescription(UmlViewsRepository.ObjectFlow.Properties.redefinedEdge, UmlMessages.ObjectFlowPropertiesEditionPart_RedefinedEdgeLabel), new ReferencesTableListener	() {
			public void handleAdd() { addRedefinedEdge(); }
			public void handleEdit(EObject element) { editRedefinedEdge(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedEdge(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRedefinedEdge(element); }
			public void navigateTo(EObject element) { }
		});
		this.redefinedEdge.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.redefinedEdge, UmlViewsRepository.FORM_KIND));
		this.redefinedEdge.createControls(parent, widgetFactory);
		this.redefinedEdge.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.redefinedEdge, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData redefinedEdgeData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedEdgeData.horizontalSpan = 3;
		this.redefinedEdge.setLayoutData(redefinedEdgeData);
		this.redefinedEdge.disableMove();
		redefinedEdge.setID(UmlViewsRepository.ObjectFlow.Properties.redefinedEdge);
		redefinedEdge.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addRedefinedEdge() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(redefinedEdge.getInput(), redefinedEdgeFilters, redefinedEdgeBusinessFilters,
		"redefinedEdge", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.redefinedEdge,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				redefinedEdge.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveRedefinedEdge(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.redefinedEdge, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		redefinedEdge.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromRedefinedEdge(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.redefinedEdge, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		redefinedEdge.refresh();
	}

	/**
	 * @generated
	 */
	protected void editRedefinedEdge(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				redefinedEdge.refresh();
			}
		}
	}

	/**
	 * @generated
	 */
	protected Composite createInPartitionReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.inPartition = new ReferencesTable(getDescription(UmlViewsRepository.ObjectFlow.Properties.inPartition, UmlMessages.ObjectFlowPropertiesEditionPart_InPartitionLabel), new ReferencesTableListener	() {
			public void handleAdd() { addInPartition(); }
			public void handleEdit(EObject element) { editInPartition(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveInPartition(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromInPartition(element); }
			public void navigateTo(EObject element) { }
		});
		this.inPartition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.inPartition, UmlViewsRepository.FORM_KIND));
		this.inPartition.createControls(parent, widgetFactory);
		this.inPartition.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.inPartition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData inPartitionData = new GridData(GridData.FILL_HORIZONTAL);
		inPartitionData.horizontalSpan = 3;
		this.inPartition.setLayoutData(inPartitionData);
		this.inPartition.disableMove();
		inPartition.setID(UmlViewsRepository.ObjectFlow.Properties.inPartition);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.inPartition,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		inPartition.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromInPartition(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createInterruptsFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ObjectFlow.Properties.interrupts, UmlMessages.ObjectFlowPropertiesEditionPart_InterruptsLabel);
		interrupts = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ObjectFlow.Properties.interrupts, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(interrupts);
		interrupts.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData interruptsData = new GridData(GridData.FILL_HORIZONTAL);
		interrupts.setLayoutData(interruptsData);
		interrupts.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.interrupts, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getInterrupts()));
			}

		});
		interrupts.setID(UmlViewsRepository.ObjectFlow.Properties.interrupts);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.interrupts, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createInStructuredNodeFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ObjectFlow.Properties.inStructuredNode, UmlMessages.ObjectFlowPropertiesEditionPart_InStructuredNodeLabel);
		inStructuredNode = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ObjectFlow.Properties.inStructuredNode, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.inStructuredNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getInStructuredNode()));
			}

		});
		inStructuredNode.setID(UmlViewsRepository.ObjectFlow.Properties.inStructuredNode);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.inStructuredNode, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createActivityFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ObjectFlow.Properties.activity, UmlMessages.ObjectFlowPropertiesEditionPart_ActivityLabel);
		activity = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ObjectFlow.Properties.activity, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.activity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getActivity()));
			}

		});
		activity.setID(UmlViewsRepository.ObjectFlow.Properties.activity);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.activity, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsMulticastCheckbox(FormToolkit widgetFactory, Composite parent) {
		isMulticast = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.ObjectFlow.Properties.isMulticast, UmlMessages.ObjectFlowPropertiesEditionPart_IsMulticastLabel), SWT.CHECK);
		isMulticast.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.isMulticast, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isMulticast.getSelection())));
			}

		});
		GridData isMulticastData = new GridData(GridData.FILL_HORIZONTAL);
		isMulticastData.horizontalSpan = 2;
		isMulticast.setLayoutData(isMulticastData);
		EditingUtils.setID(isMulticast, UmlViewsRepository.ObjectFlow.Properties.isMulticast);
		EditingUtils.setEEFtype(isMulticast, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.isMulticast, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsMultireceiveCheckbox(FormToolkit widgetFactory, Composite parent) {
		isMultireceive = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.ObjectFlow.Properties.isMultireceive, UmlMessages.ObjectFlowPropertiesEditionPart_IsMultireceiveLabel), SWT.CHECK);
		isMultireceive.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.isMultireceive, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isMultireceive.getSelection())));
			}

		});
		GridData isMultireceiveData = new GridData(GridData.FILL_HORIZONTAL);
		isMultireceiveData.horizontalSpan = 2;
		isMultireceive.setLayoutData(isMultireceiveData);
		EditingUtils.setID(isMultireceive, UmlViewsRepository.ObjectFlow.Properties.isMultireceive);
		EditingUtils.setEEFtype(isMultireceive, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.isMultireceive, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createTransformationFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ObjectFlow.Properties.transformation, UmlMessages.ObjectFlowPropertiesEditionPart_TransformationLabel);
		transformation = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ObjectFlow.Properties.transformation, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(transformation);
		transformation.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData transformationData = new GridData(GridData.FILL_HORIZONTAL);
		transformation.setLayoutData(transformationData);
		transformation.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.transformation, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getTransformation()));
			}

		});
		transformation.setID(UmlViewsRepository.ObjectFlow.Properties.transformation);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.transformation, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createSelectionFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ObjectFlow.Properties.selection, UmlMessages.ObjectFlowPropertiesEditionPart_SelectionLabel);
		selection = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ObjectFlow.Properties.selection, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(selection);
		selection.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData selectionData = new GridData(GridData.FILL_HORIZONTAL);
		selection.setLayoutData(selectionData);
		selection.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ObjectFlowPropertiesEditionPartForm.this, UmlViewsRepository.ObjectFlow.Properties.selection, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getSelection()));
			}

		});
		selection.setID(UmlViewsRepository.ObjectFlow.Properties.selection);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ObjectFlow.Properties.selection, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.name);
		if (readOnly && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
		Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
		return Boolean.valueOf(isLeaf.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
		if (newValue != null) {
			isLeaf.setSelection(newValue.booleanValue());
		} else {
			isLeaf.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.isLeaf);
		if (readOnly && isLeaf.isEnabled()) {
			isLeaf.setEnabled(false);
			isLeaf.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !isLeaf.isEnabled()) {
			isLeaf.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#getSource()
	 * @generated
	 */
	public EObject getSource() {
		if (source.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) source.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#initSource(EObjectFlatComboSettings)
	 */
	public void initSource(EObjectFlatComboSettings settings) {
		source.setInput(settings);
		if (current != null) {
			source.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.source);
		if (readOnly && source.isEnabled()) {
			source.setEnabled(false);
			source.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !source.isEnabled()) {
			source.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setSource(EObject newValue)
	 * @generated
	 */
	public void setSource(EObject newValue) {
		if (newValue != null) {
			source.setSelection(new StructuredSelection(newValue));
		} else {
			source.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.source);
		if (readOnly && source.isEnabled()) {
			source.setEnabled(false);
			source.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !source.isEnabled()) {
			source.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setSourceButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSourceButtonMode(ButtonsModeEnum newValue) {
		source.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addFilterSource(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSource(ViewerFilter filter) {
		source.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addBusinessFilterSource(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSource(ViewerFilter filter) {
		source.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#getTarget()
	 * @generated
	 */
	public EObject getTarget() {
		if (target.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) target.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#initTarget(EObjectFlatComboSettings)
	 */
	public void initTarget(EObjectFlatComboSettings settings) {
		target.setInput(settings);
		if (current != null) {
			target.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.target);
		if (readOnly && target.isEnabled()) {
			target.setEnabled(false);
			target.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !target.isEnabled()) {
			target.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setTarget(EObject newValue)
	 * @generated
	 */
	public void setTarget(EObject newValue) {
		if (newValue != null) {
			target.setSelection(new StructuredSelection(newValue));
		} else {
			target.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.target);
		if (readOnly && target.isEnabled()) {
			target.setEnabled(false);
			target.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !target.isEnabled()) {
			target.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setTargetButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTargetButtonMode(ButtonsModeEnum newValue) {
		target.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addFilterTarget(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTarget(ViewerFilter filter) {
		target.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addBusinessFilterTarget(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTarget(ViewerFilter filter) {
		target.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#initRedefinedEdge(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedEdge(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedEdge.setContentProvider(contentProvider);
		redefinedEdge.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.redefinedEdge);
		if (readOnly && redefinedEdge.getTable().isEnabled()) {
			redefinedEdge.setEnabled(false);
			redefinedEdge.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !redefinedEdge.getTable().isEnabled()) {
			redefinedEdge.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#updateRedefinedEdge()
	 * @generated
	 */
	public void updateRedefinedEdge() {
	redefinedEdge.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addFilterRedefinedEdge(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRedefinedEdge(ViewerFilter filter) {
		redefinedEdgeFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addBusinessFilterRedefinedEdge(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRedefinedEdge(ViewerFilter filter) {
		redefinedEdgeBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#isContainedInRedefinedEdgeTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInRedefinedEdgeTable(EObject element) {
		return ((ReferencesTableSettings)redefinedEdge.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#initInPartition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInPartition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		inPartition.setContentProvider(contentProvider);
		inPartition.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.inPartition);
		if (readOnly && inPartition.getTable().isEnabled()) {
			inPartition.setEnabled(false);
			inPartition.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !inPartition.getTable().isEnabled()) {
			inPartition.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#updateInPartition()
	 * @generated
	 */
	public void updateInPartition() {
	inPartition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addFilterInPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInPartition(ViewerFilter filter) {
		inPartitionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addBusinessFilterInPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInPartition(ViewerFilter filter) {
		inPartitionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#isContainedInInPartitionTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInInPartitionTable(EObject element) {
		return ((ReferencesTableSettings)inPartition.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#getInterrupts()
	 * @generated
	 */
	public EObject getInterrupts() {
		if (interrupts.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) interrupts.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#initInterrupts(EObjectFlatComboSettings)
	 */
	public void initInterrupts(EObjectFlatComboSettings settings) {
		interrupts.setInput(settings);
		if (current != null) {
			interrupts.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.interrupts);
		if (readOnly && interrupts.isEnabled()) {
			interrupts.setEnabled(false);
			interrupts.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !interrupts.isEnabled()) {
			interrupts.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setInterrupts(EObject newValue)
	 * @generated
	 */
	public void setInterrupts(EObject newValue) {
		if (newValue != null) {
			interrupts.setSelection(new StructuredSelection(newValue));
		} else {
			interrupts.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.interrupts);
		if (readOnly && interrupts.isEnabled()) {
			interrupts.setEnabled(false);
			interrupts.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !interrupts.isEnabled()) {
			interrupts.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setInterruptsButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInterruptsButtonMode(ButtonsModeEnum newValue) {
		interrupts.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addFilterInterrupts(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInterrupts(ViewerFilter filter) {
		interrupts.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addBusinessFilterInterrupts(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInterrupts(ViewerFilter filter) {
		interrupts.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#getInStructuredNode()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#initInStructuredNode(EObjectFlatComboSettings)
	 */
	public void initInStructuredNode(EObjectFlatComboSettings settings) {
		inStructuredNode.setInput(settings);
		if (current != null) {
			inStructuredNode.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.inStructuredNode);
		if (readOnly && inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(false);
			inStructuredNode.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setInStructuredNode(EObject newValue)
	 * @generated
	 */
	public void setInStructuredNode(EObject newValue) {
		if (newValue != null) {
			inStructuredNode.setSelection(new StructuredSelection(newValue));
		} else {
			inStructuredNode.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.inStructuredNode);
		if (readOnly && inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(false);
			inStructuredNode.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !inStructuredNode.isEnabled()) {
			inStructuredNode.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setInStructuredNodeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInStructuredNodeButtonMode(ButtonsModeEnum newValue) {
		inStructuredNode.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addFilterInStructuredNode(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInStructuredNode(ViewerFilter filter) {
		inStructuredNode.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addBusinessFilterInStructuredNode(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInStructuredNode(ViewerFilter filter) {
		inStructuredNode.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#getActivity()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#initActivity(EObjectFlatComboSettings)
	 */
	public void initActivity(EObjectFlatComboSettings settings) {
		activity.setInput(settings);
		if (current != null) {
			activity.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.activity);
		if (readOnly && activity.isEnabled()) {
			activity.setEnabled(false);
			activity.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !activity.isEnabled()) {
			activity.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setActivity(EObject newValue)
	 * @generated
	 */
	public void setActivity(EObject newValue) {
		if (newValue != null) {
			activity.setSelection(new StructuredSelection(newValue));
		} else {
			activity.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.activity);
		if (readOnly && activity.isEnabled()) {
			activity.setEnabled(false);
			activity.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !activity.isEnabled()) {
			activity.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setActivityButtonMode(ButtonsModeEnum newValue)
	 */
	public void setActivityButtonMode(ButtonsModeEnum newValue) {
		activity.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addFilterActivity(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToActivity(ViewerFilter filter) {
		activity.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addBusinessFilterActivity(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToActivity(ViewerFilter filter) {
		activity.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#getIsMulticast()
	 * @generated
	 */
	public Boolean getIsMulticast() {
		return Boolean.valueOf(isMulticast.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setIsMulticast(Boolean newValue)
	 * @generated
	 */
	public void setIsMulticast(Boolean newValue) {
		if (newValue != null) {
			isMulticast.setSelection(newValue.booleanValue());
		} else {
			isMulticast.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.isMulticast);
		if (readOnly && isMulticast.isEnabled()) {
			isMulticast.setEnabled(false);
			isMulticast.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !isMulticast.isEnabled()) {
			isMulticast.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#getIsMultireceive()
	 * @generated
	 */
	public Boolean getIsMultireceive() {
		return Boolean.valueOf(isMultireceive.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setIsMultireceive(Boolean newValue)
	 * @generated
	 */
	public void setIsMultireceive(Boolean newValue) {
		if (newValue != null) {
			isMultireceive.setSelection(newValue.booleanValue());
		} else {
			isMultireceive.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.isMultireceive);
		if (readOnly && isMultireceive.isEnabled()) {
			isMultireceive.setEnabled(false);
			isMultireceive.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !isMultireceive.isEnabled()) {
			isMultireceive.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#getTransformation()
	 * @generated
	 */
	public EObject getTransformation() {
		if (transformation.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) transformation.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#initTransformation(EObjectFlatComboSettings)
	 */
	public void initTransformation(EObjectFlatComboSettings settings) {
		transformation.setInput(settings);
		if (current != null) {
			transformation.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.transformation);
		if (readOnly && transformation.isEnabled()) {
			transformation.setEnabled(false);
			transformation.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !transformation.isEnabled()) {
			transformation.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setTransformation(EObject newValue)
	 * @generated
	 */
	public void setTransformation(EObject newValue) {
		if (newValue != null) {
			transformation.setSelection(new StructuredSelection(newValue));
		} else {
			transformation.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.transformation);
		if (readOnly && transformation.isEnabled()) {
			transformation.setEnabled(false);
			transformation.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !transformation.isEnabled()) {
			transformation.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setTransformationButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTransformationButtonMode(ButtonsModeEnum newValue) {
		transformation.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addFilterTransformation(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTransformation(ViewerFilter filter) {
		transformation.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addBusinessFilterTransformation(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTransformation(ViewerFilter filter) {
		transformation.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#getSelection()
	 * @generated
	 */
	public EObject getSelection() {
		if (selection.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) selection.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#initSelection(EObjectFlatComboSettings)
	 */
	public void initSelection(EObjectFlatComboSettings settings) {
		selection.setInput(settings);
		if (current != null) {
			selection.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.selection);
		if (readOnly && selection.isEnabled()) {
			selection.setEnabled(false);
			selection.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !selection.isEnabled()) {
			selection.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setSelection(EObject newValue)
	 * @generated
	 */
	public void setSelection(EObject newValue) {
		if (newValue != null) {
			selection.setSelection(new StructuredSelection(newValue));
		} else {
			selection.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ObjectFlow.Properties.selection);
		if (readOnly && selection.isEnabled()) {
			selection.setEnabled(false);
			selection.setToolTipText(UmlMessages.ObjectFlow_ReadOnly);
		} else if (!readOnly && !selection.isEnabled()) {
			selection.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#setSelectionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSelectionButtonMode(ButtonsModeEnum newValue) {
		selection.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addFilterSelection(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSelection(ViewerFilter filter) {
		selection.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ObjectFlowPropertiesEditionPart#addBusinessFilterSelection(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSelection(ViewerFilter filter) {
		selection.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.ObjectFlow_Part_Title;
	}



}
