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
package org.obeonetwork.dsl.uml2.properties.uml.parts.forms;

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
import org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class PortPropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, PortPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
		protected ReferencesTable clientDependency;
		protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected Button isLeaf;
	protected Button isStatic;
	protected EObjectFlatComboViewer type;
	protected Button isOrdered;
	protected Button isUnique;
	protected Button isReadOnly;
	protected EObjectFlatComboViewer owningTemplateParameter;
	protected EObjectFlatComboViewer templateParameter;
	protected EObjectFlatComboViewer class_;
	protected EObjectFlatComboViewer datatype;
	protected Button isDerived;
	protected Button isDerivedUnion;
	protected EMFComboViewer aggregation;
		protected ReferencesTable redefinedProperty;
		protected List<ViewerFilter> redefinedPropertyBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> redefinedPropertyFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer owningAssociation;
		protected ReferencesTable subsettedProperty;
		protected List<ViewerFilter> subsettedPropertyBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> subsettedPropertyFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer association;
	protected EObjectFlatComboViewer associationEnd;
	protected Button isBehavior;
	protected Button isService;
		protected ReferencesTable redefinedPort;
		protected List<ViewerFilter> redefinedPortBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> redefinedPortFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer protocol;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public PortPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence portStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = portStep.addStep(UmlViewsRepository.Port.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.isLeaf);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.isStatic);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.type);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.isOrdered);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.isUnique);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.isReadOnly);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.owningTemplateParameter);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.templateParameter);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.class_);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.datatype);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.isDerived);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.isDerivedUnion);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.aggregation);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.redefinedProperty);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.owningAssociation);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.subsettedProperty);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.association);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.associationEnd);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.isBehavior);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.isService);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.redefinedPort);
		propertiesStep.addStep(UmlViewsRepository.Port.Properties.protocol);
		
		
		composer = new PartComposer(portStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.Port.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.name) {
					return 		createNameText(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.visibility) {
					return createVisibilityEMFComboViewer(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.clientDependency) {
					return createClientDependencyReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.isLeaf) {
					return createIsLeafCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.isStatic) {
					return createIsStaticCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.type) {
					return createTypeFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.Port.Properties.isOrdered) {
					return createIsOrderedCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.isUnique) {
					return createIsUniqueCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.isReadOnly) {
					return createIsReadOnlyCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.owningTemplateParameter) {
					return createOwningTemplateParameterFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.Port.Properties.templateParameter) {
					return createTemplateParameterFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.Port.Properties.class_) {
					return createClass_FlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.Port.Properties.datatype) {
					return createDatatypeFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.Port.Properties.isDerived) {
					return createIsDerivedCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.isDerivedUnion) {
					return createIsDerivedUnionCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.aggregation) {
					return createAggregationEMFComboViewer(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.redefinedProperty) {
					return createRedefinedPropertyReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.owningAssociation) {
					return createOwningAssociationFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.Port.Properties.subsettedProperty) {
					return createSubsettedPropertyReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.association) {
					return createAssociationFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.Port.Properties.associationEnd) {
					return createAssociationEndFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.Port.Properties.isBehavior) {
					return createIsBehaviorCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.isService) {
					return createIsServiceCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.redefinedPort) {
					return createRedefinedPortReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Port.Properties.protocol) {
					return createProtocolFlatComboViewer(parent, widgetFactory);
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
		propertiesSection.setText(UmlMessages.PortPropertiesEditionPart_PropertiesGroupLabel);
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
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PortPropertiesEditionPart_NameLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.name, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}
		});
		EditingUtils.setID(name, UmlViewsRepository.Port.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PortPropertiesEditionPart_VisibilityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.visibility, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.Port.Properties.visibility);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.clientDependency = new ReferencesTable(UmlMessages.PortPropertiesEditionPart_ClientDependencyLabel, new ReferencesTableListener	() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
		this.clientDependency.createControls(parent, widgetFactory);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.Port.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		isLeaf = widgetFactory.createButton(parent, UmlMessages.PortPropertiesEditionPart_IsLeafLabel, SWT.CHECK);
		isLeaf.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
			}

		});
		GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
		isLeafData.horizontalSpan = 2;
		isLeaf.setLayoutData(isLeafData);
		EditingUtils.setID(isLeaf, UmlViewsRepository.Port.Properties.isLeaf);
		EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.isLeaf, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsStaticCheckbox(FormToolkit widgetFactory, Composite parent) {
		isStatic = widgetFactory.createButton(parent, UmlMessages.PortPropertiesEditionPart_IsStaticLabel, SWT.CHECK);
		isStatic.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.isStatic, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isStatic.getSelection())));
			}

		});
		GridData isStaticData = new GridData(GridData.FILL_HORIZONTAL);
		isStaticData.horizontalSpan = 2;
		isStatic.setLayoutData(isStaticData);
		EditingUtils.setID(isStatic, UmlViewsRepository.Port.Properties.isStatic);
		EditingUtils.setEEFtype(isStatic, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.isStatic, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createTypeFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PortPropertiesEditionPart_TypeLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.type, UmlViewsRepository.FORM_KIND));
		type = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.type, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(type);
		type.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData typeData = new GridData(GridData.FILL_HORIZONTAL);
		type.setLayoutData(typeData);
		type.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.type, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getType()));
			}

		});
		type.setID(UmlViewsRepository.Port.Properties.type);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.type, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsOrderedCheckbox(FormToolkit widgetFactory, Composite parent) {
		isOrdered = widgetFactory.createButton(parent, UmlMessages.PortPropertiesEditionPart_IsOrderedLabel, SWT.CHECK);
		isOrdered.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.isOrdered, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isOrdered.getSelection())));
			}

		});
		GridData isOrderedData = new GridData(GridData.FILL_HORIZONTAL);
		isOrderedData.horizontalSpan = 2;
		isOrdered.setLayoutData(isOrderedData);
		EditingUtils.setID(isOrdered, UmlViewsRepository.Port.Properties.isOrdered);
		EditingUtils.setEEFtype(isOrdered, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.isOrdered, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsUniqueCheckbox(FormToolkit widgetFactory, Composite parent) {
		isUnique = widgetFactory.createButton(parent, UmlMessages.PortPropertiesEditionPart_IsUniqueLabel, SWT.CHECK);
		isUnique.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.isUnique, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isUnique.getSelection())));
			}

		});
		GridData isUniqueData = new GridData(GridData.FILL_HORIZONTAL);
		isUniqueData.horizontalSpan = 2;
		isUnique.setLayoutData(isUniqueData);
		EditingUtils.setID(isUnique, UmlViewsRepository.Port.Properties.isUnique);
		EditingUtils.setEEFtype(isUnique, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.isUnique, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsReadOnlyCheckbox(FormToolkit widgetFactory, Composite parent) {
		isReadOnly = widgetFactory.createButton(parent, UmlMessages.PortPropertiesEditionPart_IsReadOnlyLabel, SWT.CHECK);
		isReadOnly.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.isReadOnly, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isReadOnly.getSelection())));
			}

		});
		GridData isReadOnlyData = new GridData(GridData.FILL_HORIZONTAL);
		isReadOnlyData.horizontalSpan = 2;
		isReadOnly.setLayoutData(isReadOnlyData);
		EditingUtils.setID(isReadOnly, UmlViewsRepository.Port.Properties.isReadOnly);
		EditingUtils.setEEFtype(isReadOnly, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.isReadOnly, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createOwningTemplateParameterFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PortPropertiesEditionPart_OwningTemplateParameterLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.owningTemplateParameter, UmlViewsRepository.FORM_KIND));
		owningTemplateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.owningTemplateParameter, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(owningTemplateParameter);
		owningTemplateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData owningTemplateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		owningTemplateParameter.setLayoutData(owningTemplateParameterData);
		owningTemplateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.owningTemplateParameter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getOwningTemplateParameter()));
			}

		});
		owningTemplateParameter.setID(UmlViewsRepository.Port.Properties.owningTemplateParameter);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.owningTemplateParameter, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createTemplateParameterFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PortPropertiesEditionPart_TemplateParameterLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.templateParameter, UmlViewsRepository.FORM_KIND));
		templateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.templateParameter, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(templateParameter);
		templateParameter.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData templateParameterData = new GridData(GridData.FILL_HORIZONTAL);
		templateParameter.setLayoutData(templateParameterData);
		templateParameter.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.templateParameter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getTemplateParameter()));
			}

		});
		templateParameter.setID(UmlViewsRepository.Port.Properties.templateParameter);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.templateParameter, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createClass_FlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PortPropertiesEditionPart_Class_Label, propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.class_, UmlViewsRepository.FORM_KIND));
		class_ = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.class_, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(class_);
		class_.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData class_Data = new GridData(GridData.FILL_HORIZONTAL);
		class_.setLayoutData(class_Data);
		class_.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.class_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getClass_()));
			}

		});
		class_.setID(UmlViewsRepository.Port.Properties.class_);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.class_, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createDatatypeFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PortPropertiesEditionPart_DatatypeLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.datatype, UmlViewsRepository.FORM_KIND));
		datatype = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.datatype, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(datatype);
		datatype.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData datatypeData = new GridData(GridData.FILL_HORIZONTAL);
		datatype.setLayoutData(datatypeData);
		datatype.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.datatype, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getDatatype()));
			}

		});
		datatype.setID(UmlViewsRepository.Port.Properties.datatype);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.datatype, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsDerivedCheckbox(FormToolkit widgetFactory, Composite parent) {
		isDerived = widgetFactory.createButton(parent, UmlMessages.PortPropertiesEditionPart_IsDerivedLabel, SWT.CHECK);
		isDerived.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.isDerived, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isDerived.getSelection())));
			}

		});
		GridData isDerivedData = new GridData(GridData.FILL_HORIZONTAL);
		isDerivedData.horizontalSpan = 2;
		isDerived.setLayoutData(isDerivedData);
		EditingUtils.setID(isDerived, UmlViewsRepository.Port.Properties.isDerived);
		EditingUtils.setEEFtype(isDerived, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.isDerived, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsDerivedUnionCheckbox(FormToolkit widgetFactory, Composite parent) {
		isDerivedUnion = widgetFactory.createButton(parent, UmlMessages.PortPropertiesEditionPart_IsDerivedUnionLabel, SWT.CHECK);
		isDerivedUnion.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.isDerivedUnion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isDerivedUnion.getSelection())));
			}

		});
		GridData isDerivedUnionData = new GridData(GridData.FILL_HORIZONTAL);
		isDerivedUnionData.horizontalSpan = 2;
		isDerivedUnion.setLayoutData(isDerivedUnionData);
		EditingUtils.setID(isDerivedUnion, UmlViewsRepository.Port.Properties.isDerivedUnion);
		EditingUtils.setEEFtype(isDerivedUnion, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.isDerivedUnion, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createAggregationEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PortPropertiesEditionPart_AggregationLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.aggregation, UmlViewsRepository.FORM_KIND));
		aggregation = new EMFComboViewer(parent);
		aggregation.setContentProvider(new ArrayContentProvider());
		aggregation.setLabelProvider(new AdapterFactoryLabelProvider(new EcoreAdapterFactory()));
		GridData aggregationData = new GridData(GridData.FILL_HORIZONTAL);
		aggregation.getCombo().setLayoutData(aggregationData);
		aggregation.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 * 	
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.aggregation, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getAggregation()));
			}

		});
		aggregation.setID(UmlViewsRepository.Port.Properties.aggregation);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.aggregation, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createRedefinedPropertyReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.redefinedProperty = new ReferencesTable(UmlMessages.PortPropertiesEditionPart_RedefinedPropertyLabel, new ReferencesTableListener	() {
			public void handleAdd() { addRedefinedProperty(); }
			public void handleEdit(EObject element) { editRedefinedProperty(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedProperty(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRedefinedProperty(element); }
			public void navigateTo(EObject element) { }
		});
		this.redefinedProperty.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.redefinedProperty, UmlViewsRepository.FORM_KIND));
		this.redefinedProperty.createControls(parent, widgetFactory);
		this.redefinedProperty.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.redefinedProperty, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData redefinedPropertyData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedPropertyData.horizontalSpan = 3;
		this.redefinedProperty.setLayoutData(redefinedPropertyData);
		this.redefinedProperty.disableMove();
		redefinedProperty.setID(UmlViewsRepository.Port.Properties.redefinedProperty);
		redefinedProperty.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addRedefinedProperty() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(redefinedProperty.getInput(), redefinedPropertyFilters, redefinedPropertyBusinessFilters,
		"redefinedProperty", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.redefinedProperty,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				redefinedProperty.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveRedefinedProperty(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.redefinedProperty, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		redefinedProperty.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromRedefinedProperty(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.redefinedProperty, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		redefinedProperty.refresh();
	}

	/**
	 * 
	 */
	protected void editRedefinedProperty(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				redefinedProperty.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createOwningAssociationFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PortPropertiesEditionPart_OwningAssociationLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.owningAssociation, UmlViewsRepository.FORM_KIND));
		owningAssociation = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.owningAssociation, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(owningAssociation);
		owningAssociation.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData owningAssociationData = new GridData(GridData.FILL_HORIZONTAL);
		owningAssociation.setLayoutData(owningAssociationData);
		owningAssociation.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.owningAssociation, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getOwningAssociation()));
			}

		});
		owningAssociation.setID(UmlViewsRepository.Port.Properties.owningAssociation);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.owningAssociation, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createSubsettedPropertyReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.subsettedProperty = new ReferencesTable(UmlMessages.PortPropertiesEditionPart_SubsettedPropertyLabel, new ReferencesTableListener	() {
			public void handleAdd() { addSubsettedProperty(); }
			public void handleEdit(EObject element) { editSubsettedProperty(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveSubsettedProperty(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromSubsettedProperty(element); }
			public void navigateTo(EObject element) { }
		});
		this.subsettedProperty.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.subsettedProperty, UmlViewsRepository.FORM_KIND));
		this.subsettedProperty.createControls(parent, widgetFactory);
		this.subsettedProperty.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.subsettedProperty, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData subsettedPropertyData = new GridData(GridData.FILL_HORIZONTAL);
		subsettedPropertyData.horizontalSpan = 3;
		this.subsettedProperty.setLayoutData(subsettedPropertyData);
		this.subsettedProperty.disableMove();
		subsettedProperty.setID(UmlViewsRepository.Port.Properties.subsettedProperty);
		subsettedProperty.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addSubsettedProperty() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(subsettedProperty.getInput(), subsettedPropertyFilters, subsettedPropertyBusinessFilters,
		"subsettedProperty", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.subsettedProperty,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				subsettedProperty.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveSubsettedProperty(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.subsettedProperty, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		subsettedProperty.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromSubsettedProperty(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.subsettedProperty, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		subsettedProperty.refresh();
	}

	/**
	 * 
	 */
	protected void editSubsettedProperty(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				subsettedProperty.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createAssociationFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PortPropertiesEditionPart_AssociationLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.association, UmlViewsRepository.FORM_KIND));
		association = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.association, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(association);
		association.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData associationData = new GridData(GridData.FILL_HORIZONTAL);
		association.setLayoutData(associationData);
		association.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.association, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getAssociation()));
			}

		});
		association.setID(UmlViewsRepository.Port.Properties.association);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.association, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createAssociationEndFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PortPropertiesEditionPart_AssociationEndLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.associationEnd, UmlViewsRepository.FORM_KIND));
		associationEnd = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.associationEnd, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(associationEnd);
		associationEnd.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData associationEndData = new GridData(GridData.FILL_HORIZONTAL);
		associationEnd.setLayoutData(associationEndData);
		associationEnd.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.associationEnd, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getAssociationEnd()));
			}

		});
		associationEnd.setID(UmlViewsRepository.Port.Properties.associationEnd);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.associationEnd, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsBehaviorCheckbox(FormToolkit widgetFactory, Composite parent) {
		isBehavior = widgetFactory.createButton(parent, UmlMessages.PortPropertiesEditionPart_IsBehaviorLabel, SWT.CHECK);
		isBehavior.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.isBehavior, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isBehavior.getSelection())));
			}

		});
		GridData isBehaviorData = new GridData(GridData.FILL_HORIZONTAL);
		isBehaviorData.horizontalSpan = 2;
		isBehavior.setLayoutData(isBehaviorData);
		EditingUtils.setID(isBehavior, UmlViewsRepository.Port.Properties.isBehavior);
		EditingUtils.setEEFtype(isBehavior, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.isBehavior, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsServiceCheckbox(FormToolkit widgetFactory, Composite parent) {
		isService = widgetFactory.createButton(parent, UmlMessages.PortPropertiesEditionPart_IsServiceLabel, SWT.CHECK);
		isService.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.isService, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isService.getSelection())));
			}

		});
		GridData isServiceData = new GridData(GridData.FILL_HORIZONTAL);
		isServiceData.horizontalSpan = 2;
		isService.setLayoutData(isServiceData);
		EditingUtils.setID(isService, UmlViewsRepository.Port.Properties.isService);
		EditingUtils.setEEFtype(isService, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.isService, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createRedefinedPortReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.redefinedPort = new ReferencesTable(UmlMessages.PortPropertiesEditionPart_RedefinedPortLabel, new ReferencesTableListener	() {
			public void handleAdd() { addRedefinedPort(); }
			public void handleEdit(EObject element) { editRedefinedPort(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRedefinedPort(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRedefinedPort(element); }
			public void navigateTo(EObject element) { }
		});
		this.redefinedPort.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.redefinedPort, UmlViewsRepository.FORM_KIND));
		this.redefinedPort.createControls(parent, widgetFactory);
		this.redefinedPort.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.redefinedPort, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData redefinedPortData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedPortData.horizontalSpan = 3;
		this.redefinedPort.setLayoutData(redefinedPortData);
		this.redefinedPort.disableMove();
		redefinedPort.setID(UmlViewsRepository.Port.Properties.redefinedPort);
		redefinedPort.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addRedefinedPort() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(redefinedPort.getInput(), redefinedPortFilters, redefinedPortBusinessFilters,
		"redefinedPort", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.redefinedPort,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				redefinedPort.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveRedefinedPort(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.redefinedPort, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		redefinedPort.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromRedefinedPort(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.redefinedPort, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		redefinedPort.refresh();
	}

	/**
	 * 
	 */
	protected void editRedefinedPort(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				redefinedPort.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createProtocolFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PortPropertiesEditionPart_ProtocolLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.protocol, UmlViewsRepository.FORM_KIND));
		protocol = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Port.Properties.protocol, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(protocol);
		protocol.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData protocolData = new GridData(GridData.FILL_HORIZONTAL);
		protocol.setLayoutData(protocolData);
		protocol.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PortPropertiesEditionPartForm.this, UmlViewsRepository.Port.Properties.protocol, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getProtocol()));
			}

		});
		protocol.setID(UmlViewsRepository.Port.Properties.protocol);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Port.Properties.protocol, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getName()
	 * 
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setName(String newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getVisibility()
	 * 
	 */
	public Enumerator getVisibility() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initVisibility(EEnum eenum, Enumerator current)
	 */
	public void initVisibility(EEnum eenum, Enumerator current) {
		visibility.setInput(eenum.getELiterals());
		visibility.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * 
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#updateClientDependency()
	 * 
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * 
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getIsLeaf()
	 * 
	 */
	public Boolean getIsLeaf() {
		return Boolean.valueOf(isLeaf.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setIsLeaf(Boolean newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getIsStatic()
	 * 
	 */
	public Boolean getIsStatic() {
		return Boolean.valueOf(isStatic.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setIsStatic(Boolean newValue)
	 * 
	 */
	public void setIsStatic(Boolean newValue) {
		if (newValue != null) {
			isStatic.setSelection(newValue.booleanValue());
		} else {
			isStatic.setSelection(false);
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getType()
	 * 
	 */
	public EObject getType() {
		if (type.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) type.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initType(EObjectFlatComboSettings)
	 */
	public void initType(EObjectFlatComboSettings settings) {
		type.setInput(settings);
		if (current != null) {
			type.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setType(EObject newValue)
	 * 
	 */
	public void setType(EObject newValue) {
		if (newValue != null) {
			type.setSelection(new StructuredSelection(newValue));
		} else {
			type.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setTypeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTypeButtonMode(ButtonsModeEnum newValue) {
		type.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addFilterType(ViewerFilter filter)
	 * 
	 */
	public void addFilterToType(ViewerFilter filter) {
		type.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addBusinessFilterType(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToType(ViewerFilter filter) {
		type.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getIsOrdered()
	 * 
	 */
	public Boolean getIsOrdered() {
		return Boolean.valueOf(isOrdered.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setIsOrdered(Boolean newValue)
	 * 
	 */
	public void setIsOrdered(Boolean newValue) {
		if (newValue != null) {
			isOrdered.setSelection(newValue.booleanValue());
		} else {
			isOrdered.setSelection(false);
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getIsUnique()
	 * 
	 */
	public Boolean getIsUnique() {
		return Boolean.valueOf(isUnique.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setIsUnique(Boolean newValue)
	 * 
	 */
	public void setIsUnique(Boolean newValue) {
		if (newValue != null) {
			isUnique.setSelection(newValue.booleanValue());
		} else {
			isUnique.setSelection(false);
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getIsReadOnly()
	 * 
	 */
	public Boolean getIsReadOnly() {
		return Boolean.valueOf(isReadOnly.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setIsReadOnly(Boolean newValue)
	 * 
	 */
	public void setIsReadOnly(Boolean newValue) {
		if (newValue != null) {
			isReadOnly.setSelection(newValue.booleanValue());
		} else {
			isReadOnly.setSelection(false);
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getOwningTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initOwningTemplateParameter(EObjectFlatComboSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setOwningTemplateParameter(EObject newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		owningTemplateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addFilterOwningTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addBusinessFilterOwningTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initTemplateParameter(EObjectFlatComboSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setTemplateParameter(EObject newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		templateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addFilterTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addBusinessFilterTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getClass_()
	 * 
	 */
	public EObject getClass_() {
		if (class_.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) class_.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initClass_(EObjectFlatComboSettings)
	 */
	public void initClass_(EObjectFlatComboSettings settings) {
		class_.setInput(settings);
		if (current != null) {
			class_.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setClass_(EObject newValue)
	 * 
	 */
	public void setClass_(EObject newValue) {
		if (newValue != null) {
			class_.setSelection(new StructuredSelection(newValue));
		} else {
			class_.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setClass_ButtonMode(ButtonsModeEnum newValue)
	 */
	public void setClass_ButtonMode(ButtonsModeEnum newValue) {
		class_.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addFilterClass_(ViewerFilter filter)
	 * 
	 */
	public void addFilterToClass_(ViewerFilter filter) {
		class_.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addBusinessFilterClass_(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToClass_(ViewerFilter filter) {
		class_.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getDatatype()
	 * 
	 */
	public EObject getDatatype() {
		if (datatype.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) datatype.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initDatatype(EObjectFlatComboSettings)
	 */
	public void initDatatype(EObjectFlatComboSettings settings) {
		datatype.setInput(settings);
		if (current != null) {
			datatype.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setDatatype(EObject newValue)
	 * 
	 */
	public void setDatatype(EObject newValue) {
		if (newValue != null) {
			datatype.setSelection(new StructuredSelection(newValue));
		} else {
			datatype.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setDatatypeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDatatypeButtonMode(ButtonsModeEnum newValue) {
		datatype.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addFilterDatatype(ViewerFilter filter)
	 * 
	 */
	public void addFilterToDatatype(ViewerFilter filter) {
		datatype.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addBusinessFilterDatatype(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToDatatype(ViewerFilter filter) {
		datatype.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getIsDerived()
	 * 
	 */
	public Boolean getIsDerived() {
		return Boolean.valueOf(isDerived.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setIsDerived(Boolean newValue)
	 * 
	 */
	public void setIsDerived(Boolean newValue) {
		if (newValue != null) {
			isDerived.setSelection(newValue.booleanValue());
		} else {
			isDerived.setSelection(false);
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getIsDerivedUnion()
	 * 
	 */
	public Boolean getIsDerivedUnion() {
		return Boolean.valueOf(isDerivedUnion.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setIsDerivedUnion(Boolean newValue)
	 * 
	 */
	public void setIsDerivedUnion(Boolean newValue) {
		if (newValue != null) {
			isDerivedUnion.setSelection(newValue.booleanValue());
		} else {
			isDerivedUnion.setSelection(false);
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getAggregation()
	 * 
	 */
	public Enumerator getAggregation() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) aggregation.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initAggregation(EEnum eenum, Enumerator current)
	 */
	public void initAggregation(EEnum eenum, Enumerator current) {
		aggregation.setInput(eenum.getELiterals());
		aggregation.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setAggregation(Enumerator newValue)
	 * 
	 */
	public void setAggregation(Enumerator newValue) {
		aggregation.modelUpdating(new StructuredSelection(newValue));
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initRedefinedProperty(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedProperty(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedProperty.setContentProvider(contentProvider);
		redefinedProperty.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#updateRedefinedProperty()
	 * 
	 */
	public void updateRedefinedProperty() {
	redefinedProperty.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addFilterRedefinedProperty(ViewerFilter filter)
	 * 
	 */
	public void addFilterToRedefinedProperty(ViewerFilter filter) {
		redefinedPropertyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addBusinessFilterRedefinedProperty(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToRedefinedProperty(ViewerFilter filter) {
		redefinedPropertyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#isContainedInRedefinedPropertyTable(EObject element)
	 * 
	 */
	public boolean isContainedInRedefinedPropertyTable(EObject element) {
		return ((ReferencesTableSettings)redefinedProperty.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getOwningAssociation()
	 * 
	 */
	public EObject getOwningAssociation() {
		if (owningAssociation.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) owningAssociation.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initOwningAssociation(EObjectFlatComboSettings)
	 */
	public void initOwningAssociation(EObjectFlatComboSettings settings) {
		owningAssociation.setInput(settings);
		if (current != null) {
			owningAssociation.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setOwningAssociation(EObject newValue)
	 * 
	 */
	public void setOwningAssociation(EObject newValue) {
		if (newValue != null) {
			owningAssociation.setSelection(new StructuredSelection(newValue));
		} else {
			owningAssociation.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setOwningAssociationButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningAssociationButtonMode(ButtonsModeEnum newValue) {
		owningAssociation.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addFilterOwningAssociation(ViewerFilter filter)
	 * 
	 */
	public void addFilterToOwningAssociation(ViewerFilter filter) {
		owningAssociation.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addBusinessFilterOwningAssociation(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToOwningAssociation(ViewerFilter filter) {
		owningAssociation.addBusinessRuleFilter(filter);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initSubsettedProperty(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initSubsettedProperty(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		subsettedProperty.setContentProvider(contentProvider);
		subsettedProperty.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#updateSubsettedProperty()
	 * 
	 */
	public void updateSubsettedProperty() {
	subsettedProperty.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addFilterSubsettedProperty(ViewerFilter filter)
	 * 
	 */
	public void addFilterToSubsettedProperty(ViewerFilter filter) {
		subsettedPropertyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addBusinessFilterSubsettedProperty(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToSubsettedProperty(ViewerFilter filter) {
		subsettedPropertyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#isContainedInSubsettedPropertyTable(EObject element)
	 * 
	 */
	public boolean isContainedInSubsettedPropertyTable(EObject element) {
		return ((ReferencesTableSettings)subsettedProperty.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getAssociation()
	 * 
	 */
	public EObject getAssociation() {
		if (association.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) association.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initAssociation(EObjectFlatComboSettings)
	 */
	public void initAssociation(EObjectFlatComboSettings settings) {
		association.setInput(settings);
		if (current != null) {
			association.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setAssociation(EObject newValue)
	 * 
	 */
	public void setAssociation(EObject newValue) {
		if (newValue != null) {
			association.setSelection(new StructuredSelection(newValue));
		} else {
			association.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setAssociationButtonMode(ButtonsModeEnum newValue)
	 */
	public void setAssociationButtonMode(ButtonsModeEnum newValue) {
		association.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addFilterAssociation(ViewerFilter filter)
	 * 
	 */
	public void addFilterToAssociation(ViewerFilter filter) {
		association.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addBusinessFilterAssociation(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToAssociation(ViewerFilter filter) {
		association.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getAssociationEnd()
	 * 
	 */
	public EObject getAssociationEnd() {
		if (associationEnd.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) associationEnd.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initAssociationEnd(EObjectFlatComboSettings)
	 */
	public void initAssociationEnd(EObjectFlatComboSettings settings) {
		associationEnd.setInput(settings);
		if (current != null) {
			associationEnd.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setAssociationEnd(EObject newValue)
	 * 
	 */
	public void setAssociationEnd(EObject newValue) {
		if (newValue != null) {
			associationEnd.setSelection(new StructuredSelection(newValue));
		} else {
			associationEnd.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setAssociationEndButtonMode(ButtonsModeEnum newValue)
	 */
	public void setAssociationEndButtonMode(ButtonsModeEnum newValue) {
		associationEnd.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addFilterAssociationEnd(ViewerFilter filter)
	 * 
	 */
	public void addFilterToAssociationEnd(ViewerFilter filter) {
		associationEnd.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addBusinessFilterAssociationEnd(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToAssociationEnd(ViewerFilter filter) {
		associationEnd.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getIsBehavior()
	 * 
	 */
	public Boolean getIsBehavior() {
		return Boolean.valueOf(isBehavior.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setIsBehavior(Boolean newValue)
	 * 
	 */
	public void setIsBehavior(Boolean newValue) {
		if (newValue != null) {
			isBehavior.setSelection(newValue.booleanValue());
		} else {
			isBehavior.setSelection(false);
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getIsService()
	 * 
	 */
	public Boolean getIsService() {
		return Boolean.valueOf(isService.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setIsService(Boolean newValue)
	 * 
	 */
	public void setIsService(Boolean newValue) {
		if (newValue != null) {
			isService.setSelection(newValue.booleanValue());
		} else {
			isService.setSelection(false);
		}
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initRedefinedPort(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRedefinedPort(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		redefinedPort.setContentProvider(contentProvider);
		redefinedPort.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#updateRedefinedPort()
	 * 
	 */
	public void updateRedefinedPort() {
	redefinedPort.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addFilterRedefinedPort(ViewerFilter filter)
	 * 
	 */
	public void addFilterToRedefinedPort(ViewerFilter filter) {
		redefinedPortFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addBusinessFilterRedefinedPort(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToRedefinedPort(ViewerFilter filter) {
		redefinedPortBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#isContainedInRedefinedPortTable(EObject element)
	 * 
	 */
	public boolean isContainedInRedefinedPortTable(EObject element) {
		return ((ReferencesTableSettings)redefinedPort.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#getProtocol()
	 * 
	 */
	public EObject getProtocol() {
		if (protocol.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) protocol.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#initProtocol(EObjectFlatComboSettings)
	 */
	public void initProtocol(EObjectFlatComboSettings settings) {
		protocol.setInput(settings);
		if (current != null) {
			protocol.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setProtocol(EObject newValue)
	 * 
	 */
	public void setProtocol(EObject newValue) {
		if (newValue != null) {
			protocol.setSelection(new StructuredSelection(newValue));
		} else {
			protocol.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#setProtocolButtonMode(ButtonsModeEnum newValue)
	 */
	public void setProtocolButtonMode(ButtonsModeEnum newValue) {
		protocol.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addFilterProtocol(ViewerFilter filter)
	 * 
	 */
	public void addFilterToProtocol(ViewerFilter filter) {
		protocol.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PortPropertiesEditionPart#addBusinessFilterProtocol(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToProtocol(ViewerFilter filter) {
		protocol.addBusinessRuleFilter(filter);
	}




	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.Port_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
