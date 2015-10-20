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
package org.obeonetwork.dsl.uml2.design.internal.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.obeonetwork.dsl.uml2.design.internal.dialogs.ProfileVersionDialog;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * Services to handle typed Profile concerns.
 *
 * @author Mohamed-Lamine BOUKHANOUFA <a href="mailto:mohamed-lamine.boukhanoufa@obeo.fr" >mohamed-lamine.
 *         boukhanoufa@obeo.fr</a>
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ProfileServices {
	/**
	 * A singleton instance to be accessed by other java services.
	 */
	public static final ProfileServices INSTANCE = new ProfileServices();

	/**
	 * Element.
	 */
	public static final String ELEMENT = "Element"; //$NON-NLS-1$

	private static final String SOURCE = "source"; //$NON-NLS-1$

	private static final String TARGET = "target"; //$NON-NLS-1$

	private static final String ENDTYPE = "endType"; //$NON-NLS-1$

	private static ResourceSet resourceSet = new ResourceSetImpl();

	private static Resource resource = resourceSet.getResource(URI.createURI(UMLResource.UML_METAMODEL_URI),
			true);

	private static Model umlMetamodel = (Model)resource.getContents().get(0);

	/**
	 * Hidden constructor.
	 */
	private ProfileServices() {

	}

	/**
	 * Define the profile root and all sub profile.
	 *
	 * @param rootProfile
	 *            to define
	 * @return true if defined, else false.
	 */
	public boolean defineAllProfiles(final Profile rootProfile) {
		return defineProfile(rootProfile, getAllSubProfiles(rootProfile));
	}

	/**
	 * Define and annotate the profile with the version profileVersionAnnotation.
	 *
	 * @param profile
	 *            to define.
	 * @param profileVersionAnnotation
	 *            to use for the annotation of the profile.
	 * @return true if defined, else false.
	 */
	private boolean defineAndAnnotateProfile(final Profile profile,
			final UMLDesignerProfileVersion profileVersionAnnotation) {
		boolean result = true;
		// TODO to review if we must remove the old definition of the profile
		// Remove the old definition of the root profile
		// if (rootProfile.getDefinition() != null) {
		// rootProfile.getEAnnotations().remove(rootProfile.getDefinition().eContainer());
		// }
		// Define the root profile
		if (profile.define() == null) {
			result = false;
		} else {
			final EPackage rootProfileDefinition = profile.getDefinition();
			// use this condition if the old definition of the profile is
			// removed
			// if (rootProfileDefinition
			// .getEAnnotation(UMLDesignerProfileVersion.UML_DESIGNER_PROFILE_EANNOTATION_SOURCE)
			// ==
			// null) {
			final EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			final UMLDesignerProfileVersion umlDesignerProfileVersion = new UMLDesignerProfileVersion();
			umlDesignerProfileVersion.initEAnnotationVersion(eAnnotation, profileVersionAnnotation);
			rootProfileDefinition.getEAnnotations().add(eAnnotation);
			// }
		}
		return result;
	}

	/**
	 * Define the profile and compute the version.
	 *
	 * @param rootProfile
	 *            to define.
	 * @param allContentProfile
	 *            to define.
	 * @return true if defined, else false.
	 */
	public boolean defineProfile(final Profile rootProfile, final List<Profile> allContentProfile) {
		boolean result = false;

		final ProfileVersionDialog versionDialog = new ProfileVersionDialog(
				PlatformUI.getWorkbench().getDisplay().getActiveShell(), rootProfile);
		versionDialog.open();

		if (versionDialog.getReturnCode() == IDialogConstants.OK_ID) {
			final UMLDesignerProfileVersion uMLDesignerProfileVersion = versionDialog
					.getUMLDesignerProfileVersion();

			// Remove the oldest definitions of the profile and all sub profiles
			// then redefine
			// the whole
			undefineProfile(rootProfile);

			result = defineAndAnnotateProfile(rootProfile, uMLDesignerProfileVersion);

			for (final Profile profile : allContentProfile) {

				result = defineAndAnnotateProfile(profile, uMLDesignerProfileVersion);
			}
		}
		return result;
	}

	/**
	 * Find all the sub profile of a given root profile.
	 *
	 * @param rootProfile
	 *            the root profile
	 * @return a list all sub profile.
	 */
	public List<Profile> getAllSubProfiles(final Profile rootProfile) {
		final List<Profile> allSubProfiles = Lists
				.newArrayList(Iterators.filter(rootProfile.eAllContents(), Profile.class));
		allSubProfiles.remove(rootProfile);

		return allSubProfiles;
	}

	/**
	 * Find all super class of the subClass.
	 *
	 * @param subClass
	 *            the subClass
	 * @return a list of class
	 */
	public List<Class> getAllSuperClasses(final Class subClass) {
		final List<Class> superClasses = new ArrayList<Class>();
		if (subClass.getName().equals(ELEMENT)) {
			superClasses.add(subClass);
			return superClasses;
		}

		for (final Class superClass : subClass.getSuperClasses()) {
			if (!isIn(superClass, superClasses)) {
				superClasses.add(superClass);
				superClasses.addAll(getAllSuperClasses(superClass));
			}
		}
		return superClasses;
	}

	/**
	 * Get the property named AttName owned by the class drMetaclassToTest.
	 *
	 * @param drMetaclassToTest
	 *            the class
	 * @param propertyName
	 *            the property name
	 * @return the property
	 */
	private Property getAttributeByName(final Class drMetaclassToTest, final String propertyName) {
		for (final Property att : drMetaclassToTest.getAllAttributes()) {
			if (att.getName().equals(propertyName)) {
				return att;
			}
		}
		return null;
	}

	/**
	 * Find the properties of the relationShip 'relationShipSubClasse' they have 'source' or 'target'
	 * properties in their 'Subsetted Property' reference list.
	 *
	 * @param relationShipSubClasse
	 *            the relationShip
	 * @return a list of Property
	 */
	public ArrayList<Property> getRelationShipSourceTargetPeroperties(final Class relationShipSubClasse) {
		Property sourceProp = null;
		Property targetProp = null;
		for (final Property propertyToTest : relationShipSubClasse.getAttributes()) {
			if (isInSubSet(propertyToTest, SOURCE)) {
				sourceProp = propertyToTest;
			}
			if (isInSubSet(propertyToTest, TARGET)) {
				targetProp = propertyToTest;
			}
			if (sourceProp != null && targetProp != null) {
				break;
			}
		}
		if (sourceProp == null || targetProp == null) {
			for (final Class superClass : getAllSuperClasses(relationShipSubClasse)) {
				for (final Property propertyToTest : superClass.getAttributes()) {
					if (isInSubSet(propertyToTest, SOURCE)) {
						sourceProp = propertyToTest;
					}
					if (isInSubSet(propertyToTest, TARGET)) {
						targetProp = propertyToTest;
					}
					if (sourceProp != null && targetProp != null) {
						break;
					}
				}
			}
		}
		if (sourceProp == null) {
			sourceProp = getAttributeByName(relationShipSubClasse, SOURCE);
		}
		if (targetProp == null) {
			targetProp = getAttributeByName(relationShipSubClasse, TARGET);
		}
		if (sourceProp == null || targetProp == null) {
			sourceProp = UMLFactory.eINSTANCE.createProperty();
			targetProp = UMLFactory.eINSTANCE.createProperty();
			if (relationShipSubClasse.getName().equals("Extension")) { //$NON-NLS-1$
				sourceProp.setType((Class)umlMetamodel.getMember("Stereotype")); //$NON-NLS-1$
				sourceProp.setName("stereotype"); //$NON-NLS-1$
				targetProp.setType((Class)umlMetamodel.getMember("Class")); //$NON-NLS-1$
				targetProp.setName("metaClass"); //$NON-NLS-1$
			}
			if (relationShipSubClasse.getName().equals("CommunicationPath")) { //$NON-NLS-1$
				sourceProp.setType((Class)umlMetamodel.getMember("DeploymentTarget")); //$NON-NLS-1$
				sourceProp.setName(ENDTYPE);
				targetProp.setType((Class)umlMetamodel.getMember("DeploymentTarget")); //$NON-NLS-1$
				targetProp.setName(ENDTYPE);
			}
			if (relationShipSubClasse.getName().equals("AssociationClass")) { //$NON-NLS-1$
				sourceProp.setType((Class)umlMetamodel.getMember("Classifier")); //$NON-NLS-1$
				sourceProp.setName(ENDTYPE);
				targetProp.setType((Class)umlMetamodel.getMember("Classifier")); //$NON-NLS-1$
				targetProp.setName(ENDTYPE);
			}
			if (relationShipSubClasse.getName().equals("Association")) { //$NON-NLS-1$
				sourceProp.setType((Class)umlMetamodel.getMember("Type")); //$NON-NLS-1$
				sourceProp.setName(ENDTYPE);
				targetProp.setType((Class)umlMetamodel.getMember("Type")); //$NON-NLS-1$
				targetProp.setName(ENDTYPE);
			}
			if (relationShipSubClasse.getName().equals("Relationship")) { //$NON-NLS-1$
				sourceProp.setType((Class)umlMetamodel.getMember(ELEMENT));
				sourceProp.setName("relatedElement"); //$NON-NLS-1$
				targetProp.setType((Class)umlMetamodel.getMember(ELEMENT));
				targetProp.setName("relatedElement"); //$NON-NLS-1$
			}
		}
		final ArrayList<Property> properties = new ArrayList<Property>();
		properties.add(sourceProp);
		properties.add(targetProp);
		return properties;

	}

	/**
	 * Test if the class named propName is in the list classes.
	 *
	 * @param propName
	 *            the class
	 * @param classes
	 *            the list of classes
	 * @return true if the class named propName is in the list classes
	 */
	public boolean isIn(final Class propName, final List<Class> classes) {
		for (final Class class1 : classes) {
			if (class1.getName().equals(propName.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Test if a property named : "propName" is in the reference list "Subsetted Property" of the "property"
	 * To Test. The reference list contents are of type reference References the properties of which this
	 * property is constrained to be a subset.
	 *
	 * @param property
	 *            to test
	 * @param propName
	 *            the property name
	 * @return true if a property named : propNamethe is in the reference list "Subsetted Property" of the
	 *         property property to test, else false.
	 */
	public boolean isInSubSet(final Property property, final String propName) {
		boolean result = false;
		for (final Property overProperty : property.getSubsettedProperties()) {
			if (overProperty.getName().equals(propName)) {
				result = true;
				break;
			}
			return isInSubSet(overProperty, propName);
		}
		return result;
	}

	/**
	 * Undefine the profile.
	 *
	 * @param rootProfile
	 *            to undefine
	 */
	public void undefineProfile(final Profile rootProfile) {
		if (rootProfile.getDefinition() != null) {
			final List<Profile> allContentProfile = getAllSubProfiles(rootProfile);
			rootProfile.getEAnnotations().remove(rootProfile.getDefinition().eContainer());
			for (final Profile profile : allContentProfile) {
				if (profile.getDefinition() != null) {
					profile.getEAnnotations().remove(profile.getDefinition().eContainer());
				}
			}
		}
	}
}
