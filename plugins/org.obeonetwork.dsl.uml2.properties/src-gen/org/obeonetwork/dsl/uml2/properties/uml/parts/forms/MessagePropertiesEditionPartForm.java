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
import org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class MessagePropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, MessagePropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
		protected ReferencesTable clientDependency;
		protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
		protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected EMFComboViewer messageSort;
	protected EObjectFlatComboViewer receiveEvent;
	protected EObjectFlatComboViewer sendEvent;
	protected EObjectFlatComboViewer connector;
	protected EObjectFlatComboViewer interaction;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public MessagePropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence messageStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = messageStep.addStep(UmlViewsRepository.Message.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.Message.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.Message.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.Message.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.Message.Properties.messageSort);
		propertiesStep.addStep(UmlViewsRepository.Message.Properties.receiveEvent);
		propertiesStep.addStep(UmlViewsRepository.Message.Properties.sendEvent);
		propertiesStep.addStep(UmlViewsRepository.Message.Properties.connector);
		propertiesStep.addStep(UmlViewsRepository.Message.Properties.interaction);
		
		
		composer = new PartComposer(messageStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.Message.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Message.Properties.name) {
					return 		createNameText(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Message.Properties.visibility) {
					return createVisibilityEMFComboViewer(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Message.Properties.clientDependency) {
					return createClientDependencyReferencesTable(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Message.Properties.messageSort) {
					return createMessageSortEMFComboViewer(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.Message.Properties.receiveEvent) {
					return createReceiveEventFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.Message.Properties.sendEvent) {
					return createSendEventFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.Message.Properties.connector) {
					return createConnectorFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.Message.Properties.interaction) {
					return createInteractionFlatComboViewer(parent, widgetFactory);
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
		propertiesSection.setText(UmlMessages.MessagePropertiesEditionPart_PropertiesGroupLabel);
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
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.MessagePropertiesEditionPart_NameLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.name, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartForm.this, UmlViewsRepository.Message.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartForm.this, UmlViewsRepository.Message.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}
		});
		EditingUtils.setID(name, UmlViewsRepository.Message.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.name, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.MessagePropertiesEditionPart_VisibilityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.visibility, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartForm.this, UmlViewsRepository.Message.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.Message.Properties.visibility);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.clientDependency = new ReferencesTable(UmlMessages.MessagePropertiesEditionPart_ClientDependencyLabel, new ReferencesTableListener	() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.clientDependency, UmlViewsRepository.FORM_KIND));
		this.clientDependency.createControls(parent, widgetFactory);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartForm.this, UmlViewsRepository.Message.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.Message.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartForm.this, UmlViewsRepository.Message.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartForm.this, UmlViewsRepository.Message.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartForm.this, UmlViewsRepository.Message.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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

	
	protected Composite createMessageSortEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.MessagePropertiesEditionPart_MessageSortLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.messageSort, UmlViewsRepository.FORM_KIND));
		messageSort = new EMFComboViewer(parent);
		messageSort.setContentProvider(new ArrayContentProvider());
		messageSort.setLabelProvider(new AdapterFactoryLabelProvider(new EcoreAdapterFactory()));
		GridData messageSortData = new GridData(GridData.FILL_HORIZONTAL);
		messageSort.getCombo().setLayoutData(messageSortData);
		messageSort.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 * 	
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartForm.this, UmlViewsRepository.Message.Properties.messageSort, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getMessageSort()));
			}

		});
		messageSort.setID(UmlViewsRepository.Message.Properties.messageSort);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.messageSort, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createReceiveEventFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.MessagePropertiesEditionPart_ReceiveEventLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.receiveEvent, UmlViewsRepository.FORM_KIND));
		receiveEvent = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.receiveEvent, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(receiveEvent);
		receiveEvent.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData receiveEventData = new GridData(GridData.FILL_HORIZONTAL);
		receiveEvent.setLayoutData(receiveEventData);
		receiveEvent.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartForm.this, UmlViewsRepository.Message.Properties.receiveEvent, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getReceiveEvent()));
			}

		});
		receiveEvent.setID(UmlViewsRepository.Message.Properties.receiveEvent);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.receiveEvent, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createSendEventFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.MessagePropertiesEditionPart_SendEventLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.sendEvent, UmlViewsRepository.FORM_KIND));
		sendEvent = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.sendEvent, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(sendEvent);
		sendEvent.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData sendEventData = new GridData(GridData.FILL_HORIZONTAL);
		sendEvent.setLayoutData(sendEventData);
		sendEvent.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartForm.this, UmlViewsRepository.Message.Properties.sendEvent, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getSendEvent()));
			}

		});
		sendEvent.setID(UmlViewsRepository.Message.Properties.sendEvent);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.sendEvent, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createConnectorFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.MessagePropertiesEditionPart_ConnectorLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.connector, UmlViewsRepository.FORM_KIND));
		connector = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.connector, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(connector);
		connector.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData connectorData = new GridData(GridData.FILL_HORIZONTAL);
		connector.setLayoutData(connectorData);
		connector.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartForm.this, UmlViewsRepository.Message.Properties.connector, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getConnector()));
			}

		});
		connector.setID(UmlViewsRepository.Message.Properties.connector);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.connector, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createInteractionFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.MessagePropertiesEditionPart_InteractionLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.interaction, UmlViewsRepository.FORM_KIND));
		interaction = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.Message.Properties.interaction, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(interaction);
		interaction.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData interactionData = new GridData(GridData.FILL_HORIZONTAL);
		interaction.setLayoutData(interactionData);
		interaction.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(MessagePropertiesEditionPartForm.this, UmlViewsRepository.Message.Properties.interaction, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getInteraction()));
			}

		});
		interaction.setID(UmlViewsRepository.Message.Properties.interaction);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.Message.Properties.interaction, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#getName()
	 * 
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setName(String newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#getVisibility()
	 * 
	 */
	public Enumerator getVisibility() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#initVisibility(EEnum eenum, Enumerator current)
	 */
	public void initVisibility(EEnum eenum, Enumerator current) {
		visibility.setInput(eenum.getELiterals());
		visibility.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setVisibility(Enumerator newValue)
	 * 
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#updateClientDependency()
	 * 
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * 
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#getMessageSort()
	 * 
	 */
	public Enumerator getMessageSort() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) messageSort.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#initMessageSort(EEnum eenum, Enumerator current)
	 */
	public void initMessageSort(EEnum eenum, Enumerator current) {
		messageSort.setInput(eenum.getELiterals());
		messageSort.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setMessageSort(Enumerator newValue)
	 * 
	 */
	public void setMessageSort(Enumerator newValue) {
		messageSort.modelUpdating(new StructuredSelection(newValue));
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#getReceiveEvent()
	 * 
	 */
	public EObject getReceiveEvent() {
		if (receiveEvent.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) receiveEvent.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#initReceiveEvent(EObjectFlatComboSettings)
	 */
	public void initReceiveEvent(EObjectFlatComboSettings settings) {
		receiveEvent.setInput(settings);
		if (current != null) {
			receiveEvent.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setReceiveEvent(EObject newValue)
	 * 
	 */
	public void setReceiveEvent(EObject newValue) {
		if (newValue != null) {
			receiveEvent.setSelection(new StructuredSelection(newValue));
		} else {
			receiveEvent.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setReceiveEventButtonMode(ButtonsModeEnum newValue)
	 */
	public void setReceiveEventButtonMode(ButtonsModeEnum newValue) {
		receiveEvent.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addFilterReceiveEvent(ViewerFilter filter)
	 * 
	 */
	public void addFilterToReceiveEvent(ViewerFilter filter) {
		receiveEvent.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addBusinessFilterReceiveEvent(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToReceiveEvent(ViewerFilter filter) {
		receiveEvent.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#getSendEvent()
	 * 
	 */
	public EObject getSendEvent() {
		if (sendEvent.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) sendEvent.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#initSendEvent(EObjectFlatComboSettings)
	 */
	public void initSendEvent(EObjectFlatComboSettings settings) {
		sendEvent.setInput(settings);
		if (current != null) {
			sendEvent.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setSendEvent(EObject newValue)
	 * 
	 */
	public void setSendEvent(EObject newValue) {
		if (newValue != null) {
			sendEvent.setSelection(new StructuredSelection(newValue));
		} else {
			sendEvent.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setSendEventButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSendEventButtonMode(ButtonsModeEnum newValue) {
		sendEvent.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addFilterSendEvent(ViewerFilter filter)
	 * 
	 */
	public void addFilterToSendEvent(ViewerFilter filter) {
		sendEvent.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addBusinessFilterSendEvent(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToSendEvent(ViewerFilter filter) {
		sendEvent.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#getConnector()
	 * 
	 */
	public EObject getConnector() {
		if (connector.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) connector.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#initConnector(EObjectFlatComboSettings)
	 */
	public void initConnector(EObjectFlatComboSettings settings) {
		connector.setInput(settings);
		if (current != null) {
			connector.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setConnector(EObject newValue)
	 * 
	 */
	public void setConnector(EObject newValue) {
		if (newValue != null) {
			connector.setSelection(new StructuredSelection(newValue));
		} else {
			connector.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setConnectorButtonMode(ButtonsModeEnum newValue)
	 */
	public void setConnectorButtonMode(ButtonsModeEnum newValue) {
		connector.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addFilterConnector(ViewerFilter filter)
	 * 
	 */
	public void addFilterToConnector(ViewerFilter filter) {
		connector.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addBusinessFilterConnector(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToConnector(ViewerFilter filter) {
		connector.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#getInteraction()
	 * 
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#initInteraction(EObjectFlatComboSettings)
	 */
	public void initInteraction(EObjectFlatComboSettings settings) {
		interaction.setInput(settings);
		if (current != null) {
			interaction.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setInteraction(EObject newValue)
	 * 
	 */
	public void setInteraction(EObject newValue) {
		if (newValue != null) {
			interaction.setSelection(new StructuredSelection(newValue));
		} else {
			interaction.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#setInteractionButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInteractionButtonMode(ButtonsModeEnum newValue) {
		interaction.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addFilterInteraction(ViewerFilter filter)
	 * 
	 */
	public void addFilterToInteraction(ViewerFilter filter) {
		interaction.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.MessagePropertiesEditionPart#addBusinessFilterInteraction(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToInteraction(ViewerFilter filter) {
		interaction.addBusinessRuleFilter(filter);
	}




	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.Message_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
