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

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.part.impl.SectionPropertiesEditingPart;

import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;

import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;

import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class ConnectorEndPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, ConnectorEndPropertiesEditionPart {

	protected Button isOrdered;
	protected Button isUnique;
	protected EObjectFlatComboViewer role;
	protected EObjectFlatComboViewer partWithPort;



	/**
	 * For {@link ISection} use only.
	 */
	public ConnectorEndPropertiesEditionPartForm() { super(); }

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public ConnectorEndPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence connectorEndStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = connectorEndStep.addStep(UmlViewsRepository.ConnectorEnd.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.ConnectorEnd.Properties.isOrdered);
		propertiesStep.addStep(UmlViewsRepository.ConnectorEnd.Properties.isUnique);
		propertiesStep.addStep(UmlViewsRepository.ConnectorEnd.Properties.role);
		propertiesStep.addStep(UmlViewsRepository.ConnectorEnd.Properties.partWithPort);
		
		
		composer = new PartComposer(connectorEndStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.ConnectorEnd.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ConnectorEnd.Properties.isOrdered) {
					return createIsOrderedCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ConnectorEnd.Properties.isUnique) {
					return createIsUniqueCheckbox(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ConnectorEnd.Properties.role) {
					return createRoleFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ConnectorEnd.Properties.partWithPort) {
					return createPartWithPortFlatComboViewer(parent, widgetFactory);
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
		propertiesSection.setText(UmlMessages.ConnectorEndPropertiesEditionPart_PropertiesGroupLabel);
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
	
	protected Composite createIsOrderedCheckbox(FormToolkit widgetFactory, Composite parent) {
		isOrdered = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.ConnectorEnd.Properties.isOrdered, UmlMessages.ConnectorEndPropertiesEditionPart_IsOrderedLabel), SWT.CHECK);
		isOrdered.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorEndPropertiesEditionPartForm.this, UmlViewsRepository.ConnectorEnd.Properties.isOrdered, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isOrdered.getSelection())));
			}

		});
		GridData isOrderedData = new GridData(GridData.FILL_HORIZONTAL);
		isOrderedData.horizontalSpan = 2;
		isOrdered.setLayoutData(isOrderedData);
		EditingUtils.setID(isOrdered, UmlViewsRepository.ConnectorEnd.Properties.isOrdered);
		EditingUtils.setEEFtype(isOrdered, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ConnectorEnd.Properties.isOrdered, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @generated
	 */
	
	protected Composite createIsUniqueCheckbox(FormToolkit widgetFactory, Composite parent) {
		isUnique = widgetFactory.createButton(parent, getDescription(UmlViewsRepository.ConnectorEnd.Properties.isUnique, UmlMessages.ConnectorEndPropertiesEditionPart_IsUniqueLabel), SWT.CHECK);
		isUnique.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	@generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorEndPropertiesEditionPartForm.this, UmlViewsRepository.ConnectorEnd.Properties.isUnique, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isUnique.getSelection())));
			}

		});
		GridData isUniqueData = new GridData(GridData.FILL_HORIZONTAL);
		isUniqueData.horizontalSpan = 2;
		isUnique.setLayoutData(isUniqueData);
		EditingUtils.setID(isUnique, UmlViewsRepository.ConnectorEnd.Properties.isUnique);
		EditingUtils.setEEFtype(isUnique, "eef::Checkbox"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ConnectorEnd.Properties.isUnique, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createRoleFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ConnectorEnd.Properties.role, UmlMessages.ConnectorEndPropertiesEditionPart_RoleLabel);
		role = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ConnectorEnd.Properties.role, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(role);
		role.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData roleData = new GridData(GridData.FILL_HORIZONTAL);
		role.setLayoutData(roleData);
		role.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorEndPropertiesEditionPartForm.this, UmlViewsRepository.ConnectorEnd.Properties.role, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getRole()));
			}

		});
		role.setID(UmlViewsRepository.ConnectorEnd.Properties.role);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ConnectorEnd.Properties.role, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * @generated
	 */
	protected Composite createPartWithPortFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		createDescription(parent, UmlViewsRepository.ConnectorEnd.Properties.partWithPort, UmlMessages.ConnectorEndPropertiesEditionPart_PartWithPortLabel);
		partWithPort = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ConnectorEnd.Properties.partWithPort, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(partWithPort);
		partWithPort.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData partWithPortData = new GridData(GridData.FILL_HORIZONTAL);
		partWithPort.setLayoutData(partWithPortData);
		partWithPort.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorEndPropertiesEditionPartForm.this, UmlViewsRepository.ConnectorEnd.Properties.partWithPort, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getPartWithPort()));
			}

		});
		partWithPort.setID(UmlViewsRepository.ConnectorEnd.Properties.partWithPort);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ConnectorEnd.Properties.partWithPort, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#getIsOrdered()
	 * @generated
	 */
	public Boolean getIsOrdered() {
		return Boolean.valueOf(isOrdered.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#setIsOrdered(Boolean newValue)
	 * @generated
	 */
	public void setIsOrdered(Boolean newValue) {
		if (newValue != null) {
			isOrdered.setSelection(newValue.booleanValue());
		} else {
			isOrdered.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ConnectorEnd.Properties.isOrdered);
		if (readOnly && isOrdered.isEnabled()) {
			isOrdered.setEnabled(false);
			isOrdered.setToolTipText(UmlMessages.ConnectorEnd_ReadOnly);
		} else if (!readOnly && !isOrdered.isEnabled()) {
			isOrdered.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#getIsUnique()
	 * @generated
	 */
	public Boolean getIsUnique() {
		return Boolean.valueOf(isUnique.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#setIsUnique(Boolean newValue)
	 * @generated
	 */
	public void setIsUnique(Boolean newValue) {
		if (newValue != null) {
			isUnique.setSelection(newValue.booleanValue());
		} else {
			isUnique.setSelection(false);
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ConnectorEnd.Properties.isUnique);
		if (readOnly && isUnique.isEnabled()) {
			isUnique.setEnabled(false);
			isUnique.setToolTipText(UmlMessages.ConnectorEnd_ReadOnly);
		} else if (!readOnly && !isUnique.isEnabled()) {
			isUnique.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#getRole()
	 * @generated
	 */
	public EObject getRole() {
		if (role.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) role.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#initRole(EObjectFlatComboSettings)
	 */
	public void initRole(EObjectFlatComboSettings settings) {
		role.setInput(settings);
		if (current != null) {
			role.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ConnectorEnd.Properties.role);
		if (readOnly && role.isEnabled()) {
			role.setEnabled(false);
			role.setToolTipText(UmlMessages.ConnectorEnd_ReadOnly);
		} else if (!readOnly && !role.isEnabled()) {
			role.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#setRole(EObject newValue)
	 * @generated
	 */
	public void setRole(EObject newValue) {
		if (newValue != null) {
			role.setSelection(new StructuredSelection(newValue));
		} else {
			role.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ConnectorEnd.Properties.role);
		if (readOnly && role.isEnabled()) {
			role.setEnabled(false);
			role.setToolTipText(UmlMessages.ConnectorEnd_ReadOnly);
		} else if (!readOnly && !role.isEnabled()) {
			role.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#setRoleButtonMode(ButtonsModeEnum newValue)
	 */
	public void setRoleButtonMode(ButtonsModeEnum newValue) {
		role.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#addFilterRole(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRole(ViewerFilter filter) {
		role.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#addBusinessFilterRole(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRole(ViewerFilter filter) {
		role.addBusinessRuleFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#getPartWithPort()
	 * @generated
	 */
	public EObject getPartWithPort() {
		if (partWithPort.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) partWithPort.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#initPartWithPort(EObjectFlatComboSettings)
	 */
	public void initPartWithPort(EObjectFlatComboSettings settings) {
		partWithPort.setInput(settings);
		if (current != null) {
			partWithPort.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ConnectorEnd.Properties.partWithPort);
		if (readOnly && partWithPort.isEnabled()) {
			partWithPort.setEnabled(false);
			partWithPort.setToolTipText(UmlMessages.ConnectorEnd_ReadOnly);
		} else if (!readOnly && !partWithPort.isEnabled()) {
			partWithPort.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#setPartWithPort(EObject newValue)
	 * @generated
	 */
	public void setPartWithPort(EObject newValue) {
		if (newValue != null) {
			partWithPort.setSelection(new StructuredSelection(newValue));
		} else {
			partWithPort.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
		boolean readOnly = isReadOnly(UmlViewsRepository.ConnectorEnd.Properties.partWithPort);
		if (readOnly && partWithPort.isEnabled()) {
			partWithPort.setEnabled(false);
			partWithPort.setToolTipText(UmlMessages.ConnectorEnd_ReadOnly);
		} else if (!readOnly && !partWithPort.isEnabled()) {
			partWithPort.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#setPartWithPortButtonMode(ButtonsModeEnum newValue)
	 */
	public void setPartWithPortButtonMode(ButtonsModeEnum newValue) {
		partWithPort.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#addFilterPartWithPort(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToPartWithPort(ViewerFilter filter) {
		partWithPort.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#addBusinessFilterPartWithPort(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToPartWithPort(ViewerFilter filter) {
		partWithPort.addBusinessRuleFilter(filter);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
		return UmlMessages.ConnectorEnd_Part_Title;
	}



}
