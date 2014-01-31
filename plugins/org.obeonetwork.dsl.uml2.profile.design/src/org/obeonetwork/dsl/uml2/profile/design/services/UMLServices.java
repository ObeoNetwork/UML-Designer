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
package org.obeonetwork.dsl.uml2.profile.design.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.obeonetwork.dsl.uml2.design.services.LabelServices;
import org.obeonetwork.dsl.uml2.design.services.LogServices;

/**
 * UML services.
 * 
 * @author Mohamed-Lamine BOUKHANOUFA <a
 *         href="mailto:mohamed-lamine.boukhanoufa@obeo.fr"
 *         >mohamed-lamine.boukhanoufa@obeo.fr</a>
 */
public class UMLServices {
	private static final String ELEMENT = "Element";

	private static final String SOURCE = "source";

	private static final String TARGET = "target";

	private static final String ENDTYPE = "endType";

	private static ResourceSet resourceSet = new ResourceSetImpl();

	private static Resource resource = resourceSet.getResource(
			URI.createURI(UMLResource.UML_METAMODEL_URI), true);

	private static Model umlMetamodel = (Model) resource.getContents().get(0);

	/**
	 * Return the source of an Extra Association.
	 * 
	 * @param stereotype
	 *            the {@link Association} context
	 * @return first end of the Extra association
	 */
	public static Stereotype getStereotypeSource(final Stereotype stereotype) {
		if (stereotype.getAllAttributes() != null
				&& stereotype.getAllAttributes().size() > 1) {
			if (stereotype.getAllAttributes().get(1).getType() instanceof Stereotype)
				return (Stereotype) stereotype.getAllAttributes().get(1)
						.getType();
		}
		return null;
	}

	/**
	 * Return the target of an Extra Association.
	 * 
	 * @param stereotype
	 *            the {@link Association} context
	 * @return second end of the Extra association
	 */
	public static Stereotype getStereotypeTarget(final Stereotype stereotype) {
		if (stereotype.getAllAttributes() != null
				&& stereotype.getAllAttributes().size() > 2) {
			if (stereotype.getAllAttributes().get(2).getType() instanceof Stereotype)
				return (Stereotype) stereotype.getAllAttributes().get(2)
						.getType();
		}
		return null;
	}

	/**
	 * Return the extension of an element import.
	 * 
	 * @param extension
	 *            source of element import
	 * @return The found element import.
	 */
	public ElementImport getElementImport(final Extension extension) {
		final Profile profileOwner = getProfileOwner(extension);
		if (profileOwner.getMetaclassReference(extension.getMetaclass()) != null)
			return profileOwner.getMetaclassReference(extension.getMetaclass());

		return null;
	}

	/**
	 * Get the profile owner of this element.
	 * 
	 * @param umlElement
	 *            the element
	 * @return the profile
	 */
	public Profile getProfileOwner(final Element umlElement) {

		if (umlElement.getOwner() instanceof Profile)
			return (Profile) umlElement.getOwner();
		else
			return getProfileOwner(umlElement.getOwner());
	}

	/**
	 * Test if the class superClass is a super class of the class subClass.
	 * 
	 * @param subClass
	 *            the sub class
	 * @param superClass
	 *            the super class
	 * @return true if the element superClass is an super class of the element
	 *         subClass.
	 */
	public static boolean isSubClass(final Class subClass,
			final Class superClass) {

		for (Class superClassOfItem : subClass.getSuperClasses()) {

			if (superClassOfItem.equals(superClass)
					|| (!superClassOfItem.getName().equalsIgnoreCase(ELEMENT) && isSubClass(
							superClassOfItem, superClass)))
				return true;
		}
		return false;
	}

	/**
	 * Test if a property named : "propName" is in the reference list
	 * "Subsetted Property" of the "property" To Test. The reference list
	 * contents are of type reference References the properties of which this
	 * property is constrained to be a subset.
	 * 
	 * @param property
	 *            to test
	 * @param propName
	 *            the property name
	 * @return true if a property named : propNamethe is in the reference list
	 *         "Subsetted Property" of the property property to test, else
	 *         false.
	 */
	public static boolean isInSubSet(final Property property,
			final String propName) {
		boolean result = false;
		for (Property overProperty : property.getSubsettedProperties()) {
			if (overProperty.getName().equals(propName)) {
				result = true;
				break;
			} else {
				return isInSubSet(overProperty, propName);
			}
		}
		return result;
	}

	/**
	 * Test if the EObject parent_p is a super class of the EObject children_p.
	 * 
	 * @param parent_p
	 *            the superclass
	 * @param children_p
	 * @return true if the EObject parent_p is a super EObject of the class
	 *         children_p
	 */
	public boolean isSubClassOf(final EObject parent_p, final EObject children_p) {
		if (parent_p == children_p) {
			return true;
		}
		if (children_p instanceof Classifier) {
			for (Generalization generalizationInChild : ((Classifier) children_p)
					.getGeneralizations()) {
				final Classifier superClass = generalizationInChild
						.getGeneral();
				if (isSubClassOf(parent_p, superClass)) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Test if the the targetView is different from the sourceView of an edge
	 * mapping.
	 * 
	 * @param sourceView
	 *            the source
	 * @param targetView
	 *            the target
	 * @return true if the the targetView is different from the sourceView of an
	 *         edge mapping
	 */
	public boolean isValidForEdgeMapping(final AbstractDNode sourceView,
			final AbstractDNode targetView) {
		return sourceView != targetView;
	}

	/**
	 * Find all super class of the subClass.
	 * 
	 * @param subClass
	 *            the subClass
	 * @return a list of class
	 */
	public static List<Class> getAllSuperClasses(final Class subClass) {
		final List<Class> superClasses = new ArrayList<Class>();
		if (subClass.getName().equals(ELEMENT)) {
			superClasses.add(subClass);
			return superClasses;
		}

		for (Class superClass : subClass.getSuperClasses()) {
			if (!isIn(superClass, superClasses)) {
				superClasses.add(superClass);
				superClasses.addAll(getAllSuperClasses(superClass));
			}
		}
		return superClasses;
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
	public static boolean isIn(final Class propName, final List<Class> classes) {
		for (Class class1 : classes) {
			if (class1.getName().equals(propName.getName()))
				return true;
		}
		return false;
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
	public static Property getAttributeByName(final Class drMetaclassToTest,
			final String propertyName) {
		for (Property att : drMetaclassToTest.getAllAttributes()) {
			if (att.getName().equals(propertyName))
				return att;
		}
		return null;
	}

	/**
	 * Find the properties of the relationShip 'relationShipSubClasse' they have
	 * 'source' or 'target' properties in their 'Subsetted Property' reference
	 * list.
	 * 
	 * @param relationShipSubClasse
	 *            the relationShip
	 * @return a list of Property
	 */
	public static ArrayList<Property> getRelationShipSourceTargetPeroperties(
			final Class relationShipSubClasse) {
		Property sourceProp = null;
		Property targetProp = null;
		for (Property propertyToTest : relationShipSubClasse.getAttributes()) {
			if (UMLServices.isInSubSet(propertyToTest, SOURCE))
				sourceProp = propertyToTest;
			if (UMLServices.isInSubSet(propertyToTest, TARGET))
				targetProp = propertyToTest;
			if (sourceProp != null && targetProp != null)
				break;
		}
		if (sourceProp == null || targetProp == null) {
			for (Class superClass : UMLServices
					.getAllSuperClasses(relationShipSubClasse)) {
				for (Property propertyToTest : superClass.getAttributes()) {
					if (UMLServices.isInSubSet(propertyToTest, SOURCE))
						sourceProp = propertyToTest;
					if (UMLServices.isInSubSet(propertyToTest, TARGET))
						targetProp = propertyToTest;
					if (sourceProp != null && targetProp != null)
						break;
				}
			}
		}
		if (sourceProp == null) {
			sourceProp = UMLServices.getAttributeByName(relationShipSubClasse,
					SOURCE);
		}
		if (targetProp == null) {
			targetProp = UMLServices.getAttributeByName(relationShipSubClasse,
					TARGET);
		}
		if (sourceProp == null || targetProp == null) {
			sourceProp = UMLFactory.eINSTANCE.createProperty();
			targetProp = UMLFactory.eINSTANCE.createProperty();
			if (relationShipSubClasse.getName().equals("Extension")) {
				sourceProp
						.setType((Class) umlMetamodel.getMember("Stereotype"));
				sourceProp.setName("stereotype");
				targetProp.setType((Class) umlMetamodel.getMember("Class"));
				targetProp.setName("metaClass");
			}
			if (relationShipSubClasse.getName().equals("CommunicationPath")) {
				sourceProp.setType((Class) umlMetamodel
						.getMember("DeploymentTarget"));
				sourceProp.setName(ENDTYPE);
				targetProp.setType((Class) umlMetamodel
						.getMember("DeploymentTarget"));
				targetProp.setName(ENDTYPE);
			}
			if (relationShipSubClasse.getName().equals("AssociationClass")) {
				sourceProp
						.setType((Class) umlMetamodel.getMember("Classifier"));
				sourceProp.setName(ENDTYPE);
				targetProp
						.setType((Class) umlMetamodel.getMember("Classifier"));
				targetProp.setName(ENDTYPE);
			}
			if (relationShipSubClasse.getName().equals("Association")) {
				sourceProp.setType((Class) umlMetamodel.getMember("Type"));
				sourceProp.setName(ENDTYPE);
				targetProp.setType((Class) umlMetamodel.getMember("Type"));
				targetProp.setName(ENDTYPE);
			}
			if (relationShipSubClasse.getName().equals("Relationship")) {
				sourceProp.setType((Class) umlMetamodel.getMember(ELEMENT));
				sourceProp.setName("relatedElement");
				targetProp.setType((Class) umlMetamodel.getMember(ELEMENT));
				targetProp.setName("relatedElement");
			}
		}
		final ArrayList<Property> properties = new ArrayList<Property>();
		properties.add(sourceProp);
		properties.add(targetProp);
		return properties;

	}

	/**
	 * Compute the source label of for the Extra association defined by the
	 * stereotype "stereotype".
	 * 
	 * @param stereotype
	 *            the stereotype
	 * @return the label
	 */
	public String computeSourceLabel(final Stereotype stereotype) {
		final org.obeonetwork.dsl.uml2.design.services.LabelServices labelServices = new LabelServices();
		final ArrayList<Property> properties = getRelationShipSourceTargetPeroperties(getMetaclass(stereotype
				.getOwnedAttributes().get(0)));

		return labelServices.computeAssociationEndLabel(properties.get(0));
	}

	/**
	 * Compute the target label of for the Extra association defined by the
	 * stereotype "stereotype".
	 * 
	 * @param stereotype
	 *            the stereotype
	 * @return the label
	 */
	public String computeTargetLabel(final Stereotype stereotype) {
		final org.obeonetwork.dsl.uml2.design.services.LabelServices labelServices = new LabelServices();

		final ArrayList<Property> properties = getRelationShipSourceTargetPeroperties(getMetaclass(stereotype
				.getOwnedAttributes().get(0)));

		return labelServices.computeAssociationEndLabel(properties.get(1));
	}

	/**
	 * Get the metaclass indicated by the association of the property
	 * 'property'.
	 * 
	 * @param property
	 *            the property
	 * @return the metaclass
	 */
	public static Class getMetaclass(final Property property) {
		Extension extension = null;
		if (property.getAssociation() instanceof Extension) {
			try {
				extension = (Extension) property.getAssociation();
			} catch (final Exception e) {
				new LogServices().error("getMetaclass(" + property.getClass()
						+ ") not handled", e);
				throw new RuntimeException(e);
			}
		}
		return (extension != null) ? extension.getMetaclass() : null;
	}

}
