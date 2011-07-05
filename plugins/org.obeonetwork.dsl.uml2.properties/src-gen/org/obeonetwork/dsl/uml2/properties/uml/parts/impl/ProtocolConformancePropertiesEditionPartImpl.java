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
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolConformancePropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ProtocolConformancePropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ProtocolConformancePropertiesEditionPart {

	protected EObjectFlatComboViewer generalMachine;
	protected EObjectFlatComboViewer specificMachine;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public ProtocolConformancePropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence protocolConformanceStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = protocolConformanceStep.addStep(UmlViewsRepository.ProtocolConformance.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.ProtocolConformance.Properties.generalMachine);
		propertiesStep.addStep(UmlViewsRepository.ProtocolConformance.Properties.specificMachine);
		
		
		composer = new PartComposer(protocolConformanceStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.ProtocolConformance.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.ProtocolConformance.Properties.generalMachine) {
					return createGeneralMachineFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.ProtocolConformance.Properties.specificMachine) {
					return createSpecificMachineFlatComboViewer(parent);
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
		propertiesGroup.setText(UmlMessages.ProtocolConformancePropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createGeneralMachineFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.ProtocolConformancePropertiesEditionPart_GeneralMachineLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ProtocolConformance.Properties.generalMachine, UmlViewsRepository.SWT_KIND));
		generalMachine = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ProtocolConformance.Properties.generalMachine, UmlViewsRepository.SWT_KIND));
		generalMachine.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		generalMachine.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolConformancePropertiesEditionPartImpl.this, UmlViewsRepository.ProtocolConformance.Properties.generalMachine, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getGeneralMachine()));
			}

		});
		GridData generalMachineData = new GridData(GridData.FILL_HORIZONTAL);
		generalMachine.setLayoutData(generalMachineData);
		generalMachine.setID(UmlViewsRepository.ProtocolConformance.Properties.generalMachine);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProtocolConformance.Properties.generalMachine, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createSpecificMachineFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.ProtocolConformancePropertiesEditionPart_SpecificMachineLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ProtocolConformance.Properties.specificMachine, UmlViewsRepository.SWT_KIND));
		specificMachine = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ProtocolConformance.Properties.specificMachine, UmlViewsRepository.SWT_KIND));
		specificMachine.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		specificMachine.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ProtocolConformancePropertiesEditionPartImpl.this, UmlViewsRepository.ProtocolConformance.Properties.specificMachine, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getSpecificMachine()));
			}

		});
		GridData specificMachineData = new GridData(GridData.FILL_HORIZONTAL);
		specificMachine.setLayoutData(specificMachineData);
		specificMachine.setID(UmlViewsRepository.ProtocolConformance.Properties.specificMachine);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ProtocolConformance.Properties.specificMachine, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolConformancePropertiesEditionPart#getGeneralMachine()
	 * 
	 */
	public EObject getGeneralMachine() {
		if (generalMachine.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) generalMachine.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolConformancePropertiesEditionPart#initGeneralMachine(EObjectFlatComboSettings)
	 */
	public void initGeneralMachine(EObjectFlatComboSettings settings) {
		generalMachine.setInput(settings);
		if (current != null) {
			generalMachine.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolConformancePropertiesEditionPart#setGeneralMachine(EObject newValue)
	 * 
	 */
	public void setGeneralMachine(EObject newValue) {
		if (newValue != null) {
			generalMachine.setSelection(new StructuredSelection(newValue));
		} else {
			generalMachine.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolConformancePropertiesEditionPart#setGeneralMachineButtonMode(ButtonsModeEnum newValue)
	 */
	public void setGeneralMachineButtonMode(ButtonsModeEnum newValue) {
		generalMachine.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolConformancePropertiesEditionPart#addFilterGeneralMachine(ViewerFilter filter)
	 * 
	 */
	public void addFilterToGeneralMachine(ViewerFilter filter) {
		generalMachine.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolConformancePropertiesEditionPart#addBusinessFilterGeneralMachine(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToGeneralMachine(ViewerFilter filter) {
		generalMachine.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolConformancePropertiesEditionPart#getSpecificMachine()
	 * 
	 */
	public EObject getSpecificMachine() {
		if (specificMachine.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) specificMachine.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolConformancePropertiesEditionPart#initSpecificMachine(EObjectFlatComboSettings)
	 */
	public void initSpecificMachine(EObjectFlatComboSettings settings) {
		specificMachine.setInput(settings);
		if (current != null) {
			specificMachine.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolConformancePropertiesEditionPart#setSpecificMachine(EObject newValue)
	 * 
	 */
	public void setSpecificMachine(EObject newValue) {
		if (newValue != null) {
			specificMachine.setSelection(new StructuredSelection(newValue));
		} else {
			specificMachine.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolConformancePropertiesEditionPart#setSpecificMachineButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSpecificMachineButtonMode(ButtonsModeEnum newValue) {
		specificMachine.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolConformancePropertiesEditionPart#addFilterSpecificMachine(ViewerFilter filter)
	 * 
	 */
	public void addFilterToSpecificMachine(ViewerFilter filter) {
		specificMachine.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ProtocolConformancePropertiesEditionPart#addBusinessFilterSpecificMachine(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToSpecificMachine(ViewerFilter filter) {
		specificMachine.addBusinessRuleFilter(filter);
	}







	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.ProtocolConformance_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
