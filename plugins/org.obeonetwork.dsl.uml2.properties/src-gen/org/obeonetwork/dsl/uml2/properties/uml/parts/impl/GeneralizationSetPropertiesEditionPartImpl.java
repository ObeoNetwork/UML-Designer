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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class GeneralizationSetPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, GeneralizationSetPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer owningTemplateParameter;
	protected EObjectFlatComboViewer templateParameter;
	protected Button isCovering;
	protected Button isDisjoint;
	protected EObjectFlatComboViewer powertype;
	protected ReferencesTable generalization;
	protected List<ViewerFilter> generalizationBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> generalizationFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public GeneralizationSetPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence generalizationSetStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = generalizationSetStep.addStep(UmlViewsRepository.GeneralizationSet.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.GeneralizationSet.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.GeneralizationSet.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.GeneralizationSet.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.GeneralizationSet.Properties.owningTemplateParameter);
		propertiesStep.addStep(UmlViewsRepository.GeneralizationSet.Properties.templateParameter);
		propertiesStep.addStep(UmlViewsRepository.GeneralizationSet.Properties.isCovering);
		propertiesStep.addStep(UmlViewsRepository.GeneralizationSet.Properties.isDisjoint);
		propertiesStep.addStep(UmlViewsRepository.GeneralizationSet.Properties.powertype);
		propertiesStep.addStep(UmlViewsRepository.GeneralizationSet.Properties.generalization);
		
		
		composer = new PartComposer(generalizationSetStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.GeneralizationSet.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.GeneralizationSet.Properties.name) {
					return createNameText(parent);
				}
				if (key == UmlViewsRepository.GeneralizationSet.Properties.visibility) {
					return createVisibilityEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.GeneralizationSet.Properties.clientDependency) {
					return createClientDependencyAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.GeneralizationSet.Properties.owningTemplateParameter) {
					return createOwningTemplateParameterFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.GeneralizationSet.Properties.templateParameter) {
					return createTemplateParameterFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.GeneralizationSet.Properties.isCovering) {
					return createIsCoveringCheckbox(parent);
				}
				if (key == UmlViewsRepository.GeneralizationSet.Properties.isDisjoint) {
					return createIsDisjointCheckbox(parent);
				}
				if (key == UmlViewsRepository.GeneralizationSet.Properties.powertype) {
					return createPowertypeFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.GeneralizationSet.Properties.generalization) {
					return createGeneralizationAdvancedReferencesTable(parent);
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
		propertiesGroup.setText(UmlMessages.GeneralizationSetPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	
	protected Composite createNameText(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.GeneralizationSetPropertiesEditionPart_NameLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.GeneralizationSet.Properties.name, UmlViewsRepository.SWT_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, UmlViewsRepository.GeneralizationSet.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.GeneralizationSet.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.GeneralizationSetPropertiesEditionPart_VisibilityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.GeneralizationSet.Properties.visibility, UmlViewsRepository.SWT_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.GeneralizationSet.Properties.visibility);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.GeneralizationSet.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
		this.clientDependency = new ReferencesTable(UmlMessages.GeneralizationSetPropertiesEditionPart_ClientDependencyLabel, new ReferencesTableListener() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.GeneralizationSet.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
		this.clientDependency.createControls(parent);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.GeneralizationSet.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	protected Composite createOwningTemplateParameterFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.GeneralizationSetPropertiesEditionPart_OwningTemplateParameterLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.GeneralizationSet.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND));
		owningTemplateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.GeneralizationSet.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND));
		owningTemplateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		owningTemplateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.owningTemplateParameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getOwningTemplateParameter()));
			}

		});
		GridData owningTemplateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		owningTemplateParameter.setLayoutData(owningTemplateParameterData);
		owningTemplateParameter.setID(UmlViewsRepository.GeneralizationSet.Properties.owningTemplateParameter);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.GeneralizationSet.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createTemplateParameterFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.GeneralizationSetPropertiesEditionPart_TemplateParameterLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.GeneralizationSet.Properties.templateParameter, UmlViewsRepository.SWT_KIND));
		templateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.GeneralizationSet.Properties.templateParameter, UmlViewsRepository.SWT_KIND));
		templateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		templateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.templateParameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getTemplateParameter()));
			}

		});
		GridData templateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		templateParameter.setLayoutData(templateParameterData);
		templateParameter.setID(UmlViewsRepository.GeneralizationSet.Properties.templateParameter);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.GeneralizationSet.Properties.templateParameter, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsCoveringCheckbox(Composite parent) {
		isCovering = new Button(parent, SWT.CHECK);
		isCovering.setText(UmlMessages.GeneralizationSetPropertiesEditionPart_IsCoveringLabel);
		isCovering.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.isCovering, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isCovering.getSelection())));
			}

		});
		GridData isCoveringData = new GridData(GridData.FILL_HORIZONTAL);
		isCoveringData.horizontalSpan = 2;
		isCovering.setLayoutData(isCoveringData);
		EditingUtils.setID(isCovering, UmlViewsRepository.GeneralizationSet.Properties.isCovering);
		EditingUtils.setEEFtype(isCovering, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.GeneralizationSet.Properties.isCovering, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsDisjointCheckbox(Composite parent) {
		isDisjoint = new Button(parent, SWT.CHECK);
		isDisjoint.setText(UmlMessages.GeneralizationSetPropertiesEditionPart_IsDisjointLabel);
		isDisjoint.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.isDisjoint, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isDisjoint.getSelection())));
			}

		});
		GridData isDisjointData = new GridData(GridData.FILL_HORIZONTAL);
		isDisjointData.horizontalSpan = 2;
		isDisjoint.setLayoutData(isDisjointData);
		EditingUtils.setID(isDisjoint, UmlViewsRepository.GeneralizationSet.Properties.isDisjoint);
		EditingUtils.setEEFtype(isDisjoint, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.GeneralizationSet.Properties.isDisjoint, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createPowertypeFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.GeneralizationSetPropertiesEditionPart_PowertypeLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.GeneralizationSet.Properties.powertype, UmlViewsRepository.SWT_KIND));
		powertype = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.GeneralizationSet.Properties.powertype, UmlViewsRepository.SWT_KIND));
		powertype.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		powertype.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.powertype, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getPowertype()));
			}

		});
		GridData powertypeData = new GridData(GridData.FILL_HORIZONTAL);
		powertype.setLayoutData(powertypeData);
		powertype.setID(UmlViewsRepository.GeneralizationSet.Properties.powertype);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.GeneralizationSet.Properties.powertype, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createGeneralizationAdvancedReferencesTable(Composite parent) {
		this.generalization = new ReferencesTable(UmlMessages.GeneralizationSetPropertiesEditionPart_GeneralizationLabel, new ReferencesTableListener() {
			public void handleAdd() { addGeneralization(); }
			public void handleEdit(EObject element) { editGeneralization(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveGeneralization(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromGeneralization(element); }
			public void navigateTo(EObject element) { }
		});
		this.generalization.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.GeneralizationSet.Properties.generalization, UmlViewsRepository.SWT_KIND));
		this.generalization.createControls(parent);
		this.generalization.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.generalization, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData generalizationData = new GridData(GridData.FILL_HORIZONTAL);
		generalizationData.horizontalSpan = 3;
		this.generalization.setLayoutData(generalizationData);
		this.generalization.disableMove();
		generalization.setID(UmlViewsRepository.GeneralizationSet.Properties.generalization);
		generalization.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addGeneralization() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(generalization.getInput(), generalizationFilters, generalizationBusinessFilters,
		"generalization", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.generalization,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				generalization.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveGeneralization(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.generalization, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		generalization.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromGeneralization(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralizationSetPropertiesEditionPartImpl.this, UmlViewsRepository.GeneralizationSet.Properties.generalization, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		generalization.refresh();
	}

	/**
	 * 
	 */
	protected void editGeneralization(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				generalization.refresh();
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#getName()
	 * 
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#setName(String newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#getVisibility()
	 * 
	 */
	public Enumerator getVisibility() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#initVisibility(EEnum eenum, Enumerator current)
	 */
	public void initVisibility(EEnum eenum, Enumerator current) {
		visibility.setInput(eenum.getELiterals());
		visibility.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * 
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#updateClientDependency()
	 * 
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * 
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#getOwningTemplateParameter()
	 * 
	 */
	public EObject getOwningTemplateParameter() {
		if (owningTemplateParameter.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) owningTemplateParameter.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#initOwningTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initOwningTemplateParameter(EObjectFlatComboSettings settings) {
		owningTemplateParameter.setInput(settings);
		if (current != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#setOwningTemplateParameter(EObject newValue)
	 * 
	 */
	public void setOwningTemplateParameter(EObject newValue) {
		if (newValue != null) {
			owningTemplateParameter.setSelection(new StructuredSelection(newValue));
		} else {
			owningTemplateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		owningTemplateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#addFilterOwningTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#addBusinessFilterOwningTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#getTemplateParameter()
	 * 
	 */
	public EObject getTemplateParameter() {
		if (templateParameter.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) templateParameter.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#initTemplateParameter(EObjectFlatComboSettings)
	 */
	public void initTemplateParameter(EObjectFlatComboSettings settings) {
		templateParameter.setInput(settings);
		if (current != null) {
			templateParameter.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#setTemplateParameter(EObject newValue)
	 * 
	 */
	public void setTemplateParameter(EObject newValue) {
		if (newValue != null) {
			templateParameter.setSelection(new StructuredSelection(newValue));
		} else {
			templateParameter.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#setTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		templateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#addFilterTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#addBusinessFilterTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#getIsCovering()
	 * 
	 */
	public Boolean getIsCovering() {
		return Boolean.valueOf(isCovering.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#setIsCovering(Boolean newValue)
	 * 
	 */
	public void setIsCovering(Boolean newValue) {
		if (newValue != null) {
			isCovering.setSelection(newValue.booleanValue());
		} else {
			isCovering.setSelection(false);
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#getIsDisjoint()
	 * 
	 */
	public Boolean getIsDisjoint() {
		return Boolean.valueOf(isDisjoint.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#setIsDisjoint(Boolean newValue)
	 * 
	 */
	public void setIsDisjoint(Boolean newValue) {
		if (newValue != null) {
			isDisjoint.setSelection(newValue.booleanValue());
		} else {
			isDisjoint.setSelection(false);
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#getPowertype()
	 * 
	 */
	public EObject getPowertype() {
		if (powertype.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) powertype.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#initPowertype(EObjectFlatComboSettings)
	 */
	public void initPowertype(EObjectFlatComboSettings settings) {
		powertype.setInput(settings);
		if (current != null) {
			powertype.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#setPowertype(EObject newValue)
	 * 
	 */
	public void setPowertype(EObject newValue) {
		if (newValue != null) {
			powertype.setSelection(new StructuredSelection(newValue));
		} else {
			powertype.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#setPowertypeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setPowertypeButtonMode(ButtonsModeEnum newValue) {
		powertype.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#addFilterPowertype(ViewerFilter filter)
	 * 
	 */
	public void addFilterToPowertype(ViewerFilter filter) {
		powertype.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#addBusinessFilterPowertype(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToPowertype(ViewerFilter filter) {
		powertype.addBusinessRuleFilter(filter);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#initGeneralization(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initGeneralization(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		generalization.setContentProvider(contentProvider);
		generalization.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#updateGeneralization()
	 * 
	 */
	public void updateGeneralization() {
	generalization.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#addFilterGeneralization(ViewerFilter filter)
	 * 
	 */
	public void addFilterToGeneralization(ViewerFilter filter) {
		generalizationFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#addBusinessFilterGeneralization(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToGeneralization(ViewerFilter filter) {
		generalizationBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralizationSetPropertiesEditionPart#isContainedInGeneralizationTable(EObject element)
	 * 
	 */
	public boolean isContainedInGeneralizationTable(EObject element) {
		return ((ReferencesTableSettings)generalization.getInput()).contains(element);
	}







	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.GeneralizationSet_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
