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

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
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
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EMFComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class PseudostatePropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, PseudostatePropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer container;
	protected EMFComboViewer kind;
	protected EObjectFlatComboViewer stateMachine;
	protected EObjectFlatComboViewer state;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public PseudostatePropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence pseudostateStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = pseudostateStep.addStep(UmlViewsRepository.Pseudostate.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.Pseudostate.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.Pseudostate.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.Pseudostate.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.Pseudostate.Properties.container);
		propertiesStep.addStep(UmlViewsRepository.Pseudostate.Properties.kind);
		propertiesStep.addStep(UmlViewsRepository.Pseudostate.Properties.stateMachine);
		propertiesStep.addStep(UmlViewsRepository.Pseudostate.Properties.state);
		
		
		composer = new PartComposer(pseudostateStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.Pseudostate.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.Pseudostate.Properties.name) {
					return createNameText(parent);
				}
				if (key == UmlViewsRepository.Pseudostate.Properties.visibility) {
					return createVisibilityEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.Pseudostate.Properties.clientDependency) {
					return createClientDependencyAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Pseudostate.Properties.container) {
					return createContainerFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Pseudostate.Properties.kind) {
					return createKindEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.Pseudostate.Properties.stateMachine) {
					return createStateMachineFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Pseudostate.Properties.state) {
					return createStateFlatComboViewer(parent);
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
		propertiesGroup.setText(UmlMessages.PseudostatePropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	
	protected Composite createNameText(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.PseudostatePropertiesEditionPart_NameLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Pseudostate.Properties.name, UmlViewsRepository.SWT_KIND));
		name = new Text(parent, SWT.BORDER);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(nameData);
		name.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PseudostatePropertiesEditionPartImpl.this, UmlViewsRepository.Pseudostate.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
			}

		});
		name.addKeyListener(new KeyAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PseudostatePropertiesEditionPartImpl.this, UmlViewsRepository.Pseudostate.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, UmlViewsRepository.Pseudostate.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Pseudostate.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.PseudostatePropertiesEditionPart_VisibilityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Pseudostate.Properties.visibility, UmlViewsRepository.SWT_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PseudostatePropertiesEditionPartImpl.this, UmlViewsRepository.Pseudostate.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.Pseudostate.Properties.visibility);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Pseudostate.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
		this.clientDependency = new ReferencesTable(UmlMessages.PseudostatePropertiesEditionPart_ClientDependencyLabel, new ReferencesTableListener() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Pseudostate.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
		this.clientDependency.createControls(parent);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PseudostatePropertiesEditionPartImpl.this, UmlViewsRepository.Pseudostate.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.Pseudostate.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PseudostatePropertiesEditionPartImpl.this, UmlViewsRepository.Pseudostate.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PseudostatePropertiesEditionPartImpl.this, UmlViewsRepository.Pseudostate.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PseudostatePropertiesEditionPartImpl.this, UmlViewsRepository.Pseudostate.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	 * 
	 */
	protected Composite createContainerFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.PseudostatePropertiesEditionPart_ContainerLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Pseudostate.Properties.container, UmlViewsRepository.SWT_KIND));
		container = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Pseudostate.Properties.container, UmlViewsRepository.SWT_KIND));
		container.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		container.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PseudostatePropertiesEditionPartImpl.this, UmlViewsRepository.Pseudostate.Properties.container, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getContainer()));
			}

		});
		GridData containerData = new GridData(GridData.FILL_HORIZONTAL);
		container.setLayoutData(containerData);
		container.setID(UmlViewsRepository.Pseudostate.Properties.container);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Pseudostate.Properties.container, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createKindEMFComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.PseudostatePropertiesEditionPart_KindLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Pseudostate.Properties.kind, UmlViewsRepository.SWT_KIND));
		kind = new EMFComboViewer(parent);
		kind.setContentProvider(new ArrayContentProvider());
		kind.setLabelProvider(new AdapterFactoryLabelProvider(new EcoreAdapterFactory()));
		GridData kindData = new GridData(GridData.FILL_HORIZONTAL);
		kind.getCombo().setLayoutData(kindData);
		kind.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 * 	
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PseudostatePropertiesEditionPartImpl.this, UmlViewsRepository.Pseudostate.Properties.kind, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getKind()));
			}

		});
		kind.setID(UmlViewsRepository.Pseudostate.Properties.kind);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Pseudostate.Properties.kind, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createStateMachineFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.PseudostatePropertiesEditionPart_StateMachineLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Pseudostate.Properties.stateMachine, UmlViewsRepository.SWT_KIND));
		stateMachine = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Pseudostate.Properties.stateMachine, UmlViewsRepository.SWT_KIND));
		stateMachine.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		stateMachine.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PseudostatePropertiesEditionPartImpl.this, UmlViewsRepository.Pseudostate.Properties.stateMachine, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getStateMachine()));
			}

		});
		GridData stateMachineData = new GridData(GridData.FILL_HORIZONTAL);
		stateMachine.setLayoutData(stateMachineData);
		stateMachine.setID(UmlViewsRepository.Pseudostate.Properties.stateMachine);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Pseudostate.Properties.stateMachine, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createStateFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.PseudostatePropertiesEditionPart_StateLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Pseudostate.Properties.state, UmlViewsRepository.SWT_KIND));
		state = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Pseudostate.Properties.state, UmlViewsRepository.SWT_KIND));
		state.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		state.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PseudostatePropertiesEditionPartImpl.this, UmlViewsRepository.Pseudostate.Properties.state, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getState()));
			}

		});
		GridData stateData = new GridData(GridData.FILL_HORIZONTAL);
		state.setLayoutData(stateData);
		state.setID(UmlViewsRepository.Pseudostate.Properties.state);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Pseudostate.Properties.state, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#getName()
	 * 
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#setName(String newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#getVisibility()
	 * 
	 */
	public Enumerator getVisibility() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#initVisibility(EEnum eenum, Enumerator current)
	 */
	public void initVisibility(EEnum eenum, Enumerator current) {
		visibility.setInput(eenum.getELiterals());
		visibility.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#setVisibility(Enumerator newValue)
	 * 
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#updateClientDependency()
	 * 
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * 
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#getContainer()
	 * 
	 */
	public EObject getContainer() {
		if (container.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) container.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#initContainer(EObjectFlatComboSettings)
	 */
	public void initContainer(EObjectFlatComboSettings settings) {
		container.setInput(settings);
		if (current != null) {
			container.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#setContainer(EObject newValue)
	 * 
	 */
	public void setContainer(EObject newValue) {
		if (newValue != null) {
			container.setSelection(new StructuredSelection(newValue));
		} else {
			container.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#setContainerButtonMode(ButtonsModeEnum newValue)
	 */
	public void setContainerButtonMode(ButtonsModeEnum newValue) {
		container.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#addFilterContainer(ViewerFilter filter)
	 * 
	 */
	public void addFilterToContainer(ViewerFilter filter) {
		container.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#addBusinessFilterContainer(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToContainer(ViewerFilter filter) {
		container.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#getKind()
	 * 
	 */
	public Enumerator getKind() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) kind.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#initKind(EEnum eenum, Enumerator current)
	 */
	public void initKind(EEnum eenum, Enumerator current) {
		kind.setInput(eenum.getELiterals());
		kind.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#setKind(Enumerator newValue)
	 * 
	 */
	public void setKind(Enumerator newValue) {
		kind.modelUpdating(new StructuredSelection(newValue));
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#getStateMachine()
	 * 
	 */
	public EObject getStateMachine() {
		if (stateMachine.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) stateMachine.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#initStateMachine(EObjectFlatComboSettings)
	 */
	public void initStateMachine(EObjectFlatComboSettings settings) {
		stateMachine.setInput(settings);
		if (current != null) {
			stateMachine.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#setStateMachine(EObject newValue)
	 * 
	 */
	public void setStateMachine(EObject newValue) {
		if (newValue != null) {
			stateMachine.setSelection(new StructuredSelection(newValue));
		} else {
			stateMachine.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#setStateMachineButtonMode(ButtonsModeEnum newValue)
	 */
	public void setStateMachineButtonMode(ButtonsModeEnum newValue) {
		stateMachine.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#addFilterStateMachine(ViewerFilter filter)
	 * 
	 */
	public void addFilterToStateMachine(ViewerFilter filter) {
		stateMachine.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#addBusinessFilterStateMachine(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToStateMachine(ViewerFilter filter) {
		stateMachine.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#getState()
	 * 
	 */
	public EObject getState() {
		if (state.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) state.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#initState(EObjectFlatComboSettings)
	 */
	public void initState(EObjectFlatComboSettings settings) {
		state.setInput(settings);
		if (current != null) {
			state.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#setState(EObject newValue)
	 * 
	 */
	public void setState(EObject newValue) {
		if (newValue != null) {
			state.setSelection(new StructuredSelection(newValue));
		} else {
			state.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#setStateButtonMode(ButtonsModeEnum newValue)
	 */
	public void setStateButtonMode(ButtonsModeEnum newValue) {
		state.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#addFilterState(ViewerFilter filter)
	 * 
	 */
	public void addFilterToState(ViewerFilter filter) {
		state.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PseudostatePropertiesEditionPart#addBusinessFilterState(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToState(ViewerFilter filter) {
		state.addBusinessRuleFilter(filter);
	}







	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.Pseudostate_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
