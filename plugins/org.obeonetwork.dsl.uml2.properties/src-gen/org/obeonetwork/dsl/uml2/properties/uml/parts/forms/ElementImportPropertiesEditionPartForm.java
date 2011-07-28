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
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ElementImportPropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, ElementImportPropertiesEditionPart {

	protected EMFComboViewer visibility;
	protected Text alias;
	protected EObjectFlatComboViewer importedElement;
	protected EObjectFlatComboViewer importingNamespace;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public ElementImportPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence elementImportStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = elementImportStep.addStep(UmlViewsRepository.ElementImport.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.ElementImport.Properties.visibility);
		propertiesStep.addStep(UmlViewsRepository.ElementImport.Properties.alias);
		propertiesStep.addStep(UmlViewsRepository.ElementImport.Properties.importedElement);
		propertiesStep.addStep(UmlViewsRepository.ElementImport.Properties.importingNamespace);
		
		
		composer = new PartComposer(elementImportStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.ElementImport.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ElementImport.Properties.visibility) {
					return createVisibilityEMFComboViewer(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ElementImport.Properties.alias) {
					return 		createAliasText(widgetFactory, parent);
				}
				if (key == UmlViewsRepository.ElementImport.Properties.importedElement) {
					return createImportedElementFlatComboViewer(parent, widgetFactory);
				}
				if (key == UmlViewsRepository.ElementImport.Properties.importingNamespace) {
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
		propertiesSection.setText(UmlMessages.ElementImportPropertiesEditionPart_PropertiesGroupLabel);
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
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ElementImportPropertiesEditionPart_VisibilityLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ElementImport.Properties.visibility, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ElementImportPropertiesEditionPartForm.this, UmlViewsRepository.ElementImport.Properties.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		visibility.setID(UmlViewsRepository.ElementImport.Properties.visibility);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ElementImport.Properties.visibility, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createAliasText(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ElementImportPropertiesEditionPart_AliasLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ElementImport.Properties.alias, UmlViewsRepository.FORM_KIND));
		alias = widgetFactory.createText(parent, ""); //$NON-NLS-1$
		alias.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		widgetFactory.paintBordersFor(parent);
		GridData aliasData = new GridData(GridData.FILL_HORIZONTAL);
		alias.setLayoutData(aliasData);
		alias.addFocusListener(new FocusAdapter() {
			/**
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ElementImportPropertiesEditionPartForm.this, UmlViewsRepository.ElementImport.Properties.alias, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, alias.getText()));
			}
		});
		alias.addKeyListener(new KeyAdapter() {
			/**
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ElementImportPropertiesEditionPartForm.this, UmlViewsRepository.ElementImport.Properties.alias, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, alias.getText()));
				}
			}
		});
		EditingUtils.setID(alias, UmlViewsRepository.ElementImport.Properties.alias);
		EditingUtils.setEEFtype(alias, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ElementImport.Properties.alias, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createImportedElementFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ElementImportPropertiesEditionPart_ImportedElementLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ElementImport.Properties.importedElement, UmlViewsRepository.FORM_KIND));
		importedElement = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ElementImport.Properties.importedElement, UmlViewsRepository.FORM_KIND));
		widgetFactory.adapt(importedElement);
		importedElement.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		GridData importedElementData = new GridData(GridData.FILL_HORIZONTAL);
		importedElement.setLayoutData(importedElementData);
		importedElement.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ElementImportPropertiesEditionPartForm.this, UmlViewsRepository.ElementImport.Properties.importedElement, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getImportedElement()));
			}

		});
		importedElement.setID(UmlViewsRepository.ElementImport.Properties.importedElement);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ElementImport.Properties.importedElement, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * @param widgetFactory factory to use to instanciante widget of the form
	 * 
	 */
	protected Composite createImportingNamespaceFlatComboViewer(Composite parent, FormToolkit widgetFactory) {
		FormUtils.createPartLabel(widgetFactory, parent, UmlMessages.ElementImportPropertiesEditionPart_ImportingNamespaceLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ElementImport.Properties.importingNamespace, UmlViewsRepository.FORM_KIND));
		importingNamespace = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ElementImport.Properties.importingNamespace, UmlViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ElementImportPropertiesEditionPartForm.this, UmlViewsRepository.ElementImport.Properties.importingNamespace, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getImportingNamespace()));
			}

		});
		importingNamespace.setID(UmlViewsRepository.ElementImport.Properties.importingNamespace);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ElementImport.Properties.importingNamespace, UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#getVisibility()
	 * 
	 */
	public Enumerator getVisibility() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#initVisibility(EEnum eenum, Enumerator current)
	 */
	public void initVisibility(EEnum eenum, Enumerator current) {
		visibility.setInput(eenum.getELiterals());
		visibility.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * 
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#getAlias()
	 * 
	 */
	public String getAlias() {
		return alias.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#setAlias(String newValue)
	 * 
	 */
	public void setAlias(String newValue) {
		if (newValue != null) {
			alias.setText(newValue);
		} else {
			alias.setText(""); //$NON-NLS-1$
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#getImportedElement()
	 * 
	 */
	public EObject getImportedElement() {
		if (importedElement.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) importedElement.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#initImportedElement(EObjectFlatComboSettings)
	 */
	public void initImportedElement(EObjectFlatComboSettings settings) {
		importedElement.setInput(settings);
		if (current != null) {
			importedElement.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#setImportedElement(EObject newValue)
	 * 
	 */
	public void setImportedElement(EObject newValue) {
		if (newValue != null) {
			importedElement.setSelection(new StructuredSelection(newValue));
		} else {
			importedElement.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#setImportedElementButtonMode(ButtonsModeEnum newValue)
	 */
	public void setImportedElementButtonMode(ButtonsModeEnum newValue) {
		importedElement.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#addFilterImportedElement(ViewerFilter filter)
	 * 
	 */
	public void addFilterToImportedElement(ViewerFilter filter) {
		importedElement.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#addBusinessFilterImportedElement(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToImportedElement(ViewerFilter filter) {
		importedElement.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#getImportingNamespace()
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#initImportingNamespace(EObjectFlatComboSettings)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#setImportingNamespace(EObject newValue)
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#setImportingNamespaceButtonMode(ButtonsModeEnum newValue)
	 */
	public void setImportingNamespaceButtonMode(ButtonsModeEnum newValue) {
		importingNamespace.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#addFilterImportingNamespace(ViewerFilter filter)
	 * 
	 */
	public void addFilterToImportingNamespace(ViewerFilter filter) {
		importingNamespace.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ElementImportPropertiesEditionPart#addBusinessFilterImportingNamespace(ViewerFilter filter)
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
		return UmlMessages.ElementImport_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
