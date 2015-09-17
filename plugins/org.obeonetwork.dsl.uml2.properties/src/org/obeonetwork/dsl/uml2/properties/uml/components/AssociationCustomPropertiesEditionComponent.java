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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

public class AssociationCustomPropertiesEditionComponent extends
		AssociationPropertiesEditionComponent {

	public AssociationCustomPropertiesEditionComponent(
			PropertiesEditingContext editingContext, EObject association,
			String editing_mode) {
		super(editingContext, association, editing_mode);
	}

	@Override
	public void updateSemanticModel(IPropertiesEditionEvent event) {
		super.updateSemanticModel(event);
		if (UmlViewsRepository.General.memberEnd == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.SET) {
				memberEndSettings.addToReference((EObject) event.getNewValue());
			}
		}
		if (event.getKind() == PropertiesEditionEvent.EDIT) {
			if (CustomUmlViewsRepository.General.memberEndNavigable == event
					.getAffectedEditor()) {
				boolean wasNavigable = (Boolean) event.getOldValue();

				Property property = (Property) event.getNewValue();
				Association association = property.getAssociation();
				if (wasNavigable) {
					if (association.getNavigableOwnedEnds().contains(property)) {
						association.getNavigableOwnedEnds().remove(property);
						// As the property was navigable, the owned checkbox is
						// checked by default
						association.getOwnedEnds().add(property);
					}
					property.setIsNavigable(false);
				} else {
					association.getNavigableOwnedEnds().add(property);
					property.setIsNavigable(true);
				}

			}
			if (CustomUmlViewsRepository.General.memberEndOwned == event
					.getAffectedEditor()) {
				boolean wasOwned = (Boolean) event.getOldValue();

				Property property = (Property) event.getNewValue();
				Association association = property.getAssociation();
				
				
				if (wasOwned) {
					((org.eclipse.uml2.uml.Class) property.getOtherEnd().getType())
							.getOwnedAttributes().add(property);
				} else {
					association.getOwnedEnds().add(property);
				}
			}
		}
	}
}
