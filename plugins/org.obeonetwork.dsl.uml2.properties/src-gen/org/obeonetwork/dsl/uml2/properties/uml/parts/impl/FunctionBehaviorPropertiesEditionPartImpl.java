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

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
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
import org.eclipse.emf.eef.runtime.ui.widgets.EEFFeatureEditorDialog;
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
import org.eclipse.jface.window.Window;
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
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class FunctionBehaviorPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, FunctionBehaviorPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected Button isLeaf;
	protected EObjectFlatComboViewer owningTemplateParameter;
	protected EObjectFlatComboViewer templateParameter;
	protected Button isAbstract;
	protected ReferencesTable powertypeExtent;
	protected List<ViewerFilter> powertypeExtentBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> powertypeExtentFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable redefinedClassifier;
	protected List<ViewerFilter> redefinedClassifierBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> redefinedClassifierFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer representation;
	protected ReferencesTable useCase;
	protected List<ViewerFilter> useCaseBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> useCaseFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer classifierBehavior;
	protected Button isActive;
	protected Button isReentrant;
	protected ReferencesTable redefinedBehavior;
	protected List<ViewerFilter> redefinedBehaviorBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> redefinedBehaviorFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable precondition;
	protected List<ViewerFilter> preconditionBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> preconditionFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable postcondition;
	protected List<ViewerFilter> postconditionBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> postconditionFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer specification;
	protected Text body;
	protected Button editBody;
	private EList bodyList;
	protected Text language;
	protected Button editLanguage;
	private EList languageList;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public FunctionBehaviorPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence functionBehaviorStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = functionBehaviorStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.isLeaf);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.owningTemplateParameter);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.templateParameter);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.isAbstract);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.powertypeExtent);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.redefinedClassifier);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.representation);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.useCase);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.classifierBehavior);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.isActive);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.isReentrant);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.redefinedBehavior);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.precondition);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.postcondition);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.specification);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.body);
		propertiesStep.addStep(UmlViewsRepository.FunctionBehavior.Properties.language);
		
		
		composer = new PartComposer(functionBehaviorStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.FunctionBehavior.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.name) {
					return createNameText(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.visibility) {
					return createVisibilityEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.clientDependency) {
					return createClientDependencyAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.isLeaf) {
					return createIsLeafCheckbox(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.owningTemplateParameter) {
					return createOwningTemplateParameterFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.templateParameter) {
					return createTemplateParameterFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.isAbstract) {
					return createIsAbstractCheckbox(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.powertypeExtent) {
					return createPowertypeExtentAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.redefinedClassifier) {
					return createRedefinedClassifierAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.representation) {
					return createRepresentationFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.useCase) {
					return createUseCaseAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.classifierBehavior) {
					return createClassifierBehaviorFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.isActive) {
					return createIsActiveCheckbox(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.isReentrant) {
					return createIsReentrantCheckbox(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.redefinedBehavior) {
					return createRedefinedBehaviorAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.precondition) {
					return createPreconditionAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.postcondition) {
					return createPostconditionAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.specification) {
					return createSpecificationFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.body) {
					return createBodyMultiValuedEditor(parent);
				}
				if (key == UmlViewsRepository.FunctionBehavior.Properties.language) {
					return createLanguageMultiValuedEditor(parent);
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
		propertiesGroup.setText(UmlMessages.FunctionBehaviorPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	
	protected Composite createNameText(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.FunctionBehaviorPropertiesEditionPart_NameLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.FunctionBehavior.Properties.name, UmlViewsRepository.SWT_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, UmlViewsRepository.FunctionBehavior.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.FunctionBehaviorPropertiesEditionPart_VisibilityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.FunctionBehavior.Properties.visibility, UmlViewsRepository.SWT_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.FunctionBehavior.Properties.visibility);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
		this.clientDependency = new ReferencesTable(UmlMessages.FunctionBehaviorPropertiesEditionPart_ClientDependencyLabel, new ReferencesTableListener() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
		this.clientDependency.createControls(parent);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.FunctionBehavior.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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

	
	protected Composite createIsLeafCheckbox(Composite parent) {
		isLeaf = new Button(parent, SWT.CHECK);
		isLeaf.setText(UmlMessages.FunctionBehaviorPropertiesEditionPart_IsLeafLabel);
		isLeaf.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
			}

		});
		GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
		isLeafData.horizontalSpan = 2;
		isLeaf.setLayoutData(isLeafData);
		EditingUtils.setID(isLeaf, UmlViewsRepository.FunctionBehavior.Properties.isLeaf);
		EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.isLeaf, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createOwningTemplateParameterFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.FunctionBehaviorPropertiesEditionPart_OwningTemplateParameterLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.FunctionBehavior.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND));
		owningTemplateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.FunctionBehavior.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND));
		owningTemplateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		owningTemplateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.owningTemplateParameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getOwningTemplateParameter()));
			}

		});
		GridData owningTemplateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		owningTemplateParameter.setLayoutData(owningTemplateParameterData);
		owningTemplateParameter.setID(UmlViewsRepository.FunctionBehavior.Properties.owningTemplateParameter);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.owningTemplateParameter, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createTemplateParameterFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.FunctionBehaviorPropertiesEditionPart_TemplateParameterLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.FunctionBehavior.Properties.templateParameter, UmlViewsRepository.SWT_KIND));
		templateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.FunctionBehavior.Properties.templateParameter, UmlViewsRepository.SWT_KIND));
		templateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		templateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.templateParameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getTemplateParameter()));
			}

		});
		GridData templateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		templateParameter.setLayoutData(templateParameterData);
		templateParameter.setID(UmlViewsRepository.FunctionBehavior.Properties.templateParameter);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.templateParameter, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsAbstractCheckbox(Composite parent) {
		isAbstract = new Button(parent, SWT.CHECK);
		isAbstract.setText(UmlMessages.FunctionBehaviorPropertiesEditionPart_IsAbstractLabel);
		isAbstract.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.isAbstract, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isAbstract.getSelection())));
			}

		});
		GridData isAbstractData = new GridData(GridData.FILL_HORIZONTAL);
		isAbstractData.horizontalSpan = 2;
		isAbstract.setLayoutData(isAbstractData);
		EditingUtils.setID(isAbstract, UmlViewsRepository.FunctionBehavior.Properties.isAbstract);
		EditingUtils.setEEFtype(isAbstract, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.isAbstract, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createPowertypeExtentAdvancedReferencesTable(Composite parent) {
		this.powertypeExtent = new ReferencesTable(UmlMessages.FunctionBehaviorPropertiesEditionPart_PowertypeExtentLabel, new ReferencesTableListener() {
			public void handleAdd() { addPowertypeExtent(); }
			public void handleEdit(EObject element) { editPowertypeExtent(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { movePowertypeExtent(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromPowertypeExtent(element); }
			public void navigateTo(EObject element) { }
		});
		this.powertypeExtent.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.powertypeExtent, UmlViewsRepository.SWT_KIND));
		this.powertypeExtent.createControls(parent);
		this.powertypeExtent.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.powertypeExtent, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData powertypeExtentData = new GridData(GridData.FILL_HORIZONTAL);
		powertypeExtentData.horizontalSpan = 3;
		this.powertypeExtent.setLayoutData(powertypeExtentData);
		this.powertypeExtent.disableMove();
		powertypeExtent.setID(UmlViewsRepository.FunctionBehavior.Properties.powertypeExtent);
		powertypeExtent.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addPowertypeExtent() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(powertypeExtent.getInput(), powertypeExtentFilters, powertypeExtentBusinessFilters,
		"powertypeExtent", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.powertypeExtent,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				powertypeExtent.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void movePowertypeExtent(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.powertypeExtent, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		powertypeExtent.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromPowertypeExtent(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.powertypeExtent, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		powertypeExtent.refresh();
	}

	/**
	 * 
	 */
	protected void editPowertypeExtent(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				powertypeExtent.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createRedefinedClassifierAdvancedReferencesTable(Composite parent) {
		this.redefinedClassifier = new ReferencesTable(UmlMessages.FunctionBehaviorPropertiesEditionPart_RedefinedClassifierLabel, new ReferencesTableListener() {
			public void handleAdd() { addRedefinedClassifier(); }
			public void handleEdit(EObject element) { editRedefinedClassifier(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedClassifier(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRedefinedClassifier(element); }
			public void navigateTo(EObject element) { }
		});
		this.redefinedClassifier.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.redefinedClassifier, UmlViewsRepository.SWT_KIND));
		this.redefinedClassifier.createControls(parent);
		this.redefinedClassifier.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.redefinedClassifier, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData redefinedClassifierData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedClassifierData.horizontalSpan = 3;
		this.redefinedClassifier.setLayoutData(redefinedClassifierData);
		this.redefinedClassifier.disableMove();
		redefinedClassifier.setID(UmlViewsRepository.FunctionBehavior.Properties.redefinedClassifier);
		redefinedClassifier.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addRedefinedClassifier() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(redefinedClassifier.getInput(), redefinedClassifierFilters, redefinedClassifierBusinessFilters,
		"redefinedClassifier", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.redefinedClassifier,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				redefinedClassifier.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveRedefinedClassifier(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.redefinedClassifier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		redefinedClassifier.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromRedefinedClassifier(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.redefinedClassifier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		redefinedClassifier.refresh();
	}

	/**
	 * 
	 */
	protected void editRedefinedClassifier(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				redefinedClassifier.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createRepresentationFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.FunctionBehaviorPropertiesEditionPart_RepresentationLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.FunctionBehavior.Properties.representation, UmlViewsRepository.SWT_KIND));
		representation = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.FunctionBehavior.Properties.representation, UmlViewsRepository.SWT_KIND));
		representation.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		representation.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.representation, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getRepresentation()));
			}

		});
		GridData representationData = new GridData(GridData.FILL_HORIZONTAL);
		representation.setLayoutData(representationData);
		representation.setID(UmlViewsRepository.FunctionBehavior.Properties.representation);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.representation, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createUseCaseAdvancedReferencesTable(Composite parent) {
		this.useCase = new ReferencesTable(UmlMessages.FunctionBehaviorPropertiesEditionPart_UseCaseLabel, new ReferencesTableListener() {
			public void handleAdd() { addUseCase(); }
			public void handleEdit(EObject element) { editUseCase(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveUseCase(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromUseCase(element); }
			public void navigateTo(EObject element) { }
		});
		this.useCase.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.useCase, UmlViewsRepository.SWT_KIND));
		this.useCase.createControls(parent);
		this.useCase.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.useCase, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData useCaseData = new GridData(GridData.FILL_HORIZONTAL);
		useCaseData.horizontalSpan = 3;
		this.useCase.setLayoutData(useCaseData);
		this.useCase.disableMove();
		useCase.setID(UmlViewsRepository.FunctionBehavior.Properties.useCase);
		useCase.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addUseCase() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(useCase.getInput(), useCaseFilters, useCaseBusinessFilters,
		"useCase", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.useCase,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				useCase.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveUseCase(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.useCase, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		useCase.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromUseCase(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.useCase, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		useCase.refresh();
	}

	/**
	 * 
	 */
	protected void editUseCase(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				useCase.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createClassifierBehaviorFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.FunctionBehaviorPropertiesEditionPart_ClassifierBehaviorLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.FunctionBehavior.Properties.classifierBehavior, UmlViewsRepository.SWT_KIND));
		classifierBehavior = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.FunctionBehavior.Properties.classifierBehavior, UmlViewsRepository.SWT_KIND));
		classifierBehavior.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		classifierBehavior.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.classifierBehavior, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getClassifierBehavior()));
			}

		});
		GridData classifierBehaviorData = new GridData(GridData.FILL_HORIZONTAL);
		classifierBehavior.setLayoutData(classifierBehaviorData);
		classifierBehavior.setID(UmlViewsRepository.FunctionBehavior.Properties.classifierBehavior);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.classifierBehavior, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsActiveCheckbox(Composite parent) {
		isActive = new Button(parent, SWT.CHECK);
		isActive.setText(UmlMessages.FunctionBehaviorPropertiesEditionPart_IsActiveLabel);
		isActive.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.isActive, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isActive.getSelection())));
			}

		});
		GridData isActiveData = new GridData(GridData.FILL_HORIZONTAL);
		isActiveData.horizontalSpan = 2;
		isActive.setLayoutData(isActiveData);
		EditingUtils.setID(isActive, UmlViewsRepository.FunctionBehavior.Properties.isActive);
		EditingUtils.setEEFtype(isActive, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.isActive, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsReentrantCheckbox(Composite parent) {
		isReentrant = new Button(parent, SWT.CHECK);
		isReentrant.setText(UmlMessages.FunctionBehaviorPropertiesEditionPart_IsReentrantLabel);
		isReentrant.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.isReentrant, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isReentrant.getSelection())));
			}

		});
		GridData isReentrantData = new GridData(GridData.FILL_HORIZONTAL);
		isReentrantData.horizontalSpan = 2;
		isReentrant.setLayoutData(isReentrantData);
		EditingUtils.setID(isReentrant, UmlViewsRepository.FunctionBehavior.Properties.isReentrant);
		EditingUtils.setEEFtype(isReentrant, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.isReentrant, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createRedefinedBehaviorAdvancedReferencesTable(Composite parent) {
		this.redefinedBehavior = new ReferencesTable(UmlMessages.FunctionBehaviorPropertiesEditionPart_RedefinedBehaviorLabel, new ReferencesTableListener() {
			public void handleAdd() { addRedefinedBehavior(); }
			public void handleEdit(EObject element) { editRedefinedBehavior(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedBehavior(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRedefinedBehavior(element); }
			public void navigateTo(EObject element) { }
		});
		this.redefinedBehavior.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.redefinedBehavior, UmlViewsRepository.SWT_KIND));
		this.redefinedBehavior.createControls(parent);
		this.redefinedBehavior.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.redefinedBehavior, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData redefinedBehaviorData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedBehaviorData.horizontalSpan = 3;
		this.redefinedBehavior.setLayoutData(redefinedBehaviorData);
		this.redefinedBehavior.disableMove();
		redefinedBehavior.setID(UmlViewsRepository.FunctionBehavior.Properties.redefinedBehavior);
		redefinedBehavior.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addRedefinedBehavior() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(redefinedBehavior.getInput(), redefinedBehaviorFilters, redefinedBehaviorBusinessFilters,
		"redefinedBehavior", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.redefinedBehavior,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				redefinedBehavior.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveRedefinedBehavior(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.redefinedBehavior, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		redefinedBehavior.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromRedefinedBehavior(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.redefinedBehavior, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		redefinedBehavior.refresh();
	}

	/**
	 * 
	 */
	protected void editRedefinedBehavior(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				redefinedBehavior.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createPreconditionAdvancedReferencesTable(Composite parent) {
		this.precondition = new ReferencesTable(UmlMessages.FunctionBehaviorPropertiesEditionPart_PreconditionLabel, new ReferencesTableListener() {
			public void handleAdd() { addPrecondition(); }
			public void handleEdit(EObject element) { editPrecondition(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { movePrecondition(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromPrecondition(element); }
			public void navigateTo(EObject element) { }
		});
		this.precondition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.precondition, UmlViewsRepository.SWT_KIND));
		this.precondition.createControls(parent);
		this.precondition.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.precondition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData preconditionData = new GridData(GridData.FILL_HORIZONTAL);
		preconditionData.horizontalSpan = 3;
		this.precondition.setLayoutData(preconditionData);
		this.precondition.disableMove();
		precondition.setID(UmlViewsRepository.FunctionBehavior.Properties.precondition);
		precondition.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addPrecondition() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(precondition.getInput(), preconditionFilters, preconditionBusinessFilters,
		"precondition", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.precondition,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				precondition.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void movePrecondition(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.precondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		precondition.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromPrecondition(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.precondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		precondition.refresh();
	}

	/**
	 * 
	 */
	protected void editPrecondition(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				precondition.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createPostconditionAdvancedReferencesTable(Composite parent) {
		this.postcondition = new ReferencesTable(UmlMessages.FunctionBehaviorPropertiesEditionPart_PostconditionLabel, new ReferencesTableListener() {
			public void handleAdd() { addPostcondition(); }
			public void handleEdit(EObject element) { editPostcondition(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { movePostcondition(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromPostcondition(element); }
			public void navigateTo(EObject element) { }
		});
		this.postcondition.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.postcondition, UmlViewsRepository.SWT_KIND));
		this.postcondition.createControls(parent);
		this.postcondition.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.postcondition, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData postconditionData = new GridData(GridData.FILL_HORIZONTAL);
		postconditionData.horizontalSpan = 3;
		this.postcondition.setLayoutData(postconditionData);
		this.postcondition.disableMove();
		postcondition.setID(UmlViewsRepository.FunctionBehavior.Properties.postcondition);
		postcondition.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addPostcondition() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(postcondition.getInput(), postconditionFilters, postconditionBusinessFilters,
		"postcondition", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.postcondition,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				postcondition.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void movePostcondition(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.postcondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		postcondition.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromPostcondition(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.postcondition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		postcondition.refresh();
	}

	/**
	 * 
	 */
	protected void editPostcondition(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				postcondition.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createSpecificationFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.FunctionBehaviorPropertiesEditionPart_SpecificationLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.FunctionBehavior.Properties.specification, UmlViewsRepository.SWT_KIND));
		specification = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.FunctionBehavior.Properties.specification, UmlViewsRepository.SWT_KIND));
		specification.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		specification.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.specification, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getSpecification()));
			}

		});
		GridData specificationData = new GridData(GridData.FILL_HORIZONTAL);
		specification.setLayoutData(specificationData);
		specification.setID(UmlViewsRepository.FunctionBehavior.Properties.specification);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.FunctionBehavior.Properties.specification, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	protected Composite createBodyMultiValuedEditor(Composite parent) {
		body = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
		GridData bodyData = new GridData(GridData.FILL_HORIZONTAL);
		bodyData.horizontalSpan = 2;
		body.setLayoutData(bodyData);
		EditingUtils.setID(body, UmlViewsRepository.FunctionBehavior.Properties.body);
		EditingUtils.setEEFtype(body, "eef::MultiValuedEditor::field"); //$NON-NLS-1$
		editBody = new Button(parent, SWT.NONE);
		editBody.setText(UmlMessages.FunctionBehaviorPropertiesEditionPart_BodyLabel);
		GridData editBodyData = new GridData();
		editBody.setLayoutData(editBodyData);
		editBody.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected(SelectionEvent e) {
				EEFFeatureEditorDialog dialog = new EEFFeatureEditorDialog(
						body.getShell(), "FunctionBehavior", new AdapterFactoryLabelProvider(adapterFactory), //$NON-NLS-1$
						bodyList, UMLPackage.eINSTANCE.getOpaqueBehavior_Body().getEType(), null,
						false, true, 
						null, null);
				if (dialog.open() == Window.OK) {
					bodyList = dialog.getResult();
					if (bodyList == null) {
						bodyList = new BasicEList();
					}
					body.setText(bodyList.toString());
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.body, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new BasicEList(bodyList)));
					setHasChanged(true);
				}
			}
		});
		EditingUtils.setID(editBody, UmlViewsRepository.FunctionBehavior.Properties.body);
		EditingUtils.setEEFtype(editBody, "eef::MultiValuedEditor::browsebutton"); //$NON-NLS-1$
		return parent;
	}

	protected Composite createLanguageMultiValuedEditor(Composite parent) {
		language = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
		GridData languageData = new GridData(GridData.FILL_HORIZONTAL);
		languageData.horizontalSpan = 2;
		language.setLayoutData(languageData);
		EditingUtils.setID(language, UmlViewsRepository.FunctionBehavior.Properties.language);
		EditingUtils.setEEFtype(language, "eef::MultiValuedEditor::field"); //$NON-NLS-1$
		editLanguage = new Button(parent, SWT.NONE);
		editLanguage.setText(UmlMessages.FunctionBehaviorPropertiesEditionPart_LanguageLabel);
		GridData editLanguageData = new GridData();
		editLanguage.setLayoutData(editLanguageData);
		editLanguage.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected(SelectionEvent e) {
				EEFFeatureEditorDialog dialog = new EEFFeatureEditorDialog(
						language.getShell(), "FunctionBehavior", new AdapterFactoryLabelProvider(adapterFactory), //$NON-NLS-1$
						languageList, UMLPackage.eINSTANCE.getOpaqueBehavior_Language().getEType(), null,
						false, true, 
						null, null);
				if (dialog.open() == Window.OK) {
					languageList = dialog.getResult();
					if (languageList == null) {
						languageList = new BasicEList();
					}
					language.setText(languageList.toString());
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(FunctionBehaviorPropertiesEditionPartImpl.this, UmlViewsRepository.FunctionBehavior.Properties.language, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new BasicEList(languageList)));
					setHasChanged(true);
				}
			}
		});
		EditingUtils.setID(editLanguage, UmlViewsRepository.FunctionBehavior.Properties.language);
		EditingUtils.setEEFtype(editLanguage, "eef::MultiValuedEditor::browsebutton"); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#getName()
	 * 
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setName(String newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#getVisibility()
	 * 
	 */
	public Enumerator getVisibility() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#initVisibility(EEnum eenum, Enumerator current)
	 */
	public void initVisibility(EEnum eenum, Enumerator current) {
		visibility.setInput(eenum.getELiterals());
		visibility.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * 
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#updateClientDependency()
	 * 
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * 
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#getIsLeaf()
	 * 
	 */
	public Boolean getIsLeaf() {
		return Boolean.valueOf(isLeaf.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setIsLeaf(Boolean newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#getOwningTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#initOwningTemplateParameter(EObjectFlatComboSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setOwningTemplateParameter(EObject newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		owningTemplateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addFilterOwningTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addBusinessFilterOwningTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#getTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#initTemplateParameter(EObjectFlatComboSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setTemplateParameter(EObject newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		templateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addFilterTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addBusinessFilterTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#getIsAbstract()
	 * 
	 */
	public Boolean getIsAbstract() {
		return Boolean.valueOf(isAbstract.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setIsAbstract(Boolean newValue)
	 * 
	 */
	public void setIsAbstract(Boolean newValue) {
		if (newValue != null) {
			isAbstract.setSelection(newValue.booleanValue());
		} else {
			isAbstract.setSelection(false);
		}
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#initPowertypeExtent(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initPowertypeExtent(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		powertypeExtent.setContentProvider(contentProvider);
		powertypeExtent.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#updatePowertypeExtent()
	 * 
	 */
	public void updatePowertypeExtent() {
	powertypeExtent.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addFilterPowertypeExtent(ViewerFilter filter)
	 * 
	 */
	public void addFilterToPowertypeExtent(ViewerFilter filter) {
		powertypeExtentFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addBusinessFilterPowertypeExtent(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToPowertypeExtent(ViewerFilter filter) {
		powertypeExtentBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#isContainedInPowertypeExtentTable(EObject element)
	 * 
	 */
	public boolean isContainedInPowertypeExtentTable(EObject element) {
		return ((ReferencesTableSettings)powertypeExtent.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#initRedefinedClassifier(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedClassifier(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedClassifier.setContentProvider(contentProvider);
		redefinedClassifier.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#updateRedefinedClassifier()
	 * 
	 */
	public void updateRedefinedClassifier() {
	redefinedClassifier.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addFilterRedefinedClassifier(ViewerFilter filter)
	 * 
	 */
	public void addFilterToRedefinedClassifier(ViewerFilter filter) {
		redefinedClassifierFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addBusinessFilterRedefinedClassifier(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToRedefinedClassifier(ViewerFilter filter) {
		redefinedClassifierBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#isContainedInRedefinedClassifierTable(EObject element)
	 * 
	 */
	public boolean isContainedInRedefinedClassifierTable(EObject element) {
		return ((ReferencesTableSettings)redefinedClassifier.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#getRepresentation()
	 * 
	 */
	public EObject getRepresentation() {
		if (representation.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) representation.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#initRepresentation(EObjectFlatComboSettings)
	 */
	public void initRepresentation(EObjectFlatComboSettings settings) {
		representation.setInput(settings);
		if (current != null) {
			representation.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setRepresentation(EObject newValue)
	 * 
	 */
	public void setRepresentation(EObject newValue) {
		if (newValue != null) {
			representation.setSelection(new StructuredSelection(newValue));
		} else {
			representation.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setRepresentationButtonMode(ButtonsModeEnum newValue)
	 */
	public void setRepresentationButtonMode(ButtonsModeEnum newValue) {
		representation.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addFilterRepresentation(ViewerFilter filter)
	 * 
	 */
	public void addFilterToRepresentation(ViewerFilter filter) {
		representation.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addBusinessFilterRepresentation(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToRepresentation(ViewerFilter filter) {
		representation.addBusinessRuleFilter(filter);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#initUseCase(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initUseCase(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		useCase.setContentProvider(contentProvider);
		useCase.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#updateUseCase()
	 * 
	 */
	public void updateUseCase() {
	useCase.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addFilterUseCase(ViewerFilter filter)
	 * 
	 */
	public void addFilterToUseCase(ViewerFilter filter) {
		useCaseFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addBusinessFilterUseCase(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToUseCase(ViewerFilter filter) {
		useCaseBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#isContainedInUseCaseTable(EObject element)
	 * 
	 */
	public boolean isContainedInUseCaseTable(EObject element) {
		return ((ReferencesTableSettings)useCase.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#getClassifierBehavior()
	 * 
	 */
	public EObject getClassifierBehavior() {
		if (classifierBehavior.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) classifierBehavior.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#initClassifierBehavior(EObjectFlatComboSettings)
	 */
	public void initClassifierBehavior(EObjectFlatComboSettings settings) {
		classifierBehavior.setInput(settings);
		if (current != null) {
			classifierBehavior.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setClassifierBehavior(EObject newValue)
	 * 
	 */
	public void setClassifierBehavior(EObject newValue) {
		if (newValue != null) {
			classifierBehavior.setSelection(new StructuredSelection(newValue));
		} else {
			classifierBehavior.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setClassifierBehaviorButtonMode(ButtonsModeEnum newValue)
	 */
	public void setClassifierBehaviorButtonMode(ButtonsModeEnum newValue) {
		classifierBehavior.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addFilterClassifierBehavior(ViewerFilter filter)
	 * 
	 */
	public void addFilterToClassifierBehavior(ViewerFilter filter) {
		classifierBehavior.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addBusinessFilterClassifierBehavior(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToClassifierBehavior(ViewerFilter filter) {
		classifierBehavior.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#getIsActive()
	 * 
	 */
	public Boolean getIsActive() {
		return Boolean.valueOf(isActive.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setIsActive(Boolean newValue)
	 * 
	 */
	public void setIsActive(Boolean newValue) {
		if (newValue != null) {
			isActive.setSelection(newValue.booleanValue());
		} else {
			isActive.setSelection(false);
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#getIsReentrant()
	 * 
	 */
	public Boolean getIsReentrant() {
		return Boolean.valueOf(isReentrant.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setIsReentrant(Boolean newValue)
	 * 
	 */
	public void setIsReentrant(Boolean newValue) {
		if (newValue != null) {
			isReentrant.setSelection(newValue.booleanValue());
		} else {
			isReentrant.setSelection(false);
		}
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#initRedefinedBehavior(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedBehavior(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedBehavior.setContentProvider(contentProvider);
		redefinedBehavior.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#updateRedefinedBehavior()
	 * 
	 */
	public void updateRedefinedBehavior() {
	redefinedBehavior.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addFilterRedefinedBehavior(ViewerFilter filter)
	 * 
	 */
	public void addFilterToRedefinedBehavior(ViewerFilter filter) {
		redefinedBehaviorFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addBusinessFilterRedefinedBehavior(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToRedefinedBehavior(ViewerFilter filter) {
		redefinedBehaviorBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#isContainedInRedefinedBehaviorTable(EObject element)
	 * 
	 */
	public boolean isContainedInRedefinedBehaviorTable(EObject element) {
		return ((ReferencesTableSettings)redefinedBehavior.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#initPrecondition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initPrecondition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		precondition.setContentProvider(contentProvider);
		precondition.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#updatePrecondition()
	 * 
	 */
	public void updatePrecondition() {
	precondition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addFilterPrecondition(ViewerFilter filter)
	 * 
	 */
	public void addFilterToPrecondition(ViewerFilter filter) {
		preconditionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addBusinessFilterPrecondition(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToPrecondition(ViewerFilter filter) {
		preconditionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#isContainedInPreconditionTable(EObject element)
	 * 
	 */
	public boolean isContainedInPreconditionTable(EObject element) {
		return ((ReferencesTableSettings)precondition.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#initPostcondition(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initPostcondition(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		postcondition.setContentProvider(contentProvider);
		postcondition.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#updatePostcondition()
	 * 
	 */
	public void updatePostcondition() {
	postcondition.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addFilterPostcondition(ViewerFilter filter)
	 * 
	 */
	public void addFilterToPostcondition(ViewerFilter filter) {
		postconditionFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addBusinessFilterPostcondition(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToPostcondition(ViewerFilter filter) {
		postconditionBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#isContainedInPostconditionTable(EObject element)
	 * 
	 */
	public boolean isContainedInPostconditionTable(EObject element) {
		return ((ReferencesTableSettings)postcondition.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#getSpecification()
	 * 
	 */
	public EObject getSpecification() {
		if (specification.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) specification.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#initSpecification(EObjectFlatComboSettings)
	 */
	public void initSpecification(EObjectFlatComboSettings settings) {
		specification.setInput(settings);
		if (current != null) {
			specification.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setSpecification(EObject newValue)
	 * 
	 */
	public void setSpecification(EObject newValue) {
		if (newValue != null) {
			specification.setSelection(new StructuredSelection(newValue));
		} else {
			specification.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setSpecificationButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSpecificationButtonMode(ButtonsModeEnum newValue) {
		specification.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addFilterSpecification(ViewerFilter filter)
	 * 
	 */
	public void addFilterToSpecification(ViewerFilter filter) {
		specification.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#addBusinessFilterSpecification(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToSpecification(ViewerFilter filter) {
		specification.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#getBody()
	 * 
	 */
	public EList getBody() {
		return bodyList;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setBody(EList newValue)
	 * 
	 */
	public void setBody(EList newValue) {
		bodyList = newValue;
		if (newValue != null) {
			body.setText(bodyList.toString());
		} else {
			body.setText(""); //$NON-NLS-1$
		}
	}

	public void addToBody(Object newValue) {
		bodyList.add(newValue);
		if (newValue != null) {
			body.setText(bodyList.toString());
		} else {
			body.setText(""); //$NON-NLS-1$
		}
	}

	public void removeToBody(Object newValue) {
		bodyList.remove(newValue);
		if (newValue != null) {
			body.setText(bodyList.toString());
		} else {
			body.setText(""); //$NON-NLS-1$
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#getLanguage()
	 * 
	 */
	public EList getLanguage() {
		return languageList;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.FunctionBehaviorPropertiesEditionPart#setLanguage(EList newValue)
	 * 
	 */
	public void setLanguage(EList newValue) {
		languageList = newValue;
		if (newValue != null) {
			language.setText(languageList.toString());
		} else {
			language.setText(""); //$NON-NLS-1$
		}
	}

	public void addToLanguage(Object newValue) {
		languageList.add(newValue);
		if (newValue != null) {
			language.setText(languageList.toString());
		} else {
			language.setText(""); //$NON-NLS-1$
		}
	}

	public void removeToLanguage(Object newValue) {
		languageList.remove(newValue);
		if (newValue != null) {
			language.setText(languageList.toString());
		} else {
			language.setText(""); //$NON-NLS-1$
		}
	}







	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.FunctionBehavior_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
