/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.AbstractAdvancedEObjectFlatComboViewer.EObjectFlatComboViewerListener;
import org.eclipse.emf.eef.runtime.ui.widgets.AdvancedEObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EMFComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.LinkEReferenceViewer;
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
import org.eclipse.swt.widgets.Text;
import org.obeonetwork.dsl.uml2.properties.uml.components.StereotypeApplicationsPropertiesEditionComponent.SAPropertyContext;
import org.obeonetwork.dsl.uml2.properties.uml.components.StereotypeApplicationsPropertiesEditionComponent.StereotypeApplication.StereotypeApplicationProperty;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.SAPropertyPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.providers.CustomUmlMessages;
import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;

/**
 * @author <a href="mailto:cedric.notot@obeo.fr">Cédric Notot</a>
 */
public class SAPropertyPropertiesEditionPartImpl extends
		CompositePropertiesEditionPart implements ISWTPropertiesEditionPart,
		SAPropertyPropertiesEditionPart {

	private StereotypeApplicationProperty property;
	private KindValue kind = null;

	private Text textValue;
	// private FeatureEditorDialog manyTextValue;
	private LinkEReferenceViewer singleContainmentValue;
	private ViewerFilter singleContainmentValueFilter;
	private AdvancedEObjectFlatComboViewer singleReferenceValue;
	private ViewerFilter singleReferenceValueFilter;
	private ReferencesTable manyReferenceOrContainmentValue;
	private List<ViewerFilter> manyReferenceOrContainmentValueBusinessFilters = new ArrayList<ViewerFilter>();
	private List<ViewerFilter> manyReferenceOrContainmentValueFilters = new ArrayList<ViewerFilter>();
	protected EMFComboViewer enumValue;
	private Button buttonValue;

	/**
	 * Constructor.
	 * 
	 * @param editionComponent
	 */
	public SAPropertyPropertiesEditionPartImpl(
			IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
		PropertiesEditingContext context = propertiesEditionComponent
				.getEditingContext().getParentContext();
		if (context instanceof SAPropertyContext) {
			property = ((SAPropertyContext) context)
					.getStereotypeApplicationProperty();
			setKindValue(property);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#createFigure(org.eclipse.swt.widgets.Composite)
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
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#createControls(org.eclipse.swt.widgets.Composite)
	 */
	public void createControls(Composite view) {
		CompositionSequence attributesStep = new BindingCompositionSequence(
				propertiesEditionComponent);
		attributesStep
				.addStep(CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty);

		composer = new PartComposer(attributesStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty) {
					return createPropertyField(parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}

	/**
	 * @param parent
	 * @return
	 */
	protected Composite createPropertyField(Composite parent) {
		if (getKindValue() == KindValue.BooleanField) {
			return createCheckbox(parent);
		} else if (getKindValue() == KindValue.EnumField) {
			return createEnumEMFComboViewer(parent);
		} else if (getKindValue() == KindValue.StringField) {
			return createTextValue(parent);
		} else if (getKindValue() == KindValue.SingleReferenceField) {
			return createSingleReferenceValueAdvancedFlatComboViewer(parent);
		} else if (getKindValue() == KindValue.ManyReferenceField
				|| getKindValue() == KindValue.ManyContainmentField) {
			return createManyReferenceValueAdvancedReferencesTable(parent);
		} else if (getKindValue() == KindValue.SingleContainmentField) {
			return createSingleContainmentValueLinkEReferenceViewer(parent);
		}/*
		 * else if (getKindValue() == KindValue.ManyStringField) { return
		 * createManyTextValue(parent); }
		 */
		return parent;
	}

	// TODO
	// protected Composite createManyTextValue(Composite parent) {
	// createDescription(
	// parent,
	// CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
	// property.getFeature().getName());
	// manyTextValue = new FeatureEditorDialog(
	// parent.getShell(),
	// new UMLItemDelegator(adapterFactory, null),
	// property.getContainer(),
	// property.getFeature().getEType(),
	// (List<?>)property.getValue(),
	// property.getFeature().getName(),
	// null,
	// true,
	// false,
	// property.getFeature().isUnique());
	//
	// manyTextValue.create();
	// return parent;
	// }

	/**
	 * @param parent
	 * @return
	 */
	protected Composite createTextValue(Composite parent) {
		createDescription(
				parent,
				CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
				property.getFeature().getName());
		textValue = SWTUtils.createScrollableText(parent, SWT.BORDER);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		textValue.setLayoutData(nameData);
		textValue.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									SAPropertyPropertiesEditionPartImpl.this,
									CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
									PropertiesEditionEvent.COMMIT,
									PropertiesEditionEvent.SET, null, textValue
											.getText()));
			}

		});
		textValue.addKeyListener(new KeyAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent
								.firePropertiesChanged(new PropertiesEditionEvent(
										SAPropertyPropertiesEditionPartImpl.this,
										CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
										PropertiesEditionEvent.COMMIT,
										PropertiesEditionEvent.SET, null,
										textValue.getText()));
				}
			}

		});
		EditingUtils.setID(textValue, UmlViewsRepository.General.name);
		EditingUtils.setEEFtype(textValue, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(
				parent,
				propertiesEditionComponent
						.getHelpContent(
								CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
								UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent
	 * @return
	 */
	protected Composite createSingleContainmentValueLinkEReferenceViewer(
			Composite parent) {
		createDescription(
				parent,
				CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
				property.getFeature().getName());
		// create callback listener
		EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener() {
			public void handleSet(EObject element) {
				propertiesEditionComponent
						.firePropertiesChanged(new PropertiesEditionEvent(
								SAPropertyPropertiesEditionPartImpl.this,
								CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
								PropertiesEditionEvent.COMMIT,
								PropertiesEditionEvent.SET, null, element));
			}

			public void navigateTo(EObject element) {
			}

			public EObject handleCreate() {
				propertiesEditionComponent
						.firePropertiesChanged(new PropertiesEditionEvent(
								SAPropertyPropertiesEditionPartImpl.this,
								CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
								PropertiesEditionEvent.COMMIT,
								PropertiesEditionEvent.ADD, null, null));
				singleContainmentValue.setInput(property.getValue());
				singleContainmentValue.setSelection((EObject) property
						.getValue());
				return getContainmentValue();
			}

			public void handleEdit(EObject element) {
				editValue(element);
				singleContainmentValue.setInput(element);
			}
		};
		// create widget
		singleContainmentValue = new LinkEReferenceViewer(
				getDescription(
						CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
						property.getFeature().getName()), resourceSet,
				singleContainmentValueFilter, propertiesEditionComponent
						.getEditingContext().getAdapterFactory(), listener) {
			@Override
			protected void createButton() {
				super.createButton();
				browseButton.setVisible(false);
			}
		};
		singleContainmentValue.createControls(parent);
		GridData defaultValueData = new GridData(GridData.FILL_HORIZONTAL);
		singleContainmentValue.setLayoutData(defaultValueData);
		singleContainmentValue
				.setID(CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty);
		SWTUtils.createHelpButton(
				parent,
				propertiesEditionComponent
						.getHelpContent(
								CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
								UmlViewsRepository.SWT_KIND), null);
		singleContainmentValue.setButtonMode(ButtonsModeEnum.CREATE);
		return parent;
	}

	/**
	 * @param parent
	 * @return
	 */
	protected Composite createSingleReferenceValueAdvancedFlatComboViewer(
			Composite parent) {
		createDescription(
				parent,
				CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
				property.getFeature().getName());
		// create callback listener
		EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener() {
			public void handleSet(EObject element) {
				propertiesEditionComponent
						.firePropertiesChanged(new PropertiesEditionEvent(
								SAPropertyPropertiesEditionPartImpl.this,
								CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
								PropertiesEditionEvent.COMMIT,
								PropertiesEditionEvent.SET, null, element));
			}

			public void navigateTo(EObject element) {
			}

			public EObject handleCreate() {
				propertiesEditionComponent
						.firePropertiesChanged(new PropertiesEditionEvent(
								SAPropertyPropertiesEditionPartImpl.this,
								CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
								PropertiesEditionEvent.COMMIT,
								PropertiesEditionEvent.ADD, null, null));
				return getReferenceValue();
			}

			public void handleEdit(EObject element) {
				propertiesEditionComponent
						.firePropertiesChanged(new PropertiesEditionEvent(
								SAPropertyPropertiesEditionPartImpl.this,
								CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
								PropertiesEditionEvent.COMMIT,
								PropertiesEditionEvent.EDIT, null, element));
			}
		};
		// create widget
		singleReferenceValue = new AdvancedEObjectFlatComboViewer(
				getDescription(
						CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
						property.getFeature().getName()), resourceSet,
				singleReferenceValueFilter, propertiesEditionComponent
						.getEditingContext().getAdapterFactory(), listener);
		singleReferenceValue.createControls(parent);
		GridData typeData = new GridData(GridData.FILL_HORIZONTAL);
		singleReferenceValue.setLayoutData(typeData);
		singleReferenceValue
				.setID(CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty);
		SWTUtils.createHelpButton(
				parent,
				propertiesEditionComponent
						.getHelpContent(
								CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
								UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @return
	 */
	public EObject getReferenceValue() {
		return singleReferenceValue.getSelection();
	}

	/**
	 * @return
	 */
	public EObject getContainmentValue() {
		return singleContainmentValue.getSelection();
	}

	/**
	 * @param parent
	 * @return
	 */
	protected Composite createManyReferenceValueAdvancedReferencesTable(
			Composite parent) {
		String label = getDescription(
				CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
				property.getFeature().getName());
		this.manyReferenceOrContainmentValue = new ReferencesTable(label,
				new ReferencesTableListener() {
					public void handleAdd() {
						if (getKindValue() == KindValue.ManyReferenceField) {
							addReferenceValue();
						} else {
							propertiesEditionComponent
									.firePropertiesChanged(new PropertiesEditionEvent(
											SAPropertyPropertiesEditionPartImpl.this,
											CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
											PropertiesEditionEvent.COMMIT,
											PropertiesEditionEvent.ADD, null,
											null));
							manyReferenceOrContainmentValue.refresh();
						}
					}

					public void handleEdit(EObject element) {
						editValue(element);
						manyReferenceOrContainmentValue.refresh();
					}

					public void handleMove(EObject element, int oldIndex,
							int newIndex) {
						moveReferenceValue(element, oldIndex, newIndex);
					}

					public void handleRemove(EObject element) {
						removeReferenceValue(element);
					}

					public void navigateTo(EObject element) {
					}
				});
		for (ViewerFilter filter : this.manyReferenceOrContainmentValueFilters) {
			this.manyReferenceOrContainmentValue.addFilter(filter);
		}
		this.manyReferenceOrContainmentValue
				.setHelpText(propertiesEditionComponent
						.getHelpContent(
								CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
								UmlViewsRepository.SWT_KIND));
		this.manyReferenceOrContainmentValue.createControls(parent);
		this.manyReferenceOrContainmentValue
				.addSelectionListener(new SelectionAdapter() {

					public void widgetSelected(SelectionEvent e) {
						if (e.item != null
								&& e.item.getData() instanceof EObject) {
							propertiesEditionComponent
									.firePropertiesChanged(new PropertiesEditionEvent(
											SAPropertyPropertiesEditionPartImpl.this,
											CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
											PropertiesEditionEvent.CHANGE,
											PropertiesEditionEvent.SELECTION_CHANGED,
											null, e.item.getData()));
						}
					}

				});
		GridData usecaseData = new GridData(GridData.FILL_HORIZONTAL);
		usecaseData.horizontalSpan = 3;
		this.manyReferenceOrContainmentValue.setLayoutData(usecaseData);
		this.manyReferenceOrContainmentValue.disableMove();
		manyReferenceOrContainmentValue
				.setID(CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty);
		manyReferenceOrContainmentValue
				.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	protected Composite createEnumEMFComboViewer(Composite parent) {
		createDescription(
				parent,
				CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
				property.getFeature().getName());
		enumValue = new EMFComboViewer(parent);
		enumValue.setContentProvider(new ArrayContentProvider());
		enumValue.setLabelProvider(new AdapterFactoryLabelProvider(
				EEFRuntimePlugin.getDefault().getAdapterFactory()));
		GridData enumValueData = new GridData(GridData.FILL_HORIZONTAL);
		enumValue.getCombo().setLayoutData(enumValueData);
		enumValue.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 * @generated
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)

					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									SAPropertyPropertiesEditionPartImpl.this,
									CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
									PropertiesEditionEvent.COMMIT,
									PropertiesEditionEvent.SET, null,
									getEnumValue()));
			}

		});

		enumValue
				.setID(CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty);
		SWTUtils.createHelpButton(
				parent,
				propertiesEditionComponent
						.getHelpContent(
								CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
								UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		// Start of user code for createVisibilityEMFComboViewer

		// End of user code
		return parent;
	}

	protected Composite createCheckbox(Composite parent) {
		buttonValue = new Button(parent, SWT.CHECK | SWT.FLAT);
		if (getDescription(
				CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
				property.getFeature().getName()) != null)
			buttonValue
					.setText(getDescription(
							CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
							property.getFeature().getName()));

		buttonValue.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 * @generated
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									SAPropertyPropertiesEditionPartImpl.this,
									CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
									PropertiesEditionEvent.COMMIT,
									PropertiesEditionEvent.SET, null,
									new Boolean(buttonValue.getSelection())));
			}

		});
		GridData orderedData = new GridData(GridData.FILL_HORIZONTAL);
		orderedData.horizontalSpan = 2;
		buttonValue.setLayoutData(orderedData);
		EditingUtils.setID(buttonValue,
				UmlViewsRepository.General.Qualifiers.ordered);
		EditingUtils.setEEFtype(buttonValue, "eef::Checkbox"); //$NON-NLS-1$
		SWTUtils.createHelpButton(
				parent,
				propertiesEditionComponent
						.getHelpContent(
								CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
								UmlViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		// Start of user code for createOrderedCheckbox

		// End of user code
		return parent;
	}

	protected Object getEnumValue() {
		Enumerator selection = (Enumerator) ((StructuredSelection) enumValue
				.getSelection()).getFirstElement();
		return selection;
	}

	/**
	 * 
	 */
	protected void addReferenceValue() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(
				manyReferenceOrContainmentValue.getInput(),
				manyReferenceOrContainmentValueFilters,
				manyReferenceOrContainmentValueBusinessFilters, property
						.getFeature().getName(), propertiesEditionComponent
						.getEditingContext().getAdapterFactory(),
				current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									SAPropertyPropertiesEditionPartImpl.this,
									CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
									PropertiesEditionEvent.COMMIT,
									PropertiesEditionEvent.ADD, null, elem));
				}
				manyReferenceOrContainmentValue.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * @param element
	 * @param oldIndex
	 * @param newIndex
	 */
	protected void moveReferenceValue(EObject element, int oldIndex,
			int newIndex) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						SAPropertyPropertiesEditionPartImpl.this,
						CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.MOVE, element, newIndex));
		manyReferenceOrContainmentValue.refresh();
	}

	/**
	 * @param element
	 */
	protected void removeReferenceValue(EObject element) {
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						SAPropertyPropertiesEditionPartImpl.this,
						CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.REMOVE, null, element));
		manyReferenceOrContainmentValue.refresh();
	}

	/**
	 * @param element
	 */
	protected void editValue(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(
				propertiesEditionComponent.getEditingContext(),
				propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider) adapterFactory
				.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 */
	public String getTitle() {
		return CustomUmlMessages.StereotypeApplicationProperty_Part_Title;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.SAPropertyPropertiesEditionPart#setValue(java.lang.Object)
	 */
	public void setValue(Object value) {
		if (getKindValue() == KindValue.BooleanField) {
			if (value != null) {
				buttonValue.setSelection(((Boolean) value).booleanValue());
			} else {
				buttonValue.setSelection(false);
			}
			boolean eefElementEditorReadOnlyState = isReadOnly(CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty);
			if (eefElementEditorReadOnlyState && buttonValue.isEnabled()) {
				buttonValue.setEnabled(false);
				buttonValue.setToolTipText(UmlMessages.General_ReadOnly);
			} else if (!eefElementEditorReadOnlyState
					&& !buttonValue.isEnabled()) {
				buttonValue.setEnabled(true);
			}
		} else if (getKindValue() == KindValue.EnumField) {
			enumValue.setInput((Object) ((EEnumLiteral) property.getValue())
					.getEEnum().getELiterals());
			enumValue.modelUpdating(new StructuredSelection(value));
			boolean eefElementEditorReadOnlyState = isReadOnly(CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty);
			if (eefElementEditorReadOnlyState && enumValue.isEnabled()) {
				enumValue.setEnabled(false);
				enumValue.setToolTipText(UmlMessages.General_ReadOnly);
			} else if (!eefElementEditorReadOnlyState && !enumValue.isEnabled()) {
				enumValue.setEnabled(true);
			}
		} else if (getKindValue() == KindValue.SingleReferenceField) {
			EObjectFlatComboSettings referenceSettings = new EObjectFlatComboSettings(
					property.getContainer(), (EReference) property.getFeature());
			singleReferenceValue.setInput(referenceSettings);
			if (current != null) {
				singleReferenceValue.setSelection(new StructuredSelection(
						referenceSettings.getValue()));
			}
			boolean eefElementEditorReadOnlyState = isReadOnly(CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty);
			if (eefElementEditorReadOnlyState
					&& singleReferenceValue.isEnabled()) {
				singleReferenceValue.setEnabled(false);
				singleReferenceValue
						.setToolTipText(UmlMessages.General_ReadOnly);
			} else if (!eefElementEditorReadOnlyState
					&& !singleReferenceValue.isEnabled()) {
				singleReferenceValue.setEnabled(true);
			}
		} else if (getKindValue() == KindValue.ManyReferenceField
				|| getKindValue() == KindValue.ManyContainmentField) {
			if (current.eResource() != null
					&& current.eResource().getResourceSet() != null)
				this.resourceSet = current.eResource().getResourceSet();
			ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
			manyReferenceOrContainmentValue.setContentProvider(contentProvider);
			ReferencesTableSettings settings = new ReferencesTableSettings(
					property.getContainer(), (EReference) property.getFeature());
			manyReferenceOrContainmentValue.setInput(settings);
			boolean eefElementEditorReadOnlyState = isReadOnly(CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty);
			if (eefElementEditorReadOnlyState
					&& manyReferenceOrContainmentValue.getTable().isEnabled()) {
				manyReferenceOrContainmentValue.setEnabled(false);
				manyReferenceOrContainmentValue
						.setToolTipText(UmlMessages.General_ReadOnly);
			} else if (!eefElementEditorReadOnlyState
					&& !manyReferenceOrContainmentValue.getTable().isEnabled()) {
				manyReferenceOrContainmentValue.setEnabled(true);
			}
		} else if (getKindValue() == KindValue.SingleContainmentField) {
			EObjectFlatComboSettings settings = new EObjectFlatComboSettings(
					property.getContainer(), (EReference) property.getFeature());
			singleContainmentValue.setInput(settings);
			if (current != null) {
				singleContainmentValue.setSelection(new StructuredSelection(
						settings.getValue()));
			}
			boolean eefElementEditorReadOnlyState = isReadOnly(CustomUmlViewsRepository.SAProperty.stereotypeApplicationProperty);
			if (eefElementEditorReadOnlyState
					&& singleContainmentValue.isEnabled()) {
				singleContainmentValue.setEnabled(false);
				singleContainmentValue
						.setToolTipText(UmlMessages.General_ReadOnly);
			} else if (!eefElementEditorReadOnlyState
					&& !singleContainmentValue.isEnabled()) {
				singleContainmentValue.setEnabled(true);
			}
		} else {
			if (value != null) {
				// TODO: Use a multi evaluated widget instead of it.
				if (value instanceof List) {
					String result = "";
					for (Object val : (List<Object>) value) {
						result += (" " + getStringValue(val));
					}
					result = result.replaceFirst(" ", "");
					textValue.setText(result);
				} else {
					textValue.setText(getStringValue(value));
				}
			} else {
				textValue.setText(""); //$NON-NLS-1$
			}
		}
	}

	/**
	 * @param object
	 * @return
	 */
	private String getStringValue(Object object) {
		return object.toString();
	}

	/**
	 * @param property
	 */
	private void setKindValue(StereotypeApplicationProperty property) {
		EStructuralFeature feature = property.getFeature();
		if (feature instanceof EAttribute
				&& property.getValue() instanceof EEnumLiteral) {
			kind = KindValue.EnumField;
		} else if (feature instanceof EAttribute
				&& property.getValue() instanceof Boolean) {
			kind = KindValue.BooleanField;
		} else if (feature instanceof EAttribute) {
			kind = KindValue.StringField;
		} else if (feature instanceof EReference && !feature.isMany()) {
			if (!((EReference) feature).isContainment()) {
				kind = KindValue.SingleReferenceField;
			} else {
				kind = KindValue.SingleContainmentField;
			}
		} else if (feature instanceof EReference && feature.isMany()) {
			if (!((EReference) feature).isContainment()) {
				kind = KindValue.ManyReferenceField;
			} else {
				kind = KindValue.ManyContainmentField;
			}
		}
	}

	/**
	 * @return
	 */
	private KindValue getKindValue() {
		return kind;
	}

	/**
	 * @author <a href="mailto:cedric.notot@obeo.fr">Cédric Notot</a>
	 *
	 */
	private enum KindValue {
		StringField, /* ManyStringField, */SingleReferenceField, ManyReferenceField, SingleContainmentField, ManyContainmentField, EnumField, BooleanField
	}
}
