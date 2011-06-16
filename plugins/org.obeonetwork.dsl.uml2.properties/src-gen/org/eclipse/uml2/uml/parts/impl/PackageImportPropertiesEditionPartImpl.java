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
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
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
import org.eclipse.emf.eef.runtime.ui.widgets.EMFComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.uml2.uml.parts.PackageImportPropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;
import org.eclipse.uml2.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class PackageImportPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, PackageImportPropertiesEditionPart {

	protected EMFComboViewer visibility;
	protected EObjectFlatComboViewer importedPackage;
	protected EObjectFlatComboViewer importingNamespace;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public PackageImportPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence packageImportStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = packageImportStep.addStep(UmlViewsRepository.PackageImport.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.PackageImport.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.PackageImport.Properties.importedPackage);
		propertiesStep.addStep(UmlViewsRepository.PackageImport.Properties.importingNamespace);
		
		
		composer = new PartComposer(packageImportStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.PackageImport.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.PackageImport.Properties.visibility) {
					return createVisibilityEMFComboViewer(parent);
				}
				if (key == UmlViewsRepository.PackageImport.Properties.importedPackage) {
					return createImportedPackageFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.PackageImport.Properties.importingNamespace) {
					return createImportingNamespaceFlatComboViewer(parent);
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
		propertiesGroup.setText(UmlMessages.PackageImportPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.PackageImportPropertiesEditionPart_VisibilityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.PackageImport.Properties.visibility, UmlViewsRepository.SWT_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PackageImportPropertiesEditionPartImpl.this, UmlViewsRepository.PackageImport.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.PackageImport.Properties.visibility);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.PackageImport.Properties.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createImportedPackageFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.PackageImportPropertiesEditionPart_ImportedPackageLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.PackageImport.Properties.importedPackage, UmlViewsRepository.SWT_KIND));
		importedPackage = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.PackageImport.Properties.importedPackage, UmlViewsRepository.SWT_KIND));
		importedPackage.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		importedPackage.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PackageImportPropertiesEditionPartImpl.this, UmlViewsRepository.PackageImport.Properties.importedPackage, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getImportedPackage()));
			}

		});
		GridData importedPackageData = new GridData(GridData.FILL_HORIZONTAL);
		importedPackage.setLayoutData(importedPackageData);
		importedPackage.setID(UmlViewsRepository.PackageImport.Properties.importedPackage);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.PackageImport.Properties.importedPackage, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createImportingNamespaceFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.PackageImportPropertiesEditionPart_ImportingNamespaceLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.PackageImport.Properties.importingNamespace, UmlViewsRepository.SWT_KIND));
		importingNamespace = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.PackageImport.Properties.importingNamespace, UmlViewsRepository.SWT_KIND));
		importingNamespace.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		importingNamespace.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PackageImportPropertiesEditionPartImpl.this, UmlViewsRepository.PackageImport.Properties.importingNamespace, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getImportingNamespace()));
			}

		});
		GridData importingNamespaceData = new GridData(GridData.FILL_HORIZONTAL);
		importingNamespace.setLayoutData(importingNamespaceData);
		importingNamespace.setID(UmlViewsRepository.PackageImport.Properties.importingNamespace);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.PackageImport.Properties.importingNamespace, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#getVisibility()
	 * 
	 */
	public Enumerator getVisibility() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#initVisibility(EEnum eenum, Enumerator current)
	 */
	public void initVisibility(EEnum eenum, Enumerator current) {
		visibility.setInput(eenum.getELiterals());
		visibility.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * 
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#getImportedPackage()
	 * 
	 */
	public EObject getImportedPackage() {
		if (importedPackage.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) importedPackage.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#initImportedPackage(EObjectFlatComboSettings)
	 */
	public void initImportedPackage(EObjectFlatComboSettings settings) {
		importedPackage.setInput(settings);
		if (current != null) {
			importedPackage.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#setImportedPackage(EObject newValue)
	 * 
	 */
	public void setImportedPackage(EObject newValue) {
		if (newValue != null) {
			importedPackage.setSelection(new StructuredSelection(newValue));
		} else {
			importedPackage.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#setImportedPackageButtonMode(ButtonsModeEnum newValue)
	 */
	public void setImportedPackageButtonMode(ButtonsModeEnum newValue) {
		importedPackage.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#addFilterImportedPackage(ViewerFilter filter)
	 * 
	 */
	public void addFilterToImportedPackage(ViewerFilter filter) {
		importedPackage.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#addBusinessFilterImportedPackage(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToImportedPackage(ViewerFilter filter) {
		importedPackage.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#getImportingNamespace()
	 * 
	 */
	public EObject getImportingNamespace() {
		if (importingNamespace.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) importingNamespace.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#initImportingNamespace(EObjectFlatComboSettings)
	 */
	public void initImportingNamespace(EObjectFlatComboSettings settings) {
		importingNamespace.setInput(settings);
		if (current != null) {
			importingNamespace.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#setImportingNamespace(EObject newValue)
	 * 
	 */
	public void setImportingNamespace(EObject newValue) {
		if (newValue != null) {
			importingNamespace.setSelection(new StructuredSelection(newValue));
		} else {
			importingNamespace.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#setImportingNamespaceButtonMode(ButtonsModeEnum newValue)
	 */
	public void setImportingNamespaceButtonMode(ButtonsModeEnum newValue) {
		importingNamespace.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#addFilterImportingNamespace(ViewerFilter filter)
	 * 
	 */
	public void addFilterToImportingNamespace(ViewerFilter filter) {
		importingNamespace.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart#addBusinessFilterImportingNamespace(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToImportingNamespace(ViewerFilter filter) {
		importingNamespace.addBusinessRuleFilter(filter);
	}







	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.PackageImport_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
