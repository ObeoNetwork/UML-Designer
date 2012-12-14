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

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class RedefinableTemplateSignaturePropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, RedefinableTemplateSignaturePropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected ReferencesTable clientDependency;
	protected List<ViewerFilter> clientDependencyBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientDependencyFilters = new ArrayList<ViewerFilter>();
	protected Button isLeaf;
	protected ReferencesTable parameter;
	protected List<ViewerFilter> parameterBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> parameterFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer template;
	protected ReferencesTable extendedSignature;
	protected List<ViewerFilter> extendedSignatureBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> extendedSignatureFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer classifier;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public RedefinableTemplateSignaturePropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence redefinableTemplateSignatureStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = redefinableTemplateSignatureStep.addStep(UmlViewsRepository.RedefinableTemplateSignature.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.RedefinableTemplateSignature.Properties.name);
		propertiesStep.addStep(UmlViewsRepository.RedefinableTemplateSignature.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency);
		propertiesStep.addStep(UmlViewsRepository.RedefinableTemplateSignature.Properties.isLeaf);
		propertiesStep.addStep(UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter);
		propertiesStep.addStep(UmlViewsRepository.RedefinableTemplateSignature.Properties.template);
		propertiesStep.addStep(UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature);
		propertiesStep.addStep(UmlViewsRepository.RedefinableTemplateSignature.Properties.classifier);
		
		
		composer = new PartComposer(redefinableTemplateSignatureStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.RedefinableTemplateSignature.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.RedefinableTemplateSignature.Properties.name) {
					return createNameText(parent);
				}
				if (key == UmlViewsRepository.RedefinableTemplateSignature.Properties.visibility) {
					return createVisibilityEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency) {
					return createClientDependencyAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.RedefinableTemplateSignature.Properties.isLeaf) {
					return createIsLeafCheckbox(parent);
				}
				if (key == UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter) {
					return createParameterAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.RedefinableTemplateSignature.Properties.template) {
					return createTemplateFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature) {
					return createExtendedSignatureAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.RedefinableTemplateSignature.Properties.classifier) {
					return createClassifierFlatComboViewer(parent);
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
		propertiesGroup.setText(UmlMessages.RedefinableTemplateSignaturePropertiesEditionPart_PropertiesGroupLabel);
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
		createDescription(parent, UmlViewsRepository.RedefinableTemplateSignature.Properties.name, UmlMessages.RedefinableTemplateSignaturePropertiesEditionPart_NameLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, UmlViewsRepository.RedefinableTemplateSignature.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.RedefinableTemplateSignature.Properties.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.RedefinableTemplateSignature.Properties.visibility, UmlMessages.RedefinableTemplateSignaturePropertiesEditionPart_VisibilityLabel);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.RedefinableTemplateSignature.Properties.visibility);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.RedefinableTemplateSignature.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createClientDependencyAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency, UmlMessages.RedefinableTemplateSignaturePropertiesEditionPart_ClientDependencyLabel);		 
		this.clientDependency = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addClientDependency(); }
			public void handleEdit(EObject element) { editClientDependency(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveClientDependency(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromClientDependency(element); }
			public void navigateTo(EObject element) { }
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency, UmlViewsRepository.SWT_KIND));
		this.clientDependency.createControls(parent);
		this.clientDependency.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
		clientDependency.setID(UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		clientDependency.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromClientDependency(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
	
	protected Composite createIsLeafCheckbox(Composite parent) {
		isLeaf = new Button(parent, SWT.CHECK);
		isLeaf.setText(getDescription(UmlViewsRepository.RedefinableTemplateSignature.Properties.isLeaf, UmlMessages.RedefinableTemplateSignaturePropertiesEditionPart_IsLeafLabel));
		isLeaf.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.isLeaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
			}

		});
		GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
		isLeafData.horizontalSpan = 2;
		isLeaf.setLayoutData(isLeafData);
		EditingUtils.setID(isLeaf, UmlViewsRepository.RedefinableTemplateSignature.Properties.isLeaf);
		EditingUtils.setEEFtype(isLeaf, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.RedefinableTemplateSignature.Properties.isLeaf, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createParameterAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter, UmlMessages.RedefinableTemplateSignaturePropertiesEditionPart_ParameterLabel);		 
		this.parameter = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addParameter(); }
			public void handleEdit(EObject element) { editParameter(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveParameter(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromParameter(element); }
			public void navigateTo(EObject element) { }
		});
		this.parameter.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter, UmlViewsRepository.SWT_KIND));
		this.parameter.createControls(parent);
		this.parameter.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData parameterData = new GridData(GridData.FILL_HORIZONTAL);
		parameterData.horizontalSpan = 3;
		this.parameter.setLayoutData(parameterData);
		this.parameter.disableMove();
		parameter.setID(UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter);
		parameter.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addParameter() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(parameter.getInput(), parameterFilters, parameterBusinessFilters,
		"parameter", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				parameter.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveParameter(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		parameter.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromParameter(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		parameter.refresh();
	}

	/**
	 * @generated
	 */
	protected void editParameter(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				parameter.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createTemplateFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.RedefinableTemplateSignature.Properties.template, UmlMessages.RedefinableTemplateSignaturePropertiesEditionPart_TemplateLabel);
		template = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.RedefinableTemplateSignature.Properties.template, UmlViewsRepository.SWT_KIND));
		template.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		template.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.template, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getTemplate()));
			}

		});
		GridData templateData = new GridData(GridData.FILL_HORIZONTAL);
		template.setLayoutData(templateData);
		template.setID(UmlViewsRepository.RedefinableTemplateSignature.Properties.template);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.RedefinableTemplateSignature.Properties.template, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected Composite createExtendedSignatureAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature, UmlMessages.RedefinableTemplateSignaturePropertiesEditionPart_ExtendedSignatureLabel);		 
		this.extendedSignature = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addExtendedSignature(); }
			public void handleEdit(EObject element) { editExtendedSignature(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveExtendedSignature(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromExtendedSignature(element); }
			public void navigateTo(EObject element) { }
		});
		this.extendedSignature.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature, UmlViewsRepository.SWT_KIND));
		this.extendedSignature.createControls(parent);
		this.extendedSignature.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData extendedSignatureData = new GridData(GridData.FILL_HORIZONTAL);
		extendedSignatureData.horizontalSpan = 3;
		this.extendedSignature.setLayoutData(extendedSignatureData);
		this.extendedSignature.disableMove();
		extendedSignature.setID(UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature);
		extendedSignature.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	protected void addExtendedSignature() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(extendedSignature.getInput(), extendedSignatureFilters, extendedSignatureBusinessFilters,
		"extendedSignature", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				extendedSignature.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @generated
	 */
	protected void moveExtendedSignature(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		extendedSignature.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromExtendedSignature(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		extendedSignature.refresh();
	}

	/**
	 * @generated
	 */
	protected void editExtendedSignature(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				extendedSignature.refresh();
			}
		}
	}

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createClassifierFlatComboViewer(Composite parent) {
		createDescription(parent, UmlViewsRepository.RedefinableTemplateSignature.Properties.classifier, UmlMessages.RedefinableTemplateSignaturePropertiesEditionPart_ClassifierLabel);
		classifier = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.RedefinableTemplateSignature.Properties.classifier, UmlViewsRepository.SWT_KIND));
		classifier.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		classifier.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(RedefinableTemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.RedefinableTemplateSignature.Properties.classifier, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getClassifier()));
			}

		});
		GridData classifierData = new GridData(GridData.FILL_HORIZONTAL);
		classifier.setLayoutData(classifierData);
		classifier.setID(UmlViewsRepository.RedefinableTemplateSignature.Properties.classifier);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.RedefinableTemplateSignature.Properties.classifier, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.RedefinableTemplateSignature.Properties.name);
		if (readOnly && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(UmlMessages.RedefinableTemplateSignature_ReadOnly);
		} else if (!readOnly && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
		Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean readOnly = isReadOnly(UmlViewsRepository.RedefinableTemplateSignature.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.RedefinableTemplateSignature_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
		boolean readOnly = isReadOnly(UmlViewsRepository.RedefinableTemplateSignature.Properties.visibility);
		if (readOnly && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.RedefinableTemplateSignature_ReadOnly);
		} else if (!readOnly && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#initClientDependency(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClientDependency(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		clientDependency.setContentProvider(contentProvider);
		clientDependency.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.RedefinableTemplateSignature.Properties.clientDependency);
		if (readOnly && clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(false);
			clientDependency.setToolTipText(UmlMessages.RedefinableTemplateSignature_ReadOnly);
		} else if (!readOnly && !clientDependency.getTable().isEnabled()) {
			clientDependency.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#updateClientDependency()
	 * @generated
	 */
	public void updateClientDependency() {
	clientDependency.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#addFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClientDependency(ViewerFilter filter) {
		clientDependencyFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#addBusinessFilterClientDependency(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClientDependency(ViewerFilter filter) {
		clientDependencyBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#isContainedInClientDependencyTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientDependencyTable(EObject element) {
		return ((ReferencesTableSettings)clientDependency.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#getIsLeaf()
	 * @generated
	 */
	public Boolean getIsLeaf() {
		return Boolean.valueOf(isLeaf.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#setIsLeaf(Boolean newValue)
	 * @generated
	 */
	public void setIsLeaf(Boolean newValue) {
		if (newValue != null) {
			isLeaf.setSelection(newValue.booleanValue());
		} else {
			isLeaf.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.RedefinableTemplateSignature.Properties.isLeaf);
		if (readOnly && isLeaf.isEnabled()) {
			isLeaf.setEnabled(false);
			isLeaf.setToolTipText(UmlMessages.RedefinableTemplateSignature_ReadOnly);
		} else if (!readOnly && !isLeaf.isEnabled()) {
			isLeaf.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#initParameter(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initParameter(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		parameter.setContentProvider(contentProvider);
		parameter.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.RedefinableTemplateSignature.Properties.parameter);
		if (readOnly && parameter.getTable().isEnabled()) {
			parameter.setEnabled(false);
			parameter.setToolTipText(UmlMessages.RedefinableTemplateSignature_ReadOnly);
		} else if (!readOnly && !parameter.getTable().isEnabled()) {
			parameter.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#updateParameter()
	 * @generated
	 */
	public void updateParameter() {
	parameter.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#addFilterParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToParameter(ViewerFilter filter) {
		parameterFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#addBusinessFilterParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToParameter(ViewerFilter filter) {
		parameterBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#isContainedInParameterTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInParameterTable(EObject element) {
		return ((ReferencesTableSettings)parameter.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#getTemplate()
	 * @generated
	 */
	public EObject getTemplate() {
		if (template.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) template.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#initTemplate(EObjectFlatComboSettings)
	 */
	public void initTemplate(EObjectFlatComboSettings settings) {
		template.setInput(settings);
		if (current != null) {
			template.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.RedefinableTemplateSignature.Properties.template);
		if (readOnly && template.isEnabled()) {
			template.setEnabled(false);
			template.setToolTipText(UmlMessages.RedefinableTemplateSignature_ReadOnly);
		} else if (!readOnly && !template.isEnabled()) {
			template.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#setTemplate(EObject newValue)
	 * @generated
	 */
	public void setTemplate(EObject newValue) {
		if (newValue != null) {
			template.setSelection(new StructuredSelection(newValue));
		} else {
			template.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.RedefinableTemplateSignature.Properties.template);
		if (readOnly && template.isEnabled()) {
			template.setEnabled(false);
			template.setToolTipText(UmlMessages.RedefinableTemplateSignature_ReadOnly);
		} else if (!readOnly && !template.isEnabled()) {
			template.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#setTemplateButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateButtonMode(ButtonsModeEnum newValue) {
		template.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#addFilterTemplate(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTemplate(ViewerFilter filter) {
		template.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#addBusinessFilterTemplate(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTemplate(ViewerFilter filter) {
		template.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#initExtendedSignature(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initExtendedSignature(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		extendedSignature.setContentProvider(contentProvider);
		extendedSignature.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.RedefinableTemplateSignature.Properties.extendedSignature);
		if (readOnly && extendedSignature.getTable().isEnabled()) {
			extendedSignature.setEnabled(false);
			extendedSignature.setToolTipText(UmlMessages.RedefinableTemplateSignature_ReadOnly);
		} else if (!readOnly && !extendedSignature.getTable().isEnabled()) {
			extendedSignature.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#updateExtendedSignature()
	 * @generated
	 */
	public void updateExtendedSignature() {
	extendedSignature.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#addFilterExtendedSignature(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToExtendedSignature(ViewerFilter filter) {
		extendedSignatureFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#addBusinessFilterExtendedSignature(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToExtendedSignature(ViewerFilter filter) {
		extendedSignatureBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#isContainedInExtendedSignatureTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInExtendedSignatureTable(EObject element) {
		return ((ReferencesTableSettings)extendedSignature.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#getClassifier()
	 * @generated
	 */
	public EObject getClassifier() {
		if (classifier.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) classifier.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#initClassifier(EObjectFlatComboSettings)
	 */
	public void initClassifier(EObjectFlatComboSettings settings) {
		classifier.setInput(settings);
		if (current != null) {
			classifier.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.RedefinableTemplateSignature.Properties.classifier);
		if (readOnly && classifier.isEnabled()) {
			classifier.setEnabled(false);
			classifier.setToolTipText(UmlMessages.RedefinableTemplateSignature_ReadOnly);
		} else if (!readOnly && !classifier.isEnabled()) {
			classifier.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#setClassifier(EObject newValue)
	 * @generated
	 */
	public void setClassifier(EObject newValue) {
		if (newValue != null) {
			classifier.setSelection(new StructuredSelection(newValue));
		} else {
			classifier.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.RedefinableTemplateSignature.Properties.classifier);
		if (readOnly && classifier.isEnabled()) {
			classifier.setEnabled(false);
			classifier.setToolTipText(UmlMessages.RedefinableTemplateSignature_ReadOnly);
		} else if (!readOnly && !classifier.isEnabled()) {
			classifier.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#setClassifierButtonMode(ButtonsModeEnum newValue)
	 */
	public void setClassifierButtonMode(ButtonsModeEnum newValue) {
		classifier.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#addFilterClassifier(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClassifier(ViewerFilter filter) {
		classifier.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.RedefinableTemplateSignaturePropertiesEditionPart#addBusinessFilterClassifier(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClassifier(ViewerFilter filter) {
		classifier.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.RedefinableTemplateSignature_Part_Title;
	}



}
