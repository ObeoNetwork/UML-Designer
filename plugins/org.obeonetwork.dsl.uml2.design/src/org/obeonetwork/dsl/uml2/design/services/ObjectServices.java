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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValueSpecification;

import fr.obeo.dsl.viewpoint.DRepresentation;
import fr.obeo.dsl.viewpoint.business.api.dialect.DialectManager;
import fr.obeo.dsl.viewpoint.business.api.session.Session;
import fr.obeo.dsl.viewpoint.business.api.session.SessionManager;

/**
 * Utility services to manage object diagrams.
 * 
 * @author Mélanie Bats <a href="mailto:melanie.bats@obeo.fr">melanie.bats@obeo.fr</a>
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
		Slot slot = UMLFactory.eINSTANCE.createSlot();
		slot.setDefiningFeature(property);
		source.getSlots().add(slot);

		// Set value
		createInstanceValue(target, slot);
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
		Slot slot = UMLFactory.eINSTANCE.createSlot();
		slot.setDefiningFeature(property);
		source.getSlots().add(slot);

		// Set value
		if (((PrimitiveType)property.getType()) instanceof PrimitiveType) {
			String typeName = ((PrimitiveType)property.getType()).getName();
			ValueSpecification value = null;
			if ("Integer".equals(typeName)) {
				value = UMLFactory.eINSTANCE.createLiteralInteger();
			} else if ("Boolean".equals(typeName)) {
				value = UMLFactory.eINSTANCE.createLiteralBoolean();
			} else if ("String".equals(typeName)) {
				value = UMLFactory.eINSTANCE.createLiteralString();
			}
			if (value != null)
				slot.getValues().add(value);
		}
		refresh(source.getNearestPackage());
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
		InstanceValue value = UMLFactory.eINSTANCE.createInstanceValue();
		value.setInstance(instance);
		slot.getValues().add(value);
	}

	/**
	 * Create an instance specification.
	 * 
	 * @param pkg
	 *            Package under which instance will be created
	 * @param cl
	 *            Class to instanciate
	 */
	public void createInstance(org.eclipse.uml2.uml.Package pkg, org.eclipse.uml2.uml.Class cl) {
		// Create instance specification
		InstanceSpecification instance = UMLFactory.eINSTANCE.createInstanceSpecification();
		instance.setName("new" + cl.getName());
		instance.getClassifiers().add(cl);
		pkg.getPackagedElements().add(instance);

		refresh(pkg);
	}

	/**
	 * Create an instance specification.
	 * 
	 * @param container
	 *            Package under which instance will be created
	 * @param cl
	 *            Class to instanciate
	 */
	public void createInstance(InstanceSpecification container, Class cl) {
		createInstance(container.getNearestPackage(), cl);
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
		Session session = SessionManager.INSTANCE.getSession(pkg);
		// Get representation
		DRepresentation diagram = (DRepresentation)DialectManager.INSTANCE.getRepresentations(pkg, session)
				.toArray()[0];
		// Refresh current sequence diagram
		DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
	}
}
