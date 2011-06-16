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
import org.eclipse.uml2.uml.parts.LoopNodePropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;
import org.eclipse.uml2.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class LoopNodePropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, LoopNodePropertiesEditionPart {

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
	protected EObjectFlatComboViewer inActivity;
	protected Button mustIsolate;
	protected Button isTestedFirst;
		protected ReferencesTable bodyPart;
		protected List<ViewerFilter> bodyPartBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> bodyPartFilters = new ArrayList<ViewerFilter>();
		protected ReferencesTable setupPart;
		protected List<ViewerFilter> setupPartBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> setupPartFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer decider;
		protected ReferencesTable test;
		protected List<ViewerFilter> testBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> testFilters = new ArrayList<ViewerFilter>();
		protected ReferencesTable loopVariable;
		protected List<ViewerFilter> loopVariableBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> loopVariableFilters = new ArrayList<ViewerFilter>();
		protected ReferencesTable bodyOutput;
		protected List<ViewerFilter> bodyOutputBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> bodyOutputFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public LoopNodePropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence loopNodeStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = loopNodeStep.addStep(UmlViewsRepository.LoopNode.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.isLeaf);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.inStructuredNode);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.activity);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.outgoing);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.incoming);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.inPartition);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.inInterruptibleRegion);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.redefinedNode);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.inActivity);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.mustIsolate);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.isTestedFirst);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.bodyPart);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.setupPart);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.decider);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.test);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.loopVariable);
		propertiesStep.addStep(UmlViewsRepository.LoopNode.Properties.bodyOutput);
		
		
		composer = new PartComposer(loopNodeStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.LoopNode.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.name) {
					return 		createNameText(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.visibility) {
					return createVisibilityEMFComboViewer(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.clientDependency) {
					return createClientDependencyReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.isLeaf) {
					return createIsLeafCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.inStructuredNode) {
					return createInStructuredNodeFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.activity) {
					return createActivityFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.outgoing) {
					return createOutgoingReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.incoming) {
					return createIncomingReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.inPartition) {
					return createInPartitionReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.inInterruptibleRegion) {
					return createInInterruptibleRegionReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.redefinedNode) {
					return createRedefinedNodeReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.inActivity) {
					return createInActivityFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.mustIsolate) {
					return createMustIsolateCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.isTestedFirst) {
					return createIsTestedFirstCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.bodyPart) {
					return createBodyPartReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.setupPart) {
					return createSetupPartReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.decider) {
					return createDeciderFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.test) {
					return createTestReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.loopVariable) {
					return createLoopVariableReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.LoopNode.Properties.bodyOutput) {
					return createBodyOutputReferencesTable(widgetFactory, parent);
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
		propertiesSection.setText(UmlMessages.LoopNodePropertiesEditionPart_PropertiesGroupLabel);
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
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.LoopNodePropertiesEditionPart_NameLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.LoopNode.Properties.name, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}
		});
		EditingUtils.setID(name, UmlViewsRepository.LoopNode.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.LoopNodePropertiesEditionPart_VisibilityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.LoopNode.Properties.visibility, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.LoopNode.Properties.visibility);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.clientDependency = new ReferencesTable(UmlMessages.LoopNodePropertiesEditionPart_ClientDependencyLabel, new ReferencesTableListener	() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
		this.clientDependency.createControls(parent, widgetFactory);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.LoopNode.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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

	
	protected Composite createIsLeafCheckbox(FormToolkit widgetFactory, Composite parent) {
		isLeaf = widgetFactory.createButton(parent, UmlMessages.LoopNodePropertiesEditionPart_IsLeafLabel, SWT.CHECK);
		isLeaf.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
			}

		});
		GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
		isLeafData.horizontalSpan = 2;
		isLeaf.setLayoutData(isLeafData);
		EditingUtils.setID(isLeaf, UmlViewsRepository.LoopNode.Properties.isLeaf);
		EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.isLeaf, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createInStructuredNodeFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.LoopNodePropertiesEditionPart_InStructuredNodeLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.LoopNode.Properties.inStructuredNode, UmlViewsRepository.FORM_KIND));
		inStructuredNode = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.LoopNode.Properties.inStructuredNode, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.inStructuredNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getInStructuredNode()));
			}

		});
		inStructuredNode.setID(UmlViewsRepository.LoopNode.Properties.inStructuredNode);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.inStructuredNode, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createActivityFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.LoopNodePropertiesEditionPart_ActivityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.LoopNode.Properties.activity, UmlViewsRepository.FORM_KIND));
		activity = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.LoopNode.Properties.activity, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.activity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getActivity()));
			}

		});
		activity.setID(UmlViewsRepository.LoopNode.Properties.activity);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.activity, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createOutgoingReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.outgoing = new ReferencesTable(UmlMessages.LoopNodePropertiesEditionPart_OutgoingLabel, new ReferencesTableListener	() {
			public void handleAdd() { addOutgoing(); }
			public void handleEdit(EObject element) { editOutgoing(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveOutgoing(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromOutgoing(element); }
			public void navigateTo(EObject element) { }
		});
		this.outgoing.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.outgoing, UmlViewsRepository.FORM_KIND));
		this.outgoing.createControls(parent, widgetFactory);
		this.outgoing.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.outgoing, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData outgoingData = new GridData(GridData.FILL_HORIZONTAL);
		outgoingData.horizontalSpan = 3;
		this.outgoing.setLayoutData(outgoingData);
		this.outgoing.disableMove();
		outgoing.setID(UmlViewsRepository.LoopNode.Properties.outgoing);
		outgoing.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addOutgoing() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(outgoing.getInput(), outgoingFilters, outgoingBusinessFilters,
		"outgoing", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.outgoing,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				outgoing.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveOutgoing(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.outgoing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		outgoing.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromOutgoing(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.outgoing, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		outgoing.refresh();
	}

	/**
	 * 
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
	 * 
	 */
	protected Composite createIncomingReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.incoming = new ReferencesTable(UmlMessages.LoopNodePropertiesEditionPart_IncomingLabel, new ReferencesTableListener	() {
			public void handleAdd() { addIncoming(); }
			public void handleEdit(EObject element) { editIncoming(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveIncoming(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromIncoming(element); }
			public void navigateTo(EObject element) { }
		});
		this.incoming.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.incoming, UmlViewsRepository.FORM_KIND));
		this.incoming.createControls(parent, widgetFactory);
		this.incoming.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.incoming, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData incomingData = new GridData(GridData.FILL_HORIZONTAL);
		incomingData.horizontalSpan = 3;
		this.incoming.setLayoutData(incomingData);
		this.incoming.disableMove();
		incoming.setID(UmlViewsRepository.LoopNode.Properties.incoming);
		incoming.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addIncoming() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(incoming.getInput(), incomingFilters, incomingBusinessFilters,
		"incoming", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.incoming,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				incoming.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveIncoming(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.incoming, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		incoming.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromIncoming(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.incoming, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		incoming.refresh();
	}

	/**
	 * 
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
	 * 
	 */
	protected Composite createInPartitionReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.inPartition = new ReferencesTable(UmlMessages.LoopNodePropertiesEditionPart_InPartitionLabel, new ReferencesTableListener	() {
			public void handleAdd() { addInPartition(); }
			public void handleEdit(EObject element) { editInPartition(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveInPartition(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromInPartition(element); }
			public void navigateTo(EObject element) { }
		});
		this.inPartition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.inPartition, UmlViewsRepository.FORM_KIND));
		this.inPartition.createControls(parent, widgetFactory);
		this.inPartition.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.inPartition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData inPartitionData = new GridData(GridData.FILL_HORIZONTAL);
		inPartitionData.horizontalSpan = 3;
		this.inPartition.setLayoutData(inPartitionData);
		this.inPartition.disableMove();
		inPartition.setID(UmlViewsRepository.LoopNode.Properties.inPartition);
		inPartition.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addInPartition() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(inPartition.getInput(), inPartitionFilters, inPartitionBusinessFilters,
		"inPartition", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.inPartition,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				inPartition.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveInPartition(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		inPartition.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromInPartition(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		inPartition.refresh();
	}

	/**
	 * 
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
	 * 
	 */
	protected Composite createInInterruptibleRegionReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.inInterruptibleRegion = new ReferencesTable(UmlMessages.LoopNodePropertiesEditionPart_InInterruptibleRegionLabel, new ReferencesTableListener	() {
			public void handleAdd() { addInInterruptibleRegion(); }
			public void handleEdit(EObject element) { editInInterruptibleRegion(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveInInterruptibleRegion(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromInInterruptibleRegion(element); }
			public void navigateTo(EObject element) { }
		});
		this.inInterruptibleRegion.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.inInterruptibleRegion, UmlViewsRepository.FORM_KIND));
		this.inInterruptibleRegion.createControls(parent, widgetFactory);
		this.inInterruptibleRegion.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.inInterruptibleRegion, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData inInterruptibleRegionData = new GridData(GridData.FILL_HORIZONTAL);
		inInterruptibleRegionData.horizontalSpan = 3;
		this.inInterruptibleRegion.setLayoutData(inInterruptibleRegionData);
		this.inInterruptibleRegion.disableMove();
		inInterruptibleRegion.setID(UmlViewsRepository.LoopNode.Properties.inInterruptibleRegion);
		inInterruptibleRegion.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addInInterruptibleRegion() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(inInterruptibleRegion.getInput(), inInterruptibleRegionFilters, inInterruptibleRegionBusinessFilters,
		"inInterruptibleRegion", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.inInterruptibleRegion,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				inInterruptibleRegion.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveInInterruptibleRegion(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.inInterruptibleRegion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		inInterruptibleRegion.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromInInterruptibleRegion(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.inInterruptibleRegion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		inInterruptibleRegion.refresh();
	}

	/**
	 * 
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
	 * 
	 */
	protected Composite createRedefinedNodeReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.redefinedNode = new ReferencesTable(UmlMessages.LoopNodePropertiesEditionPart_RedefinedNodeLabel, new ReferencesTableListener	() {
			public void handleAdd() { addRedefinedNode(); }
			public void handleEdit(EObject element) { editRedefinedNode(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedNode(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRedefinedNode(element); }
			public void navigateTo(EObject element) { }
		});
		this.redefinedNode.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.redefinedNode, UmlViewsRepository.FORM_KIND));
		this.redefinedNode.createControls(parent, widgetFactory);
		this.redefinedNode.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.redefinedNode, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData redefinedNodeData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedNodeData.horizontalSpan = 3;
		this.redefinedNode.setLayoutData(redefinedNodeData);
		this.redefinedNode.disableMove();
		redefinedNode.setID(UmlViewsRepository.LoopNode.Properties.redefinedNode);
		redefinedNode.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addRedefinedNode() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(redefinedNode.getInput(), redefinedNodeFilters, redefinedNodeBusinessFilters,
		"redefinedNode", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.redefinedNode,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				redefinedNode.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveRedefinedNode(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.redefinedNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		redefinedNode.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromRedefinedNode(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.redefinedNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		redefinedNode.refresh();
	}

	/**
	 * 
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
	 * 
	 */
	protected Composite createInActivityFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.LoopNodePropertiesEditionPart_InActivityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.LoopNode.Properties.inActivity, UmlViewsRepository.FORM_KIND));
		inActivity = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.LoopNode.Properties.inActivity, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.inActivity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getInActivity()));
			}

		});
		inActivity.setID(UmlViewsRepository.LoopNode.Properties.inActivity);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.inActivity, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createMustIsolateCheckbox(FormToolkit widgetFactory, Composite parent) {
		mustIsolate = widgetFactory.createButton(parent, UmlMessages.LoopNodePropertiesEditionPart_MustIsolateLabel, SWT.CHECK);
		mustIsolate.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.mustIsolate, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(mustIsolate.getSelection())));
			}

		});
		GridData mustIsolateData = new GridData(GridData.FILL_HORIZONTAL);
		mustIsolateData.horizontalSpan = 2;
		mustIsolate.setLayoutData(mustIsolateData);
		EditingUtils.setID(mustIsolate, UmlViewsRepository.LoopNode.Properties.mustIsolate);
		EditingUtils.setEEFtype(mustIsolate, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.mustIsolate, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsTestedFirstCheckbox(FormToolkit widgetFactory, Composite parent) {
		isTestedFirst = widgetFactory.createButton(parent, UmlMessages.LoopNodePropertiesEditionPart_IsTestedFirstLabel, SWT.CHECK);
		isTestedFirst.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.isTestedFirst, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isTestedFirst.getSelection())));
			}

		});
		GridData isTestedFirstData = new GridData(GridData.FILL_HORIZONTAL);
		isTestedFirstData.horizontalSpan = 2;
		isTestedFirst.setLayoutData(isTestedFirstData);
		EditingUtils.setID(isTestedFirst, UmlViewsRepository.LoopNode.Properties.isTestedFirst);
		EditingUtils.setEEFtype(isTestedFirst, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.isTestedFirst, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createBodyPartReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.bodyPart = new ReferencesTable(UmlMessages.LoopNodePropertiesEditionPart_BodyPartLabel, new ReferencesTableListener	() {
			public void handleAdd() { addBodyPart(); }
			public void handleEdit(EObject element) { editBodyPart(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveBodyPart(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromBodyPart(element); }
			public void navigateTo(EObject element) { }
		});
		this.bodyPart.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.bodyPart, UmlViewsRepository.FORM_KIND));
		this.bodyPart.createControls(parent, widgetFactory);
		this.bodyPart.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.bodyPart, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData bodyPartData = new GridData(GridData.FILL_HORIZONTAL);
		bodyPartData.horizontalSpan = 3;
		this.bodyPart.setLayoutData(bodyPartData);
		this.bodyPart.disableMove();
		bodyPart.setID(UmlViewsRepository.LoopNode.Properties.bodyPart);
		bodyPart.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addBodyPart() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(bodyPart.getInput(), bodyPartFilters, bodyPartBusinessFilters,
		"bodyPart", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.bodyPart,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				bodyPart.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveBodyPart(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.bodyPart, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		bodyPart.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromBodyPart(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.bodyPart, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		bodyPart.refresh();
	}

	/**
	 * 
	 */
	protected void editBodyPart(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				bodyPart.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createSetupPartReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.setupPart = new ReferencesTable(UmlMessages.LoopNodePropertiesEditionPart_SetupPartLabel, new ReferencesTableListener	() {
			public void handleAdd() { addSetupPart(); }
			public void handleEdit(EObject element) { editSetupPart(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveSetupPart(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromSetupPart(element); }
			public void navigateTo(EObject element) { }
		});
		this.setupPart.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.setupPart, UmlViewsRepository.FORM_KIND));
		this.setupPart.createControls(parent, widgetFactory);
		this.setupPart.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.setupPart, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData setupPartData = new GridData(GridData.FILL_HORIZONTAL);
		setupPartData.horizontalSpan = 3;
		this.setupPart.setLayoutData(setupPartData);
		this.setupPart.disableMove();
		setupPart.setID(UmlViewsRepository.LoopNode.Properties.setupPart);
		setupPart.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addSetupPart() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(setupPart.getInput(), setupPartFilters, setupPartBusinessFilters,
		"setupPart", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.setupPart,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				setupPart.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveSetupPart(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.setupPart, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		setupPart.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromSetupPart(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.setupPart, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		setupPart.refresh();
	}

	/**
	 * 
	 */
	protected void editSetupPart(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				setupPart.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createDeciderFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.LoopNodePropertiesEditionPart_DeciderLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.LoopNode.Properties.decider, UmlViewsRepository.FORM_KIND));
		decider = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.LoopNode.Properties.decider, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(decider);
		decider.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData deciderData = new GridData(GridData.FILL_HORIZONTAL);
		decider.setLayoutData(deciderData);
		decider.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.decider, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getDecider()));
			}

		});
		decider.setID(UmlViewsRepository.LoopNode.Properties.decider);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.decider, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createTestReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.test = new ReferencesTable(UmlMessages.LoopNodePropertiesEditionPart_TestLabel, new ReferencesTableListener	() {
			public void handleAdd() { addTest(); }
			public void handleEdit(EObject element) { editTest(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveTest(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromTest(element); }
			public void navigateTo(EObject element) { }
		});
		this.test.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.test, UmlViewsRepository.FORM_KIND));
		this.test.createControls(parent, widgetFactory);
		this.test.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.test, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData testData = new GridData(GridData.FILL_HORIZONTAL);
		testData.horizontalSpan = 3;
		this.test.setLayoutData(testData);
		this.test.disableMove();
		test.setID(UmlViewsRepository.LoopNode.Properties.test);
		test.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addTest() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(test.getInput(), testFilters, testBusinessFilters,
		"test", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.test,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				test.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveTest(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.test, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		test.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromTest(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.test, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		test.refresh();
	}

	/**
	 * 
	 */
	protected void editTest(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				test.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createLoopVariableReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.loopVariable = new ReferencesTable(UmlMessages.LoopNodePropertiesEditionPart_LoopVariableLabel, new ReferencesTableListener	() {
			public void handleAdd() { addLoopVariable(); }
			public void handleEdit(EObject element) { editLoopVariable(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveLoopVariable(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromLoopVariable(element); }
			public void navigateTo(EObject element) { }
		});
		this.loopVariable.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.loopVariable, UmlViewsRepository.FORM_KIND));
		this.loopVariable.createControls(parent, widgetFactory);
		this.loopVariable.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.loopVariable, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData loopVariableData = new GridData(GridData.FILL_HORIZONTAL);
		loopVariableData.horizontalSpan = 3;
		this.loopVariable.setLayoutData(loopVariableData);
		this.loopVariable.disableMove();
		loopVariable.setID(UmlViewsRepository.LoopNode.Properties.loopVariable);
		loopVariable.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addLoopVariable() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(loopVariable.getInput(), loopVariableFilters, loopVariableBusinessFilters,
		"loopVariable", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.loopVariable,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				loopVariable.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveLoopVariable(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.loopVariable, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		loopVariable.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromLoopVariable(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.loopVariable, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		loopVariable.refresh();
	}

	/**
	 * 
	 */
	protected void editLoopVariable(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				loopVariable.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createBodyOutputReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.bodyOutput = new ReferencesTable(UmlMessages.LoopNodePropertiesEditionPart_BodyOutputLabel, new ReferencesTableListener	() {
			public void handleAdd() { addBodyOutput(); }
			public void handleEdit(EObject element) { editBodyOutput(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveBodyOutput(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromBodyOutput(element); }
			public void navigateTo(EObject element) { }
		});
		this.bodyOutput.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.LoopNode.Properties.bodyOutput, UmlViewsRepository.FORM_KIND));
		this.bodyOutput.createControls(parent, widgetFactory);
		this.bodyOutput.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.bodyOutput, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData bodyOutputData = new GridData(GridData.FILL_HORIZONTAL);
		bodyOutputData.horizontalSpan = 3;
		this.bodyOutput.setLayoutData(bodyOutputData);
		this.bodyOutput.disableMove();
		bodyOutput.setID(UmlViewsRepository.LoopNode.Properties.bodyOutput);
		bodyOutput.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addBodyOutput() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(bodyOutput.getInput(), bodyOutputFilters, bodyOutputBusinessFilters,
		"bodyOutput", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.bodyOutput,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				bodyOutput.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveBodyOutput(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.bodyOutput, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		bodyOutput.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromBodyOutput(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LoopNodePropertiesEditionPartForm.this, UmlViewsRepository.LoopNode.Properties.bodyOutput, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		bodyOutput.refresh();
	}

	/**
	 * 
	 */
	protected void editBodyOutput(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				bodyOutput.refresh();
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#getName()
	 * 
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#setName(String newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#getVisibility()
	 * 
	 */
	public Enumerator getVisibility() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initVisibility(EEnum eenum, Enumerator current)
	 */
	public void initVisibility(EEnum eenum, Enumerator current) {
		visibility.setInput(eenum.getELiterals());
		visibility.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#setVisibility(Enumerator newValue)
	 * 
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#updateClientDependency()
	 * 
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * 
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#getIsLeaf()
	 * 
	 */
	public Boolean getIsLeaf() {
		return Boolean.valueOf(isLeaf.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * 
	 */
	public void setIsLeaf(Boolean newValue) {
		if (newValue != null) {
			isLeaf.setSelection(newValue.booleanValue());
		} else {
			isLeaf.setSelection(false);
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#getInStructuredNode()
	 * 
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initInStructuredNode(EObjectFlatComboSettings)
	 */
	public void initInStructuredNode(EObjectFlatComboSettings settings) {
		inStructuredNode.setInput(settings);
		if (current != null) {
			inStructuredNode.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#setInStructuredNode(EObject newValue)
	 * 
	 */
	public void setInStructuredNode(EObject newValue) {
		if (newValue != null) {
			inStructuredNode.setSelection(new StructuredSelection(newValue));
		} else {
			inStructuredNode.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#setInStructuredNodeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInStructuredNodeButtonMode(ButtonsModeEnum newValue) {
		inStructuredNode.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterInStructuredNode(ViewerFilter filter)
	 * 
	 */
	public void addFilterToInStructuredNode(ViewerFilter filter) {
		inStructuredNode.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterInStructuredNode(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToInStructuredNode(ViewerFilter filter) {
		inStructuredNode.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#getActivity()
	 * 
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initActivity(EObjectFlatComboSettings)
	 */
	public void initActivity(EObjectFlatComboSettings settings) {
		activity.setInput(settings);
		if (current != null) {
			activity.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#setActivity(EObject newValue)
	 * 
	 */
	public void setActivity(EObject newValue) {
		if (newValue != null) {
			activity.setSelection(new StructuredSelection(newValue));
		} else {
			activity.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#setActivityButtonMode(ButtonsModeEnum newValue)
	 */
	public void setActivityButtonMode(ButtonsModeEnum newValue) {
		activity.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterActivity(ViewerFilter filter)
	 * 
	 */
	public void addFilterToActivity(ViewerFilter filter) {
		activity.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterActivity(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToActivity(ViewerFilter filter) {
		activity.addBusinessRuleFilter(filter);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initOutgoing(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initOutgoing(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		outgoing.setContentProvider(contentProvider);
		outgoing.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#updateOutgoing()
	 * 
	 */
	public void updateOutgoing() {
	outgoing.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterOutgoing(ViewerFilter filter)
	 * 
	 */
	public void addFilterToOutgoing(ViewerFilter filter) {
		outgoingFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterOutgoing(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToOutgoing(ViewerFilter filter) {
		outgoingBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#isContainedInOutgoingTable(EObject element)
	 * 
	 */
	public boolean isContainedInOutgoingTable(EObject element) {
		return ((ReferencesTableSettings)outgoing.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initIncoming(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initIncoming(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		incoming.setContentProvider(contentProvider);
		incoming.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#updateIncoming()
	 * 
	 */
	public void updateIncoming() {
	incoming.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterIncoming(ViewerFilter filter)
	 * 
	 */
	public void addFilterToIncoming(ViewerFilter filter) {
		incomingFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterIncoming(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToIncoming(ViewerFilter filter) {
		incomingBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#isContainedInIncomingTable(EObject element)
	 * 
	 */
	public boolean isContainedInIncomingTable(EObject element) {
		return ((ReferencesTableSettings)incoming.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initInPartition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInPartition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		inPartition.setContentProvider(contentProvider);
		inPartition.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#updateInPartition()
	 * 
	 */
	public void updateInPartition() {
	inPartition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterInPartition(ViewerFilter filter)
	 * 
	 */
	public void addFilterToInPartition(ViewerFilter filter) {
		inPartitionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterInPartition(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToInPartition(ViewerFilter filter) {
		inPartitionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#isContainedInInPartitionTable(EObject element)
	 * 
	 */
	public boolean isContainedInInPartitionTable(EObject element) {
		return ((ReferencesTableSettings)inPartition.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initInInterruptibleRegion(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInInterruptibleRegion(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		inInterruptibleRegion.setContentProvider(contentProvider);
		inInterruptibleRegion.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#updateInInterruptibleRegion()
	 * 
	 */
	public void updateInInterruptibleRegion() {
	inInterruptibleRegion.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterInInterruptibleRegion(ViewerFilter filter)
	 * 
	 */
	public void addFilterToInInterruptibleRegion(ViewerFilter filter) {
		inInterruptibleRegionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterInInterruptibleRegion(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToInInterruptibleRegion(ViewerFilter filter) {
		inInterruptibleRegionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#isContainedInInInterruptibleRegionTable(EObject element)
	 * 
	 */
	public boolean isContainedInInInterruptibleRegionTable(EObject element) {
		return ((ReferencesTableSettings)inInterruptibleRegion.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initRedefinedNode(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedNode(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedNode.setContentProvider(contentProvider);
		redefinedNode.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#updateRedefinedNode()
	 * 
	 */
	public void updateRedefinedNode() {
	redefinedNode.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterRedefinedNode(ViewerFilter filter)
	 * 
	 */
	public void addFilterToRedefinedNode(ViewerFilter filter) {
		redefinedNodeFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterRedefinedNode(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToRedefinedNode(ViewerFilter filter) {
		redefinedNodeBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#isContainedInRedefinedNodeTable(EObject element)
	 * 
	 */
	public boolean isContainedInRedefinedNodeTable(EObject element) {
		return ((ReferencesTableSettings)redefinedNode.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#getInActivity()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initInActivity(EObjectFlatComboSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#setInActivity(EObject newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#setInActivityButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInActivityButtonMode(ButtonsModeEnum newValue) {
		inActivity.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterInActivity(ViewerFilter filter)
	 * 
	 */
	public void addFilterToInActivity(ViewerFilter filter) {
		inActivity.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterInActivity(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToInActivity(ViewerFilter filter) {
		inActivity.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#getMustIsolate()
	 * 
	 */
	public Boolean getMustIsolate() {
		return Boolean.valueOf(mustIsolate.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#setMustIsolate(Boolean newValue)
	 * 
	 */
	public void setMustIsolate(Boolean newValue) {
		if (newValue != null) {
			mustIsolate.setSelection(newValue.booleanValue());
		} else {
			mustIsolate.setSelection(false);
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#getIsTestedFirst()
	 * 
	 */
	public Boolean getIsTestedFirst() {
		return Boolean.valueOf(isTestedFirst.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#setIsTestedFirst(Boolean newValue)
	 * 
	 */
	public void setIsTestedFirst(Boolean newValue) {
		if (newValue != null) {
			isTestedFirst.setSelection(newValue.booleanValue());
		} else {
			isTestedFirst.setSelection(false);
		}
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initBodyPart(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initBodyPart(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		bodyPart.setContentProvider(contentProvider);
		bodyPart.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#updateBodyPart()
	 * 
	 */
	public void updateBodyPart() {
	bodyPart.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterBodyPart(ViewerFilter filter)
	 * 
	 */
	public void addFilterToBodyPart(ViewerFilter filter) {
		bodyPartFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterBodyPart(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToBodyPart(ViewerFilter filter) {
		bodyPartBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#isContainedInBodyPartTable(EObject element)
	 * 
	 */
	public boolean isContainedInBodyPartTable(EObject element) {
		return ((ReferencesTableSettings)bodyPart.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initSetupPart(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initSetupPart(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		setupPart.setContentProvider(contentProvider);
		setupPart.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#updateSetupPart()
	 * 
	 */
	public void updateSetupPart() {
	setupPart.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterSetupPart(ViewerFilter filter)
	 * 
	 */
	public void addFilterToSetupPart(ViewerFilter filter) {
		setupPartFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterSetupPart(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToSetupPart(ViewerFilter filter) {
		setupPartBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#isContainedInSetupPartTable(EObject element)
	 * 
	 */
	public boolean isContainedInSetupPartTable(EObject element) {
		return ((ReferencesTableSettings)setupPart.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#getDecider()
	 * 
	 */
	public EObject getDecider() {
		if (decider.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) decider.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initDecider(EObjectFlatComboSettings)
	 */
	public void initDecider(EObjectFlatComboSettings settings) {
		decider.setInput(settings);
		if (current != null) {
			decider.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#setDecider(EObject newValue)
	 * 
	 */
	public void setDecider(EObject newValue) {
		if (newValue != null) {
			decider.setSelection(new StructuredSelection(newValue));
		} else {
			decider.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#setDeciderButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDeciderButtonMode(ButtonsModeEnum newValue) {
		decider.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterDecider(ViewerFilter filter)
	 * 
	 */
	public void addFilterToDecider(ViewerFilter filter) {
		decider.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterDecider(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToDecider(ViewerFilter filter) {
		decider.addBusinessRuleFilter(filter);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initTest(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initTest(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		test.setContentProvider(contentProvider);
		test.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#updateTest()
	 * 
	 */
	public void updateTest() {
	test.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterTest(ViewerFilter filter)
	 * 
	 */
	public void addFilterToTest(ViewerFilter filter) {
		testFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterTest(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToTest(ViewerFilter filter) {
		testBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#isContainedInTestTable(EObject element)
	 * 
	 */
	public boolean isContainedInTestTable(EObject element) {
		return ((ReferencesTableSettings)test.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initLoopVariable(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initLoopVariable(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		loopVariable.setContentProvider(contentProvider);
		loopVariable.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#updateLoopVariable()
	 * 
	 */
	public void updateLoopVariable() {
	loopVariable.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterLoopVariable(ViewerFilter filter)
	 * 
	 */
	public void addFilterToLoopVariable(ViewerFilter filter) {
		loopVariableFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterLoopVariable(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToLoopVariable(ViewerFilter filter) {
		loopVariableBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#isContainedInLoopVariableTable(EObject element)
	 * 
	 */
	public boolean isContainedInLoopVariableTable(EObject element) {
		return ((ReferencesTableSettings)loopVariable.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#initBodyOutput(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initBodyOutput(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		bodyOutput.setContentProvider(contentProvider);
		bodyOutput.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#updateBodyOutput()
	 * 
	 */
	public void updateBodyOutput() {
	bodyOutput.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addFilterBodyOutput(ViewerFilter filter)
	 * 
	 */
	public void addFilterToBodyOutput(ViewerFilter filter) {
		bodyOutputFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#addBusinessFilterBodyOutput(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToBodyOutput(ViewerFilter filter) {
		bodyOutputBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LoopNodePropertiesEditionPart#isContainedInBodyOutputTable(EObject element)
	 * 
	 */
	public boolean isContainedInBodyOutputTable(EObject element) {
		return ((ReferencesTableSettings)bodyOutput.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.LoopNode_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
