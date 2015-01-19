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

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;

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

import org.eclipse.emf.eef.runtime.ui.providers.EMFListContentProvider;

import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.AbstractAdvancedEObjectFlatComboViewer.EObjectFlatComboViewerListener;

import org.eclipse.emf.eef.runtime.ui.widgets.AdvancedEObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EMFComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.HorizontalBox;
import org.eclipse.emf.eef.runtime.ui.widgets.LinkEReferenceViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.SingleCompositionEditor;

import org.eclipse.emf.eef.runtime.ui.widgets.SingleCompositionEditor.SingleCompositionListener;

import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.emf.eef.runtime.ui.widgets.imageviewer.EEFImageViewer;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import org.obeonetwork.dsl.uml2.properties.uml.providers.UmlMessages;


/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class GeneralPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, GeneralPropertiesEditionPart {

	protected Text name;
	protected EMFComboViewer visibility;
	protected Button ordered;
	protected Button abstract_;
	protected Button leaf;
	protected Button static_;
	protected Button unique;
	protected Button query;
	protected Button readOnly;
	protected Button derived;
	protected Button derivedUnion;
	protected Button substitutable;
	protected Button active;
	protected Button behavior;
	protected Button service;
	protected Button reentrant;
	protected Button indirectlyInstantiated;
	private AdvancedEObjectFlatComboViewer type;
	protected ViewerFilter typeFilter;
	protected EMFComboViewer direction;
	protected EMFComboViewer aggregation;
	protected Text lowerValue;
	protected Text upperValue;
	private LinkEReferenceViewer defaultValue;
	protected ViewerFilter defaultValueFilter;
	protected ReferencesTable memberEnd;
	protected List<ViewerFilter> memberEndBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> memberEndFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable supplier;
	protected List<ViewerFilter> supplierBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> supplierFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable client;
	protected List<ViewerFilter> clientBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> clientFilters = new ArrayList<ViewerFilter>();
	protected EMFComboViewer kind;
	protected EMFComboViewer kind_readonly;
	protected ReferencesTable trigger;
	protected List<ViewerFilter> triggerBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> triggerFilters = new ArrayList<ViewerFilter>();
	private SingleCompositionEditor effect;
	private LinkEReferenceViewer guard;
	protected ViewerFilter guardFilter;
	private AdvancedEObjectFlatComboViewer source;
	protected ViewerFilter sourceFilter;
	private AdvancedEObjectFlatComboViewer target;
	protected ViewerFilter targetFilter;
	protected ReferencesTable ownedRule;
	protected List<ViewerFilter> ownedRuleBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> ownedRuleFilters = new ArrayList<ViewerFilter>();
	protected EEFImageViewer icon;
	protected Text body;
	protected EMFComboViewer extendedCase;
	protected EMFComboViewer addition;
	private AdvancedEObjectFlatComboViewer role;
	protected ViewerFilter roleFilter;
	protected ReferencesTable usecase;
	protected List<ViewerFilter> usecaseBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> usecaseFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable subjects;
	protected List<ViewerFilter> subjectsBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> subjectsFilters = new ArrayList<ViewerFilter>();
	private AdvancedEObjectFlatComboViewer entry;
	protected ViewerFilter entryFilter;
	private AdvancedEObjectFlatComboViewer exit;
	protected ViewerFilter exitFilter;
	private AdvancedEObjectFlatComboViewer do_;
	protected ViewerFilter do_Filter;
	private LinkEReferenceViewer submachine;
	protected ViewerFilter submachineFilter;
	private SingleCompositionEditor specification;
	private LinkEReferenceViewer instanceValue;
	protected ViewerFilter instanceValueFilter;
	private LinkEReferenceViewer min;
	protected ViewerFilter minFilter;
	private LinkEReferenceViewer instance;
	protected ViewerFilter instanceFilter;
	private LinkEReferenceViewer max;
	protected ViewerFilter maxFilter;
	private LinkEReferenceViewer event;
	protected ViewerFilter eventFilter;
	private SingleCompositionEditor when;
	private SingleCompositionEditor changeExpression;
	private SingleCompositionEditor region;
	private AdvancedEObjectFlatComboViewer behaviour;
	protected ViewerFilter behaviourFilter;
	protected Button unmarshall;
	protected EObjectFlatComboViewer operation;
	protected EObjectFlatComboViewer signal;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * @generated
	 */
	public GeneralPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
    CompositionSequence generalStep = new BindingCompositionSequence(propertiesEditionComponent);
    generalStep.addStep(UmlViewsRepository.General.name);
    generalStep.addStep(UmlViewsRepository.General.visibility);
    CompositionStep qualifiersStep = generalStep.addStep(UmlViewsRepository.General.Qualifiers.class);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.ordered);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.abstract_);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.leaf);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.static_);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.unique);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.query);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.readOnly);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.derived);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.derivedUnion);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.substitutable);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.active);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.behavior);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.service);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.reentrant);
    qualifiersStep.addStep(UmlViewsRepository.General.Qualifiers.indirectlyInstantiated);
    
    generalStep.addStep(UmlViewsRepository.General.type);
    generalStep.addStep(UmlViewsRepository.General.direction);
    generalStep.addStep(UmlViewsRepository.General.aggregation);
    generalStep.addStep(UmlViewsRepository.General.lowerValue);
    generalStep.addStep(UmlViewsRepository.General.upperValue);
    generalStep.addStep(UmlViewsRepository.General.defaultValue);
    generalStep.addStep(UmlViewsRepository.General.memberEnd);
    generalStep.addStep(UmlViewsRepository.General.supplier);
    generalStep.addStep(UmlViewsRepository.General.client);
    generalStep.addStep(UmlViewsRepository.General.kind);
    generalStep.addStep(UmlViewsRepository.General.kind_readonly);
    generalStep.addStep(UmlViewsRepository.General.trigger);
    generalStep.addStep(UmlViewsRepository.General.effect);
    generalStep.addStep(UmlViewsRepository.General.guard);
    generalStep.addStep(UmlViewsRepository.General.source);
    generalStep.addStep(UmlViewsRepository.General.target);
    generalStep.addStep(UmlViewsRepository.General.ownedRule);
    generalStep.addStep(UmlViewsRepository.General.icon);
    generalStep.addStep(UmlViewsRepository.General.body);
    generalStep.addStep(UmlViewsRepository.General.extendedCase);
    generalStep.addStep(UmlViewsRepository.General.addition);
    generalStep.addStep(UmlViewsRepository.General.role);
    generalStep.addStep(UmlViewsRepository.General.usecase);
    generalStep.addStep(UmlViewsRepository.General.subjects);
    generalStep.addStep(UmlViewsRepository.General.entry);
    generalStep.addStep(UmlViewsRepository.General.exit);
    generalStep.addStep(UmlViewsRepository.General.do_);
    generalStep.addStep(UmlViewsRepository.General.submachine);
    generalStep.addStep(UmlViewsRepository.General.specification);
    generalStep.addStep(UmlViewsRepository.General.instanceValue);
    generalStep.addStep(UmlViewsRepository.General.min);
    generalStep.addStep(UmlViewsRepository.General.instance);
    generalStep.addStep(UmlViewsRepository.General.max);
    generalStep.addStep(UmlViewsRepository.General.event);
    generalStep.addStep(UmlViewsRepository.General.when);
    generalStep.addStep(UmlViewsRepository.General.changeExpression);
    generalStep.addStep(UmlViewsRepository.General.region);
    generalStep.addStep(UmlViewsRepository.General.behaviour);
    generalStep.addStep(UmlViewsRepository.General.unmarshall);
    generalStep.addStep(UmlViewsRepository.General.operation);
    generalStep.addStep(UmlViewsRepository.General.signal);
    
    composer = new PartComposer(generalStep) {

      @Override
      public Composite addToPart(Composite parent, Object key) {
        if (key == UmlViewsRepository.General.name) {
          return createNameText(parent);
        }
        if (key == UmlViewsRepository.General.visibility) {
          return createVisibilityEMFComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.class) {
          return createQualifiersHBox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.ordered) {
          return createOrderedCheckbox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.abstract_) {
          return createAbstract_Checkbox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.leaf) {
          return createLeafCheckbox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.static_) {
          return createStatic_Checkbox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.unique) {
          return createUniqueCheckbox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.query) {
          return createQueryCheckbox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.readOnly) {
          return createReadOnlyCheckbox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.derived) {
          return createDerivedCheckbox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.derivedUnion) {
          return createDerivedUnionCheckbox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.substitutable) {
          return createSubstitutableCheckbox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.active) {
          return createActiveCheckbox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.behavior) {
          return createBehaviorCheckbox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.service) {
          return createServiceCheckbox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.reentrant) {
          return createReentrantCheckbox(parent);
        }
        if (key == UmlViewsRepository.General.Qualifiers.indirectlyInstantiated) {
          return createIndirectlyInstantiatedCheckbox(parent);
        }
        if (key == UmlViewsRepository.General.type) {
          return createTypeAdvancedFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.direction) {
          return createDirectionEMFComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.aggregation) {
          return createAggregationEMFComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.lowerValue) {
          return createLowerValueText(parent);
        }
        if (key == UmlViewsRepository.General.upperValue) {
          return createUpperValueText(parent);
        }
        if (key == UmlViewsRepository.General.defaultValue) {
          return createDefaultValueLinkEReferenceViewer(parent);
        }
        if (key == UmlViewsRepository.General.memberEnd) {
          return createMemberEndAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.General.supplier) {
          return createSupplierAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.General.client) {
          return createClientAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.General.kind) {
          return createKindEMFComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.kind_readonly) {
          return createKind_readonlyEMFComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.trigger) {
          return createTriggerAdvancedTableComposition(parent);
        }
        if (key == UmlViewsRepository.General.effect) {
          return createEffectSingleCompositionEditor(parent);
        }
        if (key == UmlViewsRepository.General.guard) {
          return createGuardLinkEReferenceViewer(parent);
        }
        if (key == UmlViewsRepository.General.source) {
          return createSourceAdvancedFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.target) {
          return createTargetAdvancedFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.ownedRule) {
          return createOwnedRuleAdvancedTableComposition(parent);
        }
        if (key == UmlViewsRepository.General.icon) {
          return createIconImageViewer(parent);
        }
        if (key == UmlViewsRepository.General.body) {
          return createBodyTextarea(parent);
        }
        if (key == UmlViewsRepository.General.extendedCase) {
          return createExtendedCaseEMFComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.addition) {
          return createAdditionEMFComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.role) {
          return createRoleAdvancedFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.usecase) {
          return createUsecaseAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.General.subjects) {
          return createSubjectsAdvancedReferencesTable(parent);
        }
        if (key == UmlViewsRepository.General.entry) {
          return createEntryAdvancedFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.exit) {
          return createExitAdvancedFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.do_) {
          return createDo_AdvancedFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.submachine) {
          return createSubmachineLinkEReferenceViewer(parent);
        }
        if (key == UmlViewsRepository.General.specification) {
          return createSpecificationSingleCompositionEditor(parent);
        }
        if (key == UmlViewsRepository.General.instanceValue) {
          return createInstanceValueLinkEReferenceViewer(parent);
        }
        if (key == UmlViewsRepository.General.min) {
          return createMinLinkEReferenceViewer(parent);
        }
        if (key == UmlViewsRepository.General.instance) {
          return createInstanceLinkEReferenceViewer(parent);
        }
        if (key == UmlViewsRepository.General.max) {
          return createMaxLinkEReferenceViewer(parent);
        }
        if (key == UmlViewsRepository.General.event) {
          return createEventLinkEReferenceViewer(parent);
        }
        if (key == UmlViewsRepository.General.when) {
          return createWhenSingleCompositionEditor(parent);
        }
        if (key == UmlViewsRepository.General.changeExpression) {
          return createChangeExpressionSingleCompositionEditor(parent);
        }
        if (key == UmlViewsRepository.General.region) {
          return createRegionSingleCompositionEditor(parent);
        }
        if (key == UmlViewsRepository.General.behaviour) {
          return createBehaviourAdvancedFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.unmarshall) {
          return createUnmarshallCheckbox(parent);
        }
        if (key == UmlViewsRepository.General.operation) {
          return createOperationFlatComboViewer(parent);
        }
        if (key == UmlViewsRepository.General.signal) {
          return createSignalFlatComboViewer(parent);
        }
        return parent;
      }
    };
    composer.compose(view);
  }

	/**
	 * @generated
	 */
	
	protected Composite createNameText(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.name, UmlMessages.GeneralPropertiesEditionPart_NameLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
        }
      }

    });
    EditingUtils.setID(name, UmlViewsRepository.General.name);
    EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.name, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createNameText

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createVisibilityEMFComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.visibility, UmlMessages.GeneralPropertiesEditionPart_VisibilityLabel);
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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.visibility, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
      }

    });
    visibility.setID(UmlViewsRepository.General.visibility);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.visibility, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createVisibilityEMFComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createQualifiersHBox(Composite parent) {
    Composite container = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
    gridData.horizontalSpan = 3;
    container.setLayoutData(gridData);
    HorizontalBox qualifiersHBox = new HorizontalBox(container);
    //Apply constraint for checkbox
    GridData constraint = new GridData(GridData.FILL_HORIZONTAL);
    constraint.horizontalAlignment = GridData.BEGINNING;
    qualifiersHBox.setLayoutData(constraint);
    return qualifiersHBox;
  }

	/**
	 * @generated
	 */
	
	protected Composite createOrderedCheckbox(Composite parent) {
    ordered = new Button(parent, SWT.CHECK);
    ordered.setText(getDescription(UmlViewsRepository.General.Qualifiers.ordered, UmlMessages.GeneralPropertiesEditionPart_OrderedLabel));
    ordered.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.ordered, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(ordered.getSelection())));
      }

    });
    GridData orderedData = new GridData(GridData.FILL_HORIZONTAL);
    orderedData.horizontalSpan = 2;
    ordered.setLayoutData(orderedData);
    EditingUtils.setID(ordered, UmlViewsRepository.General.Qualifiers.ordered);
    EditingUtils.setEEFtype(ordered, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.ordered, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createOrderedCheckbox

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createAbstract_Checkbox(Composite parent) {
    abstract_ = new Button(parent, SWT.CHECK);
    abstract_.setText(getDescription(UmlViewsRepository.General.Qualifiers.abstract_, UmlMessages.GeneralPropertiesEditionPart_Abstract_Label));
    abstract_.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.abstract_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(abstract_.getSelection())));
      }

    });
    GridData abstract_Data = new GridData(GridData.FILL_HORIZONTAL);
    abstract_Data.horizontalSpan = 2;
    abstract_.setLayoutData(abstract_Data);
    EditingUtils.setID(abstract_, UmlViewsRepository.General.Qualifiers.abstract_);
    EditingUtils.setEEFtype(abstract_, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.abstract_, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createAbstract_Checkbox

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createLeafCheckbox(Composite parent) {
    leaf = new Button(parent, SWT.CHECK);
    leaf.setText(getDescription(UmlViewsRepository.General.Qualifiers.leaf, UmlMessages.GeneralPropertiesEditionPart_LeafLabel));
    leaf.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.leaf, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(leaf.getSelection())));
      }

    });
    GridData leafData = new GridData(GridData.FILL_HORIZONTAL);
    leafData.horizontalSpan = 2;
    leaf.setLayoutData(leafData);
    EditingUtils.setID(leaf, UmlViewsRepository.General.Qualifiers.leaf);
    EditingUtils.setEEFtype(leaf, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.leaf, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createLeafCheckbox

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createStatic_Checkbox(Composite parent) {
    static_ = new Button(parent, SWT.CHECK);
    static_.setText(getDescription(UmlViewsRepository.General.Qualifiers.static_, UmlMessages.GeneralPropertiesEditionPart_Static_Label));
    static_.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.static_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(static_.getSelection())));
      }

    });
    GridData static_Data = new GridData(GridData.FILL_HORIZONTAL);
    static_Data.horizontalSpan = 2;
    static_.setLayoutData(static_Data);
    EditingUtils.setID(static_, UmlViewsRepository.General.Qualifiers.static_);
    EditingUtils.setEEFtype(static_, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.static_, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createStatic_Checkbox

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createUniqueCheckbox(Composite parent) {
    unique = new Button(parent, SWT.CHECK);
    unique.setText(getDescription(UmlViewsRepository.General.Qualifiers.unique, UmlMessages.GeneralPropertiesEditionPart_UniqueLabel));
    unique.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.unique, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(unique.getSelection())));
      }

    });
    GridData uniqueData = new GridData(GridData.FILL_HORIZONTAL);
    uniqueData.horizontalSpan = 2;
    unique.setLayoutData(uniqueData);
    EditingUtils.setID(unique, UmlViewsRepository.General.Qualifiers.unique);
    EditingUtils.setEEFtype(unique, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.unique, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createUniqueCheckbox

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createQueryCheckbox(Composite parent) {
    query = new Button(parent, SWT.CHECK);
    query.setText(getDescription(UmlViewsRepository.General.Qualifiers.query, UmlMessages.GeneralPropertiesEditionPart_QueryLabel));
    query.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.query, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(query.getSelection())));
      }

    });
    GridData queryData = new GridData(GridData.FILL_HORIZONTAL);
    queryData.horizontalSpan = 2;
    query.setLayoutData(queryData);
    EditingUtils.setID(query, UmlViewsRepository.General.Qualifiers.query);
    EditingUtils.setEEFtype(query, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.query, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createQueryCheckbox

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createReadOnlyCheckbox(Composite parent) {
    readOnly = new Button(parent, SWT.CHECK);
    readOnly.setText(getDescription(UmlViewsRepository.General.Qualifiers.readOnly, UmlMessages.GeneralPropertiesEditionPart_ReadOnlyLabel));
    readOnly.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.readOnly, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(readOnly.getSelection())));
      }

    });
    GridData readOnlyData = new GridData(GridData.FILL_HORIZONTAL);
    readOnlyData.horizontalSpan = 2;
    readOnly.setLayoutData(readOnlyData);
    EditingUtils.setID(readOnly, UmlViewsRepository.General.Qualifiers.readOnly);
    EditingUtils.setEEFtype(readOnly, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.readOnly, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createReadOnlyCheckbox

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createDerivedCheckbox(Composite parent) {
    derived = new Button(parent, SWT.CHECK);
    derived.setText(getDescription(UmlViewsRepository.General.Qualifiers.derived, UmlMessages.GeneralPropertiesEditionPart_DerivedLabel));
    derived.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.derived, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(derived.getSelection())));
      }

    });
    GridData derivedData = new GridData(GridData.FILL_HORIZONTAL);
    derivedData.horizontalSpan = 2;
    derived.setLayoutData(derivedData);
    EditingUtils.setID(derived, UmlViewsRepository.General.Qualifiers.derived);
    EditingUtils.setEEFtype(derived, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.derived, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createDerivedCheckbox

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createDerivedUnionCheckbox(Composite parent) {
    derivedUnion = new Button(parent, SWT.CHECK);
    derivedUnion.setText(getDescription(UmlViewsRepository.General.Qualifiers.derivedUnion, UmlMessages.GeneralPropertiesEditionPart_DerivedUnionLabel));
    derivedUnion.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.derivedUnion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(derivedUnion.getSelection())));
      }

    });
    GridData derivedUnionData = new GridData(GridData.FILL_HORIZONTAL);
    derivedUnionData.horizontalSpan = 2;
    derivedUnion.setLayoutData(derivedUnionData);
    EditingUtils.setID(derivedUnion, UmlViewsRepository.General.Qualifiers.derivedUnion);
    EditingUtils.setEEFtype(derivedUnion, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.derivedUnion, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createDerivedUnionCheckbox

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createSubstitutableCheckbox(Composite parent) {
    substitutable = new Button(parent, SWT.CHECK);
    substitutable.setText(getDescription(UmlViewsRepository.General.Qualifiers.substitutable, UmlMessages.GeneralPropertiesEditionPart_SubstitutableLabel));
    substitutable.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.substitutable, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(substitutable.getSelection())));
      }

    });
    GridData substitutableData = new GridData(GridData.FILL_HORIZONTAL);
    substitutableData.horizontalSpan = 2;
    substitutable.setLayoutData(substitutableData);
    EditingUtils.setID(substitutable, UmlViewsRepository.General.Qualifiers.substitutable);
    EditingUtils.setEEFtype(substitutable, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.substitutable, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createSubstitutableCheckbox

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createActiveCheckbox(Composite parent) {
    active = new Button(parent, SWT.CHECK);
    active.setText(getDescription(UmlViewsRepository.General.Qualifiers.active, UmlMessages.GeneralPropertiesEditionPart_ActiveLabel));
    active.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.active, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(active.getSelection())));
      }

    });
    GridData activeData = new GridData(GridData.FILL_HORIZONTAL);
    activeData.horizontalSpan = 2;
    active.setLayoutData(activeData);
    EditingUtils.setID(active, UmlViewsRepository.General.Qualifiers.active);
    EditingUtils.setEEFtype(active, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.active, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createActiveCheckbox

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createBehaviorCheckbox(Composite parent) {
    behavior = new Button(parent, SWT.CHECK);
    behavior.setText(getDescription(UmlViewsRepository.General.Qualifiers.behavior, UmlMessages.GeneralPropertiesEditionPart_BehaviorLabel));
    behavior.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.behavior, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(behavior.getSelection())));
      }

    });
    GridData behaviorData = new GridData(GridData.FILL_HORIZONTAL);
    behaviorData.horizontalSpan = 2;
    behavior.setLayoutData(behaviorData);
    EditingUtils.setID(behavior, UmlViewsRepository.General.Qualifiers.behavior);
    EditingUtils.setEEFtype(behavior, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.behavior, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createBehaviorCheckbox

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createServiceCheckbox(Composite parent) {
    service = new Button(parent, SWT.CHECK);
    service.setText(getDescription(UmlViewsRepository.General.Qualifiers.service, UmlMessages.GeneralPropertiesEditionPart_ServiceLabel));
    service.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.service, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(service.getSelection())));
      }

    });
    GridData serviceData = new GridData(GridData.FILL_HORIZONTAL);
    serviceData.horizontalSpan = 2;
    service.setLayoutData(serviceData);
    EditingUtils.setID(service, UmlViewsRepository.General.Qualifiers.service);
    EditingUtils.setEEFtype(service, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.service, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createServiceCheckbox

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createReentrantCheckbox(Composite parent) {
    reentrant = new Button(parent, SWT.CHECK);
    reentrant.setText(getDescription(UmlViewsRepository.General.Qualifiers.reentrant, UmlMessages.GeneralPropertiesEditionPart_ReentrantLabel));
    reentrant.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.reentrant, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(reentrant.getSelection())));
      }

    });
    GridData reentrantData = new GridData(GridData.FILL_HORIZONTAL);
    reentrantData.horizontalSpan = 2;
    reentrant.setLayoutData(reentrantData);
    EditingUtils.setID(reentrant, UmlViewsRepository.General.Qualifiers.reentrant);
    EditingUtils.setEEFtype(reentrant, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.reentrant, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createReentrantCheckbox

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createIndirectlyInstantiatedCheckbox(Composite parent) {
    indirectlyInstantiated = new Button(parent, SWT.CHECK);
    indirectlyInstantiated.setText(getDescription(UmlViewsRepository.General.Qualifiers.indirectlyInstantiated, UmlMessages.GeneralPropertiesEditionPart_IndirectlyInstantiatedLabel));
    indirectlyInstantiated.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.Qualifiers.indirectlyInstantiated, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(indirectlyInstantiated.getSelection())));
      }

    });
    GridData indirectlyInstantiatedData = new GridData(GridData.FILL_HORIZONTAL);
    indirectlyInstantiatedData.horizontalSpan = 2;
    indirectlyInstantiated.setLayoutData(indirectlyInstantiatedData);
    EditingUtils.setID(indirectlyInstantiated, UmlViewsRepository.General.Qualifiers.indirectlyInstantiated);
    EditingUtils.setEEFtype(indirectlyInstantiated, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.Qualifiers.indirectlyInstantiated, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createIndirectlyInstantiatedCheckbox

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createTypeAdvancedFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.type, UmlMessages.GeneralPropertiesEditionPart_TypeLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.type, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.type, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getType();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.type, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    type = new AdvancedEObjectFlatComboViewer(getDescription(UmlViewsRepository.General.type, UmlMessages.GeneralPropertiesEditionPart_TypeLabel), resourceSet, typeFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    type.createControls(parent);
    GridData typeData = new GridData(GridData.FILL_HORIZONTAL);
    type.setLayoutData(typeData);
    type.setID(UmlViewsRepository.General.type);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.type, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createTypeAdvancedFlatComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createDirectionEMFComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.direction, UmlMessages.GeneralPropertiesEditionPart_DirectionLabel);
    direction = new EMFComboViewer(parent);
    direction.setContentProvider(new ArrayContentProvider());
    direction.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
    GridData directionData = new GridData(GridData.FILL_HORIZONTAL);
    direction.getCombo().setLayoutData(directionData);
    direction.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       * 	@generated
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.direction, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getDirection()));
      }

    });
    direction.setID(UmlViewsRepository.General.direction);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.direction, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createDirectionEMFComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createAggregationEMFComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.aggregation, UmlMessages.GeneralPropertiesEditionPart_AggregationLabel);
    aggregation = new EMFComboViewer(parent);
    aggregation.setContentProvider(new ArrayContentProvider());
    aggregation.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
    GridData aggregationData = new GridData(GridData.FILL_HORIZONTAL);
    aggregation.getCombo().setLayoutData(aggregationData);
    aggregation.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       * 	@generated
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.aggregation, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getAggregation()));
      }

    });
    aggregation.setID(UmlViewsRepository.General.aggregation);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.aggregation, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createAggregationEMFComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createLowerValueText(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.lowerValue, UmlMessages.GeneralPropertiesEditionPart_LowerValueLabel);
    lowerValue = SWTUtils.createScrollableText(parent, SWT.BORDER);
    GridData lowerValueData = new GridData(GridData.FILL_HORIZONTAL);
    lowerValue.setLayoutData(lowerValueData);
    lowerValue.addFocusListener(new FocusAdapter() {

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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.lowerValue, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, lowerValue.getText()));
      }

    });
    lowerValue.addKeyListener(new KeyAdapter() {

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
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.lowerValue, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, lowerValue.getText()));
        }
      }

    });
    EditingUtils.setID(lowerValue, UmlViewsRepository.General.lowerValue);
    EditingUtils.setEEFtype(lowerValue, "eef::Text"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.lowerValue, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createLowerValueText

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createUpperValueText(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.upperValue, UmlMessages.GeneralPropertiesEditionPart_UpperValueLabel);
    upperValue = SWTUtils.createScrollableText(parent, SWT.BORDER);
    GridData upperValueData = new GridData(GridData.FILL_HORIZONTAL);
    upperValue.setLayoutData(upperValueData);
    upperValue.addFocusListener(new FocusAdapter() {

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
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.upperValue, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, upperValue.getText()));
      }

    });
    upperValue.addKeyListener(new KeyAdapter() {

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
            propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.upperValue, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, upperValue.getText()));
        }
      }

    });
    EditingUtils.setID(upperValue, UmlViewsRepository.General.upperValue);
    EditingUtils.setEEFtype(upperValue, "eef::Text"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.upperValue, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createUpperValueText

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createDefaultValueLinkEReferenceViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.defaultValue, UmlMessages.GeneralPropertiesEditionPart_DefaultValueLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.defaultValue, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.defaultValue, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getDefaultValue();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.defaultValue, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    defaultValue = new LinkEReferenceViewer(getDescription(UmlViewsRepository.General.defaultValue, UmlMessages.GeneralPropertiesEditionPart_DefaultValueLabel), resourceSet, defaultValueFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    defaultValue.createControls(parent);
    GridData defaultValueData = new GridData(GridData.FILL_HORIZONTAL);
    defaultValue.setLayoutData(defaultValueData);
    defaultValue.setID(UmlViewsRepository.General.defaultValue);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.defaultValue, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createDefaultValueLinkEReferenceViewer

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createMemberEndAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.General.memberEnd, UmlMessages.GeneralPropertiesEditionPart_MemberEndLabel);		 
    this.memberEnd = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addMemberEnd(); }
      public void handleEdit(EObject element) { editMemberEnd(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveMemberEnd(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromMemberEnd(element); }
      public void navigateTo(EObject element) { }
    });
    this.memberEnd.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.memberEnd, UmlViewsRepository.SWT_KIND));
    this.memberEnd.createControls(parent);
    this.memberEnd.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.memberEnd, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData memberEndData = new GridData(GridData.FILL_HORIZONTAL);
    memberEndData.horizontalSpan = 3;
    this.memberEnd.setLayoutData(memberEndData);
    this.memberEnd.disableMove();
    memberEnd.setID(UmlViewsRepository.General.memberEnd);
    memberEnd.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addMemberEnd() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(memberEnd.getInput(), memberEndFilters, memberEndBusinessFilters,
    "memberEnd", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.memberEnd,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        memberEnd.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveMemberEnd(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.memberEnd, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    memberEnd.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromMemberEnd(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.memberEnd, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    memberEnd.refresh();
  }

	/**
	 * @generated
	 */
	protected void editMemberEnd(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        memberEnd.refresh();
      }
    }
  }

	/**
	 * @generated
	 */
	protected Composite createSupplierAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.General.supplier, UmlMessages.GeneralPropertiesEditionPart_SupplierLabel);		 
    this.supplier = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addSupplier(); }
      public void handleEdit(EObject element) { editSupplier(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveSupplier(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromSupplier(element); }
      public void navigateTo(EObject element) { }
    });
    this.supplier.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.supplier, UmlViewsRepository.SWT_KIND));
    this.supplier.createControls(parent);
    this.supplier.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.supplier, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData supplierData = new GridData(GridData.FILL_HORIZONTAL);
    supplierData.horizontalSpan = 3;
    this.supplier.setLayoutData(supplierData);
    this.supplier.disableMove();
    supplier.setID(UmlViewsRepository.General.supplier);
    supplier.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addSupplier() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(supplier.getInput(), supplierFilters, supplierBusinessFilters,
    "supplier", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.supplier,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        supplier.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveSupplier(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.supplier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    supplier.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromSupplier(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.supplier, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    supplier.refresh();
  }

	/**
	 * @generated
	 */
	protected void editSupplier(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        supplier.refresh();
      }
    }
  }

	/**
	 * @generated
	 */
	protected Composite createClientAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.General.client, UmlMessages.GeneralPropertiesEditionPart_ClientLabel);		 
    this.client = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addClient(); }
      public void handleEdit(EObject element) { editClient(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveClient(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromClient(element); }
      public void navigateTo(EObject element) { }
    });
    this.client.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.client, UmlViewsRepository.SWT_KIND));
    this.client.createControls(parent);
    this.client.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.client, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData clientData = new GridData(GridData.FILL_HORIZONTAL);
    clientData.horizontalSpan = 3;
    this.client.setLayoutData(clientData);
    this.client.disableMove();
    client.setID(UmlViewsRepository.General.client);
    client.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addClient() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(client.getInput(), clientFilters, clientBusinessFilters,
    "client", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.client,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        client.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveClient(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.client, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    client.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromClient(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.client, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    client.refresh();
  }

	/**
	 * @generated
	 */
	protected void editClient(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        client.refresh();
      }
    }
  }

	/**
	 * @generated
	 */
	
	protected Composite createKindEMFComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.kind, UmlMessages.GeneralPropertiesEditionPart_KindLabel);
    kind = new EMFComboViewer(parent);
    kind.setContentProvider(new ArrayContentProvider());
    kind.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
    GridData kindData = new GridData(GridData.FILL_HORIZONTAL);
    kind.getCombo().setLayoutData(kindData);
    kind.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       * 	@generated
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.kind, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getKind()));
      }

    });
    kind.setID(UmlViewsRepository.General.kind);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.kind, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createKindEMFComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createKind_readonlyEMFComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.kind_readonly, UmlMessages.GeneralPropertiesEditionPart_Kind_readonlyLabel);
    kind_readonly = new EMFComboViewer(parent);
    kind_readonly.setContentProvider(new ArrayContentProvider());
    kind_readonly.setLabelProvider(new AdapterFactoryLabelProvider(EEFRuntimePlugin.getDefault().getAdapterFactory()));
    GridData kind_readonlyData = new GridData(GridData.FILL_HORIZONTAL);
    kind_readonly.getCombo().setLayoutData(kind_readonlyData);
    kind_readonly.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       * 	@generated
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.kind_readonly, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getKind_readonly()));
      }

    });
    kind_readonly.setID(UmlViewsRepository.General.kind_readonly);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.kind_readonly, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createKind_readonlyEMFComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @param container
	 * @generated
	 */
	protected Composite createTriggerAdvancedTableComposition(Composite parent) {
    this.trigger = new ReferencesTable(getDescription(UmlViewsRepository.General.trigger, UmlMessages.GeneralPropertiesEditionPart_TriggerLabel), new ReferencesTableListener() {
      public void handleAdd() { 
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.trigger, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
        trigger.refresh();
      }
      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.trigger, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
        trigger.refresh();
      }
      public void handleMove(EObject element, int oldIndex, int newIndex) { 
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.trigger, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
        trigger.refresh();
      }
      public void handleRemove(EObject element) { 
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.trigger, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
        trigger.refresh();
      }
      public void navigateTo(EObject element) { }
    });
    for (ViewerFilter filter : this.triggerFilters) {
      this.trigger.addFilter(filter);
    }
    this.trigger.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.trigger, UmlViewsRepository.SWT_KIND));
    this.trigger.createControls(parent);
    this.trigger.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.trigger, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData triggerData = new GridData(GridData.FILL_HORIZONTAL);
    triggerData.horizontalSpan = 3;
    this.trigger.setLayoutData(triggerData);
    this.trigger.setLowerBound(0);
    this.trigger.setUpperBound(-1);
    trigger.setID(UmlViewsRepository.General.trigger);
    trigger.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
    // Start of user code for createTriggerAdvancedTableComposition

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createEffectSingleCompositionEditor(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.effect, UmlMessages.GeneralPropertiesEditionPart_EffectLabel);
    //create widget
    effect = new SingleCompositionEditor(parent, SWT.NONE);
    GridData effectData = new GridData(GridData.FILL_HORIZONTAL);
    effect.setLayoutData(effectData);
    effect.addEditorListener(new SingleCompositionListener() {
      
      public void edit() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this,  UmlViewsRepository.General.effect, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, null));				
        effect.refresh();
      }
      
      public void clear() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this,  UmlViewsRepository.General.effect, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.UNSET, null, null));
        effect.refresh();
      }
    });
    effect.setID(UmlViewsRepository.General.effect);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.effect, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createEffectSingleCompositionEditor

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createGuardLinkEReferenceViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.guard, UmlMessages.GeneralPropertiesEditionPart_GuardLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.guard, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.guard, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getGuard();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.guard, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    guard = new LinkEReferenceViewer(getDescription(UmlViewsRepository.General.guard, UmlMessages.GeneralPropertiesEditionPart_GuardLabel), resourceSet, guardFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    guard.createControls(parent);
    GridData guardData = new GridData(GridData.FILL_HORIZONTAL);
    guard.setLayoutData(guardData);
    guard.setID(UmlViewsRepository.General.guard);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.guard, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createGuardLinkEReferenceViewer

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createSourceAdvancedFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.source, UmlMessages.GeneralPropertiesEditionPart_SourceLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.source, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.source, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getSource();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.source, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    source = new AdvancedEObjectFlatComboViewer(getDescription(UmlViewsRepository.General.source, UmlMessages.GeneralPropertiesEditionPart_SourceLabel), resourceSet, sourceFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    source.createControls(parent);
    GridData sourceData = new GridData(GridData.FILL_HORIZONTAL);
    source.setLayoutData(sourceData);
    source.setID(UmlViewsRepository.General.source);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.source, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createSourceAdvancedFlatComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createTargetAdvancedFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.target, UmlMessages.GeneralPropertiesEditionPart_TargetLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.target, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.target, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getTarget();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.target, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    target = new AdvancedEObjectFlatComboViewer(getDescription(UmlViewsRepository.General.target, UmlMessages.GeneralPropertiesEditionPart_TargetLabel), resourceSet, targetFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    target.createControls(parent);
    GridData targetData = new GridData(GridData.FILL_HORIZONTAL);
    target.setLayoutData(targetData);
    target.setID(UmlViewsRepository.General.target);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.target, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createTargetAdvancedFlatComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @param container
	 * @generated
	 */
	protected Composite createOwnedRuleAdvancedTableComposition(Composite parent) {
    this.ownedRule = new ReferencesTable(getDescription(UmlViewsRepository.General.ownedRule, UmlMessages.GeneralPropertiesEditionPart_OwnedRuleLabel), new ReferencesTableListener() {
      public void handleAdd() { 
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.ownedRule, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
        ownedRule.refresh();
      }
      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.ownedRule, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
        ownedRule.refresh();
      }
      public void handleMove(EObject element, int oldIndex, int newIndex) { 
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.ownedRule, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
        ownedRule.refresh();
      }
      public void handleRemove(EObject element) { 
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.ownedRule, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
        ownedRule.refresh();
      }
      public void navigateTo(EObject element) { }
    });
    for (ViewerFilter filter : this.ownedRuleFilters) {
      this.ownedRule.addFilter(filter);
    }
    this.ownedRule.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.ownedRule, UmlViewsRepository.SWT_KIND));
    this.ownedRule.createControls(parent);
    this.ownedRule.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.ownedRule, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData ownedRuleData = new GridData(GridData.FILL_HORIZONTAL);
    ownedRuleData.horizontalSpan = 3;
    this.ownedRule.setLayoutData(ownedRuleData);
    this.ownedRule.setLowerBound(0);
    this.ownedRule.setUpperBound(-1);
    ownedRule.setID(UmlViewsRepository.General.ownedRule);
    ownedRule.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
    // Start of user code for createOwnedRuleAdvancedTableComposition

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createIconImageViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.icon, UmlMessages.GeneralPropertiesEditionPart_IconLabel);
    icon = new EEFImageViewer(parent, SWT.BORDER);
    GridData iconData = new GridData();
    // Start of user code  for icon layout data customization
        iconData.widthHint = 200;
        iconData.heightHint = 200;
        iconData.horizontalAlignment = SWT.CENTER;
        iconData.verticalAlignment = SWT.CENTER;
    
    // End of user code
    icon.setLayoutData(iconData);
    icon.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.icon, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getIcon()));
      }
    });
    icon.setID(UmlViewsRepository.General.icon);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.icon, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createIconImageViewer

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createBodyTextarea(Composite parent) {
    Label bodyLabel = createDescription(parent, UmlViewsRepository.General.body, UmlMessages.GeneralPropertiesEditionPart_BodyLabel);
    GridData bodyLabelData = new GridData(GridData.FILL_HORIZONTAL);
    bodyLabelData.horizontalSpan = 3;
    bodyLabel.setLayoutData(bodyLabelData);
    body = SWTUtils.createScrollableText(parent, SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL);
    GridData bodyData = new GridData(GridData.FILL_HORIZONTAL);
    bodyData.horizontalSpan = 2;
    bodyData.heightHint = 80;
    bodyData.widthHint = 200;
    body.setLayoutData(bodyData);
    body.addFocusListener(new FocusAdapter() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
       * @generated
       */
      public void focusLost(FocusEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.body, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, body.getText()));
      }

    });
    EditingUtils.setID(body, UmlViewsRepository.General.body);
    EditingUtils.setEEFtype(body, "eef::Textarea"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.body, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createBodyTextArea

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createExtendedCaseEMFComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.extendedCase, UmlMessages.GeneralPropertiesEditionPart_ExtendedCaseLabel);
    extendedCase = new EMFComboViewer(parent);
    GridData extendedCaseData = new GridData(GridData.FILL_HORIZONTAL);
    extendedCase.getCombo().setLayoutData(extendedCaseData);
    extendedCase.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    extendedCase.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.extendedCase, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getExtendedCase()));
      }

    });
    extendedCase.setContentProvider(new EMFListContentProvider());
    EditingUtils.setID(extendedCase.getCombo(), UmlViewsRepository.General.extendedCase);
    EditingUtils.setEEFtype(extendedCase.getCombo(), "eef::Combo"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.extendedCase, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createExtendedCaseEMFComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createAdditionEMFComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.addition, UmlMessages.GeneralPropertiesEditionPart_AdditionLabel);
    addition = new EMFComboViewer(parent);
    GridData additionData = new GridData(GridData.FILL_HORIZONTAL);
    addition.getCombo().setLayoutData(additionData);
    addition.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
    addition.addSelectionChangedListener(new ISelectionChangedListener() {

      /**
       * {@inheritDoc}
       * 
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.addition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getAddition()));
      }

    });
    addition.setContentProvider(new EMFListContentProvider());
    EditingUtils.setID(addition.getCombo(), UmlViewsRepository.General.addition);
    EditingUtils.setEEFtype(addition.getCombo(), "eef::Combo"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.addition, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createAdditionEMFComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createRoleAdvancedFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.role, UmlMessages.GeneralPropertiesEditionPart_RoleLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.role, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.role, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getRole();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.role, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    role = new AdvancedEObjectFlatComboViewer(getDescription(UmlViewsRepository.General.role, UmlMessages.GeneralPropertiesEditionPart_RoleLabel), resourceSet, roleFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    role.createControls(parent);
    GridData roleData = new GridData(GridData.FILL_HORIZONTAL);
    role.setLayoutData(roleData);
    role.setID(UmlViewsRepository.General.role);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.role, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createRoleAdvancedFlatComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	protected Composite createUsecaseAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.General.usecase, UmlMessages.GeneralPropertiesEditionPart_UsecaseLabel);		 
    this.usecase = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addUsecase(); }
      public void handleEdit(EObject element) { editUsecase(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveUsecase(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromUsecase(element); }
      public void navigateTo(EObject element) { }
    });
    this.usecase.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.usecase, UmlViewsRepository.SWT_KIND));
    this.usecase.createControls(parent);
    this.usecase.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.usecase, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData usecaseData = new GridData(GridData.FILL_HORIZONTAL);
    usecaseData.horizontalSpan = 3;
    this.usecase.setLayoutData(usecaseData);
    this.usecase.disableMove();
    usecase.setID(UmlViewsRepository.General.usecase);
    usecase.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addUsecase() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(usecase.getInput(), usecaseFilters, usecaseBusinessFilters,
    "usecase", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.usecase,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        usecase.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveUsecase(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.usecase, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    usecase.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromUsecase(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.usecase, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    usecase.refresh();
  }

	/**
	 * @generated
	 */
	protected void editUsecase(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        usecase.refresh();
      }
    }
  }

	/**
	 * @generated
	 */
	protected Composite createSubjectsAdvancedReferencesTable(Composite parent) {
    String label = getDescription(UmlViewsRepository.General.subjects, UmlMessages.GeneralPropertiesEditionPart_SubjectsLabel);		 
    this.subjects = new ReferencesTable(label, new ReferencesTableListener() {
      public void handleAdd() { addSubjects(); }
      public void handleEdit(EObject element) { editSubjects(element); }
      public void handleMove(EObject element, int oldIndex, int newIndex) { moveSubjects(element, oldIndex, newIndex); }
      public void handleRemove(EObject element) { removeFromSubjects(element); }
      public void navigateTo(EObject element) { }
    });
    this.subjects.setHelpText(propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.subjects, UmlViewsRepository.SWT_KIND));
    this.subjects.createControls(parent);
    this.subjects.addSelectionListener(new SelectionAdapter() {
      
      public void widgetSelected(SelectionEvent e) {
        if (e.item != null && e.item.getData() instanceof EObject) {
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.subjects, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
        }
      }
      
    });
    GridData subjectsData = new GridData(GridData.FILL_HORIZONTAL);
    subjectsData.horizontalSpan = 3;
    this.subjects.setLayoutData(subjectsData);
    this.subjects.disableMove();
    subjects.setID(UmlViewsRepository.General.subjects);
    subjects.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
    return parent;
  }

	/**
	 * @generated
	 */
	protected void addSubjects() {
    TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(subjects.getInput(), subjectsFilters, subjectsBusinessFilters,
    "subjects", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
      @Override
      public void process(IStructuredSelection selection) {
        for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
          EObject elem = (EObject) iter.next();
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.subjects,
            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
        }
        subjects.refresh();
      }
    };
    dialog.open();
  }

	/**
	 * @generated
	 */
	protected void moveSubjects(EObject element, int oldIndex, int newIndex) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.subjects, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
    subjects.refresh();
  }

	/**
	 * @generated
	 */
	protected void removeFromSubjects(EObject element) {
    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.subjects, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
    subjects.refresh();
  }

	/**
	 * @generated
	 */
	protected void editSubjects(EObject element) {
    EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
    PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
    if (provider != null) {
      PropertiesEditingPolicy policy = provider.getPolicy(context);
      if (policy != null) {
        policy.execute();
        subjects.refresh();
      }
    }
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createEntryAdvancedFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.entry, UmlMessages.GeneralPropertiesEditionPart_EntryLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.entry, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.entry, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getEntry();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.entry, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    entry = new AdvancedEObjectFlatComboViewer(getDescription(UmlViewsRepository.General.entry, UmlMessages.GeneralPropertiesEditionPart_EntryLabel), resourceSet, entryFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    entry.createControls(parent);
    GridData entryData = new GridData(GridData.FILL_HORIZONTAL);
    entry.setLayoutData(entryData);
    entry.setID(UmlViewsRepository.General.entry);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.entry, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createEntryAdvancedFlatComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createExitAdvancedFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.exit, UmlMessages.GeneralPropertiesEditionPart_ExitLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.exit, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.exit, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getExit();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.exit, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    exit = new AdvancedEObjectFlatComboViewer(getDescription(UmlViewsRepository.General.exit, UmlMessages.GeneralPropertiesEditionPart_ExitLabel), resourceSet, exitFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    exit.createControls(parent);
    GridData exitData = new GridData(GridData.FILL_HORIZONTAL);
    exit.setLayoutData(exitData);
    exit.setID(UmlViewsRepository.General.exit);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.exit, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createExitAdvancedFlatComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createDo_AdvancedFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.do_, UmlMessages.GeneralPropertiesEditionPart_Do_Label);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.do_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.do_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getDo_();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.do_, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    do_ = new AdvancedEObjectFlatComboViewer(getDescription(UmlViewsRepository.General.do_, UmlMessages.GeneralPropertiesEditionPart_Do_Label), resourceSet, do_Filter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    do_.createControls(parent);
    GridData do_Data = new GridData(GridData.FILL_HORIZONTAL);
    do_.setLayoutData(do_Data);
    do_.setID(UmlViewsRepository.General.do_);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.do_, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createDo_AdvancedFlatComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createSubmachineLinkEReferenceViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.submachine, UmlMessages.GeneralPropertiesEditionPart_SubmachineLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.submachine, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.submachine, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getSubmachine();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.submachine, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    submachine = new LinkEReferenceViewer(getDescription(UmlViewsRepository.General.submachine, UmlMessages.GeneralPropertiesEditionPart_SubmachineLabel), resourceSet, submachineFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    submachine.createControls(parent);
    GridData submachineData = new GridData(GridData.FILL_HORIZONTAL);
    submachine.setLayoutData(submachineData);
    submachine.setID(UmlViewsRepository.General.submachine);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.submachine, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createSubmachineLinkEReferenceViewer

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createSpecificationSingleCompositionEditor(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.specification, UmlMessages.GeneralPropertiesEditionPart_SpecificationLabel);
    //create widget
    specification = new SingleCompositionEditor(parent, SWT.NONE);
    GridData specificationData = new GridData(GridData.FILL_HORIZONTAL);
    specification.setLayoutData(specificationData);
    specification.addEditorListener(new SingleCompositionListener() {
      
      public void edit() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this,  UmlViewsRepository.General.specification, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, null));				
        specification.refresh();
      }
      
      public void clear() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this,  UmlViewsRepository.General.specification, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.UNSET, null, null));
        specification.refresh();
      }
    });
    specification.setID(UmlViewsRepository.General.specification);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.specification, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createSpecificationSingleCompositionEditor

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createInstanceValueLinkEReferenceViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.instanceValue, UmlMessages.GeneralPropertiesEditionPart_InstanceValueLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.instanceValue, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.instanceValue, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getInstanceValue();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.instanceValue, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    instanceValue = new LinkEReferenceViewer(getDescription(UmlViewsRepository.General.instanceValue, UmlMessages.GeneralPropertiesEditionPart_InstanceValueLabel), resourceSet, instanceValueFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    instanceValue.createControls(parent);
    GridData instanceValueData = new GridData(GridData.FILL_HORIZONTAL);
    instanceValue.setLayoutData(instanceValueData);
    instanceValue.setID(UmlViewsRepository.General.instanceValue);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.instanceValue, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createInstanceValueLinkEReferenceViewer

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createMinLinkEReferenceViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.min, UmlMessages.GeneralPropertiesEditionPart_MinLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.min, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.min, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getMin();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.min, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    min = new LinkEReferenceViewer(getDescription(UmlViewsRepository.General.min, UmlMessages.GeneralPropertiesEditionPart_MinLabel), resourceSet, minFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    min.createControls(parent);
    GridData minData = new GridData(GridData.FILL_HORIZONTAL);
    min.setLayoutData(minData);
    min.setID(UmlViewsRepository.General.min);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.min, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createMinLinkEReferenceViewer

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createInstanceLinkEReferenceViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.instance, UmlMessages.GeneralPropertiesEditionPart_InstanceLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.instance, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.instance, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getInstance();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.instance, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    instance = new LinkEReferenceViewer(getDescription(UmlViewsRepository.General.instance, UmlMessages.GeneralPropertiesEditionPart_InstanceLabel), resourceSet, instanceFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    instance.createControls(parent);
    GridData instanceData = new GridData(GridData.FILL_HORIZONTAL);
    instance.setLayoutData(instanceData);
    instance.setID(UmlViewsRepository.General.instance);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.instance, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createInstanceLinkEReferenceViewer

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createMaxLinkEReferenceViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.max, UmlMessages.GeneralPropertiesEditionPart_MaxLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.max, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.max, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getMax();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.max, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    max = new LinkEReferenceViewer(getDescription(UmlViewsRepository.General.max, UmlMessages.GeneralPropertiesEditionPart_MaxLabel), resourceSet, maxFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    max.createControls(parent);
    GridData maxData = new GridData(GridData.FILL_HORIZONTAL);
    max.setLayoutData(maxData);
    max.setID(UmlViewsRepository.General.max);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.max, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createMaxLinkEReferenceViewer

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createEventLinkEReferenceViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.event, UmlMessages.GeneralPropertiesEditionPart_EventLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.event, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.event, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getEvent();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.event, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    event = new LinkEReferenceViewer(getDescription(UmlViewsRepository.General.event, UmlMessages.GeneralPropertiesEditionPart_EventLabel), resourceSet, eventFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    event.createControls(parent);
    GridData eventData = new GridData(GridData.FILL_HORIZONTAL);
    event.setLayoutData(eventData);
    event.setID(UmlViewsRepository.General.event);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.event, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createEventLinkEReferenceViewer

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createWhenSingleCompositionEditor(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.when, UmlMessages.GeneralPropertiesEditionPart_WhenLabel);
    //create widget
    when = new SingleCompositionEditor(parent, SWT.NONE);
    GridData whenData = new GridData(GridData.FILL_HORIZONTAL);
    when.setLayoutData(whenData);
    when.addEditorListener(new SingleCompositionListener() {
      
      public void edit() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this,  UmlViewsRepository.General.when, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, null));				
        when.refresh();
      }
      
      public void clear() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this,  UmlViewsRepository.General.when, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.UNSET, null, null));
        when.refresh();
      }
    });
    when.setID(UmlViewsRepository.General.when);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.when, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createWhenSingleCompositionEditor

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createChangeExpressionSingleCompositionEditor(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.changeExpression, UmlMessages.GeneralPropertiesEditionPart_ChangeExpressionLabel);
    //create widget
    changeExpression = new SingleCompositionEditor(parent, SWT.NONE);
    GridData changeExpressionData = new GridData(GridData.FILL_HORIZONTAL);
    changeExpression.setLayoutData(changeExpressionData);
    changeExpression.addEditorListener(new SingleCompositionListener() {
      
      public void edit() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this,  UmlViewsRepository.General.changeExpression, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, null));				
        changeExpression.refresh();
      }
      
      public void clear() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this,  UmlViewsRepository.General.changeExpression, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.UNSET, null, null));
        changeExpression.refresh();
      }
    });
    changeExpression.setID(UmlViewsRepository.General.changeExpression);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.changeExpression, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createChangeExpressionSingleCompositionEditor

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createRegionSingleCompositionEditor(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.region, UmlMessages.GeneralPropertiesEditionPart_RegionLabel);
    //create widget
    region = new SingleCompositionEditor(parent, SWT.NONE);
    GridData regionData = new GridData(GridData.FILL_HORIZONTAL);
    region.setLayoutData(regionData);
    region.addEditorListener(new SingleCompositionListener() {
      
      public void edit() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this,  UmlViewsRepository.General.region, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, null));				
        region.refresh();
      }
      
      public void clear() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this,  UmlViewsRepository.General.region, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.UNSET, null, null));
        region.refresh();
      }
    });
    region.setID(UmlViewsRepository.General.region);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.region, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createRegionSingleCompositionEditor

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createBehaviourAdvancedFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.behaviour, UmlMessages.GeneralPropertiesEditionPart_BehaviourLabel);
    // create callback listener
    EObjectFlatComboViewerListener listener = new EObjectFlatComboViewerListener(){
      public void handleSet(EObject element){
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.behaviour, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, element)); 
      }
      public void navigateTo(EObject element){ }

      public EObject handleCreate() {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.behaviour, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null)); 
        return getBehaviour();
      }

      public void handleEdit(EObject element) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.behaviour, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element)); 
      }
    };
    //create widget
    behaviour = new AdvancedEObjectFlatComboViewer(getDescription(UmlViewsRepository.General.behaviour, UmlMessages.GeneralPropertiesEditionPart_BehaviourLabel), resourceSet, behaviourFilter, propertiesEditionComponent.getEditingContext().getAdapterFactory(), listener);
    behaviour.createControls(parent);
    GridData behaviourData = new GridData(GridData.FILL_HORIZONTAL);
    behaviour.setLayoutData(behaviourData);
    behaviour.setID(UmlViewsRepository.General.behaviour);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.behaviour, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createBehaviourAdvancedFlatComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @generated
	 */
	
	protected Composite createUnmarshallCheckbox(Composite parent) {
    unmarshall = new Button(parent, SWT.CHECK);
    unmarshall.setText(getDescription(UmlViewsRepository.General.unmarshall, UmlMessages.GeneralPropertiesEditionPart_UnmarshallLabel));
    unmarshall.addSelectionListener(new SelectionAdapter() {

      /**
       * {@inheritDoc}
       *
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       * 	@generated
       */
      public void widgetSelected(SelectionEvent e) {
        if (propertiesEditionComponent != null)
          propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.unmarshall, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(unmarshall.getSelection())));
      }

    });
    GridData unmarshallData = new GridData(GridData.FILL_HORIZONTAL);
    unmarshallData.horizontalSpan = 2;
    unmarshall.setLayoutData(unmarshallData);
    EditingUtils.setID(unmarshall, UmlViewsRepository.General.unmarshall);
    EditingUtils.setEEFtype(unmarshall, "eef::Checkbox"); //$NON-NLS-1$
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.unmarshall, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createUnmarshallCheckbox

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createOperationFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.operation, UmlMessages.GeneralPropertiesEditionPart_OperationLabel);
    operation = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.General.operation, UmlViewsRepository.SWT_KIND));
    operation.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    operation.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.operation, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getOperation()));
      }

    });
    GridData operationData = new GridData(GridData.FILL_HORIZONTAL);
    operation.setLayoutData(operationData);
    operation.setID(UmlViewsRepository.General.operation);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.operation, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createOperationFlatComboViewer

    // End of user code
    return parent;
  }

	/**
	 * @param parent the parent composite
	 * @generated
	 */
	protected Composite createSignalFlatComboViewer(Composite parent) {
    createDescription(parent, UmlViewsRepository.General.signal, UmlMessages.GeneralPropertiesEditionPart_SignalLabel);
    signal = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(UmlViewsRepository.General.signal, UmlViewsRepository.SWT_KIND));
    signal.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

    signal.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(GeneralPropertiesEditionPartImpl.this, UmlViewsRepository.General.signal, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getSignal()));
      }

    });
    GridData signalData = new GridData(GridData.FILL_HORIZONTAL);
    signal.setLayoutData(signalData);
    signal.setID(UmlViewsRepository.General.signal);
    SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(UmlViewsRepository.General.signal, UmlViewsRepository.SWT_KIND), null); //$NON-NLS-1$
    // Start of user code for createSignalFlatComboViewer

    // End of user code
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
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getName()
	 * @generated
	 */
	public String getName() {
    return name.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setName(String newValue)
	 * @generated
	 */
	public void setName(String newValue) {
    if (newValue != null) {
      name.setText(newValue);
    } else {
      name.setText(""); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.name);
    if (eefElementEditorReadOnlyState && name.isEnabled()) {
      name.setEnabled(false);
      name.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !name.isEnabled()) {
      name.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getVisibility()
	 * @generated
	 */
	public Enumerator getVisibility() {
    Enumerator selection = (Enumerator) ((StructuredSelection) visibility.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initVisibility(Object input, Enumerator current)
	 */
	public void initVisibility(Object input, Enumerator current) {
		visibility.setInput(input);
		visibility.modelUpdating(new StructuredSelection(current));
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.visibility);
		if (eefElementEditorReadOnlyState && visibility.isEnabled()) {
			visibility.setEnabled(false);
			visibility.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !visibility.isEnabled()) {
			visibility.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setVisibility(Enumerator newValue)
	 * @generated
	 */
	public void setVisibility(Enumerator newValue) {
    visibility.modelUpdating(new StructuredSelection(newValue));
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.visibility);
    if (eefElementEditorReadOnlyState && visibility.isEnabled()) {
      visibility.setEnabled(false);
      visibility.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !visibility.isEnabled()) {
      visibility.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getOrdered()
	 * @generated
	 */
	public Boolean getOrdered() {
    return Boolean.valueOf(ordered.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setOrdered(Boolean newValue)
	 * @generated
	 */
	public void setOrdered(Boolean newValue) {
    if (newValue != null) {
      ordered.setSelection(newValue.booleanValue());
    } else {
      ordered.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.ordered);
    if (eefElementEditorReadOnlyState && ordered.isEnabled()) {
      ordered.setEnabled(false);
      ordered.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !ordered.isEnabled()) {
      ordered.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getAbstract_()
	 * @generated
	 */
	public Boolean getAbstract_() {
    return Boolean.valueOf(abstract_.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setAbstract_(Boolean newValue)
	 * @generated
	 */
	public void setAbstract_(Boolean newValue) {
    if (newValue != null) {
      abstract_.setSelection(newValue.booleanValue());
    } else {
      abstract_.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.abstract_);
    if (eefElementEditorReadOnlyState && abstract_.isEnabled()) {
      abstract_.setEnabled(false);
      abstract_.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !abstract_.isEnabled()) {
      abstract_.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getLeaf()
	 * @generated
	 */
	public Boolean getLeaf() {
    return Boolean.valueOf(leaf.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setLeaf(Boolean newValue)
	 * @generated
	 */
	public void setLeaf(Boolean newValue) {
    if (newValue != null) {
      leaf.setSelection(newValue.booleanValue());
    } else {
      leaf.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.leaf);
    if (eefElementEditorReadOnlyState && leaf.isEnabled()) {
      leaf.setEnabled(false);
      leaf.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !leaf.isEnabled()) {
      leaf.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getStatic_()
	 * @generated
	 */
	public Boolean getStatic_() {
    return Boolean.valueOf(static_.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setStatic_(Boolean newValue)
	 * @generated
	 */
	public void setStatic_(Boolean newValue) {
    if (newValue != null) {
      static_.setSelection(newValue.booleanValue());
    } else {
      static_.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.static_);
    if (eefElementEditorReadOnlyState && static_.isEnabled()) {
      static_.setEnabled(false);
      static_.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !static_.isEnabled()) {
      static_.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getUnique()
	 * @generated
	 */
	public Boolean getUnique() {
    return Boolean.valueOf(unique.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setUnique(Boolean newValue)
	 * @generated
	 */
	public void setUnique(Boolean newValue) {
    if (newValue != null) {
      unique.setSelection(newValue.booleanValue());
    } else {
      unique.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.unique);
    if (eefElementEditorReadOnlyState && unique.isEnabled()) {
      unique.setEnabled(false);
      unique.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !unique.isEnabled()) {
      unique.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getQuery()
	 * @generated
	 */
	public Boolean getQuery() {
    return Boolean.valueOf(query.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setQuery(Boolean newValue)
	 * @generated
	 */
	public void setQuery(Boolean newValue) {
    if (newValue != null) {
      query.setSelection(newValue.booleanValue());
    } else {
      query.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.query);
    if (eefElementEditorReadOnlyState && query.isEnabled()) {
      query.setEnabled(false);
      query.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !query.isEnabled()) {
      query.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getReadOnly()
	 * @generated
	 */
	public Boolean getReadOnly() {
    return Boolean.valueOf(readOnly.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setReadOnly(Boolean newValue)
	 * @generated
	 */
	public void setReadOnly(Boolean newValue) {
    if (newValue != null) {
      readOnly.setSelection(newValue.booleanValue());
    } else {
      readOnly.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.readOnly);
    if (eefElementEditorReadOnlyState && readOnly.isEnabled()) {
      readOnly.setEnabled(false);
      readOnly.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !readOnly.isEnabled()) {
      readOnly.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getDerived()
	 * @generated
	 */
	public Boolean getDerived() {
    return Boolean.valueOf(derived.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setDerived(Boolean newValue)
	 * @generated
	 */
	public void setDerived(Boolean newValue) {
    if (newValue != null) {
      derived.setSelection(newValue.booleanValue());
    } else {
      derived.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.derived);
    if (eefElementEditorReadOnlyState && derived.isEnabled()) {
      derived.setEnabled(false);
      derived.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !derived.isEnabled()) {
      derived.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getDerivedUnion()
	 * @generated
	 */
	public Boolean getDerivedUnion() {
    return Boolean.valueOf(derivedUnion.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setDerivedUnion(Boolean newValue)
	 * @generated
	 */
	public void setDerivedUnion(Boolean newValue) {
    if (newValue != null) {
      derivedUnion.setSelection(newValue.booleanValue());
    } else {
      derivedUnion.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.derivedUnion);
    if (eefElementEditorReadOnlyState && derivedUnion.isEnabled()) {
      derivedUnion.setEnabled(false);
      derivedUnion.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !derivedUnion.isEnabled()) {
      derivedUnion.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getSubstitutable()
	 * @generated
	 */
	public Boolean getSubstitutable() {
    return Boolean.valueOf(substitutable.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setSubstitutable(Boolean newValue)
	 * @generated
	 */
	public void setSubstitutable(Boolean newValue) {
    if (newValue != null) {
      substitutable.setSelection(newValue.booleanValue());
    } else {
      substitutable.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.substitutable);
    if (eefElementEditorReadOnlyState && substitutable.isEnabled()) {
      substitutable.setEnabled(false);
      substitutable.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !substitutable.isEnabled()) {
      substitutable.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getActive()
	 * @generated
	 */
	public Boolean getActive() {
    return Boolean.valueOf(active.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setActive(Boolean newValue)
	 * @generated
	 */
	public void setActive(Boolean newValue) {
    if (newValue != null) {
      active.setSelection(newValue.booleanValue());
    } else {
      active.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.active);
    if (eefElementEditorReadOnlyState && active.isEnabled()) {
      active.setEnabled(false);
      active.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !active.isEnabled()) {
      active.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getBehavior()
	 * @generated
	 */
	public Boolean getBehavior() {
    return Boolean.valueOf(behavior.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setBehavior(Boolean newValue)
	 * @generated
	 */
	public void setBehavior(Boolean newValue) {
    if (newValue != null) {
      behavior.setSelection(newValue.booleanValue());
    } else {
      behavior.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.behavior);
    if (eefElementEditorReadOnlyState && behavior.isEnabled()) {
      behavior.setEnabled(false);
      behavior.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !behavior.isEnabled()) {
      behavior.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getService()
	 * @generated
	 */
	public Boolean getService() {
    return Boolean.valueOf(service.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setService(Boolean newValue)
	 * @generated
	 */
	public void setService(Boolean newValue) {
    if (newValue != null) {
      service.setSelection(newValue.booleanValue());
    } else {
      service.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.service);
    if (eefElementEditorReadOnlyState && service.isEnabled()) {
      service.setEnabled(false);
      service.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !service.isEnabled()) {
      service.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getReentrant()
	 * @generated
	 */
	public Boolean getReentrant() {
    return Boolean.valueOf(reentrant.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setReentrant(Boolean newValue)
	 * @generated
	 */
	public void setReentrant(Boolean newValue) {
    if (newValue != null) {
      reentrant.setSelection(newValue.booleanValue());
    } else {
      reentrant.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.reentrant);
    if (eefElementEditorReadOnlyState && reentrant.isEnabled()) {
      reentrant.setEnabled(false);
      reentrant.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !reentrant.isEnabled()) {
      reentrant.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getIndirectlyInstantiated()
	 * @generated
	 */
	public Boolean getIndirectlyInstantiated() {
    return Boolean.valueOf(indirectlyInstantiated.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setIndirectlyInstantiated(Boolean newValue)
	 * @generated
	 */
	public void setIndirectlyInstantiated(Boolean newValue) {
    if (newValue != null) {
      indirectlyInstantiated.setSelection(newValue.booleanValue());
    } else {
      indirectlyInstantiated.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.Qualifiers.indirectlyInstantiated);
    if (eefElementEditorReadOnlyState && indirectlyInstantiated.isEnabled()) {
      indirectlyInstantiated.setEnabled(false);
      indirectlyInstantiated.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !indirectlyInstantiated.isEnabled()) {
      indirectlyInstantiated.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getType()
	 * @generated
	 */
	public EObject getType() {
    return type.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initType(EObjectFlatComboSettings)
	 */
	public void initType(EObjectFlatComboSettings settings) {
		type.setInput(settings);
		if (current != null) {
			type.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.type);
		if (eefElementEditorReadOnlyState && type.isEnabled()) {
			type.setEnabled(false);
			type.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !type.isEnabled()) {
			type.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setType(EObject newValue)
	 * @generated
	 */
	public void setType(EObject newValue) {
    if (newValue != null) {
      type.setSelection(new StructuredSelection(newValue));
    } else {
      type.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.type);
    if (eefElementEditorReadOnlyState && type.isEnabled()) {
      type.setEnabled(false);
      type.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !type.isEnabled()) {
      type.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setTypeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTypeButtonMode(ButtonsModeEnum newValue) {
		type.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterType(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToType(ViewerFilter filter) {
    type.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterType(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToType(ViewerFilter filter) {
    type.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getDirection()
	 * @generated
	 */
	public Enumerator getDirection() {
    Enumerator selection = (Enumerator) ((StructuredSelection) direction.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initDirection(Object input, Enumerator current)
	 */
	public void initDirection(Object input, Enumerator current) {
		direction.setInput(input);
		direction.modelUpdating(new StructuredSelection(current));
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.direction);
		if (eefElementEditorReadOnlyState && direction.isEnabled()) {
			direction.setEnabled(false);
			direction.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !direction.isEnabled()) {
			direction.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setDirection(Enumerator newValue)
	 * @generated
	 */
	public void setDirection(Enumerator newValue) {
    direction.modelUpdating(new StructuredSelection(newValue));
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.direction);
    if (eefElementEditorReadOnlyState && direction.isEnabled()) {
      direction.setEnabled(false);
      direction.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !direction.isEnabled()) {
      direction.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getAggregation()
	 * @generated
	 */
	public Enumerator getAggregation() {
    Enumerator selection = (Enumerator) ((StructuredSelection) aggregation.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initAggregation(Object input, Enumerator current)
	 */
	public void initAggregation(Object input, Enumerator current) {
		aggregation.setInput(input);
		aggregation.modelUpdating(new StructuredSelection(current));
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.aggregation);
		if (eefElementEditorReadOnlyState && aggregation.isEnabled()) {
			aggregation.setEnabled(false);
			aggregation.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !aggregation.isEnabled()) {
			aggregation.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setAggregation(Enumerator newValue)
	 * @generated
	 */
	public void setAggregation(Enumerator newValue) {
    aggregation.modelUpdating(new StructuredSelection(newValue));
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.aggregation);
    if (eefElementEditorReadOnlyState && aggregation.isEnabled()) {
      aggregation.setEnabled(false);
      aggregation.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !aggregation.isEnabled()) {
      aggregation.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getLowerValue()
	 * @generated
	 */
	public String getLowerValue() {
    return lowerValue.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setLowerValue(String newValue)
	 * @generated
	 */
	public void setLowerValue(String newValue) {
    if (newValue != null) {
      lowerValue.setText(newValue);
    } else {
      lowerValue.setText(""); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.lowerValue);
    if (eefElementEditorReadOnlyState && lowerValue.isEnabled()) {
      lowerValue.setEnabled(false);
      lowerValue.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !lowerValue.isEnabled()) {
      lowerValue.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getUpperValue()
	 * @generated
	 */
	public String getUpperValue() {
    return upperValue.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setUpperValue(String newValue)
	 * @generated
	 */
	public void setUpperValue(String newValue) {
    if (newValue != null) {
      upperValue.setText(newValue);
    } else {
      upperValue.setText(""); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.upperValue);
    if (eefElementEditorReadOnlyState && upperValue.isEnabled()) {
      upperValue.setEnabled(false);
      upperValue.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !upperValue.isEnabled()) {
      upperValue.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getDefaultValue()
	 * @generated
	 */
	public EObject getDefaultValue() {
    return defaultValue.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initDefaultValue(EObjectFlatComboSettings)
	 */
	public void initDefaultValue(EObjectFlatComboSettings settings) {
		defaultValue.setInput(settings);
		if (current != null) {
			defaultValue.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.defaultValue);
		if (eefElementEditorReadOnlyState && defaultValue.isEnabled()) {
			defaultValue.setEnabled(false);
			defaultValue.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !defaultValue.isEnabled()) {
			defaultValue.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setDefaultValue(EObject newValue)
	 * @generated
	 */
	public void setDefaultValue(EObject newValue) {
    if (newValue != null) {
      defaultValue.setSelection(new StructuredSelection(newValue));
    } else {
      defaultValue.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.defaultValue);
    if (eefElementEditorReadOnlyState && defaultValue.isEnabled()) {
      defaultValue.setEnabled(false);
      defaultValue.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !defaultValue.isEnabled()) {
      defaultValue.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setDefaultValueButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDefaultValueButtonMode(ButtonsModeEnum newValue) {
		defaultValue.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterDefaultValue(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToDefaultValue(ViewerFilter filter) {
    defaultValue.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterDefaultValue(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToDefaultValue(ViewerFilter filter) {
    defaultValue.addBusinessRuleFilter(filter);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initMemberEnd(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initMemberEnd(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		memberEnd.setContentProvider(contentProvider);
		memberEnd.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.memberEnd);
		if (eefElementEditorReadOnlyState && memberEnd.getTable().isEnabled()) {
			memberEnd.setEnabled(false);
			memberEnd.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !memberEnd.getTable().isEnabled()) {
			memberEnd.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#updateMemberEnd()
	 * @generated
	 */
	public void updateMemberEnd() {
  memberEnd.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterMemberEnd(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToMemberEnd(ViewerFilter filter) {
    memberEndFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterMemberEnd(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToMemberEnd(ViewerFilter filter) {
    memberEndBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#isContainedInMemberEndTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInMemberEndTable(EObject element) {
    return ((ReferencesTableSettings)memberEnd.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initSupplier(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initSupplier(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		supplier.setContentProvider(contentProvider);
		supplier.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.supplier);
		if (eefElementEditorReadOnlyState && supplier.getTable().isEnabled()) {
			supplier.setEnabled(false);
			supplier.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !supplier.getTable().isEnabled()) {
			supplier.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#updateSupplier()
	 * @generated
	 */
	public void updateSupplier() {
  supplier.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterSupplier(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSupplier(ViewerFilter filter) {
    supplierFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterSupplier(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSupplier(ViewerFilter filter) {
    supplierBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#isContainedInSupplierTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInSupplierTable(EObject element) {
    return ((ReferencesTableSettings)supplier.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initClient(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initClient(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		client.setContentProvider(contentProvider);
		client.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.client);
		if (eefElementEditorReadOnlyState && client.getTable().isEnabled()) {
			client.setEnabled(false);
			client.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !client.getTable().isEnabled()) {
			client.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#updateClient()
	 * @generated
	 */
	public void updateClient() {
  client.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterClient(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToClient(ViewerFilter filter) {
    clientFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterClient(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToClient(ViewerFilter filter) {
    clientBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#isContainedInClientTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInClientTable(EObject element) {
    return ((ReferencesTableSettings)client.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getKind()
	 * @generated
	 */
	public Enumerator getKind() {
    Enumerator selection = (Enumerator) ((StructuredSelection) kind.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initKind(Object input, Enumerator current)
	 */
	public void initKind(Object input, Enumerator current) {
		kind.setInput(input);
		kind.modelUpdating(new StructuredSelection(current));
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.kind);
		if (eefElementEditorReadOnlyState && kind.isEnabled()) {
			kind.setEnabled(false);
			kind.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !kind.isEnabled()) {
			kind.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setKind(Enumerator newValue)
	 * @generated
	 */
	public void setKind(Enumerator newValue) {
    kind.modelUpdating(new StructuredSelection(newValue));
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.kind);
    if (eefElementEditorReadOnlyState && kind.isEnabled()) {
      kind.setEnabled(false);
      kind.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !kind.isEnabled()) {
      kind.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getKind_readonly()
	 * @generated
	 */
	public Enumerator getKind_readonly() {
    Enumerator selection = (Enumerator) ((StructuredSelection) kind_readonly.getSelection()).getFirstElement();
    return selection;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initKind_readonly(Object input, Enumerator current)
	 */
	public void initKind_readonly(Object input, Enumerator current) {
		kind_readonly.setInput(input);
		kind_readonly.modelUpdating(new StructuredSelection(current));
		kind_readonly.setEnabled(false);
		kind_readonly.setToolTipText(UmlMessages.General_ReadOnly);
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setKind_readonly(Enumerator newValue)
	 * @generated
	 */
	public void setKind_readonly(Enumerator newValue) {
    kind_readonly.modelUpdating(new StructuredSelection(newValue));
    kind_readonly.setEnabled(false);
    kind_readonly.setToolTipText(UmlMessages.General_ReadOnly);
    
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initTrigger(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initTrigger(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		trigger.setContentProvider(contentProvider);
		trigger.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.trigger);
		if (eefElementEditorReadOnlyState && trigger.isEnabled()) {
			trigger.setEnabled(false);
			trigger.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !trigger.isEnabled()) {
			trigger.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#updateTrigger()
	 * @generated
	 */
	public void updateTrigger() {
  trigger.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterTrigger(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTrigger(ViewerFilter filter) {
    triggerFilters.add(filter);
    if (this.trigger != null) {
      this.trigger.addFilter(filter);
    }
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterTrigger(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTrigger(ViewerFilter filter) {
    triggerBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#isContainedInTriggerTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInTriggerTable(EObject element) {
    return ((ReferencesTableSettings)trigger.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getEffect()
	 * @generated
	 */
	public EObject getEffect() {
    return (EObject) effect.getInput();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initEffect(EObjectFlatComboSettings)
	 */
	public void initEffect(EObjectFlatComboSettings settings) {
		effect.setAdapterFactory(adapterFactory);
		effect.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.effect);
		if (eefElementEditorReadOnlyState && effect.isEnabled()) {
			effect.setEnabled(false);
			effect.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !effect.isEnabled()) {
			effect.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setEffect(EObject newValue)
	 * @generated
	 */
	public void setEffect(EObject newValue) {
    effect.refresh();
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.effect);
    if (eefElementEditorReadOnlyState && effect.isEnabled()) {
      effect.setEnabled(false);
      effect.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !effect.isEnabled()) {
      effect.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getGuard()
	 * @generated
	 */
	public EObject getGuard() {
    return guard.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initGuard(EObjectFlatComboSettings)
	 */
	public void initGuard(EObjectFlatComboSettings settings) {
		guard.setInput(settings);
		if (current != null) {
			guard.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.guard);
		if (eefElementEditorReadOnlyState && guard.isEnabled()) {
			guard.setEnabled(false);
			guard.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !guard.isEnabled()) {
			guard.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setGuard(EObject newValue)
	 * @generated
	 */
	public void setGuard(EObject newValue) {
    if (newValue != null) {
      guard.setSelection(new StructuredSelection(newValue));
    } else {
      guard.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.guard);
    if (eefElementEditorReadOnlyState && guard.isEnabled()) {
      guard.setEnabled(false);
      guard.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !guard.isEnabled()) {
      guard.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setGuardButtonMode(ButtonsModeEnum newValue)
	 */
	public void setGuardButtonMode(ButtonsModeEnum newValue) {
		guard.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterGuard(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToGuard(ViewerFilter filter) {
    guard.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterGuard(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToGuard(ViewerFilter filter) {
    guard.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getSource()
	 * @generated
	 */
	public EObject getSource() {
    return source.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initSource(EObjectFlatComboSettings)
	 */
	public void initSource(EObjectFlatComboSettings settings) {
		source.setInput(settings);
		if (current != null) {
			source.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.source);
		if (eefElementEditorReadOnlyState && source.isEnabled()) {
			source.setEnabled(false);
			source.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !source.isEnabled()) {
			source.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setSource(EObject newValue)
	 * @generated
	 */
	public void setSource(EObject newValue) {
    if (newValue != null) {
      source.setSelection(new StructuredSelection(newValue));
    } else {
      source.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.source);
    if (eefElementEditorReadOnlyState && source.isEnabled()) {
      source.setEnabled(false);
      source.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !source.isEnabled()) {
      source.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setSourceButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSourceButtonMode(ButtonsModeEnum newValue) {
		source.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterSource(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSource(ViewerFilter filter) {
    source.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterSource(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSource(ViewerFilter filter) {
    source.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getTarget()
	 * @generated
	 */
	public EObject getTarget() {
    return target.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initTarget(EObjectFlatComboSettings)
	 */
	public void initTarget(EObjectFlatComboSettings settings) {
		target.setInput(settings);
		if (current != null) {
			target.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.target);
		if (eefElementEditorReadOnlyState && target.isEnabled()) {
			target.setEnabled(false);
			target.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !target.isEnabled()) {
			target.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setTarget(EObject newValue)
	 * @generated
	 */
	public void setTarget(EObject newValue) {
    if (newValue != null) {
      target.setSelection(new StructuredSelection(newValue));
    } else {
      target.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.target);
    if (eefElementEditorReadOnlyState && target.isEnabled()) {
      target.setEnabled(false);
      target.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !target.isEnabled()) {
      target.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setTargetButtonMode(ButtonsModeEnum newValue)
	 */
	public void setTargetButtonMode(ButtonsModeEnum newValue) {
		target.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterTarget(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToTarget(ViewerFilter filter) {
    target.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterTarget(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToTarget(ViewerFilter filter) {
    target.addBusinessRuleFilter(filter);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initOwnedRule(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initOwnedRule(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		ownedRule.setContentProvider(contentProvider);
		ownedRule.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.ownedRule);
		if (eefElementEditorReadOnlyState && ownedRule.isEnabled()) {
			ownedRule.setEnabled(false);
			ownedRule.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !ownedRule.isEnabled()) {
			ownedRule.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#updateOwnedRule()
	 * @generated
	 */
	public void updateOwnedRule() {
  ownedRule.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterOwnedRule(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOwnedRule(ViewerFilter filter) {
    ownedRuleFilters.add(filter);
    if (this.ownedRule != null) {
      this.ownedRule.addFilter(filter);
    }
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterOwnedRule(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOwnedRule(ViewerFilter filter) {
    ownedRuleBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#isContainedInOwnedRuleTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInOwnedRuleTable(EObject element) {
    return ((ReferencesTableSettings)ownedRule.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getIcon()
	 * @generated
	 */
	public String getIcon() {
    ISelection selection = icon.getSelection();
    if (selection instanceof StructuredSelection && ((StructuredSelection)selection).getFirstElement() instanceof String) {
      return (String)((StructuredSelection)selection).getFirstElement();
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initIcon(String key, String newValue)
	 */
	public void initIcon(String key, String newValue) {
		if (newValue != null) {
			icon.initViewer(key, newValue);
		} else {
			icon.initViewer(key, ""); //$NON-NLS-1$
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.icon);
		if (eefElementEditorReadOnlyState && icon.isEnabled()) {
			icon.setEnabled(false);
			icon.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !icon.isEnabled()) {
			icon.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setIcon(String newValue)
	 * @generated
	 */
	public void setIcon(String newValue) {
    if (newValue != null) {
      icon.setSelection(new StructuredSelection(newValue));
    } else {
      icon.setSelection(new StructuredSelection("")); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.icon);
    if (eefElementEditorReadOnlyState && icon.isEnabled()) {
      icon.setEnabled(false);
      icon.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !icon.isEnabled()) {
      icon.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getBody()
	 * @generated
	 */
	public String getBody() {
    return body.getText();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setBody(String newValue)
	 * @generated
	 */
	public void setBody(String newValue) {
    if (newValue != null) {
      body.setText(newValue);
    } else {
      body.setText(""); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.body);
    if (eefElementEditorReadOnlyState && body.isEnabled()) {
      body.setEnabled(false);
      body.setBackground(body.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
      body.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !body.isEnabled()) {
      body.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getExtendedCase()
	 * @generated
	 */
	public Object getExtendedCase() {
    if (extendedCase.getSelection() instanceof StructuredSelection) {
      return ((StructuredSelection) extendedCase.getSelection()).getFirstElement();
    }
    return "";
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initExtendedCase(Object input, Object currentValue)
	 */
	public void initExtendedCase(Object input, Object currentValue) {
		extendedCase.setInput(input);
		if (currentValue != null) {
			extendedCase.setSelection(new StructuredSelection(currentValue));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setExtendedCase(Object newValue)
	 * @generated
	 */
	public void setExtendedCase(Object newValue) {
    if (newValue != null) {
      extendedCase.modelUpdating(new StructuredSelection(newValue));
    } else {
      extendedCase.modelUpdating(new StructuredSelection("")); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.extendedCase);
    if (eefElementEditorReadOnlyState && extendedCase.isEnabled()) {
      extendedCase.setEnabled(false);
      extendedCase.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !extendedCase.isEnabled()) {
      extendedCase.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterExtendedCase(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToExtendedCase(ViewerFilter filter) {
    extendedCase.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getAddition()
	 * @generated
	 */
	public Object getAddition() {
    if (addition.getSelection() instanceof StructuredSelection) {
      return ((StructuredSelection) addition.getSelection()).getFirstElement();
    }
    return "";
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initAddition(Object input, Object currentValue)
	 */
	public void initAddition(Object input, Object currentValue) {
		addition.setInput(input);
		if (currentValue != null) {
			addition.setSelection(new StructuredSelection(currentValue));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setAddition(Object newValue)
	 * @generated
	 */
	public void setAddition(Object newValue) {
    if (newValue != null) {
      addition.modelUpdating(new StructuredSelection(newValue));
    } else {
      addition.modelUpdating(new StructuredSelection("")); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.addition);
    if (eefElementEditorReadOnlyState && addition.isEnabled()) {
      addition.setEnabled(false);
      addition.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !addition.isEnabled()) {
      addition.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterAddition(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToAddition(ViewerFilter filter) {
    addition.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getRole()
	 * @generated
	 */
	public EObject getRole() {
    return role.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initRole(EObjectFlatComboSettings)
	 */
	public void initRole(EObjectFlatComboSettings settings) {
		role.setInput(settings);
		if (current != null) {
			role.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.role);
		if (eefElementEditorReadOnlyState && role.isEnabled()) {
			role.setEnabled(false);
			role.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !role.isEnabled()) {
			role.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setRole(EObject newValue)
	 * @generated
	 */
	public void setRole(EObject newValue) {
    if (newValue != null) {
      role.setSelection(new StructuredSelection(newValue));
    } else {
      role.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.role);
    if (eefElementEditorReadOnlyState && role.isEnabled()) {
      role.setEnabled(false);
      role.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !role.isEnabled()) {
      role.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setRoleButtonMode(ButtonsModeEnum newValue)
	 */
	public void setRoleButtonMode(ButtonsModeEnum newValue) {
		role.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterRole(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToRole(ViewerFilter filter) {
    role.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterRole(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToRole(ViewerFilter filter) {
    role.addBusinessRuleFilter(filter);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initUsecase(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initUsecase(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		usecase.setContentProvider(contentProvider);
		usecase.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.usecase);
		if (eefElementEditorReadOnlyState && usecase.getTable().isEnabled()) {
			usecase.setEnabled(false);
			usecase.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !usecase.getTable().isEnabled()) {
			usecase.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#updateUsecase()
	 * @generated
	 */
	public void updateUsecase() {
  usecase.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterUsecase(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToUsecase(ViewerFilter filter) {
    usecaseFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterUsecase(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToUsecase(ViewerFilter filter) {
    usecaseBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#isContainedInUsecaseTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInUsecaseTable(EObject element) {
    return ((ReferencesTableSettings)usecase.getInput()).contains(element);
  }



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initSubjects(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initSubjects(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		subjects.setContentProvider(contentProvider);
		subjects.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.subjects);
		if (eefElementEditorReadOnlyState && subjects.getTable().isEnabled()) {
			subjects.setEnabled(false);
			subjects.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !subjects.getTable().isEnabled()) {
			subjects.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#updateSubjects()
	 * @generated
	 */
	public void updateSubjects() {
  subjects.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterSubjects(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSubjects(ViewerFilter filter) {
    subjectsFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterSubjects(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSubjects(ViewerFilter filter) {
    subjectsBusinessFilters.add(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#isContainedInSubjectsTable(EObject element)
	 * @generated
	 */
	public boolean isContainedInSubjectsTable(EObject element) {
    return ((ReferencesTableSettings)subjects.getInput()).contains(element);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getEntry()
	 * @generated
	 */
	public EObject getEntry() {
    return entry.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initEntry(EObjectFlatComboSettings)
	 */
	public void initEntry(EObjectFlatComboSettings settings) {
		entry.setInput(settings);
		if (current != null) {
			entry.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.entry);
		if (eefElementEditorReadOnlyState && entry.isEnabled()) {
			entry.setEnabled(false);
			entry.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !entry.isEnabled()) {
			entry.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setEntry(EObject newValue)
	 * @generated
	 */
	public void setEntry(EObject newValue) {
    if (newValue != null) {
      entry.setSelection(new StructuredSelection(newValue));
    } else {
      entry.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.entry);
    if (eefElementEditorReadOnlyState && entry.isEnabled()) {
      entry.setEnabled(false);
      entry.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !entry.isEnabled()) {
      entry.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setEntryButtonMode(ButtonsModeEnum newValue)
	 */
	public void setEntryButtonMode(ButtonsModeEnum newValue) {
		entry.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterEntry(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEntry(ViewerFilter filter) {
    entry.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterEntry(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEntry(ViewerFilter filter) {
    entry.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getExit()
	 * @generated
	 */
	public EObject getExit() {
    return exit.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initExit(EObjectFlatComboSettings)
	 */
	public void initExit(EObjectFlatComboSettings settings) {
		exit.setInput(settings);
		if (current != null) {
			exit.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.exit);
		if (eefElementEditorReadOnlyState && exit.isEnabled()) {
			exit.setEnabled(false);
			exit.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !exit.isEnabled()) {
			exit.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setExit(EObject newValue)
	 * @generated
	 */
	public void setExit(EObject newValue) {
    if (newValue != null) {
      exit.setSelection(new StructuredSelection(newValue));
    } else {
      exit.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.exit);
    if (eefElementEditorReadOnlyState && exit.isEnabled()) {
      exit.setEnabled(false);
      exit.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !exit.isEnabled()) {
      exit.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setExitButtonMode(ButtonsModeEnum newValue)
	 */
	public void setExitButtonMode(ButtonsModeEnum newValue) {
		exit.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterExit(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToExit(ViewerFilter filter) {
    exit.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterExit(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToExit(ViewerFilter filter) {
    exit.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getDo_()
	 * @generated
	 */
	public EObject getDo_() {
    return do_.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initDo_(EObjectFlatComboSettings)
	 */
	public void initDo_(EObjectFlatComboSettings settings) {
		do_.setInput(settings);
		if (current != null) {
			do_.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.do_);
		if (eefElementEditorReadOnlyState && do_.isEnabled()) {
			do_.setEnabled(false);
			do_.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !do_.isEnabled()) {
			do_.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setDo_(EObject newValue)
	 * @generated
	 */
	public void setDo_(EObject newValue) {
    if (newValue != null) {
      do_.setSelection(new StructuredSelection(newValue));
    } else {
      do_.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.do_);
    if (eefElementEditorReadOnlyState && do_.isEnabled()) {
      do_.setEnabled(false);
      do_.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !do_.isEnabled()) {
      do_.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setDo_ButtonMode(ButtonsModeEnum newValue)
	 */
	public void setDo_ButtonMode(ButtonsModeEnum newValue) {
		do_.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterDo_(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToDo_(ViewerFilter filter) {
    do_.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterDo_(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToDo_(ViewerFilter filter) {
    do_.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getSubmachine()
	 * @generated
	 */
	public EObject getSubmachine() {
    return submachine.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initSubmachine(EObjectFlatComboSettings)
	 */
	public void initSubmachine(EObjectFlatComboSettings settings) {
		submachine.setInput(settings);
		if (current != null) {
			submachine.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.submachine);
		if (eefElementEditorReadOnlyState && submachine.isEnabled()) {
			submachine.setEnabled(false);
			submachine.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !submachine.isEnabled()) {
			submachine.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setSubmachine(EObject newValue)
	 * @generated
	 */
	public void setSubmachine(EObject newValue) {
    if (newValue != null) {
      submachine.setSelection(new StructuredSelection(newValue));
    } else {
      submachine.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.submachine);
    if (eefElementEditorReadOnlyState && submachine.isEnabled()) {
      submachine.setEnabled(false);
      submachine.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !submachine.isEnabled()) {
      submachine.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setSubmachineButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSubmachineButtonMode(ButtonsModeEnum newValue) {
		submachine.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterSubmachine(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSubmachine(ViewerFilter filter) {
    submachine.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterSubmachine(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSubmachine(ViewerFilter filter) {
    submachine.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getSpecification()
	 * @generated
	 */
	public EObject getSpecification() {
    return (EObject) specification.getInput();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initSpecification(EObjectFlatComboSettings)
	 */
	public void initSpecification(EObjectFlatComboSettings settings) {
		specification.setAdapterFactory(adapterFactory);
		specification.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.specification);
		if (eefElementEditorReadOnlyState && specification.isEnabled()) {
			specification.setEnabled(false);
			specification.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !specification.isEnabled()) {
			specification.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setSpecification(EObject newValue)
	 * @generated
	 */
	public void setSpecification(EObject newValue) {
    specification.refresh();
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.specification);
    if (eefElementEditorReadOnlyState && specification.isEnabled()) {
      specification.setEnabled(false);
      specification.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !specification.isEnabled()) {
      specification.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getInstanceValue()
	 * @generated
	 */
	public EObject getInstanceValue() {
    return instanceValue.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initInstanceValue(EObjectFlatComboSettings)
	 */
	public void initInstanceValue(EObjectFlatComboSettings settings) {
		instanceValue.setInput(settings);
		if (current != null) {
			instanceValue.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.instanceValue);
		if (eefElementEditorReadOnlyState && instanceValue.isEnabled()) {
			instanceValue.setEnabled(false);
			instanceValue.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !instanceValue.isEnabled()) {
			instanceValue.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setInstanceValue(EObject newValue)
	 * @generated
	 */
	public void setInstanceValue(EObject newValue) {
    if (newValue != null) {
      instanceValue.setSelection(new StructuredSelection(newValue));
    } else {
      instanceValue.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.instanceValue);
    if (eefElementEditorReadOnlyState && instanceValue.isEnabled()) {
      instanceValue.setEnabled(false);
      instanceValue.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !instanceValue.isEnabled()) {
      instanceValue.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setInstanceValueButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInstanceValueButtonMode(ButtonsModeEnum newValue) {
		instanceValue.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterInstanceValue(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInstanceValue(ViewerFilter filter) {
    instanceValue.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterInstanceValue(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInstanceValue(ViewerFilter filter) {
    instanceValue.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getMin()
	 * @generated
	 */
	public EObject getMin() {
    return min.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initMin(EObjectFlatComboSettings)
	 */
	public void initMin(EObjectFlatComboSettings settings) {
		min.setInput(settings);
		if (current != null) {
			min.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.min);
		if (eefElementEditorReadOnlyState && min.isEnabled()) {
			min.setEnabled(false);
			min.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !min.isEnabled()) {
			min.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setMin(EObject newValue)
	 * @generated
	 */
	public void setMin(EObject newValue) {
    if (newValue != null) {
      min.setSelection(new StructuredSelection(newValue));
    } else {
      min.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.min);
    if (eefElementEditorReadOnlyState && min.isEnabled()) {
      min.setEnabled(false);
      min.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !min.isEnabled()) {
      min.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setMinButtonMode(ButtonsModeEnum newValue)
	 */
	public void setMinButtonMode(ButtonsModeEnum newValue) {
		min.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterMin(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToMin(ViewerFilter filter) {
    min.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterMin(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToMin(ViewerFilter filter) {
    min.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getInstance()
	 * @generated
	 */
	public EObject getInstance() {
    return instance.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initInstance(EObjectFlatComboSettings)
	 */
	public void initInstance(EObjectFlatComboSettings settings) {
		instance.setInput(settings);
		if (current != null) {
			instance.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.instance);
		if (eefElementEditorReadOnlyState && instance.isEnabled()) {
			instance.setEnabled(false);
			instance.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !instance.isEnabled()) {
			instance.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setInstance(EObject newValue)
	 * @generated
	 */
	public void setInstance(EObject newValue) {
    if (newValue != null) {
      instance.setSelection(new StructuredSelection(newValue));
    } else {
      instance.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.instance);
    if (eefElementEditorReadOnlyState && instance.isEnabled()) {
      instance.setEnabled(false);
      instance.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !instance.isEnabled()) {
      instance.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setInstanceButtonMode(ButtonsModeEnum newValue)
	 */
	public void setInstanceButtonMode(ButtonsModeEnum newValue) {
		instance.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterInstance(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToInstance(ViewerFilter filter) {
    instance.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterInstance(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToInstance(ViewerFilter filter) {
    instance.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getMax()
	 * @generated
	 */
	public EObject getMax() {
    return max.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initMax(EObjectFlatComboSettings)
	 */
	public void initMax(EObjectFlatComboSettings settings) {
		max.setInput(settings);
		if (current != null) {
			max.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.max);
		if (eefElementEditorReadOnlyState && max.isEnabled()) {
			max.setEnabled(false);
			max.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !max.isEnabled()) {
			max.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setMax(EObject newValue)
	 * @generated
	 */
	public void setMax(EObject newValue) {
    if (newValue != null) {
      max.setSelection(new StructuredSelection(newValue));
    } else {
      max.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.max);
    if (eefElementEditorReadOnlyState && max.isEnabled()) {
      max.setEnabled(false);
      max.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !max.isEnabled()) {
      max.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setMaxButtonMode(ButtonsModeEnum newValue)
	 */
	public void setMaxButtonMode(ButtonsModeEnum newValue) {
		max.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterMax(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToMax(ViewerFilter filter) {
    max.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterMax(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToMax(ViewerFilter filter) {
    max.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getEvent()
	 * @generated
	 */
	public EObject getEvent() {
    return event.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initEvent(EObjectFlatComboSettings)
	 */
	public void initEvent(EObjectFlatComboSettings settings) {
		event.setInput(settings);
		if (current != null) {
			event.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.event);
		if (eefElementEditorReadOnlyState && event.isEnabled()) {
			event.setEnabled(false);
			event.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !event.isEnabled()) {
			event.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setEvent(EObject newValue)
	 * @generated
	 */
	public void setEvent(EObject newValue) {
    if (newValue != null) {
      event.setSelection(new StructuredSelection(newValue));
    } else {
      event.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.event);
    if (eefElementEditorReadOnlyState && event.isEnabled()) {
      event.setEnabled(false);
      event.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !event.isEnabled()) {
      event.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setEventButtonMode(ButtonsModeEnum newValue)
	 */
	public void setEventButtonMode(ButtonsModeEnum newValue) {
		event.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterEvent(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToEvent(ViewerFilter filter) {
    event.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterEvent(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToEvent(ViewerFilter filter) {
    event.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getWhen()
	 * @generated
	 */
	public EObject getWhen() {
    return (EObject) when.getInput();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initWhen(EObjectFlatComboSettings)
	 */
	public void initWhen(EObjectFlatComboSettings settings) {
		when.setAdapterFactory(adapterFactory);
		when.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.when);
		if (eefElementEditorReadOnlyState && when.isEnabled()) {
			when.setEnabled(false);
			when.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !when.isEnabled()) {
			when.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setWhen(EObject newValue)
	 * @generated
	 */
	public void setWhen(EObject newValue) {
    when.refresh();
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.when);
    if (eefElementEditorReadOnlyState && when.isEnabled()) {
      when.setEnabled(false);
      when.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !when.isEnabled()) {
      when.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getChangeExpression()
	 * @generated
	 */
	public EObject getChangeExpression() {
    return (EObject) changeExpression.getInput();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initChangeExpression(EObjectFlatComboSettings)
	 */
	public void initChangeExpression(EObjectFlatComboSettings settings) {
		changeExpression.setAdapterFactory(adapterFactory);
		changeExpression.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.changeExpression);
		if (eefElementEditorReadOnlyState && changeExpression.isEnabled()) {
			changeExpression.setEnabled(false);
			changeExpression.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !changeExpression.isEnabled()) {
			changeExpression.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setChangeExpression(EObject newValue)
	 * @generated
	 */
	public void setChangeExpression(EObject newValue) {
    changeExpression.refresh();
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.changeExpression);
    if (eefElementEditorReadOnlyState && changeExpression.isEnabled()) {
      changeExpression.setEnabled(false);
      changeExpression.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !changeExpression.isEnabled()) {
      changeExpression.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getRegion()
	 * @generated
	 */
	public EObject getRegion() {
    return (EObject) region.getInput();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initRegion(EObjectFlatComboSettings)
	 */
	public void initRegion(EObjectFlatComboSettings settings) {
		region.setAdapterFactory(adapterFactory);
		region.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.region);
		if (eefElementEditorReadOnlyState && region.isEnabled()) {
			region.setEnabled(false);
			region.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !region.isEnabled()) {
			region.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setRegion(EObject newValue)
	 * @generated
	 */
	public void setRegion(EObject newValue) {
    region.refresh();
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.region);
    if (eefElementEditorReadOnlyState && region.isEnabled()) {
      region.setEnabled(false);
      region.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !region.isEnabled()) {
      region.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getBehaviour()
	 * @generated
	 */
	public EObject getBehaviour() {
    return behaviour.getSelection();
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initBehaviour(EObjectFlatComboSettings)
	 */
	public void initBehaviour(EObjectFlatComboSettings settings) {
		behaviour.setInput(settings);
		if (current != null) {
			behaviour.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.behaviour);
		if (eefElementEditorReadOnlyState && behaviour.isEnabled()) {
			behaviour.setEnabled(false);
			behaviour.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !behaviour.isEnabled()) {
			behaviour.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setBehaviour(EObject newValue)
	 * @generated
	 */
	public void setBehaviour(EObject newValue) {
    if (newValue != null) {
      behaviour.setSelection(new StructuredSelection(newValue));
    } else {
      behaviour.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.behaviour);
    if (eefElementEditorReadOnlyState && behaviour.isEnabled()) {
      behaviour.setEnabled(false);
      behaviour.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !behaviour.isEnabled()) {
      behaviour.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setBehaviourButtonMode(ButtonsModeEnum newValue)
	 */
	public void setBehaviourButtonMode(ButtonsModeEnum newValue) {
		behaviour.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterBehaviour(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToBehaviour(ViewerFilter filter) {
    behaviour.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterBehaviour(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToBehaviour(ViewerFilter filter) {
    behaviour.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getUnmarshall()
	 * @generated
	 */
	public Boolean getUnmarshall() {
    return Boolean.valueOf(unmarshall.getSelection());
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setUnmarshall(Boolean newValue)
	 * @generated
	 */
	public void setUnmarshall(Boolean newValue) {
    if (newValue != null) {
      unmarshall.setSelection(newValue.booleanValue());
    } else {
      unmarshall.setSelection(false);
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.unmarshall);
    if (eefElementEditorReadOnlyState && unmarshall.isEnabled()) {
      unmarshall.setEnabled(false);
      unmarshall.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !unmarshall.isEnabled()) {
      unmarshall.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getOperation()
	 * @generated
	 */
	public EObject getOperation() {
    if (operation.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) operation.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initOperation(EObjectFlatComboSettings)
	 */
	public void initOperation(EObjectFlatComboSettings settings) {
		operation.setInput(settings);
		if (current != null) {
			operation.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.operation);
		if (eefElementEditorReadOnlyState && operation.isEnabled()) {
			operation.setEnabled(false);
			operation.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !operation.isEnabled()) {
			operation.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setOperation(EObject newValue)
	 * @generated
	 */
	public void setOperation(EObject newValue) {
    if (newValue != null) {
      operation.setSelection(new StructuredSelection(newValue));
    } else {
      operation.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.operation);
    if (eefElementEditorReadOnlyState && operation.isEnabled()) {
      operation.setEnabled(false);
      operation.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !operation.isEnabled()) {
      operation.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setOperationButtonMode(ButtonsModeEnum newValue)
	 */
	public void setOperationButtonMode(ButtonsModeEnum newValue) {
		operation.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterOperation(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToOperation(ViewerFilter filter) {
    operation.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterOperation(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToOperation(ViewerFilter filter) {
    operation.addBusinessRuleFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#getSignal()
	 * @generated
	 */
	public EObject getSignal() {
    if (signal.getSelection() instanceof StructuredSelection) {
      Object firstElement = ((StructuredSelection) signal.getSelection()).getFirstElement();
      if (firstElement instanceof EObject)
        return (EObject) firstElement;
    }
    return null;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#initSignal(EObjectFlatComboSettings)
	 */
	public void initSignal(EObjectFlatComboSettings settings) {
		signal.setInput(settings);
		if (current != null) {
			signal.setSelection(new StructuredSelection(settings.getValue()));
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.signal);
		if (eefElementEditorReadOnlyState && signal.isEnabled()) {
			signal.setEnabled(false);
			signal.setToolTipText(UmlMessages.General_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !signal.isEnabled()) {
			signal.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setSignal(EObject newValue)
	 * @generated
	 */
	public void setSignal(EObject newValue) {
    if (newValue != null) {
      signal.setSelection(new StructuredSelection(newValue));
    } else {
      signal.setSelection(new StructuredSelection()); //$NON-NLS-1$
    }
    boolean eefElementEditorReadOnlyState = isReadOnly(UmlViewsRepository.General.signal);
    if (eefElementEditorReadOnlyState && signal.isEnabled()) {
      signal.setEnabled(false);
      signal.setToolTipText(UmlMessages.General_ReadOnly);
    } else if (!eefElementEditorReadOnlyState && !signal.isEnabled()) {
      signal.setEnabled(true);
    }	
    
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#setSignalButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSignalButtonMode(ButtonsModeEnum newValue) {
		signal.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addFilterSignal(ViewerFilter filter)
	 * @generated
	 */
	public void addFilterToSignal(ViewerFilter filter) {
    signal.addFilter(filter);
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart#addBusinessFilterSignal(ViewerFilter filter)
	 * @generated
	 */
	public void addBusinessFilterToSignal(ViewerFilter filter) {
    signal.addBusinessRuleFilter(filter);
  }






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * @generated
	 */
	public String getTitle() {
    return UmlMessages.General_Part_Title;
  }



}
