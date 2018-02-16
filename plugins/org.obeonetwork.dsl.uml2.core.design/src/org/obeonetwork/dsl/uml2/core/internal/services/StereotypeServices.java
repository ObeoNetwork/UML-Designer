/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.core.internal.services;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.Stereotype;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Services to handle typed Setereotype concerns.
 *
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class StereotypeServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final StereotypeServices INSTANCE = new StereotypeServices();

	/**
	 * Apply Profiles.
	 *
	 * @param pkg
	 *            Package
	 * @param profilesToApply
	 *            list of profile to apply to package
	 * @return The element on which profiles are applied
	 */
	public Package applyAllProfiles(Package pkg, List<Profile> profilesToApply) {
		final Session session = SessionManager.INSTANCE.getSession(pkg);
		final List<Profile> alreadyAppliedProfiles = pkg.getAppliedProfiles();
		for (final Profile profile : profilesToApply) {
			if (!alreadyAppliedProfiles.contains(profile)) {
				pkg.applyProfile(profile);
				// Register metamodel
				for (final ProfileApplication profileApplication : profile.getAllProfileApplications()) {
					final Set<MetamodelDescriptor> descriptorsForInterpreter = Sets.newLinkedHashSet();
					descriptorsForInterpreter
					.add(new EcoreMetamodelDescriptor(profileApplication.getAppliedDefinition()));
					session.getInterpreter().activateMetamodels(descriptorsForInterpreter);
				}
			}
		}
		return pkg;
	}

	/**
	 * Apply stereotypes.
	 *
	 * @param element
	 *            Element
	 * @param stereotypesToApply
	 *            Stereotyped to apply
	 * @return The element on which stereotypes are applied
	 */
	public Element applyAllStereotypes(Element element, List<Stereotype> stereotypesToApply) {
		final Session session = SessionManager.INSTANCE.getSession(element);
		// Unapplying not selected stereotypes
		final List<Stereotype> alreadyAppliedStereotypes = element.getAppliedStereotypes();
		for (final Stereotype alreadyAppliedStereotype : alreadyAppliedStereotypes) {
			if (stereotypesToApply == null || !stereotypesToApply.contains(alreadyAppliedStereotype)) {
				element.unapplyStereotype(alreadyAppliedStereotype);
				unapplyProfile(element, alreadyAppliedStereotype);
			}
		}

		if (stereotypesToApply == null) {
			return element;
		}
		// Applying selected stereotypes
		for (final Stereotype stereotypeToApply : stereotypesToApply) {
			final Profile profile = stereotypeToApply.getProfile();
			final Package pkg = element.getNearestPackage();
			if (!pkg.isProfileApplied(profile)) {
				pkg.applyProfile(profile);
				// Register metamodel
				for (final ProfileApplication profileApplication : profile.getAllProfileApplications()) {
					final Set<MetamodelDescriptor> descriptorsForInterpreter = Sets.newLinkedHashSet();
					descriptorsForInterpreter
					.add(new EcoreMetamodelDescriptor(profileApplication.getAppliedDefinition()));
					session.getInterpreter().activateMetamodels(descriptorsForInterpreter);
				}
			}
			if (!element.isStereotypeApplied(stereotypeToApply)) {
				element.applyStereotype(stereotypeToApply);
			}
		}
		return element;
	}

	/**
	 * Get base class associated to a stereotype application.
	 *
	 * @param stereotypeApplication
	 *            Stereotype application
	 * @return Base class
	 */
	public Element getBaseClass(EObject stereotypeApplication) {
		return (Element)stereotypeApplication
				.eGet(stereotypeApplication.eClass().getEStructuralFeature("base_Class")); //$NON-NLS-1$
	}

	/**
	 * Unapply a profile
	 *
	 * @param element
	 *            Element
	 * @param alreadyAppliedStereotype
	 *            Stereotype
	 */
	public void unapplyProfile(Element element, final Stereotype alreadyAppliedStereotype) {
		// If no other stereotype is used, unapply the profile
		final Profile profile = alreadyAppliedStereotype.getProfile();
		final Package pkg = element.getNearestPackage();
		final List<EObject> stereotypeApplications = Lists.newArrayList();

		final Iterator<Element> it = Iterators.filter(EcoreUtil.getAllProperContents(pkg, true),
				Element.class);
		while (it.hasNext()) {
			final Element cur = it.next();
			stereotypeApplications.addAll(cur.getStereotypeApplications());
		}

		for (final EObject stereotypeApplication : stereotypeApplications) {
			// Get base class
			final Element baseClass = getBaseClass(stereotypeApplication);
			// Get all applied stereotypes
			for (final Stereotype stereotype : baseClass.getAppliedStereotypes()) {
				// Get profile
				if (stereotype.getProfile().equals(profile)) {
					// If stereotypes are still applied return else unapply
					// profile on package
					return;
				}
			}
		}
		pkg.unapplyProfile(profile);
	}

	/**
	 * Unapply a profile for a Package.
	 *
	 * @param pkg
	 *            package
	 * @param appliedProfiles
	 *            profileto unapply
	 */
	public void unapplyProfile(Package pkg, List<Profile> appliedProfiles) {
		for (final Profile profile : appliedProfiles) {
			pkg.unapplyProfile(profile);
		}
	}

	/**
	 * Unapply selected stereotypes.
	 *
	 * @param element
	 *            UML element
	 * @param stereotypesToUnapply
	 *            list of stereotypes
	 */
	public void unapplyStereotypes(Element element, List<Stereotype> stereotypesToUnapply) {
		// Unapplying selected stereotypes
		final List<Stereotype> alreadyAppliedStereotypes = element.getAppliedStereotypes();
		for (final Stereotype stereotype : stereotypesToUnapply) {
			if (stereotype == null || alreadyAppliedStereotypes.contains(stereotype)) {
				element.unapplyStereotype(stereotype);
				unapplyProfile(element, stereotype);
			}
		}
	}
}
