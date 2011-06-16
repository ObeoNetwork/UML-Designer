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
package org.eclipse.uml2.uml.parts.impl;

// Start of user code for imports
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.uml2.uml.parts.ConnectorEndPropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;
import org.eclipse.uml2.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ConnectorEndPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ConnectorEndPropertiesEditionPart {

	protected Button isOrdered;
	protected Button isUnique;
	protected EObjectFlatComboViewer role;
	protected EObjectFlatComboViewer partWithPort;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public ConnectorEndPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.ConnectorEnd.Properties.isOrdered) {
					return createIsOrderedCheckbox(parent);
				}
				if (key == UmlViewsRepository.ConnectorEnd.Properties.isUnique) {
					return createIsUniqueCheckbox(parent);
				}
				if (key == UmlViewsRepository.ConnectorEnd.Properties.role) {
					return createRoleFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.ConnectorEnd.Properties.partWithPort) {
					return createPartWithPortFlatComboViewer(parent);
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
		propertiesGroup.setText(UmlMessages.ConnectorEndPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	
	protected Composite createIsOrderedCheckbox(Composite parent) {
		isOrdered = new Button(parent, SWT.CHECK);
		isOrdered.setText(UmlMessages.ConnectorEndPropertiesEditionPart_IsOrderedLabel);
		isOrdered.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorEndPropertiesEditionPartImpl.this, UmlViewsRepository.ConnectorEnd.Properties.isOrdered, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isOrdered.getSelection())));
			}

		});
		GridData isOrderedData = new GridData(GridData.FILL_HORIZONTAL);
		isOrderedData.horizontalSpan = 2;
		isOrdered.setLayoutData(isOrderedData);
		EditingUtils.setID(isOrdered, UmlViewsRepository.ConnectorEnd.Properties.isOrdered);
		EditingUtils.setEEFtype(isOrdered, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ConnectorEnd.Properties.isOrdered, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createIsUniqueCheckbox(Composite parent) {
		isUnique = new Button(parent, SWT.CHECK);
		isUnique.setText(UmlMessages.ConnectorEndPropertiesEditionPart_IsUniqueLabel);
		isUnique.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorEndPropertiesEditionPartImpl.this, UmlViewsRepository.ConnectorEnd.Properties.isUnique, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isUnique.getSelection())));
			}

		});
		GridData isUniqueData = new GridData(GridData.FILL_HORIZONTAL);
		isUniqueData.horizontalSpan = 2;
		isUnique.setLayoutData(isUniqueData);
		EditingUtils.setID(isUnique, UmlViewsRepository.ConnectorEnd.Properties.isUnique);
		EditingUtils.setEEFtype(isUnique, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ConnectorEnd.Properties.isUnique, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createRoleFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.ConnectorEndPropertiesEditionPart_RoleLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ConnectorEnd.Properties.role, UmlViewsRepository.SWT_KIND));
		role = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ConnectorEnd.Properties.role, UmlViewsRepository.SWT_KIND));
		role.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		role.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorEndPropertiesEditionPartImpl.this, UmlViewsRepository.ConnectorEnd.Properties.role, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getRole()));
			}

		});
		GridData roleData = new GridData(GridData.FILL_HORIZONTAL);
		role.setLayoutData(roleData);
		role.setID(UmlViewsRepository.ConnectorEnd.Properties.role);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ConnectorEnd.Properties.role, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createPartWithPortFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.ConnectorEndPropertiesEditionPart_PartWithPortLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ConnectorEnd.Properties.partWithPort, UmlViewsRepository.SWT_KIND));
		partWithPort = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ConnectorEnd.Properties.partWithPort, UmlViewsRepository.SWT_KIND));
		partWithPort.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		partWithPort.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ConnectorEndPropertiesEditionPartImpl.this, UmlViewsRepository.ConnectorEnd.Properties.partWithPort, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getPartWithPort()));
			}

		});
		GridData partWithPortData = new GridData(GridData.FILL_HORIZONTAL);
		partWithPort.setLayoutData(partWithPortData);
		partWithPort.setID(UmlViewsRepository.ConnectorEnd.Properties.partWithPort);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ConnectorEnd.Properties.partWithPort, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#getIsOrdered()
	 * 
	 */
	public Boolean getIsOrdered() {
		return Boolean.valueOf(isOrdered.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#setIsOrdered(Boolean newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#getIsUnique()
	 * 
	 */
	public Boolean getIsUnique() {
		return Boolean.valueOf(isUnique.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#setIsUnique(Boolean newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#getRole()
	 * 
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
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#setRole(EObject newValue)
	 * 
	 */
	public void setRole(EObject newValue) {
		if (newValue != null) {
			role.setSelection(new StructuredSelection(newValue));
		} else {
			role.setSelection(new StructuredSelection()); //$NON-NLS-1$
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
	 * 
	 */
	public void addFilterToRole(ViewerFilter filter) {
		role.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#addBusinessFilterRole(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToRole(ViewerFilter filter) {
		role.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#getPartWithPort()
	 * 
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
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#setPartWithPort(EObject newValue)
	 * 
	 */
	public void setPartWithPort(EObject newValue) {
		if (newValue != null) {
			partWithPort.setSelection(new StructuredSelection(newValue));
		} else {
			partWithPort.setSelection(new StructuredSelection()); //$NON-NLS-1$
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
	 * 
	 */
	public void addFilterToPartWithPort(ViewerFilter filter) {
		partWithPort.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ConnectorEndPropertiesEditionPart#addBusinessFilterPartWithPort(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToPartWithPort(ViewerFilter filter) {
		partWithPort.addBusinessRuleFilter(filter);
	}







	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.ConnectorEnd_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
