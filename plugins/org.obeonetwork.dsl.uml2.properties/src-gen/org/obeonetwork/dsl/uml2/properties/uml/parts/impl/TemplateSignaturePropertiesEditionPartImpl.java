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

import org.eclipse.emf.ecore.EObject;

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

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateSignaturePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class TemplateSignaturePropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, TemplateSignaturePropertiesEditionPart {

	protected ReferencesTable parameter;
	protected List<ViewerFilter> parameterBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> parameterFilters = new ArrayList<ViewerFilter>();
	protected EObjectFlatComboViewer template;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public TemplateSignaturePropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence templateSignatureStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = templateSignatureStep.addStep(UmlViewsRepository.TemplateSignature.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.TemplateSignature.Properties.parameter);
		propertiesStep.addStep(UmlViewsRepository.TemplateSignature.Properties.template);
		
		
		composer = new PartComposer(templateSignatureStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.TemplateSignature.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.TemplateSignature.Properties.parameter) {
					return createParameterAdvancedReferencesTable(parent);
				}
				if (key == UmlViewsRepository.TemplateSignature.Properties.template) {
					return createTemplateFlatComboViewer(parent);
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
		propertiesGroup.setText(UmlMessages.TemplateSignaturePropertiesEditionPart_PropertiesGroupLabel);
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
	protected Composite createParameterAdvancedReferencesTable(Composite parent) {
		String label = getDescription(UmlViewsRepository.TemplateSignature.Properties.parameter, UmlMessages.TemplateSignaturePropertiesEditionPart_ParameterLabel);		 
		this.parameter = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addParameter(); }
			public void handleEdit(EObject element) { editParameter(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveParameter(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromParameter(element); }
			public void navigateTo(EObject element) { }
		});
		this.parameter.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.TemplateSignature.Properties.parameter, UmlViewsRepository.SWT_KIND));
		this.parameter.createControls(parent);
		this.parameter.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(TemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.TemplateSignature.Properties.parameter, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData parameterData = new GridData(GridData.FILL_HORIZONTAL);
		parameterData.horizontalSpan = 3;
		this.parameter.setLayoutData(parameterData);
		this.parameter.disableMove();
		parameter.setID(UmlViewsRepository.TemplateSignature.Properties.parameter);
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(TemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.TemplateSignature.Properties.parameter,
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(TemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.TemplateSignature.Properties.parameter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		parameter.refresh();
	}

	/**
	 * @generated
	 */
	protected void removeFromParameter(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(TemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.TemplateSignature.Properties.parameter, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
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
		createDescription(parent, UmlViewsRepository.TemplateSignature.Properties.template, UmlMessages.TemplateSignaturePropertiesEditionPart_TemplateLabel);
		template = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.TemplateSignature.Properties.template, UmlViewsRepository.SWT_KIND));
		template.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		template.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(TemplateSignaturePropertiesEditionPartImpl.this, UmlViewsRepository.TemplateSignature.Properties.template, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getTemplate()));
			}

		});
		GridData templateData = new GridData(GridData.FILL_HORIZONTAL);
		template.setLayoutData(templateData);
		template.setID(UmlViewsRepository.TemplateSignature.Properties.template);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.TemplateSignature.Properties.template, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateSignaturePropertiesEditionPart#initParameter(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initParameter(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		parameter.setContentProvider(contentProvider);
		parameter.setInput(settings);
		boolean readOnly = isReadOnly(UmlViewsRepository.TemplateSignature.Properties.parameter);
		if (readOnly && parameter.getTable().isEnabled()) {
			parameter.setEnabled(false);
			parameter.setToolTipText(UmlMessages.TemplateSignature_ReadOnly);
		} else if (!readOnly && !parameter.getTable().isEnabled()) {
			parameter.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateSignaturePropertiesEditionPart#updateParameter()
	 * @generated
	 */
	public void updateParameter() {
	parameter.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateSignaturePropertiesEditionPart#addFilterParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToParameter(ViewerFilter filter) {
		parameterFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateSignaturePropertiesEditionPart#addBusinessFilterParameter(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToParameter(ViewerFilter filter) {
		parameterBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateSignaturePropertiesEditionPart#isContainedInParameterTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInParameterTable(EObject element) {
		return ((ReferencesTableSettings)parameter.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateSignaturePropertiesEditionPart#getTemplate()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateSignaturePropertiesEditionPart#initTemplate(EObjectFlatComboSettings)
	 */
	public void initTemplate(EObjectFlatComboSettings settings) {
		template.setInput(settings);
		if (current != null) {
			template.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.TemplateSignature.Properties.template);
		if (readOnly && template.isEnabled()) {
			template.setEnabled(false);
			template.setToolTipText(UmlMessages.TemplateSignature_ReadOnly);
		} else if (!readOnly && !template.isEnabled()) {
			template.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateSignaturePropertiesEditionPart#setTemplate(EObject newValue)
	 * @generated
	 */
	public void setTemplate(EObject newValue) {
		if (newValue != null) {
			template.setSelection(new StructuredSelection(newValue));
		} else {
			template.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.TemplateSignature.Properties.template);
		if (readOnly && template.isEnabled()) {
			template.setEnabled(false);
			template.setToolTipText(UmlMessages.TemplateSignature_ReadOnly);
		} else if (!readOnly && !template.isEnabled()) {
			template.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateSignaturePropertiesEditionPart#setTemplateButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTemplateButtonMode(ButtonsModeEnum newValue) {
		template.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateSignaturePropertiesEditionPart#addFilterTemplate(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTemplate(ViewerFilter filter) {
		template.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.TemplateSignaturePropertiesEditionPart#addBusinessFilterTemplate(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTemplate(ViewerFilter filter) {
		template.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.TemplateSignature_Part_Title;
	}



}
