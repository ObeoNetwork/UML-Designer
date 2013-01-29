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
package org.eclipse.emf.eef.runtime.ui.widgets.referencestable;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;

/**
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class RelationshipsTargetingTableSettings implements ITableOperations {

	private NamedElement source;

	public RelationshipsTargetingTableSettings(EObject source) {
		this.source = (NamedElement) source;
	}

	public Object[] getValues() {
		List<Relationship> list = source.getRelationships();
		List<Relationship> result = new ArrayList<Relationship>();
		for (Relationship relationship : list) {
			if (relationship instanceof DirectedRelationship
					&& ((DirectedRelationship) relationship).getTargets()
							.contains(source))
				result.add(relationship);
			else if (relationship instanceof Association) {
				Property target = ((Association) relationship).getMemberEnds()
						.get(1);
				if (source.equals(target.getType()))
					result.add(relationship);
			}
		}
		return result.isEmpty() ? new Object[] { result } : result.toArray();

	}

	public void add(EObject newValue) {
		throw new UnsupportedOperationException();
	}

	public void remove(EObject valueToRemove) {
		throw new UnsupportedOperationException();
	}

	public Object choiceOfValues() {
		throw new UnsupportedOperationException();
	}
}
