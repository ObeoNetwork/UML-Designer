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
import org.eclipse.uml2.uml.Transition;
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

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.uml2.types.TypesPackage;

import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.TransitionKind;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Vertex;
import org.eclipse.uml2.uml.VisibilityKind;

import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

/**
 * @author <a href="mailto:cedric.brun@obeo.fr">Cédric Brun</a>
 * @generated
 */
public class TransitionPropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	/**
	 * @generated
	 */

	public static String GENERAL_PART = "General"; //$NON-NLS-1$

	/**
	 * Settings for trigger ReferencesTable
	 */
	protected ReferencesTableSettings triggerSettings;

	/**
	 * Settings for guard LinkEReferenceViewer
	 */
	private EObjectFlatComboSettings guardSettings;

	/**
	 * Creation Settings for guard LinkEReferenceViewer
	 */
	private ReferencesTableSettings guardCreateSettings;

	/**
	 * Settings for effect SingleCompositionEditor
	 */
	private EObjectFlatComboSettings effectSettings;

	/**
	 * Settings for source EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings sourceSettings;

	/**
	 * Settings for target EObjectFlatComboViewer
	 */
	private EObjectFlatComboSettings targetSettings;

	/**
	 * Settings for ownedRule ReferencesTable
	 */
	protected ReferencesTableSettings ownedRuleSettings;

	/**
	 * Default constructor
	 * 
	 * @generated
	 */
	public TransitionPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject transition,
			String editing_mode) {
		super(editingContext, transition, editing_mode);
		parts = new String[] {GENERAL_PART};
		repositoryKey = UmlViewsRepository.class;
		partKey = UmlViewsRepository.General.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object,
	 *      int, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.resource.ResourceSet)
	 * @generated
	 */
	public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);

			final Transition transition = (Transition)elt;
			final GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(UmlViewsRepository.General.name))
				generalPart.setName(
						EEFConverterUtil.convertToString(TypesPackage.Literals.STRING, transition.getName()));

			if (isAccessible(UmlViewsRepository.General.visibility)) {
				generalPart.initVisibility(
						EEFUtils.choiceOfValues(transition,
								UMLPackage.eINSTANCE.getNamedElement_Visibility()),
						transition.getVisibility());
			}
			generalPart.setLeaf(transition.isLeaf());

			if (isAccessible(UmlViewsRepository.General.kind)) {
				generalPart.initKind(
						EEFUtils.choiceOfValues(transition, UMLPackage.eINSTANCE.getTransition_Kind()),
						transition.getKind());
			}
			if (isAccessible(UmlViewsRepository.General.trigger)) {
				triggerSettings = new ReferencesTableSettings(transition,
						UMLPackage.eINSTANCE.getTransition_Trigger());
				generalPart.initTrigger(triggerSettings);
			}
			if (isAccessible(UmlViewsRepository.General.guard)) {
				// init part
				guardSettings = new EObjectFlatComboSettings(transition,
						UMLPackage.eINSTANCE.getTransition_Guard());
				guardCreateSettings = new ReferencesTableSettings(getguardCreateSettingsSource(),
						UMLPackage.eINSTANCE.getNamespace_OwnedRule());
				generalPart.initGuard(guardSettings);
				// set the button mode
				generalPart.setGuardButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.General.effect)) {
				// init part
				effectSettings = new EObjectFlatComboSettings(transition,
						UMLPackage.eINSTANCE.getTransition_Effect());
				generalPart.initEffect(effectSettings);
			}
			if (isAccessible(UmlViewsRepository.General.source)) {
				// init part
				sourceSettings = new EObjectFlatComboSettings(transition,
						UMLPackage.eINSTANCE.getTransition_Source());
				generalPart.initSource(sourceSettings);
				// set the button mode
				generalPart.setSourceButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.General.target)) {
				// init part
				targetSettings = new EObjectFlatComboSettings(transition,
						UMLPackage.eINSTANCE.getTransition_Target());
				generalPart.initTarget(targetSettings);
				// set the button mode
				generalPart.setTargetButtonMode(ButtonsModeEnum.BROWSE);
			}
			if (isAccessible(UmlViewsRepository.General.ownedRule)) {
				ownedRuleSettings = new ReferencesTableSettings(transition,
						UMLPackage.eINSTANCE.getNamespace_OwnedRule());
				generalPart.initOwnedRule(ownedRuleSettings);
			}
			// init filters

			if (isAccessible(UmlViewsRepository.General.trigger)) {
				generalPart.addFilterToTrigger(new ViewerFilter() {
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
					 *      java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) //$NON-NLS-1$
								|| (element instanceof Trigger);
					}

				});
			}

			if (isAccessible(UmlViewsRepository.General.ownedRule)) {
				generalPart.addFilterToOwnedRule(new ViewerFilter() {
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
					 *      java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) //$NON-NLS-1$
								|| (element instanceof Constraint);
					}

				});
			}
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
		if (editorKey == UmlViewsRepository.General.Qualifiers.leaf) {
			return UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf();
		}
		if (editorKey == UmlViewsRepository.General.kind) {
			return UMLPackage.eINSTANCE.getTransition_Kind();
		}
		if (editorKey == UmlViewsRepository.General.trigger) {
			return UMLPackage.eINSTANCE.getTransition_Trigger();
		}
		if (editorKey == UmlViewsRepository.General.guard) {
			return UMLPackage.eINSTANCE.getTransition_Guard();
		}
		if (editorKey == UmlViewsRepository.General.effect) {
			return UMLPackage.eINSTANCE.getTransition_Effect();
		}
		if (editorKey == UmlViewsRepository.General.source) {
			return UMLPackage.eINSTANCE.getTransition_Source();
		}
		if (editorKey == UmlViewsRepository.General.target) {
			return UMLPackage.eINSTANCE.getTransition_Target();
		}
		if (editorKey == UmlViewsRepository.General.ownedRule) {
			return UMLPackage.eINSTANCE.getNamespace_OwnedRule();
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
		Transition transition = (Transition)semanticObject;
		if (UmlViewsRepository.General.name == event.getAffectedEditor()) {
			transition.setName((java.lang.String)EEFConverterUtil
					.createFromString(TypesPackage.Literals.STRING, (String)event.getNewValue()));
		}
		if (UmlViewsRepository.General.visibility == event.getAffectedEditor()) {
			transition.setVisibility((VisibilityKind)event.getNewValue());
		}
		if (UmlViewsRepository.General.Qualifiers.leaf == event.getAffectedEditor()) {
			transition.setIsLeaf((Boolean)event.getNewValue());
		}
		if (UmlViewsRepository.General.kind == event.getAffectedEditor()) {
			transition.setKind((TransitionKind)event.getNewValue());
		}
		if (UmlViewsRepository.General.trigger == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(
						editingContext, this, triggerSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext
						.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.EDIT) {
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext,
						this, (EObject)event.getNewValue(), editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext
						.getAdapterFactory()
						.adapt((EObject)event.getNewValue(), PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
					if (editionPolicy != null) {
						editionPolicy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				triggerSettings.removeFromReference((EObject)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				triggerSettings.move(event.getNewIndex(), (Trigger)event.getNewValue());
			}
		}
		if (UmlViewsRepository.General.guard == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				guardSettings.setToReference((Constraint)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.EDIT) {
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext,
						this, (EObject)event.getNewValue(), editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext
						.getAdapterFactory()
						.adapt((EObject)event.getNewValue(), PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
					if (editionPolicy != null) {
						editionPolicy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				Constraint eObject = UMLFactory.eINSTANCE.createConstraint();
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(
						editingContext, this, guardCreateSettings, editingContext.getAdapterFactory());
				context.addInstanciableTypeFilter(new InstanciableTypeFilter() {
					public boolean select(EClass instanciableType) {
						return UMLPackage.Literals.CONSTRAINT == instanciableType;
					}
				});
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext
						.getAdapterFactory().adapt(eObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
				guardSettings.setToReference(context.getEObject());
				((GeneralPropertiesEditionPart)editingPart).setGuard(context.getEObject());
			}
		}
		if (UmlViewsRepository.General.effect == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.EDIT) {
				if (effectSettings.getValue() == "") {
					EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(
							editingContext, this, effectSettings, editingContext.getAdapterFactory());
					PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext
							.getAdapterFactory()
							.adapt(editingContext.getEObject(), PropertiesEditingProvider.class);
					Object result = null;
					if (provider != null) {
						PropertiesEditingPolicy policy = provider.getPolicy(context);
						if (policy instanceof CreateEditingPolicy) {
							policy.execute();
							result = ((CreateEditingPolicy)policy).getResult();
						}
					}
					if (result != null) {
						effectSettings.setToReference(result);
					}
				} else {
					EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(
							editingContext, this, (EObject)effectSettings.getValue(),
							editingContext.getAdapterFactory());
					PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext
							.getAdapterFactory()
							.adapt(effectSettings.getValue(), PropertiesEditingProvider.class);
					if (provider != null) {
						PropertiesEditingPolicy policy = provider.getPolicy(context);
						if (policy != null) {
							policy.execute();
						}
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.UNSET) {
				effectSettings.setToReference(null);
			}

		}
		if (UmlViewsRepository.General.source == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				sourceSettings.setToReference((Vertex)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(
						editingContext, this, sourceSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext
						.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.General.target == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				targetSettings.setToReference((Vertex)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(
						editingContext, this, targetSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext
						.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			}
		}
		if (UmlViewsRepository.General.ownedRule == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(
						editingContext, this, ownedRuleSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext
						.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.EDIT) {
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext,
						this, (EObject)event.getNewValue(), editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext
						.getAdapterFactory()
						.adapt((EObject)event.getNewValue(), PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
					if (editionPolicy != null) {
						editionPolicy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				ownedRuleSettings.removeFromReference((EObject)event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				ownedRuleSettings.move(event.getNewIndex(), (Constraint)event.getNewValue());
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
			GeneralPropertiesEditionPart generalPart = (GeneralPropertiesEditionPart)editingPart;
			if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature())
					&& msg.getNotifier().equals(semanticObject) && generalPart != null
					&& isAccessible(UmlViewsRepository.General.name)) {
				if (msg.getNewValue() != null) {
					generalPart.setName(
							EcoreUtil.convertToString(TypesPackage.Literals.STRING, msg.getNewValue()));
				} else {
					generalPart.setName("");
				}
			}
			if (UMLPackage.eINSTANCE.getNamedElement_Visibility().equals(msg.getFeature())
					&& msg.getNotifier().equals(semanticObject)
					&& isAccessible(UmlViewsRepository.General.visibility))
				generalPart.setVisibility((VisibilityKind)msg.getNewValue());

			if (UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().equals(msg.getFeature())
					&& msg.getNotifier().equals(semanticObject) && generalPart != null
					&& isAccessible(UmlViewsRepository.General.Qualifiers.leaf))
				generalPart.setLeaf((Boolean)msg.getNewValue());

			if (UMLPackage.eINSTANCE.getTransition_Kind().equals(msg.getFeature())
					&& msg.getNotifier().equals(semanticObject)
					&& isAccessible(UmlViewsRepository.General.kind))
				generalPart.setKind((TransitionKind)msg.getNewValue());

			if (UMLPackage.eINSTANCE.getTransition_Trigger().equals(msg.getFeature())
					&& isAccessible(UmlViewsRepository.General.trigger))
				generalPart.updateTrigger();
			if (UMLPackage.eINSTANCE.getTransition_Guard().equals(msg.getFeature()) && generalPart != null
					&& isAccessible(UmlViewsRepository.General.guard))
				generalPart.setGuard((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTransition_Effect().equals(msg.getFeature()) && generalPart != null
					&& isAccessible(UmlViewsRepository.General.effect))
				generalPart.setEffect((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTransition_Source().equals(msg.getFeature()) && generalPart != null
					&& isAccessible(UmlViewsRepository.General.source))
				generalPart.setSource((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getTransition_Target().equals(msg.getFeature()) && generalPart != null
					&& isAccessible(UmlViewsRepository.General.target))
				generalPart.setTarget((EObject)msg.getNewValue());
			if (UMLPackage.eINSTANCE.getNamespace_OwnedRule().equals(msg.getFeature())
					&& isAccessible(UmlViewsRepository.General.ownedRule))
				generalPart.updateOwnedRule();

		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
	 */
	@Override
	protected NotificationFilter[] getNotificationFilters() {
		NotificationFilter filter = new EStructuralFeatureNotificationFilter(
				UMLPackage.eINSTANCE.getNamedElement_Name(),
				UMLPackage.eINSTANCE.getNamedElement_Visibility(),
				UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf(),
				UMLPackage.eINSTANCE.getTransition_Kind(), UMLPackage.eINSTANCE.getTransition_Trigger(),
				UMLPackage.eINSTANCE.getTransition_Guard(), UMLPackage.eINSTANCE.getTransition_Effect(),
				UMLPackage.eINSTANCE.getTransition_Source(), UMLPackage.eINSTANCE.getTransition_Target(),
				UMLPackage.eINSTANCE.getNamespace_OwnedRule());
		return new NotificationFilter[] {filter,};
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#mustBeComposed(java.lang.Object,
	 *      int)
	 */
	public boolean mustBeComposed(Object key, int kind) {
		return key == UmlViewsRepository.General.name || key == UmlViewsRepository.General.visibility
				|| key == UmlViewsRepository.General.Qualifiers.leaf || key == UmlViewsRepository.General.kind
				|| key == UmlViewsRepository.General.trigger || key == UmlViewsRepository.General.guard
				|| key == UmlViewsRepository.General.effect || key == UmlViewsRepository.General.source
				|| key == UmlViewsRepository.General.target || key == UmlViewsRepository.General.ownedRule
				|| key == UmlViewsRepository.General.Qualifiers.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object,
	 *      int)
	 * @generated
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.General.Qualifiers.leaf || key == UmlViewsRepository.General.kind
				|| key == UmlViewsRepository.General.source || key == UmlViewsRepository.General.target;
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
						newValue = EEFConverterUtil.createFromString(
								UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(),
								(String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(
							UMLPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.General.visibility == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(
								UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(),
								(String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(
							UMLPackage.eINSTANCE.getNamedElement_Visibility().getEAttributeType(), newValue);
				}
				if (UmlViewsRepository.General.Qualifiers.leaf == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(
								UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(),
								(String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(
							UMLPackage.eINSTANCE.getRedefinableElement_IsLeaf().getEAttributeType(),
							newValue);
				}
				if (UmlViewsRepository.General.kind == event.getAffectedEditor()) {
					Object newValue = event.getNewValue();
					if (newValue instanceof String) {
						newValue = EEFConverterUtil.createFromString(
								UMLPackage.eINSTANCE.getTransition_Kind().getEAttributeType(),
								(String)newValue);
					}
					ret = Diagnostician.INSTANCE.validate(
							UMLPackage.eINSTANCE.getTransition_Kind().getEAttributeType(), newValue);
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
	 * @ return source setting for guardCreateSettings
	 */
	public EObject getguardCreateSettingsSource() {
		return org.obeonetwork.dsl.uml2.properties.service.EEFService.getParent(semanticObject);
	}

}
