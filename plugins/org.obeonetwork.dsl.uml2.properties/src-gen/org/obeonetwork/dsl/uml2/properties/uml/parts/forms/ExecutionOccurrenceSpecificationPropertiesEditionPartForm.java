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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ExecutionOccurrenceSpecificationPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, ExecutionOccurrenceSpecificationPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable covered;
	protected List<ViewerFilter> coveredBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> coveredFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer enclosingInteraction;
	protected EObjectFlatComboViewer enclosingOperand;
	protected ReferencesTable toBefore;
	protected List<ViewerFilter> toBeforeBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> toBeforeFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer event;
	protected ReferencesTable toAfter;
	protected List<ViewerFilter> toAfterBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> toAfterFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer execution;



	/**
	 * For {@link ISection} use only.
	 */
	public ExecutionOccurrenceSpecificationPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ExecutionOccurrenceSpecificationPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence executionOccurrenceSpecificationStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = executionOccurrenceSpecificationStep.addStep(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered);
		propertiesStep.addStep(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction);
		propertiesStep.addStep(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand);
		propertiesStep.addStep(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore);
		propertiesStep.addStep(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event);
		propertiesStep.addStep(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter);
		propertiesStep.addStep(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution);
		
		
		composer = new PartComposer(executionOccurrenceSpecificationStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name) {
					return createNameText(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.visibility) {
					return createVisibilityEMFComboViewer(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency) {
					return createClientDependencyReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered) {
					return createCoveredReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction) {
					return createEnclosingInteractionFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand) {
					return createEnclosingOperandFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore) {
					return createToBeforeReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event) {
					return createEventFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter) {
					return createToAfterReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution) {
					return createExecutionFlatComboViewer(parent, widgetFactory);
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
		propertiesSection.setText(UmlMessages.ExecutionOccurrenceSpecificationPropertiesEditionPart_PropertiesGroupLabel);
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
		createDescription(parent, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name, UmlMessages.ExecutionOccurrenceSpecificationPropertiesEditionPart_NameLabel);
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
							ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this,
							UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name,
							PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this,
									UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name,
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
									ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this,
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}
		});
		EditingUtils.setID(name, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		createDescription(parent, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.visibility, UmlMessages.ExecutionOccurrenceSpecificationPropertiesEditionPart_VisibilityLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.visibility);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.clientDependency = new ReferencesTable(getDescription(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency, UmlMessages.ExecutionOccurrenceSpecificationPropertiesEditionPart_ClientDependencyLabel), new ReferencesTableListener	() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
		this.clientDependency.createControls(parent, widgetFactory);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	protected Composite createCoveredReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.covered = new ReferencesTable(getDescription(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered, UmlMessages.ExecutionOccurrenceSpecificationPropertiesEditionPart_CoveredLabel), new ReferencesTableListener	() {
			public void handleAdd() { addCovered(); }
			public void handleEdit(EObject element) { editCovered(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveCovered(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromCovered(element); }
			public void navigateTo(EObject element) { }
		});
		this.covered.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered, UmlViewsRepository.FORM_KIND));
		this.covered.createControls(parent, widgetFactory);
		this.covered.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData coveredData = new GridData(GridData.FILL_HORIZONTAL);
		coveredData.horizontalSpan = 3;
		this.covered.setLayoutData(coveredData);
		this.covered.disableMove();
		covered.setID(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered);
		covered.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addCovered() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(covered.getInput(), coveredFilters, coveredBusinessFilters,
		"covered", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				covered.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveCovered(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		covered.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromCovered(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		covered.refresh();
	}

	/**
	 * @generated
	 */
	protected void editCovered(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				covered.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createEnclosingInteractionFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction, UmlMessages.ExecutionOccurrenceSpecificationPropertiesEditionPart_EnclosingInteractionLabel);
		enclosingInteraction = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(enclosingInteraction);
		enclosingInteraction.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData enclosingInteractionData = new GridData(GridData.FILL_HORIZONTAL);
		enclosingInteraction.setLayoutData(enclosingInteractionData);
		enclosingInteraction.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getEnclosingInteraction()));
			}

		});
		enclosingInteraction.setID(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createEnclosingOperandFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand, UmlMessages.ExecutionOccurrenceSpecificationPropertiesEditionPart_EnclosingOperandLabel);
		enclosingOperand = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(enclosingOperand);
		enclosingOperand.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData enclosingOperandData = new GridData(GridData.FILL_HORIZONTAL);
		enclosingOperand.setLayoutData(enclosingOperandData);
		enclosingOperand.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getEnclosingOperand()));
			}

		});
		enclosingOperand.setID(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createToBeforeReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.toBefore = new ReferencesTable(getDescription(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore, UmlMessages.ExecutionOccurrenceSpecificationPropertiesEditionPart_ToBeforeLabel), new ReferencesTableListener	() {
			public void handleAdd() { addToBefore(); }
			public void handleEdit(EObject element) { editToBefore(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveToBefore(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromToBefore(element); }
			public void navigateTo(EObject element) { }
		});
		this.toBefore.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore, UmlViewsRepository.FORM_KIND));
		this.toBefore.createControls(parent, widgetFactory);
		this.toBefore.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData toBeforeData = new GridData(GridData.FILL_HORIZONTAL);
		toBeforeData.horizontalSpan = 3;
		this.toBefore.setLayoutData(toBeforeData);
		this.toBefore.disableMove();
		toBefore.setID(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore);
		toBefore.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addToBefore() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(toBefore.getInput(), toBeforeFilters, toBeforeBusinessFilters,
		"toBefore", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				toBefore.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveToBefore(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		toBefore.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromToBefore(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		toBefore.refresh();
	}

	/**
	 * @generated
	 */
	protected void editToBefore(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				toBefore.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createEventFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event, UmlMessages.ExecutionOccurrenceSpecificationPropertiesEditionPart_EventLabel);
		event = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(event);
		event.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData eventData = new GridData(GridData.FILL_HORIZONTAL);
		event.setLayoutData(eventData);
		event.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getEvent()));
			}

		});
		event.setID(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createToAfterReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.toAfter = new ReferencesTable(getDescription(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter, UmlMessages.ExecutionOccurrenceSpecificationPropertiesEditionPart_ToAfterLabel), new ReferencesTableListener	() {
			public void handleAdd() { addToAfter(); }
			public void handleEdit(EObject element) { editToAfter(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveToAfter(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromToAfter(element); }
			public void navigateTo(EObject element) { }
		});
		this.toAfter.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter, UmlViewsRepository.FORM_KIND));
		this.toAfter.createControls(parent, widgetFactory);
		this.toAfter.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData toAfterData = new GridData(GridData.FILL_HORIZONTAL);
		toAfterData.horizontalSpan = 3;
		this.toAfter.setLayoutData(toAfterData);
		this.toAfter.disableMove();
		toAfter.setID(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter);
		toAfter.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addToAfter() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(toAfter.getInput(), toAfterFilters, toAfterBusinessFilters,
		"toAfter", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				toAfter.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveToAfter(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		toAfter.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromToAfter(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		toAfter.refresh();
	}

	/**
	 * @generated
	 */
	protected void editToAfter(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				toAfter.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createExecutionFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution, UmlMessages.ExecutionOccurrenceSpecificationPropertiesEditionPart_ExecutionLabel);
		execution = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(execution);
		execution.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData executionData = new GridData(GridData.FILL_HORIZONTAL);
		execution.setLayoutData(executionData);
		execution.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExecutionOccurrenceSpecificationPropertiesEditionPartForm.this, UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getExecution()));
			}

		});
		execution.setID(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.name);
		if (readOnly && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
		Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#initCovered(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initCovered(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		covered.setContentProvider(contentProvider);
		covered.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.covered);
		if (readOnly && covered.getTable().isEnabled()) {
			covered.setEnabled(false);
			covered.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !covered.getTable().isEnabled()) {
			covered.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#updateCovered()
	 * @generated
	 */
	public void updateCovered() {
	covered.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addFilterCovered(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToCovered(ViewerFilter filter) {
		coveredFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterCovered(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToCovered(ViewerFilter filter) {
		coveredBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#isContainedInCoveredTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInCoveredTable(EObject element) {
		return ((ReferencesTableSettings)covered.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#getEnclosingInteraction()
	 * @generated
	 */
	public EObject getEnclosingInteraction() {
		if (enclosingInteraction.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) enclosingInteraction.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#initEnclosingInteraction(EObjectFlatComboSettings)
	 */
	public void initEnclosingInteraction(EObjectFlatComboSettings settings) {
		enclosingInteraction.setInput(settings);
		if (current != null) {
			enclosingInteraction.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction);
		if (readOnly && enclosingInteraction.isEnabled()) {
			enclosingInteraction.setEnabled(false);
			enclosingInteraction.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !enclosingInteraction.isEnabled()) {
			enclosingInteraction.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#setEnclosingInteraction(EObject newValue)
	 * @generated
	 */
	public void setEnclosingInteraction(EObject newValue) {
		if (newValue != null) {
			enclosingInteraction.setSelection(new StructuredSelection(newValue));
		} else {
			enclosingInteraction.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingInteraction);
		if (readOnly && enclosingInteraction.isEnabled()) {
			enclosingInteraction.setEnabled(false);
			enclosingInteraction.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !enclosingInteraction.isEnabled()) {
			enclosingInteraction.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#setEnclosingInteractionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setEnclosingInteractionButtonMode(ButtonsModeEnum newValue) {
		enclosingInteraction.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addFilterEnclosingInteraction(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEnclosingInteraction(ViewerFilter filter) {
		enclosingInteraction.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterEnclosingInteraction(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEnclosingInteraction(ViewerFilter filter) {
		enclosingInteraction.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#getEnclosingOperand()
	 * @generated
	 */
	public EObject getEnclosingOperand() {
		if (enclosingOperand.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) enclosingOperand.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#initEnclosingOperand(EObjectFlatComboSettings)
	 */
	public void initEnclosingOperand(EObjectFlatComboSettings settings) {
		enclosingOperand.setInput(settings);
		if (current != null) {
			enclosingOperand.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand);
		if (readOnly && enclosingOperand.isEnabled()) {
			enclosingOperand.setEnabled(false);
			enclosingOperand.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !enclosingOperand.isEnabled()) {
			enclosingOperand.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#setEnclosingOperand(EObject newValue)
	 * @generated
	 */
	public void setEnclosingOperand(EObject newValue) {
		if (newValue != null) {
			enclosingOperand.setSelection(new StructuredSelection(newValue));
		} else {
			enclosingOperand.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.enclosingOperand);
		if (readOnly && enclosingOperand.isEnabled()) {
			enclosingOperand.setEnabled(false);
			enclosingOperand.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !enclosingOperand.isEnabled()) {
			enclosingOperand.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#setEnclosingOperandButtonMode(ButtonsModeEnum newValue)
	 */
	public void setEnclosingOperandButtonMode(ButtonsModeEnum newValue) {
		enclosingOperand.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addFilterEnclosingOperand(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEnclosingOperand(ViewerFilter filter) {
		enclosingOperand.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterEnclosingOperand(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEnclosingOperand(ViewerFilter filter) {
		enclosingOperand.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#initToBefore(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initToBefore(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		toBefore.setContentProvider(contentProvider);
		toBefore.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toBefore);
		if (readOnly && toBefore.getTable().isEnabled()) {
			toBefore.setEnabled(false);
			toBefore.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !toBefore.getTable().isEnabled()) {
			toBefore.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#updateToBefore()
	 * @generated
	 */
	public void updateToBefore() {
	toBefore.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addFilterToBefore(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToToBefore(ViewerFilter filter) {
		toBeforeFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterToBefore(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToToBefore(ViewerFilter filter) {
		toBeforeBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#isContainedInToBeforeTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInToBeforeTable(EObject element) {
		return ((ReferencesTableSettings)toBefore.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#getEvent()
	 * @generated
	 */
	public EObject getEvent() {
		if (event.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) event.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#initEvent(EObjectFlatComboSettings)
	 */
	public void initEvent(EObjectFlatComboSettings settings) {
		event.setInput(settings);
		if (current != null) {
			event.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event);
		if (readOnly && event.isEnabled()) {
			event.setEnabled(false);
			event.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !event.isEnabled()) {
			event.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#setEvent(EObject newValue)
	 * @generated
	 */
	public void setEvent(EObject newValue) {
		if (newValue != null) {
			event.setSelection(new StructuredSelection(newValue));
		} else {
			event.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.event);
		if (readOnly && event.isEnabled()) {
			event.setEnabled(false);
			event.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !event.isEnabled()) {
			event.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#setEventButtonMode(ButtonsModeEnum newValue)
	 */
	public void setEventButtonMode(ButtonsModeEnum newValue) {
		event.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addFilterEvent(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEvent(ViewerFilter filter) {
		event.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterEvent(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEvent(ViewerFilter filter) {
		event.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#initToAfter(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initToAfter(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		toAfter.setContentProvider(contentProvider);
		toAfter.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.toAfter);
		if (readOnly && toAfter.getTable().isEnabled()) {
			toAfter.setEnabled(false);
			toAfter.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !toAfter.getTable().isEnabled()) {
			toAfter.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#updateToAfter()
	 * @generated
	 */
	public void updateToAfter() {
	toAfter.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addFilterToAfter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToToAfter(ViewerFilter filter) {
		toAfterFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterToAfter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToToAfter(ViewerFilter filter) {
		toAfterBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#isContainedInToAfterTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInToAfterTable(EObject element) {
		return ((ReferencesTableSettings)toAfter.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#getExecution()
	 * @generated
	 */
	public EObject getExecution() {
		if (execution.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) execution.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#initExecution(EObjectFlatComboSettings)
	 */
	public void initExecution(EObjectFlatComboSettings settings) {
		execution.setInput(settings);
		if (current != null) {
			execution.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution);
		if (readOnly && execution.isEnabled()) {
			execution.setEnabled(false);
			execution.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !execution.isEnabled()) {
			execution.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#setExecution(EObject newValue)
	 * @generated
	 */
	public void setExecution(EObject newValue) {
		if (newValue != null) {
			execution.setSelection(new StructuredSelection(newValue));
		} else {
			execution.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ExecutionOccurrenceSpecification.Properties.execution);
		if (readOnly && execution.isEnabled()) {
			execution.setEnabled(false);
			execution.setToolTipText(UmlMessages.ExecutionOccurrenceSpecification_ReadOnly);
		} else if (!readOnly && !execution.isEnabled()) {
			execution.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#setExecutionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setExecutionButtonMode(ButtonsModeEnum newValue) {
		execution.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addFilterExecution(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToExecution(ViewerFilter filter) {
		execution.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ExecutionOccurrenceSpecificationPropertiesEditionPart#addBusinessFilterExecution(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToExecution(ViewerFilter filter) {
		execution.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.ExecutionOccurrenceSpecification_Part_Title;
	}



}
