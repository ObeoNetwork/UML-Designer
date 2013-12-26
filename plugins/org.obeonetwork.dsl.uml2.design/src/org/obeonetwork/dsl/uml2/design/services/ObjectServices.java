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
package org.obeonetwork.dsl.uml2.design.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValueSpecification;

/**
 * Utility services to manage object diagrams.
 * 
 * @author Melanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
 */
public class ObjectServices {

	/**
	 * Create a new instance value slot.
	 * 
	 * @param source
	 *            Instance under which slot will be created
	 * @param target
	 *            Instance referenced by the slot
	 * @param property
	 *            Property attached to the slot
	 */
	public void createSlot(InstanceSpecification source, InstanceSpecification target, Property property) {
		// Create new slot
		final Slot slot = UMLFactory.eINSTANCE.createSlot();
		slot.setDefiningFeature(property);
		source.getSlots().add(slot);

		// Set value
		createInstanceValue(target, slot);
	}

	/**
	 * Create an instance value.
	 * 
	 * @param instance
	 *            Instance referenced by the slot
	 * @param slot
	 *            Slot under which instance will be created
	 */
	private void createInstanceValue(InstanceSpecification instance, Slot slot) {
		// Set instance value
		final InstanceValue value = UMLFactory.eINSTANCE.createInstanceValue();
		value.setInstance(instance);
		slot.getValues().add(value);
	}

	public Set<Element> candidatesForSlot(InstanceSpecification anInstanceSpecification) {
		HashSet<Element> result = new HashSet<Element>();
		for (Classifier classifier : anInstanceSpecification.getClassifiers()) {
			result.add(classifier);
			result.addAll(classifier.getAllAttributes());
			for (Classifier superType : getSuperTypes(classifier)) {
				result.add(superType);
				result.addAll(superType.getAllAttributes());
			}
		}

		return result;
	}

	public Set<EObject> childrenExpressionForSlot(EObject eObject) {
		HashSet<EObject> result = new HashSet<EObject>();
		if (eObject instanceof Classifier) {
			HashSet<Classifier> classifiers = new HashSet<Classifier>();
			Classifier aClassifier = (Classifier)eObject;
			classifiers.add(aClassifier);
			classifiers.addAll(getSuperTypes(aClassifier));
			for (Classifier classifier : classifiers) {
				result.addAll(classifier.getAllAttributes());
			}
		}
		return result;
	}

	/**
	 * Create a new primitive slot.
	 * 
	 * @param source
	 *            Instance under which slot will be created
	 * @param property
	 *            Property attached to the slot
	 */
	public void createSlot(InstanceSpecification source, Property property) {
		// Create new slot
		final Slot slot = UMLFactory.eINSTANCE.createSlot();
		slot.setDefiningFeature(property);
		source.getSlots().add(slot);

		// Set value
		if (property.getType() instanceof PrimitiveType) {
			final String typeName = ((PrimitiveType)property.getType()).getName();
			ValueSpecification value = null;
			if ("Integer".equals(typeName)) {
				LiteralInteger aLiteralInteger = UMLFactory.eINSTANCE.createLiteralInteger();
				aLiteralInteger.setValue(Integer.parseInt(property.getDefault()));
				value = aLiteralInteger;
			} else if ("Boolean".equals(typeName)) {
				LiteralBoolean aLiteralBoolean = UMLFactory.eINSTANCE.createLiteralBoolean();
				aLiteralBoolean.setValue(Boolean.parseBoolean(property.getDefault()));
				value = aLiteralBoolean;
			} else if ("String".equals(typeName)) {
				LiteralString aLiteralString = UMLFactory.eINSTANCE.createLiteralString();
				aLiteralString.setValue(property.getDefault());
				value = aLiteralString;
			}
			// TODO: handle other case like Double, Float...
			if (value != null)
				slot.getValues().add(value);
		}
		refresh(source.getNearestPackage());
	}

	/**
	 * Refresh the representation associated to a package.
	 * 
	 * @param pkg
	 *            Package to refresh
	 */
	private void refresh(org.eclipse.uml2.uml.Package pkg) {
		// Refresh current representation
		// Get session
		final Session session = SessionManager.INSTANCE.getSession(pkg);
		// Get representation
		Object[] representations = DialectManager.INSTANCE.getRepresentations(pkg, session).toArray();
		if (representations.length > 0) {
			final DRepresentation diagram = (DRepresentation)representations[0];
			// Refresh current sequence diagram
			DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
		}
	}

	public boolean canCreateAnInstanceSlot(InstanceSpecification preSource, InstanceSpecification preTarget) {
		boolean result = !candidatesForInstanceSlot(preSource, preTarget).isEmpty();
		return result;
	}

	public Set<Property> candidatesForInstanceSlot(InstanceSpecification source, InstanceSpecification target) {
		Set<Property> candidates = new HashSet<Property>();

		for (Classifier sourceClassifier : source.getClassifiers()) {
			for (Classifier targetClassifier : target.getClassifiers()) {
				candidates.addAll(candidatesForInstanceSlot(sourceClassifier, targetClassifier));
			}
		}

		return candidates;
	}

	private static Set<Property> candidatesForInstanceSlot(Classifier source, Classifier target) {
		Set<Property> candidates = new HashSet<Property>();
		Set<Association> associations = new HashSet<Association>();

		associations.addAll(source.getAssociations());
		for (Classifier superType : getSuperTypes(source)) {
			associations.addAll(superType.getAssociations());
		}

		ClassDiagramServices classDiagramServices = new ClassDiagramServices();
		for (Association association : associations) {
			Property sourceAsso = classDiagramServices.getSource(association);
			Property targetAsso = classDiagramServices.getTarget(association);
			if (conformTo(source, sourceAsso.getType()) && conformTo(target, targetAsso.getType())) {
				if (association.getNavigableOwnedEnds().contains(sourceAsso)) {
					candidates.add(sourceAsso);
				} else if (association.getNavigableOwnedEnds().contains(targetAsso)) {
					candidates.add(targetAsso);
				}
			}
		}
		return candidates;
	}

	private static boolean conformTo(Type type1, Type type2) {

		boolean conform = false;

		Set<Type> allTypes1 = new HashSet<Type>();
		allTypes1.add(type1);
		if (type1 instanceof Classifier) {
			allTypes1.addAll(getSuperTypes((Classifier)type1));
		}

		Set<Type> allTypes2 = new HashSet<Type>();
		allTypes2.add(type2);
		if (type2 instanceof Classifier) {
			allTypes2.addAll(getSuperTypes((Classifier)type2));
		}

		for (Type oneOfType1 : allTypes1) {
			for (Type oneOfType2 : allTypes2) {
				if (oneOfType1.conformsTo(oneOfType2)) {
					conform = true;
					break;
				}
			}
			if (conform) {
				break;
			}
		}
		return conform;
	}

	private static Set<Classifier> getSuperTypes(Classifier type) {
		HashSet<Classifier> result = new HashSet<Classifier>();
		List<Classifier> generals = type.getGenerals();
		for (Classifier general : generals) {
			result.add(general);
			result.addAll(getSuperTypes(general));
		}
		for (Dependency dependency : type.getClientDependencies()) {
			if (dependency instanceof InterfaceRealization) {
				Interface contract = ((InterfaceRealization)dependency).getContract();
				result.add(contract);
				result.addAll(getSuperTypes(contract));
			}
		}
		return result;
	}
}
