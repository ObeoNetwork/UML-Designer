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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.obeonetwork.dsl.uml2.properties.uml.parts.CustomUmlViewsRepository;
import org.obeonetwork.dsl.uml2.properties.uml.parts.UmlViewsRepository;

public class AssociationCustomPropertiesEditionComponent extends AssociationPropertiesEditionComponent {

	public AssociationCustomPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject association, String editing_mode) {
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
			if (CustomUmlViewsRepository.General.memberEndNavigable == event.getAffectedEditor()) {
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
			if (CustomUmlViewsRepository.General.memberEndOwned == event.getAffectedEditor()) {
				boolean wasOwned = (Boolean) event.getOldValue();

				Property property = (Property) event.getNewValue();
				Association association = property.getAssociation();

				if (association.getMemberEnds().size()==2){
					if (wasOwned) {
						final ChangePropertyOwnerSwitch changeOwnerService= new ChangePropertyOwnerSwitch(property);
						changeOwnerService.doSwitch(property.getOtherEnd().getType());
					} else {
						association.getOwnedEnds().add(property);
					}
				}else if (association.getMemberEnds().size()>2){
					if (wasOwned) {
						final List <Type> types = new ArrayList<Type>();
						for (Property member : association.getMemberEnds()){
							if (member.getType()!=null && !member.equals(property)){
								types.add(member.getType());
							}
						}
						ListDialog dialog = new ListDialog(Display.getCurrent().getActiveShell());
						dialog.setTitle("Owner selection:");
						dialog.setMessage("Please select the new owner: ");
						dialog.setInput(types.toArray());
						dialog.setContentProvider(new ArrayContentProvider());
						dialog.setLabelProvider(new LabelProvider() {
							@Override
							public String getText(Object element) {
								return ((NamedElement)element).getName();
							}
						});
						dialog.setInitialSelections(new Object[]{types.get(0)});

						if (dialog.open() == Window.OK) {
							final Object[] selection = dialog.getResult();
							if (selection != null && selection.length == 1) {
								final ChangePropertyOwnerSwitch changeOwnerService= new ChangePropertyOwnerSwitch(property);
								changeOwnerService.doSwitch((EObject)selection[0]);
							}
						}
					} else {
						association.getOwnedEnds().add(property);
					}

				}
			}
		}
	}
}