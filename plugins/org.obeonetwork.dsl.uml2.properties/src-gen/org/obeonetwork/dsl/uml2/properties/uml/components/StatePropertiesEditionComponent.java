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
package org.obeonetwork.dsl.uml2.properties.uml.components;

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.State;
import org.obeonetwork.dsl.uml2.properties.uml.parts.GeneralPropertiesEditionPart;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.runtime.api.notify.EStructuralFeatureNotificationFilter;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.VisibilityKind;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.Diagnostician;

import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;

import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext.InstanciableTypeFilter;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;

import org.eclipse.uml2.types.TypesPackage;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class StatePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

    /**
     * @generated
     */

    public static String GENERAL_PART = "General"; //$NON-NLS-1$

    /**
     * Settings for entry EObjectFlatComboViewer
     */
    private EObjectFlatComboSettings entrySettings;

    /**
     * Settings for exit EObjectFlatComboViewer
     */
    private EObjectFlatComboSettings exitSettings;

    /**
     * Settings for do EObjectFlatComboViewer
     */
    private EObjectFlatComboSettings do_Settings;

    /**
     * Settings for submachine LinkEReferenceViewer
     */
    private EObjectFlatComboSettings submachineSettings;

    /**
     * Creation Settings for submachine LinkEReferenceViewer
     */
    private ReferencesTableSettings submachineCreateSettings;

    /**
     * Default constructor
     * 
     * @generated
     */
    public StatePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject state, String editing_mode) {
        super(editingContext, state, editing_mode);
        parts = new String[] { GENERAL_PART };
        repositoryKey = UmlViewsRepository.class;
        partKey = UmlViewsRepository.General.class;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object,
     *      int, org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.resource.ResourceSet)
     * @generated
     */
    public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
        setInitializing(true);
        if (editingPart != null && key == partKey) {
            editingPart.setContext(elt, allResource);

            final State state = (State) elt;
            final GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart) editingPart;
            // init values
            if (isAccessible(UmlViewsRepository.General.name))
                generalPart.setName(EEFConverterUtil.convertToString(TypesPackage.Literals.STRING, state.getName()));

            if (isAccessible(UmlViewsRepository.General.visibility)) {
                generalPart.initVisibility(EEFUtils.choiceOfValues(state, UMLPackage.eINSTANCE.getNamedElement_Visibility()), state.getVisibility());
            }
            if (isAccessible(UmlViewsRepository.General.entry)) {
                // init part
                entrySettings = new EObjectFlatComboSettings(state, UMLPackage.eINSTANCE.getState_Entry());
                generalPart.initEntry(entrySettings);
                // set the button mode
                generalPart.setEntryButtonMode(ButtonsModeEnum.CREATE);
            }
            if (isAccessible(UmlViewsRepository.General.exit)) {
                // init part
                exitSettings = new EObjectFlatComboSettings(state, UMLPackage.eINSTANCE.getState_Exit());
                generalPart.initExit(exitSettings);
                // set the button mode
                generalPart.setExitButtonMode(ButtonsModeEnum.CREATE);
            }
            if (isAccessible(UmlViewsRepository.General.do_)) {
                // init part
                do_Settings = new EObjectFlatComboSettings(state, UMLPackage.eINSTANCE.getState_DoActivity());
                generalPart.initDo_(do_Settings);
                // set the button mode
                generalPart.setDo_ButtonMode(ButtonsModeEnum.CREATE);
            }
            if (isAccessible(UmlViewsRepository.General.submachine)) {
                // init part
                submachineSettings = new EObjectFlatComboSettings(state, UMLPackage.eINSTANCE.getState_Submachine());
                submachineCreateSettings = new ReferencesTableSettings(getsubmachineCreateSettingsSource(), UMLPackage.eINSTANCE.getPackage_PackagedElement());
                generalPart.initSubmachine(submachineSettings);
                // set the button mode
                generalPart.setSubmachineButtonMode(ButtonsModeEnum.BROWSE);
            }
            // init filters

            // init values for referenced views

            // init filters for referenced views

        }
        setInitializing(false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
     */
    public EStructuralFeature associatedFeature(Object editorKey) {
        if (editorKey == UmlViewsRepository.General.name) {
            return UMLPackage.eINSTANCE.getNamedElement_Name();
        }
        if (editorKey == UmlViewsRepository.General.visibility) {
            return UMLPackage.eINSTANCE.getNamedElement_Visibility();
        }
        if (editorKey == UmlViewsRepository.General.entry) {
            return UMLPackage.eINSTANCE.getState_Entry();
        }
        if (editorKey == UmlViewsRepository.General.exit) {
            return UMLPackage.eINSTANCE.getState_Exit();
        }
        if (editorKey == UmlViewsRepository.General.do_) {
            return UMLPackage.eINSTANCE.getState_DoActivity();
        }
        if (editorKey == UmlViewsRepository.General.submachine) {
            return UMLPackage.eINSTANCE.getState_Submachine();
        }
        return super.associatedFeature(editorKey);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
     * @generated
     */
    public void updateSemanticModel(final IPropertiesEditionEvent event) {
        State state = (State) semanticObject;
        if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
            state.setName((java.lang.String) EEFConverterUtil.createFromString(TypesPackage.Literals.STRING, (String) event.getNewValue()));
        }
        if (UmlViewsRepository.General.visibility == event.getAffectedEditor()) {
            state.setVisibility((VisibilityKind) event.getNewValue());
        }
        if (UmlViewsRepository.General.entry == event.getAffectedEditor()) {
            if (event.getKind() == PropertiesEditionEvent.SET) {
                entrySettings.setToReference((Behavior) event.getNewValue());
            } else if (event.getKind() == PropertiesEditionEvent.ADD) {
                EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, entrySettings, editingContext.getAdapterFactory());
                PropertiesEditingProvider provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy policy = provider.getPolicy(context);
                    if (policy instanceof CreateEditingPolicy) {
                        policy.execute();
                    }
                }
            }
        }
        if (UmlViewsRepository.General.exit == event.getAffectedEditor()) {
            if (event.getKind() == PropertiesEditionEvent.SET) {
                exitSettings.setToReference((Behavior) event.getNewValue());
            } else if (event.getKind() == PropertiesEditionEvent.ADD) {
                EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, exitSettings, editingContext.getAdapterFactory());
                PropertiesEditingProvider provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy policy = provider.getPolicy(context);
                    if (policy instanceof CreateEditingPolicy) {
                        policy.execute();
                    }
                }
            }
        }
        if (UmlViewsRepository.General.do_ == event.getAffectedEditor()) {
            if (event.getKind() == PropertiesEditionEvent.SET) {
                do_Settings.setToReference((Behavior) event.getNewValue());
            } else if (event.getKind() == PropertiesEditionEvent.ADD) {
                EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, do_Settings, editingContext.getAdapterFactory());
                PropertiesEditingProvider provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy policy = provider.getPolicy(context);
                    if (policy instanceof CreateEditingPolicy) {
                        policy.execute();
                    }
                }
            }
        }
        if (UmlViewsRepository.General.submachine == event.getAffectedEditor()) {
            if (event.getKind() == PropertiesEditionEvent.SET) {
                submachineSettings.setToReference((StateMachine) event.getNewValue());
            } else if (event.getKind() == PropertiesEditionEvent.EDIT) {
                EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, (EObject) event.getNewValue(), editingContext.getAdapterFactory());
                PropertiesEditingProvider provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt((EObject) event.getNewValue(), PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
                    if (editionPolicy != null) {
                        editionPolicy.execute();
                    }
                }
            } else if (event.getKind() == PropertiesEditionEvent.ADD) {
                StateMachine eObject = UMLFactory.eINSTANCE.createStateMachine();
                EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, submachineCreateSettings, editingContext.getAdapterFactory());
                context.addInstanciableTypeFilter(new InstanciableTypeFilter() {
                    public boolean select(EClass instanciableType) {
                        return UMLPackage.Literals.STATE_MACHINE == instanciableType;
                    }
                });
                PropertiesEditingProvider provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy policy = provider.getPolicy(context);
                    if (policy != null) {
                        policy.execute();
                    }
                }
                submachineSettings.setToReference(context.getEObject());
                ((GeneralPropertiesEditionPart) editingPart).setSubmachine(context.getEObject());
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
     */
    public void updatePart(Notification msg) {
        super.updatePart(msg);
        if (editingPart.isVisible()) {
            GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart) editingPart;
            if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && generalPart != null
                    && isAccessible(UmlViewsRepository.General.name)) {
                if (msg.getNewValue() != null) {
                    generalPart.setName(EcoreUtil.convertToString(TypesPackage.Literals.STRING, msg.getNewValue()));
                } else {
                    generalPart.setName("");
                }
            }
            if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && isAccessible(UmlViewsRepository.General.visibility))
                generalPart.setVisibility((VisibilityKind) msg.getNewValue());

            if (UMLPackage.eINSTANCE.getState_Entry().equals(msg.getFeature()) && generalPart != null && isAccessible(UmlViewsRepository.General.entry))
                generalPart.setEntry((EObject) msg.getNewValue());
            if (UMLPackage.eINSTANCE.getState_Exit().equals(msg.getFeature()) && generalPart != null && isAccessible(UmlViewsRepository.General.exit))
                generalPart.setExit((EObject) msg.getNewValue());
            if (UMLPackage.eINSTANCE.getState_DoActivity().equals(msg.getFeature()) && generalPart != null && isAccessible(UmlViewsRepository.General.do_))
                generalPart.setDo_((EObject) msg.getNewValue());
            if (UMLPackage.eINSTANCE.getState_Submachine().equals(msg.getFeature()) && generalPart != null && isAccessible(UmlViewsRepository.General.submachine))
                generalPart.setSubmachine((EObject) msg.getNewValue());

        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
     */
    @Override
    protected NotificationFilter[] getNotificationFilters() {
        NotificationFilter filter = new EStructuralFeatureNotificationFilter(UMLPackage.eINSTANCE.getNamedElement_Name(), UMLPackage.eINSTANCE.getNamedElement_Visibility(),
                UMLPackage.eINSTANCE.getState_Entry(), UMLPackage.eINSTANCE.getState_Exit(), UMLPackage.eINSTANCE.getState_DoActivity(), UMLPackage.eINSTANCE.getState_Submachine());
        return new NotificationFilter[] { filter, };
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#mustBeComposed(java.lang.Object,
     *      int)
     */
    public boolean mustBeComposed(Object key, int kind) {
        return key == UmlViewsRepository.General.name || key == UmlViewsRepository.General.visibility || key == UmlViewsRepository.General.entry || key == UmlViewsRepository.General.exit
                || key == UmlViewsRepository.General.do_ || key == UmlViewsRepository.General.submachine;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
     * @generated
     */
    public Diagnostic validateValue(IPropertiesEditionEvent event) {
        Diagnostic ret = Diagnostic.OK_INSTANCE;
        if (event.getNewValue() != null) {
            try {
                if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
                    Object newValue = event.getNewValue();
                    if (newValue instanceof String) {
                        newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), (String) newValue);
                    }
                    ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
                }
                if (UmlViewsRepository.General.visibility == event.getAffectedEditor()) {
                    Object newValue = event.getNewValue();
                    if (newValue instanceof String) {
                        newValue = EEFConverterUtil.createFromString(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), (String) newValue);
                    }
                    ret = Diagnostician.INSTANCE.validate(UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
                }
            } catch (IllegalArgumentException iae) {
                ret = BasicDiagnostic.toDiagnostic(iae);
            } catch (WrappedException we) {
                ret = BasicDiagnostic.toDiagnostic(we);
            }
        }
        return ret;
    }

    /**
     * @ return source setting for submachineCreateSettings
     */
    public EObject getsubmachineCreateSettingsSource() {
        return org.obeonetwork.dsl.uml2.properties.service.EEFService.getParent(semanticObject);
    }

}
