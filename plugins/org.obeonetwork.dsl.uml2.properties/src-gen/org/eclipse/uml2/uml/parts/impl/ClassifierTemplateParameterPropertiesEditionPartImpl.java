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
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.uml2.uml.parts.ClassifierTemplateParameterPropertiesEditionPart;
import org.eclipse.uml2.uml.parts.UmlViewsRepository;
import org.eclipse.uml2.uml.providers.UmlMessages;


// End of user code

/**
 * @author <a href="mailto:stephane.bouchet@obeo.fr">Stephane Bouchet</a>
 * 
 */
public class ClassifierTemplateParameterPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ClassifierTemplateParameterPropertiesEditionPart {

	protected EObjectFlatComboViewer signature;
	protected EObjectFlatComboViewer parameteredElement;
	protected EObjectFlatComboViewer default_;
	protected Button allowSubstitutable;
	protected ReferencesTable constrainingClassifier;
	protected List<ViewerFilter> constrainingClassifierBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> constrainingClassifierFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public ClassifierTemplateParameterPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence classifierTemplateParameterStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = classifierTemplateParameterStep.addStep(UmlViewsRepository.ClassifierTemplateParameter.Properties.class);
		propertiesStep.addStep(UmlViewsRepository.ClassifierTemplateParameter.Properties.signature);
		propertiesStep.addStep(UmlViewsRepository.ClassifierTemplateParameter.Properties.parameteredElement);
		propertiesStep.addStep(UmlViewsRepository.ClassifierTemplateParameter.Properties.default_);
		propertiesStep.addStep(UmlViewsRepository.ClassifierTemplateParameter.Properties.allowSubstitutable);
		propertiesStep.addStep(UmlViewsRepository.ClassifierTemplateParameter.Properties.constrainingClassifier);
		
		
		composer = new PartComposer(classifierTemplateParameterStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == UmlViewsRepository.ClassifierTemplateParameter.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == UmlViewsRepository.ClassifierTemplateParameter.Properties.signature) {
					return createSignatureFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.ClassifierTemplateParameter.Properties.parameteredElement) {
					return createParameteredElementFlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.ClassifierTemplateParameter.Properties.default_) {
					return createDefault_FlatComboViewer(parent);
				}
				if (key == UmlViewsRepository.ClassifierTemplateParameter.Properties.allowSubstitutable) {
					return createAllowSubstitutableCheckbox(parent);
				}
				if (key == UmlViewsRepository.ClassifierTemplateParameter.Properties.constrainingClassifier) {
					return createConstrainingClassifierAdvancedReferencesTable(parent);
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
		propertiesGroup.setText(UmlMessages.ClassifierTemplateParameterPropertiesEditionPart_PropertiesGroupLabel);
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
	protected Composite createSignatureFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.ClassifierTemplateParameterPropertiesEditionPart_SignatureLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ClassifierTemplateParameter.Properties.signature, UmlViewsRepository.SWT_KIND));
		signature = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ClassifierTemplateParameter.Properties.signature, UmlViewsRepository.SWT_KIND));
		signature.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		signature.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClassifierTemplateParameterPropertiesEditionPartImpl.this, UmlViewsRepository.ClassifierTemplateParameter.Properties.signature, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getSignature()));
			}

		});
		GridData signatureData = new GridData(GridData.FILL_HORIZONTAL);
		signature.setLayoutData(signatureData);
		signature.setID(UmlViewsRepository.ClassifierTemplateParameter.Properties.signature);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ClassifierTemplateParameter.Properties.signature, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createParameteredElementFlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.ClassifierTemplateParameterPropertiesEditionPart_ParameteredElementLabel, propertiesEditionComponent.isRequired(UmlViewsRepository.ClassifierTemplateParameter.Properties.parameteredElement, UmlViewsRepository.SWT_KIND));
		parameteredElement = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ClassifierTemplateParameter.Properties.parameteredElement, UmlViewsRepository.SWT_KIND));
		parameteredElement.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		parameteredElement.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClassifierTemplateParameterPropertiesEditionPartImpl.this, UmlViewsRepository.ClassifierTemplateParameter.Properties.parameteredElement, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getParameteredElement()));
			}

		});
		GridData parameteredElementData = new GridData(GridData.FILL_HORIZONTAL);
		parameteredElement.setLayoutData(parameteredElementData);
		parameteredElement.setID(UmlViewsRepository.ClassifierTemplateParameter.Properties.parameteredElement);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ClassifierTemplateParameter.Properties.parameteredElement, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createDefault_FlatComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, UmlMessages.ClassifierTemplateParameterPropertiesEditionPart_Default_Label, propertiesEditionComponent.isRequired(UmlViewsRepository.ClassifierTemplateParameter.Properties.default_, UmlViewsRepository.SWT_KIND));
		default_ = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.ClassifierTemplateParameter.Properties.default_, UmlViewsRepository.SWT_KIND));
		default_.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		default_.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClassifierTemplateParameterPropertiesEditionPartImpl.this, UmlViewsRepository.ClassifierTemplateParameter.Properties.default_, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getDefault_()));
			}

		});
		GridData default_Data = new GridData(GridData.FILL_HORIZONTAL);
		default_.setLayoutData(default_Data);
		default_.setID(UmlViewsRepository.ClassifierTemplateParameter.Properties.default_);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ClassifierTemplateParameter.Properties.default_, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createAllowSubstitutableCheckbox(Composite parent) {
		allowSubstitutable = new Button(parent, SWT.CHECK);
		allowSubstitutable.setText(UmlMessages.ClassifierTemplateParameterPropertiesEditionPart_AllowSubstitutableLabel);
		allowSubstitutable.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * 	
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClassifierTemplateParameterPropertiesEditionPartImpl.this, UmlViewsRepository.ClassifierTemplateParameter.Properties.allowSubstitutable, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(allowSubstitutable.getSelection())));
			}

		});
		GridData allowSubstitutableData = new GridData(GridData.FILL_HORIZONTAL);
		allowSubstitutableData.horizontalSpan = 2;
		allowSubstitutable.setLayoutData(allowSubstitutableData);
		EditingUtils.setID(allowSubstitutable, UmlViewsRepository.ClassifierTemplateParameter.Properties.allowSubstitutable);
		EditingUtils.setEEFtype(allowSubstitutable, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.ClassifierTemplateParameter.Properties.allowSubstitutable, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createConstrainingClassifierAdvancedReferencesTable(Composite parent) {
		this.constrainingClassifier = new ReferencesTable(UmlMessages.ClassifierTemplateParameterPropertiesEditionPart_ConstrainingClassifierLabel, new ReferencesTableListener() {
			public void handleAdd() { addConstrainingClassifier(); }
			public void handleEdit(EObject element) { editConstrainingClassifier(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveConstrainingClassifier(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromConstrainingClassifier(element); }
			public void navigateTo(EObject element) { }
		});
		this.constrainingClassifier.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.ClassifierTemplateParameter.Properties.constrainingClassifier, UmlViewsRepository.SWT_KIND));
		this.constrainingClassifier.createControls(parent);
		this.constrainingClassifier.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClassifierTemplateParameterPropertiesEditionPartImpl.this, UmlViewsRepository.ClassifierTemplateParameter.Properties.constrainingClassifier, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData constrainingClassifierData = new GridData(GridData.FILL_HORIZONTAL);
		constrainingClassifierData.horizontalSpan = 3;
		this.constrainingClassifier.setLayoutData(constrainingClassifierData);
		this.constrainingClassifier.disableMove();
		constrainingClassifier.setID(UmlViewsRepository.ClassifierTemplateParameter.Properties.constrainingClassifier);
		constrainingClassifier.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addConstrainingClassifier() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(constrainingClassifier.getInput(), constrainingClassifierFilters, constrainingClassifierBusinessFilters,
		"constrainingClassifier", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClassifierTemplateParameterPropertiesEditionPartImpl.this, UmlViewsRepository.ClassifierTemplateParameter.Properties.constrainingClassifier,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				constrainingClassifier.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveConstrainingClassifier(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClassifierTemplateParameterPropertiesEditionPartImpl.this, UmlViewsRepository.ClassifierTemplateParameter.Properties.constrainingClassifier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		constrainingClassifier.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromConstrainingClassifier(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ClassifierTemplateParameterPropertiesEditionPartImpl.this, UmlViewsRepository.ClassifierTemplateParameter.Properties.constrainingClassifier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		constrainingClassifier.refresh();
	}

	/**
	 * 
	 */
	protected void editConstrainingClassifier(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				constrainingClassifier.refresh();
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#getSignature()
	 * 
	 */
	public EObject getSignature() {
		if (signature.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) signature.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#initSignature(EObjectFlatComboSettings)
	 */
	public void initSignature(EObjectFlatComboSettings settings) {
		signature.setInput(settings);
		if (current != null) {
			signature.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#setSignature(EObject newValue)
	 * 
	 */
	public void setSignature(EObject newValue) {
		if (newValue != null) {
			signature.setSelection(new StructuredSelection(newValue));
		} else {
			signature.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#setSignatureButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSignatureButtonMode(ButtonsModeEnum newValue) {
		signature.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#addFilterSignature(ViewerFilter filter)
	 * 
	 */
	public void addFilterToSignature(ViewerFilter filter) {
		signature.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#addBusinessFilterSignature(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToSignature(ViewerFilter filter) {
		signature.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#getParameteredElement()
	 * 
	 */
	public EObject getParameteredElement() {
		if (parameteredElement.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) parameteredElement.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#initParameteredElement(EObjectFlatComboSettings)
	 */
	public void initParameteredElement(EObjectFlatComboSettings settings) {
		parameteredElement.setInput(settings);
		if (current != null) {
			parameteredElement.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#setParameteredElement(EObject newValue)
	 * 
	 */
	public void setParameteredElement(EObject newValue) {
		if (newValue != null) {
			parameteredElement.setSelection(new StructuredSelection(newValue));
		} else {
			parameteredElement.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#setParameteredElementButtonMode(ButtonsModeEnum newValue)
	 */
	public void setParameteredElementButtonMode(ButtonsModeEnum newValue) {
		parameteredElement.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#addFilterParameteredElement(ViewerFilter filter)
	 * 
	 */
	public void addFilterToParameteredElement(ViewerFilter filter) {
		parameteredElement.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#addBusinessFilterParameteredElement(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToParameteredElement(ViewerFilter filter) {
		parameteredElement.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#getDefault_()
	 * 
	 */
	public EObject getDefault_() {
		if (default_.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) default_.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#initDefault_(EObjectFlatComboSettings)
	 */
	public void initDefault_(EObjectFlatComboSettings settings) {
		default_.setInput(settings);
		if (current != null) {
			default_.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#setDefault_(EObject newValue)
	 * 
	 */
	public void setDefault_(EObject newValue) {
		if (newValue != null) {
			default_.setSelection(new StructuredSelection(newValue));
		} else {
			default_.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#setDefault_ButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDefault_ButtonMode(ButtonsModeEnum newValue) {
		default_.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#addFilterDefault_(ViewerFilter filter)
	 * 
	 */
	public void addFilterToDefault_(ViewerFilter filter) {
		default_.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#addBusinessFilterDefault_(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToDefault_(ViewerFilter filter) {
		default_.addBusinessRuleFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#getAllowSubstitutable()
	 * 
	 */
	public Boolean getAllowSubstitutable() {
		return Boolean.valueOf(allowSubstitutable.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#setAllowSubstitutable(Boolean newValue)
	 * 
	 */
	public void setAllowSubstitutable(Boolean newValue) {
		if (newValue != null) {
			allowSubstitutable.setSelection(newValue.booleanValue());
		} else {
			allowSubstitutable.setSelection(false);
		}
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#initConstrainingClassifier(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initConstrainingClassifier(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		constrainingClassifier.setContentProvider(contentProvider);
		constrainingClassifier.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#updateConstrainingClassifier()
	 * 
	 */
	public void updateConstrainingClassifier() {
	constrainingClassifier.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#addFilterConstrainingClassifier(ViewerFilter filter)
	 * 
	 */
	public void addFilterToConstrainingClassifier(ViewerFilter filter) {
		constrainingClassifierFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#addBusinessFilterConstrainingClassifier(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToConstrainingClassifier(ViewerFilter filter) {
		constrainingClassifierBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.ClassifierTemplateParameterPropertiesEditionPart#isContainedInConstrainingClassifierTable(EObject element)
	 * 
	 */
	public boolean isContainedInConstrainingClassifierTable(EObject element) {
		return ((ReferencesTableSettings)constrainingClassifier.getInput()).contains(element);
	}







	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return UmlMessages.ClassifierTemplateParameter_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
