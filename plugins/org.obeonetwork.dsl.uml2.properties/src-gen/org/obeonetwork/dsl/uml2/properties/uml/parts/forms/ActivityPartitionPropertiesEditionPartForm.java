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

import org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ActivityPartitionPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, ActivityPartitionPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer inActivity;
	protected Button isDimension;
	protected Button isExternal;
	protected ReferencesTable node;
	protected List<ViewerFilter> nodeBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> nodeFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer superPartition;
	protected EObjectFlatComboViewer represents;
	protected ReferencesTable edge;
	protected List<ViewerFilter> edgeBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> edgeFilters = new ArrayList<ViewerFilter>();



	/**
	 * For {@link ISection} use only.
	 */
	public ActivityPartitionPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ActivityPartitionPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence activityPartitionStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = activityPartitionStep.addStep(UmlViewsRepository.ActivityPartition.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.ActivityPartition.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.ActivityPartition.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.ActivityPartition.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.ActivityPartition.Properties.inActivity);
		propertiesStep.addStep(UmlViewsRepository.ActivityPartition.Properties.isDimension);
		propertiesStep.addStep(UmlViewsRepository.ActivityPartition.Properties.isExternal);
		propertiesStep.addStep(UmlViewsRepository.ActivityPartition.Properties.node);
		propertiesStep.addStep(UmlViewsRepository.ActivityPartition.Properties.superPartition);
		propertiesStep.addStep(UmlViewsRepository.ActivityPartition.Properties.represents);
		propertiesStep.addStep(UmlViewsRepository.ActivityPartition.Properties.edge);
		
		
		composer = new PartComposer(activityPartitionStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.ActivityPartition.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ActivityPartition.Properties.name) {
					return createNameText(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ActivityPartition.Properties.visibility) {
					return createVisibilityEMFComboViewer(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ActivityPartition.Properties.clientDependency) {
					return createClientDependencyReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ActivityPartition.Properties.inActivity) {
					return createInActivityFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ActivityPartition.Properties.isDimension) {
					return createIsDimensionCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ActivityPartition.Properties.isExternal) {
					return createIsExternalCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ActivityPartition.Properties.node) {
					return createNodeReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ActivityPartition.Properties.superPartition) {
					return createSuperPartitionFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ActivityPartition.Properties.represents) {
					return createRepresentsFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ActivityPartition.Properties.edge) {
					return createEdgeReferencesTable(widgetFactory, parent);
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
		propertiesSection.setText(UmlMessages.ActivityPartitionPropertiesEditionPart_PropertiesGroupLabel);
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
		createDescription(parent, UmlViewsRepository.ActivityPartition.Properties.name, UmlMessages.ActivityPartitionPropertiesEditionPart_NameLabel);
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
							ActivityPartitionPropertiesEditionPartForm.this,
							UmlViewsRepository.ActivityPartition.Properties.name,
							PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									ActivityPartitionPropertiesEditionPartForm.this,
									UmlViewsRepository.ActivityPartition.Properties.name,
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
									ActivityPartitionPropertiesEditionPartForm.this,
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}
		});
		EditingUtils.setID(name, UmlViewsRepository.ActivityPartition.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActivityPartition.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		createDescription(parent, UmlViewsRepository.ActivityPartition.Properties.visibility, UmlMessages.ActivityPartitionPropertiesEditionPart_VisibilityLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.ActivityPartition.Properties.visibility);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActivityPartition.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.clientDependency = new ReferencesTable(getDescription(UmlViewsRepository.ActivityPartition.Properties.clientDependency, UmlMessages.ActivityPartitionPropertiesEditionPart_ClientDependencyLabel), new ReferencesTableListener	() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActivityPartition.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
		this.clientDependency.createControls(parent, widgetFactory);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.ActivityPartition.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createInActivityFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ActivityPartition.Properties.inActivity, UmlMessages.ActivityPartitionPropertiesEditionPart_InActivityLabel);
		inActivity = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ActivityPartition.Properties.inActivity, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(inActivity);
		inActivity.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData inActivityData = new GridData(GridData.FILL_HORIZONTAL);
		inActivity.setLayoutData(inActivityData);
		inActivity.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.inActivity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getInActivity()));
			}

		});
		inActivity.setID(UmlViewsRepository.ActivityPartition.Properties.inActivity);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActivityPartition.Properties.inActivity, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsDimensionCheckbox(FormToolkit widgetFactory, Composite parent) {
		isDimension = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.ActivityPartition.Properties.isDimension, UmlMessages.ActivityPartitionPropertiesEditionPart_IsDimensionLabel), SWT.CHECK);
		isDimension.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.isDimension, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isDimension.getSelection())));
			}

		});
		GridData isDimensionData = new GridData(GridData.FILL_HORIZONTAL);
		isDimensionData.horizontalSpan = 2;
		isDimension.setLayoutData(isDimensionData);
		EditingUtils.setID(isDimension, UmlViewsRepository.ActivityPartition.Properties.isDimension);
		EditingUtils.setEEFtype(isDimension, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActivityPartition.Properties.isDimension, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsExternalCheckbox(FormToolkit widgetFactory, Composite parent) {
		isExternal = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.ActivityPartition.Properties.isExternal, UmlMessages.ActivityPartitionPropertiesEditionPart_IsExternalLabel), SWT.CHECK);
		isExternal.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.isExternal, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isExternal.getSelection())));
			}

		});
		GridData isExternalData = new GridData(GridData.FILL_HORIZONTAL);
		isExternalData.horizontalSpan = 2;
		isExternal.setLayoutData(isExternalData);
		EditingUtils.setID(isExternal, UmlViewsRepository.ActivityPartition.Properties.isExternal);
		EditingUtils.setEEFtype(isExternal, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActivityPartition.Properties.isExternal, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createNodeReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.node = new ReferencesTable(getDescription(UmlViewsRepository.ActivityPartition.Properties.node, UmlMessages.ActivityPartitionPropertiesEditionPart_NodeLabel), new ReferencesTableListener	() {
			public void handleAdd() { addNode(); }
			public void handleEdit(EObject element) { editNode(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveNode(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromNode(element); }
			public void navigateTo(EObject element) { }
		});
		this.node.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActivityPartition.Properties.node, UmlViewsRepository.FORM_KIND));
		this.node.createControls(parent, widgetFactory);
		this.node.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.node, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData nodeData = new GridData(GridData.FILL_HORIZONTAL);
		nodeData.horizontalSpan = 3;
		this.node.setLayoutData(nodeData);
		this.node.disableMove();
		node.setID(UmlViewsRepository.ActivityPartition.Properties.node);
		node.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addNode() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(node.getInput(), nodeFilters, nodeBusinessFilters,
		"node", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.node,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				node.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveNode(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.node, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		node.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromNode(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.node, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		node.refresh();
	}

	/**
	 * @generated
	 */
	protected void editNode(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				node.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createSuperPartitionFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ActivityPartition.Properties.superPartition, UmlMessages.ActivityPartitionPropertiesEditionPart_SuperPartitionLabel);
		superPartition = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ActivityPartition.Properties.superPartition, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(superPartition);
		superPartition.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData superPartitionData = new GridData(GridData.FILL_HORIZONTAL);
		superPartition.setLayoutData(superPartitionData);
		superPartition.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.superPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getSuperPartition()));
			}

		});
		superPartition.setID(UmlViewsRepository.ActivityPartition.Properties.superPartition);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActivityPartition.Properties.superPartition, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createRepresentsFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ActivityPartition.Properties.represents, UmlMessages.ActivityPartitionPropertiesEditionPart_RepresentsLabel);
		represents = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ActivityPartition.Properties.represents, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(represents);
		represents.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData representsData = new GridData(GridData.FILL_HORIZONTAL);
		represents.setLayoutData(representsData);
		represents.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.represents, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getRepresents()));
			}

		});
		represents.setID(UmlViewsRepository.ActivityPartition.Properties.represents);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActivityPartition.Properties.represents, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createEdgeReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.edge = new ReferencesTable(getDescription(UmlViewsRepository.ActivityPartition.Properties.edge, UmlMessages.ActivityPartitionPropertiesEditionPart_EdgeLabel), new ReferencesTableListener	() {
			public void handleAdd() { addEdge(); }
			public void handleEdit(EObject element) { editEdge(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveEdge(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromEdge(element); }
			public void navigateTo(EObject element) { }
		});
		this.edge.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ActivityPartition.Properties.edge, UmlViewsRepository.FORM_KIND));
		this.edge.createControls(parent, widgetFactory);
		this.edge.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.edge, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData edgeData = new GridData(GridData.FILL_HORIZONTAL);
		edgeData.horizontalSpan = 3;
		this.edge.setLayoutData(edgeData);
		this.edge.disableMove();
		edge.setID(UmlViewsRepository.ActivityPartition.Properties.edge);
		edge.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addEdge() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(edge.getInput(), edgeFilters, edgeBusinessFilters,
		"edge", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.edge,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				edge.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveEdge(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.edge, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		edge.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromEdge(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.edge, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		edge.refresh();
	}

	/**
	 * @generated
	 */
	protected void editEdge(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				edge.refresh();
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActivityPartition.Properties.name);
		if (readOnly && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(UmlMessages.ActivityPartition_ReadOnly);
		} else if (!readOnly && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
		Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.ActivityPartition.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.ActivityPartition_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.ActivityPartition.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.ActivityPartition_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ActivityPartition.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.ActivityPartition_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#getInActivity()
	 * @generated
	 */
	public EObject getInActivity() {
		if (inActivity.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) inActivity.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#initInActivity(EObjectFlatComboSettings)
	 */
	public void initInActivity(EObjectFlatComboSettings settings) {
		inActivity.setInput(settings);
		if (current != null) {
			inActivity.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActivityPartition.Properties.inActivity);
		if (readOnly && inActivity.isEnabled()) {
			inActivity.setEnabled(false);
			inActivity.setToolTipText(UmlMessages.ActivityPartition_ReadOnly);
		} else if (!readOnly && !inActivity.isEnabled()) {
			inActivity.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setInActivity(EObject newValue)
	 * @generated
	 */
	public void setInActivity(EObject newValue) {
		if (newValue != null) {
			inActivity.setSelection(new StructuredSelection(newValue));
		} else {
			inActivity.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActivityPartition.Properties.inActivity);
		if (readOnly && inActivity.isEnabled()) {
			inActivity.setEnabled(false);
			inActivity.setToolTipText(UmlMessages.ActivityPartition_ReadOnly);
		} else if (!readOnly && !inActivity.isEnabled()) {
			inActivity.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setInActivityButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInActivityButtonMode(ButtonsModeEnum newValue) {
		inActivity.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addFilterInActivity(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInActivity(ViewerFilter filter) {
		inActivity.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addBusinessFilterInActivity(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInActivity(ViewerFilter filter) {
		inActivity.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#getIsDimension()
	 * @generated
	 */
	public Boolean getIsDimension() {
		return Boolean.valueOf(isDimension.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setIsDimension(Boolean newValue)
	 * @generated
	 */
	public void setIsDimension(Boolean newValue) {
		if (newValue != null) {
			isDimension.setSelection(newValue.booleanValue());
		} else {
			isDimension.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActivityPartition.Properties.isDimension);
		if (readOnly && isDimension.isEnabled()) {
			isDimension.setEnabled(false);
			isDimension.setToolTipText(UmlMessages.ActivityPartition_ReadOnly);
		} else if (!readOnly && !isDimension.isEnabled()) {
			isDimension.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#getIsExternal()
	 * @generated
	 */
	public Boolean getIsExternal() {
		return Boolean.valueOf(isExternal.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setIsExternal(Boolean newValue)
	 * @generated
	 */
	public void setIsExternal(Boolean newValue) {
		if (newValue != null) {
			isExternal.setSelection(newValue.booleanValue());
		} else {
			isExternal.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActivityPartition.Properties.isExternal);
		if (readOnly && isExternal.isEnabled()) {
			isExternal.setEnabled(false);
			isExternal.setToolTipText(UmlMessages.ActivityPartition_ReadOnly);
		} else if (!readOnly && !isExternal.isEnabled()) {
			isExternal.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#initNode(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initNode(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		node.setContentProvider(contentProvider);
		node.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ActivityPartition.Properties.node);
		if (readOnly && node.getTable().isEnabled()) {
			node.setEnabled(false);
			node.setToolTipText(UmlMessages.ActivityPartition_ReadOnly);
		} else if (!readOnly && !node.getTable().isEnabled()) {
			node.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#updateNode()
	 * @generated
	 */
	public void updateNode() {
	node.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addFilterNode(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToNode(ViewerFilter filter) {
		nodeFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addBusinessFilterNode(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToNode(ViewerFilter filter) {
		nodeBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#isContainedInNodeTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInNodeTable(EObject element) {
		return ((ReferencesTableSettings)node.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#getSuperPartition()
	 * @generated
	 */
	public EObject getSuperPartition() {
		if (superPartition.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) superPartition.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#initSuperPartition(EObjectFlatComboSettings)
	 */
	public void initSuperPartition(EObjectFlatComboSettings settings) {
		superPartition.setInput(settings);
		if (current != null) {
			superPartition.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActivityPartition.Properties.superPartition);
		if (readOnly && superPartition.isEnabled()) {
			superPartition.setEnabled(false);
			superPartition.setToolTipText(UmlMessages.ActivityPartition_ReadOnly);
		} else if (!readOnly && !superPartition.isEnabled()) {
			superPartition.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setSuperPartition(EObject newValue)
	 * @generated
	 */
	public void setSuperPartition(EObject newValue) {
		if (newValue != null) {
			superPartition.setSelection(new StructuredSelection(newValue));
		} else {
			superPartition.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActivityPartition.Properties.superPartition);
		if (readOnly && superPartition.isEnabled()) {
			superPartition.setEnabled(false);
			superPartition.setToolTipText(UmlMessages.ActivityPartition_ReadOnly);
		} else if (!readOnly && !superPartition.isEnabled()) {
			superPartition.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setSuperPartitionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSuperPartitionButtonMode(ButtonsModeEnum newValue) {
		superPartition.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addFilterSuperPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSuperPartition(ViewerFilter filter) {
		superPartition.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addBusinessFilterSuperPartition(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSuperPartition(ViewerFilter filter) {
		superPartition.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#getRepresents()
	 * @generated
	 */
	public EObject getRepresents() {
		if (represents.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) represents.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#initRepresents(EObjectFlatComboSettings)
	 */
	public void initRepresents(EObjectFlatComboSettings settings) {
		represents.setInput(settings);
		if (current != null) {
			represents.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActivityPartition.Properties.represents);
		if (readOnly && represents.isEnabled()) {
			represents.setEnabled(false);
			represents.setToolTipText(UmlMessages.ActivityPartition_ReadOnly);
		} else if (!readOnly && !represents.isEnabled()) {
			represents.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setRepresents(EObject newValue)
	 * @generated
	 */
	public void setRepresents(EObject newValue) {
		if (newValue != null) {
			represents.setSelection(new StructuredSelection(newValue));
		} else {
			represents.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ActivityPartition.Properties.represents);
		if (readOnly && represents.isEnabled()) {
			represents.setEnabled(false);
			represents.setToolTipText(UmlMessages.ActivityPartition_ReadOnly);
		} else if (!readOnly && !represents.isEnabled()) {
			represents.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setRepresentsButtonMode(ButtonsModeEnum newValue)
	 */
	public void setRepresentsButtonMode(ButtonsModeEnum newValue) {
		represents.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addFilterRepresents(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRepresents(ViewerFilter filter) {
		represents.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addBusinessFilterRepresents(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRepresents(ViewerFilter filter) {
		represents.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#initEdge(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initEdge(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		edge.setContentProvider(contentProvider);
		edge.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ActivityPartition.Properties.edge);
		if (readOnly && edge.getTable().isEnabled()) {
			edge.setEnabled(false);
			edge.setToolTipText(UmlMessages.ActivityPartition_ReadOnly);
		} else if (!readOnly && !edge.getTable().isEnabled()) {
			edge.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#updateEdge()
	 * @generated
	 */
	public void updateEdge() {
	edge.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addFilterEdge(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEdge(ViewerFilter filter) {
		edgeFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addBusinessFilterEdge(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEdge(ViewerFilter filter) {
		edgeBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#isContainedInEdgeTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInEdgeTable(EObject element) {
		return ((ReferencesTableSettings)edge.getInput()).contains(element);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.ActivityPartition_Part_Title;
	}



}
