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
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EMFComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.obeonetwork.dsl.uml2.properties.uml.parts.PackageImportPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class PackageImportPropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, PackageImportPropertiesEditionPart {

	protected EMFComboViewer visibility;
	protected EObjectFlatComboViewer importedPackage;
	protected EObjectFlatComboViewer importingNamespace;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public PackageImportPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence packageImportStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = packageImportStep.addStep(UmlViewsRepository.PackageImport.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.PackageImport.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.PackageImport.Properties.importedPackage);
		propertiesStep.addStep(UmlViewsRepository.PackageImport.Properties.importingNamespace);
		
		
		composer = new PartComposer(packageImportStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.PackageImport.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.PackageImport.Properties.visibility) {
					return createVisibilityEMFComboViewer(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.PackageImport.Properties.importedPackage) {
					return createImportedPackageFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.PackageImport.Properties.importingNamespace) {
					return createImportingNamespaceFlatComboViewer(parent, widgetFactory);
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
		propertiesSection.setText(UmlMessages.PackageImportPropertiesEditionPart_PropertiesGroupLabel);
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

	
	protected Composite createVisibilityEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PackageImportPropertiesEditionPart_VisibilityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.PackageImport.Properties.visibility, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PackageImportPropertiesEditionPartForm.this, UmlViewsRepository.PackageImport.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.PackageImport.Properties.visibility);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.PackageImport.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createImportedPackageFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PackageImportPropertiesEditionPart_ImportedPackageLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.PackageImport.Properties.importedPackage, UmlViewsRepository.FORM_KIND));
		importedPackage = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.PackageImport.Properties.importedPackage, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(importedPackage);
		importedPackage.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData importedPackageData = new GridData(GridData.FILL_HORIZONTAL);
		importedPackage.setLayoutData(importedPackageData);
		importedPackage.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PackageImportPropertiesEditionPartForm.this, UmlViewsRepository.PackageImport.Properties.importedPackage, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getImportedPackage()));
			}

		});
		importedPackage.setID(UmlViewsRepository.PackageImport.Properties.importedPackage);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.PackageImport.Properties.importedPackage, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createImportingNamespaceFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.PackageImportPropertiesEditionPart_ImportingNamespaceLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.PackageImport.Properties.importingNamespace, UmlViewsRepository.FORM_KIND));
		importingNamespace = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.PackageImport.Properties.importingNamespace, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(importingNamespace);
		importingNamespace.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData importingNamespaceData = new GridData(GridData.FILL_HORIZONTAL);
		importingNamespace.setLayoutData(importingNamespaceData);
		importingNamespace.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PackageImportPropertiesEditionPartForm.this, UmlViewsRepository.PackageImport.Properties.importingNamespace, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getImportingNamespace()));
			}

		});
		importingNamespace.setID(UmlViewsRepository.PackageImport.Properties.importingNamespace);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.PackageImport.Properties.importingNamespace, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
