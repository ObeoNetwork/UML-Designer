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
package org.eclipse.uml2.uml.parts.forms;

// Start of user code for imports
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
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
import org.eclipse.uml2.uml.parts.ActivityPartitionPropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;
import org.eclipse.uml2.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ActivityPartitionPropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, ActivityPartitionPropertiesEditionPart {

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
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public ActivityPartitionPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
					return 		createNameText(widgetFactory, parent);
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
	 * 
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

	
	protected Composite createNameText(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ActivityPartitionPropertiesEditionPart_NameLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ActivityPartition.Properties.name, UmlViewsRepository.FORM_KIND));
		name = widgetFactory.createText(parent, ""); //$NON-NLS-1$
		name.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		widgetFactory.paintBordersFor(parent);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(nameData);
		name.addFocusListener(new FocusAdapter() {
			/**
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
			}
		});
		name.addKeyListener(new KeyAdapter() {
			/**
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
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

	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ActivityPartitionPropertiesEditionPart_VisibilityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ActivityPartition.Properties.visibility, UmlViewsRepository.FORM_KIND));
		visibility = new EMFComboViewer(parent);
		visibility.setContentProvider(new ArrayContentProvider());
		visibility.setLabelProvider(new AdapterFactoryLabelProvider(new EcoreAdapterFactory()));
		GridData visibilityData = new GridData(GridData.FILL_HORIZONTAL);
		visibility.getCombo().setLayoutData(visibilityData);
		visibility.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 * 	
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
	 * 
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.clientDependency = new ReferencesTable(UmlMessages.ActivityPartitionPropertiesEditionPart_ClientDependencyLabel, new ReferencesTableListener	() {
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
	 * 
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
	 * 
	 */
	protected void moveClientDependency(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		clientDependency.refresh();
	}

	/**
	 * 
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
	 * 
	 */
	protected Composite createInActivityFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ActivityPartitionPropertiesEditionPart_InActivityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ActivityPartition.Properties.inActivity, UmlViewsRepository.FORM_KIND));
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

	
	protected Composite createIsDimensionCheckbox(FormToolkit widgetFactory, Composite parent) {
		isDimension = widgetFactory.createButton(parent, UmlMessages.ActivityPartitionPropertiesEditionPart_IsDimensionLabel, SWT.CHECK);
		isDimension.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
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

	
	protected Composite createIsExternalCheckbox(FormToolkit widgetFactory, Composite parent) {
		isExternal = widgetFactory.createButton(parent, UmlMessages.ActivityPartitionPropertiesEditionPart_IsExternalLabel, SWT.CHECK);
		isExternal.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
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
	 * 
	 */
	protected Composite createNodeReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.node = new ReferencesTable(UmlMessages.ActivityPartitionPropertiesEditionPart_NodeLabel, new ReferencesTableListener	() {
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
	 * 
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
	 * 
	 */
	protected void moveNode(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.node, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		node.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromNode(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.node, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		node.refresh();
	}

	/**
	 * 
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
	 * 
	 */
	protected Composite createSuperPartitionFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ActivityPartitionPropertiesEditionPart_SuperPartitionLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ActivityPartition.Properties.superPartition, UmlViewsRepository.FORM_KIND));
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
	 * 
	 */
	protected Composite createRepresentsFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ActivityPartitionPropertiesEditionPart_RepresentsLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ActivityPartition.Properties.represents, UmlViewsRepository.FORM_KIND));
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
	 * 
	 */
	protected Composite createEdgeReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.edge = new ReferencesTable(UmlMessages.ActivityPartitionPropertiesEditionPart_EdgeLabel, new ReferencesTableListener	() {
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
	 * 
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
	 * 
	 */
	protected void moveEdge(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.edge, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		edge.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromEdge(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ActivityPartitionPropertiesEditionPartForm.this, UmlViewsRepository.ActivityPartition.Properties.edge, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		edge.refresh();
	}

	/**
	 * 
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
	 * 
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
		// Start of user code for tab synchronization
		
		// End of user code
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#getName()
	 * 
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setName(String newValue)
	 * 
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#getVisibility()
	 * 
	 */
	public Enumerator getVisibility() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#initVisibility(EEnum eenum, Enumerator current)
	 */
	public void initVisibility(EEnum eenum, Enumerator current) {
		visibility.setInput(eenum.getELiterals());
		visibility.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * 
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
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
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#updateClientDependency()
	 * 
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * 
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#getInActivity()
	 * 
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
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setInActivity(EObject newValue)
	 * 
	 */
	public void setInActivity(EObject newValue) {
		if (newValue != null) {
			inActivity.setSelection(new StructuredSelection(newValue));
		} else {
			inActivity.setSelection(new StructuredSelection()); //$NON-NLS-1$
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
	 * 
	 */
	public void addFilterToInActivity(ViewerFilter filter) {
		inActivity.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addBusinessFilterInActivity(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToInActivity(ViewerFilter filter) {
		inActivity.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#getIsDimension()
	 * 
	 */
	public Boolean getIsDimension() {
		return Boolean.valueOf(isDimension.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setIsDimension(Boolean newValue)
	 * 
	 */
	public void setIsDimension(Boolean newValue) {
		if (newValue != null) {
			isDimension.setSelection(newValue.booleanValue());
		} else {
			isDimension.setSelection(false);
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#getIsExternal()
	 * 
	 */
	public Boolean getIsExternal() {
		return Boolean.valueOf(isExternal.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setIsExternal(Boolean newValue)
	 * 
	 */
	public void setIsExternal(Boolean newValue) {
		if (newValue != null) {
			isExternal.setSelection(newValue.booleanValue());
		} else {
			isExternal.setSelection(false);
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
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#updateNode()
	 * 
	 */
	public void updateNode() {
	node.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addFilterNode(ViewerFilter filter)
	 * 
	 */
	public void addFilterToNode(ViewerFilter filter) {
		nodeFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addBusinessFilterNode(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToNode(ViewerFilter filter) {
		nodeBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#isContainedInNodeTable(EObject element)
	 * 
	 */
	public boolean isContainedInNodeTable(EObject element) {
		return ((ReferencesTableSettings)node.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#getSuperPartition()
	 * 
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
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setSuperPartition(EObject newValue)
	 * 
	 */
	public void setSuperPartition(EObject newValue) {
		if (newValue != null) {
			superPartition.setSelection(new StructuredSelection(newValue));
		} else {
			superPartition.setSelection(new StructuredSelection()); //$NON-NLS-1$
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
	 * 
	 */
	public void addFilterToSuperPartition(ViewerFilter filter) {
		superPartition.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addBusinessFilterSuperPartition(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToSuperPartition(ViewerFilter filter) {
		superPartition.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#getRepresents()
	 * 
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
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#setRepresents(EObject newValue)
	 * 
	 */
	public void setRepresents(EObject newValue) {
		if (newValue != null) {
			represents.setSelection(new StructuredSelection(newValue));
		} else {
			represents.setSelection(new StructuredSelection()); //$NON-NLS-1$
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
	 * 
	 */
	public void addFilterToRepresents(ViewerFilter filter) {
		represents.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addBusinessFilterRepresents(ViewerFilter filter)
	 * 
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
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#updateEdge()
	 * 
	 */
	public void updateEdge() {
	edge.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addFilterEdge(ViewerFilter filter)
	 * 
	 */
	public void addFilterToEdge(ViewerFilter filter) {
		edgeFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#addBusinessFilterEdge(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToEdge(ViewerFilter filter) {
		edgeBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ActivityPartitionPropertiesEditionPart#isContainedInEdgeTable(EObject element)
	 * 
	 */
	public boolean isContainedInEdgeTable(EObject element) {
		return ((ReferencesTableSettings)edge.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.ActivityPartition_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
