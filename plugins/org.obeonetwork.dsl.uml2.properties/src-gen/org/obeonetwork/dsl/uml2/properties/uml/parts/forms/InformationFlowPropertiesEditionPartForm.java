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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class InformationFlowPropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, InformationFlowPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
		protected ReferencesTable clientDependency;
		protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer owningTemplateParameter;
	protected EObjectFlatComboViewer templateParameter;
		protected ReferencesTable realization;
		protected List<ViewerFilter> realizationBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> realizationFilters = new ArrayList<ViewerFilter>();
		protected ReferencesTable conveyed;
		protected List<ViewerFilter> conveyedBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> conveyedFilters = new ArrayList<ViewerFilter>();
		protected ReferencesTable informationSource;
		protected List<ViewerFilter> informationSourceBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> informationSourceFilters = new ArrayList<ViewerFilter>();
		protected ReferencesTable informationTarget;
		protected List<ViewerFilter> informationTargetBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> informationTargetFilters = new ArrayList<ViewerFilter>();
		protected ReferencesTable realizingActivityEdge;
		protected List<ViewerFilter> realizingActivityEdgeBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> realizingActivityEdgeFilters = new ArrayList<ViewerFilter>();
		protected ReferencesTable realizingConnector;
		protected List<ViewerFilter> realizingConnectorBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> realizingConnectorFilters = new ArrayList<ViewerFilter>();
		protected ReferencesTable realizingMessage;
		protected List<ViewerFilter> realizingMessageBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> realizingMessageFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public InformationFlowPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence informationFlowStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = informationFlowStep.addStep(UmlViewsRepository.InformationFlow.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter);
		propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.templateParameter);
		propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.realization);
		propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.conveyed);
		propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.informationSource);
		propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.informationTarget);
		propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge);
		propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.realizingConnector);
		propertiesStep.addStep(UmlViewsRepository.InformationFlow.Properties.realizingMessage);
		
		
		composer = new PartComposer(informationFlowStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.InformationFlow.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.InformationFlow.Properties.name) {
					return 		createNameText(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.InformationFlow.Properties.visibility) {
					return createVisibilityEMFComboViewer(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.InformationFlow.Properties.clientDependency) {
					return createClientDependencyReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter) {
					return createOwningTemplateParameterFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.InformationFlow.Properties.templateParameter) {
					return createTemplateParameterFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.InformationFlow.Properties.realization) {
					return createRealizationReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.InformationFlow.Properties.conveyed) {
					return createConveyedReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.InformationFlow.Properties.informationSource) {
					return createInformationSourceReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.InformationFlow.Properties.informationTarget) {
					return createInformationTargetReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge) {
					return createRealizingActivityEdgeReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.InformationFlow.Properties.realizingConnector) {
					return createRealizingConnectorReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.InformationFlow.Properties.realizingMessage) {
					return createRealizingMessageReferencesTable(widgetFactory, parent);
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
		propertiesSection.setText(UmlMessages.InformationFlowPropertiesEditionPart_PropertiesGroupLabel);
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
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.InformationFlowPropertiesEditionPart_NameLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.InformationFlow.Properties.name, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}
		});
		EditingUtils.setID(name, UmlViewsRepository.InformationFlow.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.InformationFlowPropertiesEditionPart_VisibilityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.InformationFlow.Properties.visibility, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.InformationFlow.Properties.visibility);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.clientDependency = new ReferencesTable(UmlMessages.InformationFlowPropertiesEditionPart_ClientDependencyLabel, new ReferencesTableListener	() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
		this.clientDependency.createControls(parent, widgetFactory);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.InformationFlow.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	protected Composite createOwningTemplateParameterFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.InformationFlowPropertiesEditionPart_OwningTemplateParameterLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter, UmlViewsRepository.FORM_KIND));
		owningTemplateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getOwningTemplateParameter()));
			}

		});
		owningTemplateParameter.setID(UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.owningTemplateParameter, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createTemplateParameterFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.InformationFlowPropertiesEditionPart_TemplateParameterLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.InformationFlow.Properties.templateParameter, UmlViewsRepository.FORM_KIND));
		templateParameter = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.InformationFlow.Properties.templateParameter, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.templateParameter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getTemplateParameter()));
			}

		});
		templateParameter.setID(UmlViewsRepository.InformationFlow.Properties.templateParameter);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.templateParameter, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createRealizationReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.realization = new ReferencesTable(UmlMessages.InformationFlowPropertiesEditionPart_RealizationLabel, new ReferencesTableListener	() {
			public void handleAdd() { addRealization(); }
			public void handleEdit(EObject element) { editRealization(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRealization(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRealization(element); }
			public void navigateTo(EObject element) { }
		});
		this.realization.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.realization, UmlViewsRepository.FORM_KIND));
		this.realization.createControls(parent, widgetFactory);
		this.realization.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realization, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData realizationData = new GridData(GridData.FILL_HORIZONTAL);
		realizationData.horizontalSpan = 3;
		this.realization.setLayoutData(realizationData);
		this.realization.disableMove();
		realization.setID(UmlViewsRepository.InformationFlow.Properties.realization);
		realization.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addRealization() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(realization.getInput(), realizationFilters, realizationBusinessFilters,
		"realization", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realization,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				realization.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveRealization(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realization, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		realization.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromRealization(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realization, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		realization.refresh();
	}

	/**
	 * 
	 */
	protected void editRealization(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				realization.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createConveyedReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.conveyed = new ReferencesTable(UmlMessages.InformationFlowPropertiesEditionPart_ConveyedLabel, new ReferencesTableListener	() {
			public void handleAdd() { addConveyed(); }
			public void handleEdit(EObject element) { editConveyed(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveConveyed(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromConveyed(element); }
			public void navigateTo(EObject element) { }
		});
		this.conveyed.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.conveyed, UmlViewsRepository.FORM_KIND));
		this.conveyed.createControls(parent, widgetFactory);
		this.conveyed.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.conveyed, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData conveyedData = new GridData(GridData.FILL_HORIZONTAL);
		conveyedData.horizontalSpan = 3;
		this.conveyed.setLayoutData(conveyedData);
		this.conveyed.disableMove();
		conveyed.setID(UmlViewsRepository.InformationFlow.Properties.conveyed);
		conveyed.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addConveyed() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(conveyed.getInput(), conveyedFilters, conveyedBusinessFilters,
		"conveyed", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.conveyed,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				conveyed.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveConveyed(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.conveyed, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		conveyed.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromConveyed(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.conveyed, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		conveyed.refresh();
	}

	/**
	 * 
	 */
	protected void editConveyed(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				conveyed.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createInformationSourceReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.informationSource = new ReferencesTable(UmlMessages.InformationFlowPropertiesEditionPart_InformationSourceLabel, new ReferencesTableListener	() {
			public void handleAdd() { addInformationSource(); }
			public void handleEdit(EObject element) { editInformationSource(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveInformationSource(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromInformationSource(element); }
			public void navigateTo(EObject element) { }
		});
		this.informationSource.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.informationSource, UmlViewsRepository.FORM_KIND));
		this.informationSource.createControls(parent, widgetFactory);
		this.informationSource.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.informationSource, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData informationSourceData = new GridData(GridData.FILL_HORIZONTAL);
		informationSourceData.horizontalSpan = 3;
		this.informationSource.setLayoutData(informationSourceData);
		this.informationSource.disableMove();
		informationSource.setID(UmlViewsRepository.InformationFlow.Properties.informationSource);
		informationSource.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addInformationSource() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(informationSource.getInput(), informationSourceFilters, informationSourceBusinessFilters,
		"informationSource", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.informationSource,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				informationSource.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveInformationSource(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.informationSource, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		informationSource.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromInformationSource(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.informationSource, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		informationSource.refresh();
	}

	/**
	 * 
	 */
	protected void editInformationSource(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				informationSource.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createInformationTargetReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.informationTarget = new ReferencesTable(UmlMessages.InformationFlowPropertiesEditionPart_InformationTargetLabel, new ReferencesTableListener	() {
			public void handleAdd() { addInformationTarget(); }
			public void handleEdit(EObject element) { editInformationTarget(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveInformationTarget(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromInformationTarget(element); }
			public void navigateTo(EObject element) { }
		});
		this.informationTarget.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.informationTarget, UmlViewsRepository.FORM_KIND));
		this.informationTarget.createControls(parent, widgetFactory);
		this.informationTarget.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.informationTarget, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData informationTargetData = new GridData(GridData.FILL_HORIZONTAL);
		informationTargetData.horizontalSpan = 3;
		this.informationTarget.setLayoutData(informationTargetData);
		this.informationTarget.disableMove();
		informationTarget.setID(UmlViewsRepository.InformationFlow.Properties.informationTarget);
		informationTarget.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addInformationTarget() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(informationTarget.getInput(), informationTargetFilters, informationTargetBusinessFilters,
		"informationTarget", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.informationTarget,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				informationTarget.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveInformationTarget(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.informationTarget, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		informationTarget.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromInformationTarget(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.informationTarget, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		informationTarget.refresh();
	}

	/**
	 * 
	 */
	protected void editInformationTarget(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				informationTarget.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createRealizingActivityEdgeReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.realizingActivityEdge = new ReferencesTable(UmlMessages.InformationFlowPropertiesEditionPart_RealizingActivityEdgeLabel, new ReferencesTableListener	() {
			public void handleAdd() { addRealizingActivityEdge(); }
			public void handleEdit(EObject element) { editRealizingActivityEdge(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRealizingActivityEdge(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRealizingActivityEdge(element); }
			public void navigateTo(EObject element) { }
		});
		this.realizingActivityEdge.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge, UmlViewsRepository.FORM_KIND));
		this.realizingActivityEdge.createControls(parent, widgetFactory);
		this.realizingActivityEdge.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData realizingActivityEdgeData = new GridData(GridData.FILL_HORIZONTAL);
		realizingActivityEdgeData.horizontalSpan = 3;
		this.realizingActivityEdge.setLayoutData(realizingActivityEdgeData);
		this.realizingActivityEdge.disableMove();
		realizingActivityEdge.setID(UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge);
		realizingActivityEdge.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addRealizingActivityEdge() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(realizingActivityEdge.getInput(), realizingActivityEdgeFilters, realizingActivityEdgeBusinessFilters,
		"realizingActivityEdge", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				realizingActivityEdge.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveRealizingActivityEdge(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		realizingActivityEdge.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromRealizingActivityEdge(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realizingActivityEdge, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		realizingActivityEdge.refresh();
	}

	/**
	 * 
	 */
	protected void editRealizingActivityEdge(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				realizingActivityEdge.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createRealizingConnectorReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.realizingConnector = new ReferencesTable(UmlMessages.InformationFlowPropertiesEditionPart_RealizingConnectorLabel, new ReferencesTableListener	() {
			public void handleAdd() { addRealizingConnector(); }
			public void handleEdit(EObject element) { editRealizingConnector(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRealizingConnector(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRealizingConnector(element); }
			public void navigateTo(EObject element) { }
		});
		this.realizingConnector.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.realizingConnector, UmlViewsRepository.FORM_KIND));
		this.realizingConnector.createControls(parent, widgetFactory);
		this.realizingConnector.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realizingConnector, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData realizingConnectorData = new GridData(GridData.FILL_HORIZONTAL);
		realizingConnectorData.horizontalSpan = 3;
		this.realizingConnector.setLayoutData(realizingConnectorData);
		this.realizingConnector.disableMove();
		realizingConnector.setID(UmlViewsRepository.InformationFlow.Properties.realizingConnector);
		realizingConnector.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addRealizingConnector() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(realizingConnector.getInput(), realizingConnectorFilters, realizingConnectorBusinessFilters,
		"realizingConnector", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realizingConnector,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				realizingConnector.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveRealizingConnector(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realizingConnector, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		realizingConnector.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromRealizingConnector(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realizingConnector, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		realizingConnector.refresh();
	}

	/**
	 * 
	 */
	protected void editRealizingConnector(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				realizingConnector.refresh();
			}
		}
	}

	/**
	 * 
	 */
	protected Composite createRealizingMessageReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.realizingMessage = new ReferencesTable(UmlMessages.InformationFlowPropertiesEditionPart_RealizingMessageLabel, new ReferencesTableListener	() {
			public void handleAdd() { addRealizingMessage(); }
			public void handleEdit(EObject element) { editRealizingMessage(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveRealizingMessage(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromRealizingMessage(element); }
			public void navigateTo(EObject element) { }
		});
		this.realizingMessage.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.InformationFlow.Properties.realizingMessage, UmlViewsRepository.FORM_KIND));
		this.realizingMessage.createControls(parent, widgetFactory);
		this.realizingMessage.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realizingMessage, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData realizingMessageData = new GridData(GridData.FILL_HORIZONTAL);
		realizingMessageData.horizontalSpan = 3;
		this.realizingMessage.setLayoutData(realizingMessageData);
		this.realizingMessage.disableMove();
		realizingMessage.setID(UmlViewsRepository.InformationFlow.Properties.realizingMessage);
		realizingMessage.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addRealizingMessage() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(realizingMessage.getInput(), realizingMessageFilters, realizingMessageBusinessFilters,
		"realizingMessage", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realizingMessage,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				realizingMessage.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveRealizingMessage(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realizingMessage, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		realizingMessage.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromRealizingMessage(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InformationFlowPropertiesEditionPartForm.this, UmlViewsRepository.InformationFlow.Properties.realizingMessage, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		realizingMessage.refresh();
	}

	/**
	 * 
	 */
	protected void editRealizingMessage(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				realizingMessage.refresh();
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#getName()
	 * 
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#setName(String newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#getVisibility()
	 * 
	 */
	public Enumerator getVisibility() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initVisibility(EEnum eenum, Enumerator current)
	 */
	public void initVisibility(EEnum eenum, Enumerator current) {
		visibility.setInput(eenum.getELiterals());
		visibility.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * 
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateClientDependency()
	 * 
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * 
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#getOwningTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initOwningTemplateParameter(EObjectFlatComboSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#setOwningTemplateParameter(EObject newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOwningTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		owningTemplateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterOwningTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterOwningTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToOwningTemplateParameter(ViewerFilter filter) {
		owningTemplateParameter.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#getTemplateParameter()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initTemplateParameter(EObjectFlatComboSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#setTemplateParameter(EObject newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#setTemplateParameterButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateParameterButtonMode(ButtonsModeEnum newValue) {
		templateParameter.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterTemplateParameter(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToTemplateParameter(ViewerFilter filter) {
		templateParameter.addBusinessRuleFilter(filter);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initRealization(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRealization(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		realization.setContentProvider(contentProvider);
		realization.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateRealization()
	 * 
	 */
	public void updateRealization() {
	realization.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterRealization(ViewerFilter filter)
	 * 
	 */
	public void addFilterToRealization(ViewerFilter filter) {
		realizationFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterRealization(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToRealization(ViewerFilter filter) {
		realizationBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInRealizationTable(EObject element)
	 * 
	 */
	public boolean isContainedInRealizationTable(EObject element) {
		return ((ReferencesTableSettings)realization.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initConveyed(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initConveyed(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		conveyed.setContentProvider(contentProvider);
		conveyed.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateConveyed()
	 * 
	 */
	public void updateConveyed() {
	conveyed.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterConveyed(ViewerFilter filter)
	 * 
	 */
	public void addFilterToConveyed(ViewerFilter filter) {
		conveyedFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterConveyed(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToConveyed(ViewerFilter filter) {
		conveyedBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInConveyedTable(EObject element)
	 * 
	 */
	public boolean isContainedInConveyedTable(EObject element) {
		return ((ReferencesTableSettings)conveyed.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initInformationSource(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInformationSource(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		informationSource.setContentProvider(contentProvider);
		informationSource.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateInformationSource()
	 * 
	 */
	public void updateInformationSource() {
	informationSource.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterInformationSource(ViewerFilter filter)
	 * 
	 */
	public void addFilterToInformationSource(ViewerFilter filter) {
		informationSourceFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterInformationSource(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToInformationSource(ViewerFilter filter) {
		informationSourceBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInInformationSourceTable(EObject element)
	 * 
	 */
	public boolean isContainedInInformationSourceTable(EObject element) {
		return ((ReferencesTableSettings)informationSource.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initInformationTarget(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initInformationTarget(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		informationTarget.setContentProvider(contentProvider);
		informationTarget.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateInformationTarget()
	 * 
	 */
	public void updateInformationTarget() {
	informationTarget.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterInformationTarget(ViewerFilter filter)
	 * 
	 */
	public void addFilterToInformationTarget(ViewerFilter filter) {
		informationTargetFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterInformationTarget(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToInformationTarget(ViewerFilter filter) {
		informationTargetBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInInformationTargetTable(EObject element)
	 * 
	 */
	public boolean isContainedInInformationTargetTable(EObject element) {
		return ((ReferencesTableSettings)informationTarget.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initRealizingActivityEdge(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRealizingActivityEdge(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		realizingActivityEdge.setContentProvider(contentProvider);
		realizingActivityEdge.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateRealizingActivityEdge()
	 * 
	 */
	public void updateRealizingActivityEdge() {
	realizingActivityEdge.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterRealizingActivityEdge(ViewerFilter filter)
	 * 
	 */
	public void addFilterToRealizingActivityEdge(ViewerFilter filter) {
		realizingActivityEdgeFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterRealizingActivityEdge(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToRealizingActivityEdge(ViewerFilter filter) {
		realizingActivityEdgeBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInRealizingActivityEdgeTable(EObject element)
	 * 
	 */
	public boolean isContainedInRealizingActivityEdgeTable(EObject element) {
		return ((ReferencesTableSettings)realizingActivityEdge.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initRealizingConnector(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRealizingConnector(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		realizingConnector.setContentProvider(contentProvider);
		realizingConnector.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateRealizingConnector()
	 * 
	 */
	public void updateRealizingConnector() {
	realizingConnector.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterRealizingConnector(ViewerFilter filter)
	 * 
	 */
	public void addFilterToRealizingConnector(ViewerFilter filter) {
		realizingConnectorFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterRealizingConnector(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToRealizingConnector(ViewerFilter filter) {
		realizingConnectorBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInRealizingConnectorTable(EObject element)
	 * 
	 */
	public boolean isContainedInRealizingConnectorTable(EObject element) {
		return ((ReferencesTableSettings)realizingConnector.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#initRealizingMessage(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initRealizingMessage(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		realizingMessage.setContentProvider(contentProvider);
		realizingMessage.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#updateRealizingMessage()
	 * 
	 */
	public void updateRealizingMessage() {
	realizingMessage.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addFilterRealizingMessage(ViewerFilter filter)
	 * 
	 */
	public void addFilterToRealizingMessage(ViewerFilter filter) {
		realizingMessageFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#addBusinessFilterRealizingMessage(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToRealizingMessage(ViewerFilter filter) {
		realizingMessageBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.InformationFlowPropertiesEditionPart#isContainedInRealizingMessageTable(EObject element)
	 * 
	 */
	public boolean isContainedInRealizingMessageTable(EObject element) {
		return ((ReferencesTableSettings)realizingMessage.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.InformationFlow_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
