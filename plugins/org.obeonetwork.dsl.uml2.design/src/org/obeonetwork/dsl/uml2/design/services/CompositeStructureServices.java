/*******************************************************************************
 * Copyright (c) 2009, 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Usage;

import fr.obeo.dsl.viewpoint.DEdge;
import fr.obeo.dsl.viewpoint.DNode;

/**
 * A set of services to handle the UML Composite Structure diagram.
 * 
 * @author Hugo Marchadour <a href="mailto:hugo.marchadour@obeo.fr">hugo.marchadour@obeo.fr</a>
 */
public class CompositeStructureServices {

	/**
	 * Find provided interfaces to add.
	 * 
	 * @param portView
	 *            The port view
	 * @param interfaces
	 *            selected interfaces
	 * @return the list of provided interfaces to add
	 */
	public List<Interface> findProvidedInterfacesToAdd(DNode portView, List<Interface> interfaces) {
		List<Interface> result = new ArrayList<Interface>(interfaces);
		List<DEdge> outgoingEdges = portView.getOutgoingEdges();
		for (Iterator<DEdge> iteratorEdges = outgoingEdges.iterator(); iteratorEdges.hasNext();) {
			EObject target = ((DEdge)iteratorEdges.next()).getTarget();

			if (target instanceof InterfaceRealization) {
				InterfaceRealization targetIR = (InterfaceRealization)target;
				EList<NamedElement> suppliers = targetIR.getSuppliers();
				for (Iterator<NamedElement> iteratorSuppliers = suppliers.iterator(); iteratorSuppliers
						.hasNext();) {
					NamedElement namedElement = (NamedElement)iteratorSuppliers.next();
					if (namedElement instanceof Interface) {
						result.remove(namedElement);
					}

				}
			}
		}
		return result;
	}

	/**
	 * Find interface realizations to delete.
	 * 
	 * @param portView
	 *            The port view
	 * @param interfaces
	 *            selected interfaces
	 * @return the list of interface realizations to delete
	 */
	public List<InterfaceRealization> findInterfaceRealizationsToDelete(DNode portView,
			List<Interface> interfaces) {
		List<InterfaceRealization> result = new ArrayList<InterfaceRealization>();
		List<DEdge> outgoingEdges = portView.getOutgoingEdges();
		for (Iterator<DEdge> iteratorEdges = outgoingEdges.iterator(); iteratorEdges.hasNext();) {
			EObject target = ((DEdge)iteratorEdges.next()).getTarget();

			if (target instanceof InterfaceRealization) {
				InterfaceRealization targetIR = (InterfaceRealization)target;
				EList<NamedElement> suppliers = targetIR.getSuppliers();
				for (Iterator<NamedElement> iteratorSuppliers = suppliers.iterator(); iteratorSuppliers
						.hasNext();) {
					NamedElement namedElement = (NamedElement)iteratorSuppliers.next();
					if (namedElement instanceof Interface) {
						Interface anInterface = (Interface)namedElement;
						if (!interfaces.contains(anInterface)) {
							result.add(targetIR);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * find required interfaces to add.
	 * 
	 * @param portView
	 *            The port view
	 * @param interfaces
	 *            selected interfaces
	 * @return the list of required interfaces to add
	 */
	public List<Interface> findRequiredInterfacesToAdd(DNode portView, List<Interface> interfaces) {
		List<Interface> result = new ArrayList<Interface>(interfaces);
		List<DEdge> incomingEdges = portView.getIncomingEdges();
		for (Iterator<DEdge> iteratorEdges = incomingEdges.iterator(); iteratorEdges.hasNext();) {
			EObject target = ((DEdge)iteratorEdges.next()).getTarget();

			if (target instanceof Usage) {
				Usage targetU = (Usage)target;
				EList<NamedElement> suppliers = targetU.getSuppliers();
				for (Iterator<NamedElement> iteratorSuppliers = suppliers.iterator(); iteratorSuppliers
						.hasNext();) {
					NamedElement namedElement = (NamedElement)iteratorSuppliers.next();
					if (namedElement instanceof Interface) {
						result.remove(namedElement);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Find usages to delete.
	 * 
	 * @param portView
	 *            The port view
	 * @param interfaces
	 *            selected interfaces
	 * @return the list of usages to delete
	 */
	public List<Usage> findUsagesToDelete(DNode portView, List<Interface> interfaces) {
		List<Usage> result = new ArrayList<Usage>();
		List<DEdge> incomingEdges = portView.getIncomingEdges();
		for (Iterator<DEdge> iteratorEdges = incomingEdges.iterator(); iteratorEdges.hasNext();) {
			EObject target = ((DEdge)iteratorEdges.next()).getTarget();

			if (target instanceof Usage) {
				Usage targetU = (Usage)target;
				EList<NamedElement> suppliers = targetU.getSuppliers();
				for (Iterator<NamedElement> iteratorSuppliers = suppliers.iterator(); iteratorSuppliers
						.hasNext();) {
					NamedElement namedElement = (NamedElement)iteratorSuppliers.next();
					if (namedElement instanceof Interface) {
						Interface anInterface = (Interface)namedElement;
						if (!interfaces.contains(anInterface)) {
							result.add(targetU);
						}
					}
				}
			}
		}
		return result;
	}
}
