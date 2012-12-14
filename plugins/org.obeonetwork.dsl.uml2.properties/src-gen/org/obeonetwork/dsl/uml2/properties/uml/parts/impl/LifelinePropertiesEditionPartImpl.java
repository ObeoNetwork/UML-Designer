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
package org.obeonetwork.dsl.uml2.properties.uml.parts.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;

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

import org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class LifelinePropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, LifelinePropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer represents;
	protected EObjectFlatComboViewer interaction;
	protected EObjectFlatComboViewer decomposedAs;
	protected ReferencesTable coveredBy;
	protected List<ViewerFilter> coveredByBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> coveredByFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public LifelinePropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence lifelineStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = lifelineStep.addStep(UmlViewsRepository.Lifeline.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.Lifeline.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.Lifeline.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.Lifeline.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.Lifeline.Properties.represents);
		propertiesStep.addStep(UmlViewsRepository.Lifeline.Properties.interaction);
		propertiesStep.addStep(UmlViewsRepository.Lifeline.Properties.decomposedAs);
		propertiesStep.addStep(UmlViewsRepository.Lifeline.Properties.coveredBy);
		
		
		composer = new PartComposer(lifelineStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.Lifeline.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.Lifeline.Properties.name) {
					return createNameText(parent);
				}
				if (key == UmlViewsRepository.Lifeline.Properties.visibility) {
					return createVisibilityEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.Lifeline.Properties.clientDependency) {
					return createClientDependencyAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.Lifeline.Properties.represents) {
					return createRepresentsFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Lifeline.Properties.interaction) {
					return createInteractionFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Lifeline.Properties.decomposedAs) {
					return createDecomposedAsFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.Lifeline.Properties.coveredBy) {
					return createCoveredByAdvancedReferencesTable(parent);
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
		propertiesGroup.setText(UmlMessages.LifelinePropertiesEditionPart_PropertiesGroupLabel);
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
	
	protected Composite createNameText(Composite parent) {
		createDescription(parent, UmlViewsRepository.Lifeline.Properties.name, UmlMessages.LifelinePropertiesEditionPart_NameLabel);
		name = SWTUtils.createScrollableText(parent, SWT.BORDER);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(nameData);
		name.addFocusListener(new FocusAdapter() {

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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LifelinePropertiesEditionPartImpl.this, UmlViewsRepository.Lifeline.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
			}

		});
		name.addKeyListener(new KeyAdapter() {

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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LifelinePropertiesEditionPartImpl.this, UmlViewsRepository.Lifeline.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, UmlViewsRepository.Lifeline.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Lifeline.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Lifeline.Properties.visibility, UmlMessages.LifelinePropertiesEditionPart_VisibilityLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LifelinePropertiesEditionPartImpl.this, UmlViewsRepository.Lifeline.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.Lifeline.Properties.visibility);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Lifeline.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Lifeline.Properties.clientDependency, UmlMessages.LifelinePropertiesEditionPart_ClientDependencyLabel);		 
		this.clientDependency = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Lifeline.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
		this.clientDependency.createControls(parent);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LifelinePropertiesEditionPartImpl.this, UmlViewsRepository.Lifeline.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.Lifeline.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LifelinePropertiesEditionPartImpl.this, UmlViewsRepository.Lifeline.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LifelinePropertiesEditionPartImpl.this, UmlViewsRepository.Lifeline.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LifelinePropertiesEditionPartImpl.this, UmlViewsRepository.Lifeline.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	 * @generated
	 */
	protected Composite createRepresentsFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Lifeline.Properties.represents, UmlMessages.LifelinePropertiesEditionPart_RepresentsLabel);
		represents = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Lifeline.Properties.represents, UmlViewsRepository.SWT_KIND));
		represents.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		represents.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LifelinePropertiesEditionPartImpl.this, UmlViewsRepository.Lifeline.Properties.represents, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getRepresents()));
			}

		});
		GridData representsData = new GridData(GridData.FILL_HORIZONTAL);
		represents.setLayoutData(representsData);
		represents.setID(UmlViewsRepository.Lifeline.Properties.represents);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Lifeline.Properties.represents, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createInteractionFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Lifeline.Properties.interaction, UmlMessages.LifelinePropertiesEditionPart_InteractionLabel);
		interaction = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Lifeline.Properties.interaction, UmlViewsRepository.SWT_KIND));
		interaction.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		interaction.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LifelinePropertiesEditionPartImpl.this, UmlViewsRepository.Lifeline.Properties.interaction, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getInteraction()));
			}

		});
		GridData interactionData = new GridData(GridData.FILL_HORIZONTAL);
		interaction.setLayoutData(interactionData);
		interaction.setID(UmlViewsRepository.Lifeline.Properties.interaction);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Lifeline.Properties.interaction, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createDecomposedAsFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.Lifeline.Properties.decomposedAs, UmlMessages.LifelinePropertiesEditionPart_DecomposedAsLabel);
		decomposedAs = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Lifeline.Properties.decomposedAs, UmlViewsRepository.SWT_KIND));
		decomposedAs.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		decomposedAs.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LifelinePropertiesEditionPartImpl.this, UmlViewsRepository.Lifeline.Properties.decomposedAs, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getDecomposedAs()));
			}

		});
		GridData decomposedAsData = new GridData(GridData.FILL_HORIZONTAL);
		decomposedAs.setLayoutData(decomposedAsData);
		decomposedAs.setID(UmlViewsRepository.Lifeline.Properties.decomposedAs);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Lifeline.Properties.decomposedAs, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createCoveredByAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.Lifeline.Properties.coveredBy, UmlMessages.LifelinePropertiesEditionPart_CoveredByLabel);		 
		this.coveredBy = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addCoveredBy(); }
			public void handleEdit(EObject element) { editCoveredBy(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveCoveredBy(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromCoveredBy(element); }
			public void navigateTo(EObject element) { }
		});
		this.coveredBy.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Lifeline.Properties.coveredBy, UmlViewsRepository.SWT_KIND));
		this.coveredBy.createControls(parent);
		this.coveredBy.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LifelinePropertiesEditionPartImpl.this, UmlViewsRepository.Lifeline.Properties.coveredBy, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData coveredByData = new GridData(GridData.FILL_HORIZONTAL);
		coveredByData.horizontalSpan = 3;
		this.coveredBy.setLayoutData(coveredByData);
		this.coveredBy.disableMove();
		coveredBy.setID(UmlViewsRepository.Lifeline.Properties.coveredBy);
		coveredBy.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addCoveredBy() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(coveredBy.getInput(), coveredByFilters, coveredByBusinessFilters,
		"coveredBy", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LifelinePropertiesEditionPartImpl.this, UmlViewsRepository.Lifeline.Properties.coveredBy,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				coveredBy.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveCoveredBy(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LifelinePropertiesEditionPartImpl.this, UmlViewsRepository.Lifeline.Properties.coveredBy, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		coveredBy.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromCoveredBy(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LifelinePropertiesEditionPartImpl.this, UmlViewsRepository.Lifeline.Properties.coveredBy, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		coveredBy.refresh();
	}

	/**
	 * @generated
	 */
	protected void editCoveredBy(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				coveredBy.refresh();
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Lifeline.Properties.name);
		if (readOnly && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(UmlMessages.Lifeline_ReadOnly);
		} else if (!readOnly && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
		Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.Lifeline.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.Lifeline_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.Lifeline.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.Lifeline_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Lifeline.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.Lifeline_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#getRepresents()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#initRepresents(EObjectFlatComboSettings)
	 */
	public void initRepresents(EObjectFlatComboSettings settings) {
		represents.setInput(settings);
		if (current != null) {
			represents.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Lifeline.Properties.represents);
		if (readOnly && represents.isEnabled()) {
			represents.setEnabled(false);
			represents.setToolTipText(UmlMessages.Lifeline_ReadOnly);
		} else if (!readOnly && !represents.isEnabled()) {
			represents.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#setRepresents(EObject newValue)
	 * @generated
	 */
	public void setRepresents(EObject newValue) {
		if (newValue != null) {
			represents.setSelection(new StructuredSelection(newValue));
		} else {
			represents.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Lifeline.Properties.represents);
		if (readOnly && represents.isEnabled()) {
			represents.setEnabled(false);
			represents.setToolTipText(UmlMessages.Lifeline_ReadOnly);
		} else if (!readOnly && !represents.isEnabled()) {
			represents.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#setRepresentsButtonMode(ButtonsModeEnum newValue)
	 */
	public void setRepresentsButtonMode(ButtonsModeEnum newValue) {
		represents.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#addFilterRepresents(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRepresents(ViewerFilter filter) {
		represents.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#addBusinessFilterRepresents(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRepresents(ViewerFilter filter) {
		represents.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#getInteraction()
	 * @generated
	 */
	public EObject getInteraction() {
		if (interaction.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) interaction.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#initInteraction(EObjectFlatComboSettings)
	 */
	public void initInteraction(EObjectFlatComboSettings settings) {
		interaction.setInput(settings);
		if (current != null) {
			interaction.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Lifeline.Properties.interaction);
		if (readOnly && interaction.isEnabled()) {
			interaction.setEnabled(false);
			interaction.setToolTipText(UmlMessages.Lifeline_ReadOnly);
		} else if (!readOnly && !interaction.isEnabled()) {
			interaction.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#setInteraction(EObject newValue)
	 * @generated
	 */
	public void setInteraction(EObject newValue) {
		if (newValue != null) {
			interaction.setSelection(new StructuredSelection(newValue));
		} else {
			interaction.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Lifeline.Properties.interaction);
		if (readOnly && interaction.isEnabled()) {
			interaction.setEnabled(false);
			interaction.setToolTipText(UmlMessages.Lifeline_ReadOnly);
		} else if (!readOnly && !interaction.isEnabled()) {
			interaction.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#setInteractionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInteractionButtonMode(ButtonsModeEnum newValue) {
		interaction.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#addFilterInteraction(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInteraction(ViewerFilter filter) {
		interaction.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#addBusinessFilterInteraction(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInteraction(ViewerFilter filter) {
		interaction.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#getDecomposedAs()
	 * @generated
	 */
	public EObject getDecomposedAs() {
		if (decomposedAs.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) decomposedAs.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#initDecomposedAs(EObjectFlatComboSettings)
	 */
	public void initDecomposedAs(EObjectFlatComboSettings settings) {
		decomposedAs.setInput(settings);
		if (current != null) {
			decomposedAs.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Lifeline.Properties.decomposedAs);
		if (readOnly && decomposedAs.isEnabled()) {
			decomposedAs.setEnabled(false);
			decomposedAs.setToolTipText(UmlMessages.Lifeline_ReadOnly);
		} else if (!readOnly && !decomposedAs.isEnabled()) {
			decomposedAs.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#setDecomposedAs(EObject newValue)
	 * @generated
	 */
	public void setDecomposedAs(EObject newValue) {
		if (newValue != null) {
			decomposedAs.setSelection(new StructuredSelection(newValue));
		} else {
			decomposedAs.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.Lifeline.Properties.decomposedAs);
		if (readOnly && decomposedAs.isEnabled()) {
			decomposedAs.setEnabled(false);
			decomposedAs.setToolTipText(UmlMessages.Lifeline_ReadOnly);
		} else if (!readOnly && !decomposedAs.isEnabled()) {
			decomposedAs.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#setDecomposedAsButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDecomposedAsButtonMode(ButtonsModeEnum newValue) {
		decomposedAs.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#addFilterDecomposedAs(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToDecomposedAs(ViewerFilter filter) {
		decomposedAs.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#addBusinessFilterDecomposedAs(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToDecomposedAs(ViewerFilter filter) {
		decomposedAs.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#initCoveredBy(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initCoveredBy(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		coveredBy.setContentProvider(contentProvider);
		coveredBy.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.Lifeline.Properties.coveredBy);
		if (readOnly && coveredBy.getTable().isEnabled()) {
			coveredBy.setEnabled(false);
			coveredBy.setToolTipText(UmlMessages.Lifeline_ReadOnly);
		} else if (!readOnly && !coveredBy.getTable().isEnabled()) {
			coveredBy.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#updateCoveredBy()
	 * @generated
	 */
	public void updateCoveredBy() {
	coveredBy.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#addFilterCoveredBy(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToCoveredBy(ViewerFilter filter) {
		coveredByFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#addBusinessFilterCoveredBy(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToCoveredBy(ViewerFilter filter) {
		coveredByBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.LifelinePropertiesEditionPart#isContainedInCoveredByTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInCoveredByTable(EObject element) {
		return ((ReferencesTableSettings)coveredBy.getInput()).contains(element);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.Lifeline_Part_Title;
	}



}
