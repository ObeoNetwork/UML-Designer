/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.uml.components;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;
import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ITableOperations;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.OperationsTableSettings;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLPackage;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.ProfilesPropertiesEditionPart;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.business.api.session.SessionManager;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class PackageProfilesPropertiesEditionComponent extends
		SinglePartPropertiesEditingComponent implements ITableOperations {

	public static String PROFILES_PART = "Profiles"; //$NON-NLS-1$

	/**
	 * Settings for appliedProfiles ReferencesTable
	 */
	protected OperationsTableSettings appliedProfilesSettings;

	/**
	 * Default constructor
	 */
	public PackageProfilesPropertiesEditionComponent(
			PropertiesEditingContext editingContext, EObject class_,
			String editing_mode) {
		super(editingContext, class_, editing_mode);
		parts = new String[] { PROFILES_PART };
		repositoryKey = CustomUmlViewsRepository.class;
		partKey = CustomUmlViewsRepository.Profiles.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object,
	 *      int, org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	public void initPart(Object key, int kind, EObject elt,
			ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);

			final ProfilesPropertiesEditionPart profilesPart = (ProfilesPropertiesEditionPart) editingPart;
			// init values
			if (isAccessible(CustomUmlViewsRepository.Profiles.appliedProfiles)) {
				appliedProfilesSettings = new OperationsTableSettings(
						semanticObject, this);
				profilesPart
						.initAppliedProfiles((OperationsTableSettings) appliedProfilesSettings);
			}
			// init filters
			if (isAccessible(CustomUmlViewsRepository.Profiles.appliedProfiles)) {
				profilesPart.addFilterToAppliedProfiles(new EObjectFilter(
						UMLPackage.Literals.PROFILE));
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
		// TODO To implement
		// if (editorKey ==
		// CustomUmlViewsRepository.Profiles.appliedProfiles) {
		// return UMLPackage.eINSTANCE.getClass_SuperClass();
		// }
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		Object newValue = event.getNewValue();
		if (CustomUmlViewsRepository.Profiles.appliedProfiles == event
				.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof NamedElement) {
					appliedProfilesSettings.addToReference((EObject) event
							.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				((OperationsTableSettings) appliedProfilesSettings)
						.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.EDIT) {
				AdapterFactory adapterFactory = getEditingContext()
						.getAdapterFactory();
				EObject element = (EObject) newValue;
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(
						getEditingContext(), this, element, getEditingContext()
								.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider) adapterFactory
						.adapt(element, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider
							.getPolicy(context);
					if (policy != null) {
						policy.execute();
					}
				}
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
			ProfilesPropertiesEditionPart stereotypesPart = (ProfilesPropertiesEditionPart) editingPart;
			// TODO TO implement
			// if (UMLPackage.eINSTANCE.getClass_SuperClass().equals(
			// msg.getFeature())
			// &&
			// isAccessible(CustomUmlViewsRepository.Profiles.appliedProfiles))
			// stereotypesPart.updateAppliedProfiles();
			if (isAccessible(CustomUmlViewsRepository.Profiles.appliedProfiles))
				stereotypesPart.updateAppliedProfiles();

		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
	 */
	@Override
	protected NotificationFilter[] getNotificationFilters() {
		// NotificationFilter filter = new EStructuralFeatureNotificationFilter(
		// UMLPackage.eINSTANCE.getClass_SuperClass());
		// return new NotificationFilter[] { filter, };
		// TODO To implpement
		return new NotificationFilter[] {};
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#isRequired(java.lang.Object,
	 *      int)
	 */
	public boolean isRequired(Object key, int kind) {
		return key == UmlViewsRepository.General.Qualifiers.abstract_
				|| key == UmlViewsRepository.General.Qualifiers.leaf;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
		Diagnostic ret = Diagnostic.OK_INSTANCE;
		if (event.getNewValue() != null) {
			try {
			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}

	@Override
	protected boolean shouldProcess(IPropertiesEditionEvent event) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] getValues() {
		List<Profile> list = ((Package) semanticObject).getAppliedProfiles();
		return list.isEmpty() ? new Object[] { list } : list.toArray();
	}

	/**
	 * {@inheritDoc}
	 */
	public void add(EObject newValue) {
		((Package) semanticObject).applyProfile((Profile) newValue);
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(EObject valueToRemove) {
		Package package_ = (Package) semanticObject;
		package_.unapplyProfile((Profile) valueToRemove);

		// FIXME Force the refresh of the diagram. If the semantic element is
		// not updated, the diagram is not refreshed and then the removed
		// profile still appears on the class even if does not exists anymore
		// in the semantic model. After a manual refresh, the removed stereotype
		// disappears. The workaround is to really "update" the semantic element
		// by changing its name and re-setting it immediately
		String name = package_.getName();
		package_.setName("Dummy");
		package_.setName(name);
	}

	/**
	 * {@inheritDoc}
	 */
	public Object choiceOfValues() {
		List<Profile> applicableProfiles = new ArrayList<Profile>();
		Package package_ = (Package) semanticObject;
		// Get all possible profiles
		Session session = SessionManager.INSTANCE.getSession(package_);
		if (session != null && session.getSemanticResources() != null) {
			for (Resource resource : session.getSemanticResources()) {
				applicableProfiles.addAll(Lists.newArrayList(Iterators.filter(
						resource.getContents().iterator(), Profile.class)));
			}
		}
		// Return only the profiles that are not applied
		List<Profile> appliedProfiles = package_.getAppliedProfiles();
		applicableProfiles.removeAll(appliedProfiles);
		return applicableProfiles;
	}
	//
	// private Predicate<EObject> packagePredicate = new Predicate<EObject>() {
	// public boolean apply(EObject eObj) {
	// String className = eObj.eClass().getName();
	// return "Package".equals(className);
	// }
	// };

}
